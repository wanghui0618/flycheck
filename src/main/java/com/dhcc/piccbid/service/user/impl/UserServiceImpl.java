package com.dhcc.piccbid.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dao.role.RoleJDBCDao;
import com.dhcc.piccbid.dao.unit.UnitJDBCDao;
import com.dhcc.piccbid.dao.user.UserDao;
import com.dhcc.piccbid.dao.user.UserJdbcDao;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
import com.dhcc.piccbid.dto.user.UserDto;
import com.dhcc.piccbid.entity.procedure.Procedure;
import com.dhcc.piccbid.entity.role.Role;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.entity.user.UserTreeNode;
import com.dhcc.piccbid.entity.user.UserVo;
import com.dhcc.piccbid.service.procedure.ProcedureService;
import com.dhcc.piccbid.service.role.RoleService;
import com.dhcc.piccbid.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @version V1.0
 * @date 2019-01-09 12:41:56
 */
@Service("userService")
public class UserServiceImpl extends AbstractBaseService<User, String> implements UserService {
//创建用户时，默认密码‘123qwe’
private static final String PASSWORD="46f94c8de14fb36680850768ff1b7f2a";
    private UserDao userDao;

    @Resource
    private UserJdbcDao userJdbcDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UnitJDBCDao unitJDBCDao;
    @Autowired
    private RoleJDBCDao roleJDBCDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ProcedureService procedureService;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public PageModel list(UserDto dto) {
        Specification<User> spec = getListSpecification(dto);
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        Page<User> page = userDao.findAll(spec, dto.getPageModel().getPageable());
        dto.getPageModel().setPage(page);
        return dto.getPageModel();
    }

    protected Specification<User> getListSpecification(final UserDto dto) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
                final User user = dto.getUser();
                //构造查询条件
                if (user != null) {
                    if (!StringUtils.isNullOrEmpty(user.getName())) {
                        predicate.add(cb.like(root.get("user.name").as(String.class), user.getName()));
                    }
                    if (!StringUtils.isNullOrEmpty(user.getEmail())) {
                        predicate.add(cb.like(root.get("user.email").as(String.class), user.getEmail()));
                    }
                }
                Predicate[] pre = new Predicate[predicate.size()];
                query.orderBy(cb.asc(root.get("addDate").as(Date.class)));
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    @Override
    public User checkPassword(String userNamePage) {
        return userJdbcDao.findUser(userNamePage);
    }

    @Override
    public User checkUserPhone(User user) {

        return userJdbcDao.checkUserPhone(user);
    }

    @Override
    public User checkUserEmail(User user) {

        return userJdbcDao.checkUserEmail(user);
    }


    @Override
    public User checkUserEmail1(String email) {

        return userJdbcDao.checkUserEmail1(email);
    }

    @Override
    public Boolean updatePassword(String newpassPage, String id) {

        return userJdbcDao.updatePassword(newpassPage, id);

    }

    @Override
    public Boolean updateNewInformation(String newnamePage, String newphonePage, String newemailPage, String id) {

        return userJdbcDao.updateNewInformation(newnamePage, newphonePage, newemailPage, id);
    }

    @Override
    public User findById(User user) {

        return userJdbcDao.findById(user);
    }

    @Override
    public Boolean setNewPassword(String newPassword, String id) {

        return userJdbcDao.setNewPassword(newPassword, id);

    }

    @Override
    public User findUserById(String id) {
        return userJdbcDao.findUserById(id);
    }


    @Override
    public Boolean setAuditing(String id, String remark, String name) {
        return userJdbcDao.setAuditing(id, remark, name);
    }

    @Override
    public Boolean setReauditing(String id, String remark, String name) {
        return userJdbcDao.setReauditing(id, remark, name);
    }

    @Override
    public UserVo listUserVo(UserDto dto) {
        return userJdbcDao.listUserVo(dto);
    }

    @Override
    public UserVo query(UserDto dto) {
        return userJdbcDao.query(dto);
    }

    @Override
    public List<UserVo> latestLogin(UserDto dto) {

        return userJdbcDao.latestLogin(dto);
    }

    @Override
    public User checkUserLoginName(User user) {

        return userJdbcDao.checkUserLoginName(user);
    }

    @Override
    public String userTree(UserDto dto) {
        List<UserVo> users = users(dto);
        String jsonString = bulidTree(parseRoleComplex(users));
        return jsonString;
    }


    @Override
    public List<UserVo> users(UserDto dto) {
        userJdbcDao.userTree(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, UserVo.class, "b.id");
        List<UserVo> users = (List<UserVo>) dto.getPageModel().getPageData();
        return users;
    }

    //组装标准ztree树
    public List<UserTreeNode> parseRoleComplex(List<UserVo> users) {
        System.out.println(JSON.toJSONString(users));
        if (users != null && users.size() > 0) {
            List<UserTreeNode> treeNodesList = new ArrayList<>();
            //将Dictdiag的数据放到TreeNode里面
            for (UserVo user : users) {
                UserTreeNode treeNode = new UserTreeNode();
                treeNode.setId(user.getId());
                treeNode.setName(user.getName());
                treeNode.setParentId(user.getParentId());
                treeNode.setParentLeaf(user.getParentLeaf());
                treeNode.setAddDate(user.getAddDate());
                /*treeNode.setCityName(user.getCityName());*/
                treeNode.setEmail(user.getEmail());
                treeNode.setPhone(user.getPhone());
                treeNode.setUpdateDate(user.getUpdateDate());
                treeNode.setStatus(user.getStatus());
                treeNode.setLoginName(user.getLoginName());
                /*treeNode.setCityName(user.getCityName());*/
                /*treeNode.setPassword(user.getPassword());*/
                treeNode.setRemark(user.getRemark());
                treeNodesList.add(treeNode);
            }
            return treeNodesList;
        } else {
            return new ArrayList<>();
        }
    }

    public String bulidTree(List<UserTreeNode> treeNodes) {

        List<UserTreeNode> trees = new ArrayList<UserTreeNode>();

        for (UserTreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == null || "".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (UserTreeNode it : treeNodes) {
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<UserTreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return JSON.toJSONString(trees);
    }


    public void saveAutho(UserDto dto) {
        //这里的name存的是前端传过来的id
        String userId = dto.getUser().getName();
        String menuIds = dto.getUser().getId();
        //先删除后再插入
        userJdbcDao.deleteUserMenuAutho(userId);
        //插入数据库
        userJdbcDao.insertUserMenuAuhto(menuIds, userId);
    }


    public void saveDataAutho(UserDto dto) {
        //这里的name存的是前端传过来的id
        String userId = dto.getUser().getName();
        String menuIds = dto.getUser().getId();
        //先删除后再插入
        userJdbcDao.deleteUserDataAutho(userId);
        List<String> list = JSONObject.parseArray(menuIds, String.class);

        //如果前端没有勾选 则不用插入数据库
        if (list.size() != 0) {
            //插入数据库
            userJdbcDao.insertUserDataAuhto(menuIds, userId);
        }

    }

    public void saveButtonAutho(UserDto dto) {
        //这里的name存的是前端传过来的id
        String userId = dto.getUser().getName();
        String menuIds = dto.getUser().getId();
        //先删除后再插入
        userJdbcDao.deleteUserButtonAutho(userId);
        List<String> list = JSONObject.parseArray(menuIds, String.class);

        //如果前端没有勾选 则不用插入数据库
        if (list.size() != 0) {
            //插入数据库
            userJdbcDao.insertUserButtonAuhto(menuIds, userId);
        }

    }

    public PageModel listTreeTable(UserDto userDto) {
        userJdbcDao.roleTreeTable(userDto);
        PageModel pageModel = userDto.getPageModel();
        //pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, UserVo.class, "b.id");
        List<UserVo> users = (List<UserVo>) userDto.getPageModel().getPageData();
        //System.out.println("没有筛选之前" + JSON.toJSONString(users));
        //对数据库查出了的数据进行递归找到一个节点下的所有节点
        userDto.getPageModel().setPageData(users);
        return pageModel;
    }

    //返回某个节点下的所有子节点
    private List<UserVo> returnChildrenList(List<UserVo> users, String id, List<UserVo> userList) {
        for (UserVo user : users) {
            if (id.equals(user.getParentId())) {
                returnChildrenList(users, user.getId(), userList);
                userList.add(user);
            }
        }
        return userList;
    }

    private List<UserVo> addFirstNode(List<UserVo> userList, String id, List<UserVo> users) {
        for (UserVo user : users) {
            if (id.equals(user.getId()))
                userList.add(user);
        }
        return userList;
    }

    //添加初始权限--jpp
    @Override
    public void addInitialPermissions(User userNow) {
        userJdbcDao.addInitialPermissions(userNow);
    }

    @Override
    public void saveLogInformation(String id, Date loginTime, Date loginoutTime) {
        userJdbcDao.saveLogInformation(id, loginTime, loginoutTime);
    }

    @Override
    public void updateLoginStatus(String id) {
        userJdbcDao.updateLoginStatus(id);

    }

    @Override
    public void updateLoginoutStatus(String id) {
        userJdbcDao.updateLoginoutStatus(id);

    }

    @Override
    public void updatePhoto(String id, String photo) {
        userJdbcDao.updatePhoto(id, photo);
    }

    @Override
    public PageModel listNumber(UserDto dto) {
        userJdbcDao.listNumber(dto);
        return dto.getPageModel();
    }

    @Override
    public void deleteUserAuthority(String userId) {
        userJdbcDao.deleteUserAuthority(userId);
    }

    @Override
    public void roleChange(User user) {
        //根据id去
        String id = user.getId();
        //System.out.println("号码" + id);
        System.out.println(JSON.toJSONString(user));
        //变更之前的roleCode的权限
        List<String> roleAuthorityIdsBefore = userJdbcDao.getBeforeAhthorityByroleCode(id);
        //变更之后的roleCode的权限
        List<String> roleAuthorityIdsAfter = userJdbcDao.getAfterAhthorityByroleCode(id);
        List<String> userAuthorityIds = userJdbcDao.getAhthorityByUserId(id);
        //方法生成新的用户的权限id
        Map map = getNewUserAuthority(roleAuthorityIdsBefore, roleAuthorityIdsAfter, userAuthorityIds);
        // 更新用户授权表中数据
        userJdbcDao.updateUserAuthorityChanged(map, id);

    }

    @Override
    public String roleDelete(String id, String key) {

        //变更之前的roleCode的权限
        List<String> roleAuthorityIds = userJdbcDao.getAfterAhthorityByroleCode(id);
        // System.out.println("角色"+JSON.toJSONString(roleAuthorityIds));
        //获取该用户的权限
        List<String> userAuthorityIds = userJdbcDao.getAhthorityByUserId(id);
        //  System.out.println("用户"+JSON.toJSONString(userAuthorityIds));
        Map map = getNewUserAuthority(null, roleAuthorityIds, userAuthorityIds);

        String resultUserAuthorityIds = map.get(key).toString();
        // String userNewAhthority = JSON.toJSONString(resultUserAuthorityIds);

        //System.out.println("输出"+resultUserAuthorityIds);
        return resultUserAuthorityIds;

    }

    //角色变更后,之前可能没有角色，之后必须有角色，用户不一定有用户权限（菜单，数据，按钮）
    public Map getNewUserAuthority(List<String> roleAuthorityIdsBefore, List<String> roleAuthorityIdsAfter, List<String> userAuthorityIds) {
        Map map = new HashMap();
        List<String> menuUserIds;
        List<String> dataUserIds;
        List<String> buttonUserIds;
        String menuIdsBefore = "";
        String dataIdsBefore = "";
        String buttonIdsBefore = "";
        String menuIdsUser = "";
        String dataIdsUser = "";
        String buttonIdsUser = "";

        if (roleAuthorityIdsBefore != null) {
            if (roleAuthorityIdsBefore.size() != 0) {
                menuIdsBefore = roleAuthorityIdsBefore.get(0);
                dataIdsBefore = roleAuthorityIdsBefore.get(1);
                buttonIdsBefore = roleAuthorityIdsBefore.get(2);
            }
        }

        String menuIdsAfter = roleAuthorityIdsAfter.get(0);
        String dataIdsAfter = roleAuthorityIdsAfter.get(1);
        String buttonIdsAfter = roleAuthorityIdsAfter.get(2);

        if (userAuthorityIds != null) {
            if (userAuthorityIds.size() != 0) {
                menuIdsUser = userAuthorityIds.get(0);
                dataIdsUser = userAuthorityIds.get(1);
                buttonIdsUser = userAuthorityIds.get(2);
            }
        }
        if (roleAuthorityIdsBefore == null) {
            //System.out.println("删除角色" );
            menuUserIds = getOneNewUserAuthorityAfterDelete(menuIdsAfter, menuIdsUser);
            dataUserIds = getOneNewUserAuthorityAfterDelete(dataIdsAfter, dataIdsUser);
            buttonUserIds = getOneNewUserAuthorityAfterDelete(buttonIdsAfter, buttonIdsUser);
        } else if (roleAuthorityIdsBefore.size() != 0) { //获取具体的一个（菜单，数据，按钮）权限
            menuUserIds = getOneNewUserAuthority(menuIdsBefore, menuIdsAfter, menuIdsUser);
            dataUserIds = getOneNewUserAuthority(dataIdsBefore, dataIdsAfter, dataIdsUser);
            buttonUserIds = getOneNewUserAuthority(buttonIdsBefore, buttonIdsAfter, buttonIdsUser);
        } else {

            //角色信息变更由无角色信息（就是已经被删除的一个角色），变为有角色信息
            menuUserIds = getOneNewUserAuthorityAlreadyDelete(menuIdsAfter, menuIdsUser);
            dataUserIds = getOneNewUserAuthorityAlreadyDelete(dataIdsAfter, dataIdsUser);
            buttonUserIds = getOneNewUserAuthorityAlreadyDelete(buttonIdsAfter, buttonIdsUser);
        }

        map.put("menu", JSON.toJSONString(menuUserIds));
        map.put("data", JSON.toJSONString(dataUserIds));
        map.put("button", JSON.toJSONString(buttonUserIds));
        return map;
    }

    private List<String> getOneNewUserAuthorityAlreadyDelete(String menuIdsAfter, String menuIdsUser) {
        List<String> afterIds = JSONObject.parseArray(menuIdsAfter, String.class);
        List<String> userIds = JSONObject.parseArray(menuIdsUser, String.class);
        //删除afterIds
        userIds.addAll(afterIds);
        return userIds;
    }

    private List<String> getOneNewUserAuthority(String before, String after, String user) {
        List<String> beforeIds = JSONObject.parseArray(before, String.class);
        List<String> afterIds = JSONObject.parseArray(after, String.class);
        List<String> userIds = JSONObject.parseArray(user, String.class);
        //去掉之前的role的权限
        userIds.removeAll(beforeIds);
        //无重复取并集
        afterIds.removeAll(userIds);
        userIds.addAll(afterIds);
        return userIds;

    }

    private List<String> getOneNewUserAuthorityAfterDelete(String after, String user) {
        List<String> afterIds=new ArrayList<>();
        List<String> userIds=new ArrayList<>();
        if (!"".equals(user)) {
            afterIds = JSONObject.parseArray(after, String.class);
            userIds = JSONObject.parseArray(user, String.class);
        }
        //删除afterIds
        userIds.removeAll(afterIds);
        return userIds;
    }

    /* (non-Javadoc)
     * @see com.dhcc.piccbid.service.user.UserService#listNewLogin()
     */
    @Override
    public PageModel listNewLogin(UserDto dto, String num) {
        // TODO Auto-generated method stub
        return userJdbcDao.listNewLogin(dto, num);
    }
    @Override
    public void creatHospUsers(){
        //再次点击批量创建医院用户，删除所有的医院角色，角色授权表，所有医院用户
        //删除授权表中的相关的角色
        roleJDBCDao.deleteHospsAuthority();
       //删除用户表中为批量增加医院的用户
        userJdbcDao.deleteHospUsers();
       //删除角色表为批量增加的角色
        roleJDBCDao.deleteHospUsers();
        //查询出所有的医院
        List<Unit> units=unitJDBCDao.getAllHosps();
        //将医院对应的角色插入到“医院”下
        String hospId=roleJDBCDao.getHospId();
        if ("".equals(hospId)){
            //创建医院角色
            InsertHospRoleReturnRoleId(hospId,"yi_yuan","医院","1");
        }
        //这里必须前提创建医院角色,并且授权
        List<String> menuIdAndButtons=roleJDBCDao.getMenuAndButtonIds(hospId);
        String menuIds=menuIdAndButtons.get(0);
        String buttonIds=menuIdAndButtons.get(1);
        //遍历医院添加角色和用户
        for (Unit unit:units){
            String unitCode=unit.getUnitCode();
            String unitName=unit.getUnitName();
            String id=unit.getId();
            //插入角色库，根据医院名字创建角色
            //roleJDBCDao.insertOneHospRole(unitName,unitCode,hospId,"2");
            String roleId=InsertHospRoleReturnRoleId(hospId, unitCode, unitName,"2");
            //插入角色授权表  菜单权限
            roleJDBCDao.insertHospAuthority(menuIds,roleId,"1");
            //插入按钮权限
            roleJDBCDao.insertHospAuthority(buttonIds,roleId,"3");
            //插入数据权限
            roleJDBCDao.insertOneAuthoNew(roleId,id,unitCode,"9");
            //创建用户
            userJdbcDao.insertOneHosp(unitName,PASSWORD,unitCode,id,roleId);
        }
        //执行存储过程 统计类的结果
        ProcedureDto procedureDto=new ProcedureDto();
        procedureDto.setProcedure(new Procedure());
        procedureDto.getProcedure().setFlag("all");
        procedureService.check(procedureDto);
        procedureDto.getProcedure().setType("1");
        procedureService.executeAll(procedureDto);
    }

    private String InsertHospRoleReturnRoleId(String hospId, String unitCode, String unitName,String parentLeaf) {
        Role role =new Role();
        role.setRoleName(unitName);
        role.setRoleCode(unitCode);
        role.setParentId(hospId);
        role.setParentId(hospId);
        role.setParentLeaf(parentLeaf);
        role.setIsHospital("1");
        String hospRoleId= roleService.save(role).getId();
        return hospRoleId;
    }

}

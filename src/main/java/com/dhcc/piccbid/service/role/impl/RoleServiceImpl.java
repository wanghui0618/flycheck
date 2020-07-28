package com.dhcc.piccbid.service.role.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.role.RoleDao;
import com.dhcc.piccbid.dao.role.RoleJDBCDao;
import com.dhcc.piccbid.dto.role.RoleDto;
import com.dhcc.piccbid.entity.dictdiag.TreeNode;
import com.dhcc.piccbid.entity.role.Role;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.role.RoleService;
import com.dhcc.piccbid.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
 * @date 2019-01-14 15:42:21
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractBaseService<Role, String> implements RoleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RoleDao roleDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private RoleJDBCDao roleJDBCDao;
    @Autowired
    private UserService userService;

    public RoleServiceImpl(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public PageModel list(RoleDto dto) {
        Specification<Role> spec = getListSpecification(dto);
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        Page<Role> page = roleDao.findAll(spec, dto.getPageModel().getPageable());
        dto.getPageModel().setPage(page);
        return dto.getPageModel();
    }

    @Override
    public PageModel search(RoleDto dto) {
        roleJDBCDao.search(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, Role.class, "id");
        return dto.getPageModel();
    }

    protected Specification<Role> getListSpecification(final RoleDto dto) {
        return new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    @Override
    //返回数据库查询出来的list
    public String roleList(RoleDto roleDto) {
        List<Role> roles = roles(roleDto);
        List<TreeNode> treeNodes = bulid(parseRoleComplex(roles));
        return JSON.toJSONString(treeNodes);
    }


    public String roleAllList(RoleDto roleDto) {
        List<Role> roles = roles(roleDto);
        List<TreeNode> treeNodes = bulid(parseRoleComplex(roles));
        TreeNode treeNode = new TreeNode();
        treeNode.setChildren(treeNodes);
        treeNode.setName("全部角色");
        return JSON.toJSONString(treeNode);
    }

    @Override
    //返回数据库查询出来的list
    public List<Role> roles(RoleDto roleDto) {
        roleJDBCDao.roleList(roleDto);
        PageModel pageModel = roleDto.getPageModel();
        pageModel.setPageSize(10000);
        commonService.fillSqlPageModelData(pageModel, Role.class, "id");
        List<Role> roles = (List<Role>) roleDto.getPageModel().getPageData();
        return roles;
    }

    @Override
    public int isExist(RoleDto roleDto) {
        roleJDBCDao.roleCount(roleDto);
        PageModel pageModel = roleDto.getPageModel();
        // pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Role.class, "id");
        int count = roleDto.getPageModel().getTotals();
        return count;
    }

    @Override
    public void saveAutho(RoleDto dto) {
        String menuType = "1";
        //授权选择的角色id
        String roleId = dto.getRole().getRoleName();
        //授权的存入数据库中的id字符串
        String roleIds = dto.getRole().getId();
        //用户新勾选的权限，没有勾选传的是[]
        List<String> list = JSONObject.parseArray(roleIds, String.class);
        //判断是否需要更新跟角色相关的用户的菜单权限
        // isNeedUpdateUserAuthority(menuType, roleId, list);
        //先删除之前的权限
        roleJDBCDao.deleteAutho(roleId, menuType);
        //插入数据库
        //if (list.size() != 0) {
        roleJDBCDao.insertAutho(roleId, roleIds, menuType);
        // }

    }

    public void saveDataAutho(RoleDto dto) {
        String dataType = "9";
        //这里的name存的是前端传过来的id
        String roleId = dto.getRole().getRoleName();
        String menuIds = dto.getRole().getId();
        List<String> list = JSONObject.parseArray(menuIds, String.class);
        //判断是否需要更新跟角色相关的用户的数据权限
        //isNeedUpdateUserAuthority(dataType,roleId,list);
        //先删除后再插入
        roleJDBCDao.deleteAutho(roleId, dataType);
        List<String> HospOrAreaIdList = new ArrayList<>();
        for (String oneId : list) {
            Map maps = (Map) JSON.parse(oneId);
            String id = maps.get("id").toString();
            String parentLeaf = maps.get("parentLeaf").toString();
            if ("3-1".equals(parentLeaf) || "3-2".equals(parentLeaf)) {
                HospOrAreaIdList.add(id);
            }
        }
         List<String> removeAllList = new ArrayList<>();

        // Map map=new HashMap();
        // List cityListId=new ArrayList();
        for (String HospOrAreaId : HospOrAreaIdList) {
            //获取所有的id
            List<String> removeIds = roleJDBCDao.getRemoveIds(HospOrAreaId);
            Unit city = roleJDBCDao.getCity(HospOrAreaId);
            roleJDBCDao.insertOneAuthoNew(roleId, city.getId(), city.getUnitCode(), city.getParentLeaf());
            //cityListId.add(city.getId());
            // map.put(HospOrAreaId,city);
            removeAllList.addAll(removeIds);
            //把所有要移除的城市id都添加进去
            removeAllList.add(city.getId());
        }
        //插入数据库
        for (String oneId : list) {
            Map maps = (Map) JSON.parse(oneId);
            String id = maps.get("id").toString();
            String unitCode = maps.get("unitCode").toString();
            String parentLeaf = maps.get("parentLeaf").toString();
            //如果id没有包含在要移除的list里面，则插入数据库
            if (!removeAllList.contains(id)) {
                roleJDBCDao.insertOneAuthoNew(roleId, id, unitCode, parentLeaf);
            }

        }
    }

    public void saveRoleButtonAutho(RoleDto dto) {
        String buttonType = "3";
        //这里的name存的是前端传过来的id
        String roleId = dto.getRole().getRoleName();
        String menuIds = dto.getRole().getId();
        List<String> list = JSONObject.parseArray(menuIds, String.class);
        //判断是否需要更新跟角色相关的用户的数据权限
        // isNeedUpdateUserAuthority(buttonType,roleId,list);
        //先删除后再插入
        roleJDBCDao.deleteAutho(roleId, buttonType);
        //如果前端没有勾选 则不用插入数据库
        //if (list.size() != 0) {
        //插入数据库
        roleJDBCDao.insertAutho(roleId, menuIds, buttonType);
        //}

    }

    public PageModel listTreeTable(RoleDto roleDto) {
        List<Role> roleList = new ArrayList<>();
        String id = roleDto.getRole().getId();
        // System.out.println("id" + id);
        roleJDBCDao.roleTreeTable(roleDto);
        PageModel pageModel = roleDto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Role.class, "id");
        List<Role> roles = (List<Role>) roleDto.getPageModel().getPageData();
        //对数据库查出了的数据进行递归找到一个节点下的所有节点
        List<Role> rolesList = returnChildrenList(roles, id, roleList);
        List<Role> roleListAll = addFirstNode(rolesList, id, roles);
        // Collections.reverse(roleListAll);
        roleDto.getPageModel().setPageData(roleListAll);
        return pageModel;
    }

    @Override
    public PageModel listTreeTableNew(RoleDto roleDto) {
        roleJDBCDao.roleTreeTableNew(roleDto);
        PageModel pageModel = roleDto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, Role.class, "id");
        pageModel = roleDto.getPageModel();
        return pageModel;
    }

    public void deleteArray(String[] arrays) {
        roleJDBCDao.deleteArray(arrays);
    }

    public void deleteRoleAuthorityArrays(String[] arrays) {
        roleJDBCDao.deleteRoleAuthorityArrays(arrays);
    }

    public void deleteRoleAuthority(String id) {
        roleJDBCDao.deleteRoleAuthority(id);
    }

    @Override
    public void deleteUserAuthorityByRoleId(String id) {
        roleJDBCDao.deleteUserAuthorityByRoleId(id);
    }

    //组装标准ztree树
    public List<TreeNode> parseRoleComplex(List<Role> roles) {
        // System.out.println(JSON.toJSONString(roles));
        if (roles != null && roles.size() > 0) {
            List<TreeNode> treeNodesList = new ArrayList<>();
            //将Dictdiag的数据放到TreeNode里面
            for (Role role : roles) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(role.getId());
                treeNode.setRoleId(role.getId());
                treeNode.setRoleName(role.getRoleName());
                treeNode.setParentId(role.getParentId());
                treeNode.setParentLeaf(role.getParentLeaf());
                // treeNode.set
                treeNode.setName(role.getRoleName());
                treeNodesList.add(treeNode);
            }
            return treeNodesList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public List<TreeNode> bulid(List<TreeNode> treeNodes) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == null || "".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (TreeNode it : treeNodes) {
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    //返回某个节点下的所有子节点
    private List<Role> returnChildrenList(List<Role> roles, String id, List<Role> roleList) {
        for (Role role : roles) {
            if (id.equals(role.getParentId())) {
                returnChildrenList(roles, role.getId(), roleList);
                roleList.add(role);
            }
        }

        return roleList;
    }

    //添加点击后的第一级节点信息
    private List<Role> addFirstNode(List<Role> roleList, String id, List<Role> roles) {
        for (Role role : roles) {
            if (id.equals(role.getId()))
                roleList.add(role);
        }
        return roleList;
    }

    @Override
    public void deleteUserAuthority(String roleId) {
        //更新菜单权限
        String menuType = "1";
        String menuKey = "menu";
        String dataType = "2";
        String dataKey = "data";
        String buttonType = "3";
        String buttonKey = "button";
        //System.out.println("角色id" + roleId);
        //根据roleId 去用户表查询所有的用户
        List<String> users = roleJDBCDao.getUsers(roleId);
        int size = users.size();
        if (size == 0) {
            return;
        } else {
            Map map = new HashMap();
            for (int i = 0; i < size; i++) {
                map.put(users.get(i), "clob_" + i + "");
            }
            //更新用户表
            roleJDBCDao.updataUserAuthority(users, menuType, menuKey, map);
            //更新数据权限
            roleJDBCDao.updataUserAuthority(users, dataType, dataKey, map);
            //更新按钮权限
            roleJDBCDao.updataUserAuthority(users, buttonType, buttonKey, map);
        }
    }

    private void isNeedUpdateUserAuthority(String menuType, String roleId, List<String> list) {
        //找到之前的角色的菜单权限
        String menuString = roleJDBCDao.getRoleByIdAndType(roleId, menuType);
        List<String> menuList = JSONObject.parseArray(menuString, String.class);
        //System.out.println("之前的权限"+JSON.toJSONString(menuList));
        //判断两次的权限是否发生变化
        //两次权限发生变化
        if (list.size() != menuList.size() || (list.size() == menuList.size() && !list.containsAll(menuList))) {
            //找到改角色所关联的所有用户
            //  List<String> users = roleJDBCDao.getUsers(roleId);
            List<String> users = roleJDBCDao.getUsersWithOutAuding(roleId);
            int size = users.size();
            if (size != 0) {
                Map map = new HashMap();
                for (int i = 0; i < size; i++) {
                    map.put(users.get(i), "clob_" + i + "");
                }
                //更新菜单授权表
                roleJDBCDao.updataUserAuthorityFromRoleAhthoChanged(users, menuType, map, menuList, list);
            }
        }
    }

}

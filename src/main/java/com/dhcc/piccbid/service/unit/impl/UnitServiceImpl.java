package com.dhcc.piccbid.service.unit.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.web.context.WebContextHolder;
//import com.dhcc.piccbid.dao.cityorg.DictCityOrgJdbcDao;
import com.dhcc.piccbid.dao.unit.UnitDao;
import com.dhcc.piccbid.dao.unit.UnitJDBCDao;
import com.dhcc.piccbid.dao.user.UserJdbcDao;
import com.dhcc.piccbid.dto.unit.UnitDto;
//import com.dhcc.piccbid.entity.cityorg.DictCityOrg;
//import com.dhcc.piccbid.entity.handdingOrgAutho.HanddingOrgAutho;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.piccbid.entity.unit.UnitTreeNodes;
import com.dhcc.piccbid.entity.user.User;
//import com.dhcc.piccbid.service.handdingOrgAutho.HanddingOrgAuthoService;
import com.dhcc.piccbid.service.unit.UnitService;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @version V1.0
 * @date 2019-06-17 13:02:58
 */
@Service("unitService")
public class UnitServiceImpl extends AbstractBaseService<Unit, String> implements UnitService {
    private static final String NAME_INIT = "地市";

    private UnitDao unitDao;

    @Autowired
    private UnitJDBCDao unitJDBCDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UserJdbcDao userJdbcDao;
//    @Autowired
//    private DictCityOrgJdbcDao dictCityOrgJdbcDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private HanddingOrgAuthoService handdingOrgAuthoService;
    @Autowired
    private UnitService unitService;

    public UnitServiceImpl(UnitDao unitDao) {
        super(unitDao);
        this.unitDao = unitDao;
    }

    @Override
    public PageModel list(UnitDto dto) {
        unitJDBCDao.search(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        return dto.getPageModel();


    }

    public String unitAllList(UnitDto roleDto) {
        List<Unit> units = units(roleDto);
        //将所有的树节点查询出来
        List<UnitTreeNodes> treeNodes = bulid(parseRoleComplex(units));

        UnitTreeNodes treeNode = new UnitTreeNodes();
        treeNode.setChildren(treeNodes);
        treeNode.setName("全部组织");
        return JSON.toJSONString(treeNode);
    }

    public void synInfo(UnitDto dto) {
        unitJDBCDao.synInfo();
    }

    /**
     * 返回所有的组织集合
     *
     * @param dto
     * @return
     */
    @Override
    public List<Unit> units(UnitDto dto) {
        unitJDBCDao.unitList(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(50000);
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        List<Unit> units = (List<Unit>) dto.getPageModel().getPageData();
        return units;
    }

    @Override
    public PageModel search(UnitDto dto) {
        unitJDBCDao.search(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        return dto.getPageModel();
    }

  /*  @Override
    public PageModel listTreeTable(UnitDto dto) {
        List<Unit> roleList = new ArrayList<>();
        Unit unit = dto.getUnit();
        String id = dto.getUnit().getId();
        unitJDBCDao.roleTreeTable(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        List<Unit> units = (List<Unit>) dto.getPageModel().getPageData();
        //对数据库查出了的数据进行递归找到一个节点下的所有节点
        // List<Unit> rolesList = returnChildrenList(units, id, roleList);
        List<Unit> rolesList = returnChildrenList(units, id, roleList);
        List<Unit> roleListAll = addFirstNode(rolesList, id, units);
        //筛选按照查询条件查询出来的数据
        List<Unit> resultUnit = getNodesByLimit(roleListAll, unit);
        dto.getPageModel().setPageData(resultUnit);
        return pageModel;
    }  */

    @Override
    public PageModel listTreeTable(UnitDto dto) {
        // unitJDBCDao.roleTreeTable(dto);
        unitJDBCDao.roleTreeTableNew(dto);
        PageModel pageModel = dto.getPageModel();
        // pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        pageModel = dto.getPageModel();
        return pageModel;
    }

    private List<Unit> getNodesByLimit(List<Unit> roleListAll, Unit units) {
        List<Unit> unitList = new ArrayList<>();
        String unitName = units.getUnitName();
        String concat = units.getConcat();
        String phone = units.getPhone();
        if (!"".equals(unitName) && unitName != null || !"".equals(concat) && concat != null || !"".equals(phone) && phone != null) {
            for (Unit unit : roleListAll) {
                //如果数据库中查询出来为null，则将空字符串赋给他，避免空指针
                if (unit.getUnitName() == null) {
                    unit.setUnitName("");
                }
                if (unit.getConcat() == null) {
                    unit.setConcat("");
                }
                if (unit.getPhone() == null) {
                    unit.setPhone("");
                }
                if (unit.getUnitName().startsWith(unitName) && unit.getConcat().startsWith(concat) && unit.getPhone().startsWith(phone)) {
                    unitList.add(unit);
                }
            }
        } else {
            unitList = roleListAll;
        }

        return unitList;

    }

    @Override
    public PageModel listTreeTableForDelete(UnitDto dto) {
        List<Unit> roleList = new ArrayList<>();
        String id = dto.getUnit().getId();
        unitJDBCDao.roleTreeTable(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        List<Unit> units = (List<Unit>) dto.getPageModel().getPageData();
        // System.out.println("没有筛选之前" + JSON.toJSONString(units));
        //对数据库查出了的数据进行递归找到一个节点下的所有节点
        List<Unit> rolesList = returnChildrenListfForDelete(units, id, roleList);
        List<Unit> roleListAll = addFirstNodeForDelete(rolesList, id, units);
        // System.out.println("筛选之后" + JSON.toJSONString(rolesList));
        // Collections.reverse(roleListAll);
        dto.getPageModel().setPageData(roleListAll);
        return pageModel;
    }

    //返回某个节点下的所有子节点
    private List<Unit> returnChildrenList(List<Unit> roles, String id, List<Unit> roleList) {
        for (Unit unit : roles) {
            if (id.equals(unit.getParentId())) {
                returnChildrenList(roles, unit.getId(), roleList);
                //判断是否为一个组织，如果是组织则不会展示在table中
                if (!"1".equals(unit.getIsUnit())) {

                    roleList.add(unit);
                }
            }
        }

        return roleList;
    }


    //返回某个节点下的所有子节点
    private List<Unit> returnChildrenListfForDelete(List<Unit> roles, String id, List<Unit> roleList) {
        for (Unit unit : roles) {
            if (id.equals(unit.getParentId())) {
                returnChildrenList(roles, unit.getId(), roleList);
                roleList.add(unit);
            }
        }
        return roleList;
    }

    //添加点击后的第一级节点信息
    private List<Unit> addFirstNode(List<Unit> roleList, String id, List<Unit> roles) {
        for (Unit unit : roles) {
            if (id.equals(unit.getId()))
                //如果是组织架构那么展示点击处的第一级节点
                if (!"1".equals(unit.getIsUnit())) {
                    roleList.add(unit);
                }
        }
        return roleList;
    }

    //添加点击后的第一级节点信息(删除节点)
    private List<Unit> addFirstNodeForDelete(List<Unit> roleList, String id, List<Unit> roles) {
        for (Unit unit : roles) {
            if (id.equals(unit.getId()))
                //如果是组织架构那么展示点击处的第一级节点
                roleList.add(unit);

        }
        return roleList;
    }

    protected Specification<Unit> getListSpecification(final UnitDto dto) {
        return new Specification<Unit>() {
            @Override
            public Predicate toPredicate(Root<Unit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构�?�查询条�?

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public List<UnitTreeNodes> parseRoleComplex(List<Unit> units) {
        if (units != null && units.size() > 0) {
            List<UnitTreeNodes> treeNodesList = new ArrayList<>();
            for (Unit unit : units) {
                UnitTreeNodes treeNode = new UnitTreeNodes();
                treeNode.setId(unit.getId());
                treeNode.setConcat(unit.getConcat());
                //设置树节点的格式为名字+编码
                if ("1".equals(unit.getIsOauth())) {
                    treeNode.setName(unit.getUnitName() + "--" + unit.getUnitCode());
                }else {
                    treeNode.setName(unit.getUnitName());
                }
                treeNode.setUnitId(unit.getId());
                treeNode.setUnitCode(unit.getUnitCode());
                treeNode.setParentId(unit.getParentId());
                treeNode.setParentLeaf(unit.getParentLeaf());
                treeNode.setPhone(unit.getPhone());
                treeNode.setUnitName(unit.getUnitName());
                treeNode.setUnitAddress(unit.getUnitAddress());
                treeNode.setWebUrl(unit.getWebUrl());
                treeNode.setEmail(unit.getEmail());
                treeNode.setAddDate(unit.getAddDate());
                treeNode.setIsUnit(unit.getIsUnit());
                treeNode.setCityCode(unit.getCityCode());
                treeNode.setIsOauth(unit.getIsOauth());
                treeNodesList.add(treeNode);
            }
            return treeNodesList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     *
     */
    public List<UnitTreeNodes> bulid(List<UnitTreeNodes> treeNodes) {

        List<UnitTreeNodes> trees = new ArrayList<UnitTreeNodes>();

        for (UnitTreeNodes treeNode : treeNodes) {
            if (treeNode.getParentId() == null || "".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (UnitTreeNodes it : treeNodes) {
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<UnitTreeNodes>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    public List<UnitTreeNodes> bulidForDataAutho(List<UnitTreeNodes> treeNodes) {

        List<UnitTreeNodes> trees = new ArrayList<UnitTreeNodes>();
        //拿到组织架构下地市的以及作为数据授权
        String provinceParentId = unitJDBCDao.getParentId(NAME_INIT);
        for (UnitTreeNodes treeNode : treeNodes) {
            if (treeNode.getParentId().equals(provinceParentId)) {
                trees.add(treeNode);
            }
            for (UnitTreeNodes it : treeNodes) {
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<UnitTreeNodes>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }


    public void deleteArray(String[] arrays) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_piccbid_unit where id in (");
        int length = arrays.length;
        for (int i = 0; i <= length - 1; i++) {
            if (i == length - 1) {
                sql.append("'" + arrays[i] + "'" + ")");
            } else {
                sql.append("'" + arrays[i] + "'" + ",");
            }
        }
        jdbcTemplate.execute(sql.toString());
    }

    @Override
    public String dataAuthoList(UnitDto dto) {
        List<Unit> units = units(dto);
        List<UnitTreeNodes> treeNodes = parseRoleComplex(units);
        //获取数据库中用户已经授权的ids
        List<String> list = getAuthorityDataIdsStringByUserId(dto);
        List<UnitTreeNodes> treeNodeList = returnAuthorityData(list, treeNodes);
        List<UnitTreeNodes> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }

    @Override
    public String roleDataAuthoList(UnitDto dto) {
        List<Unit> units = units(dto);
        List<UnitTreeNodes> treeNodes = parseRoleComplex(units);
        //获取数据库中角色已经授权的ids
        List<String> list = getAuthorityDataIdsStringByRoleId(dto);
        List<UnitTreeNodes> treeNodeList = returnAuthorityData(list, treeNodes);
        List<UnitTreeNodes> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }

    //供pc端使用
    @Override
    public List<String> getUserDataAhthority() {
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        //去T_PICCBID_HANDDING_ORG_AUTHO表  查询已授权的数据权限  根据roleCode
        String roleId = user.getRoleId();
        return getHanddingOrgAuthoByUserId(roleId);
        //  return new ArrayList<>();

    }

    //供pc和app同时使用
    @Override
    public List<String> getUserDataAhthorityForApp(String loginId) {
        //如果是管理员，则不进行权限限制,如果session里面拿不到
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        //System.out.println("USER"+JSON.toJSONString(user));
        if (user!=null) {

                //去T_PICCBID_HANDDING_ORG_AUTHO表  查询已授权的数据权限  根据roleCode
                String roleId=user.getRoleId();
                return getHanddingOrgAuthoByUserId(roleId);

        }else {
            String roleId= userJdbcDao.getRoleCodeById(loginId);
                //return getUnitIdByUserId(loginId);
                return getHanddingOrgAuthoByUserId(roleId);
        }
     //   return new ArrayList<>();
    }


    //回显table表中选择行的用户的菜单id集合
    public List<String> getAuthorityDataIdsStringByUserId(UnitDto dto) {
        List<String> list = new ArrayList<>();
        //获取用户前端传过来的用户id
        String id = dto.getUnit().getId();
        String menuString = unitJDBCDao.selectIsAuthoData(id);
        if (!"".equals(menuString)) {
            list = JSONObject.parseArray(menuString, String.class);
        }
        return list;
    }
/*

    //根据以授权的用户去查询unitId
    public List<String> getUnitIdByUserId(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        //查询出已授权的id
        String ids = unitJDBCDao.selectIsRoleAuthoData(id);
        if (!"".equals(ids)) {
            list = JSONObject.parseArray(ids, String.class);
        }
        if (list.size() > 0) {
            for (String s : list) {
                stringBuilder.append("'");
                stringBuilder.append(s);
                stringBuilder.append("'");
                stringBuilder.append(",");
            }
            String string = stringBuilder.substring(0, stringBuilder.length() - 1);

            //根据id去查询unitId;
            return unitJDBCDao.selectunitIdById(string);
        } else {
            return new ArrayList<>();
        }
    }
*/


    //根据以授权的用户去查询unitId
    public List<String> getUnitIdByUserId(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        //查询出已授权的id （通过用户id） user_authority
        //String ids = unitJDBCDao.selectIsAuthoData(id);
        //查询出已授权的id （通过用户的角色去管控） role_authority
        String ids = unitJDBCDao.getRoleMenuIsAuthoByuserId(id);
        if (!"".equals(ids)) {
            list = JSONObject.parseArray(ids, String.class);
        }
        if (list.size() > 0) {
            for (String s : list) {
                stringBuilder.append("'");
                stringBuilder.append(s);
                stringBuilder.append("'");
                stringBuilder.append(",");
            }
            String string = stringBuilder.substring(0, stringBuilder.length() - 1);

            //根据id去查询unitId;
            return unitJDBCDao.selectunitIdById(string);
        } else {
            return new ArrayList<>();
        }
    }


    //根据以授权的用户去查询 授权的统筹区或者是医院（或者是城市）
    public List<String> getHanddingOrgAuthoByUserId(String roleId) {
        List<String> areaList;
        List<String> cityString;
        List<String> orgString;
       // List<String> removeList = new ArrayList<>();
      //  StringBuilder stringBuilder = new StringBuilder();
        List<String> resultList = new ArrayList<>();

        //根据角色去管控权限（查询除了勾选"医院"和"统筹区"以下的子节点以外的节点）
      //  List<String> list = unitJDBCDao.selectByRoleCode(roleId);
        //判断统筹区或者医院的父级id也是城市id  (根据这个取控制city_code)
        cityString = unitJDBCDao.getAreaOrHospAll(roleId);
       /* List<Unit> areaOrHospList = unitJDBCDao.getAreaOrHosp(roleId);
        for (Unit unit : areaOrHospList) {
            //根据parent_id 去unit表查询unit_code
            String result=unitJDBCDao.getUnitCodeById(unit.getParentId());
            cityString.add(result);
            String id = unit.getId();
            //获取全部医院和统筹区的id
            List<String> realAreaOrHosp = unitJDBCDao.getRealAreaOrHosp(id);
            removeList.addAll(realAreaOrHosp);
        }
        list.removeAll(removeList);*/

        //剩下的分类4-1 或者4-2
     /*   if (list.size() > 0) {
            for (String s : list) {
                stringBuilder.append("'");
                stringBuilder.append(s);
                stringBuilder.append("'");
                stringBuilder.append(",");
            }
            String string = stringBuilder.substring(0, stringBuilder.length() - 1);
        }*/

            areaList = unitJDBCDao.getAreaList(roleId);
            orgString = unitJDBCDao.getOrgString(roleId);
            resultList.add(JSON.toJSONString(areaList));
            resultList.add(JSON.toJSONString(cityString));
            resultList.add(JSON.toJSONString(orgString));
            //添加roleId
            resultList.add(roleId);
            //根据id去查询;
            return resultList;

    }

    /**
     * todo: 拼接权限管控的sql
     */
    public StringBuilder appendDataAuhoritySqlOld(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list) {
        StringBuilder stringBuilderResult = new StringBuilder();
        if (list.size() > 0) {
            for (String s : list) {
                stringBuilderResult.append("'");
                stringBuilderResult.append(s);
                stringBuilderResult.append("'");
                stringBuilderResult.append(",");
            }
            stringBuilderResult = stringBuilderResult.deleteCharAt(stringBuilderResult.length() - 1);
        }
        //说明没有权限限制则不进行拼接,此处可以理解为超级管理员不受权限管控
        if (!"".equals(stringBuilderResult.toString())) {
            // stringBuilder.append(stringBuilderResult);
            stringBuilder.append(" and ");
            appendInParam(limitAuthorityDict, stringBuilderResult, stringBuilder);
        }
        return stringBuilder;
    }

    /**
     * todo: 拼接权限管控的sql (统筹区和医院在一棵树上的情况)
     */
    public StringBuilder appendDataAuhoritySql(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list) {
        //list里面只有一个值说明是管理员则不进行权限限制
        if (list.size() == 1 || list.size() == 0) {
            return stringBuilder;
        } else {
            String areaString = list.get(0);
            String cityString = list.get(1);
            String hospString = list.get(2);
            List<String> areaList = JSONObject.parseArray(areaString, String.class);
            List<String> cityList = JSONObject.parseArray(cityString, String.class);
            List<String> hospList = JSONObject.parseArray(hospString, String.class);
            String sqlcity= "";
            String sqlArea= "";
            String sqlorg = "";
            String cityCodeFinal="";
            //根据‘，’去切分传入的需要授权的字段
            if (limitAuthorityDict.contains(",")) {
                String[] s = limitAuthorityDict.split(",");
                int size = s.length;
                for (int i = 0; i < size; i++) {
                    String temp = s[i];
                    if(temp.toLowerCase().contains("city_code")){
                       cityCodeFinal= temp;
                    }
                    if (temp.toLowerCase().contains("city_code")&&cityList.size()>0) {
                        sqlcity = getDifferentLimitNew(cityList, temp);
                    }
                    if (temp.toLowerCase().contains("handding_ins_code")&&areaList.size()>0) {
                        sqlArea = getDifferentLimitNew(areaList, temp);
                    }
                    if (temp.toLowerCase().contains("org_code")&&hospList.size()>0) {
                        sqlorg = getDifferentLimitNew(hospList, temp);
                    }
                }
            }else {
                if (limitAuthorityDict.toLowerCase().contains("city_code")){
                    cityCodeFinal=limitAuthorityDict ;
                }
                if (limitAuthorityDict.toLowerCase().contains("city_code")&&cityList.size()>0) {
                    sqlcity= getDifferentLimitNew(cityList, limitAuthorityDict);
                }
                if (limitAuthorityDict.toLowerCase().contains("handding_ins_code")&&areaList.size()>0) {
                    sqlArea = getDifferentLimitNew(areaList, limitAuthorityDict);
                }
                if (limitAuthorityDict.toLowerCase().contains("org_code")&&hospList.size()>0) {
                    sqlorg = getDifferentLimitNew(hospList, limitAuthorityDict);
                }
            }

        /*    //判断传过来的sql中有无 org_code,city_code,handding_org_code
            Boolean containArea = limitAuthorityDict.toLowerCase().contains("handding_ins_code");
            //返回格式
            String sqlArea = getDifferentLimit(containArea, areaList, tableName, "handding_ins_code");
            Boolean containCity = limitAuthorityDict.toLowerCase().contains("city_code");
            String sqlcity = getDifferentLimit(containCity, cityList, tableName, "city_code");
            Boolean containOrg = limitAuthorityDict.toLowerCase().contains("org_code");
            String sqlorg = getDifferentLimit(containOrg, hospList, tableName, "org_code");*/

            //用户只勾选了城市
            if (!"".equals(sqlcity) && "".equals(sqlArea) && "".equals(sqlorg)) {
                stringBuilder.append(" and ");
                stringBuilder.append(sqlcity);
            }
            //如果勾选了城市,勾选统筹区，没勾选医院
            if (!"".equals(sqlcity) && !"".equals(sqlArea)&&"".equals(sqlorg)) {
                stringBuilder.append(" and (");
                stringBuilder.append(sqlcity);
                stringBuilder.append(" or ");
                stringBuilder.append(sqlArea);
                stringBuilder.append(")");
            }
                 //全都勾选了
            if (!"".equals(sqlcity) && !"".equals(sqlArea)&&!"".equals(sqlorg)) {
                stringBuilder.append(" and (");
                stringBuilder.append(sqlcity);
                stringBuilder.append(" or ");
                stringBuilder.append(sqlArea);
                stringBuilder.append(" or ");
                stringBuilder.append(sqlorg);
                stringBuilder.append(")");
            }

            //如果选了统筹区和医院'
            if ("".equals(sqlcity)&&!"".equals(sqlArea)&&!"".equals(sqlorg) ) {
                stringBuilder.append(" and (");
                stringBuilder.append(sqlArea);
                stringBuilder.append(" or ");
                stringBuilder.append(sqlorg);
                stringBuilder.append(")");
            }
            //勾选了城市和医院
            if (!"".equals(sqlcity)&&"".equals(sqlArea)&&!"".equals(sqlorg) ) {
                stringBuilder.append(" and (");
                stringBuilder.append(sqlcity);
                stringBuilder.append(" or ");
                stringBuilder.append(sqlorg);
                stringBuilder.append(")");
            }

            //如果只勾选了医院
            if ("".equals(sqlcity) && "".equals(sqlArea) && !"".equals(sqlorg)) {
                stringBuilder.append(" and ");
                stringBuilder.append(sqlorg);
            }
            //如果只勾选了统筹区
            if ("".equals(sqlcity) && !"".equals(sqlArea) && "".equals(sqlorg)) {
                stringBuilder.append(" and ");
                stringBuilder.append(sqlArea);
            }
            //什么都没有勾选时,什么数据都无法查看
              if (("".equals(sqlcity) && "".equals(sqlArea) && "".equals(sqlorg))&&((cityList.size()==0)&&areaList.size()==0&&hospList.size()==0)) {
                  stringBuilder.append(" and 1=2");
              }
              //否则查看医院或者统筹区上面的城市
            if (("".equals(sqlcity) && "".equals(sqlArea) && "".equals(sqlorg))&&((cityList.size()==0)&&(areaList.size()!=0||hospList.size()!=0))) {
                List<String> cityListFinal=unitJDBCDao.getCityCodeByAreaCodeOrHospCode(list.get(3));
                sqlcity = getDifferentLimitNew(cityListFinal, cityCodeFinal);
                stringBuilder.append(" and ");
                stringBuilder.append(sqlcity);
            }
            return stringBuilder;
        }
    }

    private String getDifferentLimitNew( List<String> areaList, String tableAndCloum) {

        StringBuilder stringBuilderFinal = new StringBuilder();
        int length=areaList.size();
        if (length>1000) {
            //in 后面每次接1000条
            int count = 1000;
            int size = length % count;
            int loop;
            //如果有余数说明要多循环一次
            if (size>0){
           loop=length/count+1;
            }else {
            loop=length/count;
            }
            int indexStart;
            int indexEnd;
            for (int loopCount=0;loopCount<loop;loopCount++){
                StringBuilder stringBuilderResult = new StringBuilder();
                indexStart=loopCount*1000;
                indexEnd=(loopCount+1)*1000-1;
                //如果等于最后一次循环
                if (loopCount==loop-1){
                    indexStart=loopCount*1000;
                    if(size!=0){
                        indexEnd=loopCount*1000+size;
                    }
                    getAppendParam(areaList, stringBuilderResult, indexStart, indexEnd);
                    stringBuilderResult = stringBuilderResult.deleteCharAt(stringBuilderResult.length() - 1);
                }else {
                    getAppendParam(areaList, stringBuilderResult, indexStart, indexEnd);
                    stringBuilderResult = stringBuilderResult.deleteCharAt(stringBuilderResult.length() - 1);
                }
                //如果循环的不是第一次都用or连接
                if (loopCount!=0){
                    stringBuilderFinal.append(" or ");
                    appendInParam(tableAndCloum, stringBuilderResult, stringBuilderFinal);
                }else {
                    appendInParam(tableAndCloum, stringBuilderResult, stringBuilderFinal);
                }
            }
            return stringBuilderFinal.toString();
        }else {
            StringBuilder  stringBuilderResult=new StringBuilder();
            return getOracleInSqlString(areaList, tableAndCloum, stringBuilderResult, stringBuilderFinal);
        }
    }

    private void appendInParam(String tableAndCloum, StringBuilder stringBuilderResult, StringBuilder stringBuilderFinal) {
        stringBuilderFinal.append(tableAndCloum);
        stringBuilderFinal.append(" in (");
        stringBuilderFinal.append(stringBuilderResult);
        stringBuilderFinal.append(")");
    }

    private void getAppendParam(List<String> areaList, StringBuilder stringBuilderResult, int indexStart, int indexEnd) {
        for (int oneLoop = indexStart; oneLoop < indexEnd; oneLoop++) {
            stringBuilderResult.append("'");
            stringBuilderResult.append(areaList.get(oneLoop));
            stringBuilderResult.append("'");
            stringBuilderResult.append(",");
        }
    }

    private String getOracleInSqlString(List<String> areaList, String tableAndCloum, StringBuilder stringBuilderResult, StringBuilder stringBuilderFinal) {
        if (areaList.size() > 0) {
            int size=areaList.size();
            getAppendParam(areaList, stringBuilderResult, 0, size);
            stringBuilderResult = stringBuilderResult.deleteCharAt(stringBuilderResult.length() - 1);
        }

        //说明没有权限限制则不进行拼接,此处可以理解为超级管理员不受权限管控
        if (!"".equals(stringBuilderResult.toString())) {
            // stringBuilder.append(stringBuilderResult);
            // stringBuilder.append(" and ");
            appendInParam(tableAndCloum, stringBuilderResult, stringBuilderFinal);
        }
        return stringBuilderFinal.toString();
    }


    //回显table表中选择行的用户的菜单id集合
    public List<String> getAuthorityDataIdsStringByRoleId(UnitDto dto) {
        //List<String> list = new ArrayList<>();
        //获取用户前端传过来的用户id
        String id = dto.getUnit().getId();
        //String menuString = unitJDBCDao.selectIsRoleAuthoData(id);
        List<String> listData = unitJDBCDao.selectIsRoleAuthoDataForUnit(id);
        //  System.out.println("角色——————");
        //  System.out.println(menuString);
        return listData;
    }

    //数据授权的回显,checked设为true表示数据授权树上节点已选择
    public List<UnitTreeNodes> returnAuthorityData(List<String> list, List<UnitTreeNodes> treeNodes) {
        for (UnitTreeNodes treeNode : treeNodes) {
            for (String s : list) {
                if (treeNode.getId().equals(s)) {
                    treeNode.setChecked("true");
                }
            }
        }
        return treeNodes;
    }

    @Override
    public int isExist(UnitDto dto) {
        unitJDBCDao.roleCount(dto);
        PageModel pageModel = dto.getPageModel();
        // pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        int count = dto.getPageModel().getTotals();
        return count;
    }

    @Override
    public void updateUserDataAuthorityAll(String name) {
        unitJDBCDao.updateUserDataAuthorityAll(name);
    }

    @Override
    public String roleAreaAuthoList(UnitDto dto) {
        List<Unit> units = areas(dto);
        List<UnitTreeNodes> treeNodes = parseRoleComplex(units);
        //获取数据库中角色已经授权的ids
        List<String> list = getAuthorityDataIdsStringByRoleId(dto);
        List<UnitTreeNodes> treeNodeList = returnAuthorityData(list, treeNodes);
        List<UnitTreeNodes> treeNode = bulidForDataAutho(treeNodeList);
        // List<UnitTreeNodes> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }

    /**
     * 返回所有的数据授权集合
     *
     * @param dto
     * @return
     */

    @Override
    public List<Unit> areas(UnitDto dto) {
        unitJDBCDao.areaList(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(50000);
        commonService.fillSqlPageModelData(pageModel, Unit.class, "id");
        List<Unit> units = (List<Unit>) dto.getPageModel().getPageData();
        return units;
    }

//    @Override
//    public void insertDataAutho() {
//
//        //删除具体的统筹区和医院
//        unitJDBCDao.deleteAllHospAndArea();
//        //查询处所有的城市
//        List<DictCityOrg> dictCityOrgList = dictCityOrgJdbcDao.getAllCitys();
//        for (DictCityOrg dictCityOrg : dictCityOrgList) {
//            String cityCode = dictCityOrg.getCityCode();
//            String cityName = dictCityOrg.getCityName();
//            // 根据cityCode去省市表查询，城市对应的省份 code
//            String provinceCode = unitJDBCDao.getProvinceCode(cityCode);
//            //根据省份code，判断省份是否存在(),根据省份id是否存在
//            String provinceId = unitJDBCDao.provinceIsExit(provinceCode);
//            //不存在则插入省份
//            if ("".equals(provinceId)) {
//                //插入省份,并获取id
//                //去t_piccbid_administrative_dict查询到要插入的参数
//                // provinceId=getProvinceId(cityCode);
//                provinceId = getProvinceIdNew(cityCode);
//                //  unitJDBCDao.insertProvince(cityCode);
//            }
//            //根据cityCode ,去授权表查询城市是否存在，不存在则插入
//            String cityId = unitJDBCDao.cityIsExit(cityCode);
//
//            if ("".equals(cityId)) {
//                cityId = getCityId(cityCode, cityName, provinceId);
//            }
//
//            //判断医院或者统筹区是否存在（）根据parentId存在不
//            String hospId = unitJDBCDao.dictOrHospIsExist(cityId, "医院");
//            String dictId = unitJDBCDao.dictOrHospIsExist(cityId, "统筹区");
//            //插入医院和统筹区 并获取相应的id
//            if ("".equals(hospId)) {
//                hospId = getHospOrDictId("医院", cityId, "3-1","yy");
//            }
//            if ("".equals(dictId)) {
//                dictId = getHospOrDictId("统筹区", cityId, "3-2","ycq");
//            }
//
//
//            //删除医院 根据parent_id
//            unitJDBCDao.deleteHospOrDict(hospId);
//            //删除统筹区 根据parent_id
//            unitJDBCDao.deleteHospOrDict(dictId);
//            //插入具体的医院
//            unitJDBCDao.insertHosp(hospId, cityCode, "4-1");
//            //插入具体的统筹区
//            unitJDBCDao.insertDict(dictId, cityCode);
//        }
//
//    }
//
//
//    //插入省份，并且返回id
//    private String getProvinceId(String cityCode) {
//        HanddingOrgAutho handdingOrgAutho = unitJDBCDao.getProvinceInfo(cityCode);
//        handdingOrgAutho.setCityCode(handdingOrgAutho.getHanddingInsCode());
//        handdingOrgAutho.setLevelType("1");
//        HanddingOrgAutho handddingOrgAuthoResult = handdingOrgAuthoService.save(handdingOrgAutho);
//        String provinceId = handddingOrgAuthoResult.getId();
//        return provinceId;
//    }
//
//    //
//    private String getProvinceIdNew(String cityCode) {
//        HanddingOrgAutho handdingOrgAutho = unitJDBCDao.getProvinceInfo(cityCode);
//        Unit unit = new Unit();
//        unit.setUnitCode(handdingOrgAutho.getHanddingInsCode());
//        unit.setUnitName(handdingOrgAutho.getHanddingInsName());
//        unit.setParentLeaf("1");
//        unit.setIsOauth("1");
//        //插入地市的下一级，这里写死了,名字就为‘地市’
//        String provinceParentId = unitJDBCDao.getParentId(NAME_INIT);
//        unit.setParentId(provinceParentId);
//        unit.setCityCode(unit.getUnitCode());
//        Unit unitResult = unitService.save(unit);
//        String provinceId = unitResult.getId();
//        return provinceId;
//    }

  /*  private String getHospOrDictId(String hospOrDict,String parentId,String levelType){
        HanddingOrgAutho handdingOrgAuthoForHospOrDict=new HanddingOrgAutho();
        handdingOrgAuthoForHospOrDict.setHanddingInsName(hospOrDict);
        handdingOrgAuthoForHospOrDict.setParentId(parentId);
        handdingOrgAuthoForHospOrDict.setLevelType(levelType);
        HanddingOrgAutho  handdingOrgAuthoForHospOrDictResult=handdingOrgAuthoService.save(handdingOrgAuthoForHospOrDict);
        return handdingOrgAuthoForHospOrDictResult.getId();
    }*/

    private String getHospOrDictId(String hospOrDict, String parentId, String levelType,String unitCode) {
        Unit unit = new Unit();
        unit.setUnitName(hospOrDict);
        unit.setParentId(parentId);
        unit.setParentLeaf(levelType);
        //这里设定unitCode是为了给前端传值使用
        unit.setUnitCode(unitCode);
        unit.setIsOauth("1");
        Unit unitResult = unitService.save(unit);
        return unitResult.getId();
    }

    /*    private String getCityId(String cityCode, String cityName, String provinceId) {
            String cityIsExitCode;
            HanddingOrgAutho handdingOrgAuthoForCity=new HanddingOrgAutho();
            handdingOrgAuthoForCity.setLevelType("2");
            handdingOrgAuthoForCity.setCityCode(cityCode);
            handdingOrgAuthoForCity.setHanddingInsCode(cityCode);
            handdingOrgAuthoForCity.setHanddingInsName(cityName);
            handdingOrgAuthoForCity.setParentId(provinceId);
            HanddingOrgAutho HanddingOrgAuthoForCityResult = handdingOrgAuthoService.save(handdingOrgAuthoForCity);
            cityIsExitCode=HanddingOrgAuthoForCityResult.getId();
            return cityIsExitCode;
        } */
    private String getCityId(String cityCode, String cityName, String provinceId) {
        Unit unit = new Unit();
        unit.setIsOauth("1");
        unit.setParentLeaf("2");
        unit.setCityCode(cityCode);
        unit.setUnitName(cityName);
        unit.setUnitCode(cityCode);
        unit.setParentId(provinceId);
        Unit unitResult = unitService.save(unit);
        return unitResult.getId();
    }


}

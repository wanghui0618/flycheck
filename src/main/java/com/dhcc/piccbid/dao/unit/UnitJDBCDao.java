package com.dhcc.piccbid.dao.unit;

import com.alibaba.fastjson.JSON;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.unit.UnitDto;
//import com.dhcc.piccbid.entity.handdingOrgAutho.HanddingOrgAutho;
import com.dhcc.piccbid.entity.unit.AuthorityType;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.piccbid.service.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @version V1.0
 * @date 2019-06-17 21:42:21
 */
@Component
public class UnitJDBCDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UnitService unitService;


    public void unitList(UnitDto unitDto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        PageModel pageModel = new PageModel();
        unitDto.setPageModel(pageModel);
        sqlStr.append("select * from t_piccbid_unit where 2=2 order by add_date ");
    /*    //数据管控
        List<String> listCityCode=unitService.getUserDataAhthority();
        String cityCode="unit_id";
        sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);*/

        unitDto.getPageModel().setQueryHql(sqlStr.toString());
        unitDto.getPageModel().setHqlParamMap(hqlParamMap);
    }


    public void roleCount(UnitDto dto) {
        String id = dto.getUnit().getId();
        String name = dto.getUnit().getUnitName();
        String code = dto.getUnit().getUnitCode();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from t_piccbid_unit where 1=1");
        stringBuilder.append(" and unit_name='" + name + "'");
        stringBuilder.append(" and unit_code='" + code + "'");
        //编辑时的查询是否重复
        if (id != null) {
            stringBuilder.append(" and id!='" + id + "'");
        }
        dto.getPageModel().setQueryHql(stringBuilder.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public String selectIsAuthoData(String id) {
        String sql = "select  authority_id from t_piccbid_user_authority where type='2' and user_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size() != 0) {
            return authorityIds.get(0);
        } else {
            return "";
        }

    }

    public String selectIsRoleAuthoData(String id) {
        String sql = "select  authority_id from t_piccbid_role_authority where type='2' and role_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size() != 0) {
            return authorityIds.get(0);
        } else {
            return "";
        }
    }

    public List<String> selectIsRoleAuthoDataForUnit(String id) {
       // String sql = "select  type_id from t_piccbid_role_authority where type='9' and role_id ='" + id + "'";
        String sql="select  type_id from t_piccbid_role_authority where type='9' \n" +
                "and role_id ='"+id+"' union select id as type_id from t_piccbid_unit where parent_id in(select  type_id from t_piccbid_role_authority where type='9' \n" +
                "and role_id ='"+id+"' and type_leaf in ('3-1','3-2'))";
        List<String> typeList = jdbcTemplate.queryForList(sql, String.class);
        return typeList;
    }

    public String getRoleMenuIsAuthoByuserId(String id) {
        String sql = "select authority_id from t_piccbid_role_authority where role_id=(select id from t_piccbid_role where role_code=\n" +
                "(select role_code from t_piccbid_user where id='" + id + "')) and type='2'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size() != 0) {
            return authorityIds.get(0);
        } else {
            return "";
        }
    }

    public void search(UnitDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        stringBuffer.append("select * from t_piccbid_unit where 1=1 ");

       /* //数据管控
        List<String> listCityCode=unitService.getUserDataAhthority();
        String cityCode="unit_id";
        stringBuffer=unitService.appendDataAuhoritySql(cityCode,stringBuffer,listCityCode);*/

        if (dto.getUnit() != null) {
            //根据name来搜索
            if (!"".equals(dto.getUnit().getUnitName())) {
                String name = dto.getUnit().getUnitName();
                stringBuffer.append(" and unit_name like '" + name + "%'");
            }
            //根据code去搜索
            if (!"".equals(dto.getUnit().getConcat())) {
                String concat = dto.getUnit().getConcat();
                stringBuffer.append(" and concat like '" + concat + "%'");
            }
            if (!"".equals(dto.getUnit().getPhone())) {
                String phone = dto.getUnit().getPhone();
                stringBuffer.append(" and phone like '" + phone + "%'");
            }
        }
        dto.getPageModel().setQueryHql(stringBuffer.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void synInfo() {
        StringBuilder sql = new StringBuilder();
        sql.append("begin\n" +
                "pro_trans_city;\n" +
                "end;");
        jdbcTemplate.execute(sql.toString());
    }

    public void roleTreeTable(UnitDto dto) {
        String parentLeaf = dto.getUnit().getParentLeaf();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        sqlStr.append("select * from t_piccbid_unit where 1=1 ");

    /*    //数据管控
        List<String> listCityCode=unitService.getUserDataAhthority();
        String cityCode="unit_id";
        sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);*/
        if (parentLeaf != null && !"".equals(parentLeaf)) {
            sqlStr.append("and parent_leaf  >= '" + parentLeaf + "'");
        }
        /*     sqlStr.append(" and is_unit !='1'");*/
        sqlStr.append("  order by parent_leaf asc");
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void roleTreeTableNew(UnitDto dto) {
        String id = dto.getUnit().getId();
        String phone = dto.getUnit().getPhone();
        String concat = dto.getUnit().getConcat();
        String unitName = dto.getUnit().getUnitName();
        StringBuilder sqlStr = new StringBuilder();
        PageModel pageModel = dto.getPageModel();
        Map hqlParamMap = new HashMap();
        sqlStr.append("select * from (select  *  from t_piccbid_unit start with id='" + id + "'");
        sqlStr.append(" connect by prior Id = parent_id) where 1=1");
        //sqlStr.append("  where is_unit !='1'");
        if (!"".equals(phone) && phone != null) {
            sqlStr.append(" and phone like '" + phone + "%'");
        }
        if (!"".equals(unitName) && unitName != null) {
            sqlStr.append(" and unit_name like '" + unitName + "%'");
        }
        if (!"".equals(concat) && concat != null) {
            sqlStr.append(" and concat like '" + concat + "%'");
        }
        sqlStr.append("  order by parent_leaf,unit_name");
        pageModel.setQueryHql(sqlStr.toString());
        pageModel.setHqlParamMap(hqlParamMap);
    }

    public List<String> selectunitIdById(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select unit_id from T_PICCBID_UNIT where city_code in (select city_code from T_PICCBID_UNIT\n" +
                " where id in(");
        stringBuilder.append(id);
        stringBuilder.append(")) and is_unit=0");
        List<String> units = jdbcTemplate.queryForList(stringBuilder.toString(), String.class);
        return units;
    }

    public void updateUserDataAuthorityAll(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append("update  t_piccbid_user_authority set authority_id= ('[\"'||(SELECT LISTAGG(id,'\",\"') WITHIN GROUP(ORDER BY id) FROM t_piccbid_unit )||'\"]') \n" +
                "where user_id= (select id from t_piccbid_user where login_name='" + name + "'");
        //sql.append(name);
        sql.append(") and type='2'");
        jdbcTemplate.execute(sql.toString());
    }

    public List<String> selectByRoleCode(String roleId) {
        // String sql = "select type_id from t_piccbid_role_authority where role_id= '" + roleId + "' and type='9'";
        String sql = "    select type_id from t_piccbid_role_authority where type='9'and role_id='" + roleId + "' and type_id not in (  \n" +
                "   select b.id from t_piccbid_role_authority A left join \n" +
                "    T_piccbid_unit B on  A.TYPE_ID=b.parent_id WHERE B.is_oauth='1'and A.role_id='" + roleId + "' \n" +
                "    ) ";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        return authorityIds;
    }

    public List<Unit> getAreaOrHosp(String roleId) {
        //String sql = "select authority_id from t_piccbid_role_authority where role_id= '"+roleId+"' and type='2'";
        String sql = "select id,parent_id from t_piccbid_unit where is_oauth='1' and parent_leaf in ('3-1','3-2') and id in(select type_id from t_piccbid_role_authority where role_id= '" + roleId + "' and type='9')\n";
        List<Unit> authorityIds = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Unit.class));
        return authorityIds;
    }

    public List<String> getAreaOrHospAll(String roleId) {
        //   String sql = "select parent_id from t_piccbid_unit where is_oauth='1' and parent_leaf in ('3-1','3-2') and id in(select type_id from t_piccbid_role_authority where role_id= '"+roleId+"' and type='9')\n";
        String sql = "  select unit_code from t_piccbid_unit where id in (    select parent_id from t_piccbid_unit where is_oauth='1' and parent_leaf in ('3-1','3-2') and \n" +
                "    id in(select type_id from t_piccbid_role_authority where role_id= '" + roleId + "' and type='9'))";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        return authorityIds;
    }

    public List<String> getRealAreaOrHosp(String parnetId) {
        //String sql = "select authority_id from t_piccbid_role_authority where role_id= '"+roleId+"' and type='2'";
        String sql = "select id from t_piccbid_unit where parent_id ='" + parnetId + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        return authorityIds;
    }


    public AuthorityType selectHanddingOrgById(String id) {
        StringBuilder stringBuilder = new StringBuilder();
 /*       stringBuilder.append(" select sum(case when t.LEVEL_TYPE='4-2' then 1 end) as area_count ,sum(case when t.LEVEL_TYPE='4-1' then 1 end) as hospital_count ,\n" +
                "       sum(case when t.LEVEL_TYPE='3' then 1 end)  \n" +
                "        as city_count from  (select  level_type  from  T_PICCBID_HANDDING_ORG_AUTHO  where id in(" + id + ")) t ");*/
        stringBuilder.append("select sum(case when t.parent_leaf='4-2' then 1 end) as area_count ,sum(case when t.parent_leaf='4-1' then 1 end) as hospital_count ,\n" +
                "                   sum(case when t.parent_leaf in ('3-1','3-2') then 1 end) \n" +
                "                 as city_count from  (select  parent_leaf  from  t_piccbid_unit  where id in(" + id + ")) t");
        List<AuthorityType> authorityTypes = jdbcTemplate.query(stringBuilder.toString(), new BeanPropertyRowMapper(AuthorityType.class));
        return authorityTypes.get(0);
    }

    //根据统筹区去授权
    public List<String> getAreaList(String roleId) {
        StringBuilder sql = new StringBuilder();
        //sql.append("select  handding_ins_code  from  T_PICCBID_HANDDING_ORG_AUTHO where level_type='4-2' and id in (" + ids + ")");
       /* sql.append("select  unit_code  from  T_PICCBID_UNIT where PARENT_LEAF='4-2' and id in (select type_id\n" +
                "  from t_piccbid_role_authority\n" +
                " where type = '9'\n" +
                "   and role_id = '"+roleId+"'\n" +
                "   and type_id not in\n" +
                "       (select b.id\n" +
                "          from t_piccbid_role_authority A\n" +
                "          left join T_piccbid_unit B\n" +
                "            on A.TYPE_ID = b.parent_id\n" +
                "         WHERE B.is_oauth = '1'\n" +
                "           and A.role_id = '"+roleId+"'))");*/
       sql.append("select type_code from t_piccbid_role_authority where role_id='"+roleId+"' and type_leaf='4-2'");
        List<String> areas = jdbcTemplate.queryForList(sql.toString(), String.class);
        if (areas.size() > 0) {
            return areas;
        } else {
            return new ArrayList<>();
        }
    }

    //根据城市去授权（勾选了所有医院）
    public List<String> getCityString(String ids) {
        StringBuilder sql = new StringBuilder();
     /*   sql.append("select handding_ins_code from T_PICCBID_HANDDING_ORG_AUTHO where id  in " +
                "( select parent_id from T_PICCBID_HANDDING_ORG_AUTHO where level_type='3-2' and  id in (" + ids + "))");*/
        sql.append("select unit_code from t_piccbid_unit where id  in " +
                "( select parent_id from t_piccbid_unit where parent_leaf='3-1' and  id in (" + ids + "))");
        List<String> citys = jdbcTemplate.queryForList(sql.toString(), String.class);
        if (citys.size() > 0) {
            return citys;
        } else {
            return new ArrayList<>();
        }
    }

    //根据具体的医院去授权（没有勾选'医院'）
    public List<String> getOrgString(String roleId) {
        StringBuilder sql = new StringBuilder();
      /*  sql.append("select unit_code from T_PICCBID_unit where parent_leaf='4-1' and id in (select type_id\n" +
                "  from t_piccbid_role_authority\n" +
                " where type = '9'\n" +
                "   and role_id = '"+roleId+"'\n" +
                "   and type_id not in\n" +
                "       (select b.id\n" +
                "          from t_piccbid_role_authority A\n" +
                "          left join T_piccbid_unit B\n" +
                "            on A.TYPE_ID = b.parent_id\n" +
                "         WHERE B.is_oauth = '1'\n" +
                "           and A.role_id = '"+roleId+"'))");*/
      sql.append("select type_code from t_piccbid_role_authority where role_id='"+roleId+"' and type_leaf='4-1'");
        List<String> orgs = jdbcTemplate.queryForList(sql.toString(), String.class);
        if (orgs.size() > 0) {
            return orgs;
        } else {
            return new ArrayList<>();
        }
    }

    public void areaList(UnitDto unitDto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        PageModel pageModel = new PageModel();
        unitDto.setPageModel(pageModel);
        // sqlStr.append("select id,handding_ins_code as unit_code , handding_ins_name as unit_name,parent_id,city_code from T_PICCBID_HANDDING_ORG_AUTHO where 1=1 ");
        sqlStr.append("select id, unit_code , unit_name,parent_id,city_code,parent_leaf from T_PICCBID_UNIT where 1=1  AND IS_OAUTH='1' order by unit_name");
        unitDto.getPageModel().setQueryHql(sqlStr.toString());
        unitDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public String getProvinceCode(String cityCode) {
     /*   String sql = " select handding_ins_code " +
                "      from t_piccbid_administrative_dict\n" +
                "     where id = (select parent_id\n" +
                "                   from t_piccbid_administrative_dict\n" +
                "                  where dict_level='2' and handding_ins_code = '" + cityCode + "')";*/
        String sql = "select province_code from t_piccbid_dict_city  where city_code='" + cityCode + "'";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return "";
        }
    }

    public String provinceIsExit(String provinceCode) {
      /*  String sql = "select id " +
                "from  T_PICCBID_HANDDING_ORG_AUTHO " +
                "where handding_ins_code = '" + provinceCode + "'";*/
        String sql = "select id from  t_piccbid_unit  where is_oauth='1' and  unit_code='" + provinceCode + "'";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return "";
        }

    }

    public String cityIsExit(String cityCode) {
       /* String sql = "SELECT id " +
                "      FROM t_piccbid_handding_org_autho \n" +
                "       WHERE level_type='2' and CITY_CODE = '" + cityCode + "'";*/
        String sql = "select id from t_piccbid_unit where parent_leaf in ('1','2') and is_oauth='1' and unit_code='" + cityCode + "'";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        if (list.size() != 0) {
            return list.get(0);
        } else {
            return "";
        }
    }

    public String dictOrHospIsExist(String cityId, String name) {
        /*String sql = "select id from T_PICCBID_HANDDING_ORG_AUTHO where parent_id='" + cityId + "' and Handding_ins_Name='" + name + "'";*/
        String sql = "select id from t_piccbid_unit where parent_id='" + cityId + "' and unit_name='" + name + "'and is_oauth='1' ";
        List<String> ids = jdbcTemplate.queryForList(sql, String.class);
        if (ids.size() > 0) {
            return ids.get(0);
        } else {
            return "";
        }
    }

    public void deleteHospOrDict(String hospId) {
        //String sql = "delete from T_PICCBID_HANDDING_ORG_AUTHO where parent_id='" + hospId + "' ";
        String sql = "delete from t_piccbid_unit where parent_id='" + hospId + "' and is_oauth='1'";
        jdbcTemplate.execute(sql);
    }

    public void insertHosp(String parnetId, String cityCode, String levelType) {
        String sql = "insert into t_piccbid_unit (unit_code,unit_name,parent_id,parent_leaf,city_code,id,is_oauth) " +
                "select org_code,org_name,'" + parnetId + "','" + levelType + "','" + cityCode + "',sys_guid(),'1' from t_piccbid_dict_city_org where city_code='" + cityCode + "' order by org_code";
        jdbcTemplate.execute(sql);
    }

    public void insertDict(String parnetId, String cityCode) {
        String sql = "insert into t_piccbid_unit (unit_code,unit_name,parent_id,parent_leaf,city_code,id,is_oauth)" +
                " select handding_ins_code,handding_ins_name,'" + parnetId + "','4-2','" + cityCode + "',sys_guid(),'1' from t_piccbid_handding_org_code where city_code='" + cityCode + "' order by handding_ins_code";
        jdbcTemplate.execute(sql);
    }

//    public HanddingOrgAutho getProvinceInfo(String cityCode) {
//      /*  String sql = "select handding_ins_code, Handding_ins_Name from t_piccbid_administrative_dict " +
//                " where id = (select parent_id from t_piccbid_administrative_dict where dict_level='2' and handding_ins_code ='" + cityCode + "')";*/
//        String sql = "select province_code as handding_ins_code,city_adminarea as handding_ins_name  from t_piccbid_dict_city where city_code='" + cityCode + "'";
//
//        List<HanddingOrgAutho> handdingOrgAuthoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HanddingOrgAutho.class));
//        if (handdingOrgAuthoList.size() > 0) {
//            return handdingOrgAuthoList.get(0);
//        } else {
//            return new HanddingOrgAutho();
//        }
//    }

    public String getParentId(String name) {
        String sql = "select id from t_piccbid_unit where unit_name='" + name + "'";
        List<String> ids = jdbcTemplate.queryForList(sql, String.class);
        if (ids.size() > 0) {
            return ids.get(0);
        } else {
            return "";
        }

    }

    public String getUnitCodeById(String id) {
        String sql = "select unit_code from t_piccbid_unit where id='" + id + "'";

        List<String> s = jdbcTemplate.queryForList(sql, String.class);
        if (s.size() > 0) {
            return s.get(0);
        } else {
            return "";
        }
    }
    public void deleteAllHospAndArea(){
        String sql="delete t_piccbid_unit where parent_leaf in ('4-1','4-2')";
        jdbcTemplate.execute(sql);
    }

    public List<String> getCityCodeByAreaCodeOrHospCode(String roleId){
        String sql="select distinct(city_code) from t_piccbid_unit where unit_code in  (select type_code  from t_piccbid_role_authority\n" +
                "where role_id='"+roleId+"'and type='9' and type_leaf not in ('3-1','3-2'))";
      List<String>  list=  jdbcTemplate.queryForList(sql,String.class);
      return list;
    }
    public List<Unit> getAllHosps(){
        String sql="select unit_name,unit_code,id from t_piccbid_unit where  parent_leaf='4-1'";
        List<Unit> units=jdbcTemplate.query(sql,new BeanPropertyRowMapper(Unit.class));
        return units;
    }

}

package com.dhcc.piccbid.dao.dataauthority;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.dataauthority.DataAuthorityDto;
import oracle.sql.NUMBER;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataAuthorityJDBCDao {

    public void dataAuthorityList(DataAuthorityDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        // sqlStr.append("select  a.* ,b.unit_name from t_piccbid_dataauthority a left join t_piccbid_unit b on a.city_code=b.unit_id");
        sqlStr.append("select  t1.*, t2.unit_name as org_name from (select  a.* ,b.unit_name as city_name from t_piccbid_dataauthority a left join t_piccbid_unit b on a.city_code=b.unit_id)" +
                "t1 left join t_piccbid_unit t2 on  t1.org_code =t2.unit_id order by t1.city_code asc");
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }


    public void dataAuthorotyTreeTable(DataAuthorityDto dto) {
        String parentLeaf = dto.getDataAuthority().getParentLeaf();
        String cityName = null;
        String orgName = null;
        if (dto.getDataAuthorityVO() != null) {
            cityName = dto.getDataAuthorityVO().getCityName();
            orgName = dto.getDataAuthorityVO().getOrgName();
        }
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        sqlStr.append("select  t1.*, t2.unit_name as org_name from (select  a.* ,b.unit_name as city_name from t_piccbid_dataauthority a left join t_piccbid_unit b on a.city_code=b.unit_id) " +
                "t1 left join t_piccbid_unit t2 on  t1.org_code =t2.unit_id  where 1=1 ");
        if (parentLeaf != null) {
            sqlStr.append("and t1.parent_leaf  >= '" + parentLeaf + "'");
        }
        if (orgName != null) {
            sqlStr.append("and t2.unit_name  like'" + orgName + "%'");
        }
        if (cityName != null) {
            sqlStr.append("and t2.unit_name like'" + cityName + "%'");
        }
        sqlStr.append("order by t1.city_code asc");
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void findCity(DataAuthorityDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        sqlStr.append(" select distinct city_code as value ,city_name as text  from t_piccbid_dict_city_org order by city_code");
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void findOrg(DataAuthorityDto dto) {
        String cityCodeSelect=null;
        if (dto.getDataAuthority()!=null) {
         cityCodeSelect = dto.getDataAuthority().getCityCode();
        }
        String cityCode= null;
       /* if (dto.getDataAuthorityVO()!=null) {
            cityCode = dto.getDataAuthorityVO().getOrgCode();
        }*/
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        sqlStr.append(" select distinct org_code as value ,org_name as text  from t_piccbid_dict_city_org where 1=1");
      /*  if (cityCode!=null){
            sqlStr.append(" and city_code='"+cityCode+"'");
        }*/
        if (cityCodeSelect!=null&&!"".equals(cityCodeSelect)){
            sqlStr.append(" and city_code='"+cityCodeSelect+"'");
        }

        sqlStr.append(" order by org_code asc");
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }
}

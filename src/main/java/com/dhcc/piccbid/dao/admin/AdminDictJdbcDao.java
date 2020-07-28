/**
 * 
 */
package com.dhcc.piccbid.dao.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.admin.AdminDict;
import com.dhcc.piccbid.entity.admin.AdminVo;
import com.dhcc.piccbid.service.unit.UnitService;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @version V1.0
 * @date 2019-08-14 04:57:58
 */
@Component
public class AdminDictJdbcDao {
	
	private static Log logger = LogFactory.getLog(AdminDictJdbcDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	@Autowired
	private UnitService unitService;
	
	public List<AdminVo> findCityDict(AdminDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(" select distinct city_code as value ,city_name as text  from t_piccbid_dict_city order by city_code asc ");
        @SuppressWarnings("unchecked")
		List<AdminVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), AdminVo.class, null);
        return list;
    }
	
	public List<AdminVo> findCityNameDict(AdminDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(" select distinct city_name as value ,city_name as text  from t_piccbid_dict_city order by city_name asc ");
        @SuppressWarnings("unchecked")
		List<AdminVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), AdminVo.class, null);
        return list;
    }
	
	public List<AdminVo> findCityOrgDict(AdminDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(" select distinct org_code as value ,org_name as text  from t_piccbid_dict_city_org where 1=1 ");
        List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="org_code";
		//调用数据授权
		sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
        sqlStr.append(" order by org_code asc");
        @SuppressWarnings("unchecked")
		List<AdminVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), AdminVo.class, null);
        return list;
    }
	
	public List<AdminVo> findOrgNameDict(AdminDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(" select distinct org_name as value ,org_name as text  from t_piccbid_dict_city_org where 1=1 ");
        List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="org_code";
		//调用数据授权
		sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
        sqlStr.append(" order by org_name asc");
        @SuppressWarnings("unchecked")
		List<AdminVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), AdminVo.class, null);
        return list;
    }
	
	public List<AdminVo> medicalNameDict(AdminDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(" select distinct org_name as value ,org_name as text  from t_Piccbid_Medical where 1=1 ");
        List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="org_code";
		//调用数据授权
		sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
        sqlStr.append(" order by org_name asc");
        @SuppressWarnings("unchecked")
		List<AdminVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), AdminVo.class, null);
        return list;
    }
	
	public void findDict(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String dictName=dto.getDictName();
		if(dictName.equals("org")) {
			sqlStr.append("select count(org_code) over() as total, org_name as name,org_code as code from t_piccbid_dict_city_org where 1=1 ");
		}else if(dictName.equals("handding")) {
			sqlStr.append("select count(handding_ins_code) over() as total, handding_ins_name as name,handding_ins_code as code from t_piccbid_handding_org_code where 1=1 ");
		}
//		List<String> listCityCode=unitService.getUserDataAhthority();
//		String cityCode="org_code";
//		//调用数据授权
//		sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
		String keyword=dto.getKeyword();
		if(dictName.equals("org")) {
			if(keyword!=null&&!"".equals(keyword)) {
				sqlStr.append(" and org_name like '%"+keyword+"%'");
			}
		}else if(dictName.equals("handding")) {
			if(keyword!=null&&!"".equals(keyword)) {
				sqlStr.append(" and handding_ins_name like '%"+keyword+"%'");
			}
		}
		List<AdminDict> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), AdminDict.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), null);
		/*
		 * dto.getPageModel().setQueryHql(sqlStr.toString());
		 * dto.getPageModel().setHqlParamMap(hqlParamMap);
		 */
		if(list.size()>0) {
			dto.getPageModel().setTotals(list.get(0).getTotal());
		}
		dto.getPageModel().setPageData(list);
	}
	


}

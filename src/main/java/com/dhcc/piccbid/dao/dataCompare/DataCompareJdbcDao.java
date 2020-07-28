/**
 * 
 */
package com.dhcc.piccbid.dao.dataCompare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;

import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlyGeneralOverviewVo;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;


/**
 * @author lenovo
 *
 */
@Component
public class DataCompareJdbcDao {
	
	private static Log logger = LogFactory.getLog(DataCompareJdbcDao.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	/**
	 * 总体概况
	 * 
	 * @param dto
	 */
	public void FlyGeneralOverviewDtoFind(FlyGeneralOverviewDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select t.hospital_id,\r\n" + 
				"       t.hospital_name,\r\n" + 
				"       '住院' visiting_route,\r\n" + 
				"       t.benefit_group_id,\r\n" + 
				"       t.year,\r\n" + 
				"       count(distinct t.patient_id) people_number,\r\n" + 
				"       count(*) people_number2,\r\n" + 
				"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" + 
				"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" + 
				"       round(avg(t.total_amount),2) avg_cost,\r\n" + 
				"       round(avg(t.zyts),2) avg_day\r\n" + 
				"  from t_flycheck_medical t\r\n" + 
				" where 1 = 1 ");
		System.out.println("查询sql1  "+sqlStr);
		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getBenefitGroupId()) && dto.getFlyGeneralOverviewVo().getBenefitGroupId()!=null) {
			sqlStr.append(" and t.benefit_group_id= '"+dto.getFlyGeneralOverviewVo().getBenefitGroupId()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null) {
			sqlStr.append(" and t.hospital_id= '"+dto.getFlyGeneralOverviewVo().getHospitalId()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
			sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
		}
		}
		sqlStr.append("  group by t.hospital_id,\r\n" + 
				"       t.hospital_name,\r\n" + 
				"       t.benefit_group_id,\r\n" + 
				"       t.year");
		System.out.println("查询sql:"+sqlStr);
		@SuppressWarnings("unchecked")
		/*List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "t.hospital_id");*/
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, new HashMap(), pageModel.getPageNo(), pageModel.getPageSize(),
				"rownum");
		System.out.println(list);
		//int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t.hospital_id", hqlParamMap);
		pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(), new BeanPropertyRowMapper<>(FlyGeneralOverviewVo.class)).size());
		pageModel.setPageData(list);
	}
	
	public void FlyGeneralOverviewDtoFind1(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		sqlStr.append("select t.hospital_id,\r\n" + 
				"       t.hospital_name,\r\n" + 
				"       '住院' visiting_route,\r\n" + 
				"       t.benefit_group_id,\r\n" + 
				"       t.year,\r\n" + 
				"       count(distinct t.patient_id) people_number,\r\n" + 
				"       count(*) people_number2,\r\n" + 
				"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" + 
				"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" + 
				"       round(avg(t.total_amount),2) avg_cost,\r\n" + 
				"       round(avg(t.zyts),2) avg_day\r\n" + 
				"  from t_flycheck_medical t\r\n" + 
				" where 1 = 1 ");
		System.out.println("查询sql1  "+sqlStr);
		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getBenefitGroupId()) && dto.getFlyGeneralOverviewVo().getBenefitGroupId()!=null) {
			sqlStr.append(" and t.benefit_group_id= '"+dto.getFlyGeneralOverviewVo().getBenefitGroupId()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null) {
			sqlStr.append(" and t.hospital_id= '"+dto.getFlyGeneralOverviewVo().getHospitalId()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
			sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
		}
		}
		sqlStr.append("  group by t.hospital_id,\r\n" + 
				"       t.hospital_name,\r\n" + 
				"       t.benefit_group_id,\r\n" + 
				"       t.year");
		System.out.println("查询sql:"+sqlStr);
		@SuppressWarnings("unchecked")
		/*List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "t.hospital_id");*/
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 10, "id");
		System.out.println("我是echarts的     "+list);
		//int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t.hospital_id", hqlParamMap);
		//pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(), new BeanPropertyRowMapper<>(FlyGeneralOverviewVo.class)).size());
		page.setPageData(list);
//      page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
  page.setTotals(10);
  dto.setPageModel(page);
	}

}

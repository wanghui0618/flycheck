/**
 * 
 */
package com.dhcc.piccbid.dao.flyGeneralOverview;

import java.util.*;

import javax.annotation.Resource;

import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.common.SpringContextHolder;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlyGeneralOverviewVo;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;


/**
 * @author lenovo
 *
 */
@Component
public class FlyGeneralOverviewJdbcDao {
	
	private static Log logger = LogFactory.getLog(FlyGeneralOverviewJdbcDao.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	/**
	 * 总体概况
	 * 
	 * @param dto
	 */
	/*
	* 导出
	* */
	public SXSSFWorkbook getData(FlyGeneralOverviewDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		FlycheckMedical flyGeneralOverview = dto.getFlyGeneralOverview();
		SXSSFWorkbook wb = new SXSSFWorkbook();
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select t.hospital_id,\r\n" +
						"       t.hospital_name,\r\n" +
						"       '住院' visiting_route,\r\n" +
						"       t.BENEFIT_TYPE,\r\n" +
						"       t.year,\r\n" +
						"       count(distinct t.patient_id) people_number,\r\n" +
						"       count(*) people_number2,\r\n" +
						"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" +
						"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" +
						"       round(avg(t.total_amount),2) avg_cost,\r\n" +
						"       round(avg(t.zyts),2) avg_day\r\n" +
						"  from t_flycheck_medical t\r\n" +
						" where 1 = 1 ");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select t.hospital_id,\r\n" +
						"       t.hospital_name,\r\n" +
						"       '门诊' visiting_route,\r\n" +
						"       t.BENEFIT_TYPE,\r\n" +
						"       t.year,\r\n" +
						"       count(distinct t.patient_id) people_number,\r\n" +
						"       count(*) people_number2,\r\n" +
						"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" +
						"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" +
						"       round(avg(t.total_amount),2) avg_cost\r\n" +
						"  from t_flycheck_medical_mz t\r\n" +
						" where 1 = 1 ");
			}else {
				sqlStr.append("select t.hospital_id,\r\n" +
						"       t.hospital_name,\r\n" +
						"       '住院' visiting_route,\r\n" +
						"       t.BENEFIT_TYPE,\r\n" +
						"       t.year,\r\n" +
						"       count(distinct t.patient_id) people_number,\r\n" +
						"       count(*) people_number2,\r\n" +
						"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" +
						"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" +
						"       round(avg(t.total_amount),2) avg_cost,\r\n" +
						"       round(avg(t.zyts),2) avg_day\r\n" +
						"  from t_flycheck_medical t\r\n" +
						" where 1 = 1 ");
			}
		}else {
			sqlStr.append("select t.hospital_id,\r\n" +
					"       t.hospital_name,\r\n" +
					"       '住院' visiting_route,\r\n" +
					"       t.BENEFIT_TYPE,\r\n" +
					"       t.year,\r\n" +
					"       count(distinct t.patient_id) people_number,\r\n" +
					"       count(*) people_number2,\r\n" +
					"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" +
					"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" +
					"       round(avg(t.total_amount),2) avg_cost,\r\n" +
					"       round(avg(t.zyts),2) avg_day\r\n" +
					"  from t_flycheck_medical t\r\n" +
					" where 1 = 1 ");
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if (!"".equals(dto.getFlyGeneralOverviewVo().getBenefitGroupId()) && dto.getFlyGeneralOverviewVo().getBenefitGroupId()!=null) {
				sqlStr.append(" and t.BENEFIT_TYPE= '"+dto.getFlyGeneralOverviewVo().getBenefitGroupId()+"' ");
			}
			if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
				sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
			if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null) {
				sqlStr.append(" and t.hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
			}
			/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
				sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
			}*/
		}
		sqlStr.append(" and t.hospital_id is not null");
		sqlStr.append("  group by t.hospital_id,\r\n" +
				"       t.hospital_name,\r\n" +
				"       t.BENEFIT_TYPE,\r\n" +
				"       t.year");

		List<FlyGeneralOverviewVo> FlyGeneralOverviewVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, null);
		SXSSFSheet sheet = (SXSSFSheet)wb.createSheet("总体概况");
		// 在sheet里创建第一行
		SXSSFRow row1 = (SXSSFRow)sheet.createRow(0);
		// 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
		row1.createCell(0).setCellValue("医疗机构编码");
		row1.createCell(1).setCellValue("医疗机构名称");
		row1.createCell(2).setCellValue("就诊途径");
		row1.createCell(3).setCellValue("人员类型");
		row1.createCell(4).setCellValue("年度");
		row1.createCell(5).setCellValue("住院人数");
		row1.createCell(6).setCellValue("住院人次");
		row1.createCell(7).setCellValue("总费用");
		row1.createCell(8).setCellValue("报销费用");
		row1.createCell(9).setCellValue("平均费用");
		row1.createCell(10).setCellValue("平均住院天数");
		row1.createCell(11).setCellValue("药占比");
		// 插入数据
		for (int i = 0; i < FlyGeneralOverviewVoList.size(); i++) {
			SXSSFRow rowNum = (SXSSFRow)sheet.createRow(i + 1);
			rowNum.createCell(0).setCellValue(FlyGeneralOverviewVoList.get(i).getHospitalId());
			rowNum.createCell(1).setCellValue(FlyGeneralOverviewVoList.get(i).getHospitalName());
			rowNum.createCell(2).setCellValue(FlyGeneralOverviewVoList.get(i).getVisitingRoute());
			rowNum.createCell(3).setCellValue(FlyGeneralOverviewVoList.get(i).getBenefitGroupId());
			rowNum.createCell(4).setCellValue(FlyGeneralOverviewVoList.get(i).getYear());
			rowNum.createCell(5).setCellValue(FlyGeneralOverviewVoList.get(i).getPeopleNumber());
			rowNum.createCell(6).setCellValue(FlyGeneralOverviewVoList.get(i).getPeopleNumber2());
			rowNum.createCell(7).setCellValue(FlyGeneralOverviewVoList.get(i).getMedicalTotal());
			rowNum.createCell(8).setCellValue(FlyGeneralOverviewVoList.get(i).getMedicalBmi());
			rowNum.createCell(9).setCellValue(FlyGeneralOverviewVoList.get(i).getAvgCost());
			rowNum.createCell(10).setCellValue(FlyGeneralOverviewVoList.get(i).getAvgDay());
			rowNum.createCell(11).setCellValue(FlyGeneralOverviewVoList.get(i).getDrugShare());
		}
		return wb;




	}
	public void FlyGeneralOverviewDtoFind(FlyGeneralOverviewDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				System.out.println("这里是就诊类型"+dto.getFlyGeneralOverviewVo().getVisitingRoute());
				sqlStr.append("select t.hospital_id,\r\n" +
						"       t.hospital_name,\r\n" + 
						"       '住院' visiting_route,\r\n" + 
						"       t.BENEFIT_TYPE,\r\n" + 
						"       t.year,\r\n" + 
						"       count(distinct t.patient_id) people_number,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" + 
						"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" + 
						"       round(avg(t.total_amount),2) avg_cost,\r\n" + 
						"       round(avg(t.zyts),2) avg_day\r\n" + 
						"  from t_flycheck_medical t\r\n" + 
						" where 1 = 1 ");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				System.out.println("这里是就诊类型"+dto.getFlyGeneralOverviewVo().getVisitingRoute());
				sqlStr.append("select t.hospital_id,\r\n" +
						"       t.hospital_name,\r\n" + 
						"       '门诊' visiting_route,\r\n" + 
						"       t.BENEFIT_TYPE,\r\n" + 
						"       t.year,\r\n" + 
						"       count(distinct t.patient_id) people_number,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" + 
						"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" + 
						"       round(avg(t.total_amount),2) avg_cost\r\n" + 
						"  from t_flycheck_medical_mz t\r\n" + 
						" where 1 = 1 ");
			}else {
				sqlStr.append("select t.hospital_id,\r\n" + 
						"       t.hospital_name,\r\n" + 
						"       '住院' visiting_route,\r\n" + 
						"       t.BENEFIT_TYPE,\r\n" + 
						"       t.year,\r\n" + 
						"       count(distinct t.patient_id) people_number,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" + 
						"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" + 
						"       round(avg(t.total_amount),2) avg_cost,\r\n" + 
						"       round(avg(t.zyts),2) avg_day\r\n" + 
						"  from t_flycheck_medical t\r\n" + 
						" where 1 = 1 ");
			}
		}else {
			sqlStr.append("select t.hospital_id,\r\n" + 
					"       t.hospital_name,\r\n" + 
					"       '住院' visiting_route,\r\n" + 
					"       t.BENEFIT_TYPE,\r\n" + 
					"       t.year,\r\n" + 
					"       count(distinct t.patient_id) people_number,\r\n" + 
					"       count(*) people_number2,\r\n" + 
					"       round(sum(t.total_amount)/10000,2) medical_total,\r\n" + 
					"       round(sum(t.bmi_pay_amount)/10000,2) medical_bmi,\r\n" + 
					"       round(avg(t.total_amount),2) avg_cost,\r\n" + 
					"       round(avg(t.zyts),2) avg_day\r\n" + 
					"  from t_flycheck_medical t\r\n" + 
					" where 1 = 1 ");
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getBenefitGroupId()) && dto.getFlyGeneralOverviewVo().getBenefitGroupId()!=null) {
			sqlStr.append(" and t.BENEFIT_TYPE= '"+dto.getFlyGeneralOverviewVo().getBenefitGroupId()+"' ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			/*sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");*/
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and t.year between " + startTime + " and  " + endTime + " ");
		}
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null) {
			sqlStr.append(" and t.hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
			sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
		}*/
		}
		sqlStr.append(" and t.hospital_id is not null");
		sqlStr.append("  group by t.hospital_id,\r\n" + 
				"       t.hospital_name,\r\n" + 
				"       t.BENEFIT_TYPE,\r\n" + 
				"       t.year");
		@SuppressWarnings("unchecked")
		/*List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "t.hospital_id");*/
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, new HashMap(), pageModel.getPageNo(), pageModel.getPageSize(),
				"rownum");
		System.out.println("主页"+sqlStr);
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
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select t.hospital_id,\r\n" + 
						"       t.hospital_name,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount)/10000,2) medical_total\r\n" + 
						"  from t_flycheck_medical t\r\n" + 
						" where 1 = 1 ");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				System.err.println(dto.getFlyGeneralOverviewVo().getVisitingRoute());
				sqlStr.append("select t.hospital_id,\r\n" + 
						"       t.hospital_name,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount)/10000,2) medical_total\r\n" +  
						"  from t_flycheck_medical_mz t\r\n" + 
						" where 1 = 1 ");
			}else {
				sqlStr.append("select t.hospital_id,\r\n" + 
						"       t.hospital_name,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount)/10000,2) medical_total\r\n" + 
						"  from t_flycheck_medical t\r\n" + 
						" where 1 = 1 ");
			}
		}else {
			sqlStr.append("select t.hospital_id,\r\n" + 
					"       t.hospital_name,\r\n" + 
					"       count(*) people_number2,\r\n" + 
					"       round(sum(t.total_amount)/10000,2) medical_total\r\n" + 
					"  from t_flycheck_medical t\r\n" + 
					" where 1 = 1 ");
		}

		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			if(time.length>1) {
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and t.year between " + startTime + " and  " + endTime + " ");
			}else {
			sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
			sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
		}*/
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
			sqlStr.append(" and t.hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
		}
		}
		sqlStr.append("  group by t.hospital_id,\r\n" + 
				"       t.hospital_name");
		System.out.println("主页111"+sqlStr);
		@SuppressWarnings("unchecked")
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 10, "id");
		page.setPageData(list);
		  page.setTotals(10);
  			dto.setPageModel(page);
	}
	
	public void FlyGeneralOverviewDtoFind2(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select t.month,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
						"  from T_FLYCHECK_MEDICAL t\r\n" + 
						" where 1 = 1 ");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select t.month,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
						"  from T_FLYCHECK_MEDICAL_mz t\r\n" + 
						" where 1 = 1 ");
			}else {
				sqlStr.append("select t.month,\r\n" + 
						"       count(*) people_number2,\r\n" + 
						"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
						"  from T_FLYCHECK_MEDICAL t\r\n" + 
						" where 1 = 1 ");
			}
		}else {
			sqlStr.append("select t.month,\r\n" + 
					"       count(*) people_number2,\r\n" + 
					"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
					"  from T_FLYCHECK_MEDICAL t\r\n" + 
					" where 1 = 1 ");
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			if(time.length>1) {
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and t.year between " + startTime + " and  " + endTime + " ");
			}else {
			sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
		sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
	}*/
	if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
		sqlStr.append(" and t.hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
	} }
		sqlStr.append(" group by t.month");
		System.out.println("主页"+sqlStr);
		@SuppressWarnings("unchecked")
		/*List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "t.hospital_id");*/
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 12, "id");
		//int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t.hospital_id", hqlParamMap);
		//pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(), new BeanPropertyRowMapper<>(FlyGeneralOverviewVo.class)).size());
		page.setPageData(list);
//      page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
  page.setTotals(10);
  dto.setPageModel(page);
	}
	
	public void FlyGeneralOverviewDtoFind3(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select t.discharge_disease_name_main drgs_name,\r\n" + 
						"       count(*) people_number2\r\n" + 
						"  from T_FLYCHECK_MEDICAL t where 1=1 ");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select t.ADMISSION_DISEASE_NAME drgs_name,\r\n" +
						"       count(*) people_number2\r\n" + 
						"  from T_FLYCHECK_MEDICAL_mz t where 1=1 and t.ADMISSION_DISEASE_NAME is not null");
			}else {
				sqlStr.append("select t.discharge_disease_name_main drgs_name,\r\n" + 
						"       count(*) people_number2\r\n" + 
						"  from T_FLYCHECK_MEDICAL t where 1=1 ");
			}
		}else {
			sqlStr.append("select t.discharge_disease_name_main drgs_name,\r\n" + 
					"       count(*) people_number2\r\n" + 
					"  from T_FLYCHECK_MEDICAL t where 1=1 ");
		}

		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			if(time.length>1) {
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and t.year between " + startTime + " and  " + endTime + " ");
			}else {
			sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
		sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
	}*/
	if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
		sqlStr.append(" and t.hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
	}
		}
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("  group by t.discharge_disease_name_main\r\n" +
						" order by people_number2 desc");

			}else if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")){
				sqlStr.append("  group by t.ADMISSION_DISEASE_NAME\r\n" +
						" order by people_number2 desc");
			}else {
				sqlStr.append("  group by t.discharge_disease_name_main\r\n" +
						" order by people_number2 desc");
			}
			System.out.println("主页"+sqlStr);

		@SuppressWarnings("unchecked")
		/*List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "t.hospital_id");*/
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 10, "id");
		//int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t.hospital_id", hqlParamMap);
		//pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(), new BeanPropertyRowMapper<>(FlyGeneralOverviewVo.class)).size());
		page.setPageData(list);
//      page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
        page.setTotals(10);
        dto.setPageModel(page);
	}
	
	public void FlyGeneralOverviewDtoFind4(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select t.discharge_disease_name_main drg_name,\r\n" +
						"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
						"  from T_FLYCHECK_MEDICAL t\r\n" + 
						" where 1 = 1 ");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select t.ADMISSION_DISEASE_NAME drg_name,\r\n" +
						"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
						"  from T_FLYCHECK_MEDICAL_mz t\r\n" +
						" where 1 = 1 ");
			}else {
				sqlStr.append("select t.discharge_disease_name_main drg_name,\r\n" +
						"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
						"  from T_FLYCHECK_MEDICAL t\r\n" +
						" where 1 = 1 ");
			}
		}else {
			sqlStr.append("select t.discharge_disease_name_main drg_name,\r\n" +
					"       round(sum(t.total_amount) / 10000, 2) medical_total\r\n" + 
					"  from T_FLYCHECK_MEDICAL t\r\n" + 
					" where 1 = 1 ");
		}

		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			if(time.length>1) {
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and t.year between " + startTime + " and  " + endTime + " ");
			}else {
			sqlStr.append(" and t.year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
		sqlStr.append(" and t.hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
	}*/
	if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
		sqlStr.append(" and t.hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
	}
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("  group by t.discharge_disease_name_main\r\n" +
						" order by medical_total desc");

			}else if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")){
				sqlStr.append("  group by t.ADMISSION_DISEASE_NAME\r\n" +
						" order by medical_total desc");
			}else {
				sqlStr.append("  group by t.discharge_disease_name_main\r\n" +
						" order by medical_total desc");
			}
		}
		System.out.println("主页"+sqlStr);
		@SuppressWarnings("unchecked")
		/*List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyGeneralOverviewVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "t.hospital_id");*/
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 12, "id");
		//int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t.hospital_id", hqlParamMap);
		//pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(), new BeanPropertyRowMapper<>(FlyGeneralOverviewVo.class)).size());
		page.setPageData(list);
//      page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
  page.setTotals(10);
  dto.setPageModel(page);
	}
	//查询住院top10药品使用情况的药品名
	public void FlyGeneralOverviewDtoFind5(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select item_id,item_name drug_name,count(*) drug_number\n" +
						"from t_flycheck_medical_detail\n" +
						"where 1 = 1 and item_id not in ( '-')\n" +
						"and item_name is not null");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select item_id,item_name drug_name,count(*) drug_number\n" +
						"from t_Flycheck_Medical_Mz_Detail\n" +
						"where 1 = 1 and item_id not in ( '-')\n" +
						"and item_name is not null");
			}else {
				sqlStr.append("select item_id,item_name drug_name,count(*) drug_number\n" +
						"from t_flycheck_medical_detail\n" +
						"where 1 = 1 and item_id not in ( '-')\n" +
						"and item_name is not null");
			}
		}else {
			sqlStr.append("select item_id,item_name drug_name,count(*) drug_number\n" +
					"from t_flycheck_medical_detail\n" +
					"where 1 = 1 and item_id not in ( '-')\n" +
					"and item_name is not null");
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			if(time.length>1) {
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and year between " + startTime + " and  " + endTime + " ");
			}else {
			sqlStr.append(" and year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
		sqlStr.append(" and hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
	}*/
	if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
		sqlStr.append(" and hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
	}
		}
		sqlStr.append(" group by item_name,item_id\r\n" +
				" order by drug_number desc");
		System.out.println("主页住院药品top10"+sqlStr);
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 10, "id");
		page.setPageData(list);
		page.setTotals(10);//获取前十条记录
		dto.setPageModel(page);
	}
	
	public void FlyGeneralOverviewDtoFind6(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select item_name visits_item_name,count(patient_id)  visits_count\n" +
						"\t from t_flycheck_medical_detail\n" +
						" where 1=1");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select item_name visits_item_name,count(patient_id)  visits_count\n" +
						"\t from t_Flycheck_Medical_Mz_Detail\n" +
						" where 1=1");
			}else {
				sqlStr.append("select item_name visits_item_name,count(patient_id)  visits_count\n" +
						"\tfrom t_flycheck_medical_detail\n" +
						" where 1=1 ");
			}
		}else {
			sqlStr.append("select item_name visits_item_name,count(patient_id)  visits_count\n" +
					"\tfrom t_flycheck_medical_detail\n" +
					" where 1=1");
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
		if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
			String finaTime = dto.getFlyGeneralOverviewVo().getYear();
			String[] time = finaTime.split("~");
			if(time.length>1) {
			String startTime = time[0];
			String endTime = time[1];
			sqlStr.append(" and year between " + startTime + " and  " + endTime + " ");
			}else {
			sqlStr.append(" and year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
			}
		}
		/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
		sqlStr.append(" and hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
	}*/
	if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
		sqlStr.append(" and hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
	}
		if(dto.getFlyGeneralOverviewVo().getItemIds()!=null&&!" ".equals(dto.getFlyGeneralOverviewVo().getItemIds())){
			String ItemIds = dto.getFlyGeneralOverviewVo().getItemIds();
			String array[] = ItemIds.split(",");
			List<String> resultList = new ArrayList<>(array.length);
			Collections.addAll(resultList, array);
			sqlStr.append(" and item_id in(:ItemIds)");
			hqlParamMap.put("ItemIds", resultList);
		}
		}
		sqlStr.append(" group by item_name,item_id\r\n" +
				"  order by visits_count desc");
		System.out.println("主页"+sqlStr);
		@SuppressWarnings("unchecked")
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 10, "id");
		page.setPageData(list);
  page.setTotals(10);
  dto.setPageModel(page);
	}

	public void FindMedicalSumAmount(FlyGeneralOverviewDto dto) {
		PageModel page = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		page.setHqlParamMap(hqlParamMap);
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("住院")) {
				sqlStr.append("select item_id,item_name medical_name ,sum(cost) as medical_cost from t_Flycheck_Medical_Detail \n" +
						"where 1=1 and item_name is not null\n" +
						" and item_name <> '-'");
			}else if (dto.getFlyGeneralOverviewVo().getVisitingRoute()!=null && dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("门诊")) {
				sqlStr.append("select  item_id,item_name medical_name ,sum(cost) as medical_cost\n" +
						" from t_Flycheck_Medical_Mz_Detail\n" +
						" where 1=1 and item_name is not null\n" +
						" and item_name <> '-'");
			}else {
				sqlStr.append("select item_id,item_name medical_name ,sum(cost) as medical_cost from t_Flycheck_Medical_Detail \n" +
						"where 1=1 and item_name is not null\n" +
						" and item_name <> '-'");
			}
		}else {
			sqlStr.append("select item_id,item_name medical_name ,sum(cost) as medical_cost from t_Flycheck_Medical_Detail \n" +
					"where 1=1 and item_name is not null\n" +
					" and item_name <> '-'");
		}
		if(dto.getFlyGeneralOverviewVo()!=null) {
			if (!"".equals(dto.getFlyGeneralOverviewVo().getYear()) && dto.getFlyGeneralOverviewVo().getYear()!=null) {
				String finaTime = dto.getFlyGeneralOverviewVo().getYear();
				String[] time = finaTime.split("~");
				if(time.length>1) {
				String startTime = time[0];
				String endTime = time[1];
				sqlStr.append(" and year between " + startTime + " and  " + endTime + " ");
				}else {
				sqlStr.append(" and year= '"+dto.getFlyGeneralOverviewVo().getYear()+"' ");
				}
			}
			/*if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalName()) && dto.getFlyGeneralOverviewVo().getHospitalName()!=null) {
			sqlStr.append(" and hospital_name= '"+dto.getFlyGeneralOverviewVo().getHospitalName()+"' ");
		}*/
		if (!"".equals(dto.getFlyGeneralOverviewVo().getHospitalId()) && dto.getFlyGeneralOverviewVo().getHospitalId()!=null){
			sqlStr.append(" and hospital_id in("+dto.getFlyGeneralOverviewVo().getHospitalId()+") ");
		}
			if(dto.getFlyGeneralOverviewVo().getItemIdList()!=null&&!" ".equals(dto.getFlyGeneralOverviewVo().getItemIdList())){
				String ItemIdList = dto.getFlyGeneralOverviewVo().getItemIdList();
				String array[] = ItemIdList.split(",");
				List<String> resultList = new ArrayList<>(array.length);
				Collections.addAll(resultList, array);
				sqlStr.append(" and item_id in(:ItemIdList)");
				hqlParamMap.put("ItemIdList", resultList);
			}
		}
		sqlStr.append(" group by item_id,item_name\n" +
				"order by medical_cost desc");
		System.out.println("主页"+sqlStr);
		@SuppressWarnings("unchecked")
		List<FlyGeneralOverviewVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyGeneralOverviewVo.class, hqlParamMap, 1, 10, "id");
		page.setPageData(list);
		page.setTotals(10);
		dto.setPageModel(page);
	}

	@SuppressWarnings("unchecked")
	public List<FlyGeneralOverviewVo> findHospitalName(FlyGeneralOverviewVo dictRequestVo) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		List<FlyGeneralOverviewVo> flyGeneralOverviewVo = new ArrayList<FlyGeneralOverviewVo>();
		sqlStr.append("select t.hospital_name from T_FLYCHECK_MEDICAL t group by t.hospital_name");
			if (!StringUtils.isNullOrEmpty(sqlStr.toString())) {
				JdbcTemplateWrapper umJdbcTemplateWrapper = new JdbcTemplateWrapper(
						(JdbcTemplate) SpringContextHolder
								.getBean("jdbcTemplate"),
						(DataSourceProperties) SpringContextHolder
								.getBean("dataSourceProperties"),
						(JpaProperties) SpringContextHolder
								.getBean("spring.jpa-org.springframework.boot.autoconfigure.orm.jpa.JpaProperties"));

				flyGeneralOverviewVo = umJdbcTemplateWrapper.queryAllMatchListWithParaMap(
						sqlStr.toString(), FlyGeneralOverviewVo.class, map);
			}
		
		return flyGeneralOverviewVo;
	}
	
	@SuppressWarnings("unchecked")
	public List<FlyGeneralOverviewVo> findHospitalId(FlyGeneralOverviewVo dictRequestVo) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		List<FlyGeneralOverviewVo> flyGeneralOverviewVo = new ArrayList<FlyGeneralOverviewVo>();
		sqlStr.append("select t.hospital_id from T_FLYCHECK_MEDICAL t group by t.hospital_id");
			if (!StringUtils.isNullOrEmpty(sqlStr.toString())) {
				JdbcTemplateWrapper umJdbcTemplateWrapper = new JdbcTemplateWrapper(
						(JdbcTemplate) SpringContextHolder
								.getBean("jdbcTemplate"),
						(DataSourceProperties) SpringContextHolder
								.getBean("dataSourceProperties"),
						(JpaProperties) SpringContextHolder
								.getBean("spring.jpa-org.springframework.boot.autoconfigure.orm.jpa.JpaProperties"));

				flyGeneralOverviewVo = umJdbcTemplateWrapper.queryAllMatchListWithParaMap(
						sqlStr.toString(), FlyGeneralOverviewVo.class, map);
			}
		
		return flyGeneralOverviewVo;
	}

}

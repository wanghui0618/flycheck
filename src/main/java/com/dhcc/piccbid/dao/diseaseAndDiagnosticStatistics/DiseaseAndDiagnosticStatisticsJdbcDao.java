package com.dhcc.piccbid.dao.diseaseAndDiagnosticStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsDto;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;

@Component
public class DiseaseAndDiagnosticStatisticsJdbcDao {
	private static Log logger = LogFactory.getLog(DiseaseAndDiagnosticStatisticsJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	public void getlist(DiseaseAndDiagnosticStatisticsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		String admissionDiseaseName = dto.getAdmissionDiseaseName();
		if (admissionDiseaseName != null) {
			// System.out.println(inhosDiag);
			String a = admissionDiseaseName.replace("& #40;", "(");
			// System.out.println(a);
			String b = a.replace("& #41;", ")");
			// System.out.println(b);
			admissionDiseaseName = b;
			System.out.println(admissionDiseaseName);
		}
		System.out.println("诊断类型" + admissionDiseaseName);
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(
				"select ADMISSION_DISEASE_NAME from (select ADMISSION_DISEASE_NAME from T_FLY_MEDICAL_INHOS group by ADMISSION_DISEASE_NAME) where 1=1");
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and ADMISSION_DISEASE_NAME like '" + '%' + admissionDiseaseName + '%' + "'");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<DiseaseAndDiagnosticStatistics> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				DiseaseAndDiagnosticStatistics.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "ADMISSION_DISEASE_NAME", null);
		System.out.println(totals);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	public void getlistByinhosDiag(DiseaseAndDiagnosticStatisticsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		String admissionDiseaseName = dto.getAdmissionDiseaseName();
		if (admissionDiseaseName != null) {
			// System.out.println(inhosDiag);
			String a = admissionDiseaseName.replace("& #40;", "(");
			// System.out.println(a);
			String b = a.replace("& #41;", ")");
			// System.out.println(b);
			admissionDiseaseName = b;
			System.out.println(admissionDiseaseName);
		}
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(
				"select m.ADMISSION_DATE,m.HOSPITAL_NAME,m.PATIENT_NAME,m.PATIENT_AGE from T_FLY_MEDICAL_INHOS m where 1=1");
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_NAME='" + admissionDiseaseName + "'");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<DiseaseAndDiagnosticStatistics> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				DiseaseAndDiagnosticStatistics.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "m.HOSPITAL_NAME", null);
		/* System.out.println("dao" + list); */
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	public void getlistByinhosDate(DiseaseAndDiagnosticStatisticsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		String year = dto.getYear();// 年
		String month = dto.getMonth();// 月
		System.out.println("year" + year);
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);

		/*
		 * if (year == null || "".equals(year) && month == null || "".equals(month)) {
		 * Calendar date = Calendar.getInstance(); year =
		 * String.valueOf(date.get(Calendar.YEAR)); month =
		 * String.valueOf(date.get(Calendar.MONTH)); }
		 */

		sqlStr.append(
				"select m.ADMISSION_DATE,m.HOSPITAL_NAME,m.PATIENT_NAME,m.PATIENT_AGE from T_FLY_MEDICAL_INHOS m where 1=1");
		if (!StringUtils.isNullOrEmpty(year)) {
			sqlStr.append(" and to_char(m.ADMISSION_DATE,'yyyy') ='" + year + "'");
		}
		if (!StringUtils.isNullOrEmpty(month)) {
			sqlStr.append(" and to_char(m.ADMISSION_DATE,'yyyy-MM') ='" + month + "'");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<DiseaseAndDiagnosticStatistics> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				DiseaseAndDiagnosticStatistics.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "m.HOSPITAL_NAME", null);
		/* System.out.println("dao" + list); */
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 按参保类型统计分析
	 * 
	 * @param dto
	 */
	public void statisticalAnalysisByInsuranceType(DiseaseAndDiagnosticStatisticsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(" select BENEFIT_GROUP_ID,BMI_PAY_AMOUNT,ACCOMMODATION_FEE,DIAGNOSIS_FEE,INSPECTION_FEE,TEST_FEE,TREATMENT_FEE,");
		sqlStr.append("NURSING_FEE,MATERIAL_FEE,WESTERN_MEDICINE_FEE,CHINESE_MEDICINE_YINPIAN,CHINESE_MEDICINE_FORM,CONSULTATION_FEE,REGISTRATION_FEE,OTHER_FEE from (");
		sqlStr.append(
				" select mi.BENEFIT_GROUP_ID,sum(mi.BMI_PAY_AMOUNT)BMI_PAY_AMOUNT,sum(mi.ACCOMMODATION_FEE)ACCOMMODATION_FEE,sum(mi.DIAGNOSIS_FEE)DIAGNOSIS_FEE,");
		sqlStr.append(
				" sum(mi.INSPECTION_FEE)INSPECTION_FEE,sum(mi.TEST_FEE)TEST_FEE,sum(mi.TREATMENT_FEE)TREATMENT_FEE,sum(mi.NURSING_FEE)NURSING_FEE,sum(mi.MATERIAL_FEE)MATERIAL_FEE,");
		sqlStr.append(
				" sum(mi.WESTERN_MEDICINE_FEE)WESTERN_MEDICINE_FEE,sum(mi.CHINESE_MEDICINE_YINPIAN)CHINESE_MEDICINE_YINPIAN,sum(mi.CHINESE_MEDICINE_FORM)CHINESE_MEDICINE_FORM,sum(mi.CONSULTATION_FEE)CONSULTATION_FEE,");
		sqlStr.append(" sum(mi.REGISTRATION_FEE)REGISTRATION_FEE,sum(mi.OTHER_FEE)OTHER_FEE from ");
		sqlStr.append(" (");
		sqlStr.append(
				" select BENEFIT_GROUP_ID,BMI_PAY_AMOUNT,ACCOMMODATION_FEE,DIAGNOSIS_FEE,INSPECTION_FEE,TEST_FEE,");
		sqlStr.append(
				" TREATMENT_FEE,NURSING_FEE,MATERIAL_FEE,WESTERN_MEDICINE_FEE,CHINESE_MEDICINE_YINPIAN,CHINESE_MEDICINE_FORM,");
		sqlStr.append(" CONSULTATION_FEE,REGISTRATION_FEE,OTHER_FEE from T_FLY_MEDICAL_INHOS");
		sqlStr.append(" union all");
		sqlStr.append(
				" select BENEFIT_GROUP_ID,BMI_PAY_AMOUNT,ACCOMMODATION_FEE,DIAGNOSIS_FEE,INSPECTION_FEE,TEST_FEE,");
		sqlStr.append(
				" TREATMENT_FEE,NURSING_FEE,MATERIAL_FEE,WESTERN_MEDICINE_FEE,CHINESE_MEDICINE_YINPIAN,CHINESE_MEDICINE_FORM,");
		sqlStr.append(" CONSULTATION_FEE,REGISTRATION_FEE,OTHER_FEE from T_FLY_MEDICAL_MENZ");
		sqlStr.append(" )mi");
		sqlStr.append(" group by  mi.BENEFIT_GROUP_ID");
		sqlStr.append(")");
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<DiseaseAndDiagnosticStatistics> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				DiseaseAndDiagnosticStatistics.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}
}

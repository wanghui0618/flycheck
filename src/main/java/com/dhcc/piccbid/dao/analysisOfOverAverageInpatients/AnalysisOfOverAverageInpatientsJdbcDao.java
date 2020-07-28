package com.dhcc.piccbid.dao.analysisOfOverAverageInpatients;

import static org.hamcrest.CoreMatchers.nullValue;

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
import com.dhcc.piccbid.dao.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsJdbcDao;
import com.dhcc.piccbid.dto.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsDto;
import com.dhcc.piccbid.entity.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatients;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;

@Component
public class AnalysisOfOverAverageInpatientsJdbcDao {
	private static Log logger = LogFactory.getLog(DiseaseAndDiagnosticStatisticsJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	// 超平均住院人数
	public void getCount(AnalysisOfOverAverageInpatientsDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String patientName = dto.getPatientName();
		String admissionDiseaseName = dto.getAdmissionDiseaseName();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select  b.*\r\n" + 
				" from t_flycheck_medical b\r\n" +     //当天住院人数超三倍
				"inner join(\r\n" + 
				"select * from(\r\n" + 
				"select count(*) as kk,ADMISSION_DATE\r\n" + 
				"from t_flycheck_medical\r\n" + 
				"group by ADMISSION_DATE)\r\n" + 
				"where kk >=3*(select round(Avg(count(*)),2) as mm from t_flycheck_medical\r\n" + 
				"group by ADMISSION_DATE)) hid\r\n" + 
				"on b.ADMISSION_DATE  = hid.ADMISSION_DATE\r\n" + 
				"and 1=1");
		if (!StringUtils.isNullOrEmpty(patientName)) {
			sqlStr.append(" and b.PATIENT_NAME='" + patientName + "'");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and b.ADMISSION_DISEASE_NAME='" + admissionDiseaseName + "'");
		}
		//System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<DiseaseAndDiagnosticStatistics> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				DiseaseAndDiagnosticStatistics.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "rownum", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	// 两年内用药量
	public void towYears(AnalysisOfOverAverageInpatientsDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String type = dto.getType();
		String year = dto.getYear();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if (type == null && year == null) {
			sqlStr.append(
					"select a.hisid,a.HOSPITAL_NAME,a.ITEM_ID,a.ITEM_NAME,a.NUM,a.UNIT_PRICE,a.COST from T_FLY_DETAIL_INHOS a where 1=1 "
							+ "and year between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy') order by COST");
		}
		if (type != null && year.equals("")) {
			if (type.equals("0")) {
				sqlStr.append(
						"select a.hisid,a.HOSPITAL_NAME,a.ITEM_ID,a.ITEM_NAME,a.NUM,a.UNIT_PRICE,a.COST from T_FLY_DETAIL_INHOS a where 1=1 "
								+ "and year between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy') order by COST");
			}
			if (type.equals("1")) {
				sqlStr.append(
						"select  a.hisid,a.hospital_name,a.ITEM_ID,a.ITEM_NAME,a.num,a.unit_price,a.cost from T_FLY_DETAIL_MENZ a where 1=1 "
								+ "and year between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy') order by COST");
			}
		}
		if (year != null && !year.equals("")) {
			if (type.equals("0")) {
				sqlStr.append(
						"select a.hospital_name,a.ITEM_ID,a.ITEM_NAME,a.num,a.unit_price,a.cost from T_FLY_DETAIL_INHOS a where a.year='"
								+ year + "'");
			}
			if (type.equals("1")) {
				sqlStr.append(
						"select a.hospital_name,a.ITEM_ID,a.ITEM_NAME,a.num,a.unit_price,a.cost from T_FLY_DETAIL_MENZ a where a.year='"
								+ year + "'");
			}
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<AnalysisOfOverAverageInpatients> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				AnalysisOfOverAverageInpatients.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "hisid", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	// 近两年诊疗统计
	public void countDiagnosisAndTreatmentItems(AnalysisOfOverAverageInpatientsDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String year = dto.getYear();
		System.out.println(year);
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if (year == null||year.equals("")) {
			sqlStr.append("select a.hisid,a.item_id,a.item_name,a.num,a.cost from T_FLY_DETAIL_INHOS a inner join");
			sqlStr.append(" (select l.item_name from T_FLY_LINSHI l)l on a.item_name=l.item_name");
			sqlStr.append(" and a.year between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy')");
		}
		if (year != null && !year.equals("")) {
			sqlStr.append("select a.hisid,a.item_id,a.item_name,a.num,a.cost from T_FLY_DETAIL_INHOS a inner join");
			sqlStr.append(
					"(select l.item_name from T_FLY_LINSHI l)l on a.item_name=l.item_name and a.year='" + year + "'");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<AnalysisOfOverAverageInpatients> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				AnalysisOfOverAverageInpatients.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "hisid", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}
}

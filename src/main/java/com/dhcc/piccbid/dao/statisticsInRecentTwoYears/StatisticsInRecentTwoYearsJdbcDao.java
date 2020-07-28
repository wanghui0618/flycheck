package com.dhcc.piccbid.dao.statisticsInRecentTwoYears;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.From;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.sql.Select;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.statisticsInRecentTwoYears.StatisticsInRecentTwoYearsDto;
import com.dhcc.piccbid.entity.medical.Medical;
import com.dhcc.piccbid.entity.medical.MedicalVo;
import com.dhcc.piccbid.entity.medicaldetail.MedicalDetail;

@Component
public class StatisticsInRecentTwoYearsJdbcDao {

	private static Log logger = LogFactory.getLog(StatisticsInRecentTwoYearsJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	// 近两年项目统计
	public void Dosage(StatisticsInRecentTwoYearsDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String type = dto.getType();
		String year = dto.getYear();
		//System.out.println(1111111111);
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if (type == null || type.equals("1")) {
			if (year == null || year.equals("")) {
				sqlStr.append("select ITEM_NAME,ITEM_NUM,ITEM_PRICE,ITEM_COST from (");
				sqlStr.append(
						" select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where 1=1 and a.p_category like '%药%' and to_char(a.bill_date,'yyyy')");
				sqlStr.append(
						" between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy') group by a.item_name)");
			} else {
				sqlStr.append("select ITEM_NAME,ITEM_NUM,ITEM_PRICE,ITEM_COST from (");
				sqlStr.append(
						" select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where 1=1 and a.p_category like '%药%' and to_char(a.bill_date,'yyyy')='" + year
						+ "' group by a.item_name)");
			}
		}
		//System.out.println(sqlStr);
		if (type != null && type.equals("2")) {
			if (year == null || year.equals("")) {
				sqlStr.append("select ITEM_NAME,ITEM_NUM,ITEM_PRICE,ITEM_COST from (");
				sqlStr.append(
						" select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where 1=1 and a.p_category in('诊查费','检查费','化验费','治疗费','手术费','护理费') and to_char(a.bill_date,'yyyy')");
				sqlStr.append(
						" between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy') group by a.item_name)");
			} else {
				sqlStr.append("select ITEM_NAME,ITEM_NUM,ITEM_PRICE,ITEM_COST from (");
				sqlStr.append(
						" select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where 1=1 and a.p_category in('诊查费','检查费','化验费','治疗费','手术费','护理费') and to_char(a.bill_date,'yyyy')='" + year
						+ "' group by a.item_name)");
			}
		}
		if (type != null && type.equals("3")) {
			if (year == null || year.equals("")) {
				// 此处时间为T_PICCBID_MEDICAL_DETAIL明细表结算时间BALANCE_DATE
				sqlStr.append(
						"select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(
						" where a.ITEM_NAME in ('M型超声','二维超声') and to_char(a.bill_date,'yyyy') between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy')");
				sqlStr.append(" GROUP BY   a.ITEM_NAME");
			} else {
				// 此处时间为T_PICCBID_MEDICAL_DETAIL明细表结算时间BALANCE_DATE
				sqlStr.append(
						"select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where 1=1 and a.ITEM_NAME in ('M型超声','二维超声') and to_char(a.bill_date,'yyyy')='" + year
						+ "' GROUP BY   a.ITEM_NAME");
			}
		}
		if (type != null && type.equals("4")) {
			if (year == null || year.equals("")) {
				sqlStr.append(
						"select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where a.ITEM_NAME in ('注射用细辛脑','注射用头孢他啶','参芎葡头糖注射液')");
				sqlStr.append(
						" and to_char(a.bill_date,'yyyy') between to_char(add_months(sysdate,-24),'yyyy') and to_char(sysdate,'yyyy')");
				sqlStr.append(" group by a.ITEM_NAME");
			} else {
				sqlStr.append("select ITEM_NAME,ITEM_NUM,ITEM_PRICE,ITEM_COST from (");
				sqlStr.append(
						" select a.ITEM_NAME,sum(a.NUM) as ITEM_NUM,sum(a.unit_price) as ITEM_PRICE,sum(a.Cost) as ITEM_COST from T_FLYCHECK_MEDICAL_DETAIL a");
				sqlStr.append(" where 1=1 and  a.ITEM_NAME in ('注射用细辛脑','注射用头孢他啶','参芎葡头糖注射液')");
				sqlStr.append(" and to_char(a.bill_date,'yyyy')='" + year + "' group by a.ITEM_NAME");
				sqlStr.append(")");
			}
		}
		//System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<MedicalDetail> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				MedicalDetail.class, hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(),
				"rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	// 超平均住院人数
	public void getCount(StatisticsInRecentTwoYearsDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String name = dto.getName();
		String type = dto.getType();
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
		if (!StringUtils.isNullOrEmpty(name)) {
			sqlStr.append(" and b.name ='" + name + "'");
		}
		/*if (!StringUtils.isNullOrEmpty(type)) {
			sqlStr.append(" and me.DIAG_TYPE ='" + type + "'");
		}*/
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<MedicalVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), MedicalVo.class,
				hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 按病种诊断结果统计
	 */
	public void getlist(StatisticsInRecentTwoYearsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		String admissionDiseaseName = dto.getInDiagnosisName();
		if (admissionDiseaseName != null) {
			String a = admissionDiseaseName.replace("& #40;", "(");
			String b = a.replace("& #41;", ")");
			admissionDiseaseName = b;
		}
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(
				"select IN_DIAGNOSIS_NAME from (select IN_DIAGNOSIS_NAME from T_PICCBID_MEDICAL group by IN_DIAGNOSIS_NAME) where 1=1");
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and IN_DIAGNOSIS_NAME like '" + '%' + admissionDiseaseName + '%' + "'");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<Medical> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Medical.class,
				hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "IN_DIAGNOSIS_NAME", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	public void getlistByinhosDiag(StatisticsInRecentTwoYearsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		String admissionDiseaseName = dto.getInDiagnosisName();
		String year = dto.getYear();// 年
		String month = dto.getMonth();// 月
		if (admissionDiseaseName != null) {
			String a = admissionDiseaseName.replace("& #40;", "(");
			String b = a.replace("& #41;", ")");
			admissionDiseaseName = b;
		}
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select m.PAYMENT_DATE,m.ORG_NAME,m.NAME,m.AGE from T_PICCBID_MEDICAL m where 1=1");
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and m.IN_DIAGNOSIS_NAME='" + admissionDiseaseName + "'");
		}
		if (!StringUtils.isNullOrEmpty(year)) {
			sqlStr.append(" and to_char(m.PAYMENT_DATE,'yyyy') ='" + year + "'");
		}
		if (!StringUtils.isNullOrEmpty(month)) {
			sqlStr.append(" and to_char(m.PAYMENT_DATE,'yyyy-MM') ='" + month + "'");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<Medical> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Medical.class,
				hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 按参保类型统计分析
	 * 
	 * @param dto
	 */
	public void statisticalAnalysisByInsuranceType(StatisticsInRecentTwoYearsDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select * from (");
		sqlStr.append(
				"select mi.INSURE_PERSON_TYPE,sum(mi.BASIC_COST_M)BASIC_COST_M,sum(mi.BASIC_COST_R)BASIC_COST_R,sum(mi.POVERTY_ALLEVIATION_SUBSIDY)POVERTY_ALLEVIATION_SUBSIDY,sum(mi.FINANCE_SUBSIDY)FINANCE_SUBSIDY,sum(mi.OFFICIAL_SUBSIDY)OFFICIAL_SUBSIDY from");
		sqlStr.append(
				" (select INSURE_PERSON_TYPE,BASIC_COST_M,BASIC_COST_R,POVERTY_ALLEVIATION_SUBSIDY,FINANCE_SUBSIDY,OFFICIAL_SUBSIDY from T_PICCBID_MEDICAL)mi group by mi.INSURE_PERSON_TYPE");
		sqlStr.append(")");
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<Medical> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Medical.class,
				hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}
}

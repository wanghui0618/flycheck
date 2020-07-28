package com.dhcc.piccbid.dao.hospitalInformationStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dhcc.framework.utils.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.hospitalInformationStatistics.HospitalInformationStatisticsDto;
import com.dhcc.piccbid.entity.hospitalInformationStatistics.HospitalInformationStatisticsVo;

@Component
public class HospitalInformationStatisticsJdbcDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	/**
	 * 住院数据统计
	 * @param dto
	 * @return
	 */
	public void dataStatistics(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select month,count(hisid) as hospitalization_time,"
				+ "count(distinct patient_id) as hospitalization_num,"
				+ "sum(zyts) as hospitalization_total_num,"
				+ "to_char(sum(total_amount)/10000,'FM999999990.00') as hospitalization_total_cost,"
				+ "to_char(sum(bmi_pay_amount)/10000,'FM999999990.00') as hospitalization_basic_payment,"
				+ "to_char(sum(bmi_convered_amount)/10000,'FM999999990.00') as hospitalization_med_insurance,"
				+ "to_char(sum(accommodation_fee)/sum(zyts),'FM999999990.00') as bed_fee,"
				+ "to_char(sum(total_amount)/count(Hisid),'FM999999990.00') as average_cost "
				+ "from T_FLYCHECK_MEDICAL where 1=1 ");
		String medicalName = dto.getMedicalName();
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(medicalName) && medicalName != null && medicalName != "") {
			sqlStr.append(" and benefit_type = '"+medicalName+"' ");
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by month order by month ");
		System.err.println("住院数据统计=="+sqlStr);
		System.out.println("hospital=="+hospitalCode);
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "month");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 门诊数据统计
	 * @param dto
	 * @return
	 */
	public void outpatientDataStatistics(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select "
							+ "month,"
							+ "count(hisid) as outpatient_time,"
							+ "count(distinct patient_id) as outpatient_num,"
							+ "to_char((count(Hisid)-count(distinct patient_id))*100/count(distinct patient_id),'FM999999990.00') as outpatient_consultation,"
							+ "to_char(sum(total_amount)/10000,'FM999999990.00') as outpatient_total_cost,"
							+ "to_char(sum(bmi_pay_amount)/10000,'FM999999990.00') as basic_overall_payment,"
							+ "to_char(sum(bmi_convered_amount)/10000,'FM999999990.00') as basic_med_insurance "
							+ "from T_FLYCHECK_MEDICAL_MZ where month between '1' and '12' ");
		String medicalName = dto.getMedicalName();
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(medicalName) && medicalName != null && medicalName != "") {
			sqlStr.append(" and benefit_type = '"+medicalName+"' ");
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by month order by month ");
		System.err.println("门诊数据统计=="+sqlStr);
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "*");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 住院各项目收费占比
	 * @param dto
	 */
	public void proportionChargingItems(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select sum(accommodation_fee) as accommodation_fee,"
				+ "nvl(sum(diagnosis_fee),0) as diagnosis_fee,"
				+ "nvl(sum(inspection_fee),0) as inspection_fee,"
				+ "nvl(sum(test_fee),0) as test_fee,"
				+ "nvl(sum(treatment_fee),0) as treatment_fee,"
				+ "nvl(sum(nursing_fee),0) as nursing_fee,"
				+ "nvl(sum(material_fee),0) as material_fee,"
				+ "nvl(sum(western_medicine_fee),0) as western_medicine_fee,"
				+ "nvl(sum(chinese_medicine_yinpian),0) as chinese_medicine_yinpian,"
				+ "nvl(sum(chinese_medicine_form),0) as chinese_medicine_form,"
				+ "nvl(sum(consultation_fee),0) as consultation_fee,"
				+ "nvl(sum(registration_fee),0) as registration_fee,"
				+ "nvl(sum(other_fee),0) as other_fee "
				+ "from T_FLYCHECK_MEDICAL ");
		sqlStr.append(" where 1=1 ");
		String medicalName = dto.getMedicalName();
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(medicalName) && medicalName != null && medicalName != "") {
			sqlStr.append(" and benefit_type = '"+medicalName+"' ");
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, 1, 13, "id");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	/**
	 * 门诊各项目收费占比
	 * @param dto
	 */
	public void proportionChargingItemsMed(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select sum(accommodation_fee) as accommodation_fee,"
				+ "nvl(sum(diagnosis_fee),0) as diagnosis_fee,"
				+ "nvl(sum(inspection_fee),0) as inspection_fee,"
				+ "nvl(sum(test_fee),0) as test_fee,"
				+ "nvl(sum(treatment_fee),0) as treatment_fee,"
				+ "nvl(sum(nursing_fee),0) as nursing_fee,"
				+ "nvl(sum(material_fee),0) as material_fee,"
				+ "nvl(sum(western_medicine_fee),0) as western_medicine_fee,"
				+ "nvl(sum(chinese_medicine_yinpian),0) as chinese_medicine_yinpian,"
				+ "nvl(sum(chinese_medicine_form),0) as chinese_medicine_form,"
				+ "nvl(sum(consultation_fee),0) as consultation_fee,"
				+ "nvl(sum(registration_fee),0) as registration_fee,"
				+ "nvl(sum(other_fee),0) as other_fee "
				+ "from T_FLYCHECK_MEDICAL_MZ");
		sqlStr.append(" where 1=1 ");
		String medicalName = dto.getMedicalName();
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(medicalName) && medicalName != null && medicalName != "") {
			sqlStr.append(" and benefit_type = '"+medicalName+"' ");
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, 1, 13, "rownum");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/***
	 * 月度平均住院天数
	 * @param dto
	 */
	public void averageLengthOfStay(HospitalInformationStatisticsDto dto) {
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select sum(zyts)as hospitalization_total_num,count(distinct patient_id)as hospitalization_num,month\r\n" + 
				" from t_flycheck_medical where year ='2018' group by month order by month");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class, null);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 住院药品/诊疗/耗材占比
	 * @param dto
	 */
	public void drugsDiagnosisTreatment(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(" select nvl(medical_cost,0) medical_cost, ");
		sqlStr.append(" nvl(treatment_cost,0) treatment_cost, ");
		sqlStr.append(" nvl(consumable_cost,0) consumable_cost  ");
		sqlStr.append(" from (select item_type,sum(cost) as total_cost from T_FLYCHECK_MEDICAL_DETAIL  where  1=1 ");
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by item_type)  ");
		sqlStr.append(" pivot(min(total_cost) for item_type in ('1' as medical_cost,'2' as treatment_cost,'3' as consumable_cost)) ");
		System.err.println("三目占比=="+sqlStr);
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class, null);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 门诊药品/诊疗/耗材占比
	 * @param dto
	 */
	public void drugsDiagnosisTreatmentMed(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(" select nvl(medical_cost,0) medical_cost, ");
		sqlStr.append(" nvl(treatment_cost,0) treatment_cost, ");
		sqlStr.append(" nvl(consumable_cost,0) consumable_cost  ");
		sqlStr.append(" from (select item_type,sum(cost) as total_cost from T_FLYCHECK_MEDICAL_MZ_DETAIL  where 1=1 ");
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by item_type)  ");
		sqlStr.append(" pivot(min(total_cost) for item_type in ('1' as medical_cost,'2' as treatment_cost,'3' as consumable_cost)) ");
		System.out.println("门诊三目"+sqlStr);
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class, null);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 住院科室费用top10
	 * @param dto
	 */
	public void departmentRanking(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select discharge_dept_name,total_cost from("  
							+ "select decode(discharge_dept_name,null,'未知',discharge_dept_name) as discharge_dept_name,sum(cost) as total_cost from "
							+ "T_FLYCHECK_MEDICAL_DETAIL ");
		sqlStr.append(" where 1=1 ");
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by discharge_dept_name order by sum(cost) desc) ");
		sqlStr.append(" where rownum <= 10 ");


		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, 1, 10, "rownum");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 门诊科室费用top10
	 * @param dto
	 */
	public void departmentRankingMed(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select discharge_dept_name,total_cost from("  
							+ "select decode(discharge_dept_name,null,'未知',discharge_dept_name) as discharge_dept_name,sum(cost) as total_cost from "
							+ "T_FLYCHECK_MEDICAL_MZ_DETAIL ");
		sqlStr.append(" where 1=1 ");
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by discharge_dept_name order by sum(cost) desc)");
		sqlStr.append(" where rownum <= 10 ");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, 1, 10, "rownum");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 住院诊疗费用top10
	 * @param dto
	 */
	public void treatmentRanking(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select item_name,treatment_total_cost from("
							+ "select item_name,nvl(sum(cost),0) as treatment_total_cost from "
							+ "T_FLYCHECK_MEDICAL_DETAIL where item_type='2'  ");
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by item_name) ");
		sqlStr.append(" where rownum<=10 ");
		System.out.println("top10诊疗"+sqlStr);
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, 1, 10, "rownum");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 门诊诊疗费用top10
	 * @param dto
	 */
	public void treatmentRankingMed(HospitalInformationStatisticsDto dto) {
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select item_name,treatment_total_cost from("
							+ "select item_name,nvl(sum(cost),0) as treatment_total_cost from "
							+ "T_FLYCHECK_MEDICAL_MZ_DETAIL where item_type='2' ");
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by item_name) ");
		sqlStr.append(" where rownum<=10 ");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalInformationStatisticsVo.class,hqlParamMap, 1, 10, "rownum");
		@SuppressWarnings("unchecked")
		List<HospitalInformationStatisticsVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalInformationStatisticsVo.class, hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 方法名:exportBeHospitalized
	 * 方法功能描述:医院住院详细信息数据导出查询方法
	 * @param:HospitalInformationStatisticsDto
	 * @return:List<HospitalInformationStatisticsVo>
	 * @Author:王彤
	 * @Create Date:2020年1月7日
	 */
	public List<HospitalInformationStatisticsVo> exportBeHospitalized(HospitalInformationStatisticsDto dto){
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> sqlParamMap = new HashMap<String, Object>();
		sqlStr.append("select month,count(hisid) as hospitalization_time,"
				+ "count(distinct patient_id) as hospitalization_num,"
				+ "sum(zyts) as hospitalization_total_num,"
				+ "to_char(sum(total_amount)/10000,'FM999999990.00') as hospitalization_total_cost,"
				+ "to_char(sum(bmi_pay_amount)/10000,'FM999999990.00') as hospitalization_basic_payment,"
				+ "to_char(sum(bmi_convered_amount)/10000,'FM999999990.00') as hospitalization_med_insurance,"
				+ "to_char(sum(accommodation_fee)/sum(zyts),'FM999999990.00') as bed_fee,"
				+ "to_char(sum(total_amount)/count(Hisid),'FM999999990.00') as average_cost "
				+ "from T_FLYCHECK_MEDICAL where 1=1 ");
		String medicalName = dto.getMedicalName();
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(medicalName) && medicalName != null && medicalName != "") {
			sqlStr.append(" and benefit_type = '"+medicalName+"' ");
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by month order by month ");
		@SuppressWarnings("unused")
		List<HospitalInformationStatisticsVo> hospitalInformationStatistics = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				HospitalInformationStatisticsVo.class, sqlParamMap);
		return hospitalInformationStatistics;
		
	}


	/**
	 * 方法名:exportOutpatient
	 * 方法功能描述:医院门诊详细信息数据导出查询方法
	 * @param:HospitalInformationStatisticsDto
	 * @return:List<HospitalInformationStatisticsVo>
	 * @Author:王彤
	 * @Create Date:2020年1月7日
	 */
	public List<HospitalInformationStatisticsVo> exportOutpatient(HospitalInformationStatisticsDto dto){
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> sqlParamMap = new HashMap<String, Object>();
		sqlStr.append("select "
				+ "month,"
				+ "count(hisid) as outpatient_time,"
				+ "count(distinct patient_id) as outpatient_num,"
				+ "to_char((count(Hisid)-count(distinct patient_id))*100/count(distinct patient_id),'FM999999990.00') as outpatient_consultation,"
				+ "to_char(sum(total_amount)/10000,'FM999999990.00') as outpatient_total_cost,"
				+ "to_char(sum(bmi_pay_amount)/10000,'FM999999990.00') as basic_overall_payment,"
				+ "to_char(sum(bmi_convered_amount)/10000,'FM999999990.00') as basic_med_insurance "
				+ "from T_FLYCHECK_MEDICAL_MZ where month between '1' and '12' ");
		String medicalName = dto.getMedicalName();
		String hospitalCode = dto.getHospitalCode();
		String hospitalName = dto.getHospitalName();
		String balanceDatea = dto.getBalanceDatea();
		String balanceDateb = dto.getBalanceDateb();
		String[] split = hospitalCode.split(",|，");
		String newCode="";
		for(int i = 0 ; i <split.length ; i++) {
			 newCode=newCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 newCode=newCode+",";
			 }
		}
		if (!StringUtils.isNullOrEmpty(medicalName) && medicalName != null && medicalName != "") {
			sqlStr.append(" and benefit_type = '"+medicalName+"' ");
		}
		if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
			sqlStr.append(" and hospital_id in("+newCode+") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
			sqlStr.append(" and hospital_name = '"+hospitalName+"' ");
		}*/
		if (!StringUtils.isNullOrEmpty(balanceDatea) && balanceDatea != null && balanceDatea != "") {
			if (!StringUtils.isNullOrEmpty(balanceDateb) && balanceDateb != null && balanceDateb != "") {
				sqlStr.append(" and to_char(bill_date,'yyyyMMdd') between '"+balanceDatea+"' and '"+balanceDateb+"' ");
			}
		}
		sqlStr.append(" group by month order by month ");
		@SuppressWarnings("unused")
		List<HospitalInformationStatisticsVo> hospitalInformationStatisticsVos = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				HospitalInformationStatisticsVo.class, sqlParamMap);
		return hospitalInformationStatisticsVos;
	}



}

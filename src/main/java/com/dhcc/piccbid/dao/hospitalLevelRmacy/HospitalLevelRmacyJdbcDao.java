package com.dhcc.piccbid.dao.hospitalLevelRmacy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalDetailVo;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalVo;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmission;

@Component
public class HospitalLevelRmacyJdbcDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	/***
	 * 限医院等级用药主单
	 * 
	 * @param dto
	 */
	public void limitedHospitalLevelZd(HospitalLevelRmacyDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String type = dto.getType();
		FlyCheckMedicalVo flyCheckMedicalVo = dto.getFlyCheckMedicalVo();

		if ("0".equals(type)) {
			sqlStr.append(
					"select hisid,p_level,bill_date,hospital_id,discharge_disease_id_main as admission_disease_id,discharge_disease_name_main as admission_disease_name,hospital_name,zyh,social_card_id,discharge_dept_name,patient_name,admission_date,\r\n" + 
					"discharge_date,zyts,total_amount,benefit_type,western_medicine_fee,chinese_medicine_yinpian,chinese_medicine_form\r\n" + 
					"from t_flycheck_medical where 1=1");
		} else {
			sqlStr.append(
					"select hisid,p_level,bill_date,hospital_id,admission_disease_id,admission_disease_name,hospital_name,social_card_id,patient_name,total_amount,benefit_type,western_medicine_fee,chinese_medicine_yinpian,chinese_medicine_form\r\n"
							+ "       from t_flycheck_medical_mz where 1=1");
		}
		if(dto.getFlyCheckMedicalVo() !=null) {
			if(dto.getFlyCheckMedicalVo().getHospitalName()!=null && !dto.getFlyCheckMedicalVo().getHospitalName().equals("")) {
				sqlStr.append(" and Hospital_Name='"+dto.getFlyCheckMedicalVo().getHospitalName()+"'");
			}
			if(dto.getFlyCheckMedicalVo().getAdmissionDiseaseName()!=null && !dto.getFlyCheckMedicalVo().getAdmissionDiseaseName().equals("")) {
				
				sqlStr.append(" and Admission_Disease_Name='"+dto.getFlyCheckMedicalVo().getAdmissionDiseaseName()+"'");
			}
			if(dto.getFlyCheckMedicalVo().getBillDate()!=null && !dto.getFlyCheckMedicalVo().getBillDate().equals("")) {
				String billdate = dto.getFlyCheckMedicalVo().getBillDate();
				String indate2 = billdate.substring(16, 24);
				String indate1 = billdate.substring(0, 8);
				sqlStr.append(" and bill_date >=to_date('" + indate1 + "','yyyy-mm-dd') and bill_date<=to_date('" + indate2 + "','yyyy-mm-dd')");
			}
			if(dto.getFlyCheckMedicalVo().getpLevel()!=null && !dto.getFlyCheckMedicalVo().getpLevel().equals("")) {
				sqlStr.append(" and P_LEVEL='"+dto.getFlyCheckMedicalVo().getpLevel()+"'");
			}
			if(dto.getFlyCheckMedicalVo().getAdmissionDate()!=null && !dto.getFlyCheckMedicalVo().getAdmissionDate().equals("")) {
				String date1 = dto.getFlyCheckMedicalVo().getAdmissionDate();
				String indate2 = date1.substring(16, 24);
				String indate1 = date1.substring(0, 8);
				sqlStr.append(" and Admission_Date >=to_date('" + indate1 + "','yyyy-mm-dd') and Admission_Date<=to_date('" + indate2 + "','yyyy-mm-dd')");
			}
			if(dto.getFlyCheckMedicalVo().getDischargeDate()!=null && !dto.getFlyCheckMedicalVo().getDischargeDate().equals("")) {
				String date2 = dto.getFlyCheckMedicalVo().getDischargeDate();
				String indate2 = date2.substring(16, 24);
				String indate1 = date2.substring(0, 8);
				sqlStr.append(" and Admission_Date >=to_date('" + indate1 + "','yyyy-mm-dd') and Admission_Date<=to_date('" + indate2 + "','yyyy-mm-dd')");
			
			}
			}
		Integer listCount = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "hisid", hqlParamMap);
		@SuppressWarnings("unchecked")
		List<FlyCheckMedicalVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyCheckMedicalVo.class, hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(),
				null);
		dto.getPageModel().setTotals(listCount);
		dto.getPageModel().setPageData(list);
	}

	/***
	 * 限医院等级用药明细
	 * 
	 * @param dto
	 */
	public void limitedHospitalLevelMx(HospitalLevelRmacyDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		FlyCheckMedicalDetailVo flyCheckMedicalDetailVo = dto.getFlyCheckMedicalDetailVo();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String type = dto.getType();
		if ("0".equals(type)) {
			sqlStr.append(
					"select hisid,item_id,item_name,item_id_hosp,item_name_hosp,p_category,unit_price,num,cost,p_type\r\n"
							+ "       from t_flycheck_medical_detail where 1=1");

		} else {
			sqlStr.append(
					"select hisid,item_id,item_name,item_id_hosp,item_name_hosp,p_category,unit_price,num,cost,p_type\r\n"
							+ "       from t_flycheck_medical_mz_detail where 1=1");

		}

		if (flyCheckMedicalDetailVo != null) {
			euqalSpell(flyCheckMedicalDetailVo.getHisid(), "hisid", sqlStr, 0, type);
			euqalSpell(flyCheckMedicalDetailVo.getItemName(), "item_name", sqlStr, 1, type);
			euqalSpell(flyCheckMedicalDetailVo.getItemId(), "item_id", sqlStr, 0, type);
			euqalSpell(flyCheckMedicalDetailVo.getpCategory(), "p_category", sqlStr, 0, type);
		}
		Integer listCount = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "hisid", hqlParamMap);
		@SuppressWarnings("unchecked")
		List<FlyCheckMedicalDetailVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyCheckMedicalDetailVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), null);
		dto.getPageModel().setTotals(listCount);
		dto.getPageModel().setPageData(list);
	}

	/***
	 * 主单统计
	 * 
	 * @param dto
	 */
	public void countMedical(HospitalLevelRmacyDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String type = dto.getType();
		if ("1".equals(type)) {
			sqlStr.append(
					"select count(*)as total,nvl(sum(total_amount),0) as money from t_flycheck_medical_mz where 1=1");
		} else {
			sqlStr.append(
					
		"select count(*)as total,nvl(sum(total_amount),0) as money from t_flycheck_medical where 1=1");
		}
		if(dto.getFlyCheckMedicalVo() !=null) {
		if(dto.getFlyCheckMedicalVo().getHospitalName()!=null && !dto.getFlyCheckMedicalVo().getHospitalName().equals("")) {
			sqlStr.append(" and Hospital_Name='"+dto.getFlyCheckMedicalVo().getHospitalName()+"'");
		}
		if(dto.getFlyCheckMedicalVo().getAdmissionDiseaseName()!=null && !dto.getFlyCheckMedicalVo().getAdmissionDiseaseName().equals("")) {
			
			sqlStr.append(" and Admission_Disease_Name='"+dto.getFlyCheckMedicalVo().getAdmissionDiseaseName()+"'");
		}
		if(dto.getFlyCheckMedicalVo().getBillDate()!=null && !dto.getFlyCheckMedicalVo().getBillDate().equals("")) {
			String billdate = dto.getFlyCheckMedicalVo().getBillDate();
			String indate2 = billdate.substring(16, 24);
			String indate1 = billdate.substring(0, 8);
			sqlStr.append(" and bill_date >=to_date('" + indate1 + "','yyyy-mm-dd') and bill_date<=to_date('" + indate2 + "','yyyy-mm-dd')");
		}
		if(dto.getFlyCheckMedicalVo().getpLevel()!=null && !dto.getFlyCheckMedicalVo().getpLevel().equals("")) {
			sqlStr.append(" and P_LEVEL='"+dto.getFlyCheckMedicalVo().getpLevel()+"'");
		}
		if(dto.getFlyCheckMedicalVo().getAdmissionDate()!=null && !dto.getFlyCheckMedicalVo().getAdmissionDate().equals("")) {
			String date1 = dto.getFlyCheckMedicalVo().getAdmissionDate();
			String indate2 = date1.substring(16, 24);
			String indate1 = date1.substring(0, 8);
			sqlStr.append(" and Admission_Date >=to_date('" + indate1 + "','yyyy-mm-dd') and Admission_Date<=to_date('" + indate2 + "','yyyy-mm-dd')");
		}
		if(dto.getFlyCheckMedicalVo().getDischargeDate()!=null && !dto.getFlyCheckMedicalVo().getDischargeDate().equals("")) {
			String date2 = dto.getFlyCheckMedicalVo().getDischargeDate();
			String indate2 = date2.substring(16, 24);
			String indate1 = date2.substring(0, 8);
			sqlStr.append(" and Admission_Date >=to_date('" + indate1 + "','yyyy-mm-dd') and Admission_Date<=to_date('" + indate2 + "','yyyy-mm-dd')");
		
		}
		}
		@SuppressWarnings("unchecked")
		List<FlyCheckMedicalVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyCheckMedicalVo.class, null);
		dto.getPageModel().setPageData(list);
	}

	/***
	 * 明细统计
	 * 
	 * @param dto
	 */
	public void countMx(HospitalLevelRmacyDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		String type = dto.getType();
		if ("1".equals(type)) {
			sqlStr.append(
					"select count(*)as total_mx,nvl(sum(a.cost),0) as money_mx \r\n" + 
					"from t_flycheck_medical_mz_detail a,t_flycheck_medical_mz b\r\n" + 
					"where a.hisid=b.hisid");
		} else {
			
			sqlStr.append(
					"select count(*)as total_mx,nvl(sum(a.cost),0) as money_mx \r\n" + 
					"from t_flycheck_medical_detail a,t_flycheck_medical b\r\n" + 
					"where 1=1 and a.hisid=b.hisid");
		}
		if(dto.getFlyCheckMedicalVo() !=null) {
			if(dto.getFlyCheckMedicalVo().getHospitalName()!=null && !dto.getFlyCheckMedicalVo().getHospitalName().equals("")) {
				sqlStr.append(" and b.Hospital_Name='"+dto.getFlyCheckMedicalVo().getHospitalName()+"'");
			}
			if(dto.getFlyCheckMedicalVo().getAdmissionDiseaseName()!=null && !dto.getFlyCheckMedicalVo().getAdmissionDiseaseName().equals("")) {
				sqlStr.append(" and b.Admission_Disease_Name='"+dto.getFlyCheckMedicalVo().getAdmissionDiseaseName()+"'");
			}
			if(dto.getFlyCheckMedicalVo().getBillDate()!=null && !dto.getFlyCheckMedicalVo().getBillDate().equals("")) {
				String billdate = dto.getFlyCheckMedicalVo().getBillDate();
				String indate2 = billdate.substring(16, 24);
				String indate1 = billdate.substring(0, 8);
				sqlStr.append(" and b.bill_date >=to_date('" + indate1 + "','yyyy-mm-dd') and b.bill_date<=to_date('" + indate2 + "','yyyy-mm-dd')");
			}
			if(dto.getFlyCheckMedicalVo().getpLevel()!=null && !dto.getFlyCheckMedicalVo().getpLevel().equals("")) {
				sqlStr.append(" and b.P_LEVEL='"+dto.getFlyCheckMedicalVo().getpLevel()+"'");
			}
			if(dto.getFlyCheckMedicalVo().getAdmissionDate()!=null && !dto.getFlyCheckMedicalVo().getAdmissionDate().equals("")) {
				String date1 = dto.getFlyCheckMedicalVo().getAdmissionDate();
				String indate2 = date1.substring(16, 24);
				String indate1 = date1.substring(0, 8);
				sqlStr.append(" and b.Admission_Date >=to_date('" + indate1 + "','yyyy-mm-dd') and b.Admission_Date<=to_date('" + indate2 + "','yyyy-mm-dd')");
			}
			if(dto.getFlyCheckMedicalVo().getDischargeDate()!=null && !dto.getFlyCheckMedicalVo().getDischargeDate().equals("")) {
				String date2 = dto.getFlyCheckMedicalVo().getDischargeDate();
				String indate2 = date2.substring(16, 24);
				String indate1 = date2.substring(0, 8);
				sqlStr.append(" and b.Discharge_Date >=to_date('" + indate1 + "','yyyy-mm-dd') and b.Discharge_Date<=to_date('" + indate2 + "','yyyy-mm-dd')");
			
			}
			}
		@SuppressWarnings("unchecked")
		List<FlyCheckMedicalVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyCheckMedicalVo.class, null);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 导出到excel
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SXSSFWorkbook exportExcel(HospitalLevelRmacyDto dto) {
		// 获取数据
		StringBuilder sqlStr = new StringBuilder();
		SXSSFWorkbook wb = new SXSSFWorkbook();
		String type = dto.getType();
		int pageSize = 3000;
		FlyCheckMedicalVo flyCheckMedicalVo = dto.getFlyCheckMedicalVo();

		if ("0".equals(type)) {
			sqlStr.append(
					"select hisid,p_level,bill_date,hospital_id,discharge_disease_id_main as admission_disease_id,discharge_disease_name_main as admission_disease_name,hospital_name,zyh,social_card_id,discharge_dept_name,patient_name,admission_date,\r\n"
							+ "       discharge_date,zyts,total_amount,benefit_type,western_medicine_fee,chinese_medicine_yinpian,chinese_medicine_form\r\n"
							+ "       from t_flycheck_medical where 1=1");
		} else {
			sqlStr.append(
					"select hisid,p_level,bill_date,hospital_id,admission_disease_id,admission_disease_name,hospital_name,social_card_id,patient_name,total_amount,benefit_type,western_medicine_fee,chinese_medicine_yinpian,chinese_medicine_form\r\n"
							+ "       from t_flycheck_medical_mz where 1=1");
		}
		if (flyCheckMedicalVo != null) {
			euqalSpell(flyCheckMedicalVo.getHospitalId(), "hospital_id", sqlStr, 0, type);
			euqalSpell(flyCheckMedicalVo.getHospitalName(), "hospital_name", sqlStr, 1, type);
			euqalSpell(flyCheckMedicalVo.getBillDate(), "bill_date", sqlStr, 4, type);
			if ("0".equals(type)) {
				euqalSpell(flyCheckMedicalVo.getAdmissionDiseaseId(), "discharge_disease_id_main", sqlStr, 0, type);
				euqalSpell(flyCheckMedicalVo.getAdmissionDiseaseName(), "discharge_disease_name_main", sqlStr, 1, type);
			} else {
				euqalSpell(flyCheckMedicalVo.getAdmissionDiseaseId(), "admission_disease_id", sqlStr, 0, type);
				euqalSpell(flyCheckMedicalVo.getAdmissionDiseaseName(), "admission_disease_name", sqlStr, 1, type);
			}
			euqalSpell(flyCheckMedicalVo.getpLevel(), "p_level", sqlStr, 1, type);
			euqalSpell(flyCheckMedicalVo.getAdmissionDate(), "admission_date", sqlStr, 2, type);
			euqalSpell(flyCheckMedicalVo.getDischargeDate(), "discharge_date", sqlStr, 2, type);
		}
		Integer listCount = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "hisid", null);
		Integer page = (listCount + pageSize - 1) / pageSize;

		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("限医院等级用药");
		// 在sheet里创建第一行
		SXSSFRow row1 = (SXSSFRow) sheet.createRow(0);
		// 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
		row1.createCell(0).setCellValue("医疗机构编码");
		row1.createCell(1).setCellValue("医疗机构名称");
		row1.createCell(2).setCellValue("医疗机构级别");
		row1.createCell(3).setCellValue("住院号");
		row1.createCell(4).setCellValue("社保卡号");
		row1.createCell(5).setCellValue("就诊科室");
		row1.createCell(6).setCellValue("患者姓名");
		row1.createCell(7).setCellValue("诊断编码");
		row1.createCell(8).setCellValue("诊断名称");
		row1.createCell(9).setCellValue("入院日期");
		row1.createCell(10).setCellValue("出院日期");
		row1.createCell(11).setCellValue("结算日期");
		row1.createCell(12).setCellValue("住院天数");
		row1.createCell(13).setCellValue("医疗总费用");
		row1.createCell(14).setCellValue("险种类型");
		row1.createCell(15).setCellValue("西药费");
		row1.createCell(16).setCellValue("中药饮片费");
		row1.createCell(17).setCellValue("中成药费");

		List<FlyCheckMedicalVo> list = null;
		for (int i = 1; i <= page; i++) {
			list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyCheckMedicalVo.class, null, i,
					pageSize, null);
			int startRow = (i - 1) * pageSize + 1;
			// 插入数据
			for (int j = 0; j < list.size(); j++) {
				SXSSFRow rowNum = (SXSSFRow) sheet.createRow(startRow);
				rowNum.createCell(0).setCellValue(list.get(j).getHospitalId());
				rowNum.createCell(1).setCellValue(list.get(j).getHospitalName());
				rowNum.createCell(2).setCellValue(list.get(j).getpLevel());
				rowNum.createCell(3).setCellValue(list.get(j).getZyh());
				rowNum.createCell(4).setCellValue(list.get(j).getSocialCardId());
				rowNum.createCell(5).setCellValue(list.get(j).getDischargeDeptName());
				rowNum.createCell(6).setCellValue(list.get(j).getPatientName());
				rowNum.createCell(7).setCellValue(list.get(j).getAdmissionDiseaseId());
				rowNum.createCell(8).setCellValue(list.get(j).getAdmissionDiseaseName());
				rowNum.createCell(9).setCellValue(list.get(j).getAdmissionDate());
				rowNum.createCell(10).setCellValue(list.get(j).getDischargeDate());
				rowNum.createCell(11).setCellValue(list.get(j).getBillDate());
				rowNum.createCell(12).setCellValue(list.get(j).getZyts());
				rowNum.createCell(13).setCellValue(list.get(j).getTotalAmount());
				rowNum.createCell(14).setCellValue(list.get(j).getBenefitType());
				rowNum.createCell(15).setCellValue(list.get(j).getWesternMedicineFee());
				rowNum.createCell(16).setCellValue(list.get(j).getChineseMedicineYinpian());
				rowNum.createCell(16).setCellValue(list.get(j).getChineseMedicineForm());
				startRow++;
			}
			list = null;

		}

		return wb;
	}

	private void euqalSpell(String value, String field, StringBuilder sqlStr, int category, String type) {
		if (category == 0) {
			if (value != null && !"".equals(value)) {
				sqlStr.append(" and " + field + " = '" + value + "'");
			}
		} else if (category == 1) {
			if (value != null && !"".equals(value)) {
				sqlStr.append(" and " + field + " like '%" + value + "%'");
			}
		} else if (category == 2) {
			if ("0".equals(type)) {
				if (value != null && !"".equals(value)) {
					sqlStr.append(" and TO_NUMBER(TO_CHAR(" + field + ", 'YYYYMMDD')) BETWEEN "
							+ value.replace("forbid", "and"));
				}
			} else {
				if (value != null && !"".equals(value)) {
					sqlStr.append(" and 1=0 ");
				}
			}
		} else if (category == 3) {
			if ("0".equals(type)) {
				if (value != null && !"".equals(value)) {
					sqlStr.append(" and TO_NUMBER(TO_CHAR(" + field + ", 'YYYYMMDD'))='" + value + "'");
				}
			} else {
				if (value != null && !"".equals(value)) {
					sqlStr.append(" and 1=0 ");
				}
			}
		} else if (category == 4) {
			if (value != null && !"".equals(value)) {
				sqlStr.append(
						" and TO_NUMBER(TO_CHAR(" + field + ", 'YYYYMMDD')) BETWEEN " + value.replace("forbid", "and"));
			}
		}
	}

	/**
	 * 体检式入院明细
	 */
	public void physicalExaminationMx(HospitalLevelRmacyDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		FlyCheckMedicalDetailVo flyCheckMedicalDetailVo = dto.getFlyCheckMedicalDetailVo();
		String phyID = dto.getPhyID();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(
				"select hisid,item_id,item_name,item_id_hosp,item_name_hosp,p_category,unit_price,num,cost,p_type");
		sqlStr.append("  from t_flycheck_medical_detail where 1=1");
		if (!StringUtils.isNullOrEmpty(phyID)) {
			sqlStr.append(" and hisid='" + phyID + "'");
		}
		if (flyCheckMedicalDetailVo != null) {
			if (!StringUtils.isNullOrEmpty(flyCheckMedicalDetailVo.getItemId())) {
				sqlStr.append(" and item_id='" + flyCheckMedicalDetailVo.getItemId() + "'");
			}
			if (!StringUtils.isNullOrEmpty(flyCheckMedicalDetailVo.getItemName())) {
				sqlStr.append(" and item_name='" + flyCheckMedicalDetailVo.getItemName() + "'");
			}
			if (!StringUtils.isNullOrEmpty(flyCheckMedicalDetailVo.getpCategory())) {
				sqlStr.append(" and p_category='" + flyCheckMedicalDetailVo.getpCategory() + "'");
			}
		}
		System.out.println(sqlStr);
		Integer listCount = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "hisid", hqlParamMap);
		@SuppressWarnings("unchecked")
		List<FlyCheckMedicalDetailVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyCheckMedicalDetailVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), null);
		dto.getPageModel().setTotals(listCount);
		dto.getPageModel().setPageData(list);
	}
}

package com.dhcc.piccbid.dao.abnormalHospitalStay;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dao.admin.AdminDictJdbcDao;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayVo;
import com.dhcc.piccbid.entity.user.User;

@Component
public class AbnormalHospitalStayJdbcDao extends HibernatePersistentObjectDAO<AbnormalHospitalStay> {
	private static Log logger = LogFactory.getLog(AdminDictJdbcDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	public void abnormalHospitalStay(AbnormalHospitalStayDto dto) {
		String zyts = dto.getZyts();
		String indate = dto.getIndate();
		String hospitalName = dto.getHospitalName();
		String admissionDiseaseName = dto.getAdmissionDiseaseName();
		String outdate = dto.getOutdate();
		String paydate = dto.getPaydate();
		String code = dto.getCode();

		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		AbnormalHospitalStay abnormalHospitalStay = (AbnormalHospitalStay) WebContextHolder.getContext().getRequest()
				.getSession().getAttribute("abnormalHospitalStay");
		sqlStr.append("select HISID,HOSPITAL_ID,HOSPITAL_NAME,ZYH,ADMISSION_DEPT_NAME,CHINESE_MEDICINE_YINPIAN, ");
		sqlStr.append(
				"PATIENT_NAME,ADMISSION_DATE,DISCHARGE_DATE,ZYTS,TOTAL_AMOUNT,BENEFIT_TYPE,WESTERN_MEDICINE_FEE,");
		sqlStr.append(
				"CHINESE_MEDICINE_FORM,ACCOMMODATION_FEE,DIAGNOSIS_FEE,INSPECTION_FEE,TEST_FEE,TREATMENT_FEE,NURSING_FEE,MATERIAL_FEE,CONSULTATION_FEE,REGISTRATION_FEE,OTHER_FEE ");
		sqlStr.append("from T_FLYCHECK_MEDICAL where 1=1");
		if (zyts != null && !zyts.equals("")) {
			if (code.equals("one")) {
				sqlStr.append(" and zyts>" + zyts);
			}
			if (code.equals("three")) {
				sqlStr.append(" and zyts<" + zyts);
			}
			if (code.equals("two")) {
				sqlStr.append(" and zyts=" + zyts);
			}
		}

		if (hospitalName != null && !hospitalName.equals("")) {
			sqlStr.append(" and hospital_Name like '%" + hospitalName + "%'");
		}
		if (admissionDiseaseName != null && !admissionDiseaseName.equals("")) {
			sqlStr.append(" and admission_Disease_Name like '%" + admissionDiseaseName + "%'");
		}

		if (indate != null && !indate.equals("")) {
			String result2 = indate.substring(18, 28);
			String indate1 = indate.substring(0, 10);
			sqlStr.append("and ADMISSION_DATE>to_date('" + indate1 + "','yyyy-mm-dd') and ADMISSION_DATE<to_date('"
					+ result2 + "','yyyy-mm-dd')");
		}

		if (outdate != null && !outdate.equals("")) {
			String result1 = outdate.substring(18, 28);
			String outdate1 = outdate.substring(0, 10);
			sqlStr.append("and DISCHARGE_DATE<to_date('" + result1 + "','yyyy-mm-dd') and DISCHARGE_DATE>to_date('"
					+ outdate1 + "','yyyy-mm-dd')");
		}
		if (paydate != null && !paydate.equals("")) {
			String result = paydate.substring(18, 28);
			String paydate1 = paydate.substring(0, 10);
			sqlStr.append("and BILL_DATE > to_date('" + paydate1 + "','yyyy-mm-dd') and BILL_DATE < to_date('" + result
					+ "','yyyy-mm-dd')");
		}

		@SuppressWarnings("unchecked")
		List<AbnormalHospitalStay> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				AbnormalHospitalStay.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	public void countabnormalHospitalStay(AbnormalHospitalStayDto dto) {
		String zyts = dto.getZyts();
		String indate = dto.getIndate();
		String hospitalName = dto.getHospitalName();
		String admissionDiseaseName = dto.getAdmissionDiseaseName();
		String outdate = dto.getOutdate();
		String paydate = dto.getPaydate();
		String code = dto.getCode();
		PageModel pageModel = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		AbnormalHospitalStay abnormalHospitalStay = (AbnormalHospitalStay) WebContextHolder.getContext().getRequest()
				.getSession().getAttribute("abnormalHospitalStay");
		sqlStr.append(
				"select count(1) rowsum,sum(total_amount) sum_amount" + "  from  T_FLYCHECK_MEDICAL  where 1=1   ");

		if (zyts != null && !zyts.equals("")) {
			if (code.equals("one")) {
				sqlStr.append(" and zyts>" + zyts);
			}
			if (code.equals("three")) {
				sqlStr.append(" and zyts<" + zyts);
			}
			if (code.equals("two")) {
				sqlStr.append(" and zyts=" + zyts);
			}

		}
		if (hospitalName != null && !hospitalName.equals("")) {
			sqlStr.append(" and hospital_Name like '%" + hospitalName + "%'");
		}
		if (admissionDiseaseName != null && !admissionDiseaseName.equals("")) {
			sqlStr.append(" and admission_Disease_Name like '%" + admissionDiseaseName + "%'");
		}

		if (indate != null && !indate.equals("")) {
			String result2 = indate.substring(18, 28);
			String indate1 = indate.substring(0, 10);
			sqlStr.append("and ADMISSION_DATE>to_date('" + indate1 + "','yyyy-mm-dd') and ADMISSION_DATE<to_date('"
					+ result2 + "','yyyy-mm-dd')");
		}

		if (outdate != null && !outdate.equals("")) {
			String result1 = outdate.substring(18, 28);
			String outdate1 = outdate.substring(0, 10);
			sqlStr.append("and DISCHARGE_DATE<to_date('" + result1 + "','yyyy-mm-dd') and DISCHARGE_DATE>to_date('"
					+ outdate1 + "','yyyy-mm-dd')");
		}
		if (paydate != null && !paydate.equals("")) {
			String result = paydate.substring(18, 28);
			String paydate1 = paydate.substring(0, 10);
			sqlStr.append("and BILL_DATE > to_date('" + paydate1 + "','yyyy-mm-dd') and BILL_DATE < to_date('" + result
					+ "','yyyy-mm-dd')");
		}

		@SuppressWarnings("unchecked")
		List<AbnormalHospitalStay> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				AbnormalHospitalStay.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	// 导出到excel
	public SXSSFWorkbook exportExcelToSelf(AbnormalHospitalStayDto dto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String zyts = dto.getAbnormalHospitalStayexcle().getZyts();
		String indate = dto.getAbnormalHospitalStayexcle().getIndate();
		String hospitalName = dto.getAbnormalHospitalStayexcle().getHospitalName();
		String admissionDiseaseName = dto.getAbnormalHospitalStayexcle().getAdmissionDiseaseName();
		String outdate = dto.getAbnormalHospitalStayexcle().getOutdate();
		String paydate = dto.getAbnormalHospitalStayexcle().getPaydate();
		String code = dto.getAbnormalHospitalStayexcle().getCode();

		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(
				"select HOSPITAL_ID,HOSPITAL_NAME,ZYH,ADMISSION_DEPT_NAME,CHINESE_MEDICINE_YINPIAN,PATIENT_NAME,ADMISSION_DATE,"
						+ "DISCHARGE_DATE,ZYTS,TOTAL_AMOUNT,BENEFIT_TYPE,WESTERN_MEDICINE_FEE,CHINESE_MEDICINE_FORM,"
						+ "ACCOMMODATION_FEE,DIAGNOSIS_FEE,INSPECTION_FEE,TEST_FEE,TREATMENT_FEE,NURSING_FEE,MATERIAL_FEE,CONSULTATION_FEE,REGISTRATION_FEE,OTHER_FEE "
						+ "from T_FLYCHECK_MEDICAL where 1=1");

		if (zyts != null && !zyts.equals("")) {
			if (code.equals("one")) {
				sqlStr.append(" and zyts>" + zyts);
			}
			if (code.equals("three")) {
				sqlStr.append(" and zyts<" + zyts);
			}
			if (code.equals("two")) {
				sqlStr.append(" and zyts=" + zyts);
			}
		}

		if (hospitalName != null && !hospitalName.equals("")) {
			sqlStr.append(" and hospital_Name like '%" + hospitalName + "%'");
		}
		if (admissionDiseaseName != null && !admissionDiseaseName.equals("")) {
			sqlStr.append(" and admission_Disease_Name like '%" + admissionDiseaseName + "%'");
		}

		if (indate != null && !indate.equals("")) {
			String result2 = indate.substring(18, 28);
			String indate1 = indate.substring(0, 10);
			sqlStr.append("and ADMISSION_DATE>to_date('" + indate1 + "','yyyy-mm-dd') and ADMISSION_DATE<to_date('"
					+ result2 + "','yyyy-mm-dd')");
		}

		if (outdate != null && !outdate.equals("")) {
			String result1 = outdate.substring(18, 28);
			String outdate1 = outdate.substring(0, 10);
			sqlStr.append("and DISCHARGE_DATE<to_date('" + result1 + "','yyyy-mm-dd') and DISCHARGE_DATE>to_date('"
					+ outdate1 + "','yyyy-mm-dd')");
		}
		if (paydate != null && !paydate.equals("")) {
			String result = paydate.substring(18, 28);
			String paydate1 = paydate.substring(0, 10);
			sqlStr.append("and BILL_DATE > to_date('" + paydate1 + "','yyyy-mm-dd') and BILL_DATE < to_date('" + result
					+ "','yyyy-mm-dd')");
		}
		@SuppressWarnings("unchecked")
		List<AbnormalHospitalStay> HospitalViolationDetailVoList = jdbcTemplateWrapper
				.queryAllMatchListWithParaMap(sqlStr.toString(), AbnormalHospitalStay.class, null);
		SXSSFWorkbook wb = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("住院天数异常");
		SXSSFRow row1 = (SXSSFRow) sheet.createRow(0);
		row1.createCell(0).setCellValue("医疗机构编码");
		row1.createCell(1).setCellValue("医疗机构名称");
		row1.createCell(2).setCellValue("住院号");
		row1.createCell(3).setCellValue("就诊科室");
		row1.createCell(4).setCellValue("患者姓名");
		row1.createCell(5).setCellValue("入院日期");
		row1.createCell(6).setCellValue("出院日期");
		row1.createCell(7).setCellValue("住院天数");
		row1.createCell(8).setCellValue("医疗总费用");
		row1.createCell(9).setCellValue("险种类型");
		row1.createCell(10).setCellValue("西药费");
		row1.createCell(11).setCellValue("中药饮片费");
		row1.createCell(12).setCellValue("中成药费");
		row1.createCell(13).setCellValue("床位费");
		row1.createCell(14).setCellValue("诊查费");
		row1.createCell(15).setCellValue("检查费");
		row1.createCell(16).setCellValue("化验费");
		row1.createCell(17).setCellValue("治疗费");
		row1.createCell(18).setCellValue("护理费");
		row1.createCell(19).setCellValue("卫生材料费");
		row1.createCell(20).setCellValue("一般诊疗费");
		row1.createCell(21).setCellValue("挂号费");
		row1.createCell(22).setCellValue("其他费");
		for (int i = 0; i < HospitalViolationDetailVoList.size(); i++) {
			SXSSFRow rowNum = (SXSSFRow) sheet.createRow(i + 1);
			rowNum.createCell(0).setCellValue(HospitalViolationDetailVoList.get(i).getHospitalId());
			rowNum.createCell(1).setCellValue(HospitalViolationDetailVoList.get(i).getHospitalName());
			rowNum.createCell(2).setCellValue(HospitalViolationDetailVoList.get(i).getZyh());
			rowNum.createCell(3).setCellValue(HospitalViolationDetailVoList.get(i).getAdmissionDeptName());
			rowNum.createCell(4).setCellValue(HospitalViolationDetailVoList.get(i).getPatientName());
			rowNum.createCell(5).setCellValue(sdf.format(HospitalViolationDetailVoList.get(i).getAdmissionDate()));
			rowNum.createCell(6).setCellValue(sdf.format(HospitalViolationDetailVoList.get(i).getDischargeDate()));
			rowNum.createCell(7).setCellValue(HospitalViolationDetailVoList.get(i).getZyts());
			rowNum.createCell(8).setCellValue(HospitalViolationDetailVoList.get(i).getTotalAmount());
			rowNum.createCell(9).setCellValue(HospitalViolationDetailVoList.get(i).getBenefitType());
			rowNum.createCell(10).setCellValue(HospitalViolationDetailVoList.get(i).getWesternMedicineFee());
			rowNum.createCell(11).setCellValue(HospitalViolationDetailVoList.get(i).getChineseMedicineYinpian());
			rowNum.createCell(12).setCellValue(HospitalViolationDetailVoList.get(i).getChineseMedicineForm());
			rowNum.createCell(13).setCellValue(HospitalViolationDetailVoList.get(i).getAccommodationFee());
			rowNum.createCell(14).setCellValue(HospitalViolationDetailVoList.get(i).getDiagnosisFee());
			rowNum.createCell(15).setCellValue(HospitalViolationDetailVoList.get(i).getInspectionFee());
			rowNum.createCell(16).setCellValue(HospitalViolationDetailVoList.get(i).getTestFee());
			rowNum.createCell(17).setCellValue(HospitalViolationDetailVoList.get(i).getTreatmentFee());
			rowNum.createCell(18).setCellValue(HospitalViolationDetailVoList.get(i).getNursingFee());
			rowNum.createCell(19).setCellValue(HospitalViolationDetailVoList.get(i).getMaterialFee());
			rowNum.createCell(20).setCellValue(HospitalViolationDetailVoList.get(i).getConsultationFee());
			rowNum.createCell(21).setCellValue(HospitalViolationDetailVoList.get(i).getRegistrationFee());
			rowNum.createCell(22).setCellValue(HospitalViolationDetailVoList.get(i).getOtherFee());
		}
		return wb;
	}

	public void detaileTable(AbnormalHospitalStayDto dto) {
		String hisid = dto.getAbnormalHospitalStayVo().getHisid();
		String itemName = dto.getAbnormalHospitalStayVo().getItemName();
		String itemId = dto.getAbnormalHospitalStayVo().getItemId();
		String pCategory = dto.getAbnormalHospitalStayVo().getpCategory();
		PageModel pageModel = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		AbnormalHospitalStayVo abnormalHospitalStayVo = (AbnormalHospitalStayVo) WebContextHolder.getContext()
				.getRequest().getSession().getAttribute("abnormalHospitalStayVo");
		sqlStr.append("select t.item_id,t.item_name,t.item_id_hosp,\r\n"
				+ "t.item_name_hosp,t.p_category,t.unit_price,\r\n" + "t.num,t.cost,t.p_type\r\n"
				+ "from t_Flycheck_Medical_Detail t where 1=1 and hisid='" + hisid + "'");
		if (itemName != null && !itemName.equals("")) {
			sqlStr.append(" and item_name='" + itemName + "'");
		}
		if (itemId != null && !itemId.equals("")) {
			sqlStr.append(" and item_id='" + itemId + "'");
		}
		if (pCategory != null && !pCategory.equals("")) {
			sqlStr.append(" and p_Category='" + pCategory + "'");
		}
		@SuppressWarnings("unchecked")
		List<AbnormalHospitalStayVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				AbnormalHospitalStayVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

}

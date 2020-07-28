package com.dhcc.piccbid.dao.physicalExaminationAdmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dao.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsJdbcDao;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalDetailVo;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalVo;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmission;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmissionVo;
import com.dhcc.piccbid.entity.user.User;

@Component
public class PhysicalExaminationAdmissionJdbcDao {
	private static Log logger = LogFactory.getLog(DiseaseAndDiagnosticStatisticsJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	/**
	 * 体检式入院主单
	 * 
	 * @param dto
	 */
	public void physicalExamination(PhysicalExaminationAdmissionDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String hospitalId = dto.getHospitalId();// 医院编码
		String hospitalName = dto.getHospitalName();// 医院名称
		String billDate = dto.getBillDate();// 结算日期
		String code = dto.getCode();
		String code1 = dto.getCode1();
		if (!StringUtils.isNullOrEmpty(code) || !StringUtils.isNullOrEmpty(code1)) {
			if (code.equals("& gt;")) {
				code = ">";
			}
			if (code1.equals("& gt;")) {
				code1 = ">";
			}
			if (code.equals("& lt;")) {
				code = "<";
			}
			if (code1.equals("& lt;")) {
				code1 = "<";
			}
		}

		String SumDrugs = dto.getSumdrugs();// 药品费
		String jianchafei = dto.getJianchafei();// 检查费占比
		String admissionDate = dto.getAdmissionDate();// 入院日期
		String dischargeDate = dto.getDischargeDate();// 出院日期
		String admissionDiseaseId = dto.getAdmissionDiseaseId();// 诊断编码
		String admissionDiseaseName = dto.getAdmissionDiseaseName();// 诊断名称
		String baifenhao = "%";
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select m.hisid,m.HOSPITAL_ID,m.HOSPITAL_NAME,m.ZYH,m.ADMISSION_DEPT_NAME,m.PATIENT_NAME,");
		sqlStr.append("m.ADMISSION_DATE,m.DISCHARGE_DATE,m.ZYTS,m.TOTAL_AMOUNT,m.BENEFIT_TYPE,");
		sqlStr.append("  (nvl(m.WESTERN_MEDICINE_FEE, 0) + nvl(m.CHINESE_MEDICINE_FORM, 0) +\r\n" + 
				"       nvl(m.CHINESE_MEDICINE_YINPIAN, 0)) drugFee,\r\n" + 
				"       substr((m.INSPECTION_FEE / m.TOTAL_AMOUNT) * 100, 0, 5) jcfzb,");
		sqlStr.append("m.ACCOMMODATION_FEE,m.DIAGNOSIS_FEE,m.INSPECTION_FEE,m.TEST_FEE,m.TREATMENT_FEE,");
		sqlStr.append("m.NURSING_FEE,m.MATERIAL_FEE,m.WESTERN_MEDICINE_FEE,m.CHINESE_MEDICINE_YINPIAN,");
		sqlStr.append("m.CHINESE_MEDICINE_FORM,m.CONSULTATION_FEE,m.REGISTRATION_FEE,m.OTHER_FEE");
		sqlStr.append(" from T_FLYCHECK_MEDICAL m where 1=1");
		if (!StringUtils.isNullOrEmpty(hospitalId)) {
			sqlStr.append(" and m.HOSPITAL_ID in(" + hospitalId + ") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName)) {
			sqlStr.append(" and m.HOSPITAL_NAME='" + hospitalName + "'");
		}*/
		if (!StringUtils.isNullOrEmpty(billDate)) {
			String[] time = billDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(code) && !StringUtils.isNullOrEmpty(SumDrugs)) {
			sqlStr.append(
					" and nvl(WESTERN_MEDICINE_FEE,0)+nvl(CHINESE_MEDICINE_YINPIAN,0)+nvl(CHINESE_MEDICINE_FORM,0) "
							+ code + " " + SumDrugs + "  ");
		}
		if (!StringUtils.isNullOrEmpty(code1) && !StringUtils.isNullOrEmpty(jianchafei)) {
			sqlStr.append(
					" and ROUND((nvl(m.INSPECTION_FEE,0)/(nvl(m.ACCOMMODATION_FEE,0)+nvl(m.DIAGNOSIS_FEE,0)+nvl(m.INSPECTION_FEE,0)+nvl(m.TEST_FEE,0)+nvl(m.TREATMENT_FEE,0)+\r\n"
							+ "nvl(m.NURSING_FEE,0)+nvl(m.MATERIAL_FEE,0)+nvl(m.WESTERN_MEDICINE_FEE,0)+nvl(m.CHINESE_MEDICINE_YINPIAN,0)+nvl(m.CHINESE_MEDICINE_FORM,0)+\r\n"
							+ "nvl(m.CONSULTATION_FEE,0)+nvl(m.REGISTRATION_FEE,0)+nvl(m.OTHER_FEE,0)))*100,2)||'%' "
							+ code1 + " '" + jianchafei + baifenhao + "' ");
		}
		if (!StringUtils.isNullOrEmpty(admissionDate)) {
			String[] time = admissionDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(dischargeDate)) {
			String[] time = dischargeDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseId)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_ID='" + admissionDiseaseId + "'");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_NAME='" + admissionDiseaseName + "'");
		}
		System.out.println("检查费占比："+sqlStr);
		@SuppressWarnings("unchecked")
		List<PhysicalExaminationAdmissionVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				PhysicalExaminationAdmissionVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
		dto.getPageModel().setTotals(totals);
		dto.getPageModel().setPageData(list);
	}

	/**
	 * 总数
	 * 
	 * @param dto
	 */
	public void sumTotalCount(PhysicalExaminationAdmissionDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String hospitalId = dto.getHospitalId();// 医院编码
		String hospitalName = dto.getHospitalName();// 医院名称
		String billDate = dto.getBillDate();// 结算日期
		String code = dto.getCode();
		String code1 = dto.getCode1();
		if (!StringUtils.isNullOrEmpty(code) || !StringUtils.isNullOrEmpty(code1)) {
			if (!StringUtils.isNullOrEmpty(code) || !StringUtils.isNullOrEmpty(code1)) {
				if (code.equals("& gt;")) {
					code = ">";
				}
				if (code1.equals("& gt;")) {
					code1 = ">";
				}
				if (code.equals("& lt;")) {
					code = "<";
				}
				if (code1.equals("& lt;")) {
					code1 = "<";
				}
			}
		}
		String SumDrugs = dto.getSumdrugs();// 药品费
		String jianchafei = dto.getJianchafei();// 检查费占比
		String admissionDate = dto.getAdmissionDate();// 入院日期
		String dischargeDate = dto.getDischargeDate();// 出院日期
		String admissionDiseaseId = dto.getAdmissionDiseaseId();// 诊断编码
		String admissionDiseaseName = dto.getAdmissionDiseaseName();// 诊断名称
		String baifenhao = "%";
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select sum(m.TOTAL_AMOUNT) num from T_FLYCHECK_MEDICAL m where 1=1");
		if (!StringUtils.isNullOrEmpty(hospitalId)) {
			sqlStr.append(" and m.HOSPITAL_ID in(" + hospitalId + ") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName)) {
			sqlStr.append(" and m.HOSPITAL_NAME='" + hospitalName + "'");
		}*/
		if (!StringUtils.isNullOrEmpty(billDate)) {
			String[] time = billDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(code) && !StringUtils.isNullOrEmpty(SumDrugs)) {
			sqlStr.append(
					" and nvl(WESTERN_MEDICINE_FEE,0)+nvl(CHINESE_MEDICINE_YINPIAN,0)+nvl(CHINESE_MEDICINE_FORM,0) "
							+ code + " " + SumDrugs + "  ");
		}
		if (!StringUtils.isNullOrEmpty(code1) && !StringUtils.isNullOrEmpty(jianchafei)) {
			sqlStr.append(
					" and ROUND((nvl(m.INSPECTION_FEE,0)/(nvl(m.ACCOMMODATION_FEE,0)+nvl(m.DIAGNOSIS_FEE,0)+nvl(m.INSPECTION_FEE,0)+nvl(m.TEST_FEE,0)+nvl(m.TREATMENT_FEE,0)+\r\n"
							+ "nvl(m.NURSING_FEE,0)+nvl(m.MATERIAL_FEE,0)+nvl(m.WESTERN_MEDICINE_FEE,0)+nvl(m.CHINESE_MEDICINE_YINPIAN,0)+nvl(m.CHINESE_MEDICINE_FORM,0)+\r\n"
							+ "nvl(m.CONSULTATION_FEE,0)+nvl(m.REGISTRATION_FEE,0)+nvl(m.OTHER_FEE,0)))*100,2)||'%' "
							+ code1 + " '" + jianchafei + baifenhao + "' ");
		}
		if (!StringUtils.isNullOrEmpty(admissionDate)) {
			String[] time = admissionDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(dischargeDate)) {
			String[] time = dischargeDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseId)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_ID='" + admissionDiseaseId + "'");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_NAME='" + admissionDiseaseName + "'");
		}
		@SuppressWarnings("unchecked")
		List<PhysicalExaminationAdmission> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				PhysicalExaminationAdmission.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setPageData(list);
	}
	
	/**
	 * 体检式住院明细统计
	 * @param dto
	 */
	public void countphysicalExaminationMx(PhysicalExaminationAdmissionDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		String hospitalId = dto.getHospitalId();// 医院编码
		String hospitalName = dto.getHospitalName();// 医院名称
		String billDate = dto.getBillDate();// 结算日期
		String code = dto.getCode();
		String code1 = dto.getCode1();
		if (!StringUtils.isNullOrEmpty(code) || !StringUtils.isNullOrEmpty(code1)) {
			if (!StringUtils.isNullOrEmpty(code) || !StringUtils.isNullOrEmpty(code1)) {
				if (code.equals("& gt;")) {
					code = ">";
				}
				if (code1.equals("& gt;")) {
					code1 = ">";
				}
				if (code.equals("& lt;")) {
					code = "<";
				}
				if (code1.equals("& lt;")) {
					code1 = "<";
				}
			}
		}
		String SumDrugs = dto.getSumdrugs();// 药品费
		String jianchafei = dto.getJianchafei();// 检查费占比
		String admissionDate = dto.getAdmissionDate();// 入院日期
		String dischargeDate = dto.getDischargeDate();// 出院日期
		String admissionDiseaseId = dto.getAdmissionDiseaseId();// 诊断编码
		String admissionDiseaseName = dto.getAdmissionDiseaseName();// 诊断名称
		String baifenhao = "%";
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select count(a.hisid)as total_mx,nvl(sum(a.cost),0) as money_mx from t_flycheck_medical_detail a,t_flycheck_medical m where 1=1 and a.hisid=m.hisid");
		if (!StringUtils.isNullOrEmpty(hospitalId)) {
			sqlStr.append(" and m.HOSPITAL_ID in(" + hospitalId + ")");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName)) {
			sqlStr.append(" and m.HOSPITAL_NAME='" + hospitalName + "'");
		}*/
		if (!StringUtils.isNullOrEmpty(billDate)) {
			String[] time = billDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(code) && !StringUtils.isNullOrEmpty(SumDrugs)) {
			sqlStr.append(
					" and nvl(WESTERN_MEDICINE_FEE,0)+nvl(CHINESE_MEDICINE_YINPIAN,0)+nvl(CHINESE_MEDICINE_FORM,0) "
							+ code + " " + SumDrugs + "  ");
		}
		if (!StringUtils.isNullOrEmpty(code1) && !StringUtils.isNullOrEmpty(jianchafei)) {
			sqlStr.append(
					" and ROUND((nvl(m.INSPECTION_FEE,0)/(nvl(m.ACCOMMODATION_FEE,0)+nvl(m.DIAGNOSIS_FEE,0)+nvl(m.INSPECTION_FEE,0)+nvl(m.TEST_FEE,0)+nvl(m.TREATMENT_FEE,0)+\r\n"
							+ "nvl(m.NURSING_FEE,0)+nvl(m.MATERIAL_FEE,0)+nvl(m.WESTERN_MEDICINE_FEE,0)+nvl(m.CHINESE_MEDICINE_YINPIAN,0)+nvl(m.CHINESE_MEDICINE_FORM,0)+\r\n"
							+ "nvl(m.CONSULTATION_FEE,0)+nvl(m.REGISTRATION_FEE,0)+nvl(m.OTHER_FEE,0)))*100,2)||'%' "
							+ code1 + " '" + jianchafei + baifenhao + "' ");
		}
		if (!StringUtils.isNullOrEmpty(admissionDate)) {
			String[] time = admissionDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(dischargeDate)) {
			String[] time = dischargeDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseId)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_ID='" + admissionDiseaseId + "'");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_NAME='" + admissionDiseaseName + "'");
		}
		System.out.println("体检入院"+sqlStr);
		@SuppressWarnings("unchecked")
		List<FlyCheckMedicalVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				FlyCheckMedicalVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
				dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setPageData(list);
	}

	// 导出到excel
	public XSSFWorkbook exportExcelToSelf(PhysicalExaminationAdmissionDto dto) {
		String hospitalId = dto.getHospitalId();// 医院编码
		String hospitalName = dto.getHospitalName();// 医院名称
		String billDate = dto.getBillDate();// 结算日期
		String code = dto.getCode();
		String code1 = dto.getCode1();
		if (!StringUtils.isNullOrEmpty(code) || !StringUtils.isNullOrEmpty(code1)) {
			if (code.equals("& gt;")) {
				code = ">";
			}
			if (code1.equals("& gt;")) {
				code1 = ">";
			}
			if (code.equals("& lt;")) {
				code = "<";
			}
			if (code1.equals("& lt;")) {
				code1 = "<";
			}
		}

		String SumDrugs = dto.getSumdrugs();// 药品费
		String jianchafei = dto.getJianchafei();// 检查费占比
		String admissionDate = dto.getAdmissionDate();// 入院日期
		String dischargeDate = dto.getDischargeDate();// 出院日期
		String admissionDiseaseId = dto.getAdmissionDiseaseId();// 诊断编码
		String admissionDiseaseName = dto.getAdmissionDiseaseName();// 诊断名称
		String baifenhao = "%";
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select m.hisid,m.HOSPITAL_ID,m.HOSPITAL_NAME,m.ZYH,m.ADMISSION_DEPT_NAME,m.PATIENT_NAME,");
		sqlStr.append("m.ADMISSION_DATE,m.DISCHARGE_DATE,m.ZYTS,m.TOTAL_AMOUNT,m.BENEFIT_TYPE,");
		sqlStr.append("m.ACCOMMODATION_FEE,m.DIAGNOSIS_FEE,m.INSPECTION_FEE,m.TEST_FEE,m.TREATMENT_FEE,");
		sqlStr.append("m.NURSING_FEE,m.MATERIAL_FEE,m.WESTERN_MEDICINE_FEE,m.CHINESE_MEDICINE_YINPIAN,");
		sqlStr.append("m.CHINESE_MEDICINE_FORM,m.CONSULTATION_FEE,m.REGISTRATION_FEE,m.OTHER_FEE");
		sqlStr.append(" from T_FLYCHECK_MEDICAL m where 1=1");
		if (!StringUtils.isNullOrEmpty(hospitalId)) {
			sqlStr.append(" and m.HOSPITAL_ID in(" + hospitalId + ") ");
		}
		/*if (!StringUtils.isNullOrEmpty(hospitalName)) {
			sqlStr.append(" and m.HOSPITAL_NAME='" + hospitalName + "'");
		}*/
		if (!StringUtils.isNullOrEmpty(billDate)) {
			String[] time = billDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(code) && !StringUtils.isNullOrEmpty(SumDrugs)) {
			sqlStr.append(
					" and nvl(WESTERN_MEDICINE_FEE,0)+nvl(CHINESE_MEDICINE_YINPIAN,0)+nvl(CHINESE_MEDICINE_FORM,0) "
							+ code + " " + SumDrugs + "  ");
		}
		if (!StringUtils.isNullOrEmpty(code1) && !StringUtils.isNullOrEmpty(jianchafei)) {
			sqlStr.append(
					" and ROUND((nvl(m.INSPECTION_FEE,0)/(nvl(m.ACCOMMODATION_FEE,0)+nvl(m.DIAGNOSIS_FEE,0)+nvl(m.INSPECTION_FEE,0)+nvl(m.TEST_FEE,0)+nvl(m.TREATMENT_FEE,0)+\r\n"
							+ "nvl(m.NURSING_FEE,0)+nvl(m.MATERIAL_FEE,0)+nvl(m.WESTERN_MEDICINE_FEE,0)+nvl(m.CHINESE_MEDICINE_YINPIAN,0)+nvl(m.CHINESE_MEDICINE_FORM,0)+\r\n"
							+ "nvl(m.CONSULTATION_FEE,0)+nvl(m.REGISTRATION_FEE,0)+nvl(m.OTHER_FEE,0)))*100,2)||'%' "
							+ code1 + " '" + jianchafei + baifenhao + "' ");
		}
		if (!StringUtils.isNullOrEmpty(admissionDate)) {
			String[] time = admissionDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(dischargeDate)) {
			String[] time = dischargeDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sqlStr.append(" and m.DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('"
					+ endTime + "', 'yyyyMMdd')");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseId)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_ID='" + admissionDiseaseId + "'");
		}
		if (!StringUtils.isNullOrEmpty(admissionDiseaseName)) {
			sqlStr.append(" and m.ADMISSION_DISEASE_NAME='" + admissionDiseaseName + "'");
		}
		@SuppressWarnings("unchecked")
		List<PhysicalExaminationAdmission> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				PhysicalExaminationAdmission.class, null);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("体检式入院");
		XSSFRow row1 = sheet.createRow(0);
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
		for (int i = 0; i < list.size(); i++) {
			XSSFRow rowNum = sheet.createRow(i + 1);
			rowNum.createCell(0).setCellValue(list.get(i).getHospitalId());
			rowNum.createCell(1).setCellValue(list.get(i).getHospitalName());
			rowNum.createCell(2).setCellValue(list.get(i).getZyh());
			rowNum.createCell(3).setCellValue(list.get(i).getAdmissionDeptName());
			rowNum.createCell(4).setCellValue(list.get(i).getPatientName());
			rowNum.createCell(5).setCellValue(list.get(i).getAdmissionDate());
			rowNum.createCell(6).setCellValue(list.get(i).getDischargeDate());
			rowNum.createCell(7).setCellValue(list.get(i).getZyts());
			rowNum.createCell(8).setCellValue(list.get(i).getTotalAmount());
			rowNum.createCell(9).setCellValue(list.get(i).getBenefitType());
			rowNum.createCell(10).setCellValue(list.get(i).getWesternMedicineFee());
			rowNum.createCell(11).setCellValue(list.get(i).getChineseMedicineYinpian());
			rowNum.createCell(12).setCellValue(list.get(i).getChineseMedicineForm());
			rowNum.createCell(13).setCellValue(list.get(i).getAccommodationFee());
			rowNum.createCell(14).setCellValue(list.get(i).getDiagnosisFee());
			rowNum.createCell(15).setCellValue(list.get(i).getInspectionFee());
			rowNum.createCell(16).setCellValue(list.get(i).getTestFee());
			rowNum.createCell(17).setCellValue(list.get(i).getTreatmentFee());
			rowNum.createCell(18).setCellValue(list.get(i).getNursingFee());
			rowNum.createCell(19).setCellValue(list.get(i).getMaterialFee());
			rowNum.createCell(20).setCellValue(list.get(i).getConsultationFee());
			rowNum.createCell(21).setCellValue(list.get(i).getRegistrationFee());
			rowNum.createCell(22).setCellValue(list.get(i).getOtherFee());
		}
		return wb;
	}
}

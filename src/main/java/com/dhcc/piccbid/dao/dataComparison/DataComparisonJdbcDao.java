package com.dhcc.piccbid.dao.dataComparison;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.dataComparison.DataComparisonDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.dataComparison.DataComparison;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DataComparisonJdbcDao {
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;

	// 统计两个医院的总费用
	@SuppressWarnings("unchecked")
	public void table1(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		sql.append("select sum(TOTAL_AMOUNT) as TOTAL_AMOUNT, HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME\n"
				+ "  from ");
		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append(" group by HOSPITAL_ID");
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 统计两个医院的报销费用
	@SuppressWarnings("unchecked")
	public void table2(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		sql.append(
				"select sum(BMI_PAY_AMOUNT) as BMI_PAY_AMOUNT, HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME  from ");
		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode2 + "','" + orgCode1 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append(" group by HOSPITAL_ID");
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 统计两个医院收治病人人次人数
	@SuppressWarnings("unchecked")
	public void table3(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String code = dto.getCode();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		String year = dto.getYear();
		Calendar date = Calendar.getInstance();
		String year1 = String.valueOf(date.get(Calendar.YEAR));
		if (code != null) {
			if (code.equals("0")) {
				sql.append("select HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME,count(PATIENT_ID) as result");
				sql.append(" from (select PATIENT_ID,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
				if (diagType.equals("1")) {
					sql.append(" t_flycheck_medical ");
				} else if (diagType.equals("2")) {
					sql.append(" t_flycheck_medical_mz ");
				}
				if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
					sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
				} else if (diagType.equals("1")) {
					sql.append(" where HOSPITAL_ID in('0001','0002')");
				} else if (diagType.equals("2")) {
					sql.append(" where HOSPITAL_ID in('00010015','00010013')");
				}
				if (!StringUtils.isNullOrEmpty(personType)) {
					sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
				}
				if (!StringUtils.isNullOrEmpty(balanceDate)) {
					String[] time = balanceDate.split(" ");
					String startTime = time[0];
					String endTime = time[2];
					sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
							+ "', 'yyyyMMdd')");
				}
				sql.append(" group by PATIENT_ID, HOSPITAL_ID)group by HOSPITAL_ID");
			} else if (code.equals("1")) {
				sql.append("select HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME,sum(result) as result");
				sql.append(
						" from (select COUNT(*) AS result,PATIENT_ID,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
				if (diagType.equals("1")) {
					sql.append(" t_flycheck_medical ");
				} else if (diagType.equals("2")) {
					sql.append(" t_flycheck_medical_mz ");
				}
				if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
					sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
				} else if (diagType.equals("1")) {
					sql.append(" where HOSPITAL_ID in('0001','0002')");
				} else if (diagType.equals("2")) {
					sql.append(" where HOSPITAL_ID in('00010015','00010013')");
				}
				if (!StringUtils.isNullOrEmpty(personType)) {
					sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
				}
				if (!StringUtils.isNullOrEmpty(balanceDate)) {
					String[] time = balanceDate.split(" ");
					String startTime = time[0];
					String endTime = time[2];
					sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
							+ "', 'yyyyMMdd')");
				}
				sql.append(" group by PATIENT_ID, HOSPITAL_ID)group by HOSPITAL_ID");
			} else if (code.equals("2")) {
				sql.append("select HOSPITAL_ID,year,min(HOSPITAL_NAME) as HOSPITAL_NAME,sum(result) as result");
				sql.append(
						" from (select COUNT(*) AS result,year,PATIENT_ID,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
				if (diagType.equals("1")) {
					sql.append(" t_flycheck_medical ");
				} else if (diagType.equals("2")) {
					sql.append(" t_flycheck_medical_mz ");
				}
				if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
					sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
				} else if (diagType.equals("1")) {
					sql.append(" where HOSPITAL_ID in('0001','0002')");
				} else if (diagType.equals("2")) {
					sql.append(" where HOSPITAL_ID in('00010015','00010013')");
				}
				if (!StringUtils.isNullOrEmpty(personType)) {
					sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
				}
				if (!StringUtils.isNullOrEmpty(year)) {
					sql.append(" and year='" + year + "'");
				} else {
					sql.append(" and year='" + year1 + "'");
				}
				sql.append(" group by PATIENT_ID,HOSPITAL_ID,year)group by HOSPITAL_ID,year");
			} else if (code.equals("3")) {
				sql.append("select HOSPITAL_ID,year,min(HOSPITAL_NAME) as HOSPITAL_NAME,count(PATIENT_ID) as result");
				sql.append(" from (select PATIENT_ID,year,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
				if (diagType.equals("1")) {
					sql.append(" t_flycheck_medical ");
				} else if (diagType.equals("2")) {
					sql.append(" t_flycheck_medical_mz ");
				}
				if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
					sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
				} else if (diagType.equals("1")) {
					sql.append(" where HOSPITAL_ID in('0001','0002')");
				} else if (diagType.equals("2")) {
					sql.append(" where HOSPITAL_ID in('00010015','00010013')");
				}
				if (!StringUtils.isNullOrEmpty(personType)) {
					sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
				}
				if (!StringUtils.isNullOrEmpty(year)) {
					sql.append(" and year='" + year + "'");
				} else {
					sql.append(" and year='" + year1 + "'");
				}
				sql.append(" group by PATIENT_ID,HOSPITAL_ID,year)group by HOSPITAL_ID,year");
			}
		}
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 统计两个医院次均住院/门诊费用
	@SuppressWarnings("unchecked")
	public void table45(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		sql.append(
				"select ROUND(avg(TOTAL_AMOUNT)，2) as each_cost, HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from ");
		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append(" group by HOSPITAL_ID");
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 平均住院天数对比
	@SuppressWarnings("unchecked")
	public void table6(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		if (diagType.equals("1")) {
			sql.append(
					"select ROUND(avg(to_date(to_char(DISCHARGE_DATE,'yyyy-mm-dd'),'yyyy-mm-dd')-to_date(to_char(ADMISSION_DATE,'yyyy-mm-dd'),'yyyy-mm-dd')),2) as avg_days,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from  t_flycheck_medical");
			if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
				sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
			} else if (diagType.equals("1")) {
				sql.append(" where HOSPITAL_ID in('0001','0002')");
			}
			if (!StringUtils.isNullOrEmpty(personType)) {
				sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
			}
			if (!StringUtils.isNullOrEmpty(balanceDate)) {
				String[] time = balanceDate.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
			}
			sql.append(" group by HOSPITAL_ID");
		} else if (diagType.equals("2")) {
			List<DataComparison> list = null;
			page.setPageData(list);
			dto.setPageModel(page);
			return;
		}
		// 门诊没有 平均住院天数ERROR
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 复诊率对比(人次-人数)/(人数)
	@SuppressWarnings("unchecked")
	public void table7(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		sql.append(
				"SELECT hospital_id,MIN(hospital_name) AS hospital_name,round(((SUM(renci) - COUNT(patient_id)) / COUNT(patient_id)), 4) * 100 as result");
		sql.append(" FROM(SELECT COUNT(*) AS renci,patient_id,hospital_id,MIN(hospital_name) AS hospital_name FROM");
		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append("  GROUP BY patient_id,hospital_id)group by HOSPITAL_ID");
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 两个医院治疗疾病总费用第一家医院
	@SuppressWarnings("unchecked")
	public void table8_1(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		sql.append("SELECT * FROM");
		sql.append(" (SELECT hospital_name,admission_disease_name AS result,SUM(total_amount) AS sum FROM");

		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode1 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0001')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010015')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append(" GROUP BY hospital_id, hospital_name,admission_disease_name ORDER BY sum DESC)");
		sql.append(" WHERE ROWNUM < 11 ORDER BY sum DESC");
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 两个医院治疗疾病总费用第一家医院
	@SuppressWarnings("unchecked")
	public void table8_2(DataComparisonDto dto) {
		StringBuilder sql = new StringBuilder();
		PageModel page = new PageModel();
		String bingZ[] = dto.getBingZ();
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		sql.append("SELECT * FROM");
		sql.append(" (SELECT hospital_name,admission_disease_name as result,sum(total_amount) as sum FROM");
		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0002')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010013')");
		}
		if (bingZ != null) {
			sql.append(" and admission_disease_name in('" + bingZ[0] + "','" + bingZ[1] + "','" + bingZ[2] + "','"
					+ bingZ[3] + "','" + bingZ[4] + "','" + bingZ[5] + "','" + bingZ[6] + "','" + bingZ[7] + "','"
					+ bingZ[8] + "','" + bingZ[9] + "')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append(" GROUP BY hospital_name,admission_disease_name");
		sql.append(" ORDER BY sum DESC)WHERE ROWNUM < 11 ORDER BY sum DESC");
		List<DataComparison> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		page.setPageData(list);
		dto.setPageModel(page);
	}

	// 导出到excel
	@SuppressWarnings("unchecked")
	public SXSSFWorkbook exportExcelToSelf(DataComparisonDto dto) {
		String diagType = dto.getDiagType();
		String personType = dto.getPersonType();
		String orgCode1 = dto.getOrgCode1();
		String orgCode2 = dto.getOrgCode2();
		String balanceDate = dto.getBalanceDate();
		String bingZ[] = dto.getBingZ();
		String year = dto.getYear();
		Calendar date = Calendar.getInstance();
		String year1 = String.valueOf(date.get(Calendar.YEAR));
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		StringBuilder sql3 = new StringBuilder();
		StringBuilder sql4 = new StringBuilder();
		StringBuilder sql5 = new StringBuilder();
		StringBuilder sql6 = new StringBuilder();
		StringBuilder sql7 = new StringBuilder();
		StringBuilder sql8 = new StringBuilder();
		StringBuilder sql9 = new StringBuilder();
		StringBuilder sql10 = new StringBuilder();
		// 总费用对比
		sql.append("select sum(TOTAL_AMOUNT) as TOTAL_AMOUNT, HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME\n"
				+ "  from ");
		if (diagType.equals("1")) {
			sql.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql.append(" group by HOSPITAL_ID");
		// 报销金额对比
		sql1.append(
				"select sum(BMI_PAY_AMOUNT) as BMI_PAY_AMOUNT, HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME  from ");
		if (diagType.equals("1")) {
			sql1.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql1.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql1.append(" where HOSPITAL_ID in('" + orgCode2 + "','" + orgCode1 + "')");
		} else if (diagType.equals("1")) {
			sql1.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql1.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql1.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql1.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql1.append(" group by HOSPITAL_ID");
		// 收治病人对比
		sql2.append("select HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME,count(PATIENT_ID) as result");
		sql2.append(" from (select PATIENT_ID,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
		if (diagType.equals("1")) {
			sql2.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql2.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql2.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql2.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql2.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql2.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql2.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql2.append(" group by PATIENT_ID, HOSPITAL_ID)group by HOSPITAL_ID");
		sql3.append("select HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME,sum(result) as result");
		sql3.append(" from (select COUNT(*) AS result,PATIENT_ID,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
		if (diagType.equals("1")) {
			sql3.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql3.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql3.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql3.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql3.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql3.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql3.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql3.append(" group by PATIENT_ID, HOSPITAL_ID)group by HOSPITAL_ID");
		sql4.append("select HOSPITAL_ID,year,min(HOSPITAL_NAME) as HOSPITAL_NAME,sum(result) as result");
		sql4.append(
				" from (select COUNT(*) AS result,year,PATIENT_ID,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
		if (diagType.equals("1")) {
			sql4.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql4.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql4.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql4.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql4.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql4.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(year)) {
			sql4.append(" and year='" + year + "'");
		} else {
			sql4.append(" and year='" + year1 + "'");
		}
		sql4.append(" group by PATIENT_ID,HOSPITAL_ID,year)group by HOSPITAL_ID,year");
		sql5.append("select HOSPITAL_ID,year,min(HOSPITAL_NAME) as HOSPITAL_NAME,count(PATIENT_ID) as result");
		sql5.append(" from (select PATIENT_ID,year,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from");
		if (diagType.equals("1")) {
			sql5.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql5.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql5.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql5.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql5.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql5.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(year)) {
			sql5.append(" and year='" + year + "'");
		} else {
			sql5.append(" and year='" + year1 + "'");
		}
		sql5.append(" group by PATIENT_ID,HOSPITAL_ID,year)group by HOSPITAL_ID,year");
		// 次均住院/门诊费用对比
		sql6.append(
				"select ROUND(avg(TOTAL_AMOUNT)，2) as each_cost, HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from ");
		if (diagType.equals("1")) {
			sql6.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql6.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql6.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql6.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql6.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql6.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql6.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql6.append(" group by HOSPITAL_ID");
		// 平均住院天数
		if (diagType.equals("1")) {
			sql7.append(
					"select ROUND(avg(to_date(to_char(DISCHARGE_DATE,'yyyy-mm-dd'),'yyyy-mm-dd')-to_date(to_char(ADMISSION_DATE,'yyyy-mm-dd'),'yyyy-mm-dd')),2) as avg_days,HOSPITAL_ID,min(HOSPITAL_NAME) as HOSPITAL_NAME from  t_flycheck_medical");
			if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
				sql7.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
			} else if (diagType.equals("1")) {
				sql7.append(" where HOSPITAL_ID in('0001','0002')");
			}
			if (!StringUtils.isNullOrEmpty(personType)) {
				sql7.append(" and BENEFIT_GROUP_ID='" + personType + "'");
			}
			if (!StringUtils.isNullOrEmpty(balanceDate)) {
				String[] time = balanceDate.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				sql7.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
			}
			sql7.append(" group by HOSPITAL_ID");
		}
		// 复诊率对比
		sql8.append(
				"SELECT hospital_id,MIN(hospital_name) AS hospital_name,round(((SUM(renci) - COUNT(patient_id)) / COUNT(patient_id)), 4) * 100 as result");
		sql8.append(" FROM(SELECT COUNT(*) AS renci,patient_id,hospital_id,MIN(hospital_name) AS hospital_name FROM");
		if (diagType.equals("1")) {
			sql8.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql8.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql8.append(" where HOSPITAL_ID in('" + orgCode1 + "','" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql8.append(" where HOSPITAL_ID in('0001','0002')");
		} else if (diagType.equals("2")) {
			sql8.append(" where HOSPITAL_ID in('00010015','00010013')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql8.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql8.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql8.append("  GROUP BY patient_id,hospital_id)group by HOSPITAL_ID");
		// 两家医院top10病种对比
		sql9.append("SELECT * FROM");
		sql9.append(" (SELECT hospital_name,admission_disease_name AS result,SUM(total_amount) AS sum FROM");

		if (diagType.equals("1")) {
			sql9.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql9.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql9.append(" where HOSPITAL_ID in('" + orgCode1 + "')");
		} else if (diagType.equals("1")) {
			sql9.append(" where HOSPITAL_ID in('0001')");
		} else if (diagType.equals("2")) {
			sql9.append(" where HOSPITAL_ID in('00010015')");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql9.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql9.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql9.append(" GROUP BY hospital_id, hospital_name,admission_disease_name ORDER BY sum DESC)");
		sql9.append(" WHERE ROWNUM < 11 ORDER BY sum DESC");
		// BBBBBBB
		sql10.append("SELECT * FROM");
		sql10.append(" (SELECT hospital_name,admission_disease_name as result,sum(total_amount) as sum FROM");
		if (diagType.equals("1")) {
			sql10.append(" t_flycheck_medical ");
		} else if (diagType.equals("2")) {
			sql10.append(" t_flycheck_medical_mz ");
		}
		if (!StringUtils.isNullOrEmpty(orgCode1) && !StringUtils.isNullOrEmpty(orgCode2)) {
			sql10.append(" where HOSPITAL_ID in('" + orgCode2 + "')");
		} else if (diagType.equals("1")) {
			sql10.append(" where HOSPITAL_ID in('0002')");
		} else if (diagType.equals("2")) {
			sql10.append(" where HOSPITAL_ID in('00010013')");
		}
		if (bingZ != null) {
			sql10.append(" and admission_disease_name in(");
			for (int i = 0; i < bingZ.length; i++) {
				/*
				 * sql10.append(" and admission_disease_name in('" + bingZ[i] + "','" + bingZ[1]
				 * + "','" + bingZ[2] + "','" + bingZ[3] + "','" + bingZ[4] + "','" + bingZ[5] +
				 * "','" + bingZ[6] + "','" + bingZ[7] + "','" + bingZ[8] + "','" + bingZ[9] +
				 * "')");
				 */
				if (i == bingZ.length - 1)// 当循环到最后一个的时候 就不添加逗号,
				{
					sql10.append("'" + bingZ[i] + "'");
				} else {
					sql10.append("'" + bingZ[i] + "',");
				}
			}
			sql10.append(")");
		}
		if (!StringUtils.isNullOrEmpty(personType)) {
			sql10.append(" and BENEFIT_GROUP_ID='" + personType + "'");
		}
		if (!StringUtils.isNullOrEmpty(balanceDate)) {
			String[] time = balanceDate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			sql10.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
		}
		sql10.append(" GROUP BY hospital_name,admission_disease_name");
		sql10.append(" ORDER BY sum DESC)WHERE ROWNUM < 11 ORDER BY sum DESC");
		List<DataComparison> dataComparisons = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons1 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql1.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons2 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql2.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons2_1 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql3.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons2_2 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql4.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons2_3 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql5.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons6 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql6.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons7 = null;
		if (diagType.equals("1")) {
			dataComparisons7 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql7.toString(), DataComparison.class,
					null);
		}
		List<DataComparison> dataComparisons8 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql8.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons9 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql9.toString(),
				DataComparison.class, null);
		List<DataComparison> dataComparisons10 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql10.toString(),
				DataComparison.class, null);
		SXSSFWorkbook wb = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("总费用对比");
		SXSSFSheet sheet1 = (SXSSFSheet) wb.createSheet("报销金额对比");
		SXSSFSheet sheet2 = (SXSSFSheet) wb.createSheet("收治病人对比");
		SXSSFSheet sheet3 = (SXSSFSheet) wb.createSheet("次均住院||门诊费用对比");
		SXSSFSheet sheet4 = (SXSSFSheet) wb.createSheet("平均住院天数对比");
		SXSSFSheet sheet5 = (SXSSFSheet) wb.createSheet("复诊率对比");
		SXSSFSheet sheet6 = (SXSSFSheet) wb.createSheet("两个医院治疗疾病总费用top10");
		SXSSFRow row1 = (SXSSFRow) sheet.createRow(0);
		SXSSFRow row2 = (SXSSFRow) sheet1.createRow(0);
		SXSSFRow row3 = (SXSSFRow) sheet2.createRow(0);
		SXSSFRow row4 = (SXSSFRow) sheet3.createRow(0);
		SXSSFRow row5 = (SXSSFRow) sheet4.createRow(0);
		SXSSFRow row6 = (SXSSFRow) sheet5.createRow(0);
		SXSSFRow row7 = (SXSSFRow) sheet6.createRow(0);
		row1.createCell(0).setCellValue("医疗机构名称");
		row1.createCell(1).setCellValue("总费用");
		row2.createCell(0).setCellValue("医疗机构名称");
		row2.createCell(1).setCellValue("报销费用");
		row3.createCell(0).setCellValue("医疗机构名称");
		row3.createCell(1).setCellValue("人数对比");
		row3.createCell(2).setCellValue("人次对比");
		row3.createCell(3).setCellValue("人次对比按年");
		row3.createCell(4).setCellValue("人数对比按年");
		row4.createCell(0).setCellValue("医疗机构名称");
		row4.createCell(1).setCellValue("费用");
		row5.createCell(0).setCellValue("医疗机构名称");
		row5.createCell(1).setCellValue("平均住院天数");
		row6.createCell(0).setCellValue("医疗机构名称");
		row6.createCell(1).setCellValue("复诊率(%)");
		row7.createCell(0).setCellValue("医疗机构名称");
		row7.createCell(1).setCellValue("病种");
		row7.createCell(2).setCellValue("总费用");
		row7.createCell(4).setCellValue("对比医疗机构名称");
		row7.createCell(5).setCellValue("病种");
		row7.createCell(6).setCellValue("总费用");
		for (int i = 0; i < dataComparisons.size(); i++) {
			SXSSFRow rowNum = (SXSSFRow) sheet.createRow(i + 1);
			rowNum.createCell(0).setCellValue(dataComparisons.get(i).getHospitalName());
			rowNum.createCell(1).setCellValue(dataComparisons.get(i).getTotalAmount());
		}
		for (int i = 0; i < dataComparisons1.size(); i++) {
			SXSSFRow rowNum = (SXSSFRow) sheet1.createRow(i + 1);
			rowNum.createCell(0).setCellValue(dataComparisons1.get(i).getHospitalName());
			rowNum.createCell(1).setCellValue(dataComparisons1.get(i).getBmiPayAmount());
		}
		// 收治病人对比
		for (int i = 0; i < dataComparisons2.size(); i++) {
			SXSSFRow rowNum = (SXSSFRow) sheet2.createRow(i + 1);
			rowNum.createCell(0).setCellValue(dataComparisons2.get(i).getHospitalName());
			rowNum.createCell(1).setCellValue(dataComparisons2.get(i).getResult());
			for (int j = i; j < dataComparisons2_1.size(); j++) {
				rowNum.createCell(2).setCellValue(dataComparisons2_1.get(i).getResult());
			}
			for (int a = i; a < dataComparisons2_2.size(); a++) {
				rowNum.createCell(3).setCellValue(
						dataComparisons2_2.get(i).getYear() + "年人次：" + dataComparisons2_2.get(i).getResult());
			}
			for (int b = i; b < dataComparisons2_3.size(); b++) {
				rowNum.createCell(4).setCellValue(
						dataComparisons2_3.get(i).getYear() + "年人数：" + dataComparisons2_3.get(i).getResult());
			}
		}
		for (int i = 0; i < dataComparisons6.size(); i++) {
			SXSSFRow rowNum3 = (SXSSFRow) sheet3.createRow(i + 1);
			rowNum3.createCell(0).setCellValue(dataComparisons6.get(i).getHospitalName());
			rowNum3.createCell(1).setCellValue(dataComparisons6.get(i).getEachCost());
		}
		if (diagType.equals("1")) {
			for (int i = 0; i < dataComparisons7.size(); i++) {
				SXSSFRow rowNum3 = (SXSSFRow) sheet4.createRow(i + 1);
				rowNum3.createCell(0).setCellValue(dataComparisons7.get(i).getHospitalName());
				rowNum3.createCell(1).setCellValue(dataComparisons7.get(i).getAvgDays());
			}
		}
		for (int i = 0; i < dataComparisons8.size(); i++) {
			SXSSFRow rowNum3 = (SXSSFRow) sheet5.createRow(i + 1);
			rowNum3.createCell(0).setCellValue(dataComparisons8.get(i).getHospitalName());
			rowNum3.createCell(1).setCellValue(dataComparisons8.get(i).getResult());
		}
		for (int i = 0; i < dataComparisons9.size(); i++) {
			SXSSFRow rowNum3 = (SXSSFRow) sheet6.createRow(i + 1);
			rowNum3.createCell(0).setCellValue(dataComparisons9.get(i).getHospitalName());
			rowNum3.createCell(1).setCellValue(dataComparisons9.get(i).getResult());
			rowNum3.createCell(2).setCellValue(dataComparisons9.get(i).getSum());

			for (int j = i; j < dataComparisons10.size(); j++) {
				rowNum3.createCell(4).setCellValue(dataComparisons10.get(i).getHospitalName());
				rowNum3.createCell(5).setCellValue(dataComparisons10.get(i).getResult());
				rowNum3.createCell(6).setCellValue(dataComparisons10.get(i).getSum());
			}
		}
		/*
		 * for (int i = 0; i < dataComparisons10.size(); i++) { SXSSFRow rowNum3 =
		 * (SXSSFRow) sheet7.createRow(i + 1);
		 * rowNum3.createCell(0).setCellValue(dataComparisons10.get(i).getHospitalName()
		 * ); rowNum3.createCell(1).setCellValue(dataComparisons10.get(i).getResult());
		 * rowNum3.createCell(2).setCellValue(dataComparisons10.get(i).getSum()); }
		 */
		return wb;
	}
}

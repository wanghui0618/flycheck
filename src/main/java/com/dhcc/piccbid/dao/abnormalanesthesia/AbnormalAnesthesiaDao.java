package com.dhcc.piccbid.dao.abnormalanesthesia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.abnormalanesthesia.AbnormalAnesthesiaDto;
import com.dhcc.piccbid.entity.abnormalanesthesia.AbnormalAnesthesia;

@Component
public class AbnormalAnesthesiaDao {
	@Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    /**
     * 方法名:getTotalNumberOfCasesAndTotalAmount
     * 方法功能描述:就诊次数异常 计算总病例数和总金额方法
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:贺和平
     * @Create Date:2020年1月8日
     */
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalAnesthesiaDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select sum(RS) totalNumberOfCases,sum(ZS) sumTotalAmount from ( ");
        sql.append(" select count(1) RS ,sum(TOTAL_AMOUNT) ZS from T_FLYCHECK_MEDICAL b");
        sql.append(" where b.HISID IN ");	
        sql.append(" (select a.HISID from (select HISID,count(item_id) as num from T_FLYCHECK_MEDICAL_DETAIL where item_id='330100007'  ");
        //拼接条件
 
        String admissiondate = dto.getAdmissiondate(); //入院时间
        String dischargedate = dto.getDischargedate();//出院时间
        String hospitalCode = dto.getHospitalCode();//医院编码
        String hospitalName = dto.getHospitalName();//医院名称
        
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and HOSPITAL_ID='"+hospitalCode+"' ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and hospital_name='"+hospitalName+"' ");
        }
        if (!StringUtils.isNullOrEmpty(admissiondate) && admissiondate != null && admissiondate != "") {
        	String[] time = admissiondate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			
			sql.append(" and ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
        }
        if (!StringUtils.isNullOrEmpty(dischargedate) && dischargedate != null && dischargedate != "") {
        	String[] time = dischargedate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			
			sql.append(" and DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
        }
        sql.append(" group by HISID ) a where num>1)) ");
        
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
        return list;
    }
    
    /**
     * 方法名:getAbnormalAnesthes
     * 方法功能描述:同时间出入院人信息方法
     * @param:@return
     * @return:PageModel
     * @Author:贺和平
     * @Create Date:2020年1月8日
     */
    public PageModel getAbnormalAnesthes (AbnormalAnesthesiaDto dto){
    	
        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT  t.HISID,t.HOSPITAL_ID,t.HOSPITAL_NAME,t.P_LEVEL,t.BMI_AREA_ID,t.BMI_AREA_NAME,t.BILL_DATE,t.YEAR,t.MONTH,t.ZYH,t.PATIENT_ID ");
        sql.append(" ,t.SOCIAL_CARD_ID,t.MEDICAL_RECORD_ID,t.BENEFIT_TYPE,t.DISCHARGE_DEPT_NAME,t.PATIENT_NAME,t.PATIENT_GENDER,t.PATIENT_BIRTHDAY, ");
        sql.append(" t.PATIENT_AGE,t.PATIENT_COMPANY,t.CLAIM_TYPE,t.IF_LOCAL_FLAG,t.ADMISSION_DATE,t.DISCHARGE_DATE,t.ZYTS,t.TOTAL_AMOUNT, ");
        sql.append(" t.BMI_PAY_AMOUNT,t.ADMISSION_DISEASE_ID,t.ADMISSION_DISEASE_NAME,t.DISCHARGE_DISEASE_ID_MAIN,t.DISCHARGE_DISEASE_NAME_MAIN ");
        sql.append(" from T_FLYCHECK_MEDICAL t ");
        sql.append(" WHERE t.HISID IN  ");
        sql.append(" (select a.HISID from (select HISID,count(item_id) as num from T_FLYCHECK_MEDICAL_DETAIL where item_id='330100007' group by HISID) a where num>1) ");

        String admissiondate = dto.getAdmissiondate(); //入院时间
        String dischargedate = dto.getDischargedate();//出院时间
        String hospitalCode = dto.getHospitalCode();//医院编码
        String hospitalName = dto.getHospitalName();//医院名称

        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and HOSPITAL_ID='"+hospitalCode+"' ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and hospital_name='"+hospitalName+"' ");
        }
        if (!StringUtils.isNullOrEmpty(admissiondate) && admissiondate != null && admissiondate != "") {
        	String[] time = admissiondate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			
			sql.append(" and ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
        }
        if (!StringUtils.isNullOrEmpty(dischargedate) && dischargedate != null && dischargedate != "") {
        	String[] time = dischargedate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			
			sql.append(" and DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
        }
        
        //根据t.HISID 去计算总数 为了分页做准备
        int n =getResultCountWithValuesMap(sql.toString(),"HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        return dto.getPageModel();
    }
    
    /**
	 * 方法名:getFrequencyTreatmentmxbyhisidTable 方法功能描述:病例明细详情查询,根据病人的hisid查询住院明细结算表
	 * 
	 * @param:@return
	 * @return:PageModel
	 * @Author:贺和平
	 * @Create Date:2020年1月6日
	 */
	public PageModel getAbnormalAnesthesbyhisidTable(AbnormalAnesthesiaDto dto) {
		StringBuilder sql = new StringBuilder();
		String hisid = dto.getHisid();// 住院号
		sql.append(" SELECT HISID,ITEM_ID_HOSP,ITEM_NAME_HOSP,ITEM_ID,ITEM_NAME,P_CATEGORY,USAGE_DATE,BILL_DATE,DRUG_SPEC,UNIT_PRICE,NUM,COST,BMI_PAY_AMOUNT,P_TYPE ");
		sql.append(" FROM T_FLYCHECK_MEDICAL_DETAIL where 1=1 ");
		// 拼接条件
		if (!StringUtils.isNullOrEmpty(hisid) && hisid != null && hisid != "") {
			sql.append(" AND HISID ='" + hisid + "'  ");
		} else {
			sql.append(" AND  ROWNUM<10 ");
		}
		sql.append(" ORDER BY USAGE_DATE desc ");

		// 根据hisid 去计算总数 为了分页做准备
		int n = getResultCountWithValuesMap(sql.toString(), "hisid", null);
		// 调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
		// 将list添加到dto的 PageData
		dto.getPageModel().setPageData(list);
		// 将总数据量加入到dto的Totals
		dto.getPageModel().setTotals(n);
		return dto.getPageModel();
	}
    
    /**
	 * 方法名:exportD 方法功能描述:数据导出查询方法
	 * 
	 * @param:@return
	 * @return:List<AbnormalFrequencyTreatmentZY>
	 * @Author:贺和平
	 * @Create Date:2020年1月6日
	 */
	public List<AbnormalAnesthesia> exportD(AbnormalAnesthesiaDto dto) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> sqlParamMap = new HashMap<String, Object>();

		sql.append(" SELECT  t.HISID,t.ZYH,t.ADMISSION_DATE,t.DISCHARGE_DATE,t.ZYTS, ");
		sql.append(" t.BILL_DATE,t.DISCHARGE_DISEASE_ID_MAIN,t.DISCHARGE_DISEASE_NAME_MAIN,t.TOTAL_AMOUNT,t.BMI_PAY_AMOUNT ");
		sql.append(" from T_FLYCHECK_MEDICAL t ");
		sql.append(" WHERE t.HISID IN  ");
        sql.append(" (select a.HISID from (select HISID,count(item_id) as num from T_FLYCHECK_MEDICAL_DETAIL where item_id='330100007' group by HISID) a where num>1) ");

        String admissiondate = dto.getAdmissiondate(); //入院时间
        String dischargedate = dto.getDischargedate();//出院时间
        String hospitalCode = dto.getHospitalCode();//医院编码
        String hospitalName = dto.getHospitalName();//医院名称

        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and HOSPITAL_ID='"+hospitalCode+"' ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and hospital_name='"+hospitalName+"' ");
        }
        if (!StringUtils.isNullOrEmpty(admissiondate) && admissiondate != null && admissiondate != "") {
        	String[] time = admissiondate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			
			sql.append(" and ADMISSION_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
        }
        if (!StringUtils.isNullOrEmpty(dischargedate) && dischargedate != null && dischargedate != "") {
        	String[] time = dischargedate.split(" ");
			String startTime = time[0];
			String endTime = time[2];
			
			sql.append(" and DISCHARGE_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
					+ "', 'yyyyMMdd')");
        }
              
        System.out.println("下载");
        System.out.println(sql);
        
        @SuppressWarnings("unused")
		List<AbnormalAnesthesia> abnormalAnesthesias = jdbcTemplateWrapper
				.queryAllMatchListWithParaMap(sql.toString(), AbnormalAnesthesia.class, sqlParamMap);
		return abnormalAnesthesias;
	}

    
    
    
    
    /**
     * 方法名：toPageModelSql
     *方法功能描述：将sql转换为分页sql
     *@param:@sql 初始sql，pageNo  当前页数，pageSize  当前页面个数
     *@return:String
     *@Atuhor：贺和平
     *@Create Date : 2019-11-23
     */
    public static String toPageModelSql(String sql,int pageNo,int pageSize){
        sql = " select * from (select a.*,rownum as num1 from ( "+sql+") a where rownum <= "+pageNo*pageSize+")"
                + " where num1 > "+(pageNo-1)*pageSize;
        return sql;
    }
    public int getResultCountWithValuesMap(String sql, String columnNameForCount, Map<String, Object> praValuesMap) {
        String   countQuerySql = " select count(" + columnNameForCount + ") from ("+sql+")";
        return (Integer)jdbcTemplate.queryForObject(countQuerySql.toString(), null, Integer.class);
    }   
}
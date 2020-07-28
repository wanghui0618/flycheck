package com.dhcc.piccbid.dao.abnormalfrequencytreatment;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.abnormalFrequencyTreatment.AbnormalFrequencyTreatmentDto;
import com.dhcc.piccbid.entity.abnormalfrequencytreatment.AbnormalFrequencyTreatment;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>标题:AbnormalFrequencyTreatmentDao.java</p>
 * <p>业务描述:就诊次数异常</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2019年11月23日
 * @version V1.0
 */
@Component
public class AbnormalFrequencyTreatmentDao {
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
	     * @Create Date:2019年11月25日
	     */
	    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        sql.append(" select sum(count) totalNumberOfCases,sum(total) sumTotalAmount from ( ");
	        sql.append(" select SOCIAL_CARD_ID,PATIENT_ID,count(1) count,sum(TOTAL_AMOUNT) total from T_FLYCHECK_MEDICAL_MZ ");
	        sql.append(" where 1=1");	        
	        //拼接条件
	        String route = dto.getRoute();// 就诊途径
	        String hospitalCode = dto.getHospitalCode();// 医院编码
	        String hospitalName = dto.getHospitalName();// 医院名称
	        String settlementTime = dto.getSettlementTime();// 结算时间	     
	        String departmentName = dto.getDepartmentName();// 科室名称
	        String count=dto.getCount(); //诊断次数
	        String day= dto.getDay();//维度
	        String insurance = dto.getInsurance();//门诊类型
	        
	        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
	            sql.append(" and BENEFIT_TYPE ='"+route+"'  ");
	        }
	        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
	            sql.append(" and HOSPITAL_ID in("+hospitalCode+") ");
	        }
	        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
	            sql.append(" and hospital_name='"+hospitalName+"' ");
	        }else {	        	
	        	sql.append(" and hospital_name is not null ");
	        }*/
	        if (!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != "") {
	        	String[] time = settlementTime.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
	        }
	        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
	            sql.append(" and ADMISSION_DEPT_NAME like '%"+departmentName+"%' ");
	        }
	        if(!StringUtils.isNullOrEmpty(insurance) && insurance != null && insurance != "") {
	        	sql.append(" and CLAIM_TYPE ='"+insurance+"'");
	        }
	        
	        sql.append(" GROUP BY SOCIAL_CARD_ID,PATIENT_ID ORDER BY count desc ) ");
	        sql.append(" where 1=1 ");
	        
	        if (day.equals("dayu")) {
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count > "+count+"  ");
	            }else{
	                sql.append(" and  count > 100  ");
	            }
	        }else if(day.equals("xiaoyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count < "+count+"  ");
	            }else{
	                sql.append(" and  count < 100  ");
	            }
	        }else if(day.equals("dengyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count = "+count+"  ");
	            }else{
	                sql.append(" and  count = 100  ");
	            }
	        }
	        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
	        return list;
	    }


	    /**
	     * 方法名:getInsuredDataForm
	     * 方法功能描述:参保人信息展示查询方法
	     * @param:@return
	     * @return:PageModel
	     * @Author:贺和平
	     * @Create Date:2019年11月25日
	     */
	    public PageModel getInsuredDataForm(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        String route = dto.getRoute();// 就诊途径
	        String hospitalCode = dto.getHospitalCode();// 医院编码
	        String hospitalName = dto.getHospitalName();// 医院名称
	        String settlementTime = dto.getSettlementTime();// 结算时间
	        String departmentName = dto.getDepartmentName();// 科室名称
	        String count=dto.getCount(); //分解住院天数
	        String day= dto.getDay();//维度
	        String insurance = dto.getInsurance();//门诊类型
	        
	        sql.append(" select BENEFIT_TYPE,PATIENT_NAME,PATIENT_ID,SOCIAL_CARD_ID,COUNT from ( ");
	        sql.append(" select BENEFIT_TYPE,PATIENT_NAME,PATIENT_ID,SOCIAL_CARD_ID,count(1) count from T_FLYCHECK_MEDICAL_MZ ");
	        sql.append(" where 1=1 ");
	        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
	            sql.append(" and BENEFIT_TYPE ='"+route+"'  ");
	        }
	        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
	            sql.append(" and HOSPITAL_ID in("+hospitalCode+") ");
	        }
	        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
	            sql.append(" and hospital_name='"+hospitalName+"' ");
	        }else {	        	
	        	sql.append(" and hospital_name is not null ");
	        }*/
	        if (!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != "") {
	        	String[] time = settlementTime.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
	        }
	        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
	            sql.append(" and ADMISSION_DEPT_NAME like '%"+departmentName+"%' ");
	        }
	        if(!StringUtils.isNullOrEmpty(insurance) && insurance != null && insurance != "") {
	        	sql.append(" and CLAIM_TYPE ='"+insurance+"'");
	        }
	        
	        sql.append(" GROUP BY  BENEFIT_TYPE,PATIENT_NAME,PATIENT_ID,SOCIAL_CARD_ID ORDER BY count desc ) ");
	        sql.append(" where 1=1 ");
	        
	      //就诊次数 大于，小于，等于，默认100天
	        if (day.equals("dayu")) {
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count > "+count+"  ");
	            }else{
	                sql.append(" and  count > 100  ");
	            }
	        }else if(day.equals("xiaoyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count < "+count+"  ");
	            }else{
	                sql.append(" and  count < 100 ");
	            }
	        }else if(day.equals("dengyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count = "+count+"  ");
	            }else{
	                sql.append(" and  count = 100  ");
	            }
	        }
	        //根据zyh 去计算总数 为了分页做准备
	        int n =getResultCountWithValuesMap(sql.toString(),"PATIENT_ID", null);
	        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
	        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
	        //将list添加到dto的 PageData
	        dto.getPageModel().setPageData(list);
	        //将总数据量加入到dto的Totals
	        dto.getPageModel().setTotals(n);
	        return dto.getPageModel();
	    }


	    /**
	     * 方法名:getFrequencyTreatmentmxbyhisidTable
	     * 方法功能描述:病例明细详情查询,根据病人的hisid查询住院明细结算表
	     * @param:@return
	     * @return:PageModel
	     * @Author:贺和平
	     * @Create Date:2019年12月24日
	     */
	    public PageModel getFrequencyTreatmentmxbyhisidTable(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        String hisid = dto.getHisid();//住院号
	        sql.append(" SELECT HISID,DISCHARGE_DISEASE_NAME_MAIN,BILL_DATE,ITEM_ID_HOSP,ITEM_NAME_HOSP,ITEM_ID, ");
	        sql.append(" ITEM_NAME,DRUG_SPEC,DOSAGE_FORM,PACKAGE_UNIT,UNIT_PRICE,NUM,COST,BMI_CONVERED_AMOUNT,P_TYPE,P_CATEGORY ");
	        sql.append(" FROM T_FLYCHECK_MEDICAL_MZ_DETAIL where 1=1 ");
	        //拼接条件
	        if (!StringUtils.isNullOrEmpty(hisid) && hisid != null && hisid != "") {
	            sql.append(" and hisid ='"+hisid+"'  ");
	        }
	        sql.append(" ORDER BY BILL_DATE desc ");
	        //根据hisid 去计算总数 为了分页做准备
	        int n =getResultCountWithValuesMap(sql.toString(),"hisid", null);
	        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
	        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
	        //将list添加到dto的 PageData
	        dto.getPageModel().setPageData(list);
	        //将总数据量加入到dto的Totals
	        dto.getPageModel().setTotals(n);
	        return dto.getPageModel();
	    }


	    /**
	     * 方法名:getFrequencyTreatment
	     * 方法功能描述:就诊频次 方法左边
	     * @param:@return
	     * @return:PageModel
	     * @Author:贺和平
	     * @Create Date:2019年11月23日
	     */
	    public PageModel getFrequencyTreatment(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        sql.append(" select BENEFIT_TYPE,PATIENT_NAME,PATIENT_ID,SOCIAL_CARD_ID,COUNT from ( ");
	        sql.append(" select BENEFIT_TYPE,PATIENT_NAME,PATIENT_ID,SOCIAL_CARD_ID,count(1) count from T_FLYCHECK_MEDICAL_MZ ");
	        sql.append(" where 1=1 ");
	        
	        //拼接条件
	        String route = dto.getRoute();// 就诊途径
	        String hospitalCode = dto.getHospitalCode();// 医院编码
	        String hospitalName = dto.getHospitalName();// 医院名称
	        String settlementTime = dto.getSettlementTime();// 结算时间
	        String departmentName = dto.getDepartmentName();// 科室名称
	        String count=dto.getCount(); //就诊次数
	        String day= dto.getDay();//维度
	        String insurance = dto.getInsurance();//门诊类型
	        
	        
	        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
	            sql.append(" and BENEFIT_TYPE ='"+route+"'  ");
	        }
	        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
	            sql.append(" and HOSPITAL_ID in("+hospitalCode+") ");
	        }
	        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
	            sql.append(" and hospital_name='"+hospitalName+"' ");
	        }else {	        	
	        	sql.append(" and hospital_name is not null ");
	        }*/
	        
	        if (!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != "") {
	        	String[] time = settlementTime.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
	        }
	        
	        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
	            sql.append(" and ADMISSION_DEPT_NAME like '%"+departmentName+"%' ");
	        }
	        if(!StringUtils.isNullOrEmpty(insurance) && insurance != null && insurance != "") {
	        	sql.append(" and CLAIM_TYPE ='"+insurance+"'");
	        }	        
	        sql.append(" GROUP BY  BENEFIT_TYPE,PATIENT_NAME,PATIENT_ID,SOCIAL_CARD_ID ORDER BY count desc ) ");
	        sql.append(" where 1=1 ");
	        
	      //就诊次数 大于，小于，等于，默认100天
	        if (day.equals("dayu")) {
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count > "+count+"  ");
	            }else{
	                sql.append(" and  count > 100  ");
	            }
	        }else if(day.equals("xiaoyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count < "+count+"  ");
	            }else{
	                sql.append(" and  count < 100 ");
	            }
	        }else if(day.equals("dengyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count = "+count+"  ");
	            }else{
	                sql.append(" and  count = 100  ");
	            }
	        }
	        //根据t.HISID 去计算总数 为了分页做准备
	        int n =getResultCountWithValuesMap(sql.toString(),"PATIENT_ID", null);
	        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
	        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
	        //将list添加到dto的 PageData
	        dto.getPageModel().setPageData(list);
	        //将总数据量加入到dto的Totals
	        dto.getPageModel().setTotals(n);
	        return dto.getPageModel();
	    }


	    /**
	     * 方法名:exportD
	     * 方法功能描述:数据导出查询方法
	     * @param:@return
	     * @return:List<AbnormalFrequencyTreatment>
	     * @Author:贺和平
	     * @Create Date:2019年12月25日
	     */
	    public List<AbnormalFrequencyTreatment> exportD(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        Map<String, Object> sqlParamMap = new HashMap<String, Object>();

	        sql.append(" SELECT  HISID,HOSPITAL_NAME,BILL_DATE,PATIENT_ID,ADMISSION_DEPT_NAME, ");
	        sql.append(" CLAIM_TYPE,ADMISSION_DISEASE_ID,ADMISSION_DISEASE_NAME,TOTAL_AMOUNT,BMI_PAY_AMOUNT ");
	        sql.append(" FROM T_FLYCHECK_MEDICAL_MZ " );
	        sql.append(" where SOCIAL_CARD_ID in ");
	        sql.append(" ( select SOCIAL_CARD_ID FROM ( ");
	        
	        sql.append(" select SOCIAL_CARD_ID,COUNT from ( ");
	        sql.append(" select SOCIAL_CARD_ID,count(1) count from T_FLYCHECK_MEDICAL_MZ ");
	        sql.append(" where 1=1 ");
	        
	        //拼接条件
	        String route = dto.getRoute();// 就诊途径
	        String hospitalCode = dto.getHospitalCode();// 医院编码
	        String hospitalName = dto.getHospitalName();// 医院名称
	        String settlementTime = dto.getSettlementTime();// 结算时间前
	        String departmentName = dto.getDepartmentName();// 科室名称
	        String count=dto.getCount(); //就诊次数
	        String day= dto.getDay();//维度
	        String insurance = dto.getInsurance();//门诊类型
	        
	        
	        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
	            sql.append(" and BENEFIT_TYPE ='"+route+"'  ");
	        }
	        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
	            sql.append(" and HOSPITAL_ID in("+hospitalCode+") ");
	        }
	        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
	            sql.append(" and hospital_name='"+hospitalName+"' ");
	        }else {	        	
	        	sql.append(" and hospital_name is not null ");
	        }*/
	        if (!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != "") {
	        	String[] time = settlementTime.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
	        }
	        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
	            sql.append(" and ADMISSION_DEPT_NAME like '%"+departmentName+"%' ");
	        }
	        if(!StringUtils.isNullOrEmpty(insurance) && insurance != null && insurance != "") {
	        	sql.append(" and CLAIM_TYPE ='"+insurance+"'");
	        }	        
	        sql.append(" GROUP BY SOCIAL_CARD_ID ORDER BY count desc ) ");
	        sql.append(" where 1=1 ");
	        
	      //就诊次数 大于，小于，等于，默认100天
	        if (day.equals("dayu")) {
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count > "+count+"  ");
	            }else{
	                sql.append(" and  count > 100  ");
	            }
	        }else if(day.equals("xiaoyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count < "+count+"  ");
	            }else{
	                sql.append(" and  count < 100 ");
	            }
	        }else if(day.equals("dengyu")){
	            if (!StringUtils.isNullOrEmpty(count) && count != null && count != "") {
	                sql.append(" and  count = "+count+"  ");
	            }else{
	                sql.append(" and  count = 100  ");
	            }
	        }
	        
	        sql.append(" ) )");
	        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
	            sql.append(" and BENEFIT_TYPE ='"+route+"'  ");
	        }
	        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
	            sql.append(" and HOSPITAL_ID in("+hospitalCode+") ");
	        }
	        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
	            sql.append(" and hospital_name='"+hospitalName+"' ");
	        }else {	        	
	        	sql.append(" and hospital_name is not null ");
	        }*/
	        if (!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != "") {
	        	String[] time = settlementTime.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
	        }
	        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
	            sql.append(" and ADMISSION_DEPT_NAME like '%"+departmentName+"%' ");
	        }
	        if(!StringUtils.isNullOrEmpty(insurance) && insurance != null && insurance != "") {
	        	sql.append(" and CLAIM_TYPE ='"+insurance+"'");
	        }	 

//	        System.out.println(sql);
	        @SuppressWarnings("unused")
	        List<AbnormalFrequencyTreatment> abnormalFrequencyTreatments = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
	        		AbnormalFrequencyTreatment.class, sqlParamMap);
	        return abnormalFrequencyTreatments;
	    }


	    /**
	     * 方法名:exportA
	     * 方法功能描述:数据导出少量查询方法
	     * @param:@return
	     * @return:List<AbnormalFrequencyTreatment>
	     * @Author:贺和平
	     * @Create Date:2019年12月25日
	     */
	    public List<AbnormalFrequencyTreatment> exportA(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        Map<String, Object> sqlParamMap = new HashMap<String, Object>();
	        sql.append(" SELECT  HISID,HOSPITAL_NAME,BILL_DATE,PATIENT_ID,ADMISSION_DEPT_NAME, ");
	        sql.append(" CLAIM_TYPE,ADMISSION_DISEASE_ID,ADMISSION_DISEASE_NAME,TOTAL_AMOUNT,BMI_PAY_AMOUNT ");
	        sql.append(" FROM T_FLYCHECK_MEDICAL_MZ where 1=1 ");
	        String social_card_id = dto.getSocial_card_id();// 就诊途径
	        //拼接条件
	        if (!StringUtils.isNullOrEmpty(social_card_id) && social_card_id != null && social_card_id != "") {
	            sql.append(" and  social_card_id = '"+social_card_id+"' ");
	        }
	        sql.append(" order by BILL_DATE desc ");
	        @SuppressWarnings("unused")
	        List<AbnormalFrequencyTreatment> abnormalFrequencyTreatments = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
	        		AbnormalFrequencyTreatment.class, sqlParamMap);
	        return abnormalFrequencyTreatments;
	    }

	    /**
	     * 方法名:getFrequencyTreatmentDetails
	     * 方法功能描述:就诊次数右边 方法
	     * @param:@return
	     * @return:PageModel
	     * @Author:贺和平
	     * @Create Date:2019年12月24日
	     */
	    public PageModel getFrequencyTreatmentDetails(AbnormalFrequencyTreatmentDto dto){
	        StringBuilder sql = new StringBuilder();
	        sql.append(" SELECT  HISID,HOSPITAL_NAME,BILL_DATE,PATIENT_ID,ADMISSION_DEPT_NAME, ");
	        sql.append(" CLAIM_TYPE,ADMISSION_DISEASE_ID,ADMISSION_DISEASE_NAME,TOTAL_AMOUNT,BMI_PAY_AMOUNT ");
	        sql.append(" from T_FLYCHECK_MEDICAL_MZ  where 1=1 ");
	        
	        
	        String route = dto.getRoute();// 就诊途径
	        String hospitalCode = dto.getHospitalCode();// 医院编码
	        String hospitalName = dto.getHospitalName();// 医院名称
	        String settlementTime = dto.getSettlementTime();// 结算时间后
	        String departmentName = dto.getDepartmentName();// 科室名称
	        String count=dto.getCount(); //诊断次数
	        String day= dto.getDay();//维度
	        String insurance = dto.getInsurance();//门诊类型	        
	        String social_card_id = dto.getSocial_card_id();// 就诊途径
	        //拼接条件
	        if (!StringUtils.isNullOrEmpty(social_card_id) && social_card_id != null && social_card_id != "") {
	            sql.append(" and  SOCIAL_CARD_ID = '"+social_card_id+"' ");
	        }
	        
	        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
	            sql.append(" and BENEFIT_TYPE ='"+route+"'  ");
	        }
	        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
	            sql.append(" and HOSPITAL_ID in("+hospitalCode+") ");
	        }
	        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
	            sql.append(" and hospital_name='"+hospitalName+"' ");
	        }else {	        	
	        	sql.append(" and hospital_name is not null ");
	        }*/
	        if (!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != "") {
	        	String[] time = settlementTime.split(" ");
				String startTime = time[0];
				String endTime = time[2];
				
				sql.append(" and BILL_DATE between to_date('" + startTime + "', 'yyyyMMdd') and to_date('" + endTime
						+ "', 'yyyyMMdd')");
	        }
	        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
	            sql.append(" and ADMISSION_DEPT_NAME like '%"+departmentName+"%' ");
	        }
	        if(!StringUtils.isNullOrEmpty(insurance) && insurance != null && insurance != "") {
	        	sql.append(" and CLAIM_TYPE ='"+insurance+"'");
	        }
	        sql.append(" order by BILL_DATE desc ");
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
	     * 方法名:getBenefitType
	     * 方法功能描述:医保类型
	     * @param:@return
	     * @return:PageModel
	     * @Author:贺和平
	     * @Create Date:2020年1月3日
	     */

	    public List<Map<String, Object>> getBenefitType(){
			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT BENEFIT_TYPE FROM T_FLYCHECK_MEDICAL_MZ GROUP BY BENEFIT_TYPE  ");
			System.out.println(sql);
			return jdbcTemplate.queryForList(sql.toString());
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

package com.dhcc.piccbid.dao.decompositionHospital;


import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>标题: DecompositionHospitalDao.java</p>
 * <p>业务描述:分解住院DAO层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年11月23日
 * @version V1.0
 */
@Component
public class DecompositionHospitalDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    /**
     * 方法名:getTotalNumberOfCasesAndTotalAmount
     * 方法功能描述:分解住院 计算总病例数和总金额方法
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(t.hisid) totalNumberOfCases ,sum(sum(t.total_amount)) sumTotalAmount  from ( ");
        sql.append(" select t.*,(t.admission_date - t.Last_discharge_date) decomposed_hospital_stay  from ( ");
        sql.append(" select t.hisid ,t.admission_date admission_date,t.discharge_date  discharge_date,t.bill_date, ");
        sql.append(" t.TOTAL_AMOUNT,t.discharge_dept_name,t.hospital_name,t.HOSPITAL_ID,t.BENEFIT_TYPE, ");
        sql.append(" lag(t.discharge_date, 1, null) over(partition by t.social_card_id,t.hospital_id order by t.admission_date) Last_discharge_date ");
        sql.append(" from T_FLYCHECK_MEDICAL t  ");
        sql.append(" order by t.social_card_id,t.admission_date asc ");
        sql.append(" ) t where t.Last_discharge_date is not NULL) t where 1=1 ");
        //拼接条件
        String route = dto.getRoute();// 就诊途径
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementTimea = dto.getSettlementTimea();// 结算时间前
        String settlementTimeb = dto.getSettlementTimeb();// 结算时间后
        String departmentName = dto.getDepartmentName();// 科室名称
        String decomposedHospitalStay=dto.getDecomposedHospitalStay(); //分解住院天数
        String day= dto.getDay();//维度
        String insurance = dto.getInsurance();//险种类型
        if (day.equals("dayu")) {
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay > "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay > 15  ");
            }
        }else if(day.equals("xiaoyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay < "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay < 15  ");
            }
        }else if(day.equals("dengyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay = "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay = 15  ");
            }
        }
        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
            sql.append(" and t.BENEFIT_TYPE ='"+route+"'  ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.HOSPITAL_ID in ("+hospitalCode+") ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name='"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementTimea) && settlementTimea != null && settlementTimea != "") {
            if (!StringUtils.isNullOrEmpty(settlementTimeb) && settlementTimeb != null && settlementTimeb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementTimea+"' and '"+settlementTimeb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
            sql.append(" and t.DISCHARGE_DEPT_NAME like '%"+departmentName+"%' ");
        }
        sql.append(" group by t.total_amount,t.hisid ");
        System.out.println("总金额sql："+sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
        return list;
    }


    /**
     * 方法名:getInsuredDataForm
     * 方法功能描述:参保人信息展示查询方法
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年11月25日
     */
    public PageModel getInsuredDataForm(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
        String route = dto.getRoute();// 就诊途径
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementTimea = dto.getSettlementTimea();// 结算时间前
        String settlementTimeb = dto.getSettlementTimeb();// 结算时间后
        String departmentName = dto.getDepartmentName();// 科室名称
        String decomposedHospitalStay=dto.getDecomposedHospitalStay(); //分解住院天数
        String day= dto.getDay();//维度
        String insurance = dto.getInsurance();//险种类型
        sql.append(" select  t.bmi_area_name,t.patient_id,t.patient_name,tl.decomposed_hospitalizations,t.zyh from ( ");
        sql.append(" select t.*,(t.admission_date - t.Last_discharge_date) decomposed_hospital_stay  from ( ");
        sql.append(" select t.hisid,t.zyh,t.social_card_id social_card_id,t.admission_date admission_date, ");
        sql.append(" t.discharge_date  discharge_date,t.zyts,t.bill_date,t.DISCHARGE_DISEASE_ID_MAIN, ");
        sql.append(" t.DISCHARGE_DISEASE_NAME_MAIN,t.TOTAL_AMOUNT,t.BMI_PAY_AMOUNT,t.discharge_dept_name,t.hospital_name,t.HOSPITAL_ID,t.BENEFIT_TYPE, ");
        sql.append(" t.patient_name,t.bmi_area_name,t.patient_id,t.BENEFIT_GROUP_ID, ");
        sql.append(" lag(t.discharge_date, 1, null) over(partition by t.social_card_id,t.hospital_id order by t.admission_date) Last_discharge_date ");
        sql.append(" from T_FLYCHECK_MEDICAL t order by t.social_card_id,t.admission_date asc ");
        sql.append(" ) t where t.Last_discharge_date is not NULL) t, ");
        sql.append(" (select count(t.social_card_id) as decomposed_hospitalizations, t.social_card_id from ( ");
        sql.append(" select t.*,(t.admission_date - t.Last_discharge_date) decomposed_hospital_stay  from ( ");
        sql.append(" select t.hisid ,t.admission_date admission_date,t.discharge_date  discharge_date,t.bill_date,t.social_card_id, ");
        sql.append(" t.TOTAL_AMOUNT,t.discharge_dept_name,t.hospital_name,t.HOSPITAL_ID,t.BENEFIT_TYPE,t.zyh,t.patient_name,t.BENEFIT_GROUP_ID, ");
        sql.append(" lag(t.discharge_date, 1, null) over(partition by t.social_card_id,t.hospital_id order by t.admission_date) Last_discharge_date  ");
        sql.append(" from T_FLYCHECK_MEDICAL t  ");
        sql.append(" order by t.social_card_id,t.admission_date asc ");
        sql.append(" ) t where t.Last_discharge_date is not NULL) t where 1=1 ");
        //拼接条件
        //住院天数 大于，小于，等于，默认15天
        if (day.equals("dayu")) {
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay > "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay > 15  ");
            }
        }else if(day.equals("xiaoyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay < "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay < 15  ");
            }
        }else if(day.equals("dengyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay = "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay = 15  ");
            }
        }
        //就诊类型
        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
            sql.append(" and t.BENEFIT_TYPE ='"+route+"'  ");
        }
        //医院编码
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.HOSPITAL_ID in ("+hospitalCode+") ");
        }
        //医院名称
       /* if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name='"+hospitalName+"' ");
        }*/
        //结算时间范围
        if (!StringUtils.isNullOrEmpty(settlementTimea) && settlementTimea != null && settlementTimea != "") {
            if (!StringUtils.isNullOrEmpty(settlementTimeb) && settlementTimeb != null && settlementTimeb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementTimea+"' and '"+settlementTimeb+"' ");
            }
        }
        //科室名称
        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
            sql.append(" and t.DISCHARGE_DEPT_NAME like '%"+departmentName+"%' ");
        }
        sql.append(" group by t.social_card_id  ");
        sql.append("  ) tl where 1=1 and tl.social_card_id=t.social_card_id ");
        //拼接条件
        //住院天数 大于，小于，等于，默认15天
        if (day.equals("dayu")) {
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay > "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay > 15  ");
            }
        }else if(day.equals("xiaoyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay < "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay < 15  ");
            }
        }else if(day.equals("dengyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay = "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay = 15  ");
            }
        }
        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
            sql.append(" and t.BENEFIT_TYPE ='"+route+"'  ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.HOSPITAL_ID in("+hospitalCode+") ");
        }
       /* if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name='"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementTimea) && settlementTimea != null && settlementTimea != "") {
            if (!StringUtils.isNullOrEmpty(settlementTimeb) && settlementTimeb != null && settlementTimeb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementTimea+"' and '"+settlementTimeb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
            sql.append(" and t.DISCHARGE_DEPT_NAME like '%"+departmentName+"%' ");
        }
        //根据zyh 去计算总数 为了分页做准备
        int n =getResultCountWithValuesMap(sql.toString(),"zyh", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("参保人信息展示sql："+sql);
        return dto.getPageModel();
    }


    /**
     * 方法名:getDetailsHospitalTable
     * 方法功能描述:病例明细详情查询,根据病人的住院号查询住院明细结算表
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月24日
     */
    public PageModel getDetailsHospitalTable(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
        String zyh = dto.getZyh();//住院号
        sql.append(" select t.hisid,t.zyh,t.hospital_id,t.hospital_name,t.discharge_dept_id,t.discharge_dept_name, ");
        sql.append(" t.discharge_disease_name_main,t.p_category,t.usage_date,t.bill_date,t.item_id_hosp,t.item_name_hosp, ");
        sql.append(" t.item_id,t.item_name,t.unit_price,t.num,t.cost,t.bmi_convered_amount,t.bmi_pay_amount,t.p_type_pct ");
        sql.append(" from T_FLYCHECK_MEDICAL_DETAIL t where 1=1 ");
        //拼接条件
        if (!StringUtils.isNullOrEmpty(zyh) && zyh != null && zyh != "") {
            sql.append(" and t.zyh ='"+zyh+"'  ");
        }
        //根据hisid 去计算总数 为了分页做准备
        int n =getResultCountWithValuesMap(sql.toString(),"hisid", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("病例详情展示sql："+sql);
        return dto.getPageModel();
    }


    /**
     * 方法名:getDecompositionHospital
     * 方法功能描述:分解住院 方法
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年11月23日
     */
    public PageModel getDecompositionHospital(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
//        sql.append(" select a.hisid,a.zyh, a.social_card_id social_card_id,a.admission_date admission_date, ");
//        sql.append(" a.discharge_date discharge_date,a.zyts,a.bill_date,a.DISCHARGE_DISEASE_ID_MAIN, ");
//        sql.append(" a.DISCHARGE_DISEASE_NAME_MAIN, a.TOTAL_AMOUNT,a.BMI_PAY_AMOUNT, a.discharge_dept_name, ");
//        sql.append(" a.hospital_name,a.HOSPITAL_ID, a.BENEFIT_TYPE,a.Last_discharge_date ");
        sql.append(" select t.* from ( ");
        sql.append(" select t.*,(t.admission_date - t.Last_discharge_date) decomposed_hospital_stay  from ( ");
        sql.append(" select t.hisid,t.zyh,t.social_card_id social_card_id,t.admission_date admission_date, ");
        sql.append(" t.discharge_date  discharge_date,t.zyts,t.bill_date,t.DISCHARGE_DISEASE_ID_MAIN, ");
        sql.append(" t.DISCHARGE_DISEASE_NAME_MAIN,t.TOTAL_AMOUNT,t.BMI_PAY_AMOUNT,t.discharge_dept_name,t.hospital_name,t.HOSPITAL_ID,t.BENEFIT_TYPE, ");
        sql.append(" lag(t.discharge_date, 1, null) over(partition by t.social_card_id,t.hospital_id order by t.admission_date) Last_discharge_date ");
        sql.append(" from T_FLYCHECK_MEDICAL t  ");
        sql.append(" order by t.social_card_id,t.admission_date asc ");
        sql.append(" ) t where t.Last_discharge_date is not NULL) t where 1=1 ");
        //拼接条件
        String route = dto.getRoute();// 就诊途径
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementTimea = dto.getSettlementTimea();// 结算时间前
        String settlementTimeb = dto.getSettlementTimeb();// 结算时间后
        String departmentName = dto.getDepartmentName();// 科室名称
        String decomposedHospitalStay=dto.getDecomposedHospitalStay(); //分解住院天数
        String day= dto.getDay();//维度
        String insurance = dto.getInsurance();//险种类型
        //住院天数 大于，小于，等于，默认15天
        if (day.equals("dayu")) {
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay > "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay > 15  ");
            }
        }else if(day.equals("xiaoyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay < "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay < 15  ");
            }
        }else if(day.equals("dengyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay = "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay = 15  ");
            }
        }
        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
            sql.append(" and t.BENEFIT_TYPE ='"+route+"'  ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.HOSPITAL_ID in("+hospitalCode+") ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name='"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementTimea) && settlementTimea != null && settlementTimea != "") {
            if (!StringUtils.isNullOrEmpty(settlementTimeb) && settlementTimeb != null && settlementTimeb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementTimea+"' and '"+settlementTimeb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
            sql.append(" and t.DISCHARGE_DEPT_NAME like '%"+departmentName+"%' ");
        }
        //根据t.HISID 去计算总数 为了分页做准备
        int n =getResultCountWithValuesMap(sql.toString(),"HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("分解sql打印:"+sql);
        return dto.getPageModel();
    }


    /**
     * 方法名:exportD
     * 方法功能描述:数据导出查询方法
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月25日
     */
    public List<DecompositionHospital> exportD(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
        Map<String, Object> sqlParamMap = new HashMap<String, Object>();
//        sql.append(" select a.hisid,a.zyh, a.social_card_id social_card_id,a.admission_date admission_date, ");
//        sql.append(" a.discharge_date discharge_date,a.zyts,a.bill_date,a.DISCHARGE_DISEASE_ID_MAIN, ");
//        sql.append(" a.DISCHARGE_DISEASE_NAME_MAIN, a.TOTAL_AMOUNT,a.BMI_PAY_AMOUNT, a.discharge_dept_name, ");
//        sql.append(" a.hospital_name,a.HOSPITAL_ID, a.BENEFIT_TYPE,a.Last_discharge_date ");
        sql.append(" select t.* from ( ");
        sql.append(" select t.*,(t.admission_date - t.Last_discharge_date) decomposed_hospital_stay  from ( ");
        sql.append(" select t.hisid,t.zyh,t.social_card_id social_card_id,t.admission_date admission_date, ");
        sql.append(" t.discharge_date  discharge_date,t.zyts,t.bill_date,t.DISCHARGE_DISEASE_ID_MAIN, ");
        sql.append(" t.DISCHARGE_DISEASE_NAME_MAIN,t.TOTAL_AMOUNT,t.BMI_PAY_AMOUNT,t.discharge_dept_name,t.hospital_name,t.HOSPITAL_ID,t.BENEFIT_TYPE, ");
        sql.append(" lag(t.discharge_date, 1, null) over(partition by t.social_card_id,t.hospital_id order by t.admission_date) Last_discharge_date ");
        sql.append(" from T_FLYCHECK_MEDICAL t  ");
        sql.append(" order by t.social_card_id,t.admission_date asc ");
        sql.append(" ) t where t.Last_discharge_date is not NULL) t where 1=1 ");
        //拼接条件
        String route = dto.getRoute();// 就诊途径
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementTimea = dto.getSettlementTimea();// 结算时间前
        String settlementTimeb = dto.getSettlementTimeb();// 结算时间后
        String departmentName = dto.getDepartmentName();// 科室名称
        String decomposedHospitalStay=dto.getDecomposedHospitalStay(); //分解住院天数
        String day= dto.getDay();//维度
        String insurance = dto.getInsurance();//险种类型
        //住院天数 大于，小于，等于，默认15天
        if (day.equals("dayu")) {
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay > "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay > 15  ");
            }
        }else if(day.equals("xiaoyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay < "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay < 15  ");
            }
        }else if(day.equals("dengyu")){
            if (!StringUtils.isNullOrEmpty(decomposedHospitalStay) && decomposedHospitalStay != null && decomposedHospitalStay != "") {
                sql.append(" and  t.decomposed_hospital_stay = "+decomposedHospitalStay+"  ");
            }else{
                sql.append(" and  t.decomposed_hospital_stay = 15  ");
            }
        }
        if (!StringUtils.isNullOrEmpty(route) && route != null && route != "") {
            sql.append(" and t.BENEFIT_TYPE ='"+route+"'  ");
        }
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.HOSPITAL_ID in("+hospitalCode+") ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name='"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementTimea) && settlementTimea != null && settlementTimea != "") {
            if (!StringUtils.isNullOrEmpty(settlementTimeb) && settlementTimeb != null && settlementTimeb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementTimea+"' and '"+settlementTimeb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(departmentName) && departmentName != null && departmentName != "") {
            sql.append(" and t.DISCHARGE_DEPT_NAME like '%"+departmentName+"%' ");
        }
        @SuppressWarnings("unused")
        List<DecompositionHospital> decompositionHospital = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
                DecompositionHospital.class, sqlParamMap);
        return decompositionHospital;
    }


    /**
     * 方法名:exportA
     * 方法功能描述:数据导出查询方法
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月25日
     */
    public List<DecompositionHospital> exportA(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
        Map<String, Object> sqlParamMap = new HashMap<String, Object>();
        sql.append(" select t.hisid,t.zyh,t.admission_date admission_date,t.discharge_date discharge_date,t.zyts, ");
        sql.append(" t.bill_date,t.DISCHARGE_DISEASE_ID_MAIN,t.DISCHARGE_DISEASE_NAME_MAIN,t.TOTAL_AMOUNT, ");
        sql.append(" t.BMI_PAY_AMOUNT ");
        sql.append(" from T_FLYCHECK_MEDICAL t where 1=1 ");
        String social_card_id = dto.getSocial_card_id();// 就诊途径
        //拼接条件
        if (!StringUtils.isNullOrEmpty(social_card_id) && social_card_id != null && social_card_id != "") {
            sql.append(" and  t.social_card_id = '"+social_card_id+"' ");
        }
        sql.append(" order by t.admission_date ");
        @SuppressWarnings("unused")
        List<DecompositionHospital> decompositionHospital = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
                DecompositionHospital.class, sqlParamMap);
        return decompositionHospital;
    }

    /**
     * 方法名:getDecompositionHospitalDetails
     * 方法功能描述:分解住院 方法
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2019年12月24日
     */
    public PageModel getDecompositionHospitalDetails(DecompositionHospitalDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.hisid,t.zyh,t.admission_date admission_date,t.discharge_date discharge_date,t.zyts, ");
        sql.append(" t.bill_date,t.DISCHARGE_DISEASE_ID_MAIN,t.DISCHARGE_DISEASE_NAME_MAIN,t.TOTAL_AMOUNT, ");
        sql.append(" t.BMI_PAY_AMOUNT ");
        sql.append(" from T_FLYCHECK_MEDICAL t where 1=1 ");
        String social_card_id = dto.getSocial_card_id();// 就诊途径
        //拼接条件
        if (!StringUtils.isNullOrEmpty(social_card_id) && social_card_id != null && social_card_id != "") {
            sql.append(" and  t.social_card_id = '"+social_card_id+"' ");
        }
        sql.append(" order by t.admission_date ");
        //根据t.HISID 去计算总数 为了分页做准备
        int n =getResultCountWithValuesMap(sql.toString(),"HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("查询明细sql:"+sql);
        return dto.getPageModel();
    }

    /**
     * 方法名：toPageModelSql
     *方法功能描述：将sql转换为分页sql
     *@param:@sql 初始sql，pageNo  当前页数，pageSize  当前页面个数
     *@return:String
     *@Atuhor：王彤
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

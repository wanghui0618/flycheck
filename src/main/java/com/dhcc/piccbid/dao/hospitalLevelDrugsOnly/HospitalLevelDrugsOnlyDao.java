package com.dhcc.piccbid.dao.hospitalLevelDrugsOnly;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.dto.hospitalLevelDrugsOnlyDto.HospitalLevelDrugsOnlyDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;
import com.dhcc.piccbid.entity.hospitalLevelDrugsOnly.HospitalLevelDrugsOnly;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>标题: HospitalLevelDrugsOnlyDao.java</p>
 * <p>业务描述:限医院等级用药DAO层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2020</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
@Component
public class HospitalLevelDrugsOnlyDao {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    /**
     * 方法名:getCountAndTotle
     * 方法功能描述:计算住院总病例数和总金额
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public List<Map<String, Object>> getCountAndTotle(HospitalLevelDrugsOnlyDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(t.hisid) TOTALNUMBEROFCASES,sum(t.cost) SUMTOTALAMOUNT ");
        sql.append(" from T_FLYCHECK_MEDICAL_DETAIL t,T_FLYCHECK_MEDICAL d ");
        sql.append(" where t.hisid=d.hisid ");
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementDatea = dto.getSettlementDatea();// 结算时间a
        String settlementDateb = dto.getSettlementDateb();// 结算时间b
        String diagnosticCode = dto.getDiagnosticCode();// 诊断编码
        String diagnosticName = dto.getDiagnosticName();// 诊断名称
        String admissionDatea = dto.getAdmissionDatea();// 入院日期a
        String admissionDateb = dto.getAdmissionDateb();// 入院日期b
        String dischargeDatea = dto.getDischargeDatea();// 出院日期a
        String dischargeDateb = dto.getDischargeDateb();// 出院日期b
        String itemId = dto.getItemId();// 医保项目编码
        String itemName = dto.getItemName();// 医保项目名称
        if(!"".equals(hospitalCode)&&hospitalCode!=null) {
        String[] split = hospitalCode.split(",|，");
        hospitalCode="";
        for(int i = 0 ; i <split.length ; i++) {
        	hospitalCode=hospitalCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 hospitalCode=hospitalCode+",";
			 }
		}
        }
        if(!"".equals(itemId)&&itemId!=null) {
        String[] split1 = itemId.split(",|，");
        itemId="";
        for(int i = 0 ; i <split1.length ; i++) {
        	itemId=itemId+"'"+split1[i]+"'";
			 if(i<split1.length-1) {
				 itemId=itemId+",";
			 }
		}
        }
        if(!"".equals(itemName)&&itemName!=null) {
        String[] split2 = itemName.split(",|，");
        itemName="";
        for(int i = 0 ; i <split2.length ; i++) {
        	itemName=itemName+"'"+split2[i]+"'";
			 if(i<split2.length-1) {
				 itemName=itemName+",";
			 }
		}
        }
        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.hospital_id in("+hospitalCode+") ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name= '"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementDatea) && settlementDatea != null && settlementDatea != "") {
            if (!StringUtils.isNullOrEmpty(settlementDateb) && settlementDateb != null && settlementDateb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementDatea+"' and '"+settlementDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(diagnosticCode) && diagnosticCode != null && diagnosticCode != "") {
            sql.append(" and d.discharge_disease_id_main like '%"+diagnosticCode+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(diagnosticName) && diagnosticName != null && diagnosticName != "") {
            sql.append(" and d.discharge_disease_name_main like '%"+diagnosticName+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(admissionDatea) && admissionDatea != null && admissionDatea != "") {
            if (!StringUtils.isNullOrEmpty(admissionDateb) && admissionDateb != null && admissionDateb != "") {
                sql.append(" and to_char(d.admission_date,'yyyymmdd') between '"+admissionDatea+"' and '"+admissionDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(dischargeDatea) && dischargeDatea != null && dischargeDatea != "") {
            if (!StringUtils.isNullOrEmpty(dischargeDateb) && dischargeDateb != null && dischargeDateb != "") {
                sql.append(" and to_char(d.discharge_date,'yyyymmdd') between '"+dischargeDatea+"' and '"+dischargeDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(itemId) && itemId != null && itemId != "") {
            sql.append(" and (t.item_id in("+itemId+") ");
        }
        if (!StringUtils.isNullOrEmpty(itemName) && itemName != null && itemName != "") {
            sql.append(" or t.item_id in("+itemName+")) ");
        }

        System.out.println("住院"+sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
        return list;
    }

    /**
     * 方法名:getHospitalLevelDrugsOnly
     * 方法功能描述:限医院等级用药 住院
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public PageModel getHospitalLevelDrugsOnly(HospitalLevelDrugsOnlyDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.hisid, t.hospital_id,t.hospital_name,t.zyh,d.discharge_dept_name,d.patient_name, ");
        sql.append(" d.admission_date,d.discharge_date,d.zyts,d.total_amount,d.benefit_type,t.item_id,t.item_name, ");
        sql.append(" t.item_id_hosp,t.item_name_hosp,d.western_medicine_fee,d.chinese_medicine_yinpian,d.chinese_medicine_form ");
        sql.append(" from T_FLYCHECK_MEDICAL_DETAIL t,T_FLYCHECK_MEDICAL d ");
        sql.append(" where t.hisid=d.hisid ");
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementDatea = dto.getSettlementDatea();// 结算时间a
        String settlementDateb = dto.getSettlementDateb();// 结算时间b
        String diagnosticCode = dto.getDiagnosticCode();// 诊断编码
        String diagnosticName = dto.getDiagnosticName();// 诊断名称
        String admissionDatea = dto.getAdmissionDatea();// 入院日期a
        String admissionDateb = dto.getAdmissionDateb();// 入院日期b
        String dischargeDatea = dto.getDischargeDatea();// 出院日期a
        String dischargeDateb = dto.getDischargeDateb();// 出院日期b
        String itemId = dto.getItemId();// 医保项目编码
        String itemName = dto.getItemName();// 医保项目名称
        if(!"".equals(hospitalCode)&&hospitalCode!=null) {
        String[] split = hospitalCode.split(",|，");
        hospitalCode="";
        for(int i = 0 ; i <split.length ; i++) {
        	hospitalCode=hospitalCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 hospitalCode=hospitalCode+",";
			 }
		}
        }
        if(!"".equals(itemId)&&itemId!=null) {
        String[] split1 = itemId.split(",|，");
        itemId="";
        for(int i = 0 ; i <split1.length ; i++) {
        	itemId=itemId+"'"+split1[i]+"'";
			 if(i<split1.length-1) {
				 itemId=itemId+",";
			 }
		}
        }
        if(!"".equals(itemName)&&itemName!=null) {
        String[] split2 = itemName.split(",|，");
        itemName="";
        for(int i = 0 ; i <split2.length ; i++) {
        	itemName=itemName+"'"+split2[i]+"'";
			 if(i<split2.length-1) {
				 itemName=itemName+",";
			 }
		}
        }
        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.hospital_id in("+hospitalCode+")  ");
        }
       /* if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name= '"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementDatea) && settlementDatea != null && settlementDatea != "") {
            if (!StringUtils.isNullOrEmpty(settlementDateb) && settlementDateb != null && settlementDateb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementDatea+"' and '"+settlementDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(diagnosticCode) && diagnosticCode != null && diagnosticCode != "") {
            sql.append(" and d.discharge_disease_id_main like '%"+diagnosticCode+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(diagnosticName) && diagnosticName != null && diagnosticName != "") {
            sql.append(" and d.discharge_disease_name_main like '%"+diagnosticName+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(admissionDatea) && admissionDatea != null && admissionDatea != "") {
            if (!StringUtils.isNullOrEmpty(admissionDateb) && admissionDateb != null && admissionDateb != "") {
                sql.append(" and to_char(d.admission_date,'yyyymmdd') between '"+admissionDatea+"' and '"+admissionDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(dischargeDatea) && dischargeDatea != null && dischargeDatea != "") {
            if (!StringUtils.isNullOrEmpty(dischargeDateb) && dischargeDateb != null && dischargeDateb != "") {
                sql.append(" and to_char(d.discharge_date,'yyyymmdd') between '"+dischargeDatea+"' and '"+dischargeDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(itemId) && itemId != null && itemId != "") {
            sql.append(" and (t.item_id in("+itemId+") ");
        }
        if (!StringUtils.isNullOrEmpty(itemName) && itemName != null && itemName != "") {
            sql.append(" or t.item_id in("+itemName+")) ");
        }


        //根据t.HISID 去计算总数 为了分页做准备
        int n =jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"t.HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("限医院等级用药sql:"+sql);
        return dto.getPageModel();
    }

    /**
     * 方法名:getCountAndTotle
     * 方法功能描述:计算门诊总病例数和总金额
     * @param:@return
     * @return:List<Map<String, Object>>
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public List<Map<String, Object>> getOutpatientCountAndTotle(HospitalLevelDrugsOnlyDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select COUNT(t.HISID) TOTALNUMBEROFCASES,SUM(d.cost) SUMTOTALAMOUNT ");
        sql.append(" from T_FLYCHECK_MEDICAL_MZ t, T_FLYCHECK_MEDICAL_MZ_DETAIL d ");
        sql.append(" WHERE t.hisid = d.hisid ");
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementDatea = dto.getSettlementDatea();// 结算时间a
        String settlementDateb = dto.getSettlementDateb();// 结算时间b
        String diagnosticCode = dto.getDiagnosticCode();// 诊断编码
        String diagnosticName = dto.getDiagnosticName();// 诊断名称
        String itemId = dto.getItemId();// 医保项目编码
        String itemName = dto.getItemName();// 医保项目名称
        if(!"".equals(hospitalCode)&&hospitalCode!=null) {
        String[] split = hospitalCode.split(",|，");
        hospitalCode="";
        for(int i = 0 ; i <split.length ; i++) {
        	hospitalCode=hospitalCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 hospitalCode=hospitalCode+",";
			 }
		}
        }
        if(!"".equals(itemId)&&itemId!=null) {
        String[] split1 = itemId.split(",|，");
        itemId="";
        for(int i = 0 ; i <split1.length ; i++) {
        	itemId=itemId+"'"+split1[i]+"'";
			 if(i<split1.length-1) {
				 itemId=itemId+",";
			 }
		}
        }
        if(!"".equals(itemName)&&itemName!=null) {
        String[] split2 = itemName.split(",|，");
        itemName="";
        for(int i = 0 ; i <split2.length ; i++) {
        	itemName=itemName+"'"+split2[i]+"'";
			 if(i<split2.length-1) {
				 itemName=itemName+",";
			 }
		}
        }
        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.hospital_id in("+hospitalCode+")  ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name= '"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementDatea) && settlementDatea != null && settlementDatea != "") {
            if (!StringUtils.isNullOrEmpty(settlementDateb) && settlementDateb != null && settlementDateb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementDatea+"' and '"+settlementDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(diagnosticCode) && diagnosticCode != null && diagnosticCode != "") {
            sql.append(" and t.admission_disease_id like '%"+diagnosticCode+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(diagnosticName) && diagnosticName != null && diagnosticName != "") {
            sql.append(" and t.admission_disease_name like '%"+diagnosticName+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(itemId) && itemId != null && itemId != "") {
            sql.append(" and (d.item_id in("+itemId+") ");
        }
        if (!StringUtils.isNullOrEmpty(itemName) && itemName != null && itemName != "") {
            sql.append(" or d.item_name in"+itemName+")) ");
        }

        System.out.println("门诊"+sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
        return list;
    }

    /**
     * 方法名:getOutpatientHospitalLevelDrugsOnly
     * 方法功能描述:限医院等级用药 门诊
     * @param:@return
     * @return:PageModel
     * @Author:王彤
     * @Create Date:2020年1月2日
     */
    public PageModel getOutpatientHospitalLevelDrugsOnly(HospitalLevelDrugsOnlyDto dto){
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.hisid, t.hospital_id,t.hospital_name,d.discharge_dept_name,t.patient_name, ");
        sql.append(" t.total_amount,t.benefit_type,d.item_id,d.item_name,d.item_id_hosp,d.item_name_hosp, ");
        sql.append(" t.western_medicine_fee,t.chinese_medicine_yinpian,t.chinese_medicine_form  ");
        sql.append(" from T_FLYCHECK_MEDICAL_MZ t,T_FLYCHECK_MEDICAL_MZ_DETAIL d  ");
        sql.append(" WHERE t.hisid=d.hisid ");
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementDatea = dto.getSettlementDatea();// 结算时间a
        String settlementDateb = dto.getSettlementDateb();// 结算时间b
        String diagnosticCode = dto.getDiagnosticCode();// 诊断编码
        String diagnosticName = dto.getDiagnosticName();// 诊断名称
        String itemId = dto.getItemId();// 医保项目编码
        String itemName = dto.getItemName();// 医保项目名称
        if(!"".equals(hospitalCode)&&hospitalCode!=null) {
        String[] split = hospitalCode.split(",|，");
        hospitalCode="";
        for(int i = 0 ; i <split.length ; i++) {
        	hospitalCode=hospitalCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 hospitalCode=hospitalCode+",";
			 }
		}
        }
        if(!"".equals(itemId)&&itemId!=null) {
        String[] split1 = itemId.split(",|，");
        itemId="";
        for(int i = 0 ; i <split1.length ; i++) {
        	itemId=itemId+"'"+split1[i]+"'";
			 if(i<split1.length-1) {
				 itemId=itemId+",";
			 }
		}
        }
        if(!"".equals(itemName)&&itemName!=null) {
        String[] split2 = itemName.split(",|，");
        itemName="";
        for(int i = 0 ; i <split2.length ; i++) {
        	itemName=itemName+"'"+split2[i]+"'";
			 if(i<split2.length-1) {
				 itemName=itemName+",";
			 }
		}
        }
        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.hospital_id in("+hospitalCode+")  ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name= '"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementDatea) && settlementDatea != null && settlementDatea != "") {
            if (!StringUtils.isNullOrEmpty(settlementDateb) && settlementDateb != null && settlementDateb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementDatea+"' and '"+settlementDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(diagnosticCode) && diagnosticCode != null && diagnosticCode != "") {
            sql.append(" and t.admission_disease_id like '%"+diagnosticCode+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(diagnosticName) && diagnosticName != null && diagnosticName != "") {
            sql.append(" and t.admission_disease_name like '%"+diagnosticName+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(itemId) && itemId != null && itemId != "") {
            sql.append(" and (d.item_id in("+itemId+") ");
        }
        if (!StringUtils.isNullOrEmpty(itemName) && itemName != null && itemName != "") {
            sql.append(" or d.item_name in"+itemName+")) ");
        }


        //根据t.HISID 去计算总数 为了分页做准备
        int n =jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"t.HISID", null);
        //调用分页sql 方法，转为分页sql，并且返回一个List<Map<String, Object>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
        //将list添加到dto的 PageData
        dto.getPageModel().setPageData(list);
        //将总数据量加入到dto的Totals
        dto.getPageModel().setTotals(n);
        System.out.println("限医院等级用药门诊sql:"+sql);
        return dto.getPageModel();
    }

    /**
     * 方法名:exportOutpatient
     * 方法功能描述:门诊数据导出查询方法
     * @param:@return
     * @return:List<HospitalLevelDrugsOnly>
     * @Author:王彤
     * @Create Date:2020年1月3日
     */
    public List<HospitalLevelDrugsOnly> exportOutpatient(HospitalLevelDrugsOnlyDto dto){
        Map<String, Object> sqlParamMap = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.hisid, t.hospital_id,t.hospital_name,d.discharge_dept_name,t.patient_name, ");
        sql.append(" t.total_amount,t.benefit_type,d.item_id,d.item_name,d.item_id_hosp,d.item_name_hosp, ");
        sql.append(" t.western_medicine_fee,t.chinese_medicine_yinpian,t.chinese_medicine_form  ");
        sql.append(" from T_FLYCHECK_MEDICAL_MZ t,T_FLYCHECK_MEDICAL_MZ_DETAIL d  ");
        sql.append(" WHERE t.hisid=d.hisid ");
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementDatea = dto.getSettlementDatea();// 结算时间a
        String settlementDateb = dto.getSettlementDateb();// 结算时间b
        String diagnosticCode = dto.getDiagnosticCode();// 诊断编码
        String diagnosticName = dto.getDiagnosticName();// 诊断名称
        String itemId = dto.getItemId();// 医保项目编码
        String itemName = dto.getItemName();// 医保项目名称
        if(!"".equals(hospitalCode)&&hospitalCode!=null) {
        String[] split = hospitalCode.split(",|，");
        hospitalCode="";
        for(int i = 0 ; i <split.length ; i++) {
        	hospitalCode=hospitalCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 hospitalCode=hospitalCode+",";
			 }
		}
        }
        if(!"".equals(itemId)&&itemId!=null) {
        String[] split1 = itemId.split(",|，");
        itemId="";
        for(int i = 0 ; i <split1.length ; i++) {
        	itemId=itemId+"'"+split1[i]+"'";
			 if(i<split1.length-1) {
				 itemId=itemId+",";
			 }
		}
        }
        if(!"".equals(itemName)&&itemName!=null) {
        String[] split2 = itemName.split(",|，");
        itemName="";
        for(int i = 0 ; i <split2.length ; i++) {
        	itemName=itemName+"'"+split2[i]+"'";
			 if(i<split2.length-1) {
				 itemName=itemName+",";
			 }
		}
        }
        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.hospital_id in("+hospitalCode+")  ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name= '"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementDatea) && settlementDatea != null && settlementDatea != "") {
            if (!StringUtils.isNullOrEmpty(settlementDateb) && settlementDateb != null && settlementDateb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementDatea+"' and '"+settlementDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(diagnosticCode) && diagnosticCode != null && diagnosticCode != "") {
            sql.append(" and t.admission_disease_id like '%"+diagnosticCode+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(diagnosticName) && diagnosticName != null && diagnosticName != "") {
            sql.append(" and t.admission_disease_name like '%"+diagnosticName+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(itemId) && itemId != null && itemId != "") {
            sql.append(" and (d.item_id in("+itemId+") ");
        }
        if (!StringUtils.isNullOrEmpty(itemName) && itemName != null && itemName != "") {
            sql.append(" or d.item_name in"+itemName+")) ");
        }

        @SuppressWarnings("unused")
        List<HospitalLevelDrugsOnly> hospitalLevelDrughsOnly = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
                HospitalLevelDrugsOnly.class, sqlParamMap);
        return hospitalLevelDrughsOnly;
    }

    /**
     * 方法名:exportBeHospitalized
     * 方法功能描述:住院数据导出查询方法
     * @param:@return
     * @return:List<HospitalLevelDrugsOnly>
     * @Author:王彤
     * @Create Date:2020年1月3日
     */
    public List<HospitalLevelDrugsOnly> exportBeHospitalized(HospitalLevelDrugsOnlyDto dto){
        Map<String, Object> sqlParamMap = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.hisid, t.hospital_id,t.hospital_name,t.zyh,d.discharge_dept_name,d.patient_name, ");
        sql.append(" d.admission_date,d.discharge_date,d.zyts,d.total_amount,d.benefit_type,t.item_id,t.item_name, ");
        sql.append(" t.item_id_hosp,t.item_name_hosp,d.western_medicine_fee,d.chinese_medicine_yinpian,d.chinese_medicine_form ");
        sql.append(" from T_FLYCHECK_MEDICAL_DETAIL t,T_FLYCHECK_MEDICAL d ");
        sql.append(" where t.hisid=d.hisid ");
        String hospitalCode = dto.getHospitalCode();// 医院编码
        String hospitalName = dto.getHospitalName();// 医院名称
        String settlementDatea = dto.getSettlementDatea();// 结算时间a
        String settlementDateb = dto.getSettlementDateb();// 结算时间b
        String diagnosticCode = dto.getDiagnosticCode();// 诊断编码
        String diagnosticName = dto.getDiagnosticName();// 诊断名称
        String admissionDatea = dto.getAdmissionDatea();// 入院日期a
        String admissionDateb = dto.getAdmissionDateb();// 入院日期b
        String dischargeDatea = dto.getDischargeDatea();// 出院日期a
        String dischargeDateb = dto.getDischargeDateb();// 出院日期b
        String itemId = dto.getItemId();// 医保项目编码
        String itemName = dto.getItemName();// 医保项目名称
        if(!"".equals(hospitalCode)&&hospitalCode!=null) {
        String[] split = hospitalCode.split(",|，");
        hospitalCode="";
        for(int i = 0 ; i <split.length ; i++) {
        	hospitalCode=hospitalCode+"'"+split[i]+"'";
			 if(i<split.length-1) {
				 hospitalCode=hospitalCode+",";
			 }
		}
        }
        if(!"".equals(itemId)&&itemId!=null) {
        String[] split1 = itemId.split(",|，");
        itemId="";
        for(int i = 0 ; i <split1.length ; i++) {
        	itemId=itemId+"'"+split1[i]+"'";
			 if(i<split1.length-1) {
				 itemId=itemId+",";
			 }
		}
        }
        if(!"".equals(itemName)&&itemName!=null) {
        String[] split2 = itemName.split(",|，");
        itemName="";
        for(int i = 0 ; i <split2.length ; i++) {
        	itemName=itemName+"'"+split2[i]+"'";
			 if(i<split2.length-1) {
				 itemName=itemName+",";
			 }
		}
        }
        //拼接条件
        if (!StringUtils.isNullOrEmpty(hospitalCode) && hospitalCode != null && hospitalCode != "") {
            sql.append(" and t.hospital_id in("+hospitalCode+")  ");
        }
        /*if (!StringUtils.isNullOrEmpty(hospitalName) && hospitalName != null && hospitalName != "") {
            sql.append(" and t.hospital_name= '"+hospitalName+"' ");
        }*/
        if (!StringUtils.isNullOrEmpty(settlementDatea) && settlementDatea != null && settlementDatea != "") {
            if (!StringUtils.isNullOrEmpty(settlementDateb) && settlementDateb != null && settlementDateb != "") {
                sql.append(" and to_char(t.bill_date,'yyyymmdd') between '"+settlementDatea+"' and '"+settlementDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(diagnosticCode) && diagnosticCode != null && diagnosticCode != "") {
            sql.append(" and d.discharge_disease_id_main like '%"+diagnosticCode+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(diagnosticName) && diagnosticName != null && diagnosticName != "") {
            sql.append(" and d.discharge_disease_name_main like '%"+diagnosticName+"%' ");
        }
        if (!StringUtils.isNullOrEmpty(admissionDatea) && admissionDatea != null && admissionDatea != "") {
            if (!StringUtils.isNullOrEmpty(admissionDateb) && admissionDateb != null && admissionDateb != "") {
                sql.append(" and to_char(d.admission_date,'yyyymmdd') between '"+admissionDatea+"' and '"+admissionDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(dischargeDatea) && dischargeDatea != null && dischargeDatea != "") {
            if (!StringUtils.isNullOrEmpty(dischargeDateb) && dischargeDateb != null && dischargeDateb != "") {
                sql.append(" and to_char(d.discharge_date,'yyyymmdd') between '"+dischargeDatea+"' and '"+dischargeDateb+"' ");
            }
        }
        if (!StringUtils.isNullOrEmpty(itemId) && itemId != null && itemId != "") {
            sql.append(" and (t.item_id in("+itemId+") ");
        }
        if (!StringUtils.isNullOrEmpty(itemName) && itemName != null && itemName != "") {
            sql.append(" or t.item_id in("+itemName+")) ");
        }

        @SuppressWarnings("unused")
        List<HospitalLevelDrugsOnly> hospitalLevelDrughsOnly = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),
                HospitalLevelDrugsOnly.class, sqlParamMap);
        return hospitalLevelDrughsOnly;
    }

    /**
     * 方法名：toPageModelSql
     *方法功能描述：将sql转换为分页sql
     *@param:@sql 初始sql，pageNo  当前页数，pageSize  当前页面个数
     *@return:String
     *@Atuhor：王彤
     *@Create Date : 2020-1-2
     */
    public static String toPageModelSql(String sql,int pageNo,int pageSize){
        sql = " select * from (select a.*,rownum as num1 from ( "+sql+") a where rownum <= "+pageNo*pageSize+")"
                + " where num1 > "+(pageNo-1)*pageSize;
        return sql;
    }

}

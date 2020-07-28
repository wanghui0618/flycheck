package com.dhcc.piccbid.dao.OverclockingAndAmountItem;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.OverclockingAndAmountItem.OverclockingItemDto;
import com.dhcc.piccbid.entity.OverclockingAndAmountItem.OverclockingItemVo;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.PersonalInformationInquiryVo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OverclockingItemJDBC  {
    @Autowired
    JdbcTemplateWrapper jdbcTemplateWrapper;
    private static String exportsql;
    public void searchList(OverclockingItemDto dto) {
        StringBuffer stringBuffer=new StringBuffer();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        if(dto.getOverclockingItemVo()!=null) {
            if (!dto.getOverclockingItemVo().getLimitBelong().isEmpty() && dto.getOverclockingItemVo().getLimitBelong().equals("住院")) {
                stringBuffer.append("select * \r\n" +
                        "   from (select  distinct  \r\n" +
                        "       a.bridge_id bridge_id,   a.hisid hisid, a.hospital_id hospital_id, a.hospital_name hospital_name, a.p_level p_level, a.bmi_area_id bmi_area_id,a.bmi_area_name bmi_area_name, a.bill_date bill_date, a.year year,  a.month month,   a.zyh zyh, a.patient_id patient_id,   a.social_card_id social_card_id,       a.medical_record_id medical_record_id, a.benefit_type benefit_type, a.benefit_group_id benefit_group_id,a.admission_dept_name admission_dept_name,a.transfer_dept_name transfer_dept_name, a.discharge_dept_name discharge_dept_name,  a.doctor_id doctor_id,a.doctor_name doctor_name,   a.patient_name patient_name, a.patient_gender patient_gender,  a.patient_birthday patient_birthday, a.patient_age patient_age,  a.patient_company patient_company, a.patient_address patient_address,   a.bedid bedid, a.nb_type nb_type,  a.nb_birth_weight nb_birth_weight, a.nb_inpatient_weight nb_inpatient_weight, a.claim_type claim_type,    a.if_local_flag if_local_flag,  a.admission_date admission_date,   a.discharge_date discharge_date,   a.zyts zyts,       a.discharge_status discharge_status, a.pre_admission_date pre_admission_date,  a.total_amount total_amount,   a.dbbx dbbx, a.yljz yljz,a.gwybz gwybz,  a.debc debc,  a.qybc qybc, a.cash cash, a.self_pay_amount self_pay_amount,       a.self_pay_in self_pay_in, a.self_pay_out self_pay_out, a.admission_disease_id admission_disease_id,   a.admission_disease_name admission_disease_name,  a.discharge_disease_id_main discharge_disease_id_main,    a.discharge_disease_name_main discharge_disease_name_main,  a.accommodation_fee accommodation_fee, a.diagnosis_fee diagnosis_fee, a.inspection_fee inspection_fee,   a.test_fee test_fee,   a.treatment_fee treatment_fee,  a.nursing_fee nursing_fee,  a.material_fee material_fee,    a.western_medicine_fee western_medicine_fee,   a.chinese_medicine_yinpian chinese_medicine_yinpian,       a.chinese_medicine_form chinese_medicine_form,  a.consultation_fee consultation_fee,       a.registration_fee registration_fee,  a.other_fee other_fee,    a.yb_pay_type yb_pay_type,   a.drgs_code drgs_code,   a.drgs_name drgs_name,  a.sys_status sys_status,   a.stay_times stay_times, b.detail_id detail_id,  b.discharge_dept_id discharge_dept_id,  b.p_category p_category,  b.usage_date usage_date, b.usage_date_flag usage_date_flag,   b.item_id_hosp item_id_hosp,   b.item_name_hosp item_name_hosp,   b.item_id item_id,   b.item_name item_name,    b.drug_spec drug_spec,  b.dosage_form dosage_form,  b.package_unit package_unit,  b.unit_price unit_price,  b.num num,   b.cost cost,  b.bmi_convered_amount bmi_convered_amount,   b.bmi_pay_amount bmi_pay_amount, b.p_type p_type,  b.p_type_pct p_type_pct, b.item_type item_type  , \r\n");

                stringBuffer.append(" sum(b.num) over(partition by b.hisid \r\n");

                if(!dto.getOverclockingItemVo().getLimitCountType().isEmpty()&&dto.getOverclockingItemVo().getLimitCountType().equals("分别计算")){
                    stringBuffer.append(",b.item_id \r\n");
                }
                if(!dto.getOverclockingItemVo().getLimitCountType().isEmpty()&&dto.getOverclockingItemVo().getLimitCountType().equals("次数累计")){
                    stringBuffer.append("");
                }
                if (!dto.getOverclockingItemVo().getLimitAppearType().isEmpty() && dto.getOverclockingItemVo().getLimitAppearType().equals("同天")) {
                    stringBuffer.append(",TO_CHAR(b.usage_date, 'YYYYMMDD'))  usage_count \r\n");
                }
                if (!dto.getOverclockingItemVo().getLimitAppearType().isEmpty() && dto.getOverclockingItemVo().getLimitAppearType().equals("同次")) {
                    stringBuffer.append(")  usage_count \r\n");
                }

                stringBuffer.append(" from  t_flycheck_medical a, t_flycheck_medical_detail b where a.hisid=b.hisid \r\n");
                if(!dto.getOverclockingItemVo().getHospitalId().isEmpty()&&!dto.getOverclockingItemVo().getHospitalId().equals("")){
                    stringBuffer.append("and b.hospital_id in("+dto.getOverclockingItemVo().getHospitalId()+")\r\n");
                }
                /*if(!dto.getOverclockingItemVo().getHospitalName().isEmpty()&&!dto.getOverclockingItemVo().getHospitalName().equals("")){
                    stringBuffer.append("and b.hospital_name='"+dto.getOverclockingItemVo().getHospitalName()+"'\r\n");
                }*/
                if(!dto.getOverclockingItemVo().getLimitBillDate().isEmpty()&&!dto.getOverclockingItemVo().getLimitBillDate().equals("")){
                    stringBuffer.append("and to_char(b.bill_date,'yyyyMMdd') BETWEEN "+dto.getOverclockingItemVo().getLimitBillDate().replace("forbid","and")+"\r\n");
                }
                stringBuffer.append("and b.item_id in(");
            }


            if (!dto.getOverclockingItemVo().getLimitBelong().isEmpty() && dto.getOverclockingItemVo().getLimitBelong().equals("门诊")) {

                stringBuffer.append("select * from  \r\n" +
                        "(select distinct  a.hisid hisid,  a.hospital_id hospital_id, a.hospital_name hospital_name,a.p_level p_level, a.bmi_area_id bmi_area_id, a.bmi_area_name bmi_area_name,  a.bill_date bill_date,  a.year year,  a.month month,  a.patient_id patient_id, a.social_card_id social_card_id,  a.benefit_type benefit_type,  a.benefit_group_id benefit_group_id,   a.admission_dept_name admission_dept_name,  a.doctor_id doctor_id,  a.doctor_name doctor_name,  a.patient_name patient_name, a.patient_gender patient_gender,  a.patient_birthday patient_birthday,  a.patient_age patient_age,  a.claim_type claim_type,  a.if_local_flag if_local_flag,   a.total_amount total_amount,   a.cash cash,  a.self_pay_amount self_pay_amount,    a.admission_disease_id admission_disease_id,  a.admission_disease_name admission_disease_name,    a.accommodation_fee accommodation_fee,   a.diagnosis_fee diagnosis_fee,   a.inspection_fee inspection_fee,    a.test_fee test_fee,  a.treatment_fee treatment_fee, a.nursing_fee nursing_fee,  a.material_fee material_fee, a.western_medicine_fee western_medicine_fee,  a.chinese_medicine_yinpian chinese_medicine_yinpian, a.chinese_medicine_form chinese_medicine_form,  a.consultation_fee consultation_fee, a.registration_fee registration_fee,  a.other_fee other_fee, a.sys_status sys_status,  b.discharge_dept_id discharge_dept_id,  b.p_category p_category,    b.item_id_hosp item_id_hosp, b.item_name_hosp item_name_hosp,  b.item_id item_id, b.item_name item_name, b.drug_spec drug_spec, b.dosage_form dosage_form,  b.package_unit package_unit, b.unit_price unit_price,  b.num num, b.cost cost, b.bmi_convered_amount bmi_convered_amount, b.bmi_pay_amount bmi_pay_amount,   b.p_type p_type,   b.item_type item_type,\r\n");
                stringBuffer.append(" sum(b.num) over(partition by b.hisid \r\n");
                if(!dto.getOverclockingItemVo().getLimitCountType().isEmpty()&&dto.getOverclockingItemVo().getLimitCountType().equals("分别计算")){
                    stringBuffer.append(",b.item_id \r\n");
                }
                if(!dto.getOverclockingItemVo().getLimitCountType().isEmpty()&&dto.getOverclockingItemVo().getLimitCountType().equals("次数累计")){
                    stringBuffer.append("");
                }
                if (!dto.getOverclockingItemVo().getLimitAppearType().isEmpty() && dto.getOverclockingItemVo().getLimitAppearType().equals("同天")) {
                    stringBuffer.append(",TO_CHAR(b.bill_date, 'YYYYMMDD'))  usage_count \r\n");
                }
                if (!dto.getOverclockingItemVo().getLimitAppearType().isEmpty() && dto.getOverclockingItemVo().getLimitAppearType().equals("同次")) {
                    stringBuffer.append(")  usage_count \r\n");
                }
                stringBuffer.append(" from t_flycheck_medical_mz a  , t_flycheck_medical_mz_detail b where a.hisid=b.hisid   \r\n");
                if(!dto.getOverclockingItemVo().getHospitalId().isEmpty()&&!dto.getOverclockingItemVo().getHospitalId().equals("")){
                    stringBuffer.append("and b.hospital_id in("+dto.getOverclockingItemVo().getHospitalId()+")\r\n");
                }
                /*if(!dto.getOverclockingItemVo().getHospitalName().isEmpty()&&!dto.getOverclockingItemVo().getHospitalName().equals("")){
                    stringBuffer.append("and  b.hospital_name='"+dto.getOverclockingItemVo().getHospitalName()+"'\r\n");
                }*/
                if(!dto.getOverclockingItemVo().getLimitBillDate().isEmpty()&&!dto.getOverclockingItemVo().getLimitBillDate().equals("")){

                    stringBuffer.append("and to_char(b.bill_date,'yyyyMMdd') BETWEEN "+dto.getOverclockingItemVo().getLimitBillDate().replace("forbid","and")+"\r\n");
                }
                stringBuffer.append("and b.item_id in(");
            }

            String[] items = dto.getOverclockingItemVo().getLimitItems().split(",");
            stringBuffer.append("'" + items[0] + "' \r\n");
            for (int i = 1; i < items.length; i++) {
                stringBuffer.append(",'" + items[i] + "' \r\n");
            };


            stringBuffer.append(")) where  usage_count  \r\n" );
            if(!dto.getOverclockingItemVo().getLimitQueryPlan().isEmpty()&&dto.getOverclockingItemVo().getLimitQueryPlan().equals("超频次收费")) {

                if (!dto.getOverclockingItemVo().getLimitSign().isEmpty() && dto.getOverclockingItemVo().getLimitSign().equals("等于")) {
                    stringBuffer.append("=");
                }
                if (!dto.getOverclockingItemVo().getLimitSign().isEmpty() && dto.getOverclockingItemVo().getLimitSign().equals("大于")) {
                    stringBuffer.append(">");
                }
                if (!dto.getOverclockingItemVo().getLimitSign().isEmpty() && dto.getOverclockingItemVo().getLimitSign().equals("小于")) {
                    stringBuffer.append("<");
                }
                stringBuffer.append(dto.getOverclockingItemVo().getLimitNumber());

            }

            if(!dto.getOverclockingItemVo().getLimitQueryPlan().isEmpty()&&dto.getOverclockingItemVo().getLimitQueryPlan().equals("超出住院天数收费")) {

                stringBuffer.append(">");
                if (!dto.getOverclockingItemVo().getLimitDayType().isEmpty() && dto.getOverclockingItemVo().getLimitDayType().equals("算入不算出")) {
                    stringBuffer.append("  zyts");
                }
                if (!dto.getOverclockingItemVo().getLimitDayType().isEmpty() && dto.getOverclockingItemVo().getLimitDayType().equals("算入算出")) {
                    stringBuffer.append("（zyts+1）");
                }
                if (!dto.getOverclockingItemVo().getLimitChargeType().isEmpty() && dto.getOverclockingItemVo().getLimitChargeType().equals("按时收费")) {
                    stringBuffer.append("*24");
                }
                if (!dto.getOverclockingItemVo().getLimitChargeType().isEmpty() && dto.getOverclockingItemVo().getLimitChargeType().equals("按日收费")) {
                    stringBuffer.append(" ");
                }
            }

        }else{
            stringBuffer.append("select *  from  \r\n" +
                    "(select  distinct a.bridge_id bridge_id,   a.hisid hisid, a.hospital_id hospital_id, a.hospital_name hospital_name, a.p_level p_level, a.bmi_area_id bmi_area_id,a.bmi_area_name bmi_area_name, a.bill_date bill_date, a.year year,  a.month month,   a.zyh zyh, a.patient_id patient_id,   a.social_card_id social_card_id,       a.medical_record_id medical_record_id, a.benefit_type benefit_type, a.benefit_group_id benefit_group_id,a.admission_dept_name admission_dept_name,a.transfer_dept_name transfer_dept_name, a.discharge_dept_name discharge_dept_name,  a.doctor_id doctor_id,a.doctor_name doctor_name,   a.patient_name patient_name, a.patient_gender patient_gender,  a.patient_birthday patient_birthday, a.patient_age patient_age,  a.patient_company patient_company, a.patient_address patient_address,   a.bedid bedid, a.nb_type nb_type,  a.nb_birth_weight nb_birth_weight, a.nb_inpatient_weight nb_inpatient_weight, a.claim_type claim_type,    a.if_local_flag if_local_flag,  a.admission_date admission_date,   a.discharge_date discharge_date,   a.zyts zyts,       a.discharge_status discharge_status, a.pre_admission_date pre_admission_date,  a.total_amount total_amount,   a.dbbx dbbx, a.yljz yljz,a.gwybz gwybz,  a.debc debc,  a.qybc qybc, a.cash cash, a.self_pay_amount self_pay_amount,       a.self_pay_in self_pay_in, a.self_pay_out self_pay_out, a.admission_disease_id admission_disease_id,   a.admission_disease_name admission_disease_name,  a.discharge_disease_id_main discharge_disease_id_main,    a.discharge_disease_name_main discharge_disease_name_main,  a.accommodation_fee accommodation_fee, a.diagnosis_fee diagnosis_fee, a.inspection_fee inspection_fee,   a.test_fee test_fee,   a.treatment_fee treatment_fee,  a.nursing_fee nursing_fee,  a.material_fee material_fee,    a.western_medicine_fee western_medicine_fee,   a.chinese_medicine_yinpian chinese_medicine_yinpian,       a.chinese_medicine_form chinese_medicine_form,  a.consultation_fee consultation_fee,       a.registration_fee registration_fee,  a.other_fee other_fee,    a.yb_pay_type yb_pay_type,   a.drgs_code drgs_code,   a.drgs_name drgs_name,  a.sys_status sys_status,   a.stay_times stay_times, b.detail_id detail_id,  b.discharge_dept_id discharge_dept_id,  b.p_category p_category,  b.usage_date usage_date, b.usage_date_flag usage_date_flag,   b.item_id_hosp item_id_hosp,   b.item_name_hosp item_name_hosp,   b.item_id item_id,   b.item_name item_name,    b.drug_spec drug_spec,  b.dosage_form dosage_form,  b.package_unit package_unit,  b.unit_price unit_price,  b.num num,   b.cost cost,  b.bmi_convered_amount bmi_convered_amount,   b.bmi_pay_amount bmi_pay_amount, b.p_type p_type,  b.p_type_pct p_type_pct, b.item_type item_type  ," +
                    " sum(b.num) over(partition by b.hisid,b.item_id)  usage_count from  t_flycheck_medical a, t_flycheck_medical_detail b where a.hisid=b.hisid and b.item_id in('404452' )) ");
        }

        exportsql=stringBuffer.toString();
        PageModel pageModel =dto.getPageModel();
        System.out.println("数量金额异常=="+stringBuffer);
        List<OverclockingItemVo> listData=jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(),OverclockingItemVo.class,null,pageModel.getPageNo(),
                pageModel.getPageSize(),null);
  /*      System.out.println("项目金额异常"+stringBuffer.toString());*/
        String sqlStr="select count(*) from (" +stringBuffer.toString()+")";
        @SuppressWarnings("unchecked")
        int total=jdbcTemplateWrapper.queryForInt(sqlStr,null);
        dto.getPageModel().setPageData(listData);
        dto.getPageModel().setTotals(total);
    }

    //导出到excel
    public XSSFWorkbook exportExcel() {
        //获取数据
        StringBuilder sqlStr = new StringBuilder();
        XSSFWorkbook wb = new XSSFWorkbook();
        sqlStr.append(exportsql);
        // List<PersonalInformationInquiryVo> personalInformationInquiryVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), PersonalInformationInquiryVo.class, null);
        List<OverclockingItemVo> overclockingItemVoList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),OverclockingItemVo.class,null);
        XSSFSheet sheet = wb.createSheet("结算主单");
        // 在sheet里创建第一行
        XSSFRow row1 = sheet.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
        row1.createCell(0	).setCellValue("病案关联字段");
        row1.createCell(1	).setCellValue("结算单据号(唯一)");
        row1.createCell(2	).setCellValue("医疗机构编码");
        row1.createCell(3	).setCellValue("医疗机构名称");
        row1.createCell(4	).setCellValue("医保结算等级");
        row1.createCell(5	).setCellValue("参保地统筹区域编码");
        row1.createCell(6	).setCellValue("参保地统筹区域名称");
        row1.createCell(7	).setCellValue("结算日期");
        row1.createCell(8	).setCellValue("结算年份");
        row1.createCell(9	).setCellValue("结算月份");
        row1.createCell(10	).setCellValue("住院号");
        row1.createCell(11	).setCellValue("个人编码");
        row1.createCell(12	).setCellValue("患者社保卡号");
        row1.createCell(13	).setCellValue("病案号");
        row1.createCell(14	).setCellValue("险种类型");
        row1.createCell(15	).setCellValue("人员类型");
        row1.createCell(16	).setCellValue("入院科别");
        row1.createCell(17	).setCellValue("转科科别");
        row1.createCell(18	).setCellValue("出院科别");
        row1.createCell(19	).setCellValue("主诊医师编码");
        row1.createCell(20	).setCellValue("主诊医师姓名");
        row1.createCell(21	).setCellValue("患者姓名");
        row1.createCell(22	).setCellValue("患者性别");
        row1.createCell(23	).setCellValue("患者出生日期");
        row1.createCell(24	).setCellValue("患者年龄");
        row1.createCell(25	).setCellValue("患者所在单位");
        row1.createCell(26	).setCellValue("患者现住址");
        row1.createCell(27	).setCellValue("患者床位号");
        row1.createCell(28	).setCellValue("新生儿入院类型");
        row1.createCell(29	).setCellValue("新生儿出生体重");
        row1.createCell(30	).setCellValue("新生儿入院体重");
        row1.createCell(31	).setCellValue("住院医疗类型");
        row1.createCell(32	).setCellValue("异地标志");
        row1.createCell(33	).setCellValue("入院日期");
        row1.createCell(34	).setCellValue("出院日期");
        row1.createCell(35	).setCellValue("住院天数");
        row1.createCell(36	).setCellValue("离院方式");
        row1.createCell(37	).setCellValue("上一次出院日期");
        row1.createCell(38	).setCellValue("是否有31天内再住院计划");
        row1.createCell(39	).setCellValue("医疗总费用");
        row1.createCell(40	).setCellValue("基本统筹支付");
        row1.createCell(41	).setCellValue("大病保险");
        row1.createCell(42	).setCellValue("医疗救助");
        row1.createCell(43	).setCellValue("公务员医疗补助");
        row1.createCell(44	).setCellValue("大额补充");
        row1.createCell(45	).setCellValue("企业补充");
        row1.createCell(46	).setCellValue("个人现金支付");
        row1.createCell(47	).setCellValue("个人账户支付");
        row1.createCell(48	).setCellValue("个人自付");
        row1.createCell(49	).setCellValue("个人自费");
        row1.createCell(50	).setCellValue("符合基本医疗保险的费用");
        row1.createCell(51	).setCellValue("入院诊断编码");
        row1.createCell(52	).setCellValue("入院诊断名称");
        row1.createCell(53	).setCellValue("出院诊断编码");
        row1.createCell(54	).setCellValue("出院诊断名称");
        row1.createCell(55	).setCellValue("床位费");
        row1.createCell(56	).setCellValue("诊察费");
        row1.createCell(57	).setCellValue("检查费");
        row1.createCell(58	).setCellValue("化验费");
        row1.createCell(59	).setCellValue("治疗费");
        row1.createCell(60	).setCellValue("护理费");
        row1.createCell(61	).setCellValue("卫生材料费");
        row1.createCell(62	).setCellValue("西药费");
        row1.createCell(63	).setCellValue("中药饮片费");
        row1.createCell(64	).setCellValue("中成药费");
        row1.createCell(65	).setCellValue("一般诊疗费");
        row1.createCell(66	).setCellValue("挂号费");
        row1.createCell(67	).setCellValue("其他费");
        row1.createCell(68	).setCellValue("医保支付方式");
        row1.createCell(69	).setCellValue("病组_病种编码");
        row1.createCell(70	).setCellValue("病组_病种名称");
        row1.createCell(71	).setCellValue("审核状态,0违规/1疑似违规/null正常");
        row1.createCell(72	).setCellValue("住院次数");
        row1.createCell(73	).setCellValue("项目使用次数");

        // 插入数据
        for (int i = 0; i < overclockingItemVoList.size(); i++) {
            XSSFRow rowNum = sheet.createRow(i + 1);

                rowNum.createCell(0).setCellValue(overclockingItemVoList.get(i).getBridgeId());
                rowNum.createCell(1).setCellValue(overclockingItemVoList.get(i).getHisid());
                rowNum.createCell(2).setCellValue(overclockingItemVoList.get(i).getHospitalId());
                rowNum.createCell(3).setCellValue(overclockingItemVoList.get(i).getHospitalName());
                rowNum.createCell(4).setCellValue(overclockingItemVoList.get(i).getpLevel());
                rowNum.createCell(5).setCellValue(overclockingItemVoList.get(i).getBmiAreaId());
            rowNum.createCell(6).setCellValue(overclockingItemVoList.get(i).getBmiAreaName());
            rowNum.createCell(7).setCellValue(overclockingItemVoList.get(i).getBillDate());
            rowNum.createCell(8).setCellValue(overclockingItemVoList.get(i).getYear());
            rowNum.createCell(9).setCellValue(overclockingItemVoList.get(i).getMonth()	);
            rowNum.createCell(10).setCellValue(overclockingItemVoList.get(i).getZyh());
            rowNum.createCell(11).setCellValue(overclockingItemVoList.get(i).getPatientId());
            rowNum.createCell(12).setCellValue(overclockingItemVoList.get(i).getSocialCardId()	);
            rowNum.createCell(13).setCellValue(overclockingItemVoList.get(i).getMedicalRecordId());
            rowNum.createCell(14).setCellValue(overclockingItemVoList.get(i).getBenefitType());
            rowNum.createCell(15).setCellValue(overclockingItemVoList.get(i).getBenefitGroupId());
            rowNum.createCell(16).setCellValue(overclockingItemVoList.get(i).getAdmissionDeptName());
            rowNum.createCell(17).setCellValue(overclockingItemVoList.get(i).getTransferDeptName()	);
            rowNum.createCell(18).setCellValue(overclockingItemVoList.get(i).getDischargeDeptName());
            rowNum.createCell(19).setCellValue(overclockingItemVoList.get(i).getDoctorId()	);
            rowNum.createCell(20).setCellValue(overclockingItemVoList.get(i).getDoctorName());
            rowNum.createCell(21).setCellValue(overclockingItemVoList.get(i).getPatientName());
            rowNum.createCell(22).setCellValue(overclockingItemVoList.get(i).getPatientGender());
            rowNum.createCell(23).setCellValue(overclockingItemVoList.get(i).getPatientBirthday());
     //       rowNum.createCell(24).setCellValue(overclockingItemVoList.get(i).getPatientAge());
            rowNum.createCell(25).setCellValue(overclockingItemVoList.get(i).getPatientCompany());
            rowNum.createCell(26).setCellValue(overclockingItemVoList.get(i).getPatientAddress());
            rowNum.createCell(27).setCellValue(overclockingItemVoList.get(i).getBedid());
            rowNum.createCell(28).setCellValue(overclockingItemVoList.get(i).getNbType());
            rowNum.createCell(29).setCellValue(overclockingItemVoList.get(i).getNbBirthWeight());
            rowNum.createCell(30).setCellValue(overclockingItemVoList.get(i).getNbInpatientWeight());
            rowNum.createCell(31).setCellValue(overclockingItemVoList.get(i).getClaimType());
            rowNum.createCell(32).setCellValue(overclockingItemVoList.get(i).getIfLocalFlag());
            rowNum.createCell(33).setCellValue(overclockingItemVoList.get(i).getAdmissionDate());
            rowNum.createCell(34).setCellValue(overclockingItemVoList.get(i).getDischargeDate());
            rowNum.createCell(35).setCellValue(overclockingItemVoList.get(i).getZyts());
            rowNum.createCell(36).setCellValue(overclockingItemVoList.get(i).getDischargeStatus());
            rowNum.createCell(37).setCellValue(overclockingItemVoList.get(i).getPreAdmissionDate());
            //   rowNum.createCell(38).setCellValue(overclockingItemVoList.get(i).get	31days_re_admission	())
            rowNum.createCell(39).setCellValue(overclockingItemVoList.get(i).getTotalAmount());
           // rowNum.createCell(40).setCellValue(overclockingItemVoList.get(i).getBmiPayAmount());
            rowNum.createCell(41).setCellValue(overclockingItemVoList.get(i).getDbbx());
            rowNum.createCell(42).setCellValue(overclockingItemVoList.get(i).getYljz()	);
            rowNum.createCell(43).setCellValue(overclockingItemVoList.get(i).getGwybz());
            rowNum.createCell(44).setCellValue(overclockingItemVoList.get(i).getDebc()	);
            rowNum.createCell(45).setCellValue(overclockingItemVoList.get(i).getQybc());
            rowNum.createCell(46).setCellValue(overclockingItemVoList.get(i).getCash());
            rowNum.createCell(47).setCellValue(overclockingItemVoList.get(i).getSelfPayAmount());
            rowNum.createCell(48).setCellValue(overclockingItemVoList.get(i).getSelfPayIn());
            rowNum.createCell(49).setCellValue(overclockingItemVoList.get(i).getSelfPayOut());
          //  rowNum.createCell(50).setCellValue(overclockingItemVoList.get(i).BMI_CONVERED_AMOUNT()	);
            rowNum.createCell(51).setCellValue(overclockingItemVoList.get(i).getAdmissionDiseaseId());
            rowNum.createCell(52).setCellValue(overclockingItemVoList.get(i).getAdmissionDiseaseName()	);
            rowNum.createCell(53).setCellValue(overclockingItemVoList.get(i).getDischargeDiseaseIdMain());
            rowNum.createCell(54).setCellValue(overclockingItemVoList.get(i).getDischargeDiseaseNameMain());
            rowNum.createCell(55).setCellValue(overclockingItemVoList.get(i).getAccommodationFee());
            rowNum.createCell(56).setCellValue(overclockingItemVoList.get(i).getDiagnosisFee());
            rowNum.createCell(57).setCellValue(overclockingItemVoList.get(i).getInspectionFee());
            rowNum.createCell(58).setCellValue(overclockingItemVoList.get(i).getTestFee());
            rowNum.createCell(59).setCellValue(overclockingItemVoList.get(i).getTreatmentFee());
            rowNum.createCell(60).setCellValue(overclockingItemVoList.get(i).getNursingFee());
            rowNum.createCell(61).setCellValue(overclockingItemVoList.get(i).getMaterialFee());
            rowNum.createCell(62).setCellValue(overclockingItemVoList.get(i).getWesternMedicineFee());
            rowNum.createCell(63).setCellValue(overclockingItemVoList.get(i).getChineseMedicineYinpian());
            rowNum.createCell(64).setCellValue(overclockingItemVoList.get(i).getChineseMedicineForm());
            rowNum.createCell(65).setCellValue(overclockingItemVoList.get(i).getConsultationFee());
            rowNum.createCell(66).setCellValue(overclockingItemVoList.get(i).getRegistrationFee());
            rowNum.createCell(67).setCellValue(overclockingItemVoList.get(i).getOtherFee()	);
            rowNum.createCell(68).setCellValue(overclockingItemVoList.get(i).getYbPayType());
            rowNum.createCell(69).setCellValue(overclockingItemVoList.get(i).getDrgsCode()		);
            rowNum.createCell(70).setCellValue(overclockingItemVoList.get(i).getDrgsName()		);
            rowNum.createCell(71).setCellValue(overclockingItemVoList.get(i).getSysStatus());
            rowNum.createCell(72).setCellValue(overclockingItemVoList.get(i).getStayTimes());
            rowNum.createCell(73).setCellValue(overclockingItemVoList.get(i).getUsageCount());

        }

        // List<PersonalInformationInquiryVo> personalInformationInquiryVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), PersonalInformationInquiryVo.class, null);
        List<OverclockingItemVo> overclockingItemVoDetailList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),OverclockingItemVo.class,null);
        XSSFSheet sheet2= wb.createSheet("结算明细");
        // 在sheet里创建第一行
        XSSFRow DetailRow1 = sheet2.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
        DetailRow1.createCell(0).setCellValue("明细主键id");
        DetailRow1.createCell(1).setCellValue("单据号");
        DetailRow1.createCell(2).setCellValue("个人编码");
        DetailRow1.createCell(3).setCellValue("住院号");
        DetailRow1.createCell(4).setCellValue("医疗机构编码");
        DetailRow1.createCell(5).setCellValue("医疗机构名称");
        DetailRow1.createCell(6).setCellValue("出院科室编码");
        DetailRow1.createCell(7).setCellValue("出院科室名称");
        DetailRow1.createCell(8).setCellValue("主诊医师编码");
        DetailRow1.createCell(9).setCellValue("主诊医师姓名");
        DetailRow1.createCell(10).setCellValue("出院诊断名称");
        DetailRow1.createCell(11).setCellValue("费用类别");
        DetailRow1.createCell(12).setCellValue("项目使用日期");
        DetailRow1.createCell(13).setCellValue("项目使用日期标识");
        DetailRow1.createCell(14).setCellValue("结算日期");
        DetailRow1.createCell(15).setCellValue("收费年份");
        DetailRow1.createCell(16).setCellValue("收费月份");
        DetailRow1.createCell(17).setCellValue("医院项目编码");
        DetailRow1.createCell(18).setCellValue("医院项目名称");
        DetailRow1.createCell(19).setCellValue("医保项目编码");
        DetailRow1.createCell(20).setCellValue("医保项目名称");
        DetailRow1.createCell(21).setCellValue("规格");
        DetailRow1.createCell(22).setCellValue("剂型");
        DetailRow1.createCell(23).setCellValue("最小包装单位");
        DetailRow1.createCell(24).setCellValue("单价");
        DetailRow1.createCell(25).setCellValue("数量");
        DetailRow1.createCell(26).setCellValue("金额");
        DetailRow1.createCell(27).setCellValue("医保范围内金额");
        DetailRow1.createCell(28).setCellValue("医保医保实际支付金额");
        DetailRow1.createCell(29).setCellValue("支付类别");
        DetailRow1.createCell(30).setCellValue("报销比例");
        DetailRow1.createCell(31).setCellValue("项目类型 1-药品 2-诊疗 3-耗材");

        for (int i = 0; i < overclockingItemVoDetailList.size(); i++) {
            XSSFRow rowNum = sheet2.createRow(i + 1);
            rowNum.createCell(0).setCellValue(overclockingItemVoDetailList.get(i).getDetailId()	);
            rowNum.createCell(1).setCellValue(overclockingItemVoDetailList.get(i).getHisid());
            rowNum.createCell(2).setCellValue(overclockingItemVoDetailList.get(i).getPatientId()	);
            rowNum.createCell(3).setCellValue(overclockingItemVoDetailList.get(i).getZyh()	);
            rowNum.createCell(4).setCellValue(overclockingItemVoDetailList.get(i).getHisid()	);
            rowNum.createCell(5).setCellValue(overclockingItemVoDetailList.get(i).	getHospitalName()	);
            rowNum.createCell(6).setCellValue(overclockingItemVoDetailList.get(i).	getDischargeDeptId());
            rowNum.createCell(7).setCellValue(overclockingItemVoDetailList.get(i).	getDischargeDeptName()	);
            rowNum.createCell(8).setCellValue(overclockingItemVoDetailList.get(i).	getDoctorId()	);
            rowNum.createCell(9).setCellValue(overclockingItemVoDetailList.get(i).	getDoctorName()	);
            rowNum.createCell(10).setCellValue(overclockingItemVoDetailList.get(i).getDischargeDiseaseNameMain()	);
            rowNum.createCell(11).setCellValue(overclockingItemVoDetailList.get(i).getpCategory()		);
            rowNum.createCell(12).setCellValue(overclockingItemVoDetailList.get(i).getUsageDate()		);
            rowNum.createCell(13).setCellValue(overclockingItemVoDetailList.get(i).getUsageDateFlag()		);
            rowNum.createCell(14).setCellValue(overclockingItemVoDetailList.get(i).getBillDate()		);
            rowNum.createCell(15).setCellValue(overclockingItemVoDetailList.get(i).getYear()		);
            rowNum.createCell(16).setCellValue(overclockingItemVoDetailList.get(i).getMonth()		);
            rowNum.createCell(17).setCellValue(overclockingItemVoDetailList.get(i).getItemIdHosp()		);
            rowNum.createCell(18).setCellValue(overclockingItemVoDetailList.get(i).getItemNameHosp()		);
            rowNum.createCell(19).setCellValue(overclockingItemVoDetailList.get(i).getItemId()		);
            rowNum.createCell(20).setCellValue(overclockingItemVoDetailList.get(i).getItemName()		);
            rowNum.createCell(21).setCellValue(overclockingItemVoDetailList.get(i).getDrugSpec()		);
            rowNum.createCell(22).setCellValue(overclockingItemVoDetailList.get(i).getDosageForm()		);
            rowNum.createCell(23).setCellValue(overclockingItemVoDetailList.get(i).getPackageUnit()		);
            rowNum.createCell(24).setCellValue(overclockingItemVoDetailList.get(i).getUnitPrice()		);
            rowNum.createCell(25).setCellValue(overclockingItemVoDetailList.get(i).getNum()	);
            rowNum.createCell(26).setCellValue(overclockingItemVoDetailList.get(i).getCost()		);
            rowNum.createCell(27).setCellValue(overclockingItemVoDetailList.get(i).getBmiConveredAmount()		);
            rowNum.createCell(28).setCellValue(overclockingItemVoDetailList.get(i).getBmiPayAmount()		);
            rowNum.createCell(29).setCellValue(overclockingItemVoDetailList.get(i).getpType()		);
            rowNum.createCell(30).setCellValue(overclockingItemVoDetailList.get(i).getpTypePct()		);
            rowNum.createCell(31).setCellValue(overclockingItemVoDetailList.get(i).getItemType()		);
        }


        return wb;
    }
}

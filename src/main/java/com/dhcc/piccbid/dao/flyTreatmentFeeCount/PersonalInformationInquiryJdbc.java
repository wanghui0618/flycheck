package com.dhcc.piccbid.dao.flyTreatmentFeeCount;


import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.DetailedInformationVo;
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
public class PersonalInformationInquiryJdbc {

    @Autowired
    JdbcTemplateWrapper jdbcTemplateWrapper;

    private static String exportsql;
    public  void search(PersonalInformationInquiryDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
            Map<String, Object> hqlParamMap = new HashMap<String, Object>();
            if (dto.getPersonalInformationInquiryVo()!=null) {
                if (dto.getPersonalInformationInquiryVo().getBelong().equals("住院")){
                    stringBuffer.append("select hisid, hospital_id  ,hospital_name ,zyh ,patient_ID ,discharge_dept_name ,patient_name ,TO_CHAR(admission_date, 'YYYY-MM-DD') admission_date,TO_CHAR(discharge_date, 'YYYY-MM-DD') discharge_Date,zyts,total_amount ,benefit_type ,\r\n" +
                            "SUM(DECODE(western_medicine_fee, null,0 ,western_medicine_fee)+DECODE(chinese_medicine_yinpian,null,0, chinese_medicine_yinpian)+DECODE(chinese_medicine_form,null,0, chinese_medicine_form)) drug_Fee,inspection_fee ,accommodation_fee ,diagnosis_fee ,treatment_fee ,test_fee ,nursing_fee ,material_fee ,consultation_fee ,registration_fee ,other_fee \r\n" +
                            "from\r\n" +
                            "T_FLYCHECK_MEDICAL where 1=1 \r\n");
                    if (!dto.getPersonalInformationInquiryVo().getBenefitType().equals("")&&!dto.getPersonalInformationInquiryVo().getBenefitType().isEmpty()){
                        stringBuffer.append("and benefit_type like'%"+dto.getPersonalInformationInquiryVo().getBenefitType()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getHospitalId().equals("")&&!dto.getPersonalInformationInquiryVo().getHospitalId().isEmpty()){
                        stringBuffer.append("and hospital_id in("+dto.getPersonalInformationInquiryVo().getHospitalId()+")\r\n");
                    };
                    /*if (!dto.getPersonalInformationInquiryVo().getHospitalName().equals("")&&!dto.getPersonalInformationInquiryVo().getHospitalName().isEmpty()){
                        stringBuffer.append("and hospital_name like'%"+dto.getPersonalInformationInquiryVo().getHospitalName()+"%'\r\n");
                    };*/
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDeptName().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDeptName().isEmpty()){
                        stringBuffer.append("and discharge_dept_name like'%"+dto.getPersonalInformationInquiryVo().getDischargeDeptName()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDiseaseIdMain().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDiseaseIdMain().isEmpty()){
                        stringBuffer.append("and discharge_disease_id_main like'%"+dto.getPersonalInformationInquiryVo().getDischargeDiseaseIdMain()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDiseaseNameMain().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDiseaseNameMain().isEmpty()){
                        stringBuffer.append("and discharge_disease_name_main like'%"+dto.getPersonalInformationInquiryVo().getAdmissionDiseaseName()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getHisid().equals("")&&!dto.getPersonalInformationInquiryVo().getHisid().isEmpty()){
                        stringBuffer.append("and hisid like'%"+dto.getPersonalInformationInquiryVo().getHisid()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getZyh().equals("")&&!dto.getPersonalInformationInquiryVo().getZyh().isEmpty()){
                        stringBuffer.append("and zyh like'%"+dto.getPersonalInformationInquiryVo().getZyh()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getAdmissionDate().equals("")&&!dto.getPersonalInformationInquiryVo().getAdmissionDate().isEmpty()){
                        stringBuffer.append("and TO_NUMBER(TO_CHAR(admission_date, 'YYYYMMDD'))='"+dto.getPersonalInformationInquiryVo().getAdmissionDate()+"'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDate().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDate().isEmpty()){
                        stringBuffer.append("and TO_NUMBER(TO_CHAR(discharge_date, 'YYYYMMDD'))='"+dto.getPersonalInformationInquiryVo().getDischargeDate()+"'\r\n");
                    };

                    if (!dto.getPersonalInformationInquiryVo().getBillDate().equals("")&&!dto.getPersonalInformationInquiryVo().getBillDate().isEmpty()){
                        stringBuffer.append("and TO_NUMBER(TO_CHAR(bill_date, 'YYYYMMDD')) BETWEEN "+dto.getPersonalInformationInquiryVo().getBillDate().replace("forbid","and")+"\r\n");
                    };



                    stringBuffer.append("GROUP BY  hisid, hospital_id,hospital_name,zyh,patient_ID,discharge_dept_name,patient_name,admission_date,discharge_date,zyts,total_amount,benefit_type,inspection_fee,accommodation_fee,diagnosis_fee,treatment_fee,test_fee,nursing_fee,material_fee,consultation_fee,registration_fee,other_fee");
                }
                if(dto.getPersonalInformationInquiryVo().getBelong().equals("门诊")){
                    stringBuffer.append("SELECT   hisid hisId,hospital_id ,admission_disease_name discharge_disease_name_main,hospital_name ,patient_ID ,admission_dept_name  discharge_dept_name,patient_name ,total_amount ,benefit_type ,\r\n" +
                            "SUM(DECODE( western_medicine_fee, NULL, 0, western_medicine_fee ) + DECODE( chinese_medicine_yinpian, NULL, 0, chinese_medicine_yinpian ) + DECODE( chinese_medicine_form, NULL, 0, chinese_medicine_form ) ) drug_fee,\r\n" +
                            "inspection_fee ,accommodation_fee ,diagnosis_fee ,treatment_fee ,test_fee ,nursing_fee ,material_fee ,consultation_fee ,\r\n" +
                            "registration_fee other_fee FROM T_FLYCHECK_MEDICAL_MZ  where 1=1\r\n");

                    if (!dto.getPersonalInformationInquiryVo().getBenefitType().equals("")&&!dto.getPersonalInformationInquiryVo().getBenefitType().isEmpty()){
                        stringBuffer.append("and benefit_type like'%"+dto.getPersonalInformationInquiryVo().getBenefitType()+"%'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getHospitalId().equals("")&&dto.getPersonalInformationInquiryVo().getHospitalId()!= null){
                        stringBuffer.append("and hospital_id in("+dto.getPersonalInformationInquiryVo().getHospitalId()+")\r\n");
                    };
                   /* if (!dto.getPersonalInformationInquiryVo().getHospitalName().equals("")&&!dto.getPersonalInformationInquiryVo().getHospitalName().isEmpty()){
                        stringBuffer.append("and hospital_name ='"+dto.getPersonalInformationInquiryVo().getHospitalName()+"'\r\n");
                    };*/
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDeptName().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDeptName().isEmpty()){
                        stringBuffer.append("and admission_dept_name ='"+dto.getPersonalInformationInquiryVo().getDischargeDeptName()+"'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDiseaseIdMain().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDiseaseIdMain().isEmpty()){
                        stringBuffer.append("and admission_disease_id ='"+dto.getPersonalInformationInquiryVo().getDischargeDiseaseIdMain()+"'\r\n");
                    };
                    if (!dto.getPersonalInformationInquiryVo().getDischargeDiseaseNameMain().equals("")&&!dto.getPersonalInformationInquiryVo().getDischargeDiseaseNameMain().isEmpty()){
                        stringBuffer.append("and admission_disease_name ='"+dto.getPersonalInformationInquiryVo().getDischargeDiseaseNameMain()+"'\r\n");
                    };


                    if (!dto.getPersonalInformationInquiryVo().getBillDate().equals("")&&!dto.getPersonalInformationInquiryVo().getBillDate().isEmpty()){
                        stringBuffer.append("and TO_NUMBER(TO_CHAR(bill_date, 'YYYYMMDD')) BETWEEN "+dto.getPersonalInformationInquiryVo().getBillDate().replace("forbid","and")+" \r\n");
                    };

                    stringBuffer.append("GROUP BY hisid,admission_disease_name ,hospital_id,hospital_name,patient_ID,admission_dept_name,patient_name,total_amount,benefit_type,\r\n" +
                            "inspection_fee,accommodation_fee,diagnosis_fee,treatment_fee,test_fee,nursing_fee,material_fee,consultation_fee,registration_fee,other_fee");

                }

            }else{
                stringBuffer.append("select hisid, hospital_id  ,hospital_name ,zyh ,patient_ID ,discharge_dept_name ,patient_name ,TO_CHAR(admission_date, 'YYYY-MM-DD') admission_date,TO_CHAR(discharge_date, 'YYYY-MM-DD') discharge_Date,zyts,total_amount ,benefit_type ,\r\n" +
                        "SUM(DECODE(western_medicine_fee, null,0 ,western_medicine_fee)+DECODE(chinese_medicine_yinpian,null,0, chinese_medicine_yinpian)+DECODE(chinese_medicine_form,null,0, chinese_medicine_form)) drug_Fee,inspection_fee ,accommodation_fee ,diagnosis_fee ,treatment_fee ,test_fee ,nursing_fee ,material_fee ,consultation_fee ,registration_fee ,other_fee \r\n" +
                        "from\r\n" +
                        "T_FLYCHECK_MEDICAL where 1=1 \r\n"+
                        "GROUP BY  hisid, hospital_id,hospital_name,zyh,patient_ID,discharge_dept_name,patient_name,admission_date,discharge_date,zyts,total_amount,benefit_type,inspection_fee,accommodation_fee,diagnosis_fee,treatment_fee,test_fee,nursing_fee,material_fee,consultation_fee,registration_fee,other_fee");
            };
        exportsql=stringBuffer.toString();
        System.out.println("个人信息查询"+exportsql);
        dto.getPageModel().setQueryHql(stringBuffer.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);

    }
    public  void searchDetail(PersonalInformationInquiryDto dto) {

        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();


            stringBuffer.append("select  hisid,ITEM_ID,item_Name, item_Id_Hosp,item_Name_Hosp,unit_Price,num,cost,p_Category,p_Type from\r\n");
      if (dto.getDetailedInformationVo().getBelong().isEmpty() || dto.getDetailedInformationVo().getBelong().equals("")) {
            stringBuffer.append("T_FLYCHECK_MEDICAL_DETAIL where 1=1 \r\n");

        };

            if (!dto.getDetailedInformationVo().getBelong().isEmpty() && dto.getDetailedInformationVo().getBelong().equals("住院")) {
                stringBuffer.append("T_FLYCHECK_MEDICAL_DETAIL where 1=1 \r\n");
            };
            if (!dto.getDetailedInformationVo().getBelong().isEmpty() && dto.getDetailedInformationVo().getBelong().equals("门诊")) {
                stringBuffer.append("T_FLYCHECK_MEDICAL_MZ_DETAIL where 1=1 \r\n");
            };

                if (dto.getDetailedInformationVo().getItemId() != null && !dto.getDetailedInformationVo().getItemId().equals("")) {
                    stringBuffer.append("and item_id like'%" + dto.getDetailedInformationVo().getItemId() + "%'\r\n");
                }
                ;
                if (dto.getDetailedInformationVo().getItemName() != null && !dto.getDetailedInformationVo().getItemName().equals("")) {
                    stringBuffer.append("and item_name like '%" + dto.getDetailedInformationVo().getItemName() + "%'\r\n");
                }
                ;
                if (dto.getDetailedInformationVo().getpCategory() != null && !dto.getDetailedInformationVo().getpCategory().equals("")) {
                    stringBuffer.append("and p_category like'%" + dto.getDetailedInformationVo().getpCategory() + "%'\r\n");
                }
                ;
                if (dto.getDetailedInformationVo().getHisid() != null && !dto.getDetailedInformationVo().getHisid().equals("")) {
                    stringBuffer.append("and hisid ='" + dto.getDetailedInformationVo().getHisid() + "'\r\n");
                };
        System.out.println(stringBuffer.toString());

            PageModel pageModel =dto.getPageModel();
            List<DetailedInformationVo> listData=jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(),DetailedInformationVo.class,null,pageModel.getPageNo(),
                    pageModel.getPageSize(),null);
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
        List<PersonalInformationInquiryVo> personalInformationInquiryVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), PersonalInformationInquiryVo.class, null);
        XSSFSheet sheet = wb.createSheet("结算表");
        // 在sheet里创建第一行
        XSSFRow row1 = sheet.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)

        row1.createCell(0).setCellValue("医疗机构编码");
        row1.createCell(1).setCellValue("医疗机构名称");
        row1.createCell(2).setCellValue("身份证号码");
        row1.createCell(3).setCellValue("住院号");
        row1.createCell(4).setCellValue("就诊科室");
        row1.createCell(5).setCellValue("患者姓名");
        row1.createCell(6).setCellValue("入院日期");
        row1.createCell(7).setCellValue("出院日期");
        row1.createCell(8).setCellValue("住院天数");
        row1.createCell(9).setCellValue("医疗总费用");
        row1.createCell(10).setCellValue("险种类型");
        row1.createCell(11).setCellValue("药品费");
        row1.createCell(12).setCellValue("检查费");
        row1.createCell(13).setCellValue("床位费");
        row1.createCell(14).setCellValue("诊察费");
        row1.createCell(15).setCellValue("治疗费");
        row1.createCell(16).setCellValue("化验费");
        row1.createCell(17).setCellValue("护理费");
        row1.createCell(18).setCellValue("卫生材料费");
        row1.createCell(19).setCellValue("一般诊疗费");
        row1.createCell(20).setCellValue("挂号费");
        row1.createCell(21).setCellValue("其他费");


        // 插入数据
        for (int i = 0; i < personalInformationInquiryVoList.size(); i++) {
            XSSFRow rowNum = sheet.createRow(i + 1);
            rowNum.createCell(0).setCellValue(personalInformationInquiryVoList.get(i).getHospitalId());
            rowNum.createCell(1).setCellValue(personalInformationInquiryVoList.get(i).getHospitalName());
            rowNum.createCell(2).setCellValue(personalInformationInquiryVoList.get(i).getPatientId());
            rowNum.createCell(3).setCellValue(personalInformationInquiryVoList.get(i).getZyh());
            rowNum.createCell(4).setCellValue(personalInformationInquiryVoList.get(i).getDischargeDeptName());
            rowNum.createCell(5).setCellValue(personalInformationInquiryVoList.get(i).getPatientName());
            rowNum.createCell(6).setCellValue(personalInformationInquiryVoList.get(i).getAdmissionDate());
            rowNum.createCell(7).setCellValue(personalInformationInquiryVoList.get(i).getDischargeDate());
            rowNum.createCell(8).setCellValue(personalInformationInquiryVoList.get(i).getZyts());
            rowNum.createCell(9).setCellValue(personalInformationInquiryVoList.get(i).getTotalAmountBasy());
            rowNum.createCell(10).setCellValue(personalInformationInquiryVoList.get(i).getBenefitType());
            rowNum.createCell(11).setCellValue(personalInformationInquiryVoList.get(i).getDrugFee());
            rowNum.createCell(12).setCellValue(personalInformationInquiryVoList.get(i).getInspectionFee());
            rowNum.createCell(13).setCellValue(personalInformationInquiryVoList.get(i).getAccommodationFee());
            rowNum.createCell(14).setCellValue(personalInformationInquiryVoList.get(i).getDiagnosisFee());
            rowNum.createCell(15).setCellValue(personalInformationInquiryVoList.get(i).getTreatmentFee());
            rowNum.createCell(16).setCellValue(personalInformationInquiryVoList.get(i).getTestFee());
            rowNum.createCell(17).setCellValue(personalInformationInquiryVoList.get(i).getNursingFee());
            rowNum.createCell(18).setCellValue(personalInformationInquiryVoList.get(i).getMaterialFee());
            rowNum.createCell(19).setCellValue(personalInformationInquiryVoList.get(i).getConsultationFee());
            rowNum.createCell(20).setCellValue(personalInformationInquiryVoList.get(i).getRegistrationFee());
            rowNum.createCell(21).setCellValue(personalInformationInquiryVoList.get(i).getOtherFee());
        }
        return wb;
    }
}

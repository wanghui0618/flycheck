package com.dhcc.piccbid.dao.unreasonableAdmission;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugs;
import com.dhcc.piccbid.entity.unreasonableAdmission.CaseInfo;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :  zhouwei
 * @PackageNAme : com.dhcc.piccbid.dao.unreasonableAdmission
 * @ClassName : UnreasonableAdmissionJdbcDao
 * @Description:
 * @create : 2019-11-23 15:36
 */
@Component
public class UnreasonableAdmissionJdbcDao {

    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    public void findDict(UnreasonableAdmissionDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        String dictNames = dto.getDictName();
        String keyword = dto.getKeyword();

            sqlStr.append(
                    "select * from \r\n" +
                            "(select distinct p_category as name \r\n" +
                            "from t_Flycheck_Medical_Detail where p_category is not null) ");
        if (keyword != null && !"".equals(keyword)) {
            sqlStr.append(" where name like '%" + keyword + "%'");
        }
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);

    }

    //病例明细  pCategory  itemId  itemName
    public void caseInfoList(UnreasonableAdmissionDto dto){
        PageModel page = dto.getPageModel();
        CaseInfo caseInfo = dto.getCaseInfo();
        StringBuilder sql = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sql.append("select hisid,\n" +
                "\t item_id,\n" +
                "\t item_name,\n" +
                "\t item_id_hosp,\n" +
                "\t item_name_hosp,\n" +
                "\t p_category,\n" +
                "\t unit_price,\n" +
                "\t num,\n" +
                "\t cost,\n" +
                "\t p_type\n" +
                "\tfrom t_flycheck_medical_detail\n" +
                " where 1 = 1");
        if (caseInfo!=null){
            if (caseInfo.getHisid()!=null&&!caseInfo.getHisid().equals("")){
                sql.append("and hisid ='"+caseInfo.getHisid()+"'");
            }
            if(caseInfo.getItemId()!=null&&!caseInfo.getItemId().equals("")){
                sql.append("and item_id = '"+caseInfo.getItemId()+"'");
            }
            if(caseInfo.getpCategory()!=null&&!caseInfo.getpCategory().equals("")){
                sql.append("and p_category = '"+caseInfo.getpCategory()+"'");
            }
            if(caseInfo.getItemName()!=null&&!caseInfo.getItemName().equals("")){
                sql.append("and item_name = '"+caseInfo.getItemName()+"'");
            }
        }
        page.setQueryHql(sql.toString());
        page.setHqlParamMap(hqlParamMap);

    }

    //导出
    public SXSSFWorkbook getData(UnreasonableAdmissionDto dto) {
        StringBuilder sql = new StringBuilder();
        UnreasonableAdmission unreasonableAdmission = dto.getUnreasonableAdmission();
        SXSSFWorkbook wb = new SXSSFWorkbook();
        sql.append("select tfm.hospital_name,tfm.zyh,tfm.admission_dept_name,tfm.patient_name,tfm.admission_date,\n" +
                "               tfm.discharge_date,tfm.zyts,tfm.total_amount,tfm.benefit_type,tfm.western_medicine_fee, \n" +
                "                tfm.chinese_medicine_yinpian,tfm.chinese_medicine_form,tfm.consultation_fee\n" +
                "                  from  T_FLYCHECK_MEDICAL tfm where 1=1 ");
        if (unreasonableAdmission!=null){
            String code1 = unreasonableAdmission.getCode1();
            String code2 = unreasonableAdmission.getCode2();
            String code3 = unreasonableAdmission.getCode3();
            if (!StringUtils.isNullOrEmpty(code1) || !StringUtils.isNullOrEmpty(code2)|| !StringUtils.isNullOrEmpty(code3)) {
                if (code1.equals("& gt;")) {
                    code1 = ">";
                }
                if (code2.equals("& gt;")) {
                    code2 = ">";
                }
                if (code3.equals("& gt;")) {
                    code3 = ">";
                }
                if (code1.equals("& lt;")) {
                    code1 = "<";
                }
                if (code2.equals("& lt;")) {
                    code2 = "<";
                }
                if (code3.equals("& lt;")) {
                    code2 = "<";
                }
                if (code3.equals("& lt;")) {
                    code2 = "<";
                }
            }
            /*if (unreasonableAdmission.getHospitalName()!=null&&!unreasonableAdmission.getHospitalName().equals("")){
                sql.append("and  tfm.hospital_name='"+unreasonableAdmission.getHospitalName()+"'");
            }*/
            if (unreasonableAdmission.getHospitalId()!=null&&!unreasonableAdmission.getHospitalId().equals("")){
                sql.append("and  tfm.hospital_id in("+unreasonableAdmission.getHospitalId()+")");
            }
            if (unreasonableAdmission.getBillDateStr()!=null&&!unreasonableAdmission.getBillDateStr().equals("")){
                String billDate=unreasonableAdmission.getBillDateStr();
                String[] date = billDate.split("forbid");
                sql.append("and  tfm.bill_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");

            }
            if (unreasonableAdmission.getAdmissionDateStr()!=null&&!unreasonableAdmission.getAdmissionDateStr().equals("")){
                String AdmissionDateStr=unreasonableAdmission.getAdmissionDateStr();
                String[] date = AdmissionDateStr.split("forbid");
                sql.append("and  tfm.admission_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");
            }
            if (unreasonableAdmission.getDischargeDateStr()!=null&&!unreasonableAdmission.getDischargeDateStr().equals("")){
                String DischargeDateStr=unreasonableAdmission.getDischargeDateStr();
                String[] date = DischargeDateStr.split("forbid");
                sql.append("and  tfm.discharge_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");
            }
            if (unreasonableAdmission.getProportionOfMedicines()!=null&&!unreasonableAdmission.getProportionOfMedicines().equals("")){
                String proportionOfMedicine = unreasonableAdmission.getProportionOfMedicines();
                Float math = Float.valueOf(proportionOfMedicine) / 100;
                sql.append("and (tfm.western_medicine_fee+tfm.chinese_medicine_form+tfm.chinese_medicine_yinpian)/tfm.total_amount"+code1+math);
            }
            if (unreasonableAdmission.getInspectionFeeRatio()!=null&&!unreasonableAdmission.getInspectionFeeRatio().equals("")){
                String inspectionFeeRatio = unreasonableAdmission.getInspectionFeeRatio();
                Float math = Float.valueOf(inspectionFeeRatio) / 100;
                sql.append("and tfm.inspection_fee"+code2+math);
            }
            if (unreasonableAdmission.getCostOfTreatment()!=null&&!unreasonableAdmission.getCostOfTreatment().equals("")){
                String costOfTreatment = unreasonableAdmission.getCostOfTreatment();
                Float math = Float.valueOf(costOfTreatment) / 100;
                sql.append("and tfm.nursing_fee/tfm.total_amount"+code3+math);
            }
        }
        List<UnreasonableAdmission> unreasonableAdmissionList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), UnreasonableAdmission.class, null);
        SXSSFSheet sheet = (SXSSFSheet)wb.createSheet("入院不合理");
        // 在sheet里创建第一行
        SXSSFRow row1 = (SXSSFRow)sheet.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
        row1.createCell(0).setCellValue("医疗机构名称");
        row1.createCell(1).setCellValue("住院号");
        row1.createCell(2).setCellValue("就诊科室");
        row1.createCell(3).setCellValue("患者姓名");
        row1.createCell(4).setCellValue("入院日期");
        row1.createCell(5).setCellValue("出院日期");
        row1.createCell(6).setCellValue("住院天数");
        row1.createCell(7).setCellValue("医疗总费用");
        row1.createCell(8).setCellValue("险种类型");
        row1.createCell(9).setCellValue("西药费");
        row1.createCell(10).setCellValue("中药费");
        row1.createCell(11).setCellValue("中成药费");
        row1.createCell(12).setCellValue("治疗费");

        // 插入数据
        for (int i = 0; i < unreasonableAdmissionList.size(); i++) {
            SXSSFRow rowNum = (SXSSFRow)sheet.createRow(i + 1);
            rowNum.createCell(0).setCellValue(unreasonableAdmissionList.get(i).getHospitalName());
            rowNum.createCell(1).setCellValue(unreasonableAdmissionList.get(i).getZyh());
            rowNum.createCell(2).setCellValue(unreasonableAdmissionList.get(i).getAdmissionDeptName());
            rowNum.createCell(3).setCellValue(unreasonableAdmissionList.get(i).getPatientName());
            rowNum.createCell(4).setCellValue(unreasonableAdmissionList.get(i).getAdmissionDate());
            rowNum.createCell(5).setCellValue(unreasonableAdmissionList.get(i).getDischargeDate());
            rowNum.createCell(6).setCellValue(unreasonableAdmissionList.get(i).getZyts());
            rowNum.createCell(7).setCellValue(unreasonableAdmissionList.get(i).getTotalAmount());
            rowNum.createCell(8).setCellValue(unreasonableAdmissionList.get(i).getBenefitType());
            rowNum.createCell(9).setCellValue(unreasonableAdmissionList.get(i).getWesternMedicineFee());
            rowNum.createCell(10).setCellValue(unreasonableAdmissionList.get(i).getChineseMedicineYinpian());
            rowNum.createCell(11).setCellValue(unreasonableAdmissionList.get(i).getChineseMedicineForm());
            rowNum.createCell(12).setCellValue(unreasonableAdmissionList.get(i).getConsultationFee());
        }
        return wb;
    }

    public void  listUnreasonableAdmission(UnreasonableAdmissionDto dto){
        PageModel page = dto.getPageModel();
        UnreasonableAdmission unreasonableAdmission = dto.getUnreasonableAdmission();
        StringBuilder sql = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sql.append("select tfm.hisid,tfm.hospital_id,tfm.hospital_name,tfm.zyh,tfm.patient_id,tfm.admission_dept_name,tfm.patient_name,tfm.admission_date,\n" +
                "tfm.discharge_date,tfm.zyts,tfm.total_amount,tfm.benefit_type,tfm.western_medicine_fee,\n" +
                "tfm.chinese_medicine_yinpian,tfm.chinese_medicine_form,tfm.consultation_fee,tfm.inspection_fee,\n" +
                "  nvl((((tfm.western_medicine_fee + tfm.chinese_medicine_form +tfm.chinese_medicine_yinpian) / tfm.total_amount)*100),0) YFZB, nvl(((tfm.inspection_fee / tfm.total_amount)*100),0) JCFZB,\n" + 
                "  nvl(((tfm.nursing_fee / tfm.total_amount)*100),0) ZLFZB from  T_FLYCHECK_MEDICAL tfm where 1=1   ");
        if (unreasonableAdmission!=null){
            String code1 = unreasonableAdmission.getCode1();
            String code2 = unreasonableAdmission.getCode2();
            String code3 = unreasonableAdmission.getCode3();
            if (!StringUtils.isNullOrEmpty(code1) || !StringUtils.isNullOrEmpty(code2)|| !StringUtils.isNullOrEmpty(code3)) {
                if (code1.equals("& gt;")) {
                    code1 = ">";
                }
                if (code2.equals("& gt;")) {
                    code2 = ">";
                }
                if (code3.equals("& gt;")) {
                    code3 = ">";
                }
                if (code1.equals("& lt;")) {
                    code1 = "<";
                }
                if (code2.equals("& lt;")) {
                    code2 = "<";
                }
                if (code3.equals("& lt;")) {
                    code2 = "<";
                }
                if (code3.equals("& lt;")) {
                    code2 = "<";
                }
            }
            /*if (unreasonableAdmission.getHospitalName()!=null&&!unreasonableAdmission.getHospitalName().equals("")){
            sql.append("and  tfm.hospital_name='"+unreasonableAdmission.getHospitalName()+"'");
            }*/
            if (unreasonableAdmission.getHospitalId()!=null&&!unreasonableAdmission.getHospitalId().equals("")){
                sql.append("and  tfm.hospital_id in("+unreasonableAdmission.getHospitalId()+")");
            }
            if (unreasonableAdmission.getBillDateStr()!=null&&!unreasonableAdmission.getBillDateStr().equals("")){
                String billDate=unreasonableAdmission.getBillDateStr();
                String[] date = billDate.split("forbid");
             sql.append("and  tfm.bill_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");

            }
            if (unreasonableAdmission.getAdmissionDateStr()!=null&&!unreasonableAdmission.getAdmissionDateStr().equals("")){
                String AdmissionDateStr=unreasonableAdmission.getAdmissionDateStr();
                String[] date = AdmissionDateStr.split("forbid");
                sql.append("and  tfm.admission_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");
            }
            if (unreasonableAdmission.getDischargeDateStr()!=null&&!unreasonableAdmission.getDischargeDateStr().equals("")){
                String DischargeDateStr=unreasonableAdmission.getDischargeDateStr();
                String[] date = DischargeDateStr.split("forbid");
                sql.append("and  tfm.discharge_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");
            }
            if (unreasonableAdmission.getProportionOfMedicines()!=null&&!unreasonableAdmission.getProportionOfMedicines().equals("")){
                String proportionOfMedicine = unreasonableAdmission.getProportionOfMedicines();
                Float math = Float.valueOf(proportionOfMedicine) / 100;
                sql.append("and (tfm.western_medicine_fee+tfm.chinese_medicine_form+tfm.chinese_medicine_yinpian)/tfm.total_amount"+code1+math);
            }
            if (unreasonableAdmission.getInspectionFeeRatio()!=null&&!unreasonableAdmission.getInspectionFeeRatio().equals("")){
                String inspectionFeeRatio = unreasonableAdmission.getInspectionFeeRatio();
                Float math = Float.valueOf(inspectionFeeRatio) / 100;
                sql.append("and tfm.inspection_fee/tfm.total_amount"+code2+math);
            }
            if (unreasonableAdmission.getCostOfTreatment()!=null&&!unreasonableAdmission.getCostOfTreatment().equals("")){
                String costOfTreatment = unreasonableAdmission.getCostOfTreatment();
                Float math = Float.valueOf(costOfTreatment) / 100;
                sql.append("and tfm.nursing_fee/tfm.total_amount"+code3+math);
            }
        }
        System.out.println("不合理入院！！=="+sql);
        page.setQueryHql(sql.toString());
        page.setHqlParamMap(hqlParamMap);

    }
    public void  countUnreasonableAdmissions(UnreasonableAdmissionDto dto){
        PageModel page = dto.getPageModel();
        UnreasonableAdmission unreasonableAdmission = dto.getUnreasonableAdmission();
        StringBuilder sql = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sql.append("select count(1) rowsum,sum(total_amount) sum_amount"+
                "  from  T_FLYCHECK_MEDICAL where 1=1   ");
        if (unreasonableAdmission!=null){
            /*if (unreasonableAdmission.getHospitalName()!=null&&!unreasonableAdmission.getHospitalName().equals("")){
                sql.append("and  hospital_name='"+unreasonableAdmission.getHospitalName()+"'");
            }*/
            if (unreasonableAdmission.getHospitalId()!=null&&!unreasonableAdmission.getHospitalId().equals("")){
                sql.append("and  hospital_id in ("+unreasonableAdmission.getHospitalId()+")");
            }
            if (unreasonableAdmission.getBillDateStr()!=null&&!unreasonableAdmission.getBillDateStr().equals("")){
                String billDate=unreasonableAdmission.getBillDateStr();
                String[] date = billDate.split("forbid");
                sql.append("and  bill_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");

            }
            if (unreasonableAdmission.getAdmissionDateStr()!=null&&!unreasonableAdmission.getAdmissionDateStr().equals("")){
                String AdmissionDateStr=unreasonableAdmission.getAdmissionDateStr();
                String[] date = AdmissionDateStr.split("forbid");
                sql.append("and  admission_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");
            }
            if (unreasonableAdmission.getDischargeDateStr()!=null&&!unreasonableAdmission.getDischargeDateStr().equals("")){
                String DischargeDateStr=unreasonableAdmission.getDischargeDateStr();
                String[] date = DischargeDateStr.split("forbid");
                sql.append("and  discharge_date BETWEEN TO_DATE('"+date[0]+"','yyyy-MM-dd') AND TO_DATE('"+date[1]+"','yyyy-MM-dd') ");
            }
            if (unreasonableAdmission.getProportionOfMedicines()!=null&&!unreasonableAdmission.getProportionOfMedicines().equals("")){
                String proportionOfMedicine = unreasonableAdmission.getProportionOfMedicines();
                String substring = proportionOfMedicine.replace("%","");
                Float math = Float.valueOf(substring) / 100;
                sql.append("and (western_medicine_fee+chinese_medicine_form+chinese_medicine_yinpian)/total_amount>"+math);
            }
            if (unreasonableAdmission.getInspectionFeeRatio()!=null&&!unreasonableAdmission.getInspectionFeeRatio().equals("")){
                String inspectionFeeRatio = unreasonableAdmission.getInspectionFeeRatio();
                String substring = inspectionFeeRatio.replace("%","");
                Float math = Float.valueOf(substring) / 100;
                sql.append("and inspection_fee>"+math);
            }
            if (unreasonableAdmission.getCostOfTreatment()!=null&&!unreasonableAdmission.getCostOfTreatment().equals("")){
                String costOfTreatment = unreasonableAdmission.getCostOfTreatment();
                String substring = costOfTreatment.replace("%","");
                Float math = Float.valueOf(substring) / 100;
                sql.append("and nursing_fee/total_amount>"+math);
            }
        }
        System.out.println("不合理"+sql);
        page.setQueryHql(sql.toString());
        page.setHqlParamMap(hqlParamMap);

    }


}

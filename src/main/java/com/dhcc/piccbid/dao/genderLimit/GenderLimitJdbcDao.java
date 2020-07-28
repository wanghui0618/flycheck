package com.dhcc.piccbid.dao.genderLimit;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.genderLimit.GenderLimitDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugsVo;
import com.dhcc.piccbid.entity.genderLimit.GenderLimit;
import com.dhcc.piccbid.entity.genderLimit.GenderLimitVo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王明月
 * @create 2020-01-03 10:56
 */
@Component
public class GenderLimitJdbcDao {
    @Autowired
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    /**
     * 限性别主页面
     * @param dto
     */
    public void genderLimit(GenderLimitDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        sqlStr.append("SELECT distinct t1.HISID,t1.HOSPITAL_ID,t1.HOSPITAL_NAME, t1.ZYH,t1.ADMISSION_DEPT_NAME,t1.PATIENT_NAME,t1.PATIENT_GENDER,t1.ADMISSION_DATE,\n" +
                "t1.DISCHARGE_DATE,t1.ZYTS,t1.TOTAL_AMOUNT,t1.BENEFIT_TYPE,t1.WESTERN_MEDICINE_FEE,t1.CHINESE_MEDICINE_YINPIAN,\n" +
                "t1.CHINESE_MEDICINE_FORM FROM T_FLYCHECK_MEDICAL t1 ");
          if(dto.getGenderLimit()!=null){
              if( (dto.getGenderLimit().getOrgName()!=null && !"".equals(dto.getGenderLimit().getOrgName())) ||
                  (dto.getGenderLimit().getOrgCode()!=null && !"".equals(dto.getGenderLimit().getOrgCode()))){
            	  System.out.println("???");
                    sqlStr.append(",T_FLYCHECK_MEDICAL_DETAIL t2 WHERE t1.HISID = t2.HISID and 1=1 ");
              }else{
                    sqlStr.append(" WHERE 1=1 ");
              }
          }
        this.repeatCode(dto,sqlStr);
        System.out.println("性别111++"+sqlStr);
        @SuppressWarnings("unchecked")
        List<GenderLimit> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                GenderLimit.class, hqlParamMap, dto.getPageModel().getPageNo(),
                dto.getPageModel().getPageSize(), "rownum");
        int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "distinct t1.hisid", null);
        dto.getPageModel().setTotals(totals);
        dto.getPageModel().setPageData(list);
    }

    /**
     * 汇总
     * @param dto
     */
    public void gather(GenderLimitDto dto){
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        sqlStr.append(" SELECT COUNT(distinct t1.hisid) MEDICAL_COUNT,SUM(nvl(t1.TOTAL_AMOUNT,0)) MEDICAL_TOTAL,COUNT(T2.HISID) DETAIL_COUNT,\n" +
                "SUM(nvl(T2.COST,0)) DETAIL_TOTAL FROM T_FLYCHECK_MEDICAL t1 , T_FLYCHECK_MEDICAL_DETAIL t2 WHERE t1.HISID = t2.HISID ");
        this.repeatCode(dto,sqlStr);
        System.out.println("性别++"+sqlStr);
        @SuppressWarnings("unchecked")
        List<GenderLimit> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                GenderLimit.class, hqlParamMap, dto.getPageModel().getPageNo(),
                dto.getPageModel().getPageSize(), "rownum");
        int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
        dto.getPageModel().setTotals(totals);
        dto.getPageModel().setPageData(list);
    }

    /**
     * 逻辑判断代码
     * @param dto
     * @param sqlStr
     */
    private void repeatCode(GenderLimitDto dto,StringBuilder sqlStr){
        GenderLimit genderLimit = dto.getGenderLimit();
        String date[] = null;
        if(genderLimit!=null){
            String a = "";
            String b = "";
            System.out.println("orgcode1=="+genderLimit.getOrgCode());
            System.out.println("orgname1=="+genderLimit.getOrgName());
            if(genderLimit.getOrgCode()!=null&&!"".equals(genderLimit.getOrgCode())){
            String orgCode = genderLimit.getOrgCode();
            System.out.println("orgcode=="+genderLimit.getOrgCode());
            if(orgCode!=null&&!"".equals(orgCode)) {
            String[] split = orgCode.split("，|,");
            for(int i = 0 ; i < split.length ; i++) {
            	a=a+"'"+split[i]+"'";
            	if(i<split.length-1) {
            		a=a+",";
            	}
              }
            }
            }
            
            if(genderLimit.getOrgName()!=null&&!"".equals(genderLimit.getOrgName())){
            String orgName = genderLimit.getOrgName();
            System.out.println("orgname=="+genderLimit.getOrgName());
            if(orgName!=null&&!"".equals(orgName)) {
            String[] split2 = orgName.split("，|,");
            for(int i = 0 ; i < split2.length ; i++) {
            	b=b+"'"+split2[i]+"'";
            	if(i<split2.length-1) {
            		b=b+",";
            	}
            }
            }
            }
            
            
            if(genderLimit.getHospitalId()!= null && !"".equals(genderLimit.getHospitalId())){
                sqlStr.append(" AND t1.HOSPITAL_ID in("+ genderLimit.getHospitalId() +")");
            }
            /*if(genderLimit.getHospitalName()!= null && !"".equals(genderLimit.getHospitalName())){
                sqlStr.append(" AND t1.HOSPITAL_NAME = '"+ genderLimit.getHospitalName() +"' ");
            }*/
            if(dto.getBalanceDate()!= null && !"".equals(dto.getBalanceDate())){
                date = dto.getBalanceDate().split("forbid");
                sqlStr.append(" AND t1.BILL_DATE BETWEEN TO_DATE('"+ date[0] +"','yyyyMMdd') AND TO_DATE('"+ date[1] +"','yyyyMMdd') ");
            }
            if(genderLimit.getPatientGender()!= null && !"".equals(genderLimit.getPatientGender())){
                sqlStr.append(" AND t1.PATIENT_GENDER = " + " '"+ genderLimit.getPatientGender() +"' ");
            }
            if(genderLimit.getAdmissionDiseaseId()!= null && !"".equals(genderLimit.getAdmissionDiseaseId())){
                sqlStr.append(" AND t1.ADMISSION_DISEASE_ID = '"+ genderLimit.getAdmissionDiseaseId() +"' ");
            }
            if(genderLimit.getAdmissionDiseaseName()!= null && !"".equals(genderLimit.getAdmissionDiseaseName())){
                sqlStr.append(" AND t1.ADMISSION_DISEASE_NAME = '"+ genderLimit.getAdmissionDiseaseName() +"' ");
            }
            if(dto.getAdmissionDate()!= null && !"".equals(dto.getAdmissionDate())){
                date = dto.getAdmissionDate().split("forbid");
                sqlStr.append(" AND t1.ADMISSION_DATE BETWEEN TO_DATE('"+ date[0] +"','yyyyMMdd') AND TO_DATE('"+ date[1] +"','yyyyMMdd') ");
            }
            if(dto.getDischargeDate()!= null && !"".equals(dto.getDischargeDate())){
                date = dto.getDischargeDate().split("forbid");
                sqlStr.append(" AND t1.DISCHARGE_DATE BETWEEN TO_DATE('" + date[0] + "','yyyyMMdd') AND TO_DATE('" + date[1] + "','yyyyMMdd') ");
            }
           /* if(genderLimit.getItemId()!= null && !"".equals(genderLimit.getItemId())){
                sqlStr.append(" AND t2.ITEM_ID = '"+ genderLimit.getItemId() +"' ");
            }
            if(genderLimit.getItemName()!= null && !"".equals(genderLimit.getItemName())){
                if(genderLimit.getItemName().indexOf("& #40")>0||genderLimit.getItemName().indexOf("& #41")>0){
                    String str = genderLimit.getItemName().replace("& #40;", "(").replace("& #41;", ")");
                    sqlStr.append(" AND t2.ITEM_NAME = '"+ str +"' ");
                }else{
                    sqlStr.append(" AND t2.ITEM_NAME = '"+ genderLimit.getItemName() +"' ");
                }
            }*/
            if(a!=""){
                sqlStr.append(" AND (t2.ITEM_ID in("+ a+") ");
                if(b!=""){
                	 sqlStr.append(" or t2.item_id in("+ b+")) ");
	            }else {
	            	 sqlStr.append(" ) ");
	            }
            }
        }
    }


    /**
     * 导出到excel
     * @return
     */
    public XSSFWorkbook exportExcel(GenderLimitDto dto) {
        //获取数据
        StringBuilder sqlStr = new StringBuilder();
        XSSFWorkbook wb = new XSSFWorkbook();
        sqlStr.append(" select distinct t1.hisid,t1.hospital_id,t1.hospital_name,t1.zyh,t1.admission_dept_name,t1.patient_name,t1.patient_gender,t1.admission_date,\n" +
                "t1.discharge_date,t1.zyts,t1.total_amount,t1.benefit_type,t1.western_medicine_fee,t1.chinese_medicine_yinpian,\n" +
                "t1.chinese_medicine_form from t_flycheck_medical t1,t_flycheck_medical_detail t2 where t1.hisid = t2.hisid and 1=1  ");
        this.repeatCode(dto,sqlStr);
        List<GenderLimit> genderLimitList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), GenderLimit.class, null);
        XSSFSheet sheet = wb.createSheet("主单");
        // 在sheet里创建第一行
        XSSFRow row1 = sheet.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
        row1.createCell(0).setCellValue("结算单据号(唯一)");
        row1.createCell(1).setCellValue("医疗机构编码");
        row1.createCell(2).setCellValue("医疗机构名称");
        row1.createCell(3).setCellValue("住院号");
        row1.createCell(4).setCellValue("就诊科室");
        row1.createCell(5).setCellValue("患者姓名");
        row1.createCell(6).setCellValue("性别");
        row1.createCell(7).setCellValue("入院日期");
        row1.createCell(8).setCellValue("出院日期");
        row1.createCell(9).setCellValue("住院天数");
        row1.createCell(10).setCellValue("医疗总费用");
        row1.createCell(11).setCellValue("险种类型");
        row1.createCell(12).setCellValue("西药费");
        row1.createCell(13).setCellValue("中药饮片费");
        row1.createCell(14).setCellValue("中成药费");
        // 插入数据
        for (int i = 0; i < genderLimitList.size(); i++) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            XSSFRow rowNum = sheet.createRow(i + 1);
            rowNum.createCell(0).setCellValue(genderLimitList.get(i).getHisid());
            rowNum.createCell(1).setCellValue(genderLimitList.get(i).getHospitalId());
            rowNum.createCell(2).setCellValue(genderLimitList.get(i).getHospitalName());
            rowNum.createCell(3).setCellValue(genderLimitList.get(i).getZyh());
            rowNum.createCell(4).setCellValue(genderLimitList.get(i).getAdmissionDeptName());
            rowNum.createCell(5).setCellValue(genderLimitList.get(i).getPatientName());
            rowNum.createCell(6).setCellValue(genderLimitList.get(i).getPatientGender());
            rowNum.createCell(7).setCellValue(formatter.format(genderLimitList.get(i).getAdmissionDate()));
            rowNum.createCell(8).setCellValue(formatter.format(genderLimitList.get(i).getDischargeDate()));
            rowNum.createCell(9).setCellValue(genderLimitList.get(i).getZyts());
            rowNum.createCell(10).setCellValue(genderLimitList.get(i).getTotalAmount());
            rowNum.createCell(11).setCellValue(genderLimitList.get(i).getBenefitType());
            rowNum.createCell(12).setCellValue(genderLimitList.get(i).getWesternMedicineFee());
            rowNum.createCell(13).setCellValue(genderLimitList.get(i).getChineseMedicineYinpian());
            rowNum.createCell(14).setCellValue(genderLimitList.get(i).getChineseMedicineForm());
        }
        //获取数据
        StringBuilder strSql = new StringBuilder();
        strSql.append(" select t2.hisid,t2.item_name,t2.item_id_hosp,t2.item_name_hosp,t2.p_category,nvl(t2.unit_price,0) unit_price,nvl(t2.num,0) num,nvl(t2.cost,0) cost,t2.p_type\n" +
                "from  t_flycheck_medical t1,t_flycheck_medical_detail t2 where t1.hisid=t2.hisid and 1=1 ");
        this.repeatCode(dto,strSql);
        List<GenderLimitVo> genderLimitVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(strSql.toString(), GenderLimitVo.class, null);
        XSSFSheet sheet2 = wb.createSheet("明细");
        // 在sheet里创建第一行
        XSSFRow row2 = sheet2.createRow(0);
        // 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
        row2.createCell(0).setCellValue("结算单据号(唯一)");
        row2.createCell(1).setCellValue("医保项目编码");
        row2.createCell(2).setCellValue("医保项目名称");
        row2.createCell(3).setCellValue("医院项目名称");
        row2.createCell(4).setCellValue("医院项目名称");
        row2.createCell(5).setCellValue("费用类别");
        row2.createCell(6).setCellValue("单价");
        row2.createCell(7).setCellValue("数量");
        row2.createCell(8).setCellValue("总金额");
        row2.createCell(9).setCellValue("支付类别");
        // 插入数据
        for (int i = 0; i < genderLimitVoList.size(); i++){
            XSSFRow rowNum = sheet2.createRow(i + 1);
            rowNum.createCell(0).setCellValue(genderLimitVoList.get(i).getHisid());
            rowNum.createCell(1).setCellValue(genderLimitVoList.get(i).getItemId());
            rowNum.createCell(2).setCellValue(genderLimitVoList.get(i).getItemName());
            rowNum.createCell(3).setCellValue(genderLimitVoList.get(i).getItemIdHosp());
            rowNum.createCell(4).setCellValue(genderLimitVoList.get(i).getItemNameHosp());
            rowNum.createCell(5).setCellValue(genderLimitVoList.get(i).getpCategory());
            rowNum.createCell(6).setCellValue(genderLimitVoList.get(i).getUnitPrice().toString());
            rowNum.createCell(7).setCellValue(genderLimitVoList.get(i).getNum().toString());
            rowNum.createCell(8).setCellValue(genderLimitVoList.get(i).getCost().toString());
            rowNum.createCell(9).setCellValue(genderLimitVoList.get(i).getpType());
        }
        return wb;
    }

    /**
     * 结算明细
     * @param dto
     */
    public void detail(GenderLimitDto dto) {
        Map<String, Object> hqlParamMap = new HashMap<>();
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select item_id ,item_name,item_id_hosp,item_name_hosp,p_category,unit_price,num,cost,p_type\n" +
                "from T_FLYCHECK_MEDICAL_DETAIL where 1=1 ");
        GenderLimitVo genderLimitVo = dto.getGenderLimitVo();
        if (null!=genderLimitVo){
            if(null!=genderLimitVo.getHisid() && !"".equals(genderLimitVo.getHisid())){
                sqlStr.append(" and hisid = '"+ genderLimitVo.getHisid() +"'");
            }
            if(null!=genderLimitVo.getItemId() && !"".equals(genderLimitVo.getItemId())){
                sqlStr.append("and item_id = '"+genderLimitVo.getItemId()+"'");
            }

            if(null!=genderLimitVo.getpCategory() && !"".equals(genderLimitVo.getpCategory())){
                sqlStr.append("and p_category = '"+genderLimitVo.getpCategory()+"'");
            }
            if(null!=genderLimitVo.getItemName() && !"".equals(genderLimitVo.getItemName())){
                //处理英文()格式
                if(genderLimitVo.getItemName().indexOf("& #40")>0 || genderLimitVo.getItemName().indexOf("& #41")>0){
                    String str = genderLimitVo.getItemName().replace("& #40;", "(").replace("& #41;", ")");
                    sqlStr.append(" and item_name = '"+ str +"' ");
                }else{
                    sqlStr.append("and item_name = '"+genderLimitVo.getItemName()+"'");
                }
            }
        }
        @SuppressWarnings("unchecked")
        List<ChildrensDrugsVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                GenderLimitVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
                dto.getPageModel().getPageSize(), "rownum");
        int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
        dto.getPageModel().setTotals(totals);
        dto.getPageModel().setPageData(list);
    }

}



package com.dhcc.piccbid.dao.childrensDrugs;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.childrensDrugs.ChildrensDrugsDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugs;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugsVo;
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
 * @create 2019-11-23 13:34
 */
@Component
public class ChildrensDrugsDaoJdbcDao {

    @Autowired
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    /**
     * 限儿童主页面
     * @param dto
     */
    public void childrensDrugs(ChildrensDrugsDto dto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        sqlStr.append("SELECT distinct t1.HISID,t1.HOSPITAL_ID,t1.HOSPITAL_NAME, t1.ZYH,t1.ADMISSION_DEPT_NAME,t1.PATIENT_NAME,t1.PATIENT_AGE,t1.ADMISSION_DATE,\n" +
                "t1.DISCHARGE_DATE,t1.ZYTS,t1.TOTAL_AMOUNT,t1.BENEFIT_TYPE,t1.WESTERN_MEDICINE_FEE,t1.CHINESE_MEDICINE_YINPIAN,\n" +
                "t1.CHINESE_MEDICINE_FORM FROM T_FLYCHECK_MEDICAL t1 ");
        if( (dto.getChildrensDrugs().getItemId()!=null && !"".equals(dto.getChildrensDrugs().getItemId()))  ||
                (dto.getChildrensDrugs().getItemName()!=null && !"".equals(dto.getChildrensDrugs().getItemName())) ) {
            sqlStr.append(",T_FLYCHECK_MEDICAL_DETAIL t2 WHERE t1.HISID = t2.HISID and 1=1 ");
        }else{
            sqlStr.append(" WHERE 1=1 ");
        }
        this.repeatCode(dto,sqlStr);
        System.out.println("限儿童"+sqlStr);
        @SuppressWarnings("unchecked")
        List<ChildrensDrugs> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                ChildrensDrugs.class, hqlParamMap, dto.getPageModel().getPageNo(),
                dto.getPageModel().getPageSize(), "rownum");
        int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "distinct t1.hisid", null);
        dto.getPageModel().setTotals(totals);
        dto.getPageModel().setPageData(list);
    }

    /**
     * 汇总
     * @param dto
     */
    public void gather(ChildrensDrugsDto dto){
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        sqlStr.append(" SELECT COUNT(distinct t1.hisid) MEDICAL_COUNT,SUM(nvl(t1.TOTAL_AMOUNT,0)) MEDICAL_TOTAL,COUNT(T2.HISID) DETAIL_COUNT,\n" +
                "SUM(nvl(T2.COST,0)) DETAIL_TOTAL FROM T_FLYCHECK_MEDICAL t1 , T_FLYCHECK_MEDICAL_DETAIL t2 WHERE t1.HISID = t2.HISID ");
        this.repeatCode(dto,sqlStr);
        @SuppressWarnings("unchecked")
        List<ChildrensDrugs> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                ChildrensDrugs.class, hqlParamMap, dto.getPageModel().getPageNo(),
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
    private void repeatCode(ChildrensDrugsDto dto,StringBuilder sqlStr){
        ChildrensDrugs childrensDrugs = dto.getChildrensDrugs();
        String date[] = null;
        String a = "";
        String b = "";
        if(childrensDrugs.getOrgCode()!=null&&"".equals(childrensDrugs.getOrgCode())){
        String orgCode = childrensDrugs.getOrgCode();
        System.out.println("orgcode=="+orgCode);
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
        if(childrensDrugs.getOrgName()!=null&&"".equals(childrensDrugs.getOrgName())){
        String orgName = childrensDrugs.getOrgName();
        System.out.println("orgname=="+childrensDrugs.getOrgName());
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
        if(childrensDrugs!=null){
            if(childrensDrugs.getHospitalId()!= null && !"".equals(childrensDrugs.getHospitalId())){
                sqlStr.append(" AND t1.HOSPITAL_ID in("+ childrensDrugs.getHospitalId() +")");
            }
            /*if(childrensDrugs.getHospitalName()!= null && !"".equals(childrensDrugs.getHospitalName())){
                sqlStr.append(" AND t1.HOSPITAL_NAME = '"+ childrensDrugs.getHospitalName() +"' ");
            }*/
            if(dto.getBalanceDate()!= null && !"".equals(dto.getBalanceDate())){
                date = dto.getBalanceDate().split("forbid");
                sqlStr.append(" AND t1.BILL_DATE BETWEEN TO_DATE('"+ date[0] +"','yyyyMMdd') AND TO_DATE('"+ date[1] +"','yyyyMMdd') ");
            }
            if((dto.getSymbol()!= null && !"".equals(dto.getSymbol()))&(childrensDrugs.getPatientAge()!= null && !"".equals(childrensDrugs.getPatientAge()))){
                String symbol = dto.getSymbol();
                if(symbol.equals("1")){
                    symbol = ">";
                }else if(symbol.equals("-1")){
                    symbol = "<";
                }else{
                    symbol = "=";
                }
                sqlStr.append(" AND t1.PATIENT_AGE "+ symbol +" '"+ childrensDrugs.getPatientAge() +"' ");
            }
            if(childrensDrugs.getAdmissionDiseaseId()!= null && !"".equals(childrensDrugs.getAdmissionDiseaseId())){
                sqlStr.append(" AND t1.ADMISSION_DISEASE_ID = '"+ childrensDrugs.getAdmissionDiseaseId() +"' ");
            }
            /*if(childrensDrugs.getAdmissionDiseaseName()!= null && !"".equals(childrensDrugs.getAdmissionDiseaseName())){
                sqlStr.append(" AND t1.ADMISSION_DISEASE_NAME = '"+ childrensDrugs.getAdmissionDiseaseName() +"' ");
            }*/
            if(dto.getAdmissionDate()!= null && !"".equals(dto.getAdmissionDate())){
                date = dto.getAdmissionDate().split("forbid");
                sqlStr.append(" AND t1.ADMISSION_DATE BETWEEN TO_DATE('"+ date[0] +"','yyyyMMdd') AND TO_DATE('"+ date[1] +"','yyyyMMdd') ");
            }
            if(dto.getDischargeDate()!= null && !"".equals(dto.getDischargeDate())){
                date = dto.getDischargeDate().split("forbid");
                sqlStr.append(" AND t1.DISCHARGE_DATE BETWEEN TO_DATE('" + date[0] + "','yyyyMMdd') AND TO_DATE('" + date[1] + "','yyyyMMdd') ");
            }
            if(a!=""){
                sqlStr.append(" AND (t2.ITEM_ID in("+ a+") ");
                if(b!=""){
	/*                if(childrensDrugs.getItemName().indexOf("& #40")>0||childrensDrugs.getItemName().indexOf("& #41")>0){
	                    String str = childrensDrugs.getItemName().replace("& #40;", "(").replace("& #41;", ")");
	                    sqlStr.append(" AND t2.ITEM_NAME = '"+ str +"' ");
	                }else{
	                    sqlStr.append(" AND t2.ITEM_NAME = '"+ childrensDrugs.getItemName() +"' ");
	                }*/
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
    public XSSFWorkbook exportExcel(ChildrensDrugsDto dto) {
        //获取数据
        StringBuilder sqlStr = new StringBuilder();
        XSSFWorkbook wb = new XSSFWorkbook();
        sqlStr.append(" select distinct t1.hisid,t1.hospital_id,t1.hospital_name,t1.zyh,t1.admission_dept_name,t1.patient_name,t1.admission_date,\n" +
                "t1.discharge_date,t1.zyts,t1.total_amount,t1.benefit_type,t1.western_medicine_fee,t1.chinese_medicine_yinpian,\n" +
                "t1.chinese_medicine_form from t_flycheck_medical t1,t_flycheck_medical_detail t2 where t1.hisid = t2.hisid and 1=1  ");
        this.repeatCode(dto,sqlStr);
        List<ChildrensDrugs> childrensDrugsList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), ChildrensDrugs.class, null);
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
        row1.createCell(6).setCellValue("入院日期");
        row1.createCell(7).setCellValue("出院日期");
        row1.createCell(8).setCellValue("住院天数");
        row1.createCell(9).setCellValue("医疗总费用");
        row1.createCell(10).setCellValue("险种类型");
        row1.createCell(11).setCellValue("西药费");
        row1.createCell(12).setCellValue("中药饮片费");
        row1.createCell(13).setCellValue("中成药费");
        // 插入数据
        for (int i = 0; i < childrensDrugsList.size(); i++) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            XSSFRow rowNum = sheet.createRow(i + 1);
            rowNum.createCell(0).setCellValue(childrensDrugsList.get(i).getHisid());
            rowNum.createCell(1).setCellValue(childrensDrugsList.get(i).getHospitalId());
            rowNum.createCell(2).setCellValue(childrensDrugsList.get(i).getHospitalName());
            rowNum.createCell(3).setCellValue(childrensDrugsList.get(i).getZyh());
            rowNum.createCell(4).setCellValue(childrensDrugsList.get(i).getAdmissionDeptName());
            rowNum.createCell(5).setCellValue(childrensDrugsList.get(i).getPatientName());
            rowNum.createCell(6).setCellValue(formatter.format(childrensDrugsList.get(i).getAdmissionDate()));
            rowNum.createCell(7).setCellValue(formatter.format(childrensDrugsList.get(i).getDischargeDate()));
            rowNum.createCell(8).setCellValue(childrensDrugsList.get(i).getZyts());
            rowNum.createCell(9).setCellValue(childrensDrugsList.get(i).getTotalAmount());
            rowNum.createCell(10).setCellValue(childrensDrugsList.get(i).getBenefitType());
            rowNum.createCell(11).setCellValue(childrensDrugsList.get(i).getWesternMedicineFee());
            rowNum.createCell(12).setCellValue(childrensDrugsList.get(i).getChineseMedicineYinpian());
            rowNum.createCell(13).setCellValue(childrensDrugsList.get(i).getChineseMedicineForm());
        }
        //获取数据
        StringBuilder strSql = new StringBuilder();
        strSql.append(" select t2.hisid,t2.item_name,t2.item_id_hosp,t2.item_name_hosp,t2.p_category,nvl(t2.unit_price,0) unit_price,nvl(t2.num,0) num,nvl(t2.cost,0) cost,t2.p_type\n" +
                "from  t_flycheck_medical t1,t_flycheck_medical_detail t2 where t1.hisid=t2.hisid and 1=1 ");
        this.repeatCode(dto,strSql);
        List<ChildrensDrugsVo> childrensDrugsVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(strSql.toString(), ChildrensDrugsVo.class, null);
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
        for (int i = 0; i < childrensDrugsVoList.size(); i++){
            XSSFRow rowNum = sheet2.createRow(i + 1);
            rowNum.createCell(0).setCellValue(childrensDrugsVoList.get(i).getHisid());
            rowNum.createCell(1).setCellValue(childrensDrugsVoList.get(i).getItemId());
            rowNum.createCell(2).setCellValue(childrensDrugsVoList.get(i).getItemName());
            rowNum.createCell(3).setCellValue(childrensDrugsVoList.get(i).getItemIdHosp());
            rowNum.createCell(4).setCellValue(childrensDrugsVoList.get(i).getItemNameHosp());
            rowNum.createCell(5).setCellValue(childrensDrugsVoList.get(i).getpCategory());
            rowNum.createCell(6).setCellValue(childrensDrugsVoList.get(i).getUnitPrice().toString());
            rowNum.createCell(7).setCellValue(childrensDrugsVoList.get(i).getNum().toString());
            rowNum.createCell(8).setCellValue(childrensDrugsVoList.get(i).getCost().toString());
            rowNum.createCell(9).setCellValue(childrensDrugsVoList.get(i).getpType());
        }
        return wb;
    }

    /**
     * 结算明细
     * @param dto
     */
    public void detail(ChildrensDrugsDto dto) {
        Map<String, Object> hqlParamMap = new HashMap<>();
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select item_id ,item_name,item_id_hosp,item_name_hosp,p_category,unit_price,num,cost,p_type\n" +
                "from T_FLYCHECK_MEDICAL_DETAIL where 1=1 ");
        ChildrensDrugsVo childrensDrugsVo = dto.getChildrensDrugsVo();
        if (null!=childrensDrugsVo){
            if(null!=childrensDrugsVo.getHisid() && !"".equals(childrensDrugsVo.getHisid())){
                sqlStr.append(" and hisid = '"+ childrensDrugsVo.getHisid() +"'");
            }
            if(null!=childrensDrugsVo.getItemId() && !"".equals(childrensDrugsVo.getItemId())){
                sqlStr.append("and item_id = '"+childrensDrugsVo.getItemId()+"'");
            }

            if(null!=childrensDrugsVo.getpCategory() && !"".equals(childrensDrugsVo.getpCategory())){
                sqlStr.append("and p_category = '"+childrensDrugsVo.getpCategory()+"'");
            }
            if(null!=childrensDrugsVo.getItemName() && !"".equals(childrensDrugsVo.getItemName())){
                //处理英文()格式
                if(childrensDrugsVo.getItemName().indexOf("& #40")>0 || childrensDrugsVo.getItemName().indexOf("& #41")>0){
                    String str = childrensDrugsVo.getItemName().replace("& #40;", "(").replace("& #41;", ")");
                    sqlStr.append(" and item_name = '"+ str +"' ");
                }else{
                    sqlStr.append("and item_name = '"+childrensDrugsVo.getItemName()+"'");
                }
            }
        }
        @SuppressWarnings("unchecked")
        List<ChildrensDrugsVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                ChildrensDrugsVo.class, hqlParamMap, dto.getPageModel().getPageNo(),
                dto.getPageModel().getPageSize(), "rownum");
        int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", null);
        dto.getPageModel().setTotals(totals);
        dto.getPageModel().setPageData(list);
    }


}

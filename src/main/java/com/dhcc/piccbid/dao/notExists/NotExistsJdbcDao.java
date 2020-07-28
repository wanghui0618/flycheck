package com.dhcc.piccbid.dao.notExists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.notExists.NotExistsDto;
import com.dhcc.piccbid.entity.OverclockingAndAmountItem.OverclockingItemVo;
import com.dhcc.piccbid.entity.notExists.NotExists;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail_MZ;
import com.dhcc.piccbid.entity.notExists.NotExsitsmzzd;
import com.dhcc.piccbid.entity.notExists.NotExsitszyzd;
@Component
public class NotExistsJdbcDao extends HibernatePersistentObjectDAO<NotExistsDetail>{
	private static Log logger = LogFactory.getLog(NotExistsJdbcDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private  JdbcTemplateWrapper jdbcTemplateWrapper;
	
	private static String exportsql;
	/**
	 * @param 项目查询
	 */
	public void notExsits(NotExistsDto dto) {

		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
			sqlStr.append("select * from T_FLYCHECK_MEDICAL_DETAIL  ");
			@SuppressWarnings("unchecked")
			List<NotExistsDetail> listData = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
					NotExistsDetail.class, hqlParamMap, dto.getPageModel().getPageNo(),
						dto.getPageModel().getPageSize(), "rownum");
			 int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", hqlParamMap);
			 dto.getPageModel().setPageData(listData);
			 dto.getPageModel().setTotals(totals);
			 System.out.println("项目查询="+sqlStr);
			 System.out.println("totals==="+totals);
			 System.out.println("totals==="+listData.size());
	}
	
	//项目条件查询
	public void notExsits1(NotExistsDto dto) {
		String path=dto.getPath();
		String hospitalId = dto.getHospitalId();
		String billDate = dto.getBillDate();
		String itemId = dto.getItemId();
		String itemName = dto.getItemName();
		String itemId1 = dto.getItemId1();
		String itemName1 = dto.getItemName1();
		String hisid = dto.getHisid();
		String dischargeDeptName = dto.getDischargeDeptName();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if(path.equals("住院")) {
			sqlStr.append("select * from T_FLYCHECK_MEDICAL_DETAIL where 1=1 ");
		}else if(path.equals("门诊")){
			sqlStr.append("select * from T_FLYCHECK_MEDICAL_MZ_DETAIL where 1=1 ");
		}
		if(hospitalId!=null&&!"".equals(hospitalId)) {
			sqlStr.append(" and hospital_id in("+hospitalId+")");
		}
		if(billDate!=null&&!"".equals(billDate)) {
			sqlStr.append(" and to_char(bill_date,'YYYY-MM-DD')='"+billDate+"'");
		}
		if(dischargeDeptName!=null&&!"".equals(dischargeDeptName)) {
			sqlStr.append(" and discharge_dept_name like '%"+dischargeDeptName+"%'");
		}
		if(hisid!=null&&!"".equals(hisid)) {
			sqlStr.append(" and hisid ='"+hisid+"'");
		}
		if(itemId!=null&&!"".equals(itemId)) {
			sqlStr.append(" and item_id_hosp ='"+itemId+"'");
		}
		if(itemName!=null&&!"".equals(itemName)) {
			sqlStr.append(" and item_name_hosp like '%"+itemName+"%'");
		}
		if(itemId1!=null&&!"".equals(itemId1)) {
			sqlStr.append(" and item_id ='"+itemId1+"'");
		}
		if(itemName1!=null&&!"".equals(itemName1)) {
			sqlStr.append(" and item_name like '%"+itemName1+"%'");
		}
		 exportsql=sqlStr.toString();
			@SuppressWarnings("unchecked")
			List<NotExistsDetail> listData = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
					NotExistsDetail.class, hqlParamMap, dto.getPageModel().getPageNo(),
						dto.getPageModel().getPageSize(), "rownum");
			 int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", hqlParamMap);
			 dto.getPageModel().setPageData(listData);
			 dto.getPageModel().setTotals(totals);
			 System.out.println("项目条件查询="+sqlStr);
			 System.out.println("totals==="+totals);
			 System.out.println("totals==="+listData.size());
			 
	}
	
	//导出到excel
    public XSSFWorkbook exportExcel() {
        //获取数据
        StringBuilder sqlStr = new StringBuilder();
        XSSFWorkbook wb = new XSSFWorkbook();
        sqlStr.append(exportsql);
        // List<PersonalInformationInquiryVo> personalInformationInquiryVoList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), PersonalInformationInquiryVo.class, null);
        List<NotExistsDetail> overclockingItemVoDetailList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),NotExistsDetail.class,null);
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
            rowNum.createCell(5).setCellValue(overclockingItemVoDetailList.get(i).getHospitalName()	);
            rowNum.createCell(6).setCellValue(overclockingItemVoDetailList.get(i).getDischargeDeptId());
            rowNum.createCell(7).setCellValue(overclockingItemVoDetailList.get(i).getDischargeDeptName()	);
            rowNum.createCell(8).setCellValue(overclockingItemVoDetailList.get(i).getDoctorId()	);
            rowNum.createCell(9).setCellValue(overclockingItemVoDetailList.get(i).getDoctorName()	);
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

	
	
	/**
	 * @param dto
	 */
	public void countabnormalHospitalStay1(NotExistsDto dto) {
		String path=dto.getPath();
		String hospitalId = dto.getHospitalId();
		String billDate = dto.getBillDate();
		String itemId = dto.getItemId();
		String itemName = dto.getItemName();
		String itemId1 = dto.getItemId1();
		String itemName1 = dto.getItemName1();
		String hisid = dto.getHisid();
		String dischargeDeptName = dto.getDischargeDeptName();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if(("门诊").equals(path)){
			sqlStr.append("select count(1) rowsum1,nvl(sum(cost),0) SUM_AMOUNT1 from  T_FLYCHECK_MEDICAL_MZ_detail where 1=1   ");
		}else  {
			sqlStr.append("select count(1) rowsum1,nvl(sum(cost),0) SUM_AMOUNT1 from  T_FLYCHECK_MEDICAL_detail where 1=1   ");
		}
		if(hospitalId!=null&&!"".equals(hospitalId)) {
			sqlStr.append(" and hospital_id in("+hospitalId+")");
		}
		if(billDate!=null&&!"".equals(billDate)) {
			sqlStr.append(" and to_char(bill_date,'YYYY-MM-DD')='"+billDate+"'");
		}
		if(dischargeDeptName!=null&&!"".equals(dischargeDeptName)) {
			sqlStr.append(" and discharge_dept_name like '%"+dischargeDeptName+"%'");
		}
		if(hisid!=null&&!"".equals(hisid)) {
			sqlStr.append(" and hisid ='"+hisid+"'");
		}
		if(itemId!=null&&!"".equals(itemId)) {
			sqlStr.append(" and item_id_hosp ='"+itemId+"'");
		}
		if(itemName!=null&&!"".equals(itemName)) {
			sqlStr.append(" and item_name_hosp like '%"+itemName+"%'");
		}
		if(itemId1!=null&&!"".equals(itemId1)) {
			sqlStr.append(" and item_id ='"+itemId1+"'");
		}
		if(itemName1!=null&&!"".equals(itemName1)) {
			sqlStr.append(" and item_name like '%"+itemName1+"%'");
		}
			@SuppressWarnings("unchecked")
			List<NotExists> listData = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
					NotExists.class, hqlParamMap, dto.getPageModel().getPageNo(),
						dto.getPageModel().getPageSize(), "rownum");
			 int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", hqlParamMap);
			 dto.getPageModel().setPageData(listData);
			 dto.getPageModel().setTotals(totals);
			 System.out.println("明细条目"+sqlStr);
			 System.out.println("totals==="+totals);
			 System.out.println("totals==="+listData.size());
	}

	
	
	public void searchMutex(NotExistsDto dto) {
		
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
         String dialogType = dto.getDialogType();
         String hospType=dto.getHospType();
         String hospValue = dto.getHospValue();
         String dischargeType = dto.getDischargeType();
         String dischargeValue = dto.getDischargeValue();
         String itemType = dto.getItemType();
         String itemHospType = dto.getItemHospType();
         String limite = dto.getLimite();
         String mutex = dto.getMutex();
         String bbj = dto.getBbj();
         String bj = dto.getBj();
         
         System.out.println("dialogType=="+dialogType);
         System.out.println("hospType=="+hospType);
         System.out.println("hospValue=="+hospValue);
         System.out.println("dischargeType="+dischargeType);
         System.out.println("dischargeValue="+dischargeValue);
         System.out.println("itemType="+itemType);
         System.out.println("itemHospType="+itemHospType);
         System.out.println("bbj="+bbj);
         System.out.println("bj="+bj);
         System.out.println("limite="+limite);
         System.out.println("mutex="+mutex);
         
         String[] split1 = bbj.split(",|，");
         String[] split2 = bj.split(",|，");
         String[] split = hospValue.split(",|，");
         hospValue="";
         for(int i = 0 ; i <split.length ; i++) {
        	 hospValue=hospValue+"'"+split[i]+"'";
 			 if(i<split.length-1) {
 				hospValue=hospValue+",";
 			 }
 		}
         sqlStr.append("select b.* from (");
         String o="";
		 String o1="";
		 String o2="";
		 //就诊途径是住院
         if(dialogType.equals("zy")) {
        	 //同一天
        	 if(limite.equals("tt")) {
        		 //找出同时使用的
        		 if(mutex.equals("mix")) {
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+","+o1+ ")) ");//");//and rownum<100"); 
        				 if(hospType!=null&&!"".equals(hospType)) {
        				 sqlStr.append(" and hospital_id in("+hospValue+")"); 
        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+") or item_id_hosp in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+") or item_id in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }else{
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }
        	 }else {
        		 //同一次
        		 if(mutex.equals("mix")) {
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+") or item_id_hosp in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+") or item_id in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }else{
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }
        	 }
        	 if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("name")) {
    			 sqlStr.append(" and DISCHARGE_DEPT_NAME='"+dischargeValue+"'");  
    		}else if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("code")) {
    			 sqlStr.append(" and DISCHARGE_DEPT_ID='"+dischargeValue+"'");  
    		}
        	 /*if(hospType!=null&&!"".equals(hospType)) {
    			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
    				 }*/
        	 System.out.println("ttttt"+sqlStr);
        	 @SuppressWarnings("unchecked")
 			List<NotExistsDetail> listData = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
 					NotExistsDetail.class, hqlParamMap, dto.getPageModel().getPageNo(),
 						dto.getPageModel().getPageSize(), "rownum");
 			 int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", hqlParamMap);
 			 dto.getPageModel().setPageData(listData);
 			 dto.getPageModel().setTotals(totals);
 			 System.out.println(sqlStr);
 			 System.out.println("totals==="+totals);
 			 System.out.println("totals==="+listData.size());
         }else {
        	 //就诊途径是门诊，门诊不分同天和同次
        	 	//查出同时存在的
        		 if(mutex.equals("mix")) {
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+") or item_id_hosp in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+") or item_id in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }else{
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }
        		 if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("name")) {
        			 sqlStr.append(" and DISCHARGE_DEPT_NAME='"+dischargeValue+"'");  
        		}else if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("code")) {
        			 sqlStr.append(" and DISCHARGE_DEPT_ID='"+dischargeValue+"'");  
        		}
        		 System.out.println(sqlStr);
        		 @SuppressWarnings("unchecked")
                 List<NotExistsDetail_MZ> listData = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
                		 NotExistsDetail_MZ.class, hqlParamMap, dto.getPageModel().getPageNo(),
          				dto.getPageModel().getPageSize(), "rownum");
                  int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", hqlParamMap);
                  dto.getPageModel().setPageData(listData);
                  dto.getPageModel().setTotals(totals);
                  System.out.println("totalsmz==="+totals);
                  System.out.println("totalsmz==="+listData.size());
         }
	}
	
	
	public SXSSFWorkbook exportExcelToSelf(HttpServletRequest request,NotExistsDto dto) {
		
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
         String dialogType = dto.getDialogType();
         String hospType=dto.getHospType();
         String hospValue = dto.getHospValue();
         String dischargeType = dto.getDischargeType();
         String dischargeValue = dto.getDischargeValue();
         String itemType = dto.getItemType();
         String itemHospType = dto.getItemHospType();
         String limite = dto.getLimite();
         String mutex = dto.getMutex();
         String bbj = dto.getMybbj();
         String bj = dto.getMybj();
         
         System.out.println("dialogType=="+dialogType);
         System.out.println("hospType=="+hospType);
         System.out.println("hospValue=="+hospValue);
         System.out.println("dischargeType="+dischargeType);
         System.out.println("dischargeValue="+dischargeValue);
         System.out.println("itemType="+itemType);
         System.out.println("itemHospType="+itemHospType);
         System.out.println("bbj="+bbj);
         System.out.println("bj="+bj);
         System.out.println("limite="+limite);
         System.out.println("mutex="+mutex);
         
         String[] split1 = bbj.split(",|，");
         String[] split2 = bj.split(",|，");
         String[] split = hospValue.split(",|，");
         hospValue="";
         for(int i = 0 ; i <split.length ; i++) {
        	 hospValue=hospValue+"'"+split[i]+"'";
 			 if(i<split.length-1) {
 				hospValue=hospValue+",";
 			 }
 		}
         String o="";
		 String o1="";
		 String o2="";
		 //就诊途径是住院
         if(dialogType.equals("zy")) {
        	 sqlStr.append("select b.detail_id,b.hisid,b.patient_id,b.zyh,b.hospital_id,b.hospital_name,b.discharge_dept_id,b.discharge_dept_name,b.doctor_id,");
     		sqlStr.append("b.doctor_name,b.discharge_disease_name_main,b.p_category,b.usage_date,b.usage_date_flag,b.bill_date,b.year,b.month,");
     		sqlStr.append("b.item_id_hosp,b.item_name_hosp,b.item_id,b.item_name,b.drug_spec,b.dosage_form,b.package_unit,nvl(b.unit_price,0) unit_price,"); 
     		sqlStr.append("nvl(b.num,0) num,nvl(b.cost,0) cost,b.bmi_convered_amount,b.bmi_pay_amount,b.p_type,nvl(b.p_type_pct,0) p_type_pct,b.item_type from (");
        	 //同一天
        	 if(limite.equals("tt")) {
        		 //找出同时使用的
        		 if(mutex.equals("mix")) {
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+","+o1+ ")) ");//and rownum<100"); 
        				 if(hospType!=null&&!"".equals(hospType)) {
        				 sqlStr.append(" and hospital_id in("+hospValue+")"); 
        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+") or item_id_hosp in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+") or item_id in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }else{
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid,usage_date from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid and a.usage_date=b.usage_date "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }
        	 }else {
        		 //同一次
        		 if(mutex.equals("mix")) {
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+") or item_id_hosp in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+") or item_id in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }else{
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }
        	 }
        	 if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("name")) {
    			 sqlStr.append(" and DISCHARGE_DEPT_NAME='"+dischargeValue+"'");  
    		}else if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("code")) {
    			 sqlStr.append(" and DISCHARGE_DEPT_ID='"+dischargeValue+"'");  
    		}
        	 System.out.println(sqlStr);
        	 @SuppressWarnings("unchecked")
             List<NotExistsDetail> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
          		   NotExistsDetail.class, null);
              System.out.println("totals==="+list.size());
              SXSSFWorkbook wb = new SXSSFWorkbook();
              SXSSFSheet sheet=(SXSSFSheet)wb.createSheet("互斥项目查询明细");
              SXSSFRow row1 = (SXSSFRow)sheet.createRow(0);
 			row1.createCell(0).setCellValue("明细主键ID");
 			row1.createCell(1).setCellValue("单据号");
 			row1.createCell(2).setCellValue("个人编码");
 			row1.createCell(3).setCellValue("住院号");
 			row1.createCell(4).setCellValue("医疗机构编码");
 			row1.createCell(5).setCellValue("医疗机构名称");
 			row1.createCell(6).setCellValue("出院科室编码");
 			row1.createCell(7).setCellValue("出院科室名称");
 			row1.createCell(8).setCellValue("主诊医师编码");
 			row1.createCell(9).setCellValue("主诊医师姓名");
 			row1.createCell(10).setCellValue("出院诊断名称");
 			row1.createCell(11).setCellValue("费用类别");
 			row1.createCell(12).setCellValue("项目使用日期");
 			row1.createCell(13).setCellValue("项目使用日期标识");
 			row1.createCell(14).setCellValue("结算日期");
 			row1.createCell(15).setCellValue("收费年份");
 			row1.createCell(16).setCellValue("收费月份");
 			row1.createCell(17).setCellValue("医院项目编码");
 			row1.createCell(18).setCellValue("医院项目名称");
 			row1.createCell(19).setCellValue("医保项目编码");
 			row1.createCell(20).setCellValue("医保项目名称");
 			row1.createCell(21).setCellValue("规格");
 			row1.createCell(22).setCellValue("剂型");
 			row1.createCell(23).setCellValue("最小包装单位");
 			row1.createCell(24).setCellValue("单价");
 			row1.createCell(25).setCellValue("数量");
 			row1.createCell(26).setCellValue("金额");
 			row1.createCell(27).setCellValue("医保范围内金额");
 			row1.createCell(28).setCellValue("医保医保实际支付金额");
 			row1.createCell(29).setCellValue("支付类别");
 			row1.createCell(30).setCellValue("报销比例");
 			row1.createCell(31).setCellValue("项目类型");
 			for (int i = 0; i < list.size(); i++) {
 				SXSSFRow rowNum = (SXSSFRow)sheet.createRow(i + 1);
 				rowNum.createCell(0).setCellValue(list.get(i).getDetailId());
 				rowNum.createCell(1).setCellValue(list.get(i).getHisid());
 				rowNum.createCell(2).setCellValue(list.get(i).getPatientId());
 				rowNum.createCell(3).setCellValue(list.get(i).getZyh());
 				rowNum.createCell(4).setCellValue(list.get(i).getHospitalId());
 				rowNum.createCell(5).setCellValue(list.get(i).getHospitalName());
 				rowNum.createCell(6).setCellValue(list.get(i).getDischargeDeptId());
 				rowNum.createCell(7).setCellValue(list.get(i).getDischargeDeptName());
 				rowNum.createCell(8).setCellValue(list.get(i).getDoctorId());
 				rowNum.createCell(9).setCellValue(list.get(i).getDoctorName());
 				rowNum.createCell(10).setCellValue(list.get(i).getDischargeDiseaseNameMain());
 				rowNum.createCell(11).setCellValue(list.get(i).getpCategory());
 				rowNum.createCell(12).setCellValue(list.get(i).getUsageDate());
 				rowNum.createCell(13).setCellValue(list.get(i).getUsageDateFlag());
 				rowNum.createCell(14).setCellValue(list.get(i).getBillDate());
 				rowNum.createCell(15).setCellValue(list.get(i).getYear());
 				rowNum.createCell(16).setCellValue(list.get(i).getMonth());
 				rowNum.createCell(17).setCellValue(list.get(i).getItemId());
 				rowNum.createCell(18).setCellValue(list.get(i).getItemName());
 				rowNum.createCell(19).setCellValue(list.get(i).getItemIdHosp());
 				rowNum.createCell(20).setCellValue(list.get(i).getItemNameHosp());
 				rowNum.createCell(21).setCellValue(list.get(i).getDrugSpec());
 				rowNum.createCell(22).setCellValue(list.get(i).getDosageForm());
 				rowNum.createCell(23).setCellValue(list.get(i).getPackageUnit());
 				rowNum.createCell(24).setCellValue(list.get(i).getUnitPrice());
 				rowNum.createCell(25).setCellValue(list.get(i).getNum());
 				rowNum.createCell(26).setCellValue(list.get(i).getCost());
 				rowNum.createCell(27).setCellValue(list.get(i).getBmiConveredAmount());
 				rowNum.createCell(28).setCellValue(list.get(i).getBmiPayAmount());
 				rowNum.createCell(29).setCellValue(list.get(i).getpType());
 				rowNum.createCell(30).setCellValue(list.get(i).getpTypePct());
 				rowNum.createCell(31).setCellValue(list.get(i).getItemType());
 				}
 			
            SXSSFSheet sheet2=(SXSSFSheet)wb.createSheet("互斥项目查询住院主单");
            SXSSFRow row2 = (SXSSFRow)sheet2.createRow(0);
            row2.createCell(0).setCellValue("病案关联字段");
            row2.createCell(1).setCellValue("结算单据号(唯一)");
            row2.createCell(2).setCellValue("医疗机构编码");
            row2.createCell(3).setCellValue("医疗机构名称");
            row2.createCell(4).setCellValue("医保结算等级");
            row2.createCell(5).setCellValue("参保地统筹区域编码");
            row2.createCell(6).setCellValue("参保地统筹区域名称");
            row2.createCell(7).setCellValue("结算日期");
            row2.createCell(8).setCellValue("结算年份");
            row2.createCell(9).setCellValue("结算月份");
            row2.createCell(10).setCellValue("住院号");
            row2.createCell(11).setCellValue("个人编码");
            row2.createCell(12).setCellValue("患者社保卡号");
            row2.createCell(13).setCellValue("病案号");
            row2.createCell(14).setCellValue("险种类型");
            row2.createCell(15).setCellValue("人员类型");
            row2.createCell(16).setCellValue("入院科别");
            row2.createCell(17).setCellValue("转科科别");
            row2.createCell(18).setCellValue("出院科别");
            row2.createCell(19).setCellValue("主诊医师编码");
            row2.createCell(20).setCellValue("主诊医师姓名");
            row2.createCell(21).setCellValue("患者姓名");
            row2.createCell(22).setCellValue("患者性别");
            row2.createCell(23).setCellValue("患者出生日期");
            row2.createCell(24).setCellValue("患者年龄");
            row2.createCell(25).setCellValue("患者所在单位");
            row2.createCell(26).setCellValue("患者现住址");
            row2.createCell(27).setCellValue("患者床位号");
            row2.createCell(28).setCellValue("新生儿入院类型");
            row2.createCell(29).setCellValue("新生儿出生体重");
            row2.createCell(30).setCellValue("新生儿入院体重");
            row2.createCell(31).setCellValue("住院医疗类型");
            row2.createCell(32).setCellValue("异地标志");
            row2.createCell(33).setCellValue("入院日期");
            row2.createCell(34).setCellValue("出院日期");
            row2.createCell(35).setCellValue("住院天数");
            row2.createCell(36).setCellValue("离院方式");
            row2.createCell(37).setCellValue("上一次出院日期");
            row2.createCell(38).setCellValue("是否有31天内再住院计划");
            row2.createCell(39).setCellValue("医疗总费用");
            row2.createCell(40).setCellValue("基本统筹支付");
            row2.createCell(41).setCellValue("大病保险");
            row2.createCell(42).setCellValue("医疗救助");
            row2.createCell(43).setCellValue("公务员医疗补助");
            row2.createCell(44).setCellValue("大额补充");
            row2.createCell(45).setCellValue("企业补充");
            row2.createCell(46).setCellValue("个人现金支付");
            row2.createCell(47).setCellValue("个人账户支付");
            row2.createCell(48).setCellValue("个人自付");
            row2.createCell(49).setCellValue("个人自费");
            row2.createCell(50).setCellValue("符合基本医疗保险的费用");
            row2.createCell(51).setCellValue("入院诊断编码");
            row2.createCell(52).setCellValue("入院诊断名称");
            row2.createCell(53).setCellValue("出院诊断编码");
            row2.createCell(54).setCellValue("出院诊断名称");
            row2.createCell(55).setCellValue("床位费");
            row2.createCell(56).setCellValue("诊察费");
            row2.createCell(57).setCellValue("检查费");
            row2.createCell(58).setCellValue("化验费");
            row2.createCell(59).setCellValue("治疗费");
            row2.createCell(60).setCellValue("护理费");
            row2.createCell(61).setCellValue("卫生材料费");
            row2.createCell(62).setCellValue("西药费");
            row2.createCell(63).setCellValue("中药饮片费");
            row2.createCell(64).setCellValue("中成药费");
            row2.createCell(65).setCellValue("一般诊疗费");
            row2.createCell(66).setCellValue("挂号费");
            row2.createCell(67).setCellValue("其他费");
            row2.createCell(68).setCellValue("医保支付方式");
            row2.createCell(69).setCellValue("病组\\病种编码");
            row2.createCell(70).setCellValue("病组\\病种名称");
            row2.createCell(71).setCellValue("审核状态,0违规/1疑似违规/null正常");
            row2.createCell(72).setCellValue("住院次数");
            row2.createCell(73).setCellValue("违规数量");
            row2.createCell(74).setCellValue("违规金额");
            StringBuilder mysql=new StringBuilder();
   		 	mysql=sqlStr.replace(0, 498, "select c.*,a.znum,a.zcost from (select b.hisid,sum(num) znum,sum(cost) zcost ");
            mysql.append("  group by b.hisid) a left join T_FLYCHECK_MEDICAL c on a.hisid=c.hisid ");
            System.out.println("mysql"+mysql);
   		   @SuppressWarnings("unchecked")
	            List<NotExsitszyzd> list3 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(mysql.toString(),
	            		 NotExsitszyzd.class, null);
	              System.out.println("totals==="+list.size());
            for (int i = 0; i < list3.size(); i++) {
	  				SXSSFRow rowNum = (SXSSFRow)sheet2.createRow(i + 1);
	  				rowNum.createCell(0).setCellValue(list3.get(i).getBridgeId());
	  				rowNum.createCell(1).setCellValue(list3.get(i).getHisid());
	  				rowNum.createCell(2).setCellValue(list3.get(i).getHospitalId());
	  				rowNum.createCell(3).setCellValue(list3.get(i).getHospitalName());
	  				rowNum.createCell(4).setCellValue(list3.get(i).getpLevel());
	  				rowNum.createCell(5).setCellValue(list3.get(i).getBmiAreaId());
	  				rowNum.createCell(6).setCellValue(list3.get(i).getBmiAreaName());
	  				rowNum.createCell(7).setCellValue(list3.get(i).getBillDate());
	  				rowNum.createCell(8).setCellValue(list3.get(i).getYear());
	  				rowNum.createCell(9).setCellValue(list3.get(i).getMonth());
	  				rowNum.createCell(10).setCellValue(list3.get(i).getZyh());
	  				rowNum.createCell(11).setCellValue(list3.get(i).getPatientId());
	  				rowNum.createCell(12).setCellValue(list3.get(i).getSocialCardId());
	  				rowNum.createCell(13).setCellValue(list3.get(i).getMedicalRecordId());
	  				rowNum.createCell(14).setCellValue(list3.get(i).getBenefitGroupId());
	  				rowNum.createCell(15).setCellValue(list3.get(i).getBenefitGroupId());
	  				rowNum.createCell(16).setCellValue(list3.get(i).getAdmissionDeptName());
	  				rowNum.createCell(17).setCellValue(list3.get(i).getTransferDeptName());
	  				rowNum.createCell(18).setCellValue(list3.get(i).getDischargeDeptName());
	  				rowNum.createCell(19).setCellValue(list3.get(i).getDoctorId());
	  				rowNum.createCell(20).setCellValue(list3.get(i).getDoctorName());
	  				rowNum.createCell(21).setCellValue(list3.get(i).getPatientName());
	  				rowNum.createCell(22).setCellValue(list3.get(i).getPatientGender());
	  				rowNum.createCell(23).setCellValue(list3.get(i).getPatientBirthday());
	  				rowNum.createCell(24).setCellValue(list3.get(i).getPatientAge());
	  				rowNum.createCell(25).setCellValue(list3.get(i).getPatientCompany());
	  				rowNum.createCell(26).setCellValue(list3.get(i).getPatientAddress());
	  				rowNum.createCell(27).setCellValue(list3.get(i).getBedid());
	  				rowNum.createCell(28).setCellValue(list3.get(i).getNbType());
	  				rowNum.createCell(29).setCellValue(list3.get(i).getNbBirthWeight());
	  				rowNum.createCell(30).setCellValue(list3.get(i).getNbInpatientWeight());
	  				rowNum.createCell(31).setCellValue(list3.get(i).getClaimType());
	  				rowNum.createCell(32).setCellValue(list3.get(i).getIfLocalFlag());
	  				rowNum.createCell(33).setCellValue(list3.get(i).getAdmissionDate());
	  				rowNum.createCell(34).setCellValue(list3.get(i).getDischargeDate());
	  				rowNum.createCell(35).setCellValue(list3.get(i).getZyts());
	  				rowNum.createCell(36).setCellValue(list3.get(i).getDischargeStatus());
	  				rowNum.createCell(37).setCellValue(list3.get(i).getPreAdmissionDate());
	  				rowNum.createCell(38).setCellValue(list3.get(i).get_1daysReAdmission());
	  				rowNum.createCell(39).setCellValue(list3.get(i).getTotalAmount());
	  				rowNum.createCell(40).setCellValue(list3.get(i).getBmiPayAmount());
	  				rowNum.createCell(41).setCellValue(list3.get(i).getDbbx());
	  				rowNum.createCell(42).setCellValue(list3.get(i).getYljz());
	  				rowNum.createCell(43).setCellValue(list3.get(i).getGwybz());
	  				rowNum.createCell(44).setCellValue(list3.get(i).getDebc());
	  				rowNum.createCell(45).setCellValue(list3.get(i).getQybc());
	  				rowNum.createCell(46).setCellValue(list3.get(i).getCash());
	  				rowNum.createCell(47).setCellValue(list3.get(i).getSelfPayAmount());
	  				rowNum.createCell(48).setCellValue(list3.get(i).getSelfPayIn());
	  				rowNum.createCell(49).setCellValue(list3.get(i).getSelfPayOut());
	  				rowNum.createCell(50).setCellValue(list3.get(i).getBmiConveredAmount());
	  				rowNum.createCell(51).setCellValue(list3.get(i).getAdmissionDiseaseId());
	  				rowNum.createCell(52).setCellValue(list3.get(i).getAdmissionDiseaseName());
	  				rowNum.createCell(53).setCellValue(list3.get(i).getDischargeDiseaseIdMain());
	  				rowNum.createCell(54).setCellValue(list3.get(i).getDischargeDiseaseNameMain());
	  				rowNum.createCell(55).setCellValue(list3.get(i).getAccommodationFee());
	  				rowNum.createCell(56).setCellValue(list3.get(i).getDiagnosisFee());
	  				rowNum.createCell(57).setCellValue(list3.get(i).getInspectionFee());
	  				rowNum.createCell(58).setCellValue(list3.get(i).getTestFee());
	  				rowNum.createCell(59).setCellValue(list3.get(i).getTreatmentFee());
	  				rowNum.createCell(60).setCellValue(list3.get(i).getNursingFee());
	  				rowNum.createCell(61).setCellValue(list3.get(i).getMaterialFee());
	  				rowNum.createCell(62).setCellValue(list3.get(i).getWesternMedicineFee());
	  				rowNum.createCell(63).setCellValue(list3.get(i).getChineseMedicineYinpian());
	  				rowNum.createCell(64).setCellValue(list3.get(i).getChineseMedicineForm());
	  				rowNum.createCell(65).setCellValue(list3.get(i).getConsultationFee());
	  				rowNum.createCell(66).setCellValue(list3.get(i).getRegistrationFee());
	  				rowNum.createCell(67).setCellValue(list3.get(i).getOtherFee());
	  				rowNum.createCell(68).setCellValue(list3.get(i).getYbPayType());
	  				rowNum.createCell(69).setCellValue(list3.get(i).getDrgsCode());
	  				rowNum.createCell(70).setCellValue(list3.get(i).getDrgsName());
	  				rowNum.createCell(71).setCellValue(list3.get(i).getSysStatus());
	  				rowNum.createCell(72).setCellValue(list3.get(i).getStayTimes());
	  				rowNum.createCell(73).setCellValue(list3.get(i).getZnum());
	  				rowNum.createCell(74).setCellValue(list3.get(i).getZcost());
	         }
 			return wb; 
         }else {
        	 //就诊途径是门诊，门诊不分同天和同次
        	 sqlStr.append("select mz_detail_id,b.hisid,patient_id,hospital_id,hospital_name,discharge_dept_id,doctor_id,doctor_name,discharge_disease_name_main,"); 
    		 sqlStr.append("bill_date,year,month,item_id_hosp,item_name_hosp,item_id,item_name,drug_spec,dosage_form,package_unit,nvl(unit_price,0) unit_price,nvl(num,0) num,nvl(cost,0) cost,");
    		 sqlStr.append("bmi_convered_amount,bmi_pay_amount,p_type,p_category,item_type,discharge_dept_name from (");
        	 	//查出同时存在的
        		 if(mutex.equals("mix")) {
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+") or item_id_hosp in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+") or item_id in("+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100");
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+","+o1+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")"+o2+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+" item_id_hosp in ("+o+ ")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" intersect ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+o+ ") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }else{
        			 if(itemType.equals("code")&&itemHospType.equals("code1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s22"+sqlStr);
	        			 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s33"+sqlStr);
        				 
        			 }else if(itemType.equals("code")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s44"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s55"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s66"+sqlStr);
	        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s77"+sqlStr);
        			 
        			 }else if(itemType.equals("name")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name_hosp like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s88"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("code1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s99"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("name1")) {
        				 
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s10"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospcode1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o1=o1+"'"+split2[i]+"'";
        					 }else {
        						 o1=o1+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o1+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s11"+sqlStr);
        				 
        			 }else if(itemType.equals("hospcode")&&itemHospType.equals("hospname1")) {

        				 for(int i = 0 ; i<split1.length ; i++) {
        					 if(i==split1.length-1) {
        						 o=o+"'"+split1[i]+"'";
        					 }else {
        						 o=o+"'"+split1[i]+"',";
        					 }
        				 }
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 o2=o2+" or item_name_hosp like '%"+o1+"%'";
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where (item_id_hosp in("+o+")) ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s12"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("code1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s13"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("name1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name like '%"); 
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        					 o=o+" item_name like '%"+o1+"%'";
        					 }else {
        					 o=o+" or item_name like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s14"+sqlStr);
	        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospcode1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
        				 for(int i = 0 ; i<split2.length ; i++) {
        					 if(i==split2.length-1) {
        						 o=o+"'"+split2[i]+"'";
        					 }else {
        						 o=o+"'"+split2[i]+"',";
        					 }
        				 }  
        				 sqlStr.append("select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_id_hosp in("+o+")");
        				 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
        				 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
        				 System.out.println("s15"+sqlStr);
        			 
        			 }else if(itemType.equals("hospname")&&itemHospType.equals("hospname1")) {
        				 
        				 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%");
        				 for(int i = 0 ; i<split1.length ; i++) {
        					 o1=split1[i];
        					 if(i<split1.length-1) {
            					 o2=o2+"  item_name_hosp like '%"+o1+"%' or";
        					 }else {
        						 o2=o2+"  item_name_hosp like '%"+o1+"%' ";
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split1.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }
	        			 sqlStr.append(" minus ");
	        			 sqlStr.append(" select hisid  from T_FLYCHECK_MEDICAL_MZ_DETAIL where item_name_hosp like '%"); 
	        			 o1="";
	        			 for(int i = 0 ; i<split2.length ; i++) {
        					 o1=split2[i];
        					 if(i==0) {
        						 o=o+" item_name_hosp like '%"+o1+"%'"; 
        					 }else {
            					 o=o+" or item_name_hosp like '%"+o1+"%'"; 
        					 }
        					 sqlStr.append(o1+"%'");
        					 if(i<split2.length-1) {
        						 sqlStr.append(" or item_name_hosp like '%"); 
        					 }
        				 }  
	        			 sqlStr.append(") a left join  T_FLYCHECK_MEDICAL_MZ_DETAIL b on a.hisid=b.hisid  "); 
	        			 sqlStr.append(" where ("+o2+") ");//and rownum<100"); 
	        			 if(hospType!=null&&!"".equals(hospType)) {
	        			 sqlStr.append(" and hospital_id in("+hospValue+")"); 
	        				 }
	        			 System.out.println("s16"+sqlStr);
	        			 
        			 }
        		 }
        		 if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("name")) {
        			 sqlStr.append(" and DISCHARGE_DEPT_NAME='"+dischargeValue+"'");  
        		}else if(dischargeValue!=null&&!"".equals(dischargeValue)&&dischargeType.equals("code")) {
        			 sqlStr.append(" and DISCHARGE_DEPT_ID='"+dischargeValue+"'");  
        		}
        		 System.out.println(sqlStr);
        		 @SuppressWarnings("unchecked")
	             List<NotExistsDetail_MZ> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
	            		 NotExistsDetail_MZ.class, null);
	              System.out.println("totalsmzmx==="+list.size());
	              SXSSFWorkbook wb = new SXSSFWorkbook();
	              SXSSFSheet sheet=(SXSSFSheet)wb.createSheet("互斥项目查询门诊明细");
	              SXSSFRow row1 = (SXSSFRow)sheet.createRow(0);
		  			row1.createCell(0).setCellValue("门诊明细主键ID");
		  			row1.createCell(1).setCellValue("单据号");
		  			row1.createCell(2).setCellValue("个人编码");
		  			row1.createCell(3).setCellValue("医疗机构编码");
		  			row1.createCell(4).setCellValue("医疗机构名称");
		  			row1.createCell(5).setCellValue("科室编码");
		  			row1.createCell(6).setCellValue("科室名称");
		  			row1.createCell(7).setCellValue("医师编码");
		  			row1.createCell(8).setCellValue("医师名称");
		  			row1.createCell(9).setCellValue("出院诊断名称");
		  			row1.createCell(10).setCellValue("费用类别");
		  			row1.createCell(11).setCellValue("结算日期");
		  			row1.createCell(12).setCellValue("收费年份");
		  			row1.createCell(13).setCellValue("收费月份");
		  			row1.createCell(14).setCellValue("医院项目编码");
		  			row1.createCell(15).setCellValue("医院项目名称");
		  			row1.createCell(16).setCellValue("医保项目编码");
		  			row1.createCell(17).setCellValue("医保项目名称");
		  			row1.createCell(18).setCellValue("规格");
		  			row1.createCell(19).setCellValue("剂型");
		  			row1.createCell(20).setCellValue("最小包装单位");
		  			row1.createCell(21).setCellValue("单价");
		  			row1.createCell(22).setCellValue("数量");
		  			row1.createCell(23).setCellValue("金额");
		  			row1.createCell(24).setCellValue("医保范围内金额");
		  			row1.createCell(25).setCellValue("医保医保实际支付金额");
		  			row1.createCell(26).setCellValue("支付类别");
		  			row1.createCell(27).setCellValue("项目类型");
		  			for (int i = 0; i < list.size(); i++) {
		  				SXSSFRow rowNum = (SXSSFRow)sheet.createRow(i + 1);
		  				rowNum.createCell(0).setCellValue(list.get(i).getMzDetailId());
		  				rowNum.createCell(1).setCellValue(list.get(i).getHisid());
		  				rowNum.createCell(2).setCellValue(list.get(i).getPatientId());
		  				rowNum.createCell(3).setCellValue(list.get(i).getHospitalId());
		  				rowNum.createCell(4).setCellValue(list.get(i).getHospitalName());
		  				rowNum.createCell(5).setCellValue(list.get(i).getDichargeDeptId());
		  				rowNum.createCell(6).setCellValue(list.get(i).getDischargeDeptName());
		  				rowNum.createCell(7).setCellValue(list.get(i).getDoctorId());
		  				rowNum.createCell(8).setCellValue(list.get(i).getDoctorName());
		  				rowNum.createCell(9).setCellValue(list.get(i).getDischargeDiseaseNameMain());
		  				rowNum.createCell(10).setCellValue(list.get(i).getpCategory());
		  				rowNum.createCell(11).setCellValue(list.get(i).getBillDate());
		  				rowNum.createCell(12).setCellValue(list.get(i).getYear());
		  				rowNum.createCell(13).setCellValue(list.get(i).getMonth());
		  				rowNum.createCell(14).setCellValue(list.get(i).getItemId());
		  				rowNum.createCell(15).setCellValue(list.get(i).getItemName());
		  				rowNum.createCell(16).setCellValue(list.get(i).getItemIdHosp());
		  				rowNum.createCell(17).setCellValue(list.get(i).getItemNameHosp());
		  				rowNum.createCell(18).setCellValue(list.get(i).getDrugSpec());
		  				rowNum.createCell(19).setCellValue(list.get(i).getDosageForm());
		  				rowNum.createCell(20).setCellValue(list.get(i).getPackageUnit());
		  				rowNum.createCell(21).setCellValue(list.get(i).getUnitPrice());
		  				rowNum.createCell(22).setCellValue(list.get(i).getNum());
		  				rowNum.createCell(23).setCellValue(list.get(i).getCost());
		  				rowNum.createCell(24).setCellValue(list.get(i).getBmiConveredAmount());
		  				rowNum.createCell(25).setCellValue(list.get(i).getBmiPayAmount());
		  				rowNum.createCell(26).setCellValue(list.get(i).getpType());
		  				rowNum.createCell(27).setCellValue(list.get(i).getItemType());
		         }
		  			
		              SXSSFSheet sheet2=(SXSSFSheet)wb.createSheet("互斥项目查询门诊主单");
		              SXSSFRow row2 = (SXSSFRow)sheet2.createRow(0);
		              row2.createCell(0).setCellValue("结算单据号(唯一)");
		              row2.createCell(1).setCellValue("医疗机构编码");
		              row2.createCell(2).setCellValue("医疗机构名称");
		              row2.createCell(3).setCellValue("医院级别");
		              row2.createCell(4).setCellValue("统筹区域编码");
		              row2.createCell(5).setCellValue("统筹区域名称");
		              row2.createCell(6).setCellValue("结算日期");
		              row2.createCell(7).setCellValue("结算年份");
		              row2.createCell(8).setCellValue("结算月份");
		              row2.createCell(9).setCellValue("个人编码");
		              row2.createCell(10).setCellValue("患者社保卡号");
		              row2.createCell(11).setCellValue("险种类型");
		              row2.createCell(12).setCellValue("人员类别");
		              row2.createCell(13).setCellValue("科室类别");
		              row2.createCell(14).setCellValue("主诊医师编码");
		              row2.createCell(15).setCellValue("主诊医师名称");
		              row2.createCell(16).setCellValue("患者姓名");
		              row2.createCell(17).setCellValue("患者性别");
		              row2.createCell(18).setCellValue("患者出生日期");
		              row2.createCell(19).setCellValue("患者年龄");
		              row2.createCell(20).setCellValue("就医类型");
		              row2.createCell(21).setCellValue("异地标志");
		              row2.createCell(22).setCellValue("诊断编码");
		              row2.createCell(23).setCellValue("诊断名称");
		              row2.createCell(24).setCellValue("手术及操作编码");
		              row2.createCell(25).setCellValue("手术及操作名称");
		              row2.createCell(26).setCellValue("医疗总费用");
		              row2.createCell(27).setCellValue("基本统筹支付");
		              row2.createCell(28).setCellValue("现金支付");
		              row2.createCell(29).setCellValue("个人账户");
		              row2.createCell(30).setCellValue("符合基本医疗保险的费用");
		              row2.createCell(31).setCellValue("甲类费用金额");
		              row2.createCell(32).setCellValue("乙类费用金额");
		              row2.createCell(33).setCellValue("丙类费用金额（自费）");
		              row2.createCell(34).setCellValue("床位费");
		              row2.createCell(35).setCellValue("诊察费");
		              row2.createCell(36).setCellValue("检查费");
		              row2.createCell(37).setCellValue("化验费");
		              row2.createCell(38).setCellValue("治疗费");
		              row2.createCell(39).setCellValue("护理费");
		              row2.createCell(40).setCellValue("卫生材料费");
		              row2.createCell(41).setCellValue("西药费");
		              row2.createCell(42).setCellValue("中药饮片费");
		              row2.createCell(43).setCellValue("中成药费");
		              row2.createCell(44).setCellValue("一般诊疗费");
		              row2.createCell(45).setCellValue("挂号费");
		              row2.createCell(46).setCellValue("其他费");
		              row2.createCell(47).setCellValue("审核状态");
		              row2.createCell(48).setCellValue("违规数量");
		              row2.createCell(49).setCellValue("违规金额");

		              StringBuilder mysql=new StringBuilder();
		     		 	mysql=sqlStr.replace(0, 378, "select c.*,a.znum,a.zcost from (select b.hisid,sum(num) znum,sum(cost) zcost ");
		              mysql.append("  group by b.hisid) a left join T_FLYCHECK_MEDICAL_MZ c on a.hisid=c.hisid ");
		              System.out.println("mysql"+mysql);
		     		   @SuppressWarnings("unchecked")
		  	            List<NotExsitsmzzd> list3 = jdbcTemplateWrapper.queryAllMatchListWithParaMap(mysql.toString(),
		  	            		 NotExsitsmzzd.class, null);
		  	              System.out.println("totalsmzzd==="+list.size());
		              for (int i = 0; i < list3.size(); i++) {
		  	  			SXSSFRow rowNum = (SXSSFRow)sheet2.createRow(i + 1);
		  	  		rowNum.createCell(0).setCellValue(list3.get(i).getHisid());
		  	  		rowNum.createCell(1).setCellValue(list3.get(i).getHospitalId());
		  	  		rowNum.createCell(2).setCellValue(list3.get(i).getHospitalName());
		  	  		rowNum.createCell(3).setCellValue(list3.get(i).getpLevel());
		  	  		rowNum.createCell(4).setCellValue(list3.get(i).getBmiAreaId());
		  	  		rowNum.createCell(5).setCellValue(list3.get(i).getBmiAreaName());
		  	  		rowNum.createCell(6).setCellValue(list3.get(i).getBillDate());
		  	  		rowNum.createCell(7).setCellValue(list3.get(i).getYear());
		  	  		rowNum.createCell(8).setCellValue(list3.get(i).getMonth());
		  	  		rowNum.createCell(9).setCellValue(list3.get(i).getPatientId());
		  	  		rowNum.createCell(10).setCellValue(list3.get(i).getSocialCardId());
		  	  		rowNum.createCell(11).setCellValue(list3.get(i).getBenefitType());
		  	  		rowNum.createCell(12).setCellValue(list3.get(i).getBenefitGroupId());
		  	  		rowNum.createCell(13).setCellValue(list3.get(i).getAdmissionDeptName());
		  	  		rowNum.createCell(14).setCellValue(list3.get(i).getDoctorId());
		  	  		rowNum.createCell(15).setCellValue(list3.get(i).getDoctorName());
		  	  		rowNum.createCell(16).setCellValue(list3.get(i).getPatientName());
		  	  		rowNum.createCell(17).setCellValue(list3.get(i).getPatientGender());
		  	  		rowNum.createCell(18).setCellValue(list3.get(i).getPatientBirthday());
		  	  		rowNum.createCell(19).setCellValue(list3.get(i).getPatientAge());
		  	  		rowNum.createCell(20).setCellValue(list3.get(i).getClaimType());
		  	  		rowNum.createCell(21).setCellValue(list3.get(i).getIfLocalFlag());
		  	  		rowNum.createCell(22).setCellValue(list3.get(i).getAdmissionDeptName());
		  	  		rowNum.createCell(23).setCellValue(list3.get(i).getAdmissionDiseaseName());
		  	  		rowNum.createCell(24).setCellValue(list3.get(i).getIcd9Code());
		  	  		rowNum.createCell(25).setCellValue(list3.get(i).getIcd9Name());
		  	  		rowNum.createCell(26).setCellValue(list3.get(i).getTotalAmount());
		  	  		rowNum.createCell(27).setCellValue(list3.get(i).getBmiPayAmount());
		  	  		rowNum.createCell(28).setCellValue(list3.get(i).getCash());
		  	  		rowNum.createCell(29).setCellValue(list3.get(i).getSelfPayAmount());
		  	  		rowNum.createCell(30).setCellValue(list3.get(i).getBmiConveredAmount());
		  	  		rowNum.createCell(31).setCellValue(list3.get(i).getaTypeAmount());
		  	  		rowNum.createCell(32).setCellValue(list3.get(i).getbTypeAmount());
		  	  		rowNum.createCell(33).setCellValue(list3.get(i).getcTypeAmount());
		  	  		rowNum.createCell(34).setCellValue(list3.get(i).getAccommodationFee());
		  	  		rowNum.createCell(35).setCellValue(list3.get(i).getDiagnosisFee());
		  	  		rowNum.createCell(36).setCellValue(list3.get(i).getInspectionFee());
		  	  		rowNum.createCell(37).setCellValue(list3.get(i).getTestFee());
		  	  		rowNum.createCell(38).setCellValue(list3.get(i).getTreatmentFee());
		  	  		rowNum.createCell(39).setCellValue(list3.get(i).getNursingFee());
		  	  		rowNum.createCell(40).setCellValue(list3.get(i).getMaterialFee());
		  	  		rowNum.createCell(41).setCellValue(list3.get(i).getWesternMedicineFee());
		  	  		rowNum.createCell(42).setCellValue(list3.get(i).getChineseMedicineYinpian());
		  	  		rowNum.createCell(43).setCellValue(list3.get(i).getChineseMedicineForm());
		  	  		rowNum.createCell(44).setCellValue(list3.get(i).getConsultationFee());
		  	  		rowNum.createCell(45).setCellValue(list3.get(i).getRegistrationFee());
		  	  		rowNum.createCell(46).setCellValue(list3.get(i).getOtherFee());
		  	  		rowNum.createCell(47).setCellValue(list3.get(i).getSysStatus());
		  	  		rowNum.createCell(48).setCellValue(list3.get(i).getZnum());
		  	  		rowNum.createCell(49).setCellValue(list3.get(i).getZcost());
		  	         }
		  			return wb;
	        	 }
         }
	
	public  void searchMutex1(NotExistsDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
			
	
			sqlStr.append("select * from T_FLYCHECK_MEDICAL_DETAIL where rownum<100 ");
			@SuppressWarnings("unchecked")
			List<NotExistsDetail> listData = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
					NotExistsDetail.class, hqlParamMap, dto.getPageModel().getPageNo(),
						dto.getPageModel().getPageSize(), "rownum");
			 int totals = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "*", hqlParamMap);
			 dto.getPageModel().setPageData(listData);
			 dto.getPageModel().setTotals(totals);
			 System.out.println("aa="+dto.getDischargeType());
			 System.out.println(sqlStr);
			 System.out.println("totals==="+totals);
			 System.out.println("totals==="+listData.size());
	}

	
}

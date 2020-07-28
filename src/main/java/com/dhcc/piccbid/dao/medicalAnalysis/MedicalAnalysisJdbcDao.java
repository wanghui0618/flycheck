package com.dhcc.piccbid.dao.medicalAnalysis;
import java.util.Calendar;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dhcc.piccbid.dto.medicalAnalysis.MedicalResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
//import com.dhcc.piccbid.dao.dataquality.DataqualityJdbcDao;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto;
import com.dhcc.piccbid.entity.medicalAnalysis.YearDataVo;
import com.dhcc.piccbid.entity.medicalAnalysis.AbnormalCostVo;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.entity.home.HomeVo;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;
//import com.dhcc.piccbid.entity.medical.MedicalNumVo;
import com.dhcc.piccbid.entity.medicalAnalysis.DrugCostDataVo;
import com.dhcc.piccbid.entity.medicalAnalysis.HospitalVo;
import com.dhcc.piccbid.entity.medicalAnalysis.MaterialCostDataVo;
import com.dhcc.piccbid.entity.medicalAnalysis.MoneyVo;
import com.dhcc.piccbid.entity.medicalAnalysis.MonthlyTrendsDataVo;
import com.dhcc.piccbid.entity.medicalAnalysis.ServiceCostVo;
import com.dhcc.piccbid.entity.medicalAnalysis.SingleDisease;
import com.dhcc.piccbid.entity.medicalAnalysis.TotalIllDataVo;
import com.dhcc.piccbid.service.unit.UnitService;
import com.mysql.fabric.xmlrpc.base.Array;

import javassist.compiler.ast.IntConst;

/**
* <p>标题: medicalAnalysisJdbcDao.java</p>
* <p>业务描述:基层医疗卫生开发平台</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2013</p>
* @author 姚凯
* @date 2019年8月8日
* @version V1.0 
*/
@Component
public class MedicalAnalysisJdbcDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	@Autowired
	private UnitService unitService;
	
	
	public void yearData(MedicalAnalysisDto dto) {
		String inFlag = dto.getInFlag();
		String code = dto.getFindOrgName();
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select * from t_VMATER_Autonomous_yearData where balance_date='"+inFlag+"' and role_id = '"+roleId+"'");
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' = t_VMATER_Autonomous_yearData.org_code ");
		}
		sqlStr.append("order by TOTAL_NUMBER desc");
		@SuppressWarnings("unchecked")
		List<YearDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), YearDataVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
	}
	
	
	public void singleDisease(MedicalAnalysisDto dto) {
		String class1 = dto.getClass1();
		String level = dto.getLevel();
		StringBuilder sqlStr = new StringBuilder();
		String inFlag = dto.getInFlag();
		//System.out.println(inFlag);
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*sqlStr.append(" select d.* "
					 +" from (select condition as ill_name,sum(total_cost) as total_cost, "
					 +" sum(case when ORG_LEVEL = '三级' then total_cost else 0 end ) as total_cost1, "
					 +" sum(case when ORG_LEVEL = '二级' then total_cost else 0 end ) as total_cost2, "
					 +" sum(case when ORG_LEVEL = '一级' then total_cost else 0 end ) as total_cost3, "
					 +" sum(case when ORG_LEVEL = '社区' then total_cost else 0 end ) as total_cost4,diag_type "
					 +" from (select condition,total_cost,diag_type,b.ORG_LEVEL  "
					 +" from t_piccbid_medical a "
					 +" left join t_piccbid_dict_city_org b "
					 +" on a.org_code = b.org_code) c "
					 +" group by c.condition,c.diag_type) d "
					 );*/
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		sqlStr.append("select \r\n" + 
				"sum(total_amount) as total_cost,\r\n" + 
				"sum(case when p_level like '%三级%' then total_amount else 0 end) as total_cost1,\r\n" + 
				"sum(case when p_level like '%二级%' then total_amount else 0 end) as total_cost2,\r\n" + 
				"sum(case when p_level like '%一级%' then total_amount else 0 end) as total_cost3,\r\n" + 
				"sum(case when p_level like '%社区%' then total_amount else 0 end) as total_cost4,\r\n" + 
				"ADMISSION_DISEASE_NAME as ill_name,\r\n" + 
				"count(rownum) as ill_number,\r\n" + 
				"round(Avg(total_amount),2) as pj_cost,\r\n" + 
				"sum(BMI_CONVERED_AMOUNT) as total_pay,\r\n" + 
				"round(Avg(BMI_CONVERED_AMOUNT),2) as pj_pay ");
		if(class1!=null && !"".equals(class1) && !class1.equals("1")) {
			sqlStr.append(" from t_flycheck_medical_mz where year = '"+inFlag+"' ");  //查门诊
			if(class1.equals("3")) {
				sqlStr.append("and claim_type = '特殊病门诊' ");
			}
			else {
				sqlStr.append("and claim_type = '普通门诊' ");
			}
		}
		else {
			sqlStr.append(" from t_flycheck_medical where year =  '"+inFlag+"' ");   //住院
		}
		if(level!=null && !"".equals(level)) {
			sqlStr.append(" and ADMISSION_DISEASE_NAME like '%"+level+"%' ");
		}		
		sqlStr.append(" group by ADMISSION_DISEASE_NAME order by total_cost desc nulls last ");
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<YearDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), SingleDisease.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "*");
		List<YearDataVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),SingleDisease.class, hqlParamMap);
		//int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"*", hqlParamMap);
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void abnormalCost(MedicalAnalysisDto dto) {
		String illName = dto.getIllName();
		//System.out.println(illName+"-------------------");
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		String beishu = dto.getClass1();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		if(beishu==null || "".equals(beishu)) {
			beishu = "3";
		}
		if(illName==null ||  "".equals(illName)  ){
			sqlStr.append("  select org_code,handding_ins_code,city_code,name as org_name,total_cost,to_char(balance_date,'yyyy-mm-dd') as year_data,to_char(balance_date,'mm') as month_time,b.pnumber as t_csot from t_piccbid_medical join\r\n" + 
					"					(select * from( select  distinct  t.area ,round(t.pnumber,2) as pnumber,t.cvnum as cvnum \r\n" + 
					"from T_VMATER_DATATRANS_ssjkanbqzyc t   \r\n" + 
					"join (select a.total_cost as pnumber,a.condition from t_piccbid_medical a \r\n" + 
					"join (select * from (select count(condition) as mm,condition from t_piccbid_medical where diag_type='1' group by condition) c where c.mm>='30' ) d\r\n" + 
					"on a.condition = d.condition ) c  \r\n" + 
					"on t.area=c.condition \r\n" + 
					"where role_id = '"+roleId+"' \r\n" + 
					"and (c.pnumber >=t.pnumber*'"+beishu+"'  or c.pnumber <=round(to_number(t.pnumber)/'"+beishu+"',0 )) and 1=1 order by cast(pnumber as numeric(10,2)) desc) where rownum=1) b on condition = b.area" );
		}
		else{
			sqlStr.append(" select org_code,handding_ins_code,city_code,name as org_name,total_cost,to_char(balance_date,'yyyy-mm-dd') as year_data,to_char(balance_date,'mm') as month_time,b.pnumber as t_csot from t_piccbid_medical join (select pnumber,area from T_VMATER_DATATRANS_ssjkanbqzyc where area "
							+" = '"+illName+"' and role_id = '"+roleId+"') b on t_piccbid_medical.condition = b.area "
						);
			//System.out.println(sqlStr);
		}
		//System.out.println(sqlStr);
		 List<String> listCityCode = unitService.getUserDataAhthority();
	        //String cityCode = "t.org_code";
	        //新授权
	    String cityCode = "t_piccbid_medical.org_code,t_piccbid_medical.handding_ins_code,t_piccbid_medical.city_code ";
	    sqlStr = unitService.appendDataAuhoritySql(cityCode, sqlStr, listCityCode);
		@SuppressWarnings("unchecked")
		List<AbnormalCostVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),AbnormalCostVo.class, hqlParamMap);
		dto.getPageModel().setTotals(list.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void abnormalCost2(MedicalAnalysisDto dto) {
		String illName = dto.getIllName();
		//System.out.println(illName+"-------------------");
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		String beishu = dto.getClass1();
		if(beishu==null || "".equals(beishu)) {
			beishu = "3";
		}
		//System.out.println(beishu);
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		if(illName==null ||  "".equals(illName)  ){
			sqlStr.append("  select org_code,handding_ins_code,city_code,name as org_name,total_cost,to_char(balance_date,'yyyy-mm-dd') as year_data,to_char(balance_date,'mm') as month_time,b.pnumber as t_csot from t_piccbid_medical join\r\n" + 
					"					(select * from( select  distinct  t.area ,round(t.pnumber,2) as pnumber,t.cvnum as cvnum \r\n" + 
					"from T_piccbid_icd_avg_fee t   \r\n" + 
					"join (select a.total_cost as pnumber,a.condition from t_piccbid_medical a \r\n" + 
					"join (select * from (select count(condition) as mm,condition from t_piccbid_medical where diag_type='1' group by condition) c where c.mm>='30' ) d\r\n" + 
					"on a.condition = d.condition ) c  \r\n" + 
					"on t.area=c.condition \r\n" + 
					"where role_id = '"+roleId+"' \r\n" + 
					"and (c.pnumber >=t.pnumber*'"+beishu+"'  or c.pnumber <=round(to_number(t.pnumber)/'"+beishu+"',0 )) and 1=1 order by cast(pnumber as numeric(10,2)) desc) where rownum=1) b on condition = b.area" );
		}
		else{
			sqlStr.append(" select org_code,handding_ins_code,city_code,name as org_name,total_cost,to_char(balance_date,'yyyy-mm-dd') as year_data,to_char(balance_date,'mm') as month_time,b.pnumber as t_csot from t_piccbid_medical join (select pnumber,area from T_piccbid_icd_avg_fee where area "
							+" = '"+illName+"' and role_id = '"+roleId+"') b on t_piccbid_medical.condition = b.area "
						);
			//System.out.println(sqlStr);
		}
		//System.out.println(sqlStr);
		 List<String> listCityCode = unitService.getUserDataAhthority();
	        //String cityCode = "t.org_code";
	        //新授权
	    String cityCode = "t_piccbid_medical.org_code,t_piccbid_medical.handding_ins_code,t_piccbid_medical.city_code ";
	    sqlStr = unitService.appendDataAuhoritySql(cityCode, sqlStr, listCityCode);
		@SuppressWarnings("unchecked")
		List<AbnormalCostVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),AbnormalCostVo.class, hqlParamMap);
		dto.getPageModel().setTotals(list.size());
		dto.getPageModel().setPageData(list);
	}
	public void medicaStatisticsForHomePage(MedicalResultVO dto) {
	    StringBuilder sqlStr=new StringBuilder();
	    sqlStr.append("select * from (");
        sqlStr.append("select c.handding_ins_name,\n" +
                "       count(distinct(a.ORG_CODE)) as ORG_CODE ,\n" +
                "       count(distinct(a.ATTENDING_DOC_CODE)) as  ATTENDING_DOC_CODE,\n" +
                "       count(distinct(a.INSURE_PERSON_CODE)) as  INSURE_PERSON_CODE,\n" +
                "        count(distinct(b.STORE_NO)) as STORE_NO ,\n" +
                "       sum(a.TOTAL_COST) as TOTAL_COST ,\n" +
                "       sum(a.self_cost) as  self_cost\n" +
                "      \n" +
                "  from t_piccbid_medical a left join T_PICCBID_DRUGSTORE b on a.handding_ins_code =b.area_code\n" +
                "  left join t_piccbid_handding_org_code c on a.handding_ins_code =c.handding_ins_code\n" +
                "  where 1=1 and  a.handding_ins_code is not null ");
		 List<String> listCityCode = unitService.getUserDataAhthority();
	        //新授权
	    String cityCode = "a.org_code,a.handding_ins_code,a.city_code ";
	    sqlStr = unitService.appendDataAuhoritySql(cityCode, sqlStr, listCityCode);
	    sqlStr.append(" group by a.handding_ins_code, c.handding_ins_name  order by sum(a.TOTAL_COST) desc");
	    //展示前五条
	    sqlStr.append(" ) where rownum<6");
		@SuppressWarnings("unchecked")
		List<MedicalResultVO> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),MedicalResultVO.class, new HashMap());
		dto.getPageModel().setTotals(list.size());
		dto.getPageModel().setPageData(list);
	}



	
	public void totalIllData(MedicalAnalysisDto dto) {
		String code = dto.getFindOrgName();
		String inFlag = dto.getInFlag1();
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		sqlStr.append("select * from t_VMATER_Autonomous_TOTALCASE where balance_date='"+inFlag+"' and role_id = '"+roleId+"' " );
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' =  t_VMATER_Autonomous_TotalCase.org_code ");
		}
		sqlStr.append("order by TOTAL_NUMBER desc");
		@SuppressWarnings("unchecked")
		List<YearDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), TotalIllDataVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
	}
	
	public void drugCostData(MedicalAnalysisDto dto) {
		String inFlag = dto.getInFlag();
		StringBuilder sqlStr = new StringBuilder();
		String code = dto.getFindOrgName();
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		sqlStr.append("select * from t_VMATER_Autonomous_Drug where balance_date='"+inFlag+"' and DRUG_FEE!='0' and role_id = '"+roleId+"' ");
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' = t_VMATER_Autonomous_Drug.org_code ");
		}
		sqlStr.append("order by DRUG_FEE desc");
		@SuppressWarnings("unchecked")
		//List<YearDataVo> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),DrugCostDataVo.class, hqlParamMap);
		List<YearDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), DrugCostDataVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		//dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void serviceCostData(MedicalAnalysisDto dto) {
		String inFlag = dto.getInFlag();
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		String code = dto.getFindOrgName(); 
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		sqlStr.append("select * from t_VMATER_Autonomous_Service where balance_date='"+inFlag+"' and Service_FEE !='0' and role_id = '"+roleId+"' ");
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' = t_VMATER_Autonomous_Service.org_code ");
		}
		sqlStr.append("order by Service_FEE desc");
		@SuppressWarnings("unchecked")
		List<YearDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), ServiceCostVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		//dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void materialCostData(MedicalAnalysisDto dto) {
		String inFlag = dto.getInFlag();
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		String code = dto.getFindOrgName(); 
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		/*String startTime = inFlag + "-1-1";
		String endTime = inFlag + "-12-31";*/
		//System.out.println(startTime);
		//System.out.println(endTime);
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		sqlStr.append("select * from t_VMATER_Autonomous_Material where balance_date='"+inFlag+"' and Material_FEE!='0' and role_id = '"+roleId+"' ");
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' = t_VMATER_Autonomous_Material.org_code ");
		}
		sqlStr.append("order by Material_FEE desc");
		@SuppressWarnings("unchecked")
		List<YearDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), MaterialCostDataVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		//dto.getPageModel().setTotals(listCount.size());
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
	}
	
	public void monthlyTrendsData(MedicalAnalysisDto dto) {
		String inFlag = dto.getInFlag();
		StringBuilder sqlStr = new StringBuilder();
		PageModel pageModel = new PageModel();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		//String startTime = inFlag + "-1-1";
		//String endTime = inFlag + "-12-31";
		//System.out.println(startTime);
		//System.out.println(endTime);
		//System.out.print(inFlag);;
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		sqlStr.append(" select * from t_VMATER_Autonomous_month where balance_date='"+inFlag+"' and role_id = '"+roleId+"' ");
		@SuppressWarnings("unchecked")
		List<MonthlyTrendsDataVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),
				MonthlyTrendsDataVo.class, hqlParamMap);
		dto.getPageModel().setTotals(list.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void findHosNumber(MedicalAnalysisDto dto) {

		//StringBuilder sqlStr = new StringBuilder();
		String inFlag = dto.getInFlag();
		String code = dto.getFindOrgName(); 
		//System.out.print(code);
		//System.out.print(inFlag);
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*StringBuilder sqlStr = new StringBuilder().append("  select b.org_name as name,a.av_cost as digit"
                +" from (select T_PICCBID_MEDICAL.org_code,round(sum( T_PICCBID_MEDICAL.total_cost)/ "
                +" count(case when T_PICCBID_MEDICAL.Diag_Type='1' then T_PICCBID_MEDICAL.org_code else null end),2) as av_cost "
                +" from T_PICCBID_MEDICAL group by T_PICCBID_MEDICAL.org_code) a "
                +" left join T_PICCBID_DICT_CITY_ORG b on a.org_code=b.org_code order by a.av_cost desc"
				);*/
		//StringBuilder sqlStr = new StringBuilder().append("select * from V_MATER_Autonomous_PjDay where balance_date='"+inFlag+"'  order by AVG_DAY desc");
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		StringBuilder sqlStr = new StringBuilder().append("select * from t_VMATER_Autonomous_yearData where balance_date='"+inFlag+"' and role_id = '"+roleId+"' and org_name is not null ");
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' = t_VMATER_Autonomous_yearData.org_code ");
		}
		sqlStr.append("  order by AVG_DAY desc ");
		@SuppressWarnings("unchecked")
		List<HospitalVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		//dto.getPageModel().setTotals(listCount.size());
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
	}
	public void findMonNumber(MedicalAnalysisDto dto) {

		String inFlag = dto.getInFlag();
		String code = dto.getFindOrgName(); 
		//System.out.print(inFlag);
		if(inFlag==null ||"".equals(inFlag)  ){
			Calendar date = Calendar.getInstance();
			inFlag = String.valueOf(date.get(Calendar.YEAR));
		}
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*StringBuilder sqlStr = new StringBuilder().append("  select b.org_name as name,a.av_cost as digit"
                +" from (select T_PICCBID_MEDICAL.org_code,round(sum( T_PICCBID_MEDICAL.Stay_Length)/ "
                +" count(case when T_PICCBID_MEDICAL.Diag_Type='1' then T_PICCBID_MEDICAL.org_code else null end),2) as av_cost "
                +" from T_PICCBID_MEDICAL group by T_PICCBID_MEDICAL.org_code) a "
                +" left join T_PICCBID_DICT_CITY_ORG b on a.org_code=b.org_code order by a.av_cost desc"
				);*/
		//StringBuilder sqlStr = new StringBuilder().append("select * from V_MATER_Autonomous_PjMoney where balance_date='"+inFlag+"' order by AVG_COST desc");
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		StringBuilder sqlStr = new StringBuilder().append("select * from t_VMATER_Autonomous_yearData where balance_date='"+inFlag+"' and role_id = '"+roleId+"' and org_name is not null ");
		if(code!=null &&!"".equals(code)) {
			sqlStr.append(" and '"+code+"' = t_VMATER_Autonomous_yearData.org_code ");
		}
		sqlStr.append("  order by AVG_COST desc ");
		@SuppressWarnings("unchecked")
		List<MoneyVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), MoneyVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		//dto.getPageModel().setTotals(listCount.size());
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
	}


	/** 
	* 方法名:          monthlyTrendsData
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年8月16日 下午5:13:01
	*/
}

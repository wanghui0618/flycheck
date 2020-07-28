package com.dhcc.piccbid.dao.hospitalviolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.hospitalviolation.HospitalViolationDto;
import com.dhcc.piccbid.entity.hospitalviolation.HospitalViolation;
import com.dhcc.piccbid.entity.hospitalviolation.HospitalViolationVo;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.entity.violationdetails.SysVerifyVo;
import com.dhcc.piccbid.entity.violationdetails.ViolationDetail;
import com.dhcc.piccbid.service.unit.UnitService;

@Component
public class HospitalViolationJdbcDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	@Autowired
	private UnitService unitService;
	public void listHospitalViolationVo(HospitalViolationDto dto) {
		//从session中取出user对象
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		//String code = "1";
        //code = dto.getHospitalViolation().getOrgCode();
		//System.out.println("-----"+code);
		PageModel pageModel = dto.getPageModel();
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		//主页切换
		/*if(dto.getStatus()!=null&&!"".equals(dto.getStatus())) {
			if("0".equals(dto.getStatus())) {
				//机审
				//sqlStr.append("select * from V_MATER_hospital_violation t where 1=1  ");
				sqlStr.append("select * from (select org_code,org_name,city_name,sum(vio_count) as vio_count,sum(sus_count) as sus_count,\r\n" + 
						"sum(vio_money) as vio_money,sum(sus_money) as sus_money\r\n" + 
						" from T_VMATER_hospital_violation where role_id='"+roleId+"' GROUP BY org_code,org_name,city_name) where 1=1 ");
			}else if("1".equals(dto.getStatus())) {
				//终审
				//sqlStr.append("select * from V_MATER_HOSPITAL_VIOLATIONZS t where 1=1 ");
				sqlStr.append("select * from (select org_code,org_name,city_name,sum(vio_count) as vio_count,\r\n" + 
						"sum(vio_money) as vio_money\r\n" + 
						" from T_VMATER_hospital_violation_zs where role_id='"+roleId+"' GROUP BY org_code,org_name,city_name) where 1=1 ");
			}else {
				//sqlStr.append("select * from V_MATER_hospital_violation t where 1=1 ");
				sqlStr.append("select * from (select org_code,org_name,city_name,sum(vio_count) as vio_count,sum(sus_count) as sus_count,\r\n" + 
						"sum(vio_money) as vio_money,sum(sus_money) as sus_money\r\n" + 
						" from T_VMATER_hospital_violation where role_id='"+roleId+"' GROUP BY org_code,org_name,city_name) where 1=1 ");
			}
		}else {
			//sqlStr.append("select * from V_MATER_hospital_violation t where 1=1 ");
			sqlStr.append("select * from (select org_code,org_name,city_name,sum(vio_count) as vio_count,sum(sus_count) as sus_count,\r\n" + 
					"sum(vio_money) as vio_money,sum(sus_money) as sus_money\r\n" + 
					" from T_VMATER_hospital_violation where role_id='"+roleId+"' GROUP BY org_code,org_name,city_name) where 1=1 ");
		}*/
		sqlStr.append("select \r\n" + 
				"hospital_name as org_name,\r\n" + 
				"hospital_id as org_code,\r\n" + 
				"count(case when sys_status in (0) then '违规' end) as vio_count,\r\n" + 
				"count(case when sys_status in (1) then '疑似违规' end) as sus_count,\r\n" + 
				"sum(decode(sys_status , '0' ,total_amount,0)) as vio_money,\r\n" + 
				"sum(decode(sys_status , '1' ,total_amount,0)) as sus_money \r\n" + 
				"from t_flycheck_medical\r\n" );
		if(dto.getHospitalViolation()!=null && !"".equals(dto.getHospitalViolation())) {
			if(dto.getHospitalViolation().getOrgCode()!=null && !"".equals(dto.getHospitalViolation().getOrgCode()))
			sqlStr.append(" where hospital_id = '"+dto.getHospitalViolation().getOrgCode()+"' ");
		}
		sqlStr.append(" group by hospital_name,hospital_id\r\n"+
			          "order by vio_count desc"
		);
		//System.out.println(dto.getHospitalViolation().getOrgCode());
		//实时监控大页面跳转
		if(dto.getComType()!=null&&"4".equals(dto.getComType())) {
			StringBuilder sqlStr1=new StringBuilder();
			/*sqlStr1.append("select org_code, org_name, city_name, count(org_name) as vio_count\r\n" + 
					"  from (select a.id,\r\n" + 
					"               a.city_code,\r\n" + 
					"               a.name,\r\n" + 
					"               a.org_name,\r\n" + 
					"               a.org_code,\r\n" + 
					"               b.city_name,\r\n" + 
					"               a.SYS_STATUS,\r\n" + 
					"               a.USER_STATUS,\r\n" + 
					"               a.FINA_STATUS,\r\n" + 
					"               a.CREATE_TIME\r\n" + 
					"          from t_piccbid_medical a  left join T_PICCBID_DICT_CITY b on a.city_code=b.city_code\r\n" + 
					"\r\n" + 
					"         where\r\n" + 
					"            a.sys_status in ('0', '1') and a.HANDDING_INS_CODE= getHanddingInsCode('"+dto.getHanddingInsName()+"' ))  \r\n" + 
					"\r\n" + 
					" where org_name is not null\r\n" + 
					" group by org_name, city_name, org_code");*/
			/*sqlStr1.append("SELECT ORG_CODE, ORG_NAME, CITY_NAME, COUNT(ID) AS VIO_COUNT\r\n" + 
					"  FROM (SELECT A.ID,\r\n" + 
					"               A.CITY_CODE,\r\n" + 
					"               A.NAME,\r\n" + 
					"               A.ORG_NAME,\r\n" + 
					"               A.ORG_CODE,\r\n" + 
					"               B.CITY_NAME,\r\n" + 
					"               A.SYS_STATUS,\r\n" + 
					"               A.USER_STATUS,\r\n" + 
					"               A.FINA_STATUS,\r\n" + 
					"               A.CREATE_TIME\r\n" + 
					"          FROM T_PICCBID_MEDICAL A,\r\n" + 
					"               T_PICCBID_DICT_CITY B,\r\n" + 
					"               T_PICCBID_HANDDING_ORG_CODE C\r\n" + 
					"         WHERE A.CITY_CODE = B.CITY_CODE\r\n" + 
					"           AND A.SYS_STATUS IN ('0', '1')\r\n" + 
					"           AND A.HANDDING_INS_CODE = C.HANDDING_INS_CODE\r\n" + 
					"           AND C.NAME_ALIAS = '"+dto.getHanddingInsName()+"' )\r\n" + 
					" GROUP BY ORG_NAME, CITY_NAME, ORG_CODE\r\n" + 
					" ORDER BY 4 DESC  \r\n" + 
					"");
			sqlStr=new StringBuilder();
			sqlStr.append("select * from ("+sqlStr1.toString()+") t where 1=1 ");*/
			sqlStr=new StringBuilder();
			sqlStr.append("select * from T_VMATER_hospital_violation  where  role_id='"+roleId+"' and name_alias='"+dto.getHanddingInsName()+"' ");
		}
		///////////
		/*HospitalViolation hosoitalViolation = dto.getHospitalViolation();
		if(hosoitalViolation!=null){
			if(hosoitalViolation.getCityName()!=null && !"".equals(hosoitalViolation.getCityName())){
				sqlStr.append(" and city_name='"+hosoitalViolation.getCityName()+"'");
			}
			if(hosoitalViolation.getOrgName()!=null && !"".equals(hosoitalViolation.getOrgName())){
				sqlStr.append(" and org_name like '%"+hosoitalViolation.getOrgName()+"%'");
			}
			if(hosoitalViolation.getOrgCode()!=null && !"".equals(hosoitalViolation.getOrgCode())){
				sqlStr.append(" and org_code = '"+hosoitalViolation.getOrgCode()+"'");
			}
		}*/

		//数据授权
		//List<String> listCityCode=unitService.getUserDataAhthority();
		//String cityCode="t.city_code";
		//调用数据授权
		//sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);

		//sqlStr.append(" order by to_number(vio_count) desc");
		@SuppressWarnings("unchecked")
//		String medicalCountSql="select count(id) from T_PICCBID_MEDICAL ";
//		int sumCount =jdbcTemplate.queryForObject(medicalCountSql, Integer.class);
//		for(int k=0;k<HosoitalViolationList.size();k++) {
//			HosoitalViolationList.get(k).setSumCount(sumCount);
//		}
		//int count = jdbcTemplate.queryForObject(sqlStr.toString(),Integer.class);
		
		List<HospitalViolationVo> HosoitalViolationList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalViolationVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(),"rownum");
		pageModel.setTotals(HosoitalViolationList.size());
		pageModel.setPageData(HosoitalViolationList); 

	}
	//医院案件违规--违规类型分布
	public List<SysVerifyVo> violationSpread(HospitalViolationDto dto) {
		//从session中取出user对象
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
	    String roleId = user.getRoleId();

		//String sql="select getrulename(type_no) as type_names,count(type_no) as count_num from (select type_no from V_MATER_hviolate_Spread  where 1=1  \r\n" + 
				//"and  org_name='"+dto.getHospitalViolation().getOrgName()+"' and city_name='"+dto.getHospitalViolation().getCityName()+"' and type_no is not null\r\n" + 
				//"group by type_no,id ) group by type_no order by count_num desc";
		//String sql="select getrulename(type_no) as type_names,count(type_no) as count_num from (select type_no from T_VMATER_ho_violation_spread  where   role_id='"+roleId+"' \r\n" + 
				//"and  org_name='"+dto.getHospitalViolation().getOrgName()+"' and city_name='"+dto.getHospitalViolation().getCityName()+"' and type_no is not null\r\n" + 
				//"group by type_no,id ) group by type_no order by count_num desc";
		String sql=" select type_no,getrulename(type_no) as type_names, count(type_no) as count_num\r\n" + 
				"   from (select *\r\n" + 
				"           from T_VMATER_violation_spread\r\n" + 
				"          where org_code = '"+dto.getHospitalViolation().getOrgCode()+"'\r\n" + 
				"            and city_name = '"+dto.getHospitalViolation().getCityName()+"'\r\n" + 
				"            and V_role_id = '"+roleId+"')\r\n" + 
				"  group by type_no\r\n" + 
				"  order by count_num desc ";
		if(dto.getStatus()!=null&&!"".equals(dto.getStatus())) {
			if("1".equals(dto.getStatus())) {
			/*sql="select getrulename(type_no) as type_names,count(type_no) as count_num from (select * from V_MATER_hviolate_Spread where fina_status ='0' ) where 1=1\r\n" + 
					"and  org_name='"+dto.getHospitalViolation().getOrgName()+"' and city_name='"+dto.getHospitalViolation().getCityName()+"' and type_no is not null\r\n" + 
					"group by type_no order by count_num desc";*/
				//sql="select getrulename(type_no) as type_names,count(type_no) as count_num from (select type_no from T_VMATER_ho_violation_spread where fina_status ='0' and role_id='"+roleId+"' \r\n" + 
						//"and  org_name='"+dto.getHospitalViolation().getOrgName()+"' and city_name='"+dto.getHospitalViolation().getCityName()+"' and type_no is not null\r\n" + 
						//"group by type_no,id ) group by type_no order by count_num desc";
				sql=" select getrulename(type_no) as type_names, count(type_no) as count_num\r\n" + 
						"   from (select *\r\n" + 
						"           from T_VMATER_violation_spreadzs\r\n" + 
						"          where org_code = '"+dto.getHospitalViolation().getOrgCode()+"'\r\n" + 
						"            and city_name = '"+dto.getHospitalViolation().getCityName()+"'\r\n" + 
						"            and v_role_id = '"+roleId+"')\r\n" + 
						"  group by type_no\r\n" + 
						"  order by count_num desc ";
			}
		}
		//实时监控大页面跳转
		if(dto.getComType()!=null&&"4".equals(dto.getComType())) {
			/* sql=" select getrulename(type_no) as type_names, count(type_no) as count_num\r\n" + 
					"   from (select *\r\n" + 
					"           from (select id, type_no, city_name, org_name\r\n" + 
					"  from (select y.id,\r\n" + 
					"               t.type_no,\r\n" + 
					"               k.city_name,\r\n" + 
					"               y.org_name,\r\n" + 
					"               y.sys_status,\r\n" + 
					"               y.fina_status\r\n" + 
					"          from t_piccbid_medical y\r\n" + 
					"          left join V_PICCBID_VIOLATION_DETAILS t\r\n" + 
					"            on t.medical_id = y.id \r\n" + 
					"          left join T_PICCBID_DICT_CITY k\r\n" + 
					"            on y.city_code = k.city_code\r\n" + 
					"         where y.sys_status in ('0', '1')\r\n" + 
					"           and t.type_no is not null and y.HANDDING_INS_CODE= getHanddingInsCode('"+dto.getHanddingInsName()+"') )\r\n" + 
					" group by id, type_no, city_name, org_name ) t\r\n" + 
					"          where org_name = '"+dto.getHospitalViolation().getOrgName()+"'\r\n" + 
					"            and city_name = '"+dto.getHospitalViolation().getCityName()+"'\r\n" + 
					"            )\r\n" + 
					"  group by type_no\r\n" + 
					"  order by count_num desc ";*/
			/* 
			 sql="SELECT ORG_CODE, ORG_NAME, CITY_NAME, COUNT(ID) AS VIO_COUNT\r\n" + 
			 		"  FROM (SELECT A.ID,\r\n" + 
			 		"               A.CITY_CODE,\r\n" + 
			 		"               A.NAME,\r\n" + 
			 		"               A.ORG_NAME,\r\n" + 
			 		"               A.ORG_CODE,\r\n" + 
			 		"               B.CITY_NAME,\r\n" + 
			 		"               A.SYS_STATUS,\r\n" + 
			 		"               A.USER_STATUS,\r\n" + 
			 		"               A.FINA_STATUS,\r\n" + 
			 		"               A.CREATE_TIME\r\n" + 
			 		"          FROM T_PICCBID_MEDICAL A,\r\n" + 
			 		"               T_PICCBID_DICT_CITY B,\r\n" + 
			 		"               T_PICCBID_HANDDING_ORG_CODE C\r\n" + 
			 		"         WHERE A.CITY_CODE = B.CITY_CODE\r\n" + 
			 		"           AND A.SYS_STATUS IN ('0', '1')\r\n" + 
			 		"           AND A.HANDDING_INS_CODE = C.HANDDING_INS_CODE\r\n" + 
			 		"           AND C.NAME_ALIAS = '"+dto.getHanddingInsName()+"')\r\n" + 
			 		"          and  a.org_name = '"+dto.getHospitalViolation().getOrgName()+"'\r\n" + 
					"            and b.city_name = '"+dto.getHospitalViolation().getCityName()+"'\r\n" + 
			 		" GROUP BY ORG_NAME, CITY_NAME, ORG_CODE\r\n" + 
			 		" ORDER BY 4 DESC ";*/
			 sql=" select getrulename(type_no) as type_names, count(type_no) as count_num from(\r\n" + 
			 		"  select * from (\r\n" + 
			 		"  select y.id,\r\n" + 
			 		"         t.type_no,\r\n" + 
			 		"         k.city_name, y.org_code, \r\n" + 
			 		"         y.org_name\r\n" + 
			 		"    from t_piccbid_medical y\r\n" + 
			 		"    left join T_PICCBID_VIOLATION_DETAILS t\r\n" + 
			 		"      on t.medical_id = y.id\r\n" + 
			 		"    left join T_PICCBID_DICT_CITY k\r\n" + 
			 		"      on y.city_code = k.city_code\r\n" + 
			 		"    left join T_PICCBID_HANDDING_ORG_CODE h\r\n" + 
			 		"      on y.HANDDING_INS_CODE = h.HANDDING_INS_CODE\r\n" + 
			 		"   where y.sys_status in ('0', '1')\r\n" + 
			 		"     and t.type_no is not null\r\n" + 
			 		"     and h.NAME_ALIAS = '"+dto.getHanddingInsName()+"' \r\n" + 
			 		"     )\r\n" + 
			 		"   group by id, type_no, city_name, org_name,org_code\r\n" + 
			 		"   ) where  org_code = '"+dto.getHospitalViolation().getOrgCode()+"' and city_name = '"+dto.getHospitalViolation().getCityName()+"' group by type_no order by count_num desc";
		}
		@SuppressWarnings("unchecked")
		List<SysVerifyVo> HosoitalViolationList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql, SysVerifyVo.class, null);
		if(HosoitalViolationList.size()>0) {
			return HosoitalViolationList;
		}
		return null;
	}
	//导出到excel
	public XSSFWorkbook exportExcelToSelf(String status,String handdingInsName) {
		//从session中取出user对象
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		//获取数据
		//String sql="select * from V_MATER_hospital_violation t where 1=1 order by t.vio_count desc";
		String sql="select * from(select org_code,org_name,sum(vio_count) as vio_count,sum(sus_count) as sus_count,\r\n" + 
				"sum(vio_money) as vio_money,sum(sus_money) as sus_money\r\n" + 
				" from T_VMATER_hospital_violation where role_id='"+roleId+"' group by org_code,org_name) order by to_number(vio_count) desc ";
		//切换 --机审0  --终审1
		if(status!=null&&!"".equals(status)) {
			if("1".equals(status)) {
				//sql="select * from V_MATER_HOSPITAL_VIOLATIONZS t where 1=1 order by t.vio_count desc";
				sql="select * from(select org_code,org_name,sum(vio_count) as vio_count,\r\n" + 
						"sum(vio_money) as vio_money\r\n" + 
						" from T_VMATER_hospital_violation_zs where role_id='"+roleId+"' group by org_code,org_name) order by to_number(vio_count) desc ";
			}
		}
		//实时监控大页面跳转
				if(handdingInsName!=null&&!"".equals(handdingInsName)&&!"undefined".equals(handdingInsName)) {
					//StringBuilder sqlStr1=new StringBuilder();
					/*sqlStr1.append("select org_code, org_name, city_name, count(org_name) as vio_count\r\n" + 
							"  from (select a.id,\r\n" + 
							"               a.city_code,\r\n" + 
							"               a.name,\r\n" + 
							"               a.org_name,\r\n" + 
							"               a.org_code,\r\n" + 
							"               b.city_name,\r\n" + 
							"               a.SYS_STATUS,\r\n" + 
							"               a.USER_STATUS,\r\n" + 
							"               a.FINA_STATUS,\r\n" + 
							"               a.CREATE_TIME\r\n" + 
							"          from t_piccbid_medical a  left join T_PICCBID_DICT_CITY b on a.city_code=b.city_code\r\n" + 
							"\r\n" + 
							"         where\r\n" + 
							"            a.sys_status in ('0', '1') and a.HANDDING_INS_CODE= getHanddingInsCode('"+handdingInsName+"' ))  \r\n" + 
							"\r\n" + 
							" where org_name is not null\r\n" + 
							" group by org_name, city_name, org_code");*/
					/*sqlStr1.append("SELECT ORG_CODE, ORG_NAME, CITY_NAME, COUNT(ID) AS VIO_COUNT\r\n" + 
							"  FROM (SELECT A.ID,\r\n" + 
							"               A.CITY_CODE,\r\n" + 
							"               A.NAME,\r\n" + 
							"               A.ORG_NAME,\r\n" + 
							"               A.ORG_CODE,\r\n" + 
							"               B.CITY_NAME,\r\n" + 
							"               A.SYS_STATUS,\r\n" + 
							"               A.USER_STATUS,\r\n" + 
							"               A.FINA_STATUS,\r\n" + 
							"               A.CREATE_TIME\r\n" + 
							"          FROM T_PICCBID_MEDICAL A,\r\n" + 
							"               T_PICCBID_DICT_CITY B,\r\n" + 
							"               T_PICCBID_HANDDING_ORG_CODE C\r\n" + 
							"         WHERE A.CITY_CODE = B.CITY_CODE\r\n" + 
							"           AND A.SYS_STATUS IN ('0', '1')\r\n" + 
							"           AND A.HANDDING_INS_CODE = C.HANDDING_INS_CODE\r\n" + 
							"           AND C.NAME_ALIAS = '"+handdingInsName+"' )\r\n" + 
							" GROUP BY ORG_NAME, CITY_NAME, ORG_CODE\r\n" + 
							" ORDER BY 4 DESC \r\n" + 
							"");
					StringBuilder sqlStr=new StringBuilder();
					sqlStr.append("select * from ("+sqlStr1.toString()+") t where 1=1");
					sql=sqlStr.toString();*/
					StringBuilder sqlStr=new StringBuilder();
					sqlStr.append("select * from(select org_code,org_name,sum(vio_count) as vio_count,sum(sus_count) as sus_count,\r\n" + 
							"sum(vio_money) as vio_money,sum(sus_money) as sus_money\r\n" + 
							" from T_VMATER_hospital_violation where role_id='"+roleId+"' and name_alias='"+handdingInsName+"' group by org_code,org_name) order by to_number(vio_count) desc ");
					sql=sqlStr.toString();
				}
		@SuppressWarnings("unchecked")
		List<HospitalViolationVo> HosoitalViolationList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql, HospitalViolationVo.class,null);
		/*
		 * String medicalCountSql="select count(id) from T_PICCBID_MEDICAL "; int
		 * sumCount =jdbcTemplate.queryForObject(medicalCountSql, Integer.class);
		 * for(int k=0;k<HosoitalViolationList.size();k++) {
		 * HosoitalViolationList.get(k).setSumCount(sumCount); }
		 */
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("医院违规统计");
		// 在sheet里创建第一行
		XSSFRow row1 = sheet.createRow(0);
		// 创建单元格并设置单元格内容(可以设置为数组或者枚举进行取值)
		if("1".equals(status)) {
			row1.createCell(0).setCellValue("医院名称");
			row1.createCell(1).setCellValue("违规病例数");
			row1.createCell(2).setCellValue("违规金额");
		}else {
			row1.createCell(0).setCellValue("医院名称");
			row1.createCell(1).setCellValue("违规病例数");
			row1.createCell(2).setCellValue("可疑病例数");
			row1.createCell(3).setCellValue("违规金额");
			row1.createCell(4).setCellValue("可疑金额");
		}
		
		// 插入数据
		for (int i = 0; i < HosoitalViolationList.size(); i++) {
			XSSFRow rowNum = sheet.createRow(i + 1);
			if("1".equals(status)) {
				rowNum.createCell(0).setCellValue(HosoitalViolationList.get(i).getOrgName());
				rowNum.createCell(1).setCellValue(HosoitalViolationList.get(i).getVioCount());
				rowNum.createCell(2).setCellValue(HosoitalViolationList.get(i).getVioMoney());
			}else {
				rowNum.createCell(0).setCellValue(HosoitalViolationList.get(i).getOrgName());
				rowNum.createCell(1).setCellValue(HosoitalViolationList.get(i).getVioCount());
				rowNum.createCell(2).setCellValue(HosoitalViolationList.get(i).getSusCount());
				rowNum.createCell(3).setCellValue(HosoitalViolationList.get(i).getVioMoney());
				rowNum.createCell(4).setCellValue(HosoitalViolationList.get(i).getSusMoney());
			}
			/*
			 * long
			 * round=Math.round(Double.parseDouble(HosoitalViolationList.get(i).getVioCount(
			 * ))/HosoitalViolationList.get(i).getSumCount()*10000); String cv="";
			 * if(round==0) { cv="-"; }else {
			 * cv=Math.round(Double.parseDouble(HosoitalViolationList.get(i).getVioCount())/
			 * HosoitalViolationList.get(i).getSumCount()*10000)/ 100.00+"%"; }
			 * rowNum.createCell(2).setCellValue(cv);
			 */
		}
		return wb;
	}
	
	public void hopVio(HospitalViolationDto dto){
		StringBuilder sql=new StringBuilder();
		String orgCode = "";
		String type=dto.getType();
		String status=dto.getStatus();
		String handdingInsName=dto.getHanddingInsName();
		String comType=dto.getComType();
		if( dto.getHospitalViolation().getOrgCode()!=null&&!dto.getHospitalViolation().getOrgCode().equals("")) {
			orgCode = dto.getHospitalViolation().getOrgCode();
		}
		if("1".equals(status)) {
			sql.append("select * from (select sum(item_cost) as item_cost,sum(count_detail) as count_detail,type_name\r\n" + 
					"from t_vmater_hospital_rulezs where org_code='"+orgCode+"' GROUP BY type_name)");
			
		}else {
			//sql.append("select * from t_vmater_hospital_rule where role_id='"+roleId+"' and org_code='" +orgCode+"'");
			sql.append("select * from (select sum(item_cost) as item_cost,sum(count_detail) as count_detail,type_name\r\n" + 
					"from t_vmater_hospital_rule where org_code='"+orgCode+"' GROUP BY type_name)");
		}
		
		if("cost".equals(type)) {
			sql.append(" order by to_number(item_cost) desc nulls last");
			
		}else {
			sql.append(" order by to_number(count_detail) desc nulls last");
		}
		
		if(comType!=null&&"4".equals(comType)) {
			StringBuilder sqlStr=new StringBuilder();
			sqlStr.append("select * from t_vmater_hospital_rule where org_code='"+orgCode+"' and name_alias='"+handdingInsName+"'");
		}
		
		@SuppressWarnings("unchecked")
		List<ViolationDetail> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), ViolationDetail.class, null,1,10,"*");
		dto.getPageModel().setPageData(list);
	}
	
	public void medVio(HospitalViolationDto dto){
		StringBuilder sql=new StringBuilder();
		String orgCode = "";
		String type=dto.getType();
		String status=dto.getStatus();
		String handdingInsName=dto.getHanddingInsName();
		String comType=dto.getComType();
		if( dto.getHospitalViolation().getOrgCode()!=null&&!dto.getHospitalViolation().getOrgCode().equals("")) {
			orgCode = dto.getHospitalViolation().getOrgCode();
		}
		if("1".equals(status)) {
			sql.append("select * from (select sum(item_cost) as item_cost,sum(count_detail)as count_detail,item_name\r\n" + 
					" from t_vmater_hospital_suszs where org_code='"+orgCode+"' GROUP BY item_name)");
		}else {
			sql.append("select * from (select sum(item_cost) as item_cost,sum(count_detail)as count_detail,item_name\r\n" + 
					" from t_vmater_hospital_sus where org_code='"+orgCode+"' GROUP BY item_name)");
		}
		
		if("cost".equals(type)) {
			sql.append(" order by to_number(item_cost) desc nulls last");
			
		}else {
			sql.append(" order by to_number(count_detail) desc nulls last");
		}
		
		if(comType!=null&&"4".equals(comType)) {
			StringBuilder sqlStr=new StringBuilder();
			sqlStr.append("select * from t_vmater_hospital_sus where org_code='"+orgCode+"' and name_alias='"+handdingInsName+"'");
		}
		
		@SuppressWarnings("unchecked")
		List<ViolationDetail> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), ViolationDetail.class, null,1,10,"*");
		dto.getPageModel().setPageData(list);
	}
}

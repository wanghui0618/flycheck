package com.dhcc.piccbid.dao.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.unit.UnitService;

@Component
public class AdminJdbcDao {

	private static Log logger = LogFactory.getLog(AdminJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;
	
	public void listNumber(AdminDto dto) {
		String inFlag = dto.getInFlag();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		List<String> listCityCode=unitService.getUserDataAhthority();
		sqlStr.append(" select a.aaa as zynumber ,b.bbb as mznumber, c.ccc as mtnumber ");
		sqlStr.append( "from ( select count(diag_type) as aaa  from t_Piccbid_Medical  where diag_type ='1'   and create_time = to_date(to_char(sysdate - 1, 'yyyy-MM-dd'), 'yyyy-MM-dd') ");
		sqlStr=unitService.appendDataAuhoritySql("org_code,city_code,handding_ins_code",sqlStr,listCityCode);
		sqlStr.append(")a ,( select count(diag_type) as bbb  from t_Piccbid_Medical  where diag_type ='2'   and create_time = to_date(to_char(sysdate - 1, 'yyyy-MM-dd'), 'yyyy-MM-dd') ");
		sqlStr=unitService.appendDataAuhoritySql("org_code,city_code,handding_ins_code",sqlStr,listCityCode);
		sqlStr.append(")b ,( select count(diag_type) as ccc  from t_Piccbid_Medical  where diag_type ='3'  and create_time = to_date(to_char(sysdate - 1, 'yyyy-MM-dd'), 'yyyy-MM-dd') ");
		sqlStr=unitService.appendDataAuhoritySql("org_code,city_code,handding_ins_code",sqlStr,listCityCode);
		sqlStr.append(")c ");
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	//昨日上传数量统计
	public void TodayNumber(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		sqlStr.append(" select decode(total_number,'','0',total_number) as total_number, decode(org_name,'','其他',org_name) as org_name from T_VMATER_DATATRANS_ZRSCSL ");
		sqlStr.append(" where 1=1 and role_id = '"+ role_id+"'"); // 授权
		Admin admin =dto.getAdmin();
		if(admin!=null) {
			if (admin.getOrgName() != null && !"".equals(admin.getOrgName())) {
				sqlStr.append("  and org_name='" + admin.getOrgName() + "'");
			}
		}
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}

	//昨日质量统计
	public void QualityToday(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
	/*	sqlStr.append("  select decode(total_number,'','0',total_number) as total_number, decode(org_name,'','其他',org_name) as org_name , decode(effect_number,'','0',effect_number) as effect_number , decode(effective,'','0',effective) as effective from T_VMATER_DATATRANS_EFFECTIVE ");
		sqlStr.append(" where 1=1 and role_id = '"+ role_id+"'"); // 授权
*/		
		sqlStr.append("  select decode(org_name, '', '其他', org_name) as org_name, decode(sum(effect_number),'','0',sum(effect_number)) as effect_number,decode(sum(effective),'','0',sum(effective)) as effective, ");
		sqlStr.append("  decode(sum(total_number),'','0',sum(total_number)) as total_number from T_VMATER_DATATRANS_EFFECTIVE where role_id = '"+role_id+"' ");
		Admin admin =dto.getAdmin();
		if(admin!=null) {
			if (admin.getOrgName() != null && !"".equals(admin.getOrgName())) {
				sqlStr.append("  and   org_name='" + admin.getOrgName() + "'");
			}
		}
		sqlStr.append("  group by org_name ");
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void IntegrityToday(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		/*sqlStr.append(" select decode(total_number,'','0',total_number) as total_number, decode(org_name,'','其他',org_name) as org_name , decode(uneffect_number,'','0',uneffect_number) as uneffect_number  from T_VMATER_DATATRANS_INTEGRITY ");
		sqlStr.append(" where 1=1 and role_id = '"+ role_id+"'"); // 授权
*/		
		sqlStr.append(" select decode(org_name, '', '其他', org_name) as org_name,decode(sum(total_number),'','0',sum(total_number)) as total_number,decode(sum(uneffect_number),'','0',sum(uneffect_number)) as uneffect_number ");
		sqlStr.append("  from T_VMATER_DATATRANS_INTEGRITY where role_id = '"+role_id+"' ");
		Admin admin =dto.getAdmin();
		if(admin!=null) {
			
			if (admin.getOrgName() != null && !"".equals(admin.getOrgName())) {
				sqlStr.append("  and   org_name='" + admin.getOrgName() + "'");
			}
		}
		sqlStr.append("  group by org_name ");
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void YearData(AdminDto dto) {
		String inFlag = dto.getInFlag();
		String roleId = dto.getRoleId();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*sqlStr.append(" select * from V_DATATRANS_MATER_YEARDATA where year_day='"+inFlag+"'");*/
		sqlStr.append(" select * from T_VMATER_DATATRANS_YEARDATA where year_day='"+inFlag+"'");
		sqlStr.append("  and role_id = '"+ roleId+"'"); // 授权
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), 12, "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	
	public void InhosNumber(AdminDto dto) {
		String inFlag = dto.getInFlag();
		String roleId = dto.getRoleId();
		StringBuilder sqlStr = new StringBuilder();
		Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        year="2019";
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*sqlStr.append(" select * from V_DATATRANS_MATER_YEARDATA where year_day='"+inFlag+"'");*/
		sqlStr.append(" select year ,round(pnumber,2) as pnumber  from T_VMATER_DATATRANS_SSJKZYTSAYR where year like'%"+year+"%'");
		sqlStr.append(" and role_id = '"+ roleId+"'"); // 授权
		if(dto.getHanddingName()!=null) {
			sqlStr.append(" and handding_ins_name = '"+ dto.getHanddingName()+"'"); // 授权
		}else {
			sqlStr.append(" and handding_ins_name = (select area from T_VMATER_DATATRANS_ssjkantcqz o where rownum <2 and role_id='"+roleId+"' and year_day='"+year+"')  "); 
		}
		sqlStr.append(" order by year "); // 授权
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), 12, "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void AreaNumber(AdminDto dto) {
		String inFlag = dto.getInFlag();
		String roleId = dto.getRoleId();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*sqlStr.append(" select * from V_DATATRANS_MATER_YEARDATA where year_day='"+inFlag+"'");*/
		sqlStr.append(" select area ,round(pnumber,0) as pnumber  from T_VMATER_DATATRANS_ssjkantcqz where year_day like'%"+inFlag+"%'");
		sqlStr.append(" and role_id = '"+ roleId+"'"); // 授权
		sqlStr.append(" order by pnumber "); // 授权
		@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),Admin.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), Admin.class, hqlParamMap,dto.getPageModel().getPageNo(), 12, "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	

}

package com.dhcc.piccbid.dao.admin;


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
import com.dhcc.piccbid.entity.admin.superviseRuleIndex;
//import com.dhcc.piccbid.entity.city.City;
//import com.dhcc.piccbid.entity.medical.SysStatus;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.unit.UnitService;

@Component
public class SuperviseRuleIndexJdbcDao {

	private static Log logger = LogFactory.getLog(SuperviseRuleIndexJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;
	
	public void RuleNumber(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		sqlStr.append(" select a.aaa as total_number ,b.bbb as person_number  ,c.ccc as money_number  from  ");
		sqlStr.append(" (select count( t.rule_no) as aaa  from t_piccbid_dict_rule t where valid_flag='1') a , ");
		sqlStr.append(" (select sum(pnumber) as bbb from T_VMATER_DATATRANS_JGJGTCQPJS where 1=1 and role_id='"+role_id+"') b , ");
		sqlStr.append(" (select sum( td.item_cost) as  ccc from T_PICCBID_VIOLATION_DETAILS t left join ");
		sqlStr.append(" t_piccbid_medical_detail td on t.medical_detail_id = td.id) c ");
		@SuppressWarnings("unchecked")
		List<superviseRuleIndex> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),superviseRuleIndex.class, hqlParamMap);
		List<superviseRuleIndex> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), superviseRuleIndex.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void MonitorNumber(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		sqlStr.append("select type_names as pname ,count_num as pnumber from T_VMATER_violation_details where role_id='"+role_id+"' order by to_number(pnumber)  desc");
		/*sqlStr.append(" select * from V_MATER_DATATRANS_RULEINFORANK o");*/
		/*sqlStr.append(" select count( medical_id) as person_number ,tt.rule_name as rule_name from T_PICCBID_VIOLATION_DETAILS t  left  ");
		sqlStr.append(" join t_piccbid_dict_rule tt on t.type_no = tt.rule_no group by tt.rule_name order by person_number desc ");*/
		@SuppressWarnings("unchecked")
		List<superviseRuleIndex> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),superviseRuleIndex.class, hqlParamMap);
		List<superviseRuleIndex> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), superviseRuleIndex.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void ProvinceNumber(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append(" select d.pnumber as person_number,dd.unit_name as province_name  from  ( select  distinct a.city_code as city_code  ,count( a.city_code) as pnumber  from  t_piccbid_dict_city_org a join ");
		sqlStr.append(" (select * from t_piccbid_medical m  join (select t.*,p.item_cost,p.medical_id as aaa  from T_PICCBID_VIOLATION_DETAILS t   left join t_piccbid_medical_detail p on ");
		sqlStr.append(" t.medical_detail_id = p.id) c on m.id=c.aaa) b on a.org_code =b.org_code  group by  a.city_code ) d  left join  T_PICCBID_UNIT dd on  d.city_code = dd.city_code order by person_number desc");
		@SuppressWarnings("unchecked")
		List<superviseRuleIndex> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),superviseRuleIndex.class, hqlParamMap);
		List<superviseRuleIndex> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), superviseRuleIndex.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void TcNumber(AdminDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		sqlStr.append(" select pname,decode(pnumber,'','0',pnumber) as pnumber,handding_ins_code from T_VMATER_DATATRANS_JGJGTCQPJS  ");
		sqlStr.append(" where 1=1  and role_id='"+role_id+"'  order by to_number(pnumber) desc ");
		/*sqlStr.append(" select  distinct  k.city_adminarea as province_name , count( k.city_adminarea) as person_number from   T_PICCBID_DICT_CITY k  join (select a.city_code as city_code  from ");
		sqlStr.append(" t_piccbid_dict_city_org a join (select * from t_piccbid_medical m join (select t.*,p.item_cost,p.medical_id as aaa  from T_PICCBID_VIOLATION_DETAILS t  ");
		sqlStr.append(" left join t_piccbid_medical_detail p on t.medical_detail_id = p.id) c on m.id=c.aaa) b on a.org_code =b.org_code) tt  on k.city_code=tt.city_code  group by k.city_adminarea order by person_number desc");*/
		@SuppressWarnings("unchecked")
		List<superviseRuleIndex> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),superviseRuleIndex.class, hqlParamMap);
		List<superviseRuleIndex> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), superviseRuleIndex.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	
	public void AverageNumber(AdminDto dto) {
		String number="3";
		if (dto.getNumber() != null && !"".equals(dto.getNumber())) {
			number=dto.getNumber();
        }
		int a = Integer.parseInt(number);
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		List<String> listCityCode = unitService.getUserDataAhthority();
		//新授权
		String cityCode = "org_code,handding_ins_code,city_code";
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		sqlStr.append("  select  distinct area as pname ,ceil(t.pnumber) as pnumber ,decode(t.cv_number,'null','0',t.cv_number) as cv_number from T_VMATER_DATATRANS_ssjkanbqzy t   join ");
		sqlStr.append("  (select   pnumber, condition from ( select STAY_LENGTH as pnumber, condition ,count(id) as aaa from t_piccbid_medical where 1 = 1 and STAY_LENGTH != 0 and STAY_LENGTH is not null   ");
		unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
		sqlStr.append("   group by condition  ,STAY_LENGTH) where aaa>=30 ) c  on t.area=c.condition where 1=1 and (c.pnumber >=t.pnumber*"+a+"  or c.pnumber <=ceil(to_number(t.pnumber)/"+a+")) and t.role_id='"+role_id+"'  and ( round(t.pnumber,0)!=0 and  round(t.pnumber,0) is not null ) ");
		if (dto.getInFlag() != null && !"".equals(dto.getInFlag())) {
			sqlStr.append(" and t.area like'%"+dto.getInFlag()+"%'  ");
		}
		sqlStr.append(" order by to_number(pnumber) desc ");
		/*sqlStr.append(" select area as pname ,round(pnumber,0) as pnumber from T_VMATER_DATATRANS_ssjkanbqzy  ");
		sqlStr.append(" where 1=1  and role_id='"+role_id+"' order by to_number(pnumber) desc ");*/
		@SuppressWarnings("unchecked")
		List<superviseRuleIndex> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),superviseRuleIndex.class, hqlParamMap);
		List<superviseRuleIndex> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), superviseRuleIndex.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
	}
	

	public List<superviseRuleIndex> TcNumberDate() {
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		String sql = "select type_names as pname ,count_num as pnumber from T_VMATER_violation_details where role_id='"+role_id+"'  and  rownum<11 order by to_number(pnumber)  desc ";
		@SuppressWarnings("unused")
		List<superviseRuleIndex> queryAllMatchList = jdbcTemplateWrapper.queryAllMatchList(sql, superviseRuleIndex.class, null);
		if (queryAllMatchList.size() > 0) {
			return queryAllMatchList;
		} else {
			return null;
		}
	}
	
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		List<String> listCityCode = unitService.getUserDataAhthority();
		//新授权
		String cityCode = "org_code,handding_ins_code,city_code";
		StringBuilder sql = new StringBuilder(" select STAY_LENGTH as pnumber  ,name as  pname , to_char(BALANCE_DATE, 'yyyy-mm-dd') as pdate, to_number( decode(to_char(BALANCE_DATE, 'mm'),'01','1','02','2','03','3','04','4','05','5','06','6','07','7','08','8','09','9',to_char(BALANCE_DATE, 'mm'))) as person_number from t_piccbid_medical where STAY_LENGTH !=0 and   STAY_LENGTH is  not null and condition ='"+dto.getInFlag()+"'  ");
		unitService.appendDataAuhoritySql(cityCode,sql,listCityCode);
		sql.append(" order by    to_char(BALANCE_DATE, 'yyyy-mm-dd')  asc ");
		@SuppressWarnings("unused")
		List<superviseRuleIndex> queryAllMatchList = jdbcTemplateWrapper.queryAllMatchList(sql.toString(), superviseRuleIndex.class, null);
		if (queryAllMatchList.size() > 0) {
			return queryAllMatchList;
		} else {
			return null;
		}
	}
	
	public superviseRuleIndex getName() {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		List<String> listCityCode = unitService.getUserDataAhthority();
		//新授权
		String cityCode = "org_code,handding_ins_code,city_code";
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String role_id = user.getRoleId();
		sqlStr.append("  select  distinct area as pname ,ceil(t.pnumber) as pnumber from T_VMATER_DATATRANS_ssjkanbqzy t   join ");
		sqlStr.append("  (select STAY_LENGTH as pnumber  ,condition  from t_piccbid_medical where 1=1 and  STAY_LENGTH !=0 and   STAY_LENGTH is  not null  ");
		unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);
		sqlStr.append("   ) c  on t.area=c.condition where 1=1 and (c.pnumber >=t.pnumber*3  or c.pnumber <=ceil(to_number(t.pnumber)/3)) and t.role_id='"+role_id+"'  and ( ceil(t.pnumber)!=0 and  ceil(t.pnumber) is not null ) ");
		sqlStr.append("  and  rownum<2  order by to_number(pnumber) desc ");
		/*sqlStr.append(" select area as pname ,round(pnumber,0) as pnumber from T_VMATER_DATATRANS_ssjkanbqzy  ");
		sqlStr.append(" where 1=1  and role_id='"+role_id+"'  and rownum<2 order by to_number(pnumber) desc ");*/
		@SuppressWarnings("unused")
		List<superviseRuleIndex> queryAllMatchList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), superviseRuleIndex.class,hqlParamMap);
		if (queryAllMatchList.size() > 0) {
			return queryAllMatchList.get(0);
		} else {
			return null;
		}
	}



}

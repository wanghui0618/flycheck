package com.dhcc.piccbid.dao.flyAvgDay;

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
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
import com.dhcc.piccbid.entity.flyAvgDay.FlyAvgDay;
//import com.dhcc.piccbid.entity.medicalAnalysis.SingleDisease;
//import com.dhcc.piccbid.entity.medicalAnalysis.YearDataVo;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.unit.UnitService;

@Component
public class FlyAvgDayJdbcDao {

	private static Log logger = LogFactory.getLog(FlyAvgDayJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;

	/** 
	* 方法名:          flyAvgDay
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年10月15日 下午2:53:45
	*/
	public void flyAvgDay(FlyAvgDayDto dto) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder();
		String inFlag  = dto.getInFlag();
		String orgName = dto.getOrgName();
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
		sqlStr.append("select aa.*,bb.stay_length,bb.m_time\r\n" + 
				"from(\r\n" + 
				"select b.*,a.patient_name as name  from t_fly_medical_inhos a\r\n" + 
				"inner join (select sum(zyts) as all_day,a.hospital_id as org_code,a.hospital_name as org_name,year as year_time,\r\n" + 
				"round(count(*)/max(to_number(month)),2) as M_num,\r\n" + 
				"round(avg(zyts),2) as avg_stay_length,max(zyts) as max_stay_length\r\n" + 
				"from t_fly_medical_inhos a\r\n" + 
				"group by a.hospital_id,a.hospital_name,year) b\r\n" + 
				"on a.hospital_id = b.org_code and a.zyts = b.max_stay_length and a.year = b.year_Time) aa\r\n" + 
				"left join (\r\n" + 
				"select t.* from \r\n" + 
				"(select sum(zyts) as stay_length,hospital_id as org_code,year as year_time,month as m_time\r\n" + 
				"from t_fly_medical_inhos\r\n" + 
				"group by year,month,hospital_id) t\r\n" + 
				"inner join\r\n" + 
				"(select max(stay_length) as stay_length ,year_time,org_code from \r\n" + 
				"(select sum(zyts) as stay_length,hospital_id as org_code,year as year_time,month as m_time\r\n" + 
				"from t_fly_medical_inhos\r\n" + 
				"group by year,month,hospital_id) group by year_time,org_code) q\r\n" + 
				"on t.stay_length = q.stay_length and t.year_Time = q.year_Time and t.org_code = q.org_code)\r\n" + 
				"bb on aa.org_code = bb.org_code and aa.year_Time = bb.year_Time "+
				" where 1=1 and aa.year_Time = '"+inFlag+"' ");
		if(orgName!=null && !"".equals(orgName)) {
			sqlStr.append(" and aa.org_name='"+orgName+"' ");
		}
		StringBuilder sss = new StringBuilder();
		sss.append("select * from ("+sqlStr+")   t " );
		sqlStr.append(" order by stay_length desc nulls last ");
		//System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<FlyAvgDay> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyAvgDay.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sss.toString(),"rownum", hqlParamMap);
		dto.getPageModel().setTotals(count);
		/*dto.getPageModel().setTotals(list.size());*/
		dto.getPageModel().setPageData(list);
	}

	






	

}

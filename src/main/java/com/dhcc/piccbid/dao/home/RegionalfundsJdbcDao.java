package com.dhcc.piccbid.dao.home;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.home.MonitorPolygonalchartDto;
import com.dhcc.piccbid.dto.home.RegionalfundsDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
import com.dhcc.piccbid.entity.home.MonitorPolygonalchart;
import com.dhcc.piccbid.entity.home.Regionalfunds;
import com.dhcc.piccbid.entity.hospitalizationMonitor.HospitalizationMonitor;
import com.dhcc.piccbid.entity.user.User;

import com.dhcc.piccbid.service.unit.UnitService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RegionalfundsJdbcDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	@Resource
	private UnitService unitService;

	public void listVo(RegionalfundsDto dto){

		PageModel page=dto.getPageModel();
		Map<String,Object> hqlParamMap=new HashMap<String,Object>();
		StringBuilder sql=new StringBuilder();
		User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		if(dto.getRegionalfunds().getPlace().equals("org")) {
			sql.append("select * from T_VMATER_MONTOR_HOME_ORG where role_id='"+roleId+"' order by funds desc");
			List<Regionalfunds> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),Regionalfunds.class,hqlParamMap,1,6,"id");
			page.setPageData(list);
		}
		if(dto.getRegionalfunds().getPlace().equals("city")){
			sql.append("   select * from T_VMATER_monitor_home_handding where role_id='"+roleId+"' order by funds desc\n");
			List<Regionalfunds> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),Regionalfunds.class,hqlParamMap,1,4,"id");
			page.setPageData(list);
		}

	}

	public void monitorPolygonalchart(MonitorPolygonalchartDto dto){
		PageModel page=new PageModel();
		Map<String,Object> hqlParamMap=new HashMap<String,Object>();
		StringBuilder sql=new StringBuilder();
		List<MonitorPolygonalchart> list=new ArrayList<MonitorPolygonalchart>();
		User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
		sql.append("select * from T_VMATER_monitor_working where role_id='"+roleId+"'");
		List<MonitorPolygonalchart>  m=(jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),MonitorPolygonalchart.class,hqlParamMap));
		page.setPageData(m);
		dto.setPageModel(page);
	}

	public void totalTimes(HospitalizationMonitorDto dto){
		PageModel page=new PageModel();
		Map<String,Object> hqlParamMap=new HashMap<String,Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select count(*) as total_amount,diag_type as diag from t_piccbid_medical where  to_char(BALANCE_DATE,'YYYY')=to_char(sysdate,'YYYY')  " );
		List<String> listCityCode = unitService.getUserDataAhthority();
		//String cityCode = "t.org_code";
		//新授权
		String cityCode = "org_code,handding_ins_code,city_code ";

		// 调用数据授权
		sql = unitService.appendDataAuhoritySql(cityCode, sql, listCityCode);
		sql.append("group  by diag_type");
		List<HospitalizationMonitor> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),HospitalizationMonitor.class,hqlParamMap,1,4,"id");
		page.setPageData(list);
		dto.setPageModel(page);
	}
	/**
	 * @param dto
	 */
	public void bigDataHomepage(RegionalfundsDto dto) {
		//从session中取出user对象
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();

		Map<String,Object> hqlParamMap=new HashMap<String,Object>();
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from T_VMATER_HOME_BIGDATA where role_id='"+roleId+"' ");
		@SuppressWarnings("unchecked")
		List<RegionalfundsDto> stuList=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), RegionalfundsDto.class, hqlParamMap);
		if(stuList.get(0).getMenZhenRenCi()==null||"".equals(stuList.get(0).getMenZhenRenCi())) {
			dto.setMenZhenRenCi("0");
		}else {
			dto.setMenZhenRenCi(stuList.get(0).getMenZhenRenCi());
		}
		if(stuList.get(0).getZuoRiJiuZhen()==null||"".equals(stuList.get(0).getZuoRiJiuZhen())) {
			dto.setZuoRiJiuZhen("0");
		}else {
			dto.setZuoRiJiuZhen(stuList.get(0).getZuoRiJiuZhen());
		}
		if(stuList.get(0).getZongFeiYong()==null||"".equals(stuList.get(0).getZongFeiYong())) {
			dto.setZongFeiYong("0");
		}else {
			dto.setZongFeiYong(stuList.get(0).getZongFeiYong());
		}
		
		dto.setBenZhouRenShu(stuList.get(0).getBenZhouRenShu());
		if(stuList.get(0).getZuoRiChuYuan()==null||"".equals(stuList.get(0).getZuoRiChuYuan())) {
			dto.setZuoRiChuYuan("0");
		}else {
			dto.setZuoRiChuYuan(stuList.get(0).getZuoRiChuYuan());
		}
		
		if(stuList.get(0).getZaiYuanRenShu()==null||"".equals(stuList.get(0).getZaiYuanRenShu())) {
			dto.setZaiYuanRenShu("1");
		}else {
			dto.setZaiYuanRenShu(stuList.get(0).getZaiYuanRenShu());
		}
		dto.setShouShu(stuList.get(0).getShouShu());
		dto.setMenZhenShouShu(stuList.get(0).getMenZhenShouShu());
		dto.setZhuYuanShouShu(stuList.get(0).getZhuYuanShouShu());
		if(stuList.get(0).getMenFeiYong()==null||"".equals(stuList.get(0).getMenFeiYong())) {
			dto.setMenFeiYong("0");
		}else {
			dto.setMenFeiYong(stuList.get(0).getMenFeiYong());
		}
		if(stuList.get(0).getMenTeTotal()==null||"".equals(stuList.get(0).getMenTeTotal())) {
			dto.setMenTeTotal("0");
		}else {
			dto.setMenTeTotal(stuList.get(0).getMenTeTotal());
		}
		if(stuList.get(0).getMenTeRenCi()==null||"".equals(stuList.get(0).getMenTeRenCi())) {
			dto.setMenTeRenCi("0");
		}else {
			dto.setMenTeRenCi(stuList.get(0).getMenTeRenCi());
		}
		
		dto.setMenFeiYongNum(stuList.get(0).getMenFeiYongNum());
		if(stuList.get(0).getMenYao()==null||"".equals(stuList.get(0).getMenYao())) {
			dto.setMenYao("0");
		}else {
			dto.setMenYao(stuList.get(0).getMenYao());
		}
		
		dto.setMenYaoNum(stuList.get(0).getMenYaoNum());
		if(stuList.get(0).getMenZhenliao()==null||"".equals(stuList.get(0).getMenZhenliao())) {
			dto.setMenZhenliao("0");
		}else {
			dto.setMenZhenliao(stuList.get(0).getMenZhenliao());

		}
		
		dto.setMenZhenliaoNum(stuList.get(0).getMenZhenliaoNum());
		if(stuList.get(0).getMenHao()==null||"".equals(stuList.get(0).getMenHao())) {
			dto.setMenHao("0");
		}else {
			dto.setMenHao(stuList.get(0).getMenHao());

		}
		
		dto.setMenHaoNum(stuList.get(0).getMenHaoNum());
		if(stuList.get(0).getZhuFeiYong()==null||"".equals(stuList.get(0).getZhuFeiYong())) {
			dto.setZhuFeiYong("0");
		}else {
			dto.setZhuFeiYong(stuList.get(0).getZhuFeiYong());

		}
		if(stuList.get(0).getRuYuanRenCi()==null||"".equals(stuList.get(0).getRuYuanRenCi())) {
			dto.setRuYuanRenCi("0");
		}else {
			dto.setRuYuanRenCi(stuList.get(0).getRuYuanRenCi());

		}
		
		if(stuList.get(0).getZhuYao()==null||"".equals(stuList.get(0).getZhuYao())) {
			dto.setZhuYao("0");
		}else {
			dto.setZhuYao(stuList.get(0).getZhuYao());

		}
		dto.setZhuFeiYongNum(stuList.get(0).getZhuFeiYongNum());
		
		dto.setZhuYaoNum(stuList.get(0).getZhuYaoNum());
		if(stuList.get(0).getZhuZhenliao()==null||"".equals(stuList.get(0).getZhuZhenliao())) {
			dto.setZhuZhenliao("0");
		}else {
			dto.setZhuZhenliao(stuList.get(0).getZhuZhenliao());

		}
		
		dto.setZhuZhenliaoNum(stuList.get(0).getZhuZhenliaoNum());
		if(stuList.get(0).getZhuHao()==null||"".equals(stuList.get(0).getZhuHao())) {
			dto.setZhuHao("0");
		}else {
			dto.setZhuHao(stuList.get(0).getZhuHao());

		}
		
		dto.setZhuHaoNum(stuList.get(0).getZhuHaoNum());
		if(stuList.get(0).getYiJi()==null||"".equals(stuList.get(0).getYiJi())) {
			dto.setYiJi("0");
		}else {
			dto.setYiJi(stuList.get(0).getYiJi());

		}
		
		if(stuList.get(0).getErJi()==null||"".equals(stuList.get(0).getZhuHao())) {
			dto.setErJi("0");
		}else {
			dto.setErJi(stuList.get(0).getErJi());

		}
	
		if(stuList.get(0).getSanJi()==null||"".equals(stuList.get(0).getSanJi())) {
			dto.setSanJi("0");
		}else {
			dto.setSanJi(stuList.get(0).getSanJi());

		}
		
		if(stuList.get(0).getShiShu()==null||"".equals(stuList.get(0).getShiShu())) {
			dto.setShiShu("0");
		}else {
			dto.setShiShu(stuList.get(0).getShiShu());
		}
		
		if(stuList.get(0).getXianShu()==null||"".equals(stuList.get(0).getXianShu())) {
			dto.setXianShu("0");
		}else {
			dto.setXianShu(stuList.get(0).getXianShu());

		}
		
		if(stuList.get(0).getQuShu()==null||"".equals(stuList.get(0).getQuShu())) {
			dto.setQuShu("0");
		}else {
			dto.setQuShu(stuList.get(0).getQuShu());

		}
		
		if(stuList.get(0).getYiRi()==null||"".equals(stuList.get(0).getYiRi())) {
							dto.setYiRi("0");
						}else {
							dto.setYiRi(stuList.get(0).getYiRi());
						}

		if(stuList.get(0).getErRi()==null||"".equals(stuList.get(0).getErRi())) {
							dto.setErRi("0");
						}else {
							dto.setErRi(stuList.get(0).getErRi());
						}

		if(stuList.get(0).getSanRi()==null||"".equals(stuList.get(0).getSanRi())) {
							dto.setSanRi("0");
						}else {
							dto.setSanRi(stuList.get(0).getSanRi());
						}

		if(stuList.get(0).getSiRi()==null||"".equals(stuList.get(0).getSiRi())) {
							dto.setSiRi("0");
						}else {
							dto.setSiRi(stuList.get(0).getSiRi());
						}

		if(stuList.get(0).getWuRi()==null||"".equals(stuList.get(0).getWuRi())) {
							dto.setWuRi("0");
						}else {
							dto.setWuRi(stuList.get(0).getWuRi());
						}

		if(stuList.get(0).getLiuRi()==null||"".equals(stuList.get(0).getLiuRi())) {
							dto.setLiuRi("0");
						}else {
							dto.setLiuRi(stuList.get(0).getLiuRi());
						}

		if(stuList.get(0).getQiRi()==null||"".equals(stuList.get(0).getQiRi())) {
							dto.setQiRi("0");
						}else {
							dto.setQiRi(stuList.get(0).getQiRi());
						}


	}
}

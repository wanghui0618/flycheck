package com.dhcc.piccbid.dao.flyMedicalDetail;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetailVo;

@Component
public class FlyMedicalDetailJdbcDao {
	
	private static Log logger = LogFactory.getLog(FlyMedicalDetailJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	/***
	 * 检查费收费金额次数统计
	 * @param dto
	 */
	public void list(FlyMedicalDetailDto dto) {
		if(dto.getFlyMedicalDetailVo()==null) {
			dto.setFlyMedicalDetailVo(new FlyMedicalDetailVo());
		}
		String year=dto.getYear();
		String month=dto.getMonth();
		if(month==null||"".equals(month)) {
			month=Calendar.getInstance().get(Calendar.MONTH)+1+"";
		}else {
			if(year==null||"".equals(year)) {
				year=month.split("-")[0];
			}
			month=month.split("-")[1];
		}
		if(year==null||"".equals(year)) {
			year=Calendar.getInstance().get(Calendar.YEAR)+"";
		}
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		sqlStr.append("select * from (select nvl(sum(cost),0)as money,count(*) as item_num,hospital_name,item_name_hosp from\r\n" + 
				"(select cost,hospital_id,hospital_name,item_id_hosp,item_name_hosp\r\n" + 
				"from t_fly_detail_inhos where p_category='检查费' and year='"+year+"' and month='"+month+"' union all\r\n" + 
				"select cost,hospital_id,hospital_name,item_id_hosp,item_name_hosp\r\n" + 
				"from t_fly_detail_menz where p_category='检查费' and year='"+year+"' and month='"+month+"')\r\n" + 
				"group by hospital_name,item_name_hosp) where hospital_name is not null and item_name_hosp is not null ");
		
		if(dto.getFlyMedicalDetailVo()!=null) {
			if(dto.getFlyMedicalDetailVo().getItemNameHosp()!=null&&!"".equals(dto.getFlyMedicalDetailVo().getItemNameHosp())) {
				sqlStr.append(" and item_name_hosp like '%"+dto.getFlyMedicalDetailVo().getItemNameHosp()+"%'");
			}
			if(dto.getFlyMedicalDetailVo().getHospitalName()!=null&&!"".equals(dto.getFlyMedicalDetailVo().getHospitalName())) {
				sqlStr.append(" and hospital_name like '%"+dto.getFlyMedicalDetailVo().getHospitalName()+"%'");
			}
		}
		
		sqlStr.append(" order by item_num desc nulls last");
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);

	}

}

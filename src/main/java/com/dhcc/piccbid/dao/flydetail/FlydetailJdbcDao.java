package com.dhcc.piccbid.dao.flydetail;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flydetail.FlydetailDto;
import com.dhcc.piccbid.service.unit.UnitService;

@Component
public class FlydetailJdbcDao {

	private static Log logger = LogFactory.getLog(FlydetailJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;

	public void list(FlydetailDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="city_code";*/
		sqlStr.append(" select * from t_fly_medical_detail where 1=1 ");
		/*sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);*/
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);

	}






	

}

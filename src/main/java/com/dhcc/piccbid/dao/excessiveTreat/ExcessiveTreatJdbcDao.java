package com.dhcc.piccbid.dao.excessiveTreat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.excessiveTreat.ExcessiveTreatDto;
//import com.dhcc.piccbid.dto.indicationTreat.IndicationTreatDto;
//import com.dhcc.piccbid.dto.outprice.OutpriceDto;
//import com.dhcc.piccbid.dto.resolveFee.ResolveFeeDto;
import com.dhcc.piccbid.entity.excessiveTreat.ExcessiveTreat;
//import com.dhcc.piccbid.entity.indicationTreat.IndicationTreat;
//import com.dhcc.piccbid.entity.outprice.Outprice;
//import com.dhcc.piccbid.entity.resolveFee.ResolveFee;
import com.dhcc.piccbid.service.unit.UnitService;
import com.sun.org.apache.bcel.internal.generic.Select;

@Component
public class ExcessiveTreatJdbcDao {

	private static Log logger = LogFactory.getLog(ExcessiveTreatJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;

	public void list(ExcessiveTreatDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="city_code";*/
		/*sqlStr.append(" select    id , city_code ,  type_no , type_name  ,item_code , item_name, logic_type, " );
		sqlStr.append(" logic_type_detail ,logic , logic_sql, comments,  relevance_item from T_PICCBID_OUTPRICE where 1=1 ");*/
		sqlStr.append("select * from T_PICCBID_EXCESSIVE_TREATMENT where 1=1");
		ExcessiveTreat excessiveTreat = dto.getExcessiveTreat();
		if (excessiveTreat != null) {
			System.out.println(excessiveTreat.getCityCode());
			if (excessiveTreat.getCityCode() != null && !"".equals(excessiveTreat.getCityCode())) {
				sqlStr.append(" and city_code='" + excessiveTreat.getCityCode() + "'");
			}
			if (excessiveTreat.getTypeNo() != null && !"".equals(excessiveTreat.getTypeNo())) {
				sqlStr.append(" and type_no='" + excessiveTreat.getTypeNo() + "'");
			}
			if (excessiveTreat.getTypeName() != null && !"".equals(excessiveTreat.getTypeName())) {
				sqlStr.append(" and type_name='" + excessiveTreat.getTypeName() + "'");
			}
		}
		/*sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);*/
		//sqlStr.append(" order by comments asc");
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);

	}






	

}

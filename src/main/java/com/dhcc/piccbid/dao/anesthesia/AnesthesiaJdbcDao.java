/**
 * 
 */
package com.dhcc.piccbid.dao.anesthesia;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.anesthesia.AnesthesiaDto;
import com.dhcc.piccbid.entity.anesthesia.AnesthesiaVo;

/**
 * @author zwy15
 *
 */
@Component
public class AnesthesiaJdbcDao {
	 @Resource
	 private JdbcTemplate jdbcTemplate;
	 @Resource
	 private JdbcTemplateWrapper jdbcTemplateWrapper;
	/**
	 * @param dto
	 */
	public void listVo(AnesthesiaDto dto) {
		 StringBuilder sqlStr=new StringBuilder();
			Map<String,Object> hqlParamMap = new HashMap<String,Object>();
			dto.getPageModel().setHqlParamMap(hqlParamMap);
			// item_id=330100007  的为 item_name='支气管内麻醉'
 			sqlStr.append("select * from T_FLYCHECK_MEDICAL b where  b.HISID in " + 
					"(select a.HISID from (select HISID,count(item_id) as num from T_FLYCHECK_MEDICAL_DETAIL where item_id='330100007' group by HISID) a where num>1)" );
			AnesthesiaVo anesthesia = dto.getAnesthesiaVo();
			if(anesthesia!=null) {
				if (anesthesia.getHospitalName()!=null&&!"".equals(anesthesia.getHospitalName())) {
					sqlStr.append(" and b.Hospital_Name like '%"+anesthesia.getHospitalName()+"%' ");
				}if (anesthesia.getHisid()!=null&&!"".equals(anesthesia.getHisid())) {
					sqlStr.append(" and b.HISID like '%"+anesthesia.getHisid()+"%' ");
				}
			}
			sqlStr.append("order by b.HISID  asc");
			System.err.println(sqlStr.toString());
			dto.getPageModel().setQueryHql(sqlStr.toString());
			dto.getPageModel().setHqlParamMap(hqlParamMap);
		
	 }

}

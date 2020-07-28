package com.dhcc.piccbid.dao.nursingViolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.nursingViolation.NursingViolationDto;
import com.dhcc.piccbid.entity.nursingViolation.NursingViolation;

@Component
public class NursingViolationJdbcDao extends HibernatePersistentObjectDAO<NursingViolation>{
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private  JdbcTemplateWrapper jdbcTemplateWrapper;
	public void nursingViolation(NursingViolationDto dto) {
		String limitgrade = dto.getLimitgrade();
		PageModel pageModel = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		NursingViolation nursingViolation = (NursingViolation)WebContextHolder.getContext().getRequest().getSession().getAttribute("nursingViolation");
        sqlStr.append("select a.patient_id,a.social_card_id,a.medical_record_id,a.benefit_type,a.patient_name,\r\n" + 
        		"a.patient_gender,a.patient_birthday,a.patient_age,a.hospital_id,a.hospital_name,b.item_name,\r\n" + 
        		"a.admission_date,a.discharge_date,a.zyts from \r\n" + 
        		"T_FLY_MEDICAL_INHOS a,T_FLY_DETAIL_INHOS b where a.hisid=b.hisid and b.item_name like '%特级护理%'\r\n" + 
        		"group by a.patient_id,a.social_card_id,a.medical_record_id,a.benefit_type,a.patient_name,\r\n" + 
        		"a.patient_gender,a.patient_birthday,a.patient_age,a.hospital_id,a.hospital_name,\r\n" + 
        		"a.admission_date,a.discharge_date,a.zyts,b.item_name");
        
        if(limitgrade != null && !limitgrade.equals("")) {
        	sqlStr.append(" having b.item_name like '%"+limitgrade+"%'");
        }
        @SuppressWarnings("unchecked")
        List<NursingViolation> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),NursingViolation.class, hqlParamMap);
        List<NursingViolation> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), NursingViolation.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
        dto.getPageModel().setTotals(listCount.size());
        dto.getPageModel().setPageData(list);
		
	}

}

package com.dhcc.piccbid.dao.hospitalizationConditions;

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
import com.dhcc.piccbid.dto.hospitalizationConditions.HospitalizationConditionsDto;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;
import com.dhcc.piccbid.entity.hospitalizationConditions.HospitalizationConditions;
@Component
public class HospitalizationConditionsJdbcDao extends HibernatePersistentObjectDAO<HospitalizationAnalysis>{
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private  JdbcTemplateWrapper jdbcTemplateWrapper;
	public void hospitalizationConditions(HospitalizationConditionsDto dto) {
		String admissionDiseaseId = dto.getAdmissionDiseaseId();
		String admissionDiseaseName = dto.getAdmissionDiseaseName();
		String limitdays = dto.getLimitdays();	
		String limitgrade = dto.getLimitgrade();
		PageModel pageModel = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		HospitalizationConditions hospitalizationConditions = (HospitalizationConditions)WebContextHolder.getContext().getRequest().getSession().getAttribute("hospitalizationConditions");
		sqlStr.append("select a.MEDICAL_RECORD_ID,a.PATIENT_NAME,a.PATIENT_GENDER,a.PATIENT_AGE,a.HOSPITAL_ID,a.HOSPITAL_NAME,b.ITEM_NAME,b.ITEM_ID "
				+ "from  T_FLY_MEDICAL_INHOS a,T_FLY_DETAIL_INHOS b where a.hisid=b.hisid");
		
		if(admissionDiseaseName != null && !admissionDiseaseName.equals("")) {
			sqlStr.append(" and a.admission_disease_name='"+admissionDiseaseName+"'");
		}
		if(limitdays != null && !limitdays.equals("")) {
			sqlStr.append(" and a.zyts>="+limitdays);
		}
		if(limitgrade != null && !limitgrade.equals("")) {
			sqlStr.append(" and b.item_name like '%"+limitgrade+"%'");
		}
		@SuppressWarnings("unchecked")
        List<HospitalizationConditions> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalizationConditions.class, hqlParamMap);
        List<HospitalizationConditions> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalizationConditions.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
        dto.getPageModel().setTotals(listCount.size());
        dto.getPageModel().setPageData(list);
	}
	
	
	public void getlist(HospitalizationConditionsDto dto) {
		
		PageModel pageModel = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		HospitalizationConditions hospitalizationConditions = (HospitalizationConditions)WebContextHolder.getContext().getRequest().getSession().getAttribute("hospitalizationConditions");
		sqlStr.append("select a.id,a.admission_disease_id,a.admission_disease_name from T_FLY_CONDITION a");
		 @SuppressWarnings("unchecked")
	        List<HospitalizationConditions> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(),HospitalizationConditions.class, hqlParamMap);
	        List<HospitalizationConditions> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalizationConditions.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
	        dto.getPageModel().setTotals(listCount.size());
	        dto.getPageModel().setPageData(list);
	}

}

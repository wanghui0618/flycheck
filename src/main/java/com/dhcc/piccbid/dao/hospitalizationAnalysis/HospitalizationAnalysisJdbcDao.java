package com.dhcc.piccbid.dao.hospitalizationAnalysis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.hibernate.dao.HibernatePersistentObjectDAO;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.hospitalizationAnalysis.HospitalizationAnalysisDto;
import com.dhcc.piccbid.entity.hospitalizationAnalysis.HospitalizationAnalysis;
@Component
public class HospitalizationAnalysisJdbcDao extends HibernatePersistentObjectDAO<HospitalizationAnalysis> {
	private static Log logger = LogFactory.getLog(HospitalizationAnalysisJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private  JdbcTemplateWrapper jdbcTemplateWrapper;
	
	public void hospitalizationAnalysis(HospitalizationAnalysisDto dto) {
		BigDecimal zyts = dto.getZyts();
		String num = dto.getNum();
		String admissionDate = dto.getAdmissionDate();
		String  dischargeDate = dto.getDischargeDate();
		PageModel pageModel = new PageModel();
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}		
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		HospitalizationAnalysis hospitalizationAnalysis = (HospitalizationAnalysis)WebContextHolder.getContext().getRequest().getSession().getAttribute("hospitalizationAnalysis");
		sqlStr.append("select * from T_FLY_MEDICAL_INHOS t where 1=1");
		if(zyts != null && !zyts.equals("")) {
			sqlStr.append("and zyts="+zyts);
		}
		if(num != null && !num.equals("")) {
			sqlStr.append("and (select  count(*) from T_FLY_MEDICAL_INHOS group by patient_id having count(*)="+num+")="+num );
		}
		if(admissionDate != null && !admissionDate.equals("")) {
			sqlStr.append("and admission_date>to_date('"+admissionDate+"','yyyy-mm-dd hh24:mi:ss')");
		}
		if(dischargeDate !=null && !dischargeDate.equals("")) {
			sqlStr.append("and discharge_Date<to_date('"+dischargeDate+"','yyyy-mm-dd hh24:mi:ss')");
		}
		System.out.println(sqlStr);
		@SuppressWarnings("unchecked")
		List<HospitalizationAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), HospitalizationAnalysis.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "hisid");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(),"hisid", hqlParamMap);
		dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
	}
}

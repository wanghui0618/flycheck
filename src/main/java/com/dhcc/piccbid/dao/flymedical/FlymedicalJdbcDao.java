package com.dhcc.piccbid.dao.flymedical;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;
import com.dhcc.piccbid.service.unit.UnitService;

@Component
public class FlymedicalJdbcDao {

	private static Log logger = LogFactory.getLog(FlymedicalJdbcDao.class);

	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	@Autowired
	private UnitService unitService;

	public void list(FlymedicalDto dto) {
		StringBuilder sqlStr = new StringBuilder();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		/*List<String> listCityCode=unitService.getUserDataAhthority();
		String cityCode="city_code";*/
		sqlStr.append(" select * from T_FLY_MEDICAL where 1=1 ");
		/*sqlStr=unitService.appendDataAuhoritySql(cityCode,sqlStr,listCityCode);*/
		dto.getPageModel().setQueryHql(sqlStr.toString());
		dto.getPageModel().setHqlParamMap(hqlParamMap);

	}

	public void screenSameEntryAndExitDate(FlymedicalDto dto) {
		StringBuilder sqlB = new StringBuilder();
		sqlB.append("select distinct fm.*\n" + 
				"from t_fly_medicalrecord fm, T_Fly_Medicalrecord fmII\n" + 
				"where fm.bridge_id != fmII.Bridge_Id\n" + 
				"and fm.hospital_name = fmII.Hospital_Name\n" + 
				"and trunc(fm.admission_date, 'dd') = trunc(fmII.Admission_Date, 'dd')\n" + 
				"and trunc(fm.discharge_date, 'dd') = trunc(fmII.Discharge_Date, 'dd')\n");
		
		String year = dto.getYear();
		if(year!=null && !"".equals(year)) {
			sqlB.append("and to_char(fm.admission_date, 'yyyy') = '" + year + "'\n");
		}
		
		StringBuilder couB = new StringBuilder();// 计算总数
		couB.append("select count(1)\n" + 
				"from (" + sqlB.toString() + ")");
		
		sqlB.append("order by fm.hospital_name, fm.admission_date, fm.discharge_date");
		
		String sql = sqlB.toString();
		Map<String, Object> hqlParamMap = new HashMap<String, Object>();
		PageModel pageModel = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<FlyMedicalrecord> flymedicals = jdbcTemplateWrapper.queryAllMatchListWithParaMap(
			sql.toString(),
			FlyMedicalrecord.class,
			hqlParamMap,
			pageModel.getPageNo(),
			pageModel.getPageSize(),
			"rownum"
		);
		pageModel.setPageData(flymedicals);
		
		String cou = couB.toString();
		int count = jdbcTemplateWrapper.queryForInt(cou);
		pageModel.setTotals(count);
		
		pageModel.setHqlParamMap(hqlParamMap);
		
		logger.info(sql);
		logger.info(cou);
	}

	public void relateDiagnosis(FlymedicalDto dto) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_fly_medicalrecord fm\n" +
				"where 1=1\n");
		
		String diagnosis = dto.getDiagnosis();
		if(diagnosis != null) {
			sql.append("and fm.ICD10_NAME_BASY like '%" + diagnosis + "%'\n");
		}
		
		sql.append("order by fm.ICD10_NAME_BASY");
		
		Map<String, Object> map = new HashMap<String, Object>();
		PageModel model = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<FlyMedicalrecord> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(
			sql.toString(),
			FlyMedicalrecord.class,
			map,
			model.getPageNo(),
			model.getPageSize(),
			"rownum"
		);
		int total = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "bridge_id", null);
		
		dto.getPageModel().setTotals(total);
		dto.getPageModel().setPageData(list);
		
		logger.info(sql);
	}

}

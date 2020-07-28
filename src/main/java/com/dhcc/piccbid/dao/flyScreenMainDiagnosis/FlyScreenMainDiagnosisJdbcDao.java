package com.dhcc.piccbid.dao.flyScreenMainDiagnosis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flyScreenMainDiagnosis.FlyScreenMainDiagnosisDto;
import com.dhcc.piccbid.entity.flyScreenMainDiagnosis.FlyScreenMainDiagnosis;

@Component
public class FlyScreenMainDiagnosisJdbcDao {
	
	private static Log logger = LogFactory.getLog(FlyScreenMainDiagnosisJdbcDao.class);// 配置控制台日志
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	public void list(FlyScreenMainDiagnosisDto dto) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select fsmd.*\n" +
				"from t_fly_screen_main_diagnosis fsmd\n" +
				"where 1=1\n");
		
		String diagnosis = dto.getDiagnosis();
		if(diagnosis != null) {
			sql.append("and fsmd.MAIN_DIAGNOSIS like '" + "%" + diagnosis + "%" + "'\n");
		}
		
		sql.append("order by fsmd.PROJECT_NAME, fsmd.MAIN_DIAGNOSIS");
		
		Map<String, Object> map = new HashMap<String, Object>();
		PageModel model = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<FlyScreenMainDiagnosis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(
			sql.toString(),
			FlyScreenMainDiagnosis.class,
			map,
			model.getPageNo(),
			model.getPageSize(),
			"rownum"
		);
		int total = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "id", null);
		
		dto.getPageModel().setTotals(total);
		dto.getPageModel().setPageData(list);
		
		logger.info(sql);
		
	}

}

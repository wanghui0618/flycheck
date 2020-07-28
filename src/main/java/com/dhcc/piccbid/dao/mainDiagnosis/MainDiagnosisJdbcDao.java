package com.dhcc.piccbid.dao.mainDiagnosis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.mainDiagnosis.MainDiagnosisDto;
import com.dhcc.piccbid.entity.mainDiagnosis.MainDiagnosis;

@Component
public class MainDiagnosisJdbcDao {
	
	private static Log logger = LogFactory.getLog(MainDiagnosisJdbcDao.class);// 配置控制台日志
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	
	public void list(MainDiagnosisDto dto) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select d.*\n" +
				"from T_PICCBID_MAIN_DIAGNOSIS d\n" +
				"where 1=1\n");
		
		String diagnosis = dto.getDiagnosis();
		if(diagnosis != null) {
			sql.append("and d.MAIN_DIAGNOSIS like '" + "%" + diagnosis + "%" + "'\n");
		}
		
		sql.append("order by d.PROJECT_NAME, d.MAIN_DIAGNOSIS");
		
		Map<String, Object> map = new HashMap<String, Object>();
		PageModel model = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<MainDiagnosis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(
			sql.toString(),
			MainDiagnosis.class,
			map,
			model.getPageNo(),
			model.getPageSize(),
			"rownum"
		);
		int total = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "id", null);
		
		dto.getPageModel().setTotals(total);
		dto.getPageModel().setPageData(list);
		model.setHqlParamMap(map);
		
		logger.info(sql);
		
	}

}

package com.dhcc.piccbid.dao.flyDetailInhos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flyDetailInhos.FlyDetailInhosDto;
import com.dhcc.piccbid.entity.flyDetailInhos.FlyDetailInhos;

@Component
public class FlyDetailInhosJdbcDao {
	
	private static Log logger = LogFactory.getLog(FlyDetailInhosJdbcDao.class);
	
	@Resource
	private JdbcTemplateWrapper wrapper;

	public void statisticsDrugs(FlyDetailInhosDto dto) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t.ITEM_NAME, t.YEAR, sum(t.NUM) as num, sum(t.COST) as cost\r\n" + 
				"from T_FLY_DETAIL_INHOS t\r\n" + 
				"where (t.item_name like '%头孢%'\r\n" +
				"or t.item_name like '%细辛脑%'\r\n" +
				"or t.item_name like '%芎葡头糖%')\r\n" + 
				"and t.year between to_number(to_char(add_months(sysdate, -12),'yyyy')) and to_number(to_char(sysdate,'yyyy'))\r\n" + 
				"group by t.ITEM_NAME, t.YEAR\r\n");
		
		String count = "select count(1) from (" + sql.toString() + ")";// 计算总数
		
		sql.append("order by t.ITEM_NAME, t.YEAR");
		
		Map<String, Object> map = new HashMap<String, Object>();
		PageModel model = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<FlyDetailInhos> list = wrapper.queryAllMatchListWithParaMap(
			sql.toString(),
			FlyDetailInhos.class,
			map,
			model.getPageNo(),
			model.getPageSize(),
			"rownum"
		);
		int total = wrapper.queryForInt(count);
		
		dto.getPageModel().setTotals(total);
		dto.getPageModel().setPageData(list);
		
		logger.info(sql);
		
	}

}

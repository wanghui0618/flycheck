package com.dhcc.piccbid.dao.flyMedicalInhos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flyMedicalInhos.FlyMedicalInhosDto;
import com.dhcc.piccbid.entity.flyMedicalInhos.FlyMedicalInhos;

@Component
public class FlyMedicalInhosJdbcDao {
	
	private static Log log = LogFactory.getLog(FlyMedicalInhosJdbcDao.class);
	
	@Resource
	private JdbcTemplateWrapper wrapper;

	public void findByYear(FlyMedicalInhosDto dto) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(
			"select to_char(i.BILL_DATE, 'yyyy') as year, sum(i.TOTAL_AMOUNT) as TOTAL_AMOUNT, sum(i.BMI_PAY_AMOUNT) as BMI_PAY_AMOUNT, sum(i.SELF_PAY_OUT) as SELF_PAY_OUT\r\n" + 
			"from T_FLY_MEDICAL_INHOS i\r\n" + 
			"group by to_char(i.BILL_DATE, 'yyyy')\r\n"
		);
		
		String count = "select count(1) from (" + sql.toString() + ")";
		int total = wrapper.queryForInt(count);
		
		sql.append("order by to_char(i.BILL_DATE, 'yyyy')");
		
		Map<String, Object> map = new HashMap<String, Object>();
		PageModel model = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<FlyMedicalInhos> list = wrapper.queryAllMatchListWithParaMap(
			sql.toString()
			, FlyMedicalInhos.class
			, map
			, model.getPageNo()
			, model.getPageSize()
			, "rownum"
		);
		
		model.setPageData(list);
		model.setTotals(total);
		
		log.info(sql);
		log.info(count);
		
	}

	public void findByMonth(FlyMedicalInhosDto dto) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(
			"select to_char(i.BILL_DATE, 'mm') as month, sum(i.TOTAL_AMOUNT) as TOTAL_AMOUNT, sum(i.BMI_PAY_AMOUNT) as BMI_PAY_AMOUNT, sum(i.SELF_PAY_OUT) as SELF_PAY_OUT\r\n" + 
			"from T_FLY_MEDICAL_INHOS i\r\n" + 
			"where 1=1\r\n"
		);
		
		String year = dto.getYear();
		
		if(year!=null && !year.equals("")) {
			sql.append("and to_char(i.BILL_DATE, 'yyyy') = " + year + "\r\n");
		}
		
		sql.append("group by to_char(i.BILL_DATE, 'mm')\r\n");
		
		String count = "select count(1) from (" + sql.toString() + ")";
		int total = wrapper.queryForInt(count);
		
		sql.append("order by to_char(i.BILL_DATE, 'mm')");
		
		Map<String, Object> map = new HashMap<String, Object>();
		PageModel model = dto.getPageModel();
		@SuppressWarnings("unchecked")
		List<FlyMedicalInhos> list = wrapper.queryAllMatchListWithParaMap(
			sql.toString()
			, FlyMedicalInhos.class
			, map
			, model.getPageNo()
			, model.getPageSize()
			, "rownum"
		);
		
		model.setPageData(list);
		model.setTotals(total);
		
		log.info(sql);
		log.info(count);
		
	}

}

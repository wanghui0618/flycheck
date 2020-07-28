package com.dhcc.piccbid.dao.diseaseStatistics;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.diseaseStatistics.DiseaseStatisticsDto;

@Component
public class DiseaseStatisticsDao {

	 @Resource
	    private JdbcTemplate jdbcTemplate;
	    @Resource
	    private JdbcTemplateWrapper jdbcTemplateWrapper;
	    
	    public PageModel getDiseaseStatistics(DiseaseStatisticsDto dto){
	        StringBuilder sql = new StringBuilder();
	        sql.append(" select inhosDiag , cases, totalCost , avgCost , fundCost , avgFundCost  from  " );
	        sql.append("( select DRGS_NAME  as inhosDiag,count(*) as cases, sum(total_amount) as totalCost ,");
	        sql.append(" avg(total_amount) as  avgCost,sum(amount) as fundCost , avg(amount) as avgFundCost ");
	        sql.append(" from (");
	        sql.append(" select  DRGS_NAME，total_amount ，（total_amount-self_pay_amount）as amount ");
	        sql.append(" from  T_FLY_MEDICAL_INHOS ");
	        sql.append(" where 1=1 ");
	        sql.append(")");
	        sql.append(" group by DRGS_NAME )" );
	        
	        int n = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"inhosDiag", null);
	        
	        List<Map<String, Object>> list = jdbcTemplate.queryForList(toPageModelSql(sql.toString(), dto.getPage(), dto.getLimit()));
	        dto.getPageModel().setPageData(list);
	        dto.getPageModel().setTotals(n);
	        System.out.println("按病种统计分析:"+sql);
	        return dto.getPageModel();
	    }
	    /**
	     * 方法名：toPageModelSql
	     *方法功能描述：将sql转换为分页sql
	     */
	    public static String toPageModelSql(String sql,int pageNo,int pageSize){
	        sql = " select * from (select a.*,rownum as num1 from ( "+sql+") a where rownum <= "+pageNo*pageSize+")"
	                + " where num1 > "+(pageNo-1)*pageSize;
	        return sql;
	    }
}

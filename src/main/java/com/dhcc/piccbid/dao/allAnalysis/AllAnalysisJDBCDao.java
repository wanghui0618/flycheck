package com.dhcc.piccbid.dao.allAnalysis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.allAnalysis.AllAnalysisDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis2;
import com.dhcc.piccbid.entity.user.User;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-08-25 16:54:00
 * @version V1.0
 */
@Component
public class AllAnalysisJDBCDao {

	/**
	 * @param dto
	 */
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
    
    long year = 0;
    public void search1(AllAnalysisDto dto) {
    	User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		String roleId = user.getRoleId();
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
        Calendar cale = null;
        cale = Calendar.getInstance();
        year = cale.get(Calendar.YEAR);      
		dto.getPageModel().setHqlParamMap(hqlParamMap);
/*        stringBuffer.append(" select nvl(yeartime,0)yeartime,ROW_NUMBER() over(order By CONDITION) as ID,CONDITION AS CON_DITION,");
        stringBuffer.append("count(id) as ALL_COUNT,sum(TOTAL_COST) as ALL_PRICE,sum(TOTAL_COST)/count(id) as AVG_PRICE ");
        stringBuffer.append(" from (select t.*,to_char(balance_date,'yyyy')yeartime from t_piccbid_medical t)  where DIAG_TYPE=1 ");*/
        AllAnalysis allAnalysis = dto.getAllAnalysis();
        //pd_datatrans_cjblzyfy
        stringBuffer.append(" select * from T_VMATER_DATATRANS_cjblzyfy where role_id='"+roleId+"' ");
		if(allAnalysis!=null){
			 if(dto.getAllAnalysis().getYearTime()!=null&&!dto.getAllAnalysis().getYearTime().equals("")){
	                year=Long.parseLong(dto.getAllAnalysis().getYearTime());
	            
			 }     
			if(allAnalysis.getConDition()!=null && !"".equals(allAnalysis.getConDition())){
				String condition = WebContextHolder.getContext().getRequest().getParameter("AllAnalysis.conDition");
				//stringBuffer.append(" and CON_DITION like '%"+allAnalysis.getConDition()+"%'");
				stringBuffer.append(" and CON_DITION like '%"+condition+"%'");
			}
		}
	    stringBuffer.append(" and yeartime ="+year);
		stringBuffer.append("  order by ALL_COUNT desc ");
        //System.out.println("stringBuffer++"+stringBuffer);
        /*@SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(),AllAnalysis.class, hqlParamMap);*/
        @SuppressWarnings("unchecked")
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(), AllAnalysis.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "ID");
        int count =jdbcTemplateWrapper.getResultCountWithValuesMap(stringBuffer.toString(), "*", hqlParamMap);
        dto.getPageModel().setTotals(count);
		dto.getPageModel().setPageData(list);
    }
	 
    public void search2(String gid,AllAnalysisDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
        //String condition=gid;
        String condition = WebContextHolder.getContext().getRequest().getParameter("gid");
        //解决括号里内容传值异常
        String[] split = condition.split("\\（");
        if(split.length>1) {
        String[] split2 = split[1].split("\\）");
        if(split2[0].equals(" ")) {
        	condition=split[0]+"（"+"+"+"）";
        }/*else {
        condition=split[0]+"（"+split2[0]+"）";}*/
        //System.out.println("condition=="+condition);
        }
        //解决英文括号传值异常的情况
        condition = condition.replace("& #40;", "(");
        condition = condition.replace("& #41;", ")");
        //System.out.println("condition=="+condition);
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		if(condition!=null&&""!=condition) {
        stringBuffer.append(" select nvl(yeartime,0)yeartime,ROW_NUMBER() over(order By ORG_NAME) as ID,ORG_NAME,count(id) ALL_COUNT,sum(TOTAL_COST) ALL_PRICE,sum(TOTAL_COST)/count(id) AVG_PRICE ");
        stringBuffer.append(" from (select t.*,to_char(balance_date,'yyyy')yeartime from t_piccbid_medical t)  where DIAG_TYPE=1 ");
        stringBuffer.append("  and yeartime="+year);
        stringBuffer.append(" AND CONDITION='"+condition+"' group by ORG_NAME,yeartime order by ALL_COUNT desc ");
        }
		//System.out.println("stringBuffer222++"+stringBuffer);
        @SuppressWarnings("unchecked")
		List<Admin> listCount = jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(),AllAnalysis2.class, hqlParamMap);
		List<Admin> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(), AllAnalysis2.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "ID");
        //int count =jdbcTemplateWrapper.getResultCountWithValuesMap(stringBuffer.toString(), "ID", hqlParamMap);
        dto.getPageModel().setTotals(listCount.size());
		dto.getPageModel().setPageData(list);
    }
}

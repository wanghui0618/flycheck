package com.dhcc.piccbid.dao.analysisOfPersonTime;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.analysisOfPersonTime.AnalysisOfPersonTimeDto;
import com.dhcc.piccbid.entity.analysisOfPersonTime.AnalysisOfPersonTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnalysisOfPersonTimeJdbcDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    public void createTable1(AnalysisOfPersonTimeDto dto){
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        StringBuilder sql=new StringBuilder();
        String diagType=dto.getAnalysisOfPersonTime().getType();
        String year=dto.getAnalysisOfPersonTime().getYear();
        sql.append("select a.total_person,a.HANDDING_INS_CODE,b.HANDDING_INS_NAME \n" +
                "from \n" +
                "(\n" +
                "       select count(*) as total_person,HANDDING_INS_CODE \n" +
                "       from \n" +
                "              t_piccbid_medical  where diag_type='"+diagType+"' and to_char(BALANCE_DATE,'yyyy')='"+year+"' \n" +
                "       group by HANDDING_INS_CODE\n" +
                ") a \n" +
                "inner join \n" +
                "     t_piccbid_handding_org_code b \n" +
                "on a.HANDDING_INS_CODE=b.HANDDING_INS_CODE");
        if(dto.getAnalysisOfPersonTime().getHanddingInsCode()!=null&&!dto.getAnalysisOfPersonTime().getHanddingInsCode().equals("")){
            sql.append(" and b.HANDDING_INS_CODE= '"+dto.getAnalysisOfPersonTime().getHanddingInsCode()+"'");
        }
        List<AnalysisOfPersonTime> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AnalysisOfPersonTime.class,hqlParamMap);
        page.setPageData(list);
        dto.setPageModel(page);
    }



    public void createTable2(AnalysisOfPersonTimeDto dto){
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        StringBuilder sql=new StringBuilder();
        String diagType=dto.getAnalysisOfPersonTime().getType();
        String year=dto.getAnalysisOfPersonTime().getYear();
        String handingCode=dto.getAnalysisOfPersonTime().getHanddingInsCode();
        String orgCode=dto.getAnalysisOfPersonTime().getOrgCode();
        sql.append("select count(*) as total_person,sum(total_time) as total_time,month \n" +
                "from \n" +
                "(\n" +
                "       select count(*) as total_time,to_char(balance_date,'mm') as month \n" +
                "       from \n" +
                "              t_piccbid_medical \n" +
                "       where to_char(balance_date,'yyyy')='"+year+"' and diag_type='"+diagType+"' and handding_ins_code='"+handingCode+"' and org_code='"+orgCode+"'\n" +
                "       group by INSURE_PERSON_CODE,to_char(balance_date,'mm') \n" +
                ") \n" +
                "group by month\n");
        List<AnalysisOfPersonTime> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AnalysisOfPersonTime.class,hqlParamMap);
        DecimalFormat df   = new DecimalFormat("######0.00");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getTotalPerson()!=0) {
                list.get(i).setEachPersonTime(Double.parseDouble(df.format(list.get(i).getTotalTime() / list.get(i).getTotalPerson())));
            }else{
                list.get(i).setEachPersonTime(0);
            }
        }
        page.setPageData(list);
        dto.setPageModel(page);
    }

    public void createTable3(AnalysisOfPersonTimeDto dto){
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        StringBuilder sql=new StringBuilder();
        String diagType=dto.getAnalysisOfPersonTime().getType();
        String year=dto.getAnalysisOfPersonTime().getYear();
        String handingCode=dto.getAnalysisOfPersonTime().getHanddingInsCode();
        sql.append("select a.*,b.org_name from\n" +
                "(\n" +
                "select count(*) as total_person,sum(total_time) as total_time,org_code from \n" +
                "       (\n" +
                "       select count(*) as total_time,org_code from \n" +
                "              t_piccbid_medical\n" +
                "       where to_char(balance_date,'yyyy')='"+year+"' and diag_Type='"+diagType+"' and handding_ins_Code='"+handingCode+"' group by INSURE_PERSON_CODE,org_code\n" +
                "       ) \n" +
                "GROUP BY org_code\n" +
                ") a inner join t_piccbid_dict_city_org b on a.org_code=b.org_code  order by total_time desc");
        List<AnalysisOfPersonTime> list=new ArrayList<AnalysisOfPersonTime>();
        if(dto.getPageModel()!=null&&dto.getPageModel().getPageNo()!=0){
            list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AnalysisOfPersonTime.class,hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "*");
            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "*", hqlParamMap));
        }else{
            list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AnalysisOfPersonTime.class,hqlParamMap, 1, 10, "*");
        }
        DecimalFormat df   = new DecimalFormat("######0.00");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getTotalPerson()!=0) {
                list.get(i).setEachPersonTime(Double.parseDouble(df.format(list.get(i).getTotalTime() / list.get(i).getTotalPerson())));
            }else{
                list.get(i).setEachPersonTime(0);
            }
        }
        page.setPageData(list);
        dto.setPageModel(page);
    }


    public void createTable4(AnalysisOfPersonTimeDto dto){
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        StringBuilder sql=new StringBuilder();
        String diagType=dto.getAnalysisOfPersonTime().getType();
        String year=dto.getAnalysisOfPersonTime().getYear();
        String handingCode=dto.getAnalysisOfPersonTime().getHanddingInsCode();
        String orgCode=dto.getAnalysisOfPersonTime().getOrgCode();
        sql.append("select count(*) as total_person,sum(total_time) as total_time,condition from \n" +
                "       (\n" +
                "       select count(*) as total_time,condition from \n" +
                "              t_piccbid_medical\n" +
                "       where condition is not null and to_char(balance_date,'yyyy')='"+year+"' and diag_Type='"+diagType+"' and handding_ins_Code='"+handingCode+"' and org_code='"+orgCode+"'group by INSURE_PERSON_CODE,condition\n" +
                "       ) \n" +
                "GROUP BY condition order by total_time desc");
        List<AnalysisOfPersonTime> list=new ArrayList<AnalysisOfPersonTime>();
        DecimalFormat df   = new DecimalFormat("######0.00");
        if(dto.getPageModel()!=null&&dto.getPageModel().getPageNo()!=0){
            list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AnalysisOfPersonTime.class,hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "*");
            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "*", hqlParamMap));
        }else{
            list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AnalysisOfPersonTime.class,hqlParamMap, 1, 10, "*");
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTotalPerson()!=0) {
                list.get(i).setEachPersonTime(Double.parseDouble(df.format(list.get(i).getTotalTime() / list.get(i).getTotalPerson())));
            }else{
                list.get(i).setEachPersonTime(0);
            }
        }
        page.setPageData(list);
        dto.setPageModel(page);
    }

}

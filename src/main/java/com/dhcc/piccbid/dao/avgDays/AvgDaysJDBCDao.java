package com.dhcc.piccbid.dao.avgDays;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.avgDays.AvgDaysDto;
import com.dhcc.piccbid.entity.avgDays.AvgDays;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class AvgDaysJDBCDao {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    public List<AvgDays> findAll(AvgDaysDto dto){
        PageModel page=dto.getPageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        StringBuilder sql=new StringBuilder();
        String startyear="2017";
        String endyear="2019";
        if(dto.getAvgDays()!=null) {
            if (dto.getAvgDays().getYear() != null) {
                if(!dto.getAvgDays().getYear().equals("")) {
                    String year = dto.getAvgDays().getYear();
                    startyear = year.substring(0, 4);
                    endyear = year.substring(year.length() - 5);
                }else{
                    startyear="1900";
                    Calendar date= Calendar.getInstance();
                    endyear=String.valueOf(date.get(Calendar.YEAR));
                }
            }
        }

        sql.append("select org_name,count_person,stay_length,stay_avg from"+
                "(select b.org_name,a.count_person,a.stay_length,a.stay_avg,b.org_code from" +
                "               (select org_code as org_code,count(*) as count_person,sum(stay_length) as stay_length,round(avg(stay_length)) as stay_avg" +
                "               from t_piccbid_medical " +
                "               WHERE diag_type='1' and payment_date between to_date('"+startyear+"-1-1','yyyy-mm-dd') and to_date('"+endyear+"-12-31','yyyy-mm-dd') " +
                "               GROUP BY org_code " +
                "               )  a " +
                "        inner JOIN T_PICCBID_DICT_CITY_ORG  b " +
                "        ON a.org_code=b.org_code " +
                "        ORDER BY stay_avg asc ,stay_length asc )" +
                        "where 1=1 ");
        if(dto.getAvgDays()!=null){
            if(dto.getAvgDays().getOrgName()!=null&&!dto.getAvgDays().getOrgName().equals("")){
                String name=dto.getAvgDays().getOrgName();
                //sql.append("and org_code like '%"+name+"%'");
                sql.append("and org_code =  '"+name+"'");
            }
        }
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        List<AvgDays> avgDays=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),AvgDays.class,hqlParamMap,page.getPageNo(),page.getPageSize(),"id");
        int i=1;
        int pageNo=(dto.getPageModel().getPageNo()-1)*10;
        for(AvgDays a:avgDays){
            a.setId(String.valueOf(i+pageNo));
            i++;
        }
        page.setPageData(avgDays);
        page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(),"org_name",hqlParamMap));
        return  avgDays;
    }
}
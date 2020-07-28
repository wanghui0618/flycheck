package com.dhcc.piccbid.dao.indicator;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.indicator.DiseasesAnalysisDto;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.unit.UnitService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DiseasesAnalysisJdbcDao {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    @Resource
    private UnitService unitService;

    public void listVo(DiseasesAnalysisDto dto){
    }

    public void listHos(DiseasesAnalysisDto dto) {
    }

    public void listHosTest(DiseasesAnalysisDto dto) {
        StringBuilder str = new StringBuilder();
        PageModel page = new PageModel();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        page.setHqlParamMap(hqlParamMap);
        Calendar date = Calendar.getInstance();
        String year=String.valueOf(date.get(Calendar.YEAR));
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        String roleId = user.getRoleId();
        if(dto.getDiseasesAnalysis().getYear()!=null&&!dto.getDiseasesAnalysis().getYear().equals("")){
            year=dto.getDiseasesAnalysis().getYear();
        }
        String condition="";
        if(dto.getDiseasesAnalysis().getCondition()!=null&&!dto.getDiseasesAnalysis().getCondition().equals("")){
            condition=dto.getDiseasesAnalysis().getCondition();
        }
        DecimalFormat df   = new DecimalFormat("######0.00");
        if (dto.getDiseasesAnalysis() != null && dto.getDiseasesAnalysis().getOrgName() != null && dto.getDiseasesAnalysis().getOrgName().equals("hosAll")) {
            str.append(" select condition,year,diag_type,sum(total_cost) as total_cost,( select sum(total_cost) from T_VMATER_DiseAnal_diseases where diag_type='1' and role_id='"+roleId+"' and year='"+year+"' " );
            if(!condition.equals("")){
                str.append(" and condition = '"+condition+"'");
            }
            str.append(" ) as all_total_cost,sum(total_times) as total_times,role_name,role_id from T_VMATER_DiseAnal_diseases where condition is not null and diag_type='1' and role_id='"+roleId+"' and year='"+year+"' " );
            if(!condition.equals("")){
                str.append(" and condition = '"+condition+"'");
            }
            str.append("GROUP BY condition,year,diag_type,role_name,role_id order by total_cost desc\n");
            List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "id");

            int i = 10;
            if (list.size() < 10) {
                i = list.size();
            }
            for (int k = 0; k < i; k++) {
                double p = list.get(k).getTotalCost() / list.get(k).getAllTotalCost() * 100;
                list.get(k).setTotalProportion(String.format("%.2f", p));
                list.get(k).setEachTimeCost(Double.valueOf(df.format(list.get(k).getTotalCost()/list.get(k).getTotalTimes())));
            }
            page.setPageData(list);
            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(), "*", hqlParamMap));
            dto.setPageModel(page);
        } else if (dto.getDiseasesAnalysis() != null && dto.getDiseasesAnalysis().getOrgName() != null && dto.getDiseasesAnalysis().getOrgName().equals("outAll")) {
//            str.append("select condition,sum(total_cost) as total_cost,diag_type,(select sum(total_Cost) from t_piccbid_medical where diag_type='2'  and to_char(balance_date,'yyyy')='"+year+"') as all_total_cost from t_piccbid_medical where diag_type='2' and  id  in (select medical_id from t_piccbid_medical_detail) and to_char(balance_date,'yyyy')='"+year+"'" );
//            List<String> listCityCode = unitService.getUserDataAhthority();
//            //String cityCode = "t.org_code";
//            //新授权
//            String cityCode = "org_code,handding_ins_code,city_code ";
//
//            // 调用数据授权
//            str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
//            str.append(" GROUP BY condition,diag_type  order by total_cost desc");
            str.append(" select condition,year,diag_type,sum(total_cost) as total_cost,( select sum(total_cost) from T_VMATER_DiseAnal_diseases where diag_type='2' and role_id='"+roleId+"' and year='"+year+"'" );
            if(!condition.equals("")){
                str.append(" and condition = '"+condition+"'");
            }
            str.append(" ) as all_total_cost,sum(total_times) as total_times,role_name,role_id from T_VMATER_DiseAnal_diseases where condition is not null and diag_type='2' and role_id='"+roleId+"'and year='"+year+"' " );
            if(!condition.equals("")){
                str.append(" and condition = '"+condition+"'");
            }
            str.append("GROUP BY condition,year,diag_type,role_name,role_id order by total_cost desc\n");

            List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "id");

            int i = 10;
            if (list.size() < 10) {
                i = list.size();
            }
            for (int k = 0; k < i; k++) {
                double p = list.get(k).getTotalCost() / list.get(k).getAllTotalCost() * 100;
                list.get(k).setTotalProportion(String.format("%.2f", p));
                list.get(k).setEachTimeCost(Double.valueOf(df.format(list.get(k).getTotalCost()/list.get(k).getTotalTimes())));

            }
            page.setPageData(list);
            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(), "*", hqlParamMap));
            dto.setPageModel(page);
        } else if (dto.getDiseasesAnalysis() != null && dto.getDiseasesAnalysis().getOrgName() != null && dto.getDiseasesAnalysis().getOrgName().equals("hosTop")) {
//            str.append("select condition,sum(total_cost) as total_cost,diag_type from t_piccbid_medical where diag_type='1' and  id  in (select medical_id from t_piccbid_medical_detail)  and to_char(balance_date,'yyyy')='"+year+"'" );
//            List<String> listCityCode = unitService.getUserDataAhthority();
//            //String cityCode = "t.org_code";
//            //新授权
//            String cityCode = "org_code,handding_ins_code,city_code ";
//
//            // 调用数据授权
//            str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
//            str.append(" group by condition,diag_type  order by total_cost desc");
            str.append(" select condition,year,diag_type,total_cost,total_times,role_name,role_id from T_VMATER_DiseAnal_diseases where condition is not null and diag_type='1' and role_id='"+roleId+"' and year='"+year+"' " );
            if(!condition.equals("")){
                str.append(" and condition = '"+condition+"'");
            }
            str.append(" order by total_cost desc\n");

            List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, 1, 10, "id");
            int i = 10;
            if (list.size() < 10) {
                i = list.size();
            }
            double total = 0;
            for (int y = 0; y < i; y++) {
                total = total + list.get(y).getTotalCost();
            }
            for (int k = 0; k < i; k++) {
                list.get(k).setTotalProportion(String.format("%.2f", list.get(k).getTotalCost() / total * 100));
                list.get(k).setEachTimeCost(Double.valueOf(df.format(list.get(k).getTotalCost()/list.get(k).getTotalTimes())));

            }
            page.setPageData(list);
//            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
            dto.setPageModel(page);
        } else if (dto.getDiseasesAnalysis() != null && dto.getDiseasesAnalysis().getOrgName() != null && dto.getDiseasesAnalysis().getOrgName().equals("outTop")) {
//            str.append("select condition,sum(total_cost) as total_cost,diag_type from t_piccbid_medical where diag_type='2' and  id  in (select medical_id from t_piccbid_medical_detail)  and to_char(balance_date,'yyyy')='"+year+"' " );
//            List<String> listCityCode = unitService.getUserDataAhthority();
//            //String cityCode = "t.org_code";
//            //新授权
//            String cityCode = "org_code,handding_ins_code,city_code ";
//
//            // 调用数据授权
//            str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
//            str.append("group by condition,diag_type  order by total_cost desc");
            str.append(" select condition,year,diag_type,total_cost,total_times,role_name,role_id from T_VMATER_DiseAnal_diseases where condition is not null and diag_type='2' and role_id='"+roleId+"' and year='"+year+"' " );
            if(!condition.equals("")){
                str.append(" and condition = '"+condition+"'");
            }
            str.append(" order by total_cost desc\n");

            List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, 1, 10, "id");
            int i = 10;
            if (list.size() < 10) {
                i = list.size();
            }
            double total = 0;
            for (int y = 0; y < i; y++) {
                total = total + list.get(y).getTotalCost();
            }
            for (int k = 0; k < i; k++) {
                list.get(k).setTotalProportion(String.format("%.2f", list.get(k).getTotalCost() / total * 100));
                list.get(k).setEachTimeCost(Double.valueOf(df.format(list.get(k).getTotalCost()/list.get(k).getTotalTimes())));

            }
            page.setPageData(list);
            dto.setPageModel(page);

        }
    }

    public void diseases(DiseasesAnalysisDto dto) {
        StringBuilder str = new StringBuilder();
        PageModel page = new PageModel();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        page.setHqlParamMap(hqlParamMap);
        String handdingInsName="";
        String orgName="";
        if(dto.getDiseasesAnalysis().getHanddingInsName()!=null&&!dto.getDiseasesAnalysis().getHanddingInsName().equals(" ")){
            handdingInsName=dto.getDiseasesAnalysis().getHanddingInsName();
        }
        if(dto.getDiseasesAnalysis().getOrgName()!=null&&!dto.getDiseasesAnalysis().getOrgName().equals(" ")){
            orgName=dto.getDiseasesAnalysis().getOrgName();
        }
        Calendar date = Calendar.getInstance();
        String year=String.valueOf(date.get(Calendar.YEAR));
        if(dto.getDiseasesAnalysis().getYear()!=null&&!dto.getDiseasesAnalysis().getYear().equals("")){
            year=dto.getDiseasesAnalysis().getYear();
        }
        String type="";
        if(dto.getDiseasesAnalysis().getType()!=null&&!dto.getDiseasesAnalysis().getType().equals("")){
            type=dto.getDiseasesAnalysis().getType();
        }
//从session中取出user对象
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        String roleId = user.getRoleId();
//        str.append("select sum(item_cost)as total_cost,project_type from t_piccbid_medical_detail a inner join t_piccbid_medical b on a.medical_id=b.id and b.condition='"+handdingInsName+"' and b.org_code='"+orgName+"'  " );
//        List<String> listCityCode = unitService.getUserDataAhthority();
//        //String cityCode = "t.org_code";
//        //新授权
//        String cityCode = "b.org_code,b.handding_ins_code,b.city_code ";
//
//        // 调用数据授权
//        str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
//        str.append("group by project_type\n");
        str.append("select * from T_VMATER_DiseAnal_YPZLHC where condition='"+handdingInsName+"' and org_CODE='"+orgName+"' and role_id='"+roleId+"'and year='"+year+"' and diag_type='"+type+"'");
        List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, 1, 10, "id");
        page.setPageData(list);
        dto.setPageModel(page);
    }

    public void handdInfo(DiseasesAnalysisDto dto) {
        StringBuilder str = new StringBuilder();
        PageModel page = new PageModel();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        page.setHqlParamMap(hqlParamMap);
        String handdingInsName="";
        if(dto.getDiseasesAnalysis().getHanddingInsName()!=null&&!dto.getDiseasesAnalysis().getHanddingInsName().equals(" ")){
            handdingInsName=dto.getDiseasesAnalysis().getHanddingInsName();
        }
        Calendar date = Calendar.getInstance();
        String year=String.valueOf(date.get(Calendar.YEAR));
        if(dto.getDiseasesAnalysis().getYear()!=null&&!dto.getDiseasesAnalysis().getYear().equals("")){
            year=dto.getDiseasesAnalysis().getYear();
        }
        String type="";
        if(dto.getDiseasesAnalysis().getType()!=null&&!dto.getDiseasesAnalysis().getType().equals("")){
            type=dto.getDiseasesAnalysis().getType();
        }
        str.append("select b.org_name,a.org_code,a.total,c.total_cost,c.total_times from(\n" +
                "select org_code,count(*) as total from t_piccbid_medical where condition='"+handdingInsName+"' and diag_type='"+type+"' and id  in (select medical_id from t_piccbid_medical_detail) and to_char(balance_date,'yyyy')='"+year+"' " );
        List<String> listCityCode = unitService.getUserDataAhthority();
        //String cityCode = "t.org_code";
        //新授权
        String cityCode = "org_code,handding_ins_code,city_code ";

        // 调用数据授权
        str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
        str.append(" group by org_code) a \n" +
                "inner join t_piccbid_dict_city_org b on a.org_code=b.org_code  ");
        if(dto.getDiseasesAnalysis().getOrgLevel()!=null&&!dto.getDiseasesAnalysis().getOrgLevel().equals("")){
            str.append("and org_level='"+dto.getDiseasesAnalysis().getOrgLevel()+"'");
        }
        str.append("left join(select org_code,sum(total_cost) as total_cost,count(*) as total_times from t_piccbid_medical where condition='"+handdingInsName+"'and to_char(balance_date,'yyyy')='"+year+"' group by org_code)c on c.org_code=a.org_code order by c.total_cost/c.total_times desc");
        List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, 1, 10, "id");
        DecimalFormat df   = new DecimalFormat("######0.00");
        for(int i=0;i<list.size();i++){
            list.get(i).setTimeCost(df.format(list.get(i).getTotalCost()/list.get(i).getTotalTimes()));
        }
        page.setPageData(list);
//            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
        page.setTotals(10);
        dto.setPageModel(page);
    }



    public void hopLevel(DiseasesAnalysisDto dto){
        StringBuilder str = new StringBuilder();
        PageModel page = new PageModel();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        page.setHqlParamMap(hqlParamMap);
        String condition="";
        if(dto.getDiseasesAnalysis().getCondition()!=null&&!dto.getDiseasesAnalysis().getCondition().equals(" ")){
            condition=dto.getDiseasesAnalysis().getCondition();
        }
        Calendar date = Calendar.getInstance();
        String year=String.valueOf(date.get(Calendar.YEAR));
        if(dto.getDiseasesAnalysis().getYear()!=null&&!dto.getDiseasesAnalysis().getYear().equals("")){
            year=dto.getDiseasesAnalysis().getYear();
        }
        String type="";
        if(dto.getDiseasesAnalysis().getType()!=null&&!dto.getDiseasesAnalysis().getType().equals("")){
            type=dto.getDiseasesAnalysis().getType();
        }
//        str.append("select sum(total_time)/sum(total_person) as each_Person_time,org_level  \n" +
//                "from (\n" +
//                "       select org_code,count(*) as total_person,sum(total_time) as total_time from \n" +
//                "       (\n" +
//                "              select count(*) as total_time,org_code \n" +
//                "              from \n" +
//                "                     t_piccbid_medical  where condition='"+condition+"'and to_char(balance_date,'yyyy')='"+year+"' and diag_type='"+type+"'\n" +
//                "              group by insure_person_code,org_code\n" +
//                "       ) group by org_code\n" +
//                ") a \n" +
//                "inner join\n" +
//                "     t_piccbid_dict_city_org b\n" +
//                "on a.org_code=b.org_code\n" +
//                "group by org_level order by each_Person_time desc");
                str.append("select sum(total_cost)/sum(total_time) as each_Person_time,org_level  \n" +
                        "from (\n" +
                        "       select org_code,count(*) as total_time,sum(total_cost) as total_cost from \n" +
                        "                     t_piccbid_medical  where condition='"+condition+"'and to_char(balance_date,'yyyy')='"+year+"' and diag_type='"+type+"' and id  in (select medical_id from t_piccbid_medical_detail)\n" +
                        "group by org_code\n" +
                        ") a \n" +
                        "inner join\n" +
                        "     t_piccbid_dict_city_org b\n" +
                        "on a.org_code=b.org_code\n" +
                        "group by org_level order by each_Person_time desc");
        List<DiseasesAnalysis> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), DiseasesAnalysis.class, hqlParamMap, 1, 10, "id");
        DecimalFormat df   = new DecimalFormat("######0.00");
        for(int i=0;i<list.size();i++){
            list.get(i).setEachPersonTime(Double.valueOf(df.format(list.get(i).getEachPersonTime())));
        }
        page.setPageData(list);
//            page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
        page.setTotals(10);
        dto.setPageModel(page);
    }
}

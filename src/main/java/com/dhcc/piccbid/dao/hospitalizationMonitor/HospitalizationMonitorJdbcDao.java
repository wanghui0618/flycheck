package com.dhcc.piccbid.dao.hospitalizationMonitor;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
//import com.dhcc.piccbid.dto.coststatistics.HospitalCostStatisticsDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
//import com.dhcc.piccbid.entity.coststatistics.HospitalCostStatistics;
import com.dhcc.piccbid.entity.hospitalizationMonitor.HospitalizationMonitor;
//import com.dhcc.piccbid.entity.medical.Medical;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.unit.UnitService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class HospitalizationMonitorJdbcDao {
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    @Resource
    private UnitService unitService;
    public PageModel yesterday(HospitalizationMonitorDto dto){
        Calendar cal=Calendar.getInstance();
        int thisYear=cal.get(Calendar.YEAR);
//        cal.add(Calendar.DATE,-1);
//        Date day=cal.getTime();

        StringBuilder str=new StringBuilder();
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap = new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        if(dto!=null){
            if(dto.getHospitalizationMonitor()!=null){
                if(dto.getHospitalizationMonitor().getName()!=null){
                    if(dto.getHospitalizationMonitor().getName().equals("totalHos") ){
                        str.append("select count(*) as total_amount from t_piccbid_dict_city_org where 1=1");
                        List<String> listCityCode = unitService.getUserDataAhthority();
                        //String cityCode = "t.org_code";
                        //新授权
                        String cityCode = "city_code ";

                        // 调用数据授权
                        str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
                        List<HospitalizationMonitor> medical = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalizationMonitor.class, hqlParamMap, page.getPageNo(), page.getPageSize(), "id");
                        page.setPageData(medical);
                        dto.setPageModel(page);
                        return page;
                    }else if(dto.getHospitalizationMonitor().getName().equals("person") ){

                        str.append("select count(*) as person_num,DIAG_TYPE from\n" +
                                "(\n" +
                                "select DIAG_TYPE,insure_person_code from t_piccbid_medical where to_char(BALANCE_DATE,'YYYY')=to_char(sysdate,'YYYY') " );
                        List<String> listCityCode = unitService.getUserDataAhthority();
                        //String cityCode = "t.org_code";
                        //新授权
                        String cityCode = "org_code,handding_ins_code,city_code ";

                        // 调用数据授权
                        str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
                        str.append(" group by DIAG_TYPE,insure_person_code\n" +
                                ") group by DIAG_TYPE");
                        List<HospitalizationMonitor> medical = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalizationMonitor.class, hqlParamMap, page.getPageNo(), page.getPageSize(), "id");
                        page.setPageData(medical);
                        dto.setPageModel(page);
                        return page;
                    }else if(dto.getHospitalizationMonitor().getName().equals("case") ){

                        str.append("select count(*) as case_num,DIAG_TYPE  from t_piccbid_medical where to_char(BALANCE_DATE,'YYYY')=to_char(sysdate,'YYYY')" );
                        List<String> listCityCode = unitService.getUserDataAhthority();
                        //String cityCode = "t.org_code";
                        //新授权
                        String cityCode = "org_code,handding_ins_code,city_code ";

                        // 调用数据授权
                        str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
                        str.append(" group by DIAG_TYPE");
                        List<HospitalizationMonitor> medical = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalizationMonitor.class, hqlParamMap, page.getPageNo(), page.getPageSize(), "id");
                        page.setPageData(medical);
                        dto.setPageModel(page);
                        return page;
                    }else if(dto.getHospitalizationMonitor().getName().equals("money") ){

                        str.append("select sum(total_cost) as total_cost,DIAG_TYPE  from t_piccbid_medical where to_char(BALANCE_DATE,'YYYY')=to_char(sysdate,'YYYY') " );
                        List<String> listCityCode = unitService.getUserDataAhthority();
                        //String cityCode = "t.org_code";
                        //新授权
                        String cityCode = "org_code,handding_ins_code,city_code ";

                        // 调用数据授权
                        str = unitService.appendDataAuhoritySql(cityCode, str, listCityCode);
                        str.append("group by DIAG_TYPE");
                        List<HospitalizationMonitor> medical = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalizationMonitor.class, hqlParamMap, page.getPageNo(), page.getPageSize(), "id");
                        page.setPageData(medical);
                        dto.setPageModel(page);
                        return page;
                    }
                }
            }
        }
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        String roleId = user.getRoleId();
            str.append("select * from T_VMATER_hospmonitor_cost where role_id='"+roleId+"'");
            List<HospitalizationMonitor> medical = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalizationMonitor.class, hqlParamMap, page.getPageNo(), page.getPageSize(), "id");
            page.setPageData(medical);
            dto.setPageModel(page);
        return page;
    }

//    public void cityInfo(HospitalCostStatisticsDto dto){
//        StringBuilder str=new StringBuilder();
//        PageModel page=new PageModel();
//        Map<String,Object> hqlParamMap = new HashMap<String,Object>();
//        page.setHqlParamMap(hqlParamMap);
//        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
//        String roleId = user.getRoleId();
//        str.append("select (case when a.HANDDING_INS_NAME is not null then a.HANDDING_INS_NAME else b.HANDDING_INS_NAME end) as HANDDING_INS_NAME,a.person_num,a.total_cost,b.person_num as person_num1\n" +
//                ",b.total_cost as total_cost1 from\n" +
//                "   (select * from T_VMATER_hospmonitor_hand  where diag_type='1' and role_id='"+roleId+"' and HANDDING_INS_NAME is not null)a\n" +
//                "full join \n" +
//                "     (select * from T_VMATER_hospmonitor_hand  where diag_type='2' and role_id='"+roleId+"' and HANDDING_INS_NAME is not null)b\n" +
//                "on a.HANDDING_INS_code=b.HANDDING_INS_code");
//        List<HospitalCostStatistics> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalCostStatistics.class, hqlParamMap, 1, 10, "id");
//        page.setPageData(list);
//        dto.setPageModel(page);
//
//    }



    public void inTop(HospitalizationMonitorDto dto){
//        StringBuilder str=new StringBuilder();
//        PageModel page=dto.getPageModel();
//        Map<String,Object> hqlParamMap = new HashMap<String,Object>();
//        Calendar cal=Calendar.getInstance();
//        cal.add(Calendar.DATE,-1);
//        Date d=cal.getTime();
//        String date= new SimpleDateFormat("yyyy-MM-dd").format(d);
//        str.append("select * from V_MATER_hospMonitor__inTop order bu");
//        List<HospitalizationMonitor> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(str.toString(), HospitalizationMonitor.class, hqlParamMap, 1, 5, "id");
//        page.setPageData(list);
//        page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(str.toString(),"*",hqlParamMap));
    }
}

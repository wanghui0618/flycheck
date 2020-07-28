package com.dhcc.piccbid.dao.drugsAndInspectionStatistics;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDto;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsVO;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetail;
import com.dhcc.piccbid.entity.flyMedicalrecord.FlyMedicalrecord;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DrugsAndInspectionStatisticsJDBCDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    public PageModel list(DrugsAndInspectionStatisticsDto dto){
       // String itemNameHos="";
        String itemNameIns="";
        String inteCodeIns="";
      String chooeseYear=dto.getChooseYear();
      String chooseMonth=dto.getChooseMonth();
      if (chooseMonth!=null&&chooseMonth.contains("-")){
        String[] strings=  chooseMonth.split("-");
          chooeseYear= strings[0];
          chooseMonth= strings[1];
      }
        if (dto.getDrugsAndInspectionStatistics()!=null) {
           // itemNameHos = dto.getDrugsAndInspectionStatistics().getItemNameHos();
            itemNameIns = WebContextHolder.getContext().getRequest().getParameter("drugsAndInspectionStatistics.itemNameIns");
            inteCodeIns = WebContextHolder.getContext().getRequest().getParameter("drugsAndInspectionStatistics.itemCodeIns");

        }
        StringBuilder sqlStr = new StringBuilder();
        PageModel pageModel = dto.getPageModel();
        Map hqlParamMap = new HashMap();
        sqlStr.append("select item_code_ins, item_Name_ins,ROUND(sum(item_num)/count(item_code_ins),2) as frequency,count(item_code_ins) as total_number,sum(item_cost) as total_cost\n" +
                "from t_fly_medical_detail  where  item_code_ins is not null ");
        if (chooeseYear!=null&&!"".equals(chooeseYear)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooeseYear+"'");
        }
        if (chooseMonth!=null&&!"".equals(chooseMonth)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooeseYear+"'");
            sqlStr.append(" and extract(month from balance_date ) ='"+chooseMonth+"'");
        }
      /*  if (itemNameHos!=null&&!"".equals(itemNameHos)){
            sqlStr.append("and item_code_Hos like '"+itemNameHos+"%'");
        }*/
        if (itemNameIns!=null&&!"".equals(itemNameIns)){
            sqlStr.append("and item_name_Ins like '"+itemNameIns+"%'");
        }
        if (inteCodeIns!=null&&!"".equals(inteCodeIns)){
            sqlStr.append("and item_code_Ins = '"+inteCodeIns+"'");
        }
        sqlStr.append("group by item_code_ins,item_NAME_ins");

    /*    pageModel.setQueryHql(sqlStr.toString());
        pageModel.setHqlParamMap(hqlParamMap);*/
        @SuppressWarnings("unchecked")
        List<DrugsAndInspectionStatisticsVO> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), DrugsAndInspectionStatisticsVO.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
        pageModel.setPageData(list);
        pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(),new BeanPropertyRowMapper<>(DrugsAndInspectionStatisticsVO.class)).size());
        return pageModel;

    }


  public PageModel listForCostDetail(DrugsAndInspectionStatisticsDto dto){
       // String itemNameHos="";
        String chooseYearFromMonth="";
        String inteCodeIns="";
      String chooeseYear=dto.getChooseYear();
      String chooseMonth=dto.getChooseMonth();
      if (chooseMonth!=null&&chooseMonth.contains("-")){
        String[] strings=  chooseMonth.split("-");
          chooseYearFromMonth= strings[0];
          chooseMonth= strings[1];
      }
        if (dto.getDrugsAndInspectionStatistics()!=null) {
            inteCodeIns = WebContextHolder.getContext().getRequest().getParameter("drugsAndInspectionStatistics.itemCodeIns");

        }
        StringBuilder sqlStr = new StringBuilder();
        PageModel pageModel = dto.getPageModel();
        Map hqlParamMap = new HashMap();
        sqlStr.append("select * from t_fly_medical_detail  where  item_code_ins is not null ");
        if (chooeseYear!=null&&!"".equals(chooeseYear)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooeseYear+"'");
        }
        if (chooseMonth!=null&&!"".equals(chooseMonth)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooseYearFromMonth+"'");
            sqlStr.append(" and extract(month from balance_date ) ='"+chooseMonth+"'");
        }
        if (inteCodeIns!=null&&!"".equals(inteCodeIns)){
            sqlStr.append("and item_code_Ins = '"+inteCodeIns+"'");
        }

        @SuppressWarnings("unchecked")
        List<DrugsAndInspectionStatisticsVO> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyMedicalDetail.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
        pageModel.setPageData(list);
        pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(),new BeanPropertyRowMapper<>(FlyMedicalDetail.class)).size());
        return pageModel;
    }

    public PageModel listForInselectionCostDetail(DrugsAndInspectionStatisticsDto dto){
       // String itemNameHos="";
        String chooseYearFromMonth="";
        String inteCodeHos="";
      String chooeseYear=dto.getChooseYear();
      String chooseMonth=dto.getChooseMonth();
      if (chooseMonth!=null&&chooseMonth.contains("-")){
        String[] strings=  chooseMonth.split("-");
          chooseYearFromMonth= strings[0];
          chooseMonth= strings[1];
      }
        if (dto.getDrugsAndInspectionStatistics()!=null) {
            inteCodeHos = WebContextHolder.getContext().getRequest().getParameter("drugsAndInspectionStatistics.itemCodeHos");

        }
        StringBuilder sqlStr = new StringBuilder();
        PageModel pageModel = dto.getPageModel();
        Map hqlParamMap = new HashMap();
        sqlStr.append("select * from t_fly_medical_detail  where  item_code_ins is  null ");
        if (chooeseYear!=null&&!"".equals(chooeseYear)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooeseYear+"'");
        }
        if (chooseMonth!=null&&!"".equals(chooseMonth)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooseYearFromMonth+"'");
            sqlStr.append(" and extract(month from balance_date ) ='"+chooseMonth+"'");
        }
        if (inteCodeHos!=null&&!"".equals(inteCodeHos)){
            sqlStr.append("and item_code_Hos = '"+inteCodeHos+"'");
        }

        @SuppressWarnings("unchecked")
        List<DrugsAndInspectionStatisticsVO> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyMedicalDetail.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
        pageModel.setPageData(list);
        pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(),new BeanPropertyRowMapper<>(FlyMedicalDetail.class)).size());
        return pageModel;
    }

    public PageModel listForInspection(DrugsAndInspectionStatisticsDto dto){
        String itemNameHos="";
      String chooeseYear=dto.getChooseYear();
      String chooseMonth=dto.getChooseMonth();
      if (chooseMonth!=null&&chooseMonth.contains("-")){
        String[] strings=  chooseMonth.split("-");
          chooeseYear= strings[0];
          chooseMonth= strings[1];
      }
        if (dto.getDrugsAndInspectionStatistics()!=null) {
           // itemNameHos = dto.getDrugsAndInspectionStatistics().getItemNameHos();
            itemNameHos = WebContextHolder.getContext().getRequest().getParameter("drugsAndInspectionStatistics.itemNameHos");
        }
        StringBuilder sqlStr = new StringBuilder();
        PageModel pageModel = dto.getPageModel();
        Map hqlParamMap = new HashMap();
        sqlStr.append("select item_code_hos, item_Name_hos,ROUND(sum(item_num)/count(item_code_hos),2) as frequency,count(item_code_hos) as total_number,sum(item_cost) as total_cost\n" +
                "from t_fly_medical_detail  where  item_code_ins is  null ");
        if (chooeseYear!=null&&!"".equals(chooeseYear)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooeseYear+"'");
        }
        if (chooseMonth!=null&&!"".equals(chooseMonth)){
            sqlStr.append(" and extract(year from balance_date ) ='"+chooeseYear+"'");
            sqlStr.append(" and extract(month from balance_date ) ='"+chooseMonth+"'");
        }
        if (itemNameHos!=null&&!"".equals(itemNameHos)){
            sqlStr.append("and item_name_hos like '"+itemNameHos+"%'");
        }
        sqlStr.append("group by item_code_hos,item_NAME_hos");

        @SuppressWarnings("unchecked")
        List<DrugsAndInspectionStatisticsVO> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), DrugsAndInspectionStatisticsVO.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
        pageModel.setPageData(list);
        pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(),new BeanPropertyRowMapper<>(DrugsAndInspectionStatisticsVO.class)).size());
        return pageModel;

    }

}

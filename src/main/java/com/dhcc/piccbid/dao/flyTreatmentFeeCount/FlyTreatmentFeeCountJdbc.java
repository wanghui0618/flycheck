package com.dhcc.piccbid.dao.flyTreatmentFeeCount;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.FlyTreatmentFeeCountDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyTreatmentFeeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlyTreatmentFeeCountJdbc {

    @Autowired
    JdbcTemplateWrapper jdbcTemplateWrapper;

    public void search(FlyTreatmentFeeCountDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        if (dto.getFlyTreatmentFeeCount()!=null) {
        if(dto.getFlyTreatmentFeeCount().getBelong().equals("0")){
            stringBuffer.append("select item_id_hosp as id,item_name_hosp as item ,sum(NUM) as frequency from t_fly_detail_inhos where 1=1 ");
        }

        if (dto.getFlyTreatmentFeeCount().getBelong().equals("1")){
            stringBuffer.append("select item_id_hosp as id,item_name_hosp as item ,sum(NUM) as frequency from T_FLY_DETAIL_MENZ where 1=1 ");
        }
            if ( dto.getFlyTreatmentFeeCount().getItem() != null &&!dto.getFlyTreatmentFeeCount().getItem().equals("")) {
                String item = dto.getFlyTreatmentFeeCount().getItem();
                stringBuffer.append(" and item_name_hosp like'%" + item + "%'" );
            }
          if ( dto.getFlyTreatmentFeeCount().getYear() != null &&!dto.getFlyTreatmentFeeCount().getYear().equals("")) {
                String year = dto.getFlyTreatmentFeeCount().getYear();
                stringBuffer.append(" and year='" + year+"'" );
              if (dto.getFlyTreatmentFeeCount().getMonth() != null && !dto.getFlyTreatmentFeeCount().getMonth().equals("")) {
                  String month = dto.getFlyTreatmentFeeCount().getMonth();
                  stringBuffer.append(" and month='" + month+ "'");
              }
            }
        }else {
            stringBuffer.append("select item_id_hosp as id,item_name_hosp as item ,sum(NUM) as frequency from t_fly_detail_inhos where 1=1 ");
        }
       stringBuffer.append(" group by item_id_hosp, item_name_hosp order by frequency desc");
        PageModel pageModel =dto.getPageModel();
        List<FlyTreatmentFeeCount> listData=jdbcTemplateWrapper.queryAllMatchListWithParaMap(stringBuffer.toString(),FlyTreatmentFeeCount.class,null,pageModel.getPageNo(),
                pageModel.getPageSize(),"item");
        String sqlStr="select count(*) from (" +stringBuffer.toString()+")";
        @SuppressWarnings("unchecked")
        int total=jdbcTemplateWrapper.queryForInt(sqlStr,null);
        dto.getPageModel().setPageData(listData);
        dto.getPageModel().setTotals(total);
    }

    public void ultrasoundlist(FlyTreatmentFeeCountDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        stringBuffer.append("select item_name_hosp as item ,sum(a) as frequency,sum(b) as sum FROM (select item_name_hosp ,sum(num) a,sum(cost) b from t_fly_detail_inhos where item_name_hosp in ('M型超声','二维超声') GROUP BY item_name_hosp\r\n" +
                " UNION ALL \r\n" +
                "select item_name_hosp ,sum(num) as a,sum(cost) b from T_FLY_DETAIL_MENZ where item_name_hosp in ('M型超声','二维超声') GROUP BY item_name_hosp) GROUP BY item_name_hosp");
        dto.getPageModel().setQueryHql(stringBuffer.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

}

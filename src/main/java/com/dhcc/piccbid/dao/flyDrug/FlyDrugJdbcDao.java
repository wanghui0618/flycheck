package com.dhcc.piccbid.dao.flyDrug;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsDto;
import com.dhcc.piccbid.dto.flyDrug.FlyDrugDto;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatisticsVO;
import com.dhcc.piccbid.entity.flyDrug.FlyDrugDetailVo;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetailVo;

@Component
public class FlyDrugJdbcDao {
	
	private static Log logger = LogFactory.getLog(FlyDrugJdbcDao.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplateWrapper jdbcTemplateWrapper;
	

	  public PageModel list(FlyDrugDto dto){
		    String itemNameIns=dto.getItemNameIns();	        
	        String chooseYear=dto.getChooseYear();
	        String chooseMonth=dto.getChooseMonth();
	        String chooseYear1="";
	        String chooseMonth1="";
	        if (chooseMonth!=null&&chooseMonth.contains("-")){
	            String[] strings=  chooseMonth.split("-");
	               chooseYear1= strings[0];
	              chooseMonth1= strings[1];
	          }
	        StringBuilder sqlStr = new StringBuilder();
	        PageModel pageModel = dto.getPageModel();
	        Map hqlParamMap = new HashMap();
	        System.out.println(itemNameIns);
	        System.out.println(chooseYear);
	        System.out.println(chooseMonth);
	        sqlStr.append(" select item_code_ins,sum(item_cost)as cost,sum(item_num)as item_num,\r\n" + 
	        		"  min(item_price)as item_price,min(item_name_ins)as item_name_ins,\r\n" + 
	        		"  min(charge_type)as charge_type from T_FLY_MEDICAL_DETAIL where item_code_ins is not null");
	        if(chooseYear!=null&&!"".equals(chooseYear)) {
	        	sqlStr.append(" and extract(year from balance_date ) ='"+chooseYear+"'");
	        	/*if(chooseMonth!=null&&!"".equals(chooseMonth)) {
		        	sqlStr.append(" and extract(year from balance_date ) ='"+chooseMonth+"'");
	        	}*/
	        }
	        if (chooseMonth!=null&&!"".equals(chooseMonth)){
	            sqlStr.append(" and extract(year from balance_date ) ='"+chooseYear1+"'");
	            sqlStr.append(" and extract(month from balance_date ) ='"+chooseMonth1+"'");
	        }
	        if(itemNameIns!=null&&!"".equals(itemNameIns)) {
	        	sqlStr.append(" and item_name_Ins like '%"+itemNameIns+"%'");
	        }
	        sqlStr.append(" group by item_code_ins order by cost desc");

	        @SuppressWarnings("unchecked")
	        List<FlyDrugDetailVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), FlyDrugDetailVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "rownum");
	        pageModel.setPageData(list);
	        pageModel.setTotals(jdbcTemplate.query(sqlStr.toString(),new BeanPropertyRowMapper<>(FlyDrugDetailVo.class)).size());
	        return pageModel;

	    }
}

package com.dhcc.piccbid.dao.itemError;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.itemError.ItemErrorDto;
import com.dhcc.piccbid.entity.itemError.ItemError;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemErrorJdbcDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;

    public void listHop(ItemErrorDto dto){
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        StringBuilder sql=new StringBuilder();
        String orgCode="";
        sql.append("select * from (select org_code,org_name,sort,count(*) as total from t_piccbid_item_error GROUP BY org_code,org_name,sort  order by sort asc) b where 1=1");
        if(dto.getItemError()!=null&&dto.getItemError().getOrgName()!=null&&!dto.getItemError().getOrgName().equals("")){
            orgCode=dto.getItemError().getOrgName();
            sql.append(" and b.org_name  like '%"+orgCode+"%' ");
        }
        List<ItemError> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),ItemError.class,hqlParamMap, dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(), "*");
        page.setTotals(jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "*", hqlParamMap));
        page.setPageData(list);
        dto.setPageModel(page);
    }

    public void listItem(ItemErrorDto dto){
        PageModel page=new PageModel();
        Map<String,Object> hqlParamMap=new HashMap<String,Object>();
        page.setHqlParamMap(hqlParamMap);
        StringBuilder sql=new StringBuilder();
        String orgCode=dto.getItemError().getOrgCode();
        sql.append("select count(*) as item_num,item_name from t_piccbid_item_error where org_code='"+orgCode+"' group by org_code,item_name,sort1 order by sort1 asc");
        List<ItemError> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),ItemError.class,hqlParamMap, 1, 10, "*");
        page.setPageData(list);
        dto.setPageModel(page);
    }
}

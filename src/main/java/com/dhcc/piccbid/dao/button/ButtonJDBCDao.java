package com.dhcc.piccbid.dao.button;

import com.alibaba.fastjson.JSON;
import com.dhcc.piccbid.dto.button.ButtonDto;
import com.dhcc.piccbid.entity.button.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ButtonJDBCDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getButtonAuthoByUserId(String id) {
        String sql = "select  authority_id from t_piccbid_user_authority where 1=1 and type ='3' and user_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size() != 0) {
            return authorityIds.get(0);
        } else {
            return "";
        }
    }
    public String getButtonAuthoByroleCode(String roleId) {
        String sql = "select authority_id from t_piccbid_role_authority where role_id ='"+roleId+"' and type='3'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size() != 0) {
            return authorityIds.get(0);
        } else {
            return "";
        }
    }

    public String getButtonCode(String ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("select button_code from t_piccbid_button where 1=1 ");
        sql.append("and id not in (");
        sql.append(ids + ")");
        List<String> s = jdbcTemplate.queryForList(sql.toString(), String.class);
        return JSON.toJSONString(s);

    }

    public void search(ButtonDto dto) {
        String buttonCode ="";
        String buttonName="";
        String buttonPageName="";
        if (dto.getButton()!=null) {
            buttonCode = dto.getButton().getButtonCode();
            buttonName = dto.getButton().getButtonName();
            buttonPageName=dto.getButton().getButtonPageName();
        }
        Map hqlParamMap=new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_piccbid_button where 1=1 ");
        if (!"".equals(buttonName)&&buttonName!=null) {
            sql.append(" and button_name like '" + buttonName + "%'");
        }
        if (!"".equals(buttonCode)&&buttonCode!=null) {
            sql.append(" and button_code like '" + buttonCode + "%'");
        }
        if (!"".equals(buttonPageName)&&buttonPageName!=null) {
            sql.append(" and button_page_name like '" + buttonPageName + "%'");
        }
        sql.append(" order by button_page_name asc");
        dto.getPageModel().setQueryHql(sql.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);

    }

    public int isExist(ButtonDto dto){
        String id=dto.getButton().getId();
        String buttonCode=dto.getButton().getButtonCode();
        StringBuilder sql=new StringBuilder();
        sql.append("select id from t_piccbid_button where button_code='"+buttonCode+"'");
        if (!"".equals(id)&&id!=null){
            sql.append(" and id!='"+id+"'");
        }
        List<String> stringList=jdbcTemplate.queryForList(sql.toString(),String.class);
        return stringList.size();
    }
    public void selectListTreeTable(ButtonDto dto){
        String id=dto.getButton().getId();
        String buttonName=dto.getButton().getButtonName();
        String buttonCode=dto.getButton().getButtonCode();
        String buttonPageName=dto.getButton().getButtonPageName();
        StringBuilder sqlStr=new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        sqlStr.append("select * from ( with TempTable as \n" +
                "       (\n" +
                "       select * from （select id,parent_id,button_name as menu_name,button_code as menu_code,button_page_name as onclick_aft, '1' is_button from t_piccbid_button)\n" +
                "     union all  (select id,parent_id,menu_name,menu_code, onclick_aft,'0'is_button from t_piccbid_menu) \n" +
                " )\n" +
                " select * from TempTable start with id='"+id+"'");
        sqlStr.append("connect by prior  id=parent_id  ) A where A.is_button='1'");
        if (!"".equals(buttonName)&&buttonName!=null){
            sqlStr.append(" and menu_name like '"+buttonName+"%'");
        }
        if (!"".equals(buttonCode)&&buttonCode!=null){
            sqlStr.append(" and menu_code like '"+buttonCode+"%'");
        }
        if (!"".equals(buttonPageName)&&buttonPageName!=null){
            sqlStr.append(" and onclick_aft like '"+buttonPageName+"%'");
        }
        sqlStr.append("order by onclick_aft");
        dto.getPageModel().setHqlParamMap(map);
        dto.getPageModel().setQueryHql(sqlStr.toString());

    }
}

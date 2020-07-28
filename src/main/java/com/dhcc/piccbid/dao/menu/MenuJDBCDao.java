package com.dhcc.piccbid.dao.menu;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.menu.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @version V1.0
 * @date 2019-06-3 21:42:21
 */
@Component
public class MenuJDBCDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void search(MenuDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        stringBuffer.append("select * from t_piccbid_menu where 1=1");
        //根据name来搜索
        if (dto.getMenu().getMenuName() != null) {
            String name = dto.getMenu().getMenuName();
            if (name != null) {
                stringBuffer.append(" and menu_name like '%" + name + "%'");
            }
        }
        //根据code去搜索
        if (dto.getMenu().getMenuCode() != null) {
            String code = dto.getMenu().getMenuCode();
            stringBuffer.append(" and menu_code like '%" + code + "%'");
        }
        dto.getPageModel().setQueryHql(stringBuffer.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void menuList(MenuDto menuDto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        menuDto.setPageModel(pageModel);
        sqlStr.append("select * from t_piccbid_menu");
        sqlStr.append("  order by menu_code asc");
        menuDto.getPageModel().setQueryHql(sqlStr.toString());
        menuDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void buttonsList(MenuDto menuDto){
        String sql="select *  from t_piccbid_button";
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        menuDto.setPageModel(pageModel);
        menuDto.getPageModel().setQueryHql(sql);
        menuDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public String selectParentIdByMenuName(String menuName) {
        String sql = "select id from t_piccbid_menu where menu_name ='" + menuName + "'";
        List<String> parentId = jdbcTemplate.queryForList(sql, String.class);
        if (parentId.size()>0){
            return parentId.get(0);
        }else {
            return "";
        }

    }

    public String selectIsAuthoMenu(String id) {
        String sql = "select  authority_id from t_piccbid_user_authority where 1=1 and type ='1' and user_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size()!=0) {
            return authorityIds.get(0);
        }else {
            return "";
        }

    }

    public String selectIsAuthoMenuByRoleCode(String roleId) {
        String sql = "select  authority_id from t_piccbid_role_authority where 1=1 and type ='1' and role_id='"+roleId+"'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size()!=0) {
            return authorityIds.get(0);
        }else {
            return "";
        }

    }
    public String selectIsAuthoButton(String id) {
        String sql = "select  authority_id from t_piccbid_user_authority where 1=1 and type ='3' and user_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size()!=0) {
            return authorityIds.get(0);
        }else {
            return "";
        }

    }
    public String selectRoleIsAuthoButton(String id) {
        String sql = "select  authority_id from t_piccbid_role_authority where 1=1 and type ='3' and role_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size()!=0) {
            return authorityIds.get(0);
        }else {
            return "";
        }

    }
    public String selectRoleIsAuthoMenu(String id) {
        String sql = "select  authority_id from t_piccbid_role_authority where 1=1 and type ='1' and role_id ='" + id + "'";
        List<String> authorityIds = jdbcTemplate.queryForList(sql, String.class);
        if (authorityIds.size()!=0) {
            return authorityIds.get(0);
        }else {
            return "";
        }

    }


    public void menuCount(MenuDto menuDto) {
        String menuName = menuDto.getMenu().getMenuName();
        String parentId = menuDto.getMenu().getParentId();
        String id=menuDto.getMenu().getId();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        menuDto.setPageModel(pageModel);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from t_piccbid_menu where 1=1");
        stringBuilder.append(" and menu_name='" + menuName + "'");
        stringBuilder.append(" and parent_id='" + parentId + "'");
        //编辑时的查询是否重复
        if (id != null) {
            stringBuilder.append(" and id!='" + id + "'");
        }
        menuDto.getPageModel().setQueryHql(stringBuilder.toString());
        menuDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void menuTreeTable(MenuDto menuDto) {
        String parentLeaf = menuDto.getMenu().getParentLeaf();
        String menuName = menuDto.getMenu().getMenuName();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        menuDto.setPageModel(pageModel);
        sqlStr.append("select * from t_piccbid_menu where 1=1 ");
        if (parentLeaf != null || parentLeaf != "") {
            sqlStr.append("and parent_leaf  >= '" + parentLeaf + "'");
        }
        if (menuName != null) {
            sqlStr.append(" and menu_name like'%" + menuName + "%'");
        }
        sqlStr.append("  order by parent_leaf asc");
        menuDto.getPageModel().setQueryHql(sqlStr.toString());
        menuDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void getFirstMenu(MenuDto dto) {
        String sql = "select * from t_piccbid_menu where parent_leaf='1' order by menu_code asc";
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        dto.getPageModel().setQueryHql(sql);
    }
    public void updateAdminMenuAutho(String name){
        StringBuilder sql=new StringBuilder();
        /*sql.append("update  t_piccbid_user_authority set authority_id= ('[\"'||(SELECT LISTAGG(id,'\",\"') WITHIN GROUP(ORDER BY id) FROM t_piccbid_menu )||'\"]') \n" +
                "where user_id= (select id from t_piccbid_user where login_name='"+name+"'");*/
      //  sql.append(name);
        sql.append("update  t_piccbid_user_authority set authority_id= (substr('[\"'||(SELECT xmlagg(xmlparse(content id||'\",\"' wellformed) order by id).getclobval() FROM t_piccbid_menu ),0,\n" +
                " (length('[\"'||(SELECT xmlagg(xmlparse(content id||'\",\"' wellformed) order by id).getclobval() FROM t_piccbid_menu ))-2 )\n" +
                " \n" +
                " )||']') \n" +
                "where user_id in (select id from t_piccbid_user where login_name='"+name+"'");


        sql.append(") and type='1'");
     jdbcTemplate.execute(sql.toString());
    }

    public void updateAdminMenuAuthoForRole(String roleCode){
        StringBuilder sql=new StringBuilder();
        /*sql.append("update  t_piccbid_user_authority set authority_id= ('[\"'||(SELECT LISTAGG(id,'\",\"') WITHIN GROUP(ORDER BY id) FROM t_piccbid_menu )||'\"]') \n" +
                "where user_id= (select id from t_piccbid_user where login_name='"+name+"'");*/
      //  sql.append(name);
        sql.append("update  t_piccbid_user_authority set authority_id= (substr('[\"'||(SELECT xmlagg(xmlparse(content id||'\",\"' wellformed) order by id).getclobval() FROM t_piccbid_menu ),0,\n" +
                " (length('[\"'||(SELECT xmlagg(xmlparse(content id||'\",\"' wellformed) order by id).getclobval() FROM t_piccbid_menu ))-2 )\n" +
                " \n" +
                " )||']') \n" +
                "where user_id in (select id from t_piccbid_user where role_code='"+roleCode+"'");


        sql.append(") and type='1'");
        System.out.println(sql.toString());
     jdbcTemplate.execute(sql.toString());
    }
    public void menuTreeTableNew(MenuDto dto){
        StringBuilder sqlStr=new StringBuilder();
          String id= dto.getMenu().getId();
          String menuName= dto.getMenu().getMenuName();
          String menuCode= dto.getMenu().getMenuCode();
          Map<String, Object> map = new HashMap<>();
          sqlStr.append("select * from (select * from t_piccbid_menu start with" +
                  " id='"+id+"' connect by prior id=parent_id) where 1=1");
          if (!"".equals(menuName)&&menuName!=null){
              sqlStr.append(" and menu_name='"+menuName+"'");
          }
          if (!"".equals(menuCode)&&menuCode!=null){
              sqlStr.append(" and menu_code='"+menuCode+"'");
          }
          sqlStr.append(" order by parent_leaf asc");
          dto.getPageModel().setQueryHql(sqlStr.toString());
          dto.getPageModel().setHqlParamMap(map);
    }
}


package com.dhcc.piccbid.dao.role;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.user.UserJdbcDao;
import com.dhcc.piccbid.dto.role.RoleDto;
import com.dhcc.piccbid.entity.role.Role;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
public class RoleJDBCDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private UserJdbcDao userJdbcDao;


    public void search(RoleDto dto) {
        StringBuilder stringBuffer = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        stringBuffer.append("select * from t_piccbid_role where 1=1");
        //根据name来搜索
        if (dto.getRole().getRoleName() != null) {
            String name = dto.getRole().getRoleName();
            if (name != null) {
                stringBuffer.append(" and role_name like '" + name + "%'");
            }
        }
        //根据code去搜索
        if (dto.getRole().getRoleCode() != null) {
            String code = dto.getRole().getRoleCode();
            stringBuffer.append(" and role_code like '" + code + "%'");
        }
        dto.getPageModel().setQueryHql(stringBuffer.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void roleList(RoleDto roleDto) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        roleDto.setPageModel(pageModel);
        sqlStr.append("select * from t_piccbid_role");
        sqlStr.append("  order by role_code asc");
        //System.out.println(sqlStr);
        roleDto.getPageModel().setQueryHql(sqlStr.toString());
        roleDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void roleCount(RoleDto roleDto) {
        String roleName = roleDto.getRole().getRoleName();
        String roleCode = roleDto.getRole().getRoleCode();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        roleDto.setPageModel(pageModel);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from t_piccbid_role where 1=1");
        stringBuilder.append(" and role_name='" + roleName + "' and role_code='" + roleCode + "'");
        roleDto.getPageModel().setQueryHql(stringBuilder.toString());
        roleDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    public void roleTreeTable(RoleDto roleDto) {
        String parentLeaf = roleDto.getRole().getParentLeaf();
        String roleName = roleDto.getRole().getRoleName();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        roleDto.setPageModel(pageModel);
        sqlStr.append("select * from t_piccbid_role where 1=1 ");
        if (parentLeaf != null || parentLeaf != "") {
            sqlStr.append("and parent_leaf  >= '" + parentLeaf + "'");
        }
        if (roleName != null) {
            sqlStr.append("and role_name like'" + roleName + "%'");
        }
        sqlStr.append("  order by parent_leaf asc");
        roleDto.getPageModel().setQueryHql(sqlStr.toString());
        roleDto.getPageModel().setHqlParamMap(hqlParamMap);
    }


    public void roleTreeTableNew(RoleDto roleDto) {
        String parentLeaf = roleDto.getRole().getParentLeaf();
        String roleName = roleDto.getRole().getRoleName();
        String roleCode = roleDto.getRole().getRoleCode();
        String id = roleDto.getRole().getId();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        sqlStr.append("select * from (select  *  from t_piccbid_role start with id='" + id + "'\n" +
                "        connect by prior Id = parent_id) where 2=2 ");
        if (parentLeaf != null && !"".equals(parentLeaf)) {
            sqlStr.append("and parent_leaf  >= '" + parentLeaf + "'");
        }
        if (roleName != null && !"".equals(roleName)) {
            sqlStr.append("and role_name like'" + roleName + "%'");
        }
        if (roleCode != null && !"".equals(roleCode)) {
            sqlStr.append("and role_code like'" + roleCode + "%'");
        }
        sqlStr.append("  order by parent_leaf asc");
        roleDto.getPageModel().setQueryHql(sqlStr.toString());
        roleDto.getPageModel().setHqlParamMap(hqlParamMap);
    }


    public void deleteAutho(String roleId, String type) {
        String sql = "delete from t_piccbid_role_authority where role_id='" + roleId + "' and type in ('" + type + "')";
        jdbcTemplate.execute(sql);
    }

    /*   public void deleteRoleDataAutho(String roleId) {
           String sql = "delete from t_piccbid_role_authority where role_id='" + roleId + "' and type='2'";
           jdbcTemplate.execute(sql);
       }
       public void deleteRoleButtonAutho(String roleId) {
           String sql = "delete from t_piccbid_role_authority where role_id='" + roleId + "' and type='3'";
           jdbcTemplate.execute(sql);
       }*/
    public void insertAutho(String id, String ids, String type) {
        String sql = "  declare v_clob clob:='" + ids + "'; begin insert into t_piccbid_role_authority (id,role_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "',v_clob,'" + type + "'); end;";
        // String sql="insert into t_piccbid_role_authority (id,role_id,authority_id,type) values (sys_guid(),'"+id+"','"+ids+"','1')";
        jdbcTemplate.execute(sql);
    }

    public void insertOneAutho(String roleId, String oneId, String typeCode) {
        String sql = " insert into t_piccbid_role_authority (id,role_id,type_id,type,type_code) values( sys_guid(),'" + roleId + "','" + oneId + "','9','" + typeCode + "')";
        // String sql="insert into t_piccbid_role_authority (id,role_id,authority_id,type) values (sys_guid(),'"+id+"','"+ids+"','1')";
        jdbcTemplate.execute(sql);
    }
  /*  public void insertRoleDataAuhto(String id,String ids){
       // String sql="insert into t_piccbid_role_authority (id,role_id,authority_id,type) values (sys_guid(),'"+id+"','"+ids+"','2')";
        String sql="  declare v_clob clob:='"+ids+"'; begin insert into t_piccbid_role_authority (id,role_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "',v_clob,'2'); end;";

        jdbcTemplate.execute(sql);
    }

    public void insertRoleButtonAuhto(String id,String ids){
        //String sql="insert into t_piccbid_role_authority (id,role_id,authority_id,type) values (sys_guid(),'"+id+"','"+ids+"','')";
        String sql="  declare v_clob clob:='"+ids+"'; begin insert into t_piccbid_role_authority (id,role_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "',v_clob,'3'); end;";
        jdbcTemplate.execute(sql);
    }*/

    public void deleteArray(String[] arrays) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_piccbid_role where id in (");
        int length = arrays.length;
        for (int i = 0; i <= length - 1; i++) {
            if (i == length - 1) {
                sql.append("'" + arrays[i] + "'" + ")");
            } else {
                sql.append("'" + arrays[i] + "'" + ",");
            }
        }
        jdbcTemplate.execute(sql.toString());
    }

    public void deleteRoleAuthorityArrays(String[] arrays) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_piccbid_role_authority where role_id in (");
        int length = arrays.length;
        for (int i = 0; i <= length - 1; i++) {
            if (i == length - 1) {
                sql.append("'" + arrays[i] + "'" + ")");
            } else {
                sql.append("'" + arrays[i] + "'" + ",");
                // System.out.println(sql.toString());
            }
        }
        jdbcTemplate.execute(sql.toString());
    }

    public void deleteRoleAuthority(String id) {
        String sql = "delete from t_piccbid_role_authority where role_id ='" + id + "'";
        jdbcTemplate.execute(sql);
    }

    public void deleteUserAuthorityByRoleId(String roleId) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_piccbid_user_authority where user_id in (select id from t_piccbid_user where role_code=(select role_code from " +
                "t_piccbid_role where id='" + roleId + "'))");
        jdbcTemplate.execute(sql.toString());
    }

    public List<String> getUsers(String roleId) {
        String sql = "select id from t_piccbid_user where role_code in (select role_code from t_piccbid_role where id='" + roleId + "')";
        //  List<User> users=jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        List<String> users = jdbcTemplate.queryForList(sql, String.class);
        // System.out.println("删除");
        // System.out.println(JSON.toJSONString(users));
        return users;
    }

    //查询角色关联的用户，审核没通过的除外
    public List<String> getUsersWithOutAuding(String roleId) {
        String sql = "select id from t_piccbid_user where role_code in (select role_code from t_piccbid_role where id='" + roleId + "') and status !='2'";
        //  List<User> users=jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        List<String> users = jdbcTemplate.queryForList(sql, String.class);
        // System.out.println("删除");
        // System.out.println(JSON.toJSONString(users));
        return users;
    }

   /* public void updataUserAuthority(List<String> userIds,String type,String key){
        StringBuilder sqlStr=new StringBuilder();
        sqlStr.append("UPDATE t_piccbid_user_authority  SET authority_id = CASE user_id ");
        int size=userIds.size();
        for (int i=0;i<size;i++){
            String userId=userIds.get(i);
            String  userNewAhthority= userService.roleDelete(userId,key);
            sqlStr.append(" when '"+userId+"' then "+userNewAhthority+"" );
        }
        sqlStr.append(" END  WHERE type='"+type+"'");
        System.out.println("输出"+sqlStr.toString());
    }*/

    public void updataUserAuthority(List<String> userIds, String type, String key, Map map) {
        StringBuilder sqlStr = new StringBuilder();
        int size = userIds.size();
        sqlStr.append("declare ");
        for (int i = 0; i < size; i++) {
            String userId = userIds.get(i);
            String userNewAhthority = userService.roleDelete(userId, key);
            sqlStr.append(" " + map.get(userId) + " clob:='" + userNewAhthority + "';");
        }
        sqlStr.append(" begin");
        for (int i = 0; i < size; i++) {
            String userId = userIds.get(i);
            sqlStr.append(" UPDATE t_piccbid_user_authority  " +
                    " SET authority_id= " + map.get(userId) + " where user_id='" + userId + "' and type='" + type + "';");
        }
        sqlStr.append("end;");
        //  System.out.println("打印sql"+sqlStr.toString());
        jdbcTemplate.execute(sqlStr.toString());
    }

    public String getRoleByIdAndType(String id, String type) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select authority_id from t_piccbid_role_authority where 1=1");
        sqlStr.append(" and role_id ='" + id + "'");
        sqlStr.append(" and type='" + type + "'");
        List<String> menuList = jdbcTemplate.queryForList(sqlStr.toString(), String.class);
        if (menuList.size() > 0) {
            return menuList.get(0);
        } else {
            return JSON.toJSONString(new ArrayList<>());
        }
    }

    public void updataUserAuthorityFromRoleAhthoChanged(List<String> userIds, String type, Map map, List<String> before, List<String> after) {
        StringBuilder sqlStr = new StringBuilder();
        int size = userIds.size();
        sqlStr.append("declare ");
        for (int i = 0; i < size; i++) {
            String userId = userIds.get(i);
            //获取某个用户最后的权限
            String userAuthorityString = getOneUserFinalAuthority(type, before, after, userId);
            sqlStr.append(" " + map.get(userId) + " clob:='" + userAuthorityString + "';");
        }
        sqlStr.append(" begin");
        for (int i = 0; i < size; i++) {
            String userId = userIds.get(i);
            sqlStr.append(" UPDATE t_piccbid_user_authority  " +
                    " SET authority_id= " + map.get(userId) + " where user_id='" + userId + "' and type='" + type + "';");
        }
        sqlStr.append("end;");
        // System.out.println("打印sql" + sqlStr.toString());
        jdbcTemplate.execute(sqlStr.toString());
    }

    private String getOneUserFinalAuthority(String type, List<String> before, List<String> after, String userId) {
        List<String> userAuthorityString = userJdbcDao.getAhthorityByUserIdAndType(userId, type);
        List<String> userAuthorityList = JSONObject.parseArray(userAuthorityString.get(0), String.class);
        //去掉之前的role的权限
        userAuthorityList.removeAll(before);
        //无重复取并集
        after.removeAll(userAuthorityList);
        userAuthorityList.addAll(after);
        // System.out.println("最后" + JSON.toJSONString(userAuthorityList));
        return JSON.toJSONString(userAuthorityList);
    }

    public List<String> getRemoveIds(String id) {
        String sql = "select id from t_piccbid_unit where parent_id='" + id + "'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> getRemoveCodes(String id) {
        String sql = "select unit_code from t_piccbid_unit where parent_id='" + id + "'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public Unit getCity(String id) {
        String sql = "select id,unit_code,parent_leaf from t_piccbid_unit where id=(select parent_id from t_piccbid_unit where id='" + id + "')";
        List<Unit> unit = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Unit.class));
        if (unit.size() > 0) {
            return unit.get(0);
        } else {
            return new Unit();
        }
    }

    public void insertOneAuthoNew(String roleId, String id, String unitCode, String typeLeaf) {
        String sql = "insert into t_piccbid_role_authority (id,role_id,type_id,type,type_code,type_leaf) values(sys_guid(),'" + roleId + "','" + id + "','9','" + unitCode + "','" + typeLeaf + "')";
        jdbcTemplate.execute(sql);
    }

    public String getHospId() {
        String sql = "select id from t_piccbid_role where role_name='医院'";
        List<String> ids = jdbcTemplate.queryForList(sql, String.class);
        if (ids.size() > 0) {
            return ids.get(0);
        } else {
            return "";
        }
    }

    public List<String> getMenuAndButtonIds(String roleId) {
        String sql = "select authority_id from t_piccbid_role_authority where role_id='" + roleId + "' and type in ('1','3')";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public void insertHospAuthority(String menuids, String roleId, String type) {
        String sql = "  declare v_clob clob:='" + menuids + "'; begin insert into t_piccbid_role_authority (id,role_id,AUTHORITY_ID,type) values( sys_guid(),'" + roleId + "',v_clob,'" + type + "'); end;";
        jdbcTemplate.execute(sql);
    }
    public void deleteHospUsers(){
        String sql="delete t_piccbid_role where is_hospital='1'" ;
        jdbcTemplate.execute(sql);
    }

    public void deleteHospsAuthority(){
        String sql="delete t_piccbid_role_authority where role_id in (select id from t_piccbid_role where is_hospital='1')";
        jdbcTemplate.execute(sql);
    }
}


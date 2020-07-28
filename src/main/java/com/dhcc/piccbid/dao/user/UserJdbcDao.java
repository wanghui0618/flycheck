package com.dhcc.piccbid.dao.user;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.jdbc.JdbcTemplateWrapper;
import com.dhcc.piccbid.dto.user.UserDto;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.entity.user.UserVo;
import com.dhcc.piccbid.service.unit.UnitService;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhuoyue
 * @version V1.0
 * @date 2018-10-12 08:46:55
 */
@Component
public class UserJdbcDao {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private JdbcTemplateWrapper jdbcTemplateWrapper;
    @Autowired
	private UnitService unitService;


    public User findUser(String userNamePage) {
        String sql = "select t.id,name,phone,email,add_date,update_date,password,role_id,status,remark,login_name,role_id_change,user_change,photo,unit_id from T_PICCBID_USER t where 1=1 ";
        sql += "and  phone = '" + userNamePage + "' or email ='" + userNamePage + "' or login_name='" + userNamePage + "'";
        
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql, User.class, null);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }


    public User checkUserPhone(User user) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sqlStr.append("SELECT *");
        sqlStr.append(" from T_PICCBID_USER  where PHONE='" + user.getPhone() + "'");
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), User.class, hqlParamMap);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public User checkUserLoginName(User user) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sqlStr.append("SELECT *");
        sqlStr.append(" from T_PICCBID_USER  where LOGIN_NAME='" + user.getLoginName() + "'");
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), User.class, hqlParamMap);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public User checkUserEmail(User user) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sqlStr.append("SELECT *");
        sqlStr.append(" from T_PICCBID_USER  where EMAIL='" + user.getEmail() + "'");
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), User.class, hqlParamMap);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    

    public User checkUserEmail1(String email) {
        String sql = "select * from T_PICCBID_USER where 1=1";
        sql += "and  email = '" + email + "' ";
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql, User.class, null);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }


    public Boolean updatePassword(String newpassPage, String id) {
        try {
            String sql = "update t_piccbid_user set password='" + newpassPage + "' where id='" + id + "'";
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean updateNewInformation(String newnamePage,String newphonePage, String newemailPage, String id) {
    	
        try {
            String sql = "update t_piccbid_user set name='" + newnamePage + "',phone ='" + newphonePage + "',email ='" + newemailPage + "' where id='" + id + "'";
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public User findById(User user) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sqlStr.append("SELECT *");
        sqlStr.append(" from T_PICCBID_USER  where ID='" + user.getId() + "'");
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), User.class, hqlParamMap);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public Boolean setNewPassword(String newPassword, String id) {
        try {
            String sql = "update t_piccbid_user set password='" + newPassword + "' where id='" + id + "'";
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User findUserById(String id) {
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        sqlStr.append("SELECT *");
        sqlStr.append(" from T_PICCBID_USER  where ID='" + id + "'");
        @SuppressWarnings("unchecked")
        List<User> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), User.class, hqlParamMap);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public  String getRoleCodeById(String id){
        String sql="select role_id from T_PICCBID_USER where id='"+id+"'";
        List<String> strings=jdbcTemplate.queryForList(sql,String.class);
        if (strings.size()>0){
            return  strings.get(0);
        }else {
            return "";
        }
    }
    
    

    public UserVo listUserVo(UserDto dto) {
        PageModel pageModel = dto.getPageModel();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        sqlStr.append("SELECT t1.*,t2.role_name as change_name,t3.unit_name");
        sqlStr.append(" from (select a.*,b.role_name as role_name from t_piccbid_user a left join t_piccbid_role b on a.role_id =b.id) t1 left join t_piccbid_role t2 on t1.role_id_change=t2.id" +
                "  left join t_piccbid_unit t3 on t3.id=t1.unit_id where 1=1");
        UserVo userVo = dto.getUserVo();
        if (userVo != null) {
            if (userVo.getName() != null && !"".equals(userVo.getName())) {
                sqlStr.append(" and t1.name like '%" + userVo.getName() + "%'");
            }
            if (userVo.getLoginName() != null && !"".equals(userVo.getLoginName())) {
                sqlStr.append(" and t1.login_name like '%" + userVo.getLoginName() + "%'");
            }
            if (userVo.getPhone() != null && !"".equals(userVo.getPhone())) {
                sqlStr.append(" and t1.phone like '%" + userVo.getPhone() + "%'");
            }

        }
        sqlStr.append(" order by t1.name desc ");

        @SuppressWarnings("unchecked")
        List<UserVo> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), UserVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "b.id");
        int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t1.id", hqlParamMap);
        pageModel.setTotals(count);
        pageModel.setPageData(userList);
        return userVo;

    }
    
    public UserVo query(UserDto dto) {
		String keyDom = dto.getKeyDom();
		PageModel pageModel = dto.getPageModel();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        sqlStr.append("SELECT t1.*,t2.role_name as change_name,t3.unit_name");
        sqlStr.append(" from (select a.*,b.role_name as role_name from t_piccbid_user a left join t_piccbid_role b on a.role_id =b.id) t1 left join t_piccbid_role t2 on t1.role_id_change=t2.id" +
                "  left join t_piccbid_unit t3 on t3.id=t1.unit_id where 1=1");
        UserVo userVo = dto.getUserVo();
        if(keyDom != null && !"".equals(keyDom)){
        	sqlStr.append(" and t1.name like '%" + keyDom + "%' or t1.phone like '%" + keyDom + "%' or t1.email like '%" + keyDom + "%' or t3.unit_name like '%" + keyDom + "%' or t2.role_name like '%" + keyDom + "%'");
        }
        sqlStr.append(" order by t1.add_date desc ");
        @SuppressWarnings("unchecked")
        List<UserVo> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), UserVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "b.id");
        int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sqlStr.toString(), "t1.id", hqlParamMap);
        pageModel.setTotals(count);
        pageModel.setPageData(userList);
        return userVo;
	}

    
    public List<UserVo> latestLogin(UserDto dto) {
    	PageModel pageModel = dto.getPageModel();
		pageModel.setPageSize(4);
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        dto.getPageModel().setHqlParamMap(hqlParamMap);
        
        /*Date loginTime = new Date();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = temp.format(loginTime); 
		Date begin = null;
		
		try {
			begin = temp.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long mss=begin.getTime()-1000*60*60*24;
		Date dt = new Date(mss);
		String date2 = temp.format(dt);*/
		String sql = " select name,login_time,id from (" +
					 " select a.name as name ,b.login_time as login_time ,a.id as id "+
                     " from " +
                     " (select max(id) as id,name from  T_PICCBID_USER  group by name)a "+
                     " left join (select max(login_time) as login_time,user_id from T_PICCBID_LOG group by user_id) b "+
                     " on a.id=b.user_id  where b.login_time >= sysdate-1 "+
                     ")";
		@SuppressWarnings("unchecked")
        List<UserVo> userList = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql, UserVo.class, hqlParamMap, pageModel.getPageNo(), pageModel.getPageSize(), "id");
        pageModel.setPageData(userList);
        return userList;
	}
    
    public Boolean setAuditing(String id, String remark, String name) {
        try {
            String sql = "update t_piccbid_user set status='1',remark='" + remark + "',user_change='"+name+"' where id='" + id + "'";
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            return false;
        }


    }


    public Boolean setReauditing(String id, String remark, String name) {
        try {
            String sql = "update t_piccbid_user set status='2',remark='" + remark + "',user_change='"+name+"' where id='" + id + "'";
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void userTree(UserDto dto) {

        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
        PageModel pageModel = new PageModel();
        dto.setPageModel(pageModel);
      /*  sqlStr.append("select * from t_piccbid_user ");
        sqlStr.append("  order by id asc");*/
        sqlStr.append("SELECT b.*,a.city_name");
        sqlStr.append(" from T_PICCBID_USER b left join T_PICCBID_DICT_CITY a on b.city_code=a.city_code where 1=1");
        dto.getPageModel().setQueryHql(sqlStr.toString());
        dto.getPageModel().setHqlParamMap(hqlParamMap);

    }

    /*    public void roleTreeTable(UserDto userDto) {
            String roleCode=userDto.getUserVo().getRoleCode();
            String name=userDto.getUserVo().getName();
            String loginName=userDto.getUserVo().getLoginName();
            String phone=userDto.getUserVo().getPhone();
            StringBuilder sqlStr = new StringBuilder();
            Map<String, Object> hqlParamMap = new HashMap<String, Object>();
            PageModel pageModel = new PageModel();
            userDto.setPageModel(pageModel);
            sqlStr.append("SELECT b.*,a.role_name");
            sqlStr.append(" from T_PICCBID_USER b left join T_PICCBID_ROLE a on b.role_id=a.role_id where 1=1 ");

            if (name != null) {
                sqlStr.append("and b.name like'" + name + "%'");
            }
            if (phone != null) {
                sqlStr.append("and b.phone like'" + phone + "%'");
            }
            if (loginName != null) {
                sqlStr.append("and b.login_name like'" + loginName + "%'");
            }
            if (roleCode != null) {
                sqlStr.append("and b.role_id ='" + roleCode + "'");
            }
            sqlStr.append("  order by name asc");
            System.out.println(sqlStr);
            userDto.getPageModel().setQueryHql(sqlStr.toString());
            userDto.getPageModel().setHqlParamMap(hqlParamMap);
        }*/
    public void roleTreeTable(UserDto userDto) {
        String unitId = userDto.getUserVo().getUnitId();
        String name = userDto.getUserVo().getName();
        String loginName = userDto.getUserVo().getLoginName();
        String phone = userDto.getUserVo().getPhone();
        String role=userDto.getUserVo().getRoleId();
        StringBuilder sqlStr = new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<String, Object>();
      /*  PageModel pageModel = userDto.getPageModel();
        userDto.setPageModel(pageModel);*/
        sqlStr.append("SELECT b.*,a.role_name,c.unit_name");
        sqlStr.append(" from T_PICCBID_USER b left join T_PICCBID_ROLE a on b.role_id=a.id left join t_piccbid_unit c on b.unit_id=c.id where 1=1 ");

        if (name != null) {
            sqlStr.append("and b.name like'" + name + "%'");
        }
        if (phone != null) {
            sqlStr.append("and b.phone like'" + phone + "%'");
        }
        if (loginName != null) {
            sqlStr.append("and b.login_name like'" + loginName + "%'");
        }
        if (unitId != null) {
            sqlStr.append("and b.unit_id ='" + unitId + "'");
        }
        if(role!=null){
            sqlStr.append("and b.role_id ='" + role + "'");
        }
        sqlStr.append("  order by name asc");
        userDto.getPageModel().setQueryHql(sqlStr.toString());
        userDto.getPageModel().setHqlParamMap(hqlParamMap);
    }

    //添加初始权限--jpp
	public void addInitialPermissions(User userNow) {
		//根据roleId查询权限

        String sql="select a.authority_id from T_PICCBID_role_authority a left join t_piccbid_role b on a.role_id=b.id where b.id='"+userNow.getRoleId()+"' and type in ('1','3') order by type ";
       // System.out.println(sql);
        @SuppressWarnings("unchecked")
		//List<String> authority = jdbcTemplateWrapper.queryAllMatchList(sql, String.class, null);
                //这里要求查询出来的角色的权限必须是拥有三种类型的权限
                List<String> authority = jdbcTemplate.queryForList(sql,String.class);
        // System.out.println("查询的结果"+JSON.toJSONString(authority));
		if(authority.size()>0) {
		    //给用户赋值角色自带菜单权限
			//String sqlUserMenu="insert into T_PICCBID_user_authority values(sys_guid(),'"+userNow.getId()+"','"+authority.get(0)+"','1')";
            String sqlUserMenu=" declare var_clod clob:='"+authority.get(0)+"';begin insert into T_PICCBID_user_authority values(sys_guid(),'"+userNow.getId()+"',var_clod,'1');end;";

            jdbcTemplate.execute(sqlUserMenu);
			//如果查询出来的size为2 说明角色也赋予了数据权限，此时也要给改用户赋上
			if (authority.size()>1) {
                String sqlUserData = " declare var_clod_data clob:='"+authority.get(1)+"';begin insert into T_PICCBID_user_authority values(sys_guid(),'"+userNow.getId()+"',var_clod_data,'2');end;";

                jdbcTemplate.execute(sqlUserData);
            }
			if (authority.size()>2){
                String sqlUserData =" declare var_clod_button clob:='"+authority.get(2)+"';begin insert into T_PICCBID_user_authority values(sys_guid(),'"+userNow.getId()+"',var_clod_button,'3');end;";

                jdbcTemplate.execute(sqlUserData);
            }
		}
	}

    public void deleteUserMenuAutho(String id) {
        String sql = "delete from t_piccbid_user_authority where type = '1' and user_id='" + id + "'";
        jdbcTemplate.execute(sql);
    }
    public void deleteUserDataAutho(String id) {
        String sql = "delete from t_piccbid_user_authority where type = '2' and user_id='" + id + "'";
        jdbcTemplate.execute(sql);
    }
    public void deleteUserButtonAutho(String id) {
        String sql = "delete from t_piccbid_user_authority where type = '3' and user_id='" + id + "'";
        jdbcTemplate.execute(sql);
    }


    public void insertUserMenuAuhto(String  menuIds, String id) {

        //String sql = "insert into t_piccbid_user_authority (id,user_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "','" + menuIds + "','1')";
        String sql="  declare v_clob clob:='"+menuIds+"'; begin insert into t_piccbid_user_authority (id,user_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "',v_clob,'1'); end;";

        jdbcTemplate.execute(sql);
    }

    public void insertUserDataAuhto(String menuIds, String id) {
      //  String sql = "insert into t_piccbid_user_authority (id,user_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "','" + menuIds + "','2')";
        String sql="  declare v_clob clob:='"+menuIds+"'; begin insert into t_piccbid_user_authority (id,user_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "',v_clob,'2'); end;";

        jdbcTemplate.execute(sql);
    }

    public void insertUserButtonAuhto(String menuIds, String id) {
        //String sql = "insert into t_piccbid_user_authority (id,user_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "','" + menuIds + "','3')";
        String sql="  declare v_clob clob:='"+menuIds+"'; begin insert into t_piccbid_user_authority (id,user_id,AUTHORITY_ID,type) values( sys_guid(),'" + id + "',v_clob,'3'); end;";
        jdbcTemplate.execute(sql);
    }

    public List<User> selectAllAutority(String s){
        StringBuilder sql=new StringBuilder();
        Map<String, Object> hqlParamMap = new HashMap<>();
        sql.append("select id from (select * from T_PICCBID_UNIT where city_code in (select city_code from T_PICCBID_UNIT" +
                " where id in(");
        sql.append(s);
        sql.append(") and is_hospital ='0'))");
        return jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(),User.class,hqlParamMap);
    }


	public void saveLogInformation(String id, Date loginTime, Date loginoutTime) {
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String date1 = temp.format(loginTime); 
		
		String date2 = temp.format(loginoutTime);
		
		Date begin = null;
		Date end = null ;
		try {
			begin = temp.parse(date1);
			end = temp.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long mss=end.getTime()-begin.getTime();
	    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
	    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
	    long seconds = (mss % (1000 * 60)) / 1000;  
	    String day=   hours + "小时" + minutes + "分钟"  + seconds +"秒";  
		
		String sql = "insert into T_PICCBID_LOG (ID,USER_ID,LOGIN_TIME,LOGINOUT_TIME,MINUTES) VAlUES"
				+ " ('"+UUID.randomUUID().toString().replaceAll("-","")+"','"+id+"',to_date('"+date1+"','yyyy-MM-dd hh24:mi:ss'),"
						+ "to_date('"+date2+"','yyyy-MM-dd hh24:mi:ss'),'"+day+"')";
		jdbcTemplate.execute(sql);
		
	}


	public void updateLoginStatus(String id) {
            String sql = "update t_piccbid_user set LOGIN_STATUS='1' where id='" + id + "'";
            jdbcTemplate.update(sql);
	}


	public void updateLoginoutStatus(String id) {
		String sql = "update t_piccbid_user set LOGIN_STATUS='0' where id='" + id + "'";
        jdbcTemplate.update(sql);
	}


	public void updatePhoto(String id, String photo) {
		String sql = "update t_piccbid_user set PHOTO='"+photo+"' where id='" + id + "'";
		jdbcTemplate.update(sql);
	}


	public void listNumber(UserDto dto) {
		StringBuilder sqlStr=new StringBuilder();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sqlStr.append("select * from (select decode(role_id,'yk','游客','admin','系统管理员','hospital','医院','yibaoju','医保局','user','普通用户','ywsh','业务审核',role_id) as medical_name, count(role_id) as medical_number from T_PICCBID_USER ");
		sqlStr.append(" group by role_id order by count(role_id) desc )");
		
		@SuppressWarnings("unchecked")
		List<UserVo> list=jdbcTemplateWrapper.queryAllMatchListWithParaMap(sqlStr.toString(), UserVo.class, hqlParamMap);
		dto.getPageModel().setTotals(list.size());
		dto.getPageModel().setPageData(list);
	}

	public void deleteUserAuthority(String id){
        String sql="delete from t_piccbid_user_authority where user_id='"+id+"'";
        jdbcTemplate.execute(sql);
    }

    public List<String> getBeforeAhthorityByroleCode(String id){
        String sql="select authority_id from t_piccbid_role_authority where role_id  " +
                "in (select id from t_piccbid_role where role_id " +
                "in(select role_id_change from t_piccbid_user where id='"+id+"') ) order by type";
        List<String>  roleAuthorityIds=jdbcTemplate.queryForList(sql,String.class);
        return roleAuthorityIds;
    }

    public List<String> getAfterAhthorityByroleCode(String id){
        String sql="select authority_id from t_piccbid_role_authority where role_id  " +
                "in (select id from t_piccbid_role where role_id " +
                "in(select role_id from t_piccbid_user where id='"+id+"') ) order by type";
        List<String>  roleAuthorityIds=jdbcTemplate.queryForList(sql,String.class);
        return roleAuthorityIds;
    }
    public List<String> getAhthorityByUserId(String id){
        String sql="select authority_id from t_piccbid_user_authority  where user_id='"+id+"' order by type" ;
        List<String>  userAuthorityIds=jdbcTemplate.queryForList(sql,String.class);
        return userAuthorityIds;
    }

    public List<String> getAhthorityByUserIdAndType(String id,String type){
        String sql="select authority_id from t_piccbid_user_authority  where user_id='"+id+"' and type='"+type+"'" ;
        List<String>  userAuthorityIds=jdbcTemplate.queryForList(sql,String.class);
        System.out.println("语句"+sql);
        return userAuthorityIds;
    }

    public void updateUserAuthorityChanged(Map map,String id){
       // StringBuilder sql=new StringBuilder();
        String menuIds=String.valueOf(map.get("menu"));
        String dataIds=String.valueOf(map.get("data"));
        String buttonIds=String.valueOf(map.get("button"));
        //删除原来的记录
        deleteUserAuthority(id);
        insertUserMenuAuhto(menuIds,id);
        insertUserDataAuhto(dataIds,id);
        insertUserButtonAuhto(buttonIds,id);
    }


	/**
	 * @param num 
	 * @return
	 */
	public PageModel listNewLogin(UserDto dto, String num) {
		// TODO Auto-generated method stub
		String num2 = "1";
		if(!num.equals("")){
			num2 = num;
		}
		String keyDom = dto.getKeyDom();
		/*System.out.println(keyDom);*/
		PageModel pageModel = dto.getPageModel();
		StringBuilder sql = new StringBuilder();
		//List<String> listCityCode=unitService.getUserDataAhthority();
		Map<String,Object> hqlParamMap = new HashMap<String,Object>();
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		dto.getPageModel().setHqlParamMap(hqlParamMap);
		sql.append("select distinct * from (select b.user_id as idd,login_time,c.city_code,a.name,a.phone,a.email,c.unit_name,d.role_name,a.login_name, b.logout_time,b.minutes from (");
		sql.append(" (select max(login_time) as login_time,user_id as user_id, max(loginout_time) as logout_time, max(minutes) as minutes from T_PICCBID_LOG where login_time >= sysdate-"+num2+" group  by user_id) b"); 
		sql.append(" left join (select name,id,role_id,unit_id,phone,email,login_name from T_PICCBID_USER where 1=1");
		//调用数据授权
		sql.append(" group by name,id,role_id,unit_id,phone,email,login_name) a on a.id=b.user_id");
		sql.append(" left join T_PICCBID_ROLE d on d.id=a.role_id");
		sql.append(" left join t_piccbid_unit c on a.unit_id=c.id) where 1=1");
		//String cityCode="c.city_code";
		//调用数据授权
		//sql=unitService.appendDataAuhoritySql(cityCode,sql,listCityCode);
        if(keyDom != null && !"".equals(keyDom)){
        	sql.append(" and a.name like '%" + keyDom + "%' or a.login_name like '%" + keyDom + "%' or a.phone like '%" + keyDom + "%' or a.email like '%" + keyDom + "%' or c.unit_name like '%" + keyDom + "%' or d.id like '%" + keyDom + "%'");
        }
		sql.append(" )");
		sql.append(" order by login_time desc");
		System.out.println(sql);
		
		List<UserVo> list = jdbcTemplateWrapper.queryAllMatchListWithParaMap(sql.toString(), UserVo.class, hqlParamMap,dto.getPageModel().getPageNo(), dto.getPageModel().getPageSize(),"idd");
		int count = jdbcTemplateWrapper.getResultCountWithValuesMap(sql.toString(), "idd", hqlParamMap);
        pageModel.setTotals(count);
        pageModel.setPageData(list);
		return pageModel;
	}

  public void insertOneHosp(String name,String password,String loginName,String unitId,String roleId){
	    String sql="insert into t_piccbid_user (id,name,add_date,password,status,login_name,unit_id,role_id)\n" +
                "values(sys_guid(),'"+name+"',Sysdate,'"+password+"','1','"+loginName+"','"+unitId+"','"+roleId+"')";
	    jdbcTemplate.execute(sql);
  }
  public void deleteHospUsers(){
	    String sql="delete t_piccbid_user where role_id in (select id from t_piccbid_role where is_hospital='1')";
	    jdbcTemplate.execute(sql);
  }


}
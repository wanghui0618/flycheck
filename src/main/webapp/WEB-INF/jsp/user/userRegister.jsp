<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/login.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/userRegister.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/login/encode.js"></script>
<style>
</style>
<script language="JavaScript">
function keyRegister(){
 if (event.keyCode==13)  //回车键的键值为13
   document.getElementById("registerbtn").click(); //调用登录按钮的登录事件
}
</script> 
<title>普通用户注册</title>
</head>
<body onkeydown="keyRegister();">
	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login">
    <div class="layadmin-user-login-main">
    
      
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>普通新用户注册</h2>
      </div>
      
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
      
     <!--  <div class="layui-form-item"  style="width:335px" >
    	<select name="cityCode"  id='city' >
    	  <option value='' disabled selected style='display:none;'>请选择医疗机构</option>
	      <option value=""></option>
	    </select>
  	  </div> -->
  	  
  	  <div class="layui-form-item">
      	<label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-name"></label>
      	<input type="text" name="loginName" id="LAY-user-login-loginName" lay-verify="required" placeholder="用户名" class="layui-input">
      </div>
  	  
      <div class="layui-form-item">
      	<label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-login-cellphone"></label>
      	<input type="text" name="cellphone" id="LAY-user-login-cellphone" lay-verify="phone" placeholder="11位手机号" class="layui-input">
      </div>
        
      <div class="layui-form-item">
      	<label class="layadmin-user-login-icon layui-icon layui-icon-rate" for="LAY-user-login-email"></label>
      	<input type="text" name="email" id="LAY-user-login-email" lay-verify="email" placeholder="邮箱" class="layui-input">
      </div>
      
      <div class="layui-form-item">
      	<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
      	<input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码（6到16位数字加字母）"  class="layui-input">
      </div>
      
      <div class="layui-form-item">
      	<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label>
      	<input type="password" name="repass" id="LAY-user-login-repass" lay-verify="required" placeholder="确认密码" class="layui-input">
      </div>
      
      <div class="layui-form-item">
      	<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-name"></label>
      	<input type="text" name="name" id="LAY-user-login-name" lay-verify="required" placeholder="真实姓名" class="layui-input">
      </div>
        
      
      <div class="layui-form-item">
      	<button class="layui-btn layui-btn-fluid" id="userRegisterbtn" lay-submit lay-filter="LAY-user-reg-submit">注 册</button>
      </div>
      
      <div class="layui-trans layui-form-item layadmin-user-login-other">
      	<a href="<%=request.getContextPath() %>/" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
      </div>
      
       </div>
      </div>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.dhcc.piccbid.entity.user.User" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  	<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/login.css" media="all">
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/set/info.js"></script>
<title>设置我的资料</title>
</head>
<%
	User user = (User)session.getAttribute("user");
	String name = user.getName();
	String phone = user.getPhone();
	String email = user.getEmail();
	String loginName = user.getLoginName();
%>
<body>
	<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">设置我的资料</div>
          <div class="layui-card-body" pad15>
            
            <div class="layui-form" lay-filter="">
              
              <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline" style="line-height: 35px;margin: 0 0 0 10px;">
                	<%=loginName %>
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                  <input type="text" name="user.name" id="newname" value="<%=name %>" class="layui-input">
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                  <input type="text" name="user.phone" id="newphone" value="<%=phone %>" lay-verify="phone" autocomplete="off" class="layui-input">
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" name="user.email" id="newemail" value="<%=email %>" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
              </div>
              <br>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" id="qrxg" lay-submit lay-filter="setmyinfo">确认修改</button>
                </div>
              </div>
              
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
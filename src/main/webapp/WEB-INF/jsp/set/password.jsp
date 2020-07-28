<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/set/password.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/login/encode.js"></script>
<title>设置我的密码</title>
</head>
<body>
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">修改密码</div>
          <div class="layui-card-body" pad15>
            
            <div class="layui-form" lay-filter="">
            
            	<div class="input_div" style="display:none">
	            	<input type="text" style="background-color: #fff;padding-left: 10px;border: 1px solid #C7C7C7;border-radius: 3px;width: 159px;height: 36px;" name="loginName" id="LAY-user-register-loginName" autocomplete="off" placeholder="用户名" class="my-input-xingming">
	            </div>
            
              <div class="layui-form-item">
                <label class="layui-form-label">当前密码</label>
                <div class="layui-input-inline">
                  <input type="password"  style="margin-left: 10px;background-color: #fff;padding-left: 10px;border: 1px solid #C7C7C7;border-radius: 3px;width: 159px;height: 36px;" lay-verify="required" lay-verType="tips" autocomplete="new-password" class="layui-input-one">
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                  <input type="password" style="margin-left: 10px;background-color: #fff;padding-left: 10px;border: 1px solid #C7C7C7;border-radius: 3px;width: 159px;height: 36px;"  lay-verify="required" lay-verType="tips" autocomplete="new-password" class="layui-input-two">
                </div>
                <div style="margin-left:25px;" class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">确认新密码</label>
                <div class="layui-input-inline">
                  <input type="password" style="margin-left: 10px;background-color: #fff;padding-left: 10px;border: 1px solid #C7C7C7;border-radius: 3px;width: 159px;height: 36px;"  lay-verify="required" lay-verType="tips" autocomplete="new-password" class="layui-input-three">
                </div>
              </div>
              <br>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" id="qrxg" lay-submit lay-filter="setmypass">确认修改</button>
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
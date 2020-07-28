<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<style>
</style>
<title>用户审核</title>
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    
    <!-- 通过id来查找用户，但是显示用户信息时不显示用户id -->
    <input type="hidden"  name="user.id" id="id">
    
    <div class="layui-form-item">
    <label class="layui-form-label">申请角色由:</label>
    	<div class="layui-input-inline" >
			<input type="text" class="layui-input" name="user.roleCodeChange"  id="changeName"  readonly="readonly">
	    </div>
	  </div>
	  <div class="layui-form-item">
	  <label class="layui-form-label">变为</label>
    	<div class="layui-input-inline" >
			<input type="text" name="user.roleCode"  id="roleName" class="layui-input" readonly="readonly">
	    </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-block" >
      <input type="radio" name="auditing"  id="auditing" value="pass"  title="审核通过">
      <input type="radio" name="auditing"  id="auditing" value="nopass"  title="审核不通过" checked>
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">审核意见</label>
    <div class="layui-input-block" style="width:220px">
      <textarea name="user.remark"  id="remark"  placeholder="请输入内容（如无请输入“无”）"  class="layui-textarea"></textarea>
    </div>
  </div>
    
    <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
    
  </div>

  <script>
  $(function(){
	  var auditing = $("#auditing").val();
	  var remark = $("#remark").val();
  });
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
	  var form = layui.form;
  })
  
  function child(obj){
	  var user = JSON.parse(obj);
	  $("#id").val(user["id"]);
	  for (var index in user){
	      $("#"+index).val(user[index]);
	  }
  }
  </script>
</body>
</html>
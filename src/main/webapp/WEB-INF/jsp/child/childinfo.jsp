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

<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">

<title>用户管理</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
         
          <div class="layui-inline">
            <label class="layui-form-label">是否违规</label>
            <div class="layui-input-block" style="width:100px;">
              <select name="child.ilegalChild">
                <option value="">全部</option>
              	<option value="0">0-未违规</option>
                <option value="1">1-明确违规</option>
                <option value="2">2-疑似违规</option>
              </select>
            </div>
          </div>
       
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
          <font color="red">违规判断：0-未违规，1-违规，2为疑似违规</font>
        </div>
      </div>
      
      <div class="layui-card-body">
        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
        <script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="changeToWeiweigui"><i class="layui-icon layui-icon-edit"></i>标为未违规</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="changeToWeigui"><i class="layui-icon layui-icon-delete"></i>标为违规</a>
        </script>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/child/childinfo.js"></script>
  <script>
  var billing_no,admission_no;
  function child(obj){
  	  var user = JSON.parse(obj);
  	  billing_no = user["billingNo"];
  	  admission_no = user["admissionNo"];
  }
  </script>
</body>
</html>
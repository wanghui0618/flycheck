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
            <label class="layui-form-label">收费单据号</label>
            <div class="layui-input-block">
              <input type="text" style="width:100px;" name="child.billingNo" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
           <div class="layui-inline">
            <label class="layui-form-label">住院号</label>
            <div class="layui-input-block">
              <input type="text" style="width:100px;" name="child.admissionNo" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
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
            <label class="layui-form-label">病案编号</label>
            <div class="layui-input-block">
              <input type="text" style="width:100px;" name="temp" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
     	<!--<font color="red">性别违规判断：0-未违规，1-违规，2为疑似违规</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     	<font color="blue">查询条件：case_no唯一确定一条就诊记录，收费单据号-billing_no+住院号admission_no唯一确定一次就诊</font>
         --> 
         <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
        <script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="changeToWeiweigui"><i class="layui-icon layui-icon-edit"></i>标为未违规</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="changeToWeigui"><i class="layui-icon layui-icon-delete"></i>标为违规</a>
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="viewInfo">明细</a>
        </script>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/child/child.js"></script>
</body>
</html>
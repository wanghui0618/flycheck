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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>过度检查</title>
</head>
<body style="">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">城市编码</label>
            <div class="layui-input-block">
              <input type="text" name="excessiveTreat.cityCode" placeholder="请输入城市编码" autocomplete="off" maxlength="20" class="layui-input">
            </div>
          </div>
            <div class="layui-inline">
            <label class="layui-form-label">规则编码</label>
            <div class="layui-input-block">
              <input type="text" name="excessiveTreat.typeNo" placeholder="请输入规则编码" autocomplete="off" maxlength="20" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">规则名称</label>
            <div class="layui-input-block">
              <input type="text" name="excessiveTreat.typeName" placeholder="请输入规则名称" autocomplete="off" maxlength="20" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" stylename="search"  lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button id='cityprice-city-add' class="layui-btn layuiadmin-btn-useradmin" stylename="add" data-type="add"> 
             <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>新增
             </button>
          </div>
        </div>
      </div>
 <!--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a> -->
 <!--  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a> -->
<table id="excessiveTreatTable" class="layui-hide" lay-filter="excessiveTreatTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
	     {{#if (!existsButton('excessiveTreat-update')) { }}
         <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
	     {{# } }}
         {{#if (!existsButton('excessiveTreat-delete')) { }}         
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>         
	     {{# } }}       
     </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/excessiveTreat/excessiveTreat.js"></script>
</body>
</html>
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
<title>城市字典维护</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        <div class="layui-inline">
             <label class="layui-form-label">城市编码</label>
           <div class="layui-input-block" style="width:140px;" >
    	      <select id="cityCode" name="drugPrice.cityCode" lay-filter="cityCode" >
    	        <option  value=""  disabled selected style='display:none;'>请选择</option>
              </select>
          </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">药品编码</label>
            <div class="layui-input-block" style="width:140px;">
              <input type="text" name="drugPrice.drugId" placeholder="请输入药品编码" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">药品名称</label>
            <div class="layui-input-block" style="width:140px;">
              <input type="text" name="drugPrice.drugName" placeholder="请输入药品名称" autocomplete="off" maxlength="10" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-drugprice-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">添加</button>
          </div>
        </div>
      </div>
 <!--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a> -->
 <!--  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a> -->
<table id="drugPriceTable" class="layui-hide" lay-filter="drugPriceTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
         <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/drugprice/drugPriceinfo.js"></script>
</body>
</html>
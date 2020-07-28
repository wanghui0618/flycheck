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
<title>合理用药</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">药物大类</label>
            <div class="layui-input-block">
              <input type="text" name="druguse.drugbig" placeholder="请输入药物大类" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">药物小类</label>
            <div class="layui-input-block">
              <input type="text" name="druguse.drugsmall" placeholder="请输入药物小类" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
           <div class="layui-inline">
            <label class="layui-form-label">药物名称</label>
            <div class="layui-input-block" >
              <input type="text" name="druguse.drugName" placeholder="请输入药物名称" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-druguse-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button id='druguse-add' class="layui-btn  layui-icon-add layuiadmin-btn-useradmin" data-type="add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>添加</button>
          </div>
        </div>
      </div>
      <div class="layui-card-body">
        <table id="druguseTable" class="layui-hide" lay-filter="druguseTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
          {{#if (!existsButton('druguse-view')) { }} 
	      <a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>预览</a>
          {{# } }}          
          {{#if (!existsButton('druguse-edit')) { }}         
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          {{# } }} 
          {{#if (!existsButton('druguse-del')) { }}         
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
          {{# } }}       
 </script>
        </div>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/druguse/druguseinfo.js"></script> 
</body>
</html>
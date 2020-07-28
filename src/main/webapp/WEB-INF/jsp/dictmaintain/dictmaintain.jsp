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

<title>字典管理</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        
         	<div class="layui-inline">
            <label class="layui-form-label">字典名称</label>
            <div class="layui-input-block">
              <input type="text" name="dictmaintain.name"  placeholder="请输入字典名称" autocomplete="off" class="layui-input">
            </div>
          </div>
          
           <div class="layui-inline">
            <label class="layui-form-label" >业务类型</label>
            <div class="layui-input-block">
              <select name="dictmaintain.type" id="typeDesc">
              	<option value="" disabled selected style='display:none ;'>请选择业务类型</option>
              </select>
            </div>
          </div> 
          
          <!-- *  -->
            <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" stylename="search"   lay-submit lay-filter="LAY-user-front-search" id="dictmaintain-dictmaintain-select">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
            <button class="layui-btn layuiadmin-btn-useradmin" stylename="add" data-type="add" id="dictmaintain-dictmaintain-add"> 
            <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>新增</button>
          </div>
        </div>
      </div>
          
     <div class="layui-card-body">
        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
        <script type="text/html" id="table-useradmin-webuser">
			{{#if (!existsButton('dictmaintain-dictmaintain-edit')) { }}
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
			{{# } }}
			{{#if (!existsButton('dictmaintain-dictmaintain-del')) { }}
			 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
			{{# } }}	
          
         
        </script>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictmaintain/dictmaintain.js"></script>
</body>
</html>
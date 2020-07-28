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

<title>药品说明书</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        
         	<div class="layui-inline">
            <label class="layui-form-label">药品名称</label>
            <div class="layui-input-block" style="width:150px">
              <input type="text" name="drugLnstmction.name"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          
           	<div class="layui-inline">
            <label class="layui-form-label">英文名称</label>
            <div class="layui-input-block" style="width:150px">
              <input type="text" name="drugLnstmction.enName"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          
          <!-- *  -->
            <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin"  lay-submit lay-filter="LAY-user-front-search">查询
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
            <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">添加</button>
          </div>
        </div>
      </div>
          
     <div class="layui-card-body">
        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
        <script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="viewInfo">药物说明</a>	
        </script>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/icd/icdinfo1.js"></script>
</body>
</html>
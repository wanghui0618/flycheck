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
            <label class="layui-form-label">行政区域</label>
           		<div class="layui-input-block">
    	        	<select id="cityAdminarea" name="city.cityAdminarea" lay-filter="cityAdminarea" lay-search="">
    	           		<option  value=""  disabled selected style='display:none;'>请选择</option>
               		</select>
         		</div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">城市编码</label>
            <div class="layui-input-block">
              <input type="text" name="city.cityCode" placeholder="请输入城市编码" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">城市名称</label>
            <div class="layui-input-block">
              <input type="text" name="city.cityName" placeholder="请输入城市名称" autocomplete="off" maxlength="10" class="layui-input">
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
<table id="cityTable" class="layui-hide" lay-filter="cityTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
	     {{#if (!existsButton('city-update')) { }}
         <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
	     {{# } }}
         {{#if (!existsButton('city-delete')) { }}         
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>         
	     {{# } }}       
     </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/city/cityinfo.js"></script>
</body>
</html>
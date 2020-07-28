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
<title>诊疗数据对照关系</title>
</head>
<body >

<div class="layui-fluid">
    	<div class="layui-card">
      	<div class="layui-form layui-card-header layuiadmin-card-header-auto">
       		<div class="layui-form-item">
          		<div class="layui-inline">
            		<label class="layui-form-label">版本信息</label>
                <div class="layui-inline" style="width:130px;">
    	        	<select id="cityNameLeft" name="cityRelation.cityNameLeft" lay-filter="cityNameLeft" lay-search=" ">
    	           		<option  value=""  disabled selected style='display:none;'>请选择</option>
               		</select>
         		</div>
          		</div>
          		          		<div class="layui-inline">
            		<label class="layui-form-label">诊疗编码</label>
           			<div class="layui-input-block">
             			<input  style="width:130px" type="text" name="cityRelation.itemCodeLeft" placeholder="药品编码" autocomplete="off" maxlength="15" class="layui-input">
            		</div>
          		</div>
          		          		<div class="layui-inline">
            		<label class="layui-form-label">诊疗名称</label>
           			<div class="layui-input-block">
             			<input  style="width:130px" type="text" name="cityRelation.itemNameLeft" placeholder="药品名称" autocomplete="off" maxlength="15" class="layui-input">
            		</div>
          		</div>
         		<div class="layui-inline" >
            		<button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
             			 <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            		</button>
         		</div>
        	</div>
      </div>
	 <table id="drugTableShow" class="layui-hide" lay-filter="drugTableShow"></table>
	    <script type="text/html" id="table-orgadmin-webuser">
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>解除关联</a>
        </script>
      </div>
</div>
   <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/treatment_relation/treatmentinfo.js"></script>
</body>
</html>
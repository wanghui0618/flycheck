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

<title>超期住院</title>
<style>
</style>
</head>
<body>
<div class="layui-fluid" style="overflow:hidden">
<div class="layui-row layui-col-space15">
<div class="layui-col-md5">
    <div class="layui-card">
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
    <div class="layui-form-item">
         <div class="layui-inline">
            <label class="layui-form-label">疾病名称</label>
            <div class="layui-input-block">
              <input type="text" name="inFlag" placeholder="请输入疾病名称" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
         <div class="layui-inline">
            <label class="layui-form-label">偏离系数</label>
            <div class="layui-input-block">
              <input type="text" name="number" id="value" placeholder="请输入1到9的数字" autocomplete="off" maxlength="4" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" stylename="search"  lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
          </div>
    </div>
    </div>
     <div class="layui-card-body">
        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
     </div>
  
    </div>
  </div>
  
  <div class="layui-col-md7">
    <div class="layui-card">
          <div class="layui-card-body">
       <div id="main" class="echart" style="height:530px;"></div>
      </div>
    </div>
  </div>
  </div>
</div>
  <script
		src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/bigDataAntiFraud/extendedHospitalization.js"></script>
<script type="text/javascript">
    layui.config({
 		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
 	}).extend({
 		index: 'lib/index' //主入口模块
 	}).use(['index', 'table','laydate'], function(){
 		var $ = layui.$
 		,form = layui.form
 		,table = layui.table;
 		
	

 		      
 		
 		});
    </script>
</body>
</html>
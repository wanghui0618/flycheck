<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>活跃用户</title>
<style>
.pt {
	width: 300px;
}
</style>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
		   <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
			  <img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					活跃用户
  				</div>


			 <table id="violationDetail2" class="layui-hide" lay-filter="violationDetail2"></table>

		</div>
	</div>

		
		<script type="text/javascript" ">
	
		
		layui.config({
			    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
			  }).extend({
			    index: 'lib/index' //主入口模块
			  }).use(['index', 'table'], function(){
			    var $ = layui.$
			    ,form = layui.form
			    ,table = layui.table;
			
			    table.render({
			    	elem: '#violationDetail2'
			        ,url: $WEB_ROOT_PATH+'/dhccApi/user/user/userNumber'
			        ,cellMinWidth: 80
			        ,height: 480
			        ,cols: [[	
			         {type: 'numbers', title: '序号' }
					,{field:'lastday',align:'center', title: '最近一天' }
					,{field:'lastweek', align:'center',title: '最近一周' }
					,{field: 'lastmonth',align:'center', title: '最近一月'}
			            ]]
			          ,page: true
			          });
		
			  });
	</script>
</body>
</html>
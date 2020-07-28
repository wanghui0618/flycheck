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
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>超期住院</title>
<style>
</style>
</head>
<body>
	<div class="layui-fluid" style="overflow: hidden">

		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">结算单据号</label>
						<div class="layui-input-block">
							<input type="text" style="width: 120px" name="anesthesiaVo.hisid"
								placeholder="请输入结算单据号" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">医院名称</label>
						<div class="layui-input-block">
							<input type="text" style="width: 130px" name="anesthesiaVo.hospitalName" id="anesthesiaVo.hospitalName"
								placeholder="请输入医院名称" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin"
							stylename="search" lay-submit lay-filter="LAY-anesthesia-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>

					<div class="layui-card-body">
						<table id="userTable" class="layui-hide" lay-filter="userTable"></table>
					</div>

				</div>
			</div>

		</div>
	</div>
	<%-- <script
		src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script> --%>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/app/js/anesthesia/anesthesia.js"></script>
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
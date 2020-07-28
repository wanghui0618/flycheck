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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/app/js/set/info.js"></script>
<title>参保人医疗费用统计</title>
</head>

<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-inline"><label class="layui-form-label" style="width:200px">统筹区违规情况</label></div>
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
		<!-- 		<div class="layui-form-item">


					<div class="layui-inline" id="selProvince">
						<label class="layui-form-label">选择省份</label>
						<div class="layui-input-block">
							<input type="text" style="width: 150px;" name="diseasesFirst.id"
								placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">上报日期</label>
						<div class="layui-input-block">
							<input type="text" id="finaTime" name="balanceDate"
								placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							查询 <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>
				</div> -->
			</div>

			<div class="layui-card-body">
				<table id="userTable" class="layui-hide" lay-filter="userTable"></table>
				<script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>柱</a>
<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit1"><i class="layui-icon layui-icon-edit"></i>线</a>          
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>饼</a>
        </script>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/superviseRule/superviseRule.js"></script>
</body>
</html>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/login.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/tree/css/zTreeStyle.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
<title>分解住院情况筛查分析</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="test1"
								placeholder="限定时间(小于)" name="billdate">
						</div>
					</div>

					<div class="layui-inline layuiadmin-btn-useradmin">
						<button id="search" class="layui-btn" lay-submit=""
							lay-filter="search">查询</button>
					</div>
					<div class="layui-inline layuiadmin-btn-useradmin">
						<button id="search1" class="layui-btn" lay-filter="search1" onclick="week()">
							查询本周</button>
					</div>
					<div class="layui-inline layuiadmin-btn-useradmin">
						<button id="search2" class="layui-btn" lay-filter="search2" onclick="month()">
							查询本月</button>
					</div>
					<div class="layui-inline layuiadmin-btn-useradmin">
						<button id="search3" class="layui-btn" lay-filter="search3" onclick="year()">
							查询本年</button>
					</div>

				</div>
			</div>
			<div class="layui-card-body">
				<table id="TotalExpenses" class="layui-hide"
					lay-filter="TotalExpenses"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/fly/totalExpenses.js"></script>
</body>

</html>
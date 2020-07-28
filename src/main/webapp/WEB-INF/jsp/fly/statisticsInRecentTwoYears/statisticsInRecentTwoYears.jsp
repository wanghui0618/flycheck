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
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/UserHistoryin.css"
	media="all">
<title>近两年项目统计</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">查询列表</label>
						<div class="layui-input-inline">
							<select name="type" lay-verify="">
								<option value="1" selected="selected">近两年用药量</option>
								<option value="2">近两年诊疗项目统计</option>
								<option value="3">近两年超声检查收费次数统计</option>
								<option value="4">近两年细辛脑、头孢他啶和芎葡头糖的使用数量及金额</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">选择年份 </label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="year" name="year"
								readonly="true" placeholder="请选择年份">
						</div>
					</div>
					<div class="layui-inline ">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="sel">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
				</div>
			</div>
			<div class="layui-card-body">
				<table id="AA" class="layui-hide" lay-filter="AA">
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/fly/statisticsInRecentTwoYears/statisticsInRecentTwoYears.js"></script>
</body>
</html>
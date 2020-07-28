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
<title>按病种诊断结果统计</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline layui-col-md4">
						<div class="layui-inline">
							<label class="layui-form-label">诊断名称</label>
							<div class="layui-input-block">
								<input type="text" name="inDiagnosisName" placeholder="请输入诊断名称"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<button class="layui-btn" lay-submit lay-filter="zhenduanName">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询诊断名称
							</button>
						</div>
					</div>
					<div class="layui-inline">
						<div class="layui-inline">
							<label class="layui-form-label">选择年份 </label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" id="year" name="year"
									readonly="true" name="inhosDate" placeholder="请选择年份">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">选择月份</label>
							<div class="layui-input-inline">
								<input readonly="true" type="text" class="layui-input"
									id="month" name="month" placeholder="请选择月份">
							</div>
						</div>
						<div class="layui-inline layuiadmin-btn-useradmin">
							<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
								lay-filter="year">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询就诊信息
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="layui-card-body layui-inline layui-col-md4">
				<table id="admissionDiseaseName" class="layui-hide"
					lay-filter="admissionDiseaseName">
				</table>
			</div>
			<div class="layui-card-body layui-inline layui-col-md8">
				<table id="diseaseAndDiagnosticStatistics" class="layui-hide"
					lay-filter="diseaseAndDiagnosticStatistics"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/fly/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics.js"></script>
</body>
</html>
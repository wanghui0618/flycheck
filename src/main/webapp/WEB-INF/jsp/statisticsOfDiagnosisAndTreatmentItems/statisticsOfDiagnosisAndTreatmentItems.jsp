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
<title>近两年XX项目统计</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">

			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">选择年份 </label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="year" name="year"
								readonly="true" placeholder="请选择年份">
						</div>
					</div>
					<div class="layui-inline ">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="countDiagnosisAndTreatmentItems">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询诊疗统计
						</button>
					</div>
				</div>
			</div>
			<div class="layui-card-body">
				<table id="statisticsOfDiagnosisAndTreatmentItems" class="layui-hide"
					lay-filter="statisticsOfDiagnosisAndTreatmentItems">
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems.js"></script>
</body>
</html>
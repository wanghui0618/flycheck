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
<title>超平均住院人数分析</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" name="patientName" placeholder="请输入姓名" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">诊断类型</label>
						<div class="layui-input-block">
							<input type="text" name="admissionDiseaseName" placeholder="请输入诊断类型"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline layuiadmin-btn-useradmin">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="getCount">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询病例
						</button>
					</div>
				</div>
			</div>

			<div class="layui-card-body">
				<table id="emptyHangingBedAnalysis" class="layui-hide"
					lay-filter="emptyHangingBedAnalysis"></table>
				<!--<script type="text/html" id="table-useradmin-webuser">
               	<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
            </script>-->
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/analysisOfOverAverageInpatients/analysisOfOverAverageInpatients.js"></script>
</body>
</html>
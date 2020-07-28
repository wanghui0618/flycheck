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
<title>政策仿真</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					
					<div class="layui-inline pt">
						<label class="layui-form-label" style="width:45px;">主题</label>
						<div class="layui-input-inline">
							<input type="text"
								name="scheduledTask.taskKey" placeholder="请输入主题" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					
				
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<!-- <div class="layui-inline">
						<button id='weihu' data-type="weihu"
							class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-add">现行政策维护</button>
					</div> -->
					<div class="layui-inline">
						<button id='add' data-type="add"
							class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>模拟政策新增</button>
					</div>
				</div>
			</div>

			<div class="layui-card-body">

				<table id="policyTable" class="layui-hide"
					lay-filter="policyTable"></table>
				<script type="text/html" id="table-useradmin-webuser">
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
					<a class="layui-btn layui-btn-danger layui-btn-normal layui-btn-xs" lay-event="addRs"><i class="layui-icon layui-icon-survey"></i>生成报告</a>
					<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="lookRs"><i class="layui-icon layui-icon-tabs"></i>查看报告</a>
        		</script>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/assistDecision/policyList.js"></script>
</body>
</html>
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
<title>统计护理违规</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<div
							class="layui-form layui-card-header layuiadmin-card-header-auto layui-inline">
							<div class="layui-form-item pt">
								<div class="layui-inline pt">
									<div class="layui-inline">限制护理等级</div>
									<div class="layui-inline">
										<select name="limitgrade" lay-filter="myselect">
											<option value=""></option>
											<option value="一级护理">一级护理</option>
											<option value="二级护理">二级护理</option>
											<option value="三级护理">三级护理</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-inline">
							<button class="layui-btn" lay-submit lay-filter="search"
								id="search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-card-body">
				<table id="NursingViolation" class="layui-hide"
					lay-filter="NursingViolation"></table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/nursingViolation/nursingViolation.js"></script>
</html>
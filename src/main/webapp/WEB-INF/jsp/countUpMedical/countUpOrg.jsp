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

<title>门诊类型统计</title>

</head>
<body >

	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline ">
					
					<label class="layui-form-label">机构代码</label>
						<div class="layui-input-inline">
							<input type="text" id="orgCode""
								name="countDataVo.orgCode" lay-verify="required|number"
								placeholder="请输入机构代码" autocomplete="off" class="layui-input">
						</div>
						<!-- 	<label class="layui-form-label">机构名称</label>
						<div class="layui-input-inline">
							<input type="text" id="orgName""
								name="countDataVo.orgName" lay-verify="required"
								placeholder="请输入机构名称" autocomplete="off" class="layui-input">
						</div> -->
						<!-- <label class="layui-form-label">机构名称</label>
						<div class="layui-input-block " style="width: 150px;">
							<select name="countDataVo.orgCode" id="orgCode"
								lay-filter="orgCode">
								<option value="" disabled selected style='display: none;'>请选择机构名</option>
							</select>
						</div> -->
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">时间段选择</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input type="text" id="startTime" name="countDataVo.startTime"
								lay-verify="required" placeholder="请选择查询时间段" autocomplete="off"
								class="layui-input">
						</div>
					</div>

					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>

					</div>

				</div>

			</div>

			<div class="layui-card-body">

				<table id="blackList" class="layui-hide" lay-filter="blackList"></table>

			</div>
		</div>
	</div>


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/countUpMedical/countUpOrg.js"></script> 
</body>
</html>
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
<title>诚信管理统计</title>
<style>
.echart {
	width: 700px;
	height: 405px;
}
</style>
</head>
<body style="overflow:hidden">
	<div class="layui-fluid" style="overflow:hidden">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card" style="height:50px;">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto">
							<div class="layui-inline ">
								<label class="layui-form-label " style="width:45px">类型</label>
								<div class="layui-input-inline ">
									<select name="blackListVo.type" id="type"
										lay-filter="choose">
										<option value="2">医院</option>
										<option value="1">参保人</option>
										<option value="3">药店</option>
										<option value="4">医生</option>
									</select>
								</div>
							</div>
			 				<div class="layui-inline">
								<button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="search" 
									lay-filter="LAY-user-front-search">
									<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
							</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md6" style="width: 45%;">
				<div class="layui-card">
					<div class="layui-card-body">
						<table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
					</div>
				</div>
			</div>
			<div class="layui-col-md6" style="width: 55%; height:542px">
				<div class="layui-card">
					<div class="layui-card-header" style="height: 40px"
						id="layui-card-header-s" value=""></div>
					<div class="layui-card-body">
						<div id="main" class="echart"></div>
						<div id="msg" ></div>
					</div>
				</div>
			</div>
		</div>
	</div>
 <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/countUpMedical/countUpBlackList.js"></script> 
</body>
</html>
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
<title>住院信息异常筛查分析</title>
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
									<div class="layui-inline">限制住院天数</div>
									<div class="layui-inline">
										<input type="text" name="limitdays" placeholder="请输入住院天数（等于）"
											autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
						</div>
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
			<div class="layui-card-body layui-inline layui-col-md4">
				<table id="admissionDiseaseName" class="layui-hide"
					lay-filter="admissionDiseaseName">
				</table>
			</div>

			<div class="layui-card-body layui-inline layui-col-md8">
				<table id="HospitalizationConditions" class="layui-hide"
					lay-filter="HospitalizationConditions"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/fly/hospitalizationConditions.js"></script>
</body>
</html>
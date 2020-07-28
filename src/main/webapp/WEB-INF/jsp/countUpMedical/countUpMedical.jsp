<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
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
<title>上传数据统计</title>
</head>
<body style="overflow:hidden;">
	<div class="layui-fluid" style="overflow:hidden;">
		<div class="layui-row layui-col-space15" style="padding: 5px;">
			<div class="layui-col-md6"  style="width: 30% ;">
				<div class="layui-card">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto">
						<div class="layui-form-item pt">
							<div class="layui-inline ">
								<label class="layui-form-label ">年度选择</label>
								<div class="layui-input-inline" style="width: 180px;">
									<input id="createTime" name="inFlag" lay-filter="createTime"
										type="text" class="layui-input" placeholder="yyyy">
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

					<div class="layui-card-body">
						<table id="dataTable" class="layui-hide" lay-filter="dataTable"  ></table>
					</div>
				</div>
			</div>
			<div class="layui-col-md6" style="width: 70%;">
				<div class="layui-card">
					<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  						<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  						<span id="yearHtml"></span>
  				  	</div>
				<div class="layui-card-body">
					<div id="main" style="margin-top:-10px;" class="echart"></div>
				</div>
				</div>
			</div>
		</div>

	</div>
	</div>
 <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/countUpMedical/countUpMedical.js"></script> 
</body>
</html>
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
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<title>主页面</title>
</head>
<style>
.layui-table thead tr{
height: 30px;
}
.echart {
	width: inherit;
	height: inherit;
}
</style>
<body>
<!-- style="overflow-y:hidden;" -->
	<div class="layui-fluid">
			<div class="layui-row layui-col-space15">
			<div class="layui-col-md12"  style="height:50px;">
				<div class="layui-card"
					style="min-height:47px; border-width: 1px; border-style: solid; border-color: #e6e6e6;">
					<div class="layui-from">
						<div class="layui-card-header" style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
							<div class="layui-inline ">
								<label class="layui-form-label ">年度选择</label>
								<div class="layui-input-inline" style="width: 180px;">
									<input id="firstTime"  lay-filter="firstTime"
										type="text" class="layui-input" placeholder="yyyy">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md4" style="height: 325px;">
				<div class="layui-card" style="min-height: 200px; border-width: 1px; border-style: solid; border-color: #e6e6e6;">
					<div class="layui-from">
						<div class="layui-card-header" style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
							违规金额占比
						</div>
					    <div class="layui-card-body" style="height: 300px;">
				        <div id="amount" class="echart"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4" style="height: 325px;">
				<div class="layui-card" style="min-height: 200px; border-width: 1px; border-style: solid; border-color: #e6e6e6;">
					<div class="layui-from">
					<div class="layui-card-header" style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
							违规病例数占比
						</div>
						<div class="layui-card-body" style="height: 300px;">
				        <div id="case" class="echart"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4" style="height: 325px;">
				<div class="layui-card" style="min-height: 200px; border-width: 1px; border-style: solid; border-color: #e6e6e6;">
					<div class="layui-from">
					<div class="layui-card-header" style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
							违规明细占比
						</div>
						<div class="layui-card-body" style="height: 300px;">
				        <div id="detail" class="echart"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-row layui-col-space15"  style="margin-top:35px;">
				<div class="layui-col-md6">
					<div class="layui-card"
						style="min-height: 363px; border-width: 1px; border-style: solid; border-color: #e6e6e6;">
						<div class="layui-card-header" 	style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
							统筹区违规金额
						</div>
						<div class="layui-card-body" style="height: 400px;">
				        <div id="region" class="echart"></div>
						</div>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-card" style="min-height: 363px; border-width: 1px; border-style: solid; border-color: #e6e6e6;">
						<div class="layui-card-header" style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
							医院违规金额
						</div>
						<div class="layui-card-body" style="height: 400px;">
						<div id="hospital" class="echart"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
 <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/countUpViolation/countUpViolation.js"></script> 
</body>
</html>
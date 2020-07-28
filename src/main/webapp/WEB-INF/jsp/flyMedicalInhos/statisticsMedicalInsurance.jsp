<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="renderer" content="webkit">
		
		<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
		
		<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
		
		<title>统计医保使用情况</title>
		
	</head>
	
	<body>
	
		<div class="layui-fluid layui-col-md6">
			<div class="layui-card">
				<div class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-form-item">
						<label>年度使用情况</label>
					</div>
				</div>
				<div class="layui-card-body">
					<table id="firstTable" class="layui-hide" lay-filter="firstTable">
						<tbody>
							<script class="serialNumber" type="text/html">
								{{d.LAY_TABLE_INDEX+1}}
							</script>
						</tbody>
					</table> 
				</div>
			</div>
		</div>
		<div class="layui-fluid layui-col-md6">
			<div class="layui-card">
				<div class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-form-item">
						<label>月度使用情况</label>
						<div class="layui-inline">
							<div class="layui-input-inline">
								<input id="yearPlug" type="text" name="year" style=""  class="layui-input" placeholder="yyyy" readonly="true">
							</div>
						</div>
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div> 
				</div>
				<div class="layui-card-body">
					<table id="secondTable" class="layui-hide" lay-filter="secondTable">
						<tbody>
							<script class="serialNumber" type="text/html">
								{{d.LAY_TABLE_INDEX+1}}
							</script>
						</tbody>
					</table> 
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/flyMedicalInhos/statisticsMedicalInsurance.js"></script>
		
	</body>
	
</html>
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
		
		<title>近两年细辛脑、头孢他啶和芎葡头糖的使用数量及金额</title>
	</head>
	<body>
		<div class="layui-fluid layui-col-md4">
			<div class="layui-card">
				<div class="layui-card-body">
					<table id="userTable" class="layui-hide" lay-filter="userTable">
						<tbody>
							<script type="text/html" id="xuhao">
								{{d.LAY_TABLE_INDEX+1}}
							</script>
						</tbody>
					</table> 
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/flyDetailInhos/statisticsDrugs.js"></script>
	</body>
</html>
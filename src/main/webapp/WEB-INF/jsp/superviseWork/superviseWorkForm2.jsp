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

<title>违规类型明细统计</title>
<style>
.pt {
	width: 300px;
}
</style>
</head>
<body>
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header"
					style="border-bottom: 1px solid #f6f6f6;">
					<img style="margin-top: -2px; padding-right: 8px;"
						src="<%=request.getContextPath()%>/images/auditing/mark.png" />
					接入省份情况
				</div>
				<div class="layui-card-body">
					<div id="map" style="height: 300px;"></div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/js/echarts_home/echarts.min.js"></script>
	<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('map'));
	option = {
		    xAxis: {
		        type: 'category',
		        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: [820, 932, 901, 934, 1290, 1330, 1320],
		        type: 'line'
		    }]
		};

	myChart.setOption(option);
	
	
	
	
	</script>

	<script type="text/javascript">
		
	</script>
</body>
</html>
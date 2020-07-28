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
		    title: {
		        text: '世界人口总量',
		        subtext: '数据来自网络'
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['2011年', '2012年']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    yAxis: {
		        type: 'category',
		        data: ['巴西','印尼','美国','印度','中国','世界人口(万)']
		    },
		    series: [
		        {
		            name: '2011年',
		            type: 'bar',
		            data: [18203, 23489, 29034, 104970, 131744, 630230]
		        },
		        {
		            name: '2012年',
		            type: 'bar',
		            data: [19325, 23438, 31000, 121594, 134141, 681807]
		        }
		    ]
		};
	
	myChart.setOption(option);
	
	
	
	
	</script>

	<script type="text/javascript">
		
	</script>
</body>
</html>
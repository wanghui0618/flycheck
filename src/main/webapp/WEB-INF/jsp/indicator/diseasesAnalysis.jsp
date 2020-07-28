<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<title>病种分析</title>
<style type="text/css">
html{
background-color: white;
}
	div[lay-id=diseasesAnalysisTable] .layui-table-body{
		height: 280px;
	}
</style>
</head>
<body>
<div class="layui-row layui-col-space15">

	<div class="layui-col-md12">

		<div class="layui-col-md6" style="height: 420px">
			<div class="layui-card">
				<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
					<img style="margin-top:-2px;padding-right: 8px;"
						 src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
					住院病种费用统计
				</div>
				<div class="layui-card-body">
					<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
						<ul class="layui-tab-title"
							style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
							<li class="layui-this"
								style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
								Top10
							</li>
							<img style="height: 25px;width: 2px;"
								 src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
							<li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
								全部
							</li>
						</ul>
						<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
							<div class="layui-tab-item layui-show" style="height:250px;">
								<div id="main" style="height: 250px;width:520px;margin-left:25px;"></div>
							</div>
							<div class="layui-tab-item ">
								<div class="layui-card" style="padding-left: 10px;">
									<table id="diseasesAnalysisTable" class="layui-hide"
										   lay-filter="hospitalViolationTable" style="height: 290px;"></table>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-col-md6" style="height: 410px">
			<div class="layui-card">
				<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
					<img style="margin-top:-2px;padding-right: 8px;"
						 src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
					门诊病种费用统计
				</div>
				<div class="layui-card-body">
					<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
						<ul class="layui-tab-title"
							style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
							<li class="layui-this"
								style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
								Top10
							</li>
							<img style="height: 25px;width: 2px;"
								 src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
							<li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
								全部
							</li>
						</ul>
						<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
							<div class="layui-tab-item layui-show" style="height:250px;">
								<div id="main1" style="height: 250px;width:520px;margin-left:25px;"></div>
							</div>
							<div class="layui-tab-item ">
								<div class="layui-card" style="padding-left: 10px;">
									<table id="diseasesAnalysisTable1" class="layui-hide"
										   lay-filter="hospitalViolationTable" style="height: 290px;"></table>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="layui-col-md12">
		<div class="layui-col-md6" style="padding-left: 5px;padding-right: 5px;">
			<div class="layui-card">
				<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
					<h3>地区病种费用top10</h3>
				</div>
				<div class="layui-card-body">
					<table id="addressTable" table class="layui-table"></table>
				</div>
			</div>
		</div>
		<div class="layui-col-md6" style="padding-left: 5px;padding-right: 5px;">
			<div class="layui-card">
				<%--<div style="width: 46%;float:left;background-color: white;margin-left: 4%">--%>
					<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">

						<h3>医院病种费用 top10</h3>
					</div>
					<div class="layui-card-body">
						<table id="doctorTable" table class="layui-table"></table>
					</div>
			</div>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/app/js/indicator/diseasesAnalysis.js"></script>
<script type="text/javascript">
require.config({
    paths: {
        echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
    }
});

// 使用
require(
 [
     'echarts',
     'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
 ],
 function (ec) {
     // 基于准备好的dom，初始化echarts图表
     var myChart = ec.init(document.getElementById('main'));
     
     var option = {
         tooltip: {
             show: true
         },
         legend: {
             data:['病种费用']
         },
         xAxis : [
             {
				 type : 'category',
				 axisLabel: {
					 interval:0,
					 rotate:20,
					 textStyle: {
						 fontSize : 10,
					 }
				 } ,
                 data : []
             }
         ],
         yAxis : [
             {
                 type : 'value'
             }
         ],
         series : [
             {
                 "name":"病种费用",
                 "type":"bar",
                 "data":[],
                 itemStyle:{
                     normal:{
                         color:'#EE7621'
                     }
                 }
             }
         ]
     };
		
     // 为echarts对象加载数据 
       $.ajax({
	         url:$WEB_ROOT_PATH+"/dhccApi/indicator/diseasesAnalysis/listVo?diseasesAnalysis.orgName=hosTop",
	          // type : "get",
	         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
	         dataType : "json",
		      // where:{"pageModel.pageNo":"0"},
	         success : function(result) {
	         	console.log(result)
				 for(var i=0;i<result.data.length;i++){
					 option.series[0].data.push(result.data[i].totalCost);
					 option.xAxis[0].data.push(result.data[i].orgName);
				 }
	        	 // var hvdata=result.data;
	        	 // if (hvdata != null && hvdata.length > 0) {
	        		//  var k;
	        		//  if(hvdata.length>10){
	        		// 	 k=10;
	        		//  }else{
	        		// 	 k=hvdata.length;
	        		//  }
	        		//  for(var i=0;i<k;i++){
		         //           option.series[0].data.push(hvdata[i].averagePrice);
		         //           option.xAxis[0].data.push(hvdata[i].hospitalName);
		         //     }
	        		 myChart.setOption(option); 
	        	//  }
	         }
       });
 }
);
    </script>

<script type="text/javascript">
	require.config({
		paths: {
			echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
		}
	});

	// 使用
	require(
			[
				'echarts',
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			],
			function (ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main1'));

				var option = {
					tooltip: {
						show: true
					},
					legend: {
						data:['病种费用']
					},
					xAxis : [
						{
							type : 'category',
							axisLabel: {
								interval:0,
								rotate:20,
								textStyle: {
									fontSize : 10,
								}
							} ,
							data : []
						}
					],
					yAxis : [
						{
							type : 'value'
						}
					],
					series : [
						{
							"name":"病种费用",
							"type":"bar",
							"data":[],
							itemStyle:{
								normal:{
									color:'#EE7621'
								}
							}
						}
					]
				};

				// 为echarts对象加载数据
				$.ajax({
					url:$WEB_ROOT_PATH+"/dhccApi/indicator/diseasesAnalysis/listVo?diseasesAnalysis.orgName=outTop",
					// type : "get",
					async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
					dataType : "json",
					// where:{"pageModel.pageNo":"0"},
					success : function(result) {
						console.log(result)
						for(var i=0;i<result.data.length;i++){
							option.series[0].data.push(result.data[i].totalCost);
							option.xAxis[0].data.push(result.data[i].orgName);
						}
						// var hvdata=result.data;
						// if (hvdata != null && hvdata.length > 0) {
						//  var k;
						//  if(hvdata.length>10){
						// 	 k=10;
						//  }else{
						// 	 k=hvdata.length;
						//  }
						//  for(var i=0;i<k;i++){
						//           option.series[0].data.push(hvdata[i].averagePrice);
						//           option.xAxis[0].data.push(hvdata[i].hospitalName);
						//     }
						myChart.setOption(option);
						//  }
					}
				});
			}
	);
</script>



    <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console']);
  </script>
</body>
</html>
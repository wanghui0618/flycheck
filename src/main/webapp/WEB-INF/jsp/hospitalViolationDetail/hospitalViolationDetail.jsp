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
<%@include file="/WEB-INF/jsp/common/easyui.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<title>违规明细/违规医院</title>
<style>
.layui-table-page {
	height: 38px;
}
</style>
</head>
<body style="overflow: hidden;">
	<div>
		<!-- 隐藏a标签，用于跳转模拟 -->
		<div style="display: none;" id="tz-hide" href="javascript:;"
			lay-href="<%=request.getContextPath()%>/medical/medicaltab-jpp-zs"></div>
		<div class="layui-row">
			<div class="layui-col-md6" id="parent">
				<div class="grid-demo grid-demo-bg1" id="child">
					<div class="layui-fluid">
						<div class="layui-card">
							<div
								class="layui-form layui-card-header layuiadmin-card-header-auto">
								<div class="layui-form-item">

									<div class="layui-inline">
										<label class="layui-form-label">项目名称</label>
										<div class="layui-input-inline">
											<input type="text" name="hospitalViolationDetail.itemName"
												placeholder="" autocomplete="off" class="layui-input">
										</div>
									</div>

									<div class="layui-inline">
										<label class="layui-form-label">审核状态</label>
										<div class="layui-input-inline" onblur="lol()">
											<select name="status" lay-search="" id='stat'>
												<option></option>
												<option value="0" selected="selected">机审</option>
												<option value="1">终审</option>
											</select>
										</div>
									</div>

									<div class="layui-inline">
										<label class="layui-form-label" >选择年份</label>
										<div class="layui-input-inline">

											<input id="createTime" name="createTime"
												lay-filter="createTime" type="text" class="layui-input"
												placeholder="yyyy">
										</div>
									</div>

									<div class="layui-inline">
										<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
											lay-filter="LAY-user-front-search1">
											<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
										</button>
									</div>
									<div class="layui-inline">
										<button id="violation-dc"
											class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
											lay-submit lay-filter="LAY-user-front-export">
											<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
										</button>
									</div>
								</div>
							</div>

							<div class="layui-card-body">

								<table id="hospitalViolationTable1" class="layui-hide"
									lay-filter="hospitalViolationTable1"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md6">
				<div class="grid-demo">
					<div class="layui-fluid">
						<div class="layui-card" style="width: 101%; margin-left: -10px;">
							<div class="layui-card-header" style="height: 20px">
								医院违规TOP10
								<button id="yiyuan" href="javascript:;"
									style="margin-top: 10px; margin-left: 30px; z-index: 9999; position: absolute;"
									class="layui-btn  layui-btn-xs">医院</button>
							</div>
							<div class="layui-card-body">
								<div id="main" style="margin-left: -10px"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<a id='yl' style="display: none" href='javascript:;'
		lay-href='<%=request.getContextPath()%>/medical/medical'
		lay-tips='病例审核'>病例审核</a>
	<!-- ECharts单文件引入 -->
	<script
		src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/hospitalViolationDetail/hospitalViolationDetail.js"></script>
	<script type="text/javascript">
		document.getElementById("main").style.height = document.documentElement.clientHeight
				- 48 + "px";
		// 路径配置
		require.config({
			paths : {
				echarts : $WEB_ROOT_PATH
						+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
			}
		});

		// 使用
		/*  require(
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
		                 data:['医院违规记录数']
		             },
		             xAxis : [
		                 {
		                     type : 'category',
		                     data : [],
		                     axisLabel : {//坐标轴刻度标签的相关设置。
		                         interval:0,
		                         rotate:"25"
		                     }
		                 }
		             ],
		             yAxis : [
		                 {
		                     type : 'value'
		                 }
		                
		             ],
		             series : [
		                 {
		                     "name":"违规数量",
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
				         url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/listVo",
				         type : "post",		
				         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
				         dataType : "json",	
				         success : function(result) {
				        	 var hvdata=result.data;
				        	 if (hvdata != null && hvdata.length > 0) {
				        		 var k;
				        		 if(hvdata.length>10){
				        			 k=10;
				        		 }else{
				        			 k=hvdata.length;
				        		 }
				        		 for(var i=0;i<k;i++){ 
					                   option.series[0].data.push(hvdata[i].vioCount);
					                   option.xAxis[0].data.push(hvdata[i].orgName+"\n"+hvdata[i].cityName);
					             } 
				        		 myChart.setOption(option); 
				        	 }
				         }
		           });
		         
		         
		         
		         
		         
		         
		     }
		 ); */
	</script>
	<script>
		function lol() {
			var h = document.getElementById("stat").value;
			status = h;
		}
	</script>
	<script type="text/javascript">
		function reloadTable(orgName, status,createTime) {
			require(
					[ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
					],
					function(echarts) {
						echarts.init(document.getElementById('main')).dispose();//销毁前一个实例
						memoBar = echarts.init(document.getElementById('main'));//构建下一个实例

						var option = {
							tooltip : {
								show : true
							},
							padding : [ 0, 0, 10, 10 ], // 位置
							legend : {
								padding : 10, // [5, 10, 15, 20]
								itemGap : 20,
								left : 'right',
								data : [ '违规类型分布TOP10统计' ]
							},
							xAxis : [ {
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval : 0,
									rotate : "15"
								}
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							series : [ {

								"name" : "违规数量",
								"type" : "bar",
								barWidth : 30,
								"data" : [],
								itemStyle : {
									normal : {
										color : '#419bf9'
									}
								}
							} ]
						};
						// 为echarts对象加载数据 
						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/hospitalviolationdetail/hospitalViolationDetail/echartList?status="
											+ status,
									type : "post",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									data : {
										"itemName" : orgName,"createTime":createTime
									},
									success : function(result) {
										var hvdata = result;
										if (hvdata != null && hvdata.length > 0) {
											var k;
											if (hvdata.length > 10) {
												k = 10;
											} else {
												k = hvdata.length;
											}
											for (var i = 0; i < k; i++) {
												option.series[0].data
														.push(hvdata[i].shu);
												option.xAxis[0].data
														.push(hvdata[i].medicalInsName);
											}
											memoBar.setOption(option);
										}
									}
								});
						//下面是需要添加的方法内容  
						//点击柱状图跳转相应页面的功能，其中param.name参数为横坐标的值   
					/* 	var ecConfig = require('echarts/config');

						memoBar.on(ecConfig.EVENT.CLICK, eConsoleSpread); */
					});

		};
		function eConsoleSpread(param) {
			if (typeof param.seriesIndex != 'undefined') {
				var str = param.name;

				str = encodeURI(encodeURI(str));
				son = encodeURI(encodeURI(on));
				scn = encodeURI(encodeURI(cn));
				//默认为初审
				if (!status_qj) {
					status_qj = '0';
				}

				var tit = status_qj == '0' ? "事后病例初审" : "事后病历终审";
				var cs = "/piccbid/medical/medical/jpp?orgName=" + son
						+ "&cityName=" + scn + "&typeNameTz=" + str + "&type=3";
				var zs = "/piccbid/medical/medical/jpp-zs?orgName=" + son
						+ "&cityName=" + scn + "&typeNameTz=" + str + "&type=3";

				var urlGo = status_qj == '0' ? cs : zs;

				$("#tz-hide").attr('lay-href', urlGo);
				$("#tz-hide").html(tit);
				$('#tz-hide').trigger("click");
			}
		}
	</script>
</body>
</html>
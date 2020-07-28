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
<title>主页面</title>
<style>
.layui-table td, .layui-table th {
	position: relative;
	padding: 9px 15px;
	min-height: 20px;
	line-height: 24px;
	font-size: 14px;
}

.layui-fluid {
	overflow: hidden;
}

.daiban li {
	height: 65px;
	width: 180px;
	border-radius: 3px;
	color: #fff;
	margin: 0px 6px 13px 6px;
}

.xinzeng {
	background-image: linear-gradient(-225deg, #27AEFF 0%, #2284FF 100%);
}

.youxiao {
	background-image: linear-gradient(-225deg, #F48A56 0%, #EA5C44 100%);
}

.wuxiao {
	background-image: linear-gradient(-225deg, #F5B00C 0%, #EF8D07 100%);
}

.zhuyuan {
	background-image: linear-gradient(-225deg, #00D888 0%, #00B365 100%);
}

.menzhen {
	background-image: linear-gradient(-225deg, #B28EF1 0%, #806BE4 100%);
}

.mente {
	background-image: linear-gradient(-225deg, #2DE0BB 0%, #03BCC7 100%);
}
</style>
</head>
<body>
	<div class="layui-fluid" style="overflow: hidden">
		<!-- <div class="layui-row layui-col-space15" style="overflow: hidden"> -->
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card" style="line-height: 40px;">
				<div style="float:left;margin-left:10px;margin-right:10px;">数据审核类型：<select id="findYearBoss" name="year"
							onchange="getValueBoss()">
							<option value="机审" selected="selected">机审</option>
							<option value="终审">终审</option>
							</select></div>
				<div id="JGGZLALL"></div>
					<%-- <div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						监管工作量统计
					</div> --%>
					<div class="layui-card-body">
						<!-- <div id="map" style="height: 400px;padding-right: 500px"></div>
						
						<div
							style="position: absolute; width: 280px; height: 500px; float: left; top: 20px; left: 1060px;">
							<table class="layui-table" style="border: 1px solid;">
								<colgroup>
									<col width="400px">
									<col width="400px">
								</colgroup>
								<tbody>
									<tr style='background: #F5FCF9'>
										<td>规则检出人次</td>
										<td id="rnumber">1亿</td>
									</tr>
									<tr style='background: #F5FCF9'>
										<td>审核人次</td>
										<td id="cnumber">1000万</td>
									</tr>
									<tr style='background: #F5FCF9'>
										<td>确认违规人次</td>

										<td id="surenumber">200万</td>
									</tr>
									<tr style='background: #F5FCF9'>
										<td>确认违规金额</td>

										<td id="suremoney">1028万</td>
									</tr>
									<tr style='background: #F5FCF9'>
										<td>排除违规人次</td>
										<td id="enumber">800万</td>
									</tr>
									<tr style='background: #F5FCF9'>
										<td>排除违规金额</td>
										<td id="emoney">2028万</td>
									</tr>
									<tr style='background: #F5FCF9'>
										<td>阳性率</td>
										<td id="rate">40%</td>
									</tr>
								</tbody>
							</table>
						</div> -->
					</div>
				</div>
			</div>
		</div>
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						统筹区审核工作量趋势图
						 <select id="findYear1" name="year"
							onchange="getValue1()">
							<option value="机审" selected="selected">机审</option>
							<option value="终审">终审</option>
							</select>
						<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
					</div>
					<div class="layui-card-body">
					<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title"
								style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
								<li class="layui-this"
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none" onclick="changeCardTop1()">Top10</li>
								<img style="height: 25px; width: 2px;"
									src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
								<li
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none" onclick="changeCardTop2()">全部</li>
							</ul>
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 295px;">

										<div id="line1" width='700px ' style="height: 300px;padding-left: 50px"></div>
								</div>
								<div class="layui-tab-item ">
									<div class="layui-card">
										<div
											class="layui-form layui-card-header layuiadmin-card-header-auto"
											style="padding-left: 5px;">
											<div class="layui-form-item">
												<!-- <div class="layui-inline pt">
										<label class="layui-form-label ">城市名称</label>
										
										<div class="layui-input-block " style="width: 120px;">
											<select name="hospitalViolation.cityName" id="city" >
												<option value="" disabled selected style='display:none;'>请选择城市</option>
											</select>
										</div>
									</div> -->

												<div class="layui-inline pt" style="margin-right: 4px;">
													<label class="layui-form-label">统筹区名称</label>
													<div class="layui-input-block">
														<!-- <input type="text" style="width: 120px;"
												name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
												class="layui-input"> -->
														<select id="zyOrgName" style="width: 150px;"
															name="superviseWorkVo.area" lay-verify=""
															lay-search=" ">
															<option value="" disabled selected style='display: none;'>请选择统筹区</option>
														</select>
													</div>
												</div>
												<button
													style="display: inline-block; position: absolute; margin-top: 8px; padding: 0px 7px"
													class="layui-btn layui-btn-sm layuiadmin-btn-useradmin"
													lay-submit lay-filter="LAY-user-front-search">
													<i
														class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
												</button>

											</div>
										</div>
										<table id="hospitalViolationTable" class="layui-hide"
											lay-filter="hospitalViolationTable"></table>
									</div>
								</div>

							</div>
						</div>
					
					
					</div>
				</div>
			</div>
			<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						统筹区审核情况 <select id="findYear2" name="year"
							onchange="getValue2()">
							<option value="机审" selected="selected">机审</option>
							<option value="终审">终审</option>
							</select>
					</div>
					<div class="layui-card-body">
					
						<div id="line2" style="height: 318px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="layui-col-md12 layui-col-xs12"
		style="padding: 6px 0px 5px 0px;">
		<div class="layui-card">
			<div class="layui-col-md6">

				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						医院公示反馈工作量排名 <a style="float: right;" lay-urlname="医院公示反馈工作量排名"
							href="javascript:;"
							lay-href="<%=request.getContextPath()%>/superviseWork/superviseWork/getAllSureInfo">查看全部<img
							style="width: 14px; margin-top: -2px; padding-right: 2px; margin-left: 6px;"
							src="<%=request.getContextPath()%>/images/auditing/more.png" /></a>
					</div>
					<div class="layui-card-body layui-text">
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody id="sureAllInfo">
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->

							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="layui-col-md6">

				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						统筹区工作量排名 <a style="float: right;" lay-urlname="统筹区工作量排名"
							href="javascript:;"
							lay-href="<%=request.getContextPath()%>/superviseWork/superviseWorkAllArea">查看全部<img
							style="width: 14px; margin-top: -2px; padding-right: 2px; margin-left: 6px;"
							src="<%=request.getContextPath()%>/images/auditing/more.png" /></a>
					</div>
					<div class="layui-card-body layui-text">
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody id="areaAllInfo">
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
	/* function mapData(name,value){
		var o = new Object();
		o.name=name;
		o.value=value;
		return o;
	}
	var dataArr = new Array();
	$.ajax({
		url: $WEB_ROOT_PATH+"/dhccApi/superviseWork/superviseWork/getMapData",
		type:"get",
		success:function(data){
			console.log(data);
			for(var index in data){
				var area = data[index].pname;
				var length = area.length;
				var name = area.substring(0,length-1);
				var value = parseInt(data[index].pnumber);
				console.log(name);
				console.log(value);
				dataArr[index] = new mapData(name,value);
			}
			console.log(dataArr);
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require([ 'echarts', 'echarts/chart/map' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('map'));

				myChart.setOption(option = {
					backgroundColor : '#FFFFFF',
					title : {
						text : '全国审核工作量情况分布图',
						subtext : '',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
					},
					toolbox : {
						show : true,
						orient : 'vertical',
						left : 'right',
						top : 'center',
					},/* 
					                     dataRange: {
					                         min: 0,
					                         max: 100,
					                         text:['100','1'],           // 文本，默认为数值文本
					                         splitNumber:0
					                     },
					                     visualMap: {
					                    	 show : true,  
					                         x: 'left',  
					                         y: 'center',  
					                     }, */
					                     /*series : [ {
						name : '',
						type : 'map',
						mapType : 'china', // 自定义扩展图表类型
						roam : false,
						itemStyle : {
							normal : {
								label : {
									show : false
								},
								color : function(params) {
									if (params.data && params.data.value > 0
											&& params.data.value <= 20) {
										return '#4DD8BB'/* 4DD8BB  */
										/*} else if (params.data
											&& params.data.value > 20
											&& params.data.value <= 40) {
										return "#EBA639" /* 63D469 */
									/*} else if (params.data
											&& params.data.value > 40
											&& params.data.value <= 60) {
										return "#EBA639"/* 3FA2FF */
									/*} else if (params.data
											&& params.data.value > 60
											&& params.data.value <= 75) {
										return "#7288E1"/* A585EE */
									/*} else if (params.data
											&& params.data.value > 75
											&& params.data.value <= 90) {
										return "#63D469"/* EBA639 */
									/*} else if (params.data
											&& params.data.value > 90
											&& params.data.value < 100) {
										return "#3FA2FF"/* EBA639 */
									/*}else if (params.data
											&& params.data.value > 100
											&& params.data.value < 300) {
										return "#3FA2FF"/* EBA639 */
									/*}else if (params.data
											&& params.data.value > 300
											&& params.data.value < 400) {
										return "#3FA2FF"/* EBA639 */
									/*}
									else if (params.data
											&& params.data.value > 400
											&& params.data.value < 700) {
										return "#3FA2FF"/* EBA639 */
										/*}

								}
							},
							emphasis : {
								label : {
									show : true
								}
							}

						},
						data : dataArr,
					} ]
				});
				myChart.on('click', function(params) {
					var joincity = params.name;
					var uploadnumber = params.value;
					$("#joincity").html(joincity);
					$("#uploadnumber").html(uploadnumber);
				})
			});
		},
		error:function(data){
			
		}
	}) */
	</script>
	<script type="text/javascript">
	var dataAxis = new Array();
	var strNumber = new Array();
	var sureNumber = new Array();
	var eNumber = new Array();
	$.ajax({
		url:$WEB_ROOT_PATH
		+ "/dhccApi/superviseWork/superviseWork/getAllInfoByAreaJS",
		type:"get",
		success:function(data){
			for(var index in data){
				if(index == 10) break;
			
				dataAxis[index] = data[index].area==null?"未定义":data[index].area;
				if(data[index.area == null]) continue;
				strNumber[index] = parseInt(data[index].cnumber);
				sureNumber[index]= parseInt(data[index].surenumber==null?"0":data[index].surenumber);
				eNumber[index]=parseInt(data[index].enumber==null?"0":data[index].enumber);
			}
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('line1'));

				var option = {
					title : {
						text : ''
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : [ '审核人次','标注违规人次','标注正常人次' ]
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						containLabel : true
					},
					toolbox : {
						feature : {
							saveAsImage : {}
						}
					},
					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : dataAxis ,  axisLabel: {
                            interval:0,
                            rotate:16
                         } 
					},
					yAxis : {
						type : 'value'
					},
					series : [ /* {
						name : '检出人次',
						type : 'line',
						stack : '总量',
						data : [ 12000, 13200, 10100, 134000 ]
					}, */ {
						name : '审核人次',
						type : 'line',
						
						data : strNumber
					},
					{
						name : '标注违规人次',
						type : 'line',
						
						data : sureNumber
					},
					{
						name : '标注正常人次',
						type : 'line',
						
						data : eNumber
					}

					]
				};

				myChart.setOption(option);
			});
			
			
			
			
			
		},
		error:function(data){
			
		}
	});
		
	</script>
	<script>
		function getJSData(){
			var dataAxis = new Array();
			var strNumber = new Array();
			var sureNumber = new Array();
			var eNumber = new Array();
			$.ajax({
				url:$WEB_ROOT_PATH
				+ "/dhccApi/superviseWork/superviseWork/getAllInfoByAreaJS",
				type:"get",
				success:function(data){
					for(var index in data){
						if(index == 10) break;
					
						dataAxis[index] = data[index].area==null?"未定义":data[index].area;
						if(data[index.area == null]) continue;
						strNumber[index] = parseInt(data[index].cnumber);
						sureNumber[index]= parseInt(data[index].surenumber==null?"0":data[index].surenumber);
						eNumber[index]=parseInt(data[index].enumber==null?"0":data[index].enumber);
					}
					// 路径配置
					require.config({
						paths : {
							echarts : $WEB_ROOT_PATH
									+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
						}
					});
					// 使用
					require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
					], function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = ec.init(document.getElementById('line1'));

						var option = {
							title : {
								text : ''
							},
							tooltip : {
								trigger : 'axis'
							},
							legend : {
								data : [ '审核人次','标注违规人次','标注正常人次' ]
							},
							grid : {
								left : '3%',
								right : '4%',
								bottom : '3%',
								containLabel : true
							},
							toolbox : {
								feature : {
									saveAsImage : {}
								}
							},
							xAxis : {
								type : 'category',
								boundaryGap : false,
								data : dataAxis ,  axisLabel: {
		                            interval:0,
		                            rotate:16
		                         } 
							},
							yAxis : {
								type : 'value'
							},
							series : [ /* {
								name : '检出人次',
								type : 'line',
								stack : '总量',
								data : [ 12000, 13200, 10100, 134000 ]
							}, */ {
								name : '审核人次',
								type : 'line',
								
								data : strNumber
							},
							{
								name : '标注违规人次',
								type : 'line',
								
								data : sureNumber
							},
							{
								name : '标注正常人次',
								type : 'line',
								
								data : eNumber
							}

							]
						};

						myChart.setOption(option);
					});
					
					
					
					
					
				},
				error:function(data){
					
				}
			});
				
		}
		function getZSData(){
			var dataAxis = new Array();
			var strNumber = new Array();
			var sureNumber = new Array();
			var eNumber = new Array();
			$.ajax({
				url:$WEB_ROOT_PATH
				+ "/dhccApi/superviseWork/superviseWork/getAllInfoByArea",
				type:"get",
				success:function(data){
					for(var index in data){
						if(index == 10) break;
					
						dataAxis[index] = data[index].area==null?"未定义":data[index].area;
						if(data[index.area == null]) continue;
						strNumber[index] = parseInt(data[index].cnumber);
						sureNumber[index]= parseInt(data[index].surenumber==null?"0":data[index].surenumber);
						eNumber[index]=parseInt(data[index].enumber==null?"0":data[index].enumber);
					}
					// 路径配置
					require.config({
						paths : {
							echarts : $WEB_ROOT_PATH
									+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
						}
					});
					// 使用
					require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
					], function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = ec.init(document.getElementById('line1'));

						var option = {
							title : {
								text : ''
							},
							tooltip : {
								trigger : 'axis'
							},
							legend : {
								data : [ '审核人次','排除违规人次','确认违规人次' ]
							},
							grid : {
								left : '3%',
								right : '4%',
								bottom : '3%',
								containLabel : true
							},
							toolbox : {
								feature : {
									saveAsImage : {}
								}
							},
							xAxis : {
								type : 'category',
								boundaryGap : false,
								data : dataAxis ,  axisLabel: {
		                            interval:0,
		                            rotate:16
		                         } 
							},
							yAxis : {
								type : 'value'
							},
							series : [ /* {
								name : '检出人次',
								type : 'line',
								stack : '总量',
								data : [ 12000, 13200, 10100, 134000 ]
							}, */ {
								name : '审核人次',
								type : 'line',
								
								data : strNumber
							},
							{
								name : '排除违规人次',
								type : 'line',
								
								data : eNumber
							},
							{
								name : '确认违规人次',
								type : 'line',
								
								data : sureNumber
							}

							]
						};

						myChart.setOption(option);
					});
					
					
					
					
					
				},
				error:function(data){
					
				}
			});
				
		}
	</script>
	
	
	<script type="text/javascript">
	$.ajax({
		url:$WEB_ROOT_PATH
			+ "/dhccApi/superviseWork/superviseWork/getAllInfoJS",
		type:"get",
		success:function(data){
			var cnumber = parseInt(data.cnumber);
			var surenumber = parseInt(data.surenumber);
			var enumber=parseFloat(data.enumber);
			/* var rnumber=parseInt(data.rnumber);
			var enumber=parseInt(data.enumber);
			var emoney=parseFloat(data.emoney); */
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require([ 'echarts',  // 使用柱状图就加载bar模块，按需加载,
					  'echarts/chart/bar' ], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('line2'));

				var option = {
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    legend: {
					        data:[ '审核人次','标注违规人次','标注正常人次' ]
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis :{
					       
					            type : 'category',
					            data : [ '审核人次','标注违规人次','标注正常人次'  ]
					    /*,  axisLabel: {
                            interval:0,
                            rotate:40
                         }*/
					    },
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        /* {
					            name:'检出人次',
					            type:'bar',
					            data:[rnumber]
					        }, */
					        {
					            name:'审核人次',
					            type:'bar',
					           
					            data:[cnumber,'-','-']
					        },
					        {
					            name:'标注违规人次',
					            type:'bar',
					            
					            data:['-',surenumber]
					        },
					        {
					            name:'标注正常人次',
					            type:'bar',
					            
					            data:['-','-',enumber]
					        },
					    ]
				};

				myChart.setOption(option);
			});
			
		},
		error:function(data){
			
		}
	});
	</script>
	<script>
		function getMZSData(){
			$.ajax({
				url:$WEB_ROOT_PATH
					+ "/dhccApi/superviseWork/superviseWork/getAllInfo",
				type:"get",
				success:function(data){
					var cnumber = parseInt(data.cnumber);
					var surenumber = parseInt(data.surenumber);
					var suremoney=parseFloat(data.suremoney);
					var rnumber=parseInt(data.rnumber);
					var enumber=parseInt(data.enumber);
					var emoney=parseFloat(data.emoney);
					// 路径配置
					require.config({
						paths : {
							echarts : $WEB_ROOT_PATH
									+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
						}
					});
					// 使用
					require([ 'echarts',  // 使用柱状图就加载bar模块，按需加载,
							  'echarts/chart/bar' ], function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = ec.init(document.getElementById('line2'));

						var option = {
							    tooltip : {
							        trigger: 'axis',
							        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
							        }
							    },
							    legend: {
							        data:[ '审核人次','排除违规人次','确认违规人次' ]
							    },
							    grid: {
							        left: '3%',
							        right: '4%',
							        bottom: '3%',
							        containLabel: true
							    },
							    xAxis :{
							       
							            type : 'category',
							            data : [ '审核人次','排除违规人次','确认违规人次' ]
							    /*,  axisLabel: {
		                            interval:0,
		                            rotate:40
		                         }*/
							    },
							    yAxis : [
							        {
							            type : 'value'
							        }
							    ],
							    series : [
							        /* {
							            name:'检出人次',
							            type:'bar',
							            data:[rnumber]
							        }, */
							        {
							            name:'审核人次',
							            type:'bar',
							            
							            data:[cnumber,'-','-']
							        },
							        {
							            name:'排除违规人次',
							            type:'bar',
							           
							            data:['-',enumber]
							        },
							        {
							            name:'确认违规人次',
							            type:'bar',
							            
							            data:['-','-',surenumber]
							        },
							    ]
						};

						myChart.setOption(option);
					});
					
				},
				error:function(data){
					
				}
			});
		}
		
		function getMJSData(){
			$.ajax({
				url:$WEB_ROOT_PATH
					+ "/dhccApi/superviseWork/superviseWork/getAllInfoJS",
				type:"get",
				success:function(data){
					var cnumber = parseInt(data.cnumber);
					var surenumber = parseInt(data.surenumber);
					var enumber=parseFloat(data.enumber);
					/* var rnumber=parseInt(data.rnumber);
					var enumber=parseInt(data.enumber);
					var emoney=parseFloat(data.emoney); */
					// 路径配置
					require.config({
						paths : {
							echarts : $WEB_ROOT_PATH
									+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
						}
					});
					// 使用
					require([ 'echarts',  // 使用柱状图就加载bar模块，按需加载,
							  'echarts/chart/bar' ], function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = ec.init(document.getElementById('line2'));

						var option = {
							    tooltip : {
							        trigger: 'axis',
							        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
							        }
							    },
							    legend: {
							        data:[ '审核人次','标注违规人次','标注正常人次' ]
							    },
							    grid: {
							        left: '3%',
							        right: '4%',
							        bottom: '3%',
							        containLabel: true
							    },
							    xAxis :{
							       
							            type : 'category',
							            data : [ '审核人次','标注违规人次','标注正常人次'  ]
							    /*,  axisLabel: {
		                            interval:0,
		                            rotate:40
		                         }*/
							    },
							    yAxis : [
							        {
							            type : 'value'
							        }
							    ],
							    series : [
							        /* {
							            name:'检出人次',
							            type:'bar',
							            data:[rnumber]
							        }, */
							        {
							            name:'审核人次',
							            type:'bar',
							           
							            data:[cnumber,'-','-']
							        },
							        {
							            name:'标注违规人次',
							            type:'bar',
							            
							            data:['-',surenumber]
							        },
							        {
							            name:'标注正常人次',
							            type:'bar',
							            
							            data:['-','-',enumber]
							        },
							    ]
						};

						myChart.setOption(option);
					});
					
				},
				error:function(data){
					
				}
			});
		}
	</script>
	<script type="text/javascript">
		layui.config({
			base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
		}).extend({
			index : 'lib/index' //主入口模块
		}).use([ 'index', 'table' ], function() {
			var $ = layui.$, form = layui.form, table = layui.table;
			table.render({
				elem : '#maptable',
				url : $WEB_ROOT_PATH + '/dhccApi/medical/medical/listQuality',
				cellMinWidth : 80,
				height : 200,
				cols : [ [ {
					field : 'qualityName',
					align : 'center',
					title : '医院名称'
				}, {
					field : 'qualityNumber',
					align : 'center',
					title : '上传总数量'
				}, {
					type : 'numbers',
					width : 200,
					align : 'center',
					title : '排名 '
				} ] ],
				page : true
			});
		});
	</script>
	<!-- 所有的动态获取 -->
	<!-- 改版统筹区 -->
	<script>
	
	$.ajax({
		url:$WEB_ROOT_PATH+"/dhccApi/superviseWork/superviseWork/getAllInfoJS",
		type:"get",
		success:function(data){
			//审核人次
			var cnumber = data.cnumber == null ? "-":data.cnumber;
			//违规人次
			var surenumber = data.surenumber == null ? "-":data.surenumber;
			//正常人次
			var enumber=data.enumber == null ? "-":data.enumber;
			
			if(data != ""|| data!= null){
				$('#JGGZLALL').html("<font color='red' size='4'> " +"统筹区机审审核人次为:"+cnumber+","
						+" 统筹区机审标注违规人次为:"+surenumber+", 统筹区机审标注正常人次为:"+enumber+"</font>"
				);
			}
	},error:function(data){
		
	}
	});
	</script>
	
	<script>
		function getTIZSData(){
			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseWork/superviseWork/getAllInfo",
				type:"get",
				success:function(data){
				
					var cnumber = data.cnumber == null ? "-":data.cnumber;
					var surenumber = data.surenumber == null ? "-":data.surenumber;
					var enumber=data.enumber == null ? "-":data.enumber;
					
					if(data != ""|| data!= null){
						$('#JGGZLALL').html("<font color='red' size='4'> " +"统筹区终审审核人次为:"+cnumber+","
								+" 统筹区终审确认违规人次为:"+surenumber+", 统筹区终审排除违规人次为:"+enumber+"</font>"
						);
					}
			},error:function(data){
				
			}
			});
		}
		
		function getTIJSData(){
			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseWork/superviseWork/getAllInfoJS",
				type:"get",
				success:function(data){
				
					//审核人次
					var cnumber = data.cnumber == null ? "-":data.cnumber;
					//违规人次
					var surenumber = data.surenumber == null ? "-":data.surenumber;
					//正常人次
					var enumber=data.enumber == null ? "-":data.enumber;
					
					if(data != ""|| data!= null){
						$('#JGGZLALL').html("<font color='red' size='4'> " +"统筹区机审审核人次为:"+cnumber+","
								+" 统筹区机审标注违规人次为:"+surenumber+", 统筹区机审标注正常人次为:"+enumber+"</font>"
						);
					}
			},error:function(data){
				
			}
			});
		}
		
	</script>
	
	<!-- 得到列表所有的违规信息 -->
	<script>
		/* $.ajax({
			url:$WEB_ROOT_PATH
				+ "/dhccApi/superviseWork/superviseWork/getAllInfo",
			type:"get",
			success:function(data){
				console.log(data);
				var cnumber = data.cnumber;
				var surenumber = data.surenumber;
				var suremoney=data.suremoney
				//var rnumber = data.rnumber;
				var enumber = data.enumber;
				var emoney = data.emoney;
				$("#cnumber").html(cnumber==null?"/":cnumber);
				$("#surenumber").html(surenumber==null?"/":surenumber);
				$("#suremoney").html(suremoney==null?"/":suremoney);
				//$("#rnumber").html(rnumber==null?"/":rnumber);
				$("#enumber").html((enumber==null?"/":enumber)=="0"?"/":(enumber==null?"":enumber));
				$("#emoney").html(emoney==null?"/":emoney);
			},
			error:function(data){
				
			}
		}); */
	</script>
	<!-- sureAllInfo -->
	
	<script>
	$.ajax({
		url:$WEB_ROOT_PATH+"/dhccApi/superviseWork/superviseWork/getAllSureData",
		type:"get",
		success:function(data){
			var table = $('#sureAllInfo');
    		var html = " <tr style='background:#E5F7EF'>"
					+"<td>排名</td>"
					+"<td>医院</td>"
					+"<td>公示反馈次数</td>"
					+"</tr>"
					var i = 1;
                for(var index in data){
                	if(index==5){
                		break;
                	}else{
                	var area=data[index].pname!=null?data[index].pname:"";
                	var number=data[index].pnumber!=null?data[index].pnumber:"-";
                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
               		html+="<td>"+(i++)+"</td>"
               		+"<td>"+area+"</td>"
               		+"<td>"+number+"</td>"
               		+"</tr>";
                }
                
                
                table.html(html);
                }
    			
			
		},
		error:function(data){
			
		}
	});
	
	
	</script>
	
	
	<script>
	$.ajax({
		url:$WEB_ROOT_PATH
		+ "/dhccApi/superviseWork/superviseWork/getAllInfoByAreaJS",
		type:"get",
		success:function(data){
			
			var table = $('#areaAllInfo');
    		var html = " <tr style='background:#E5F7EF'>"
					+"<td>排名</td>"
					+"<td>统筹区</td>"
					+"<td>审核人次</td>"
					+"</tr>"
					var i = 1;
                for(var index in data){
                	
                	if(index==5){
                		break;
                	}else{
                		
                	var area = data[index].area==null?"未定义":data[index].area;
        			var number= parseInt(data[index].cnumber==null?"-":data[index].cnumber);
        			number= number==0?"-":number;
        			if(area =="未定义"){
       					continue;
       				}
        			//var rate

                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
               		html+="<td>"+(i++)+"</td>"
               		+"<td>"+area+"</td>"
               		+"<td>"+number+"</td>"
               		+"</tr>";
                }
                
                
                table.html(html);
			
                }	
		},
		error:function(data){
				
		}
	});
	</script>
	<script>
		function getTCJSData(){
			$.ajax({
				url:$WEB_ROOT_PATH
				+ "/dhccApi/superviseWork/superviseWork/getAllInfoByAreaJS",
				type:"get",
				success:function(data){
					
					var table = $('#areaAllInfo');
		    		var html = " <tr style='background:#E5F7EF'>"
							+"<td>排名</td>"
							+"<td>统筹区</td>"
							+"<td>审核人次</td>"
							+"</tr>"
							var i = 1;
		                for(var index in data){
		                	
		                	if(index==5){
		                		break;
		                	}else{
		                		
		                	var area = data[index].area==null?"未定义":data[index].area;
		        			var number= parseInt(data[index].cnumber==null?"-":data[index].cnumber);
		        			number= number==0?"-":number;
		        			if(area =="未定义"){
		       					continue;
		       				}
		        			//var rate

		                	if(index%2==0){
		                		html+="<tr style='background:#fff'>";
		                	}else{
		                		html+="<tr style='background:#F5FCF9'>";
		                	}
		               		html+="<td>"+(i++)+"</td>"
		               		+"<td>"+area+"</td>"
		               		+"<td>"+number+"</td>"
		               		+"</tr>";
		                }
		                
		                
		                table.html(html);
					
		                }	
				},
				error:function(data){
						
				}
			});
		}
		function getTCZSData(){
			$.ajax({
				url:$WEB_ROOT_PATH
				+ "/dhccApi/superviseWork/superviseWork/getAllInfoByArea",
				type:"get",
				success:function(data){
					
					var table = $('#areaAllInfo');
		    		var html = " <tr style='background:#E5F7EF'>"
							+"<td>排名</td>"
							+"<td>统筹区</td>"
							+"<td>审核人次</td>"
							+"</tr>"
							var i = 1;
		                for(var index in data){
		                	
		                	if(index==5){
		                		break;
		                	}else{
		                		
		                	var area = data[index].area==null?"未定义":data[index].area;
		        			var number= parseInt(data[index].cnumber==null?"-":data[index].cnumber);
		        			number= number==0?"-":number;
		        			if(area =="未定义"){
		       					continue;
		       				}
		        			//var rate

		                	if(index%2==0){
		                		html+="<tr style='background:#fff'>";
		                	}else{
		                		html+="<tr style='background:#F5FCF9'>";
		                	}
		               		html+="<td>"+(i++)+"</td>"
		               		+"<td>"+area+"</td>"
		               		+"<td>"+number+"</td>"
		               		+"</tr>";
		                }
		                
		                
		                table.html(html);
					
		                }	
				},
				error:function(data){
						
				}
			});
		}
	</script>
	
	
	
	<script>
	var path =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllAreaJS';
	function getValue1(){
		
		var value = $("#findYear1 option:selected").val();
		
		if(value=="终审"){
			
			path =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllArea';
			getZSData();
			layui.table.reload('hospitalViolationTable',{
				 url: path
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}else if(value=="机审"){
		
			path =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllAreaJS';
			getJSData();
			layui.table.reload('hospitalViolationTable',{
				 url: path
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}else{
			path =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllAreaJS';
			getJSData();
			layui.table.reload('hospitalViolationTable',{
				 url: path
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}
		
	}
	function getValue2(){
		var value = $("#findYear2 option:selected").val();
		//alert(value);
		if(value=="终审"){
			
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1';
			getMZSData();
			
		}else if(value=="机审"){
		
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
			getMJSData();
			
		}else{
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
			getMJSData();
			
		}
	}
	function changeCardTop1(){
		 getValue1();
	}
	function getValueBoss(){
		var value = $("#findYearBoss option:selected").val();
		if(value=="终审"){
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllArea';
			getMZSData();
			getTCZSData();
	
			getTIZSData();
			getZSData();
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			
		}else if(value=="机审"){
		
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllAreaJS';
			getMJSData();
			getTCJSData();
			getTIJSData();
			getJSData();
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}else{
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllAreaJS';
			getMJSData();
			getTCJSData();
			getTIJSData();
			getJSData();
			
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}
	}
	
	
	
	
	//初始化	
	layui.config({
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'table'], function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table;
		
		

		 //加载统筹区下拉字典
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dictCity', 
					function(data){
			 var  dataList= data.dictList;
	     		
	     		let log = console.log.bind(console);
	     		let obj = {};
	     		
	     		var cur=[];
	     		person = dataList.reduce((cur,next) => {
	     		    obj[next.text] ? "" : obj[next.text] = true && cur.push(next);
	     		    return cur;
	     		},[]) // 设置cur默认类型为数组，并且初始值为空的数组
	     		log(person);
	     		dataList=person;
	     		for(var i=0 ;i<dataList.length;i++){
	     			var nn="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
	     			//$("#cityName").append(nn);
			     			$("#city").append(nn); 
			     		}
			     	form.render('select');
		}); 
		//加载统筹区下拉字典
	     $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dictCity',
	         function(data){
	    	 var  dataList= data.dictList;
	     		
	     		let log = console.log.bind(console);
	     		let obj = {};
	     		
	     		var cur=[];
	     		person = dataList.reduce((cur,next) => {
	     		    obj[next.text] ? "" : obj[next.text] = true && cur.push(next);
	     		    return cur;
	     		},[]) // 设置cur默认类型为数组，并且初始值为空的数组
	     		log(person);
	     		dataList=person;
	     		for(var i=0 ;i<dataList.length;i++){
	     			var nn="<option value='"+dataList[i].text+"'>"+dataList[i].text+"</option>";
	     			//$("#cityName").append(nn);
	                 $("#zyOrgName").append(nn);
	             }
	             form.render('select');
	        });
		 
		// medicalStatusStatistics();//待审核状态统计
		table.render({
			elem: '#hospitalViolationTable'
				,url: $WEB_ROOT_PATH+'/dhccApi/superviseWork/superviseWork/getAllInfoByAllAreaJS'
				,height: 250		
				,limit:5
				,where: {  ilegalChild: '1'  }
		,cols: [[
			{type: 'numbers',align:"center", title: '序号' }
			/*,{field:'cityName',title: '城市名称'}*/
			,{field:'area',align:"center", title: '统筹区名称'}
			,{field:'cnumber',align:"center", title: '审核人次',templet:function(d){
				codex = d.cnumber ;
				if(codex == ""|| codex == null || codex == "null" || codex== 0){
					codex='-';
				}
				return codex;
			}}
			,{field:'surenumber',align:"center", title: '确认违规人次',templet:function(d){
				codex = d.surenumber ;
				if(codex == ""|| codex == null || codex == "null"){
					codex='-';
				}
				return codex;
			}}
			,{field:'enumber',align:"center", title: '排除违规人次',templet:function(d){
				codex = d.enumber ;
				if(codex == ""|| codex == null || codex == "null"){
					codex='-';
				}
				return codex;
			}}
			
			]]
		,page: true
		});
		
		//监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
			var field = data.field;
			console.log(field)
			//console.log(field);
			//执行重载
			layui.table.reload('hospitalViolationTable', {
				where: field
			});
		});
		
		
		//sysVerify();//违规统计
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	</script>
	
	
	
</body>
</html>
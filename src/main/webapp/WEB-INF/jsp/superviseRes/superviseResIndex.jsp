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
					<div id="tcqAllInfo"></div>
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
						统筹区违规人次与人数趋势图 <select id="findYear1" name="year"
							onchange="getValue1()">
							<option value="机审" selected="selected">机审</option>
							<option value="终审">终审</option>
						</select>

					</div>
					
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title"
								style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;" >
								<li class="layui-this"
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none" onclick="changeCardTop1();">Top10</li>
								<img style="height: 25px; width: 2px;"
									src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
								<li
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none" onclick="changeCardTop1();">全部</li>
							</ul>
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;" >
								<div class="layui-tab-item layui-show" style="height: 295px;">
									<div id="line1" style="height: 300px; padding-left: 5px"></div>
								</div>
								<div class="layui-tab-item ">
									<div class="layui-card">
										<div
											class="layui-form layui-card-header layuiadmin-card-header-auto"
											style="padding-left: 5px;">
											<div class="layui-form-item">
												<div class="layui-inline pt" style="margin-right: 4px;">
													<label class="layui-form-label">统筹区名称</label>
													<div class="layui-input-block">
												
														<select id="zyOrgName" style="width: 150px;"
															name="superviseResVo.pname" lay-verify="" lay-search=" ">
															<option value="" disabled selected style='display: none;'>请选择统筹区</option>
														</select>
													</div>
												</div>
												<button
													style="display: inline-block; position: absolute; margin-top: 8px; padding: 0px 7px"
													class="layui-btn layui-btn-sm layuiadmin-btn-useradmin"
													lay-submit lay-filter="LAY-user-front-search1">
													<i
														class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
												</button>

											</div>
										</div>
										<table id="hospitalViolationTable1" class="layui-hide"
											lay-filter="hospitalViolationTable1"></table>
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
						统筹区违扣减金额趋势图 <select id="findYear2" name="year"
							onchange="getValue2()">
							<option value="机审" selected="selected">机审</option>
							<option value="终审">终审</option>
						</select>
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title"
								style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
								<li class="layui-this"
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none" onclick="changeCardTop2();">Top10</li>
								<img style="height: 25px; width: 2px;"
									src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
								<li
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none" onclick="changeCardTop2();">全部</li>
							</ul>
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 295px;">

									<div id="line2" style="height: 300px; padding-left: 50px;"></div>
								</div>
								<div class="layui-tab-item ">
									<div class="layui-card">
										<div
											class="layui-form layui-card-header layuiadmin-card-header-auto"
											style="padding-left: 5px;">
											<div class="layui-form-item">
												<div class="layui-inline pt" style="margin-right: 4px;">
													<label class="layui-form-label">统筹区名称</label>
													<div class="layui-input-block">
														<select id="city" style="width: 150px;"
															name="superviseResVo.city" lay-verify="" lay-search=" ">
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
		</div>
	</div>
	<div class="layui-col-md12 layui-col-xs12"
		style="padding: 6px 0px 5px 0px;">
		<!-- <div class="layui-card-header"
			style="border-bottom: 1px solid #f6f6f6;">
			<center>
				<font size="6"><b>统筹区违规人次情况排名</b></font>
			</center>
		</div> -->
		<div class="layui-card">
			<div class="layui-col-md6">

				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						统筹区监控规则排名 <a id='ruleRank' style="float: right;" lay-urlname="统筹区监控规则排名"
							href="javascript:;" lay-urlname="监控规则全部信息"
							lay-href="<%=request.getContextPath()%>/superviseRes/superviseResAllRule?value=rule">查看全部<img
							style="width: 14px; margin-top: -2px; padding-right: 2px; margin-left: 6px;"
							src="<%=request.getContextPath()%>/images/auditing/more.png" /></a>
					</div>
					<div class="layui-card-body layui-text">
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody id="ruleAllInfo">
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td>排名</td>
									<td>规则名称</td>
									<td>违规人次</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr style='background: #F5FCF9'>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr style='background: #F5FCF9'>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr style='background: #F5FCF9'>
									<td></td>
									<td></td>
									<td></td>
								</tr>
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
						统筹区违规人次排名 <a id="TCQrankA" style="float: right;" lay-urlname="统筹区违规人次排名"
							href="javascript:;"
							lay-href="<%=request.getContextPath()%>/superviseRes/superviseResAllArea">查看全部<img
							style="width: 14px; margin-top: -2px; padding-right: 2px; margin-left: 6px;"
							src="<%=request.getContextPath()%>/images/auditing/more.png" /></a>
					</div>
					<div class="layui-card-body layui-text">
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody id="illegalPNumber">
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td>排名</td>
									<td>统筹区</td>
									<td>违规人次</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr style='background: #F5FCF9'>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr style='background: #F5FCF9'>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr style='background: #F5FCF9'>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
	var dataAxis =new Array();
	var strNumber=new Array();
	var strTime = new Array();
	
	$.ajax({
		url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getMapDataJS",
		type:"get",
		success:function(data){
			for(var index in data){
				if(index == 11 || index > 10) break;
				dataAxis[index] = data[index].pname;
				strNumber[index] = parseInt(data[index].pnumber);
				strTime[index] = parseInt(data[index].ptime);
			}
			
			
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require([ 'echarts', 'echarts/chart/bar','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('line1'));				
			var	option = {
					   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    legend: {
					        data: ['违规人数', '违规人次']
					    },
					    toolbox: {
					        show: true,
					        top: 'center',
					        feature: {
					        	
					            magicType: {show: true, type: ['line', 'bar']},
					            restore: {show: true}
					        }
					    },
					    calculable: true,
					    xAxis: [
					        {
					            type: 'category',
					            axisTick: {show: false},
					            data:dataAxis
					            ,  axisLabel: {
		                            interval:0,
		                            rotate:16
		                         }
					        }
					    ],
					    yAxis: [
					        {
					            type: 'value'
					        }
					    ],
					    series: [
					        {
					            name: '违规人数',
					            type: 'bar',
					            data: strNumber
					        },
					        {
					            name: '违规人次',
					            type: 'bar',
					            data: strTime
					        }
					    ]
					};
				
				myChart.setOption(option);
			});
			
			
			
		},
		error:function(data){
			
		}
	})
	</script>
	<script>
	function getJSData(){
		var dataAxis =new Array();
		var strNumber=new Array();
		var strTime = new Array();
		
		$.ajax({
			url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getMapDataJS",
			type:"get",
			success:function(data){
				for(var index in data){
					if(index == 11 || index > 10) break;
					dataAxis[index] = data[index].pname;
					strNumber[index] = parseInt(data[index].pnumber);
					strTime[index] = parseInt(data[index].ptime);
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
			var	option = {
					   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    legend: {
					        data: ['违规人数', '违规人次']
					    },
					    toolbox: {
					        show: true,
					     
					        top:2,
							right:200,
					        feature: {
					        	
					            magicType: {show: true, type: ['line', 'bar']},
					            restore: {show: true}
					        }
					    },
					    calculable: true,
					    xAxis: [
					        {
					            type: 'category',
					            axisTick: {show: false},
					            data:dataAxis
					            ,  axisLabel: {
		                            interval:0,
		                            rotate:16
		                         }
					        }
					    ],
					    yAxis: [
					        {
					            type: 'value'
					        }
					    ],
					    series: [
					        {
					            name: '违规人数',
					            type: 'bar',
					            /* barGap: 0, */
					           
					            data: strNumber
					        },
					        {
					            name: '违规人次',
					            type: 'bar',
					          
					            data: strTime
					        }
					    ]
					};
				

					myChart.setOption(option);
				});
				
				
				
			},
			error:function(data){
				
			}
		})
	}
	function getZSData(){
		var dataAxis =new Array();
		var strNumber=new Array();
		var strTime = new Array();
		
		$.ajax({
			url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getMapData",
			type:"get",
			success:function(data){
				for(var index in data){
					if(index == 11 || index > 10) break;
					dataAxis[index] = data[index].pname;
					strNumber[index] = parseInt(data[index].pnumber);
					strTime[index] = parseInt(data[index].ptime);
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
			var	option = {
					   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    legend: {
					        data: ['违规人数', '违规人次']
					    },
					    toolbox: {
					        show: true,
					     
					        top:2,
							right:200,
					        feature: {
					        	
					            magicType: {show: true, type: ['line', 'bar']},
					            restore: {show: true}
					        }
					    },
					    calculable: true,
					    xAxis: [
					        {
					            type: 'category',
					            axisTick: {show: false},
					            data:dataAxis
					            ,  axisLabel: {
		                            interval:0,
		                            rotate:16
		                         }
					        }
					    ],
					    yAxis: [
					        {
					            type: 'value'
					        }
					    ],
					    series: [
					        {
					            name: '违规人数',
					            type: 'bar',
					           /*  barGap: 0, */
					           
					            data: strNumber
					        },
					        {
					            name: '违规人次',
					            type: 'bar',
					          
					            data: strTime
					        }
					    ]
					};
				

					myChart.setOption(option);
				});
				
				
				
			},
			error:function(data){
				
			}
		})
	}
	</script>
	<script type="text/javascript">
	 var dataAxis1 =new Array();
	var strNumber1=new Array();
	
	
	
	
	$.ajax({
		url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getIllegalByAreaJS",
		type:"get",
		success:function(data){
			console.log(data);
			for(var index in data){
				if(index == 11 || index > 10) break;
				var money = data[index].money;
				money = money == null ? 0 :money;
				money = money == ""  ? 0 : money; 
				dataAxis1[index] = data[index].city;
				strNumber1[index] = parseFloat(money);
			}
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
			'echarts/chart/line' ], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart1 = ec.init(document.getElementById('line2'));			
		var	option = {
				    /* color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'shadow'
				        }
				    },
				    legend: {
				        data: ['扣减金额']
				    },
				    toolbox: {
				        show: true,
				     
				        top:2,
						right:200,
				        feature: {
				        	
				            magicType: {show: true, type: ['line', 'bar']},
				            restore: {show: true}
				        }
				    },
				    calculable: true,
				    xAxis: [
				        {
				            type: 'category',
				            axisTick: {show: false},
				            data:dataAxis1
				            ,  axisLabel: {
	                            interval:0,
	                            rotate:16
	                         }
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value'
				        }
				    ],
				    series: [
				        {
				            name: '扣减金额',
				            type: 'bar',
				           /*  barGap: 0, */
				           
				            data: strNumber1
				        }
				    ]
				};
			
				myChart1.setOption(option);
			});
			
		}
		});
	
	
	
		
	</script>
	<script>
		function getMJSData(){
			 var dataAxis =new Array();
				var strNumber1=new Array();
				
				
				$.ajax({
					url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getIllegalByAreaJS",
					type:"get",
					success:function(data){
					
						for(var index in data){
							if(index == 11 || index > 10) break;
							var money = data[index].money;
							money = money == null ? 0 :money;
							money = money == ""  ? 0 : money; 
							dataAxis[index] = data[index].city;
							strNumber1[index] = parseFloat(money);
						}
						console.log(dataAxis);
						console.log(strNumber1);
						// 路径配置
						require.config({
							paths : {
								echarts : $WEB_ROOT_PATH
										+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
							}
						});
						// 使用
						require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
						'echarts/chart/line' ], function(ec) {
							// 基于准备好的dom，初始化echarts图表
							var myChart = ec.init(document.getElementById('line2'));
						
					var	option = {
							    /* color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
							    tooltip: {
							        trigger: 'axis',
							        axisPointer: {
							            type: 'shadow'
							        }
							    },
							    legend: {
							        data: ['扣减金额']
							    },
							    toolbox: {
							        show: true,
							     
							        top:2,
									right:200,
							        feature: {
							        	
							            magicType: {show: true, type: ['line', 'bar']},
							            restore: {show: true}
							        }
							    },
							    calculable: true,
							    xAxis: [
							        {
							            type: 'category',
							            axisTick: {show: false},
							            data:dataAxis
							            ,  axisLabel: {
				                            interval:0,
				                            rotate:16
				                         }
							        }
							    ],
							    yAxis: [
							        {
							            type: 'value'
							        }
							    ],
							    series: [
							        {
							            name: '扣减金额',
							            type: 'bar',
							           /*  barGap: 0, */
							           
							            data: strNumber1
							        }
							    ]
							};
						
							myChart.setOption(option);
						});
						
					}
					});
				
				
				
		}
		function getMZSData(){
			 var dataAxis =new Array();
				var strNumber1=new Array();
				
				
				$.ajax({
					url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getIllegalByArea",
					type:"get",
					success:function(data){
					
						for(var index in data){
							if(index == 11 || index > 10) break;
							var money = data[index].money;
							money = money == null ? 0 :money;
							money = money == ""  ? 0 : money; 
							dataAxis[index] = data[index].city;
							strNumber1[index] = parseFloat(money);
						}
						console.log(dataAxis);
						console.log(strNumber1);
						// 路径配置
						require.config({
							paths : {
								echarts : $WEB_ROOT_PATH
										+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
							}
						});
						// 使用
						require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
						'echarts/chart/line' ], function(ec) {
							// 基于准备好的dom，初始化echarts图表
							var myChart = ec.init(document.getElementById('line2'));
						
					var	option = {
							   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
							    tooltip: {
							        trigger: 'axis',
							        axisPointer: {
							            type: 'shadow'
							        }
							    },
							    legend: {
							        data: ['扣减金额']
							    },
							    toolbox: {
							        show: true,
							        top: -12,
							        left:20,
							        feature: {
							        	
							            magicType: {show: true, type: ['line', 'bar']},
							            restore: {show: true}
							        }
							    },
							    calculable: true,
							    xAxis: [
							        {
							            type: 'category',
							            axisTick: {show: false},
							            data:dataAxis
							            ,  axisLabel: {
				                            interval:0,
				                            rotate:16
				                         }
							        }
							    ],
							    yAxis: [
							        {
							            type: 'value'
							        }
							    ],
							    series: [
							        {
							            name: '扣减金额',
							            type: 'bar',
							          /*   barGap: 0, */
							           
							            data: strNumber1
							        }
							    ]
							};
						
							myChart.setOption(option);
						});
						
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
		url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getIllegalJS",
		type:"get",
		success:function(data){
			
			if(data != ""|| data!= null){
				var str = data.split(",");
				var totalAreas = str[4]== null ? "-":str[4];
				totalAreas=str[4]=="0"?"-":str[4];
				totalAreas=str[4]==null?"-":str[4];
				totalAreas=str[4]=="null"?"-":str[4];
				totalAreas=str[4]==""?"-":str[4];
				var totalPtime = str[0]== null ? "-":str[0];
				totalPtime=str[0]=="0"?"-":str[0];
				totalPtime=str[0]==null?"-":str[0];
				totalPtime=str[0]=="null"?"-":str[0];
				totalPtime=str[0]==""?"-":str[0];
				var totalPnumber=str[1]== null ? "-":str[1];
				totalPnumber=str[1]=="0"?"-":str[1];
				totalPnumber=str[1]==null?"-":str[1];
				totalPnumber=str[1]=="null"?"-":str[1];
				totalPnumber=str[1]==""?"-":str[1];
				var totalCost = str[2]== null ? "-":str[2];
				totalCost=str[2]=="0"?"-":str[2];
				totalCost=str[2]==null?"-":str[2];
				totalCost=str[2]=="null"?"-":str[2];
				totalCost=str[2]==""?"-":str[2];
				var prate = str[3]== null ? "-":str[3];
				prate=str[3]=="0"?"-":str[3];
				prate=str[3]==null?"-":str[3];
				prate=str[3]=="null"?"-":str[3];
				prate=str[3]==""?"-":str[3];
				prate=str[3]=="%"?"-":str[3];
				prate=str[3]=="0.000000%"?"-":str[3];
				$('#tcqAllInfo').html("<font color='red' size='4'> "
						+" 统筹区机审违规人次为:"+totalPtime+", 统筹区机审违规人数为:"+totalPnumber
				+", 统筹区机审违规金额为:"+totalCost+", 统筹区机审违规次率为:"+prate+"</font>");
			}else{
				$('#tcqAllInfo').html("<font color='red' size='4'> "+","
						+" 统筹区机审违规人次为:"+"-"+", 统筹区机审违规人数为:"+"-"
				+", 统筹区机审违规金额为:"+"-"+", 统筹区机审违规次率为:"+"-"+"</font>");
			}
		},
		error:function(data){
			alert("服务器错误");
		}
	});
	
	</script>
	<script>
		function getTIJSData(){

			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getIllegalJS",
				type:"get",
				success:function(data){
					if(data != ""|| data!= null){
						var str = data.split(",");
						var totalAreas = str[4]== null ? "-":str[4];
						totalAreas=str[4]=="0"?"-":str[4];
						totalAreas=str[4]==null?"-":str[4];
						totalAreas=str[4]=="null"?"-":str[4];
						totalAreas=str[4]==""?"-":str[4];
						var totalPtime = str[0]== null ? "-":str[0];
						totalPtime=str[0]=="0"?"-":str[0];
						totalPtime=str[0]==null?"-":str[0];
						totalPtime=str[0]=="null"?"-":str[0];
						totalPtime=str[0]==""?"-":str[0];
						var totalPnumber=str[1]== null ? "-":str[1];
						totalPnumber=str[1]=="0"?"-":str[1];
						totalPnumber=str[1]==null?"-":str[1];
						totalPnumber=str[1]=="null"?"-":str[1];
						totalPnumber=str[1]==""?"-":str[1];
						var totalCost = str[2]== null ? "-":str[2];
						totalCost=str[2]=="0"?"-":str[2];
						totalCost=str[2]==null?"-":str[2];
						totalCost=str[2]=="null"?"-":str[2];
						totalCost=str[2]==""?"-":str[2];
						var prate = str[3]== null ? "-":str[3];
						prate=str[3]=="0"?"-":str[3];
						prate=str[3]==null?"-":str[3];
						prate=str[3]=="null"?"-":str[3];
						prate=str[3]==""?"-":str[3];
						prate=str[3]=="%"?"-":str[3];
						prate=str[3]=="0.000000%"?"-":str[3];
						$('#tcqAllInfo').html("<font color='red' size='4'>"
								+" 统筹区机审违规人次为:"+totalPtime+", 统筹区机审违规人数为:"+totalPnumber
						+", 统筹区机审违规金额为:"+totalCost+", 统筹区机审违规次率为:"+prate+"</font>");
					}
				},
				error:function(data){
					alert("服务器错误");
				}
			});
		}
	function getTIZSData(){
		$.ajax({
			url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getIllegal",
			type:"get",
			success:function(data){
				if(data != ""|| data!= null){
					if(data == "noSession"){
						alert("你的会话已经失效,请重新登录");
						window.top.location.href=$WEB_ROOT_PATH; 
					}else{
					var str = data.split(",");
					var totalAreas = str[4]== null ? "-":str[4];
					totalAreas=str[4]=="0"?"-":str[4];
					totalAreas=str[4]==null?"-":str[4];
					totalAreas=str[4]=="null"?"-":str[4];
					totalAreas=str[4]==""?"-":str[4];
					
					var totalPtime = str[0]== null ? "-":str[0];
					totalPtime=str[0]=="0"?"-":str[0];
					totalPtime=str[0]==null?"-":str[0];
					totalPtime=str[0]=="null"?"-":str[0];
					totalPtime=str[0]==""?"-":str[0];
					var totalPnumber=str[1]== null ? "-":str[1];
					totalPnumber=str[1]=="0"?"-":str[1];
					totalPnumber=str[1]==null?"-":str[1];
					totalPnumber=str[1]=="null"?"-":str[1];
					totalPnumber=str[1]==""?"-":str[1];
					var totalCost = str[2]== null ? "-":str[2];
					totalCost=str[2]=="0"?"-":str[2];
					totalCost=str[2]==null?"-":str[2];
					totalCost=str[2]=="null"?"-":str[2];
					totalCost=str[2]==""?"-":str[2];
					var prate = str[3]== null ? "-":str[3];
					prate=str[3]=="0"?"-":str[3];
					prate=str[3]==null?"-":str[3];
					prate=str[3]=="null"?"-":str[3];
					prate=str[3]==""?"-":str[3];
					prate=str[3]=="%"?"-":str[3];
					prate=str[3]=="0.000000%"?"-":str[3];
					$('#tcqAllInfo').html("<font color='red' size='4'>  "
							+" 统筹区终审违规人次为:"+totalPtime+", 统筹区终审违规人数为:"+totalPnumber
					+", 统筹区终审违规金额为:"+totalCost+", 统筹区终审违规次率为:"+prate+"</font>");
				}}
			},
			error:function(data){
				alert("服务器错误");
			}
		});
	}
	</script>
	<!-- 规则检出人次 -->
	<script>
	$.ajax({
		url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllRuleInfo1JS",
		type:"get",
		success:function(data){
			var table = $('#ruleAllInfo');
    		var html = " <tr style='background:#E5F7EF'>"
					+"<td>排名</td>"
					+"<td>规则名称</td>"
					+"<td>违规人次</td>"
					+"</tr>"
					var i = 1;
					var flag = 1;
                for(var index in data){
                	
                	if(index==5){
                		break;
                	}else{
                		
                	var area=data[index].pname!=null?data[index].pname:"";
                	if(area == ""){
                		continue;
                	}
                	flag++;
                	var number=data[index].pnumber!=null?data[index].pnumber:"-";
                	number = number == ""?"-":number;
                	number = number == "0"?"-":number;	
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
                
                	
                }
    			
                for(var i = flag;i<=5;i++){
                 	html+="<td>"+i+"</td>"
                		+"<td>"+"</td>"
                		+"<td>"+"</td>"
                		+"</tr>";
                 }
            table.html(html);
		},
		error:function(data){
			alert("阿三大苏打离开后");
		}
	});
	
	
	</script>
	<script>
 		function getRZSData(){
			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllRuleInfo1",
				type:"get",
				success:function(data){
					var table = $('#ruleAllInfo');
		    		var html = " <tr style='background:#E5F7EF'>"
							+"<td>排名</td>"
							+"<td>规则名称</td>"
							+"<td>违规人次</td>"
							+"</tr>"
							var i = 1;
							var flag=1;
		                for(var index in data){
		                	
		                	if(index==5){
		                		break;
		                	}else{
		                		
		                	var area=data[index].pname!=null?data[index].pname:"";
		                	if(area == ""){
		                		continue;
		                	}
		                	flag++;
		                	var number=data[index].pnumber!=null?data[index].pnumber:"-";
		                	number = number == ""?"-":number;
		                	number = number == "0"?"-":number;	
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
		                
		                	}
		    			
		                for(var i = flag;i<=5;i++){
	                     	html+="<td>"+i+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"</tr>";
	                     }
	                table.html(html);
				},
				error:function(data){
					alert("阿三大苏打离开后");
				}
			});
			
			
		} 	
		function getRJSData(){
			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllRuleInfo1JS",
				type:"get",
				success:function(data){
					var table = $('#ruleAllInfo');
		    		var html = " <tr style='background:#E5F7EF'>"
							+"<td>排名</td>"
							+"<td>规则名称</td>"
							+"<td>违规人次</td>"
							+"</tr>"
							var i = 1;
							var flag=1;
		                for(var index in data){
		                	
		                	if(index==5){
		                		break;
		                	}else{
		                		
		                	var area=data[index].typeNames!=null?data[index].typeNames:"";
		                	if(area == ""){
		                		continue;
		                	}
		                	flag++;
		                	var number=data[index].countNum!=null?data[index].countNum:"-";
		                	number = number == ""?"-":number;
		                	number = number == "0"?"-":number;	
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
		                
		                }
		    			

	                	 for(var i = flag;i<=5;i++){
	                     	html+="<td>"+i+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"</tr>";
	                     }
	                table.html(html);
				},
				error:function(data){
					alert("阿三大苏打离开后");
				}
			});
			
			
		}	
		
	</script>
	
	<!-- 统筹区违规人数 -->
	<script>
	$.ajax({
		url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllAreaDataJS",
		type:"get",
		success:function(data){
			var table = $('#illegalPNumber');
    		var html = " <tr style='background:#E5F7EF'>"
					+"<td>排名</td>"
					+"<td>统筹区</td>"
					+"<td>违规人次</td>"
					+"</tr>"
					var i = 1;
					var flag=1;
                for(var index in data){
                	
                	if(index==5){
                		break;
                	}else{
                		
                	var area=data[index].pname!=null?data[index].pname:"";
                	if(area == ""){
                		continue;
                	}
                	flag++;
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
                
                	 }
                for(var i = flag;i<=5;i++){
                 	html+="<td>"+i+"</td>"
                		+"<td>"+"</td>"
                		+"<td>"+"</td>"
                		+"</tr>";
                 }
            table.html(html);
			
		},
		error:function(data){
			
		}
	});
	
	
	</script>
	<script>
		//统筹区违规人次函数
		function getPJSData(){
			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllAreaDataJS",
				type:"get",
				success:function(data){
					var table = $('#illegalPNumber');
		    		var html = " <tr style='background:#E5F7EF'>"
							+"<td>排名</td>"
							+"<td>统筹区</td>"
							+"<td>违规人次</td>"
							+"</tr>"
							var i = 1;
							var flag=1;
		                for(var index in data){
		                	
		                	if(index==5){
		                		break;
		                	}else{
		                		
		                	var area=data[index].pname!=null?data[index].pname:"";
		                	if(area == ""){
		                		continue;
		                	}
		                	flag++;
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
		                
		                	 }
		    			
		                for(var i = flag;i<=5;i++){
	                     	html+="<td>"+i+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"</tr>";
	                     }
	                table.html(html);
				},
				error:function(data){
					
				}
			});
			
		}
		function getPZSData(){
			$.ajax({
				url:$WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllAreaData",
				type:"get",
				success:function(data){
					var table = $('#illegalPNumber');
		    		var html = " <tr style='background:#E5F7EF'>"
							+"<td>排名</td>"
							+"<td>统筹区</td>"
							+"<td>违规人次</td>"
							+"</tr>"
							var i = 1;
							var falg=1;
		                for(var index in data){
		                	
		                	if(index==5){
		                		break;
		                	}else{
		                		
		                	var area=data[index].pname!=null?data[index].pname:"";
		                	if(area == ""){
		                		continue;
		                	}
		                	flag++;
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
		                
		                	 }
		                for(var i = flag;i<=5;i++){
	                     	html+="<td>"+i+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"<td>"+"</td>"
	                    		+"</tr>";
	                     }
	                table.html(html);
					
				},
				error:function(data){
					
				}
			});
			
		}

		$(function(){
			getMJSData();
			getPJSData();
			getRJSData();
			getTIJSData();
			getJSData();
		});
				
	var path =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getMapData1JS';
	var mpath=$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
	function getValue1(){
		
		var value = $("#findYear1 option:selected").val();
		
		if(value=="终审"){
			
			path =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getMapData1';
			getZSData();
			layui.table.reload('hospitalViolationTable1',{
				 url: path
		     });
		}else if(value=="机审"){
		
			path =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getMapData1JS';
			getJSData();
			layui.table.reload('hospitalViolationTable1',{
				 url: path
		     });
		}else{
			path =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getMapData1JS';
			getJSData();
			layui.table.reload('hospitalViolationTable1',{
				 url: path
		     });
		}
		
	}
	function getValue2(){
		var value = $("#findYear2 option:selected").val();
		//alert(value);
		if(value=="终审"){
			
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1';
			getMZSData();
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			
		}else if(value=="机审"){
		
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
			getMJSData();
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}else{
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
			getMJSData();
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}
	}
	function changeCardTop1(){
		 getValue1();
	}
	function changeCardTop2(){
		getValue2();
	}
	function getValueBoss(){
		var value = $("#findYearBoss option:selected").val();
		if(value=="终审"){
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1';
			getMZSData();
			getPZSData();
			getRZSData();
			getTIZSData();
			getZSData();
			
			$('#ruleRank').attr('lay-href',$WEB_ROOT_PATH+"/superviseRes/superviseResAllRule?value=zs");
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			
		}else if(value=="机审"){
		
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
			getMJSData();
			getPJSData();
			getRJSData();
			getTIJSData();
			getJSData();
			$('#ruleRank').attr('lay-href',$WEB_ROOT_PATH+"/superviseRes/superviseResAllRule?value=js");
			
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}else{
			mpath =$WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS';
			getMJSData();
			getPJSData();
			getRJSData();
			getTIJSData();
			getJSData();
			$('#ruleRank').attr('lay-href',$WEB_ROOT_PATH+"/superviseRes/superviseResAllRule?value=js");
			
			layui.table.reload('hospitalViolationTable',{
				 url: mpath
		     });
			layui.table.reload('hospitalViolationTable',{
				
		     });
		}
	}
	
	</script>
	<script>
	//初始化	
	
	layui.config({
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'table','form'], function(){
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
	     		person = dataList.reduce((cur,next) = >{
	     		    obj[next.text] ? "" : obj[next.text] = true && cur.push(next);
	     		    return cur;
	     		},[]) // 设置cur默认类型为数组，并且初始值为空的数组
	     		log(person);
	     		dataList=person;
	     		for(var i=0 ;i<dataList.length;i++){
	     			var nn="<option value='"+dataList[i].text+"'>"+dataList[i].text+"</option>";
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
	     		var person = dataList.reduce((cur,next) =>{
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
			elem: '#hospitalViolationTable1'
			,url: $WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getMapData1JS'
			,height: 250
		
			,limit:10
			,where: {  ilegalChild: '1'  }
		    ,cols: [[
			 {type: 'numbers',align:"center", title: '序号' }
			,{field:'pname',align:"center",title: '统筹区名称'}
			,{field:'ptime',align:"center", title: '违规人次',templet:function(d){
				codex = d.ptime ;
				if(codex == ""|| codex == null || codex == "null"){
					codex='-';
				}
				return codex;
			}}
			,{field:'pnumber',align:"center",title: '违规人数',templet:function(d){
				codex = d.pnumber ;
				if(codex == ""|| codex == null || codex == "null" |codex==0){
					codex='-';
				}
				return codex;
			}}
			
			]]
		,page: true
		}); 
		table.render({
			elem: '#hospitalViolationTable'
				,url: $WEB_ROOT_PATH+'/dhccApi/superviseRes/superviseRes/getIllegalByArea1JS'
				,height: 250	
				,limit:5
				,where: {  ilegalChild: '1'  }
		,cols: [[
			{type: 'numbers', title: '序号' }
			/*,{field:'cityName',title: '城市名称'}*/
			,{field:'city', title: '统筹区名称',align:"center"}
			,{field:'money', title: '扣减金额',align:"center",templet:function(d){
				codex = d.money ;
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
			console.log(field);
			//执行重载
			layui.table.reload('hospitalViolationTable', {
				where: field
			});
		});
		form.on('submit(LAY-user-front-search1)', function(data){
			var field = data.field;
			console.log(field);
			//执行重载
			layui.table.reload('hospitalViolationTable1', {
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
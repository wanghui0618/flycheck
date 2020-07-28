<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<script
	src="<%=request.getContextPath()%>/js/echarts_home/echarts.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<title>平均住院天数分析</title>
<style type="text/css">
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
	<div class="layui-fluid" style="overflow: hidden;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card" style="line-height: 40px;">
					<div style="float: left; margin-left: 10px; margin-right: 10px;">
						<h3>
							<font color='red' id="tcqAllInfo">
								
							</font>
						</h3>
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
							统筹区平均住院天数

							<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
						</div>
						<div class="layui-card-body">
							<div class="layui-tab layui-tab-brief"
								lay-filter="docDemoTabBrief">
								<ul class="layui-tab-title"
									style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
									<li class="layui-this"
										style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
										>Top10</li>
									<img style="height: 25px; width: 2px;"
										src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
									<li
										style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
										>全部</li>
								</ul>
								<div class="layui-tab-content"
									style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
									<div class="layui-tab-item layui-show" >
										<div id="line1" style="height: 355px; padding-left: 5px"></div>
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
															<!-- <input type="text" style="width: 120px;"
												name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
											class="layui-input"> -->
												   <input id="getHanddingName"/>
                        							<input type="text" id="handdingName" name="hosanalysisVo.area" style="display: none;" />
															<!-- <select id="zyOrgName" style="width: 150px;"
																name="hosanalysisVo.area" lay-verify="" lay-search=" ">
																<option value="" disabled selected
																	style='display: none;'>请选择统筹区</option>
															</select> -->
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
											<table id="addressTable" class="layui-hide"
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
						style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						平均住院天数月度趋势
					</div>
						<!-- <div class="layui-inline ">
						<label class="layui-form-label ">年度选择</label>
						<div class="layui-input-inline" style="width: 180px;">
							<input id="Time" name="inFlag" lay-filter="Time"
								type="text" class="layui-input" placeholder="yyyy" >
						</div>
					</div> -->
					<div class="layui-card-body">
						<div id="main1" style="width: 100%; height: 330px; ">
					</div>

					</div>
				</div>
			</div>
			
		</div>
		

		<div class="layui-row layui-col-space15">
			
					<div class="layui-col-md6"
						style="padding-left: 5px; padding-right: 5px;">
						<div class="layui-card">
							<div class="layui-card-header"
								style="border-bottom: 1px solid #f6f6f6;">
								<img style="margin-top: -2px; padding-right: 8px;"
									src="<%=request.getContextPath()%>/images/auditing/mark.png" />
								医院平均住院天数

								<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
							</div>
							<div class="layui-card-body">
								<div class="layui-tab layui-tab-brief"
									lay-filter="docDemoTabBrief">
									<ul class="layui-tab-title"
										style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
										<li class="layui-this"
											style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
											>Top10</li>
										<img style="height: 25px; width: 2px;"
											src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
										<li
											style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
											>全部</li>
									</ul>
									<div class="layui-tab-content"
										style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
										<div class="layui-tab-item layui-show" >
											<div id="line2" style="height: 300px; padding-left: 5px"></div>
										</div>
										<div class="layui-tab-item ">
											<div class="layui-card">
												<div
													class="layui-form layui-card-header layuiadmin-card-header-auto"
													style="padding-left: 5px;">
													<div class="layui-form-item">
														<div class="layui-inline pt" style="margin-right: 4px;">
															<label class="layui-form-label">医院名称</label>
															<div class="layui-input-block">
																<!-- <input type="text" style="width: 120px;"
												name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
												class="layui-input"> -->
																<input id="getOrgName"/>
																<input type="text" id="orgName" name="hosanalysisVo.area" style="display: none;" />
																<!-- <select id="orgName" style="width: 150px;"
																	name="hosanalysisVo.area" lay-verify=""
																	lay-search=" ">
																	<option value="" disabled selected
																		style='display: none;'>请选择医院</option>
																</select> -->
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
												<table id="doctorTable" class="layui-hide"
													lay-filter="doctorTest"></table>
											</div>
										</div>

									</div>
								</div>



							</div>
						</div>
					</div>
					<div class="layui-col-md6"
						style="padding-left: 5px; padding-right: 5px;">
						<div class="layui-card">
							<div class="layui-card-header"
								style="border-bottom: 1px solid #f6f6f6;">
								<img style="margin-top: -2px; padding-right: 8px;"
									src="<%=request.getContextPath()%>/images/auditing/mark.png" />
								诊断平均住院天数

								<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
							</div>
							<div class="layui-card-body">
								<div class="layui-tab layui-tab-brief"
									lay-filter="docDemoTabBrief">
									<ul class="layui-tab-title"
										style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
										<li class="layui-this"
											style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
											>Top10</li>
										<img style="height: 25px; width: 2px;"
											src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
										<li
											style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
											>全部</li>
									</ul>
									<div class="layui-tab-content"
										style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
										<div class="layui-tab-item layui-show" >
											<div id="line3" style="height: 300px; padding-left: 5px"></div>
										</div>
										<div class="layui-tab-item ">
											<div class="layui-card">
												<div
													class="layui-form layui-card-header layuiadmin-card-header-auto"
													style="padding-left: 5px;">
													<div class="layui-form-item">
														<div class="layui-inline pt" style="margin-right: 4px;">
															<label class="layui-form-label">病情</label>
															<div class="layui-input-block">
																<!-- <input type="text" style="width: 120px;"
												name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
												class="layui-input"> -->
																<select id="zyOrgName2" style="width: 150px;"
																	name="hosanalysisVo.area" lay-verify=""
																	lay-search=" ">
																	<option value="" disabled selected
																		style='display: none;'>请选择病情</option>
																</select>
															</div>
														</div>
														<button
															style="display: inline-block; position: absolute; margin-top: 8px; padding: 0px 7px"
															class="layui-btn layui-btn-sm layuiadmin-btn-useradmin"
															lay-submit lay-filter="LAY-user-front-search2">
															<i
																class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
														</button>

													</div>
												</div>
												<table id="condidtionTable" class="layui-hide"
													lay-filter="conditionTest"></table>
											</div>
										</div>

									</div>
								</div>



							</div>
						</div>
					</div>
				
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/handdingDictSelect.js"></script>
		<script src="<%=request.getContextPath()%>/app/js/indicator/hospitalizationAnalysis.js"></script>
<!-- 		<script>
		$.ajax({
			url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getZYTitleInfo"
			,type:'get'
			,success:function(data){
				$('#tcqAllInfo').html("全市平均住院天数："+parseInt(data)+"天");
			}
		});
		
	</script>
 -->
 <script>
 $("#Time").change(function(){
 })
 </script>
 
		<script>
	 	var dataAxis1 =new Array();
		var strNumber1=new Array();
		$.ajax({
			url: $WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getInhosDayByArea",
			type:"get",
			success:function(data1){
				var data = data1.data
				for(var index in data){
					if(index == 11 || index > 10) break;
					var money = data[index].pnumber;
					money = money == null ? 0 :money;
					money = money == ""  ? 0 : money; 
					dataAxis1[index] = data[index].area;
					strNumber1[index] = parseFloat(Math.round(data[index].pnumber*100)/100);	
				}
				
				// 路径配置
				require.config({
					paths : {
						echarts : $WEB_ROOT_PATH
								+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
					}
				});
				// 使用
				require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
				'echarts/chart/line' ], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var myChart = ec.init(document.getElementById('line1'));
			var	option = {
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    legend: {
					        data: ['平均住院天数']
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
					            name: '平均住院天数',
					            type: 'line',
					            data: strNumber1
					        }
					    ]
					};
				
					myChart.setOption(option);
					var ecConfig = require('echarts/config');  
					myChart.on(ecConfig.EVENT.CLICK, flush);
				});
				
			}
			});
		
		
		

	</script>
		<script>
	 	var dataAxis2 =new Array();
		var strNumber2=new Array();
		$.ajax({
			url: $WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getInhosDayByHos",
			type:"get",
			success:function(data1){
				var data = data1.data
				for(var index in data){
					if(index == 11 || index > 10) break;
					var money = data[index].pnumber;
					money = money == null ? 0 :money;
					money = money == ""  ? 0 : money; 
					dataAxis2[index] = data[index].area;
					strNumber2[index] = parseFloat(Math.round(data[index].pnumber*100)/100);	
				}
				
				// 路径配置
				require.config({
					paths : {
						echarts : $WEB_ROOT_PATH
								+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
					}
				});
				// 使用
				require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
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
					        data: ['平均住院天数']
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
					            data:dataAxis2
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
					            name: '平均住院天数',
					            type: 'bar',
					            data: strNumber2,
					            itemStyle:{  
 				                     normal:{  
 				                       color:'#2284FF',  
 				                       }  
 				                },  
					        }
					    ]
					};
				
					myChart.setOption(option);
					var ecConfig = require('echarts/config');  
					myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
					
				});
				
			}
			});
		
	

	</script>

		<script>
	 	var dataAxis3 =new Array();
		var strNumber3=new Array();
		$.ajax({
			url: $WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByCondition",
			type:"get",
			success:function(data1){
				var data = data1.data
				for(var index in data){
					if(index == 11 || index > 10) break;
					var money = data[index].pnumber;
					money = money == null ? 0 :money;
					money = money == ""  ? 0 : money; 
					dataAxis3[index] = data[index].area;
					strNumber3[index] = parseFloat(Math.round(data[index].pnumber*100)/100);
					
				}
				
				// 路径配置
				require.config({
					paths : {
						echarts : $WEB_ROOT_PATH
								+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
					}
				});
				// 使用
				require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
				'echarts/chart/line' ], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var myChart = ec.init(document.getElementById('line3'));
			var	option = {
					   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    legend: {
					        data: ['平均住院天数']
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
					            data:dataAxis3
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
					            name: '平均住院天数',
					            type: 'bar',
					            data: strNumber3,
					            itemStyle:{  
 				                     normal:{  
 				                       color:'#00bb00',  
 				                       }  
 				                }, 
					        }
					    ]
					};
				
					myChart.setOption(option);
				});
				
				}
			});
		</script>
		<script>
		function eConsoleSpread(params){
			var name = params.name;
			var dataAxis3 =new Array();
			var strNumber3=new Array();
			$.ajax({
				url: $WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByName?name="+name,
				type:"get",
				success:function(data1){
					var data = data1.data
					for(var index in data){
						if(index == 11 || index > 10) break;
						var money = data[index].pnumber;
						money = money == null ? 0 :money;
						money = money == ""  ? 0 : money; 
						dataAxis3[index] = data[index].area;
						strNumber3[index] = parseFloat(Math.round(data[index].pnumber*100)/100);
					}
					
					// 路径配置
					require.config({
						paths : {
							echarts : $WEB_ROOT_PATH
									+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
						}
					});
					// 使用
					require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
					'echarts/chart/line' ], function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = ec.init(document.getElementById('line3'));
				var	option = {
						   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
						    tooltip: {
						        trigger: 'axis',
						        axisPointer: {
						            type: 'shadow'
						        }
						    },
						    legend: {
						        data: ['平均住院天数']
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
						            data:dataAxis3
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
						            name: '平均住院天数',
						            type: 'bar',
						            data: strNumber3,
						            itemStyle:{  
	  				                     normal:{  
	  				                       color:'#00bb00',  
	  				                       }  
	  				                },  
						        }
						    ]
						};
						myChart.setOption(option);
					});
					
					}
				});
			var path =$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByNameList?name="+name;
			layui.table.reload('condidtionTable',{
				 url: path
		     });
		}
		</script>
		<script >
		function flush(params){
			console.log(params);
			var handdingName=params.name;
			var dataAxis2 =new Array();
			var strNumber2=new Array();
			$.ajax({
				url: $WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getInhosDayByHos",
				type:"post",
				async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
				data : {
					"handdingName" : handdingName
				},
				success:function(data1){
					var data = data1.data
					for(var index in data){
						if(index == 11 || index > 10) break;
						var money = data[index].pnumber;
						money = money == null ? 0 :money;
						money = money == ""  ? 0 : money; 
						dataAxis2[index] = data[index].area;
						strNumber2[index] = parseFloat(Math.round(data[index].pnumber*100)/100);	
					}
					
					// 路径配置
					require.config({
						paths : {
							echarts : $WEB_ROOT_PATH
									+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
						}
					});
					// 使用
					require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
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
						        data: ['平均住院天数']
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
						            data:dataAxis2
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
						            name: '平均住院天数',
						            type: 'bar',
						            data: strNumber2,
						            itemStyle:{  
	 				                     normal:{  
	 				                       color:'#2284FF',  
	 				                       }  
	 				                },  
						        }
						    ]
						};
					
						myChart.setOption(option);
						var ecConfig = require('echarts/config');  
						myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
						
					});
					
				}
				});
			
			
			$.ajax({
		    	  url: $WEB_ROOT_PATH + "/dhccApi/admin/admin/InhosNumber",/* ?inFlag="+value, */
		    	type:"post",
		    	async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
				data : {
					"handdingName" : handdingName
				},
		    	success:function(data){
		    	 	 if(data.code==22){
              		 alert('会话过期，请重新登录');
						window.top.location.href=$WEB_ROOT_PATH;
              	 }
		    		var data=data.data;
		    	    yearArr= new Array();
		    	     valueArr = new Array();
		    		for(var index in data){
		    			var year = data[index].year;
		    			yearArr[index]=data[index].year;
		    			valueArr[index]=data[index].pnumber;
		    		}
		        // 基于0准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main1'));

		     // 指定图表的配置项和数据
		        var option = {
		            title: {
		                text: ''
		            },
		            tooltip: {},
		            legend: {
		                data:['天数']
		            },
		            xAxis: {
			            type: 'category',
		                data: yearArr,
		                axisLabel: {
                            interval:0,
                            rotate:16
                         }
		            },
		            yAxis: {},
		            series: [{
		                name: '天数',
		                type: 'line',
		                data: valueArr
		            }]
		        };
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);},error:function(data){
		        	}
		        });
			
		}
		
		</script>
		

<script type="text/javascript">
    var total = "";
    var yearArr;
    var valueArr ;
    var date = new Date();
	var today_nian = date.getFullYear();
    var str = today_nian;
    $.ajax({
    	  url: $WEB_ROOT_PATH + "/dhccApi/admin/admin/InhosNumber?inFlag="+str,
    	type:"post",
    	success:function(data){
    		var data=data.data;
    	    yearArr= new Array();
    	     valueArr = new Array();
    		for(var index in data){
    			var year = data[index].year;
    			yearArr[index]=data[index].year;
    			valueArr[index]=data[index].pnumber;
    		}
        // 基于0准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main1'));

     // 指定图表的配置项和数据
        var option = {
            title: {
                text: ''
            },
            tooltip: {},
            legend: {
                data:['天数']
            },
            xAxis: {
            	type: 'category',
                data: yearArr,
                axisLabel: {
                    interval:0,
                    rotate:16
                 }
            },
            yAxis: {},
            series: [{
                name: '天数',
                type: 'line',
                data: valueArr
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);},error:function(data){
        	}
        });
    
    layui.config({
 		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
 	}).extend({
 		index: 'lib/index' //主入口模块
 	}).use(['index', 'table','laydate'], function(){
 		var $ = layui.$
 		,form = layui.form
 		,table = layui.table
 		,laydate=layui.laydate;
 		laydate.render({
 			  elem: '#Time'
 				  ,type:"year"
 					  ,trigger:'click'
 						 ,value: new Date() 
 						   ,isInitValue:true
 						      ,done:function(value){  
 						    	  dateChange(value);
 						      }
 		  }); 
 		function dateChange(value){
 			 $.ajax({
 		    	  url: $WEB_ROOT_PATH + "/dhccApi/admin/admin/InhosNumber?inFlag="+value,
 		    	type:"post",
 		    	success:function(data){
 		    	 	 if(data.code==22){
                		 alert('会话过期，请重新登录');
 						window.top.location.href=$WEB_ROOT_PATH;
                	 }
 		    		var data=data.data;
 		    	    yearArr= new Array();
		    	     valueArr = new Array();
 		    		for(var index in data){
 		    			var year = data[index].year;
 		    			yearArr[index]=data[index].year;
 		    			valueArr[index]=data[index].pnumber;
 		    		}
 		        // 基于0准备好的dom，初始化echarts实例
 		        var myChart = echarts.init(document.getElementById('main1'));

 		     // 指定图表的配置项和数据
 		        var option = {
 		            title: {
 		                text: ''
 		            },
 		            tooltip: {},
 		            legend: {
 		                data:['天数']
 		            },
 		            xAxis: {
 		            	type: 'category',
		                data: yearArr,
		                axisLabel: {
                            interval:0,
                            rotate:16
                         }
 		            },
 		            yAxis: {},
 		            series: [{
 		                name: '天数',
 		                type: 'line',
 		                data: valueArr
 		            }]
 		        };
 		        // 使用刚指定的配置项和数据显示图表。
 		        myChart.setOption(option);},error:function(data){
 		        	}
 		        });
 		}
 		});
    </script>
	<script>
  layui.config({ base: '<%=request.getContextPath()%>/plugins/layui/layuiadmin/' //静态资源所在路径
		}).extend({
			index : 'lib/index' //主入口模块
		}).use([ 'index', 'console' ]);
	</script>
</body>
</html>
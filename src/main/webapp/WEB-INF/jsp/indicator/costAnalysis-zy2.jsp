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
<%@include file="/WEB-INF/jsp/common/easyui.jsp"%>
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
<title>次均费用分析</title>
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
							<font color='red' id="tcqAllInfo">住院全市次均费用 ： </font>
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
						统筹区住院次均费用

						<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title"
								style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
								<li class="layui-this"
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">Top10</li>
								<img style="height: 25px; width: 2px;"
									src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
								<li
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">全部</li>
							</ul>
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show">
									<div id="line1" style="height: 310px; padding-left: 5px"></div>
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
														<!-- <select id="zyOrgName" style="width: 150px;"
															name="hosanalysisVo.area" lay-verify="" lay-search=" ">
															<option value="" disabled selected style='display: none;'>请选择统筹区</option>
														</select> -->
														<input id="getHanddingName" /> <input type="text"
															id="handdingName" name="hosanalysisVo.area"
															style="display: none;" />
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
						2019年住院次均费用月度趋势
					</div>
					<div class="layui-card-body">
						<div id="main1" style="width: 100%; height: 330px;"></div>

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
						住院医院次均费用

						<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title"
								style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
								<li class="layui-this"
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">Top10</li>
								<img style="height: 25px; width: 2px;"
									src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
								<li
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">全部</li>
							</ul>
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show">
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
														<!-- <select id="zyOrgName1" style="width: 150px;"
															name="hosanalysisVo.area" lay-verify="" lay-search=" ">
															<option value="" disabled selected style='display: none;'>请选择医院</option>
														</select> -->
														<input id="getOrgName" /> <input type="text" id="orgName"
															name="hosanalysisVo.area" style="display: none;" />
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
						住院诊断次均费用

						<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title"
								style="height: 20px; line-height: 15px; border: none; margin-top: -28px; float: right;">
								<li class="layui-this"
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">Top10</li>
								<img style="height: 25px; width: 2px;"
									src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
								<li
									style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">全部</li>
							</ul>
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show">
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
															name="hosanalysisVo.area" lay-verify="" lay-search=" ">
															<option value="" disabled selected style='display: none;'>请选择病情</option>
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


	
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/common/orgDictSelect.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/common/handdingDictSelect.js"></script>
	<script
		src="<%=request.getContextPath()%>/app/js/indicator/costAnalysis-zy2.js"></script>
	<!-- 头信息 全市的次均费用  -->
	<script>
$.ajax({
	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getZYMTitleInfo"
	,type:'get'
	,success:function(data){
		$('#tcqAllInfo').html("住院全市次均费用 ："+parseFloat(data).toFixed(2)+"元");
	}
});
</script>
	<script>
function changeNumber(num){

	   if(num && num!=null){  
	        num = String(num);  
	        var left=num.split('.')[0],right=num.split('.')[1];  
	        right = right ? (right.length>=2 ? '.'+right.substr(0,2) : '.'+right+'0') : '.00';  
	        var temp = left.split('').reverse().join('').match(/(\d{1,3})/g);  
	        return (Number(num)<0?"-":"") + temp.join(',').split('').reverse().join('')+right;  
	    }else if(num===0){   //注意===在这里的使用，如果传入的num为0,if中会将其判定为boolean类型，故而要另外做===判断  
	        return '0.00';  
	    }else{  
	        return "";  
	    }  
}

</script>
	<!-- 统筹区的top10 饼图 -->
	<script>
var totalMoney = 0;
var dataYear1 = new Array();
var dataArr =new Array()
function data1(value,name){
	var o = new Object();
	o.value=value;
	o.name=name;
	return o;
}
$.ajax({
	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getAllAreaDataZR",
	type:"post",
	success:function(data){
		console.log(data);
		for(var index  in data){
			totalMoney += parseFloat(data[index].pavgcost);
			dataYear1[index]=data[index].area;
			var dataPie = data1(parseFloat(data[index].pavgcost).toFixed(2),data[index].area);
			dataArr[index] = dataPie;
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
			
		
			var myChart = echarts.init(document.getElementById('line1'));
		     var   option = {
		    		 tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    toolbox: {
						        show: true,
						        top: 5,
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
					            data:dataYear1
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
					            name: '均次费用',
					            type: 'line',
					             data:dataArr
					             
					            
					        }
					    ]
					};
				
					myChart.setOption(option);
					var ecConfig = require('echarts/config');  
					myChart.on(ecConfig.EVENT.CLICK, fresh);
				});
				
				}
			});
		
/* 		var path =$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByNameList?name="+name;
		layui.table.reload('condidtionTable',{
			 url: path
	     });
 */		
		
	
		    
</script>
<script>
	function fresh(params){
		console.log(params);
		//折线图
		 var total = "";
		    var yearArr= new Array();
		    var valueArr = new Array();
		    var yearFlag = "2017";
		    var hangddingName=params.name;
		    $.ajax({
		    	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getPCostByMonthZY",
		    	type:"post",
		    	data:{
		    		"handdingName":hangddingName
		    	},
		    	success:function(data){
		    		console.log(data);
		    		for(var index in data){
		    			var year = data[index].year;
		    		
		    			yearArr[index]=data[index].year;
		    		
		    			//yearFlag = data[index].year.substring(0,4);
		    			//if(yearFlag == )
		    			//console.log(yearFlag);
		    			
		    			valueArr[index]=parseFloat(data[index].pnumber).toFixed(2);
		    			
		    		}
		    		
		        // 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main1'));

		     // 指定图表的配置项和数据
		        var option = {
		            title: {
		                text: '2019年住院月度费用趋势'
		            },
		            tooltip: {},
		            legend: {
		                data:['住院月度次均费用']
		            },
		            xAxis: {
		                data: yearArr,
		                axisLabel:{
		                    //X轴刻度配置
		                    interval:0 //0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
		               }
		            },
		            yAxis: {},
		            series: [{
		                name: '住院月度次均费用',
		                type: 'line',
		                data: valueArr,
		               
		            }]
		        };

		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
		        	},error:function(data){
		        		}
		        	});
		
		
		   //第三张图  柱状图
		    var totalMoney2 = 0;
		    var dataYear2 = new Array();
		    var dataArr2 =new Array()
		    function data2(value,name){
		    	var o = new Object();
		    	o.value=value;
		    	o.name=name;
		    	return o;
		    }

		    //路径配置
		    require.config({
		    	paths : {
		    		echarts : $WEB_ROOT_PATH
		    				+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
		    	}
		    });
		    // 使用
		    require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
		    'echarts/chart/line' ], function(ec) {
		    var myChart = echarts.init(document.getElementById('line2'));
		    $.ajax({
		    	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getAllYYDataZR",
		    	type:"post",
		    	data:{
		    		"handdingName":hangddingName
		    	},
		    	success:function(data){
		    		for(var index  in data){
		    			totalMoney2 += parseFloat(data[index].pavgcost);
		    			dataYear2[index]=data[index].area;
		    			var dataPie = data2(parseFloat(data[index].pavgcost).toFixed(2),data[index].area);
		    			dataArr2[index] = dataPie;
		    		}
		    			
		    		     var   option = {
		    		    		 tooltip: {
		    					        trigger: 'axis',
		    					        axisPointer: {
		    					            type: 'shadow'
		    					        }
		    					    },
		    					    
		    					    toolbox: {
		    						        show: true,
		    						        top: 5,
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
		    					            data:dataYear2
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
		    					            name: '费用',
		    					            type: 'bar',
		    					             data:dataArr2,
		    					             itemStyle:{  
		      				                     normal:{  
		      				                       color:'#2284FF',  
		      				                       }  
		      				                },  
		    					        }
		    						    ]
		    						};
		    					
		    						myChart.setOption(option);
		    						myChart.on("click", eConsoleSpread);
		    	},error:function(){
		    		//var ecConfig = require('echarts/config');  
		    		//myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
		    		
		    	} 
		    					});
		    					
		    				
		    				}); 
		    		   
		    
		    
	}
</script>
	<!-- 医院的top10  -->
	<script>
var totalMoney2 = 0;
var dataYear2 = new Array();
var dataArr2 =new Array()
function data2(value,name){
	var o = new Object();
	o.value=value;
	o.name=name;
	return o;
}

//路径配置
require.config({
	paths : {
		echarts : $WEB_ROOT_PATH
				+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
	}
});
// 使用
require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
'echarts/chart/line' ], function(ec) {
var myChart = echarts.init(document.getElementById('line2'));
$.ajax({
	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getAllYYDataZR",
	type:"post",
	success:function(data){
		for(var index  in data){
			totalMoney2 += parseFloat(data[index].pavgcost);
			dataYear2[index]=data[index].area;
			var dataPie = data2(parseFloat(data[index].pavgcost).toFixed(2),data[index].area);
			dataArr2[index] = dataPie;
		}
			
		     var   option = {
		    		 tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    
					    toolbox: {
						        show: true,
						        top: 5,
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
					            data:dataYear2
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
					            name: '费用',
					            type: 'bar',
					             data:dataArr2,
					             itemStyle:{  
  				                     normal:{  
  				                       color:'#2284FF',  
  				                       }  
  				                },  
					        }
						    ]
						};
					
						myChart.setOption(option);
						myChart.on("click", eConsoleSpread);
	},error:function(){
		//var ecConfig = require('echarts/config');  
		//myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
		
	} 
					});
					
				
				}); 
		   
		

/* app.title = '环形图'; */
// 使用刚指定的配置项和数据显示图表。

</script>
	<!-- 病情的top10  -->
	<script>
var totalMoney3 = 0;
var dataYear3 = new Array();
var dataArr3 =new Array()
function data3(value,name){
	var o = new Object();
	o.value=value;
	o.name=name;
	return o;
}
//路径配置
require.config({
	paths : {
		echarts : $WEB_ROOT_PATH
				+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
	}
});
// 使用
require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
'echarts/chart/line' ], function(ec) {

$.ajax({
	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByZYCondition",
	type:"post",
	success:function(data1){
		var data = data1.data;
		for(var index  in data){
			totalMoney3 += parseFloat(data[index].pnumber);
			dataYear3[index]=data[index].area;
			var dataPie = data3(parseFloat(data[index].pnumber).toFixed(2),data[index].area);
			dataArr3[index] = dataPie;
		}
		// 路径配置
		/* require.config({
			paths : {
				echarts : $WEB_ROOT_PATH
						+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
			}
		});
		// 使用
		require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
		'echarts/chart/line' ], function(ec) { */
			var myChart = echarts.init(document.getElementById('line3'));
		     var   option = {
		    		 tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    
					    toolbox: {
						        show: true,
						        top: 5,
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
					            data:dataYear3
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
					            name: '天数',
					            type: 'bar',
					             data:dataArr3,
					             itemStyle:{  
  				                     normal:{  
  				                       color:'#00bb00',  
  				                       }  
  				                },  
					        }
						    ]
						};
					
						myChart.setOption(option);

		    		 
		    		 
		    /* tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}:<br> 次均费用{c}元({d}%)"
		    },
			title: {
		        "text": '病情住院次均费用',
		        subtext:changeNumber(Math.round(totalMoney3))+'元',
		        //itemGap:90,
		        "x": '110px',
		        "y": '90px',
		         textAlign: "center",
		        "subtextStyle": {
		            "fontWeight": 'normal',
		            "fontSize": 14,
		            color:'black',
		            
		        }
			}, 
		    series: [
		        {
		            name:'病情住院次均费用',
		            type:'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:dataArr3
		        }
		    ]
		 */
			myChart.on("click", eConsoleSpread);
},error:function(){
//var ecConfig = require('echarts/config');  
//myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);

} 
		});
		
	
	}); 
 
/* app.title = '环形图'; */
// 使用刚指定的配置项和数据显示图表。

</script>
	<script>
function eConsoleSpread(param){
	var name = param.name;
	var totalMoney3 = 0;
  	var dataYear3 = new Array();
  	var dataArr3 =new Array()
  	function data3(value,name){
  		var o = new Object();
  		o.value=value;
  		o.name=name;
  		return o;
  	}


  	$.ajax({

  		url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getZYConditionByName?name="+name,
  		type:"post",
  		success:function(data1){
  			var data = data1.data;
  			for(var index  in data){
  				totalMoney3 += parseFloat(data[index].pnumber);
  				dataYear3[index]=data[index].area;
  				var dataPie = data3(parseFloat(data[index].pnumber).toFixed(2),data[index].area);
  				dataArr3[index] = dataPie;
  			}
  			// 路径配置
  			/* require.config({
  				paths : {
  					echarts : $WEB_ROOT_PATH
  							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
  				}
  			});
  			// 使用
  			require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
  			'echarts/chart/line' ], function(ec) { */
  				var myChart = echarts.init(document.getElementById('line3'));
  			     var   option = {
  			    		 tooltip: {
  						        trigger: 'axis',
  						        axisPointer: {
  						            type: 'shadow'
  						        }
  						    },
  						 
  						    
  						    toolbox: {
  						        show: true,
  						        top: 5,
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
  						            data:dataYear3
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
  						            name: '天数',
  						            type: 'bar',
  						             data:dataArr3,
  						           itemStyle:{  
  				                     normal:{  
  				                       color:'#00bb00',  
  				                       }  
  				                },  
  						        }
  							    ]
  							};
  						
  							myChart.setOption(option);
  		
  		/* 
  	}
  		url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getZYConditionByName?name="+name,
  		type:"post",
  		success:function(data1){
  			var data = data1.data;
  			for(var index  in data){
  				totalMoney3 += parseFloat(data[index].pnumber);
  				dataYear3[index]=data[index].area;
  				var dataPie = data3(parseFloat(data[index].pnumber).toFixed(2),data[index].area);
  				dataArr3[index] = dataPie;
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
  				var myChart = echarts.init(document.getElementById('line3'));
  			     var   option = {
  			    tooltip: {
  			        trigger: 'item',
  			        formatter: "{a} <br/>{b}:<br> 次均费用{c}元({d}%)"
  			    },
  			 
  			    series: [
  			        {
  			            name:'病情住院次均费用',
  			            type:'pie',
  			            radius: ['50%', '70%'],
  			            avoidLabelOverlap: false,
  			            label: {
  			                normal: {
  			                    show: false,
  			                    position: 'center'
  			                },
  			                emphasis: {
  			                    show: true,
  			                    textStyle: {
  			                        fontSize: '30',
  			                        fontWeight: 'bold'
  			                    }
  			                }
  			            },
  			            labelLine: {
  			                normal: {
  			                    show: false
  			                }
  			            },
  			            data:dataArr3
  			        }
  			    ]
  			};
  		
  			myChart.setOption(option); */
  			/* var ecConfig = require('echarts/config');  
  			myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread); */
  			myChart.on('click', function (param) {
  				console.log(param);
  			});
  			
  		//});
  		
  		
  		},error:function(){
  		
  		
  		}
  	 
  	/* app.title = '环形图'; */
  	// 使用刚指定的配置项和数据显示图表。
  	});
  	var path =$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getZYConditionByNameList?name="+name;
	
	layui.table.reload('condidtionTable',{
		 url: path,
		 limit:5,
		 page:true
     });
}

</script>

	<script type="text/javascript">
    var total = "";
    var yearArr= new Array();
    var valueArr = new Array();
    var yearFlag = "2017";
    $.ajax({
    	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getPCostByMonthZY",
    	type:"post",
    	success:function(data){
    		console.log(data);
    		for(var index in data){
    			var year = data[index].year;
    		
    			yearArr[index]=data[index].year;
    		
    			//yearFlag = data[index].year.substring(0,4);
    			//if(yearFlag == )
    			//console.log(yearFlag);
    			
    			valueArr[index]=parseFloat(data[index].pnumber).toFixed(2);
    			
    		}
    		
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main1'));

     // 指定图表的配置项和数据
        var option = {
            title: {
                text: '2019年住院月度费用趋势'
            },
            tooltip: {},
            legend: {
                data:['住院月度次均费用']
            },
            xAxis: {
                data: yearArr,
                axisLabel:{
                    //X轴刻度配置
                    interval:0 //0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
               }
            },
            yAxis: {},
            series: [{
                name: '住院月度次均费用',
                type: 'line',
                data: valueArr
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        	},error:function(data){
        		}
        	});
    </script>
	

 
   
	<script>
  layui.config({
base: '<%=request.getContextPath()%>/plugins/layui/layuiadmin/' //静态资源所在路径
							}).extend({
						index : 'lib/index' //主入口模块
					}).use([ 'index', 'console' ]);
		</script>
</body>
</html>
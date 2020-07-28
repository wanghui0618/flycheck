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
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<title>医院违规数据</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body>
<div>
<!-- 隐藏a标签，用于跳转模拟 -->
<div style="display: none;" id="tz-hide"  href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicaltab-jpp-zs"></div>
<div class="layui-row">
<div class="layui-col-md6" id="parent" >
<div class="grid-demo grid-demo-bg1" id="child" >
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item" >
					<!-- <div class="layui-inline pt">
						<label class="layui-form-label ">城市名称</label>
						
						<div class="layui-input-block " style="width: 120px;">
							<select name="hospitalViolation.cityName" id="city" lay-search=" ">
								<option value="" disabled selected style='display:none;'>请选择城市</option>
							</select>
						</div>
					</div> -->
					
					<div class="layui-inline">
						<label class="layui-form-label">机构名称</label>
						<div class="layui-input-inline">
							<!-- <input type="text" style="width: 120px;"
								name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
								class="layui-input"> -->
								<input id="getOrgName"/>
                                <input type="text" id="orgCode" name="hospitalViolation.orgCode" style="display: none;" />
								<!-- <select id="zyOrgName" style="width: 190px;" name="hospitalViolation.orgCode" lay-verify="" lay-search=" ">
                                    <option value="" disabled selected style='display:none;'>请选择医院</option>
                                </select> -->
						</div>
					</div>
					
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search1">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<div class="layui-inline">
						<button id="violation-dc" class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main" 
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
	<div class="layui-col-md6"  >
    <div class="grid-demo" >
    <div class="layui-fluid">
    <div class="layui-card" style="width:101%;margin-left:-10px;" >
    	<div class="layui-card-header" style="height:20px">医院违规TOP10
    	<button id="yiyuan" href="javascript:;" style="margin-top: 10px;margin-left: 30px;z-index: 9999;position: absolute;" class="layui-btn  layui-btn-xs">医院</button>
    	</div>
  		<div class="layui-card-body">
   			<div id="main" style="margin-left:-10px"></div>
  		</div>	
    </div>
    </div>
    </div>
    </div>
    <div class="layui-col-md12" style="height: 500px">
				<div id="card1" class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 500px">
					<div class="layui-card" style="height: 490px;">
						<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;"
								 src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
							规则违规统计

						</div>
						<div class="layui-card-body">
							<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
								<ul class="layui-tab-title"
									style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
									<li class="layui-this"
										style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" id="change1">
										违规金额
									</li>
									<img style="height: 25px;width: 2px;"
										 src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
									<li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" id="change2">
										违规明细
									</li>
								</ul>
								<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
									<div class="layui-tab-item layui-show" id="main1" style="height:350px;margin-left:25px;"></div>
									<div class="layui-tab-item"  id="main2" style="height:350px;margin-left:25px;"></div>
									<!-- <div class="layui-tab-item layui-show" id="mainGrap1" style="height:300px;">
										<div id="main1" style="height:300px;margin-left:25px;"></div>
									</div>
									<div class="layui-tab-item" id="mainGrap2" style="height:300px;">
										<div id="main2"  style="height:300px;margin-left:25px;"></div>
									</div> -->

								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="card2" class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 500px">
					<div class="layui-card" style="height: 490px;">
						<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top:-2px;padding-right: 8px;"
								 src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
							医保项目违规统计

						</div>
						<div class="layui-card-body">
							<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
								<ul class="layui-tab-title"
									style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
									<li class="layui-this"
										style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" id="change3">
										违规金额
									</li>
									<img style="height: 25px;width: 2px;"
										 src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
									<li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" id="change4">
										违规明细
									</li>
								</ul>
								<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
									<div class="layui-tab-item layui-show" id="main3" style="height:350px;margin-left:25px;"></div>
									<div class="layui-tab-item" id="main4" style="height:350px;margin-left:25px;"></div>
									<!-- <div class="layui-tab-item layui-show" id="mainGrap3" style="height:300px;">
										<div id="main3" style="height:300px;margin-left:25px;"></div>
									</div>
									<div class="layui-tab-item" id="mainGrap4" style="height:300px;">
										<div id="main4"  style="height:300px;margin-left:25px;"></div>
									</div> -->

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
    
	</div>
	</div>
	<a id='yl' style="display:none" href='javascript:;' lay-href='<%=request.getContextPath()%>/medical/medical' lay-tips='病例审核'>病例审核</a>
	<!-- ECharts单文件引入 -->
	 <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/hospitalViolation/hospitalViolation.js"></script>
	 <script type="text/javascript">
	 document.getElementById("main").style.height=document.documentElement.clientHeight-48+"px";
	 // 路径配置
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
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
    <script type="text/javascript">
    function reloadTable(orgCode,cityName,statusUrl,comType,handdingInsName) {
   	 require(['echarts','echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
   		 	  ],
   	          function (echarts) {
   					echarts.init(document.getElementById('main')).dispose();//销毁前一个实例
   					memoBar = echarts.init(document.getElementById('main'));//构建下一个实例
   				 	
   		 var option = {
                    tooltip: {
                        show: true
                    },
                    padding: [0, 0, 10, 10],  // 位置
                    legend: {
                        padding: 10,    // [5, 10, 15, 20]
                        itemGap: 20,
                        left:'right',
                        data:['违规类型分布TOP10统计']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : [],
                            axisLabel : {//坐标轴刻度标签的相关设置。
                                interval:0,
                                rotate:"15"
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
                            barWidth:30,
                            barMaxWidth:20,
                            "data":[],
                            itemStyle:{
                                normal:{
                                    color:'#419bf9'
                                }
                            }
                        }
                    ]
   					};
   		 // 为echarts对象加载数据 
            $.ajax({
   		         url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/violationSpread",
   		         type : "post",		
   		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
   		         data:{"hospitalViolation.orgCode":orgCode,"hospitalViolation.cityName":cityName,"status":statusUrl,"comType":comType,"handdingInsName":handdingInsName},
   		         success : function(result) {
   		        	 var hvdata=result;
   		        	 if (hvdata != null && hvdata.length > 0) {
   		        		 var k;
   		        		 if(hvdata.length>10){
   		        			 k=10;
   		        		 }else{
   		        			 k=hvdata.length;
   		        		 }
   		        		 for(var i=0;i<k;i++){ 
   			                   option.series[0].data.push(hvdata[i].countNum);
   			                   option.xAxis[0].data.push(hvdata[i].typeNames);
   			             } 
   		        		 memoBar.setOption(option); 
   		        	 }
   		         }
            });
          //下面是需要添加的方法内容  
            //点击柱状图跳转相应页面的功能，其中param.name参数为横坐标的值   
            var ecConfig = require('echarts/config');  
            
            memoBar.on(ecConfig.EVENT.CLICK, eConsoleSpread); 
   	 }); 
 
    };
	function eConsoleSpread(param){
		 if (typeof param.seriesIndex != 'undefined') { 
		   	 	var str=param.name;
		    	
		   	 	str=encodeURI(encodeURI(str));
		   	 	son=encodeURI(encodeURI(on));
		   	 	soc=encodeURI(encodeURI(oc));
		   	 	scn=encodeURI(encodeURI(cn));
		   	 	if(handdingInsNameAll){	
			   	 	 handdingInsNameAll=decodeURI(handdingInsNameAll);
			   	     handdingInsNameAll=encodeURI(encodeURI(handdingInsNameAll));
		   	 	}else{
		   	 		 handdingInsNameAll=null;
		   	 	}
		    	//默认为初审
		    	if(!status_qj){
		    		status_qj='0';
		    	}
		    	
		    	var tit=status_qj=='0'?"事后病例初审":"事后病历终审";
		    	if(handdingInsNameAll){
		    		var cs="/piccbid/medical/medical/jpp?orgCode="+soc+"&cityName="+scn+"&typeNameTz="+str+"&type=3&handdingInsName="+handdingInsNameAll;
		    	}else{
		    		var cs="/piccbid/medical/medical/jpp?orgCode="+soc+"&cityName="+scn+"&typeNameTz="+str+"&type=3";
		    	}
		    	var zs="/piccbid/medical/medical/jpp-zs?orgCode="+soc+"&cityName="+scn+"&typeNameTz="+str+"&type=3";
		    	
		    	var urlGo=status_qj=='0'?cs:zs;
		    	
		    	$("#tz-hide").attr('lay-href',urlGo);
		    	$("#tz-hide").html(tit);
		    	$('#tz-hide').trigger("click");
		    }  
	};
	
	function reloadTable1(orgCode,statusUrl,comType,handdingInsName) {
		var width=$("#card1").css("width").replace("px","");
		$("#main1").css("width",width*0.93);
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					echarts.init(document.getElementById('main1')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main1'));//构建下一个实例
					var option = {
						tooltip: {
							show: true
						},
						grid:{
							y2:100
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['规则违规金额TOP10统计']
						},
						xAxis : [
							{
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval:0,
									rotate:"20"
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
								"name":"规则违规金额TOP10统计",
								"type":"line",
								barWidth:30,
								barMaxWidth:20,
								"data":[],
								itemStyle:{
									normal:{
										color:'#419bf9'
									}
								}
							}
						]
					};
					// 为echarts对象加载数据
					$.ajax({
						url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/hopVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"hospitalViolation.orgCode":orgCode,"type":"cost","status":statusUrl,"handdingInsName":handdingInsName,"comType":comType},
						success : function(result) {
							var resultData=result.data;
							for(var i=0;i<resultData.length;i++){
								if(resultData[i].typeName==null||resultData[i].typeName==""){
									option.series[0].data.push(resultData[i].itemCost);
									option.xAxis[0].data.push("其它");
								}else{
									option.series[0].data.push(resultData[i].itemCost);
									option.xAxis[0].data.push(resultData[i].typeName);
								}
								
							}
								memoBar.setOption(option);
								//$("#test").attr("class","layui-this");
								//$("#change").attr("class","");

						}
					});
				});
	};
	
	function reloadTable2(orgCode,statusUrl,comType,handdingInsName) {
		var width=$("#card1").css("width").replace("px","");
		$("#main2").css("width",width*0.93);
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					echarts.init(document.getElementById('main2')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main2'));//构建下一个实例
					var option = {
						tooltip: {
							show: true
						},
						grid:{
							y2:100
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['规则违规明细TOP10统计']
						},
						xAxis : [
							{
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval:0,
									rotate:"20"
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
								"name":"规则违规明细TOP10统计",
								"type":"line",
								barWidth:30,
								barMaxWidth:20,
								"data":[],
								itemStyle:{
									normal:{
										color:'#419bf9'
									}
								}
							}
						]
					};
					// 为echarts对象加载数据
					$.ajax({
						url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/hopVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"hospitalViolation.orgCode":orgCode,"type":"count","status":statusUrl,"handdingInsName":handdingInsName,"comType":comType},
						success : function(result) {
							var resultData=result.data;
							for(var i=0;i<resultData.length;i++){
								if(resultData[i].typeName==null||resultData[i].typeName==""){
									option.series[0].data.push(resultData[i].countDetail);
									option.xAxis[0].data.push("其它");
								}else{
									option.series[0].data.push(resultData[i].countDetail);
									option.xAxis[0].data.push(resultData[i].typeName);
								}
								
							}
								memoBar.setOption(option);
								//$("#main2").hide();

								
						}
					});
				});
	};
	
	function reloadTable3(orgCode,statusUrl,comType,handdingInsName) {
		var width=$("#card2").css("width").replace("px","");
		$("#main3").css("width",width*0.93);
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					echarts.init(document.getElementById('main3')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main3'));//构建下一个实例
					var option = {
						tooltip: {
							show: true
						},
						grid:{
							y2:100
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['医保项目违规金额TOP10统计']
						},
						xAxis : [
							{
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval:0,
									rotate:"30"
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
								"name":"医保项目违规金额TOP10统计",
								"type":"line",
								barWidth:30,
								barMaxWidth:20,
								"data":[],
								itemStyle:{
									normal:{
										color:'#419bf9'
									}
								}
							}
						]
					};
					// 为echarts对象加载数据
					$.ajax({
						url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/medVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"hospitalViolation.orgCode":orgCode,"type":"cost","status":statusUrl,"handdingInsName":handdingInsName,"comType":comType},
						success : function(result) {
							var resultData=result.data;
							for(var i=0;i<resultData.length;i++){
								if(resultData[i].itemName==null||resultData[i].itemName==""){
									option.series[0].data.push(resultData[i].itemCost);
									option.xAxis[0].data.push("其它");
								}else{
									option.series[0].data.push(resultData[i].itemCost);
									option.xAxis[0].data.push(resultData[i].itemName);
								}
								
							}
							
								memoBar.setOption(option);

						}
					});
				});
	};
	
	function reloadTable4(orgCode,statusUrl,comType,handdingInsName) {
		var width=$("#card2").css("width").replace("px","");
		$("#main4").css("width",width*0.93);
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					echarts.init(document.getElementById('main4')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main4'));//构建下一个实例
					var option = {
						tooltip: {
							show: true
						},
						grid:{
							y2:100
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['医保项目违规明细TOP10统计']
						},
						xAxis : [
							{
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval:0,
									rotate:"20"
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
								"name":"医保项目违规明细TOP10统计",
								"type":"line",
								barWidth:30,
								barMaxWidth:20,
								"data":[],
								itemStyle:{
									normal:{
										color:'#419bf9'
									}
								}
							}
						]
					};
					// 为echarts对象加载数据
					$.ajax({
						url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/medVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"hospitalViolation.orgCode":orgCode,"type":"count","status":statusUrl,"handdingInsName":handdingInsName,"comType":comType},
						success : function(result) {
							var resultData=result.data;
							for(var i=0;i<resultData.length;i++){
								if(resultData[i].itemName==null||resultData[i].itemName==""){
									option.series[0].data.push(resultData[i].countDetail);
									option.xAxis[0].data.push("其它");
								}else{
									option.series[0].data.push(resultData[i].countDetail);
									option.xAxis[0].data.push(resultData[i].itemName);
								}
								
							}
							
								memoBar.setOption(option);
								//$("#main4").hide();

						}
					});
				});
	};
	
	/*  $("#change2").click(function () {
		$("#main2").attr("class","layui-tab-item layui-hide");
	});
	
	$("#change4").click(function () {
		$("#main4").show();
	});  */
	
    </script>
</body>
</html>
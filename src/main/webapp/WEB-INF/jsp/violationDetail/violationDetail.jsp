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
</head>
<body>
<!-- 隐藏a标签，用于跳转模拟 -->
<div style="display: none;" id="tz-hide"  href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicaltab-jpp-zs"></div>
<div class="layui-fluid" style="overflow: hidden;">
	<div class="layui-row layui-col-space15">

		<div class="layui-col-md12" style="height: 450px">

			<div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;height: 60px">
				<div class="layui-card" style="height: 440px;">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto">
						<div class="layui-inline" >
							<label class="layui-form-label">违规类型</label>
							<div class="layui-input-inline" id="xldown">
								<select id="ruleType" name="violationDetail.typeNo" xm-select="select2" xm-select-direction="down" lay-search>
									<option value="" disabled selected style='display:none;'>请选择类型</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<button id="medical-jpp-detail-search-cx"
									class="layui-btn layuiadmin-btn-useradmin"
									lay-submit lay-filter="LAY-user-front-search2">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
						<div class="layui-inline ">
							<button id="violation-dc" class="layui-btn layui-icon-down-main layuiadmin-btn-useradmin"
									lay-submit lay-filter="LAY-user-front-export">
								<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;">
				<div class="layui-card" >
					<div class="layui-row layui-col-space15">
						<div class="layui-col-md6">
							<table id="violationDetail" class="layui-hide" lay-filter="violationDetail"></table>
						</div>
						<div class="layui-col-md6">
							<div class="layui-card-header" style="height:20px">
								<button id="yiyuan" href="javascript:;" style="margin-right:5%;z-index: 9999;position: absolute;" class="layui-btn  layui-btn-xs">医院</button>
							</div>
							<div class="layui-card-body">
								<div id="main" style="height: 320px;"></div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-col-md12">
			<div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
				<div class="layui-card">
					<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top:-2px;padding-right: 8px;"
							 src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
						医院违规统计
						<div class="layui-input-inline">
							<select id="type" lay-filter="brickType" onchange="change()">
								<option value="cost">违规金额</option>
								<option value="2">违规明细数</option>
							</select>
						</div>
					</div>

					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
							<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
								<div class="layui-tab-item layui-show" id="mainGrap1" style="height:300px;">
									<div id="main1" style="height:300px;margin-left:25px;"></div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
				<div class="layui-card">

					<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top:-2px;padding-right: 8px;"
							 src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
						医保项目违规统计

					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
							<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
								<div class="layui-tab-item layui-show" id="mainGrap3" style="height:300px;">
									<div id="main3" style="height:300px;margin-left:25px;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>








	<%--<div class="layui-fluid">--%>

		<%--<div class="layui-row layui-col-space15">--%>

			<%--<div class="layui-col-md12" style="height: 500px">--%>

		<%--<div class="layui-card">--%>
			<%--<div class="layui-form layui-card-header layuiadmin-card-header-auto">--%>
				<%--<div class="layui-inline" >--%>
					<%--<label class="layui-form-label">违规类型</label>--%>
					<%--<div class="layui-input-inline" id="xldown">--%>
						<%--<select id="ruleType" name="violationDetail.typeNo" xm-select="select2" xm-select-direction="down" lay-search>--%>
							<%--<option value="" disabled selected style='display:none;'>请选择类型</option>--%>
						<%--</select>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="layui-inline">--%>
					<%--<button id="medical-jpp-detail-search-cx"--%>
						<%--class="layui-btn layuiadmin-btn-useradmin"--%>
						<%--lay-submit lay-filter="LAY-user-front-search2">--%>
						<%--<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询--%>
					<%--</button>--%>
				<%--</div>--%>
				<%--<div class="layui-inline ">--%>
					<%--<button id="violation-dc" class="layui-btn layui-icon-down-main layuiadmin-btn-useradmin" --%>
						<%--lay-submit lay-filter="LAY-user-front-export">--%>
						<%--<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出--%>
					<%--</button>--%>
				<%--</div>--%>
			<%--</div>--%>

			<%--<div class="layui-card-body">--%>
				 <%--<div class="layui-row layui-col-space15">--%>
            	 <%--<div class="layui-col-md6">--%>
				   <%--<table id="violationDetail" class="layui-hide" lay-filter="violationDetail"></table>--%>
				 <%--</div>--%>
				 <%--<div class="layui-col-md6">--%>
				 	<%--<div class="layui-card-header" style="height:20px">--%>
    					<%--<button id="yiyuan" href="javascript:;" style="margin-right:5%;z-index: 9999;position: absolute;" class="layui-btn  layui-btn-xs">医院</button>--%>
    				<%--</div>--%>
  					<%--<div class="layui-card-body">--%>
   						<%--<div id="main" style="height: 435px;"></div>--%>
  					<%--</div>	--%>
				   	<%----%>
				 <%--</div>--%>
				 <%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
			<%--</div>--%>
			<%--<div class="layui-col-md12" style="height: 500px">--%>
				<%--<div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 500px">--%>
					<%--<div class="layui-card" style="height: 490px;">--%>
						<%--<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">--%>
							<%--<img style="margin-top:-2px;padding-right: 8px;"--%>
								 <%--src="<%=request.getContextPath() %>/images/auditing/mark.png"/>--%>
							<%--医院违规统计--%>
							<%--<div class="layui-input-inline">--%>
								<%--<select id="type" lay-filter="brickType" onchange="change()">--%>
									<%--<option value="cost">违规金额</option>--%>
									<%--<option value="2">违规明细数</option>--%>
								<%--</select>--%>
							<%--</div>--%>
						<%--</div>--%>

						<%--<div class="layui-card-body">--%>
							<%--<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">--%>
								<%--<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">--%>
									<%--<div class="layui-tab-item layui-show" id="mainGrap1" style="height:300px;">--%>
										<%--<div id="main1" style="height:300px;margin-left:25px;"></div>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 500px">--%>
					<%--<div class="layui-card" style="height: 490px;">--%>
						<%--<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">--%>
							<%--<img style="margin-top:-2px;padding-right: 8px;"--%>
								 <%--src="<%=request.getContextPath() %>/images/auditing/mark.png"/>--%>
							<%--医保项目违规统计--%>

						<%--</div>--%>
						<%--<div class="layui-card-body">--%>
							<%--<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">--%>
								<%--<div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">--%>
									<%--<div class="layui-tab-item layui-show" id="mainGrap3" style="height:300px;">--%>
										<%--<div id="main3" style="height:300px;margin-left:25px;"></div>--%>
									<%--</div>--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

		<%--</div>--%>
	<%--</div>--%>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
	require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
	</script>
	
	
	
	<script type="text/javascript">
	var tn='';//全局类型变量.表格type_no
	var status_qj='';//机审0  终审1
	var typeNameCl='';//typeName
	function GetRequest() {
		var url = location.search; //获取url中"?"符后的字串 
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
		theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
		}
		return theRequest;
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
		var Request = GetRequest();
		var statusUrl = Request["status"];
		var type_no_violation = Request["typeNo"];
		//解码
		var typeNocs=decodeURI(type_no_violation);
		status_qj=statusUrl;//全局变量赋值
		//加载违规类别
		$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_rule', 
				function(data){
		     		var  dataList= data.dictList;
		     		for(var i=0 ;i<dataList.length;i++){
		     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
		     			$("#ruleType").append(mm); 
		     		}
		     		if(type_no_violation){
		    			$("#ruleType").find("option[value ='"+typeNocs+"']").attr("selected","selected");
		     		}	
		     	form.render('select');
		});
		 
		var fileds={"status": statusUrl};
		if(type_no_violation){
			fileds={"status": statusUrl,"typeNo":type_no_violation};
			
		}

		table.render({
			elem: '#violationDetail'
			,url: $WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/listVo"
			,where: fileds
			// ,height:  tableHeight
			,height:  380
			,limit:10
			,cellMinWidth: 80
		    ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'id', hide:true,title: '编号'}
			,{field:'typeNo', width:115,title: '违规类型编码'}
			,{field:'typeNames',width:220, title: '违规类型名称'}
			,{field:'itemCost', title: '违规金额'}
			,{field:'countNum', title: '违规案例数量'}
			,{field:'sumNum', title: '违规占比',templet: function (d) {
				var round=(Math.round((d.countNum/d.sumNum)*10000))/100.00;
				if(round<0.0001 ){
					return '-';
				}else{
					return round+ "%";
				}
			}
			}
			]]
		,page: true
		 ,done:function(res){
			 $('tr').eq(1).css("background-color","#C0C0C0");
         	var result=res.data;
         	if(result.length>0){
         		tn=result[0].typeNo;
         		typeNameCl=result[0].typeNames;
         		$("#yiyuan").html(typeNameCl);
         		reloadTable(tn,statusUrl);
         		reloadTable1(tn,statusUrl,type2);
         		typeNo2=tn;
         	}
		 }
		});
	
		
		//监听搜索
		form.on('submit(LAY-user-front-search2)', function(data){
			var field = data.field;
			type_no_violation='';
			var tablenew=layui.table;
			//执行重载
			tablenew.reload('violationDetail', {
				where: field
			});
		});
		//导出
		form.on('submit(LAY-user-front-export)', function(data){
			var field = data.field;
			//执行重载
			 window.open($WEB_ROOT_PATH+'/dhccApi/violationdetails/violationDetail/exportExcelToSelf?status='+statusUrl);
		});
		 table.on('row(violationDetail)', function(obj){
			 $("tr").css("background-color",""); 
	         $(this).css("background-color","#C0C0C0"); 
			 var result=obj.data;
			 var typeNo=result.typeNo;
			 tn=typeNo;//全局变量赋值
			 typeNameCl=result.typeNames;
			 $("#yiyuan").html(typeNameCl);
			 reloadTable(typeNo,statusUrl);
			 if(type2=="cost"){
				 reloadTable1(typeNo,statusUrl,type2);
			 }else{
				 reloadTable2(typeNo,statusUrl,type2);
			 }
			 // reloadTable3(typeNo,statusUrl);
			 // reloadTable4(typeNo,statusUrl);
			 typeNo2=typeNo
		 });
		 $("#yiyuan").on("click",function(){
			 var param = {seriesIndex:"1", name:tn};
			 eConsole(param);
		});
	
		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	//跳转函数
	function eConsole(param) {  
	    if (typeof param.seriesIndex != 'undefined') { 
	   	 	var str=param.name;
	    
	   	 	str=encodeURI(encodeURI(str));
	    	//默认为初审
	    	if(!status_qj){
	    		status_qj='0';
	    	}
	    	
	    	var tit=status_qj=='0'?"事后病例初审":"事后病历终审";
	    	var cs="/piccbid/medical/medical/jpp?typeNo="+str+"&type=2";
	    	var zs="/piccbid/medical/medical/jpp-zs?typeNo="+str+"&type=2";
	    	
	    	var urlGo=status_qj=='0'?cs:zs;
	    	$("#tz-hide").attr('lay-href',urlGo);
	    	$("#tz-hide").html(tit);
	    	$('#tz-hide').trigger("click");
	    }  
	} 
	
	function eConsoleSpread(param) {  
	    if (typeof param.seriesIndex != 'undefined') { 
	   	 	var str=param.name;
	   	 	var s=str.split('_'); 
	  		//alert(param.data.orgCode);
	   	 	var s0=encodeURI(encodeURI(s[0]));   
	   	 	var s1=encodeURI(encodeURI(s[1]));
	   	 	var s2=encodeURI(param.data.orgCode);
	   	 	var typeNoTz=encodeURI(encodeURI(tn));
	    	//默认为初审
	    	if(!status_qj){
	    		status_qj='0';
	    	}
	    	
	    	var tit=status_qj=='0'?"事后病例初审":"事后病历终审";
	    	var cs="/piccbid/medical/medical/jpp?orgCode="+s2+"&cityName="+s0+"&typeNo="+typeNoTz+"&type=4";
	    	var zs="/piccbid/medical/medical/jpp-zs?orgCode="+s2+"&cityName="+s0+"&typeNo="+typeNoTz+"&type=4";
	    
	    	var urlGo=status_qj=='0'?cs:zs;
	    	
	    	$("#tz-hide").attr('lay-href',urlGo);
	    	$("#tz-hide").html(tit);
	    	$('#tz-hide').trigger("click");
	    }  
	} 
	
	function reloadTable(typeNo,statusUrl) {
	 require(['echarts','echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		 	  ],
	          function (echarts) {
					echarts.init(document.getElementById('main')).dispose();//销毁前一个实例
					 var memoBar = echarts.init(document.getElementById('main'));//构建下一个实例
		 var option = {
                 tooltip: {
                     show: true
                 },
                 padding: [0, 0, 10, 10],  // 位置
                 legend: {
                     padding: 10,    // [5, 10, 15, 20]
                     itemGap: 20,
                     left:'right',
                     data:['违规类型医院分布TOP10统计']
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
		         url:$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/hospitalSpread",
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         data:{"violationDetail.typeNo":typeNo,"status":statusUrl},
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
		        			 	var dir={"orgCode":"23456","value":11};
			                   dir.value=hvdata[i].countNum;
			                   dir.orgCode=hvdata[i].orgCode;
			                   option.series[0].data.push(dir);
			                   option.xAxis[0].data.push(hvdata[i].coName);
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
	}
	//大屏跳转过来处理（默认查询点击）
	function bigScreeController(typeNo,form){
		//让违规类型选中
		$("#ruleType").find("option[value ='"+typeNo+"']").attr("selected","selected");
		 form.render('select');
		//模拟点击查询
		$('#medical-jpp-detail-search-cx').trigger("click");
	}
	</script>



<script>
	var typeNo2
	var type2="cost"
	function reloadTable1(typeNo,statusUrl) {
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					//echarts.init(document.getElementById('main1')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main1'));//构建下一个实例
					var option = {
						tooltip: {
							show: true,
							formatter:function(data){
								var hosNames=data.name.split('-');
								var hosName=hosNames[0];
								return hosName+":<br/>违规金额："+data.data+"元"
							}
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['医院违规金额TOP10统计']
						},
						xAxis : [
							{
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval:0,
									rotate:"20",
									formatter: function (data) {
										var hosNames=data.split('-');
										var hosName=hosNames[0];
										return hosName;
									}
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
								"name":"医院违规金额TOP10统计",
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
						url:$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/hopVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"violationDetail.typeNo":typeNo,"violationDetail.returnName":"cost"},
						success : function(result) {
							for(var i=0;i<result.length;i++){
								option.series[0].data.push(result[i].itemCost);
								option.xAxis[0].data.push(result[i].coName+"-"+result[i].orgCode);
							}
								memoBar.setOption(option);
								reloadTable3(typeNo,statusUrl,result[0].orgCode)


						}
					});
					//下面是需要添加的方法内容
					//点击柱状图跳转相应页面的功能，其中param.name参数为横坐标的值
					var ecConfig = require('echarts/config');
					memoBar.on(ecConfig.EVENT.CLICK, eConsoleSpread1);
				});
	}

	function eConsoleSpread1(param) {
		var hosNames = param.name.split('-');
		var code = hosNames[1];
		reloadTable3(typeNo2,"",code,type2)
	}

	function eConsoleSpread2(param) {
		var hosNames = param.name.split('-');
		var code = hosNames[1];
		reloadTable3(typeNo2,"",code,type2)
	}


	function reloadTable2(typeNo,statusUrl) {
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					//echarts.init(document.getElementById('main2')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main1'));//构建下一个实例
					var option = {
						tooltip: {
							show: true,
							formatter:function(data){
								var hosNames=data.name.split('-');
								var hosName=hosNames[0];
								return hosName+":<br/>违规金额："+data.data+"元"
							}
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['医院违规明细数TOP10统计']
						},
						xAxis : [
							{
								type : 'category',
								data : [],
								axisLabel : {//坐标轴刻度标签的相关设置。
									interval:0,
									rotate:"20",
									formatter: function (data) {
										var hosNames=data.split('-');
										var hosName=hosNames[0];
										return hosName;
									}
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
								"name":"医院违规明细数TOP10统计",
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
						url:$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/hopVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"violationDetail.typeNo":typeNo,"violationDetail.returnName":"count"},
						success : function(result) {
							for(var i=0;i<result.length;i++){
								option.series[0].data.push(result[i].countDetail);
								option.xAxis[0].data.push(result[i].coName+"-"+result[i].orgCode);
							}
								memoBar.setOption(option);
								reloadTable4(typeNo,statusUrl,result[0].orgCode)

						}
					});
					//下面是需要添加的方法内容
					//点击柱状图跳转相应页面的功能，其中param.name参数为横坐标的值
					var ecConfig = require('echarts/config');
					memoBar.on(ecConfig.EVENT.CLICK, eConsoleSpread2);
				});
	}

	function reloadTable3(typeNo,statusUrl,orgCode) {
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					//echarts.init(document.getElementById('main3')).dispose();//销毁前一个实例
					var memoBar = echarts.init(document.getElementById('main3'));//构建下一个实例
					var option = {
						tooltip: {
							show: true
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['项目违规金额TOP10统计']
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
								"name":"项目违规金额TOP10统计",
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
						url:$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/proVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"violationDetail.typeNo":typeNo,"status":statusUrl,"violationDetail.returnName":"cost","violationDetail.itemCode":orgCode},
						success : function(result) {
							console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
							for(var i=0;i<result.length;i++){
								option.series[0].data.push(result[i].itemCost);
								option.xAxis[0].data.push(result[i].itemName);
							}
								memoBar.setOption(option);

						}
					});
				});
	}

	function reloadTable4(typeNo,statusUrl,orgCode) {
		require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
				function (echarts) {
					//echarts.init(document.getElementById('main4')).dispose();//销毁前一个实例
					 var memoBar = echarts.init(document.getElementById('main3'));//构建下一个实例
					var option = {
						tooltip: {
							show: true
						},
						padding: [0, 0, 10, 10],  // 位置
						legend: {
							padding: 10,    // [5, 10, 15, 20]
							itemGap: 20,
							left:'right',
							data:['项目违规明细数量OP10统计']
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
								"name":"项目违规明细数量OP10统计",
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
						url:$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/proVio",
						type : "post",
						async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
						data:{"violationDetail.typeNo":typeNo,"status":statusUrl,"violationDetail.returnName":"detail","violationDetail.itemCode":orgCode},
						success : function(result) {
							console.log(result)
							console.log("======================================")
							var hvdata=result;
							if (hvdata != null && hvdata.length > 0) {
								for(var i=0;i<result.length;i++){
									option.series[0].data.push(result[i].countDetail);
									option.xAxis[0].data.push(result[i].itemName);
								}
								memoBar.setOption(option);
							}

						}
					});
				});
	}


	layui.use(['form'], function() {
		var form=layui.form;
		form.on('select(brickType)', function(data){
			console.log(data)
		});
	});

	function change(){
		var value=document.getElementById("type")
		console.log(value.selectedIndex)
		if(value.selectedIndex=="0"){
			type2="cost"
			reloadTable1(typeNo2,"")
		}else if(value.selectedIndex=="1"){
			type2="count"
			reloadTable2(typeNo2,"")
		}
	}

</script>
</body>
</html>
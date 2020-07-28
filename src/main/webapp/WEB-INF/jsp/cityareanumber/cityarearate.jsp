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
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
				接入城市基本情况	
			</div>

			<div class="layui-card-body">
				 <div class="layui-row layui-col-space15">
            	 <div class="layui-col-md6">
				   <table id="violationDetail" class="layui-hide" lay-filter="violationDetail"></table>
				 </div>
				 <div class="layui-col-md6">
				   	<div id="main1" style="height: 485px;width:800px;padding-left:30px" align="right"></div>
				 </div>
				 </div>
			</div>
		</div>
	</div>
	<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
	<script type="text/javascript">
  		var cityData=new Array();
  		var personData=new Array();
  		var hosData=new Array();
        // 基于准备好的dom，初始化echarts实例
        /* var dataAxis=new Array();
    	var data=new Array(); */
         $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/cityAreaRate/cityAreaRate/listRateVo1",
		type : "get",
		success : function(data1) {	
			//var newstr=stringObj.replace(/%/, "");
			
			for(var index in data1){
				if(index >= 10 || index ==10){
					break;
				}
				cityData[index]=data1[index].city;
				personData[index]=(data1[index].prate.replace("%",""))/* .replace(/%/,"") */;
				hosData[index]=(data1[index].hrate.replace("%",""))/* .replace(/%/,"") */;
				
			}
			var myChart = echarts.init(document.getElementById('main1'));
			// 指定图表的配置项和数据
			option = {
						toolbox: {
									top:-5,
									right:150,
									show: true,
									feature: {
										mark:{show:false		},
										magicType: {
											type: ['line', 'bar'],
											show: true
										},
										dataZoom: {
											show: false
										},
										dataView: {
											show: false,
											readOnly:false
										},
										restore: {
											show: false	
										},
										saveAsImage: {
											show: false
										}
								}
							}
						,
					    title: {
					        text: '接入城市数据比例top10'
					    },
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        },
					       
					       
					    },
					    legend: {
					        data: ['参保人员比例','医院比例']
					    },
					    grid: {
					        left: '3%',
					        right: '20%',
					        bottom: '10%',
					        top:'10%',
					        containLabel: true
					    },
					    xAxis: {
					    	type: 'category',
					        data: cityData,axisLabel: {  
					        	   interval:0,  
					        	   rotate:40  
					        	}  
					    },
					    yAxis: {
					    	type: 'value',
					    	 axisLabel: {  
		                            show: true,  
		                            interval: 'auto',  
		                            formatter: '{value} %'  
		                            },  
		                        show: true  
					    },
					    series: [
					        {
					            name: '参保人员比例',
					            type: 'bar',
					            data: personData,
					             
					            	label: {
					            		normal: {
					                        show: true,
					                        position: 'top',
					                        formatter: '{c}%'
					                    }

					            		    }
					            
					        },
					        {
					            name: '医院比例',
					            type: 'bar',
					            data: hosData,
					        	label: {
				            		normal: {
				                        show: true,
				                        position: 'top',
				                        formatter: '{c}%'
				                    }

				            		    }
					        }
					    ]
					};
				myChart.setOption(option);
				
		},
		error : function(data) {
			alert("服务器错误");
		} 
    });
	
	</script>
	
	<script type="text/javascript">
	//初始化	
	layui.config({
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'table'], function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table;
		table.render({
			elem: '#violationDetail'
			,url: $WEB_ROOT_PATH+"/dhccApi/cityAreaRate/cityAreaRate/listRateVo"
			,height: document.documentElement.clientHeight-65
			,cellMinWidth: 80
		    ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'area',width:100,title: '行政区域',align:"center",templet:function(d){
	     		   var codex = d.area;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'city', width:100,title: '城市',align:"center",templet:function(d){
	     		   var codex = d.city;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'time', width:100,title: '接入时间',align:"center",templet:function(d){
	     		   var codex = d.time;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'pnumber', width:100,title: '参保人数量',align:"center",templet:function(d){
	     		   var codex = d.pnumber;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'prate', width:100,title: '占比',align:"center",templet:function(d){
	     		   var codex = d.prate;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'hnumber', width:100,title: '医院数量',align:"center",templet:function(d){
	     		   var codex = d.hnumber;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'hrate', width:100,title: '占比',align:"center",templet:function(d){
	     		   var codex = d.hrate;
	    		   if(codex == "0"){
	    			   codex="-";
	    		   }else if(codex == "%"){
	    			   codex="-";
	    		   }else if(codex == null){
	    			   codex="-";
	    		   }else if(codex == ""){
	    			   codex="-";
	    		   }
	    		   else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			]]
		,page: true
		});

		//监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
			var field = data.field;
			//执行重载
			layui.table.reload('violationDetail', {
				where: field
			});
		});
		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	</script>
</body>
</html>
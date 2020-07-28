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
				接入城市统计	
			</div>

			<div class="layui-card-body">
				 <div class="layui-row layui-col-space15">
            	 <div class="layui-col-md6">
				   <table id="violationDetail" class="layui-hide" lay-filter="violationDetail"></table>
				 </div>
				 <div class="layui-col-md6">
				   	<div id="main" style="height: 485px;"></div>
				 </div>
				 </div>
			</div>
		</div>
	</div>
	<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
	<script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
    
	/* {
        name: '数量',
        type: 'bar',
        barWidth:16,
        data: data,
        itemStyle:{
            normal:{
                color:'#319CFE'
            }
        }
    } */
	
	var dataAxis=new Array();
	var data=new Array();
	var topic = new Array();
	var map = new Map();
	//var topic1 = new Array();
     $.ajax({
	url : $WEB_ROOT_PATH+"/dhccApi/homerest/homerest/findCityNumber",
	type : "get",
	success : function(data1) {	
		for(var index in data1){
			if(index >= 10 || index == 10){
				break;
			}
			dataAxis[index] = data1[index].name;
			data[index]=parseInt(data1[index].digit);
			topic[index]=data1[index].name+":"+data1[index].city;
			var str=data1[index].city;
			str=str.replace(/,/g,'<br>');
			console.log(str);
			map.set(data1[index].name,str);
			//topic1[index]=new Myserais(data1[index].city);
		}
		/* console.log(topic1[0].name); */
		  var myChart = echarts.init(document.getElementById('main'));
	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: ''
	            },
	            tooltip : {//鼠标悬浮弹窗提示
	     	       /*  trigger: 'axis' */
	     	    	 trigger : 'axis',
	     	    	 show:true,
	     	         showDelay: 0,
	     	         hideDelay: 0,
	     	         transitionDuration:0, 
	     	         backgroundColor : 'rgba(255,0,255,0.7)',
	     	         borderColor : '#f50',
	     	         borderRadius : 8,
	     	         borderWidth: 2,
	     	         padding: 10,    // [5, 10, 15, 20]
	     	         formatter: function (params,ticket,callback) {
	     	        	 //console.log(params);
	     	        	 //console.log(params[0].axisValue);
	     	            //console.log(params.index);
	     	            //console.log(topic.length);
	     	           // console.log(ticket);
	     	          // console.log(map.get(params[0].axisValue));
	     	           var res = "包含城市："+ '<br/>';
	     	           res+=map.get(params[0].axisValue)+'<br/>';
	     	          /*  for(var i=0;i<topic.length;i++){
	     	              res +=  topic[i]+ '<br/>';
	     	            //console.log(res);
	     	           } */
	     	           return res;
	     	         }
	            },
	            legend: {
	            	x: '30px',
	                y: '5px',
	                data:['数量']
	            },
	            xAxis: {
	                data: dataAxis,
	                grid: {
	                	left: '10%',
	                	bottom:'35%'
	                	}
	            },
	            yAxis: {
	            	data:data.max
	            },
	            series: [ {
	                name: '数量',
	                type: 'bar',
	                barWidth:16,
	                data: data,
	                itemStyle:{
	                    normal:{
	                        color:'#319CFE'
	                    }
	                }} ]
	        };
	       /*  for(var i=0;i<=data.length;i++){
	        	var item = "{"+
		                "name:"+ '数量'+","
		                "type:"+ 'bar'+","
		                +"barWidth:+16,"
		                +"data:" +data[i]
		              + "}";
	        	option.series.push(item);
	        } */
	        // 使用刚指定的配置项和数据显示图表。
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
			,url: $WEB_ROOT_PATH+"/dhccApi/cityAreaRate/cityAreaRate/listVo"
			,height: document.documentElement.clientHeight-65

			,cellMinWidth: 80
		    ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'name',width:350,title: '行政区域',align:"center"}
			,{field:'digit', width:250,title: '城市数量',align:"center",sort:true}
			]]
		,page: true
		});
		function cityArea(name,value){
			var o = new Object();
			o.name=name;
			o.value=value;
			return o;
		}
		
		/* table.on('row(violationDetail)', function(obj){
			var dataAxis = new Array();
			var name = obj.data.name;
			$.ajax({
				url: $WEB_ROOT_PATH+"/dhccApi/cityAreaRate/cityAreaRate/listCityByName",
				type:"post",
				data : {
					"name" : name
				},
				success:function(data){
					for(var index in data){
						dataAxis[index] = new cityArea(data[index].city,1);
					}
					console.log(dataAxis);
					var myChart = echarts.init(document.getElementById('main'));
					myChart.clear();
					var option = {
						    series: [{
						        type: 'treemap',
						        data: [{
						            name: name,            // First tree
						            value: 10,
						            children: dataAxis
						        }]
						    }]
						};
					myChart.setOption(option);
				},
				error:function(data){
					
				}
			});		
		}); */
	    
		
	
		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	</script>
</body>
</html>
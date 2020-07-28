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

<title>金额统计</title>
<style>

</style>
</head>
<body>
<div class="layui-fluid" style="overflow:hidden">
	<div class="layui-row layui-col-space15">
	<div class="layui-col-md6">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
			    金额统计
			</div>
			<div class="layui-form layui-card-header layuiadmin-card-header-auto"
				style="padding: 3px">

					<div class="layui-inline ">
						<label class="layui-form-label ">年度选择</label>
						<div class="layui-input-inline" style="width: 180px;">
							<input id="createTime" name="inFlag" lay-filter="createTime"
								type="text" class="layui-input" placeholder="yyyy">
						</div>
					</div>
			</div>

			<div class="layui-card-body">
				   	<div id="main"></div>
				 </div>
			</div>
		</div>
	
		<div class="layui-col-md6">
		<div class="layui-card">
		    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
			    年度住院统计
			</div>
              <div class="layui-card-body">
              	<div class="layui-inline ">
						<label class="layui-form-label ">年度选择</label>
						<div class="layui-input-inline" style="width: 180px;">
							<input id="Time" name="inFlag" lay-filter="Time"
								type="text" class="layui-input" placeholder="yyyy">
						</div>
					</div>
            <div id="mainzu"></div>
             </div>
		</div>
		</div>
   </div>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script  src="<%=request.getContextPath() %>/app/js/medicalCount/countDateNumber.js"></script>
	<script type="text/javascript">
	  var time=(new Date).getTime();
	  var date = new Date(time);
      var today_nian = date.getFullYear();
      var str = today_nian;
	 // 路径配置
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
             var myChart = ec.init(document.getElementById('mainzu'));

             var month1;


             var option = {
            		    title: {
            		        text: ''
            		    },
            		    tooltip : {
            		        trigger: 'axis',
            		        axisPointer: {
            		            type: 'cross',
            		            label: {
            		                backgroundColor: '#6a7985'
            		            }
            		        }
            		    },
            		    legend: {
            		        data:['住院人次']
            		    },
            		    toolbox: {
            		        feature: {
            		            saveAsImage: {}
            		        }
            		    },
            		    grid: {
            		        left: '3%',
            		        right: '4%',
            		        bottom: '3%',
            		        containLabel: true
            		    },
            		    xAxis : [
            		        {
            		            type : 'category',
            		            /* boundaryGap : false, */
            		            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
            		        }
            		    ],
            		    yAxis : [
            		        {
            		            type : 'value'
            		        }
            		    ],
            		    series : [
            		       {
            		            name:'住院人次',
            		            type:'bar',
            		            stack: '总量',
            		            barWidth:16,
            		            areaStyle: {},
            		            data:[],
            		            itemStyle:{
                                    normal:{
                                        color:'#319CFE'
                                    }
                                }
            		        }

            		    ]
            		};

           
                 $.ajax({
                     url: $WEB_ROOT_PATH + "/dhccApi/admin/admin/YearData?inFlag="+str,
                     type: "post",
                     async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                     dataType: "json",
                     success: function (data) {
                         var flag=0;
                         for(var i=0;i<12;i++) {
                             flag=0;
                            for (var y = 0; y < data.data.length; y++) {
                                     var month1 = data.data[y];
                                     var k = i + 1
                                     if(data.data[y].monthDay == (k)){
                                         if(month1.totalNumber==null || month1.totalNumber==""){
                                                 option.series[0].data.push(0)
                                         }else {
                                                 option.series[0].data.push(month1.totalNumber);
                                         }
                                         flag=1;
                                     }
                                 } 
                             if(flag==0){
                                 option.series[0].data.push(0);
                             }
                             }
                         myChart.setOption(option);
                     }
                 });
         }
     );
	   
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
 	             var myChart = ec.init(document.getElementById('mainzu'));

 	             var month1;

 	             var option = {
 	            		    title: {
 	            		        text: ''
 	            		    },
 	            		    tooltip : {
 	            		        trigger: 'axis',
 	            		        axisPointer: {
 	            		            type: 'cross',
 	            		            label: {
 	            		                backgroundColor: '#6a7985'
 	            		            }
 	            		        }
 	            		    },
 	            		    legend: {
 	            		        data:['住院人次']
 	            		    },
 	            		    toolbox: {
 	            		        feature: {
 	            		            saveAsImage: {}
 	            		        }
 	            		    },
 	            		    grid: {
 	            		        left: '3%',
 	            		        right: '4%',
 	            		        bottom: '3%',
 	            		        containLabel: true
 	            		    },
 	            		    xAxis : [
 	            		        {
 	            		            type : 'category',
 	            		            /* boundaryGap : false, */
 	            		            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
 	            		        }
 	            		    ],
 	            		    yAxis : [
 	            		        {
 	            		            type : 'value'
 	            		        }
 	            		    ],
 	            		    series : [
 	            		       {
 	            		            name:'住院人次',
 	            		            type:'bar',
 	            		            stack: '总量',
 	            		            barWidth:16,
 	            		            areaStyle: {},
 	            		            data:[],
 	            		            itemStyle:{
 	                                    normal:{
 	                                        color:'#319CFE'
 	                                    }
 	                                }
 	            		        }

 	            		    ]
 	            		};


 	                 $.ajax({
 	                     url: $WEB_ROOT_PATH + "/dhccApi/admin/admin/YearData?inFlag="+value,
 	                     type: "post",
 	                     async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
 	                     dataType: "json",
 	                     success: function (data) {
 	                   	 if(data.code==22){
                    		 alert('会话过期，请重新登录');
     						window.top.location.href=$WEB_ROOT_PATH;
                    	 }
 	                         var flag=0;
 	                         for(var i=0;i<12;i++) {
 	                             flag=0;
 	                            for (var y = 0; y < data.data.length; y++) {
 	                                     var month1 = data.data[y];
 	                                     var k = i + 1
 	                                     if(data.data[y].monthDay == (k)){
 	                                         if(month1.totalNumber==null || month1.totalNumber==""){
 	                                                 option.series[0].data.push(0)
 	                                         }else {
 	                                                 option.series[0].data.push(month1.totalNumber);
 	                                         }
 	                                         flag=1;
 	                                     }
 	                                 } 
 	                             if(flag==0){
 	                                 option.series[0].data.push(0);
 	                             }
 	                             }
 	                         myChart.setOption(option);
 	                     },error:function(data){
 	                    	 alert("数据错误");
 	                     }
 	                 });
 	         }
 	     );
 			
 		}
 		
 		});
    </script>
    </div>
</body>
</html>
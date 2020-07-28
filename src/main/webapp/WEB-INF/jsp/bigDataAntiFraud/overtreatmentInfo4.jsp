<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">

<title>项目数量异常</title>
<style>
.echart {
	width: 700px;
	height: 200px;
}
.lb {
	
	margin-left: 30px; 
	width: 90px;
	font-weight: normal;
	margin-top:-10px;
	display: inline-block;
	text-align: right;
	/*  border-style: solid;  */
}
.ipt {
	
	margin-left: 10px;
	width: 90px;
	font-weight: normal;
	margin-top:-10px;
	display: inline-block;
	text-align: left;
	/*  border-style: solid;  */
}
</style>
</head>
<body>
<div class="layui-fluid">
 <div class="grid-demo grid-demo-bg1" >
            
             				<hr style="margin-top:5px">
             					<div>
      								<lable class="lb">问题类型:</lable>
      								<lable class="ipt">血型冲突</lable>
      								
      								<lable class="lb">监管对象:</lable>
      								<lable class="ipt">患者</lable>
      								<lable class="lb">监管时间段：</lable>
      								<lable class="ipt">6月-8月</lable>
      							</div>
      							<div>
      								<lable class="lb">可疑总金额:</lable>
      								<lable class="ipt">4161元</lable>
      								<lable class="lb"></lable>
      								<lable class="ipt"></lable>
      								<lable class="lb">可疑报销金额:</lable>
      								<lable class="ipt">3225元</lable>
      							</div>
      							<hr style="margin-top:5px">
      							<div>
      								<lable class="lb">患者ID:</lable>
      								<lable class="ipt">325566</lable>
      								<lable class="lb">患者姓名:</lable>
      								<lable class="ipt">刘富贵</lable>
      								<lable class="lb">就诊医院:</lable>
      								<lable class="ipt">北京市第一医院</lable>
      							</div>
      							
      					<hr style="margin-top:5px">		
          </div>  
          <!-- <div id="main" class="echart"></div>  -->
    <div class="layui-card">
      <div class=" layui-card-header layuiadmin-card-header-auto" >
     
      </div>
          
     	<div class="layui-card-body">
       		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="margin-top:0px " >
  				<ul class="layui-tab-title">
   				<li class="layui-this">检验单据</li>
    			<li>设计单据</li>
  				</ul>
  			<div class="layui-tab-content" >
  			 	<div class="layui-tab-item layui-show">
  			 		<div class="layui-card">
  			 		 	<table id="userTable2" class="layui-hide" lay-filter="userTable2"></table>
  			 		</div>
  			 	</div>
    			<div class="layui-tab-item">内容2</div>
  			</div>
      </div>
    </div>
  </div>
  <!-- ECharts单文件引入 -->
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
	layui.use(['element','table'], function(){
		  var element = layui.element;
		  
		  var table=layui.table;
		  table.render({
		    	elem: '#userTable2'
		            ,url: '/piccbid/app/js/resultAppeal/overtreatment5.json'
		            ,cellMinWidth: 80
		           // ,where: {  }
		            ,height: 250
		            ,cols: [[
		            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
		            	  ,{field:'id', title: 'ID', sort: true, hide:true} 
			              //,{title:'操作', toolbar: '#table-useradmin-webuser', width:150,align:'center'}
			              //,{field:'billingNo', title: '单据号',width:100,align:'center'}
			              ,{field:'time', title: '检查时间',width:120,align:'center'}
			              ,{field:'name', title: '患者姓名',width:120,align:'center'}		             
			              ,{field:'hospital', title: '就诊医院',width:180,align:'center'}		             
			              ,{field:'num', title: '检验序号',width:120,align:'center'}		             
			           /*    ,{field:'comName', title: '组合项目名',width:120,align:'center'}		 */             
			              ,{field:'itemName', title: '项目名称',width:120,align:'center'}		             
			              ,{field:'result', title: '检验结果',width:120,align:'center'}		             
			              ,{field:'personName', title: '检验技师',width:120,align:'center'}		             
			             	             		             
			             

		            ]]
		            ,page: true
		          });
		    
		  //…
		});
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
             'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
         ],
         function (ec) {
             // 基于准备好的dom，初始化echarts图表
             var myChart = ec.init(document.getElementById('main')); 
             
             var option = {
                 tooltip: {
                     show: true
                 },
                 legend: {
                     data:['项目数量异常']
                 },
                 xAxis : [
                     {
                         type : 'category',
                         data : ["1" ,"2","3","4","5","6","7","8","9","10"]
                     }
                 ],
                 yAxis : [
                     {
                         type : 'value'
                     }
                 ],
                 series : [
                     {
                         "name":"",
                         "type":"line",
                         "data":[2,3,5,4,5,19,6,6,6,7,1],
                         itemStyle:{
                             normal:{
                                 color:'#EE7621'
                             }
                         }
                     }
                 ]
             };
     		
             // 为echarts对象加载数据 
              /*  $.ajax({
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
			        	 }
			         }
               }); */
			        		 myChart.setOption(option); 
             
             
             
             
             
             
         }
     );
    </script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/bigDataAntiFraud/overtreatmentInfo4.js"></script>
</body>
</html>
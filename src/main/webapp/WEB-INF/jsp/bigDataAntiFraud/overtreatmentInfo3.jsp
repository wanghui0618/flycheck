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
      								<lable class="ipt">项目数量异常</lable>
      								
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
      								<lable class="lb">医生ID:</lable>
      								<lable class="ipt">325566</lable>
      								<lable class="lb">就诊医院:</lable>
      								<lable class="ipt">XX市医院</lable>
      								<lable class="lb">诊断疾病:</lable>
      								<lable class="ipt">心血管疾病</lable>
      							</div>
      							<div>
      								<lable class="lb">住院天数:</lable>
      								<lable class="ipt">16</lable>
      								<lable class="lb">异常项目名称:</lable>
      								<lable class="ipt">免法</lable>
      								<lable class="lb">项目使用次数:</lable>
      								<lable class="ipt">2次</lable>
      							</div>
      							<div>
      								<lable class="lb">项目单价:</lable>
      								<lable class="ipt">3200</lable>
      								<lable class="lb">项目总金额:</lable>
      								<lable class="ipt">20</lable>
      								<lable class="lb">该医生使用率:</lable>
      								<lable class="ipt">20%</lable>
      								
      							</div>
      							<div>
      								<lable class="lb" style="width:190px">90%同患者检查次数小于:</lable>
      								<lable class="ipt">15次</lable>
      							</div>
      							<div>
      								<lable class="lb" style="width:190px" >90%同患者检查日均次数小于:</lable>
      								<lable class="ipt">5次</lable>
      							</div>
      					<hr style="margin-top:5px">		
          </div>  
          <div id="main" class="echart"></div> 
    <div class="layui-card">
      <div class=" layui-card-header layuiadmin-card-header-auto" >
     
      </div>
          
     	<div class="layui-card-body">
       		
      </div>
    </div>
  </div>
  <!-- ECharts单文件引入 -->
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
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
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/bigDataAntiFraud/overtreatmentInfo.js"></script>
</body>
</html>
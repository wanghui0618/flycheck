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

<title>超期住院</title>
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
      								<lable class="ipt">过度医疗</lable>
      								<lable class="lb"></lable>
      								<lable class="ipt"></lable>
      								<lable class="lb">监管对象:</lable>
      								<lable class="ipt">医院</lable>
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
      								<lable class="lb">医院名称:</lable>
      								<lable class="ipt">XX市医院</lable>
      								<lable class="lb">医院等级:</lable>
      								<lable class="ipt">一级</lable>
      								<lable class="lb">治疗疾病:</lable>
      								<lable class="ipt">腰椎间盘突出</lable>
      							</div>
      							<div>
      								<lable class="lb">异常时段:</lable>
      								<lable class="ipt">5月-6月</lable>
      								<lable class="lb">治疗人次:</lable>
      								<lable class="ipt">331</lable>
      								<lable class="lb">平均费用:</lable>
      								<lable class="ipt">32566.5元</lable>
      							</div>
      							<div>
      								<lable class="lb">二级治疗费用:</lable>
      								<lable class="ipt">3225元</lable>
      								
      							</div>
      					<hr style="margin-top:5px">		
          </div>  
          <div id="main" class="echart"></div> 
    <div class="layui-card">
      <div class=" layui-card-header layuiadmin-card-header-auto" >
      <!-- <div class="grid-demo grid-demo-bg1" >
            
             				<hr style="margin-top:5px">
             					<div>
      								<lable class="lb">问题类型:</lable>
      								<lable class="ipt">过度医疗</lable>
      								<lable class="lb">监管对象:</lable>
      								<lable class="ipt">医院</lable>
      								<lable class="lb">发现时间:</lable>
      								<lable class="ipt">2019-8-6</lable>
      							</div>
      							<div>
      								<lable class="lb">可疑总金额:</lable>
      								<lable class="ipt">4161元</lable>
      								<lable class="lb">可疑报销金额:</lable>
      								<lable class="ipt">3225元</lable>
      							</div>
      							<hr style="margin-top:5px">
      							<div>
      								<lable class="lb">医院名称:</lable>
      								<lable class="ipt">XX市医院</lable>
      								<lable class="lb">医院等级:</lable>
      								<lable class="ipt">一级</lable>
      								<lable class="lb">治疗疾病:</lable>
      								<lable class="ipt">腰椎间盘突出</lable>
      							</div>
      							<div>
      								<lable class="lb">异常时段:</lable>
      								<lable class="ipt">5月-6月</lable>
      								<lable class="lb">治疗人次:</lable>
      								<lable class="ipt">331</lable>
      								<lable class="lb">平均费用:</lable>
      								<lable class="ipt">32566.5元</lable>
      							</div>
      							<div>
      								<lable class="lb">二级治疗费用:</lable>
      								<lable class="ipt">3225元</lable>
      								
      							</div>
      							
          </div>    -->
      </div>
          
     	<div class="layui-card-body">
       		<!-- <div id="main" class="echart"></div> -->
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
                     data:['医院过度治疗']
                 },
                 xAxis : [
                     {
                         type : 'category',
                         data : ["1" ,"2","3"]
                     }
                 ],
                 yAxis : [
                     {
                         type : 'value'
                     }
                 ],
                 series : [
                     {
                         "name":"平均费用",
                         "type":"line",
                         "data":[2,3,5],
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
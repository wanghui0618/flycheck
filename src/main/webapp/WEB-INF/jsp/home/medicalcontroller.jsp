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
<title>实时监控系统</title>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 24px;
    font-size: 14px;
}
h2{
	text-align:center;
}
.imgul li{
	float:left;
	margin:30px 55px 1px 80px;
	 font-size: 16px;
}
</style>
</head>
<body>
<div class="layui-fluid"style="overflow: hidden">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12" style="overflow: hidden">
        <div class="layui-row layui-col-space15">
        <div>
        <div style="float:left;width:60%">
            <div class="layui-card"  style="width:100%">
              <div class="layui-card-header"><span id="nowYear"></span>医疗机构监控</div>
              <div class="layui-card-body">
            <div id="n" style="height:100px;">
            <h2 >总计<span id="total_count"></span>次数</h2>
             <ul class="imgul">
                <li >住院：<span id="count1"></span>次数</li>
                <li >门诊：<span id="count2"></span>次数</li>
                <li >门特：<span id="count3"></span>次数</li>
             </ul>
            </div>
              </div>
            </div>
          
            <div class="layui-card" style="width:100% ">
              <div class="layui-card-header">医疗机构监控曲线图</div>
              <div class="layui-card-body">
            <div id="main" style="height:350px;"></div>
                
              </div>
            </div>
         
          </div> 
          
        <div style="float:left ;width:35%;margin-left:20px;">
        <div class="layui-card" style="width:100% ">
          <div class="layui-card-header">区域基金全年使用</div>
            <div class="layui-card-body">

                <table id="fundTable" class="layui-hide" lay-filter="fundTable"></table>

            </div>
          </div>
            
        <div class="layui-card" style="width:100%;height:330px">
          <div class="layui-card-header">医院基金全年使用</div>
            <div class="layui-card-body">

                <table id="orgFundTable" class="layui-hide" lay-filter="orgFundTable"></table>

            </div>
             </div>
            </div>
          </div>
      
<!--       <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">
          	智能审核系统介绍
            <i class="layui-icon layui-icon-tips" lay-tips="系统介绍" lay-offset="5"></i>
          </div>
          <div class="layui-card-body layui-text layadmin-text">
            <p>智能审核系统，审核规则...</p>
          </div>
        </div>
      </div> -->
      </div>
    </div>
  </div></div>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script src="<%=request.getContextPath() %>/app/js/home/medicalcontroller.js"></script>
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
            		        data:['在职职工人次','退休职工人次','居民人次']
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
            		            boundaryGap : false,
            		            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
                                // data:[]
            		        }
            		    ],
            		    yAxis : [
            		        {
            		            type : 'value'
            		        }
            		    ],
            		    series : [
                            // {
                            //     "name":"",
                            //     "type":"line",
                            //     "data":[],
                            //     itemStyle:{
                            //         normal:{
                            //             color:'#EE7621'
                            //         }
                            //     }
                            // }
            		       {
            		            name:'在职职工人次',
            		            type:'line',
            		            // stack: '总量',
            		            areaStyle: {},
            		            data:[]
            		        },
                            {
            		            name:'退休职工人次',
            		            type:'line',
            		            // stack: '总量',
            		            areaStyle: {},
            		            // data:[220, 282, 191, 134, 290, 230, 110, 230, 290, 270, 310, 150]
            		            data:[]
            		        },
            		        {
            		            name:'居民人次',
            		            type:'line',
            		            // stack: '总量',
            		            areaStyle: {},
            		            // data:[150, 232, 201, 154, 190, 130, 410, 200, 180, 130, 210, 260]
            		            data:[]
            		        }

            		    ]
            		};


                 $.ajax({
                     url: $WEB_ROOT_PATH + "/dhccApi/home/regionalfunds/listVo2",
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
                                     if(data.data[y].month == k){
                                         if(month1.insurePersonType1==null){
                                                 option.series[0].data.push(0)
                                             }else {
                                                 option.series[0].data.push(month1.insurePersonType1);
                                             }
                                             if(month1.insurePersonType2==null){
                                                 option.series[1].data.push(0)
                                             }else {
                                                 option.series[1].data.push(month1.insurePersonType2);
                                             }
                                             if(month1.crowdType==null){
                                                 option.series[2].data.push(0)
                                             }else {
                                                 option.series[2].data.push(month1.crowdType);
                                             }
                                             flag=1;
                                     }
                                 }
                             if(flag==0){
                                 option.series[0].data.push(0);
                                 option.series[1].data.push(0);
                                 option.series[2].data.push(0);
                             }
                             }
                         myChart.setOption(option);
                     }
                 });
         }
     );
    </script>
    
  <script>

      var date=new Date;
      var year=date.getFullYear();
      var nowYear=document.getElementById("nowYear");
      nowYear.innerText=year;

  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console']);
  </script>
</body>
</html>
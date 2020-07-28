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
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-row layui-col-space15">
        <div>
        <div style="float:left;width:60%">
            <div class="layui-card"  style="width:100%">
              <div class="layui-card-header">2019疾病监控</div>
              <div class="layui-card-body">
            <div id="n" style="height:100px;">
            <h2 >总计22152.14次数</h2>
             <ul class="imgul">
                <li >1.14次数</li>
                <li >82.14次数</li>
                <li >12.14次数</li>
             </ul>
            </div>
              </div>
            </div>
          
            <div class="layui-card" style="width:100%">
              <div class="layui-card-header">疾病监控曲线图</div>
              <div class="layui-card-body">
            <div id="main" style="height:350px;"></div>
                
              </div>
            </div>
         
          </div> 
          
        <div style="float:left ;width:35%;margin-left:20px;">
        <div class="layui-card" style="width:100%">
          <div class="layui-card-header">区域基金全年使用</div>
          <div class="layui-card-body layui-text" style="height:180px;">
            <table class="layui-table" style="border:1px solid;">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>区域名称</td>
                  <td>
					基金金额（万元）
                  </td>
                   <td>基金占比</td>
                </tr>
                <tr>
                  <td>北京市</td>
                  <td> 1574.20</td>
                  <td>15.21%</td>
                </tr>
                <tr>
                  <td>广州市</td>
                  <td>1157.32</td>
                   <td>12.58%</td>
                </tr>
                <tr>
                  <td>西安市</td>
                  <td>623.57</td>
                   <td>6.14%</td>
                </tr>
              </tbody>
            </table>
          </div>
          </div>
            
        <div class="layui-card" style="width:100%">
          <div class="layui-card-header">医院基金全年使用</div>
          <div class="layui-card-body layui-text" style="height:260px;">
            <table class="layui-table" style="border:1px solid;">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>医院名称</td>
                  <td>
					基金金额（万元）
                  </td>
                  <td>
					基金占比
                  </td>
                </tr>
                <tr>
                  <td>洛江万禾医院</td>
                  <td> 873</td>
                  <td>9.3%</td>
                </tr>
                <tr>
                  <td>洛江阳光医院</td>
                  <td>514</td>
                   <td>6.2%</td>
                </tr>
                <tr>
                  <td>解放军第180医院</td>
                  <td>542</td>
                   <td>6.6%</td>
                 </tr>
                </tbody>
               </table>
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
             
             var option = option = {
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
            		        }
            		    ],
            		    yAxis : [
            		        {
            		            type : 'value'
            		        }
            		    ],
            		    series : [
            		        {
            		            name:'在职职工人次',
            		            type:'line',
            		            stack: '总量',
            		            areaStyle: {},
            		            data:[150, 132, 201, 234, 190, 230, 110, 130, 280, 130, 290, 140]
            		        },
            		        {
            		            name:'退休职工人次',
            		            type:'line',
            		            stack: '总量',
            		            areaStyle: {},
            		            data:[220, 282, 191, 134, 290, 230, 110, 230, 290, 270, 310, 150]
            		        },
            		        {
            		            name:'居民人次',
            		            type:'line',
            		            stack: '总量',
            		            areaStyle: {},
            		            data:[150, 232, 201, 154, 190, 130, 410, 200, 180, 130, 210, 260]
            		        }
            		      
            		    ]
            		};
             myChart.setOption(option);
         }
     );
    </script>
    
  <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console']);
  </script>
</body>
</html>
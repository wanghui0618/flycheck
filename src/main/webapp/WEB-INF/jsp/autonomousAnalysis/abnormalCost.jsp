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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/login.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
<style>
input::-webkit-outer-spin-button,            
input::-webkit-inner-spin-button{                -webkit-appearance: none !important;            }
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
<title>住院费用分析</title>
</head>
<body style="overflow: hidden">
<div class="layui-fluid" style="overflow: hidden">
  <div class="layui-row layui-col-space15">

    <div class="layui-col-md12">
    <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
      <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
          <div class="layui-form-item">
			<div class="layui-inline pt" style="margin-right: 4px;">
			 <div class="layui-inline">
		                 <label class="layui-form-label">疾病名称</label>
				            <div class="layui-input-inline">
				                <input id = "hahaha" type="text" name="illName"  required  autocomplete="off" class="layui-input">    
				            </div>
				          </div>
				</div>
				<div class="layui-inline">
		                 <label class="layui-form-label">偏离度</label>
				            <div class="layui-input-inline">
				                <input id = "gggg" value = "3" type="number" min = "1" name="beiShu" onkeyup="this.value=this.value.replace(/\D|^0/g,'')"  required  autocomplete="off" class="layui-input">    
				            </div>
				          </div>
			<button
		style="display: inline-block; position: absolute; margin-top: 8px; padding: 0px 7px;margin-left: 20px"
			class="layui-btn layui-btn-sm layuiadmin-btn-useradmin"
			lay-submit lay-filter="LAY-user-front-search2">
				<i
					class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
				</button>
			</div>
        </div>

        <div class="layui-card-body">
         	<table id="condidtionTable" class="layui-hide" lay-filter="test"
			lay-filter="conditionTest"></table>
        </div>
      </div>
      </div>
      
      <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
      	<div class="layui-card">
			<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
	  			<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	  			费用分析
	  		</div>
		    <div class="layui-card-body" style="color:#3D3D3D">
		    	<div id="mainTwo" style="height:document.documentElement.clientHeight-80"></div>
		    </div>
	  	</div>
      </div>
      
    </div>
  
  </div>
</div>
<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/itemCost/medicalCost.js"></script>
<script
		src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/costAnalysis-zy2.js"></script>
</body>
<script type="text/javascript" >
var illName = document.getElementById("hahaha").value;
$.ajax({
	 url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/abnormalCost',
	 //url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/monthlyTrendsData',
	  type : "post",		
      async : false,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
      dataType : "json",
      data:{illName:illName},
      success : function(result) {
    	 document.getElementById('mainTwo').style.height = document.documentElement.clientHeight-80 + "px";
   	 	var hvdata=result.data;
   	 	//console.log(hvdata);
   	 	var mon = parseFloat(hvdata[0].tCsot);
   	 	var name = document.getElementById("hahaha").value;
   	 	//console.log(name);
   	 	//console.log(mon);
   	 require.config({
		        paths: {
		            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
		        }
		    });
   	 require(
			     [
			         'echarts',
			         'echarts/chart/scatter' ,
			         'echarts/chart/bar'
			     ],
			     function (ec) {
			         var myChart = ec.init(document.getElementById('mainTwo')); 
			         var option = {
			 			    title : {
						        text: '住院费用',
						       
						    },
						    tooltip : {
						        trigger: 'axis',
						        showDelay : 0,
						        formatter : function (params) {
						            if (params.value.length > 1) {
						                return params.seriesName + ' :<br/>'
						                   + params.value[1] + '元' +'<br/>'
						                   + params.value[3]  +'<br/>'
						                   + params.value[2];
						            }
						            else {
						                return params.seriesName + ' :<br/>'
						                   + params.name + ' : '
						                   + params.value + ' ';
						            }
						        },  
						        /*axisPointer:{
						            show: true,
						            type : 'cross',
						            lineStyle: {
						                type : 'dashed',
						                width : 1
						            }
						        }*/
						    },
						    legend: {
						        data:['费用']
						    },
						    /*toolbox: {
						        show : true,
						        feature : {
						            mark : {show: true},
						            dataZoom : {show: true},
						            dataView : {show: true, readOnly: false},
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },*/
						    xAxis : [
						        {
						            type : 'value',
						            data: [1,2,3,4,5,6,7,8,9,10,11,12],
						            axisLabel : {
						                formatter: '{value} 月'
						            }
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value',
						            name:'费用',
						            data: [],
						       		axisLabel : {
					                formatter: '{value} 元'
					            	}
						        }
						    ],
						    series : [
						        
						        {
						            name:'费用',
						            type:'scatter',
						            symbolSize: 10,
						            data: [
						            ],
						            /*markPoint : {
						               /* data : [
						                    {type : 'max', name: '最大值'},
						                    {type : 'min', name: '最小值'}
						                ]
						            },*/
						            markLine : {
						            	itemStyle:{
						            		normal:{
						            			label:{
						            				formatter :'住院次均费用',
						            			}
						            		}
						            	},
						                data : [
						                	//{type : 'average', name: '平均值'},
						                	//{name:'ggg',yAxis:25000}
						                	//{type : 'average*3', name: '平均值'},
						                	//{type : 'average/3', name: '平均值'}
						                	[ 
						                		{ name: mon+'元', xAxis: 0, yAxis: mon},
						                        { xAxis: 12, yAxis: mon },
						                    ],
						                ]
						            },
						        }
						    ]
						};
			         //console.log(option);
			         var i = 0;
			         var data2 = [0,mon,'平均费用',''];
			         //console.log(data2);
			         option.series[0].data.push(data2);
			         for(i = 0; i < hvdata.length;i++){
			        	 var data1 = [];
			        	 if(parseFloat(hvdata[i].totalCost)*3 <= mon || parseFloat(hvdata[i].totalCost)/3 >= mon){
			        		 data1.push(parseInt(hvdata[i].monthTime));
   			        	 	data1.push(parseFloat(hvdata[i].totalCost));
   			        	 	data1.push(hvdata[i].orgName);
   			        	 	data1.push(hvdata[i].yearData);
   			        	 	//console.log(data1);
   			        	 option.series[0].data.push(data1);
   			        	 //console.log(parseFloat(hvdata[i].totalCost));
			        	 }
			        	 
			        	//console.log(option.series[0].data);
			         }
			         //myChart = layui.echarts.init($('#mainTwo')[0]);
			         myChart.setOption(option); 
			     	}
			     );
    }
});
</script>
</html>
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
<title>主页面</title>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 24px;
    font-size: 14px;
}
#showPic img{
	position:relative;
	height:250px;
}
.layui-tab-title .layui-this:after {
    height: 23px!important;
}
.daiban li{
	height: 65px;
	width: 180px;
	border-radius: 2px;
	color:#fff;
	margin: 0px 6px 13px 6px;
}
.daichushen{
	background:linear-gradient(to right, #2286FF , #27AAFF);
}
.daijihe{
	background:linear-gradient(to right, #EB6046 , #F38655);
}
.daigongshi{
	background:linear-gradient(to right, #EF8F08 , #F4AC0B);
}
.daizhongshen{
	background:linear-gradient(to right, #00B768 , #00D686);
}
.layui-table-body {
    overflow: hidden;
}
.layui-table-page {
    border-top: 0px;
}
/* .zr-element{
	left: -40px;
	width: 520px!important;
	height: 319px!important;
} */
.layui-input-block {
    margin-left: 60px;
}
.layui-form-label {
    padding: 9px 0px;
    width: 60px;
}
.layui-tab-brief > .layui-tab-more li.layui-this::after, .layui-tab-brief > .layui-tab-title .layui-this::after {
    border: none;
    border-bottom: 0px;
}
.layui-this{
	background-color: #2284FF;
	color:#fff!important;
	border-radius: 1px;
}
</style>
</head> 
<body>

<div class="layui-fluid" style="overflow-y:hidden;">
  	<div class="layui-row layui-col-space15">
  	<div class="layui-col-md12">
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">今天</label><label id="dateweek" style="margin-left:10px;">&nbsp;</label>
				</div>
  				<div class="layui-card-body">
				  	<ul class="layui-row layui-col-space10 daiban">
                        <li class="layui-col-xs2 daichushen" href="javascript:;"  >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:45px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daichushen.png"  />
                        	</div>
                            <div style="line-height:25px;">
                             	<h3 style="font-size: 14px;">本年度就诊人次</h3>
                               	<p style="font-size: 22px;"><label id="all">154795</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 daijihe" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:45px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daijihe.png"  />
                        	</div>
                            <div style="line-height:25px;">
                             	<h3 style="font-size: 14px;">住院人次</h3>
                               	<p style="font-size: 22px;"><label id="zy">47795</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 daigongshi" href="javascript:;">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:45px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height:25px;">
                             	<h3 style="font-size: 14px;">门诊人次</h3>
                               	<p style="font-size: 22px;"><label id="mz">96598</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 daizhongshen" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:45px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daizhongshen.png"  />
                        	</div>
                            <div style="line-height:25px;">
                             	<h3 style="font-size: 14px;">住院总费用(元)</h3>
                               	<p style="font-size: 22px;"><label id="zhuzong">7864</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 daizhongshen" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:45px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daizhongshen.png"  />
                        	</div>
                            <div style="line-height:25px;">
                             	<h3 style="font-size: 14px;">门诊总费用(元)</h3>
                               	<p style="font-size: 22px;"><label id="menz">9884</label></p>
                            </div>
                        </li>
                    </ul>
  				</div>
  			</div>
	</div>
  		<div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />月度住院就诊人数柱状图</div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main" style="height:290px;"></div>      
              </div>
            </div>
        </div>
  		<div class="layui-col-md4">
      		<div class="layui-card" style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
  				<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					本年度住院（门诊）数据信息 
  					<span style="float: right;"href="javascript:;" lay-href="<%=request.getContextPath()%>/violationDetail/violationDetail">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  				</div>
		 		<div class="layui-card-body">
		 		    <table id="sysVerify" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0">
		 		  <tbody>
              <!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
                <tr style="background:#E5F7EF">
                  <td>本年度住院（门诊）</td>
                  <td>就诊人次数</td>
                  <td>本年住院（门诊）</td>
                  <td>总费用 </td>
                  <td>统筹支出费用</td>  
                </tr>
                <tr>
                  <td>陕西省</td>
                  <td>西安市</td>
                  <td>2019-1-21</td>
                  <td>35</td>
                  <td>45</td>
                 
                </tr>
                 <tr style='background:#F5FCF9'>
                  <td>陕西省</td>
                  <td>西安市</td>
                  <td>2019-1-21</td>
                  <td>35</td>
                  <td>45</td>
                 
                </tr>
                <tr>
                  <td>陕西省</td>
                  <td>西安市</td>
                   <td>2019-1-21</td>
                  <td>35</td>
                  <td>45</td>
                  
                </tr>
                 <tr style='background:#F5FCF9'>
                  <td>陕西省</td>
                  <td>西安市</td>
                  <td>2019-1-21</td>
                  <td>35</td>
                  <td>45</td>
                  
                </tr>
              </tbody>
           </table>
			    </div>
    	    </div>
  	    </div>
		<div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
              	月度住院总费用，统筹支出费用柱状图（同比增长率 折线图）
              
              </div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main1" style="height:290px;"></div>      
              </div>
            </div>
        </div>
	</div>
  
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	各地区门诊总费用 就诊人数 次均费用 气泡图 
				</div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main2" style="height:290px;"></div>      
              </div>
            </div>
        </div>
  		<div class="layui-col-md4">
      		 <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
              	 按就诊类型（门诊、住院）统筹支付费用占比
              
              </div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main3" style="height:290px;"></div>      
              </div>
  	    </div>
		<div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
              	各地区住院总费用 就诊人数 次均费用 气泡图 
              
              </div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main41" style="height:290px;"></div>      
              </div>
            </div>
        </div>
           </div>
           <div class="layui-row layui-col-space15">
           <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />病种按住院费用排名（矩形树图）</div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main4" style="height:290px;"></div>      
              </div>
            </div>
        </div>
  		<div class="layui-col-md4">
      		<div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />各类就诊人群统筹支付费用占比图（雷达图）</div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main5" style="height:290px;"></div>      
              </div>
            </div>
  	    </div>
		<div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />各地区违规占比排名 横柱状图 （点各地区能否下钻至每家医院的占比排名）
</div>
              <div class="layui-card-body" style="height: 315px;">
            <div id="main6" style="height:290px;"></div>      
              </div>
            </div>
        </div>
	
      </div>
   
	  <script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
	 <script type="text/javascript">
	 var myChart = echarts.init(document.getElementById('main'));
     // 指定图表的配置项和数据
  option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {	
        data:['人数','比率']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '住院就诊人数',
            min: 0,
           
            interval: 100,
            axisLabel: {
                formatter: '{value} '
            }
        },
        {
            type: 'value',
            name: '同比增长率',
            min: 0,
            max: 100,
            interval: 10,
            axisLabel: {
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name:'人数',
            type:'bar',
            data:[55, 65, 75, 85, 95, 60, 100, 150, 190,130, 230, 142]
        },
        
       
        {
            name:'比率',
            type:'line',
            yAxisIndex: 1,
            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
        }
    ]
};

     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
  <script type="text/javascript">
	 var myChart = echarts.init(document.getElementById('main1'));
     // 指定图表的配置项和数据
   option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {	
        data:['住院费用','支出费用','比率']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '费用',
            min: 0,
           
            interval: 100,
            axisLabel: {
                formatter: '{value} '
            }
        },
        {
            type: 'value',
            name: '同比增长率',
            min: 0,
            max: 100,
            interval: 10,
            axisLabel: {
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name:'住院费用',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
        },
        {
            name:'支出费用',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
        },
       
        {
            name:'比率',
            type:'line',
            yAxisIndex: 1,
            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
        }
    ]
};
     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
     <script type="text/javascript">
	 var myChart = echarts.init(document.getElementById('main2'));
     // 指定图表的配置项和数据
  var data = [
    [[28604,77,17096869,'Australia',1990],[31163,77.4,27662440,'Canada',1990],[1516,68,1154605773,'China',1990]],
    [[44056,81.8,23968973,'Australia',2015],[43294,81.7,35939927,'Canada',2015],[13334,76.9,1376048943,'China',2015]],
    [[21291,78.5,11389562,'Cuba',2015],[38923,80.8,5503457,'Finland',2015],[37599,81.9,64395345,'France',2015]]
];

option = {
    backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
        offset: 0,
        color: '#f7f8fa'
    }, {
        offset: 1,
        color: '#cdd0d5'
    }]),
    title: {
        text: '门诊地区信息统计图'
    },
    legend: {
        right: 10,
        data: ['门诊总费用', '就诊人数','次均费用']
    },
    xAxis: {
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        }
    },
    yAxis: {
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        },
        scale: true
    },
    series: [{
        name: '门诊总费用',
        data: data[0],
        type: 'scatter',
        symbolSize: function (data) {
            return Math.sqrt(data[2]) / 5e2;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(120, 36, 50, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(251, 118, 123)'
                }, {
                    offset: 1,
                    color: 'rgb(204, 46, 72)'
                }])
            }
        }
    }, {
        name: '就诊人数',
        data: data[1],
        type: 'scatter',
        symbolSize: function (data) {
            return Math.sqrt(data[2]) / 5e2;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(25, 100, 150, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(129, 227, 238)'
                }, {
                    offset: 1,
                    color: 'rgb(25, 183, 207)'
                }])
            }
        }
    },{
        name: '次均费用',
        data: data[2],
        type: 'scatter',
        symbolSize: function (data) {
            return Math.sqrt(data[2]) / 5e2;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(14, 89, 132, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(156, 256, 240)'
                }, {
                    offset: 1,
                    color: 'rgb(23, 170, 199)'
                }])
            }
        }
    }]
};
     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
    
     
     <script type="text/javascript">
	 var myChart = echarts.init(document.getElementById('main3'));
     // 指定图表的配置项和数据
 option = {
    title : {
        
        subtext: '数据汇总',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['门诊','住院']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'门诊'},
                {value:310, name:'住院'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
 
 
 <script type="text/javascript">
	 var myChart = echarts.init(document.getElementById('main41'));
     // 指定图表的配置项和数据
    // 指定图表的配置项和数据
  var data = [
    [[31476,75.4,78958237,'Germany',1990],[28666,78.1,254830,'Iceland',1990],[1777,57.7,870601776,'India',1990],[29550,79.1,122249285,'Japan',1990],[2076,67.9,20194354,'North Korea',1990],[12087,72,42972254,'South Korea',1990],[24021,75.4,3397534,'New Zealand',1990],[43296,76.8,4240375,'Norway',1990],[10088,70.8,38195258,'Poland',1990],[19349,69.6,147568552,'Russia',1990],[10670,67.3,53994605,'Turkey',1990],[26424,75.7,57110117,'United Kingdom',1990],[37062,75.4,252847810,'United States',1990]],
    [[24787,77.3,38611794,'Poland',2015],[23038,73.13,143456918,'Russia',2015],[19360,76.5,78665830,'Turkey',2015],[38225,81.4,64715810,'United Kingdom',2015],[53354,79.1,321773631,'United States',2015]],
    [[28604,77,17096869,'Australia',1990],[31163,77.4,27662440,'Canada',1990],[1516,68,1154605773,'China',1990]]
];

option = {
    backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
        offset: 0,
        color: '#f7f8fa'
    }, {
        offset: 1,
        color: '#cdd0d5'
    }]),
    title: {
        text: '住院地区信息统计'
    },
    legend: {
        right: 10,
        data: ['门诊总费用', '就诊人数','次均费用']
    },
    xAxis: {
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        }
    },
    yAxis: {
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        },
        scale: true
    },
    series: [{
        name: '门诊总费用',
        data: data[0],
        type: 'scatter',
        symbolSize: function (data) {
            return Math.sqrt(data[2]) / 5e2;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(120, 36, 50, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(251, 118, 123)'
                }, {
                    offset: 1,
                    color: 'rgb(204, 46, 72)'
                }])
            }
        }
    }, {
        name: '就诊人数',
        data: data[1],
        type: 'scatter',
        symbolSize: function (data) {
            return Math.sqrt(data[2]) / 5e2;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(25, 100, 150, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(129, 227, 238)'
                }, {
                    offset: 1,
                    color: 'rgb(25, 183, 207)'
                }])
            }
        }
    },{
        name: '次均费用',
        data: data[2],
        type: 'scatter',
        symbolSize: function (data) {
            return Math.sqrt(data[2]) / 5e2;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(14, 89, 132, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(156, 256, 240)'
                }, {
                    offset: 1,
                    color: 'rgb(23, 170, 199)'
                }])
            }
        }
    }]
};
     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
 
 
 
  <script type="text/javascript">
  var myChart = echarts.init(document.getElementById('main4'));
  option = {
		    series: [{
		        type: 'treemap',
		        data: [{
		            name: '疾病费用排名',            // First tree
		            value: 500,
		            children: [{
		                name: '不孕不育',       // First leaf of first tree
		                value: 45
		            }, {
		                name: '脑梗',       // Second leaf of first tree
		                value: 55
		            }, {
		                name: '脑瘫',       // Second leaf of first tree
		                value: 300
		            }, {
		                name: '疾病式感冒',       // Second leaf of first tree
		                value: 79
		            }, {
		                name: '急性阑尾炎',       // Second leaf of first tree
		                value: 121
		            },]
		        }]
		            }]
		     
		};

     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
    
     <script type="text/javascript">
  var myChart = echarts.init(document.getElementById('main5'));
  option = {
		  /*   title: {
		        text: '基础雷达图'
		    }, */
		    tooltip: {},
		    legend: {
		    	left: 20,
		        data: ['统筹支付费用占比']
		    },
		    radar: {
		        // shape: 'circle',
		        name: {
		            textStyle: {
		                color: '#fff',
		                backgroundColor: '#999',
		                borderRadius: 3,
		                padding: [3, 5]
		           }
		        },
		        indicator: [
		           { name: '职员', max: 6000},
		           { name: '公务员', max: 3000},
		           { name: '农名工', max: 4000},
		           { name: '学生', max: 3000}
		        ]
		    },
		    series: [{
		        name: '统筹支付费用占比',
		        type: 'radar',
		        // areaStyle: {normal: {}},
		        data : [
		            {
		                value : [5788, 1280, 2260, 1110],
		                name : '统筹支付费用占比'
		            }
		        ]
		    }]
		};
     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
    <script type="text/javascript">
  var myChart = echarts.init(document.getElementById('main6'));
  option = {
		    title: {
		       /*  text: '世界人口总量',
		        subtext: '数据来自网络' */
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: ['违规占比排名']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    yAxis: {
		        type: 'category',
		        data: ['广州市','成都市','遂宁市','北京市','西安市']
		    },
		    series: [
		        {
		            name: '违规占比排名',
		            type: 'bar',
		            data: [18203, 23489, 29034, 104970, 131744]
		           
		        }
		    ]
		};

     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
  <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console']);
  </script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/hospitalViolation/hospitalViolation.js"></script>
</body>
</html>
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
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<title>主页</title>
<style>

</style>
</head> 
<body>

<div class="layui-fluid" style="overflow-y:hidden;">
  	<div class="layui-row layui-col-space15">
  		<div class="layui-col-md4" style="width: 50%">
            <div class="layui-card" style="width: 100%;height: 500px;">
              <div class="layui-card-header" style="">当年就诊人数</div>
              <font style="margin-left: 15px;font-size: 1.5em"><font id="peopleNumber"></font>&nbsp; <i class="layui-icon layui-icon-group" style="color: #3DD8E8;"></i>	</font>
              <div style="float: right;margin-right: 41px;"><div style="float: left">本周就诊人次</div> <div style="border: 1px solid #1D7182;float: left;width: 75px;text-align: center;margin-left: 8px;"><span id="benZhouRenShu"></span> &nbsp;人</div> </div>
              <hr class="layui-bg-cyan" style="margin: 0px 41px 0px 15px;">
              <div class="layui-card-body" style="height: 390px;width: 99%;margin-top: 5px;" >
                   <div class="layui-card" style="width: 48%;height: 407px;float: left">
                    <div class="layui-card-header" style="font-size: 14px;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />门诊</div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin-left: 15px;border: 1px solid #1D7182;" > 门诊人次<span style="margin-right: 10%;float: right;" id="menZhenRenCi"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="zuorimenzhenren" >
                            <div class="layui-progress-bar layui-bg-blue"  id="zuorimenzhenren1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;"  > 门特人次<span style="margin-right: 10%;float: right;" id="menTeRenCi"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="mente" >
                            <div class="layui-progress-bar layui-bg-blue"  id="mente1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                   <div class="layui-card-body" style="height: 42px;width:90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" > 手术人次<span style="margin-right: 10%;float: right;" id="menZhenShouShu"></span>
                        <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="menshou" >
                            <div class="layui-progress-bar layui-bg-blue" id="menshou1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" >药品数量<span style="margin-right: 10%;float: right;" id="menYao"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="menzhenyao" >
                            <div class="layui-progress-bar layui-bg-blue" id="menzhenyao1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" >耗材数量<span style="margin-right: 10%;float: right;" id="menHao"></span>
                        <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="menzhenhao" >
                            <div class="layui-progress-bar layui-bg-blue" id="menzhenhao1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" >诊疗数量<span style="margin-right: 10%;float: right;" id="menZhenliao"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="menzhenzhenliao" >
                            <div class="layui-progress-bar layui-bg-blue" id="menzhenzhenliao1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
               </div>
               <div class="layui-card" style="width: 48%;height: 407px;float: left;">
                    <div class="layui-card-header" style="font-size: 14px;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />住院</div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin-left: 15px;border: 1px solid #1D7182;" > 住院人次<span style="margin-right: 10%;float: right;" id="ruYuanRenCi"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="zuoriruyuan" >
                            <div class="layui-progress-bar layui-bg-blue" id="zuoriruyuan1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" > 出院人次<span style="margin-right: 10%;float: right;" id="chuYuanRenCi"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="chuYuan" >
                            <div class="layui-progress-bar layui-bg-blue" id="chuYuan1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                   <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" > 手术人次<span style="margin-right: 10%;float: right;" id="zhuYuanShouShu"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="zhushou" >
                            <div class="layui-progress-bar layui-bg-blue" id="zhushou1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" > 药品数量<span style="margin-right: 10%;float: right;" id="zhuYao"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="zhuyuanyao" >
                            <div class="layui-progress-bar layui-bg-blue" id="zhuyuanyao1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" > 耗材数量<span style="margin-right: 10%;float: right;" id="zhuHao"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="zhuyuanhao" >
                            <div class="layui-progress-bar layui-bg-blue" id="zhuyuanhao1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="height: 42px;width: 90%;margin: 8px 0px 0px 15px;border: 1px solid #1D7182;" > 诊疗数量<span style="margin-right: 10%;float: right;" id="zhuZhenliao"></span>
                         <div class="layui-progress layui-progress-big" style="height: 11px" lay-filter="zhuyuanzhenliao" >
                            <div class="layui-progress-bar layui-bg-blue"  id="zhuyuanzhenliao1" lay-percent="" style="height: 11px"></div>
                        </div>
                    </div>
               </div>
              </div>
            </div>
        </div>
  		<div class="layui-col-md4" style="width: 50%">
            <div class="layui-card" style="width: 100%;height: 500px;">
              <div class="layui-card-header" style="">当年总收入</div>
              <font style="margin-left: 15px;font-size: 1.5em" id="zongFeiYong">&nbsp;<font style="color: #F69278">&nbsp;</font></font>
               <div style="float: right;margin-right: 18px;"><div style="float: left">本周总收入</div> <div style="border: 1px solid #1D7182;float: left;width: 75px;text-align: center;margin-left: 8px;"><span id="zhouShouRu"></span> &nbsp;万元</div> </div>
              <hr class="layui-bg-cyan" style="margin: 0px 18px 0px 15px;">
              <div class="layui-card-body" style="height: 390px;width: 99%;" >
                   <div class="layui-card" style="width: 100%;height: 185px;">
                      <div class="layui-card-header" style="font-size: 14px;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />收入分类</div>
                         <div id="main" class="layui-card-body" style="height: 130px;width: 29%;margin-left: 15px;border: 1px solid #5D3836;float: left;" > 
                         </div>
                         <div id="main1" class="layui-card-body" style="height: 130px;width: 29%;margin-left: 15px;border: 1px solid #5D3836;float: left;" > 
                         </div>
                         <div id="main2" class="layui-card-body" style="height: 130px;width: 29%;margin-left: 15px;border: 1px solid #5D3836;float: left;" > 
                         </div>
                  </div>
                   <div class="layui-card" style="width: 40%;height: 180px;float: left;">
                      <div class="layui-card-header" style="font-size: 14px;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />本周医疗总收入分类构成</div>
                         <div id="main3" class="layui-card-body" style="height: 130px;width: 90%;margin-left: 15px;border: 1px solid #5D3836;float: left;" > 
                         </div>
                  </div>
                  <div class="layui-card" style="width: 60%;height: 180px;float: left;">
                      <div class="layui-card-header" style="font-size: 14px;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />最近一周次均费用</div>
                         <div id="main4" class="layui-card-body" style="height: 130px;width: 80%;margin-left: 15px;border: 1px solid #5D3836;float: left;" > 
                         </div>
                  </div>
              </div>
            </div>
        </div>
	</div>
      </div>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<!-- <script type="text/javascript">
	$(function() {
		$.ajax({
    	     url:$WEB_ROOT_PATH+'/dhccApi/medical/medical/AdminBigData',
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",	
		         success : function(result) {
		        	 console.log(result)
		        	 console.log(result[0].peopleNmuber);
		        	 $("#peopleNumber").html(result[0].peopleNmuber);
		         }
       });
	});
	</script> -->
	 <!-- <script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('main'));

     /* app.title = '环形图'; */
     
       var data = [
    	   {value:223, name:'门诊'},
           {value:180, name:'门特'},
           {value:362, name:'住院'}
        ]

     var option = {
    		 title: {
                 text: '业务维度分析',
                 "x": '9%',
                 "y": '',
                 textStyle:{ //设置主标题风格
                	 color:'#4679BB',//设置主标题字体颜色
                	 fontStyle:'',//主标题文字风格
                	 "fontSize": 10,
                	 }
             },
    		 tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c}万元 ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: '55%',
    		        y: '25%',
    		        itemWidth: 10,             // 图例图形宽度
    		        itemHeight: 7,
    		        textStyle:{
                        fontSize: 9
                    },
    		        data:['门诊','门特','住院'],
    		        formatter: function (name) {
                        var target;
                        for (var i = 0, l = data.length; i < l; i++) {
                        if (data[i].name == name) {
                            target = data[i].value;
                            }
                        }
                        var arr = [
                            name+'   '+target+'万'
                        ]
                        return arr.join('\n')
		            }
    		    },
    		    "color": ["#53C181","#6A66C3","#3F78B7"],
    		    series: [
    		        {
    		            name:'业务维度分析',
    		            type:'pie',
    		            hoverAnimation:false,
                        center:['25%','55%'],
    		            radius: ['35%', '60%'],
    		            avoidLabelOverlap: false,
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:data
    		        }
    		    ]
         };
  // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
    <script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('main1'));

     /* app.title = '环形图'; */
     
       var data1 = [
    	   {value:453, name:'一级医院'},
           {value:193, name:'二级医院'},
           {value:119, name:'三级医院'}
        ]

     var option = {
    		 title: {
                 text: '级别维度分析',
                 "x": '9%',
                 "y": '',
                 textStyle:{ //设置主标题风格
                	 color:'#4679BB',//设置主标题字体颜色
                	 fontStyle:'',//主标题文字风格
                	 "fontSize": 10,
                	 }
             },
    		 tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c}万元 ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: '55%',
    		        y: '25%',
    		        itemWidth: 10,             // 图例图形宽度
    		        itemHeight: 7,
    		        textStyle:{
                        fontSize: 9
                    },
    		        data:['一级医院','二级医院','三级医院'],
    		        formatter: function (name) {
                        var target;
                        for (var i = 0, l = data1.length; i < l; i++) {
                        if (data1[i].name == name) {
                            target = data1[i].value;
                            }
                        }
                        var arr = [
                            name+'  '+target+'万'
                        ]
                        return arr.join('\n')
		            }
    		    },
    		    "color": ["#53C181","#6A66C3","#3F78B7"],
    		    series: [
    		        {
    		            name:'级别维度分析',
    		            type:'pie',
    		            hoverAnimation:false,
                        center:['25%','55%'],
    		            radius: ['35%', '60%'],
    		            avoidLabelOverlap: false,
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:data1
    		        }
    		    ]
         };
  // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
    <script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('main2'));

     /* app.title = '环形图'; */
     
       var data2 = [
    	   {value:386, name:'市属医院'},
           {value:223, name:'县属医院'},
           {value:156, name:'区属医院'}
        ]

     var option = {
    		 title: {
                 text: '归属维度分析',
                 "x": '9%',
                 "y": '',
                 textStyle:{ //设置主标题风格
                	 color:'#4679BB',//设置主标题字体颜色
                	 fontStyle:'',//主标题文字风格
                	 "fontSize": 10,
                	 }
             },
    		 tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c}万元 ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: '55%',
    		        y: '25%',
    		        itemWidth: 10,             // 图例图形宽度
    		        itemHeight: 7,
    		        textStyle:{
                        fontSize: 9
                    },
    		        data:['市属医院','县属医院','区属医院'],
    		        formatter: function (name) {
                        var target;
                        for (var i = 0, l = data2.length; i < l; i++) {
                        if (data2[i].name == name) {
                            target = data2[i].value;
                            }
                        }
                        var arr = [
                            name+'   '+target+'万'
                        ]
                        return arr.join('\n')
		            }
    		    },
    		    "color": ["#53C181","#6A66C3","#3F78B7"],
    		    series: [
    		        {
    		            name:'归属维度分析',
    		            type:'pie',
    		            hoverAnimation:false,
                        center:['25%','55%'],
    		            radius: ['35%', '60%'],
    		            avoidLabelOverlap: false,
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:data2
    		        }
    		    ]
         };
  // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
    
     <script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('main3'));

     /* app.title = '环形图'; */
     
       var data3 = [
    	   {value:2678, name:'药品费'},
           {value:812, name:'耗材费'},
           {value:1178, name:'诊疗费'},
        ]

     var option = {
    		 tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c}万元 ({d}%)"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: '50%',
    		        y: '30%',
    		        itemWidth: 15,             // 图例图形宽度
    		        itemHeight: 10,
    		        textStyle:{
                        fontSize: 12
                    },
    		        data:['药品费','耗材费','诊疗费'],
    		        formatter: function (name) {
                        var target;
                        for (var i = 0, l = data3.length; i < l; i++) {
                        if (data3[i].name == name) {
                            target = data3[i].value;
                            }
                        }
                        var arr = [
                            name+'   '+target+'万'
                        ]
                        return arr.join('\n')
		            }
    		    },
    		    series: [
    		        {
    		            name:'本周医疗总收入分类构成',
    		            type:'pie',
    		            hoverAnimation:false,
                        center:['17%','55%'],
    		            radius: ['35%', '60%'],
    		            avoidLabelOverlap: false,
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:data3
    		        }
    		    ]
         };
  // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script>
    
     <script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('main4'));

     /* app.title = '环形图'; */
     

     var option = {
             xAxis: {
                 type: 'category',
                 boundaryGap: false,
                 data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
             },
             yAxis: {
                 type: 'value'
             },
             grid: {
                 x:  45,
                 x2: 15,
                 y: 30,
                 y2: 30
             },
             series: [{
                 data: [125, 191, 620, 345, 567, 1130, 873],
                 
                 type: 'line',
                 areaStyle: {
                   normal: {
                	    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                           { offset: 0, color: "LightSalmon" },
                           { offset: 0.5, color: "pink" },
                           { offset: 1, color: "Seashell" }
                         ])
                         /* color:'#E77D65' */
                   }
                 },
                 itemStyle : { 
                	 normal: {
                		 label : {
                			 show: true,
                			 textStyle: {
                			        color: '#E77D65',
                			        fontSize:'10',
                			    }
                			 }
                     }
                 },
             }]
         };
  // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
    </script> -->
    
  <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console']);
  </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/home/bigDataHomepage.js"></script>
</body>
</html>
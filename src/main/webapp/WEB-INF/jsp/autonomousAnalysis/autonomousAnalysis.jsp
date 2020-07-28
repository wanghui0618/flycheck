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
</head>
<body>
	<div class="layui-fluid" style="overflow-y:hidden;">
  	<div class="layui-row layui-col-space15">
  		<div class="layui-col-md7">
      		<div class="layui-card" style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
      			<div class="layui-from">
  				<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					年度住院统计 
  				      
        				   <select  id="findYear" name="inFlag" onchange="getValue()" >
        				     
      	  	               </select>
                     
  					<span style="float: right;" lay-urlname="年度住院统计"  href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/yearData">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  				</div>
		 		<div class="layui-card-body">
		 		    <table id="hospitalInfo" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0">
				 		  <tbody>
				 		  <script type="text/html" id="xuhao">
   								{{d.LAY_TABLE_INDEX+1}}
						</script>
		              	  </tbody>
          			</table>
			    </div>
			 </div>
    	    </div>
  	    </div>
		<div class="layui-col-md5">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
              <img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
              	费用月度趋势
              	<!--  <select id="findYear1" name="year"  onchange="yearChange()">
				</select> -->
              </div>
              <div class="layui-card-body" style="height: 315px";>
            	 <div id="main1" class="echart" style="height:290px;">  
              </div>
            </div>
        </div>
	</div>
        <div class="layui-row layui-col-space15">
			<div class="layui-col-md4">
				 <div class="layui-card" style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
				
				 		<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
				 			<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
				 			总病例数
				 			<!--  <select id="findYear3" name="year" onchange="getValue1()" >
							</select> -->
							<span style="float: right;"  lay-urlname="总病例数" href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/allTotalCase">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
				 		</div>
				  		<div class="layui-card-body">
				  		    <table id="totalCase" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0">
				  		 		 <tbody>
				  		 		 </tbody>
				    		</table>
				 	    </div>
				 	 
				  </div>
			</div>
            <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
						平均住院天数  
						<!--  <select id="findYear4" name="year" onchange="yearChange2()" >
						</select> -->
						<span style="float: right;" lay-urlname="平均住院天数" href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/allAvgDay">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
			  </div>
              <div class="layui-card-body" style="height: 315px;">
            		<div id="main2" style="height: 315px;"></div>    
              </div>
            </div>
        </div>
		<div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
              	        平均住院费用
              	      <!--   <select id="findYear5" name="year" onchange="yearChange3()" >
						</select>  -->
						<span style="float: right;" lay-urlname="平均住院费用" href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/allAvgCost">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
              </div>
              <div class="layui-card-body" style="height: 315px;">
            		<div id="main41" style="height:315px;"></div>      
              </div>
            </div>
        </div>
           </div>
           <div class="layui-row layui-col-space15">
           <div class="layui-col-md4">
           	 <div class="layui-card" style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
           	 		<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
           	 			<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
           	 					药品总费用
           	 					<!--  <select id="findYear6" name="year" onchange="getValue4()" >
						</select> -->
						<span style="float: right;" lay-urlname="药品总费用" href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/allDrugCost">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
           	 		</div>
           	  		<div class="layui-card-body">
           	  		    <table id="drugCostRank" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0">
           	  		  		<tbody>
           	  		  		<script type="text/html" id="xuhao">
   								{{d.LAY_TABLE_INDEX+1}}
						</script>
           	   				</tbody>
           	   			</table>
           	 	    </div>
           	 </div>
          </div>
  		<div class="layui-col-md4">
  			 <div class="layui-card" style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
  			 		<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  			 			<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  			 			诊疗总费用
  			 			<!--<select id="findYear7" name="year" onchange="getValue5()" >
						</select>   -->
						<span style="float: right;" lay-urlname="诊疗总费用" href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/allServiceCostRank">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  			 		</div>
  			  		<div class="layui-card-body">
  			  		    <table id="serviceCostRank" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0">
  			  		  <tbody>
  			      			<script type="text/html" id="xuhao">
   								{{d.LAY_TABLE_INDEX+1}}
						</script>
  			       </tbody>
  			    </table>
  			 	    </div>
  			     </div>
  			 </div>
		<div class="layui-col-md4">
			 <div class="layui-card" style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
			 		<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
			 			<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
			 			耗材总费用
			 			<!--  <select id="findYear8" name="year" onchange="getValue6()" >
						</select> -->
						<span style="float: right;" lay-urlname="耗材总费用"  href="javascript:;" lay-href="<%=request.getContextPath()%>/autonomousAnalysis/allMaterialCostRank">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
			 		</div>
			  		<div class="layui-card-body">
			  		    <table id="materialCostRank" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0">
			  		  <tbody>			    			
						<script type="text/html" id="xuhao">
   								{{d.LAY_TABLE_INDEX+1}}
						</script>
			       </tbody>
			    </table>
			 	    </div>
			     </div>
			 </div>
	
      </div>
   </div>
     
	  <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/monthlyTrend.js"> </script>

<script type="text/javascript">
//基于准备好的dom，初始化echarts实例
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
          var myChart = ec.init(document.getElementById('main2')); 
          
          var option = {
        		  tooltip : {
        		        trigger: 'axis',
        		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        		        }
        		    },
        		    
        		    grid:{
        		    	x:70,
        		    	y:20,
        		    	x2:50,
        		    	y2:60
                    },
              xAxis : [
                  {
                      type : 'category',
                      axisLabel: {    
                    	  interval:0,
                          rotate:15,
                          textStyle: {
                        	  fontSize : 8,
                          }
                       } ,
                      data : [],
                      //data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                      axisTick: {
                          alignWithLabel: true
                      }
                  }
              ],
              yAxis : [
                  {
                      type : 'value'
                  }
              ],
              series : [
                  {
                      name:"平均住院天数",
                      type:"bar",
                      barWidth:16,
                      data:[],
                      itemStyle:{
                          normal:{
                              color:'#319CFE'
                          }
                      }
                  }
              ]
          };
  		
          // 为echarts对象加载数据 
            $.ajax({
         	     url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/findHosNumber',
			         type : "post",		
			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         dataType : "json",	
			         success : function(result) {
			        	 var hvdata=result.data;
			        	 //alert(hvdata);
			        		 var k;
			        		 if(hvdata.length>=10){
			        			 k=9;
			        		 }else{
			        			 k=hvdata.length;
			        		 }
			        		 for(var i=0;i<k;i++){ 
			        			 //alert(hvdata[i].avgDay)
			        				 option.series[0].data.push(hvdata[i].avgDay);
					                 option.xAxis[0].data.push(hvdata[i].orgName);		
				             } 
			        		 myChart.setOption(option); 
			         }
            });
          
      }
  );

//myChart.setOption(option);

</script>
  <script type="text/javascript">
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
          var myChart = ec.init(document.getElementById('main41')); 
          
          var option = {
        		  tooltip : {
        		        trigger: 'axis',
        		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        		        }
        		    },
        		    grid:{
        		    	x:50,
        		    	y:20,
        		    	x2:50,
        		    	y2:60
                    },
              xAxis : [
                  {
                      type : 'category',
                      axisLabel: {    
                    	  interval:0,
                          rotate:15,
                          textStyle: {
                        	  fontSize : 8,
                          }
                          
                       } ,
                      data : [],
                       //data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                      axisTick: {
                          alignWithLabel: true
                      }
                  }
              ],
              yAxis : [
                  {
                      type : 'value'
                  }
              ],
              series : [
                  {
                      name:"平均住院费用",
                      type:"bar",
                      barWidth:16,
                      data:[],
                      itemStyle:{
                          normal:{
                              color:'#319CFE'
                          }
                      }
                  }
              ]
          };
  		
          // 为echarts对象加载数据 
            $.ajax({
         	     url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/findMonNumber',
			         type : "post",		
			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         dataType : "json",	
			         success : function(result) {
			        	 var hvdata=result.data;
			        	 //alert(hvdata.length);
			        		 var i;
			        		 if(hvdata.length>10){
			        			 k=9;
			        		 }else{
			        			 k=hvdata.length;
			        		 }
			        		 for(var i=0;i<k;i++){ 
			        			 //alert(hvdata[i].name);
			        				 option.series[0].data.push(hvdata[i].avgCost);
					                 option.xAxis[0].data.push(hvdata[i].orgName);
				             } 
			        		 //alert(series);
			        		 myChart.setOption(option); 
			         }
            });
          
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/autonomousAnalysis.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/totalCase.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/drugCostRank.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/serviceCostRank.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/materialCostRank.js"></script>
  <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/monthlyTrend.js"> </script>  -->
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/avgDay.js"> </script> 
  <!--<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/autonomousAnalysis/avgCost.js"> </script>  -->

  
  <script type="text/javascript">
     var year=new Date().getFullYear(); //获取当前年份
     var sel = document.getElementById ('findYear');//获取select下拉列表
     for ( var i = year; i > year-3; i--)
     {
         var option = document.createElement ('option');
         option.value = i.toString();
         var txt = document.createTextNode (i);
         option.appendChild (txt);
         sel.appendChild (option);
     }
  </script>
 
</body>
<script type="text/javascript" >
var myChart;
require.config({
    paths: {
        echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
    }
});
  // 使用
require(
 [
     'echarts',
     'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
     'echarts/chart/line' 
 ],
 function (ec) {
     // 基于准备好的dom，初始化echarts图表
     var myChart = ec.init(document.getElementById('main1')); 
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
 	 	    legend: {	
 	 	        data:['住院费用','门诊费用','住院费用比率','门诊费用比率']
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
 	 	            /*type: 'value',
 	 	            name: '费用',
 	 	            min: 0,*/
 	 	           
 	 	            axisLabel: {
 	 	                formatter: '{value} 万元'
 	 	            }
 	 	        },
 	 	        {
 	 	            type: 'value',
 	 	            name: '比率',
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
	 	            data:[]
	 	        },
	 	        {
	 	            name:'门诊费用',
	 	            type:'bar',
	 	            data:[]
	 	        },
	 	       
	 	        {
	 	            name:'住院费用比率',
	 	            type:'line',
	 	            yAxisIndex: 1,
	 	            data:[]
	 	        },
	 	        
	 	        {
	 	            name:'门诊费用比率',
	 	            type:'line',
	 	            yAxisIndex: 1,
	 	            data:[]
	 	        }
	 	    ]
 	 	};
		
     // 为echarts对象加载数据 
       $.ajax({
    	     url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/monthlyTrendsData',
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",	
		         success : function(result) {
		        	 var hvdata=result.data;
		        	 //var myChart = echarts.init(document.getElementById('main1'));
		        	 //myChart.clear();
		        	 //document.getElementById('main1').removeAttribute('_echarts_instance_');
		        	 //alert(hvdata);
		        	 //console.log(hvdata);
		        	 var index,i=0;
		        	 	for(index=1;index<=12;index++){
		        	 		if(i<hvdata.length && index==parseInt(hvdata[i].monthTime)){
		        	 			option.series[0].data.push(hvdata[i].inHospital);
		        	 			option.series[1].data.push(hvdata[i].outCost);
		        	 			option.series[2].data.push(hvdata[i].inCost);
		        	 			option.series[3].data.push(hvdata[i].costRatio);
		        	 			i++;
		        	 		}
		        	 		else{
		        	 			option.series[0].data.push(0+0);
		        	 			option.series[1].data.push(0+0);
		        	 			option.series[2].data.push(0+0);
		        	 			option.series[3].data.push(0+0);
		        	 		}
		        	 		//option.series[0].data.push("1");
		        	 		//option.series[1].data.push("2");
		        	 	}
		        		myChart.setOption(option,true); 
		         }
       });
     
 }
);
</script>
</html>
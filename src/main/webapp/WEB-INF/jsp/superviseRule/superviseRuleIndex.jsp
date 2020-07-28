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
<title>主页面</title>
<style>
.layui-table td, .layui-table th {
	position: relative;
	padding: 9px 15px;
	min-height: 20px;
	line-height: 24px;
	font-size: 14px;
}

.layui-fluid {
	overflow: hidden;
}

.daiban li {
	height: 65px;
	width: 180px;
	border-radius: 3px;
	color: #fff;
	margin: 0px 6px 13px 6px;
}

.xinzeng {
	background-image: linear-gradient(-225deg, #27AEFF 0%, #2284FF 100%);
}

.youxiao {
	background-image: linear-gradient(-225deg, #F48A56 0%, #EA5C44 100%);
}

.wuxiao {
	background-image: linear-gradient(-225deg, #F5B00C 0%, #EF8D07 100%);
}

.zhuyuan {
	background-image: linear-gradient(-225deg, #00D888 0%, #00B365 100%);
}

.menzhen {
	background-image: linear-gradient(-225deg, #B28EF1 0%, #806BE4 100%);
}

.mente {
	background-image: linear-gradient(-225deg, #2DE0BB 0%, #03BCC7 100%);
}
</style>
</head>
<body>
	<div class="layui-fluid" style="overflow: hidden">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card" style="line-height: 40px;padding-left:10px;padding-right:10px;">
				    <h2 id="h2number" style="color:red"></h2>
				</div>
			</div>
		</div>
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						    监控规则违规排名 <a style="float: right;" lay-urlname="全市监控规则违规排名"
							href="javascript:;"
							lay-href="<%=request.getContextPath()%>/admin/MonitorNumber">查看全部<img
							style="width: 14px; margin-top: -2px; padding-right: 2px; margin-left: 6px;"
							src="<%=request.getContextPath()%>/images/auditing/more.png" /></a>
					</div>
					<div class="layui-card-body layui-text">
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody>
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td>排名</td>
									<td>规则名称</td>
									<td>违规人次</td>
								</tr>
							</tbody>
							<tbody id="MonitorNumber" ></tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						监控规则违规情况占比top10
					</div>
					<div class="layui-card-body">
						<div id="line2" style="height: 265px;"></div>
					</div>
				</div>
			</div>
		</div>
	<div class="layui-col-md12 layui-col-xs12"
		style="padding: 6px 0px 5px 0px;">
		<div class="layui-row layui-col-space15">
					<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						统筹区违规病例数排名 <a style="float: right;" lay-urlname="统筹区违规病例数排名"
							href="javascript:;"
							lay-href="<%=request.getContextPath()%>/admin/tcNumber">查看全部<img
							style="width: 14px; margin-top: -2px; padding-right: 2px; margin-left: 6px;"
							src="<%=request.getContextPath()%>/images/auditing/more.png" /></a>
					</div>
					<div class="layui-card-body layui-text">
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody>
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td>排名</td>
									<td>统筹区</td>
									<td>违规人次</td>
								</tr>
							</tbody>
							<tbody id="tcNumber" ></tbody>
						</table>
					</div>

				</div>
			</div>
		<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						统筹区违规病例数top10
						<%-- <span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/viewnumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> --%>
					</div>
					<div class="layui-card-body">
						<div id="line1" style="height: 265px;"></div>
					</div>
				</div>
			</div>

		</div>
	</div>
	</div>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/superviseRule/superviseRuleIndex.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>

	<script type="text/javascript">
		// 路径配置
		require.config({
			paths : {
				echarts : $WEB_ROOT_PATH
						+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
			}
		});
		// 使用
		require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('line1'));

			var option = {
			tooltip: {
	                     show: true
	                 },
				title : {
					text : ''
				},
				xAxis : [{
					type : 'category',
					   axisLabel: {    
	                          rotate:16 ,
	                          fontSize : 8
	                       } ,
					data : []
				}],
				yAxis :[ {
					type : 'value'
				}],
				series : [  
			{
                    "name":"违规人次",
                    "type":"bar",
                    "data":[],
                    barWidth:22,
                    itemStyle:{
                        normal:{
                            color:'#319CFE'
                        }
                    }
                }
				]
			};
			
	        $.ajax({
       	     url: $WEB_ROOT_PATH+"/dhccApi/admin/admin/TcNumber",
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",	
		         success : function(result) {
		        	 var hvdata=result.data;
		        	 if (hvdata != null && hvdata.length > 0) {
		        		 var i;
		        		 if(hvdata.length>10){
		        			 k=10;
		        		 }else{
		        			 k=hvdata.length;
		        		 }
		        		 for(var i=0;i<k;i++){ 
			                   option.series[0].data.push(hvdata[i].pnumber);
			                   option.xAxis[0].data.push(hvdata[i].pname);
			             } 
		        		 myChart.setOption(option); 
		        	 }
		         }
          });
		});
	</script>
	<script type="text/javascript">
		// 路径配置
		require.config({
			paths : {
				echarts : $WEB_ROOT_PATH
						+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
			}
		});
	/* 	// 使用
		require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
		'echarts/chart/line' ], function(ec) { */
			// 基于准备好的dom，初始化echarts图表
		/* 	var myChart = ec.init(document.getElementById('line2')); */
		 var myChart = echarts.init(document.getElementById('line2'));
            var option = {
        		    title : {
        		        x:'center'
        		    },
        		    tooltip : {
        		        trigger: 'item',
        		        formatter: "{a} <br/>{b} : {c} ({d}%)"
        		    },
        		    legend: {
        		        left: 'left',
        		        data: []
        		    },
        		    series : [
        		        {
        		            name: '违规占比',
        		            type: 'pie',
        		            radius : '70%',
        		            center:['50%','60%'],
        		            labelLine: {
                                normal: {
                                    show: true

                                }
                            },
                            label: {
                                normal: {
                                show:true
                                }
                            },
        		            data:[
        		                
        		            ]
        		        }
        		    ]
        		};
            
            // 为echarts对象加载数据 
            var names=[];//定义两个数组
            var nums=[];
            $.ajax({
			         url:$WEB_ROOT_PATH+"/dhccApi/admin/admin/TcNumberDate",
			         type : "post",		
			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         dataType : "json",	
			         success : function(result) {
			        	 for(var i=0;i<result.length;i++){
			        		 var pnumber =parseInt(result[i].pnumber);
                             if( pnumber==''|| pnumber==null ||  pnumber==0){
				            	 continue;
				             }else{
			        		 names.push(result[i].pname);
				        	 var obj = new Object();
				             obj.name = result[i].pname;
				             obj.value = parseInt(pnumber);
				             
				             nums[i]=obj;
				             }
			        	 }
			        	
			        	
			        	 myChart.setOption({ //加载数据图表
			        		 title : {
		            		        x:'center'
		            		    },
		            		    tooltip : {
		            		        trigger: 'item',
		            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		            		    },
			        		  legend: {
			        			  left: 'left',
			                      data:[]
			                      },
			                  series: [{
			                	  name: '违规数量及占比',
	            		          type: 'pie',
	            		          radius : '60%',
	            		          center:['50%','60%'],
			                     data: nums,
			     	            labelLine: {
	                                normal: {
	                                    show:true

	                                }
	                            },
	                            label: {
	                                normal: {
	                                show:true
	                                }
	                            }
			                     }]
			           	  });
			        	
			         }
            });

	/* 	}); */
	</script>

	<script type="text/javascript">
		layui.config({
			base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
		}).extend({
			index : 'lib/index' //主入口模块
		}).use([ 'index', 'table' ], function() {
			var $ = layui.$, form = layui.form, table = layui.table;

		});
	</script>
</body>
</html>
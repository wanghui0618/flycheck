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
<%-- <%
String type=request.getParameter("type");//注意此处不要用getParameterValue方法
String typeNameTz=request.getParameter("typeNameTz");//注意此处不要用getParameterValue方法
System.out.println(type);
System.out.println(typeNameTz);
 %> --%>
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
	border-radius: 3px;
	color:#fff;
	margin: 0px 6px 13px 6px;
}
.daichushen{
	background-image: linear-gradient(-225deg, #00C4FE 0%, #137CFF 100%);
}
.daijihe{
	background-image: linear-gradient(-225deg, #00D988 0%, #00B365 100%);
}
.daigongshi{
	background-image: linear-gradient(-225deg, #FEC500 0%, #F68E00 100%);
}
.daizhongshen{
	background-image: linear-gradient(-225deg, #B998F2 0%, #806BE4 100%);
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
	background-color: #F68F00;
	color:#fff!important;
	border-radius: 2px;
}
.layui-form-select{
width:200px;
}
#sysVerify{
font-size: 14px;
color: #353535;
width: 100%;
line-height:34px;
}
</style>
</head> 
<body>
<!-- 隐藏a标签，用于跳转模拟 -->
<div style="display: none;" id="tz-hide"  href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicaltab-jpp-zs"></div>
<div class="layui-fluid" style="overflow-y:hidden;">
  	<div class="layui-row layui-col-space15">
  	<div class="layui-col-md12">
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">今天</label><label id="dateweek" style="margin-left:10px;">&nbsp;</label>
				</div>
  				<div class="layui-card-body">
				  	<ul class="layui-row layui-col-space10 daiban">
                        <li style="padding-top: 0px;" id="userStatus-li" class="layui-col-xs2 daichushen" href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medical" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daichushen.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">待初审</h3>
                               	<p style="font-size: 20px;"><label id="userStatus">&nbsp;</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" id="gs-li" class="layui-col-xs2 daijihe" href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicalAudit">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daijihe.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">待稽核</h3>
                               	<p style="font-size: 20px;"><label id="jh">&nbsp;</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" id="gs-li" class="layui-col-xs2 daigongshi" href="javascript:;" lay-href="<%=request.getContextPath()%>/resultAppeal/resultAppealH">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">待公示</h3>
                               	<p style="font-size: 20px;"><label id="gs">&nbsp;</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" id="finaStatus-li" class="layui-col-xs2 daizhongshen" href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicaltab-jpp-zs">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daizhongshen.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">待终审</h3>
                               	<p style="font-size: 20px;"><label id="finaStatus">&nbsp;</label></p>
                            </div>
                        </li>
                    </ul>
  				</div>
  			</div>
	</div>
  		<div class="layui-col-md4">
      		<div class="layui-card">
  				<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					违规医院统计TOP10
  					<select id="hospital-top10" style="margin-left:10px">
						<option value="0">机审</option>
						<option value="1">终审</option>
					</select>
  					<span id="hospital-top10-href" style="float: right;" lay-urlname="违规医院统计" href="javascript:;" lay-href="<%=request.getContextPath()%>/hospitalviolation/hospitalViolation?status=0">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  				</div>
  				<div class="layui-card-body">
				   <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					  <ul class="layui-tab-title" style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
					    <li class="layui-this" style="display:none;height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">TOP10</li>
					    <%-- <img style="height: 25px;width: 2px;" src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"  /> --%>
					    <!-- <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">全部</li> -->
					  </ul>
					  <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
					  	<div class="layui-tab-item layui-show" style="height:305px;">
							<div id="mainHos" style="height: 315px;width:520px;margin-left:5px;margin-left:-10px"></div>
						</div>
					  	<div class="layui-tab-item ">
					  		 <div class="layui-card">
							<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding-left: 5px;">
								<div class="layui-form-item">
									<!-- <div class="layui-inline pt">
										<label class="layui-form-label ">城市名称</label>
										
										<div class="layui-input-block " style="width: 120px;">
											<select name="hospitalViolation.cityName" id="city" >
												<option value="" disabled selected style='display:none;'>请选择城市</option>
											</select>
										</div>
									</div> -->
									
									<div class="layui-inline pt" style="margin-right: 4px;">
										<label class="layui-form-label">医院名称</label>
										<div class="layui-input-block">
											<!-- <input type="text" style="width: 120px;"
												name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
												class="layui-input"> -->
												<!-- <select id="zyOrgName" style="width: 150px;" name="hospitalViolation.orgCode" lay-verify="" lay-search=" ">
                                    				<option value="" disabled selected style='display:none;'>请选择医院</option>
                                				</select> -->
                                				 <input id="getOrgName"/>
                                 				 <input type="text" id="orgCode" name="hospitalViolation.orgCode" style="display: none;" />
										</div>
									</div>
									<button style="display: inline-block;position: absolute;margin-top: 8px;padding:0px 7px" class="layui-btn layui-btn-sm layuiadmin-btn-useradmin" lay-submit
										lay-filter="LAY-user-front-search">
										<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
									</button>
									
								</div>
							</div>
								<table id="hospitalViolationTable" class="layui-hide"
									lay-filter="hospitalViolationTable"></table>
						  </div>
					  	</div>
    					
					  </div>
					</div>    
  				</div>
			</div>
    	</div>
  		<div class="layui-col-md4">
      		<div class="layui-card" style="min-height:373px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
  				<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					违规类型统计
  					<!-- <select id="violation-top10" style="margin-left:10px">
						<option value="0">机审</option>
						<option value="1">终审</option>
					</select> -->
  					<span id="violation-top10-href" lay-urlname="违规类型统计" style="float: right;"href="javascript:;" lay-href="<%=request.getContextPath()%>/violationDetail/violationDetail?status=0">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  				</div>
		 		<div id="sysVerifyTableDiv" class="layui-card-body">
		 		    <table id="sysVerify" style="font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0"></table>
			    </div>
    	    </div>
  	    </div>
		<div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />违规数据占比</div>
              <div class="layui-card-body" style="height: 325px;">
            <div id="main1" style="height:290px;"></div>      
              </div>
            </div>
        </div>
	</div>
  
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md6">
	            <div class="layui-card">
	              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />事中审核流程</div>
	              <div class="layui-card-body">
	              	  <img style="width:99%;height: 300px;" src="<%=request.getContextPath() %>/images/auditing/shizhongshenhe.png"  />
	              </div>
	            </div>
            </div>
            <div class="layui-col-md6">
	            <div class="layui-card">
	              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />事后审核流程</div>
	              <div class="layui-card-body">
	              	   <img style="width:99%;height: 300px;" src="<%=request.getContextPath() %>/images/auditing/shihoushenhe.png"  />
	              </div>
	            </div>
            </div> 
           </div>
           <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
            <div class="layui-card" style="min-height:247px">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />关键流程解析</div>
              <div class="layui-card-body">
             （一） 稽核流程：<br>
              病例初审（违规）-》发送医院-》医院填材料（填完）—》点发送-》医保局接受/不接受
(接受)-》病例终审    、不接受-》打回<br>
              	  （二） 终审数据来源：<br>
1、稽核- 》违规     待终审<br>
2、公示反馈-》医院上传材料-》医保局接收材料    待终审<br>
3、公示反馈-》医院接受违规   -》直接提交完成终审 （标记违规）<br>
              </div>
            </div>
            </div> 
  
        
      </div>
      </div>
   
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
             'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
         ],
         function (ec) {
             // 基于准备好的dom，初始化echarts图表
             var myChart = ec.init(document.getElementById('main1')); 
             
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
            		            name: '违规数量',
            		            type: 'pie',
            		            radius : '70%',
            		            center:['50%','60%'],
            		            data:[
            		                
            		            ]
            		        }
            		    ]
            		};
             
             // 为echarts对象加载数据 
             var names=[];//定义两个数组
             var nums=[];
             $.ajax({
			         url:$WEB_ROOT_PATH+"/dhccApi/medical/medical/sysStatus",
			         type : "post",		
			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         dataType : "json",	
			         success : function(result) {
			        	
			        	 for(var i=0;i<result.length;i++){
			        		 names.push(result[i].wgName);
				        	 var obj = new Object();
				             obj.name = result[i].wgName;
				             obj.value = parseInt(result[i].wgNum);
				             nums[i]=obj;
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
			                      data: names
			                      },
			                  series: [{
			                	  name: '违规数量',
	            		          type: 'pie',
	            		          radius : '60%',
	            		          center:['50%','60%'],
			                     data: nums
			                     }]
			           	  });
			        	
			         }
             });
         }
     );
	 //医院违规柱状图
     require(
             [
                 'echarts',
                 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
             ],
             function (ec) {
                 // 基于准备好的dom，初始化echarts图表
                 var myChart = ec.init(document.getElementById('mainHos')); 
                 
                 var option = {
                     tooltip: {
                         show: true
                     },
                     padding: [0, 0, 10, 10],  // 位置
                     legend: {
                         padding: 10,    // [5, 10, 15, 20]
                         itemGap: 20,
                         left:'right',
                         data:['医院违规记录数']
                     },
                     xAxis : [
                         {
                             type : 'category',
                             data : [],
                             axisLabel : {//坐标轴刻度标签的相关设置。
                                 interval:0,
                                 rotate:"20"
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
                             "name":"违规数量",
                             "type":"bar",
                             "data":[],
                             barWidth:16,
                             itemStyle:{
                                 normal:{
                                     color:'#419bf9'
                                 }
                             }
                         }
                     ]
                 };
         		
                 // 为echarts对象加载数据 
                   $.ajax({
    			         url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/listVo",
    			         type : "post",		
    			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    			         dataType : "json",	
    			         success : function(result) {
    			        	 var hvdata=result.data;
    			        	 console.log(hvdata);
    			        	 if (hvdata != null && hvdata.length > 0) {
    			        		 var k;
    			        		 if(hvdata.length>10){
    			        			 k=10;
    			        		 }else{
    			        			 k=hvdata.length;
    			        		 }
    			        		 for(var i=0;i<k;i++){ 
    				                   var dir={"orgcode":"23456","value":11};
    				                   dir.value=hvdata[i].vioCount;
    				                   dir.orgcode=hvdata[i].orgCode;
    				                   option.series[0].data.push(dir);
    				                   option.xAxis[0].data.push(hvdata[i].cityName+"-"+hvdata[i].orgName);
    				             } 
    			        		 myChart.setOption(option); 
    			        	 }
    			         }
                   });
                   var ecConfig = require('echarts/config');  
	                 
                   myChart.on(ecConfig.EVENT.CLICK, eConsole); 
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/hospitalViolation/hospitalViolation.js"></script>
</body>
</html>
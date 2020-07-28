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
</style>
</head>
<body>

<div class="layui-fluid" style="overflow-y:hidden;">

<div class="layui-row layui-col-space15" >
    	<div class="layui-col-md3">
      		<lable id="userStatus">待初审数据：0 条</lable>
    	</div>
    	<div class="layui-col-md3">
      		<lable id="jh">待稽核数据：0 条</lable>
    	</div>
    	<div class="layui-col-md3">
      		<lable id="gs">待公示数据：0 条</lable>
    	</div>
    	<div class="layui-col-md3">
      		<lable id="finaStatus">待终审数据：0 条</lable>
    	</div>
  	</div>
  	<div class="layui-row layui-col-space15">
  		<div class="layui-col-md6">
      		<div class="layui-card">
  				<div class="layui-card-header" >医院违规统计</div>
  				<div class="layui-card-body">
				   <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					  <ul class="layui-tab-title" style="height:20px;line-height:15px">
					    <li style="height:15px;line-height:15px">列表</li>
					    <li class="layui-this"style="height:15px;line-height:15px">柱状图</li>
					  </ul>
					  <div class="layui-tab-content" style="margin-top:-15px;padding-left: 0px;padding-right: 0px;">
					  	<div class="layui-tab-item ">
					  		 <div class="layui-card">
							<div class="layui-form layui-card-header layuiadmin-card-header-auto">
								<div class="layui-form-item">
									<div class="layui-inline pt">
										<label class="layui-form-label ">城市名称</label>
										
										<div class="layui-input-block " style="width: 120px;">
											<select name="hospitalViolation.cityName" id="city" >
												<option value="" disabled selected style='display:none;'>请选择城市</option>
											</select>
										</div>
									</div>
									
									<div class="layui-inline pt">
										<label class="layui-form-label">医院名称</label>
										<div class="layui-input-block">
											<input type="text" style="width: 120px;"
												name="hospitalViolation.orgName" placeholder="请输入医院名称" autocomplete="off"
												class="layui-input">
										</div>
									</div>
									
									<div class="layui-inline " style="margin-left:20px" >
										<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
											lay-filter="LAY-user-front-search">
											<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
										</button>
									</div>
									
								</div>
							</div>
				
							<div class="layui-card-body">
				
								<table id="hospitalViolationTable" class="layui-hide"
									lay-filter="hospitalViolationTable"></table>
							</div>
						  </div>
					  	</div>
    					<div class="layui-tab-item layui-show">
							<div class="layui-card" >
								<div class="layui-card-header" >医院违规TOP10</div>
								<div class="layui-card-body">
									<div id="main" style="height: 252px"></div>
								</div>
							</div>
						</div>
					  </div>
					</div>    
  				</div>
			</div>
    	</div>
  		<div class="layui-col-md6">
      		<div class="layui-card" style="min-height:382px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
  				<div class="layui-form layui-card-header " style="height:27px;font-size:13px;line-height:25px;">违规条目统计</div>
		 		<div class="layui-card-body">
		 		    <table id="sysVerify" border="0" cellpadding="0" cellspacing="0"></table>
			    </div>
    	    </div>
  	    </div>

	</div>








  
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md6">
	            <div class="layui-card">
	              <div class="layui-card-header">事中审核流程</div>
	              <div class="layui-card-body">
	              	  <img style="width:670px;height: 380px;" src="<%=request.getContextPath() %>/images/main/zhaofu1.png"  />
	              </div>
	            </div>
            </div>
            <div class="layui-col-md6">
	            <div class="layui-card">
	              <div class="layui-card-header">事后审核流程</div>
	              <div class="layui-card-body">
	              	   <img style="width:670px;height: 380px;" src="<%=request.getContextPath() %>/images/main/zhaofu.png"  />
	              </div>
	            </div>
            </div> 
           </div>
           <div class="layui-row layui-col-space15">
          <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header">违规类型比例</div>
              <div class="layui-card-body">
            <div id="main1" style="height:200px;"></div>      
              </div>
            </div>
            </div>
            <div class="layui-col-md8">
            <div class="layui-card" style="min-height:247px">
              <div class="layui-card-header">关键流程解析</div>
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
            		        data: ['违规','未违规','疑似违规']
            		    },
            		    series : [
            		        {
            		            name: '违规数量',
            		            type: 'pie',
            		            radius : '50%',
            		            data:[
            		                {value:22, name:'违规'},
            		                {value:66, name:'未违规'},
            		                {value:33, name:' 疑似违规'}
            		            ]
            		        }
            		    ]
            		};
             myChart.setOption(option);
/*              // 为echarts对象加载数据 
               $.ajax({
            	     url: $WEB_ROOT_PATH+"/dhccApi/medical/medical/listNumber",
			         type : "post",		
			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         dataType : "json",	
			         success : function(data) {
			                for(var i=0;i<data.length;i++){
			                    var obj=new Object();
			                    obj.name=data[i].medicalName; 
			                    obj.value=data[i].medicalNumber;
			                    servicedata[i]=obj;
			                }
			        		 myChart.setOption({
			            		    title : {
			            		        text: '违规操作',
			            		        x:'center'
			            		    },
			            		    series : [{
			            		            name: '违规数据',
			            		            type: 'pie',
			            		            radius : '20%',
			            		            data:servicedata
			            		        }]
			        		 }); 
			         }
               }); */
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
                 var myChart = ec.init(document.getElementById('main')); 
                 
                 var option = {
                     tooltip: {
                         show: true
                     },
                     legend: {
                         data:['医院违规记录数']
                     },
                     xAxis : [
                         {
                             type : 'category',
                             data : []
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
                             itemStyle:{
                                 normal:{
                                     color:'#EE7621'
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
    			        		 myChart.setOption(option); 
    			        	 }
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/hospitalViolation/hospitalViolation.js"></script>
</body>
</html>
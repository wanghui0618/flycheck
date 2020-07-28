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
.rd_table{
	width:100%;
	margin:0 auto;
}
.rd_table td{
	border:1px solid #eee;
}
.select_search{
	width: 120px;
	display: block;
	float: left;
	margin-left:0px;
}
.select_search input{
	border:1px solid #eee;
	height: 45px;
}
.layui-tab-title{
	background: #E5F2F8;
}
.layui-tab-title .layui-this{
	background: #fff;
}
.total_span{
	float: right;
	color:#6C6C6C;
	font-size: 14px;
}
.layui-progress-big, .layui-progress-big .layui-progress-bar {
    height: 10px;
    line-height: 10px;
}
.lastOneDayBar{
	background: -moz-linear-gradient(top, #FF9C22 0%, #FF630F 100%) ;
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FF9C22), color-stop(100%,#FF630F))  ;
    background: -webkit-linear-gradient(top, #FF9C22 0%,#FF630F 100%) ;
    background: -o-linear-gradient(top, #FF9C22 0%,#FF630F 100%) ;
    background: -ms-linear-gradient(top, #FF9C22 0%,#FF630F 100%) ;
}
.lastSevenDayBar{
	background: -moz-linear-gradient(top, #37B0FF 0%, #2284FF 100%) ;
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#37B0FF), color-stop(100%,#2284FF))  ;
    background: -webkit-linear-gradient(top, #37B0FF 0%,#2284FF 100%) ;
    background: -o-linear-gradient(top, #37B0FF 0%,#2284FF 100%) ;
    background: -ms-linear-gradient(top, #37B0FF 0%,#2284FF 100%) ;
}
.last30DayBar{
	background: -moz-linear-gradient(top, #76DB26 0%, #25B209 100%) ;
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#76DB26), color-stop(100%,#25B209))  ;
    background: -webkit-linear-gradient(top, #76DB26 0%,#25B209 100%) ;
    background: -o-linear-gradient(top, #76DB26 0%,#25B209 100%) ;
    background: -ms-linear-gradient(top, #76DB26 0%,#25B209 100%) ;
}
.layui-table-box {
	width:100%;
}
</style>
</head>
<body>
<div class="layui-fluid" style="overflow: hidden;">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
          <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					当前在线
  				    <span style="float: right;"><img style="width:14px;margin-top:-4px;padding-right: 8px;"/><label  href="javascript:;" lay-href="<%=request.getContextPath()%>/violationdetail/violationDetail1">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></label></span>
  			  </div>
              <div class="layui-card-body" style="height: 185px;">
                	<table style="border: none;border:0px;width: 90%;line-height:34px;margin:0 auto">
		               <colgroup>
		                <col width="20%">
		                <col width="21%">
		                <col width="14%">
		                <col width="25%">
		              </colgroup>
		              <tbody id="tableOnline" >
		              </tbody>
		            </table>
              </div>
            </div>
          </div>
          
		<div class="layui-col-md4" style="padding-left: 5px;padding-right: 5px;">
			<div class="layui-card">
          	<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					活跃用户
  				    <%-- <span style="float: right;"><img style="width:14px;margin-top:-4px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/oauth/reflush_blue.png"  /><label  href="javascript:;" lay-href="<%=request.getContextPath()%>/violationdetail/violationDetail2">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></label></span> --%>
  			 </div>
             <div class="layui-card-body" style="height: 155px;padding: 18px;">
             	<div style="margin-bottom: 3px;">最近一天<span class="total_span" id="lastOneDaySpan">/</span></div>
				<div class="layui-progress layui-progress-big" lay-filter="lastOneDay" lay-showPercent="true">
  					<div class="layui-progress-bar lastOneDayBar" id="lastOneDayBar" lay-percent=""></div>
				</div>
 				<div style="margin-top:15px;margin-bottom: 3px;">最近一周<span class="total_span" id="lastSevenDaySpan">/</span></div>
				<div class="layui-progress layui-progress-big" lay-filter="lastSevenDay" lay-showPercent="true">
  					<div class="layui-progress-bar lastSevenDayBar" id="lastSevenDayBar" lay-percent=""></div>
				</div>
 				<div style="margin-top:15px;margin-bottom: 3px;">最近一月<span class="total_span" id="last30DaySpan">/</span></div>
				<div class="layui-progress layui-progress-big" lay-filter="last30Day" lay-showPercent="true">
  					<div class="layui-progress-bar last30DayBar" id="last30DayBar"  lay-percent=""></div>
				</div>
      		 </div>
            </div>
          </div>
          
          <div class="layui-col-md4" style="padding-left: 5px;padding-right: 2px;">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					最近登录
  				    <span style="float: right;"><img lay-urlname="最近登录全部信息" style="width:14px;margin-top:-4px;padding-right: 8px;"/><a  href="javascript:;" lay-href="<%=request.getContextPath()%>/user/newLogin?num=1">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></a></span>
  			  </div>
              <div class="layui-card-body" style="height: 185px;">
                	<!-- <table id="userTable2" class="layui-hide" lay-filter="userTable2"></table> -->
                	<div id="tableTwo" style="height:210px;margin: 0 auto;width:90%;" >
		              <table  style="border: none;border:0px;width: 100%;line-height:34px;">
		               <colgroup>
		                <col>
		                <col width="200">
		              </colgroup>
		              <tbody id="qualityTable" >
		              </tbody>
		              </table>
		              </div>
              </div>
            </div>
          </div>
          
    </div>
    
        <div class="layui-row layui-col-space15">
		        <div class="layui-col-md8" style="padding-left: 2px;padding-right: 5px;">
			        <div class="layui-card">
				        <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
			  				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
			  					通讯录
			  				<div style="float: right;margin-top: 6px;">
					            <input style="background-image: url(<%=request.getContextPath() %>/images/oauth/search.png);background-repeat: no-repeat;border: solid 1px #e6e6e6;background-position: right;width:180px;height:32px;padding-right: 20px;" type="text" name="userVo.keyDom" id="keyDom"  class="layui-input" >
			  			    </div>
			  			</div>
			         <div class="layui-card-body">
          				<table style="width:100%" id="userTable1" class="layui-hide" lay-filter="userTable1" ></table>
        			</div>
		        </div>
		        
		        </div>
		        
		        <div class="layui-col-md4" style="padding-left: 5px;padding-right: 2px;">
			        <div class="layui-card">
		              	<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
	  						<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	  						用户登录情况统计
	  						<span style="float: right;" href="javascript:;" lay-href="<%=request.getContextPath()%>/log/log">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
	  				  	</div>
		              	<div class="layui-card-body" style="color:#3D3D3D">
		       				<div id="mainTwo"></div>
		      		  	</div>
		        	</div>
		        </div>
  		</div>
  		
  </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/home/homeOauth.js"></script>
  <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
  
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
         var myChart = ec.init(document.getElementById('mainTwo')); 
         var option = {
     		    tooltip : {
     		    	show: true
     		    },
     		    legend: {
                     data:['登录总人数']
                 },
                 /* toolbox: {
                     show : true,
                     feature : {
                         restore : {show: true},
                     }
                 },
                 calculable : true, */
     		    xAxis : [
     		        {
     		        	axisLabel: {    
                            rotate:45 ,
                            fontSize : 2
                         } ,
     		        	type : 'category',
     		             data : []
     		        }
     		    ],
     		    yAxis : [
     		        {
     		        	type : 'value',
     		        }
     		    ],
     		    series : [
     		        {
     		            name:'登录总人数',
     		            type:'bar',
     		            data:[],
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
      	     url: $WEB_ROOT_PATH+"/dhccApi/user/user/userListNumber",
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",	
		         success : function(result) {
		        	/*  var yk=result.data[0];//游客
		        	 var hospital=result.data[1];//医院
		        	 var admin=result.data[2];//管理员
		        	 var yibaoju=result.data[3];//医保局
		        	 var user=result.data[4];//普通用户 */
		        	 var oneDay = result.data[0].oneDay;
		        	 var twoDay = result.data[0].twoDay;
		        	 var threeDay = result.data[0].threeDay;
		        	 var fourDay = result.data[0].fourDay;
		        	 var fiveDay = result.data[0].fiveDay;
		        	 var sixDay = result.data[0].sixDay;
		        	 var sevenDay = result.data[0].sevenDay;
		        	 
		        	 /* console.log(result);  */
		        	 option.series[0].data.push(oneDay,twoDay,threeDay,fourDay,fiveDay,sixDay,sevenDay);
		        		 
			            /*  option.series[0].data.push(yk.oneDay);
			             option.series[1].data.push(hospital.oneDay);
			             option.series[2].data.push(admin.oneDay);
			             option.series[3].data.push(yibaoju.oneDay);
			             option.series[4].data.push(user.oneDay);
			             
			             option.series[0].data.push(yk.twoDay);
			             option.series[1].data.push(hospital.twoDay);
			             option.series[2].data.push(admin.twoDay);
			             option.series[3].data.push(yibaoju.twoDay);
			             option.series[4].data.push(user.twoDay);
			             
			             option.series[0].data.push(yk.threeDay);
			             option.series[1].data.push(hospital.threeDay);
			             option.series[2].data.push(admin.threeDay);
			             option.series[3].data.push(yibaoju.threeDay);
			             option.series[4].data.push(user.threeDay);
			             
			             option.series[0].data.push(yk.fourDay);
			             option.series[1].data.push(hospital.fourDay);
			             option.series[2].data.push(admin.fourDay);
			             option.series[3].data.push(yibaoju.fourDay);
			             option.series[4].data.push(user.fourDay);
			             
			             option.series[0].data.push(yk.fiveDay);
			             option.series[1].data.push(hospital.fiveDay);
			             option.series[2].data.push(admin.fiveDay);
			             option.series[3].data.push(yibaoju.fiveDay);
			             option.series[4].data.push(user.fiveDay);
			             
			             option.series[0].data.push(yk.sixDay);
			             option.series[1].data.push(hospital.sixDay);
			             option.series[2].data.push(admin.sixDay);
			             option.series[3].data.push(yibaoju.sixDay);
			             option.series[4].data.push(user.sixDay);
			             
			             option.series[0].data.push(yk.sevenDay);
			             option.series[1].data.push(hospital.sevenDay);
			             option.series[2].data.push(admin.sevenDay);
			             option.series[3].data.push(yibaoju.sevenDay);
			             option.series[4].data.push(user.sevenDay); */
			             
		        		 var date = new Date();
		        		 var year= date.getFullYear();
		        		 var month= date.getMonth() + 1;
		        		 var day= date.getDate();
		        		 
		        		 var date1 = new Date(date-24*3600*1000);
		        		 var year1= date1.getFullYear();
		        		 var month1= date1.getMonth() + 1;
		        		 var day1= date1.getDate();
		        		 
		        		 var date2 = new Date(date-2*24*3600*1000);
		        		 var year2= date2.getFullYear();
		        		 var month2= date2.getMonth() + 1;
		        		 var day2= date2.getDate();
		        		 
		        		 var date3 = new Date(date-3*24*3600*1000);
		        		 var year3= date3.getFullYear();
		        		 var month3= date3.getMonth() + 1;
		        		 var day3= date3.getDate();
		        		 
		        		 var date4 = new Date(date-4*24*3600*1000);
		        		 var year4= date4.getFullYear();
		        		 var month4= date4.getMonth() + 1;
		        		 var day4= date4.getDate();
		        		 
		        		 var date5 = new Date(date-5*24*3600*1000);
		        		 var year5= date5.getFullYear();
		        		 var month5= date5.getMonth() + 1;
		        		 var day5= date5.getDate();
		        		 
		        		 var date6 = new Date(date-6*24*3600*1000);
		        		 var year6= date6.getFullYear();
		        		 var month6= date6.getMonth() + 1;
		        		 var day6= date6.getDate();
		        		 
		        		/*  var date7 = new Date(date-7*24*3600*1000);
		        		 var year7= date7.getFullYear();
		        		 var month7= date7.getMonth() + 1;
		        		 var day7= date7.getDate(); */
		        		 
		        		/*  ,year2+"."+month2+"."+day2,year3+"."+month3+"."+day3,year4+"."+month4+"."+day4,year5+"."+month5+"."+day5,year6+"."+month6+"."+day6,year7+"."+month7+"."+day7 */
		        		 option.xAxis[0].data.push(year+"."+month+"."+day,year1+"."+month1+"."+day1,year2+"."+month2+"."+day2,year3+"."+month3+"."+day3,year4+"."+month4+"."+day4,year5+"."+month5+"."+day5,year6+"."+month6+"."+day6);
		        		 myChart.setOption(option); 
		         }
         }); 
  }
);
 </script>
  
  <!-- <script type="text/javascript">
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
         var myChart = ec.init(document.getElementById('mainOne')); 
         
         var option = {
        		    tooltip : {
        		    	show: true
        		    },
        		    legend: {
                        data:['总数'],
                        left:'left',
                        textStyle:{//图例文字的样式
                            color:'#2C89FE',
                            fontSize:12
                        },
                        itemStyle:{
                            normal:{
                                color:'#2C89FE'
                            }
    		            }
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
        		            name:'总数',
        		            type:'bar',
        		            data:[],
        		            barWidth:16,
        		            itemStyle:{
                                normal:{
                                    color:'#2C89FE'
                                }
        		            }
        		        }
        		    ]
        		};
        
      		// 为echarts对象加载数据
           $.ajax({
        	     url: $WEB_ROOT_PATH+"/dhccApi/user/user/listNumber",
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",	
		         success : function(result) {
		        	 var hvdata=result.data;
		        	 /* console.log(hvdata);
		        	 console.log(hvdata[0].medicalNumber); 
		        	 console.log(hvdata[0].medicalName);  */
		        	 if (hvdata != null && hvdata.length > 0) {
		        		 var k;
		        		 if(hvdata.length>10){
		        			 k=10;
		        		 }else{
		        			 k=hvdata.length-1;
		        		 }
		        		 for(var i=0;i<k;i++){ 
			               option.series[0].data.push(hvdata[i].medicalNumber);
			               option.xAxis[0].data.push(hvdata[i].medicalName);
			                 
			                /* option.series[0].data.push(20);
			                 option.xAxis[0].data.push('jj');  */
		                }
		        		myChart.setOption(option); 
		         }
		       }
           }); 
     }
 );
 </script> -->
</body>
</html>
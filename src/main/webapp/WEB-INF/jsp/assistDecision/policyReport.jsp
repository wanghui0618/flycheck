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
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>模拟政策报告</title>
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
	width: 190px;
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
.layui-form-label {
	width: 115px;
}
.layui-col-space10 > * {
    padding: 10px;
}
</style>
</head>
<body>
<div class="layui-fluid" style="overflow-y:hidden;">
 <div class="layui-row layui-col-space15">
  	<div class="layui-col-md12">
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">费用概览</label>
				</div>
  				<div class="layui-card-body">
				  	<%-- <ul class="layui-row layui-col-space10 daiban">
                        <li class="layui-col-xs2 daichushen" href="javascript:;"  >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height:25px;">
                               	<p style="font-size: 16px;"><label id="all">90401.47（实际值）</label></p>
                               	<p style="font-size: 16px;"><label id="all">90232.11（模拟值）</label></p>
                             	<h3 style="font-size: 14px;">统筹支付总费用（万元）</h3>
                            </div>
                        </li>
                        <li class="layui-col-xs2 daijihe" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height:25px;">
                               	<p style="font-size: 16px;"><label id="all">34668.86（实际值）</label></p>
                               	<p style="font-size: 16px;"><label id="all">34618.82（模拟值）</label></p>
                               	<h3 style="font-size: 14px;">职工支付总费用（万元）</h3>
                            </div>
                        </li>
                        <li class="layui-col-xs2 daigongshi" href="javascript:;">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:32px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height:25px;">
                               	<p style="font-size: 16px;"><label id="all">55732.61（实际值）</label></p>
                               	<p style="font-size: 16px;"><label id="all">55613.29（模拟值）</label></p>
                               	<h3 style="font-size: 14px;">居民支付总费用（万元）</h3>
                            </div>
                        </li>
                    </ul> --%>
                    <ul class="layui-row layui-col-space10 daiban">
                        <li style="padding-top: 0px;" id="userStatus-li" class="layui-col-xs2 daichushen" href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medical" >
                        	<div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 13px;">90401.47（实际值）</h3>
                               	<p style="font-size: 13px;">90232.11（模拟值）</p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" id="gs-li" class="layui-col-xs2 daijihe" href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicalAudit">
                        	<div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 13px;">34668.86（实际值）</h3>
                               	<p style="font-size: 13px;">34618.82（模拟值）</p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" id="gs-li" class="layui-col-xs2 daigongshi" href="javascript:;" lay-href="<%=request.getContextPath()%>/resultAppeal/resultAppeal">
                        	<div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daigongshi.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 13px;">55732.61（实际值）</h3>
                               	<p style="font-size: 13px;">55613.29（模拟值）</p>
                            </div>
                        </li>
                        <%-- <li style="padding-top: 0px;" id="finaStatus-li" class="layui-col-xs2 daizhongshen" href="javascript:;" lay-href="<%=request.getContextPath()%>/medical/medicaltab-jpp-zs">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/auditing/daizhongshen.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 14px;">待终审</h3>
                               	<p style="font-size: 14px;"><label id="finaStatus">&nbsp;</label></p>
                            </div>
                        </li> --%>
                    </ul>
  				</div>
  			</div>
      		<!-- <div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">就诊类型</label>
				</div>
  				<div class="layui-card-body">
					<table class="layui-table" lay-skin="line">
					  <thead>
					    <tr><th colspan="4" style="text-align: center;">住院</th>
					    <th colspan="3" style="text-align: center;">门特</th></tr> 
					    <tr><th></th><th>实际值（万元）</th><th>模拟值（万元）</th><th>增长率</th>
					    <th>实际值（万元）</th><th>模拟值（万元）</th><th>增长率</th></tr> 
					  </thead>
					  <tbody>
					    <tr>
					      <td>职工</td>
					      <td>100</td>
					      <td>120</td>
					      <td>20%</td>
					      <td>80</td>
					      <td>90</td>
					      <td>10%</td>
					    </tr>
					    <tr>
					      <td>居民</td>
					      <td>50</td>
					      <td>70</td>
					      <td>40%</td>
					      <td>20</td>
					      <td>25</td>
					      <td>25%</td>
					    </tr>
					  </tbody>
					</table>
  				</div>
  			</div> -->
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">医疗机构</label>
				</div>
  				<div class="layui-card-body">
	  				 <div class="layui-row layui-col-space15">
	  					<div class="layui-col-md6">
			        		 <div id="main3" style="height:290px;"></div>      
	  					</div>
	  					<div class="layui-col-md6">
			        		 <div id="main4" style="height:290px;"></div>      
	  					</div>
					</div>
  				</div>
  			</div>
      		<!-- <div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">行政区域</label>
				</div>
  				<div class="layui-card-body">
        			 <div id="main5" style="height:320px;"></div>   
  				</div>
  			</div> -->
	</div>
	
</div>
</div>
<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
<script>
	layui.config({
	  base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate'], function(){
		  var form=layui.form;
		
	  });
	 function child(obj){
	   var cityOrg = JSON.parse(obj);
		$("#id").val(cityOrg["id"]);
		for (var index in cityOrg){
		 $("#"+index).val(cityOrg[index]);
		}
	    var initStartFlag=cityOrg.initStartFlag;
	    $("#initStartFlag").find("option[value ='"+initStartFlag+"']").attr("selected","selected");
	    form.render('select');
	}
	 
	 var main3Chart = echarts.init(document.getElementById('main3'));
		 main3option = {
			    title : {
			        subtext: '现行政策实际值',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['三级 15,997.07 现在值','二级3,677.72 现在值','一级17,518.48 现在值']
			    },
			    series : [
			        {
			            name: '费用（万元）',
			            type: 'pie',
			            radius : '70%',
			            center: ['50%', '60%'],
			            data:[
			            	{value:15997.07, name:'三级 15,997.07 现在值'},
			                {value:3677.72, name:'二级3,677.72 现在值'},
			                {value:17518.48, name:'一级17,518.48 现在值'}
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
     main3Chart.setOption(main3option);
     
	 var main4Chart = echarts.init(document.getElementById('main4'));
		 main4option = {
			    title : {
			        subtext: '模拟政策预计值',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['三级 15,944.52 模拟值','二级 3,673.32 模拟值','一级 17,406.07 模拟值']
			    },
			    series : [
			        {
			            name: '费用（万元）',
			            type: 'pie',
			            radius : '70%',
			            center: ['50%', '60%'],
			            data:[
			                {value:15944.52, name:'三级 15,944.52 模拟值'},
			                {value:3673.32, name:'二级 3,673.32 模拟值'},
			                {value:17406.07, name:'一级 17,406.07 模拟值'}
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
     main4Chart.setOption(main4option);
     
     var main5Chart = echarts.init(document.getElementById('main5'));
     main5option = {
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
		        data:['实际值','模拟值']
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: ['河北省','北京市','湖南省','陕西省'],
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
		        }
		    ],
		    series: [
		        {
		            name:'实际值',
		            type:'bar',
		            data:[245,131,214,509]
		        },
		        {
		            name:'模拟值',
		            type:'bar',
		            data:[211,34,566,900]
		        }
		    ]
		};
     main5Chart.setOption(main5option);
</script>
</body>
</html>
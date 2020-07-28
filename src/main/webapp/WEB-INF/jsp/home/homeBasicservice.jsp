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
.layui-fluid {
    padding: 10px 6px 0px 6px;
    overflow-x: hidden;
}
.daiban li{
	height: 65px;
	width: 180px;
	border-radius: 3px;
	color:#fff;
	margin: 0px 6px 13px 6px;
}
.dangqianguize{
	background-image: linear-gradient(-225deg, #F48A56 0%, #EA5C44 100%);
}
.menzhenguize{
	background-image: linear-gradient(-225deg, #F5B00C 0%, #EF8D07 100%);
}
.zhuyuanguize{
	background-image: linear-gradient(-225deg, #9396F8 0%, #6469F7 100%);
}
.youxiaoguize{
	background-image: linear-gradient(-225deg, #34F2BB 0%, #17C3BC 100%);
}
.wuxiaoguize{
	background-image: linear-gradient(-225deg, #F88EB8 0%, #FF5E9D 100%);
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
table th td{
	border:none;
}
</style>
</head>
<body>
<div class="layui-fluid">

        <%-- <div class="layui-col-md12">
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">今天</label><label id="dateweek" style="margin-left:10px;">&nbsp;</label>
				</div>
  				<div class="layui-card-body">
				  	<ul class="layui-row layui-col-space10 layui-this daiban">
                        <li class="layui-col-xs2 dangqianguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:52px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/guizezongshu.png"  />
                        	</div>
                            <div style="line-height:23px;margin-top: 3px;">
                             	<h3 style="font-size: 12px;">当前规则总数</h3>
                               	<p style="font-size: 20px;"><label id="total">32</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 menzhenguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:52px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/menzhen.png"  />
                        	</div>
                            <div style="line-height:23px;margin-top: 3px;">
                             	<h3 style="font-size: 12px;">门诊规则</h3>
                               	<p style="font-size: 20px;"><label id="outrule">20</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 zhuyuanguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:52px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/zhuyuan.png"  />
                        	</div>
                            <div style="line-height:23px;margin-top: 3px;">
                             	<h3 style="font-size: 12px;">住院规则</h3>
                               	<p style="font-size: 20px;"><label id="inrule">10</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 youxiaoguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:52px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/youxiao.png"  />
                        	</div>
                            <div style="line-height:23px;margin-top: 3px;">
                             	<h3 style="font-size: 12px;">有效规则</h3>
                               	<p style="font-size: 20px;"><label id="activerule">30</label></p>
                            </div>
                        </li>
                        <li class="layui-col-xs2 wuxiaoguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:52px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/wuxiao.png"  />
                        	</div>
                            <div style="line-height:23px;margin-top: 3px;">
                             	<h3 style="font-size: 12px;">无效规则</h3>
                               	<p style="font-size: 20px;"><label id="nullrule">2</label></p>
                            </div>
                        </li>
                    </ul>
  				</div>
  			</div>
	</div> --%>

    <div class="layui-row layui-col-space30">
       <div class="layui-col-md7" style="padding: 6px 5px 5px 15px;">
            <div class="layui-card">
          		<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					接入城市基本情况
 					<a style="float: right;" lay-urlname="接入城市全部信息" href="javascript:;" lay-href="<%=request.getContextPath()%>/cityAreaRate/cityAreaRate">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></a>
  				</div>
          
          		<div class="layui-card-body layui-text" style="height: 310px;">
            	<table class="layui-table" style="border:1px solid;">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody id="table111">
              </tbody>
            </table>
            	</div>
 			</div>
 		</div>
   		<div class="layui-col-md5" style="padding: 6px 15px 5px 5px;">
   			<div class="layui-card">
   				<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					行政区域接入城市统计
  					<a style="float: right;" href="javascript:;" lay-urlname="行政区域全部信息" lay-href="<%=request.getContextPath()%>/cityAreaNumber/cityAreaNumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></a>
  				</div>
          		<div class="layui-card-body layui-text">
				  <div id="main1" style="width: 600px;height:310px;"></div>
				</div>
 			</div>
        </div>
        
      </div>
       <div class="layui-row layui-col-space50">
      <div class="layui-col-md12 layui-col-xs12" style="padding: 6px 0px 5px 0px;">
   		<div class="layui-card" >
             <div class="layui-card">
          		<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					接入地区规则数量
  					<a style="float: right;" lay-urlname="数据对照全部信息" href="javascript:;" lay-href="<%=request.getContextPath()%>/ruletype/ruleType">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></a>
  				</div>
          		<div class="layui-card-body layui-text">
            	<table class="layui-table" style="border:1px solid;">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody id="ruleAreaInfo">
              <!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
                <tr style="background:#E5F7EF">
                  <td>行政区域</td>
                  <td>接入城市</td>
                  <td>接入时间</td>
                  <td>icd对照数</td>
                  <td>未对照</td>
                  <td>药品对照数</td>
                  <td>未对照</td>
                  <td>诊疗对照数</td>
                  <td>未对照</td>
                  <td>耗材对照数</td>
                  <td>未对照</td>       
                </tr>
                 <tr>
                  <td>北京市</td>
                  <td>北京市</td>
                  <td>2018-1-20</td>
                  <td>550</td>
                  <td>200</td>
                  <td>370</td>
                  <td>122</td>
              	  <td>121</td>
                  <td>66</td>
                  <td>133</td>
                  <td>50</td>
                </tr>
                 <tr style='background:#F5FCF9'>
                  <td>陕西省</td>
                  <td>西安市</td>
                  <td>2018-2-10</td>
                  <td>300</td>
                  <td>145</td>
                  <td>378</td>
                  <td>145</td>
              	  <td>177</td>
                  <td>88</td>
                  <td>199</td>
                  <td>90</td>
                </tr>
                <tr>
                  <td>陕西省</td>
                  <td>咸阳市</td>
                   <td>2018-5-11</td>
                  <td>235</td>
                  <td>125</td>
                  <td>358</td>
                  <td>132</td>
              	  <td>451</td>
                  <td>155</td>
                  <td>223</td>
                  <td>109</td>
                </tr>
                 <tr style='background:#F5FCF9'>
                  <td>四川省</td>
                  <td>成都市</td>
                  <td>2019-1-21</td>
                  <td>366</td>
                  <td>184</td>
                  <td>295</td>
                  <td>122</td>
                  <td>187</td>
                  <td>83</td>
                  <td>156</td>
                  <td>86</td> 
                </tr>
                <tr style='background:#F5FCF9'>
                  <td>四川省</td>
                  <td>广元市</td>
                  <td>2019-3-18</td>
                  <td>205</td>
                  <td>145</td>
                  <td>254</td>
                  <td>122</td>
                  <td>327</td>
                  <td>133</td>
                  <td>266</td>
                  <td>96</td> 
                </tr>
              </tbody>
            </table>
            	</div>
          	</div>
 			</div>
        </div>
      </div>
      </div>
    <script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
    
  <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console'], function(){
	    var a = new Array("日", "一", "二", "三", "四", "五", "六");
		var date = new Date();
		var today_nian = date.getFullYear();
		var today_yue = date.getMonth()+1;
		var today_day = date.getDate();
		var week = date.getDay();
		var str = today_nian+"年"+today_yue+"月"+today_day+"日&nbsp;星期"+ a[week];
		$("#dateweek").html(str);
  });
  </script>
  <script type="text/javascript">
  $.ajax({
  	url : $WEB_ROOT_PATH+"/dhccApi/homerest/homerest/findRate",
		type : "get",
		success : function(data1) {	
			
			
		},error : function(data) {
			alert("服务器错误");
		}
    });
  
  
  
  
  </script>
  <!--  -->
      
    </script>
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var dataAxis=new Array();
	var data=new Array();
     $.ajax({
	url : $WEB_ROOT_PATH+"/dhccApi/homerest/homerest/findCityNumber",
	type : "get",
	success : function(data1) {	
		for(var index in data1){
			if(index >= 10 || index == 10){
				break;
			}
			dataAxis[index] = data1[index].name;
			data[index]=parseInt(data1[index].digit);
		}
		  var myChart = echarts.init(document.getElementById('main1'));
	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: ''
	            },
	            tooltip: {},
	            legend: {
	            	x: '30px',
	                y: '5px',
	                data:['数量']
	            },
	            xAxis: {
	                data: dataAxis
	            },
	            yAxis: {},
	            series: [{
	                name: '数量',
	                type: 'bar',
	                barWidth:16,
	                data: data,
                    itemStyle:{
                        normal:{
                            color:'#319CFE'
                        }
                    }
	            }]
	        };
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
			
	},
	error : function(data) {
		alert("服务器错误");
	}
});
   
    
    </script>
    
    <script type="text/javascript">
    $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/homerest/homerest/findHPRate",
    	type : "get",
    	success : function(data1) {	
   
    		var data = data1;
    		var table = $('#table111');
    		var html = " <tr style='background:#E5F7EF'>"
                +"<td>行政区域</td>"
                +"<td>接入城市</td>"
                +"<td>接入时间</td>"
                +"<td>参保人数量</td>"
                +"<td>参保人数量占比(%)</td>"
                +"<td>医院数量</td>"
                +"<td>医院数量占比(%)</td>  "  
                +"</tr>";
                
                for(var index in data){
                	
                
                	if(index==4){
                		break;
                	}else{
                	var area=data[index].area!=null?data[index].area:"-";
                	var city=data[index].city!=null?data[index].city:"-";
                	var time=data[index].time!=null?data[index].time:"-";
                	var pnumber=data[index].pnumber!=null?data[index].pnumber:"-";
                	var prate=data[index].prate!=null?data[index].prate:"-";
                	var hnumber=data[index].hnumber!=null?data[index].hnumber:"-";
                	var hrate=data[index].hrate!=null?data[index].hrate:"-";
                	prate=prate == "%"?"-":prate;
                	hrate=hrate == "%"?"-":hrate;
                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
               		html+="<td>"+area+"</td>"
               		+"<td>"+city+"</td>"
               		+"<td>"+time+"</td>"
               		+"<td>"+pnumber+"</td>"
               		+"<td>"+prate+"</td>"
               		+"<td>"+hnumber+"</td>"
               		+"<td>"+hrate+"</td>"
               		+"</tr>";
                }
                
                
                table.html(html);}
    			
    	},
    	error : function(data) {
    		alert("服务器错误");
    	}
    });
    
    </script>
    
    <script type="text/javascript">
    $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/superviseRule/superviseRule/getMapData",
    	type : "get",
    	success : function(data1) {	
   
    		var data = data1;
    		var table = $('#ruleAreaInfo');
    		var html = " <tr style='background:#E5F7EF'>"
    			+"<td>排名</td>"
                +"<td>行政区域</td>"
                +"<td>规则数量</td>"
                +"</tr>";
                var i = 1;
                for(var index in data){
                	var pnumber = data[index].pnumber;
                	var pname = data[index].pname;
                	if(pnumber==""||pnumber==null||pnumber=="null"||pnumber==0){
                		continue;
                	}else if(index==6){
                		break;
                	}else if(pname == "" || pname == null || pname=="null"){
                		continue;
                	}else{
                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
                	
               		html+="<td>"+(i++)+"</td>"
               		+"<td>"+pname+"</td>"
               		+"<td>"+pnumber+"</td>"
               		+"</tr>";
               		
                }
                
                
                table.html(html);}
    			
    	},
    	error : function(data) {
    		alert("服务器错误");
    	}
    });
    
    </script>
    
    
    <script type="text/javascript">
    
    $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/ruletype/ruleType/listVoRule",
    	type : "get",
    	success : function(data1) {	
    		if(data1 != null && data1 != ""){
    			var data = data1.split(",");
    			$('#total').html(data[0]);
        		$('#outrule').html(data[2]);
        		$('#inrule').html(data[1]);
        		$('#activerule').html(data[3]);
        		$('#nullrule').html(data[4]);
    		}else{
    			$('#total').html(0);
        		$('#outrule').html(0);
        		$('#inrule').html(0);
        		$('#activerule').html(0);
        		$('#nullrule').html(0);
    		}
    	
    		
    	},
    	error : function(data){
    		alert("服务器错误");
    	}
    });
    
    
    
    
    </script>
</body>
</html>
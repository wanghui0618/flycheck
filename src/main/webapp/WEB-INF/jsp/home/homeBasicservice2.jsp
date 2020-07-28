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
<body style="overflow-x:hidden">
<div class="layui-fluid" style="overflow-y:hidden">

        <div class="layui-col-md12" style="overflow-y:hidden">
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label style="font-size: 16px;">今天</label><label id="dateweek" style="margin-left:10px;">&nbsp;</label>
				</div>
  				<div class="layui-card-body ">
				  	<ul class="layui-row layui-col-space10 layui-this daiban">
                        <li style="padding-top: 0px;" class="layui-col-xs2 dangqianguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/guizezongshu.png"  />
                        	</div>
                            <div style="line-height:25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">当前规则总数</h3>
                               	<p style="font-size: 20px;"><label id="total">32</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="layui-col-xs2 menzhenguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/menzhen.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">门诊规则</h3>
                               	<p style="font-size: 20px;"><label id="outrule">20</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="layui-col-xs2 zhuyuanguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/zhuyuan.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">住院规则</h3>
                               	<p style="font-size: 20px;"><label id="inrule">10</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="layui-col-xs2 youxiaoguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/youxiao.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">有效规则</h3>
                               	<p style="font-size: 20px;"><label id="activerule">30</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="layui-col-xs2 wuxiaoguize" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/baseservice/wuxiao.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">无效规则</h3>
                               	<p style="font-size: 20px;"><label id="nullrule">2</label></p>
                            </div>
                        </li>
                    </ul>
  				</div>
  			</div>
	</div>

    <div class="layui-row layui-col-space30">
       <div class="layui-col-md12" style="padding: 6px 5px 5px 15px;">
            <div class="layui-card">
          		<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					上线规则
 					<a style="float: right;" lay-urlname="上线规则" href="javascript:;" lay-href="<%=request.getContextPath()%>/ruletype/ruleType2">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></a>
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
        
        <div class="layui-row layui-col-space50">
      <div class="layui-col-md12 layui-col-xs12" style="padding: 6px 0px 5px 0px;">
   		<div class="layui-card" >
             <div class="layui-card">
          		<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					数据对照情况
  					<%-- <a style="float: right;" lay-urlname="数据对照全部信息" href="javascript:;" lay-href="<%=request.getContextPath()%>/ruletype/ruleType">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></a> --%>
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
                  <td width="180" >类型</td>
                  <td>版本</td>
                  <td>总数</td>
                  <td>已对照</td>
                  <td>对照率</td>
                  <td>未对照</td>
                  <td>未对照率</td>       
                </tr>
                 <tr>
                  <td>疾病诊断与编码(ICD)对照</td>
                  <td>国家临床V2.0</td>
                  <td>25874</td>
                  <td>550</td>
                  <td>85.5%</td>
                  <td>370</td>
                  <td>14.5%</td>
                </tr>
                 <tr style='background:#F5FCF9'>
                  <td>药品对照</td>
                  <td>晋城市</td>
                  <td>37622</td>
                  <td>300</td>
                  <td>76.1%</td>
                  <td>378</td>
                  <td>23.9%</td>
                </tr>
                <tr>
                  <td>诊疗对照</td>
                  <td>晋城市</td>
                   <td>36734</td>
                  <td>235</td>
                  <td>81.2%</td>
                  <td>358</td>
                  <td>18.8%</td>
                </tr>
                 <tr style='background:#F5FCF9'>
                  <td>耗材对照</td>
                  <td>晋城市</td>
                  <td>37521</td>
                  <td>366</td>
                  <td>79.1%</td>
                  <td>295</td>
                  <td>20.9%</td>
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
 
    <script type="text/javascript">
    $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/ruletype/ruleType/listVo",
    	type : "get",
    	success : function(data1) {	
    		var data = data1;
    		var table = $('#table111');
    		var html = " <tr style='background:#E5F7EF'>"
                +"<td width=\"145\">规则名称</td>"
                +"<td>规则解释</td>  "  
                +"</tr>";
                for(var index=0;index<data.data.length;index++){
                	if(index==5){
                		break;
                	}else{
                	 var name=data.data[index].ruleName!=null?data.data[index].ruleName:"-";
                	var infrom=data.data[index].ruleDetails!=null?data.data[index].ruleDetails:"-"; 
                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
               		html+="<td>"+name+"</td>"
               		+"<td>"+infrom+"</td>"
               		+"</tr>";
                }
                
                
                table.html(html);}
    			
    	},
    	error : function(data) {
    		alert("服务器错误");
    	}
    });
    
    </script>
    
  <!--   <script type="text/javascript">
    $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/superviseRule/superviseRule/getMapData",
    	type : "get",
    	success : function(data1) {	
   
    		var data = data1;
    		var table = $('#ruleAreaInfo');
    		var html = " <tr style='background:#E5F7EF'>"
    			+"<td>类型</td>"
                +"<td>版本</td>"
                +"<td>总数</td>"
                +"<td>已对照</td>"
                +"<td>对照率</td>"
                +"<td>未对照</td>"
                +"<td>未对照率</td>"
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
                		html+="<tr style='background:#F5FCF9'>";
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
    
    </script> -->
    
    
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
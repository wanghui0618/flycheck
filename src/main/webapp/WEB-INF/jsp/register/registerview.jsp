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
<title>合理用药信息新增/修改页面</title>
<style>
.lb {
	margin-left: 10px; 
	width: 85px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: right;
	/* border-style: solid; */
}
.ipt {
	margin-left: 5px;
	width: 100px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: left;
	/* border-style: solid;  */
}

#div{
    margin-top: 8px;
    margin-bottom:8px;
   
}
</style>
</head>
<body >
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="register.id">
  				<div class="layui-fluid" id="parent"><!-- 宽度100% -->
  							<div class="layui-card" id="child"><!-- 面板 -->
  								<h5 style="font-size:13px;font-weight: bold;">就诊登记信息</h5>
      							<hr style="margin-top:1px;margin-bottom:1px;">
      							<div id="div">
      								<lable class="lb">城市编码:</lable>
      								<lable class="ipt" id="cityCode"></lable>
      								<lable class="lb">城市名称:</lable>
      								<lable class="ipt" id="cityName"></lable>
      								<lable class="lb">机构编码:</lable>
      								<lable class="ipt" id="handdingInsCode"></lable>
      								<lable class="lb">机构名称:</lable>
      								<lable class="ipt" id="handdingInsName"></lable>
      								<lable class="lb">创建日期:</lable>
      								<lable class="ipt" id="createDate"></lable>	
      							</div>
      							<div id="div">
      							    <lable class="lb">姓名:</lable>
      								<lable class="ipt" id="name"></lable>
      								<lable class="lb">性别:</lable>
      								<lable class="ipt" id="sex"></lable>	
      								<lable class="lb">年龄:</lable>
      								<lable class="ipt" id="age"></lable>
      								<lable class="lb">身份证号码:</lable>
      								<lable class="ipt" id="idCard"></lable>	
      								<lable class="lb">人群类别:</lable>
      								<lable class="ipt" id="personType"></lable>
      							
      							</div>
      							<div id="div">
      								<lable class="lb">个人医疗年度:</lable>
      								<lable class="ipt" id="selfMedical"></lable>
      								<lable class="lb">就诊类别:</lable>
      								<lable class="ipt" id="seeDocType"></lable>
      								<lable class="lb">就诊类别明细:</lable>
      								<lable class="ipt" id="seeDocDetail"></lable>
      								<lable class="lb">医院编码:</lable>
      								<lable class="ipt" id="hosCode"></lable>
      								<lable class="lb">医院名称:</lable>
      								<lable class="ipt" id="hosName"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb">科室编码:</lable>
      								<lable class="ipt" id="departCode"></lable>
      								<lable class="lb">科室名称:</lable>
      								<lable class="ipt" id="departName"></lable>
      								<lable class="lb">医师编码:</lable>
      								<lable class="ipt" id="docCode"></lable>
      								<lable class="lb">医师名称:</lable>
      								<lable class="ipt" id="docName"></lable>
      								<lable class="lb">人员状态:</lable>
      								<lable class="ipt" id="personFlag"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb">入院时间:</lable>
      								<lable class="ipt" id="inhosDate"></lable>
      								<lable class="lb">出院日期:</lable>
      								<lable class="ipt" id="outhosDate"></lable>
      								<lable class="lb">结算日期:</lable>
      								<lable class="ipt" id="balanceDate"></lable>
      								<lable class="lb">入院方式:</lable>
      								<lable class="ipt" id="inhosWay"></lable>
      								<lable class="lb">出院原因:</lable>
      								<lable class="ipt" id="outhosReason"></lable>
      							</div>
      						    <div id="div">
      								<lable class="lb">入院诊断:</lable>
      								<lable class="ipt" id="inhosSeeDoc"></lable>
      								<lable class="lb">出院诊断:</lable>
      								<lable class="ipt" id="outhosReason"></lable>
      								<lable class="lb">医院结算地点:</lable>
      								<lable class="ipt" id="balanceArea"></lable>
      								<lable class="lb">异地结算标志:</lable>
      								<lable class="ipt" id="balanceAreaFlag"></lable>
      								<lable class="lb">人员状态:</lable>
      								<lable class="ipt" id="personFlag"></lable>
      							</div>
      						    <div id="div">
      						    	<lable class="lb">就诊登记ID:</lable>
      								<lable class="ipt" id="seeDocId"></lable>
      								<lable class="lb" >单位ID:</lable>
      								<lable class="ipt" id="unitId"></lable>
      								<lable class="lb">结算ID:</lable>
      								<lable class="ipt" id="balanceId"></lable>
      								<lable class="lb">参保人员类别:</lable>
      								<lable class="ipt" id="insPersonType"></lable>
      								<lable class="lb">出院标志:</lable>
      								<lable class="ipt" id="outhosFlag"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb">险种标志:</lable>
      								<lable class="ipt" id="insuranceType"></lable>
      							</div>
      							<br>
  							</div>    
    
    <div class="layui-form-item layui-hide" style="align:center">
      <input type="button" lay-submit lay-filter="LAY-org-front-submit" id="LAY-org-front-submit" value="确认">
    </div>
  </div>


  <script>
  var form;
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
	  form = layui.form;
	  var layer = layui.layer;
	 
  })
  function child(obj){
	  var org = JSON.parse(obj);
	  //险种标志
	  if(org.insuranceType=='C'){
		  org.insuranceType='医疗'
	  }else if(org.insuranceType=='E'){
		  org.insuranceType='生育'
	  }
	  
	  //就诊类别
	  if(org.seeDocType=='1'){
		  org.seeDocType='住院'
	  }else if(org.seeDocType=='2'){
		  org.seeDocType='普通门诊'
	  }else if(org.seeDocType=='3'){
		  org.seeDocType='门诊大病'
	  }else if(org.seeDocType=='9'){
		  org.seeDocType='其他'
	  }
	  
	  //就诊类别明细
	  if(org.seeDocDetail=='11'){
		  org.seeDocDetail='普通门诊'
	  }else if(org.seeDocDetail=='12'){
		  org.seeDocDetail='急诊'
	  }else if(org.seeDocDetail=='3'){
		  org.seeDocDetail='门诊慢性病'
	  }else if(org.seeDocDetail=='14'){
		  org.seeDocDetail='特殊病种门诊'
	  }else if(org.seeDocDetail=='15'){
		  org.seeDocDetail='异地门诊'
	  }else if(org.seeDocDetail=='21'){
		  org.seeDocDetail='普通住院'
	  }else if(org.seeDocDetail=='22'){
		  org.seeDocDetail='特殊病种住院'
	  }else if(org.seeDocDetail=='23'){
		  org.seeDocDetail='转外诊治住院'
	  }else if(org.seeDocDetail=='25'){
		  org.seeDocDetail='异地住院'
	  }else if(org.seeDocDetail=='31'){
		  org.seeDocDetail='家庭病床'
	  }else if(org.seeDocDetail=='41'){
		  org.seeDocDetail='指定药店购药'
	  }else if(org.seeDocDetail=='51'){
		  org.seeDocDetail='生育门诊'
	  }else if(org.seeDocDetail=='52'){
		  org.seeDocDetail='生育住院'
	  }else if(org.seeDocDetail=='61'){
		  org.seeDocDetail='失业门诊'
	  }else if(org.seeDocDetail=='62'){
		  org.seeDocDetail='失业住院'
	  }else if(org.seeDocDetail=='71'){
		  org.seeDocDetail='工伤门诊'
	  }else if(org.seeDocDetail=='72'){
		  org.seeDocDetail='工伤住院'
	  }else if(org.seeDocDetail=='81'){
		  org.seeDocDetail='体检'
	  }else if(org.seeDocDetail=='99'){
		  org.seeDocDetail='其他'
	  }
	  
	  //入院方式
	  if(org.inhosWay=='1'){
		  org.inhosWay='新住院'
	  }else if(org.inhosWay=='2'){
		  org.inhosWay='转入院'
	  }else if(org.inhosWay=='3'){
		  org.inhosWay='急诊抢救住院'
	  }else if(org.inhosWay=='4'){
		  org.inhosWay='中途结算'
	  }
	  
	  //出院原因
	  if(org.outhosReason=='1'){
		  org.outhosReason='治愈'
	  }else if(org.outhosReason=='2'){
		  org.outhosReason='好转'
	  }else if(org.outhosReason=='3'){
		  org.outhosReason='未愈'
	  }else if(org.outhosReason=='4'){
		  org.outhosReason='死亡'
	  }else if(org.outhosReason=='5'){
		  org.outhosReason='转院'
	  }else if(org.outhosReason=='6'){
		  org.outhosReason='转外'
	  }
	  
	  //异地结算标志
	  if(org.balanceAreaFlag=='0'){
		  org.balanceAreaFlag='非异地'
	  }else if(org.balanceAreaFlag=='1'){
		  org.balanceAreaFlag='省内异地'
	  }else if(org.balanceAreaFlag=='2'){
		  org.balanceAreaFlag='省外异地'
	  }
	  
	  //出院标志
	  if(org.outhosFlag=='0'){
		  org.outhosFlag='在院'
	  }else if(org.outhosFlag=='1'){
		  org.outhosFlag='出院'
	  }
	  
	  //性别
	  if(org.sex=='1'){
		  org.sex='男'
	  }else if(org.sex=='2'){
		  org.sex='女'
	  }
	  
	  
	 
	  
	  
	  
	  for (var index in org){
	  /*     $("#"+index).text(org[index]); */
	  	     $("#" + index).html(org[index]);
	  }
  }
  </script>
</body>
</html>
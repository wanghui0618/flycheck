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

<title>城市机构管理</title>
<style>
.layui-form-label {
	width: 140px;
}

.layui-form-item {
	margin-top: 25px;
}
</style>
</head>
<body>
<style>
    .layui-form-select dl { max-height:130px; }
</style>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="rangeInfo.id" id="id">
		<!-- rangeInfo -->
		<div class="layui-form-item">
			<label class="layui-form-label">城市编码</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.cityCode" id="city" lay-search="">
					<option value="" disabled selected style='display: none;'>请选择城市</option>
				</select> -->
				<input type="text" id="cityName"
					name="rangeInfo.cityCode" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
			</div>
			
			<label class="layui-form-label">机构类型</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.orgType" id="orgType">
					<option value="" disabled selected style='display: none;'>请选择机构类型</option>
					<option value="1">医院</option>
					<option value="0">门诊</option>
					<option value="2">药店</option>
				</select> -->
				<input type="text" id="orgType"
					name="rangeInfo.orgType" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
				
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">机构编号</label>
			<div class="layui-input-inline">
				<input type="text" id="orgCode"
					name="rangeInfo.orgCode" lay-verify="required"
					placeholder=" " autocomplete="off" class="layui-input" readonly="true">
			</div>

			<label class="layui-form-label">机构名称</label>
			<div class="layui-input-inline">
				<input type="text" id="orgName"
					name="rangeInfo.orgName" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">互联网结算开通</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.netContact" id="netContact">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select> -->
				<input type="text" id="netContact"
					name="rangeInfo.orgType" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
				
			</div>
			
			<label class="layui-form-label">住院结算开通</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.inhosBalance" id="inhosBalance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select> -->
				<input type="text" id="inhosBalance"
					name="rangeInfo.orgType" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
				
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">门诊大病结算开通</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.outpatientBalance" id="outpatientBalance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select> -->
				<input type="text" id="outpatientBalance"
					name="rangeInfo.orgType" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
				
			</div>
			
			<label class="layui-form-label">生育结算开通</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.birthBalance" id="birthBalance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select> -->
							<input type="text" id="birthBalance"
					name="rangeInfo.orgType" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
				
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">刷卡结算开通</label>
			<div class="layui-input-inline">
				<!-- <select name="rangeInfo.cardBanlance" id="cardBanlance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select> -->
						<input type="text" id="cardBanlance"
					name="rangeInfo.orgType" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
				
			</div>
			<label class="layui-form-label">经办机构编码</label>
			<div class="layui-input-inline">
				<input type="text" id="handdingInsCode"
					name="rangeInfo.handdingInsCode" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">定点协议结算日期</label>
			<div class="layui-input-inline">
				<input type="text" id="insBalanceDate" name="rangeInfo.insBalanceDate"
					lay-verify="required" placeholder="" autocomplete="off"
					class="layui-input" readonly="true">
			</div>

			<label class="layui-form-label">定点协议终止或暂停结算日期</label>
			<div class="layui-input-inline">
				<input type="text" id="insBalanceStopDate" name="rangeInfo.insBalanceStopDate"
					lay-verify="required" placeholder="" autocomplete="off"
					class="layui-input" readonly="true">
			</div>
		</div>
		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityorg-front-submit"
				id="LAY-cityorg-front-submit" value="确认">
		</div>
	</div>


	<script>

  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form','laydate'], function(){
	  var form=layui.form;
	
	//监听select change
	 form.on('select(city)', function(data){
			 //$("#cityName").attr("value",data.text);
			// alert($(data.elem).find("option:selected").text());
			$("#cityName").val($(data.elem).find("option:selected").text());
			form.render('select');
	 
	});
	
  });
	 function child(obj){
		 
		  var selfPayRatio = JSON.parse(obj);
		  $("#id").val(selfPayRatio["id"]);
		  for (var index in selfPayRatio){
		      $("#"+index).val(selfPayRatio[index]);
		  }
		  
		  
		  var orgtype = selfPayRatio["orgType"];
		  var netContact = selfPayRatio["netContact"];
		  var outpatientBalance = selfPayRatio["outpatientBalance"];
		  var inhosBalance = selfPayRatio["inhosBalance"];
		  var cardBanlance = selfPayRatio["cardBanlance"];
		  var birthBalance = selfPayRatio["birthBalance"];
		  if(orgtype == 1){
			  $("#orgType").val("医院");
		  }else if(orgtype == 2){
			  $("#orgType").val("药店");
		  }else if(orgtype == 0){
			  $("#orgType").val("门诊");
		  }else{
			  $("#orgType").val(orgtype);
		  }
		  if(netContact == 1){
			  $("#netContact").val("开通");
		  }else if(netContact == 2){
			  $("#netContact").val("停止");
		  }else if(netContact == 0){
			  $("#netContact").val("未开通");
		  }else{
			  $("#netContact").val(netContact);
		  }
		  
		  if(outpatientBalance == 1){
			  $("#outpatientBalance").val("开通");
		  }else if(outpatientBalance == 2){
			  $("#outpatientBalance").val("停止");
		  }else if(outpatientBalance == 0){
			  $("#outpatientBalance").val("未开通");
		  }else{
			  $("#outpatientBalance").val(outpatientBalance);
		  }
		  if(inhosBalance == 1){
			  $("#inhosBalance").val("开通");
		  }else if(inhosBalance == 2){
			  $("#inhosBalance").val("停止");
		  }else if(inhosBalance == 0){
			  $("#inhosBalance").val("未开通");
		  }else{
			  $("#inhosBalance").val(inhosBalance);
		  }
		  if(cardBanlance == 1){
			  $("#cardBanlance").val("开通");
		  }else if(cardBanlance == 2){
			  $("#cardBanlance").val("停止");
		  }else if(cardBanlance == 0){
			  $("#cardBanlance").val("未开通");
		  }else{
			  $("#cardBanlance").val(cardBanlance);
		  }
		  if(birthBalance == 1){
			  $("#birthBalance").val("开通");
		  }else if(birthBalance == 2){
			  $("#birthBalance").val("停止");
		  }else if(birthBalance == 0){
			  $("#birthBalance").val("未开通");
		  }else{
			  $("#birthBalance").val(birthBalance);
		  }
		  
		  
		  
		  $("#insuranceType").find("option[value ='"+selfPayRatio.insuranceType+"']").prop("selected",true);
		  var code=selfPayRatio.cityCode;
		  loadSelect(code);
		  
	  }
	//加载城市下拉字典,根据参数为select赋值
  	function loadSelect(code){
  	
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
							function(data){
			 					var form=layui.form;
					     		var  dataList= data.dictList;
					     		for(var i=0 ;i<dataList.length;i++){
					     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
					     			$("#city").append(mm); 
					     		}
					     		if(code!=null){
						           $("#city").find("option[value ='"+code+"']").attr("selected","selected");
					     		}
					     		form.render('select');
			}); 
  	}
 
  </script>
</body>
</html>
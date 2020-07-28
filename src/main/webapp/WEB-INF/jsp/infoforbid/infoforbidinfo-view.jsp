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

		<input type="hidden" name="infoForbid.id" id="id">
		<!-- rangeInfo -->
		<div class="layui-form-item">
			<label class="layui-form-label">城市编码</label>
			<div class="layui-input-inline">
				<!-- <select name="infoForbid.cityCode" id="city" lay-serach="" readonly="true">
					<option value="" disabled selected style='display: none;'>请选择城市</option>
				</select> -->
				<input type="text" id="cityName"
					name="infoForbid.cityCode" lay-verify="required"
					placeholder=" " autocomplete="off" class="layui-input" readonly="true">
			</div>
			
			<label class="layui-form-label">机构类型</label>
			<div class="layui-input-inline">
				<!-- <select name="infoForbid.orgType" id="orgType" readonly="true">
					<option value="" disabled selected style='display: none;'>请选择机构类型</option>
					<option value="1">医院</option>
					<option value="0">门诊</option>
					<option value="2">药店</option>
				</select> -->
				<input type="text" id="orgType"
					name="infoForbid.orgType" lay-verify="required"
					placeholder=" " autocomplete="off" class="layui-input" readonly="true">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label" >机构编号</label>
			<div class="layui-input-inline">
				<input type="text" id="orgCode"
					name="infoForbid.orgCode" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
			</div>

			<label class="layui-form-label">机构名称</label>
			<div class="layui-input-inline">
				<input type="text" id="orgName"
					name="infoForbid.orgName" lay-verify="required"
					placeholder="" autocomplete="off" class="layui-input" readonly="true">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">所在地区编码</label>
			<div class="layui-input-inline">
				<input type="text" id="areaCode"
					name="infoForbid.areaCode" lay-verify="required"
					placeholder=" " autocomplete="off" class="layui-input" readonly="true">
			</div>
			
			<label class="layui-form-label">服务资格停止原因</label>
			<div class="layui-input-inline">
				<input type="text" id="stopReason"
					name="infoForbid.stopReason" lay-verify="required"
					placeholder=" " autocomplete="off" class="layui-input" readonly="true">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">停止服务资格起始时间</label>
			<div class="layui-input-inline">
				<input type="text" id="stopBeginDate" name="infoForbid.stopBeginDate"
					lay-verify="required" placeholder="" autocomplete="off"
					class="layui-input" readonly="true">
			</div>

			<label class="layui-form-label">停止服务资格终止时间</label>
			<div class="layui-input-inline">
				<input type="text" id="stopOverDate" name="infoForbid.stopOverDate"
					lay-verify="required" placeholder="" autocomplete="off"
					class="layui-input" readonly="true">
			</div>
		</div>
		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityorg-front-submit"
				id="LAY-cityorg-front-submit" value="确认" >
		</div>
	</div>

<!--  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/infoforbid/infoforbidinfo.js"></script>-->
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
		 console.log(obj);
		  var selfPayRatio = JSON.parse(obj);
		  $("#id").val(selfPayRatio["id"]);
		  for (var index in selfPayRatio){
		      $("#"+index).val(selfPayRatio[index]);
		  }
		  
		  var orgtype = selfPayRatio["orgType"];
		  if(orgtype == 1){
			  $("#orgType").val("医院");
		  }else if(orgtype == 2){
			  $("#orgType").val("药店");
		  }else if(orgtype == 0){
			  $("#orgType").val("门诊");
		  }else{
			  $("#orgType").val(orgtype);
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
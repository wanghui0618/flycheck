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
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">
<style>
    .layui-form-select dl { max-height:130px; }
</style>
		<input type="hidden" name="selfPayRatio.id" id="id">

		<div class="layui-form-item">
			<label class="layui-form-label">城市名称</label>
			<div class="layui-input-inline">
				<select name="selfPayRatio.cityCode" id="city" lay-search="">
					<option value="" disabled selected style='display: none;'>请选择城市</option>
				</select>
			</div>
			
			<label class="layui-form-label">险种类型</label>
			<div class="layui-input-inline">
				<select name="selfPayRatio.insuranceType" id="insuranceType">
					<option value="" disabled selected style='display: none;'>请选择险种类型</option>
					<option value="1">医疗</option>
					<option value="2">工伤</option>
					<option value="3">生育</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">三大目录编码</label>
			<div class="layui-input-inline">
				<input type="text" id="threeDirectoryCode"
					name="selfPayRatio.threeDirectoryCode" lay-verify="number"
					placeholder="请输入三大目录编码 " autocomplete="off" class="layui-input">
			</div>

			<label class="layui-form-label">三大目录自付比例编码</label>
			<div class="layui-input-inline">
				<input type="text" id="selfPaymentRatioCoding"
					name="selfPayRatio.selfPaymentRatioCoding" lay-verify="number"
					placeholder="请输入三大目录比例编码" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">剂型</label>
			<div class="layui-input-inline">
				<input type="text" id="dosageForm" name="selfPayRatio.dosageForm"
					lay-verify="required" placeholder="请输入剂型" autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">单处方先自付比例</label>
			<div class="layui-input-inline">
				<input type="text" id="singlePrescriptionRatio"
					name="selfPayRatio.singlePrescriptionRatio" lay-verify="required"
					placeholder="请输入单处方先自付比例" autocomplete="off" class="layui-input"
					>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">先自费比例</label>
			<div class="layui-input-inline">
				<input type="text" id="selfPaymentRatio"
					name="selfPayRatio.selfPaymentRatio" lay-verify="required"
					placeholder="请输入先自费比例" autocomplete="off" class="layui-input">
			</div>

			<label class="layui-form-label">参保身份</label>
			<div class="layui-input-inline">
				<input type="text" id="insuredStatus"
					name="selfPayRatio.insuredStatus" lay-verify="required"
					placeholder="请输入参保身份" autocomplete="off" class="layui-input">
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
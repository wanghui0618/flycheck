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

		<input type="hidden" name="insureUnit.id" id="id">
		<!-- rangeInfo -->
		<div class="layui-form-item">
			<label class="layui-form-label">城市编码</label>
			<div class="layui-input-inline">
				<input type="text" id="cityName"
					name="insureUnit.handdingInsCode" lay-verify="required"
					placeholder=" " readonly="true" autocomplete="off" class="layui-input">
			</div>
			
			<label class="layui-form-label">经办机构编码</label>
			<div class="layui-input-inline">
				<input type="text" id=handdingInsCode
					name="insureUnit.handdingInsCode" lay-verify="required"
					placeholder="" readonly="true" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">单位编号</label>
			<div class="layui-input-inline">
				<input type="text" id="unitCode"
					name="insureUnit.unitCode" lay-verify="required"
					placeholder="请输入单位编号 " readonly="true" autocomplete="off" class="layui-input">
			</div>

			<label class="layui-form-label">单位名称</label>
			<div class="layui-input-inline">
				<input type="text" id="unitName"
					name="insureUnit.unitName" lay-verify="required"
					placeholder="请输入单位名称" readonly="true" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">组织机构代码</label>
			<div class="layui-input-inline">
				<input type="text" id="stateOrgCode"
					name="insureUnit.stateOrgCode" lay-verify="required"
					placeholder="" readonly="true" autocomplete="off" class="layui-input">
			</div>


			<label class="layui-form-label">统一社会信用代码</label>
			<div class="layui-input-inline">
				<input type="text" id="socialCreditCode"
					name="insureUnit.socialCreditCode" lay-verify="required"
					placeholder="" readonly="true" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单位性质</label>
			<div class="layui-input-inline">
				<input type="text" id="unitNature"
					name="insureUnit.unitNature" lay-verify="required"
					placeholder="" readonly="true" autocomplete="off" class="layui-input">
			</div>
			
			<label class="layui-form-label">单位目前投保的险种</label>
			<div class="layui-input-inline">
				<input type="text" id="unitInsurance"
					name="insureUnit.unitInsurance" lay-verify="required"
					placeholder=" " readonly="true" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单位目前投保的险种名称</label>
			<div class="layui-input-inline">
				<input type="text" id="unitInsranceName"
					name="insureUnit.unitInsranceName" lay-verify="required"
					placeholder="" readonly="true" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityorg-front-submit"
				id="LAY-cityorg-front-submit" value="确认">
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/infoforbid/infoforbidinfo.js"></script>
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
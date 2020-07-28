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
.layui-form-label{
	width:115px;
}

</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="dictCityPrice.id" id="id">

		<div class="layui-form-item">
			<label class="layui-form-label">项目编码</label>
			<div class="layui-input-inline">
				<input type="text" id="itemCode" name="dictCityPrice.itemCode"
					lay-verify="number" placeholder="请输入项目编码 "autocomplete="off"
					class="layui-input">
			</div>
		
		
			<label class="layui-form-label">项目名称</label>
			<div class="layui-input-inline">
				<input type="text" id="itemName"
					name="dictCityPrice.itemName" lay-verify="required"
					placeholder="请输入项目名称" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">项目内涵</label>
			<div class="layui-input-inline">
				<input type="text" id="itemContent" name="dictCityPrice.itemContent"
					 placeholder="请输入项目内涵"  autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">除外内容</label>
			<div class="layui-input-inline">
				<input type="text" id="exclusionContent"
					name="dictCityPrice.exclusionContent" 
					placeholder="请输入除外内容" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">计价单位</label>
			<div class="layui-input-inline">
				<input type="text" id="chargeUnit" name="dictCityPrice.chargeUnit"
					 placeholder="请输入计价单位" autocomplete="off"
					class="layui-input">
			</div>
		
			<label class="layui-form-label">三甲医院</label>
			<div class="layui-input-inline">
				<input type="text" id="iiiaPrice" name="dictCityPrice.iiiaPrice"
					 placeholder="请输入三甲医院" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">三甲以下医院</label>
			<div class="layui-input-inline">
				<input type="text" id="iiiaLowPrice" name="dictCityPrice.iiiaLowPrice"
					 placeholder="请输入三甲以下医院" autocomplete="off"
					class="layui-input">
			</div>
		
			<label class="layui-form-label">原价</label>
			<div class="layui-input-inline">
				<input type="text" id="regularPrice" name="dictCityPrice.regularPrice"
					 placeholder="请输入原价" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">基层原方案</label>
			<div class="layui-input-inline">
				<input type="text" id="primaryRegularPrice" name="dictCityPrice.primaryRegularPrice"
					 placeholder="请输入基层原方案" autocomplete="off"
					class="layui-input">
			</div>
		
			<label class="layui-form-label">基层</label>
			<div class="layui-input-inline">
				<input type="text" id="primaryPrice" name="dictCityPrice.primaryPrice"
					 placeholder="请输入基层" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">县</label>
			<div class="layui-input-inline">
				<input type="text" id="countyPrice" name="dictCityPrice.countyPrice"
					 placeholder="请输入县" autocomplete="off"
					class="layui-input">
			</div>
		
			<label class="layui-form-label">市</label>
			<div class="layui-input-inline">
				<input type="text" id="cityPrice" name="dictCityPrice.cityPrice"
					 placeholder="请输入市" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">说明</label>
			<div class="layui-input-inline">
				<input type="text" id="explanation" name="dictCityPrice.explanation"
					 placeholder="请输入说明" autocomplete="off"
					class="layui-input">
			</div>
			
			<label class="layui-form-label">城市</label>
			<div class="layui-input-inline">
				<select   name="dictCityPrice.cityCode" autocomplete="off" id="city" lay-verify="required" lay-filter="city" lay-search="">
					<option value="">请选择城市</option>
				</select>
			</div>
		</div>
<style>
    .layui-form-select dl { max-height:150px; }
</style>
		
		<div class="layui-form-item">
			<!-- <label class="layui-form-label">城市名称</label> -->
			<div class="layui-input-inline">
				<input type="hidden" id="cityName" name="dictCityPrice.cityName"
					lay-verify="required" placeholder="请输入城市名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityprice-front-submit"
				id="LAY-cityprice-front-submit" value="确认">
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
  })
  
  function child(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#id").val(cityOrg["studentId"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
	  var code=cityOrg.cityCode;
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
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
	width: 115px;
}
</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="dictCityOrg.id" id="id">

		<div class="layui-form-item">
			<label class="layui-form-label">机构编码</label>
			<div class="layui-input-inline">
				<input type="text" id="orgCode" name="dictCityOrg.orgCode"
					lay-verify="required" placeholder="请输入机构编码 " autocomplete="off"
					class="layui-input">
			</div>


			<label class="layui-form-label">机构名称</label>
			<div class="layui-input-inline">
				<input type="text" id="orgName" name="dictCityOrg.orgName"
					lay-verify="required" placeholder="请输入机构名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">服务机构类型</label>
			<div class="layui-input-inline">
				<input type="text" id="orgKind" name="dictCityOrg.orgKind"
					lay-verify="required" placeholder="请输入机构类型" autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">医疗机构级别</label>
			<div class="layui-input-inline">
				<input type="text" id="orgType" name="dictCityOrg.orgType"
					lay-verify="required" placeholder="请输入机构级别" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">医院等级</label>
			<div class="layui-input-inline">
				<input type="text" id="orgLevel" name="dictCityOrg.orgLevel"
					lay-verify="required" placeholder="请输入医院等级" autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">医保许可范围</label>
			<div class="layui-input-inline">
				<input type="text" id="medicareCoverage"
					name="dictCityOrg.medicareCoverage" lay-verify="required"
					placeholder="请输入医保许可范围" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">床位数</label>
			<div class="layui-input-inline">
				<input type="text" id="bedNum" name="dictCityOrg.bedNum"
					lay-verify="required" placeholder="请输入床位数" autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">公立医院级别</label>
			<div class="layui-input-inline">
				<input type="text" id="publicHospitalLevel"
					name="dictCityOrg.publicHospitalLevel" lay-verify="required"
					placeholder="请输入公立医院级别" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">行政区域级别</label>
			<div class="layui-input-inline">
				<input type="text" id="administrativeLevel"
					name="dictCityOrg.administrativeLevel" lay-verify="required"
					placeholder="请输入行政区域级别" autocomplete="off" class="layui-input">
			</div>

			<label class="layui-form-label">医疗机构类别级别</label>
			<div class="layui-input-inline">
				<input type="text" id="orgTypeLevel" name="dictCityOrg.orgTypeLevel"
					lay-verify="required" placeholder="请输入医疗机构类别级别" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
<style>
    .layui-form-select dl { max-height:100px; }
</style>
			<label class="layui-form-label">城市</label>
			<div class="layui-input-inline">
				<select   name="dictCityOrg.cityCode" autocomplete="off" id="city" lay-verify="required" lay-filter="city" lay-search="">
					<option value="">请选择城市</option>
				</select>
			</div>
			<label class="layui-form-label">医疗机构级别别名</label>
			<div class="layui-input-inline">
				<input type="text" id="orgLevelAlias"
					name="dictCityOrg.orgLevelAlias" lay-verify="required"
					placeholder="请输入医疗机构级别别名" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
		<!-- <label class="layui-form-label">城市名称</label> -->
			<div class="layui-input-inline">
				<input type="hidden" id="cityName" name="dictCityOrg.cityName"
					lay-verify="required" placeholder="" autocomplete="off"
					class="layui-input" >
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
		 
		  var cityOrg = JSON.parse(obj);
		  $("#id").val(cityOrg["id"]);
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
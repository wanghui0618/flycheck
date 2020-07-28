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

<title>药品相互作用管理</title>
<style>
.layui-form-label{
	width:115px;
}

</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="drugInteraction.id" id="id">

		<div class="layui-form-item">
			<label class="layui-form-label">药品名称</label>
			<div class="layui-input-inline">
				<input type="text" id="drugName" name="drugInteraction.drugName"
					lay-verify="required" placeholder="请输入药品名称 "autocomplete="off"
					class="layui-input">
			</div>
		
		
			<label class="layui-form-label">相互作用药品名称</label>
			<div class="layui-input-inline">
				<input type="text" id="interactionDrugName"
					name="drugInteraction.interactionDrugName" lay-verify="required"
					placeholder="请输入相互作用药品名称" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">相互作用效果</label>
			<div class="layui-input-inline">
				<input type="text" id="interactionEffect" name="drugInteraction.interactionEffect"
					lay-verify="required" placeholder="请输入相互作用效果"  autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">临床建议</label>
			<div class="layui-input-inline">
				<input type="text" id="clinicalRecommendations"
					name="drugInteraction.clinicalRecommendations" lay-verify="required"
					placeholder="请输入临床建议" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">临床证据</label>
			<div class="layui-input-inline">
				<input type="text" id="clinicalEvidence" name="drugInteraction.clinicalEvidence"
					lay-verify="required" placeholder="请输入临床证据" autocomplete="off"
					class="layui-input">
			</div>
		
			<label class="layui-form-label">证据级别</label>
			<div class="layui-input-inline">
				<input type="text" id="evidenceLevel" name="drugInteraction.evidenceLevel"
					lay-verify="required" placeholder="请输入证据级别" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">参考文献</label>
			<div class="layui-input-inline">
				<input type="text" id="reference" name="drugInteraction.reference"
					lay-verify="required" placeholder="请输入参考文献" autocomplete="off"
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
	
  })
  
  function child(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#id").val(cityOrg["id"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
  }
  function child1(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#drugName").val(cityOrg["drugName"]);
  }

  </script>
</body>
</html>
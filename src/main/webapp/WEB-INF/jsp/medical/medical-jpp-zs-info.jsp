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

<title>终审稽核</title>
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
			<label class="layui-form-label">巡查审核结果:</label>
			<div class="layui-input-inline">
				<input type="text" id="reviewResult" name="medicalAudit.reviewResult"
					readonly="readonly"
					class="layui-input">
			</div>


			<label class="layui-form-label">巡查时间:</label>
			<div class="layui-input-inline">
				<input type="text" id="patrolTime" name="medicalAudit.patrolTime" readonly="readonly"
					
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">巡查记录状态:</label>
			<div class="layui-input-inline">
				<!-- <select id="patrolRecordState"  name="medicalAudit.patrolRecordState" >
				    <option value=""></option>
				    <option value="0">未提交</option>
				    <option value="1">提交</option>
				</select> -->
				<input type="text" id="patrolRecordState" name="medicalAudit.patrolRecordState"
					readonly="readonly"
					class="layui-input">
			</div>

			<label class="layui-form-label">审核意见:</label>
			<div class="layui-input-inline">
				<input type="text" id="auditOpinion" name="medicalAudit.auditOpinion"
					readonly="readonly"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">总体问题:</label>
			<div class="layui-input-inline">
				<input type="text" id="overallProblem" name="medicalAudit.overallProblem"
					readonly="readonly"
					class="layui-input">
			</div>

			<label class="layui-form-label">巡查结论:</label>
			<div class="layui-input-inline">
				<input type="text" id="patrolConclusion"
					name="medicalAudit.patrolConclusion" lay-verify="required"
					readonly="readonly" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">文件地址:</label>
			<div class="layui-input-inline">
				<input type="text" id="fileurl" name="medicalAudit.fileurl"
					readonly="readonly"
					class="layui-input">
			</div>

			<label class="layui-form-label">文件原始名称:</label>
			<div class="layui-input-inline">
				<input type="text" id="filenameurl"
					name="medicalAudit.filenameurl" lay-verify="required"
					readonly="readonly" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">稽核状态:</label>
			<div class="layui-input-inline">
				<input type="text" id="aduitStatus"
					name="medicalAudit.aduitStatus" lay-verify="required"
					readonly="readonly" class="layui-input">
				<!-- <select id="aduitStatus"  name="medicalAudit.aduitStatus" >
				    <option value=""></option>
				    <option value="0">违规</option>
				    <option value="2">未违规</option>
				</select> -->
			</div>
		</div>
		
		
	</div>

	<script>

  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form','laydate'], function(){
	  var form=layui.form;
	  var laydate = layui.laydate;
	  laydate.render({
		    elem: '#patrolTime' //指定元素
	  });
	
	
  });
	 function child(obj){
		 
		  var cityOrg = JSON.parse(obj);
		  $("#id").val(cityOrg["id"]);
		  for (var index in cityOrg){
		      $("#"+index).val(cityOrg[index]);
		  }
		  var code=cityOrg.patrolRecordState;
		 if(code==0){
		  	 $("#patrolRecordState").val("未提交");
		 }else if(code=1){
			 $("#patrolRecordState").val("提交");
		 }
		 var code2=cityOrg.aduitStatus;
		 if(code2==0){
			  	 $("#aduitStatus").val("违规");
		 }else if(code2==2){
				 $("#aduitStatus").val("未违规");
		 }
		  
		  
	  }
	
 
  </script>
</body>
</html>
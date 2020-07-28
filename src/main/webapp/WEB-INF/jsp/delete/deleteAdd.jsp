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
<title>新增就诊信息删除页面</title>
</head>
<style>
.layui-form-select dl {
   max-height:160px; 
}
.layui-form-item {
   margin-left: 50px;
}
</style>
<body style="overflow: hidden">
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
		<input type="hidden" id="id" name="delete.id"> <br>
		<div class="layui-form-item">
			<label class="layui-form-label">城市名称</label>
			<div class="layui-input-inline">
				<select name="delete.cityCode" id="cityCode" lay-search="">
					<option value="" disabled selected style='display: none;'>请选择城市</option>
				</select>
			</div>
			
			<label class="layui-form-label">统筹编码</label>
			<div class="layui-input-inline">
				<input type="text" id="handdingInsCode"
					name="delete.handdingInsCode" lay-verify="required"
					placeholder="请填写统筹区编码" autocomplete="off" maxlength="32"
					class="layui-input">
			</div>
		</div>
		<br>
		<div class="layui-form-item">
		
			<label class="layui-form-label">机构编码</label>
			<div class="layui-input-inline">
				<input type="text" id="orgCode"
					name="delete.orgCode" lay-verify="required"
					placeholder="请填写机构编码" autocomplete="off" maxlength="50"
					class="layui-input">
			</div>
			 <label class="layui-form-label">处理方式</label>
			<div class="layui-input-inline" id="dealFlag">
				<select name="delete.dealFlag">
					<option value="1">修改</option>
					<option value="2" id="deleteTest">删除</option>
				</select>
			</div>
		</div>
		<br>
		<div class="layui-form-item">
		    <label class="layui-form-label">出院状态</label>
			<div class="layui-input-inline" id="outHosFlag">
				<select name="delete.outHosFlag">
					<option value="0">在院</option>
					<option value="1">出院</option>
					<option value="2">撤销出院</option>
				</select>
			</div>
			<label class="layui-form-label">就诊类别</label>
			<div class="layui-input-inline" id="seeDocType">
				<select name="delete.seeDocType">
					<option value="1">住院</option>
					<option value="2">门诊</option>
				</select>
			</div>
		</div>
		<br>
		<div class="layui-form-item">
		    <label class="layui-form-label">业务编码</label>
			<div class="layui-input-inline">
				<input type="text" id="workId" name="delete.workId"
					lay-verify="required" placeholder="请填写业务编码" autocomplete="off"
					maxlength="255" class="layui-input">
			</div>
			<label class="layui-form-label">业务类别</label>
			<div class="layui-input-inline layui-form-selectup" >
				<select name="delete.workType" id="workType" style="height: 10px;">
								<option value="" disabled selected style='display:none;'>请选择类别</option>
								<option>就诊登记信息</option>
    	           		<option>就诊单据信息（住院）</option>
    	           		<option>就诊单据明细信息（住院）</option>
    	           		<option>就诊医嘱信息（住院）</option>
    	           		<option>病案首页信息（住院）</option>
    	           		<option>门慢备案信息（门诊）</option>
    	           		<option>就诊单据信息（门诊）</option>
    	           		<option>就诊单据明细信息（门诊）</option>
    	           		<option>就诊处方信息（门诊）</option>
    	           		<option>就诊诊断信息</option>
    	           		<option>就诊手术操作信息</option>
							</select>
			</div>
		</div>
		<br>
		<div class="layui-form-item">
		    <label class="layui-form-label">创建日期</label>
			<div class="layui-input-inline">
				<input type="text" id="createDate" name="delete.createDate"
					lay-verify="required" placeholder="点击选择创建日期" autocomplete="off"
					class="layui-input">
			</div>
			<label class="layui-form-label">更新日期</label>
			<div class="layui-input-inline">
				<input type="text" id="updateDate" name="delete.updateDate"
					placeholder="点击选择更新日期" autocomplete="off" class="layui-input">
			</div>
		</div>
		<br>
		<div class="layui-form-item">
		    <label class="layui-form-label">删除日期</label>
			<div class="layui-input-inline">
				<input type="text" id="deleteDate" name="delete.deleteDate"
					placeholder="点击选择删除日期" autocomplete="off" class="layui-input">
			</div>
		</div><br> <br> <br> <br> <br>
		<div class="layui-form-item layui-hide" style="align: center">
			<input type="button" lay-submit lay-filter="LAY-org-front-submit"
				id="LAY-org-front-submit" value="确认">
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
	  for (var index in org){
	      $("#"+index).val(org[index]);
	  }
  }
  </script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/delete/deleteAdd.js"></script>
</body>
</html>
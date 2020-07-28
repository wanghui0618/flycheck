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
		<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
		<title>新增页面</title>
		<style>
			.layui-form-item{
				margin-left:20px;
			}
		</style>
	</head>
	<body>
		<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
			<input type="hidden" id="id" name="flyScreenMainDiagnosis.id" >
			<div class="layui-form-item">
				<label class="layui-form-label">主要诊断</label>
				<div class="layui-input-inline">
					<input id="mainDiagnosis" class="layui-input" type="text" name="flyScreenMainDiagnosis.mainDiagnosis" lay-verify="required" placeholder="请输入" autocomplete="off">
				</div>
				<label class="layui-form-label">操作人员</label>
				<div class="layui-input-inline">
					<input id="operator" class="layui-input" type="text" name="flyScreenMainDiagnosis.operator" lay-verify="required"  placeholder="请输入" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">项目代码</label>
				<div class="layui-input-inline">
					<input id="projectId" class="layui-input" type="text" name="flyScreenMainDiagnosis.projectId"  placeholder="请输入" autocomplete="off">
				</div>
				<label class="layui-form-label">项目名称</label>
				<div class="layui-input-inline">
					<input id="projectName" class="layui-input" type="text" name="flyScreenMainDiagnosis.projectName"  placeholder="请输入" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item layui-hide">
				<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
			</div>
		</div>
		<script>
			layui.config(
				{
					base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
				}
			).extend(
				{
					index: 'lib/index' //主入口模块
				}
			).use(
				['index', 'form'],
				function(){}
			);
			function child(obj){
				var medical = JSON.parse(obj);
				$("#id").val(medical["id"]);
				for(var index in medical){
					$("#"+index).val(medical[index]);
				}
			}
		</script>
	</body>
</html>
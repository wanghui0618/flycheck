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

<title>立即执行一次</title>
<style>

</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="scheduledTask.taskKey" id="taskKey">

		<div class="layui-form-item">
			<label class="layui-form-label">支付时间段</label>
			<div class="layui-input-inline">
				<input type="text" id="paymentDate" name="paymentDate"
						placeholder="请选择时间段" autocomplete="off" class="layui-input">
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
  }).use(['index', 'form','laydate','element'], function(){
	  var form=layui.form;
	  var element = layui.element;
	  var laydate = layui.laydate;
	  laydate.render({
	    	elem: '#paymentDate'
	    		,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: true
	  });
  });
function child(obj){
	
	$("#taskKey").val(obj);
}
	
 
  </script>
</body>
</html>
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

<title>定时任务维护</title>
<style>
.layui-form-label {
	width: 115px;
}
</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="scheduledTask.id" id="id">

		<div class="layui-form-item">
			<label class="layui-form-label">任务名称</label>
			<div class="layui-input-inline">
				<input type="text" id="taskKey" name="scheduledTask.taskKey"
					lay-verify="required" placeholder="任务类名首字母小写 " autocomplete="off"
					class="layui-input">
			</div>


			<label class="layui-form-label">任务描述</label>
			<div class="layui-input-inline">
				<input type="text" id="taskDesc" name="scheduledTask.taskDesc"
					lay-verify="required" placeholder="请输入任务描述" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">任务时间表达式</label>
			<div class="layui-input-inline">
				<input type="text" id="taskCron" name="scheduledTask.taskCron"
					lay-verify="required" placeholder="格式： 0/5 * * * * ?"autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">开机启动</label>
			<div class="layui-input-inline">
				<select   name="scheduledTask.initStartFlag" autocomplete="off" id="initStartFlag" lay-verify="required" lay-filter="city">
					<option value=""></option>
					<option value="1">启动</option>
					<option value="0">不启动</option>
				</select>
			</div>
		</div>

		
		<div class="layui-form-item">
			<span style="margin-left:30px;color:red"> 
				例:</br>
				&emsp;&emsp;&emsp;0 0 12 * * ?&ensp;&emsp;&emsp;&emsp;&emsp;每天12点触发<br/> 
				&emsp;&emsp;&emsp;0 15 10 ? * *&emsp;&emsp;&emsp;&emsp;每天10点15分触发 <br/>
				&emsp;&emsp;&emsp;0 15 10 * * ?&emsp;&emsp;&emsp;&emsp;每天10点15分触发  <br/>
				&emsp;&emsp;&emsp;0 15 10 * * ? * &emsp;&emsp;&emsp;每天10点15分触发 <br/>
				&emsp;&emsp;&emsp;0 15 10 * * ? 2005 &emsp;2005年每天10点15分触发
			</span>
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
	
  });
 function child(obj){
		 
   var cityOrg = JSON.parse(obj);
	$("#id").val(cityOrg["id"]);
	for (var index in cityOrg){
	 $("#"+index).val(cityOrg[index]);
	}
    var initStartFlag=cityOrg.initStartFlag;
    $("#initStartFlag").find("option[value ='"+initStartFlag+"']").attr("selected","selected");
    form.render('select');
		  
}

  </script>
 
</body>
</html>
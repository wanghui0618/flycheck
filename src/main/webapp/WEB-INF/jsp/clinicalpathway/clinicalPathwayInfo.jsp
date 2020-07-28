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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor_4.9.2/ckeditor.js"></script> <!--载入fckeditor类-->
<title>临床路径</title>
<style type="text/css">
.layui-form-label {
    width: 60px;
    text-align: center;
}
.layui-input, .layui-textarea {
    width: 650px;
}
.cke_contents cke_reset{
height: 300px;
}
</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="clinicalPath.id" id="id">
		<input type="hidden" name="clinicalPath.content" id="contentHidden">

		<div class="layui-form-item">
			<label class="layui-form-label">名称</label>
			<div class="layui-input-inline">
				<input type="text" id="name" name="clinicalPath.name"
					lay-verify="required" placeholder="请输入临床路径名称"autocomplete="off"
					class="layui-input ">
			</div>	
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">版本</label>
			<div class="layui-input-inline">
				<input type="text" id="edition" name="clinicalPath.edition"
					lay-verify="required" placeholder="请输入临床路径版本"  autocomplete="off"
					class="layui-input ">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">编号</label>
			<div class="layui-input-inline">
				<input type="text" id="code" name="clinicalPath.code"
					lay-verify="required" placeholder="请输入临床路径编号" autocomplete="off"
					class="layui-input ">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-inline" style="width:650px;margin-left:68px">
				<!-- <textarea id="content"  name="clinicalPath.content" class="layui-textarea pt" style="display: none;" lay-verify="article_desc"></textarea> -->
				<textarea id="TextArea" lay-verify="article_desc" name="TextArea" cols="20" rows="2" class="ckeditor"></textarea>
			</div>
		</div>

		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityprice-front-submit"
				id="LAY-cityprice-front-submit" value="确认">
		</div>
	</div>

<script>
var neirong;
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form','laydate','layedit'], function(){
	  var form=layui.form;
	  var layedit = layui.layedit;
	  var index =layedit.build('content', {
		  height: 200 //设置编辑器高度
	  });
	  form.verify({
		  article_desc: function(value){
			   var content = CKEDITOR.instances.TextArea.getData();
	           $("#contentHidden").val(content);
		       return layedit.sync(index);
		    }
	 });
     CKEDITOR.instances.TextArea.setData(neirong);
     
})
function child(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#id").val(cityOrg["id"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
	  neirong = cityOrg["content"];
	  /* var body = cityOrg.content;
	     layedit.setContent(index,body); */
}
  </script>
</body>
</html>
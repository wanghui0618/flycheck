<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor_4.9.2/ckeditor.js"></script> <!--载入fckeditor类-->
<title>临床指南添加</title>
<style>
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
<body style="overflow: hidden;">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 10px 0 0 0;">
       <input type="hidden" name="clinicalGuideline.id" id="id" hide=true>
       <input type="hidden" name="clinicalGuideline.content" id="contentHidden">
             <div class="layui-form-item">
                <div class="layui-inline">
                   <label class="layui-form-label">名称</label>
                   <div class="layui-input-inline">
                      <input type="text" id="name"name="clinicalGuideline.name" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                 </div>
              </div>
                <div class="layui-form-item">
                 <div class="layui-inline">
                  <label class="layui-form-label">来源</label>
                  <div class="layui-input-inline">
                  <input type="text" id="source" name="clinicalGuideline.source" autocomplete="off" lay-verify="upload" class="layui-input" lay-verify="required">
                  </div>
                 </div>
                </div>
 		        <div class="layui-form-item">
			   <div class="layui-input-inline" style="width:650px;margin-left:68px">
				<textarea id="TextArea" lay-verify="article_desc" name="TextArea" cols="20" rows="2" class="ckeditor"></textarea>
			  </div>
	        	</div>
                   <div class="layui-form-item layui-hide" style="align:center">
                   <input type="button" lay-submit lay-filter="LAY-org-front-submit" id="LAY-org-front-submit" value="确认">
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
		  height: 300 //设置编辑器高度
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
	  neirong = cityOrg["content"];
	  $("#id").val(cityOrg["id"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
}
  </script>
</body>
</html>
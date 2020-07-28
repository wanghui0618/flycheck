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
<title>合理用药信息新增/修改页面</title>
<style>
.layui-textarea{
	width:98%;
}
</style>
</head>
<body >
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="druguse.id">
     <div class="layui-form-item">
          <label class="layui-form-label">药品大类：</label>
           <div class="layui-input-inline" style="width:250px;">
             <input type="text" id="drugbig" name="druguse.drugbig" lay-verify="required" placeholder="请填写药品大类" autocomplete="off" maxlength="15" class="layui-input">
           </div>
     </div>
      <div class="layui-form-item">
      		<label class="layui-form-label">药品小类：</label>
      			<div class="layui-input-inline" style="width:250px;">
        			<input type="text" id="drugsmall" name="druguse.drugsmall" lay-verify="required" placeholder="请填写药品小类" autocomplete="off" maxlength="15" class="layui-input">
      			</div>
      </div>
        <div class="layui-form-item">
     		 <label class="layui-form-label">药品名称：</label>
     			 <div class="layui-input-inline" style="width:250px;">
        			<input type="text" id="drugName" name="druguse.drugName" lay-verify="required" placeholder="请填写药品名称" autocomplete="off" maxlength="15" class="layui-input">
      			</div>
   		 </div>
    	<div class="layui-form-item">
      		<label class="layui-form-label">英文名称：</label>
      		<div class="layui-input-inline" style="width:250px;">
        		<input type="text" id="englishname" name="druguse.englishname" lay-verify="required" placeholder="请填写英文名称" autocomplete="off" maxlength="15" class="layui-input">
      		</div>
   	    </div>
        <div class="layui-form-item">
     		 <label class="layui-form-label">适应症：</label>
      			<div class="layui-input-block">
      			<textarea  name="druguse.adaptation" placeholder="" class="layui-textarea"></textarea>
      			</div>
    	</div>
        <div class="layui-form-item">
     		 <label class="layui-form-label">用法用量：</label>
     			 <div class="layui-input-block">
     			 <textarea  name="druguse.usage" placeholder="" class="layui-textarea"></textarea>
     			 </div>
    	</div>
        <div class="layui-form-item">
      		<label class="layui-form-label">药理学：</label>
     		 <div class="layui-input-block">
     		 <textarea  name="druguse.pharmacology" placeholder="" class="layui-textarea"></textarea>
     		 </div>
   		 </div>
        <div class="layui-form-item">
     		 <label class="layui-form-label">药动学：</label>
      			<div class="layui-input-block">
      			<textarea  name="druguse.pharmacokinetics" placeholder="" class="layui-textarea"></textarea>
      			</div>
    	</div>
        <div class="layui-form-item">
     		 <label class="layui-form-label">不良反应：</label>
      			<div class="layui-input-block">
      			<textarea  name="druguse.untowardEffect" placeholder="" class="layui-textarea"></textarea>
      			</div>
   		 </div>
    <div class="layui-form-item layui-hide" style="align:center">
      <input type="button" lay-submit lay-filter="LAY-org-front-submit" id="LAY-org-front-submit" value="确认">
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
</body>
</html>
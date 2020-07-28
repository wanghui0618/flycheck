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
<style>
.layui-form-select dl {
     max-height:160px; 
}
.layui-form-label {
    width: 100px;
}
.layui-form-item {
    margin-left: 60px;
}
.layui-input-block {
    margin-left: 130px;
}
.layui-form-select .layui-edge {
    right: 20px;
}
</style>
    
<title>业务信息新增页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="code.id">
       <br>
       <div class="layui-form-item">
       		<label class="layui-form-label">业务编码</label>
        	<div class="layui-input-inline" style="width: 200px;">
	        	<input type="text" id="businessId" name="code.businessId" lay-verify="required" placeholder="请填写业务编码" autocomplete="off" maxlength="15" class="layui-input">
	        </div>
      </div>
      <br>
      <div class="layui-form-item">
	      <label class="layui-form-label">业务类别代码</label>
	      <div class="layui-input-inline" style="width: 200px;">
	        <input type="text" id="businessTypeCode" name="code.businessTypeCode" lay-verify="required"   placeholder="请填写业务类别代码" autocomplete="off" maxlength="10" class="layui-input">
	      </div>
	  </div>
      <br>
      <div class="layui-form-item">
		<label class="layui-form-label ">业务类别</label>
		<div class="layui-input-inline layui-form-selectup" style="width: 200px;">
			<select name="code.businessType" id="businessType" style="height: 10px;">
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
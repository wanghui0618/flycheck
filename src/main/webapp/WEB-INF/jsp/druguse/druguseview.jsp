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
label{
	font-size:15px;
	color:#353535;
	line-height: 28px;
}
html{
	background-color:#fff;
}
</style>
</head>
<body >
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="druguse.id">
		<div class="layui-form-item">
     		 <label style="color:#1D9A04;margin-left:6%">药品名称：</label>
     		  <label id="drugName" style="color:#F01313;margin-left:8px;"></label>
   		</div>
   		<center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
     <div class="layui-form-item">
             <label style="color:#1D9A04;margin-left:6%">药品大类：</label>
             <label  id="drugbig" style="margin-left:8px;"></label>
     </div>
      <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
      <div class="layui-form-item">
      		<label style="color:#1D9A04;margin-left:6%">药品小类：</label>
      		 <label id="drugsmall" style="margin-left:8px;"></label>
      </div>
        <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
    	<div class="layui-form-item">
      		<label style="color:#1D9A04;margin-left:6%">英文名称：</label>
      		 <label id="englishname" style="margin-left:8px;"></label>
   	    </div>
      <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
        <div class="layui-form-item">
     		 <label style="color:#1D9A04;margin-left:6%;float: left;">&nbsp;&nbsp;&nbsp;&nbsp;适应症：</label>
     		  <label id="adaptation" style="margin-left:8px;width: 80%;display: block;float: left;"></label>
    	</div>
      <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
        <div class="layui-form-item">
     		 <label style="color:#1D9A04;margin-left:6%;float: left;">用法用量：</label>
     		  <label id="usage" style="margin-left:8px;width: 80%;display: block;float: left;"></label>
    	</div>
     <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
        <div class="layui-form-item">
      		<label style="color:#1D9A04;margin-left:6%;float: left;">&nbsp;&nbsp;&nbsp;&nbsp;药理学：</label>
      		 <label id="pharmacology" style="margin-left:8px;width: 80%;display: block;float: left;"></label>
   		 </div>
      <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
        <div class="layui-form-item">
     		 <label style="color:#1D9A04;margin-left:6%;float: left;">&nbsp;&nbsp;&nbsp;&nbsp;药动学：</label>
     		  <label id="pharmacokinetics" style="margin-left:8px;width: 80%;display: block;float: left;"></label>
    	</div>
        <center><hr width="90%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center> 
        <div class="layui-form-item">
     		 <label style="color:#1D9A04;margin-left:6%;float: left;">不良反应：</label>
     		  <label id="untowardEffect" style="margin-left:8px;width: 80%;display: block;float: left;"></label>
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
	  
	    /* var id=$('#id').val();
		//页面一出来就会执行
	    $.get($WEB_ROOT_PATH+"/dhccApi/druguse/druguse/findById",
				{'drugPrice.id':id},function(data){
					var  field =data.druguse;
					$("#adaptation").val(field.adaptation);
					$("#usage").val(field.usage);
					$("#pharmacology").val(field.pharmacology);
					$("#pharmacokinetics").val(field.pharmacokinetics);
					$("#untowardEffect").val(field.untowardEffect);
		},"json"); */
	 
  })
  function child(obj){
	  var org = JSON.parse(obj);
	  for (var index in org){
	      $("#"+index).text(org[index]);
	  }
  }
  </script>
</body>
</html>
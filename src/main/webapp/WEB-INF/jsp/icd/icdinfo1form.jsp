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

<title>药品新增/修改</title>
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    
    <input type="hidden"  name="drugLnstmction.id" id="id">
   		
   	   <div class="layui-form-item">
      	<label class="layui-form-label">药品名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="name" name="drugLnstmction.name" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
   		
      <div class="layui-form-item">
      	<label class="layui-form-label">药品英文名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="enName" name="drugLnstmction.enName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
          
         <div class="layui-form-item">
      	<label class="layui-form-label">用途成分</label>
      	<div class="layui-input-inline">
        	<input type="text" id="classificationUses" name="drugLnstmction.classificationUses" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
       
          <div class="layui-form-item">
      	<label class="layui-form-label">主要成分</label>
      	<div class="layui-input-inline">
        	<input type="text" id="mainIngredients" name="drugLnstmction.mainIngredients" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
      
      
      
      <div class="layui-form-item">
      	<label class="layui-form-label">用途</label>
      	<div class="layui-input-inline">
        	<input type="text" id="use" name="drugLnstmction.use" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
      
      <div class="layui-form-item">
      	<label class="layui-form-label">用法用量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="dosage" name="drugLnstmction.dosage" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
      <div class="layui-form-item">
      	<label class="layui-form-label">产品说明</label>
      	<div class="layui-input-inline">
        	<input type="text" id="product" name="drugLnstmction.product" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
       
      
    
      
      		<div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
 	   </div>
  <%-- <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictmaintain/dictmaintainform.js"></script> --%>
  <script>
  
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index'   //主入口模块
	  }).use(['index', 'form'], function(){})
  
  function child(obj){
	  var druglnstmction = JSON.parse(obj);
	  $("#id").val(druglnstmction["id"]);
	  for (var index in druglnstmction){
	      $("#"+index).val(druglnstmction[index]);
	  }
  }
  

  </script>
</body>
</html>
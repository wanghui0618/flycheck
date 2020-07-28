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

<title>明细单</title>

</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    
    <input type="hidden" id="id" name="medicalDetail.id" >
   		
   		<div class="layui-form-item">
      	<label class="layui-form-label">收费单据号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="billingNo" name="medicalDetail.billingNo" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  </div>
      
      <div class="layui-form-item">
      	<label class="layui-form-label">项目编号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemCode" name="medicalDetail.itemCode"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
     	 <label class="layui-form-label">项目名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemName" name="medicalDetail.itemName"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
      </div>
      
      <div class="layui-form-item">
      	<label class="layui-form-label">项目规格</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemStandard" name="medicalDetail.itemStandard"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      
      	<label class="layui-form-label">药品类别</label>
      	<div class="layui-input-inline">
        	<input type="text" id="drugType" name="medicalDetail.drugType"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
      </div>
   		
   		<div class="layui-form-item">
      	<label class="layui-form-label">项目单价</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemPrice" name="medicalDetail.itemPrice"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
   		
      	<label class="layui-form-label">项目数量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemQuantity" name="medicalDetail.itemQuantity"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         
         	<div class="layui-form-item">
      	<label class="layui-form-label">项目金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemCost" name="medicalDetail.itemCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div>
      
      <div class="layui-form-item">
      
      <label class="layui-form-label">t_piccbid_medical_id</label>
      	<div class="layui-input-inline">
        	<input type="text" id="tPiccbidMedicalId" name="medicalDetail.tPiccbidMedicalId"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      
          </div>
      
      		<div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
 	   </div>
  <script>
  
  <%-- layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form'], function(){})
   --%>
  
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form',], function(){
				})  
				
				
  
  function child(obj){
	  var medicalDetail = JSON.parse(obj);
	  $("#id").val(medicalDetail["id"]);
	  for (var index in medicalDetail){
	      $("#"+index).val(medicalDetail[index]);
	  }
  }
  </script>
</body>
</html>
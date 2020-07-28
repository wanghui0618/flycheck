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

<title>药品说明书</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
      
            
      
          
        <div class="layui-card-body">
        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
         <script type="text/html" id="table-useradmin-webuser">
        </script>
      </div>
     
    </div>
  </div>
  </div>
 	   
     	
     	 <!-- <label class="layui-form-label">用法用量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="dosage" name="drugLnstmction.dosage" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">产品说明</label>
      	<div class="layui-input-inline">
        	<input type="text" id="product" name="drugLnstmction.product" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div> -->
 	    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/icd/icdform.js"></script>
  <script>
  
  var id;
  function child(obj){
	  var druglnstmction = JSON.parse(obj);
	 id=druglnstmction.id;
  }
  

  </script>
</body>
</html>
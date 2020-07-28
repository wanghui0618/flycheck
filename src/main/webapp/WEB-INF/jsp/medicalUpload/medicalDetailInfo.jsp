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

<title>用户管理</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
      
            
      <div class="layui-form-item">
         	<div class="layui-inline">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-block">
              <input type="text" name="medicalDetail.itemName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
      
         	<div class="layui-inline">
            <label class="layui-form-label">项目编号</label>
            <div class="layui-input-block">
              <input type="text" name="medicalDetail.itemCode" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
       <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin"  lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
            <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">添加</button>
          </div>
      
      
        <div class="layui-card-body">
        <table id="medicalTable" class="layui-hide" lay-filter="medicalTable"></table>
         <script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="editinfo"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delinfo"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
     
    </div>
  </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/medical/medicalDetailInfo.js"></script>
  <script>
  var t_piccbid_medical_id,billing_no,medical_id;
 
  function child(obj){
	  var medicalDetail = JSON.parse(obj);
	  t_piccbid_medical_id = medicalDetail.id;
	  billing_no=medicalDetail.billingNo;
	  medical_id=medicalDetail.medicalId;
  }
  </script>
</body>
</html>
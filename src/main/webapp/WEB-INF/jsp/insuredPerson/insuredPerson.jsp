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

<title>参保人员信息管理</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">

      <div class="layui-form layui-card-header layuiadmin-card-header-auto">    
        <div class="layui-form-item" >
           <!--    <div class="layui-inline ">
						<label class="layui-form-label " >城市名称</label>
						<div class="layui-input-block " style="width:160px;" >
							<select name="insuredPerson.cityCode" id="city"  lay-search="">
								<option value="" disabled selected style='display:none;'>请选择城市</option>
							</select>
						</div>
					</div> -->
        
          <div class="layui-inline" >
            <label class="layui-form-label" style="width:55px;">参保号</label>
            <div class="layui-input-inline">
              <input type="text" name="insuredPerson.insuranceCode" placeholder="请输入参保号" autocomplete="off" class="layui-input">
            </div>
          </div>
         
          
            <div class="layui-inline" > 
              <label class="layui-form-label" >身份证号</label>
              <div class="layui-input-inline">
              <input type="text" name="insuredPerson.idCard" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
               </div>
             </div>
             
    
  
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" stylename="search"  lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
            <button id="insuredPerson-add"   class="layui-btn layuiadmin-btn-useradmin" stylename="add" 
            data-type="add"> <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>
              新增</button>
          </div>
          
      </div>
      
   </div>
         
      <div class="layui-card-body">

        <table id="personTable" class="layui-hide" lay-filter="personTable"></table>
        <script type="text/html" id="table-useradmin-webuser">
{{#if (!existsButton('insuredPerson-edit')) { }}
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i class="layui-icon layui-icon-edit"></i>修改</a>
{{# } }}
{{#if (!existsButton('insuredPerson-delete')) { }}          
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i class="layui-icon layui-icon-delete"></i>删除</a>
{{# } }}       
 </script>
      </div>
    
  </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/insuredPerson/insuredPerson.js"></script>
</body>
</html>
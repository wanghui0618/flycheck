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
<title>联合手术项目审核</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">

      <div class="layui-form layui-card-header layuiadmin-card-header-auto">    
        <div class="layui-form-item" >
            <div class="layui-inline">
						<label class="layui-form-label " >城市名称</label>
						<div class="layui-input-inline">
							<select name="jointOper.cityCode" id="city"  lay-search="">
								<option value="" disabled selected style='display:none;'>请选择城市</option>
							</select>
						</div>
					</div>
          <div class="layui-inline" >
            <label class="layui-form-label">目录编码</label>
            <div class="layui-input-inline">
              <input type="text" name="jointOper.itemCode" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
         
          
            <div class="layui-inline" > 
              <label class="layui-form-label" >目录名称</label>
              <div class="layui-input-inline">
              <input type="text" style="width:160px;" name="jointOper.itemName" placeholder="请输入" autocomplete="off" class="layui-input">
               </div>
             </div>
             
      
          
          <div class="layui-inline">
            <button id="treatmentRule-search"  stylename="search"  class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
            <button id="treatmentRule-add" class="layui-btn layuiadmin-btn-useradmin" stylename="add" data-type="add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>新增</button>
          </div>
          
      </div>
      
   </div>
         
      <div class="layui-card-body">

        <table id="drugruleTable" class="layui-hide" lay-filter="drugruleTable"></table>
        <script type="text/html" id="table-useradmin-treatment">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i class="layui-icon layui-icon-edit"></i>维护</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
    
  </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/matchCheck/jointOper.js"></script>
</body>
</html>
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

<title>诚信管理</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">

      <div class="layui-form layui-card-header layuiadmin-card-header-auto">    
        <div class="layui-form-item" >
        			<div class="layui-inline ">
						<label class="layui-form-label ">城市名称</label>
						<div class="layui-input-inline" >
							<select name="blackList.cityCode" id="city" lay-search="">
								<option value="" disabled selected style='display: none;'>请选择城市</option>
							</select>
						</div>
					</div>
              <div class="layui-inline ">
				<label class="layui-form-label" style="width:45px">类型</label>
				<div class="layui-input-inline">
					<select name="blackList.type"" id="type" >
						<option value="" >请选择类型</option>
						<option value="1">参保人</option>
						<option value="2">医院</option>
						<option value="3">药店</option>
					</select>
				</div>
			</div>
        <div class="layui-inline" >
            <label class="layui-form-label" style="width:45px">名称</label>
            <div class="layui-input-inline">
              <input type="text"  name="blackList.name" placeholder="请输入名称" autocomplete="off" class="layui-input">
            </div>
          </div>
          
            <div class="layui-inline" style="width: 380px;"> 
              <label class="layui-form-label" style="width: 180px;">身份证号/机构代码/药店编号</label>
              <div class="layui-input-block">
              <input type="text" name="blackList.code" placeholder="请输入" autocomplete="off" class="layui-input">
               </div>
             </div>
             
    
  
          <div class="layui-inline">
            <button id="blackList-search" class="layui-btn layuiadmin-btn-useradmin"  stylename="search"  lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
            <button id="blackList-add" class="layui-btn layuiadmin-btn-useradmin" data-type="add" stylename="add" ><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>新增</button>
          </div>
          
      </div>
      
   </div>
         
      <div class="layui-card-body">

        <table id="blackList" class="layui-hide" lay-filter="blackList"></table>
        <script type="text/html" id="table-useradmin-webuser">
{{#if (!existsButton('blackList-view')) { }}
		  <a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>查看</a>
	 {{# } }}
{{#if (!existsButton('blackList-update')) { }}  
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i class="layui-icon layui-icon-edit"></i>修改</a>
	 {{# } }}
{{#if (!existsButton('blackList-delete')) { }}
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i class="layui-icon layui-icon-delete"></i>删除</a>
	 {{# } }}       
       </script>
      </div>
    
  </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/blackList/blackList.js"></script>
</body>
</html>
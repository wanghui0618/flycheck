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
<title>知识库编辑</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">

      <div class="layui-form layui-card-header layuiadmin-card-header-auto">    
        <div class="layui-form-item" >
              <div class="layui-inline ">
              <input type="hidden" name="knowledge.typeSmall" id="typeSmall">
              <input type="hidden" name="inflag" id="inflag">
        <div class="layui-inline" >
            <label class="layui-form-label" style="width:45px">标题</label>
            <div class="layui-input-block" style="margin-left:55px">
              <input type="text" name="knowledge.title" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
          </div>
          
            <div class="layui-inline" > 
              <label class="layui-form-label" style="width:55px">关键字</label>
              <div class="layui-input-block" style="margin-left:65px">
              <input type="text" name="knowledge.keywords" placeholder="请输入关键字" autocomplete="off" class="layui-input">
               </div>
             </div>
             
    
  
          
          <div class="layui-inline">
                  <button id="search" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search" onclick="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
            <button id="knowledge-add" class="layui-btn layuiadmin-btn-useradmin layui-icon-add" data-type="add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>添加</button>
          </div> 
      </div>
      
   </div>
        </div>   
      <div class="layui-card-body">

        <table id="knowledge" class="layui-hide" lay-filter="knowledge"></table>
        <script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>预览</a>
{{#if (!existsButton('knowledge-edit')) { }}
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i class="layui-icon layui-icon-edit"></i>修改</a>
{{# } }}
{{#if (!existsButton('knowledge-delete')) { }}      
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i class="layui-icon layui-icon-delete"></i>删除</a>
{{# } }}
        </script>
      </div>
  </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/knowledge/knowledgeInfo.js"></script>
</body>
</html>
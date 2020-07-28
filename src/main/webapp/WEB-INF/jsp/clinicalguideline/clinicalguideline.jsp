<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <title>上传</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline pt">
                    <label class="layui-form-label" style="width:100px">临床指南名称</label>
                    <div class="layui-input-block" style="margin-left:110px">
                        <input type="text"
                               name="clinicalGuideline.name" placeholder="请输入名称"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                            lay-filter="LAY-user-front-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                </div>
                <div class="layui-inline">
                    <button id='clinicalguideline-add' class="layui-btn layui-icon-add  layuiadmin-btn-useradmin" data-type="add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>添加</button></button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">

            <table id="clinicalGuidelineTable" class="layui-hide"
                   lay-filter="clinicalGuidelineTable"></table>

            <script type="text/html" id="table-useradmin-webuser">
              {{#if (!existsButton('clinicalguideline-view')) { }} 
               <a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>预览</a>
               {{# } }}               
              {{#if (!existsButton('clinicalguideline-update')) { }}              
               <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
              {{# } }}                
               {{#if (!existsButton('clinicalguideline-delete')) { }}               
               <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
               {{# } }}             
               </script>
        </div>
    </div>
</div>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/clinicalguideline/clinicalguideline.js"></script>
</body>
</html>
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
<style>
.layui-table-page{
    height: 38px;
}
</style>
<title>诊疗规则管理</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item" >

                <div class="layui-inline" >
                    <label class="layui-form-label" >业务表名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="quality.tableName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>


                <div class="layui-inline" >
                    <label class="layui-form-label" >表字段名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="quality.tableColName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label "  style="width:45px;">类型</label>
                    <div class="layui-input-inline">
                        <input type="text" name="quality.type" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-quality-front-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                    <button class="layui-btn layui-icon-add  layuiadmin-btn-useradmin" data-type="add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>新增</button>
                </div>

            </div>

        </div>

        <div class="layui-card-body">

            <table id="qualityTable" class="layui-hide" lay-filter="qualityTable"></table>
            <script type="text/html" id="table-useradmin-quality">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="change"><i class="layui-icon layui-icon-edit"></i>修改</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
            <%--<script type="text/html" id="table-useradmin-treatment1">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i class="layui-icon layui-icon-form"></i>规则维护</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i class="layui-icon layui-icon-file"></i>清除规则</a>
            </script>--%>

        </div>

    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/quality/quality.js"></script>
</body>
</html>
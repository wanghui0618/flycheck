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
    <title>质量标准新增/修改页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <input type="hidden" id="id" name="quality.id">
    <br>
    <div class="layui-form-item">

        <label class="layui-form-label">业务表名</label>
        <div class="layui-input-inline">
            <input type="text" id="tableName" name="quality.tableName" lay-verify="required" placeholder="请填写业务表名" autocomplete="off" maxlength="100" class="layui-input">
        </div>
        <label class="layui-form-label">业务表字段</label>
        <div class="layui-input-inline">
            <input type="text" id="tableCol" name="quality.tableCol" lay-verify="required"   placeholder="请填写业务表字段" autocomplete="off" maxlength="50" class="layui-input">
        </div>
    </div>
    <br>
    <div class="layui-form-item">
        <label class="layui-form-label">表字段名</label>
        <div class="layui-input-inline">
            <input type="text" id="tableColName" name="quality.tableColName" lay-verify="required"   placeholder="请填写表字段名" autocomplete="off" maxlength="100" class="layui-input">
        </div>
        <label class="layui-form-label">类型</label>
        <div class="layui-input-inline">
            <input type="text" id="type" name="quality.type"    placeholder="请填写类型" autocomplete="off" maxlength="50" class="layui-input">
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="layui-form-item layui-hide" style="align:center">
        <input type="button" lay-submit lay-filter="LAY-quality-front-submit" id="LAY-quality-front-submit" value="确认">
    </div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/quality/quality.js"></script>
</html>

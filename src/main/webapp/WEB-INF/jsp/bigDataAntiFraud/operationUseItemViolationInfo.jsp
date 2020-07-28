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
    <title>疾病使用项目违规查询详情页</title>
</head>
<body style="overflow:hidden">
<%--<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">--%>
    <%--<br>--%>
    <%--<div class="layui-card-body" >--%>

        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>

    <%--</div>--%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/bigDataAntiFraud/operationUseItemViolationInfo.js"></script>
<%--</div>--%>



</body>
</html>
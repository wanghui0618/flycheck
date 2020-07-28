<%--
  Created by IntelliJ IDEA.
  User: WT
  Date: 2019/10/17
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
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
    <title>按病种统计分析</title>
</head>
<body>

    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-body">
                <table id="diseaseStatistics" lay-filter="diseaseStatistics"></table>
            </div>
        </div>
    </div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/diseaseStatistics/diseaseStatistics.js"></script>
</body>

</html>

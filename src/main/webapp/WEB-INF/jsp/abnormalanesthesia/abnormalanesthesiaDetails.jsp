<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <title>住院明细</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <table id="decompositionTable" class="layui-hide"
                   lay-filter="decompositionTable">
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/abnormalanesthesia/abnormalanesthesiaDetails.js"></script>
</body>
</html>

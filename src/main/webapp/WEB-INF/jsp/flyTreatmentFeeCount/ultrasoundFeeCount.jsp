<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <title>超声检查收费金额次数统计</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
<p>近两年超声检查收费金额次数统计:</p>
        </div>
        <div class="layui-card-body">
            <table id="flyTreatmentFeeCountStatisticsTable" class="layui-hide"
                   lay-filter="flyTreatmentFeeCountStatisticsTable">
            </table>
        </div>
    </div>
    </div>
        <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/flyTreatmentFeeCount/ultrasoundFeeCount.js"></script>
</body>
</html>

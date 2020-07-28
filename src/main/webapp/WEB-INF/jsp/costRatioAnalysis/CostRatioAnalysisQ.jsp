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
    <title>费用分析页面</title>
</head>
<body>


    <div class="layui-card" id="tx">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto" >
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <select id="Detail" lay-filter="Detail">
                                <option value="Inhos"  selected>住院明细</option>
                                <option value="menz">门诊明细</option>
                            </select>
                        </div>
                    </div>
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search" onclick="search()">查询</button>
                </div>
        </div>
        <div class="layui-card-body" >
            <table id="dg" lay-filter="dg"></table>
        </div>
    </div>


<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/costRatioAnalysis/CostRatioAnalysis.js"></script>
</body>
</html>

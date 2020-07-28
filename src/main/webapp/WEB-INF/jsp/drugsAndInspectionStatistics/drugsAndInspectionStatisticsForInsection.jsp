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
    <%--    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/UserHistoryin.css" media="all">--%>
    <title>检查项目数量频次统计</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">选择年份</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="chooseYear" name="chooseYear" placeholder="选择年份">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">选择月份</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="chooseMonth" id="chooseMonth" placeholder="选择月份">
                    </div>
                </div>
                <%--                <div class="layui-inline">--%>
                <%--                    <div class="layui-form-item">--%>
                <%--                        <div class="layui-inline" style="width: 75px;">--%>
                <%--                            <input type="radio" name="sex" value="男" title="男">--%>
                <%--                        </div>--%>
                <%--                        <div class="layui-inline" style="width: 75px;">--%>
                <%--                            <input type="radio" name="sex" value="女" title="女" checked>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                </div>--%>
                 <div class="layui-inline" style="width: 310px;">
                     <label class="layui-form-label" style="width: 100px;">检查项目名称</label>
                     <div class="layui-input-block">
                         <input type="text" name="drugsAndInspectionStatisticsForInsection.itemNameHos" placeholder="请输入" autocomplete="off" class="layui-input">
                     </div>
                 </div>

                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                </div>

            </div>


        </div>
        <div class="layui-card-body">
            <table id="drugsAndInspectionStatisticsForInsectionTable" class="layui-hide" lay-filter="drugsAndInspectionStatisticsForInsectionTable"></table>
            <script type="text/html" id="table-useradmin-webuser">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="costDetail"><i class="layui-icon "></i>检查费用明细</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="personInfo"><i class="layui-icon"></i>参保人员信息</a>
            </script>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/drugsAndInspectionStatistics/drugsAndInspectionStatisticsForInsection.js"></script>
</body>
</html>
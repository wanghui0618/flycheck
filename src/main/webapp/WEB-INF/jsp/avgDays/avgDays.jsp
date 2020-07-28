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

    <title>平均住院天数</title>
</head>
<!-- <style type="text/css">
 .layui-form-item .layui-inline {
    margin-bottom: 5px;
    margin-right: 10px;
    padding-right: 20px;
}
</style> -->
<body>
<div class="layui-fluid">
    <div class="layui-card">

        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item" >

                <%--<div class="layui-inline" >--%>
                    <%--<label class="layui-form-label" >医疗机构</label>--%>
                    <%--<div class="layui-input-block">--%>
                        <%--<input type="text" style="width:160px;" name="avgDays.orgName" placeholder="请输入" autocomplete="off" class="layui-input">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="layui-inline">
                    <label class="layui-form-label">医疗机构</label>
                    <div class="layui-input-block" >
                        <select id="zyOrgName" name="avgDays.orgName" lay-verify="" lay-search=" ">
                            <option value="" disabled selected style='display:none;'>请选择</option>
                        </select>
                    </div>
                    <!-- <div class="layui-input-block" >
                        <input type="text" name="name" lay-verify=""autocomplete="off" placeholder="请输入机构名" class="layui-input" >
                    </div> -->

                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">年范围</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="avgDays.year" id="test7" placeholder=" - " readonly="true">
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-avgDays-front-search">

                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                        查询
                    </button>
                </div>

            </div>

        </div>

        <div class="layui-card-body">

            <table id="avgDaysTable" class="layui-hide" lay-filter="avgDaysTable"></table>

            <%--<script type="text/html" id="table-useradmin-treatment1">--%>
            <%--<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i class="layui-icon layui-icon-form"></i>规则维护</a>--%>
            <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i class="layui-icon layui-icon-file"></i>清除规则</a>--%>
        <%--</script>--%>

        </div>

    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/avgDays/avgDays.js"></script>
</body>
</html>
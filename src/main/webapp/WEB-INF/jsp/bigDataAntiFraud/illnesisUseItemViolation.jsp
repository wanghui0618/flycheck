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

    <title>疾病使用项目违规查询</title>
    <style>
    .layui-tab-content{
    	font-size:14px;
    }
    </style>
</head>
<body>
<div class="layui-fluid">

    <div class="layui-card">
        <div class="layui-card-body" style="padding:0px">
            <div class="layui-tab layui-tab-card">
                <div class="layui-tab-content"  >
                    <!-- <div class="layui-tab-item layui-show" >
                        <a style="width:23%;font-size:1rem;display:inline-block;" >问题类型：疾病使用项目违规查询</a>
                        <a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">监管对象：患者</a>
                        <a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">发现时间：2018-01-15 18:59:01</a>
                    </div>
                    <div class="layui-tab-item layui-show" >
                        <a style="width:23%;font-size:1rem;display:inline-block;" >可疑总金额：<span id="allCost">87908.2</span>元&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a>
                        <a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">可疑报销金额：<span id="allCost1">67878.8</span></a>
                        <a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">就诊医院：第一人民医院</a>
                    </div> -->
                    <table style="width:60%">
                    	<tr>
                    		<td>监管对象：患者</td>
                    		<td>诊断：<span id="zhenduan"></span></td>
                    		<td>发现时间：2018-01-15</td>
                    	</tr>
                    	<tr>
                    		<td>就诊医院：第一人民医院</td>
                    		<td>可疑总金额：<span id="allCost">87908.2</span></td>
                    		<td>可疑报销金额：<span id="allCost1">67878.8</span></td>
                    	</tr>
                    </table>
                    <div class="layui-tab-item layui-show" id="illcontent"></div>

                    <%--<div class="layui-tab-item layui-show" >--%>
                        <%--<a style="width:23%;font-size:1rem;display:inline-block;">频繁度：14</a>--%>
                        <%--<a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">平均就医时间间隔：12</a>--%>
                        <%----%>
                    <%--</div>--%>
                    <%--<div class="layui-tab-item layui-show" >--%>
                        <%--<a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">涉及单位：******有限公司</a>--%>
                        <%--<a style="width:23%;line-height:40px;text-align:left;font-size:1rem;display:inline-block">涉及人员：刘旺，李旺，吴顺，王伟</a>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-card">

        <div class="layui-card-body">
            <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
            <script type="text/html" id="table-useradmin-webuser">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="operation"><i class="layui-icon layui-icon-operation"></i>查看详情</a>

            </script>
        </div>
    </div>


</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/bigDataAntiFraud/illnesisUseItemViolation.js"></script>
</body>
</html>
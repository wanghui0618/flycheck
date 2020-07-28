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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
    <title>疾病信息</title>
</head>
<body style="overflow:hidden">
    <table id="userTable" class="layui-hide" lay-filter="userTable"></table>

<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medical/medicalNumInfo.js"></script>
    <script>
        // var medicalName;
        //
        // function child(obj){
        //     var data = JSON.parse(obj);
        //     debugger;
        //     medicalName = data.medicalName;
        // }
    </script>
</body>
</html>
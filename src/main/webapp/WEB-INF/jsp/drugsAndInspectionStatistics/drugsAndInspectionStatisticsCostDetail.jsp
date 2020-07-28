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
    <title>药品检查项目数量频次统计</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <table id="drugsAndInspectionStatisticsTable" class="layui-hide" lay-filter="drugsAndInspectionStatisticsTable"></table>
        </div>
    </div>
</div>
<script>
    var parentMethodValue=parent.getMethodValue();
    var parentMethodValue2=parent.getMethodValue2();
    layui.config({
        base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'laydate'], function () {
        var $ = layui.$
            , form = layui.form
            , table = layui.table;
        var laydate = layui.laydate;
           console.log(parentMethodValue2)
        table.render({
            elem: '#drugsAndInspectionStatisticsTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/drugsAndInspectionStatistics/drugsAndInspectionStatistics/listForCostDetail?drugsAndInspectionStatistics.itemCodeIns='+parentMethodValue2.itemCodeIns+'&chooseYear='+parentMethodValue2.year+'&chooseMonth='+parentMethodValue2.month
            , cellMinWidth: 80
            , height: tableHeight
            , cols: [[
                  {type: 'numbers', width: 40, title: '编号'}
                , {field: 'name', align: 'center', title: '姓名'}
                , {field: 'billingNo', align: 'center', title: '单据号'}
                , {field: 'itemNameIns',width:250, align: 'center', title: '药品名称'}
                , {field: 'itemCodeIns',  align: 'center', title: '药品编码'}
                , {field: 'itemPrice',  align: 'center', title: '单价'}
                , {field: 'itemNum',  align: 'center', title: '数量'}
                , {field: 'itemCost',  align: 'center', title: '金额'}
                , {field: 'doseUnit',  align: 'center', title: '单位'}
            ]]
            , page: true
        });


        //按钮事件绑定底层方法-勿动
        $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

</script>
</body>
</html>
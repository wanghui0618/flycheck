<%--
  Created by IntelliJ IDEA.
  User: adam_ming
  Date: 2019-02-15
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算项目明细</title>
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <%
        String orgCode = request.getParameter("orgCode");
        String billingNo = request.getParameter("billingNo");
        String admissionNo = request.getParameter("admissionNo");
    %>
</head>
<body>
    <input id="orgCode" type="hidden" value="<%=orgCode%>">
    <input id="billingNo" type="hidden" value="<%=billingNo%>">
    <input id="admissionNo" type="hidden" value="<%=admissionNo%>">

    <table id="t2"></table>
</body>
<script>
    layui.use(['table'], function () {
        var table = layui.table;

        var orgCode = $('#orgCode').val();
        var billingNo = $('#billingNo').val();
        var admissionNo = $('#admissionNo').val();

        table.render({
            elem: '#t2',
            height: 490,
            url: $WEB_ROOT_PATH + "/dhccApi/duplicateCharge/listCostDetail",
            where: {
                'medicalRecord.orgCode': orgCode,
                'medicalRecord.billingNo': billingNo,
                'medicalRecord.admissionNo': admissionNo
            },
            page: true,
            loading: true,
            cellMinWidth: 100,
            cols: [[
                {field: 'itemCode', title: '项目代码', width: 180},
                {field: 'itemName', title: '项目名称', width: 200},
                {field: 'itemQuantity', title: '项目数量', align: 'right', style: 'background-color: #5FB878;'},
                {field: 'chargeUnit', title: '计价单位', width: 90, style: 'background-color: #5FB878;'},
                {field: 'itemPrice', title: '项目单价', width: 90, align: 'right'},
                {field: 'itemCost', title: '项目金额', width: 90, align: 'right'},
                {field: 'itemStandard', title: '项目规格', width: 120},
                {field: 'billingNo', title: '收费单据号', width: 120},
                {field: 'admissionNo', title: '住院号', width: 120},
                {field: 'orgCode', title: '机构代码', width: 120}
            ]],
            done: function (res, page, count) {
                /*var that = this.elem.next();
                console.log(that);
                res.data.forEach(function (item, index) {
                    if (item.needCheck === '1') {
                        that.find(".layui-table-box tbody tr[data-index='" + index + "']").css('background-color', '#FFA54F');
                    }
                });*/
            }
        });
    });
</script>
</html>

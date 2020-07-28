<%--
  Created by IntelliJ IDEA.
  User: adam_ming
  Date: 2019-02-14
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge">
    <title>重复收费</title>
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
</head>
<body>
    <table id="t1" lay-filter="t1-filter"></table>
</body>

<script>
    layui.use(['table', 'util', 'layer'], function () {
        var table = layui.table;
        var util = layui.util;
        var layer = layui.layer;

        layer.msg('请双击显示病案的结算项目～');

        table.render({
            elem: '#t1',
            height: 620,
            url: $WEB_ROOT_PATH + '/dhccApi/duplicateCharge/list', //数据接口
            page: true, //开启分页
            loading: true,
            limits: [10, 15, 20, 30, 50, 90],
            limit: 15,
            cols: [[ //表头
                {field: 'comments', title: '备注【违规说明】', width: 160, align: 'center'},
                {field: 'caseNo', title: '案件编号', width: 120, align: 'center'},
                {field: 'billingNo', title: '收费单据号', width: 120, align: 'center'},
                {field: 'admissionNo', title: '住院号', width: 120, align: 'center'},
                {field: 'orgCode', title: '机构代码', width: 120, align: 'center'},
                {
                    field: 'admissionDate', title: '入院日期', width: 180, align: 'center',
                    // templet: function (d) {
                    //     return util.toDateString(d.admissionDate);
                    // }
                },
                {
                    field: 'dischargeDate', title: '出院日期', width: 180, align: 'center',
                    // templet: function (d) {
                    //     return util.toDateString(d.dischargeDate);
                    // }
                },
                {field: 'stayLength', title: '住院天数', width: 120, align: 'center', style: 'background-color: #5FB878;'},
                {field: 'paymentDate', title: '费用发生日期', width: 120, align: 'center'},
                {field: 'reversalMark', title: '冲销标志', width: 120, align: 'center'},
                {field: 'idNo', title: '身份证号', width: 160, align: 'center'},
                {field: 'sscNo', title: '社保卡号', width: 140, align: 'center'},
                {field: 'patientName', title: '姓名', width: 80, align: 'center'},
                {field: 'sex', title: '性别', width: 80, align: 'center'},
                {field: 'age', title: '年龄', width: 80, align: ' center '},
                {field: 'admissionType', title: '住院类型', width: 100, align: 'center'},
                {field: 'medicalType', title: '医疗类型', width: 100, align: 'center'},
                {field: 'condition', title: '病情', width: 100, align: 'center'},
                {field: 'medicalService', title: '科室', width: 100, align: 'center'},
                {field: 'totalCost', title: '总金额', width: 100, align: 'center'},
                {field: 'fundCost', title: '基金支付金额', width: 120, align: 'center'},
                {field: 'sscAccountCost', title: '账户支付金额', width: 120, align: 'center'},
                {field: 'cashCost', title: '现金支付金额', width: 120, align: ' center '},
                {field: 'civilAffairSubsidy', title: '民政补助', width: 100, align: ' center '},
                {field: 'povertyAlleviationSubsidy', title: '扶贫补助', width: 100, align: ' center '},
                {field: 'financeSubsidy', title: '财政补助', width: 100, align: ' center '},
                {field: 'officialSubsidy', title: '公务员补助', width: 100, align: ' center '},
                {field: 'biFundCost', title: '商保基金支付', width: 120, align: ' center '},
                {field: 'biAccountCost', title: '商保帐户支付', width: 120, align: ' center '},
                {field: 'biCashCost', title: '商保现金支付', width: 120, align: ' center '},
                {field: 'sscSelfCost', title: '医保个人费用', width: 120, align: ' center '},
                {field: 'treatmentType', title: '待遇享受类别', width: 120, align: ' center '},
                {field: 'medicalTreatmentState', title: '医疗待遇状态', width: 120, align: ' center '},
                {field: 'dischargeState', title: '出院状态', width: 100, align: ' center '},
                {field: 'treatmentWay', title: '就诊方式', width: 100, align: ' center '},

            ]]
        });

        table.on('rowDouble(t1-filter)', function (obj) {
            var data = obj.data;

            layer.open({
                title: '结算项目明细【住院天数：' + data.stayLength + '】【违规：' + data.comments + '】',
                type: 2,
                area: ['1150px', '553px'],
                content: $WEB_ROOT_PATH + "/duplicateCharge/costDetail?orgCode=" + data.orgCode +
                    "&billingNo=" + data.billingNo + "&admissionNo=" + data.admissionNo,
                success: function (layero, index) {
                    // title 悬浮显示全部
                    $('div.layui-layer-title').attr('title', data.comments);
                }
            });
        });
    });
</script>
</html>

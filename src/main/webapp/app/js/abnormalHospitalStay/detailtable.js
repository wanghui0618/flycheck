
//初始化
var hisid="";
function initData(data) {
    hisid=data.hisid;
};
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','form'], function () {
    var  form = layui.form
        , table = layui.table
        ,laydate = layui.laydate;

    table.render({
        elem: '#detaileTable'
        ,url:$WEB_ROOT_PATH + '/dhccApi/abnormalHospitalStay/abnormalHospitalStay/detaileTable'
        ,height: tableHeight
        ,method:'POST'
        ,where:{"AbnormalHospitalStayVo.hisid":hisid}
        , cols: [
            [
                {type: 'numbers',title: '序号'}
                , {field: 'itemId', align: 'center', title: '医保项目编码',width:140}
                , {field: 'itemName', align: 'center', title: '医保项目名称',width:150}
                , {field: 'itemIdHosp', align: 'center', title: '医院项目编码',width:141}
                , {field: 'itemNameHosp', align: 'center', title: '医院项目名称',width:140}
                , {field: 'pCategory', align: 'center', title: '费用类别',width:95}
                , {field: 'unitPrice', align: 'center', title: '单价',width:75}
                , {field: 'num', align: 'center', title: '数量',width:70}
                , {field: 'cost', align: 'center', title: '总金额',width:95}
                , {field: 'pType', align: 'center', title: '支付类别',width:85}

            ]
        ]
        , page: true
    });
    //监听搜索
    form.on('submit(LAY-detailedInformation-front-search)', function (data) {
        var field = data.field;
        //执行重载
       layui.table.reload('detaileTable', {
            where: field
            ,page: { curr: 1}
        });
    });
});




var indexAll;
var tableAll;
var indexAdd;
//初始化
var inithisid="";
var initbelong="";
function initData(data) {
    inithisid=data.selecthisid;
    initbelong=data.selectBelong;
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
        elem: '#detailedInformationTable'
        ,url:$WEB_ROOT_PATH + '/dhccApi/flyTreatmentFeeCount/personalInformationInquiry/searchDetail'
        , height: tableHeight
        , cols: [
            [
                {type: 'numbers',title: '序号'}
                , {field: 'hisid', align: 'center', title: 'hisid', width:"200"}
                , {field: 'itemId', align: 'center', title: '医保项目编码',width:"133"}
                , {field: 'itemName', align: 'center', title: '医保项目名称',width:"147"}
                , {field: 'itemIdHosp', align: 'center', title: '医院项目编码',width:"141"}
                , {field: 'itemNameHosp', align: 'center', title: '医院项目名称',width:"140"}
                , {field: 'pCategory', align: 'center', title: '费用类别',width:"103"}
                , {field: 'unitPrice', align: 'center', title: '单价',width:"81"}
                , {field: 'num', align: 'center', title: '数量',width:"72"}
                , {field: 'cost', align: 'center', title: '总金额',width:"88"}
                , {field: 'pType', align: 'center', title: '支付类别',width:"114"}

            ]
        ]
        , parseData: function (res) { //res 即为原始返回的数据
            console.log(res);
            return {
                "code": 0, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        ,where:{'detailedInformationVo.hisid':inithisid ,'detailedInformationVo.belong':initbelong}
        , page: true
    });
    //监听搜索
    form.on('submit(LAY-detailedInformation-front-search)', function (data) {
        var field = data.field;
        //执行重载
       layui.table.reload('detailedInformationTable', {
            where: field
            ,page: { curr: 1}
        });
    });
});

var indexAll;
var tableAll;
layui.config({
  base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
  index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','form'], function () {
    var table = layui.table

        table.render({
            elem: '#flyTreatmentFeeCountStatisticsTable'
            ,
            url:$WEB_ROOT_PATH + '/dhccApi/flyTreatmentFeeCount/flyTreatmentFeeCount/ultrasoundlist'
            ,
            height: tableHeight
            ,
            cols: [
                [
                    {type: 'numbers',title: '序号'}
                    , {field: 'item', align: 'center', title: '项目名称'}
                    , {field: 'frequency', align: 'center', title: '频次'}
                    , {field: 'sum', align: 'center', title: '总金额'}

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
            ,
            page: true
        });

});

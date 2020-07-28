var itemCodeIns;
var allParam;
var year;
var month;
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'laydate'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
    var laydate = layui.laydate;
    //日期范围
    laydate.render({
        elem: '#chooseYear'
        , type: 'year'
        ,done: function(value, date, endDate){
            year=value;
        }
    });
    laydate.render({
        elem: '#chooseMonth'
        , type: 'month'
        ,done: function(value, date, endDate){
            month=value;
        }
    });

    table.render({
        elem: '#drugsAndInspectionStatisticsForInsectionTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/drugsAndInspectionStatistics/drugsAndInspectionStatistics/listForInspection'
        , cellMinWidth: 80
        , height: tableHeight
        , cols: [[
            {type: 'numbers', width: 40, title: '编号'}
            , {align: 'center', title: '操作', toolbar: '#table-useradmin-webuser', width: 220}
            , {field: 'itemNameHos',width:250, align: 'center', title: '检查项目名称'}
            , {field: 'itemCodeHos',  align: 'center', title: '检查项目编码'}
            , {field: 'frequency',  align: 'center', title: '频次'}
            , {field: 'totalNumber',  align: 'center', title: '总数量'}
            , {field: 'totalCost',  align: 'center', title: '总金额'}
        ]]
        , page: true
    });

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('drugsAndInspectionStatisticsForInsectionTable', {
            where: field
        });
    });

//监听行点击
    table.on('tool(drugsAndInspectionStatisticsForInsectionTable)', function (obj) {
        var data = obj.data;
        allParam=data
        if (obj.event === 'costDetail') {
            //修改方法
            itemCodeIns=data.itemCodeIns
            layer.open({
                type: 2
                , title: '检查项目费用明细'
                , content: $WEB_ROOT_PATH + '/drugsAndInspectionStatistics/drugsAndInspectionStatisticsForInselectionCostDetail'
                , maxmin: true
                , area: ['92%', '600px']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                }

            });
        }

    })

    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
function  getMethodValue3(){
    if (month==undefined) {
        month='';
    }
    if (year==undefined){
        year='';
    }
    allParam.month=month;
    allParam.year=year;
    return allParam;
}
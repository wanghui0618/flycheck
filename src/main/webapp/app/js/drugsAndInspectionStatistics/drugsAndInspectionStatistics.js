//
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
            elem: '#drugsAndInspectionStatisticsTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/drugsAndInspectionStatistics/drugsAndInspectionStatistics/list'
            , cellMinWidth: 80
            , height: tableHeight
            , cols: [[
                {type: 'numbers', width: 40, title: '编号'}
                , {align: 'center', title: '操作', toolbar: '#table-useradmin-webuser', width: 220}
                , {field: 'itemNameIns',width:250, align: 'center', title: '药品名称'}
                , {field: 'itemCodeIns',  align: 'center', title: '药品编码'}
                , {field: 'frequency',  align: 'center', title: '频次'}
                , {field: 'totalNumber',  align: 'center', title: '总数量'}
                , {field: 'totalCost',  align: 'center', title: '总金额'}
            ]]
            , page: true
        });


    // hideButtonStatic();//按钮权限

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('drugsAndInspectionStatisticsTable', {
            where: field
        });
    });



    //监听行点击
    table.on('tool(drugsAndInspectionStatisticsTable)', function (obj) {
        var data = obj.data;
        allParam=data
        if (obj.event === 'costDetail') {
            //修改方法
            itemCodeIns=data.itemCodeIns
            layer.open({
                type: 2
                , title: '编辑用户信息'
                , content: $WEB_ROOT_PATH + '/drugsAndInspectionStatistics/drugsAndInspectionStatisticsCostDetail'
                , maxmin: true
                , area: ['92%', '600px']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));
                }

            });
        }

    })

    //监听行单击事件
    table.on('row(drugsAndInspectionStatisticsTable)', function(obj){
        console.log(obj.tr) //得到当前行元素对象
        console.log(obj.data) //得到当前行数据
        //obj.del(); //删除当前行
        //obj.update(fields) //修改当前行数据
    });

    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});


function  getMethodValue(){
    return itemCodeIns;
}

function  getMethodValue2(){
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

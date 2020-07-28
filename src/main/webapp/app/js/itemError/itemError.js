//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;

    table.render({
        elem: '#itemError'
        ,url: $WEB_ROOT_PATH+'/dhccApi/itemError/itemError/listHop111'
        ,cellMinWidth: 80
        ,height: document.documentElement.clientHeight-65
        ,where: {  ilegalChild: '1'  }
        ,cols: [[
            {type: 'numbers', width: '10%', title: '序号'}
            , {field: 'orgName', width: '47.5%', title: '医院名称'}
            , {field: 'orgCode', width: '40%', title: '医院代码'}
        ]]
        ,done:function(res) {
            $('tr').eq(1).css("background-color", "#C0C0C0");
            table1(res.data[0].orgCode)
        }
        ,page: true
    });

     form.on('submit(LAY-user-front-search2)', function (data) {
        var field = data.field;
        //执行重载
         layui.table.reload('itemError', {
            where: field
            , page: {curr: 1}

        });
    });

    table.on('row(itemErrorTable)', function (obj) {
        $("tr").css("background-color", "");
        $(this).css("background-color", "#C0C0C0");
        console.log(obj.data.orgCode)
        table1(obj.data.orgCode)
    });








    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});

































// //初始化
// layui.config({
//     base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
// }).extend({
//     index: 'lib/index' //主入口模块
// }).use(['index', 'table'], function () {
//     var $ = layui.$
//         , form = layui.form
//         , table = layui.table;
//
//     table.render({
//         elem: '#itemError',
//         type: 'post'
//         , url: $WEB_ROOT_PATH + "/dhccApi/itemError/itemError/listHop"
//         , height: document.documentElement.clientHeight - 65
//         , cellMinWidth: 80
//         , cols: [[
//             {type: 'numbers', title: '序号'}
//             , {field: 'orgName', width: 220, title: '医院名称'}
//             , {field: 'orgCode', width: 115, title: '医院代码'}
//
//         ]]
//         , page: true
//         , done: function (res) {
//
//         }
//     });
//
//     //
//     // //监听搜索
//     // form.on('submit(LAY-user-front-search2)', function (data) {
//     //     var field = data.field;
//     //     type_no_violation = '';
//     //     console.log(field);
//     //     var tablenew = layui.table;
//     //     //执行重载
//     //     tablenew.reload('violationDetail', {
//     //         where: field
//     //     });
//     // });
//     //
//     // table.on('row(violationDetail)', function (obj) {
//     //     $("tr").css("background-color", "");
//     //     $(this).css("background-color", "#C0C0C0");
//     //     var result = obj.data;
//     //     var typeNo = result.typeNo;
//     //     tn = typeNo;//全局变量赋值
//     //     typeNameCl = result.typeNames;
//     //     $("#yiyuan").html(typeNameCl);
//     //     reloadTable(typeNo, statusUrl);
//     // });
//     // $("#yiyuan").on("click", function () {
//     //     var param = {seriesIndex: "1", name: tn};
//     //     eConsole(param);
//     // });
//
//
//     //按钮事件绑定底层方法-勿动
//     $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
//         var type = $(this).data('type');
//         active[type] ? active[type].call(this) : '';
//     });
// });
//
//
// // function eConsoleSpread(param) {
// //
// // }
//
// // function reloadTable(typeNo, statusUrl) {
// //     require(['echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
// //         ],
// //         function (echarts) {
// //             echarts.init(document.getElementById('main')).dispose();//销毁前一个实例
// //             memoBar = echarts.init(document.getElementById('main'));//构建下一个实例
// //             var option = {
// //                 tooltip: {
// //                     show: true
// //                 },
// //                 padding: [0, 0, 10, 10],  // 位置
// //                 legend: {
// //                     padding: 10,    // [5, 10, 15, 20]
// //                     itemGap: 20,
// //                     left: 'right',
// //                     data: ['违规类型医院分布TOP10统计']
// //                 },
// //                 xAxis: [
// //                     {
// //                         type: 'category',
// //                         data: [],
// //                         axisLabel: {//坐标轴刻度标签的相关设置。
// //                             interval: 0,
// //                             rotate: "20"
// //                         }
// //                     }
// //                 ],
// //                 yAxis: [
// //                     {
// //                         type: 'value'
// //                     }
// //                 ],
// //                 series: [
// //                     {
// //                         "name": "违规数量",
// //                         "type": "bar",
// //                         barWidth: 30,
// //                         barMaxWidth: 20,
// //                         "data": [],
// //                         itemStyle: {
// //                             normal: {
// //                                 color: '#419bf9'
// //                             }
// //                         }
// //                     }
// //                 ]
// //             };
// //             // 为echarts对象加载数据
// //             $.ajax({
// //                 url: $WEB_ROOT_PATH + "",
// //                 type: "post",
// //                 async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
// //                 data: {"violationDetail.typeNo": typeNo, "status": statusUrl},
// //                 success: function (result) {
// //
// //                 }
// //             });
// //             //下面是需要添加的方法内容
// //             //点击柱状图跳转相应页面的功能，其中param.name参数为横坐标的值
// //             var ecConfig = require('echarts/config');
// //             memoBar.on(ecConfig.EVENT.CLICK, eConsoleSpread);
// //         });
// // }

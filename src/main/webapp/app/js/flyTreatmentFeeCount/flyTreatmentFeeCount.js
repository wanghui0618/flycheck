var indexAll;
var tableAll;
var indexAdd;
//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','form'], function () {
    var  form = layui.form
        , table = layui.table
        ,laydate = layui.laydate;
    tableAll=table;
    laydate.render({
        elem: '#testYear'
        ,type: 'year'
    });
        table.render({
            elem: '#flyTreatmentFeeCountStatisticsTable'
            ,
            url:$WEB_ROOT_PATH + '/dhccApi/flyTreatmentFeeCount/flyTreatmentFeeCount/list'
            ,
            height: tableHeight
            ,
            cols: [
                [
                    {type: 'numbers',title: '序号'}
                    , {field: 'item', align: 'center', title: '项目名称'}
                    , {field: 'frequency', align: 'center', title: '频次'}

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

    //监听搜索
    form.on('submit(LAY-flyTreatmentFeeCountStatistics-front-search)', function (data) {
        var isMonth= /^0?[1-9]$|^1[0-2]$/;
        var field = data.field;
       if(jQuery("#seachmonth").val()!==""){

           if(jQuery("#testYear").val()==""){
               layer.alert("请先选择年份")
               return false;
           }
          if(!isMonth.test(jQuery("#seachmonth").val())){
              layer.alert("请输入1-12之间的一位数或两位数");
              return false;
          }
    };
        //执行重载
        layui.table.reload('flyTreatmentFeeCountStatisticsTable', {
            where: field
            ,page: { curr: 1}
        });
    });
});

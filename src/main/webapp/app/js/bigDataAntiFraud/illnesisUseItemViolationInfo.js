//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;

    //日期范围
    var laydate1=layui.laydate;
    laydate1.render({
        elem:'#startTime'
        ,trigger:'click'
        ,format:'yyyy-MM-dd'
        ,range: true
    });
    table.render({
        elem: '#userTable'
        ,url: $WEB_ROOT_PATH+'/app/js/bigDataAntiFraud/illnesisUseItemViolationInfo.json'
        ,cellMinWidth: 80
        ,height: 425

        ,scrollbar: false

        //,where: {  ilegalChild: '1'  }
        ,cols: [[
            {type: 'numbers', title: '序号' }
            ,{field: 'firstResult',width:100,align:'center', title: '初审结果zzz' }
            ,{field:'machineAudit',width:100,align:'center', title: '机审结果11111'}
            ,{field:'itemNum',width:100,align:'center', title: '项目编号'}
            ,{field:'itemName',width:100,align:'center', title: '项目名称'}
            ,{field:'medicalType',width:100,align:'center', title: '药品类别'}
            ,{field:'itemCost',width:100,align:'center', title: '项目金额'}
            ,{field:'totalCost',width:100,align:'center', title: '总金额'}
            ,{field:'itemAmount',width:100,align:'center', title: '项目数量'}
            ,{field:'itemPrice',width:100,align:'center', title: '项目单价'}
            ,{field:'itemStandards',width:100,align:'center', title: '项目规格'}
            ,{field:'',width:100,align:'center', title: '剂型'}
            ,{field:'',width:100,align:'center', title: '限价金额'}
            ,{field:'',width:100,align:'center', title: '部分统筹'}
            ,{field:'',width:100,align:'center', title: '部分自付'}
            ,{field:'',width:150,align:'center', title: '门诊处方编号'}
            ,{field:'',width:100,align:'center', title: '自付金额'}
            ,{field:'',width:100,align:'center', title: '单词用量'}
            ,{field:'',width:100,align:'center', title: '服用频次'}
            ,{field:'',width:100,align:'center', title: '用药天数'}
            ,{field:'',width:100,align:'center', title: '报销金额'}
            ,{field:'',width:100,align:'center', title: '违规类型'}
            ,{field:'',width:100,align:'center', title: '报销级别'}
            ,{field:'',width:100,align:'center', title: '收费类别'}
            ,{field:'',width:100,align:'center', title: '给药途径'}
            ,{field:'',width:100,align:'center', title: '用量单位'}
            ,{field:'',width:150,align:'center', title: '费用发生时间'}
            ,{field:'',width:100,align:'center', title: '全额统筹'}



        ]]

        ,page: true
    });









    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
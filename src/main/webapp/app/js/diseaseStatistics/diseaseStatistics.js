//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function(){
    var table = layui.table;
    table.render({
        elem: '#diseaseStatistics'
        ,url: $WEB_ROOT_PATH+'/dhccApi/diseaseStatistics/diseaseStatistics/list'
        ,cellMinWidth: 80
        ,height: document.documentElement.clientHeight-25
        ,cols: [[
            {type: 'numbers', fixed: 'left',title:"序号"}
            // ,{field: 'orgName', title: '医疗机构' ,width:'15%'}
            ,{field:'INHOSDIAG',align:'center', title: '诊断'}
            ,{field:'CASES',align:'center', title: '病例数'}
			,{field:'TOTALCOST', align:'center',title: '总费用（元）' }
			,{field:'AVGCOST',align:'center', title: '次均费用（元）' }
			,{field:'FUNDCOST', align:'center',title: '医保报销金额（元）' }
			,{field:'AVGFUNDCOST',align:'center', title: '医保报销次均金额（元）' }
        ]]
        ,page: true
    });
});
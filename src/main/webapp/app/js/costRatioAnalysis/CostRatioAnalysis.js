//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function(){
    var width = $(window).width();
    var height = $(window).height();
    $('#tx').css('width',width-30);
    $('#tx').css('height',height-20);
    //这里调用是初始化进来的时候，防止表格没有数据而加的
    Detaildg("getCostRatioAnalysis");
});
//搜索方法
function search(){
    //获取id 为 Detail 下拉框的value，并且判断，去对应的方法
    var Detail=$("#Detail").val();
    if(Detail == "Inhos"){
        //调用方法，传入动态的链接
        Detaildg("getCostRatioAnalysis");
    }else if(Detail == "menz"){
        //调用方法，传入动态的链接
        Detaildg("getCostRatioAnalysisMenz");
    }
};
//数据表格渲染方法
function Detaildg(data){
    var table = layui.table;
table.render({
    elem: '#dg'
    //根据传过来的data 拼接链接，目的是去不同的方法
    ,url: $WEB_ROOT_PATH+'/dhccApi/CostRatioAnalysisRest/'+data+''
    ,cellMinWidth: 80
    ,height:tableHeight
    ,cols: [[
        {type: 'numbers', fixed: 'left',title:"序号"}
        // ,{field: 'orgName', title: '医疗机构' ,width:'15%'}
        ,{field: 'T_HISID', title: '单据号'}
        ,{field: 'ITEM_COST_DRUG_ZB', title: '药占比(%)' }
        ,{field: 'ITEM_COST_MEDICAL_ZB', title: '诊疗项目占比(%)'}
        ,{field: 'ITEM_COST_AN_ZB', title: '检查项目占比(%)'}
    ]]
    ,page: true
});
}

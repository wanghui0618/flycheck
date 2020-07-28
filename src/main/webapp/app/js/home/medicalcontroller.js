//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function() {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    function InitTable() {
        table.render({
            elem: '#fundTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/home/regionalfunds/listVo'
            , cellMinWidth: 80
            , height: 185
            // ,limit: 5
            ,where:{"regionalfunds.place":"city"}
            , cols: [[
                {type: 'numbers', title: '序号' }
                ,{field: 'handdingInsName',width:250, title: '统筹区'}
                , {field: 'funds',align:'center',title: '基金金额(万元)',templet:function(value){
                        var a =value.funds;
                        return (a/10000).toFixed(2)}}
                , {field: 'fundsProportion',align:'center', title: '基金占比',templet:function(value){
                        var a =value.fundsProportion;
                        return a+'%'}}
                // , {field: 'id', title: '排名'}

            ]]
            , page: false
        })
    };
    InitTable();

    function InitTable1() {
        table.render({
            elem: '#orgFundTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/home/regionalfunds/listVo'
            , cellMinWidth: 80
            , height: 300
            // ,limit: 5
            ,where:{"regionalfunds.place":"org"}
            , cols: [[
                {type: 'numbers', title: '序号' }
                ,{field: 'orgName',width:250, title: '机构名称'}
                , {field: 'funds',align:'center', title: '基金金额(万元)',templet:function(value){
                        var a =value.funds;
                        return (a/10000).toFixed(2)}}
                , {field: 'fundsProportion', align:'center',title: '基金占比',templet:function(value){
                        var a =value.fundsProportion;
                        return a+'%'}}
                // , {field: 'id', title: '排名'}

            ]]
            , page: false
        })
    };
    InitTable1();


    $.getJSON($WEB_ROOT_PATH+'/dhccApi/home/regionalfunds/totalTimes',
        function(data){
        // console.log(data);
            var diag1=0;
            var diag2=0;
            var diag3=0;
            for(var i=0;i<data.data.length;i++){
                if(data.data[i].diag=='1'){
                    diag1=parseInt(diag1)+parseInt(data.data[i].totalAmount);
                }else if(data.data[i].diag=='2'){
                    diag2=parseInt(diag2)+parseInt(data.data[i].totalAmount);
                }else if(data.data[i].diag=='3'){
                    diag3=parseInt(diag3)+parseInt(data.data[i].totalAmount);
                }
            }
            var total=parseInt(diag1)+parseInt(diag2)+parseInt(diag3);
            var count1=document.getElementById("count1");
            count1.innerText=diag1;
            var count2=document.getElementById("count2");
            count2.innerText=diag2;
            var count3=document.getElementById("count3");
            count3.innerText=diag3;
            var total_count=document.getElementById("total_count");
            total_count.innerText=total;
        });



});

var checkFlag=0;

layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
    table.render({
        elem: '#procedureTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/procedure/procedure/listVo'
        , cellMinWidth: 80
        ,height: tableHeight
        , cols: [[
            {type: 'numbers', title: '序号' }
            ,{field: 'runningType',title:'操作', align:'center',templet:function (value) {
                    // console.log(value.pdName);
                    var html =""
                    // console.log(_flagSelectorAll)
                    if(_flagSelectorAll||value.runningType=='1'){
                    // if(value.runningType=='1'){
                        html= '<a class="layui-btn layui-btn-danger layui-btn-xs work loading" name="startOne" id='+value.pdName+' lay-event="start"><i class="layui-icon layui-icon-play"></i>执行</a>';
                    }else{
                        html='<a class="layui-btn layui-btn-danger layui-btn-xs work" name="startOne" id='+value.pdName+' lay-event="start"><i class="layui-icon layui-icon-play"></i>执行</a>';
                    }
                    return html

                }, width: 90 }
            ,{field: 'pdName',title: '存储过程名称'}
            , {field: 'pdModule', title: '模块名称'}
            // , {field: 'pdXm',width:120, title: '负责人'}
            /*, {field: 'pdCode', title: '存储过程代码'}*/
            , {field: 'pdDesc', title: '描述'}
            , {field: 'type',width:80, title: '类型',templet:function (value) {
                if(value.type=='1'){
                    return '统计';
                }if(value.type=='0'){
                        return '机审';
                }if(value.type=='2'){
                        return '预处理';
                }
            }
            }
            , {field: 'status',width:100, title: '是否可用',templet:function (value) {
                    if(value.status=='1'){
                        return '可用';
                    }if(value.status=='0'){
                        return '不可用';
                    }
            }
            }
            , {field: 'pdTable', title: '虚拟表'}
        ]]
        , page: true
    });
    //搜索
    form.on('submit(LAY-avgDays-front-search)', function(data){
        var field = data.field;
        console.log(field)
        //执行重载
        layui.table.reload('procedureTable', {
            where: field
            ,page: { curr: 1}
        });
    });

    form.on('submit(LAY-user-front-stop)', function(data){
        checkFlag=0;
        var url = $WEB_ROOT_PATH + "/dhccApi/procedure/procedure/stop";
        $.get(url,function(result){
            check();
            if(result.msg=='true'){
                layer.msg('全部停止成功');
            }else {
                layer.msg('当前未执行');
            }
        });
    });

    // var active2 = {
    //     stopAll: function () {
    //         var url = $WEB_ROOT_PATH + "/dhccApi/procedure/procedure/stop";
    //         $.get(url,function(result){
    //             if(result.msg=='true'){
    //                 layer.msg('全部停止成功');
    //             }
    //         });
    //     }
    // }

    var active = {
        startAll: function(){
            var url=$WEB_ROOT_PATH+"/dhccApi/procedure/procedure/check?procedure.flag=all";
            $.get(url,function(result){
                checkFlag=1;
                if(result.msg=='false'){
                    wroking();
                    layer.msg('正在全部执行，预计5-20分钟，请稍等');

                    var pdName=document.getElementById("pdName").value;
                    var pdModule=document.getElementById("pdModule").value;
                    // var type=document.getElementById("type").value;
                    var time=document.getElementById("time").value;
                    var time2=document.getElementById("time2").value;
                    var hospNum=document.getElementById("hospNum").value;
                    var params={"procedure.pdName":pdName,"procedure.pdModule":pdModule,"procedure.time":time,"procedure.year":time2,"procedure.hospNum":hospNum};
                    console.log(params)
                    var url=$WEB_ROOT_PATH+"/dhccApi/procedure/procedure/executeAll";
                    $.get(url,params,function(result){
                        // layer.msg('正在执行·····');
                        runEvery10Sec();
                    });
                }else{
                    layer.msg('其他用户正在执行，请稍后再试');
                    runEvery10Sec();
                }
            });


        }
    };

check();

function check(){
    $.getJSON($WEB_ROOT_PATH+'/dhccApi/procedure/procedure/flag',
        function(data){
            // console.log(data)
            if(data.msg=='true'){
                // checkFlag=0
                _flagSelectorAll=true;
                // var startOne=$(".work")
                // startOne.addClass("loading");
                wroking();
            }else{
                // checkFlag=0
                _flagSelectorAll=false;
                var startAll = $("#startAll")
                if(startAll.hasClass("loading")) {
                    startAll.removeClass("loading");
                }
                var a=document.getElementById("exc");
                a.innerText="执行全部"
                var startOne=$(".work")
                if(startOne.hasClass("loading")) {
                    startOne.removeClass("loading");
                }
                // layui.table.reload('procedureTable', {
                //     where: field
                //     ,page: { curr: 1}
                // });
            }
        });
}


    function runEvery10Sec() {
        // 1000 * 10 = 10 秒钟
        if(checkFlag=='1') {
            setTimeout(runEvery10Sec, 1000 * 5);
            check();
        }

    }
    // runEvery10Sec();

    var _flagSelectorAll = false
    function wroking(){
        //var startAll = document.querySelector("#startAll");
        var startAll = $("#startAll");
        var a=document.getElementById("exc");
        a.innerText='正在全部执行中';
        if(!startAll.hasClass("loading")) {
            startAll.addClass("loading");
        }
        _flagSelectorAll=true;
        var startOne=$(".work")
        if(!startOne.hasClass("loading")) {
            console.log("============================")
            startOne.addClass("loading");
        }
    }


    table.on('tool(procedureTable)', function(obj){
        var pdName = obj.data.pdName;
        var $pdName= $("#"+pdName)
        $pdName.addClass("loading");
        var data = obj.data;
        if(obj.event === 'start'){

            $.get($WEB_ROOT_PATH+"/dhccApi/procedure/procedure/check?procedure.pdName="+data.pdName,function(res) {
                console.log(res)
                console.log(res.msg=="false")
                console.log(res.msg==="false")
                if(res.msg=="false") {

                    var time=document.getElementById("time").value;
                    var time2=document.getElementById("time2").value;
                    var hospNum=document.getElementById("hospNum").value;
                    var params={"procedure.time":time,"procedure.year":time2,"procedure.hospNum":hospNum};
                    var url = $WEB_ROOT_PATH + "/dhccApi/procedure/procedure/execute?procedure.pdName=" + data.pdName;
                    $.get(url,params, function (result) {
                        if (result.msg == 'true') {
                            layer.msg('执行成功！');
                            // setTimeout(function () {
                                $pdName.removeClass("loading");
                            // }, 800)


                        } else {
                            layer.msg('执行失败，请重新执行！');
                            // setTimeout(function () {

                                $pdName.removeClass("loading");
                            // }, 1000)
                        }
                    });
                }else{
                    layer.msg('此存储过程正在执行，请刷新再试！');
                }
            });
        }
    });



    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});



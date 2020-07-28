var year=thisYear();
var type=1;
var handdingCode;
var orgCode;
var flag=-1
var rowName;

var handdingCode2=GetRequest()


layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function() {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    table.on('row(hopTable)', function (obj){
        $("tr").css("background-color", "");
        $(this).css("background-color", "#C0C0C0");
        console.log(obj)
        rowName=obj.data.orgCode
        change324(obj.data.orgName,obj.data.orgCode)
    });
    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});

function table3All(year,type,handdingInsCode){
        layui.config({
            base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
        }).use(['index', 'table'], function() {
            var $ = layui.$
                , form = layui.form
                , table = layui.table;
            table.render({
                elem: '#hopTable'
                ,url: $WEB_ROOT_PATH + "/dhccApi/analysisOfPersonTime/analysisOfPersonTime/table3?analysisOfPersonTime.year="+year+"&analysisOfPersonTime.type="+type+"&analysisOfPersonTime.handdingInsCode="+handdingInsCode
                , cellMinWidth: 80
                ,height: 270
                , cols: [[
                    {type: 'numbers', width: '10%', title: '序号' }
                    ,{field: 'orgName', width: '47.5%',title: '医院名称'}
                    , {field: 'totalTime', width: '20%', title: '就诊人次'}
                    , {field: 'eachPersonTime', width: '20%', title: '就诊频次'}
                ]]
                ,done:function(res){
                    if(flag==-1) {
                        $('tr').eq(1).css("background-color", "#C0C0C0");
                        flag=1
                    }else{
                        for(var i=0;i<10;i++){
                            if(res.data[i]!=null&&res.data[i].orgCode==rowName){
                                $('tr').eq(i+1).css("background-color", "#C0C0C0");

                            }
                        }
                    }
                }
                , page: true
            });
            //按钮事件绑定底层方法-勿动
            $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
}



function table4All(year,type,handdingInsCode,orgCode){
    layui.config({
        base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function() {
        var $ = layui.$
            , form = layui.form
            , table = layui.table;
        table.render({
            elem: '#diseTable'
            ,url: $WEB_ROOT_PATH + "/dhccApi/analysisOfPersonTime/analysisOfPersonTime/table4?analysisOfPersonTime.year="+year+"&analysisOfPersonTime.type="+type+"&analysisOfPersonTime.handdingInsCode="+handdingInsCode+"&analysisOfPersonTime.orgCode="+orgCode
            , cellMinWidth: 80
            ,height: 270
            , cols: [[
                {type: 'numbers', width: '10%', title: '序号' }
                ,{field: 'condition', width: '47.5%',title: '医院名称'}
                , {field: 'totalTime', width: '20%', title: '就诊人次'}
                , {field: 'eachPersonTime', width: '20%', title: '就诊频次'}
            ]]

            , page: true
        });

        //按钮事件绑定底层方法-勿动
        $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
}

layui.use(['form'], function() {
    var form=layui.form;
    form.on('select(brickType)', function(data){
        console.log(data)
        type=data.value;
        console.log(handdingCode)
        table1(year,type);
        flag=-1;
        rowName=null
    });
});






function thisYear(){
    var myDate = new Date();
    var tYear = myDate.getFullYear();
    return tYear;
}


var grapWidth=$("#mainGrap")[0].offsetWidth;
$("#main2").css('width',grapWidth);
var grapWidth1=$("#mainGrap1")[0].offsetWidth;
$("#main3").css('width',grapWidth1);

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
console.log(location)
    if(url!="") {
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
                // theRequest[i] = decodeURIComponent(strs[i].split("=")[1]);
            }
        }
        console.log(theRequest)
        console.log(theRequest.tcqCode)
        return theRequest.tcqCode;
    }else{
        return ""
    }
    // return "132456"
}



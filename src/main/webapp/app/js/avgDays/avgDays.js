layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;

    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
        function(data){
            var orgs=data.pageModel.rows
            //var  dataList= data.dictList;
            var org_save=JSON.stringify(orgs);//解析为字符串
            //localStorage.clear();

            localStorage.setItem('org_save',org_save);//存入浏览器数据库
            for(var i=0 ;i<orgs.length;i++){
                // var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                $("#zyOrgName").append(mm);
            }
            form.render('select');
        });

    function InitTable() {
        table.render({
            elem: '#avgDaysTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/avgDays/avgDays/listVo'
            , cellMinWidth: 80
            , height: 435
            // ,limit: 5
            //  ,where:{"avgDays.orgName":orgName,"type":type,"sHType":"cs"}
            , cols: [[
                  {type: 'numbers', title: '序号' }
                 ,{field: 'orgName', title: '机构名称'}
                , {field: 'countPerson', title: '总人数'}
                , {field: 'stayLength', title: '总住院天数'}
                , {field: 'stayAvg', title: '平均住院天数'}
               // , {field: 'id', title: '排名'}

            ]]
            , page: true
        })
    };
    InitTable();
    //搜索
    form.on('submit(LAY-avgDays-front-search)', function(data){
        var field = data.field;
        //执行重载
        layui.table.reload('avgDaysTable', {
            where: field
            ,page: { curr: 1}
        });
    });

    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //年范围
        laydate.render({
            elem: '#test7'
            ,type: 'year'
            ,value:year()
            ,max:nowYear()
            ,range: true
        });
    });

    function nowYear(){
        var date=new Date();
        var nowYear=date.getFullYear()
        return nowYear.toString()
    }

    function year(){
        var date=new Date();
        var nowYear=date.getFullYear();
        var oldYear=nowYear-3;
        return oldYear+" - "+nowYear;
    }
    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});


function child(obj) {
    var diseasesClass = JSON.parse(obj);
    $("#id").val(diseasesClass["id"]);
    for ( var index in diseasesClass) {
        $("#" + index).val(diseasesClass[index])
    }
}
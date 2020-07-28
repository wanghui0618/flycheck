layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','form'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
    //校验
    var val=$("#btnChoose input:checked").val();
    if(val== 1){
        $("#unitHide").hide();
        $("#cityName").show();
    }else if(val==0){
        $("#cityName").hide();
        $("#unitHide").show();
    }

    form.on('radio(aaa)', function (data){
       if(this.value == 1){
           $("#unitHide").hide();
           $("#cityName").show();
       }else{
            $("#cityName").hide();
           $("#unitHide").show();
       }
    });


    var unitName =$("#unitName").val();
    var cityCodeInit=$("#cityCode").val();
    $.getJSON($WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/findOrg?dataAuthority.cityCode='+cityCodeInit,
        function (data) {
            var orgs = data.pageModel.rows
            //var  dataList= data.dictList;
            var org_save = JSON.stringify(orgs);//解析为字符串
            //localStorage.clear();
            localStorage.setItem('org_save', org_save);//存入浏览器数据库
            var mm = "<option value='' style='width: 250px' class='layui-select-tips '>请选择</option>";
            for (var i = 0; i < orgs.length; i++) {
                //拼接code和name  后端入库
                if(unitName===orgs[i].text){
                    mm += "<option class='layui-elip' value='"+orgs[i].value+"!!##@1a@a1@##!!"+orgs[i].text+"' selected>" + orgs[i].text + "</option>";
                }else{
                    mm += "<option class='layui-elip' value='"+orgs[i].value+"!!##@1a@a1@##!!"+orgs[i].text+"'>" + orgs[i].text + "</option>";

                }
            }
            $("#unitId").html(mm);
            form.render('select');
            var ddList=$("#unitId+.layui-form-select dl dd");
            for(var i=0,l=ddList.length;i<l;i++){
                if(i>0){
                    ddList[i].setAttribute('title',orgs[i-1].text)
                }
            }
        });
    $("#cityCode").mouseout(function() {
        var cityCode = $("#cityCode").val();
        if(cityCode===cityCodeInit) return;
        cityCodeInit=cityCode;
        $.getJSON($WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/findOrg?dataAuthority.cityCode='+cityCode,
            function (data) {
                var orgs = data.pageModel.rows
                //var  dataList= data.dictList;
                var org_save = JSON.stringify(orgs);//解析为字符串
                //localStorage.clear();
                localStorage.setItem('org_save', org_save);//存入浏览器数据库
                var mm = "<option value='' style='width: 250px' class='layui-select-tips '>请选择</option>";
                for (var i = 0; i < orgs.length; i++) {
                    //拼接code和name  后端入库
                     mm += "<option class='layui-elip' value='"+orgs[i].value+"!!##@1a@a1@##!!"+orgs[i].text+"'>" + orgs[i].text + "</option>";
                }
                console.log(mm)
                $("#unitId").html(mm);
                form.render('select');
                var ddList=$("#unitId+.layui-form-select dl dd");
                for(var i=0,l=ddList.length;i<l;i++){
                    if(i>0){
                        ddList[i].setAttribute('title',orgs[i-1].text)
                    }
                }
            });
    })

    //自定义验证规则
    form.verify({
        cityCode:[
           // /^[A-Za-z0-9_\-]+$/
            /^[A-Za-z0-9_\-]+$/
            ,'城市编码只能为字母/数字/下划线/-'
        ]
    });

});

function child(obj) {
    var diseasesClass = JSON.parse(obj);
    unitName=diseasesClass.unitName;
    $("#id").val(diseasesClass["id"]);
    for ( var index in diseasesClass) {
        $("#" + index).val(diseasesClass[index])

    }
};

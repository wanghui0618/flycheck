layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','form'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
//加载城市下拉字典
    var city_save=localStorage.getItem('city_save');//从浏览器数据库取出
    var citys=$.parseJSON(city_save);//解析成对象
    if(citys){
        for(var i=0 ;i<citys.length;i++){
            var mm="<option value='"+citys[i].value+"'>"+citys[i].text+"</option>";
            $("#cityCode").append(mm);
        }
        form.render('select');
    }else{
        //加载违规类型下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findCity',
            function(data){
            console.log(data)
                var citys=data.pageModel.rows
                //var  dataList= data.dictList;
                var city_save=JSON.stringify(citys);//解析为字符串
                //localStorage.clear();
                localStorage.setItem('city_save',city_save);//存入浏览器数据库
                for(var i=0 ;i<citys.length;i++){
                    var mm="<option value='"+citys[i].value+"'>"+citys[i].text+"</option>";
                    $("#cityCode").append(mm);
                }
                form.render('select');
            });
    }
   //加载org下拉字典
    var org_save=localStorage.getItem('org_save');//从浏览器数据库取出
    var orgs=$.parseJSON(org_save);//解析成对象
    if(orgs){
        for(var i=0 ;i<orgs.length;i++){
            var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
            $("#orgCode").append(mm);
        }
        form.render('select');
    }else{
        //加载违规类型下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
            function(data){
                console.log(data)
                var orgs=data.pageModel.rows
                //var  dataList= data.dictList;
                var org_save=JSON.stringify(orgs);//解析为字符串
                //localStorage.clear();
                localStorage.setItem('org_save',org_save);//存入浏览器数据库
                for(var i=0 ;i<orgs.length;i++){
                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                    $("#orgCode").append(mm);
                }
                form.render('select');
            });
    }


});


function child(obj) {
    console.log("child")
    console.log(obj)
    var diseasesClass = JSON.parse(obj);
    $("#id").val(diseasesClass["id"]);
    for ( var index in diseasesClass) {
        $("#" + index).val(diseasesClass[index])
    }
};

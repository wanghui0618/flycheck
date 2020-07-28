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
        ,url: $WEB_ROOT_PATH+'/app/js/bigDataAntiFraud/illnesisUserItemViolation.json'
        ,cellMinWidth: 80
        ,height: document.documentElement.clientHeight-120
        //,where: {  ilegalChild: '1'  }
        ,cols: [[
            {type: 'numbers', title: '序号' }
            ,{field:'id', title: 'ID', sort: true, hide:true}
            ,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
            ,{field: 'inTime', title: '入院时间' }
            ,{field:'outTime', title: '出院时间'}
            ,{field:'name', title: '患者姓名'}
            ,{field:'hosp', title: '医院名称'}
            ,{field:'zhenduan', title: '诊断疾病'}
            ,{field:'allCost', title: '可疑总金额（元）'}
            ,{field:'allCost1', title: '可疑报销金额（元）'}


        ]]
        ,done:function(data){
            medicalId=data.data[0].id;
            allCost=data.data[0].allCost;
            allCost1=data.data[0].allCost1;
            zhenduan = data.data[0].zhenduan;
            clickHandle(medicalId,allCost,allCost1,zhenduan);
            $('tr').eq(3).css("background-color","#C0C0C0");

        }

        ,page: true
    });
    //监听搜索
    form.on('submit(LAY-user-front-search)', function(data){
        var field = data.field;
        //执行重载
        layui.table.reload('userTable', {
            where: field
        });
    });


    table.on('row(userTable)', function (obj){
        var data = obj.data;
        var id = data.id;
        // alert('已选中患者为'+data.patientName);
        medicalId=data.id;
        allCost=data.allCost;
        allCost1=data.allCost1;
        date=data.inTime;

        console.log(data);
        console.log(medicalId=="10001");

        $("tr").css("background-color","");
        $(this).css("background-color","#C0C0C0");

        clickHandle(medicalId,allCost,allCost1,data.zhenduan);


        /*$("#totalCost").html(data.totalCost);*/
    });

    table.on('tool(userTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'operation') {
            layer.open({
                type: 2
                , title: '疾病使用项目违规详情'
                , content: $WEB_ROOT_PATH + '/bigDataAntiFraud/illnesisUseItemViolationInfo'
                , maxmin: true
                , area: ['1250px', '485px']
            });
        }
    });


function clickHandle(medicalId,allCost,allCost1,zhenduan){
    var allCostObj = document.getElementById("allCost");
    allCostObj.innerText=allCost;
    var allCost1Obj = document.getElementById("allCost1");
    allCost1Obj.innerText=allCost1;
    var zhenduanText = document.getElementById("zhenduan");
    zhenduanText.innerText=zhenduan;
    var str="";
    if(medicalId=="10001"){
        str="问题说明："+zhenduan+"，必需的检查项目：  血常规、尿常规、便常规;但未发现血常规检查"
    }
    if(medicalId=="10002"){
        str="问题说明："+zhenduan+"，必需的检查项目：  血、尿、便常规，便一般菌培养及鉴定、霍乱弧菌培养、PCT、肝炎病毒梅毒HIV、炎性肠病相关抗体、肝功能、肾功能、血糖、电解质、血脂、肿瘤标志物、凝血功能、D-二聚体" +
            "心电图、胸片、腹部彩超;但未发现心电图检查</a>"
    }
    var divobj=document.getElementById("illcontent");
    divobj.innerHTML=str;
}

    function deRepeat(arr){
        var newArr=[];
        for(var i=0;i<arr.length;i++){
            var text=arr[i].text;
            if($.inArray(arr[i],newArr)==-1){
                newArr.push(arr[i]);
            }
        }
        return newArr;
    }


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
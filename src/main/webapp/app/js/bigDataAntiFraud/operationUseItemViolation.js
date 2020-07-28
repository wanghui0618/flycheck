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
        ,url: $WEB_ROOT_PATH+'/app/js/bigDataAntiFraud/operationUseItemViolation.json'
        ,cellMinWidth: 80
        ,height: 415
        //,where: {  ilegalChild: '1'  }
        ,cols: [[
            {type: 'numbers', title: '序号' }
            ,{field:'id', title: 'ID', sort: true, hide:true}
            ,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
            ,{field: 'inTime', title: '入院时间' }
            ,{field:'outTime', title: '出院时间'}
            ,{field:'name', title: '患者姓名'}
            ,{field:'hosp', title: '医院名称'}
            ,{field:'zhenduan', title: '手术'}
            ,{field:'allCost', title: '可疑总金额（元）'}
            ,{field:'allCost1', title: '可疑报销金额（元）'}

        ]]
        ,done:function(data){
            medicalId=data.data[0].id;
            allCost=data.data[0].allCost;
            allCost1=data.data[0].allCost1;
            clickHandle(medicalId,allCost,allCost1);
            $('tr').eq(1).css("background-color","#C0C0C0");

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

    table.on('tool(userTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'operation') {
            layer.open({
                type: 2
                , title: '手术使用项目违规查询'
                , content: $WEB_ROOT_PATH + '/bigDataAntiFraud/operationUseItemViolationInfo'
                // , content: $WEB_ROOT_PATH + '/bigDataAntiFraud/illnesisUseItemViolationInfo'
                , maxmin: true
                , area: ['1250px', '485px']
            });
        }
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


        clickHandle(medicalId,allCost,allCost1);

    });

    function clickHandle(medicalId,allCost,allCost1){
        var allCostObj = document.getElementById("allCost");
        allCostObj.innerText=allCost;
        var allCost1Obj = document.getElementById("allCost1");
        allCost1Obj.innerText=allCost1;
        var str="";
        if(medicalId=="10001"){
            str="<a style='width:23%;font-size:1rem;display:inline-block;'>手术：原发性肺癌手术  </a><br><a style='font-size:1rem;display:inline-block;'>必需的检查项目：  （1）血常规、尿常规、大便常规；\n" +
                "（2）凝血功能、血型、肝功能、肾功能、电解质、感染性疾病筛查（乙肝、丙肝、艾滋病、梅毒等）；" +
                "（3）肺功能、心电图、动脉血气分析；" +
                "（4）痰细胞学检查、纤维支气管镜检查；" +
                "（5）影像学检查：X线胸片、胸部CT（平扫＋增强扫描）、腹部超声或腹部CT、全身骨扫描、头颅MRI或增强CT。" +
                "2.根据患者病情，可选择以下项目：" +
                "（1）纵隔镜或EBUS；" +
                "（2）经皮肺穿刺活检；" +
                "（3）超声心动图，24小时动态心电图；" +
                "（4）肿瘤标志物；" +
                "（5）心脑血管疾病相关检查<br>但未发现血常规检查</a>"
        }
        if(medicalId=="10002"){
            str="<a style='width:23%;font-size:1rem;display:inline-block;'>手术：胃癌根治手术  </a><br><a style='font-size:1rem;display:inline-block;'>必需的检查项目：（1）血常规、尿常规、粪常规+粪隐血；" +
                "（2）肝功能、肾功能、电解质、凝血功能、消化道肿瘤标志物、幽门螺杆菌检查、感染性疾病筛查（乙肝、丙肝、艾滋病、梅毒等）；" +
                "（3）胃镜、腹部及盆腔超声（女性）、腹部及盆腔CT平扫+增强。" +
                "（4）心电图、胸部X线检查或胸部CT;" +
                "（5）病理学活组织检查与诊断。"+
                "<br>但未发现心电图检查</a>"
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
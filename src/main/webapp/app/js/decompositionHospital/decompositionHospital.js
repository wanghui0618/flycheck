//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element','laydate'], function(){
    var width = $(window).width();
    var height = $(window).height();
    // $('#laycode').css('width',width-100);
    // $('#laycode').css('height',height-200);
    //这里调用是初始化进来的时候，防止表格没有数据而加的
    search();
    var laydate = layui.laydate;
    var table = layui.table;
    laydate.render({
        elem: '#settlementTime'
        ,format: 'yyyyMMdd'
        ,range: true  //中间以‘/’分开
        ,max: 0 //7天后
    });

});
//搜索方法
function search(){
    var route = $("input[name='route']").val();//就诊途径
    //var insurance = $("input[name='insurance']").val();//险种类型
    var hospitalCode = $("input[name='hospitalCode']").val();//医院编码
    var hospitalName = $("input[name='hospitalName']").val();//医院名称
    var settlementTime = $("input[name='settlementTime']").val();//结算时间
    var departmentName = $("input[name='departmentName']").val();//科室名称
    var day = $("#day").val();//分解住院天数维度
    var decomposedHospitalStay = $("input[name='decomposedHospitalStay']").val();//分解住院天数

    var  formDate={route:route,hospitalCode:hospitalCode,hospitalName:hospitalName,
                    settlementTime:settlementTime,departmentName:departmentName,day:day,decomposedHospitalStay:decomposedHospitalStay};
    console.log(formDate);
    Detaildg(formDate);
    totle(formDate);
    InsuredDig(formDate);
};
//重置按钮
function reset(){
    $("input[name='route']").val("");//就诊途径
    //$("input[name='insurance']").val("");//险种类型
    $('#getOrgName').combogrid("setValue", "");//医疗机构
    $("input[name='settlementTime']").val("");//结算时间
    $("input[name='departmentName']").val("");//科室名称
    $("input[name='decomposedHospitalStay']").val("");//分解住院天数
}

//给汇总按钮赋值
function totle(formDate){
    $.ajax({
        url:$WEB_ROOT_PATH+'/dhccApi/DecompositionHospitalRest/getTotalNumberOfCasesAndTotalAmount',
        type: "POST",
        data:formDate,
        dataType: "json",
        async: true,
        success: function(result){
            $("#total_number_of_cases").html(result[0].TOTALNUMBEROFCASES);
            $("#total_amount").html(result[0].SUMTOTALAMOUNT);
        },
    });
}
//具体病例信息数据表格渲染方法
function Detaildg(formDate){
    var table = layui.table;
    var index = layer.load(0); //添加laoding,0-2两种方式
    table.render({
        elem: '#dg'
        ,loading:true
        ,url: $WEB_ROOT_PATH+'/dhccApi/DecompositionHospitalRest/getDecompositionHospital'
        ,cellMinWidth: 80
        ,loading: true  //翻页加loading
        ,height: tableHeight-55
        ,cols: [[
            //文本靠左，数字靠右，等长居中
            {type: 'numbers', fixed: 'left',title:"序号" ,fixed: 'left',align:'right'}
            ,{field: 'HISID', title: '结算单据号' ,width:'18%',align:'center',width:200}
            ,{field: 'ZYH', title: '住院号',align:'center',width:165}
            ,{field: 'ADMISSION_DATE', title: '入院日期',align:'right',width:165,templet:function(d)
                {
                    return  layui.util.toDateString(d.ADMISSION_DATE);
                }
            }
            ,{field: 'DISCHARGE_DATE', title: '出院日期',align:'right',width:165,templet:function(d)
                {
                    return  layui.util.toDateString(d.DISCHARGE_DATE);
                }
            }
            ,{field: 'ZYTS', title: '住院天数',align:'right',width:90}
            ,{field: 'BILL_DATE', title: '结算日期',align:'right',width:170,templet:function(d)
                {
                    return  layui.util.toDateString(d.BILL_DATE);
                }
            }
            ,{field: 'DISCHARGE_DISEASE_ID_MAIN', title: '出院诊断编码',align:'left',width:200}
            ,{field: 'DISCHARGE_DISEASE_NAME_MAIN', title: '出院诊断名称',align:'left',width:200}
            ,{field: 'TOTAL_AMOUNT', title: '医疗总费用',align:'right',width:140}
            ,{field: 'BMI_PAY_AMOUNT', title: '基本统筹支付',align:'right',width:130}
            ,{fixed: 'right', title: '详情',width:100, align:'center', toolbar: '#barDetails'} //这里的toolbar值是模板元素的选择器
        ]]
            ,page: true
            ,where:formDate
            ,done: function (res) {//返回数据执行回调函数
                layer.close(index);    //返回数据关闭loading
            }

    });
    //监听工具条
    table.on('tool(dg)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'detail'){ //查看
            var form = {zyh:obj.data.ZYH};
            layer.open({
                type: 2,
                area: ['1100px', '550px'],
                title:'病例详情',
                content:$WEB_ROOT_PATH + '/decompositionHospital/decompositionHospitalDetails',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                success: function(layero, index){
                    //var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    iframeWin.initData(form);
                }
            });
            return false;
        }
    });
}
//参保人数据表格渲染方法
function InsuredDig(formDate){
    var table = layui.table;
    var index = layer.load(0); //添加laoding,0-2两种方式
    table.render({
        elem: '#dig'
        ,loading:true
        ,url: $WEB_ROOT_PATH+'/dhccApi/DecompositionHospitalRest/getInsuredDataForm'
        ,cellMinWidth: 50
        ,loading: true  //翻页加loading
        ,height: tableHeight-55
        ,cols: [[
            //文本靠左，数字靠右，等长居中
            {type: 'numbers', fixed: 'left',title:"序号" ,fixed: 'left',align:'right'}
            ,{field: 'BMI_AREA_NAME', title: '参保地统筹区域名称' ,width:'15%',align:'left',width:185}
            ,{field: 'PATIENT_ID', title: '个人编码' ,width:'15%',align:'left',width:110}
            ,{field: 'PATIENT_NAME', title: '患者姓名' ,width:'15%',align:'left',width:110}
            ,{field: 'DECOMPOSED_HOSPITALIZATIONS', title: '分解住院次数',align:'right',width:120,sort:true}
            ,{ title: '详情',width:70, align:'center', toolbar: '#barDetailsA'} //这里的toolbar值是模板元素的选择器
        ]]
        ,page: true
        ,where:formDate
        ,done:function (res) {   //返回数据执行回调函数
            layer.close(index);    //返回数据关闭loading
        }
    });
    // 监听行单击事件
    table.on('row(dig)', function(obj){
        var formDate = {social_card_id:obj.data.SOCIAL_CARD_ID};
        //console.log(formDate);
        View_details(formDate);
        //console.log(obj.tr); //得到当前行元素对象
        //console.log(obj.data); //得到当前行数据
        //obj.del(); //删除当前行
        //obj.update(fields) //修改当前行数据
    });
    //监听工具条
    table.on('tool(dig)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var socid=obj.data.SOCIAL_CARD_ID;
        console.log(socid);
        exportD(socid);
    });
}
function View_details(formDate){
    var table = layui.table;
    var index = layer.load(0); //添加laoding,0-2两种方式
    table.render({
        elem: '#dg'
        ,loading:true
        ,url: $WEB_ROOT_PATH+'/dhccApi/DecompositionHospitalRest/getDecompositionHospitalDetails'
        ,cellMinWidth: 80
        ,loading: true  //翻页加loading
        ,height: tableHeight-55
        ,cols: [[
            //文本靠左，数字靠右，等长居中
            {type: 'numbers', fixed: 'left',title:"序号" ,fixed: 'left',align:'right'}
            ,{field: 'HISID', title: '结算单据号' ,width:'18%',align:'center',width:200}
            ,{field: 'ZYH', title: '住院号',align:'center',width:165}
            ,{field: 'ADMISSION_DATE', title: '入院日期',align:'right',width:165,templet:function(d)
                {
                    return  layui.util.toDateString(d.ADMISSION_DATE);
                }
            }
            ,{field: 'DISCHARGE_DATE', title: '出院日期',align:'right',width:165,templet:function(d)
                {
                    return  layui.util.toDateString(d.DISCHARGE_DATE);
                }
            }
            ,{field: 'ZYTS', title: '住院天数',align:'right',width:90}
            ,{field: 'BILL_DATE', title: '结算日期',align:'right',width:170,templet:function(d)
                {
                    return  layui.util.toDateString(d.BILL_DATE);
                }
            }
            ,{field: 'DISCHARGE_DISEASE_ID_MAIN', title: '出院诊断编码',align:'left',width:200}
            ,{field: 'DISCHARGE_DISEASE_NAME_MAIN', title: '出院诊断名称',align:'left',width:200}
            ,{field: 'TOTAL_AMOUNT', title: '医疗总费用',align:'right',width:140}
            ,{field: 'BMI_PAY_AMOUNT', title: '基本统筹支付',align:'right',width:130}
            ,{fixed: 'right', title: '详情',width:100, align:'center', toolbar: '#barDetails'} //这里的toolbar值是模板元素的选择器
        ]]
        ,page: true
        ,where:formDate
        ,done: function (res) {//返回数据执行回调函数
            layer.close(index);    //返回数据关闭loading
        }
    });
    //监听工具条
    table.on('tool(dg)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'detail'){ //查看
            var form = {zyh:obj.data.ZYH};
            layer.open({
                type: 2,
                area: ['1100px', '550px'],
                title:'病例详情',
                content:$WEB_ROOT_PATH + '/decompositionHospital/decompositionHospitalDetails',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                success: function(layero, index){
                    //var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    iframeWin.initData(form);
                }
            });
            return false;
        }
    });
}
$(".cxtjtop").hide();
$("#shangla").hide();
function showSearch(){
	$("#shangla").show();
	$("#xiala").hide();
	$(".cxtjtop").show();
}
function hideSearch(){
	$(".cxtjtop").hide();
	$("#shangla").hide();
	$("#xiala").show();
}

//文档下载
function downLoadXsl(fileName){
    var form =$("<form>");
    form.attr('style','display:none');
    form.attr('target','');
    form.attr('method','post');
    form.attr('action',$WEB_ROOT_PATH+'/dhccApi/DecompositionHospitalRest/downLoadFile');
    var inputConfirmId =$('<input>');
    inputConfirmId.attr('type','hidden');
    inputConfirmId.attr('name','fileName');
    inputConfirmId.attr('value',fileName);
    $('body').append(form);
    form.append(inputConfirmId);
    form.submit();
    form.remove();
}
function exportD(sid){
    var formDate={social_card_id:sid};
    layer.load(2,{shade: [0.4, '#000']});
    $.ajax({
        url:$WEB_ROOT_PATH+"/dhccApi/DecompositionHospitalRest/expDate",
        type: "POST",
        data: formDate,
        dataType: "json",
        async: true,
        success: function(result){
            layer.closeAll('loading');
            if(result.operateSuccess){
                var fileName = result.fileName;
                downLoadXsl(fileName);
            } else {
                layer.alert("下载异常");
            }
        },
        error: function(){
            layer.closeAll('loading');
            layer.msg("数据导出失败，请联系管理员！");
        }
    });
    return false;
}
//批量导出
$("#export").click(function(){
        var route = $("input[name='route']").val();//就诊途径
        //var insurance = $("input[name='insurance']").val();//险种类型
        var hospitalCode = $("input[name='hospitalCode']").val();//医院编码
        var hospitalName = $("input[name='hospitalName']").val();//医院名称
        var settlementTime = $("input[name='settlementTime']").val();//结算时间
        var departmentName = $("input[name='departmentName']").val();//科室名称
        var day = $("#day").val();//分解住院天数维度
        var decomposedHospitalStay = $("input[name='decomposedHospitalStay']").val();//分解住院天数
        var formDate={};
        var sid=null;
        if(sid != null && sid != ""){
            formDate={social_card_id:sid};
        }else{
            formDate={route:route,hospitalCode:hospitalCode,hospitalName:hospitalName,
                settlementTime:settlementTime,departmentName:departmentName,day:day,decomposedHospitalStay:decomposedHospitalStay};
        }
        layer.load(2,{shade: [0.4, '#000']});
        $.ajax({
            url:$WEB_ROOT_PATH+"/dhccApi/DecompositionHospitalRest/expDate",
            type: "POST",
            data: formDate,
            dataType: "json",
            async: true,
            success: function(result){
                layer.closeAll('loading');
                if(result.operateSuccess){
                    var fileName = result.fileName;
                    downLoadXsl(fileName);
                } else {
                    layer.alert("下载异常");
                }
            },
            error: function(){
                layer.closeAll('loading');
                layer.msg("数据导出失败，请联系管理员！");
            }
        });
        return false;
});

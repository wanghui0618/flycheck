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
    laydate.render({
        elem: '#SettlementDate'
        ,format: 'yyyyMMdd'
        ,range: true  //中间以‘/’分开
        ,max: 0 //7天后
    });
    laydate.render({
        elem: '#AdmissionDate'
        ,format: 'yyyyMMdd'
        ,range: true  //中间以‘/’分开
        ,max: 0 //7天后
    });
    laydate.render({
        elem: '#DischargeDate'
        ,format: 'yyyyMMdd'
        ,range: true  //中间以‘/’分开
        ,max: 0 //7天后
    });
});
//搜索方法
function search(){
    var hospitalCode = $("input[name='hospitalCode']").val();//医院编码
    var hospitalName = $("input[name='hospitalName']").val();//医院名称
    var SettlementDate = $("input[name='SettlementDate']").val();//结算时间
    var TypeOfTreatment = $("#TypeOfTreatment").val();//就医类型
    var DiagnosticCode = $("input[name='DiagnosticCode']").val();//诊断编码
    var DiagnosticName = $("input[name='DiagnosticName']").val();//诊断名称
    var AdmissionDate = $("input[name='AdmissionDate']").val();//入院日期
    var DischargeDate = $("input[name='DischargeDate']").val();//出院日期
    var itemId = $("input[name='hospitalLevelDrugsOnly.itemId']").val();//医保项目编码
    var itemId1 = $("#getItemId").val();//医保项目编码
    var itemName = $("input[name='hospitalLevelDrugsOnly.itemName']").val();//医保项目名称
    var itemName1 = $("#getItemName").val();//医保项目名称
    var  formDate={hospitalCode:hospitalCode,hospitalName:hospitalName,SettlementDate:SettlementDate,TypeOfTreatment:TypeOfTreatment,
        DiagnosticCode:DiagnosticCode,DiagnosticName:DiagnosticName,AdmissionDate:AdmissionDate,DischargeDate:DischargeDate,
        itemId:itemId1,itemName:itemName1};
    console.log(formDate);
    console.log(itemId);
    console.log(itemId1);
    console.log(itemName);
    console.log(itemName1);
    totle(formDate);
    if(TypeOfTreatment == "BeHospitalized"){
        HospitalLevelDrugsOnly(formDate);
    }else if(TypeOfTreatment == "Outpatient"){
        OutpatientHospitalLevelDrugsOnly(formDate);
    }
};
function HospitalLevelDrugsOnly(formDate){
    var table = layui.table;
    var index = layer.load(0); //添加laoding,0-2两种方式
    table.render({
        elem: '#dg'
        ,loading:true
        ,url: $WEB_ROOT_PATH+'/dhccApi/HospitalLevelDrugsOnlyRest/getHospitalLevelDrugsOnly'
        ,cellMinWidth: 80
        ,loading: true  //翻页加loading
        ,height: tableHeight-55
        ,cols: [[
            //文本靠左，数字靠右，等长居中
            {type: 'numbers', fixed: 'left',title:"序号" ,fixed: 'left',align:'right'}
            ,{field: 'HOSPITAL_ID', title: '医院编码',align:'center',width:165}
            ,{field: 'HOSPITAL_NAME', title: '医院名称',align:'right',width:165}
            ,{field: 'ZYH', title: '住院号',align:'right',width:165}
            ,{field: 'DISCHARGE_DEPT_NAME', title: '就诊科室',align:'right',width:180}
            ,{field: 'PATIENT_NAME', title: '患者姓名',align:'right',width:90}
            ,{field: 'ADMISSION_DATE', title: '入院日期',align:'right',width:170,templet:function(d)
                {
                    return  layui.util.toDateString(d.ADMISSION_DATE);
                }
            }
            ,{field: 'DISCHARGE_DATE', title: '出院日期',align:'right',width:170,templet:function(d)
                {
                    return  layui.util.toDateString(d.DISCHARGE_DATE);
                }
            }
            ,{field: 'ZYTS', title: '住院天数',align:'left',width:90}
            ,{field: 'TOTAL_AMOUNT', title: '医疗总费用',align:'right',width:140}
            ,{field: 'BENEFIT_TYPE', title: '险种类型',align:'right',width:140}
            ,{field: 'ITEM_ID', title: '医保项目编码',align:'right',width:140}
            ,{field: 'ITEM_NAME', title: '医保项目名称',align:'right',width:140}
            ,{field: 'ITEM_ID_HOSP', title: '医院项目编码',align:'right',width:140}
            ,{field: 'ITEM_NAME_HOSP', title: '医院项目名称',align:'right',width:140}
            ,{field: 'WESTERN_MEDICINE_FEE', title: '西药费',align:'right',width:130}
            ,{field: 'CHINESE_MEDICINE_YINPIAN', title: '中药饮片费',align:'right',width:130}
            ,{field: 'CHINESE_MEDICINE_FORM', title: '中成药费',align:'right',width:130}
        ]]
        ,page: true
        ,where:formDate
        ,done: function (res) {//返回数据执行回调函数
            layer.close(index);    //返回数据关闭loading
        }
    });
}
function OutpatientHospitalLevelDrugsOnly(formDate){
    var table = layui.table;
    var index = layer.load(0); //添加laoding,0-2两种方式
    table.render({
        elem: '#dg'
        ,loading:true
        ,url: $WEB_ROOT_PATH+'/dhccApi/HospitalLevelDrugsOnlyRest/getHospitalLevelDrugsOnly'
        ,cellMinWidth: 80
        ,loading: true  //翻页加loading
        ,height: tableHeight-55
        ,cols: [[
            //文本靠左，数字靠右，等长居中
            {type: 'numbers', fixed: 'left',title:"序号" ,fixed: 'left',align:'right'}
            ,{field: 'HOSPITAL_ID', title: '医院编码',align:'center',width:165}
            ,{field: 'HOSPITAL_NAME', title: '医院名称',align:'right',width:165}
            ,{field: 'DISCHARGE_DEPT_NAME', title: '就诊科室',align:'right',width:180}
            ,{field: 'PATIENT_NAME', title: '患者姓名',align:'right',width:90}
            ,{field: 'TOTAL_AMOUNT', title: '医疗总费用',align:'right',width:140}
            ,{field: 'BENEFIT_TYPE', title: '险种类型',align:'right',width:140}
            ,{field: 'ITEM_ID', title: '医保项目编码',align:'right',width:140}
            ,{field: 'ITEM_NAME', title: '医保项目名称',align:'right',width:140}
            ,{field: 'ITEM_ID_HOSP', title: '医院项目编码',align:'right',width:140}
            ,{field: 'ITEM_NAME_HOSP', title: '医院项目名称',align:'right',width:140}
            ,{field: 'WESTERN_MEDICINE_FEE', title: '西药费',align:'right',width:130}
            ,{field: 'CHINESE_MEDICINE_YINPIAN', title: '中药饮片费',align:'right',width:130}
            ,{field: 'CHINESE_MEDICINE_FORM', title: '中成药费',align:'right',width:130}
        ]]
        ,page: true
        ,where:formDate
        ,done: function (res) {//返回数据执行回调函数
            layer.close(index);    //返回数据关闭loading
        }
    });
}
//重置按钮
function reset(){
    $('#getOrgName').combogrid("setValue", "");//医疗机构
    $("input[name='SettlementDate']").val("");//结算时间

    $("input[name='DiagnosticCode']").val("");//诊断编码
    $("input[name='DiagnosticName']").val("");//诊断名称
    $("input[name='AdmissionDate']").val("");//入院日期
    $("input[name='DischargeDate']").val("");//出院日期

    $("input[name='itemId']").val("");//医保项目编码
    $("input[name='itemName']").val("");//医保项目名称
    $("input[name='itemIdHos']").val("");//医院项目编码
    $("input[name='itemNameHos']").val("");//医院项目名称
}
//给汇总按钮赋值
function totle(formDate){
    $.ajax({
        url:$WEB_ROOT_PATH+'/dhccApi/HospitalLevelDrugsOnlyRest/getCountAndTotle',
        type: "POST",
        data:formDate,
        dataType: "json",
        async: true,
        success: function(result){
        	console.log("aa="+result[0].TOTALNUMBEROFCASES)
        	console.log("aa="+result[0].SUMTOTALAMOUNT)
            $("#total_number_of_cases").html(result[0].TOTALNUMBEROFCASES);
            $("#total_amount").html(result[0].SUMTOTALAMOUNT);
        },
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

//批量导出
$("#export").click(function(){
    var hospitalCode = $("input[name='hospitalCode']").val();//医院编码
    var hospitalName = $("input[name='hospitalName']").val();//医院名称
    var SettlementDate = $("input[name='SettlementDate']").val();//结算时间
    var TypeOfTreatment = $("#TypeOfTreatment").val();//就医类型
    var DiagnosticCode = $("input[name='DiagnosticCode']").val();//诊断编码
    var DiagnosticName = $("input[name='DiagnosticName']").val();//诊断名称
    var AdmissionDate = $("input[name='AdmissionDate']").val();//入院日期
    var DischargeDate = $("input[name='DischargeDate']").val();//出院日期
    var itemId = $("input[name='itemId']").val();//医保项目编码
    var itemName = $("input[name='itemName']").val();//医保项目名称
    var  formDate={hospitalCode:hospitalCode,hospitalName:hospitalName,SettlementDate:SettlementDate,TypeOfTreatment:TypeOfTreatment,
        DiagnosticCode:DiagnosticCode,DiagnosticName:DiagnosticName,AdmissionDate:AdmissionDate,DischargeDate:DischargeDate,
        itemId:itemId,itemName:itemName};
    if(TypeOfTreatment == "BeHospitalized"){
        BeHospitalized(formDate);
    }else if(TypeOfTreatment == "Outpatient"){
        OutpatientHospital(formDate);
    }

});
//门诊导出
function OutpatientHospital(formDate){
    layer.load(2,{shade: [0.4, '#000']});
    $.ajax({
        url:$WEB_ROOT_PATH+"/dhccApi/HospitalLevelDrugsOnlyRest/exportOutpatient",
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
//住院导出
function BeHospitalized(formDate){
    layer.load(2,{shade: [0.4, '#000']});
    $.ajax({
        url:$WEB_ROOT_PATH+"/dhccApi/HospitalLevelDrugsOnlyRest/exportBeHospitalized",
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
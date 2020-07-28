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
        ,url: $WEB_ROOT_PATH+'/app/js/bigDataAntiFraud/operationUseItemViolationInfo.json'
        ,cellMinWidth: 80
        ,height: 425
        ,where: {  ilegalChild: '1'  }
        ,cols: [[
            {type: 'numbers', title: '序号' }
            // ,{field: 'firstResult',width:100,align:'center', title: '初审结果' }
            // ,{field:'machineAudit',width:100,align:'center', title: '机审结果'}
            // ,{field:'itemNum',width:100,align:'center', title: '项目编号'}
            // ,{field:'itemName',width:100,align:'center', title: '项目名称'}
            // ,{field:'medicalType',width:100,align:'center', title: '药品类别'}
            // ,{field:'itemCost',width:100,align:'center', title: '项目金额'}
            // ,{field:'totalCost',width:100,align:'center', title: '总金额'}
            // ,{field:'itemAmount',width:100,align:'center', title: '项目数量'}
            // ,{field:'itemPrice',width:100,align:'center', title: '项目单价'}
            // ,{field:'itemStandards',width:100,align:'center', title: '项目规格'}
            // ,{field:'',width:100,align:'center', title: '剂型'}
            // ,{field:'',width:100,align:'center', title: '限价金额'}
            // ,{field:'',width:100,align:'center', title: '部分统筹'}
            // ,{field:'',width:100,align:'center', title: '部分自付'}
            // ,{field:'',width:150,align:'center', title: '门诊处方编号'}
            // ,{field:'',width:100,align:'center', title: '自付金额'}
            // ,{field:'',width:100,align:'center', title: '单词用量'}
            // ,{field:'',width:100,align:'center', title: '服用频次'}
            // ,{field:'',width:100,align:'center', title: '用药天数'}
            // ,{field:'',width:100,align:'center', title: '报销金额'}
            // ,{field:'',width:100,align:'center', title: '违规类型'}
            // ,{field:'',width:100,align:'center', title: '报销级别'}
            // ,{field:'',width:100,align:'center', title: '收费类别'}
            // ,{field:'',width:100,align:'center', title: '给药途径'}
            // ,{field:'',width:100,align:'center', title: '用量单位'}
            // ,{field:'',width:150,align:'center', title: '费用发生时间'}
            // ,{field:'',width:100,align:'center', title: '全额统筹'}

            // {field:'ck',checkbox:true,width:40},
            // {field:'id',title:'ID',hidden:true,width:80}
            ,{field:'userStatus', title: '初审结果',width:150}
            ,{field:'sysStatus', title: '机审结果',width:100,formatter:function(value){
                    var a =value;
                    if(a=="1"){
                        a="疑似违规";
                    }else if(a=="0"){
                        a="违规";
                    }else if(a=="2"){
                        a="正常";
                    }else if(a==null || a=="null"){
                        a="";
                    }
                    return '<span >'+ a +'</span>'
                }}
            ,{field:'itemCode', title: '项目编号',width:150}
            ,{field:'itemName', title: '项目名称',width:150}
            ,{field:'drugType', title: '药品类别',width:80}
            ,{field:'itemCost', title: '项目金额'}
            ,{field:'sumAmount', title: '总金额'}
            ,{field:'itemNum', title: '项目数量'}
            ,{field:'itemPrice', title: '项目单价'}
            ,{field:'itemStandard', title: '项目规格',width:80}
            ,{field:'doseForm', title: '剂型'}
            ,{field:'limitPrice', title: '限价金额'}
            ,{field:'partialOrdination', title: '部分统筹'}
            ,{field:'partialPayment', title: '部分自付'}
            ,{field:'recipelId', title: '门诊处方编号'}
            ,{field:'selfPayAmount', title: '自付金额'}
            ,{field:'singleDose', title: '单次用量'}
            ,{field:'takeFrequence', title: '服用频次'}
            ,{field:'useDay', title: '用药天数'}
            ,{field:'applyPayAmount', title: '报销金额'}
            ,{field:'ilegalType', title: '违规类型'}
            ,{field:'applyPayLevel', title: '报销级别',formatter: function(value){
                    var a =value;
                    if(a=="1"){
                        a="甲类";
                    }else if(a=="2"){
                        a="乙类";
                    }else if(a=="3"){
                        a="丙类";
                    }else if(a==null || a=="null"){
                        a="";
                    }
                    return '<span >'+ a +'</span>'
                }}
            /*,{field:'billingNo', title: '收费单据号'}*/
            ,{field:'chargeType', title: '收费类别'}
            ,{field:'deliverWay',title: '给药途径'}
            ,{field:'doseUnit', title: '用量单位'}
            ,{field:'feeCreateDate', title: '费用发生时间',width:100}
            ,{field:'fullOrdination', title: '全额统筹'}
            ,{field:'fullPayment', title: '全额自付'}
            ,{field:'isInsuranceProject', title: '是否医保项目',formatter: function(value){
                    var a =value;
                    if(a=="0"){
                        a="否";
                    }else if(a=="1"){
                        a="是"
                    }else if(a==null || a=="null"){
                        a="";
                    }
                    return '<span >'+ a +'</span>'
                }}

        ]]

        ,page: true
    });









    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
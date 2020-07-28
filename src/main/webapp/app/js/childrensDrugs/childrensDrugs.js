var obj = {"symbol":$("#symbol").val(),"childrensDrugs.patientAge":$("#patientAge").val()};



layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
    var $ = layui.$
        ,form = layui.form
        ,laydate = layui.laydate
        ,table = layui.table;

    //结算日期
    laydate.render({
        elem: '#balanceDate'
        ,trigger:'click'
        ,format:'yyyyMMdd'
        ,range: true
    });

    //入院日期
    laydate.render({
        elem: '#admissionDate'
        ,trigger:'click'
        ,format:'yyyyMMdd'
        ,range: true
    });

    //出院日期
    laydate.render({
        elem: '#dischargeDate'
        ,trigger:'click'
        ,format:'yyyyMMdd'
        ,range: true
    });


    table.render({
        elem: '#dataTable'
        ,url: $WEB_ROOT_PATH+"/dhccApi/childrensDrugs/childrensDrugs/childrensDrugs"
        ,cellMinWidth: 70
        ,height: tableHeight-80
        ,cols: [[
            {type: 'numbers', width:70, title: '序号'}
            ,{title:'操作', toolbar: '#barDemo', width:100}
            ,{field: 'hospitalId',width:120,align:'center', title: '医疗机构编码'}
            ,{field:'hospitalName', width:220,align:'center',title: '医疗机构名称' }
            ,{field:'zyh',width:145, align:'center',title: '住院号' }
            ,{field:'admissionDeptName', width:100,align:'center',title: '就诊科室' }
            ,{field:'patientName', width:100,align:'center',title: '患者姓名' }
            ,{field:'patientAge', width:100,align:'center',title: '患者年龄' }
            ,{field:'admissionDate', width:105,align:'center',title: '入院日期' }
            ,{field:'dischargeDate',width:105, align:'center',title: '出院日期' }
            ,{field:'zyts',width:100, align:'center',title: '住院天数' }
            ,{field:'totalAmount', width:120,align:'center',title: '医疗总费用' }
            ,{field:'benefitType',width:100, align:'center',title: '险种类型' }
            ,{field:'westernMedicineFee', width:120,align:'center',title: '西药费' }
            ,{field:'chineseMedicineYinpian', width:120,align:'center',title: '中药饮片费' }
            ,{field:'chineseMedicineForm', width:120,align:'center',title: '中成药费' }
        ]]
        ,page: true
        ,done: function() {
            gather(obj)
        }
        ,where: {
            "childrensDrugs.patientAge" :obj["childrensDrugs.patientAge"],
            "symbol" : obj.symbol
        }

    });

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        console.log(field);
        //执行重载
        table.render({
            elem: '#dataTable'
            ,url: $WEB_ROOT_PATH+"/dhccApi/childrensDrugs/childrensDrugs/childrensDrugs"
            ,cellMinWidth: 70
            ,height: tableHeight-80
            ,cols: [[
                {type: 'numbers', width:70, title: '序号'}
                ,{title:'操作', toolbar: '#barDemo', width:100}
                ,{field: 'hospitalId',width:120,align:'center', title: '医疗机构编码'}
                ,{field:'hospitalName', width:220,align:'center',title: '医疗机构名称' }
                ,{field:'zyh',width:145, align:'center',title: '住院号' }
                ,{field:'admissionDeptName', width:100,align:'center',title: '就诊科室' }
                ,{field:'patientName', width:100,align:'center',title: '患者姓名' }
                ,{field:'patientAge', width:100,align:'center',title: '患者年龄' }
                ,{field:'admissionDate', width:105,align:'center',title: '入院日期' }
                ,{field:'dischargeDate',width:105, align:'center',title: '出院日期' }
                ,{field:'zyts',width:100, align:'center',title: '住院天数' }
                ,{field:'totalAmount', width:120,align:'center',title: '医疗总费用' }
                ,{field:'benefitType',width:100, align:'center',title: '险种类型' }
                ,{field:'westernMedicineFee', width:120,align:'center',title: '西药费' }
                ,{field:'chineseMedicineYinpian', width:120,align:'center',title: '中药饮片费' }
                ,{field:'chineseMedicineForm', width:120,align:'center',title: '中成药费' }
            ]]
            ,page : {curr:1}
            ,where: field
            ,done: function() {
                gather(field)
            }

        });
    });

    //明细
    table.on('tool(dataTable)', function(obj){
        if(obj.event === 'searcDetail'){
            var data =  {'hisid':obj.data.hisid,'itemId':$('#getItemId').val(),'itemName':$('#getItemName').val()};
            var formIndex = layer.open({
                type: 2,
                area: ['1100px', '500px'],
                content:$WEB_ROOT_PATH + '/childrensDrugs/detail',
                success: function(layero, index){
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    iframeWin.initData(data);
                }
            });
            return false;
        }
    });

    //重置
    form.on('submit(LAY-user-front-reset)', function () {
        $('input').val('');
        $('#symbol').val('');
    });

    //导出
    form.on('submit(LAY-user-front-export)', function(data){
        //执行重载
        var field = data.field;
        var param = encodeURI(JSON.stringify(field));
        window.open($WEB_ROOT_PATH+"/dhccApi/childrensDrugs/childrensDrugs/exportExcel?param="+param);
    });

    //自定义表单验证（年龄）
    form.verify({
        validateMoney: function(value){ //value：表单的值
            if(value!=""){
                if(! /^((?!0)\d{1,2}|100)$/.test(value)){
                    return '请输入1-100之间的整数!';
                }
            }
        }
    });

});

/**
 * 汇总
 */
function gather(data) {
    $.ajax({
        url: $WEB_ROOT_PATH + '/dhccApi/childrensDrugs/childrensDrugs/gather',
        type: "post",
        dataType: "json",
        data: data,
        success: function (res) {
            $("#medicalCount").text(res.data[0].medicalCount);
            $('#detailCount').text(res.data[0].detailCount);
            if(res.data[0].medicalTotal!=null){
                $('#medicalTotal').text(res.data[0].medicalTotal);
            }else{
                $('#medicalTotal').text('0.00');
            }
            if(res.data[0].detailTotal!=null){
                $('#detailTotal').text(res.data[0].detailTotal);
            }else{
                $('#detailTotal').text('0.00');
            }
        }
    });
}

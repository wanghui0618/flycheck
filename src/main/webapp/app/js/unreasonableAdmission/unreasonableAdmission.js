layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'laydate', 'jquery'], function () {
    var $ = layui.$
        , form = layui.form
        , $ = layui.jquery
        , table = layui.table;

    var laydate = layui.laydate;
    laydate.render({
        elem: '#billDate'
        , format: 'yyyyMMdd'
        , range: true
    });
    laydate.render({
        elem: '#admissionDate'
        , format: 'yyyyMMdd'
        , range: true
    });
    laydate.render({
        elem: '#dischargeDate'
        , format: 'yyyyMMdd'
        , range: true
    });
    table.render({
        elem: '#unreasonableAdmission'
        , height: 500
        , url: $WEB_ROOT_PATH + '/dhccApi/unreasonableAdmission/unreasonableAdmission/listUnreasonableAdmission'
        , cols: [[ //表头
            {type: 'numbers', title: '序号', width: 80, fixed: 'left'}
            ,{ title:'操作', toolbar: '#caseInfo', width:'100', fixed: 'left'}
            , {field: 'hospitalId', title: '医疗机构编码', width: 115}
            , {field: 'hospitalName', title: '医疗机构名称', width: 125}
            , {field: 'zyh', title: '住院号', width: 160}
            , {field: 'patientId', title: '个人编码', width: 130}
            , {field: 'admissionDeptName', title: '就诊科室', width: 90}
            , {field: 'patientName', title: '患者姓名', width: 90}
            , {field: 'admissionDate', title: '入院日期', width: 110}
            , {field: 'dischargeDate', title: '出院日期', width: 110}
            , {field: 'zyts', title: '住院天数', width: 90}
            , {field: 'totalAmount', title: '医疗总费用', width: 100}
            , {field: 'benefitType', title: '险种类型', width: 90}
            , {field: 'westernMedicineFee', title: '西药费', width: 90}
            , {field: 'chineseMedicineYinpian', title: '中药费', width: 90}
            , {field: 'chineseMedicineForm', title: '中成药费', width: 90}
            , {field: 'consultationFee', title: '治疗费', width: 90}
            , {field: 'inspectionFee', title: '检查费', width: 90}
            , {field: 'yfzb', title: '药费占比(%)', width: 130,templet:function(d){
            	var yfzb=d.yfzb;
            	var aa;
            	 aa=yfzb.toString();
            	 var str = aa.indexOf(".");
             	yfzb=aa.substring(0,str+3);
            	
            	return yfzb
            }}
            , {field: 'jcfzb', title: '检查费占比(%)', width: 130,templet:function(d){
            	var jcfzb=d.jcfzb;
            	var aa;
            	 aa=jcfzb.toString();
            	 var str = aa.indexOf(".");
            	 jcfzb=aa.substring(0,str+3);
            	
            	return jcfzb
            }}
            , {field: 'zlfzb', title: '治疗费占比(%)', width: 130,templet:function(d){
            	var zlfzb=d.zlfzb;
            	var aa;
            	 aa=zlfzb.toString();
            	 var str = aa.indexOf(".");
            	 zlfzb=aa.substring(0,str+3);
            	
            	return zlfzb
            }}

        ]]
        , done: function (res, curr, count) {
            sumCount()
        }
        , page: true //是否显示分页
    });
    table.on('tool(unreasonableAdmission)', function(obj){

        if(obj.event === 'selectDetail'){
            var data =  {'hisid':obj.data.hisid};
            var formIndex = layer.open({
                type: 2,
                area: ['1100px', '500px'],
                content:$WEB_ROOT_PATH + '/unreasonableAdmission/caseInfo_form',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                success: function(layero, index){
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();

                    iframeWin.initData(data);
                }
            });
            return false;
        }
    });
    //表单验证
        layui.form.verify({
            percentage:
                function (value, item) {
                    if (value !== "") {
                        if (!new RegExp("^-?[1-9]\\d*$").test(value)) {
                            return '占比请输入正整数';
                        }
                    }
                }
        });
    //监听搜索
    form.on('submit(LAY-front-search-to)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('unreasonableAdmission', {
            where: field
            , page: {curr: 1}
            , done: function (res, curr, count) {
                sumCount(field)
            }
        });
    });
    //导出监听
    form.on('submit(LAY-user-front-export)', function (data) {
        var field = data.field;
        var param=encodeURI(JSON.stringify(field));
   /*     var unreasonableAdmission={};
        unreasonableAdmission.hospitalId= field['unreasonableAdmission.hospitalId'];
        unreasonableAdmission.hospitalName= field['unreasonableAdmission.hospitalName'];
        unreasonableAdmission.billDateStr= field['unreasonableAdmission.billDateStr'];
        unreasonableAdmission.admissionDateStr= field['unreasonableAdmission.admissionDateStr'];
        unreasonableAdmission.dischargeDateStr= field['unreasonableAdmission.dischargeDateStr'];
        unreasonableAdmission.costOfTreatment= field['unreasonableAdmission.costOfTreatment'];
        unreasonableAdmission.inspectionFeeRatio= field['unreasonableAdmission.inspectionFeeRatio'];
        unreasonableAdmission.proportionOfMedicines= field['unreasonableAdmission.proportionOfMedicines'];
        unreasonableAdmission.code1= field['unreasonableAdmission.code1'];
        unreasonableAdmission.code2= field['unreasonableAdmission.code2'];
        unreasonableAdmission.code3= field['unreasonableAdmission.code3'];*/
        // var url = $WEB_ROOT_PATH + '/dhccApi/unreasonableAdmission/unreasonableAdmission/exportExcel/';
        // window.location.href = url;
        window.open($WEB_ROOT_PATH + '/dhccApi/unreasonableAdmission/unreasonableAdmission//exportExcel?param='+param);
        return false;
    });
});
function restForm() {
    layui.form.val("unreasonableAdmission_form", {
        "unreasonableAdmission.hospitalId": ""
        , "unreasonableAdmission.hospitalName": ""
        , "unreasonableAdmission.billDateStr": ""
        , "unreasonableAdmission.admissionDateStr": ""
        , "unreasonableAdmission.dischargeDateStr": ""
        , "unreasonableAdmission.costOfTreatment": ""
        , "unreasonableAdmission.inspectionFeeRatio":""
        , "unreasonableAdmission.proportionOfMedicines":""
        , "unreasonableAdmission.code1":">"
        , "unreasonableAdmission.code2":">"
        , "unreasonableAdmission.code3":">"
    });
}


function sumCount(data) {
    layui.$.ajax({
        url: $WEB_ROOT_PATH + '/dhccApi/unreasonableAdmission/unreasonableAdmission/countUnreasonableAdmissions',
        type: "post",
        dataType: "json",
        data: data,
        success: function (data) {
            if (data.data[0] != null && data.data[0] !== "") {
                $("#rowsum").text(data.data[0].rowsum);
                $("#sumAmount").text(data.data[0].sumAmount);
            } else {
                $("#rowsum").text(0);
                $("#sumAmount").text(0);

            }

        }
    });
}




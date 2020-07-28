var indexAll;
var tableAll;
var indexAdd;
//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index'//主入口模块
}).use(['index', 'table','laydate','form'], function () {
    var  form = layui.form
        , table = layui.table
        ,laydate = layui.laydate;
    var formSelects = layui.formSelects;
    tableAll=table;
    laydate.render({
        elem: '#billData'
        ,type:'date'
        ,format:'yyyyMMdd'
        ,range: true //或 range: '~' 来自定义分割字符
        });
    laydate.render({
        elem: '#admissionTime'
            ,type:'date'
            ,format:'yyyyMMdd'
    });
    laydate.render({
        elem: '#dischargedTime'
        ,type:'date'
        ,format:'yyyyMMdd'
    });

        table.render({
            elem: '#personalInformationInquiryTable'
            ,height: tableHeight
            ,url:$WEB_ROOT_PATH + '/dhccApi/flyTreatmentFeeCount/personalInformationInquiry/search'
            ,defaultToolbar: [ 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,cols: [
                [

                    {type: 'numbers',title: '序号' ,width:'50'}
                    ,{ title:'操作', toolbar: '#barDemo', width:'100'}
                    , {field: 'hisid', align: 'center', title: '结算号',width:'200' }
                    , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:'120' }
                    , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:'240'}
                    , {field: 'zyh', align: 'center', title: '住院号',width:'160'}
                    , {field: 'patientId', align: 'center', title: '身份证号码',width:'220'}
                    , {field: 'patientName', align: 'center', title: '患者姓名',width:'102'}
                    , {field: 'dischargeDeptName', align: 'center', title: '出院科室',width:'177'}
                    , {field: 'dischargeDiseaseNameMain', align: 'center', title: '出院诊断',width:'177'}
                    , {field: 'admissionDate', align: 'center', title: '入院日期',width:'110'}
                    , {field: 'dischargeDate', align: 'center', title: '出院日期',width:'116'}
                    , {field: 'zyts', align: 'center', title: '住院天数',width:'100'}
                    , {field: 'totalAmountBasy', align: 'center', title: '医疗总费用',width:'115'}
                    , {field: 'benefitType', align: 'center', title: '险种类型',width:'138'}
                    , {field: 'DrugFee', align: 'center', title: '药品费',width:'100'}
                    , {field: 'inspectionFee', align: 'center', title: '检查费',width:'100'}
                    , {field: 'accommodationFee', align: 'center', title: '床位费',width:'100'}
                    , {field: 'diagnosisFee', align: 'center', title: '诊察费',width:'100'}
                    , {field: 'treatmentFee', align: 'center', title: '治疗费',width:'100'}
                    , {field: 'testFee', align: 'center', title: '化验费',width:'100'}
                    , {field: 'nursingFee', align: 'center', title: '护理费',width:'100'}
                    , {field: 'materialFee', align: 'center', title: '卫生材料费',width:'108'}
                    , {field: 'consultationFee', align: 'center', title: '一般诊疗费',width:'108'}
                    , {field: 'registrationFee', align: 'center', title: '挂号费',width:'100'}
                    , {field: 'otherFee', align: 'center', title: '其他费',width:'100'}
                ]
            ]
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            ,
            page: true
        });

    //监听搜索
    form.on('submit(LAY-personalInformationInquiry-front-search)', function (data) {
        var field = data.field;
        if($("#belonginfo").val()==""){
            layer.alert("请选择就诊途径");
            return false;
        };
        //执行重载
        layui.table.reload('personalInformationInquiryTable', {

            where: field
            ,page: { curr: 1}
        });
        return false;
    });
    table.on('tool(personalInformationInquiryTable)', function(obj){

        if(obj.event === 'searcDetail'){
            var data =  {'selecthisid':obj.data.hisid,'selectBelong':$("#belonginfo").val()};
            var formIndex = layer.open({
                type: 2,
                area: ['1100px', '500px'],
                content:$WEB_ROOT_PATH + '/flyTreatmentFeeCount/detailedInformation',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                success: function(layero, index){
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    iframeWin.initData(data);
                }
            });
            return false;
        }
    });

    $("#exportInfo").click(function () {
        window.open($WEB_ROOT_PATH+'/dhccApi/flyTreatmentFeeCount/personalInformationInquiry/exportExcel');
        return false;
    });
$("#reset").click(function () {
    $('#getOrgName').combogrid("setValue", "");
    $('#getDiagName').combogrid("setValue", "");
});

var isMZ="";
    $("#testbelong").mouseleave(function(){
        //要触发的事件
        isMZ=$("#belonginfo").val();
        if(isMZ=="门诊"){
            $("#zyzyh").attr("disabled",true);
            $("#dischargedTime").attr("disabled",true);
            $("#admissionTime").attr("disabled",true);
            $("#zyzyh").val("");
            $("#dischargedTime").val("");
            $("#admissionTime").val("");
            return false;
        };
        $("#zyzyh").attr("disabled",false);
        $("#dischargedTime").attr("disabled",false);
        $("#admissionTime").attr("disabled",false);
        return false;
    });


});
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
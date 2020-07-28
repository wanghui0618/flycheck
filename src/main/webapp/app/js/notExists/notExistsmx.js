var indexAll;
var tableAll;
var indexAdd;
//初始化
var path_child="";
var inithisid="";
var initbelong="";
function initData(data,goal_path) {
    inithisid=data.selecthisid;
    path_child=goal_path;
    comsole.log("zi:"+path_child);
};
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','form'], function () {
    var  form = layui.form
        , table = layui.table
        ,laydate = layui.laydate;

    table.render({
        elem: '#detailedInformationTable'
        ,url:$WEB_ROOT_PATH + '/dhccApi/notExists/notExists/searchDetail'
        ,where:{"path":path_child}
        , height: tableHeight
        , cols: [
            [
                {type: 'numbers',title: '序号'}
                , {field: 'hisid', align: 'center', title: '单据号',width:"200"}
                , {field: 'billDate', align: 'center', title: '结算日期',width:"190"}
                , {field: 'bmiConveredAmount', align: 'center', title: '医保范围内金额',width:"141"}
                , {field: 'bmiPayAmount', align: 'center', title: '医保实际支付金额',width:"160"}
                , {field: 'cost', align: 'center', title: '金额',width:"70"}
                , {field: 'dischargeDeptId', align: 'center', title: '出院科室编码',width:"140"}
                , {field: 'dischargeDeptName', align: 'center', title: '出院科室名称',width:"180"}
                , {field: 'dischargeDiseaseNameMain', align: 'center', title: '出院诊断名称',width:"130"}
                , {field: 'doctorId', align: 'center', title: '主诊医师编码',width:"114"}
                , {field: 'doctorName', align: 'center', title: '主诊医师姓名',width:"133"}
                , {field: 'dosageForm', align: 'center', title: '剂型',width:"100"}
                , {field: 'drugSpec', align: 'center', title: '规格',width:"100"}
                , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:"125"}
                , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:"140"}
                , {field: 'itemId', align: 'center', title: '医保项目编码',width:"180"}
                , {field: 'itemIdHosp', align: 'center', title: '医院项目编码',width:"180"}
                , {field: 'itemName', align: 'center', title: '医保项目名称',width:"220"}
                , {field: 'itemNameHosp', align: 'center', title: '医院项目名称',width:"220"}
                , {field: 'month', align: 'center', title: '收费月份',width:"90"}
                , {field: 'num', align: 'center', title: '数量',width:"65"}
                , {field: 'pCategory', align: 'center', title: '费用类别',width:"120"}
                , {field: 'pType', align: 'center', title: '支付类别',width:"120"}
                , {field: 'pTypePct', align: 'center', title: '报销比例',width:"103"}
                , {field: 'packageUnit', align: 'center', title: '最小包装单位',width:"120"}
                , {field: 'patientId', align: 'center', title: '个人编码',width:"130"}
                , {field: 'unitPrice', align: 'center', title: '单价',width:"88"}
                , {field: 'usageDate', align: 'center', title: '项目使用日期',width:"200"}
                , {field: 'usageDateFlag', align: 'center', title: '项目使用日期标识',width:"165"}
                , {field: 'year', align: 'center', title: '收费年份',width:"110"}
                , {field: 'zyh', align: 'center', title: '住院号',width:"160"}
            ]
        ]
        , parseData: function (res) { //res 即为原始返回的数据
            console.log(res);
            console.log("hisid==="+inithisid)
            return {
                "code": 0, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
    ,where:{'detailedInformationVo.hisId':inithisid ,'detailedInformationVo.belong':initbelong}
    , page: true
    });
    //监听搜索
    form.on('submit(LAY-detailedInformation-front-search)', function (data) {
        var field = data.field;
        //执行重载
       layui.table.reload('detailedInformationTable', {
            where: field
            ,page: { curr: 1}
        });
    });
});

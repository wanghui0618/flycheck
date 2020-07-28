var form;
function initData(formDate){
    form = formDate;
}
//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element','laydate'], function(){
    var table =layui.table;
    var index = layer.load(0); //添加laoding,0-2两种方式
    table.render({
        elem: '#decompositionTable'
        ,loading:true
        ,url: $WEB_ROOT_PATH+'/dhccApi/DecompositionHospitalRest/getDetailsHospitalTable'
        ,loading: true  //翻页加loading
        ,height: tableHeight + 50
        ,cols: [[
            //文本靠左，数字靠右，等长居中
            {type: 'numbers', fixed: 'left',title:"序号" ,fixed: 'left',align:'right'}
            ,{field: 'HISID', title: '结算单据号',align:'center', width:190}
            ,{field: 'ZYH', title: '住院号',align:'center',width:150}
            ,{field: 'HOSPITAL_ID', title: '医院编码' ,align:'right',width:90}
            ,{field: 'HOSPITAL_NAME', title: '医院名称' ,align:'left',width:300}
            ,{field: 'DISCHARGE_DEPT_ID', title: '出院科室编码' ,align:'left',width:120}
            ,{field: 'DISCHARGE_DEPT_NAME', title: '出院科室名称' ,align:'left',width:140}
            ,{field: 'DISCHARGE_DISEASE_NAME_MAIN', title: '出院诊断名称' ,align:'left',width:140}
            ,{field: 'P_CATEGORY', title: '费用类别' ,align:'left',width:100}
            ,{field: 'USAGE_DATE', title: '项目使用日期' ,align:'left',width:170,templet:function(d)
                {
                    return  layui.util.toDateString(d.USAGE_DATE);
                }
            }
            ,{field: 'BILL_DATE', title: '结算日期' ,align:'left',width:170,templet:function(d)
                {
                    return  layui.util.toDateString(d.BILL_DATE);
                }
            }
            ,{field: 'ITEM_ID_HOSP', title: '医院项目编码' ,align:'left',width:250}
            ,{field: 'ITEM_NAME_HOSP', title: '医院项目名称' ,align:'left',width:330}
            ,{field: 'ITEM_ID', title: '医保项目编码' ,align:'left',width:250}
            ,{field: 'ITEM_NAME', title: '医保项目名称' ,align:'left',width:330}
            ,{field: 'UNIT_PRICE', title: '单价' ,align:'right',width:100}
            ,{field: 'NUM', title: '数量' ,align:'right',width:80}
            ,{field: 'COST', title: '金额' ,align:'right',width:120}
            ,{field: 'BMI_CONVERED_AMOUNT', title: '医保范围内金额',align:'right',width:140}
            ,{field: 'BMI_PAY_AMOUNT', title: '医保实际支付金额',align:'right',width:160}
            ,{field: 'P_TYPE_PCT', title: '报销比例',align:'right',width:120}
        ]]
        ,page: true
        ,where:form
        ,done:function (res) {   //返回数据执行回调函数
            layer.close(index);    //返回数据关闭loading
        }
    });
});

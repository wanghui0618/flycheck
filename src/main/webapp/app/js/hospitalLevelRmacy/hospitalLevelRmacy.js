var indexAll;
var tableAll;
var indexAdd;
//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','form','element','jquery'], function () {
    var  form = layui.form,
     element = layui.element
        , table = layui.table,
        jquery=layui.jquery
        ,laydate = layui.laydate;
    var typeGlobal="";
    
    tableAll=table;
    laydate.render({
        elem: '#billData'
        ,type:'date'
        ,format:'yyyyMMdd'
        ,range: true //或 range: '~' 来自定义分割字符
        });
    laydate.render({
        elem: '#admissionDate'
            ,type:'date'
            ,format:'yyyyMMdd'
            ,range: true
    });
    laydate.render({
        elem: '#dischargeDate'
        ,type:'date'
        ,format:'yyyyMMdd'
        ,range: true
    });
    
    table.render({
        elem: '#hospitalLevelRmacyTable'
        ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalLevelRmacy/hospitalLevelRmacy/limitedHospitalLevelZd'
        ,height: tableHeight
        ,cellMinWidth: 100
        ,where:{"type":$("#type").val()}
        ,
        cols: [
            [
                {type: 'numbers',title: '序号'}
                ,{ title:'操作', toolbar: '#barDemo', width:'100'}
                , {field: 'hisid', align: 'center', title: '明细表关联Id', hide:true}
                , {field: 'hospitalId', align: 'center',width:180, title: '医疗机构编码' }
                , {field: 'hospitalName', align: 'center',width:180,  title: '医疗机构名称'}
                , {field: 'pLevel', align: 'center',width:180,  title: '医疗机构级别'}
                , {field: 'zyh', align: 'center',width:180,  title: '住院号'}
                , {field: 'socialCardId', align: 'center',width:180,  title: '社保卡号'}
                , {field: 'dischargeDeptName', align: 'center',width:180,  title: '就诊科室'}
                , {field: 'patientName', align: 'center', title: '患者姓名'}
                , {field: 'admissionDiseaseId', align: 'center',width:180,  title: '诊断编码'}
                , {field: 'admissionDiseaseName', align: 'center',width:180,  title: '诊断名称'}
                , {field: 'admissionDate', align: 'center',width:180,  title: '入院日期'}
                , {field: 'dischargeDate', align: 'center',width:180,  title: '出院日期'}
                , {field: 'billDate', align: 'center',width:180,  title: '结算日期'}
                , {field: 'zyts', align: 'center', title: '住院天数'}
                , {field: 'totalAmount', align: 'center',width:180,  title: '医疗总费用'}
                , {field: 'benefitType', align: 'center', title: '险种类型'}
                , {field: 'westernMedicineFee', align: 'center', title: '西药费'}
                , {field: 'chineseMedicineYinpian', align: 'center', title: '中药饮片费'}
                , {field: 'chineseMedicineForm', align: 'center', title: '中成药费'}
            ]
        ],
        done: function(res, curr, count){
        	countMedical();
        	countMx();
        } 
        ,page: true
    }); 
    //下拉框监听事件
    form.on('select(type)', function (data) {
 	   var message=data.value;
 	   if(message=="1"){
 		 $("#admissionDate").hide();
 		$("#dischargeDate").hide();
 		 $("#admissionDates").hide();
  		$("#dischargeDates").hide();
 		//form.render('select');
 	   }else{
 		  $("#admissionDate").show();
 	 		$("#dischargeDate").show();
 	 		 $("#admissionDates").show();
 	  		$("#dischargeDates").show();
 	   }
    });
    //监听搜索
    form.on('submit(LAY-personalInformationInquiry-front-search)', function (data) {
        var field = data.field;
        var type=$("#type").val();
        //执行重载
        layui.table.reload('hospitalLevelRmacyTable', {
            where: field
            , page: {curr: 1}
        ,
        done: function(res, curr, count){
        	countMedical(field);
        	countMx(field);
        }
        });
    });
    
  //重置
    form.on('submit(LAY-user-front-reset)', function (data) {
    	$('#getOrgName').combogrid("setValue", " ");
    	$('#getDiagName').combogrid("setValue", " ");
    	$('#getOrgName').combogrid("setValue", "");
    	$('#getDiagName').combogrid("setValue", "");
    	$("#orgCode").val("");
    	$("#diagCode").val("");
    	$("#billData").val("");
    	$("#pLevel").val("");
    	$("#admissionDate").val("");
    	$("#dischargeDate").val("");
    	layui.form.render();
    });
    
  //导出
    form.on('submit(LAY-flyTreatmentFeeCountStatistics-front-search)', function(data){
    	var field = data.field;
    	var queryParams=encodeURI(JSON.stringify(field));
    	console.info(queryParams);
        //执行重载
        window.open($WEB_ROOT_PATH+"/dhccApi/hospitalLevelRmacy/hospitalLevelRmacy/exportExcel?queryParams="+queryParams);
    });
    
    table.on('tool(hospitalLevelRmacyTable)', function(obj){

        if(obj.event === 'searcDetail'){
            var data =  {'hisid':obj.data.hisid,"type":$("#type").val()};
            var formIndex = layer.open({
                type: 2,
                area: ['1100px', '500px'],
                content:$WEB_ROOT_PATH + '/hospitalLevelRmacy/detailedInformation',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                success: function(layero, index){
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    iframeWin.initData(data);
                }
            });
            return false;
        }
    });
    
    //汇总 主单
    function countMedical(data) {
        $.ajax({
            url: $WEB_ROOT_PATH + '/dhccApi/hospitalLevelRmacy/hospitalLevelRmacy/countMedical',
            type: "post",
            dataType: "json",
            data: data,
            success: function (res) {
                $("#total").text(res.data[0].total);
                $('#money').text(res.data[0].money);
            }
        });
    }
    
    //汇总 明细
    function countMx(data) {
        $.ajax({
            url: $WEB_ROOT_PATH + '/dhccApi/hospitalLevelRmacy/hospitalLevelRmacy/countMx',
            type: "post",
            dataType: "json",
            data: data,
            success: function (res) {
                $("#totalMx").text(res.data[0].totalMx);
                $('#moneyMx').text(res.data[0].moneyMx);
            }
        });
    }
    
    
   /* //监听行单击事件（双击事件为：rowDouble）
    table.on('row(personalInformationInquiryTable)', function(obj){
        var data = obj.data;
          window.location.href=$WEB_ROOT_PATH + '/flyTreatmentFeeCount/detailedInformation?hisid='+data.hisid+"&belong="+$("#belonginfo").val();//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });*/
});
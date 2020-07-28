layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var laydate = layui.laydate;
    	var hosName=getUrlParam(location.href,"hospitalName");
    	if(hosName!=null||hosName!= ""){
            $('#getOrgName').combogrid("setValue", hosName);//医疗机构
		}

	  //日期范围
	    laydate.render({
	        elem: '#settlementDate'
            ,format: 'yyyyMMdd'
            ,range: false  //中间以‘/’分开
            ,max: 0 //7天后
	        ,done: function(value, date, endDate){
	            inputDate=value;
	        }
	    });
    	search();
	   //按钮事件绑定底层方法-勿动
	   $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		   var type = $(this).data('type');
		   active[type] ? active[type].call(this) : '';
	   });
});
//搜索方法
function search(){
    var treatmentApproach = $("#treatmentApproach").val();//就诊途径
    var medicalName = $("input[name='CostStatisticsVo.medicalName']").val();//人群类别
    var hospitalCode = $("#getOrgName").val();//医院编码
    var hospitalName = $("#orgCode").val();//医院名称
    var balanceDate = $("input[name='balanceDate']").val();//结算时间
    var  formDate={medicalName:medicalName,hospitalCode:hospitalCode,hospitalName:hospitalName,
       				 balanceDate:balanceDate};
    console.log(formDate);
    if(treatmentApproach == "BeHospitalized"){
        BeHospitalized(formDate);
        test1(formDate);
        test3(formDate);
        test8(formDate);
        test10(formDate);
    }else if(treatmentApproach == "Outpatient") {
        Outpatient(formDate);
        test2(formDate);
        test4(formDate);
        test9(formDate);
        test11(formDate);
     }/*else if(treatmentApproach == "byC"){
        BeHospitalized(formDate);
        test1(formDate);
        test3(formDate);
        test8(formDate);
        test10(formDate);
        Outpatient(formDate);
        test2(formDate);
        test4(formDate);
        test9(formDate);
        test11(formDate);
	}*/
};
//重置按钮
function reset(){
	$("input").val("");
    $("input[name='CostStatisticsVo.medicalName']").val("");//人群类别
    $('#getOrgName').combogrid("setValue", "");//医疗机构
    $("input[name='balanceDate']").val("");//结算时间
}

function BeHospitalized(formDate){
    var table = layui.table;
    table.render({
        elem: '#hospitalizationTable'
        ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/dataStatistics'
        ,cellMinWidth: 80
        ,height:200
        ,cols: [[
            {field: 'month', width:60, title: '月份'}
            ,{field:'hospitalizationTime',width:100,align:'center', title: '住院人次' }
            ,{field:'hospitalizationNum',width:100,align:'center',title: '住院人数' }
            ,{field:'hospitalizationTotalNum',width:100,align:'center',title: '总住院日' }
            ,{field:'hospitalizationTotalCost',width:150,align:'center',title: '住院总费用(万元)' }
            ,{field:'hospitalizationBasicPayment',width:200,align:'center',title: '住院基本统筹支付(万元)' }
            ,{field:'hospitalizationMedInsurance',width:230,align:'center',title: '住院纳入医保范畴的费用(万元)' }
            ,{field:'bedFee',width:100,align:'center',title: '床日费(元)' }
            ,{field:'averageCost',width:120,align:'center',title: '次均费用(元)' }
        ]]
        ,done : function() {
            reloadTableFive(table.cache.hospitalizationTable);
            reloadTableFourZy(table.cache.hospitalizationTable);
        },page: false
        ,limit:12
        ,where:formDate
    });
}

function Outpatient(formDate){
    var table = layui.table;
    table.render({
        elem: '#outpatientTable'
        ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/outpatientDataStatistics'
        ,cellMinWidth: 80
        ,height:200
        ,cols: [[
            {field: 'month', width:60, title: '月份'}
            ,{field:'outpatientTime',width:100,align:'center',title: '门诊人次' }
            ,{field:'outpatientNum',width:100,align:'center',title: '门诊人数' }
            ,{field:'outpatientConsultation',width:100,align:'center',title: '门诊复诊率',templet:function(d){
                    var codex = d.outpatientConsultation;
                    codex+="%";
                    return codex;
                }}
            ,{field:'outpatientTotalCost',width:150,align:'center',title: '门诊总费用(万元)' }
            ,{field:'basicOverallPayment',width:155,align:'center',title: '基本统筹支付(万元)' }
            ,{field:'basicMedInsurance',width:230,align:'center',title: '符合基本医疗保险的费用(万元)' }
        ]]
        ,done : function() {
            reloadTableFourMz(table.cache.outpatientTable);
            document.getElementById("hospitalizationTable").style.display="none";  
        },page: false
        ,limit:12
        ,where:formDate
    });
}

function reloadTableFive(data){
    require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
        ],
        function (echarts) {
            echarts.init(document.getElementById('five')).dispose();//销毁前一个实例
            var memoBar = echarts.init(document.getElementById('five'));//构建下一个实例
            var option = {
                tooltip: {
                    show: true,
                    position: function(point, params, dom, rect, size){
                        return [point[0],0];
                    }
                },
                grid:{
                    y2:50
                },
                padding: [0, 0, 10, 10],  // 位置
                legend: {
                    padding: 10,    // [5, 10, 15, 20]
                    itemGap: 20,
                    left:'right',
                    data:['月度平均住院天数']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [],
                        axisLabel : {//坐标轴刻度标签的相关设置。
                            interval:0,
                            rotate:"30"
                        }

                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"月度平均住院天数",
                        "type":"line",
                        barWidth:30,
                        barMaxWidth:20,
                        "data":[],
                        itemStyle:{
                            normal:{
                                color:'#419bf9'
                            }
                        }
                    }
                ]
            };
            for(var i=0;i<data.length;i++){
                option.series[0].data.push((data[i].hospitalizationTotalNum/data[i].hospitalizationNum).toPrecision(3));
                option.xAxis[0].data.push(data[i].month+"月");
            }
            memoBar.setOption(option);
        });


}

function reloadTableFourZy(data){
    var width=$("#card1").css("width").replace("px","");
    $("#fourZy").css("width",width*0.93);
    require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
        ],
        function (echarts) {
            echarts.init(document.getElementById('fourZy')).dispose();//销毁前一个实例
            var memoBar = echarts.init(document.getElementById('fourZy'));//构建下一个实例
            var option = {
                tooltip: {
                    show: true,
                    position: function(point, params, dom, rect, size){
                        return [point[0],0];
                    }
                },
                grid:{
                    y2:50
                },
                padding: [0, 0, 10, 10],  // 位置
                legend: {
                    padding: 10,    // [5, 10, 15, 20]
                    itemGap: 20,
                    left:'right',
                    data:['月度平均费用-住院']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [],
                        axisLabel : {//坐标轴刻度标签的相关设置。
                            interval:0,
                            rotate:"30"
                        }

                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"月度平均费用-住院",
                        "type":"line",
                        barWidth:30,
                        barMaxWidth:20,
                        "data":[],
                        itemStyle:{
                            normal:{
                                color:'#419bf9'
                            }
                        }
                    }
                ]
            };
            for(var i=0;i<data.length;i++){
                var money=(data[i].hospitalizationTotalCost/data[i].hospitalizationTime)*10000
                option.series[0].data.push(money.toFixed(2));
                option.xAxis[0].data.push(data[i].month+"月");
            }
            memoBar.setOption(option);
        });


}

function reloadTableFourMz(data){
    var width=$("#card1").css("width").replace("px","");
    $("#fourMz").css("width",width*0.93);
    require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
        ],
        function (echarts) {
            echarts.init(document.getElementById('fourMz')).dispose();//销毁前一个实例
            var memoBar = echarts.init(document.getElementById('fourMz'));//构建下一个实例
            var option = {
                tooltip: {
                    show: true,
                    position: function(point, params, dom, rect, size){
                        return [point[0],0];
                    }
                },
                grid:{
                    y2:50
                },
                padding: [0, 0, 10, 10],  // 位置
                legend: {
                    padding: 10,    // [5, 10, 15, 20]
                    itemGap: 20,
                    left:'right',
                    data:['月度平均费用-门诊']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [],
                        axisLabel : {//坐标轴刻度标签的相关设置。
                            interval:0,
                            rotate:"30"
                        }

                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"月度平均费用-门诊",
                        "type":"line",
                        barWidth:30,
                        barMaxWidth:20,
                        "data":[],
                        itemStyle:{
                            normal:{
                                color:'#419bf9'
                            }
                        }
                    }
                ]
            };
            for(var i=0;i<data.length;i++){
                var money=(data[i].outpatientTotalCost/data[i].outpatientTime)*10000
                option.series[0].data.push(money.toFixed(2));
                option.xAxis[0].data.push(data[i].month+"月");
            }
            memoBar.setOption(option);
        });


}

function test1(formDate){
    var width=$("#card1").css("width").replace("px","");
    $("#one").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = ec.init(document.getElementById('one'));
            $.ajax({
                url: $WEB_ROOT_PATH+"/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/drugsDiagnosisTreatment",
                type : "post",
                async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType : "json",
                data: formDate,
                success : function(result) {
              /*      console.log(result);*/
                    if(result.data[0].medicalCost == 0 && result.data[0].treatmentCost ==0 && result.data[0].consumableCost==0){
                 	   myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : []
                            },
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '60%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : []
                            } ]
                        });
                 }else{
                    if(result.data.length>1){
                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : []
                            },
                            color:['#F93AF9','#F6C4CE','#39E9E9'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '60%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : []
                            } ]
                        });
                    }else{
                        var names=[];//定义两个数组
                        var nums=[];

                        names.push("药品");
                        var obj = new Object();
                        obj.name = "药品";
                        obj.value = result.data[0].medicalCost;
                        nums.push(obj);

                        names.push("诊疗");
                        var obj = new Object();
                        obj.name = "诊疗";
                        obj.value = result.data[0].treatmentCost;
                        nums.push(obj);

                        names.push("耗材");
                        var obj = new Object();
                        obj.name = "耗材";
                        obj.value = result.data[0].consumableCost;
                        nums.push(obj);

                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : names
                            },
                            color:['#F93AF9','#F6C4CE','#39E9E9'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '60%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : nums
                            } ]
                        });

                    }
                }
                }
            });
        }
    );
}

function test2(formDate){
    var width=$("#card1").css("width").replace("px","");
    $("#two").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = ec.init(document.getElementById('two'));

            $.ajax({
                url: $WEB_ROOT_PATH+"/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/drugsDiagnosisTreatmentMed",
                type : "post",
                async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType : "json",
				data:formDate,
                success : function(result) {
                    if(result.data.length>1){
                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : []
                            },
                            color:['#F93AF9','#F6C4CE','#39E9E9'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '60%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : []
                            } ]
                        });
                    }else{
                        var names=[];//定义两个数组
                        var nums=[];

                        names.push("药品");
                        var obj = new Object();
                        obj.name = "药品";
                        obj.value = result.data[0].medicalCost;
                        nums.push(obj);

                        names.push("诊疗");
                        var obj = new Object();
                        obj.name = "诊疗";
                        obj.value = result.data[0].treatmentCost;
                        nums.push(obj);

                        names.push("耗材");
                        var obj = new Object();
                        obj.name = "耗材";
                        obj.value = result.data[0].consumableCost;
                        nums.push(obj);

                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : names
                            },
                            color:['#F93AF9','#F6C4CE','#39E9E9'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '60%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : nums
                            } ]
                        });

                    }
                }
            });
        }
    );
}

function test3(formDate){
    var width=$("#card1").css("width").replace("px","");
    $("#three").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = ec.init(document.getElementById('three'));

            $.ajax({
                url: $WEB_ROOT_PATH+"/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/proportionChargingItems",
                type : "post",
                async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType : "json",
                data:formDate,
                success : function(result) {
                	 if(result.data[0].accommodationFee == null && result.data[0].diagnosisFee ==0 && result.data[0].inspectionFee==0
                		&& result.data[0].testFee == 0 && result.data[0].treatmentFee ==0 && result.data[0].nursingFee==0	 
                		&& result.data[0].materialFee == 0 && result.data[0].westernMedicineFee ==0 && result.data[0].chineseMedicineYinpian==0	 
                		&& result.data[0].chineseMedicineForm == 0 && result.data[0].consultationFee ==0 && result.data[0].registrationFee==0	 
                		&& result.data[0].otherFee == 0 	 
                	 ){
                   	   myChart.setOption({ //加载数据图表
                              title : {
                                  x : 'center'
                              },
                              tooltip : {
                                  trigger : 'item',
                                  formatter : "{a} <br/>{b} : {c} ({d}%)"
                              },
                              legend : {
                                  left : 'left',
                                  data : []
                              },
                              series : [ {
                                  name : '金额',
                                  type : 'pie',
                                  radius : '60%',
                                  center : [
                                      '50%',
                                      '60%' ],
                                  data : []
                              } ]
                          });
                   }else{
                	
                    if(result.data.length==0){
                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                position: function(point, params, dom, rect, size){
                                    return [point[0],0];
                                },
                                formatter : "{a} <br/>{b} : {c} ({d}%)",

                            },
                            legend : {
                                left : 'left',
                                data : []
                            },
                            color:['#FF0000','#B0B060','#800000','#3A60F9','#F93AF9','#899895','#8F00EE','#39E9E9','#4D30BE','#F6C4CE','#172D2D','#52CC33','#70ef77'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '40%',
                                center : [
                                    '43%',
                                    '50%' ],
                                data : []
                            } ]
                        });
                    }else{
                        var names=[];//定义两个数组
                        var nums=[];

                        names.push("床位费");
                        var obj = new Object();
                        obj.name = "床位费";
                        obj.value = result.data[0].accommodationFee;
                        nums.push(obj);

                        names.push("诊察费");
                        var obj = new Object();
                        obj.name = "诊察费";
                        obj.value = result.data[0].diagnosisFee;
                        nums.push(obj);

                        names.push("检查费");
                        var obj = new Object();
                        obj.name = "检查费";
                        obj.value = result.data[0].inspectionFee;
                        nums.push(obj);

                        names.push("化验费");
                        var obj = new Object();
                        obj.name = "化验费";
                        obj.value = result.data[0].testFee;
                        nums.push(obj);

                        names.push("治疗费");
                        var obj = new Object();
                        obj.name = "治疗费";
                        obj.value = result.data[0].treatmentFee;
                        nums.push(obj);

                        names.push("护理费");
                        var obj = new Object();
                        obj.name = "护理费";
                        obj.value = result.data[0].nursingFee;
                        nums.push(obj);

                        names.push("卫生材料费");
                        var obj = new Object();
                        obj.name = "卫生材料费";
                        obj.value = result.data[0].materialFee;
                        nums.push(obj);

                        names.push("西药费");
                        var obj = new Object();
                        obj.name = "西药费";
                        obj.value = result.data[0].westernMedicineFee;
                        nums.push(obj);

                        names.push("中药饮片费");
                        var obj = new Object();
                        obj.name = "中药饮片费";
                        obj.value = result.data[0].chineseMedicineYinpian;
                        nums.push(obj);

                        names.push("中成药费");
                        var obj = new Object();
                        obj.name = "中成药费";
                        obj.value = result.data[0].chineseMedicineForm;
                        nums.push(obj);

                        names.push("一般诊疗费");
                        var obj = new Object();
                        obj.name = "一般诊疗费";
                        obj.value = result.data[0].consultationFee;
                        nums.push(obj);

                        names.push("挂号费");
                        var obj = new Object();
                        obj.name = "挂号费";
                        obj.value = result.data[0].registrationFee;
                        nums.push(obj);

                        names.push("其他费");
                        var obj = new Object();
                        obj.name = "其他费";
                        obj.value = result.data[0].otherFee;
                        nums.push(obj);

                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                position: function(point, params, dom, rect, size){
                                    return [point[0],0];
                                },
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : names
                            },
                            color:['#FF0000','#B0B060','#800000','#3A60F9','#F93AF9','#899895','#8F00EE','#39E9E9','#4D30BE','#F6C4CE','#172D2D','#52CC33','#70ef77'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '40%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : nums
                            } ]
                        });

                    }
                }
                }
            });
        }
    );
}

function test4(formDate){
    var width=$("#card1").css("width").replace("px","");
    $("#four").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = ec.init(document.getElementById('four'));

            $.ajax({
                url: $WEB_ROOT_PATH+"/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/proportionChargingItemsMed",
                type : "post",
                async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType : "json",
                data:formDate,
                success : function(result) {
                    if(result.data.length==0){
                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                position: function(point, params, dom, rect, size){
                                    return [point[0],0];
                                },
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : []
                            },
                            color:['#FF0000','#B0B060','#FAFA6B','#3A60F9','#F93AF9','#899895','#8F00EE','#39E9E9','#4D30BE','#F6C4CE','#172D2D','#52CC33','#70ef77'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '40%',
                                center : [
                                    '43%',
                                    '50%' ],
                                data : []
                            } ]
                        });
                    }else{
                        var names=[];//定义两个数组
                        var nums=[];

                        names.push("床位费");
                        var obj = new Object();
                        obj.name = "床位费";
                        obj.value = result.data[0].accommodationFee;
                        nums.push(obj);

                        names.push("诊察费");
                        var obj = new Object();
                        obj.name = "诊察费";
                        obj.value = result.data[0].diagnosisFee;
                        nums.push(obj);

                        names.push("检查费");
                        var obj = new Object();
                        obj.name = "检查费";
                        obj.value = result.data[0].inspectionFee;
                        nums.push(obj);

                        names.push("化验费");
                        var obj = new Object();
                        obj.name = "化验费";
                        obj.value = result.data[0].testFee;
                        nums.push(obj);

                        names.push("治疗费");
                        var obj = new Object();
                        obj.name = "治疗费";
                        obj.value = result.data[0].treatmentFee;
                        nums.push(obj);

                        names.push("护理费");
                        var obj = new Object();
                        obj.name = "护理费";
                        obj.value = result.data[0].nursingFee;
                        nums.push(obj);

                        names.push("卫生材料费");
                        var obj = new Object();
                        obj.name = "卫生材料费";
                        obj.value = result.data[0].materialFee;
                        nums.push(obj);

                        names.push("西药费");
                        var obj = new Object();
                        obj.name = "西药费";
                        obj.value = result.data[0].westernMedicineFee;
                        nums.push(obj);

                        names.push("中药饮片费");
                        var obj = new Object();
                        obj.name = "中药饮片费";
                        obj.value = result.data[0].chineseMedicineYinpian;
                        nums.push(obj);

                        names.push("中成药费");
                        var obj = new Object();
                        obj.name = "中成药费";
                        obj.value = result.data[0].chineseMedicineForm;
                        nums.push(obj);

                        names.push("一般诊疗费");
                        var obj = new Object();
                        obj.name = "一般诊疗费";
                        obj.value = result.data[0].consultationFee;
                        nums.push(obj);

                        names.push("挂号费");
                        var obj = new Object();
                        obj.name = "挂号费";
                        obj.value = result.data[0].registrationFee;
                        nums.push(obj);

                        names.push("其他费");
                        var obj = new Object();
                        obj.name = "其他费";
                        obj.value = result.data[0].otherFee;
                        nums.push(obj);

                        myChart.setOption({ //加载数据图表
                            title : {
                                x : 'center'
                            },
                            tooltip : {
                                trigger : 'item',
                                position: function(point, params, dom, rect, size){
                                    return [point[0],0];
                                },
                                formatter : "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend : {
                                left : 'left',
                                data : names
                            },
                            color:['#FF0000','#B0B060','#FAFA6B','#3A60F9','#F93AF9','#899895','#8F00EE','#39E9E9','#4D30BE','#F6C4CE','#172D2D','#52CC33','#70ef77'],
                            series : [ {
                                name : '金额',
                                type : 'pie',
                                radius : '40%',
                                center : [
                                    '50%',
                                    '60%' ],
                                data : nums
                            } ]
                        });

                    }
                }
            });
        }
    );
}

function test8(formDate) {
    var width=$("#card1").css("width").replace("px","");
    $("#eight").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' ,// 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('eight'));
            var option = {
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        axisLabel: {
                            interval: 0,
                            rotate: 20,
                            textStyle: {
                                fontSize: 10,
                            }
                        }
                    }
                ],
                yAxis: [
                    {
                        name:'金额',
                        type: 'value'
                    }
                ],
                tooltip: {
                    // show: true
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                series: [
                    {
                        "type": "bar",
                        name:'费用',
                        barMaxWidth:30,
                        "data": [],
                        itemStyle: {
                            normal: {
                                color: '#22b0ee'
                            }
                        }
                    }
                ]
            };
            $.ajax({
                url: $WEB_ROOT_PATH + "/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/departmentRanking",
                type: "post",
                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType: "json",
                data:formDate,
                success: function (data) {
                    console.log(data);
                    for (var i = 0; i < data.data.length; i++) {
                        option.series[0].data.push(data.data[i].totalCost);
                        option.xAxis[0].data.push(data.data[i].dischargeDeptName);
                    }
                    myChart.setOption(option);
                }
            });
        }
    );
}

function test9(formDate) {
    var width=$("#card1").css("width").replace("px","");
    $("#nine").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' ,// 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('nine'));
            var option = {
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        axisLabel: {
                            interval: 0,
                            rotate: 20,
                            textStyle: {
                                fontSize: 10,
                            }
                        }
                    }
                ],
                yAxis: [
                    {
                        name:'金额',
                        type: 'value'
                    }
                ],
                tooltip: {
                    // show: true
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                series: [
                    {
                        "type": "bar",
                        name:'费用',
                        barMaxWidth:30,
                        "data": [],
                        itemStyle: {
                            normal: {
                                color: '#22b0ee'
                            }
                        }
                    }
                ]
            };
            $.ajax({
                url: $WEB_ROOT_PATH + "/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/departmentRankingMed",
                type: "post",
                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType: "json",
                data:formDate,
                success: function (data) {
                    console.log(data);
                    for (var i = 0; i < data.data.length; i++) {
                        option.series[0].data.push(data.data[i].totalCost);
                        option.xAxis[0].data.push(data.data[i].dischargeDeptName);
                    }
                    myChart.setOption(option);
                }
            });
        }
    );
}

function test10(formDate) {
    var width=$("#card1").css("width").replace("px","");
    $("#ten").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' ,// 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('ten'));
            var option = {
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        axisLabel: {
                            interval: 0,
                            rotate: 20,
                            textStyle: {
                                fontSize: 10,
                            }
                        }
                    }
                ],
                yAxis: [
                    {
                        name:'金额',
                        type: 'value'
                    }
                ],
                tooltip: {
                    // show: true
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                series: [
                    {
                        "type": "bar",
                        name:'费用',
                        barMaxWidth:30,
                        "data": [],
                        itemStyle: {
                            normal: {
                                color: '#22b0ee'
                            }
                        }
                    }
                ]
            };
            $.ajax({
                url: $WEB_ROOT_PATH + "/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/treatmentRanking",
                type: "post",
                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType: "json",
                data:formDate,
                success: function (data) {
                    for (var i = 0; i < data.data.length; i++) {
                        option.series[0].data.push(data.data[i].treatmentTotalCost);
                        option.xAxis[0].data.push(data.data[i].itemName);
                    }
                    myChart.setOption(option);
                }
            });
        }
    );
}

function test11(formDate) {
    var width=$("#card1").css("width").replace("px","");
    $("#eleven").css("width",width*0.93);
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' ,// 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('eleven'));
            var option = {
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        axisLabel: {
                            interval: 0,
                            rotate: 20,
                            textStyle: {
                                fontSize: 10,
                            }
                        }
                    }
                ],
                yAxis: [
                    {
                        name:'金额',
                        type: 'value'
                    }
                ],
                tooltip: {
                    // show: true
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                series: [
                    {
                        "type": "bar",
                        name:'费用',
                        barMaxWidth:30,
                        "data": [],
                        itemStyle: {
                            normal: {
                                color: '#22b0ee'
                            }
                        }
                    }
                ]
            };
            $.ajax({
                url: $WEB_ROOT_PATH + "/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/treatmentRankingMed",
                type: "post",
                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType: "json",
                data:formDate,
                success: function (data) {
                    for (var i = 0; i < data.data.length; i++) {
                        option.series[0].data.push(data.data[i].treatmentTotalCost);
                        option.xAxis[0].data.push(data.data[i].itemName);
                    }
                    myChart.setOption(option);
                }
            });
        }
    );
}


//批量导出
$("#export").click(function(){
    var treatmentApproach = $("#treatmentApproach").val();//就诊途径
    var medicalName = $("input[name='CostStatisticsVo.medicalName']").val();//人群类别
    var hospitalCode = $("input[name='hospitalCode']").val();//医院编码
    var hospitalName = $("input[name='hospitalName']").val();//医院名称
    var balanceDate = $("input[name='balanceDate']").val();//结算时间
    var formDate={treatmentApproach:treatmentApproach,medicalName:medicalName,hospitalCode:hospitalCode,hospitalName:hospitalName,
        balanceDate:balanceDate};
    if(treatmentApproach == "byC"){
        layer.alert("请选择就诊途径");
    }else{
        console.log(formDate);
        layer.load(2,{shade: [0.4, '#000']});
        $.ajax({
            url:$WEB_ROOT_PATH+"/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics/expDate",
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
    }
    return false;
});
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
function getUrlParam(url,parm) {
    var reg = new RegExp("(^|&)" + parm + "=([^&]*)(&|$)");
    var r = url.substr(url.indexOf("\?") + 1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}
function showReturn(){
	window.location.href= $WEB_ROOT_PATH
    + '/flyGeneralOverview/flyGeneralOverview'
};

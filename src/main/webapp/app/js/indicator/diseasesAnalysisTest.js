var flag=0;
var tableName=0;
var rowName="";
var selectedYear;
var type=1
var condition
//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
  var grapWidth=$("#mainGrap")[0].offsetWidth;
   $("#main").css('width',grapWidth);
    table.render({
        elem: '#diseasesAnalysisTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/indicator/diseasesAnalysis/listVoTest'
        , cellMinWidth: 80
        , height: 340
        , where: {"diseasesAnalysis.orgName": "hosAll","diseasesAnalysis.condition":GetRequest()}
        , skin: 'nob' //行边框风格
        /*,even: true *///开启隔行背景
        , size: 'sm'
        , cols: [[
            {type: 'numbers', width: '10%', title: '序号'}
            , {field: 'condition', width: '27.5%', title: '疾病名称'}
            , {field: 'totalCost', width: '20%', title: '病种费用(元)'}
            , {field: 'eachTimeCost', width: '20%', title: '次均费用(元)'}
            , {
                field: 'totalProportion', width: '20%', title: '费用占总比', templet: function (value) {
                    return value.totalProportion + "%";
                }
            }
        ]]
        ,done:function(res){
            // console.log(res)
            if(flag==0) {
                $('tr').eq(1).css("background-color", "#C0C0C0");

                // dise(res.data[0].condition);
                tableName=1
                rowName=res.data[0].condition
                flag=1;
            }else if(tableName==1){
                for(var i=0;i<10;i++){
                    if(res.data[i]!=null&&res.data[i].condition==rowName){
                        $('tr').eq(i+1).css("background-color", "#C0C0C0");

                    }
                }
            }
        }
        , page: true
    });



    table.on('row(diseasesAnalysisTable)', function (obj){
        $("tr").css("background-color", "");
        $(this).css("background-color", "#C0C0C0");
        var handding=document.getElementById("hosp")
        handding.innerText=obj.data.condition
        var handding1=document.getElementById("hosp11")
        handding1.innerText=obj.data.condition
        dise(obj.data.condition);
        hopLevel(obj.data.condition,type,selectedYear)
        tableName=1;
        rowName=obj.data.condition;
    });












    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});



function dise(handdingInsName,orgLevel){
    if(orgLevel==undefined){
        orgLevel=""
    }
    var year=selectedYear;
    if(selectedYear==undefined){
        year=""
    }


    // table.render({
    //     elem: '#'
    //     , url: $WEB_ROOT_PATH + '/dhccApi/indicator/diseasesAnalysis/listVoTest'
    //     , cellMinWidth: 80
    //     , height: 340
    //     , where: {"diseasesAnalysis.orgName": "outAll"}
    //     , skin: 'nob' //行边框风格
    //     /*,even: true *///开启隔行背景
    //     , size: 'sm'
    //     , cols: [[
    //         {type: 'numbers', width: '10%', title: '序号'}
    //         , {field: 'condition', width: '30%', title: '疾病名称'}
    //         , {field: 'totalCost', width: '30%', title: '病种费用(元)'}
    //         , {
    //             field: 'totalProportion', width: '25%', title: '占总比', templet: function (value) {
    //                 return value.totalProportion + "%";
    //             }
    //         }
    //     ]],done:function (res) {
    //         if (tableName == 2) {
    //             for (var i = 0; i < 10; i++) {
    //                 if (res.data[i].condition == rowName) {
    //                     // $('tr').eq(i + 1).css("background-color", "#C0C0C0");
    //                     $('tr').eq(i+11).css("background-color", "#C0C0C0");
    //
    //
    //                 }
    //             }
    //         }
    //     }
    //     , page: true
    // });


    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });

    // 使用
    require(
        [
            'echarts',
            'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main2'));

            var option = {
                tooltip: {
                    trigger:'axis'

                },
                legend: {
                    data:['医院疾病病例数','医院次均费用']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [],
                        axisLabel:{interval:0,
                            rotate:20,
                            textStyle: {
                                fontSize : 10,
                            },
                            formatter: function (data) {
                            var hosNames=data.split('-');
                            var hosName=hosNames[0];
                            return hosName;
                        }}
                    }
                ],
                yAxis : [
                    {
                        name:'病例数（次）',
                        type : 'value'
                    },{
                        name:'次均费用（元/人次）',
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"医院疾病病例数",
                        "type":"bar",
                        barMaxWidth:30,
                        "data":[],
                        // "rawdate": [],
                        itemStyle:{
                            normal:{
                                color:'#2284ff'
                            }
                        }
                    },
                    {
                        "name":"医院次均费用",
                        "type":"line",
                        yAxisIndex: 1,
                        "data":[],
                        // "rawdate": [],
                        itemStyle:{
                            normal:{
                                color:'#5fbc35'
                            }
                        }
                    }
                ]
            };
            if(handdingInsName=="error"){
                pieCharts("error", "");
                myChart.setOption(option);
            }else {
                // 为echarts对象加载数据
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/indicator/diseasesAnalysis/handdInfo?diseasesAnalysis.handdingInsName=" + handdingInsName + "&diseasesAnalysis.year=" + year+ "&diseasesAnalysis.type=" + type+ "&diseasesAnalysis.orgLevel=" + orgLevel,
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (result) {
                        console.log(result)

                        for (var i = 0; i < result.data.length; i++) {
                            option.series[0].data.push(result.data[i].total);
                            option.series[1].data.push(result.data[i].timeCost);
                            option.xAxis[0].data.push(result.data[i].orgName + "-" + result.data[i].orgCode);
                        }
                        // console.log(result.data[0].orgCode)
                        pieCharts(handdingInsName, result.data[0].orgCode);
                        var handding1 = document.getElementById("hos")
                        handding1.innerText = result.data[0].orgName;
                        myChart.setOption(option);

                    }
                });
                var ecConfig = require('echarts/config');
                myChart.on(ecConfig.EVENT.CLICK, test);
            }
        });

};


function pieCharts(diseasesName,orgName) {
    var year=selectedYear;
    if(selectedYear==undefined){
        year=""
    }



    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            var myChart = ec.init(document.getElementById('main3'));
            if(diseasesName=="error"){
                var option = {

                    tooltip: {
                        // trigger:'axis'
                        trigger: 'item',
                        position: 'right',
                        confine: false,
                    },
                    "color": ["#F4560B", "#8B8B00", "#0696ff", "#ff0045"],
                    series: [
                        {
                            name: '药品、耗材、诊疗费用、其他',
                            type: 'pie',
                            radius: ['0', '70%'],
                            hoverAnimation: false,
                            center: ['40%', '50%'],
                            avoidLabelOverlap: false,
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data: [
                            ]
                        }
                    ]
                };
                myChart.setOption(option);

            }else {
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/indicator/diseasesAnalysis/diseases?diseasesAnalysis.handdingInsName=" + diseasesName + "&diseasesAnalysis.orgName=" + orgName + "&&diseasesAnalysis.year=" + year+ "&&diseasesAnalysis.type=" + type,
                    type: "post",
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (data) {
                        // console.log(data)
                        var names = [];
                        var num1 = [];
                        var present1 = [];
                        for (var i = 0; i < data.data.length; i++) {
                            if (data.data[i].projectType == '1') {
                                if (data.data[i].totalCost == " " || data.data[i].totalCost == undefined || data.data[i].totalCost == null) {
                                    num1[0] = 0
                                } else {
                                    num1[0] = parseInt(data.data[i].totalCost)
                                }
                            }
                            if (data.data[i].projectType == '2') {
                                if (data.data[i].totalCost == " " || data.data[i].totalCost == undefined || data.data[i].totalCost == null) {
                                    num1[1] = 0
                                } else {
                                    num1[1] = parseInt(data.data[i].totalCost)
                                }
                            }
                            if (data.data[i].projectType == '3') {
                                if (data.data[i].totalCost == " " || data.data[i].totalCost == undefined || data.data[i].totalCost == null) {
                                    num1[2] = 0
                                } else {
                                    num1[2] = parseInt(data.data[i].totalCost)
                                }
                            }
                            if (data.data[i].projectType == null) {
                                if (data.data[i].totalCost == " " || data.data[i].totalCost == undefined || data.data[i].totalCost == null) {
                                    num1[3] = 0
                                } else {
                                    num1[3] = parseInt(data.data[i].totalCost)
                                }
                            }
                        }
                        for (var i = 0; i < 4; i++) {
                            if (num1[i] == '' || num1[i] == null || num1[i] == undefined) {
                                num1[i] = 0
                            }
                        }


                        for (var i = 0; i < 4; i++) {
                            present1[i] = ((num1[i] / (num1[0] + num1[1] + num1[2])) * 100).toFixed(2) + '%';
                        }

                        names[0] = '药品';
                        names[1] = '诊疗';
                        names[2] = '耗材';
                        names[3] = '其他';


                        /* app.title = '环形图'; */
                        var option = {

                            tooltip: {
                                // trigger:'axis'
                                trigger: 'item',
                                position: 'right',
                                confine: false,
                                formatter: function (data) {
                                    if (data.name != 0) {
                                        return data.name.substring(0, 2) + "情况 <br/>" + data.value + " 元(" + data.percent + "%)";
                                    }
                                    return "1111111111111"
                                }
                            },
                            "color": ["#F4560B", "#8B8B00", "#0696ff", "#ff0045"],
                            series: [
                                {
                                    name: '药品、耗材、诊疗费用、其他',
                                    type: 'pie',
                                    radius: ['0', '70%'],
                                    hoverAnimation: false,
                                    center: ['40%', '50%'],
                                    avoidLabelOverlap: false,
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    data: [
                                        {value: num1[0], name: names[0] + '\n' + present1[0]},
                                        {value: num1[1], name: names[1] + '\n' + present1[1]},
                                        {value: num1[2], name: names[2] + '\n' + present1[2]},
                                        {value: num1[3], name: names[3] + '\n' + present1[3]},
                                    ]
                                }
                            ]
                        };
                        myChart.setOption(option);
                    }
                });
            }
        }
    );
}

function test(params) {
    console.log(params)
    var hosNames=params.name.split('-');
    var hosName=hosNames[0];
    var hosCode=hosNames[hosNames.length-1];
    var handding1=document.getElementById("hos")
    handding1.innerText=hosName;
    pieCharts(condition,hosCode)
}


layui.use(['form'], function() {
    var form=layui.form;
    form.on('select(brickType)', function(data){
        type=data.value;
        console.log(selectedYear)
        var hopIn=document.getElementById("hopIn")

        if(type==1){
            var field={"diseasesAnalysis.orgName": "hosAll","diseasesAnalysis.year":selectedYear,"diseasesAnalysis.condition":condition11}
            layui.table.reload('diseasesAnalysisTable', {
                where: field
                ,page: { curr: 1}
            });
            hosTop("hosTop",selectedYear);
            hopIn.innerText="住院"
        }else{
            var field2={"diseasesAnalysis.orgName": "outAll","diseasesAnalysis.year":selectedYear,"diseasesAnalysis.condition":condition11}
            layui.table.reload('diseasesAnalysisTable', {
                where: field2
                ,page: { curr: 1}
            });
            hosTop("outTop",selectedYear);
            hopIn.innerText="门诊"

        }
    });
});
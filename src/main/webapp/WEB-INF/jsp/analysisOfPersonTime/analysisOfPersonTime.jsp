<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
    <title>就诊人数分析</title>
    <style type="text/css">
        html {
            background-color: #F2F2F2;
        }

        div[lay-id=diseasesAnalysisTable] .layui-table-body {
            height: 280px;
        }
    </style>
</head>
<body>

<%--<div class="layui-fluid" style="overflow: hidden;">--%>
    <%--<div class="layui-col-md12" >--%>
        <%--<div class="layui-col-md12" style="height: 70px">--%>


            <%--<div class="layui-form layui-card-header layuiadmin-card-header-auto">--%>


                <%--<div class="layui-inline">--%>
                    <%--<div style="width: 50px"></div>--%>
                    <%--<label class="layui-form-label">年份选择</label>--%>
                    <%--<div class="layui-input-inline">--%>
                        <%--<input type="text" class="layui-input" id="year" placeholder="yyyy" readonly="true"--%>
                               <%--lay-filter="year">--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label">就诊类型</label>--%>
                    <%--<div class="layui-input-inline">--%>
                        <%--<select id="type" lay-filter="brickType">--%>
                            <%--<option value="1">住院</option>--%>
                            <%--<option value="2">门诊</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>

                <%--</div>--%>


            <%--</div>--%>







            <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="layui-fluid" style="overflow: hidden;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" style="line-height: 40px;">
                <div style="float: left; margin-left: 10px; margin-right: 10px;">
                    <h3>
                        <font color='red' id="tcqAllInfo">

                        </font>
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12" style="height: 450px">

            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 450px">
                <div class="layui-card" style="height: 440px;">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">


                        <div class="layui-inline">
                            <div style="width: 50px"></div>
                            <label class="layui-form-label">年份选择</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="year" placeholder="yyyy" readonly="true"
                                       lay-filter="year">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">就诊类型</label>
                            <div class="layui-input-inline">
                                <select id="type" lay-filter="brickType">
                                    <option value="1">住院</option>
                                    <option value="2">门诊</option>
                                </select>
                            </div>

                        </div>


                    </div>
                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        统筹区就诊人次饼状图

                    </div>

                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" style="height:300px;">
                                    <div id="main" style="height:300px;width:800px;margin-left:25px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card" style="height: 440px;">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="height: 42px">
                    </div>
                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="hopName"></span> 就诊人次月度趋势分析
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" style="height:300px;">

                                    <div id="main1" style="height: 300px;width:100%;margin-left:25px;"></div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="handdingName"></span> 医院就诊人次/就诊频次
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" >
                                    Top10
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    全部
                                </li>
                            </ul>
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;height:300px">
                                <div class="layui-tab-item layui-show" style="height:300px;">
                                    <div class="layui-tab-item layui-show" id="mainGrap" style="height:300px;">

                                    <div id="main2" style="height: 300px;width:800px;margin-left:25px;"></div>
                                    </div>
                                </div>
                                <div class="layui-tab-item ">
                                    <div class="layui-card" style="padding-left: 10px;">
                                        <table id="hopTable" class="layui-hide"
                                               lay-filter="hopTable" style="height: 290px;"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">

                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="hopName1"></span> 诊断就诊人次/就诊频次
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" >
                                    Top10
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    全部
                                </li>
                            </ul>
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;height:300px">
                                <div class="layui-tab-item layui-show" style="height:300px;">
                                    <div class="layui-tab-item layui-show" id="mainGrap1" style="height:300px;">

                                    <div id="main3" style="height: 300px;width:800px;margin-left:25px;"></div>
                                    </div>
                                </div>
                                <div class="layui-tab-item ">
                                    <div class="layui-card" style="padding-left: 10px;">
                                        <table id="diseTable" class="layui-hide"
                                               lay-filter="diseTable" style="height: 290px;"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/app/js/analysisOfPersonTime/analysisOfPersonTime.js"></script>
<script>
    function thisYear(){
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        return tYear;
    }


    layui.use('laydate', function() {

        var laydate = layui.laydate;

        laydate.render({
            elem:'#year',//制定元素
            type:'year',
            value:thisYear(),
            max:thisYear(),//规定时间期限
            position:'absolute',
            done:function(value,date){
                year=value
                table1(year,type);
                flag=-1;
                rowName=null
            }
        });

    });
</script>
<script>
    table1(thisYear(),"1");
    function table1(year,type) {
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });

        // 使用
        // require(
        //     [
        //         'echarts',
        //         'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        //     ],
            // function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = echarts.init(document.getElementById('main'));

                var option = {
                    tooltip: {
                        // trigger:'axis'
                        trigger: 'item',
                        position: 'right',
                        confine: true,
                        formatter: function (data) {
                            if (data.value != 0) {
                                var hosNames=data.name.split('-');
                                var hosName=hosNames[0];
                                return hosName + "就诊人次情况 <br/>" + data.value + " 人次(" + data.percent + "%)";
                            }
                            return "1111111111111"
                        }
                    },
                    // "color": ["#F4560B", "#8B8B00", "#0696ff", "#ff0045"],
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data:[],
                        formatter: function (data) {
                            var hosNames=data.split('-');
                            var hosName=hosNames[0];
                            return hosName;
                        }
                    },
                    series: [
                        {
                            name: "统筹区就诊人次占比",
                            type: 'pie',
                            radius: ['0', '70%'],
                            hoverAnimation: true,
                            center: ['50%', '50%'],
                            avoidLabelOverlap: true,
                            labelLine: {
                                normal: {
                                    show: true

                                }
                            },
                            label: {
                                normal: {
                                    formatter: function(data){
                                        // console.log(data)
                                        var hosNames=data.name.split('-');
                                        var hosName=hosNames[0];
                                        return hosName
                                    }
                                }
                            },
                            data: []
                        }
                    ]
                };

                // 为echarts对象加载数据
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/analysisOfPersonTime/analysisOfPersonTime/table1?analysisOfPersonTime.year="+year+"&analysisOfPersonTime.type="+type+"&analysisOfPersonTime.handdingInsCode="+handdingCode2,
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (result) {
                        var date;
                        for (var i = 0; i < result.rows.length; i++) {
                            data={value:+result.rows[i].totalPerson,name:result.rows[i].handdingInsName+"-"+result.rows[i].handdingInsCode}
                            option.series[0].data.push(data);
                            // option.legend.data.push(result.rows[i].handdingInsName+"-"+result.rows[i].handdingInsCode);
                            option.legend.data.push(result.rows[i].handdingInsName+"-"+result.rows[i].handdingInsCode);
                        }
                        if(result.rows.length>0){
                            change13(result.rows[0].handdingInsName,result.rows[0].handdingInsCode)
                        }else{
                            change13("","error")
                        }

                        myChart.setOption(option);
                        var ecConfig = require('echarts/config');
                        myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
                    }
                });
            // }
        // );
    }

    function eConsoleSpread(param) {
        var handdingNames=param.name.split('-');
        var handdingName=handdingNames[0];
        var handdingCode=handdingNames[1];
        change13(handdingName,handdingCode)
        flag=-1;
        rowName=null
    }


    function table2(year,type,handdingInsCode,orgCode) {
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });

        require(
            [
                'echarts',
                'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
               // 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main1'));

                var option = {
                    tooltip: {
                        trigger:'axis'

                    },
                    legend: {
                        data:['月度就诊人次','月度就诊频次']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : [],
                            axisLabel:{interval:0,
                                rotate:20,
                                textStyle: {
                                    fontSize : 10,
                                }}
                        }
                    ],
                    yAxis : [
                        {
                            name:'就诊人次（次）',
                            type : 'value'
                        },{
                            name:'就诊频次（次/人）',
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"月度就诊人次",
                            "type":"line",
                            "data":[],
                            itemStyle:{
                                normal:{
                                    color:'#2284ff'
                                }
                            }
                        },
                        {
                            "name":"月度就诊频次",
                            "type":"line",
                            yAxisIndex: 1,
                            "data":[],
                            itemStyle:{
                                normal:{
                                    color:'#5fbc35'
                                }
                            }
                        }
                    ]
                };
                if(handdingInsCode=="error"){
                    myChart.setOption(option);
                }else {
                    // 为echarts对象加载数据
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/analysisOfPersonTime/analysisOfPersonTime/table2?analysisOfPersonTime.year="+year+"&analysisOfPersonTime.type="+type+"&analysisOfPersonTime.handdingInsCode="+handdingInsCode+"&analysisOfPersonTime.orgCode="+orgCode,
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (result) {

                            for(var i =0;i<12;i++){
                                var index=-1
                                for(var y=0;y<result.rows.length;y++){
                                    if(result.rows[y].month==(i+1)){
                                        index=y;
                                    }
                                }
                                if(index==-1){
                                    option.series[0].data.push(0);
                                    option.series[1].data.push(0);
                                    option.xAxis[0].data.push((i+1)+"月");
                                }else{
                                    option.series[0].data.push(result.rows[index].totalTime);
                                    option.series[1].data.push(result.rows[index].eachPersonTime);
                                    option.xAxis[0].data.push((i+1)+"月");
                                }
                            }
                            myChart.setOption(option);

                        }
                    });

                }
            });
    }


    function table3(year,type,handdingInsCode) {
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });

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
                        trigger:'axis',
                        // trigger: 'item',
                        // position: 'right',
                        // confine: false,
                        formatter:function(data){
                            // console.log(data)
                            var hosNames=data[0].name.split('-');
                            var hosName=hosNames[0];
                            return hosName+":<br/>就诊人次："+data[0].value+"<br/>就诊频次:"+data[1].value
                        }

                    },
                    legend: {
                        data:['医院就诊人次','医院就诊频次']
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
                            name:'就诊人次（次）',
                            type : 'value'
                        },{
                            name:'就诊频次（次/人）',
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"医院就诊人次",
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
                            "name":"医院就诊频次",
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
                if(handdingInsCode=="error"){
                    change324("error","error")
                    myChart.setOption(option);
                }else {
                    // 为echarts对象加载数据
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/analysisOfPersonTime/analysisOfPersonTime/table3?analysisOfPersonTime.year="+year+"&analysisOfPersonTime.type="+type+"&analysisOfPersonTime.handdingInsCode="+handdingInsCode,
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (result) {
                            // console.log(result)

                            for (var i = 0; i < result.data.length; i++) {
                                option.series[0].data.push(result.data[i].totalTime);
                                option.series[1].data.push(result.data[i].eachPersonTime);
                                option.xAxis[0].data.push(result.data[i].orgName + "-" + result.data[i].orgCode);
                            }
                            change324(result.data[0].orgName ,result.data[0].orgCode)
                            myChart.setOption(option);

                        }
                    });
                    var ecConfig = require('echarts/config');
                    myChart.on(ecConfig.EVENT.CLICK, test);
                }
            });
    }
    function test(param) {
        var orgNames=param.name.split('-');
        var orgName=orgNames[0];
        var orgCode=orgNames[1];
        change324(orgName,orgCode)
        // rowName=null

    }


    function table4(year,type,handdingInsCode,orgCode) {
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });

        require(
            [
                'echarts',
                'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main3'));

                var option = {
                    tooltip: {
                        trigger:'axis'

                    },
                    legend: {
                        data:['诊断就诊人次','诊断就诊频次']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : [],
                            axisLabel:{interval:0,
                                rotate:20,
                                textStyle: {
                                    fontSize : 10,
                                }
                                ,
                                formatter: function (data) {
                                    return data
                                }
                            }
                        }
                    ],
                    yAxis : [
                        {
                            name:'就诊人次（次）',
                            type : 'value'
                        },{
                            name:'就诊频次（次/人）',
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"诊断就诊人次",
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
                            "name":"诊断就诊频次",
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
                if(handdingInsCode=="error"){
                    myChart.setOption(option);
                }else {
                    // 为echarts对象加载数据
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/analysisOfPersonTime/analysisOfPersonTime/table4?analysisOfPersonTime.year="+year+"&analysisOfPersonTime.type="+type+"&analysisOfPersonTime.handdingInsCode="+handdingInsCode+"&analysisOfPersonTime.orgCode="+orgCode,
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (result) {
                            // console.log(result)

                            for (var i = 0; i < result.data.length; i++) {
                                option.series[0].data.push(result.data[i].totalTime);
                                option.series[1].data.push(result.data[i].eachPersonTime);
                                option.xAxis[0].data.push(result.data[i].condition);
                            }
                            myChart.setOption(option);

                        }
                    });

                }
            });
    }

    function thisYear(){
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        return tYear;
    }
</script>
<script>
    function change13(name,Code) {
        var handdingName=document.getElementById("handdingName")
        handdingName.innerText=name;
        handdingCode=Code;
        table3(year,type,Code)
        table3All(year,type,Code)
    }

    function change324(orgName,orgCode){


        if(orgCode=="error"){
            var hopName=document.getElementById("hopName")
            hopName.innerText="";
            var hopName1=document.getElementById("hopName1")
            hopName1.innerText="";
            table2("","","error","")
            table4("","","error","")
        }else {
            var hopName=document.getElementById("hopName")
            hopName.innerText=orgName;
            var hopName1=document.getElementById("hopName1")
            hopName1.innerText=orgName;
            table2(year, type, handdingCode, orgCode)
            table4(year, type, handdingCode, orgCode)
            table4All(year, type, handdingCode, orgCode)
        }
    }



</script>
</body>
</html>
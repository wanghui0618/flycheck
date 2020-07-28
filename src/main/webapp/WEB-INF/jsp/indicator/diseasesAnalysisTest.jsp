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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
    <title>病种分析</title>
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

<div class="layui-fluid" style="overflow: hidden;">

    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12" style="height: 500px">

            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 500px">
                <div class="layui-card" style="height: 490px;">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-inline">
                        <div style="width: 50px"></div>
                        <label class="layui-form-label">年份选择</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="year" placeholder="yyyy" readonly="true" lay-filter="year" >
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
                        <span id="hopIn">住院</span>病种费用统计

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
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" id="mainGrap" style="height:300px;">
                                    <div id="main" style="height:300px;margin-left:25px;"></div>
                                </div>
                                <div class="layui-tab-item ">
                                    <div class="layui-card" style="padding-left: 10px;">
                                        <table id="diseasesAnalysisTable" class="layui-hide"
                                               lay-filter="diseasesAnalysisTable" style="height: 290px;"></table>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 500px">
                <div class="layui-card" style="height: 490px;">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="height: 42px">
                    </div>
                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="des2"></span>医院等级均次费用
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" >
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
                        <span id="hosp"></span>&nbsp;&nbsp;<span id="level"></span>&nbsp; 医院病例数统计top10
                    </div>
                    <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                        <div class="layui-tab-item layui-show" style="height:300px;">
                            <div id="main2" style="height: 300px;width:100%;margin-left:25px;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">

                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="hosp11"></span> &nbsp; <span id="hos"></span> 药品、诊疗、耗材占比
                    </div>
                    <div class="layui-card-body" style="height: 315px;">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" style="height:300px;">
                                    <div id="main3" style="height: 300px;width:100%;margin-left:25px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/app/js/indicator/diseasesAnalysisTest.js"></script>
<script type="text/javascript">
    selectedYear=thisYear()
    hosTop("hosTop",thisYear());
    function hosTop(hopType,year) {
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
                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'));

                var option = {
                    tooltip: {
                        // show: true
                        trigger: 'axis'

                    },
                    legend: {
                        data: ['病种费用','次均费用']
                    },
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
                            name:'病种费用（元）',
                            type: 'value'
                        },{
                            name:'次均费用（元/次）',
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            "name": "病种费用",
                            "type": "bar",
                            barMaxWidth:30,
                            "data": [],
                            itemStyle: {
                                normal: {
                                    color: '#22b0ee'
                                }
                            }
                        },{
                            "name": "次均费用",
                            "type": "line",
                            "data": [],
                            yAxisIndex: 1,
                            itemStyle: {
                                normal: {
                                    color: '#2dee1a'
                                }
                            }
                        }
                    ]
                };

                // 为echarts对象加载数据
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/indicator/diseasesAnalysis/listVoTest?diseasesAnalysis.orgName="+hopType+"&diseasesAnalysis.year="+year+"&diseasesAnalysis.condition="+condition11,
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (result) {
                        for (var i = 0; i < result.data.length; i++) {
                            option.series[0].data.push(result.data[i].totalCost);
                            option.series[1].data.push(result.data[i].eachTimeCost);
                            option.xAxis[0].data.push(result.data[i].condition);
                        }
                        myChart.setOption(option);
                        var ecConfig = require('echarts/config');
                        // console.log(result.data[0].condition)
                        // console.log(result.data[0].condition==" ")
                        // console.log(result.data[0].condition==null)
                        console.log(result.data[0]==undefined)
                        if(result.data[0]==undefined){
                            dise("error")
                            hopLevel("error",type,year)
                            var handding = document.getElementById("hosp")
                            handding.innerText = ""
                            var handding1 = document.getElementById("hosp11")
                            handding1.innerText =""
                            var handding2 = document.getElementById("des2")
                            handding2.innerText =""
                        }else {
                            condition=result.data[0].condition
                            dise(result.data[0].condition);
                            hopLevel(result.data[0].condition,type,year)
                            var handding = document.getElementById("hosp")
                            handding.innerText = result.data[0].condition
                            var handding1 = document.getElementById("hosp11")
                            handding1.innerText = result.data[0].condition
                            var handding2 = document.getElementById("des2")
                            handding2.innerText = result.data[0].condition
                            var handding3 = document.getElementById("level")
                            handding3.innerText =""

                        }
                        myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
                    }
                });
            }
        );
    }

    function eConsoleSpread(param) {
        $("tr").css("background-color", "");
        var handding = document.getElementById("hosp")
        handding.innerText = param.name
        var handding1 = document.getElementById("hosp11")
        handding1.innerText = param.name
        var handding2 = document.getElementById("des2")
        handding2.innerText = param.name
        var handding3 = document.getElementById("level")
        handding3.innerText =""
        hopLevel(param.name,type,selectedYear)
        dise(param.name);
        condition=param.name
    }

    function thisYear(){
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        return tYear;
    }
</script>

<script type="text/javascript">
    // outTop(thisYear());
    function hopLevel(condition,type,year) {
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });

        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main1'));

                var option = {
                    tooltip: {
                        trigger: 'axis'

                    },
                    legend: {
                        data: ['次均费用']
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisLabel: {
                                interval: 0,
                                rotate: 20,
                                textStyle: {
                                    fontSize: 10,
                                }
                            },
                            data: []
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            "name": "次均费用",
                            "type": "line",
                            "data": [],
                            itemStyle: {
                                normal: {
                                    color: '#EE7621'
                                }
                            }
                        }
                    ]
                };
                if(condition=="error"){
                    myChart.setOption(option);
                }else {
                    // 为echarts对象加载数据
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/indicator/diseasesAnalysis/hopLevel?diseasesAnalysis.condition=" + condition + "&diseasesAnalysis.year=" + year + "&diseasesAnalysis.type=" + type,
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (result) {
                            for (var i = 0; i < result.data.length; i++) {
                                option.series[0].data.push(result.data[i].eachPersonTime);
                                option.xAxis[0].data.push(result.data[i].orgLevel);
                            }
                            myChart.setOption(option);

                        }
                    });
                }
                var ecConfig = require('echarts/config');

                myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread1);
            }
        );
    }
    function eConsoleSpread1(param) {
        $("tr").css("background-color", "");
        // var handding = document.getElementById("hosp")
        // handding.innerText = param.name
        // var handding1 = document.getElementById("hosp11")
        // handding1.innerText = param.name
        var handding3 = document.getElementById("level")
        handding3.innerText =param.name
        dise(condition,param.name);
        tableName=0
        rowName=param.name
    }
    function thisYear(){
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        return tYear;
    }
</script>

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
            done:function(value,date){//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
                selectedYear=value;
                if(type==1){
                    var field={"diseasesAnalysis.orgName": "hosAll","diseasesAnalysis.year":value,"diseasesAnalysis.condition":condition11}
                    layui.table.reload('diseasesAnalysisTable', {
                        where: field
                        ,page: { curr: 1}
                    });
                    hosTop("hosTop",value);
                }else{
                    var field2={"diseasesAnalysis.orgName": "outAll","diseasesAnalysis.year":value,"diseasesAnalysis.condition":condition11}
                    layui.table.reload('diseasesAnalysisTable', {
                        where: field2
                        ,page: { curr: 1}
                    });
                    hosTop("outTop",value);
                }



                // hosTop("hosTop",value);
                // hosTop("outTop",value);

            }
        });

    });








    layui.config({
        base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'console']);


var condition11=GetRequest()
    // console.log(condition11)
    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        console.log(location)
        if(url!="") {
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for (var i = 0; i < strs.length; i++) {
                    theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
                }
            }
            console.log(theRequest)
            console.log(theRequest.bzCode)
            return theRequest.bzCode;
        }else{
            return ""
        }
    }
</script>
</body>
</html>
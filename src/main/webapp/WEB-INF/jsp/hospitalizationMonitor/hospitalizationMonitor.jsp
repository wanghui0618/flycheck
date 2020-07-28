<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<title>住院病例监控</title>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 24px;
    font-size: 14px;
}
#showPic img{
	position:relative;
	height:250px;
}
.layui-tab-title .layui-this:after {
    height: 23px!important;
}
.daiban li{
	height: 65px;
	border-radius: 3px;
	color:#fff;
	margin: 0px 6px 13px 6px;
}
.daichushen{
	background-image: linear-gradient(-225deg, #00C4FE 0%, #137CFF 100%);
}
.daijihe{
	background-image: linear-gradient(-225deg, #00D988 0%, #00B365 100%);
}
.daigongshi{
	background-image: linear-gradient(-225deg, #FEC500 0%, #F68E00 100%);
}
.daizhongshen{
	background-image: linear-gradient(-225deg, #B998F2 0%, #806BE4 100%);
}
.layui-table-body {
    overflow: hidden;
}
.layui-table-page {
    border-top: 0px;
}
/* .zr-element{
	left: -40px;
	width: 520px!important;
	height: 319px!important;
} */
.layui-input-block {
    margin-left: 60px;
}
.layui-form-label {
    padding: 9px 0px;
}
.layui-tab-brief > .layui-tab-more li.layui-this::after, .layui-tab-brief > .layui-tab-title .layui-this::after {
    border: none;
    border-bottom: 0px;
}
.layui-this{
	background-color: #F68F00;
	color:#fff!important;
	border-radius: 2px;
}
.layui-form-select{
width:200px;
}
#sysVerify{
font-size: 14px;
color: #353535;
width: 100%;
line-height:34px;
}
.layui-form-label {
}
.layui-col-space10 > * {
    padding: 10px;
}
.nowrap{white-space:nowrap;}
#navListCard li{
    float:left;
}
</style>
</head>
<body>

<div class="layui-fluid" style="overflow-y:hidden;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <label style="font-size: 16px;"></label><label id="dateweek"
                                                                   style="margin-left:10px;">&nbsp;</label>
                </div>
                <div class="layui-card-body">
                    <ul class="layui-row layui-col-space10 daiban" id="navListCard">
                        <li style="padding-top: 0px;" class="daijihe">
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/guizezongshu.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float:left;">
                                <h3 style="font-size: 12px;">接入医院数量</h3>
                                <p style="font-size: 20px;"><label id="hosStatus"><span id="hopNum"></span>家</label></p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="daigongshi">
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/menzhen.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float:left;">
                                <h3 style="font-size: 12px;">今年住院总人数</h3>
                                <p style="font-size: 20px;"><label id="userStatus"><span id="person"></span>人</label>
                                </p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="daigongshi">
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/youxiao.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float:left;">
                                <h3 style="font-size: 12px;">今年住院病例数</h3>
                                <p style="font-size: 20px;"><label id="yestPerStatus"><span id="case"></span>人次</label>
                                </p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="daigongshi nowrap " >
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/zhuyuan.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float: left;">
                                <h3 style="font-size: 12px;">今年住院总费用</h3>
                                <p style="font-size: 20px;"><label id="finaStatus"><span id="money"></span></label>
                                </p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class=" daizhongshen">
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/menzhen.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float:left;">
                                <h3 style="font-size: 12px;">今年门诊总人数</h3>
                                <p style="font-size: 20px;"><label id="diag2userStatus"><span id="diag2person"></span>人</label>
                                </p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="daizhongshen">
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/youxiao.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float:left;">
                                <h3 style="font-size: 12px;">今年门诊病例数</h3>
                                <p style="font-size: 20px;"><label id="diag2yestPerStatus"><span id="diag2Case"></span>人次</label>
                                </p>
                            </div>
                        </li>
                        <li style="padding-top: 0px;" class="daizhongshen">
                            <div style="display: block;float: left;padding-right: 10px;line-height:65px;">
                                <img style="height:40px;"
                                     src="<%=request.getContextPath() %>/images/baseservice/zhuyuan.png"/>
                            </div>
                            <div style="line-height: 25px;margin-top: 7px;float:left;">
                                <h3 style="font-size: 12px;">今年门诊总费用</h3>
                                <p style="font-size: 20px;"><label id="diag2finaStatus"><span id="diag2money"></span></label>
                                </p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <%--<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  /><span id="nowYear"></span>年药品、耗材、诊疗占比</div>--%>
                <%--<div class="layui-card-body" style="height: 315px;">--%>
                <%--<div id="main" style="height:100%;width: 100%;margin: auto;position: absolute;top: 0; left: 0; bottom: 0; right: 0;"></div>--%>
                <%--</div>--%>
                <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                    <img style="margin-top:-2px;padding-right: 8px;"
                         src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                    <span id="nowYear"></span>年药品、耗材、诊疗费用占比
                </div>
                <div class="layui-card-body" style="height: 315px;">
                    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                        <ul class="layui-tab-title"
                            style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                            <li class="layui-this"
                                style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                住院
                            </li>
                            <img style="height: 25px;width: 2px;"
                                 src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                            <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none"
                                id="change">门诊
                            </li>
                        </ul>
                        <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                            <div class="layui-tab-item layui-show" style="height:250px;">
                                <div id="main" style="height: 250px;width:520px;margin-left:25px;"></div>
                            </div>
                            <div class="layui-tab-item layui-show" style="height:250px;">
                                <div id="main1" hidden style="height: 250px;width:520px;margin-left:25px;"></div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md8">
            <div class="layui-card" style="border-width: 1px;border-style: solid;border-color: #e6e6e6;">
                <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                    <img style="margin-top:-2px;padding-right: 8px;"
                         src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                    今年住院人数Top5
                </div>
                <div class="layui-card-body">
                    <table id="personTop" class="layui-table" style="border:1px solid;"></table>

                </div>
            </div>
        </div>
        <%--<div class="layui-col-md4">--%>
        <%--<div class="layui-card">--%>
        <%--<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />各个统筹区情况</div>--%>
        <%--<div class="layui-card-body" style="height: 315px;">--%>
        <%--<div id="main3" style="height:290px;"></div>      --%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img
                        style="margin-top:-2px;padding-right: 8px;"
                        src="<%=request.getContextPath() %>/images/auditing/mark.png"/>今年各个统筹区Top10情况
                </div>
                <div class="layui-card-body" style="height: 315px;">
                    <div id="main3" style="height:290px;"></div>
                </div>
            </div>
        </div>
        <%--<div class="layui-col-md4">--%>
        <%--<div class="layui-card">--%>
        <%--<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">--%>
        <%--<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />当前住院费用Top5</div>--%>
        <%--<div class="layui-card-body" style="height: 315px;">--%>
        <%--<table id="costTop" class="layui-table" style="border:1px solid;"></table>--%>

        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="layui-col-md8">
            <div class="layui-card"
                 style="min-height:363px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
                <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                    <img style="margin-top:-2px;padding-right: 8px;"
                         src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                    今年门诊人数Top5
                </div>
                <div class="layui-card-body">
                    <table id="outpatientPersonTop" class="layui-table" style="border:1px solid;"></table>

                </div>
            </div>
        </div>

    </div>
    <script src="<%=request.getContextPath()%>/app/js/hospitalizationMonitor/hospitalizationMonitor.js"></script>
    <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
</div>

</body>

    <script type="text/javascript">




         var date=new Date;
         var year=date.getFullYear();
         var nowYear=document.getElementById("nowYear");
         nowYear.innerText=year;


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
                var myChart = ec.init(document.getElementById('main'));

                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/hospitalizationMonitor/hospitalizationMonitor/yesterday",
                    type: "post",
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (data) {
                        // console.log(data)
                        var names = [];
                        var num1 = [];
                        var present1 = [];
                        var num2 = [];
                        var present2 = [];
                        for (var i = 0; i < data.rows.length; i++) {
                            if (data.rows[i].diagType == '1') {
                                if (data.rows[i].projectType == '1') {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num1[0] = 0
                                    }else {
                                        num1[0] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                                if (data.rows[i].projectType == '2') {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num1[1] = 0
                                    }else {
                                        num1[1] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                                if (data.rows[i].projectType == '3') {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num1[2] = 0
                                    }else {
                                        num1[2] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                                if (data.rows[i].projectType ==null) {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num1[3] = 0
                                    }else {
                                        num1[3] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                            }
                            if (data.rows[i].diagType == '2') {
                                if (data.rows[i].projectType == '1') {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num2[0] = 0
                                    }else {
                                        num2[0] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                                if (data.rows[i].projectType == '2') {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num2[1] = 0
                                    }else {
                                        num2[1] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                                if (data.rows[i].projectType == '3') {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num2[2] = 0
                                    }else {
                                        num2[2] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                                if (data.rows[i].projectType == null) {
                                    if(data.rows[i].totalCost==" "||data.rows[i].totalCost==undefined||data.rows[i].totalCost==null) {
                                        num2[3] = 0
                                    }else {
                                        num2[3] = parseInt(data.rows[i].totalCost)
                                    }
                                }
                            }
                        }
                        for(var i=0;i<4;i++){
                            if(num1[i]==''||num1[i]==null||num1[i]==undefined){
                                num1[i]=0
                            }
                            if(num2[i]==''||num2[i]==null||num2[i]==undefined){
                                num2[i]=0
                            }
                        }


                        for (var i = 0; i < 4; i++) {
                            present1[i] = ((num1[i] / (num1[0] + num1[1] + num1[2])) * 100).toFixed(2) + '%';
                            present2[i] = ((num2[i] / (num2[0] + num2[1] + num2[2])) * 100).toFixed(2) + '%';

                        }

                        names[0] = '药品';
                        names[1] = '诊疗';
                        names[2] = '耗材';
                        names[3] = '其他';



                        /* app.title = '环形图'; */
                        var option = {

                            tooltip: {
                                trigger: 'item',
                                position: 'right',
                                confine: false,
                                formatter: function (data) {
                                    if (data.name != 0) {
                                        return year+"年"+data.name.substring(0, 2) + "情况 <br/>"+ data.value + " 元(" + data.percent + "%)";
                                    }
                                    return ""
                                }
                            },
                            "color": ["#F4560B", "#8B8B00", "#0696ff","#0ce03b"],
                            series: [
                                {
                                    name: '药品、耗材、诊疗费用、其他',
                                    type: 'pie',
                                    radius: ['50%', '70%'],
                                    hoverAnimation: false,
                                    center: ['40%', '55%'],
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

                        var myChart1 = ec.init(document.getElementById('main1'));

                        /* app.title = '环形图'; */
                        var option1 = {

                            tooltip: {
                                trigger: 'item',
                                position: 'right',
                                confine: false,
                                formatter: function (data) {
                                    if (data.name != 0) {
                                        return year+"年"+data.name.substring(0, 2) + "情况 <br/>"+ data.value + " 元(" + data.percent + "%)";
                                    }
                                    return ""
                                }
                            },
                            "color": ["#F4560B", "#8B8B00", "#0696ff","#0ce03b"],
                            series: [
                                {
                                    name: '药品、耗材、诊疗费用',
                                    type: 'pie',
                                    radius: ['50%', '70%'],
                                    hoverAnimation: false,
                                    center: ['40%', '55%'],
                                    avoidLabelOverlap: false,
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    data: [
                                        {value: num2[0], name: names[0] + '\n' + present2[0]},
                                        {value: num2[1], name: names[1] + '\n' + present2[1]},
                                        {value: num2[2], name: names[2] + '\n' + present2[2]},
                                        {value: num2[3], name: names[3] + '\n' + present2[3]},
                                    ]
                                }
                            ]
                        };
                        myChart1.setOption(option1);
                        $("#main1").hide();
                        $("#change").click(function () {
                            $("#main1").show();

                        });
                    }
                });
            });
    </script>
    <script type="text/javascript">


        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });

        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {

                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/hospitalizationMonitor/hospitalizationMonitor/cityInfo",
                    type: "post",
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (data) {
                        // console.log(data);

                        var totalCost = [];
                        var person = [];
                        var totalCost1 = [];
                        var person1 = [];
                        var names = [];


                        var myChart = ec.init(document.getElementById('main3'));

                        /* app.title = '环形图'; */

                        var option = {
                            /*title: {
                                text: 'ECharts 入门示例'
                            },*/
                            tooltip: {
                                trigger:'axis'
                            },
                            legend: {
                                data: ['住院人数', '住院费用(万元)', '门诊人数', '门诊费用(万元)']
                            },
                            xAxis: {
                                type: 'category',
                                axisLabel: {
                                    interval: 0,
                                    rotate: 20,
                                    textStyle: {
                                        fontSize: 10,
                                    }
                                },
                                data: []
                            },
                            yAxis: {},
                            series: [{
                                name: '住院人数',
                                barMaxWidth:30,
                                type: 'bar',
                                data: []
                            },
                                {
                                    name: '住院费用(万元)',
                                    barMaxWidth:30,
                                    type: 'bar',
                                    data: []
                                }, {
                                    name: '门诊人数',
                                    barMaxWidth:30,
                                    type: 'bar',
                                    data: []
                                },
                                {
                                    name: '门诊费用(万元)',
                                    barMaxWidth:30,
                                    type: 'bar',
                                    data: []
                                }]
                        };
                        // console.log(data)

                        for (var i = 0; i < data.rows.length; i++) {
                            names[i] = data.rows[i].handdingInsName;
                            totalCost[i] = data.rows[i].totalCost / 10000;
                            totalCost1[i] = data.rows[i].totalCost1 / 10000;
                            person[i] = data.rows[i].personNum;
                            person1[i] = data.rows[i].personNum1;
                            option.xAxis.data.push(names[i]);
                            option.series[0].data.push(person[i]);
                            option.series[1].data.push(totalCost[i]);
                            option.series[2].data.push(person1[i]);
                            option.series[3].data.push(totalCost1[i]);
                        }
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    }
                });
            });


    </script>
    <%--<script type="text/javascript">--%>
        <%--$.ajax({--%>
            <%--url: $WEB_ROOT_PATH + "/dhccApi/hospitalizationMonitor/hospitalizationMonitor/cityInfo?hospitalCostStatistics.orgName=violation",--%>
            <%--type: "post",--%>
            <%--async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）--%>
            <%--dataType: "json",--%>
            <%--success: function (data) {--%>
                <%--console.log(data);--%>

                <%--var totalCost=[];--%>
                <%--var person=[];--%>
                <%--var names=[];--%>



                <%--var myChart = echarts.init(document.getElementById('main4'));--%>

                <%--/* app.title = '环形图'; */--%>

                <%--var option = {--%>
                    <%--/*title: {--%>
                        <%--text: 'ECharts 入门示例'--%>
                    <%--},*/--%>
                    <%--tooltip: {},--%>
                    <%--legend: {--%>
                        <%--data:['违规病例数']--%>
                    <%--},--%>
                    <%--xAxis: {--%>
                        <%--type : 'category',--%>
                        <%--axisLabel: {--%>
                            <%--interval:0,--%>
                            <%--rotate:20,--%>
                            <%--textStyle: {--%>
                                <%--fontSize : 10,--%>
                            <%--}--%>
                        <%--} ,--%>
                        <%--data: [],--%>
                        <%--axisTick: {--%>
                            <%--alignWithLabel: true--%>
                        <%--}--%>
                    <%--},--%>
                    <%--yAxis: {},--%>
                    <%--series: [{--%>
                        <%--name: '违规病例数',--%>
                        <%--type: 'bar',--%>
                        <%--data: []--%>
                        <%--}]--%>
                <%--};--%>
                <%--console.log(data)--%>

                <%--for(var i=0;i<data.rows.length;i++){--%>
                    <%--names[i]=data.rows[i].ruleName;--%>
                    <%--totalCost[i]=data.rows[i].violationNum;--%>
                    <%--option.xAxis.data.push(names[i]);--%>
                    <%--option.series[0].data.push( totalCost[i]);--%>
                <%--}--%>
                <%--// 使用刚指定的配置项和数据显示图表。--%>
                <%--myChart.setOption(option);--%>
            <%--}--%>
        <%--});--%>



  <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console'],function(){
	    var a = new Array("日", "一", "二", "三", "四", "五", "六");
		var date = new Date();
		var today_nian = date.getFullYear();
		var today_yue = date.getMonth()+1;
		var today_day = date.getDate();
		var week = date.getDay();
		var str = today_nian+"年"+today_yue+"月"+today_day+"日&nbsp;星期"+ a[week];
		$("#dateweek").html(str);
  });
  </script>
</html>
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
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
    <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
    <title>医院异常项目分析</title>
    <style type="text/css">
        html {
            background-color: #F2F2F2;
        }

        /* div[lay-id=diseasesAnalysisTable] .layui-table-body {
            height: 280px;
        } */
    </style>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-inline" >
                <label class="layui-form-label">医院名称</label>
                <div class="layui-input-inline"style="text-align:center;">
                    <input type="text" style=""id="orgName"name="itemError.orgName" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <button id="medical-jpp-detail-search-cx"
                        class="layui-btn layuiadmin-btn-useradmin"
                        lay-submit lay-filter="LAY-user-front-search2">
                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                </button>
            </div>
        </div>

        <div class="layui-card-body">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md6">
                    <table id="itemError" class="layui-hide" lay-filter="itemErrorTable"></table>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-card-body">
                        <div id="main" style="height: 485px;"></div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<script src="<%=request.getContextPath()%>/app/js/itemError/itemError.js"></script>
<script type="text/javascript">
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // table1("36050201000177")
    function table1(orgCode) {
        require(
            [
                'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                // 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = echarts.init(document.getElementById('main'));

                var option = {
                    tooltip: {
                        trigger:'axis'

                    },
                    legend: {
                        data:['医院异常项目统计']
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
                            name:'异常项目数',
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"医院异常项目统计",
                            "type":"bar",
                            barMaxWidth:20,
                            "data":[],
                            itemStyle:{
                                normal:{
                                    color:'#2284ff'
                                }
                            }
                        }
                    ]
                };

                // 为echarts对象加载数据
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/itemError/itemError/listItem111?itemError.orgCode=" + orgCode,
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (result) {
                        console.log(result)
                        for (var i = 0; i < result.rows.length; i++) {
                            console.log(result.rows[i].itemNum)
                            option.series[0].data.push(result.rows[i].itemNum);
                            option.xAxis[0].data.push(result.rows[i].itemName);
                        }
                        myChart.setOption(option);
                        var ecConfig = require('echarts/config');
                        myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
                    }
                });
                // }
                // );
            });
    }
</script>



</body>
</html>
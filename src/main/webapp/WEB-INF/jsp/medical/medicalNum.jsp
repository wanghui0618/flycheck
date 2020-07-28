<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
    <title>疾病信息</title>
</head>
<body style="overflow:hidden">
<div style="padding: 8px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                    <img style="margin-top:-2px;padding-right: 8px;"
                         src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                    常见疾病Top20

                    <%--<label class="layui-form-label">年份选择</label>--%>
                    <div class="layui-input-inline" style="padding-left: 30px">
                        <input type="text" class="layui-input" id="year" placeholder="yyyy" readonly="true"
                               lay-filter="year">
                    </div>
                </div>

                <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding:3px">
                    <div class="layui-form-item">
                    </div>
                </div>
                <table id="medicalNumTable" class="layui-hide" lay-filter="medicalNumTable"></table>
            </div>
        </div>

        <div class="layui-col-md8">
            <div class="layui-card">
                <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                    <img style="margin-top:-2px;padding-right: 8px;"
                         src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                    常见疾病分析
                </div>

                <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding:3px">
                    <div class="layui-form-item">
                    </div>
                </div>
                <div class="layui-card-body">
                    <div id="main" style="margin-top:-30px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ECharts单文件引入 -->
<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medical/medicalNum.js"></script>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'));

            var option = {
                tooltip: {
                    show: true
                },
                legend: {
                    data: ['']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisLabel: {
                            rotate: 25,
                            fontSize: 8
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
                        "name": "",
                        "type": "bar",
                        "data": [],
                        barWidth: 16,
                        itemStyle: {
                            normal: {
                                color: '#EE7621'
                            }
                        }
                    }
                ]
            };

            // 为echarts对象加载数据
            $.ajax({
                url: $WEB_ROOT_PATH + "/dhccApi/medical/medical/listNumber",
                type: "post",
                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                dataType: "json",
                success: function (result) {
                    var hvdata = result.data;
                    if (hvdata != null && hvdata.length > 0) {
                        var i;
                        if (hvdata.length > 10) {
                            k = 20;
                        } else {
                            k = hvdata.length;
                        }
                        for (var i = 0; i < k; i++) {
                            option.series[0].data.push(hvdata[i].medicalNumber);
                            option.xAxis[0].data.push(hvdata[i].medicalName);
                        }
                        myChart.setOption(option);
                    }
                }
            });

        }
    );
</script>
</body>
</html>
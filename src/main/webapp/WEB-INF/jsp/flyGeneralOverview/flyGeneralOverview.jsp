<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">

    <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
    <!--医疗机构下拉js-->
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
    <title>总体概况</title>
    <style type="text/css">
html {
	background-color: #F2F2F2;
}

.layui-form-select dl {
	max-height: 180px;
}

div[lay-id=diseasesAnalysisTable] .layui-table-body {
	height: 280px;
}

.layui-form-select .layui-edge {
	left: 120px;
}
</style>

</head>
<body>

<div class="layui-fluid" style="overflow: hidden;">
    <div class="layui-col-md12" >
            <div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card" style="height: 320px" >
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">就诊途径</label>
						<div class="layui-input-inline">
						<select name="visitingRoute" id="visitingRoute" lay-filter="myselect">
                           <option >住院</option>
                           <option >门诊</option>
                        </select>
						</div>
					</div>
					<!-- <div class="layui-inline">
						<label class="layui-form-label">人员类型</label>
						<div class="layui-input-inline">
							<input type="text" name="benefitGroupId" id="benefitGroupId" placeholder="请输入人员类型"
								autocomplete="off" class="layui-input">
						</div>
					</div> -->
					 <div class="layui-inline ">
                    <label class="layui-form-label ">人员类型</label>
                    <div class="layui-input-inline">
                        <select name="benefitGroupId" id="benefitGroupId">
                            <option value="" selected>请选择</option>
                            <option value="市属职工">市属职工</option>
                            <option value="市属居民">市属居民</option>
                            <option value="省直（职工）">省直（职工）</option>
                            <option value="省内异地">省内异地</option>
                            <option value="异地省外">异地省外</option>
                            <option value="自费">自费</option>
                        </select>
                    </div>
                </div>
					 <div class="layui-inline">
                        <div style="width: 30px"></div>
                        <label class="layui-form-label">结算年度</label>
                        <div class="layui-input-inline">
                            <!-- <input type="text" class="layui-input" id="year" name="year" placeholder="yyyy" readonly="true" lay-filter="year" > -->
                        	<input type="text" id="year" name="year"  readonly="true" 
									placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                        <%--                           医疗机构做成下拉框--%>
                        <div class="layui-inline">
                            <label class="layui-form-label">医疗机构</label>
                            <div class="layui-input-inline">
                                <input id="getOrgName" name="hospitalName"/>
                                <input type="text" id="orgCode" name="hospitalId" style="display: none"/>
                            </div>
                        </div>
							<div class="layui-inline">
								<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
									lay-filter="flyCheckFind" stylename="search" >
									<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
								<button class="layui-btn layui-btn-primary" 
									id="reset" stylename="allUpdate">
									<i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
								</button>
								<button class="layui-btn" stylename="export" lay-submit lay-filter="LAY-flycheck-front-export">
									<i class="layui-icon layui-icon-file  layuiadmin-button-btn" ></i>导出
								</button>
							</div>
						</div>
                    </div>
              <div class="layui-card-body">
				<table id="flyCheck" class="layui-hide"
					lay-filter="flyCheck">
				</table>
			</div>
                </div>
            </div>
        </div>

        <div class="layui-col-md12" style="height: 500px;margin-top: 10px">

            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 490px">
                <div class="layui-card" style="height: 490px;">
                
                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="hopIn">住院</span>占比统计

                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" >
                                    就诊人次
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    医疗费用
                                </li>
                            </ul>
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" id="flycheck" style="height:450px;">
                                    <div id="flycheck" style="height:450px;margin-left:25px;"></div>
                                </div>
                                <div class="layui-tab-item layui-show" id="flycheck1" style="height:450px;">
                                    <div id="flycheck1" style="height:450px;margin-left:25px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;height: 490px">
                <div class="layui-card" style="height: 490px;">
                  
                    <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        <span id="des2"></span>&nbsp;月度趋势
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" >
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" style="height:450px;">
                                    <div id="flycheck2" style="height: 450px;width:100%;margin-left:25px;"></div>
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
                        <span id="hosp"></span>&nbsp;&nbsp;<span id="level"></span>&nbsp; 病种排名top10
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" >
                                    就诊人次
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    医疗费用
                                </li>
                            </ul>
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;height:450px;">
                                <div class="layui-tab-item layui-show" id="flycheck3" style="height:450px;">
                                    <div id="flycheck3" style="height:450px;margin-left:25px;"></div>
                                </div>
                                <div class="layui-tab-item layui-show" id="flycheck4" style="height:450px;">
                                    <div id="flycheck4" style="height:450px;margin-left:25px;"></div>
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
                        <span id="hosp11"></span> &nbsp; <span id="hos"></span> 药品使用情况top10
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none" >
                                    就诊人次
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    医疗费用
                                </li>
                            </ul>
                            <div class="layui-tab-content" style="margin-top:-10px;padding-left: 0px;padding-right: 0px;height:450px;">
                                <div class="layui-tab-item layui-show" id="flycheck5" style="height:450px;">
                                    <div id="flycheck5" style="height:450px;margin-left:25px;"></div>
                                </div>
                                <div class="layui-tab-item layui-show" id="flycheck6" style="height:450px;">
                                    <div id="flycheck6" style="height:450px;margin-left:25px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </div>
<script src="<%=request.getContextPath()%>/app/js/flyGeneralOverview/flyGeneralOverview.js"></script>
<script type="text/javascript">
    selectedYear=thisYear();
    var visitingRoute="住院";  
    var hospitalId="";
    var hospitalName="";
    hosTop(visitingRoute,selectedYear,hospitalId,hospitalName);
    function hosTop(visitingRoute1,year,hospitalId,hospitalName) {
        require.config({
            paths: {
                echarts: $WEB_ROOT_PATH + '/js/echarts_jpp/echarts-2.2.7/build/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/pie' ,// 使用圆形图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('flycheck'));
                var myChart1 = ec.init(document.getElementById('flycheck1'));
               var option = {
                	    legend: {
                	        orient: 'vertical',
                	        x: '0%',
                	        y:'80%',
                	        data:[]
                	    },
                       tooltip: {
                    	   trigger: 'item',
                    	   alwaysShowContent:true,
                    	   enterable:true,
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        }, 
                        series: [
                            {
                                name: '就诊人次占比',
                                type: 'pie',
                                radius: ['0', '60%'],
                                hoverAnimation: false,
                                center: ['45%', '40%'],
                                avoidLabelOverlap: false,
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: []
                            }
                        ]
                    };
              var  option1 = {
                	    legend: {
                	        orient: 'vertical',
                	        x: '0%',
                	        y:'80%',
                	        data:[]
                	    },
                       tooltip: {
                            trigger: 'item',
                            alwaysShowContent:true,
                     	    enterable:true,
                            formatter: "{a} <br/>{b}: {c}万元 ({d}%)"
                        }, 
                        series: [
                            {
                                name: '医疗费用占比',
                                type: 'pie',
                                radius: ['0', '60%'],
                                hoverAnimation: false,
                                center: ['45%', '40%'],
                                avoidLabelOverlap: false,
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: []
                            }
                        ]
                    };
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind1?flyGeneralOverviewVo.visitingRoute="+visitingRoute+"&flyGeneralOverviewVo.year="+year,
                    type: "post",
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (data) {
                        var name="";
                        var number=0;
                        var ecConfig = require('echarts/config');
                        for (var i = 0; i < data.data.length; i++) {
                        	
                        	if(parseInt(data.data[i].peopleNumber2)>number){
                        		number=data.data[i].peopleNumber2;
                        		name=data.data[i].hospitalName;
                        	};
                            option.series[0].data.push({
                                name: data.data[i].hospitalName,
                                value: data.data[i].peopleNumber2
                            });
                            option1.series[0].data.push({
                                name: data.data[i].hospitalName,
                                value: data.data[i].medicalTotal
                            });
                            option.legend.data.push(data.data[i].hospitalName); 
                            option1.legend.data.push(data.data[i].hospitalName);
                        }
                        myChart.setOption(option);  //    有空值。。。
                        myChart1.setOption(option1);
                        if(data.data[0]==undefined){
                            hopLevel("error",name,year);
                            flycheck1("error",name,year);
                            flycheck2("error",name,year);
                            /* var handding = document.getElementById("hosp");
                            handding.innerText ="";
                            var handding1 = document.getElementById("hosp11");
                            handding1.innerText = "";
                            var handding2 = document.getElementById("des2");
                            handding2.innerText = ""; */
                        }else {
                            /* var handding2 = document.getElementById("des2");
                            handding2.innerText =hospitalName;
                            var handding = document.getElementById("hosp");
                            handding.innerText = hospitalName;
                            var handding1 = document.getElementById("hosp11");
                            handding1.innerText = hospitalName; */
                            selectedYear=year;
                            visitingRoute=visitingRoute1;
                           hopLevel(visitingRoute,hospitalName,year,hospitalId);
                          flycheck1(visitingRoute,hospitalName,year,hospitalId);
                          flycheck2(visitingRoute,hospitalName,year,hospitalId);
                          flycheck3(visitingRoute,hospitalName,year,hospitalId);
                          flycheck4(visitingRoute,hospitalName,year,hospitalId);
                        }
                        visitingRoute=null;
                        myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread);
                        myChart1.on(ecConfig.EVENT.CLICK, eConsoleSpread);
                    }
                });
            }
        );
    }


    function eConsoleSpread(param) {
     var vis= layui.$("#visitingRoute").val();
        $("tr").css("background-color", "");
        var handding2 = document.getElementById("des2");
        handding2.innerText = param.name;
        var handding2 = document.getElementById("hosp");
        handding2.innerText = param.name;
        var handding2 = document.getElementById("hosp11");
        handding2.innerText = param.name;
        hopLevel(vis,param.name,selectedYear," ");
       flycheck1(vis,param.name,selectedYear," ");
       flycheck2(vis,param.name,selectedYear," ");
       flycheck3(vis,param.name,selectedYear," ");
       flycheck4(vis,param.name,selectedYear," ");
    }
 
    function thisYear(){
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        return tYear;
    }
</script>
<%--月度趋势--%>
<script type="text/javascript">
    // outTop(thisYear());
    function hopLevel(visitingRoute,hospitalName,year,hospitalId) {
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
                'echarts/chart/line' // 使用线状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('flycheck2'));
                var option = {
                tooltip: {
                    // show: true
                    trigger: 'axis'

                },
                legend: {
                    data: ['医疗费用','就诊人次']
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
                        name:'医疗费用(万元)',
                        type: 'value'
                    },{
                        name:'就诊人次',
                        type: 'value'
                    }
                ],
                series: [
                    {
                        "name": "医疗费用",
                        "type": "bar",
                        barMaxWidth:30,
                        "data": [],
                        itemStyle: {
                            normal: {
                                color: '#22b0ee'
                            }
                        }
                    },{
                        "name": "就诊人次",
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
                if(visitingRoute=="error"){
                    myChart.setOption(option);
                }else {
                    // 为echarts对象加载数据
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind2?" +
                            "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                            "flyGeneralOverviewVo.visitingRoute="+visitingRoute+"" +
                            "&flyGeneralOverviewVo.hospitalName=" +hospitalName+"&flyGeneralOverviewVo.year="+year,
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (result) {
                            for (var i = 0; i < result.data.length; i++) {
                            	option.series[0].data.push(result.data[i].medicalTotal);
                                option.series[1].data.push(result.data[i].peopleNumber2);
                                option.xAxis[0].data.push(result.data[i].month+"月");
                            }
                            myChart.setOption(option);

                        }
                    });
/*                 var ecConfig = require('echarts/config');

                myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread1); */
            }
            }
        );
    }
</script>
<%--病种top10就诊人次柱状图flycheck1--%>
<script type="text/javascript">
    function flycheck1(visitingRoute,hospitalName,year,hospitalId) {
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
                var myChart = ec.init(document.getElementById('flycheck3'));
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
                                name:'就诊人次',
                                type: 'value'
                            }
                        ],
                        tooltip: {
                            // show: true
                            trigger: 'axis',
                            alwaysShowContent:true,
                         	enterable:true,
                            formatter: hospitalName+" <br/>{b}: {c}人次"

                        },
                        series: [
                        	{
                            	"type": "bar",
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
                if(visitingRoute=="error"){
                    myChart.setOption(option);
                }else {
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind3?" +
                        "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                        "flyGeneralOverviewVo.visitingRoute="+visitingRoute+"" +
                        "&flyGeneralOverviewVo.hospitalName=" + hospitalName+"&flyGeneralOverviewVo.year="+year,
                    type: "post",
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (data) {
                        for (var i = 0; i < data.data.length; i++) {
                        	option.series[0].data.push(data.data[i].peopleNumber2);
                        	option.xAxis[0].data.push(data.data[i].drgsName);
                        }
                        myChart.setOption(option);
                    }
                });
            }
            }
        );
    }
</script>
<%--病种top10医疗费用柱状图flycheck2--%>
<script type="text/javascript">
    function flycheck2(visitingRoute,hospitalName,year,hospitalId) {
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
                var myChart = ec.init(document.getElementById('flycheck4'));
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
                                name:'医疗费用',
                                type: 'value'
                            }
                        ],
                        tooltip: {
                            // show: true
                            trigger: 'axis',
                            alwaysShowContent:true,
                         	enterable:true,
                            formatter: hospitalName+" <br/>{b}: {c}(万元)"

                        },
                        series: [
                        	{
                            	"type": "bar",
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
                if(visitingRoute=="error"){
                    myChart.setOption(option);
                }else {
                $.ajax({
                    url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind4?" +
                        "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                        "flyGeneralOverviewVo.visitingRoute="+visitingRoute+"" +
                        "&flyGeneralOverviewVo.hospitalName=" + hospitalName+"" +
                        "&flyGeneralOverviewVo.year="+year,
                    type: "post",
                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    dataType: "json",
                    success: function (data) {
                        for (var i = 0; i < data.data.length; i++) {
                        	option.series[0].data.push(data.data[i].medicalTotal);
                        	option.xAxis[0].data.push(data.data[i].drgName);
                        }
                        myChart.setOption(option);
                    }
                });
            }
            }
        );
    }
</script>
<%--//药品使用情况top10就诊人次柱状图--%>
<script type="text/javascript">

    function flycheck3(visitingRoute,hospitalName,year,hospitalId) {
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
                var myChart = ec.init(document.getElementById('flycheck5'));
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
                            name:'就诊人次',
                            type: 'value'
                        }
                    ],
                    tooltip: {
                        // show: true
                        trigger: 'axis',
                        alwaysShowContent:true,
                        enterable:true,
                        formatter: hospitalName+" <br/>{b}: {c}人次"

                    },
                    series: [
                        {
                            "type": "bar",
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
                if(visitingRoute=="error"){
                    myChart.setOption(option);
                }else {
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind5?flyGeneralOverviewVo.visitingRoute="+visitingRoute+"" +
                            "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                            "&flyGeneralOverviewVo.hospitalName=" + hospitalName+"" +
                            "&flyGeneralOverviewVo.year="+year,
                        type: "post",
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (data) {
                            var itemIds="";
                            for (var i = 0; i < data.data.length; i++) {

                            itemIds+= data.data[i].itemId+",";
                            }
                                $.ajax({
                                    url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind6?flyGeneralOverviewVo.hospitalName=" + hospitalName+"" +
                                        "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                                        "&flyGeneralOverviewVo.year="+year,
                                    type: "post",
                                    async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                                    dataType: "json",
                                    data:{"flyGeneralOverviewVo.itemIds":itemIds},
                                    success: function (res) {
                                        if(res!=null){
                                            for(var i=0;i<res.data.length;i++){
                                                option.series[0].data.push(res.data[i].visitsCount);
                                                option.xAxis[0].data.push(res.data[i].visitsItemName);
                                            }
                                        }
                                        myChart.setOption(option);
                                    }



                                })



                        }
                    });
                }
            }
        );
    }
</script>
<%--//药品使用情况top10医疗费用柱状图--%>
<script type="text/javascript">

    function flycheck4(visitingRoute,hospitalName,year,hospitalId) {
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
                var myChart = ec.init(document.getElementById('flycheck6'));
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
                            name:'医疗费用',
                            type: 'value'
                        }
                    ],
                    tooltip: {
                        // show: true
                        trigger: 'axis',
                        alwaysShowContent:true,
                        enterable:true,
                        formatter: hospitalName+" <br/>{b}: {c}万元"

                    },
                    series: [
                        {
                            "type": "bar",
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
                if(visitingRoute=="error"){
                    myChart.setOption(option);
                }else {
                    $.ajax({
                        url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind5?flyGeneralOverviewVo.visitingRoute="+visitingRoute+"" +
                            "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                            "&flyGeneralOverviewVo.hospitalName=" + hospitalName+"" +
                            "&flyGeneralOverviewVo.year="+year,
                        type: "post",
                        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        dataType: "json",
                        success: function (data) {
                            var itemIds = " ";
                            for (var i = 0; i < data.data.length; i++) {
                                itemIds += data.data[i].itemId + ",";
                            }
                            $.ajax({
                                url: $WEB_ROOT_PATH + "/dhccApi/flyGeneralOverview/flyGeneralOverview/findMedicalSumAmount?" +
                                    "flyGeneralOverviewVo.hospitalId="+hospitalId+"&"+
                                    "flyGeneralOverviewVo.hospitalName=" + hospitalName+"" +
                                    "&flyGeneralOverviewVo.year="+year,
                                type: "post",
                                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                                dataType: "json",
                                data:JSON.stringify({"flyGeneralOverviewVo.itemIdList":itemIds}),
                                cache:false,
                                contentType:"applicaiton/json",
                                success: function (res) {
                                    if(res!=null){
                                        for(var i=0;i<res.data.length;i++){
                                            option.series[0].data.push(res.data[i].medicalCost);
                                            option.xAxis[0].data.push(res.data[i].medicalName);
                                        }
                                    }
                                    myChart.setOption(option);
                                }



                            })



                        }
                    });
                }
            }
        );
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
      	   trigger:'click',
            type:'year',
            value:"",
            range: '~' ,
            max:thisYear(),//规定时间期限
        });

    });




</script>
</body>
</html>
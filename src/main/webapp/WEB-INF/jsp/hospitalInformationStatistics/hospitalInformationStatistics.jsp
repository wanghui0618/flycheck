<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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
    <script src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
    <title>医院详细信息统计</title>
</head>
<body>
<div class="layui-fluid" style="overflow: hidden;">

    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item" id="excelInfo">
                            <div class="layui-inline" >
                                <label class="layui-form-label ">就诊途径</label>
                                <div class="layui-input-inline">
                                    <select id="treatmentApproach" name="CostStatisticsVo.medicalName">
                                        <!-- <option value="byC" selected selected>请选择</option> -->
                                        <option value="BeHospitalized" selected>住院</option>
                                        <option value="Outpatient">门诊</option>
                                    </select>
                                </div>
                            </div>
                            <%--                            <div class="layui-inline">--%>
                            <%--                                <label class="layui-form-label">就诊途径</label>--%>
                            <%--                                <div class="layui-input-inline">--%>
                            <%--                                    <input type="text" id="treatmentApproach"--%>
                            <%--                                           name="CostStatisticsVo.medicalName" placeholder="请输入" autocomplete="off"--%>
                            <%--                                           class="layui-input">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>

                            <div class="layui-inline">
                                <label class="layui-form-label">人员类别</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="crowdClassification"
                                           name="CostStatisticsVo.medicalName" placeholder="请输入" autocomplete="off"
                                           class="layui-input">
                                </div>
                            </div>


                            <div class="layui-inline">
                                <label class="layui-form-label">医疗机构</label>
                                <div class="layui-input-inline">
                                    <input id="getOrgName" name="hospitalName"/>
                                    <input type="text" id="orgCode" name="hospitalCode"
                                           style="display: none;"/>
                                </div>
                            </div>
                            <%--                            <div class="layui-inline cxtjtop">--%>
                            <%--                                <label class="layui-form-label">医院编码</label>--%>
                            <%--                                <div class="layui-input-block">--%>
                            <%--                                    <input type="text" id="hospitalCode"--%>
                            <%--                                           name="CostStatisticsVo.medicalName" placeholder="请输入" autocomplete="off"--%>
                            <%--                                           class="layui-input">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>

                            <%--                            <div class="layui-inline cxtjtop">--%>
                            <%--                                <label class="layui-form-label">医院名称</label>--%>
                            <%--                                <div class="layui-input-block">--%>
                            <%--                                    <input type="text" id="hospitalName"--%>
                            <%--                                           name="CostStatisticsVo.medicalName" placeholder="请输入" autocomplete="off"--%>
                            <%--                                           class="layui-input">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>

                            <div id="xiala" onclick="showSearch()"
                                 style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">更多<img
                                    style="margin: 0 auto; height: 6px;width: 8px;"
                                    src="<%=request.getContextPath()%>/images/main/xiala.png"/></div>
                            <div class="layui-inline cxtjtop">
                                <label class="layui-form-label">结算日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="settlementDate"
                                           name="balanceDate" placeholder="请选择结算时间">
                                </div>
                            </div>


                            <div id="shangla" onclick="hideSearch()"
                                 style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">收起<img
                                    style="margin: 0 auto; height: 6px;width: 8px;"
                                    src="<%=request.getContextPath()%>/images/main/shangla.png"/></div>

                            <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="search"
                                        lay-filter="LAY-user-front-search" onclick="search()">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                                </button>

                                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="allUpdate"
                                        lay-filter="LAY-user-front-reset" onclick="reset()">
                                    <i class="layui-icon layui-icon-set layuiadmin-button-btn"></i>重置
                                </button>

                                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="export"
                                        lay-filter="LAY-user-front-export" id="export">
                                    <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                                </button>
                            </div>
                            <div id="return" onclick="showReturn()">
                            <i class="layui-icon layui-icon-return" style="font-size: 25px; color:#xe683;top:18px"></i>  
                             </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md12">
            <!-- 住院 -->
            <div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card-body">
                    <table id="hospitalizationTable" class="layui-hide" lay-filter="hospitalizationTable"></table>
                    <table id="outpatientTable" class="layui-hide" lay-filter="outpatientTable"></table>
                </div>
            </div>
             <!-- 门诊 -->
          <!--   <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card-body">
                    <table id="outpatientTable" class="layui-hide" lay-filter="outpatientTable"></table>
                </div>
            </div> --> 

        </div>

        <div class="layui-col-md12">

            <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        药品、诊疗、耗材占比
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    住院
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    门诊
                                </li>
                            </ul>
                            <div class="layui-tab-content"
                                 style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" id="one" style="height:314px"></div>
                                <div class="layui-tab-item" id="two" style="height:314px"></div>
                                <!-- <div class="layui-tab-item layui-show" id="one" style="height:314px;">
                                    <div id="one" style="height:314px;margin-left:25px;"></div>
                                </div>
                                <div class="layui-tab-item " id="two" style="height:314px;">
                                    <div id="two" style="height:314px;margin-left:25px;"></div>
                                </div> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        各收费项目占比
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    住院
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    门诊
                                </li>
                            </ul>
                            <div class="layui-tab-content"
                                 style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" id="three" style="height:314px;">
                                    <div id="three" style="height:314px;margin-left:25px;"></div>
                                </div>
                                <div class="layui-tab-item " id="four" style="height:314px;">
                                    <div id="four" style="height:314px;margin-left:25px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        月度平均住院天数
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <div class="layui-tab-content"
                                 style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div id="five" style="height:314px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="layui-col-md12">

            <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card" id="card1">
                    <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        月度平均费用
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    住院
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    门诊
                                </li>
                            </ul>
                            <div class="layui-tab-content"
                                 style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                <div class="layui-tab-item layui-show" id="fourZy" style="height:314px"></div>
                                <div class="layui-tab-item" id="fourMz" style="height:314px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        科室费用TOP10
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    住院
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    门诊
                                </li>
                            </ul>
                            <div class="layui-tab-content"
                                 style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                 <div class="layui-tab-item layui-show" id="eight" style="height:314px"></div>
                                <div class="layui-tab-item" id="nine" style="height:314px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4" style="padding-left: 2px;padding-right: 5px;">
                <div class="layui-card">
                    <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
                        <img style="margin-top:-2px;padding-right: 8px;"
                             src="<%=request.getContextPath() %>/images/auditing/mark.png"/>
                        诊疗费用TOP10
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title"
                                style="height:20px;line-height:15px;border:none;margin-top: -28px;float: right;">
                                <li class="layui-this"
                                    style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    住院
                                </li>
                                <img style="height: 25px;width: 2px;"
                                     src="<%=request.getContextPath() %>/images/auditing/fenge_red.png"/>
                                <li style="height:15px;line-height:15px;min-width: 25px;margin: 4px;padding:4px;border-bottom:none">
                                    门诊
                                </li>
                            </ul>
                            <div class="layui-tab-content"
                                 style="margin-top:-10px;padding-left: 0px;padding-right: 0px;">
                                 <div class="layui-tab-item layui-show" id="ten" style="height:314px"></div>
                                <div class="layui-tab-item" id="eleven" style="height:314px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
<script src="<%=request.getContextPath()%>/app/js/hospitalInformationStatistics/hospitalInformationStatistics.js"></script>
<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript">
    $(".cxtjtop").hide();
    $("#shangla").hide();

    // function showSearch() {
    //     $("#shangla").show();
    //     $("#xiala").hide();
    //     $(".cxtjtop").show();
    // }
    //
    // function hideSearch() {
    //     $(".cxtjtop").hide();
    //     $("#shangla").hide();
    //     $("#xiala").show();
    // }
</script>
</body>
</html>
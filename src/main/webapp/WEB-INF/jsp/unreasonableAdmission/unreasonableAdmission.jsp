<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
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
    <title>超期住院</title>
    <style>
        /*.layui-form-select .layui-input {*/
        /*    width: 150px;*/
        /*}*/
    </style>
</head>
<style>
    .layui-form-label {
        width: 90px;
    }
</style>
<body>

<div class="layui-fluid" style="overflow:hidden;">
    <div class="layui-row layui-col-space15" style="overflow:hidden;">

        <div class="layui-col-md12">
            <div class="layui-card" style="min-width: 100%">
                <div class=" layui-card-header layuiadmin-card-header-auto" style="padding-top: 0px">

                    <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding-top: 0px"
                         id="unreasonableAdmission_form" lay-filter="unreasonableAdmission_form">
                        <div class="layui-form-item">
                            <%--                           医疗机构做成下拉框--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">医疗机构</label>
                                <div class="layui-input-inline">
                                    <input id="getOrgName" name="unreasonableAdmission.hospitalName"/>
                                    <input type="text" id="orgCode" name="unreasonableAdmission.hospitalId"
                                           style="display: none;"/>
                                </div>
                            </div>
                            <%--                            <div class="layui-inline">--%>
                            <%--                                <label class="layui-form-label">医疗机构</label>--%>
                            <%--                                <div class="layui-input-inline">--%>
                            <%--                                    <input type="text" name="unreasonableAdmission.hospitalId" placeholder="请输入"--%>
                            <%--                                           autocomplete="off"--%>
                            <%--                                           class="layui-input">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="layui-inline">--%>
                            <%--                                <label class="layui-form-label">医院名称</label>--%>
                            <%--                                <div class="layui-input-inline">--%>
                            <%--                                    <input type="text" name="unreasonableAdmission.hospitalName" placeholder="请输入"--%>
                            <%--                                           autocomplete="off"--%>
                            <%--                                           class="layui-input">--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">结算日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="billDate" placeholder=" 请选择结算时间 "
                                           name="unreasonableAdmission.billDateStr">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">药费占比</label>
                                <div class="layui-input-inline" style="width: 80px;">
                                    <select name="unreasonableAdmission.code1" id="code1" autocomplete="off"
                                            style="width: 90px;">
                                        <option value=">" selected="selected">大于</option>
                                        <option value="=">等于</option>
                                        <option value="<">小于</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline" style="width: 38px;">
                                    <input type="text" name="unreasonableAdmission.proportionOfMedicines" id="medicineFee"
                                           style="width: 76px;" value="" autocomplete="off" lay-verify="percentage"
                                           class="layui-input">
                                </div>
                                <div class="layui-input-inline" style="width: 40px;">
                                    <input type="text" name="baifenhao" id="baifenhao"
                                           style="width: 36px;" autocomplete="off" class="layui-input"
                                           value="%" readonly="readonly">
                                </div>
                            </div>

                            <!-- 下拉按钮 -->
                            <div id="xiala" onclick="showSearch()"
                                 style="color:#2284FF;cursor:pointer;z-index:9999; margin-left: 10px;display: inline-block;">
                                更多
                                <img style="margin: 0 auto;height:6px;width:8px;"
                                     src="<%=request.getContextPath()%>/images/main/xiala.png"/>
                            </div>

                            <div class="layui-inline cxtjtop">
                                <label class="layui-form-label">检查费占比 </label>
                                <div class="layui-input-inline" style="width: 80px;">
                                    <select name="unreasonableAdmission.code2" id="code2" autocomplete="off"
                                            style="width: 90px;">
                                        <option value=">" selected="selected">大于</option>
                                        <option value="=">等于</option>
                                        <option value="<">小于</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline" style="width: 38px;">
                                    <input type="text" name="unreasonableAdmission.inspectionFeeRatio" id="inspectionFee"
                                           style="width: 76px;" value="" autocomplete="off" lay-verify="percentage"
                                           class="layui-input">
                                </div>
                                <div class="layui-input-inline" style="width: 40px;">
                                    <input type="text" name="baifenhao"
                                           style="width: 36px;" autocomplete="off" class="layui-input"
                                           value="%" readonly="readonly">
                                </div>
                            </div>
                            <div class="layui-inline cxtjtop">
                                <label class="layui-form-label">治疗费占比</label>
                                <div class="layui-input-inline" style="width: 80px;">
                                    <select name="unreasonableAdmission.code3" id="code3" autocomplete="off"
                                            style="width: 90px;">
                                        <option value=">" selected="selected">大于</option>
                                        <option value="=">等于</option>
                                        <option value="<">小于</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline" style="width: 38px;">
                                    <input type="text" name="unreasonableAdmission.costOfTreatment"
                                           style="width: 76px;" value="" autocomplete="off" id="treatmentCosts" lay-verify="percentage"
                                           class="layui-input">
                                </div>
                                <div class="layui-input-inline" style="width: 40px;">
                                    <input type="text" name="baifenhao" id=""
                                           style="width: 36px;" autocomplete="off" class="layui-input"
                                           value="%" readonly="readonly">
                                </div>
                            </div>

                            <div class="layui-inline cxtjtop">
                                <label class="layui-form-label">入院日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="admissionDate" placeholder=" 请选择入院时间 "
                                           name="unreasonableAdmission.admissionDateStr">
                                </div>
                            </div>
                            <div class="layui-inline cxtjtop">
                                <label class="layui-form-label">出院日期</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="dischargeDate" placeholder=" 请选择出院时间 "
                                           name="unreasonableAdmission.dischargeDateStr">
                                </div>
                            </div>

                            <!-- 上收按钮 -->
                            <div id="shangla" onclick="hideSearch()"
                                 style="color:#2284FF;cursor:pointer; margin-left: 10px;z-index:9999;display:inline-block;">
                                收起
                                <img style="margin: 0 auto;height:6px;width:8px;"
                                     src="<%=request.getContextPath()%>/images/main/shangla.png"/>
                            </div>

                            <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                                        lay-filter="LAY-front-search-to" stylename="search">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                                </button>
                                <button onclick="restForm()" class="layui-btn layui-btn-primary" type="button"
                                        id="resets" stylename="allUpdate">
                                    <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                                </button>

                                <button id="violation-dc" stylename="export"
                                        class="layui-btn layuiadmin-btn-useradmin"
                                        lay-submit lay-filter="LAY-user-front-export">
                                    <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="layui-card-body" style="min-height: 553px">
                        <script type="text/html" id="caseInfo">
                            <a class="layui-btn layui-btn-xs" lay-event="selectDetail">结算明细</a>
                        </script>
                        <table id="unreasonableAdmission" class="layui-hide" lay-filter="unreasonableAdmission"></table>

                        <div class="layui-tab layui-tab-brief">
                            <ul class="layui-tab-title">
                                <li class="layui-this">汇总：</li>
                                <li>总病例数：<span class="layui-badge" id="rowsum"></span></li>
                                <li>涉及病例总金额：<span class="layui-badge" id="sumAmount"></span></li>
                            </ul>
                            <div class="layui-tab-content"></div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(".cxtjtop").hide();
    $("#shangla").hide();
    //
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


<script type="text/javascript"
        src="<%=request.getContextPath() %>/app/js/unreasonableAdmission/unreasonableAdmission.js"></script>
<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/login.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/tree/css/zTreeStyle.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
    <title>限医院等级用药</title>
</head>
<style>
    /*.layui-form-label {*/
    /*    width: 100px;*/
    /*}*/

    /*.layui-form-select .layui-edge {*/
    /*left: 120px;*/
    /*}*/
</style>

<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto" id="myform" lay-filter="myform">

            <div class="layui-inline">
                <label class="layui-form-label">就医类型</label>
                <div class="layui-input-inline">
                    <select id=type name="type" lay-filter="type">
                        <option value="0">住院</option>
                        <option value="1">门诊</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">医疗机构</label>
                <div class="layui-input-inline">
                    <input id="getOrgName" name="flyCheckMedicalVo.hospitalName"/>
                    <input type="text" id="orgCode" name="flyCheckMedicalVo.hospitalId" style="display: none;"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">诊断</label>
                <div class="layui-input-inline">
                    <input id="getDiagName" name="flyCheckMedicalVo.admissionDiseaseName"/>
                    <input type="text" id="diagCode" name="flyCheckMedicalVo.admissionDiseaseId"
                           style="display: none;"/>
                </div>
            </div>
           

            <div id="xiala" onclick="showSearch()"
                 style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">更多<img
                    style="margin: 0 auto; height: 6px;width: 8px;"
                    src="<%=request.getContextPath()%>/images/main/xiala.png"/></div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">结算日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="flyCheckMedicalVo.billDate" autocomplete="off"
                           class="layui-input" id="billData" placeholder="结算日期 ">
                </div>
            </div>


            <div class="layui-inline cxtjtop">
                <label class="layui-form-label">医院级别</label>
                <div class="layui-input-inline">
                    <input type="text" id="pLevel"
                           name="flyCheckMedicalVo.pLevel" placeholder="查询医院级别"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" id="admissionDates">入院时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" autocomplete="off"
                           id="admissionDate" name="flyCheckMedicalVo.admissionDate"
                           placeholder="入院时间">
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" id="dischargeDates">出院时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" autocomplete="off"
                           id="dischargeDate" name="flyCheckMedicalVo.dischargeDate"
                           placeholder="出院时间">
                </div>
            </div>

            <div id="shangla" onclick="hideSearch()"
                 style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">收起<img
                    style="margin: 0 auto; height: 6px;width: 8px;"
                    src="<%=request.getContextPath()%>/images/main/shangla.png"/>
            </div>

            <div class="layui-inline">
                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="search"
                        lay-filter="LAY-personalInformationInquiry-front-search">
                    <i class="layui-icon  layuiadmin-button-btn layuiadmin-button-btn"></i>查询
                </button>

                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="allUpdate"
                        lay-filter="LAY-user-front-reset">
                    <i class="layui-icon layui-icon-set layuiadmin-button-btn"></i>重置
                </button>

                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="export"
                        lay-filter="LAY-flyTreatmentFeeCountStatistics-front-search">
                    <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                </button>

            </div>
        </div>


        <div class="layui-card-body">
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="searcDetail">结算明细</a>
            </script>
            <table id="hospitalLevelRmacyTable" class="layui-hide"
                   lay-filter="hospitalLevelRmacyTable">
            </table>
        </div>
        <div class="layui-card-body">
            <table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this">汇总：</li>
                    <li>总病例数：<span class="layui-badge" id="total"></span></li>
                    <li>涉及病例总金额：<span class="layui-badge" id="money"></span></li>
                    <li>涉及明细数量：<span class="layui-badge" id="totalMx"></span></li>
                    <li>涉及明细总金额：<span class="layui-badge" id="moneyMx"></span></li>
                </ul>
                <div class="layui-tab-content"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/hospitalLevelRmacy/hospitalLevelRmacy.js"></script>
<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<!--诊断下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/diagDictSelect.js"></script>
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
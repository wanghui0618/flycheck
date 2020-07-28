<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<style>
    .layui-form-select .layui-edge {
        left: 120px;
    }
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/layui/dist-jpp/dist/formSelects-v4.css">--%>
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <title>个人信息查询</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <form class="layui-form layui-card-header layuiadmin-card-header-auto"
             action="">
            <div class="layui-form-item">
                <div class="layui-inline" id="testbelong">
                    <label class="layui-form-label ">就诊途径</label>
                    <div class="layui-input-inline">
                        <select id="belonginfo" name="personalInformationInquiryVo.belong">
                            <option value="" selected>请选择</option>
                            <option value="住院">住院</option>
                            <option value="门诊">门诊</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline ">
                    <label class="layui-form-label ">险种类型</label>
                    <div class="layui-input-inline">
                        <select name="personalInformationInquiryVo.benefitType">
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
                    <label class="layui-form-label">医疗机构</label>
                    <div class="layui-input-inline">
                        <input id="getOrgName" name="personalInformationInquiryVo.hospitalName"/>
                        <input type="text" id="orgCode" name="personalInformationInquiryVo.hospitalId"
                               style="display: none;"/>
                    </div>
                </div>


                <div id="xiala" onclick="showSearch()"
                     style="color:#2284FF;cursor:pointer;z-index:9999; margin-left: 10px;display: inline-block;">更多<img
                        style="margin: 0 auto;height: 6px;width: 8px;"
                        src="<%=request.getContextPath()%>/images/main/xiala.png"/></div>


                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">诊断</label>
                    <div class="layui-input-inline">
                        <input id="getDiagName" name="personalInformationInquiryVo.dischargeDiseaseNameMain"/>
                        <input type="text" id="diagCode" name="personalInformationInquiryVo.dischargeDiseaseIdMain"
                               style="display: none;"/>
                    </div>
                </div>


                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">结算时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="personalInformationInquiryVo.billDate"
                               autocomplete="off" class="layui-input" id="billData"
                               placeholder="选择结算时间">
                    </div>
                </div>

                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">入院时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" autocomplete="off"
                               id="admissionTime"
                               name="personalInformationInquiryVo.admissionDate"
                               placeholder="选择入院时间">
                    </div>
                </div>

                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">出院时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" autocomplete="off"
                               id="dischargedTime"
                               name="personalInformationInquiryVo.dischargeDate"
                               placeholder="选择出院时间">
                    </div>
                </div>

                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">科室名称</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" autocomplete="off"
                               name="personalInformationInquiryVo.DischargeDeptName"
                               placeholder="请输入科室名称">
                    </div>
                </div>


                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">住院号</label>
                    <div class="layui-input-inline">
                        <input type="text" id="zyzyh" class="layui-input"
                               autocomplete="off" name="personalInformationInquiryVo.zyh"
                               placeholder="请输入住院号">
                    </div>
                </div>

                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label">结算号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" autocomplete="off"
                               name="personalInformationInquiryVo.hisid" placeholder="结算号">
                    </div>
                </div>
                <div id="shangla" onclick="hideSearch()"
                     style="color:#2284FF;cursor:pointer; margin-left: 38px;z-index:9999;display: inline-block;">收起<img
                        style="margin: 0 auto;height: 6px;width: 8px;"
                        src="<%=request.getContextPath()%>/images/main/shangla.png"/></div>

                <div class="layui-inline ">
                    <button class="layui-btn " lay-submit stylename="search"
                            lay-filter="LAY-personalInformationInquiry-front-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                    <button id="reset" type="reset" class="layui-btn " stylename="allUpdate">
                        <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                    </button>
                    <button class="layui-btn layuiadmin-btn-useradmin" id="exportInfo" stylename="export">
                        <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                    </button>
                </div>
            </div>
        </form>

        <div class="layui-card-body">
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="searcDetail">结算明细</a>
            </script>
            <table id="personalInformationInquiryTable" class="layui-hide"
                   lay-filter="personalInformationInquiryTable">
            </table>

        </div>
    </div>
</div>


<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<!--诊断下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/diagDictSelect.js"></script>
<script type="text/javascript">
    $(".cxtjtop").hide();
    $("#shangla").hide();
</script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/flyTreatmentFeeCount/personalInformationInquiry.js"></script>
</body>
</html>

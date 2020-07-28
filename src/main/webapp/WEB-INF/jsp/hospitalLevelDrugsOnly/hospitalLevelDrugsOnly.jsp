<%--
  Created by IntelliJ IDEA.
  User: WT
  Date: 2020/1/2
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <title>限医院等级用药</title>
    <style>
        .textbox.easyui-fluid.combo {
            margin-top: 0px;}
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">医疗机构</label>
                    <div class="layui-input-inline">
                        <input id="getOrgName" name="hospitalName"/>
                        <input type="text" id="orgCode" name="hospitalCode" style="display: none;"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">结算日期</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="SettlementDate" type="text" id="SettlementDate"
                               placeholder="20180808/20181107">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">就医类型</label>
                    <div class="layui-input-inline">
                        <select name="TypeOfTreatment" id="TypeOfTreatment">
                            <option value="BeHospitalized" selected>住院</option>
                            <option value="Outpatient">门诊</option>
                        </select>
                    </div>
                </div>
                <div id="xiala" onclick="showSearch()" style="color:#2284FF;cursor:pointer;z-index:9999; margin-left: 10px;display: inline-block;">更多<img style="margin: 0 auto;height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/xiala.png" /></div>
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px;">诊断编码</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="DiagnosticCode" type="text"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px;">诊断名称</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="DiagnosticName" type="text"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px;">入院日期</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="AdmissionDate" type="text" id="AdmissionDate"
                               placeholder="20180808/20181107">
                    </div>
                </div>
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px;">出院日期</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="DischargeDate" type="text" id="DischargeDate"
                               placeholder="20180808/20181107">
                    </div>
                </div>
<!--                 <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px">医保项目编码</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="itemId" type="text"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px">医保项目名称</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="itemName" type="text"
                               placeholder="请输入">
                    </div>
                </div>
               
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px">医院项目编码</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="itemIdHos" type="text" style="display: none;"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="layui-inline cxtjtop">
                    <label class="layui-form-label" style="width: 100px">医院项目名称</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="itemNameHos" type="text" style="display: none;"
                               placeholder="请输入">
                    </div>
                </div> 
                 -->
                 <div class="layui-inline cxtjtop">
                <label class="layui-form-label" style="width: 100px;">项目编码</label>
                <div class="layui-input-inline">
                    <input id="getItemId" name="hospitalLevelDrugsOnly.itemId"/>
                     <input type="text" id="itemId" name="hospitalLevelDrugsOnly.orgCode"  style="display: none;"/>
                    <%--<input type="text" id="itemName" name="childrensDrugs.itemName" style="display: none;"/>--%>
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" style="width: 100px;">项目名称</label>
                <div class="layui-input-inline">
                    <input id="getItemName" name="hospitalLevelDrugsOnly.itemName"/>
                    <input type="text" id="itemName" name="hospitalLevelDrugsOnly.orgName"  style="display: none;"/>
                    <%--<input type="text" id="itemCode" name="childrensDrugs.itemId" style="display: none;"/>--%>
                </div>
            </div>
                
                
                <div id="shangla" onclick="hideSearch()" style="color:#2284FF;cursor:pointer; margin-left: 68px;z-index:9999;display: inline-block;">收起<img style="margin: 0 auto;height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/shangla.png" /></div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="search"
                            lay-filter="LAY-user-front-search" onclick="search()">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="allUpdate"
                            lay-filter="LAY-user-front-search" onclick="reset()">
                        <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                    </button>
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="export"
                            lay-filter="LAY-user-front-search" id="export">
                        <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                    </button>
                </div>
                
            </div>
        </div>
        <div class="layui-card-body">
            <table id="dg" class="layui-hide" lay-filter="dg"></table>
        </div>
        
        <!-- <div class="layui-form-item layui-row">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 100px;">汇总：</label>
                        <button class="layui-btn layui-btn-radius layui-btn-danger">
                            总病例数<span id="total_number_of_cases"></span>
                        </button>
                        <button class="layui-btn layui-btn-radius layui-btn-danger">
                            涉及病例总金额<span id="total_amount"></span>
                        </button>
                    </div>
                </div> -->
                
                  <div class="layui-tab layui-tab-brief">
                            <ul class="layui-tab-title">
                                <li class="layui-this">汇总：</li>
                                <li>总病例数：<span class="layui-badge" id="total_number_of_cases"></span></li>
                                <li>涉及病例总金额：<span class="layui-badge" id="total_amount"></span></li>
                            </ul>
                            <div class="layui-tab-content"></div>
                        </div>  
    </div>
</div>

</body>
</html>
<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/hospitalLevelDrugsOnly/hospitalLevelDrugsOnly.js"></script>
<!--医保项目编码下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/childrensDrugs/itemIdDiscSelect.js"></script>
<!--医保项目名称下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/childrensDrugs/itemNameDiscSelect.js"></script>


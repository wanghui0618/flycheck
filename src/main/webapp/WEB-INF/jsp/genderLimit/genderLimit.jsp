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
    <title>限性别用药</title>
</head>
<style>
    .layui-form-label {
        width: 100px;
    }
    .layui-form-select .layui-edge {
        left: 64.767px;
    }
    .layui-edge {
        margin-left: 73px;

    }
</style>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto" id="dataForm">

            <div class="layui-inline">
                <label class="layui-form-label" style="width: 80px;">医疗机构</label>
                <div class="layui-input-inline">
                    <input id="getOrgName" name="genderLimit.hospitalName"/>
                    <input type="text" id="orgCode" name="genderLimit.hospitalId" style="display: none;"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 100px;padding-left:40px">诊断</label>
                <div class="layui-input-inline">
                    <input id="getDiagName" name="genderLimit.admissionDiseaseName"/>
                    <input type="text" id="diagCode" name="genderLimit.admissionDiseaseId" style="display: none;"/>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label" style="width: 130px;padding-left:40px">医保项目编码</label>
                <div class="layui-input-inline">
                    <input id="getItemId" name="genderLimit.itemId"/>
                    <input type="text" id="itemId" name="genderLimit.orgCode"  style="display: none;"/>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label" style="width: 130px;padding-left:40px">医保项目名称</label>
                <div class="layui-input-inline">
                    <input id="getItemName" name="genderLimit.itemName"/>
                    <input type="text" id="itemName" name="genderLimit.orgName"  style="display: none;"/>
                </div>
            </div>


            <!-- 下拉按钮 -->
            <div id="xiala" onclick="showSearch()"
                 style="color:#2284FF;cursor:pointer;z-index:9999; margin-left: 10px;display: inline-block;">更多
                <img style="margin: 0 auto;height:6px;width:8px;"
                     src="<%=request.getContextPath()%>/images/main/xiala.png"/>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" style="width: 80px;">性别</label>
                <div class="layui-input-inline">
                    <select name="genderLimit.patientGender" id="patientGender">
                        <option value="" selected>请选择</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
            </div>

            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" style="width: 100px;padding-left:40px">结算日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="balanceDate" name="balanceDate"
                           placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
                </div>
            </div>


            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" style="width: 130px;padding-left:40px">入院日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="admissionDate" name="admissionDate"
                           placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
                </div>
            </div>


            <div class="layui-inline cxtjtop">
                <label class="layui-form-label" style="width: 130px;padding-left:40px">出院日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="dischargeDate" name="dischargeDate"
                           placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
                </div>
            </div>

            <!-- 上收按钮 -->
            <div id="shangla" onclick="hideSearch()"
                 style="color:#2284FF;cursor:pointer; margin-left: 10px;z-index:9999;display:inline-block;">收起
                <img style="margin: 0 auto;height:6px;width:8px;"
                     src="<%=request.getContextPath()%>/images/main/shangla.png"/>
            </div>

            <div class="layui-inline">
                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                        lay-filter="LAY-user-front-search" stylename="search">
                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                </button>

                <button class="layui-btn layui-btn-primary" lay-submit
                        id="resets" stylename="allUpdate" lay-filter="LAY-user-front-reset">
                    <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                </button>

                <button id="violation-dc" stylename="export"
                        class="layui-btn layuiadmin-btn-useradmin"
                        lay-submit lay-filter="LAY-user-front-export">
                    <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                </button>
            </div>

        </div>
        <div class="layui-card-body">
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="searcDetail">结算明细</a>
            </script>
            <table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
            
        </div>
        <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this">汇总：</li>
                    <li>总病例数：<span class="layui-badge" id="medicalCount"></span></li>
                    <li>涉及病例总金额：<span class="layui-badge" id="medicalTotal"></span></li>
                    <li>涉及明细数量：<span class="layui-badge" id="detailCount"></span></li>
                    <li>涉及明细总金额：<span class="layui-badge" id="detailTotal"></span></li>
                </ul>
                <div class="layui-tab-content"></div>
            </div>
    </div>
</div>

<script type="text/javascript">
    $(".cxtjtop").hide();
    $("#shangla").hide();

</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/genderLimit/genderLimit.js"></script>

<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<!--诊断下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/diagDictSelect.js"></script>
<!--医保项目编码下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/childrensDrugs/itemIdDiscSelect.js"></script>
<!--医保项目名称下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/childrensDrugs/itemNameDiscSelect.js"></script>
</body>
</html>
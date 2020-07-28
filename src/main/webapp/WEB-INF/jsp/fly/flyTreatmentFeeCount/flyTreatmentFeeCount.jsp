<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <title>收费金额次数统计</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">

        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline ">
                    <label class="layui-form-label ">收费类型</label>
                    <div class="layui-input-block">
                        <select name="flyTreatmentFeeCountVo.type">
                            <option value="1" >药品费</option>
                            <option value="2" >检查费</option>
                            <option value="3" selected>治疗费</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline ">
                    <label class="layui-form-label ">结算类型</label>
                    <div class="layui-input-block">
                        <select name="flyTreatmentFeeCountVo.belong">
                            <option value="0" selected>全部</option>
                            <option value="1" >门诊结算</option>
                            <option value="2">住院结算</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline pt">
                    <label class="layui-form-label">项目名称</label>
                    <div class="layui-input-block">
                        <input type="text"
                               name="flyTreatmentFeeCountVo.item" placeholder="查询项目名称"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline pt">
                    <label class="layui-form-label">查询年份</label>
                    <div class="layui-input-block">
                        <%-- <input type="text"
                                name="flyTreatmentFeeCount.year" placeholder="查询年份"
                                autocomplete="off" class="layui-input">--%>
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="testYear" readonly
                                       autocomplete="off" name="flyTreatmentFeeCountVo.year" placeholder="查询年份"></div>
                        </div>
                    </div>
                </div>
                <div class="layui-inline pt">
                    <label class="layui-form-label">查询月份</label>
                    <div class="layui-input-block">
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" autocomplete="off"
                                    id="seachmonth"   name="flyTreatmentFeeCountVo.month" placeholder="查询月份">
                            </div>
                        </div>
                        <%--<input type="text"
                               name="flyTreatmentFeeCount.month" placeholder="查询月份"
                               autocomplete="off" class="layui-input">--%>
                    </div>
                </div>

                <div class="layui-inline ">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                            lay-filter="LAY-flyTreatmentFeeCountStatistics-front-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="flyTreatmentFeeCountStatisticsTable" class="layui-hide"
                   lay-filter="flyTreatmentFeeCountStatisticsTable">
            </table>

        </div>
    </div>
    </div>
        <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/fly/flyTreatmentFeeCount/flyTreatmentFeeCount.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: YangSX
  Date: 2019/12/12
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/UserHistoryin.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <title>项目信息查询</title>
</head>
<!--但label中的文字超过4个字时使用  -->
<style>
    .layui-form-label {
        width: 80px;
    }
</style>

<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline" >
                    <label class="layui-form-label ">项目种类</label>
                    <div class="layui-input-inline">
                        <select id="queryType" name="queryType">
                            <%--                            <option value="" selected>请选择</option>--%>
                            <option value="cent" selected>医保项目</option>
                            <option value="hosp">医院项目</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline" id="testbelong">
                    <label class="layui-form-label ">查询方式</label>
                    <div class="layui-input-inline">
                        <select id="queryItemType" name="queryItemType">
                            <%--                            <option value="" selected>请选择</option>--%>
                                <option value="code" selected>按编码</option>
                                <option value="name">按名称</option>

                        </select>
                    </div>
                </div>
                <!--组合判断条件-->
                <div class="layui-inline">
                    <label class="layui-form-label">项目信息</label>
                    <div class="layui-input-inline">
                        <input type="text" name="queryItemInfo" id="queryItemInfo"
                                autocomplete="off"
                               class="layui-input">
                    </div>
                </div>


                <!--组合按钮框-->
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                            lay-filter="itemCodeAndNameSearch" stylename="search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                   <button class="layui-btn layui-btn-primary"  lay-submit id="resets" stylename="allUpdate" lay-filter="LAY-user-front-reset" >
                       <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                   </button>
                   <button id="exportInfo"  stylename="export"
                           class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
                           lay-submit lay-filter="LAY-user-front-export">
                       <i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
                   </button> 
                </div>
            </div>
        </div>

        <!--下方table-->
        <div class="layui-card-body">
            <table id="itemCodeAndNameSearchTable" class="layui-hide"
                   lay-filter="itemCodeAndNameSearchTable">
            </table>
        </div>
    </div>
</div>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/itemCodeAndNameSearch/itemCodeAndNameSearch.js"></script>
</body>
</html>

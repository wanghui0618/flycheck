<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
    <title>数据权限</title>
    <style>
        .pt {
            width: 300px;
        }    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs3 layui-col-md3">
            <div class="layui-card" style="min-height:493px">
                <div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
                    <div class="layui-form-item" >
                        <div class="layui-inline">
                            <label style="width:100%; text-align:left;" id="dataAuthorityNameText" class="layui-form-label"></label>
                        </div>
                    </div>
                    <div>
                        <ul id="tree-sorts" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-xs9 layui-col-md9">
            <!-- 填充内容 -->
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-card">
                        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                            <div class="layui-form-item">
                                <div class="layui-inline pt">
                                    <label class="layui-form-label" style="width:90px">数据权限名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" style="width: 160px;"
                                               name="dataauthorityVo.dataAuthorityName" placeholder="请输入数据权限名称"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>

                           <%--     <div class="layui-inline pt">
                                    <label class="layui-form-label" style="width:90px">菜单代码</label>
                                    <div class="layui-input-block">
                                        <input type="text" style="width: 160px;"
                                               name="menu.menuCode" placeholder="请输入菜单代码"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
--%>
                                <div class="layui-inline " style="margin-left: 20px">
                                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                                            lay-filter="LAY-user-front-search">
                                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                                    </button>
                                </div>
                            </div>

                            <%--  <div class="layui-inline">
                                  <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增</button>
                              </div>--%>
                        </div>
                    </div>

                    <div class="layui-card-body">

                        <table id="dataAuthorityTable" class="layui-hide"
                               lay-filter="dataAuthorityTable"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/dataauthority/dataauthority.js"></script>
</body>
</html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
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

    <title>疾病分类</title>
</head>
<body>
<div class="layui-fluid">

        <div class="layui-row layui-col-space10">

        <div class="layui-col-xs7 layui-col-md7">
            <!-- 填充内容 -->
            <div class="layui-card" >
                <div class="layui-card-body layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">疾病编码</label>
                            <div class="layui-input-block">
                                <input type="text" style="width:150px;" name="Dictdiag.diagCode" placeholder="请输入"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">疾病名称</label>
                            <div class="layui-input-block">
                                <input type="text" style="width:150px;" name="Dictdiag.diagName"
                                       placeholder="请输入疾病名称" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                                    lay-filter="LAY-user-front-search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                            </button>
                            <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增</button>
                        </div>
                    </div>
                    <div class="layui-card-body" style="padding-top:0px">
                        <table id="diseasesFirstClassTable" class="layui-hide"
                               lay-filter="diseasesFirstClassTable"></table>
                        <script type="text/html" id="table-useradmin-webuser">
                            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="xiugai"><i
                                    class="layui-icon layui-icon-edit"></i>编辑</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="shanchu"><i
                                    class="layui-icon layui-icon-delete"></i>删除</a>
                        </script>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-xs5 layui-col-md5">
            <div class="layui-card" style="min-height:493px">
                <div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
                    <div class="layui-form-item" >
                        <div class="layui-inline">
                            <label style="width:100%; text-align:left;" id="diagNameText" class="layui-form-label"></label>
                        </div>
                    </div>
                    <div>
                        <ul id="tree-sorts" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/dictdiag.js"></script>

</body>
</html>
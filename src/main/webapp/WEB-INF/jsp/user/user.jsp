<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/login.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
<title>用户管理</title>
</head>
<body style="overflow: hidden">
<div class="layui-fluid" style="overflow: hidden">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md3" style="width: 20%">
      <div class="layui-card" id="lefttab" style="overflow: auto">
        <div class="layui-card-body">
            <div class="layui-tab layui-tab-card">
              <ul class="layui-tab-title">
                <li class="layui-this" >组织管理</li>
                <li id="change" >角色管理</li>
              </ul>
              <div class="layui-tab-content" id="leftPan">
                <div class="layui-tab-item layui-show">
                  <ul id="tree-sorts" class="ztree"></ul>
                </div>
                <div class="layui-tab-item">
                  <ul id="tree-sorts1" hidden class="ztree"></ul>
                </div>
              </div>
            </div>
        </div>
      </div>
    </div>



    <div class="layui-col-md9" style="width: 80%">
      <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
          <div class="layui-form-item">

            <div class="layui-inline">
              <label class="layui-form-label" style="width:50px;">姓名</label>
              <div class="layui-input-block" style="margin-left:60px;">
                <input type="text" name="userVo.name" placeholder="请输入" autocomplete="off" class="layui-input">
              </div>
            </div>

            <div class="layui-inline">
              <label class="layui-form-label" style="width:55px;">用户名</label>
              <div class="layui-input-block" style="margin-left:65px;">
                <input type="text" name="userVo.loginName" placeholder="请输入" autocomplete="off" class="layui-input">
              </div>
            </div>

            <div class="layui-inline">
              <label class="layui-form-label" style="width:70px;">手机号码</label>
              <div class="layui-input-block"  style="margin-left:80px;">
                <input type="text" name="userVo.phone" placeholder="请输入" autocomplete="off" class="layui-input">
              </div>
            </div>

            <div class="layui-inline">
              <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
              </button>
              <button class="layui-btn layui-icon-add layuiadmin-btn-useradmin" data-type="add">
                <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn"></i>添加
              </button>
            </div>

          </div>
        </div>

        <div class="layui-card-body">
          <table id="userTable1" class="layui-hide" lay-filter="userTable1"></table>
          <script type="text/html" id="table-useradmin-webuser">
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
<%--            <a class="layui-btn  layui-btn-normal layui-btn-xs" lay-event="autho"><i class="layui-icon layui-icon-auz"></i>授权</a>--%>
          </script>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/user.js"></script>
<script>
  $("#change").click(function () {
    $("#tree-sorts1").show();
  });

</script>
</body>
</html>
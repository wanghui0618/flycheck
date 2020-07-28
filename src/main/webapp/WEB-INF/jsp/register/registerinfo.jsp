<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<style>
.layui-table-page{
    height: 38px;
}
</style>
<title>就诊登记信息</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <!-- <div class="layui-inline">
            <label class="layui-form-label">医院编码</label>
            <div class="layui-input-inline">
              <input type="text" name="register.hosCode" placeholder="请输入医院编码" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div> -->
         <!--  <div class="layui-inline">
            <label class="layui-form-label">医院名称</label>
            <div class="layui-input-block" style="width:140px;">
              <input type="text" name="register.hosName" placeholder="请输入医院名称" autocomplete="off" maxlength="10" class="layui-input">
            </div>
          </div> -->
			<div class="layui-inline">
				<label class="layui-form-label">医院名称</label>
				<div class="layui-input-inline">
					<!-- <input id="getOrgName"  /> <input type="text" id="orgName"
						name="register.hosName" style="display: none;" /> -->
						 <input type="text" name="register.hosName" placeholder="请输入医院名称" autocomplete="off" maxlength="30" class="layui-input">
				</div>
			</div>
					<!--          <div class="layui-inline">
            <label class="layui-form-label">医师名称</label>
            <div class="layui-input-block" style="width:140px;">
              <input type="text" name="register.docName" placeholder="请输入医师名称" autocomplete="off" maxlength="10" class="layui-input">
            </div>
          </div> -->
          <div class="layui-inline">
            <label class="layui-form-label" style="width:45px;">姓名</label>
            <div class="layui-input-inline">
              <input type="text" name="register.name" placeholder="请输入姓名" autocomplete="off" maxlength="10" class="layui-input">
            </div>
          </div>
          	<div class="layui-inline">
				<label class="layui-form-label">身份证号</label>
				<div class="layui-input-inline">
			 <input type="text" name="register.idCard" placeholder="请输入身份证号" autocomplete="off" maxlength="30" class="layui-input">
				</div>
			</div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
          </div>
        </div>
      </div>
      <table id="registerTable" class="layui-hide" lay-filter="registerTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
	     <a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>详细</a>
        </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/register/registerinfo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>
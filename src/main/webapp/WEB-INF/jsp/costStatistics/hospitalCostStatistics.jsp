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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<title>医疗机构费用统计</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body style="overflow: hidden;">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        	<%--<div class="layui-inline" >--%>
				<%--<label class="layui-form-label" >医疗机构</label>--%>
                <%--<div class="layui-input-block" >--%>
                    <%--<select id="zyOrgName"  name="hospitalCostStatistics.orgName" lay-verify="" lay-search=" ">--%>
                        <%--<option value="" disabled selected style='display:none;'>请选择</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
			<%--</div>--%>
                <div class="layui-inline">
                    <label class="layui-form-label">医疗机构</label>
                    <div class="layui-input-inline">
                        <input id="getOrgName"/>
                        <input type="text" id="orgCode" name="hospitalCostStatistics.orgName" style="display: none;" />
                        <!-- <select id="getOrgName" name="medicalVo.orgCode" lay-search="">
                           <option value=""  class="layui-select-tips layui-unselect">请选择</option>
                        </select> -->
                    </div>
                </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width:50px" >年份</label>
                <div class="layui-input-inline">
                    <input type="text" name="hospitalCostStatistics.year" style=""  class="layui-input" id="test2" placeholder="yyyy" readonly="true">
                </div>
            </div>
            <div class="layui-inline">
	            <button class="layui-btn layuiadmin-btn-useradmin"  lay-submit lay-filter="LAY-user-front-search">
	              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
	            </button>
	        </div>
        </div>
      </div>
          
     <div class="layui-card-body">
        <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
        <script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/costStatistics/hospitalCostStatistics.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>

</body>
</html>
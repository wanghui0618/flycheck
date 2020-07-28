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

<title>参保人医疗费用统计</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        
            <div class="layui-inline" >
				<label class="layui-form-label" >疾病名称</label>
				<div class="layui-input-block" >
					<input type="text"  name="diseasesCostStatistics.mainDiagName" placeholder="请输入" autocomplete="off" class="layui-input">
				</div>
			</div>
            <%--<div class="layui-inline" >--%>
            <%--<select name="diseasesCostStatistics.year" lay-verify="" id="select">--%>
                <%--<option value="2019">2019</option>--%>
                <%--<option value="2018">2018</option>--%>
                <%--<option value="2017">2017</option>--%>
                <%--<option value="2016">2016</option>--%>
            <%--</select>--%>
                <%----%>
        <%--</div>--%>
            <div class="layui-inline">
                <label class="layui-form-label" style="width:50px;">年份</label>
                <div class="layui-input-inline" >
                    <input type="text"  name="diseasesCostStatistics.year"  class="layui-input" id="test2" placeholder="yyyy" readonly="true">
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
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/costStatistics/diseasesCostStatistics.js"></script>
</body>
</html>
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

<title>上线规则</title>
</head>
<body>
<div class="layui-fluid">
  <div class="layui-card">
  	<div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item" >
                         <div class="layui-inline">
		            <label class="layui-form-label">违规名称</label>
		            <div class="layui-input-block">
		               <input type="text" name="RuleType.ruleName" placeholder="请输入" autocomplete="off" class="layui-input">
		            </div>
		         </div>
		          <div class="layui-inline">
                          <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
                          <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                           </button>
					</div>
                    </div>
    </div> 
    <div class="layui-card-body" style="padding-top:0px">
        <table id="userTable2" class="layui-hide" lay-filter="userTable2"></table>
      </div>
    </div>
</div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/ruletype/ruleType2.js"></script>
</body>
</html>
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
<style>
.layui-table-page{
    height: 38px;
}
</style>
<title>年度住院（门诊）数据信息</title>
</head>
<body style="overflow:hidden;">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        		<div class="layui-inline">
				             <label class="layui-form-label">医疗机构</label> 
				            <div class="layui-input-block">
				                 <input id="getOrgName"/>
                                 <input type="text" id="orgCode" name="findOrgName" lay-search=" " style="display: none;" />
				            </div>
				          </div>
                    查询年份
                   <div class="layui-inline">
                <div class="layui-input-inline"style="" >
                    <input type="text" name="inFlag1" style=""  class="layui-input" id="test3" placeholder="yyyy" readonly="true">
                </div>
            		</div>
		
	            <button class="layui-btn layuiadmin-btn-useradmin"  lay-submit lay-filter="LAY-user-front-search">
	            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
	            </button>
        </div>
          </div>
     <div class="layui-card-body">
          <table id="userTable" class="layui-hide" lay-filter="userTable">
        	<script type="text/html" id="xuhao">
   								{{d.LAY_TABLE_INDEX+1}}
			</script>
        </table>
      </div>
    </div>
    </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/autonomousAnalysis/allTotalCase.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>城市字典维护</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
                  <div class="layui-inline">
            <label class="layui-form-label">业务类别</label>
           		<div class="layui-input-inline">
    	        	<select id="cityAdminarea" name="code.businessType" lay-filter="cityAdminarea" >
    	           		<option  value=""  disabled selected style='display:none;'>请选择业务类别</option>
    	           		<option>就诊登记信息</option>
    	           		<option>就诊单据信息（住院）</option>
    	           		<option>就诊单据明细信息（住院）</option>
    	           		<option>就诊医嘱信息（住院）</option>
    	           		<option>病案首页信息（住院）</option>
    	           		<option>门慢备案信息（门诊）</option>
    	           		<option>就诊单据信息（门诊）</option>
    	           		<option>就诊单据明细信息（门诊）</option>
    	           		<option>就诊处方信息（门诊）</option>
    	           		<option>就诊诊断信息</option>
    	           		<option>就诊手术操作信息</option>
               		</select>
         		</div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">业务编码</label>
            <div class="layui-input-inline">
              <input type="text" name="code.businessId" placeholder="请输入业务编码" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button id="code-add" class="layui-btn layui-icon-add layuiadmin-btn-useradmin" data-type="add" lay-submit
							lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>新增</button>
          </div>
        </div>
      </div>
 <!--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a> -->
 <!--  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a> -->
<table id="codeTable" class="layui-hide" lay-filter="codeTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
                     {{#if (!existsButton('code-update')) { }}
         					 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        			 {{# } }}
          			 {{#if (!existsButton('code-delete')) { }}
         					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        			 {{# } }}	 
         
         
        </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/code/code.js"></script>
</body>
</html>
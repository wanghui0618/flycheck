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
<title>ICD数据对照</title>
</head>
<body >
<div class="layui-fluid">
    	<div class="layui-card">
      	<div class="layui-form layui-card-header layuiadmin-card-header-auto">
       		<div class="layui-form-item">
          		<div class="layui-inline">
            		<label class="layui-form-label">ICD版本名</label>
                		<div class="layui-input-inline" >
    	        	<select id="typeName" name="icdRelation.typeNameLeft" lay-filter="typeNameLeft">
    	           		<option  value=""  disabled selected style='display:none;'>请选择</option>
               		</select>
         		</div>
          		</div>
					<div class="layui-inline">
						<label class="layui-form-label">ICD名称</label>
								<div class="layui-input-inline" >
							<input type="text"
								name="icdRelation.icdNameLeft" placeholder="ICD名称"
								autocomplete="off" maxlength="15" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">ICD编码</label>
								<div class="layui-input-inline" >
							<input  type="text"
								name="icdRelation.icdCodeLeft" placeholder="ICD编码"
								autocomplete="off" maxlength="15" class="layui-input">
						</div>
					</div>

					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-org-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
				</div>
      </div>
	 <table id="icdTableShow" class="layui-hide" lay-filter="icdTableShow"></table>
	    <script type="text/html" id="table-orgadmin-webuser">
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>解除关联</a>
        </script>
      </div>
</div>
   <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/icdRelation/icdRelationShow.js"></script>
</body>
</html>
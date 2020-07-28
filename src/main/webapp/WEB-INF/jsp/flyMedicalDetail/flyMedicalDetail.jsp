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
<title>检查费收费金额次数统计	</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        
          <div class="layui-inline">
            <label class="layui-form-label">机构名称</label>
            <div class="layui-input-inline">
              <input type="text" name="flyMedicalDetailVo.hospitalName" placeholder="请输入" autocomplete="off" maxlength="32" class="layui-input">
            </div>
          </div>
           <div class="layui-inline">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-inline">
              <input type="text" name="flyMedicalDetailVo.itemNameHosp" placeholder="请输入" autocomplete="off" maxlength="32" class="layui-input">
            </div>
          </div>
          
          <div class="layui-inline">
			<label class="layui-form-label">选择年份</label>
			<div class="layui-input-inline">
				<input id="createTime1" name="year" lay-filter="createTime"
					type="text" class="layui-input" placeholder="">
			</div>
		  </div>
		  
		  <div class="layui-inline">
			<label class="layui-form-label">选择月份</label>
			<div class="layui-input-inline">
				<input id="createTime2" name="month" lay-filter="createTime"
					type="text" class="layui-input" placeholder="">
			</div>
		  </div>
		  
		  
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
          </div>
        </div>
      </div>
 <!--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a> -->
 <!--  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a> -->
<table id="flyMedicalDetailTable" class="layui-hide" lay-filter="flyMedicalDetailTable"></table>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/flyMedicalDetail/flyMedicalDetail.js"></script>
	
	
</body>
</html>
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
<title>经办机构信息</title>
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
                 <!--  <div class="layui-inline">
            <label class="layui-form-label">城市名称</label>
           		<div class="layui-input-block" style="width:140px;">
         		   <select id="cityName" name="handle.cityName" lay-verify="" lay-search=" ">
                       <option value="" disabled selected style='display:none;'>请选择</option>
                   </select>
         		</div>
          </div> -->
          <!-- <div class="layui-inline">
            <label class="layui-form-label">统筹区名称</label>
            <div class="layui-input-block" style="width:140px;">
                 <select id="handdingInsName" name="handle.handdingInsName" lay-verify="" lay-search=" ">
                       <option value="" disabled selected style='display:none;'>请选择</option>
                 </select>
            </div>
          </div> -->

			<div class="layui-inline">
				<label class="layui-form-label">统筹区</label>
				<div class="layui-input-inline">
					<input id="getHanddingName" /> <input type="text"
						id="handdingName" name="handle.handdingInsName"
						style="display: none;" />
				</div>
			</div>
					<div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button id='handle-add' class="layui-btn layui-icon-add layuiadmin-btn-useradmin" data-type="add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>添加</button>
          </div>
        </div>
      </div>
      <table id="handleTable" class="layui-hide" lay-filter="handleTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
         {{#if (!existsButton('handle-edit')) { }}
         <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
         {{# } }}     
         {{#if (!existsButton('handle-del')) { }}           
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
          {{# } }}         
       </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/handle/handleinfo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/handdingDictSelect.js"></script>
</body>
</html>
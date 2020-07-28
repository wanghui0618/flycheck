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
<title>就诊数据删除</title>
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
          
          <!-- <div class="layui-inline">
            <label class="layui-form-label">城市名称</label>
            <div class="layui-input-block" style="width:160px;">
              <input type="text" name="deleteVo.cityName" placeholder="请输入城市名称" autocomplete="off" maxlength="15" class="layui-input">
               <select id="getCityName" name="deleteVo.cityCode" lay-search="">
                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
              </select>
            </div>
          </div> -->
          
          <!-- <div class="layui-inline">
            <label class="layui-form-label">医疗机构</label>
            <div class="layui-input-block" style="width:160px;">
              <input type="text" name="deleteVo.orgName" placeholder="请输入机构名称" autocomplete="off" maxlength="15" class="layui-input">
              <select id="getOrgName" name="deleteVo.handdingInsCode" lay-search="">
                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
              </select>
            </div>
          </div> -->
          
          <div class="layui-inline">
            <label class="layui-form-label">统筹区</label>
            <div class="layui-input-inline">
                 <input id="getHanddingName" name="deleteVo.handdingInsName"/>
                 <input type="text" id="handdingCode" name="deleteVo.handdingInsCode" style="display: none;" />
            </div>
          </div>
          
           <div class="layui-inline">
            <label class="layui-form-label">医疗机构</label>
            <div class="layui-input-inline">
                 <input id="getOrgName" name="deleteVo.orgName"/>
                 <input type="text" id="orgCode" name="deleteVo.orgCode" style="display: none;" />
            </div>
          </div>
          
          <div class="layui-inline">
            <label class="layui-form-label">业务编码</label>
            <div class="layui-input-inline">
              <input type="text" name="deleteVo.workId" placeholder="请输入业务编码" autocomplete="off" maxlength="32" class="layui-input">
            </div>
          </div>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button id="delete_add" class="layui-btn  layui-icon-add layuiadmin-btn-useradmin" data-type="add">
             <i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>新增</button>
          </div>
        </div>
      </div>
 <!--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a> -->
 <!--  <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a> -->
<table id="deleteTable" class="layui-hide" lay-filter="deleteTable"></table>
        <script type="text/html" id="table-orgadmin-webuser">
         {{#if (!existsButton('delete_update')) { }}
         	<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
         {{# } }}
         {{#if (!existsButton('delete_delete')) { }}
         	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a> 
         {{# } }}	
        </script>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/delete/deleteInfo.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/handdingDictSelect.js"></script>
	
</body>
</html>
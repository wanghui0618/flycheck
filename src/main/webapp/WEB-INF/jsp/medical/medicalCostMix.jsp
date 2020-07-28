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
<title>医疗项目费用占比</title>
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
               <select id="getCityName" name="medicalDetailCostVo.cityCode" lay-search="">
                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
              </select>
            </div>
          </div> -->
         <!--  <div class="layui-inline">
            <label class="layui-form-label">医疗机构</label>
            <div class="layui-input-block">
              <select id="getOrgName" name="medicalDetailCostVo.orgCode" lay-search="">
                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
              </select>
            </div>
          </div> -->
          <div class="layui-inline">
            <label class="layui-form-label">医疗机构</label>
            <div class="layui-input-inline">
                 <input id="getOrgName" name="medicalDetailCostVo.orgName"/>
                 <input type="text" id="orgCode" name="medicalDetailCostVo.orgCode" style="display: none;" />
            </div>
          </div>
	
			<div class="layui-inline ">
				<label class="layui-form-label " style="width:50px;">年份</label>
				<div class="layui-input-inline" style="width: 180px;">
					<input id="createTime" name="inFlag" lay-filter="createTime"
						type="text" class="layui-input" placeholder="yyyy">
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
<table id="medicalCostMixTable" class="layui-hide" lay-filter="medicalCostMixTable"></table>
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medical/medicalCostMix.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>
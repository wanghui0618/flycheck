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
<title>数据完整性校验</title>
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
			        <div class="layui-form-item" >
			        		<!-- <div class="layui-inline">
				            <label class="layui-form-label">城市名称</label>
				            <div class="layui-input-block">
				              <input type="text"  id="cityName"  name="medicalVo.cityName" placeholder="请输入" autocomplete="off" class="layui-input">
				              <select id="getCityName" name="medicalVo.cityCode" lay-search="">
				                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
				              </select>
				            </div>
				          </div> -->
			        	  <!-- <div class="layui-inline">
				            <label class="layui-form-label">医疗机构</label>
				            <div class="layui-input-block">
				            	<input type="text" class="layui-input" name="medicalVo.orgName"  placeholder="请输入" autocomplete="off">
				            	<select id="getOrgName" name="medicalVo.orgCode" lay-search="">
				                   <option value=""  class="layui-select-tips layui-unselect">请选择</option>
				                </select>
				            </div>
				          </div> -->
				          <div class="layui-inline">
				            <label class="layui-form-label" style="width:55px;">统筹区</label>
				            <div class="layui-input-inline">
				                 <input id="getHanddingName" name="medicalVo.handdingInsName"/>
                                 <input type="text" id="handdingCode" name="medicalVo.handdingInsCode" style="display: none;" />
				            </div>
				          </div>
			        	  <div class="layui-inline">
				            <label class="layui-form-label">医疗机构</label>
				            <div class="layui-input-inline">
				                 <input id="getOrgName" name="medicalVo.orgName"/>
                                 <input type="text" id="orgCode" name="medicalVo.orgCode" style="display: none;" />
				            </div>
				          </div>
			        	  <div class="layui-inline">
				            <label class="layui-form-label">就诊类型</label>
				            <div class="layui-input-inline">
								<select name="medicalVo.diagType" id="diagType">
									<option value="" disabled selected style='display: none;'>请选择就诊类型</option>
									<option value="1"  style='display: none;'>住院</option>
									<option value="2"  style='display: none;'>门诊</option>
									<option value="3"  style='display: none;'>门特</option>
									<option value="9"  style='display: none;'>其它</option>
								</select>
							</div>
				          </div>
			              <div class="layui-inline">
				            <label class="layui-form-label" style="width:45px;">姓名</label>
				            <div class="layui-input-inline">
				              <input type="text" style="margin-top: 3px;" name="medicalVo.name" placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div>
				            <div class="layui-inline">
                           <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search" id="datastatisticsinfo-chaxun">
                           <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                            </button>
<!-- 				            <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">添加</button>
				          </div>
				          <div class="layui-inline">
							<button class="layui-btn layuiadmin-btn-useradmin " id="importExcel1">
								<i class="layui-icon  layuiadmin-button-btn"></i>导入
							</button> -->
						</div>
			      </div>
			   
          </div>
          <div class="layui-card-body" style="padding-top:0px">
			     <table id="dataIntegrityTable" class="layui-hide" lay-filter="dataIntegrityTable"></table>
			   </div>
        </div>
      </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/rulesManager/dataIntegrity.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/handdingDictSelect.js"></script>
</body>
</html>
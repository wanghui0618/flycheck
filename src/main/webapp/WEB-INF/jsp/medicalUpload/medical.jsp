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
<title>患者就诊记录</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body style="">
<div class="layui-fluid">
  <div class="layui-card">
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
			        <div class="layui-form-item" >
			        		<!-- <div class="layui-inline">
				            <label class="layui-form-label" >统筹区</label>
				            <div class="layui-input-block" style="width:130px">
				             <select id="handdingInsCode" name="medical.handdingInsCode" lay-verify="" lay-search=" ">
                                <option value="" disabled selected style='display:none;'>请选择</option>
                              </select>
				            </div>
				          </div> -->
			        	 <!--  <div class="layui-inline">
				            <label class="layui-form-label">医疗机构</label>
				            <div class="layui-input-block" style="width:160px">
				               <select id="orgName" name="medical.orgName" lay-verify="" lay-search=" ">
                                <option value="" disabled selected style='display:none;'>请选择</option>
                              </select>
				            </div>
				          </div> -->
				           <div class="layui-inline">
				            <label class="layui-form-label" style="width:55px;">统筹区</label>
				            <div class="layui-input-inline">
				                 <input id="getHanddingName"/>
                                 <input type="text" id="handdingCode" name="medical.handdingInsCode" style="display: none;" />
				            </div>
				          </div>
			        	  <div class="layui-inline">
				            <label class="layui-form-label">医疗机构</label>
				            <div class="layui-input-inline">
				                 <input id="getOrgName"/>
                                 <input type="text" id="orgName" name="medical.orgName" style="display: none;" />
				            </div>
				          </div>
			        	  <div class="layui-inline">
				            <label class="layui-form-label">就诊类型</label>
				          <div class="layui-input-inline">
        				   <select   id="diagType" name="medical.diagType" lay-filter="diagType">
        	     			<option value="">请选择</option>
     			 			<option value="1">住院</option>
     			 			<option value="2">门诊</option>
     			 			<option value="3">门特</option>
     			 			<option value="9">其他</option>
      	  	               </select>
                          </div>
				          </div>
			              <div class="layui-inline">
				            <label class="layui-form-label" style="width:45px;">姓名</label>
				            <div class="layui-input-inline">
				              <input type="text" name="medical.name" placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div>
				            <div class="layui-inline">
                           <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search" id="medicalUpload-medical-select">
                           <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                            </button>
				            <button class="layui-btn  layui-icon-add layuiadmin-btn-useradmin" data-type="add" id="medicalUpload-medical-add">
				           <i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>新增</button>
				          </div>
				          <div class="layui-inline">
							<button class="layui-btn layui-icon-upload-main" id="medicalUplpad-medical-importExcel1">
								<i class="layui-icon layui-icon-upload-drag  layuiadmin-button-btn"></i>导入
							</button>
						</div>
			      </div>
			   
          </div>
          <div class="layui-card-body" style="padding-top:0px">
			     <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
		        <script type="text/html" id="table-useradmin-webuser">
					{{#if (!existsButton('medicalUplpad-medical-viewInfo')) { }}
						<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="viewInfo">病例明细</a>
        			{{# } }}
					{{#if (!existsButton('medicalUplpad-medical-edit')) { }}
						<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        			{{# } }}
					{{#if (!existsButton('medicalUplpad-medical-del')) { }}
						<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        			{{# } }}
		  			
					
          			
        		</script>
			   </div>
        </div>
      </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/medicalUpload/medical.js"></script>
  <script type="text/javascript">
	 
	 layui.use(['upload','table'], function(){
		  var upload = layui.upload;
		  var table = layui.table;
		  //执行实例
		  upload.render({
		    elem: '#medicalUplpad-medical-importExcel1' //绑定元素
		    ,accept:'file'
		    ,multiple:false
		    ,exts:'xls|xlsx|xlsm|xlt|xltx|xltm'
		    ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/importExcel' //上传接口
		    ,done: function(res){
		      //上传完毕回调
		      layer.msg(res.msg);
		      table.reload('userTable');
		    }
		    ,error: function(){
		      //请求异常回调
		    	layer.msg("导入失败");
		    }
		  });
		});
	
	</script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/handdingDictSelect.js"></script>
	
</body>
</html>
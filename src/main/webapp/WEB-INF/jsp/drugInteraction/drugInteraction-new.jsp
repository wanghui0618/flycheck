<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>药品相互作用</title>
</head>
<body style="overflow:hidden;">
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					
					<div class="layui-inline pt">
						<label class="layui-form-label" style="width:100px">药品通用名称</label>
						<div class="layui-input-block" style="margin-left:110px">
							<input type="text"
								name="drugInteraction.drugName" placeholder="请输入药品通用名称" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline">
						<button id="drugInteraction-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<div class="layui-inline">
						<button id='add' data-type="add"
							class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>添加</button></button>
					</div>
				</div>
			</div>

				
    			<div class="layui-card" style="margin-left:0;margin: 0px 0px 0px 0px;" >
					<div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
					<table id="drugTable" class="layui-hide" lay-filter="drugTable"></table>
					</div>
				</div>
				
				
				<!-- <div class="layui-col-md7" >
    			<div class="layui-card" style="margin-left:1px;margin: 3px 0px 0px 0px;" >
					<div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
					<table id="interactionDrugNameTable" class="layui-hide" lay-filter="interactionDrugNameTable" ></table>
					</div>
				</div>
				</div> -->
			
				<script type="text/html" id="table-useradmin-webuser">
					 {{#if (!existsButton('drugInteraction-interaction-update')) { }}
         					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        			 {{# } }}
          			 {{#if (!existsButton('drugInteraction-interaction-delete')) { }}
         					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a> 
        			 {{# } }}
          			
        		</script>
				<script type="text/html" id="table-useradmin-webuser1">
					{{#if (!existsButton('drugInteraction-drug-show')) { }}
         					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="show"><i class="layui-icon layui-icon-edit"></i>预览</a>
        			 {{# } }}
          		
					 {{#if (!existsButton('drugInteraction-drug-update')) { }}
         					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        			 {{# } }}
          			 {{#if (!existsButton('drugInteraction-drug-delete')) { }}
         					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a> 
        			 {{# } }}
          			
        		</script>
				<script type="text/html" id="table-useradmin-webuser2">
					 {{#if (!existsButton('drugInteraction-drug-add')) { }}
         					<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-edit"></i>添加药品</a>
        			 {{# } }}
        		</script>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/drugInteraction/drugInteraction-new.js"></script>
</body>
</html>
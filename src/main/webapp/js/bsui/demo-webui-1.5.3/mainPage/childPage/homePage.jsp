<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bsui/dhccbs3/dhccbs3.7.css"/>
<!--icons-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bsui/font-awesome/css/font-awesome.min.css"/>
<!--皮肤（蓝色）-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/base/css/home/skin-blue.css"/>
<!--顶部菜单模板样式文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/base/css/home/menu.css"/>
<!--菜单小图标-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/base/css/home/menu-icon.css"/>
<title>主页模板-顶部菜单</title>
<style type="text/css">
.body-bg{
  background-color: #f3f4f5;
  overflow-x: hidden;
}
</style>
</head>
<body >
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="bsui-datagrid" data-options="
			title:'通知公告 ',
			height: 600,
			fitColumns: true,
			rownumbers:true,
			singleSelect:true,
			toolbar: '#tb',
			url: '<%=request.getContextPath() %>/js/bsui/demo-webui-1.5.3/mainPage/childPage/datagrid_data1.json',
			pagination:true,
			pageSize:5,
			layout:['first','prev','links','next','last','refresh'],
			method: 'get'">
				<thead>
					<tr>
						<th data-options="field:'tzbt',width:'50%',halign:'center'">通知标题</th>
						<th data-options="field:'type',width:'15%',align:'center',halign:'center'">通知类型</th>
						<th data-options="field:'date',width:'25%',align:'center',halign:'center'">发布日期</th>
						<th data-options="field:'opt',width:'10%',align:'center',halign:'center',formatter:function(value,row){
					return '<a>详细</a>';
				}">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<%-- <div class="col-md-6">
			<table class="bsui-datagrid" data-options="
			title:'行业动态 ',
			height: 'auto',
			fitColumns: true,
			rownumbers:true,
			singleSelect:true,
			toolbar: '#tb',
			url: '<%=request.getContextPath() %>/js/bsui/demo-webui-1.5.3/mainPage/childPage/datagrid_data2.json',
			pagination:true,
			pageSize:5,
			layout:['first','prev','links','next','last','refresh'],
			method: 'get'">
				<thead>
					<tr>
						<th data-options="field:'title',width:'60%',halign:'center'">标题</th>
						<th data-options="field:'date',width:'30%',align:'center',halign:'center'">发布日期</th>
						<th data-options="field:'opt',width:'10%',align:'center',halign:'center',formatter:function(value,row){
					return '<a>详细</a>';
				}">操作</th>
					</tr>
				</thead>
			</table>
		</div> --%>
	</div>
		<%-- <div class="row" style="margin-top: 15px">
		<div class="col-md-6">
			<table class="bsui-datagrid" data-options="
			title:'交流互动 ',
			height: 'auto',
			fitColumns: true,
			rownumbers:true,
			singleSelect:true,
			toolbar: '#tb',
			url: '<%=request.getContextPath() %>/js/bsui/demo-webui-1.5.3/mainPage/childPage/datagrid_data3.json',
			pagination:true,
			pageSize:5,
			layout:['first','prev','links','next','last','refresh'],
			method: 'get'">
				<thead>
					<tr>
						<th data-options="field:'type',width:'15%',align:'center',halign:'center'">互动类型</th>
						<th data-options="field:'yyly',width:'15%',align:'center',halign:'center'">应用领域</th>
						<th data-options="field:'title',width:'40%',align:'left',halign:'center'">标题</th>
						<th data-options="field:'date',width:'20%',align:'center',halign:'center'">时间</th>
						<th data-options="field:'opt',width:'10%',align:'center',halign:'center',formatter:function(value,row){
					return '<a>详细</a>';
				}">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="col-md-6">
			<table class="bsui-datagrid" data-options="
			title:'登录日志',
			height: 'auto',
			fitColumns: true,
			rownumbers:true,
			singleSelect:true,
			toolbar: '#tb',
			url: '<%=request.getContextPath() %>/js/bsui/demo-webui-1.5.3/mainPage/childPage/datagrid_data4.json',
			pagination:true,
			pageSize:5,
			layout:['first','prev','links','next','last','refresh'],
			method: 'get'">
				<thead>
					<tr>
						<th data-options="field:'zh',width:'25%',halign:'center'">账号</th>
						<th data-options="field:'name',width:'25%',halign:'center'">姓名</th>
						<th data-options="field:'ip',width:'20%',align:'center',halign:'center'">登录IP</th>
						<th data-options="field:'date',width:'30%',align:'center',halign:'center'">登录时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div> --%>
</div>
</body>
<!--[if lt IE 9]>
<script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/lib/pre/html5shiv.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/lib/pre/respond.min.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/commonUI.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/js/commonValidate.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/base/js/home/menu.js" type="text/javascript" charset="utf-8"></script>
</html>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ol class="breadcrumb">
	<li>
		<a href="#" class="fa fa-home"></a>
	</li>
	<li class="active">demo</li>
</ol>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="bsui-datagrid" data-options="
			title:'通知公告 ',
			height: 'auto',
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
	</div>
</div>
<div id="">
</div>
<script type="text/javascript">
	$.parser.parse();
</script>
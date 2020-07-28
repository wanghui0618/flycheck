<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ol class="breadcrumb">
	<li>
		<a href="#" class="fa fa-home"></a>
	</li>
	<li>
		<a href="#">数据列表</a>
	</li>
	<li class="active">datagrid</li>
</ol>

<table id="dg" class="bsui-datagrid" title="数据表格" data-options="
	height: 'auto',
	fitColumns: true,
	rownumbers:true,
	singleSelect:true,
	toolbar: '#tb',
	url: 'js/bsui/demo-webui-1.5.3/mainPage/childPage/datagrid_data1.json',
	pagination:true,
	pageSize:10,
	layout:['first','prev','links','next','last','refresh'],
	method: 'get',
	onClickCell: onClickCell,
	onHeaderContextMenu: onHeaderContextMenu">
	<thead>
		<tr>
			<th data-options="field:'itemid',width:'15%',editor:'textbox'">Item ID</th>
			<th data-options="field:'productid',width:'20%',
				formatter:function(value,row){
					return row.productname;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'productid',
						textField:'productname',
						method:'get',
						url:'childPage/products.json',
						required:true
					}
				}">Product</th>
			<th data-options="field:'listprice',width:'8%',align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
			<th data-options="field:'unitcost',width:'8%',align:'right',editor:'numberbox'">Unit Cost</th>
			<th data-options="field:'attr1',width:'25%',editor:'textbox'">Attribute</th>
			<th data-options="field:'status',width:'10%',align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
			<!--<th data-options="field:'operate',width:'13%',align:'center',formatter:operateFormat">操作</th>-->
		</tr>
	</thead>
</table>
<div id="tb" style="padding:12px 20px;">
	<input class="bsui-datebox" data-options="label:'从：',labelAlign:'right',width:150"/> 
	<input class="bsui-datebox" data-options="label:'到：',labelWidth:45,labelAlign:'right',width:150">
	<select id="lang" class="bsui-combobox" label="语言：" labelWidth="60" labelAlign="right" panelHeight="auto" style="width:150px;">
		<option value="java">Java</option>
		<option value="c">C</option>
		<option value="basic">Basic</option>
		<option value="perl">Perl</option>
		<option value="python">Python</option>
	</select> 
	<a class="bsui-linkbutton" data-options="btnCls:'primary',size:'xs'" style="margin-left:1.2em;margin-right: .1em;" onclick="showAddWin()">新增</a>
	<a class="bsui-linkbutton" data-options="btnCls:'success',size:'xs'" style="margin: 0 .1em;" onclick="showEditWin()">修改</a>
	<a class="bsui-linkbutton" data-options="btnCls:'danger',size:'xs'" style="margin: 0 .1em;" onclick="delProduct()">删除</a>
	<a class="bsui-linkbutton" data-options="btnCls:'gray',size:'xs'" onclick="cancel()">取消</a>
</div>

<!--右键菜单-->
<div id="mm" class="bsui-menu" data-options="hideOnUnhover:false" style="width:120px;">
	<div data-options="iconCls:'icon-add'">新增</div>
	<div class="menu-sep"></div>
	<div>折叠</div>
	<div>展开</div>
</div>

<!-- 修改密码 -->
<div id="addWin" class="bsui-window" title="新增" style="display:none;" data-options="
	modal:true,
	width: 330,
	footer:'#addWinFooter',
	minimizable:false,
	maximizable:false,
	closed:true">
	<form id="addForm" method="post">
		<table class="form-table">
			<tr style="height:3em;">
	    		<th style="text-align:right;"><sup style="color: red;">*</sup>Product：</th>
	    		<td><input type="test" class="bsui-textbox" name="product" data-options="required:true,prompt:'请输入文本内容'"/></td>
	    	</tr>
	    	<tr style="height:3em;">
				<th style="text-align:right;"><sup style="color: red;">*</sup>List Price：</th>
	    		<td><input type="text" class="bsui-numberbox" name="price" data-options="required:true,prompt:'请输入数值内容'"/></td>
	    	</tr>
	    	<tr style="height:3em;">
				<th style="text-align:right;"><sup style="color: red;">*</sup>Unit Cost：</th>
	    		<td><input type="text" class="bsui-numberbox" name="cost" data-options="required:true,prompt:'请输入数值内容'"/></td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="addWinFooter" align="center" style="padding:5px;">
	<button type="button" class="btn btn-primary" onclick="submitSave()">提交</button>
    <button type="button" class="btn btn-default" onclick="reset()">重置</button>
</div>

<script type="text/javascript">
	// 扫描子页面调用的easyui组件，引入组件的css及js代码
	$.parser.parse();
	// 执行此方法，引入datagrid的editor调用的nuberbox的css及js文件
	$CommonUI.getNumberBox();
	
	function onHeaderContextMenu(e, field) {
		e.preventDefault();
		$CommonUI.getMenu('#mm').menu('show', {
			left:e.pageX,
			top:e.pageY
		});
	}
	
	function showAddWin() {
		$CommonUI.getWindow("#addWin").window('open');
	}
	
	function submitSave() {
		var valid = $CommonUI.getForm("#addForm").form('validate');
		if (!valid) {
			return;
		}
		
		$CommonUI.getWindow("#addWin").window('close');
		$CommonUI.notify('新增成功！');
	}
	
	function reset() {
		$CommonUI.getForm("#addForm").form('reset');
		$CommonUI.notify('数据重置成功！', {type: 'success'});
	}
	
	function showEditWin() {
		$CommonUI.notify('请谨慎修改！', {type: 'warning'});
	}
	
	function delProduct() {
		$CommonUI.notify('删除操作危险！', {type: 'danger'});
	}
	
	function cancel() {
		$CommonUI.notify('显示时间及位置！', {type:'success', delay:2000, placement:{from:'top', align:'right'}});
	}
	
	function operateFormat(value, row, index) {
		return "<button type=\"button\" class=\"btn btn-success-outline btn-mi\">修改</button>" 
			+ " <button type=\"button\" class=\"btn btn-danger-outline btn-mi\">删除</button>";
	}
	
	//$CommonUI.getComboBox("#lang").combobox('clear');
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($CommonUI.getDataGrid('#dg').datagrid('validateRow', editIndex)){
			var ed = $CommonUI.getDataGrid('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
			var productname = $CommonUI.getComboBox(ed.target).combobox('getText');
			$CommonUI.getDataGrid('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
			$CommonUI.getDataGrid('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if (editIndex != index){
			if (endEditing()){
				$CommonUI.getDataGrid('#dg').datagrid('selectRow', index)
					.datagrid('beginEdit', index);
				var ed = $CommonUI.getDataGrid('#dg').datagrid('getEditor', {index:index,field:field});
				if (ed){
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
				}
				editIndex = index;
			} else {
				$CommonUI.getDataGrid('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
</script>
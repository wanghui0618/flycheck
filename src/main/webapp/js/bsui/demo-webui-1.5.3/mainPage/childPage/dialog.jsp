<ol class="breadcrumb">
	<li>
		<a href="#" class="fa fa-home"></a>
	</li>
	<li>
		<a href="#">弹窗组件</a>
	</li>
	<li>
		<a href="#">对话框</a>
	</li>
	<li class="active">dialog</li>
</ol>

<div style="margin:20px 0;">
	<a href="javascript:void(0)" class="bsui-linkbutton btn-primary" onclick="$CommonUI.getDialog('#dlg').dialog('open')">Open</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-danger" onclick="$CommonUI.getDialog('#dlg').dialog('close')">Close</a>
</div>

<div id="dlg" class="bsui-dialog" title="Complex Toolbar on Dialog" style="width:400px;height:200px;padding:10px" data-options="
	iconCls: 'icon-save',
	toolbar: '#dlg-toolbar',
	buttons: [{
		text:'Ok',
		iconCls:'icon-ok',
		handler:function(){
			$CommonUI.alert('ok');
		}
	},{
		text:'Cancel',
		handler:function(){
			$CommonUI.alert('cancel');;
		}
	}]">
	The dialog content.
</div>
<div id="dlg-toolbar" style="padding:2px 0">
	<table cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td style="padding-left:2px">
				<a href="javascript:void(0)" class="bsui-linkbutton" data-options="iconCls:'icon-edit',plain:true">Edit</a>
				<a href="javascript:void(0)" class="bsui-linkbutton" data-options="iconCls:'icon-help',plain:true">Help</a>
			</td>
			<td style="text-align:right;padding-right:2px">
				<input class="bsui-searchbox" data-options="prompt:'Please input somthing'" style="width:180px"></input>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
	$.parser.parse();
</script>
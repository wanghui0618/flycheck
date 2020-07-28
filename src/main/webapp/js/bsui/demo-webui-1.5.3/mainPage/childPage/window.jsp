<ol class="breadcrumb">
	<li>
		<a href="#" class="fa fa-home"></a>
	</li>
	<li>
		<a href="#">弹窗组件</a>
	</li>
	<li>
		<a href="#">弹窗</a>
	</li>
	<li class="active">window</li>
</ol>

<h4>普通弹窗</h4>
<div style="margin:20px 0;">
	<button type="button" class="btn btn-primary" onclick="$CommonUI.getWindow('#w').window('open')">打开</button>
	<button type="button" class="btn btn-primary" onclick="$CommonUI.getWindow('#w').window('close')">关闭</button>
	<button type="button" class="btn btn-primary" onclick="$CommonUI.getWindow('#w').window('setTitle', 'new')">设置标题</button>
</div>
<div id="w" class="bsui-window" title="Window with a Footer" data-options="iconCls:'icon-save',tools:'#tt',footer:'#footer'" style="width:500px;height:200px;padding:10px;">
	这是一个带footer的普通弹窗。
</div>
<div id="tt">
	<a href="javascript:void(0)" class="icon-add" onclick="$CommonUI.alert('新增')"></a>
	<a href="javascript:void(0)" class="icon-edit" onclick="$CommonUI.alert('修改', 'info')"></a>
	<a href="javascript:void(0)" class="icon-cut" onclick="$CommonUI.alert('删除', 'warning')"></a>
	<a href="javascript:void(0)" class="icon-help" onclick="$CommonUI.alert('帮助', 'question')"></a>
</div>
<div id="footer" style="padding:5px;">Footer Content.</div>

<h4>模态窗</h4>
<div style="margin:20px 0;">
	<button type="button" class="btn btn-primary" onclick="$CommonUI.getWindow('#modal').window('open')">打开</button>
</div>
<div id="modal" class="bsui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	这是一个模态弹窗。
</div>

<script type="text/javascript">
	$.parser.parse();
</script>
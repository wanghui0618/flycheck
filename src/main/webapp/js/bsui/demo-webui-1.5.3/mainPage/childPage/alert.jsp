<ol class="breadcrumb">
	<li>
		<a href="#" class="fa fa-home"></a>
	</li>
	<li>
		<a href="#">弹窗组件</a>
	</li>
	<li>
		<a href="#">提示框</a>
	</li>
	<li class="active">alert</li>
</ol>

<h4>各种样式的消息提示框</h4>
<div style="margin:20px 0;">
	<a href="#" class="bsui-linkbutton" onclick="alert('')">Default</a>
	<a href="#" class="bsui-linkbutton btn-inverse" onclick="alert('dark')">Dark</a>
	<a href="#" class="bsui-linkbutton btn-danger" onclick="alert('error')">Error</a>
	<a href="#" class="bsui-linkbutton btn-info" onclick="alert('info')">Info</a>
	<a href="#" class="bsui-linkbutton btn-success" onclick="alert('question')">Question</a>
	<a href="#" class="bsui-linkbutton btn-warning" onclick="alert('warning')">Warning</a>
</div>

<h4>右下角消息提示</h4>
<div style="margin:20px 0;">
	<a href="#" class="bsui-linkbutton btn-primary" onclick="show()">Show</a>
	<a href="#" class="bsui-linkbutton btn-info" onclick="slide()">Slide</a>
	<a href="#" class="bsui-linkbutton btn-success" onclick="fade()">Fade</a>
	<a href="#" class="bsui-linkbutton btn-warning" onclick="progress()">Progress</a>
</div>

<h4>不同位置的消息提示框</h4>
<div style="margin:20px 0;">
	<a href="javascript:void(0)" class="bsui-linkbutton btn-primary" onclick="showMessager(4000,'show','top-left')">TopLeft</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-primary" onclick="showMessager(1000,'slide','top-center')">TopCenter</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-primary" onclick="showMessager(1000,'fade','top-right')">TopRight</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-info" onclick="showMessager(5000,'fade','center-left')">CenterLeft</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-info" onclick="showMessager(1000,'show','center')">Center</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-info" onclick="showMessager(1000,'slide','center-right')">CenterRight</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-success" onclick="showMessager(1000,'slide','bottom-left')">BottomLeft</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-success" onclick="showMessager(1000,'show','bottom-center')">BottomCenter</a>
	<a href="javascript:void(0)" class="bsui-linkbutton btn-success" onclick="showMessager(1000,'fade','bottom-right')">BottomRight</a>
</div>

<h4>互动式消息框</h4>
<div style="margin:20px 0;">
	<a href="#" class="bsui-linkbutton btn-info" onclick="confirm();">Confirm</a>
	<a href="#" class="bsui-linkbutton btn-info" onclick="prompt()">Prompt</a>
</div>

<script type="text/javascript">
	$.parser.parse();
	
	function alert(type){
		$CommonUI.alert('Here is a message!',type);
	}
	
	function show(){
		$CommonUI.autoCloseRightBottomMessage('Message will be closed after 4 seconds.<br>Message will be closed after 4 seconds.<br>Message will be closed after 4 seconds.','My Title', 400000, 'show');
	}
	function slide(){
		$CommonUI.autoCloseRightBottomMessage('Message will be closed after 5 seconds.','My Title', 5000, 'slide');
	}
	function fade(){
		$CommonUI.autoCloseRightBottomMessage('Message never be closed.','My Title', 0, 'fade');
	}
	function progress(){
		$CommonUI.progress({
			title:'Please waiting',
			msg:'Loading data...'
		});
		setTimeout(function(){
			$CommonUI.progress('close');
		},5000);
	}
	
	function showMessager(timeout, animate ,position) {
		$CommonUI.autoCloseMessage('The message content','My Title',timeout,animate,position);
	}
	
	function confirm(){
		$CommonUI.confirm('确定删除吗?', 'question', '确定', function() {
			$CommonUI.alert('执行删除');
		},'取消',function(){
			$CommonUI.alert('取消并退出');
		},'标题',true);
	}
	function prompt(){
		$CommonUI.prompt('请输入内容', 'info', '确定', function() {
			$CommonUI.alert('执行');
		},'取消',function(){
			$CommonUI.alert('取消并退出');
		},'标题',true);
	}
</script>
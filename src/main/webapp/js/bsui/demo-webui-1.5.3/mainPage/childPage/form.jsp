<ol class="breadcrumb">
	<li>
		<a href="#" class="fa fa-home"></a>
	</li>
	<li>
		<a href="#">表单组件</a>
	</li>
	<li class="active">表单回填及遍历</li>
</ol>

<div class="bsui-panel" style="width: 100%;">
	<form id="user" class="form-horizontal" method="post">
		<div class="col-md-6 col-lg-6" style="margin-bottom: .8em">
			<input id="username" class="bsui-textbox" data-options="required:true,validType:'length[1,10]',width:'90%',label:'姓名：',labelWidth:70,labelAlign:'right'" name="dto.user.username" value="张三">
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom: .8em">
			<input id="password" class="bsui-passwordbox" data-options="width:'90%',label:'密码：',labelWidth:70,labelAlign:'right',prompt:'Password',iconWidth:'28'" name="dto.user.password" value="111111">
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom: .8em">
			<input id="sex" class="bsui-combobox" label="性别：" labelWidth="70" labelAlign="right" name="dto.user.sex" data-options="width:'90%',valueField:'value',textField:'label',
								data:[{label:'男',value:'male'},{label:'女',value:'female'}]" value="male"/>
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input id="birthday" class="bsui-datebox" label="生日：" labelWidth="70" labelAlign="right" name="dto.user.birthday" style="width:90%" value="1998-03-21">
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input id="age" class="bsui-numberbox" data-options="label:'年龄：',labelWidth:70,labelAlign:'right',width:'90%'" name="dto.user.age" value="23"/>
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input id="email" class="bsui-textbox" label="邮箱：" labelWidth="70" labelAlign="right" data-options="prompt:'输入邮箱',validType:'email',width:'90%'" name="dto.user.email" value="zhangsan@qq.com">
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input id="photo" class="bsui-filebox" data-options="label:'照片：',labelWidth:70,labelAlign:'right',prompt:'选择文件',width:'90%'" name="dto.user.photo">
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input class="bsui-combobox" label="多选：" labelWidth="70" labelAlign="right" name="dto.user.sport" data-options="width:'90%',valueField:'value',textField:'label',multiple:true,
				data:[{label:'篮球',value:'basketball'},{label:'足球',value:'football'},{label:'乒乓球',value:'pingpong'},{label:'羽毛球',value:'badminton'}]" />
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input class="bsui-textbox" label="手机号1：" labelAlign="right" name="dto.user.mobiles[0]" style="width: 90%;" />
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:.8em">
			<input class="bsui-textbox" label="手机号2：" labelAlign="right" name="dto.user.mobiles[1]" style="width: 90%;" />
		</div>
		<div class="col-xs-8 col-sm-7 col-md-11 col-lg-11" style="margin-bottom: .8em">
			<input class="bsui-textbox" data-options="height:50,width:'95%',label:'描述：',labelWidth:70,labelAlign:'right',multiline:true" name="dto.user.desc"/>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin: .1em 0 1em;border: 1px dotted #aaa;"></div>
		<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">描述：</label>
		<div class="col-xs-8 col-sm-7 col-md-10 col-lg-10" style="margin-bottom: .8em">
			<textarea class="form-control" rows="2" cols="5"></textarea>
		</div>
		<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">单选按钮：</label>
		<div class="col-xs-8 col-sm-7 col-md-4 col-lg-4" style="margin-bottom: 3em;padding-top: .5em;">
			<input type="radio" name="dto.user.skill" value="java" /> Java
			<input type="radio" name="dto.user.skill" value="c" /> C/C++
			<input type="radio" name="dto.user.skill" value="ruby" /> Ruby
		</div>
		<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">多选按钮：</label>
		<div class="col-xs-8 col-sm-7 col-md-4 col-lg-4" style="margin-bottom: 3em;padding-top: .5em;">
			<input type="checkbox" name="dto.user.language" value="english" /> 英语
			<input type="checkbox" name="dto.user.language" value="franch" /> 法语
			<input type="checkbox" name="dto.user.language" value="chinese" /> 汉语
		</div>
		<div class="col-md-4 col-lg-4 col-md-offset-1 col-lg-offset-1" style="margin-bottom:4.8em">
			<!--<input type="checkbox" name="dto.user.remember" value="miss"/> 记住我&nbsp;&nbsp;&nbsp;&nbsp;-->
			<input class="bsui-switchbutton" data-options="onText:'Yes',offText:'No'" name="dto.user.switch"><br />
		</div>
		<div class="col-md-6 col-lg-6" style="margin-bottom:4.8em">
			<input id="slider" class="bsui-slider" name="dto.user.range" data-options="
				showTip: true,
				range: true,
				rule: [0,'|',25,'|',50,'|',75,'|',100]">
		</div>
	</form>
</div>

<script type="text/javascript">
	$.parser.parse();
	setTimeout(function() {
		/*var data = {
			"dto.user": {
				"username": "张三",
				"password": "111111",
				"sex": "male",
				"birthday": "1998-03-21",
				"age": 19,
				"email": "zhangsan@qq.com",
				"photo": "a.jpg",
				"sport": "basketball,pingpong",
				"mobiles": ['13876543212', '13098730348'],
				"desc": "我的名字叫张三。",
				"skill": "c",
				"language": "english,chinese",
				"remember": "miss",
				"range": "10,50"
			}
		};
		$CommonUI.fillBlock("#user", data);*/
		var json = $CommonUI.loopBlock("#user");
		console.log(json);
		/*var json = $CommonUI.loopBlock("#user", ['dto.user.language']);
		console.log(json);*/
		/*var json = $CommonUI.loopBlock("#user", null, true);
		console.log(json);*/
		var json = $CommonUI.loopBlock("#user", null, false, true);
		console.log(json);

		/*$CommonUI.setUIValue('#username', 'zhangsan');
		$CommonUI.setUIValue('#sex', 'male');
		$CommonUI.setUIValue('#birthday', '1998-03-21');
		$CommonUI.setUIValue('#age', 18);
		$CommonUI.setUIValue("#email", "test@qq.com");
		$CommonUI.setUIValue("#photo", "a.jpg");
		$CommonUI.setUIValue("#check", "miss");
		$CommonUI.setUIValue("#slider", ['10','50']);*/

		/*alert($CommonUI.getUIValue("#check"));*/
	}, 100);
	/*setTimeout(function() {
		var json = $CommonUI.loopBlock("#user");
		console.log(json);
	}, 5000);*/
</script>
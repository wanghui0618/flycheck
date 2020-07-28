String.prototype.trim=function(){
	 return this.replace(/(^\s*)|(\s*$)/g, "");
 };

$(function(){
	
	//layui.layer.alert('hello');
	//修改密码
	$("#qrxg").on('click',function(){
		
		var oldpwd=$(".layui-input4").val();
		var newpwd=$(".layui-input5").val();
		var confirm=$(".layui-input6").val();
		
		if(oldpwd==null||""==$.trim(oldpwd)||"null"==$.trim(oldpwd)){
			Layer.alert("请输入原始密码");
			return false;
		}
		
		if(newpwd==null||""==$.trim(newpwd)||"null"==$.trim(newpwd)){
			Layer.alert("新密码不能为空");
			return false;
		}
		
		//验证密码
		var regPassword =/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/; 
		if (newpwd != "" && !regPassword.test(newpwd)){
			Layer.alert("密码必须是6到16位的数字和字母!");
	    	return false; 
	    }
		
		//验证原始密码和新密码是否一致
		if(oldpwd == newpwd){
			Layer.alert("新密码不能和原始密码相同");
			return false;
		}
		
		//验证两次输入的密码是否一致
		if(newpwd != confirm) {
			Layer.alert("两次密码不同，请重新输入");
		    return false;
		}
		newpwd = hex_md5(newpwd);
		oldpwd = hex_md5(oldpwd);
		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/changePassword",
			{
				'inFlag1':oldpwd,
				'inFlag2':newpwd,
			},function(dog){
				var inFlag= dog.inFlag;
				if(inFlag==1){
					Layer.alert("修改密码成功");
				}else if(inFlag==3){
					Layer.alert("输入原始密码错误，请重新输入");
					return false;
				}else if(inFlag==9){
					Layer.alert("修改失败，请重新尝试");
					return false;
				}
		
	},"json");
		
	});
	
	
	
});
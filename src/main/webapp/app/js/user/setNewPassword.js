//函数的作用是去除字符串首尾的空白字符(包括空格，回车符，换行符，制表符)。
String.prototype.trim=function(){
	 return this.replace(/(^\s*)|(\s*$)/g, "");
	};
	
$(function(){
	//密码找回
	$("#setNewPassword").on('click', function() {
		//用户输入新密码和确认新密码
		var newPassword = $(".my-input-mima").val();
		console.log(newPassword)
		var confirmNewPassword = $(".my-input-remima").val();
		
		if(newPassword==null||""==$.trim(newPassword)||"null"==$.trim(newPassword)){
			Layer.alert("请输入新密码！");
			return false;
		}
		
		if(confirmNewPassword==null||""==$.trim(confirmNewPassword)||"null"==$.trim(confirmNewPassword)){
			Layer.alert("请再次确认新密码！");
			return false;
		}
		
		//验证密码
		var regPassword =/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/; 
		if (newPassword != "" && !regPassword.test(newPassword)){
			Layer.alert("密码必须是6到16位的数字和字母!");
	    	return false;
	    }
		
		//验证两次输入的密码是否一致
		if(newPassword != confirmNewPassword) {
			Layer.alert("两次密码不同，请重新输入");
		    return false;
		}
		
		//给新密码加密
		newPassword = hex_md5(newPassword);
		var loc = location.href;
		var n1 = loc.length;//地址的总长度
		var n2 = loc.indexOf("=");//取得=号的位置
		var id = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
		
		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/setNewPassword",
				{
				'inFlag1':newPassword,
				'inFlag2':id
				},function(dog){
					var inFlag= dog.inFlag;
					if(inFlag=="1"){
						layer.alert('密码修改成功，即将跳转至登录页面！', function(index){
							  window.location.href=$WEB_ROOT_PATH+"/";
							});
					}else if(inFlag=="2"){
						Layer.alert("新密码不能和老密码一样，请重新输入");
						return false;
					}else if(inFlag=="3"){
						Layer.alert("设置新密码失败，请重新尝试");
						return false;
					}else if(inFlag=="0"){
						Layer.alert("网络连接异常！");
						return false;
					}
		},"json");
		
	});
});

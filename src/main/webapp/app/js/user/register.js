//函数的作用是去除字符串首尾的空白字符(包括空格，回车符，换行符，制表符)。
String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

$(function (){
	//注册
	$("#registerbtn").on('click',function(){
		
		var phone = $("#LAY-user-register-cellphone").val();
		var email = $("#LAY-user-register-email").val();
		var password = $(".my-input-mima").val();
		/*console.log(password)*/
		var repass = $(".my-input-remima").val();
		var name = $("#LAY-user-register-name").val();
		var loginName = $("#LAY-user-register-loginName").val();
		
		//用户名
		if(loginName==null||""==$.trim(loginName)||"null"==$.trim(loginName)){
			Layer.alert("请输入用户名!");
	        return false;
		}
		
		//验证用户名
		var regLoginName = /^[a-zA-Z0-9_-]{4,16}$/;
		if(loginName !=""&&!regLoginName.test(loginName)){
			Layer.alert("用户名为4到16位(字母/数字/下划线/减号)");
			return false;
		}
		
		//手机号
		if(phone==null||""==$.trim(phone)||"null"==$.trim(phone)){
			Layer.alert("请输入手机号!");
	        return false;
		}
		
		//验证手机号
		var regPhone = /^1[345789]\d{9}$/;
		if(phone !=""&&!regPhone.test(phone)){
			Layer.alert("请输入正确的手机号！");
			return false;
		}
		
		//邮箱
		if(email==null||""==$.trim(email)||"null"==$.trim(email)){
			Layer.alert("请输入邮箱!");
	        return false;
		}
		
		//验证邮箱
		var regEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(email !=""&&!regEmail.test(email)){
			Layer.alert("邮箱格式不正确，请重新输入！");
			return false;
		}
		
		//密码
		if(password==null||""==$.trim(password)||"null"==$.trim(password)){
			Layer.alert("请输入密码!");
	        return false;
		}
		
		//验证密码
		var regPassword =/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/; 
		if (password != "" && !regPassword.test(password)){
			Layer.alert("密码必须是6到16位的数字和字母!");
	    	return false;
	    }
		
		//验证两次输入的密码是否一致
		if(password != repass) {
			Layer.alert("两次密码不同，请重新输入");
		    return false;
		}
		
		//昵称
		if(name==null||""==$.trim(name)||"null"==$.trim(name)){
			Layer.alert("请输入昵称!");
	        return false;
		}
		
		
		
		password = hex_md5(password);
		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/register",
			{
			'user.loginName':loginName,
			'user.phone':phone,
			'user.email':email,
			'user.password':password, 
			'user.name':name,
			},function(dog){
				var inFlag= dog.inFlag; 
				if(inFlag==0){
					layer.alert('注册成功！', function(index){
						  window.location.href=$WEB_ROOT_PATH+"/";
						});
				}else if(inFlag==1){
					Layer.alert("该用户名已经被注册过了!");
					return false;
				}else if(inFlag==2){
					Layer.alert("该手机号已被注册!");
					return false;
				}else if(inFlag==3){
					Layer.alert("该邮箱号已被注册过!");
					return false;
				}
		
	},"json");
	
	
	});
	

	
});

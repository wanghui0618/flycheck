//函数的作用是去除字符串首尾的空白字符(包括空格，回车符，换行符，制表符)。
String.prototype.trim=function(){
	 return this.replace(/(^\s*)|(\s*$)/g, "");
	};
	
$(function(){
	//密码找回
	$("#forget").on('click', function() {
		//用户输入要找回密码的邮箱地址
		var loginName = $("#LAY-user-forget-loginName").val();
		var phone = $("#LAY-user-forget-phone").val();
		var email = $("#LAY-user-forget-email").val();  
		
		if(loginName==null||""==$.trim(loginName)||"null"==$.trim(loginName)){
			Layer.alert("请输入用户名！");
			return false;
		}
		
		if(phone==null||""==$.trim(phone)||"null"==$.trim(phone)){
			Layer.alert("请输入手机号！");
			return false;
		}
		
		if(email==null||""==$.trim(email)||"null"==$.trim(email)){
			Layer.alert("请输入邮箱地址！");
			return false;
		}
		
		
		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/forget",
				{
				'user.loginName':loginName,
				'user.email':email,
				'user.phone':phone
				},function(dog){
					var inFlag= dog.inFlag;
					var id = dog.oldPassword;//通过后台查询得到的用户id，传回给页面
					if(inFlag=="1"){
							 top.location.href=$WEB_ROOT_PATH+"/user/setNewPassword?"+"id="+encodeURI(id);//将id传给setNewPassword页面
					}else if(inFlag=="2"){
						Layer.alert("用户名、手机号或邮箱错误，请重新输入");
						return false;
					}else if(inFlag=="3"){
						Layer.alert("用户名、手机号或邮箱错误，请重新输入");
						return false;
					}else if(inFlag=="0"){
						Layer.alert("网络连接异常！");
						return false;
					}
		},"json");
		
	});
});

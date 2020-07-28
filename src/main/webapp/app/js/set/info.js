String.prototype.trim=function(){
	 return this.replace(/(^\s*)|(\s*$)/g, "");
 };
$(function (){

	$("#qrxg").on('click', function() {
		
		var newname = $("#newname").val();
		var newphone = $("#newphone").val();
		var newemail = $("#newemail").val();
		
		if(newname==null||""==$.trim(newname)||"null"==$.trim(newname)){
			Layer.alert("姓名不能为空");
			return false;
		}
		
		if(newphone==null||""==$.trim(newphone)||"null"==$.trim(newphone)){
			Layer.alert("电话号码不能为空");
			return false;
		}
		
		if(newemail==null||""==$.trim(newemail)||"null"==$.trim(newemail)){
			Layer.alert("邮箱不能为空");
			return false;
		}
		
		//验证电话
	    var regPhone= /^[\d]{11}$/;
		if (newphone != "" && !regPhone.test(newphone)){
			Layer.alert("手机格式不正确!");
	    	return false;
	    }
		
		//验证邮箱
		var regEmail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if (newemail != "" && !regEmail.test(newemail)){
			Layer.alert("邮箱格式不正确!");
	    	return false;
	    }
		
		
		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/updateNew",
				{
				'inFlag4':newname,
				'inFlag5':newphone,
				'inFlag6':newemail
				},function(dog){
					var inFlag= dog.inFlag;
					if(inFlag==1){
						Layer.alert("该用户名已存在，请重新输入");
						return false;
					}else if(inFlag==2){
						Layer.alert("该手机号已存在，请重新输入");
						return false;
					}else if(inFlag==3){
						Layer.alert("该邮箱号已存在，请重新输入");
						return false;
					}else if(inFlag==9){
						Layer.alert("修改失败，请重新尝试");
						return false;
					}else if(inFlag==4){
						Layer.alert("修改成功");
					}
			
		},"json");
		
		
	});
	
	
});


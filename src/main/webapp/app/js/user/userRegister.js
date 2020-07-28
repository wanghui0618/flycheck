/*layui.use(['form','layer','element'], function(){
  var form = layui.form;
  layer = layui.layer;
  $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
			function(data){
	     		var  dataList= data.dictList;
	     		for(var i=0 ;i<dataList.length;i++){
	     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
	     			$("#city").append(mm); 
	     		}
	     	form.render('select');
		});
	});*/

//函数的作用是去除字符串首尾的空白字符(包括空格，回车符，换行符，制表符)。
String.prototype.trim=function(){
		return this.replace(/(^\s*)|(\s*$)/g, "");
	};

$(function (){
	//注册
	$("#userRegisterbtn").on('click',function(){
		
		var phone = $("#LAY-user-login-cellphone").val();
		var email = $("#LAY-user-login-email").val();
		var password = $("#LAY-user-login-password").val();
		var repass = $("#LAY-user-login-repass").val();
		var name = $("#LAY-user-login-name").val();
		var loginName = $("#LAY-user-login-loginName").val();
		//手机号
		if(phone==null||""==$.trim(phone)||"null"==$.trim(phone)){
			Layer.alert("请输入手机号!");
	        return false;
		}
		
		//验证手机号
	    /*var regPhone= /^[\d]{11}$/;
		if (phone != "" && !regPhone.test(phone)){
			Layer.alert("手机格式不正确!");
	    	return false;
	    }*/
		
		//邮箱
		if(email==null||""==$.trim(email)||"null"==$.trim(email)){
			Layer.alert("请输入邮箱!");
	        return false;
		}
		
		//验证邮箱
		/*var regEmail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if (email != "" && !regEmail.test(email)){
			Layer.alert("邮箱格式不正确!");
	    	return false;
	    }*/
		
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
		if(loginName==null||""==$.trim(loginName)||"null"==$.trim(loginName)){
			Layer.alert("请输入昵称!");
	        return false;
		}
		
		//验证昵称
		var regLoginName = /^[a-zA-Z0-9_-]{4,16}$/;
		if(loginName !=""&&!regLoginName.test(loginName)){
			Layer.alert("昵称为4到16位(字母/数字/下划线/减号)");
			return false;
		}
		
		//用户名
		if(name==null||""==$.trim(name)||"null"==$.trim(name)){
			Layer.alert("请输入姓名!");
	        return false;
		}
		/*var style = password;*/
		password = hex_md5(password);
		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/userRegister",
			{
			'user.loginName':loginName,
			'user.phone':phone,
			'user.email':email,
			'user.password':password, 
			'user.name':name
			/*'user.oldpassword':style*/
			},function(dog){
				var inFlag= dog.inFlag; 
				if(inFlag==0){
					layer.alert('恭喜，注册成功！', function(index){
						  window.location.href=$WEB_ROOT_PATH+"/";
						});
				}else if(inFlag==1){
					Layer.alert("该手机号已被注册!");
					return false;
				}else if(inFlag==2){
					Layer.alert("该邮箱号已被注册过!");
					return false;
				}else if(inFlag==4){
					Layer.alert("该昵称已经被注册过了!");
					return false;
				}
	},"json");
	});
});

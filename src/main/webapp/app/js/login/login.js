//函数的作用是去除字符串首尾的空白字符(包括空格，回车符，换行符，制表符)。
String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

//一进来就检测本地存储，如果有的话，显示在账号、密码栏中
$(document).ready(function(){
	  var strName = localStorage.getItem('keyName');
	  var strPass = localStorage.getItem('keyPass');
	  if(strName){
		  $('#LAY-user-login-username').val(strName);
	  }if(strPass){
		  $('#LAY-user-login-password').val(strPass);
	  }
});
/*var code; //在全局 定义验证码
function createCode(){ 
	 event.stopPropagation();
	 //创建验证码函数
	 code = "";
	 var codeLength =4;//验证码的长度
	 var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k',
	 'm','n','p','q','r','s','t','u','v','w','x','y','z');//所有候选组成验证码的字符，当然也可以用中文的
	 for(var i=0;i<codeLength;i++)
	 { 
	 var charIndex =Math.floor(Math.random()*34);
	 code +=selectChar[charIndex]; 
	 }
	// 设置验证码的显示样式，并显示
	 document.getElementById("discode").style.fontFamily="Fixedsys"; //设置字体
	 document.getElementById("discode").style.letterSpacing="5px"; //字体间距
	 document.getElementById("discode").style.color="#0ab000"; //字体颜色
	 document.getElementById("discode").innerHTML=code; // 显示
}*/
var firstCode=parseInt(Math.random()*10);
var secondCode=parseInt(Math.random()*10);
$(function() {
	$("#code").text(firstCode+"+"+secondCode+"=?");
	$('#textCheck').val("请输入运算结果");
	$('#LAY-user-login-username').keydown(function(event){ 
		if(event.keyCode==13){
			$("#LAY-user-login-password").focus();
		}
	});
	$('#LAY-user-login-password').keydown(function(event){ 
		if(event.keyCode==13){
			$("#textCheck").focus();
		}
	});
	$('#textCheck').keydown(function(event){ 
		if(event.keyCode==13){
			equalCode();
		}
	});
	$("#textCheck").focus(function(){
		var v = $('#textCheck').val();
		if( v == "请输入运算结果"){
			$('#textCheck').val("");
		}
	});
	//登录动效
	//初始化判断否记住密码
	/*if($("#remember").is(':checked')){
		 $('.checkboxImg').attr('src','/piccbid/images/login/checkbox1.png');
	}else{
		$('.checkboxImg').attr('src','/piccbid/images/login/checkbox.png');
	};*/
	$("body").click(function(e){
			if(!$(e.target).is('#LAY-user-login-username') && !$(e.target).is('#LAY-user-login-password') && !$(e.target).is('.closeImg') && !$(e.target).is('.passwordImg')){
				 $(".closeImg").hide();
				  $(".passwordImg").hide();
			}
		});
	/*$("#verificationCode").focus(function(){
		  $(".closeImg").hide();
		  $(".passwordImg").hide();
		});*/
	$("#LAY-user-login-username").focus(function(){
		  $(".closeImg").show();
		  /*$(".passwordImg").show();*/
		});
	$(".closeImg").click(function(){
		 $("#LAY-user-login-username").val("");
		 $("#LAY-user-login-password").val("");
	});
	$("#LAY-user-login-password").focus(function(){
		  /*$(".passwordImg").show();*/
		  $(".closeImg").hide();
		});
	
	
	/*$(".passwordImg").click(function(){
		if($('#LAY-user-login-password').attr('type') == 'password'){
			  $("#LAY-user-login-password").attr("type","text");
			  $('.passwordImg').attr('src','/piccbid/images/login/password.png');
			  
		}else{
			$("#LAY-user-login-password").attr("type","password");
			 $('.passwordImg').attr('src','/piccbid/images/login/password1.png');
		}
		
	});*/
	
	//点击记住密码，出现有√和没有√的图片
	$("#remember").click(function(){
		if($("#remember").is(':checked')){
			$('.checkboxImg').attr('src','/piccbid/images/login/checkbox1.png');
		}else{
			$('.checkboxImg').attr('src','/piccbid/images/login/checkbox.png');
		}
		
	});
	
	 $("#loginbtn").on('click', function() {
		 
		  var targetCode = firstCode+secondCode;
		  var realCode = $("#textCheck").val();
		  var strName = $('#LAY-user-login-username').val();
		  var strPass = $('#LAY-user-login-password').val();
		 /* var verificationCode = $('#verificationCode').val();*/
		  
			// 用户输入的账户名
		    var userName = $("#LAY-user-login-username").val();
			// 用户输入的密码
		    var password = $("#LAY-user-login-password").val();
		    if(userName==null||""==$.trim(userName)||"null"==$.trim(userName)){
		    	showError("请填写用户名/邮箱/手机号");
				return false;
			}
			if(password==null||""==$.trim(password)||"null"==$.trim(password)){
				//showError("请输入密码！");
				showError("请填写密码");
				return false;
			}
			var regRealCode = /^([0-9]|(1[0-8]))$/;
			if(realCode!= "" && !regRealCode.test(realCode)){
				showError("验证码输入不正确！");
				refreshCode();
				clearVal();
				return false;
			}
			if(realCode == ""){
				showError("请输入验证码！");
				$("#textCheck").focus();
				refreshCode();
				clearVal();
				return false;
			}
			if(parseInt(targetCode) != parseInt(realCode)){
				showError("验证码不正确！");
				refreshCode();
				clearVal();
				return false;
			}
			/*but();*/
			//验证登录名
		    var regPhone= /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
		    var regEmail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		    var regLoginName=/^[a-zA-Z0-9_-]{4,16}$/;
			if (userName != "" && !regPhone.test(userName) && !regEmail.test(userName)&& !regLoginName.test(userName)){
				showError("手机/邮箱格式不正确!");
		    	return false;
		    }
			password = hex_md5(password);
		    $.get($WEB_ROOT_PATH+"/dhccApi/user/user/login",
		  			{
		  			'inFlag7':userName,
		  			'inFlag8':password
		  			},function(data){
		  				var role=data.role;
		  				var status=data.status;
		  				if(data.msgStr=="1"){
		  					//当登录成功的时候，点击登录按钮，先把账号和密码都存储进去
		  					var strName = $('#LAY-user-login-username').val();
		  					var strPass = $('#LAY-user-login-password').val();
		  					localStorage.setItem('keyName',strName);
		  					//如果勾选了“记住密码”，则把密码存储进去
		  					if($('#remember').is(':checked')){
		  					  localStorage.setItem('keyPass',strPass);
		  					}else{
		  						//否则，就把密码这个键值对删除
		  						localStorage.removeItem('keyPass');
		  					}
		  					//然后跳转到首页
		  					if(status=="1"){
		  						top.location.href=$WEB_ROOT_PATH+"/indexHome";
		  					}else if(status=="0"){//城市用户需要判断status状态码
		  							showError("此账号还未通过管理员审核，请联系管理员");
			  						return false;
		  						}else if(status=="2"){
		  							showError("账号审核未通过，请联系管理员");
			  						return false;
		  						}
		  					}else if(data.msgStr=="2"){
		  						showError("用户名/邮箱/手机号，或密码错误，请重新输入");
		  						return false;
		  					}else if(data.msgStr=="3"){
		  						showError("账号不存在");
		  						return false;
		  					}else if(data.msgStr=="0"){
			  					showError("登录异常");
			  					return false;
			  				}
		  			},"json");
			});
});
function clearVal(){
	$("#textCheck").focus(function(){
		var v = $('#textCheck').val();
		if( v == "请输入运算结果"){
			$('#textCheck').val("");
			$("#textCheck").focus();
		}
	});
}
function refreshCode(){
	firstCode = parseInt(Math.random()*10);
	secondCode = parseInt(Math.random()*10);
	var text = firstCode+"+"+secondCode+"=?";
	$("#code").text(text);
}
function showError(info){
	$("#emptyUserName").html(info);
}
 
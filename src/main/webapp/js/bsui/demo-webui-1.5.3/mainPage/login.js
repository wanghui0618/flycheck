// dom缓存
var objs = {
	$loginName: $("#loginName"),
	$password: $("#password"),
	$verifyCode: $("#verifyCode"),
	$remember: $("#remember"),
	$login: $("#login")
};

//创建验证码插件
var container = document.getElementById("codeImg");
var code = new vCode(container, {
	len: 4,
	bgColor: '#eff3f7',
	colors: [
	    "#114618",
	    "#0d2210",
        "#110b66",
        "#6f0716",
        "#0d424a"
	]
});

$(function() {
	rememberMe();
	
	// 单击回车键登录
	$(document).keydown(function(event) {
		if (event.keyCode == 13) {
			//loginAuthen();
		}
	});
	
	// 单击“登录”按钮登录
	objs.$login.off().on('click', function() {
		//loginAuthen();
	});
	
	// 验证码输入框监听
    objs.$verifyCode.on("input propertychange", function() {
    	var verifyCode = $(this).val();
		var length = verifyCode.length;

		if (length) {
			if (4 == length) {
				if (code.verify(verifyCode)) {
					$(this).parent().removeClass('has-error');
				} else {
					$(this).parent().addClass('has-error');
				}
			} else {
				$(this).parent().addClass('has-error');
			}
		} else {
			$(this).parent().addClass('has-error');
		}
	});
    objs.$verifyCode.focusout(function() {
		if (!$(this).parent().hasClass('has-error') && $(this).val().length==0) {
			$(this).parent().addClass('has-error');
		}
	});
});

// 记住用户名和密码
function rememberMe() {
	if (undefined!=localStorage.checked && "true"==localStorage.checked) {
		objs.$loginName.on('input propertychange', function() {
			localStorage.loginName = objs.$loginName.val();
		});
		
		objs.$password.on('input propertychange', function() {
			localStorage.password = objs.$password.val();
		});
		
		objs.$loginName.val(localStorage.loginName);
		objs.$password.val(localStorage.password);
		objs.$remember.prop('checked', true);
	} else {
		sessionStorage.removeItem("loginName");
		sessionStorage.removeItem("password");
	}

	objs.$remember.off().on('click', function() {
		if (objs.$remember.is(':checked')) {
			localStorage.checked = 'true';
			localStorage.loginName = objs.$loginName.val();
			localStorage.password = objs.$password.val();
		} else {
			localStorage.checked = 'false';
			sessionStorage.removeItem("loginName");
			sessionStorage.removeItem("password");
		}
	});
}

// 登录跳转
function loginAuthen() {
	var loginName = objs.$loginName.val();
	var password = objs.$password.val();
	
	if (!loginName.length) {
		return false;
	} else {
		if (!password.length) {
			return false;
		} else {
			postReq(
				$.WEBROOT + '/login/loginCtrl.htm?BLHMI=login',
				'#logForm',
				function(data) {
					if ("1" == data.opFlg) {
						window.location.href = $.WEBROOT + '/menu/menuCtrl.htm?BLHMI=mainPage';
					} else {
						$CommonUI.alert(data.msg, null, 3000);
					}
				},
				function(xhr,textStatus,errorThrown) {
					$CommonUI.alert(textStatus);
				}
			);
		}
	}
}
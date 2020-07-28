<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
    <title>用户注册</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/modules/layer/default/layer.css" media="all">
	
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/login/register.css" />
    <script src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonUI.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonValidate.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/lay/modules/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/register.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/login/encode.js"></script>
<script language="JavaScript">
var Layer;
layui.use('layer', function(){
		Layer = layui.layer;
	});
function keyRegister(){
	 if (event.keyCode==13)  //回车键的键值为13
	   document.getElementById("registerbtn").click(); //调用登录按钮的登录事件
	}
</script> 
</head>
<body onkeydown="keyRegister();">
    <div class="index">
        <div class="login_header">
            <div class="login_dhc"><img src="<%=request.getContextPath() %>/images/main/picc_logo_red_blcak.png" class="logosize" /></div>
        </div>

        <div class="login_contain">
        	<div style="line-height: 60px;font-size: 28px;color:#292929">
	        	<center>- 用户注册 -</center>
	        </div>
            <div class="login_carousel">
            	<div class="loginbox_cq">
	            	<div class="input_div">
	            		<input type="text" name="loginName" id="LAY-user-register-loginName" autoComplete="off" placeholder="用户名" class="my-input-xingming">
	            	</div>
	            	<div class="input_div">
	            		<input type="text" name="cellphone" id="LAY-user-register-cellphone" autoComplete="off" placeholder="手机号码" class="my-input-phone">
	            	</div>
	            	<div class="input_div">
	            		<input type="text" name="email" id="LAY-user-register-email" autoComplete="off" placeholder="邮箱" class="my-input-email">
	            	</div>
	            	<div class="input_div">
	            		<input type="password"  autocomplete="new-password" placeholder="密码" class="my-input-mima">
	            	</div>
	            	<div class="input_div">
	            		<input type="password" autocomplete="new-password" placeholder="确认密码" class="my-input-remima">
	            	</div>
	            	<div class="input_div">
	            		<input type="text" name="name" id="LAY-user-register-name" autoComplete="off"  placeholder="真实姓名" class="my-input-xingming">
	            	</div>
	            	<button type="button" id="registerbtn" class="btn btn-success btn-lg btn-block">注册</button>
            		<div class="input_div" style="text-align:right;margin-top: 15px;padding-right: 48px;">
	            		<a style="color:#535353;font-family: PingFangSC-Regular;" href="<%=request.getContextPath() %>/">用已有帐号登入>></a>
	            	</div>
            	</div>
            </div>
        </div>
    </div>
</body>
</html>

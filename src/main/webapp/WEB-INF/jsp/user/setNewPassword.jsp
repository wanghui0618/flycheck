<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
    <title>密码找回</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/modules/layer/default/layer.css" media="all">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/login/register.css" />
    <script src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonUI.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonValidate.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/lay/modules/layer.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/setNewPassword.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/login/encode.js"></script>
<script language="JavaScript">
var Layer;
layui.use('layer', function(){
		Layer = layui.layer;
	});
function keySetNewPassword(){
 if (event.keyCode==13)  //回车键的键值为13
   document.getElementById("setNewPassword").click(); //调用登录按钮的登录事件
}
</script> 
</head>
<body onkeydown="keySetNewPassword();">
    <div class="index">
        <div class="login_header">
            <div class="login_dhc"><img src="<%=request.getContextPath() %>/images/main/picc_logo_red_blcak.png" class="logosize" /></div>
        </div>

        <div class="login_contain">
        	<div style="line-height: 90px;font-size: 28px;color:#292929">
        		<center>- 设置新密码 -</center>
        	</div>
            <div class="login_carousel">
            	<div class="loginbox_cq" style="height: 280px;">
            		<div class="input_div" style="display:none">
	            		<input type="text" name="loginName" id="LAY-user-register-loginName" autocomplete="off" placeholder="用户名" class="my-input-xingming">
	            	</div>
	            	<div class="input_div">
	            		<input type="password"  autocomplete="new-password" placeholder="密码" class="my-input-mima">
	            	</div>
	            	<div class="input_div">
	            		<input type="password" autocomplete="new-password" placeholder="确认密码" class="my-input-remima">
	            	</div>
	            	
	            	<button type="button" id="setNewPassword" class="btn btn-success btn-lg btn-block">确定</button>
	            	
            		<div class="input_div" style="text-align:right;margin-top: 15px;padding-right: 10px;">
	            		<a style="color:#535353;font-family: PingFangSC-Regular;" href="<%=request.getContextPath() %>/">返回登录界面>></a>
	            	</div>
            	</div>
            </div>
        </div>
        <!--页脚-->
        <div class="login_footer">
            <div class="log_f">
            </div>
        </div>
        <!--页脚-->
    </div>
</body>
</html>

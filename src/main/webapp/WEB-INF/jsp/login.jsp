<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<%@page import="com.dhcc.piccbid.utils.DhccUtil"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
    <title><%=DhccUtil.title %></title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/modules/layer/default/layer.css" media="all">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/login/login.css" />
    <script src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonUI.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonValidate.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/lay/modules/layer.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/login/login.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/login/encode.js"></script>
<script>
var Layer;
layui.use('layer', function(){
		Layer = layui.layer;
	});
function keyLogin(){
 if (event.keyCode==13)  //回车键的键值为13
   document.getElementById("loginbtn").click(); //调用登录按钮的登录事件
}
</script> 
<style type="text/css">
		#textCheck{
			color:#767676;
		}
		#textCheck:focus{
			color:#000;
		}
    </style>

</head>
<body onkeydown="keyLogin();" style="overflow:hidden">
    <div class="index">
        <!--LOGO-->
        <div class="login_header" >
              <div class="login_dhc" id="login_header"><img src="<%=request.getContextPath()%>/images/main/logo_black_<%=DhccUtil.logo %>.png"   class="xtlogo_<%=DhccUtil.css %>" /></div>  
        </div>
        <!--LOGO-->

        <div class="login_contain">
            <div class="login_carousel"></div>
        </div>

        <!--登录框 SCQ-->
        <div class="loginbox_cq">
            <form style="margin-left: 55px;margin-top: 35px;">
                <div class="tcq">欢迎登录</div>
                <div id="msgContainer" class="login_box_msg" style=""><div class="error" id="emptyUserName">&nbsp;</div></div>
                <div class="input_div">
                	<img src="<%=request.getContextPath() %>/images/login/xingming.png" style="width:16px;z-index: 999;position: absolute;right: 35px;top: 3px;left: 65px;top: 115px;">
            		<input type="text" id="LAY-user-login-username" name="j_username" placeholder="用户名/邮箱/手机号">
            		<img class="closeImg" alt="" src="<%=request.getContextPath() %>/images/login/close.png" style="position: relative;right: 33px;top: 4px;display:none;">
            	</div>
            	
            	<div class="input_div">
            		<img src="<%=request.getContextPath() %>/images/login/mima.png" style="width:16px;z-index: 999;position: absolute;right: 35px;top: 3px;left: 65px;top: 160px;">
            		<input type="password"  id="LAY-user-login-password" name="j_password" placeholder="密码">
            		<%-- <img class="passwordImg" alt="" src="<%=request.getContextPath() %>/images/login/password1.png" style="position: relative;right: 35px;top: 3px;display:none;"> --%>
            	</div>
            	<div class="input_div" onkeydown="login();">
            		<img src="<%=request.getContextPath() %>/images/login/yzm.png" style="width:16px;z-index: 999;position: absolute;right: 35px;top: 3px;left: 65px;top: 210px;">
            		<input id="textCheck" type="text" lay-verify="required" placeholder=""  autocomplete="off"/>
            		<a style="text-decoration:none;float: right;margin-top: -30px;margin-left: 210px;width: 40px;height: 30px;display: block;position: absolute;cursor:pointer" href="javascript:refreshCode()">
							<div style="color: black;width: 90px;height: 39px;float: left;margin-top: -9px;text-align: center;background-color: #dddddd;font-weight: bold;margin-left: -34px;" >
								<label id="code" style="cursor:pointer;line-height: 38px;"></label>
							</div>
				    	</a>
            	</div>
                <div class="input_div" style="margin-top: -10px;">
                    <img class="checkboxImg" alt="" src="<%=request.getContextPath() %>/images/login/checkbox1.png" style="position: relative;top: 3px;">
                    <input style="height: 38px;width: 16px;margin-left: -18px;z-index: 99;position: relative;opacity: 0;" type="checkbox"  value="1"  name="remember"  id="remember"  checked>记住密码
                </div>
                <button type="button" id="loginbtn" class="btn btn-success btn-lg btn-block">登录</button>
            	<div class="input-group forget">
                    <label><a style="color: #616771;text-decoration:none" href="<%=request.getContextPath() %>/user/register" class="layadmin-user-jump-change layadmin-link">用户注册</a>&nbsp;|&nbsp;<a href="<%=request.getContextPath() %>/user/forget" style="color: #616771;text-decoration:none" class="layadmin-user-jump-change layadmin-link">忘记密码?</a></label>
                </div>
            </form>
        </div>
        <!--登录框 ECQ-->
        <!--页脚-->
        <div class="login_footer">
            <div class="log_f">
                <i class="fa fa-copyright"></i>
                <span>Copyright © PICC Health Insurance Company Limited.. All Rights Reserved.</span>
            </div>
        </div>
        <!--页脚-->
    </div>
</body>
</html>

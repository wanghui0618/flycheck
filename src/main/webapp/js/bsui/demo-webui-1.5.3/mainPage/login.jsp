<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="../../dhccbs3/dhccbs3.7.css"/>
<!--登录页面样式文件-->
<link rel="stylesheet" type="text/css" href="login.css"/>
<title>用户登录</title>
</head>
<body>
	<!--header-->
	<header>
		<img class="pull-left" src="images/iHealthLOGO_sm.png"/>
		<div class="pull-right"><span class="glyphicon glyphicon-cog"></span> <span>帮助中心</span></div>
	</header><!--/.header-->
	
	<!--content-->
	<div class="content">
		<div class="panel panel-default pull-right">
			<div class="panel-body">
    			<form id="logForm" role="form">
    				<div class="form-group">
    					<p class="form-control-static login-title">用户登录</p>
    				</div>
    				<div class="form-group">
    					<input id="loginName" type="text" class="form-control user" placeholder="用户名"/>
    				</div>
    				<div class="form-group">
    					<input id="password" type="password" class="form-control password" placeholder="密码"/>
    				</div>
    				<div class="form-group">
    					<span class="code-in">
    						<input id="verifyCode" type="text" class="form-control" placeholder="输入验证码"/>
    					</span>
    					<span id="codeImg" class="code-box"></span>
    				</div>
    				<div class="checkbox">
    					<label>
      						<input id="remember" type="checkbox">下次自动登录
    					</label>
  					</div>
  					<button id="login" type="button" class="btn btn-primary btn-block">登录</button>
    			</form>
  			</div>
		</div>
	</div><!--/.content-->
	
	<!--footer-->
	<footer>
		<p>推荐使用IE8及以上版本浏览器</p>
		<p>版本号：东华软件研发七部系统1.2.0 &#169;东华软件股份公司 保留所有权利</p>
	</footer><!--/.footer-->
</body>
<!--[if lt IE 9]>
<script src="../../dhccbs3/lib/pre/html5shiv.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../dhccbs3/lib/pre/respond.min.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<script src="../../dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../commonUI.js" type="text/javascript" charset="utf-8"></script>
<!--验证码插件js-->
<script src="checkCode.js" type="application/javascript" charset="UTF-8"></script>
<!--登录页面js-->
<script src="login.js" type="text/javascript" charset="utf-8"></script>
</html>
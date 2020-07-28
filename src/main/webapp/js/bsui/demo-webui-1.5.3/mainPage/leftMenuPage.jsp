<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.dhcc.framework.util.JsonUtils"%>-->
<!-- 通过pms获取session需要引入的java类
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dhcc.framework.web.context.VisitUser" %>
<%@page import="com.dhcc.framework.web.context.Visit" %> -->
<!-- 通过cas获取session需要引入的java类
<%@page import="com.dhcc.um.common.auth.SessionUtils"%>
<%@page import="com.dhcc.um.common.spring.cas.userdetails.DhccUser"%> -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="../../dhccbs3/dhccbs3.7.css"/>
<!--icons-->
<link rel="stylesheet" type="text/css" href="../../font-awesome/css/font-awesome.min.css"/>
<!--皮肤（蓝色）-->
<link rel="stylesheet" type="text/css" href="skins/skin-blue-l.css"/>
<!--菜单小图标-->
<link rel="stylesheet" type="text/css" href="skins/menu-icon.css"/>
<!--左侧菜单模板样式文件-->
<link rel="stylesheet" type="text/css" href="leftMenu.css"/>
<title>主页模板-左侧菜单</title>
</head>
<body class="skin-blue sidebar-mini">
	<!--修改密码弹窗-->
	<div id="modifyPwWin" class="bsui-window" title="修改账户密码" style="display:none;" data-options="
		modal:true,
		width: 330,
		footer:'#modifyPwWinFooter',
		minimizable:false,
		maximizable:false,
		closed:true">
		<form id="modifyPwForm" method="post">
			<table class="form-table">
				<tr style="height:3em;">
	    			<th style="text-align:right;"><sup style="color: red;">*</sup>原始密码：</th>
	    			<td><input type="password" class="bsui-validatebox" name="validatorVo.fieldValue" data-options="required:true,prompt:'请输入原始密码'"/></td>
	    		</tr>
	    		<tr style="height:3em;">
					<th style="text-align:right;"><sup style="color: red;">*</sup>新密码：</th>
	    			<td><input id="pwd" type="password" class="bsui-validatebox" name="account.password" data-options="required:true,prompt:'请输入新密码'" validType="password"/></td>
	    		</tr>
	    		<tr style="height:3em;">
					<th style="text-align:right;"><sup style="color: red;">*</sup>确认新密码：</th>
	    			<td><input id="rpwd" type="password" class="bsui-validatebox" name="confirm_password" data-options="required:true,prompt:'请再次输入新密码'" validType="equals['#pwd']"/></td>
	    		</tr>
	    	</table>
		</form>
	</div>
	<div id="modifyPwWinFooter" align="center" style="padding:5px;">
       	<button type="button" class="btn btn-primary" onclick="modifyPassword()">确认修改</button>
      	<button type="button" class="btn btn-default" onclick="resetPassword()">重置</button>
	</div><!--/.修改密码弹窗-->
	
	<!--页面布局-->
	<div id="wrapper" class="wrapper">
		<!--header navigation-->
		<header id="main_header" class="navbar navbar-default navbar-fixed-top" role="navigation">
			<!--Logo-->
			<div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="offcanvas">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="logo" href="#"><img src="images/iHeathLOGO.png" alt="此处放logo"/></a>
			</div><!--/.Logo-->
           	
           	<!--navbar-top-links-->
			<ul class="nav navbar-top-links navbar-right">
           		<li class="dropdown dropdown-operates">
           			<a class="dropdown-toggle user" data-toggle="dropdown" href="#">
           				<img src="images/user7-128x128.jpg" class="img-circle img-responsive pull-left"/>
           				<span class="hidden-xs user">您好！管理员 <i class="fa fa-angle-down"></i></span>
           			</a>
           			<ul class="dropdown-menu">
           				<li><a href="#"><i class="fa fa-user"></i> <span>用户基本信息</span></a></li>
           				<li><a href="#" onclick="showPasswordWin()"><i class="fa fa-lock"></i> <span>修改密码</span></a></li>
           				<li><a href="#"><i class="fa fa-upload"></i> <span>上传头像</span></a></li>
           				<li class="divider"></li>
           				<li><a href="#"><i class="fa fa-power-off"></i> <span>安全退出</span></a></li>
           			</ul>
           		</li>
           		<li class="dropdown dropdown-messages">
           			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
           				<i class="fa fa-bell icon"></i>
           			</a>
           			<span class="tips">6</span>
           			<ul class="dropdown-menu">
           				<li class="header"><label>最新消息通知</label><span class="badge pull-right">New 6</span></li>
           				<li>
           					<!-- inner menu: contains the actual data -->
           					<ul class="menu">
           						<li>
           							<a href="#">
           								<span class="fa fa-user pull-left"></span>
           								<p>您有最新订阅的医疗保健新闻<br><small>2小时前</small></p>
           							</a>
           						</li>
           						<li>
           							<a href="#">
           								<span class="fa fa-user pull-left"></span>
           								<p>您的病人等待回复<br><small>3小时前</small></p>
           							</a>
           						</li>
           						<li>
           							<a href="#">
           								<span class="fa fa-user pull-left"></span>
           								<p>您有两份病历需要整理提交<br><small>1天前</small></p>
           							</a>
           						</li>
           						<li>
           							<a href="#">
           								<span class="fa fa-user pull-left"></span>
           								<p>您的头像未上传<br><small>10天前</small></p>
           							</a>
           						</li>
           					</ul>
           				</li>
           				<li class="footer">
           					<a href="#">管理您的消息</a>
           					<a href="#" class="pull-right"><i class="fa fa-cog"></i></a>
           				</li>
           			</ul>
           		</li>
           		<li class="dropdown">
           			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
           				<i class="fa fa-power-off icon"></i>
           			</a>
           		</li>
			</ul><!--/.navbar-top-links-->
		</header><!--/.header navigation-->
		
		<!--main menu-->
		<nav id="main_sidebar" class="navbar-default main-sidebar" role="navigation">
			<section class="sidebar-nav navbar-collapse sidebar">
				<div class="menu-header hidden-xs">
					<span class="sidemenu-name">系统导航</span>
					<!--<span class="menu-toggle pull-right" data-toggle="offcanvas"><i class="fa fa-outdent"></i></span>-->
					<span class="menu-toggle pull-right" data-toggle="offcanvas"><i class="fa fa-bars"></i><i class="fa fa-caret-left"></i></span>
				</div>
				<ul id="side_menu" class="sidebar-menu"></ul>
			</section>
		</nav><!--/.main menu-->
		
		<!--aside tool-->
		<!--<aside class="aside-tool">
			<i class="fa fa-cog"></i>
		</aside>--><!--/.aside tool-->
		
		<!--page content-->
		<div id="page_wrapper" class="page-wrapper"></div><!--/content wrapper-->
	</div><!--/.页面布局-->
</body>
<!--[if lt IE 9]>
<script src="../../dhccbs3/lib/pre/html5shiv.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../dhccbs3/lib/pre/respond.min.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<script src="../../dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
<!--滚动条插件-->
<script src="../../../jquery/jquery.slimscroll.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../common.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../commonUI.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../commonValidate.js" type="text/javascript" charset="utf-8"></script>
<!--获取系统访问权限）-->
<!--<script type="text/javascript">
	var authorities = null,
		authoritiesMap = new Object();
	// 通过pms登录
	<%-- Visit visit = (Visit)session.getAttribute("currentUser");
	VisitUser visitUser = visit.getUserInfo();
	%>
	authorities = <%=JsonUtils.toJson(visitUser.getPrivilege())%>;
	if(authorities.length) {
		$.each(authorities, function(index, url) {
			authoritiesMap[url] = true;
		});
	}
	
	// 通过cas登录
	<%
	DhccUser dhccUser = SessionUtils.getSessionUser();
	%>
	authorities = <%=JsonUtils.toJson(dhccUser.getPermissions())%>; --%>
	if(authorities.length) {
		$.each(authorities, function(i, v) {
			authoritiesMap[v.securityUrl] = true;
		});
	}
</script>-->
<!--主页模板js-->
<script src="leftMenu.js" type="text/javascript" charset="utf-8"></script>
</html>
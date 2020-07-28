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
<link rel="stylesheet" type="text/css" href="skins/skin-blue-t.css"/>
<!--顶部菜单模板样式文件-->
<link rel="stylesheet" type="text/css" href="topMenu.css"/>
<!--菜单小图标-->
<link rel="stylesheet" type="text/css" href="skins/menu-icon.css"/>
<title>主页模板-顶部菜单</title>
</head>
<body class="skin-blue">
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
                <!--<span class="menu-toggle pull-right" data-toggle="offcanvas"><i class="fa fa-bars"></i><i class="fa fa-caret-left"></i></span>-->
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
           				<li><a href="#"><i class="fa fa-lock"></i> <span>修改密码</span></a></li>
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
           				<li class="header"><label>最新消息通知</label><span class="badge pull-right">New 5</span></li>
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
			
			<!--main menu-->
			<nav id="main_sidebar" class="navbar-default main-sidebar" role="navigation">
				<div class="menu-btn text-center"><i class="fa fa-chevron-left"></i></div>
				<ul id="side_menu" class="sidebar-menu"></ul>
				<div class="menu-btn text-center"><i class="fa fa-chevron-right"></i></div>
			</nav><!--/.main menu-->
		</header><!--/.header navigation-->
		
		<!--aside tool-->
		<!-- <aside class="aside-tool">
			<i class="fa fa-cog"></i>
		</aside>--><!--/.aside tool-->
		
		<!--page content-->
		<div id="page_wrapper" class="page-wrapper"></div><!--/content wrapper-->
	</div>
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
	<%-- <%
	Visit visit = (Visit)session.getAttribute("currentUser");
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
<script src="topMenu.js" type="text/javascript" charset="utf-8"></script>
</html>
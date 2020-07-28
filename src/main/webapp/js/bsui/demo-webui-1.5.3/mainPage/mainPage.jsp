<!-- <%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="../../dhccbs3/dhccbs3.7.css"/>
<link rel="stylesheet" type="text/css" href="../../font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="skins/skin-blue.css"/>
<link rel="stylesheet" type="text/css" href="skins/menu-icon.css"/>
<link rel="stylesheet" type="text/css" href="mainPage.css"/>
<title>主页模板-左侧菜单</title>
</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
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
           		<li class="dropdown">
           			<a class="dropdown-toggle user" data-toggle="dropdown" href="#">
           				<img src="images/user7-128x128.jpg" class="img-circle img-responsive pull-left"/>
           				<span class="hidden-xs user">您好！管理员 <i class="fa fa-angle-down"></i></span>
           			</a>
           			<ul class="dropdown-menu">
           				<li><a href="#"><i class="fa fa-user"></i><span>用户基本信息</span></a></li>
           				<li><a href="#"><i class="fa fa-lock"></i><span>修改密码</span></a></li>
           				<li><a href="#"><i class="fa fa-upload"></i><span>上传头像</span></a></li>
           				<li class="divider"></li>
           				<li><a href="#"><i class="fa fa-power-off"></i><span>安全退出</span></a></li>
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
           				<li class="footer"><a href="#">管理您的消息</a></li>
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
				<div class="text-center menu-header hidden-xs">
					<span>系统导航</span>
					<span class="menu-toggle pull-right" data-toggle="offcanvas"><i class="fa fa-bars"></i><i class="fa fa-caret-left"></i></span>
					<!--<span class="fa fa-indent pull-right"></span>-->
				</div>
				<ul id="side_menu" class="sidebar-menu"></ul>
			</section>
		</nav><!--/.main menu-->
		
		<!--aside tool-->
		<aside class="aside-tool">
			<i class="fa fa-cog"></i>
		</aside><!--/.aside tool-->
		
		<!--page content-->
		<div id="page_wrapper" class="page-wrapper"></div><!--/content wrapper-->
	</div>
</body>
<!--[if lt IE 9]>
<script src="../../dhccbs3/lib/pre/html5shiv.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../dhccbs3/lib/pre/respond.min.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<script src="../../dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../jquery/jquery.slimscroll.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../common.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../commonUI.js" type="text/javascript" charset="utf-8"></script>
<script src="../../../commonValidate.js" type="text/javascript" charset="utf-8"></script>
<script src="mainPage.js" type="text/javascript" charset="utf-8"></script>
</html>
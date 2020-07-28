<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.dhcc.piccbid.entity.user.User"%>
<%@page import="com.dhcc.piccbid.utils.DhccUtil"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<script
	src="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/layui.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/dist-layui.css"
	media="all">
<title><%=DhccUtil.title %></title>
<style type="text/css">
.curr {
	color: #0171bb;
	font-style: italic;
	font-weight: bolder;
}

.layui-nav-item {
	float: left;
}

.layui-nav .layui-nav-more {
	border-color: #000 transparent transparent;
}

.layui-nav .layui-nav-mored {
	border-color: transparent transparent #000;
}

.my-head-left {
	line-height: 50px;
}
</style>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/newLayui.css" />
</head>
<%
	User user = (User) session.getAttribute("user");
	String name = user.getName();
%>
<body class="layui-layout-body">

	<div id="LAY_app">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header"
				style="border-bottom-width: 0px; z-index: 9999; background-color: #2284FF;">
				<ul class="my-head-left">
					<li style="margin-left: 2%;" class="layui-nav-item"><img
						style="margin: 0 auto; " class="xtlogohome_<%=DhccUtil.css %>"
						src="<%=request.getContextPath()%>/images/main/logo_white_<%=DhccUtil.logo %>.png" /></li>
					<li style="margin-left: 1%;" class="layui-nav-item"><img
						style="margin: 0 auto; height: 30px;"
						src="<%=request.getContextPath()%>/images/main/fenge.png" /></li>
					<li style="margin-left: 1%;" class="layui-nav-item"><a
						href="<%=request.getContextPath()%>/indexHome"><img
							onmouseover="{this.setAttribute('src', '/flycheck/images/main/jiugong_hover.png')}"
							onmouseout="{this.setAttribute('src', '/flycheck/images/main/jiugong.png')}"
							style="margin: 0 auto; width: 25px;"
							src="<%=request.getContextPath()%>/images/main/jiugong.png" /></a></li>
					<li style="margin-left: 2%;" class="layui-nav-item"><img
						style="margin: 0 auto; width: 10px;"
						src="<%=request.getContextPath()%>/images/main/position.png" /></li>
					<li style="margin-left: 5px; font-size: 15px; color: #fff"
						class="layui-nav-item">总体概况<span id="textHidden">&nbsp;>&nbsp;主页</span></li>
				</ul>
				<ul class="layui-nav layui-layout-right"
					lay-filter="layadmin-layout-right">
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a href="javascript:;" style="margin: 0; padding: 0;"> 
							<img style="margin: 0 auto; height: 36px; padding: 0 10px;width:36px;border-radius:50%;"
							src="<%=request.getContextPath()%>${sessionScope.headimgurl}"
							onerror='<c:catch>src="<%=request.getContextPath()%>/images/main/touxiang.png"</c:catch>' />
					</a>
					</li>
					<li class="layui-nav-item" lay-unselect><a href="javascript:;">
							<cite style="color: #fff">您好！<%=name%></cite>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a lay-href="<%=request.getContextPath()%>/set/info">基本资料</a>
							</dd>
							<dd>
								<a lay-href="<%=request.getContextPath()%>/set/password">修改密码</a>
							</dd>
							<hr>
							<dd style="text-align: center;">
								<a onclick="logout()">退出</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item" lay-unselect style="margin-right: 30px;"></li>
					<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm"
						lay-unselect><a href="javascript:;" layadmin-event="more"><i
							class="layui-icon layui-icon-more-vertical"></i></a></li>
				</ul>
			</div>

			<div class="topBtn" style="">
				<!-- 头部区域 -->
				<div class="left_menu">
					<img
						onmouseover="{this.setAttribute('src', '/flycheck/images/main/left_hover.png')}"
						onmouseout="{this.setAttribute('src', '/flycheck/images/main/left.png')}"
						style="margin: 0 auto; height: 22px;"
						src="<%=request.getContextPath()%>/images/main/left.png" />
				</div>
				<div class="left_caidanfenge">
					<img style="margin: 0 auto; height: 35px;"
						src="<%=request.getContextPath()%>/images/main/caidanfenge.png" />
				</div>
				<ul id="ul" class="layui-nav layui-layout-center"
					style="border-radius: 0px;"></ul>
				<div class="right_menu">
					<img
						onmouseover="{this.setAttribute('src', '/flycheck/images/main/right_hover.png')}"
						onmouseout="{this.setAttribute('src', '/flycheck/images/main/right.png')}"
						style="margin: 0 auto; height: 22px;"
						src="<%=request.getContextPath()%>/images/main/right.png" />
				</div>
				<div class="right_caidanfenge">
					<img style="margin: 0 auto; height: 35px;"
						src="<%=request.getContextPath()%>/images/main/caidanfenge.png" />
				</div>
			</div>

			<!-- 页面标签 -->
			<div class="layadmin-pagetabs" id="LAY_app_tabs">
				<div class="layui-icon layadmin-tabs-control layui-icon-prev"
					layadmin-event="leftPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-next"
					layadmin-event="rightPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-down">
					<ul class="layui-nav layadmin-tabs-select"
						lay-filter="layadmin-pagetabs-nav">
						<li class="layui-nav-item" lay-unselect><a
							href="javascript:;"></a>
							<dl class="layui-nav-child layui-anim-fadein">
								<dd layadmin-event="closeThisTabs">
									<a href="javascript:;">关闭当前标签页</a>
								</dd>
								<dd layadmin-event="closeOtherTabs">
									<a href="javascript:;">关闭其它标签页</a>
								</dd>
								<dd layadmin-event="closeAllTabs">
									<a href="javascript:;">关闭全部标签页</a>
								</dd>
							</dl></li>
					</ul>
				</div>
				<div class="layui-tab" lay-unauto lay-allowClose="true"
					lay-filter="layadmin-layout-tabs">
					<ul class="layui-tab-title" id="LAY_app_tabsheader">
						<li id="idTab" lay-id="" lay-attr="" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
					</ul>
				</div>
			</div>


			<!-- 主体内容 -->
			<div class="layui-body" id="LAY_app_body">
				<div class="layadmin-tabsbody-item layui-show">
					<iframe id="idIframe" src="" frameborder="0" class="layadmin-iframe"></iframe>
				</div>
			</div>

			<!-- 辅助元素，一般用于移动设备下遮罩 -->
			<div class="layadmin-body-shade" layadmin-event="shade"></div>
		</div>
	</div>

	<script>
		layui.config({
			base : 'plugins/layui/layuiadmin/' //静态资源所在路径
		}).extend({
			index : 'lib/index' //主入口模块
		}).use('index');

		layui.use('element', function() {
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

			//监听导航点击
			element.on('nav(demo)', function(elem) {
				layer.msg(elem.text());
			});
		});
		$(document).ready(function() {
			menus("总体概况");
			var oldSrc = "";
			$('.topBtn a').on({
				mouseover : function() {
					//新建数组
					//获取当前图片路径
					oldSrc = $(this).children('img').attr('src').toString();
					//获取需要修改的图片名称
					var name = $(this).attr('class');
					//修改图片名称
					var src = '/flycheck' + name;
					$(this).children('img').attr('src', src);
				},
				mouseout : function() {
					//修改图片路径
					$(this).children('img').attr('src', oldSrc);
				}
			});
		});
		//其他菜单
		function menus(parentMenusName) {
			$.ajax({
				async : false, //是否异步
				cache : false, //是否使用缓存
				type : 'POST', //请求方式：post
				url : $WEB_ROOT_PATH + '/dhccApi/menu/menu/listTree',//非8大目录请求的路径
				data : {
					"menu.menuName" : parentMenusName
				},
				success : function(data) {
					treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
					var menuList = treeNodes[0].children;
					$("#idTab").attr("lay-id",$WEB_ROOT_PATH + menuList[0].menuUrl);
					$("#idTab").attr("lay-attr",$WEB_ROOT_PATH + menuList[0].menuUrl);
					$("#idIframe").attr("src",$WEB_ROOT_PATH + menuList[0].menuUrl);
					if (menuList.length > 0) {
						for (var i = 0; i < menuList.length; i++) {
							if (menuList[i].hasChildren == 0) {
								addMenus(menuList[i]);//单菜单拼接
							} else {
								var child = menuList[i].children;
								if (child.length > 0) {
									addMenusChild(menuList[i]);//带子菜单拼接
								}
							}
						}
					}
				}
			});
		}

		//加载下拉菜单
		function addMenus(menu) {
			var ul = document.getElementById("ul");
			var li = document.createElement("li");
			li.setAttribute("class", "layui-nav-item");
			var a = document.createElement("a");
			a.setAttribute("href", "javascript:;");
			a.setAttribute("lay-href", $WEB_ROOT_PATH + menu.menuUrl);
			a.setAttribute("lay-tips", menu.menuName);
			a.setAttribute("class", menu.onclickAft);//class为点击后图片地址
			//a标签中拼接img图标
			var img = document.createElement("img");
			img.setAttribute("class", "btnImg");
			img.setAttribute("src", $WEB_ROOT_PATH + menu.onclickBef);//点击前图片地址
			//a.innerHTML =menu.menuName;
			a.appendChild(img);
			var span = document.createElement("span");
			span.innerHTML = menu.menuName;
			a.appendChild(span);
			//a.innerHTML =menu.menuName;
			li.appendChild(a);
			ul.appendChild(li);
		}
		function addMenusChild(menu) {
			var ul = document.getElementById("ul");
			var li = document.createElement("li");
			li.setAttribute("class", "layui-nav-item");
			var a = document.createElement("a");
			a.setAttribute("href", "javascript:;");
			a.setAttribute("class", menu.onclickAft);
			//a标签中拼接img图标
			var img = document.createElement("img");
			img.setAttribute("class", "btnImg");
			img.setAttribute("src", $WEB_ROOT_PATH + menu.onclickBef);
			a.appendChild(img);
			var span = document.createElement("span");
			span.innerHTML = menu.menuName;
			a.appendChild(span);
			var dl = document.createElement("dl");
			dl.setAttribute("class", "layui-nav-child");

			var child = menu.children;
			for (var i = 0; i < child.length; i++) {
				var dd = document.createElement("dd");
				var a1 = document.createElement("a");
				a1.setAttribute("href", "javascript:;");
				a1.setAttribute("lay-href", $WEB_ROOT_PATH + child[i].menuUrl);
				a1.innerHTML = child[i].menuName;
				dd.appendChild(a1);
				dl.appendChild(dd);
			}
			li.appendChild(a);
			li.appendChild(dl);
			ul.appendChild(li);
		}
	</script>
</body>
</html>

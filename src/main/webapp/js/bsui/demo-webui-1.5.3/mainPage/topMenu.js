/*
 * topMenu.js
 * @auth: lifei
 * @date: 2017/1/12
 */
'use strict';  // javascript严格模式

var mainObjs = {
	leafMenuLev: 3,
	$accountId: $("#accountId"),
	$role: $("#role"),
	$userName: $("#userName"),
	$html: $("html"),
	$body: $("body"),
	$wrapper: $("#wrapper"),
	$mainHead: $("#main_header"),
	$menu: $(".menu"),
	$mainSide: $("#main_sidebar"),
	$menuBtn: $(".menu-btn"),
	$sideMenu: $("#side_menu"),
	$pageWrap: $("#page_wrapper")
}

$.Menu = {};
$.Menu.options = {
	// Activate wrapper slimscroll replace the default body scroll (requires SlimScroll Plugin)
	wrapperSlimScroll: true,
	// 菜单移动速度：三种预定速度之一的字符串("slow","normal" or "fast")或表示动画时长的毫秒数值(如：100)
	speed: 100, //'slow',
	// 每次点击按钮时菜单的偏移量
	offset: 150,
	screenSizes: {
		xs: 480,
		sm: 768,
		md: 992,
		lg: 1200
	}
};

$(function () {
	var data = [/*{
		"text": "数据列表",
		"url": null,
		"imgUrl": "fa fa-folder-open",
		"level":"1",
		"children": [{
			"text": "datagrid",
		 	"url": "childPage/datagrid.jsp",
		 	"imgUrl": "fa fa-file-archive-o",
		 	"level": "2"
		}]
 	}*/];
 	
	// 加载菜单
 	if (data.length) {
 		loadMenu(data);
 	} else{
 		$.ajax({
			type: 'GET',
			url: $WEB_ROOT_PATH+'/js/bsui/demo-webui-1.5.3/mainPage/menu.json',
			data: null,//{'menuDto.role': mainObjs.$role.text()},
			dataType: 'json'
		}).done(function(data) {
			if (data && data.length) {
				loadMenu(data);
			} else {
				$CommonUI.autoCloseCenterMessage('菜单数据未正确配置', null, '温馨提示', 1000);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			$CommonUI.autoCloseCenterMessage(textStatus);
		});
 	}
});

/* 主菜单加载 */
function loadMenu(data) {
	var menuHtml = '',
		recurMenus = (function rm(menus) {
			$.each(menus, function(i, v) {
				/*if (v.url && authoritiesMap && !authoritiesMap[v.url]) {
					return true;
				}*/
				if (v.children && v.children.length) {
					menuHtml += '<li class="treeview">'
							  + '  <a href="javascript:void(0);" class="level-' + v.level + '">'
							  + '    <i class="' + v.imgUrl + ' sidemenu-icon"></i>'
							  + '    <span class="sidemenu-name">' + v.text + '</span> ';
					if ('1' == v.level) {
						menuHtml += '<i class="fa fa-angle-down"></i>';
					} else{
						menuHtml += '<i class="fa fa-angle-right" style="margin-right:17px"></i>';
					}
					menuHtml += '  </a>'
							  + '  <ul class="treeview-menu">'
					rm(v.children);
					menuHtml += '</ul>';
				} else if (v.url) {
					menuHtml += '<li>';
					if (mainObjs.leafMenuLev == v.level) {
						menuHtml += '<a href="javascript:void(0);" class="level-3" src="' + v.url + '">'
							//+ '<i class="fa fa-caret-right"></i>' + v.text + '</a>';
							+ v.text + '</a>';
					} else {
						menuHtml += '<a href="javascript:void(0);" class="level-' + v.level + '" src="' + v.url + '">'
							+ '<i class="' + v.imgUrl + ' sidemenu-icon"></i><span class="sidemenu-name-' + v.level + '">' + v.text + '</span></a>';
					}
				}
				menuHtml += '</li>';
			});
		});
				
	// 递归拼接菜单html
	recurMenus(data);
	mainObjs.$sideMenu.empty().append(menuHtml);
	mainObjs.$sideMenu.find('li:first').addClass('sidemenu-active');
	mainObjs.$sideMenu.find('li.sidemenu-active').find('li:first').addClass('sidemenu-active');
				
	// 给菜单加上单击事件,异步加载及切换子页面
	mainObjs.$sideMenu.find("a[src]").on('click', function(e) {
		var src = $(this).attr('src');
		pageJump(src);
		$("html,body").animate({scrollTop: 0}, 200);
	});
					
	init();
}

// 页面切换
function pageJump(src) {
	if (mainObjs.$wrapper.parent(".slimScrollDiv").length) {
		var $removeDom = mainObjs.$wrapper.parent(".slimScrollDiv").nextAll('div');
		if ($removeDom.length) {
			$removeDom.remove();
		}
	} else {
		var $removeDom = mainObjs.$wrapper.nextAll('div');
		if ($removeDom.length) {
			$removeDom.remove();
		}
	}
	
	/*if(!(/^http/i.test(src))) {
		src = $WEB_ROOT_PATH + src;
	}*/
	mainObjs.$pageWrap.load(src);
}

function init() {
	// 菜单隐藏
	$(document).off(".sidebar").on("click.sidebar", function(e) {
		var t = $(e.target);
		/*if (!t.is(".fa-angle-down,.fa-angle-right") && $(".menu-open").length) {*/
		/*if (!t.is(".fa, .sidemenu-name") && $(".menu-open").length) {*/
		if ((!t.is(".level-2, .fa, .sidemenu-name") || t.is("a")) && $(".menu-open").length) {
			$(".menu-open").removeClass("menu-open").css('display', 'none');
		}
	});
	
	// 加载默认子页面
	var mainUrl = getMainUrl(mainObjs.$sideMenu);
	
	mainObjs.$pageWrap.load(mainUrl, null, function() {
		// 加载菜单插件
		if (typeof MenuOptions !== "undefined") {
			$.extend(true, $.Menu.options, MenuOptions);
		}
		var o = $.Menu.options;
		
		_init();
		
		if (typeof $.fn.slimscroll != 'undefined') {
			var bodyHeight = mainObjs.$body.height(),
				winHeight = $(window).height(),
				height = bodyHeight > winHeight ? bodyHeight : winHeight;
			mainObjs.$menu.slimscroll({
				height: "200px",
				alwaysVisible: false,
				color: "rgba(0,0,0,0.3)",
				size: "3px"
			}).css("width", "100%");
			
			if (o.wrapperSlimScroll) {
				mainObjs.$wrapper.slimscroll({
					height: height + "px",
					alwaysVisible: false,
					size: "3px"
				}).css("width", "100%");
			}
		}
		
		//Enable sidebar tree view controls
		$.Menu.tree('.sidebar-menu');
	});
	
	function getMainUrl(obj) {
		var url = obj.find('li:first').children("a").attr('src');
		if (!url || "null"==url) {
			url = getMainUrl(obj.find('li:first'));
		}
		return url;
	}
}

function _init() {
	/*
	 * Tree() ====== Converts the sidebar into a multilevel tree view menu.
	 * @type Function @Usage: $.Menu.tree('.sidebar')
	 */
	$.Menu.tree = function (menu) {
		var _this = this,
			speed = _this.options.speed,
			offset = _this.options.offset,
			winWidth = $(window).width(),
			menuUlWidth = mainObjs.$sideMenu.width(),
			menuWidth = 0;
			
		mainObjs.$sideMenu.contents().each(function() {
			menuWidth += $(this).width();
		});
		
		if (menuUlWidth < menuWidth) {
			var outside = menuWidth - winWidth + 60;
		
			mainObjs.$menuBtn.on('click.move', function(e) {
				var t = $(e.target),
					right = parseInt(mainObjs.$sideMenu.css('right')),
					left = parseInt(mainObjs.$sideMenu.css('left'));
				if (isNaN(right) && isNaN(left)) {
					right = 0;
					left = 0;
				} else if (isNaN(right) && !isNaN(left)) {
					right = -left;
				}
				if (t.is(".fa-chevron-left") && outside > right) {
					mainObjs.$sideMenu.animate({left: "-="+offset}, speed);
				} else if (t.is(".fa-chevron-right") && 0 != left) {
					mainObjs.$sideMenu.animate({left: "+="+offset}, speed);
				}
			});
		} else {
			mainObjs.$menuBtn.children().hide();
		}

	    $("li a", $(menu)).on('click', function (e) {
	    	//Get the clicked link and the next element
	    	var $this = $(this);
	    	var checkElement = $this.next();

	    	//Check if the next element is a menu and is visible
	    	if ((checkElement.is('.treeview-menu')) && (checkElement.is(':visible'))) {
	    		var parent_li = $this.parent("li");
	    		//Close the menu
	    		checkElement.slideUp('normal', function () {
	    			checkElement.removeClass('menu-open');
	    			parent_li.removeClass('sidemenu-active');
	    		});
	    		//checkElement.parent("li").removeClass("sidemenu-active");
	    	} else if ((checkElement.is('.treeview-menu')) && (!checkElement.is(':visible'))) {
	    		//If the menu is not visible, Get the parent menu
	    		var parent = $this.parents('ul').first();
	    		//Close all open menus within the parent
	    		var ul = parent.find('ul:visible').slideUp('normal');
	    		//Remove the menu-open class from the parent
	    		ul.removeClass('menu-open');
	    		//Get the parent li
	    		var parent_li = $this.parent("li");

	    		//Open the target menu and add the menu-open class
	    		if (checkElement.children().length) {
	    			checkElement.slideDown('normal', function () {
	    				//Add the class active to the parent li
	    				checkElement.addClass('menu-open');
	    				parent.find('li.sidemenu-active').removeClass('sidemenu-active');
	    				parent_li.addClass('sidemenu-active');
	    			});
	    		}
	    	} else if (checkElement.length == 0) {
	    		//If the menu is a 2nd level menu and has't sub menu, or it's a 3rd level menu
	    		//Get the parent menu
	            var parent = $this.parents('ul').first();
	            //Close all open menus within the parent
	            var ul = parent.find('ul:visible').slideUp('normal');
	            //Remove the menu-open class from the parent
	            ul.removeClass('menu-open');
	            //Get the parent li
	            var parent_li = $this.parent("li");
	            
	            parent.find('li.sidemenu-active').removeClass('sidemenu-active');
	          	parent_li.addClass('sidemenu-active');
	    	}
	    	
	    	//if this isn't a link, prevent the page from being redirected
	    	if (checkElement.is('.treeview-menu')) {
	    		e.preventDefault();
	    	}
	    });
	};

}

// session超时提示，并退出登录
(function() {
	var _AJAX_RESCODE = {
		5004: function(res) {
			$CommonUI.showModalMessage(res.responseText);
			setTimeout(function() {
				// 通过cas单点登录，退出时调此url
				window.location.href = $.WEBROOT + '/logout/cas';
	        }, 1550);
		},
		403: function(res) {
			$CommonUI.showModalMessage("访问超时，即将退出系统，请重新登录！", 'warning', '会话超时提示', 2000, true);
			setTimeout(function() {
				window.location.href = $.WEBROOT + '/logout/cas';
	        }, 1550);
		}
	};
	
	$.ajaxSetup({
		statusCode : _AJAX_RESCODE
	});
})();
//# sourceURL=topMenu.js
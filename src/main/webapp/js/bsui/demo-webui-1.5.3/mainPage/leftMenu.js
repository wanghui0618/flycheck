/*
 * leftMenu.js
 * @auth: lifei
 * @date: 2017/1/12
 */
'use strict';  // javascript严格模式

var mainObjs = {
	leafMenuLev: 3,
	$modifyPwWin: $CommonUI.getWindow("#modifyPwWin"),
	$modifyPwForm: $CommonUI.getForm("#modifyPwForm"),
	$modifyPwWinFooter: $("#modifyPwWinFooter"),
	$accountId: $("#accountId"),
	$role: $("#role"),
	$userName: $("#userName"),
	$html: $("html"),
	$body: $("body"),
	$wrapper: $("#wrapper"),
	$mainHead: $("#main_header"),
	$menu: $(".menu"),
	$mainSide: $("#main_sidebar"),
	$sideBar: $("#sidebar"),
	$menuHead: $(".menu-header"),
	$sideMenu: $("#side_menu"),
	$pageWrap: $("#page_wrapper")
}

$.Menu = {};
$.Menu.options = {
	sidebarToggleSelector: "[data-toggle='offcanvas']",
	// Activate sidebar push menu
	sidebarPushMenu: true,
	// Activate wrapper slimscroll replace the default body scroll (requires SlimScroll Plugin)
	wrapperSlimScroll: true,
	// Activate sidebar slimscroll (requires SlimScroll Plugin)
	sidebarSlimScroll: true,
	// Enable sidebar expand on hover effect for sidebar mini
	// This option is forced to true if both the fixed layout and sidebar mini are used together
	sidebarExpandOnHover: true,
	screenSizes: {
		xs: 480,
		sm: 768,
		md: 992,
		lg: 1200
	}
};

$(function () {
	$.extend($.fn.validatebox.defaults.rules, {
	    equals: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '两次输入的密码不一致！'
	    },
	    password: {
	    	validator: function(value, param) {
	    		if (/^(?=.*\d)(?=.*[a-zA-Z]).{6,16}$/.test(value)) {
	        		return true;
	        	} else {
	        		return false;
	        	}
	        },
	        message: '6-16个字符，字母、数字和特殊字符至少包含2种！'
	    }
	});
	
	setTimeout(function() {
		mainObjs.$modifyPwWinFooter.css('visibility', 'visible');
	}, 50);
	
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
 	} else {
 		$.ajax({
			type: 'GET',
			url: 'menu.json',
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
							  + '    <span class="sidemenu-name">' + v.text + '</span> '
							  + '    <i class="fa fa-angle-right pull-right"></i>'
							  + '  </a>'
							  + '  <ul class="treeview-menu">';
					rm(v.children);
					menuHtml += '</ul>';
				} else if (v.url) {
					menuHtml += '<li>';
					if (mainObjs.leafMenuLev == v.level) {
						menuHtml += '<a href="javascript:void(0);" class="level-3" src="' + v.url + '">'
								  //+ '  <i class="fa fa-caret-right"></i>' + v.text
								  + v.text 
								  + '</a>';
					} else {
						menuHtml += '<a href="javascript:void(0);" class="level-' + v.level + '" src="' + v.url + '">'
								  + '  <i class="' + v.imgUrl + ' sidemenu-icon"></i>'
								  + '  <span class="sidemenu-name">' + v.text + '</span>'
								  + '</a>';
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
				
	// 给菜单加上单击事件
	mainObjs.$sideMenu.find("a[src]").on('click', function(e) {
		var src = $(this).attr('src');
		pageJump(src);
		$("html,body").animate({scrollTop: 0}, 200);
	});
					
	init();
}

// 页面切换
function pageJump(src) {
	var $modifyPwWin = mainObjs.$modifyPwWin.closest("div.window"),
		$mask = $modifyPwWin.next('div.window-mask');
	if (mainObjs.$wrapper.parent(".slimScrollDiv").length) {
		var $removeDom = mainObjs.$wrapper.parent(".slimScrollDiv").nextAll('div').not($modifyPwWin).not($mask);
		if ($removeDom.length) {
			$removeDom.remove();
		}
	} else {
		var $removeDom = mainObjs.$wrapper.nextAll('div').not($modifyPwWin).not($mask);
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
			var winHeight = $(window).height(),
				scrollHeight = winHeight - mainObjs.$mainHead.height() - mainObjs.$menuHead.height();
			
			mainObjs.$menu.slimscroll({
				height: "200px",
				alwaysVisible: false,
				color: "rgba(0,0,0,0.3)",
				size: "3px"
			}).css("width", "100%");
			
			if (o.wrapperSlimScroll) {
				mainObjs.$wrapper.slimscroll({
					height: winHeight + "px",
					alwaysVisible: false,
					size: "3px"
				}).css("width", "100%");
			}
			if (o.sidebarSlimScroll) {
				mainObjs.$sideMenu.slimscroll({
					height: scrollHeight + "px",
					color: "rgba(255,255,255,0.7)",
					size: "3px"
				});
			}
		}
		
		//Enable sidebar tree view controls
		$.Menu.tree('.sidebar');
		
		if (o.sidebarPushMenu) {
		    $.Menu.pushMenu.activate(o.sidebarToggleSelector);
		}
	});
	
	function getMainUrl(obj) {
		var url = obj.find('li:first').children("a").attr('src');
		if (!url || "null"==url) {
			url = getMainUrl(obj.find('li:first'));
		}
		return url;
	}
}

// 打开修改密码弹窗
function showPasswordWin() {
	$CommonUI.getWindow("#modifyPwWin").window('open');
}
	
function modifyPassword() {
	var valid = $CommonUI.getForm("#modifyPwForm").form('validate');
	if (!valid) {
		return;
	}
		
	$CommonUI.getWindow("#modifyPwWin").window('close');
	$CommonUI.notify('密码修改成功！');
}

function resetPassword() {
	$CommonUI.getForm("#modifyPwForm").form('reset');
	$CommonUI.notify('数据重置成功！', {type: 'success'});
}

function _init() {
	/*
	 * PushMenu() ========== Adds the push menu functionality to the sidebar.
	 * @type Function @usage: $.Menu.pushMenu("[data-toggle='offcanvas']")
	 */
	$.Menu.pushMenu = {
		activate: function(toggleBtn) {
			// Get the screen sizes
			var _this = this;
			var screenSizes = $.Menu.options.screenSizes;

			// Enable sidebar toggle
			$(toggleBtn).on('click', function(e) {
				e.preventDefault();
				
				// Enable sidebar push menu
				if ($(window).width() > (screenSizes.sm - 1)) {
					mainObjs.$body.toggleClass('sidebar-collapse');
				} else {
					// Handle sidebar push menu for small screens
					if (mainObjs.$body.hasClass('sidebar-open')) {
						mainObjs.$body.removeClass('sidebar-open sidebar-collapse');
					} else {
						mainObjs.$body.addClass('sidebar-open');
					}
				}
				
				_this.datagridResize();
				/*setTimeout(function() {
					$.bsui.throttle(adaption);
				function adaption() {
					$CommonUI.getDataGrid("#dg").datagrid('resize');
				}
				}, 80);*/
			});
			
			// Enable hide menu when clicking on the content-wrapper on small screens
			mainObjs.$pageWrap.click(function() {
				if ($(window).width() <= (screenSizes.sm - 1)
						&& mainObjs.$body.hasClass("sidebar-open")) {
					mainObjs.$body.removeClass('sidebar-open');
				}
			});

			// Enable expand on hover for sidebar mini
			if ($.Menu.options.sidebarExpandOnHover && mainObjs.$body.hasClass('sidebar-mini')) {
				this.expandOnHover();
			}
			
			/*mainObjs.$sideMenu.off('.push').on('click.push', function() {
				var screenWidth = $.Menu.options.screenSizes.sm - 1;
				
				if (mainObjs.$body.hasClass('sidebar-mini')) {
					if (mainObjs.$body.hasClass('sidebar-collapse')
						&& $(window).width() > screenWidth) {
						_this.expand();
					} else if (mainObjs.$body.hasClass('sidebar-expanded-on-hover')
						&& $(window).width() > screenWidth) {
						_this.collapse();
					}
				}
			});*/
		},
		expandOnHover: function() {
			var _this = this;
			var screenWidth = $.Menu.options.screenSizes.sm - 1;
			// Expand sidebar on hover
			mainObjs.$mainSide.hover(
				function(e) {
					if ($(e.target).is(".menu-header")) {
						return;
					}
					if (mainObjs.$body.hasClass('sidebar-mini') && mainObjs.$body.hasClass('sidebar-collapse')
							&& $(window).width() > screenWidth) {
						_this.expand();
						_this.datagridResize();
					}
				},
				function(e) {
					if ($(e.target).is(".menu-header")) {
						return;
					}
					if (mainObjs.$body.hasClass('sidebar-mini') && mainObjs.$body.hasClass('sidebar-expanded-on-hover')
							&& $(window).width() > screenWidth) {
						_this.collapse();
						_this.datagridResize();
					}
				}
			);
		},
		expand: function() {
			mainObjs.$body.removeClass('sidebar-collapse').addClass('sidebar-expanded-on-hover');
		},
		collapse: function() {
			if (mainObjs.$body.hasClass('sidebar-expanded-on-hover')) {
				mainObjs.$body.removeClass('sidebar-expanded-on-hover').addClass('sidebar-collapse');
			}
		},
		datagridResize: function() {
			clearTimeout(timer);
			var timer = setTimeout(function() {
				$('.panel-body', mainObjs.$pageWrap).panel('resize');
			}, 301);
		}
	};

	/*
	 * Tree() ====== Converts the sidebar into a multilevel tree view menu.
	 * @type Function @Usage: $.Menu.tree('.sidebar')
	 */
	$.Menu.tree = function (menu) {
		var _this = this;

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
	    		checkElement.slideDown('normal', function () {
	    			//Add the class active to the parent li
	    			checkElement.addClass('menu-open');
	    			parent.find('li.sidemenu-active').removeClass('sidemenu-active');
	    			parent_li.addClass('sidemenu-active');
	    		});
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
//# sourceURL=leftMenu.js
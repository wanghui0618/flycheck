/*
 * main.js
 * @auth: lifei
 * @date: 2017/1/7
 */
'use strict';  // javascript严格模式

var mainObjs = {
	leafMenuLev: 3,
	tableHeight: window.innerHeight -245, // 表格高度
	$html: $("html"),
	$body: $("body"),
	$accountId: $("#accountId"),
	$role: $("#role"),
	$userName: $("#userName"),
	$mainHead: $("#main_header"),
	$mainSide: $("#main_sidebar"),
	$sideBar: $("#sidebar"),
	$sideMenu: $("#side_menu"),
	$contentWrap: $("#content_wrapper")
}

$.Menu = {};
$.Menu.options = {
	sidebarToggleSelector: "[data-toggle='offcanvas']",
	sidebarFixed: true,
	// Activate sidebar push menu
	sidebarPushMenu: true,
	// Activate sidebar slimscroll if the fixed layout is set (requires SlimScroll Plugin)
	sidebarSlimScroll: true,
	// Enable sidebar expand on hover effect for sidebar mini
	// This option is forced to true if both the fixed layout and sidebar mini are used together
	sidebarExpandOnHover: false,
	// Control Sidebar Options
	enableControlSidebar: true,
	controlSidebarOptions: {
		// Which button should trigger the open/close event
		toggleBtnSelector: "[data-toggle='control-sidebar']",
		// The sidebar selector
		selector: ".control-sidebar",
		// Enable slide over content
		slide: true
	},
	screenSizes: {
		xs: 480,
		sm: 768,
		md: 992,
		lg: 1200
	}
};

$(function () {
	if ($.Menu.options.sidebarFixed) {
		mainObjs.$body.addClass("fixed");
	}
	
	// 加载菜单
	loadMenu();
});

/* 主菜单加载 */
function loadMenu() {
	$.ajax({
		type: 'GET',
		url: 'menu.json',
		data: null,//{'menuDto.role': mainObjs.$role.text()},
		dataType: 'json'
	}).done(function(data) {
		if (data) {
			var menuHtml = '',
				recurMenus = (function rm(menus) {
					$.each(menus, function(i, v) {
						if (v.children && v.children.length) {
							menuHtml += '<li class="treeview"><a href="javascript:void(0);" class="level-' + v.level + '">'
								+ '<i class="' + v.imgUrl + '"></i><span class="sidemenu-name-'+v.level+'">' + v.text + '</span> '
								+ '<i class="fa fa-angle-left pull-right"></i></a><ul class="treeview-menu">';
							rm(v.children);
							menuHtml += '</ul>';
						} else if (v.url) {
							menuHtml += '<li>';
							if (mainObjs.leafMenuLev == v.level) {
								menuHtml += '<a href="javascript:void(0);" class="level-3 triangle" src="' + v.url + '">' + v.text + '</a>';
							} else {
								menuHtml += '<a href="javascript:void(0);" class="level-' + v.level + '" src="' + v.url + '">'
									+ '<i class="' + v.imgUrl + '"></i><span class="sidemenu-name-' + v.level + '">' + v.text + '</span></a>';
							}
						}
						menuHtml += '</li>';
					});
				});
				
				// 递归拼接菜单html
				recurMenus(data);
				mainObjs.$sideMenu.empty().append(menuHtml);
				mainObjs.$sideBar.find('ul:first').find('li:first').addClass('sidemenu-active');
				mainObjs.$sideBar.find('li.sidemenu-active').find('li:first').addClass('sidemenu-active');
				
				// 给菜单加上单击事件
				mainObjs.$sideMenu.find("a[src]").on('click', function(e) {
					var src = $(this).attr('src');
					mainObjs.$contentWrap.load(src, null, function() {
						if (!mainObjs.$body.hasClass("fixed")) {
							$.Menu.layout.fix();
						}
					});
					$("html,body").animate({scrollTop: 0}, 200);
				});
					
				init();
			} else {
				$CommonUI.autoCloseCenterMessage('菜单数据未正确配置', null, '温馨提示', 1000);
			}
		}
	).fail(function(xhr, textStatus, errorThrown) {
		$CommonUI.autoCloseCenterMessage(textStatus);
	});
}

function init() {
	// 加载默认子页面
	var mainUrl = getMainUrl(mainObjs.$sideMenu);
	
	mainObjs.$contentWrap.load(mainUrl, null, function() {
		// 加载菜单插件
		if (typeof MenuOptions !== "undefined") {
			$.extend(true, $.Menu.options, MenuOptions);
		}
		var o = $.Menu.options;
		
		_init();
		//Activate the layout maker
		$.Menu.layout.activate();
		//Enable sidebar tree view controls
		$.Menu.tree('.sidebar');
		//Enable control sidebar
		if (o.enableControlSidebar) {
	    	$.Menu.controlSidebar.activate();
	  	}
		if (o.sidebarPushMenu) {
		    $.Menu.pushMenu.activate(o.sidebarToggleSelector);
		}
		
		if (!mainObjs.$body.hasClass("fixed")) {
			$(".nav").off(".resize").on("click.resize", function() {
				$.Menu.layout.fix();
			});
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

function _init() {
	/*
	 * Layout ====== Fixes the layout height in case min-height fails.
	 * @type Object @usage $.Menu.layout.activate() $.Menu.layout.fix()
	 * $.Menu.layout.fixSidebar()
	 */
	$.Menu.layout = {
		activate: function() {
			var _this = this;
			_this.fix();
			_this.fixSidebar();
		},
		fix: function() {
			if ($.Menu.options.sidebarFixed) {
				return;
			}
			// Get window height and the wrapper height
			var neg = mainObjs.$mainHead.outerHeight(),
				window_height = $(window).height(),
				sidebar_height = mainObjs.$sideBar.height(),
				content_height = mainObjs.$contentWrap.outerHeight() + neg,
				postSetWidth = Math.max.apply(null, [window_height, sidebar_height, content_height]);
			// Set the min-height of the content and sidebar based on the the height of the document.
			mainObjs.$mainSide.css('min-height', postSetWidth);

			// Fix for the control sidebar height
			var controlSidebar = $($.Menu.options.controlSidebarOptions.selector);
			if (typeof controlSidebar !== "undefined") {
				if (controlSidebar.height() > postSetWidth)
					mainObjs.$contentWrap.css('min-height', controlSidebar.height());
			}
		},
		fixSidebar: function() {
			// Make sure the body tag has the .fixed class
			if (!mainObjs.$body.hasClass("fixed")) {
				if (typeof $.fn.slimScroll != 'undefined') {
					mainObjs.$sideBar.slimScroll({destroy: true});
				}
				return;
			} else if (typeof $.fn.slimScroll == 'undefined' && console) {
				console.error("Error: the fixed layout requires the slimscroll plugin!");
			}
			// Enable slimscroll for fixed layout
			if ($.Menu.options.sidebarSlimScroll) {
				if (typeof $.fn.slimScroll != 'undefined') {
					// Destroy if it exists
					mainObjs.$sideBar.slimScroll({destroy: true}).height("auto");
					// Add slimscroll
					mainObjs.$sideBar.slimscroll({
						height: ($(window).height() - mainObjs.$mainHead.height()) + "px",
						color: "rgba(255,255,255,0.7)",
						size: "3px"
					});
				}
			}
		}
	};

	/*
	 * PushMenu() ========== Adds the push menu functionality to the sidebar.
	 * @type Function @usage: $.Menu.pushMenu("[data-toggle='offcanvas']")
	 */
	$.Menu.pushMenu = {
		activate: function(toggleBtn) {
			// Get the screen sizes
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
			});
			
			// Enable hide menu when clicking on the content-wrapper on small screens
			mainObjs.$contentWrap.click(function() {
				if ($(window).width() <= (screenSizes.sm - 1)
						&& mainObjs.$body.hasClass("sidebar-open")) {
					mainObjs.$body.removeClass('sidebar-open');
				}
			});

			// Enable expand on hover for sidebar mini
			if ($.Menu.options.sidebarExpandOnHover || (mainObjs.$body.hasClass('fixed') 
					&& mainObjs.$body.hasClass('sidebar-mini'))) {
				this.expandOnHover();
			}

		},
		expandOnHover: function() {
			var _this = this;
			var screenWidth = $.Menu.options.screenSizes.sm - 1;
			// Expand sidebar on hover
			mainObjs.$mainSide.hover(
				function() {
					if (mainObjs.$body.hasClass('sidebar-mini') && mainObjs.$body.hasClass('sidebar-collapse')
							&& $(window).width() > screenWidth) {
						_this.expand();
					}
				},
				function() {
					if (mainObjs.$body.hasClass('sidebar-mini') && mainObjs.$body.hasClass('sidebar-expanded-on-hover')
							&& $(window).width() > screenWidth) {
						_this.collapse();
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
	    			//Fix the layout in case the sidebar stretches over the height of the window
	    			//_this.layout.fix();
	    		});
	    		//checkElement.parent("li").removeClass("sidemenu-active");
	    	} else if ((checkElement.is('.treeview-menu')) && (!checkElement.is(':visible'))) {
	    		//If the menu is not visible
	    		//Get the parent menu
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
	    			//Fix the layout in case the sidebar stretches over the height of the window
	    			//_this.layout.fix();
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
	          	//Fix the layout in case the sidebar stretches over the height of the window
	            //_this.layout.fix();
	    	}
	    	
	    	//if this isn't a link, prevent the page from being redirected
	    	if (checkElement.is('.treeview-menu')) {
	    		e.preventDefault();
	    	}
	    });
	};

	/*
	 * ControlSidebar ============== Adds functionality to the right sidebar
	 * @type Object @usage $.Menu.controlSidebar.activate(options)
	 */
	$.Menu.controlSidebar = {
		// instantiate the object
		activate: function() {
			// Get the object
			var _this = this;
			// Update options
			var o = $.Menu.options.controlSidebarOptions;
			// Get the sidebar
			var sidebar = $(o.selector);
			// The toggle button
			var btn = $(o.toggleBtnSelector);

			// Listen to the click event
			btn.on('click', function(e) {
				e.preventDefault();
				// If the sidebar is not open
				if (!sidebar.hasClass('control-sidebar-open') && !mainObjs.$body.hasClass('control-sidebar-open')) {
					// Open the sidebar
					_this.open(sidebar, o.slide);
				} else {
					_this.close(sidebar, o.slide);
				}
			});

			// If the body has a boxed layout, fix the sidebar bg position
			var bg = $(".control-sidebar-bg");
			_this._fix(bg);

			// If the body has a fixed layout, make the control sidebar fixed
			if (mainObjs.$body.hasClass('fixed')) {
				_this._fixForFixed(sidebar);
			} else {
				// If the content height is less than the sidebar's height, force max height
				if (mainObjs.$contentWrap.height() < sidebar.height()) {
					_this._fixForContent(sidebar);
				}
			}
		},
		// Open the control sidebar
		open: function(sidebar, slide) {
			//var _this = this;
			// Slide over content
			if (slide) {
				sidebar.addClass('control-sidebar-open');
			} else {
				// Push the content by adding the open class to the body instead of the sidebar itself
				mainObjs.$body.addClass('control-sidebar-open');
			}
		},
		// Close the control sidebar
		close: function(sidebar, slide) {
			if (slide) {
				sidebar.removeClass('control-sidebar-open');
			} else {
				mainObjs.$body.removeClass('control-sidebar-open');
			}
		},
		_fix: function(sidebar) {
			var _this = this;
			if (mainObjs.$body.hasClass('layout-boxed')) {
				sidebar.css('position', 'absolute');
				//sidebar.height($(".wrapper").height());
				sidebar.height(mainObjs.$contentWrap.height());
				$(window).resize(function() {
					_this._fix(sidebar);
				});
			} else {
				sidebar.css({'position':'fixed', 'height':'auto'});
			}
		},
		_fixForFixed: function(sidebar) {
			sidebar.css({
				'position': 'fixed',
				'max-height': '100%',
				'overflow': 'auto',
				'padding-bottom': '50px'
			});
		},
		_fixForContent: function(sidebar) {
			mainObjs.$contentWrap.css('min-height', sidebar.height());
		}
	};

}

// session超时提示，并退出登录
(function() {
	var _AJAX_RESCODE = {
		5004: function(res) {
			$CommonUI.showModalMessage(res.responseText);
			setTimeout(function() {
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
//# sourceURL=main.js
/**
 * 公共UI组件
 * @author 张磊
 * @since 2013-7-18
 * @class
 */

function CommonUI(option){
	/**
	 * 版本
	 */
	this.version = '1.5.3.4';
	/**
	 * 选项
	 */
	this.option = option;
	
	/**
	 * 初始化完成标志
	 */
	this._INIT_COMPLETED = false;

	/**
	 * 对话框ID
	 */
	this._DIALOG_ID = "BSUI_DIALOG";
	
	/**
	 * 默认语言
	 */
	this._LANG = "zh_CN";
	
	/**
	 * 是否开启ajax_debug功能
	 */
	this._AJAX_DEBUG = false;
	
	/**
	 * 是否开启AJAX成功提示
	 */
	this._AJAX_SUCC = false;
	
	/**
	 * 是否开启AJAX错误提示
	 */
	this._AJAX_ERROR = false;
	
	/**
	 * 是否显示加载动画遮罩
	 */
	this._SHOW_LOADING = true;
	
	/**
	 * AJAX服务器响应代码
	 */
	this._AJAX_RESCODE = {
		401: function(res) {
			$CommonUI.alert(res.statusText, 'error', res.status);
		},
		403: function(res) {
			$CommonUI.alert(res.statusText, 'error', res.status);
		},
		404: function(res) {
			$CommonUI.alert(res.statusText, 'error', res.status);
		},
		500: function(res) {
			$CommonUI.alert(res.statusText, 'error', res.status);
		},
		503: function(res) {
			$CommonUI.alert(res.statusText, 'error', res.status);
		},
		504: function(res) {
			$CommonUI.alert(res.statusText, 'error', res.status);
		},
		599: function(res) {
			$CommonUI.alert(res.responseText, 'error', 500);
		},
		4003: function(res) {
			$CommonUI.alert(res.responseText, 'error', 500);
		},
		5003: function(res) {
			$CommonUI.alert(res.responseText, 'error', 500);
		},
		5004: function(res) {
			$CommonUI.alert(res.responseText, 'error', 500);
		}
	};
	
	/**
	 * 公用字典服务的URL
	 */
	this._PUBLIC_DICT_SERVICE_URL = '';
	
	/**
	 * 自定义字典服务的URL
	 */
	this._CUSTOM_DICT_SERVICE_URL = '';

	/**
	 * 默认自动关闭时间(毫秒)
	 */
	this._DEFAULT_AUTO_CLOSE_TIME = 1500;

	/**
	 * 默认消息标题
	 */
	this._DEFAULT_TITLE_MESSAGE = "消息";

	/**
	 * 默认确认框标题
	 */
	this._DEFAULT_TITLE_CONFIRM = "请确认";

	/**
	 * 默认消息提示类型
	 */
	this._DEFAULT_ALERT_TYPE_MESSAGE = null;

	/**
	 * 默认确认提示类型
	 */
	this._DEFAULT_CONFIRM_TYPE_MESSAGE = "question";

	/**
	 * 默认对话框提示类型
	 */
	this._DEFAULT_DIADIOG_TYPE_MESSAGE = "question";

	/**
	 * 默认ok按钮文本
	 */
	this._DEFAULT_OK_BUTTON_TEXT = "确定";

	/**
	 * 默认cancel按钮文本
	 */
	this._DEFAULT_CANCEL_BUTTON_TEXT = "取消";

	/**
	 * 日期格式
	 */
	this._DEFAULT_DATE_FORMAT = "yyyy-mm-dd";

	/**
	 * 默认日期选择器的class
	 */
	this._DEFAULT_DATEPICKER_CLASS = "datepicker";

	/**
	 * jqGrid的默认文本的class
	 */
	this._DEFAULT_TEXT_CLASS = "defaultText";
	
	/**
	 * 默认datagrid的选项
	 */
	this._DEFAULT_DATAGRID_OPTIONS = {
		title:'',      			// 标题
		autoRowHeight: false,   // 自动行高
	    // 翻页工具
	    pagePosition: 'bottom', // 翻页位置
	    pageList: [10,15,20,50,100], // 可选翻页列表
	    striped: true, // 行间是否条纹间隔
	    // 显示表信息
	    showHeader: true, 		// 显示表头
//	    showFooter: true, 		// 显示表尾
	    rownumbers: true, 		// 显示行号 
	    // 加载错误处理函数
		onLoadError: function(error){
			$CommonUI.autoCloseCenterMessage("加载数据失败");
		}
	};

	/**
	 * 默认皮肤名称
	 */
	this._BSUI_DEFAULT_THEMES = 'dhcc-ui';

	/**
	 * 皮肤在cookie里保存的天数
	 */
	this._BSUI_THEMES_DELAY = 0;

	/**
	 * 皮肤在cookie里变量名
	 */
	this._BSUI_THEMES_CNAME = '__current_theme';

	/**
	 * 默认colorbox的选项
	 */
	this._DEFAULT_COLORBOX_OPTIONS = {
			
	};

	/**
	 * 默认poshytip选项
	 */
	this._DEFAULT_POSHYTIP_OPTIONS = {
		className: 'tip-yellow',
		showOn: 'focus',
		alignTo: 'target',
		alignX: 'top',
		offsetX: -125,
		offsetY: 5
	};
	
	/**
	 * CommonUI加载状态
	 */
	this.isLoadingComplete = function(){
		return this._INIT_COMPLETED;
	};

	/**
	 * 加载完成
	 */
	this.loadComplete = function(){
		this._INIT_COMPLETED = true;
	};

	this.getDialogId = function(){
		return this._DIALOG_ID;
	};
	
	/**
	 * style of BodyElement
	 */
	this._styleBodyElement;
	
	/**
	 * use icon fold
	 */
	this._useIcon = function(theme) {
		theme = theme || this._BSUI_DEFAULT_THEMES;
		if(jQuery.inArray(theme, ['dhcc-bs','dhcc-ui']) > -1) {
			includeCSS("/js/bsui/themes/icon-"+theme+".css");
		} else {
			includeCSS("/js/bsui/themes/icon.css");
		}
		includeCSS("/js/bsui/themes/"+theme+"/textbox.css");
	};

	/**
	 * 内部初始化
	 * @author 张磊
	 * @since 2013-7-18
	 */
	this._init = function(){
		if(!this.isLoadingComplete()){
			// 检查是否支持W3C标准盒式模型（IE8以下返回false）
			if (!$.support.boxModel) {
				document.execCommand("BackgroundImageCache",false,true);
			}
			includeCSS("/js/bsui/themes/main.css");
			if (this._SHOW_LOADING) {
				this._styleBodyElement = $(_bodyElement).attr('style') || '';
				$(_bodyElement).addClass('body-mask-big');
				$(_bodyElement).width($(window).width()).height($(window).height());
			}
			var theme = getCookie(this._BSUI_THEMES_CNAME);
			if(!theme) {
				theme = this._BSUI_DEFAULT_THEMES;
			}
			if(this._BSUI_THEMES_DELAY > 0) {
				addCookie(this._BSUI_THEMES_CNAME, 
						theme, this._BSUI_THEMES_DELAY);
			}
			this._useIcon(theme);
			//includeJS("/js/jquery/resize.js");
			includeJS("/js/bsui/plugins/bsui.parser.js");
			includeJS("/js/bsui/webuiloader.js");
			// bsui 语言
			bsuiloader.locale = this._LANG.replace('-','_');
			bsuiloader.base = $WEB_ROOT_PATH + '/js/bsui/';
			// bsui 主题
			bsuiloader.theme = theme;
			// jquery json
			includeJS("/js/jquery/jquery.json-2.4.min.js");
			// update load flag
			this.loadComplete();
		}
	};
	
	/**
	 * 页面局部异步加载初始化方法
	 * @author 李飞
	 * @since 2016-4-8
	 */
	this.asyncInit = function(options) {
		$.parser.parse();
		batCombo();
		//setAuthority(options);
	};
	
	/**
	 * 菜单/按钮权限控制
	 * @param options 扩展选项
	 * 默认值：
	   options = {
	       list: [],  // 权限列表，类型Array
		   display: 'hidden',  // 无权限的菜单节点/按钮显示模式disable or hidden
		   opacity: '0.3',  // disable显示模式下的透明度，默认值0.3(范围0~1)
	   }
	 * @author 李飞
	 * @since 2016-5-9
	 */
	this.setAuthBtnsAppearance = function(options) {
		setAuthority(options);
	};

	/**
	 * 消息提示框，用于替换原生alert
	 * @param message 消息
	 * @param alertType 提示图标类型（可选,应在下列值之间：error|question|info|warning）
	 * @param title 标题（可选，默认为消息）
	 * @param okText （可选，按钮文本）
	 * @param okFunc (可选，确定按钮处理函数)
	 * @param hasCloseCross 有关闭按钮（可选，默认true）
	 * @author 张磊
	 * @since 2013-7-18
	 */
	this.alert = function (message, alertType, title, okText, okFunc, hasCloseCross){
		this.loadUIM('messager');
		var _alertType = alertType ? alertType : this._DEFAULT_ALERT_TYPE_MESSAGE; 
		jQuery.messager.defaults.ok = this._DEFAULT_OK_BUTTON_TEXT;
		if(okText) {
			jQuery.messager.defaults.ok = okText;
		}
		var _title = title ? title : this._DEFAULT_TITLE_MESSAGE; 

		jQuery.messager.alert(_title, message, _alertType, okFunc);
		
		if(!hasCloseCross) {
			jQuery("body > .messager-window:last .panel-tool > .panel-tool-close").remove();
		}
	};

	/**
	 * 消息确认框，用于替换原生confirm
	 * @param message 消息
	 * @param alertType 提示图标类型（可选,应在下列值之间：error|question|info|warning）
	 * @param okText 确定按钮文本（可选，有默认）
	 * @param okFunc 确定按钮处理函数（可选）
	 * @param cancelText 取消按钮文本（可选，有默认）
	 * @param cancelFunc 取消按钮处理函数（可选）
	 * @param title 标题（可选，有默认）
	 * @param hasCloseCross 有关闭按钮（可选，默认true）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.confirm = function (message, alertType, okText, okFunc, cancelText, cancelFunc, title, hasCloseCross){
		this.loadUIM('messager');
		var _alertType = alertType ? alertType : this._DEFAULT_CONFIRM_TYPE_MESSAGE; 
		var _title = null!=title ? title : this._DEFAULT_TITLE_CONFIRM;
		jQuery.messager.defaults.ok = this._DEFAULT_OK_BUTTON_TEXT;
		if(okText) {
			jQuery.messager.defaults.ok = okText;
		}
		jQuery.messager.defaults.cancel = this._DEFAULT_CANCEL_BUTTON_TEXT;
		if(cancelText) {
			jQuery.messager.defaults.cancel = cancelText;
		}
		jQuery.messager.confirm(_title, message, function(rlt){
			if (rlt) { // ok
				if(okFunc) okFunc();
			} else {  // cancel
				if(cancelFunc) cancelFunc();
			}
		});
		if(_alertType) {
			jQuery("body > .messager-window:last > .messager-body > .messager-icon")
				.removeClass("messager-question").addClass("messager-" + alertType);
		}
		if(!hasCloseCross) {
			jQuery("body > .messager-window:last > .panel-heading > .panel-tool > .panel-tool-close").remove();
		}
	};
	
	/**
	 * 文本输入弹窗，用于替换原生prompt
	 * @param message 消息
	 * @param alertType 提示图标类型（可选,应在下列值之间：error|question|info|warning）
	 * @param okText 确定按钮文本（可选，有默认）
	 * @param okFunc 确定按钮处理函数（可选）
	 * @param cancelText 取消按钮文本（可选，有默认）
	 * @param cancelFunc 取消按钮处理函数（可选）
	 * @param title 标题（可选，有默认）
	 * @param hasCloseCross 有关闭按钮（可选，默认true）
	 * @author 李飞
	 * @since 2016-3-9
	 */
	this.prompt = function(message, alertType, okText, okFunc, cancelText, cancelFunc, title, hasCloseCross){
		this.loadUIM('messager');
		var _alertType = alertType ? alertType : this._DEFAULT_CONFIRM_TYPE_MESSAGE; 
		var _title = null!=title ? title : this._DEFAULT_TITLE_CONFIRM;
		jQuery.messager.defaults.ok = this._DEFAULT_OK_BUTTON_TEXT;
		if(okText) {
			jQuery.messager.defaults.ok = okText;
		}
		jQuery.messager.defaults.cancel = this._DEFAULT_CANCEL_BUTTON_TEXT;
		if(cancelText) {
			jQuery.messager.defaults.cancel = cancelText;
		}
		jQuery.messager.prompt(_title, message, function(r){
			if (r) { // ok
				if(okFunc) okFunc();
			} else {  // cancel
				if(cancelFunc) cancelFunc();
			}
		});
		if(_alertType) {
			jQuery("body > .messager-window:last > .messager-body > .messager-icon")
				.removeClass("messager-question").addClass("messager-" + alertType);
		}
		if(!hasCloseCross) {
			jQuery("body > .messager-window:last > .panel-heading > .panel-tool > .panel-tool-close").remove();
		}
	};
	
	/**
	 * 进度条弹窗
	 * @param message 消息
	 * @param options 扩展选项（可选）
	 * @author 李飞
	 * @since 2016-4-15
	 */
	this.progress = function(options){
		this.loadUIM('messager');
		jQuery.messager.progress(options);
	};

	/**
	 * 自动关闭的消息提示框（中间显示）
	 * @param message 消息
	 * @param alertType 提示图标类型（可选,应在下列值之间：error|question|info|warning）
	 * @param title 标题（可选）
	 * @param timeout 自动关闭时间（毫秒，默认1500）
	 * @author 张磊
	 * @since 2013-7-21
	 */
	this.autoCloseCenterMessage = function(message, alertType, title, timeout){
		var _timeout = timeout ? timeout : this._DEFAULT_AUTO_CLOSE_TIME;
		this.showMessage(message, alertType, title, _timeout, true);
	};

	/**
	 * 自动关闭的消息提示框（右下角显示）
	 * @param message 消息
	 * @param title 标题（可选）
	 * @param timeout 自动关闭时间（毫秒，默认1500）
	 * @param showType 显示方式（可选,应在下列值之间：fade|slide|show）
	 * @author 张磊
	 * @since 2013-7-21
	 */
	this.autoCloseRightBottomMessage = function(message, title, timeout, showType){
		this.loadUIM('messager');
		var _showType = showType ? showType : 'slide';
		var _timeout = (timeout || Number(timeout)==0) ? timeout : this._DEFAULT_AUTO_CLOSE_TIME;
		jQuery.messager.show({
			title: title,
			msg: message,
			timeout: _timeout,
			showType:_showType
		});
	};
	
	/**
	 * 自动关闭的消息提示框（自定义显示位置）
	 * @param message 消息
	 * @param title 标题（可选）
	 * @param timeout 自动关闭时间（毫秒，默认1500）
	 * @param showType 显示时的动画效果（可选,应在下列值之间：fade|slide|show）
	 * @param position 显示位置（可选,应在下列值之间：top-left|top-center|top-right|center-left|center|center-right|bottom-left|bottom-center|bottom-right）
	 * @author 李飞
	 * @since 2016-3-9
	 */
	this.autoCloseMessage = function(message, title, timeout, showType, position){
		this.loadUIM('messager');
		var _showType = showType ? showType : 'slide';
		var _timeout = (timeout || Number(timeout)==0) ? timeout : this._DEFAULT_AUTO_CLOSE_TIME;
		var _style;
		switch(position) {
		case 'top-left':
			_style = {
				right:'',
				left:0,
				top:document.body.scrollTop+document.documentElement.scrollTop,
				bottom:''};
			break;
		case 'top-center':
			_style = {right:'',
				top:document.body.scrollTop+document.documentElement.scrollTop,
				bottom:''};
			break;
		case 'top-right':
			_style = {left:'',
				right:0,
				top:document.body.scrollTop+document.documentElement.scrollTop,
				bottom:''};
			break;
		case 'center-left':
			_style = {left:0,
				right:'',
				bottom:''};
			break;
		case 'center':
			_style = {right:'',
				bottom:''};
			break;
		case 'center-right':
			_style = {left:'',
				right:0,
				bottom:''};
			break;
		case 'bottom-left':
			_style = {left:0,
				right:'',
				top:'',
				bottom:-document.body.scrollTop-document.documentElement.scrollTop};
			break;
		case 'bottom-center':
			_style = {right:'',
				top:'',
				bottom:-document.body.scrollTop-document.documentElement.scrollTop};
			break;
		default:
			break;
		}
		
		jQuery.messager.show({
			title: title,
			msg: message,
			timeout: _timeout,
			showType: _showType,
			style: _style
		});
	};

	/**
	 * 显示模态消息
	 * @param message 消息
	 * @param alertType 提示图标类型（可选,应在下列值之间：error|question|info|warning）
	 * @param title 标题（可选，默认为消息）
	 * @param timeout 自动关闭时间（毫秒，默认1500）
	 * @param autoClose 是否自动关闭（可选，默认为false）
	 * @author 张磊
	 * @since 2013-7-21
	 */
	this.showModalMessage = function(message, alertType, title, timeout, autoClose){
		this.showMessage(message, alertType, title, timeout, autoClose, true);
	};
	
	/**
	 * 显示消息（提供高级选项，中间显示）
	 * @param message 消息
	 * @param alertType 提示图标类型（可选,应在下列值之间：error|question|info|warning）
	 * @param title 标题（可选，默认为消息）
	 * @param timeout 自动关闭时间（可选，毫秒，默认1500）
	 * @param autoClose 是否自动关闭（可选，默认为false）
	 * @param modal 是否是模态对话框（可选，默认为false）
	 * @param okText（可选，按钮1文本）
	 * @param okFunc（可选，按下按钮1后的回调函数）
	 * @param cancelText（可选，按钮2文本，设置此参数将创建2个按钮）
	 * @param cancelFunc（可选，按下按钮2后的回调函数，仅当设置了按钮2时才可用）
	 * @param hasCloseCross（可选，有关闭x按钮，默认true）
	 * @author 张磊
	 * @since 2013-7-21
	 */
	this.showMessage = function(message, alertType, title, timeout, autoClose, modal, okText, okFunc, cancelText, cancelFunc, hasCloseCross){
		this.loadUIM('messager');
		var _alertType = alertType ? alertType : this._DEFAULT_DIADIOG_TYPE_MESSAGE;
		var _title = title ? title : this._DEFAULT_TITLE_MESSAGE;
		var _timeout = parseInt(timeout)>0 ? parseInt(timeout) : this._DEFAULT_AUTO_CLOSE_TIME;
		var _modal = null != modal ? modal : false; 
		var _okText = okText ? okText : '';
		var _cancelText = cancelText ? cancelText : '';
		var _hasCloseCross = null != hasCloseCross ? hasCloseCross : true;
		jQuery.messager.defaults.ok = this._DEFAULT_OK_BUTTON_TEXT;
		jQuery.messager.defaults.cancel = this._DEFAULT_CANCEL_BUTTON_TEXT;
		if(cancelText) {
			// two button
			this.confirm(message, _alertType, _okText, okFunc, _cancelText, cancelFunc, _title);
		} else {
			// one button
			this.alert(message, _alertType, _title, _okText, okFunc);
		}
		var win = jQuery("body > .messager-window:last > .messager-body");
		win.window({"modal": _modal});     // 模式窗口, 
		win.window({"draggable" : false}); // 禁止拖动窗口
		win.window({"shadow" : false});    // 去掉阴影
		var msEffect = 200;                // 效果持续时间
		function destroyMsgWin() { win.window('destroy'); }
		function closeCustomWin(ms) {
			try {
				if(!win.window("window")) return;
				if(win) {
					win.window("window").fadeOut(ms);
					setTimeout(function(){ destroyMsgWin(); }, ms);
				}
			} catch (e) {}
		}
		if(null != autoClose && !!autoClose && parseInt(timeout)>0) {
			win.window({"onBeforeClose": function() {
				if(win.timer) {
					clearTimeout(win.timer);
				}
				closeCustomWin(msEffect);
				return false;
			}});
			win.timer=setTimeout(function(){ closeCustomWin(msEffect); }, _timeout);
			win.window('window').hover(function(){
					if(win.timer) {
						clearTimeout(win.timer);
					}
				},function() {
					win.timer=setTimeout(function(){ closeCustomWin(msEffect); }, _timeout);
				}
			);
		}
		if(!_hasCloseCross) {
			jQuery("body > .messager-window:last > .panel-heading > .panel-tool > .panel-tool-close").remove();
		}
	};
	
	/**
	 * 显示用户无法关闭的对话框
	 * @param message 消息
	 * @param alertType 提示类型（可选，默认为""）
	 * @param title 标题(可选，默认为消息)
	 * @param buttonText 关闭按钮文字（可选，如果没有或为空字符串，则不创建此按钮）
	 * @param callback 关闭按钮回调事件
	 * @param buttonText2 非关闭按钮文字（可选，如果没有或为空字符串，则不创建此按钮）
	 * @param callback2 非关闭按钮回调函数
	 */
	this.showUnCloseDialog = function(message, alertType, title, okText, okFunc, cancelText, cancelFunc){
		this.showMessage(message, alertType, title, 0, false, false, okText, okFunc, cancelText, cancelFunc, false);
	};
	
	/**
	 * 取得拖动UI对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDraggable = function(obj) {
		this.loadUIM('draggable');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得放置UI对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDroppable = function(obj) {
		this.loadUIM('droppable');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得重设大小UI对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getResizable = function(obj) {
		this.loadUIM('resizable');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得翻页UI对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getPagination = function(obj) {
		this.loadUIM('pagination');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得搜索框对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getSearchBox = function(obj) {
		this.loadUIM('searchbox');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得进度条对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getProgressBar = function(obj) {
		this.loadUIM('progressbar');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得提示工具对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getTooltip = function(obj) {
		this.loadUIM('tooltip');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	}
	
	/**
	 * 取得panel对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getPanel = function(obj) {
		this.loadUIM('panel');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得tabs对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getTabs = function(obj) {
		this.loadUIM('tabs');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得accordion对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getAccordion = function(obj) {
		this.loadUIM('accordion');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得layout对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getLayout = function(obj) {
		this.loadUIM('layout');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得menu对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getMenu = function(obj) {
		this.loadUIM('menu');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得linkbutton对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getLinkButton = function (obj){
		this.loadUIM('linkbutton');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得menubutton对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getMenuButton = function(obj) {
		this.loadUIM('menubutton');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得splitbutton对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getSplitButton = function(obj) {
		this.loadUIM('splitbutton');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得switchbutton对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getSwitchButton = function(obj) {
		this.loadUIM('switchbutton');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得form对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getForm = function(obj){
		this.loadUIM('form');
		if(obj) {
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 提交form
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param async 是否异步提交
	 * @param url 提交url
	 * @param onSuccess 成功时调用函数
	 * @param onSubmit 提交时调用函数（可选）
	 */
	this.submitForm = function(obj, async, url, onSuccess, onSubmit){
		this.loadUIM('form');
		if(obj) {
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				url: url,
				"success": onSuccess
			};
			onSubmit && jQuery.extend(_options, {"onSubmit": onSubmit});
			if (aync) {
				// ajax submit
				jqobj.form(_options);
				jqobj.submit();
			} else {
				// direct submit
				jqobj.submit('submit', _options);
			}
		}
	};
	
	/**
	 * 表单验证弹出对象
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @since 2013-8-21
	 */
	this.formValidation = function(obj){
		var isValid=true;
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			isValid = jqobj.form('validate');
			if(!isValid){				
				jqobj.append("<div id='validationPanel'><div id='validationInfo'></div>");
				
				var obj = $("[class*=validatebox-invalid]", jqobj);
				$('#validationInfo').empty();
				obj.each(function(){
					var validInfo=$(this).prev().html();
					if(!validInfo){
						var validInfo=$(this).parent().prevAll('label').html();
					}
					var data = $(this).data('validatebox');
					$('#validationInfo').append(validInfo+data.message+'<br><br>');
				});
				
				$CommonUI.getDialog('#validationPanel').dialog({title: '提交错误',
					modal:true,closed:true,width:350,height:"auto",
					onClose:function(){$("#validationPanel").dialog('destroy');} });
				$("#validationPanel").dialog("open");				
				$(".window-mask").on('click', function() {
					var val=$("#validationPanel").parent().css("display");
					if(val=="block"){
						$("#validationPanel").dialog('destroy');
						$(".window-mask").off();
					}
				});
			}
			return isValid;
		}
		return isValid;
	};
	
	/**
	 * 取得validatebox对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getValidateBox = function(obj) {
		this.loadUIM('validatebox');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得textbox对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getTextBox = function(obj) {
		this.loadUIM('textbox');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得passwordbox对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getPasswordBox = function(obj) {
		this.loadUIM('passwordbox');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	}
	
	/**
	 * 取得combo对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getCombo = function(obj) {
		this.loadUIM('combo');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得combobox对象
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getComboBox = function(obj) {
		this.loadUIM('combobox');
		if(obj){
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 取得combotree对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getComboTree = function(obj) {
		this.loadUIM('combotree');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得combogrid对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getComboGrid = function(obj) {
		this.loadUIM('combogrid');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得combotreegrid对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getComboTreeGrid = function(obj) {
		this.loadUIM('combotreegrid');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	}
	
	/**
	 * 取得numberbox对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getNumberBox = function(obj) {
		this.loadUIM('numberbox');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 获取日期对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDateBox = function(obj){
		this.loadUIM('datebox');
		if(obj){
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 获取日期时间控件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDateTimeBox = function(obj){
		this.loadUIM('datetimebox');
		if(obj){
			return getJqueryDomElement(obj);
		}else{
			return null;
		}
	};
	
	/**
	 * 获取日期时间微调器控件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDateTimeSpinner = function(obj) {
		this.loadUIM('datetimespinner');
		if(obj){
			return getJqueryDomElement(obj);
		}else{
			return null;
		}
	}
	
	/**
	 * 取得calendar对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getCalendar = function(obj) {
		this.loadUIM('calendar');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得spinner对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getSpinner = function(obj) {
		this.loadUIM('spinner');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	}
	
	/**
	 * 取得numberspinner对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getNumberSpinner = function(obj) {
		this.loadUIM('numberspinner');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 获取时间微调器对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getTimeSpinner = function(obj){
		this.loadUIM('timespinner');
		if(obj){
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 取得slider对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getSlider = function(obj) {
		this.loadUIM('slider');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 获取文件上传对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getFileBox = function(obj){
		this.loadUIM('filebox');
		if(obj){
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 取得window对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getWindow = function(obj) {
		this.loadUIM('window');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得dialog对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDialog = function(obj) {
		this.loadUIM('dialog');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得alert|confirm对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getMessager = function(obj) {
		this.loadUIM('messager');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得datagrid对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDataGrid = function(obj) {
		this.loadUIM('datagrid');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得edatagrid对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getEdataGrid = function(obj) {
		this.loadUIM('edatagrid');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得datalist对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDataList = function(obj) {
		this.loadUIM('datalist');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	}
	
	/**
	 * 取得propertygrid对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getPropertyGrid = function(obj) {
		this.loadUIM('propertygrid');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得tree对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getTree = function(obj) {
		this.loadUIM('tree');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得treegrid对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getTreeGrid = function(obj) {
		this.loadUIM('treegrid');
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 取得datetimepick对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getDatetimepicker = function(obj) {
		if(obj){
			includeCSS("/js/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css");
			includeJS("/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js");
			includeJS("/js/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js");
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 取得portal对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getPortal= function(obj) {
		this.loadUIM(['panel', 'draggable']);
		includeJS("/js/bsui/extension/jquery.portal.js");
		if(obj) {
			return getJqueryDomElement(obj);
		}else {
			return null;
		}
	};
	
	/**
	 * 进度条
	 * @param obj 进度条dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param progressValue 进度值（参考范围[0-100]）
	 * @param text 显示文本（可选，默认{value}%）
	 * @param color 填充颜色（可选）
	 * @param bordercolor 边框颜色（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.progressbar = function(obj, progressValue, text, color, bordercolor, options){
		this.loadUIM('progressbar');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				
			};
			jQuery.extend(_options, { value: progressValue });
			if(text) {
				jQuery.extend(_options, { "text": text });
			}
			options && jQuery.extend(_options, options);
			jqobj.progressbar(_options);
			if(null != color) {
				jqobj.find(".progressbar-value").css("background-color", color);
			}
			if(null != bordercolor) {
				jqobj.css("border-color", bordercolor);
			}
		}
	};
	
	/**
	 * 下拉列表
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param value 值（url|object）
	 * @param textField 显示值（可选，textField）
	 * @param valueField 保存值（可选，valueField）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-22
	 */
	this.combobox = function(obj, value, textField, valueField, options){
		this.loadUIM('combobox');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				panelHeight:'auto'
			};
			if(null != textField) {
				jQuery.extend(_options, { "textField": textField });
			}
			if(null != valueField) {
				jQuery.extend(_options, { "valueField": valueField });
			}
			options && jQuery.extend(_options, options);
			if('object' == typeof(value)) {
				// data
				jQuery.extend(_options, {'data': value, 'mode': 'local'});
			} else {
				// url
				jQuery.extend(_options, {'url': value, 'mode': 'remote'});
			}
			jqobj.combobox(_options);
		}
	};
	
	/**
	 * 构造日期控件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param value 初始值
	 * @param required 是否必须（可选，默认为false）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.datebox = function(obj, value, required, options){
		this.loadUIM('datebox');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				panelWidth: '280',
				panelHeight: '220'
			};
			if(null != required) {
				jQuery.extend(_options, { required: required });
			}
			options && jQuery.extend(_options, options);
			jqobj.datebox(_options); 
			jqobj.datebox('setValue', value);
		}
	};
	
	/**
	 * 构造日期时间控件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param value 初始值
	 * @param required 是否必须（可选，默认为false）
	 * @param showSeconds 显示秒（可选，默认为false）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.datetimebox = function(obj, value, required, showSeconds, options){
		this.loadUIM('datetimebox');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				width: 160,
				showSeconds: false,
				panelWidth: '320',
				panelHeight: '230'
			};
			if(null != required) {
				jQuery.extend(_options, {required: required});
			}
			if(null != showSeconds) {
				jQuery.extend(_options, {"showSeconds": showSeconds});
			}
			options && jQuery.extend(_options, options);
			jqobj.datetimebox(_options); 
			jqobj.datetimebox('setValue', value);
		}
	};
	
	/**
	 * 构造时间控件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param value 初始值
	 * @param required 是否必须（可选，默认为false）
	 * @param showSeconds 是否显示秒（可选，默认为false）
	 * @param min 最小值（可选）
	 * @param max 最大值（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.timespinner = function(obj, value, required, showSeconds, min, max, options){
		this.loadUIM('timespinner');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				highlight: 0  // 高亮“时”
			};
			if(null != required) {
				jQuery.extend(_options, {required: required});
			}
			if(null != showSeconds) {
				jQuery.extend(_options, {"showSeconds": showSeconds});
			}
			if(min) {
				jQuery.extend(_options, {"min": min});
			}
			if(max) {
				jQuery.extend(_options, {"max": max});
			}
			options && jQuery.extend(_options, options);
			jqobj.timespinner(_options);
			jqobj.timespinner('setValue', value); // 验证max&min
		}
	};
	
	/**
	 * 构造文件上传控件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param options 扩展选项（可选）
	 * @author 李飞
	 * @since 2016-3-14
	 */
	this.filebox = function(obj, options){
		this.loadUIM('filebox');
		
		if(obj){
			var jqObj = getJqueryDomElement(obj);
			var _options = {
				buttonText: '选择文件'
			};
			
			options && jQuery.extend(_options, options);
			jqObj.filebox(_options);
		}
	};
	
	/**
	 * 构造DataGrid组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param url 数据来源
	 * @param queryParams 参数(例：{page:1,rows:10})
	 * @param columns 表头选项
	 * @param pageOpts 分页选项（可选）
	 * @param sortOpts 排序选项（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.datagrid = function(obj, url, queryParams, columns, pageOpts, sortOpts, options) {
		this.loadUIM('datagrid');
		if (!obj) return;
		var jqobj = getJqueryDomElement(obj);
		var _options = this._DEFAULT_DATAGRID_OPTIONS;
		jQuery.extend(_options, {
                    view: $.fn.datagrid.defaults.view,
		    url: url,
		    queryParams: queryParams,
		    collapsible: true,
		    nowrap: true,
		    singleSelect: false,
		    pagination: false,
		    pageNumber: 1,
		    pageSize: 15
		});
		columns && jQuery.extend(_options, {"columns": columns});
		if(pageOpts) {
			jQuery.extend(_options, pageOpts);
		}
		if(sortOpts) {
			jQuery.extend(_options, sortOpts);
		}
		if(options) {
			jQuery.extend(_options, options);
		}
		jqobj.datagrid(_options);
	};
	
	/**
	 * 构造EdataGrid组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param url 数据来源
	 * @param queryParams 参数(例：{page:1,rows:10})
	 * @param columns 表头选项
	 * @param pageOpts 分页选项（可选）
	 * @param sortOpts 排序选项（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2014-6-20
	 */
	this.edatagrid = function(obj, url, queryParams, columns, pageOpts, sortOpts, options) {
		this.loadUIM('edatagrid');
		if (!obj) return;
		var jqobj = getJqueryDomElement(obj);
		var _options = this._DEFAULT_DATAGRID_OPTIONS;
		jQuery.extend(_options, {
		    url: url,
		    saveUrl: null,
		    updateUrl: null,
		    destroyUrl: null,
		    queryParams: queryParams,
		    collapsible: true,
		    nowrap: true,
		    singleSelect: false,
		    pagination: false,
		    pageNumber: 1,
		    pageSize: 15,
			autoSave:false
		});
		columns && jQuery.extend(_options, {"columns": columns});
		if(pageOpts) {
			jQuery.extend(_options, pageOpts);
		}
		if(sortOpts) {
			jQuery.extend(_options, sortOpts);
		}
		if(options) {
			jQuery.extend(_options, options);
		}
		jqobj.edatagrid(_options);
	};
	
	/**
	 * 按条件加载DataGrid数据
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param params 条件
	 * @author 张磊
	 * @since 2013-7-21
	 */
	this.queryForDataGrid = function(obj, params){
		this.loadUIM('datagrid');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			jqobj.datagrid('load', params);
		}
	};
	
	/**
	 * 构造portal - panel组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param options 扩展选项（可选）
	 * @author 李飞
	 * @since 2017-3-20
	 */
	this.portal = function(obj, options) {
		if(!obj) return;
		this.loadUIM(['panel', 'draggable']);
		includeJS("/js/bsui/extension/jquery.portal.js");
		var jqobj = getJqueryDomElement(obj);
		jqobj.portal(options);
	}
	
	/**
	 * 构造DetailView - DataGrid组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param url 数据来源
	 * @param queryParams 参数
	 * @param detailFormatter 取得详细内容URL（字符串|函数，函数传递index和record参数）
	 * @param columns 表头选项
	 * @param pageOpts 分页选项（可选）
	 * @param sortOpts 排序选项（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.datagriddetail = function(obj, url, queryParams, detailFormatter, columns, pageOpts, sortOpts, options) {
		this.loadUIM('datagrid');
		if(!obj) return;
		var jqobj = getJqueryDomElement(obj);
		includeJS("/js/bsui/extension/datagrid-detailview.js");
		var _options = jQuery.extend({}, {
		    view: detailview
		});
		if('function' == typeof(detailFormatter)) {
			//直接渲染
			_options = jQuery.extend({}, _options, {
				'detailFormatter': detailFormatter||function(){}
			});
		} else {
			//内嵌url
			_options = jQuery.extend({}, _options, {
				onExpandRow: function(index, row){
					jQuery(jqobj.selector + '#ddv-'+index).panel({ 
			            border:false, 
			            cache:false, 
			            href: detailFormatter||'', 
			            onLoad:function(){ 
			            	jqobj.datagrid('fixDetailRowHeight',index); 
			            } 
			        }); 
					jqobj.datagrid('fixDetailRowHeight',index); 
			    }, 
			    'detailFormatter': function(index,row){ 
			        return '<div id="ddv-' + index + '" style="padding:5px 0"></div>'; 
			    }
			});
		}
		_options = jQuery.extend({}, _options, options);
		this.datagrid(obj, url, queryParams, columns, pageOpts, sortOpts, _options);
	};
	
	/**
	 * 构造GroupView - DataGrid组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param url 数据来源
	 * @param queryParams 参数
	 * @param groupField 分组名
	 * @param groupFormatter 分组依据函数（默认传递参数[value, rows]）
	 * @param columns 表头选项
	 * @param pageOpts 分页选项（可选）
	 * @param sortOpts 排序选项（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2014-5-9
	 */
	this.datagridgroup = function(obj, url, queryParams, groupField, groupFormatter, columns, pageOpts, sortOpts, options) {
		this.loadUIM('datagrid');
		if(!obj) return;
		var jqobj = getJqueryDomElement(obj);
		includeJS("/js/bsui/extension/datagrid-groupview.js");
		var _options = jQuery.extend({}, { 
		    groupField: groupField||'id',
			'groupFormatter': groupFormatter||function(){},
		    view: groupview
		}, options);
		this.datagrid(obj, url, queryParams, columns, pageOpts, sortOpts, _options);
	};
	
	/**
	 * 构造FilterView - DataGrid组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param url 数据来源
	 * @param queryParams 参数
	 * @param filterColumns 过滤列描述
	 * @param columns 表头选项
	 * @param pageOpts 分页选项（可选）
	 * @param sortOpts 排序选项（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2014-5-23
	 */
	this.datagridfilter = function(obj, url, queryParams, filterColumns, columns, pageOpts, sortOpts, options) {
		this.loadUIM(['menu', 'numberbox', 'datagrid', 'combobox', 'treegrid']);
		if(!obj) return;
		var jqobj = getJqueryDomElement(obj);
		includeJS("/js/bsui/extension/datagrid-filter.js");
		var _options = jQuery.extend({}, {}, options);
		this.datagrid(obj, url, queryParams, columns, pageOpts, sortOpts, _options);
		filterColumns = filterColumns && jQuery.isArray(filterColumns)? filterColumns:[];
		jqobj.datagrid('enableFilter', filterColumns);
	};
	
	/**
	 * 构造树组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-21
	 */
	this.tree = function(obj, url, data, onClick, onDblClick, checkbox, onlyLeafCheck, options){
		this.loadUIM('tree');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				dnd: false, 		// 不能拖动
				cascadeCheck: true, // checkbox选择时联动
				lines: true 		// 导航虚线
			};
			if (url) {
				jQuery.extend(_options, {"url": url});
			}
			if (null!=data) {
				if('string'===typeof(data) && data) {
					jQuery.extend(_options, {"data": jQuery.parseJSON(data)});
				} else if('object'===typeof(data) && data) {
					jQuery.extend(_options, {"data": data});
				}
			}
			if (onClick) {
				jQuery.extend(_options, {"onClick": onClick});
			}
			if (onDblClick) {
				jQuery.extend(_options, {"onDblClick": onDblClick});
			}
			if (null != checkbox) {
				jQuery.extend(_options, {"checkbox": checkbox});
			}
			if (null != onlyLeafCheck) {
				jQuery.extend(_options, {"onlyLeafCheck": onlyLeafCheck});
			}
			options && jQuery.extend(_options, options);
			jqobj.tree(_options); 
		}
	};
	
	/**
	 * 创建bootstrap版日期时间组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param width 组件宽度，默认值为200
	 * @param height 组件高度，最小高度默认值为30px
	 * @param options datetimepicker选项
	 * @author 李飞
	 * @since 2016-3-11
	 */
	this.datetimepicker = function(obj, width, height, options, float) {
		var jqObj = getJqueryDomElement(obj);
		if (!float) {
			float = '';
		}
		
		if(jqObj && jqObj.length) {
			includeCSS("/js/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css");
			includeJS("/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js");
			includeJS("/js/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js");
			
			jqObj.each(function() {
				var $this = $(this);
				
				// 初始化日期时间输入框组件
				var $wrap = $this.next('datetimepicker-wrap'),
					$text = $wrap.find('input');
				if ($wrap.length) {
					$text.datetimepicker('remove');
					$wrap.remove();
				} else {
					$wrap = $('<div class="datetimepicker-wrap input-group '+float+'"><input class="form-control" readonly/><span class="input-group-addon">'
						+ '<i class="glyphicon glyphicon-calendar"></i></span></div>').insertAfter($this);
					$text = $wrap.find('input');
				}
				$this.addClass('datetimepicker-f hidden');
				
				var namePro = $this.attr("name") || $this.attr("pickername");
				if (namePro) {
					$text.attr("name", namePro).css('width', '100%');
					$this.removeAttr("name").attr("pickername", namePro);
				}
				var placeholder = $this.attr('placeholder');
				if (placeholder) {
					$text.attr('placeholder', placeholder);
				}
				
				if (!width) {
					width = 200;
				}
				$wrap.width(width);
				
				if (height) {
					$text.css({'min-height':'30px', 'height':Number(height)});
				}
				
				var _options = {
					format: 'yyyy-mm-dd hh:ii:ss',
					autoclose: true,
					todayBtn: true,
					todayHighlight: true,
					language: 'zh-CN'
				};
				options = jQuery.extend({}, _options, options);
				
				$text.datetimepicker(options);
				return $text;
			});
		}
	};
	
	/**
	 * 动态加载bsui组件
	 * @param type 类型
	 * @author 张磊
	 * @since 2013-7-20
	 */
	this.loadUIM = function (type, fn) {
		// 加载组件
		bsuiloader.load(type, fn);
	};
	
	/**
	 * 取得指定id对象的类型
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param tbCls 自定义筛选的组件，类型String|Array
	 * @return string 
	 */
	this.getUIType = function(obj){
		var target = getJqueryDomElement(obj);
		if(!target || target.length==0) return '';
		
		/*var tbCls = ['textbox','passwordbox','filebox','numberbox','numberspinner','timespinner','datetimespinner',
			'combo','combobox','combotree','combogrid','combotreegrid','datebox','datetimebox','slider','validatebox'];*/
		var tbCls = ['slider','textbox','passwordbox','timespinner','datetimespinner','numberbox','numberspinner',
			'filebox','combo','combotreegrid','combotree','combogrid','combobox','datebox','datetimebox'];
		
		for(var i=tbCls.length-1; i>=0; i--){
			var type = tbCls[i];
			if(target.eq(0).hasClass(type+'-f')){
				return type;
			}else if(target.eq(0).hasClass('validatebox-text') && !target.eq(0).hasClass(type+'-f')) {
				return "validatebox";
			}
		}

		if(target.eq(0).hasClass('multiselect2side')) {
			return 'multiselect2side';
		}
		if(target.eq(0).hasClass('calendar')) {
			return 'calendar';
		}
		
		var tagName = target.eq(0).prop('tagName').toLowerCase();
		if('input' === tagName && target.eq(0).attr('type') &&
			-1 != $.inArray(target.eq(0).attr('type'), ['checkbox', 'radio'])) {
			tagName = target.eq(0).attr('type').toLowerCase();
		}
		return tagName;
	};
	
	/**
	 * 取得指定对象值
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	// textbox及继承自textbox的组件map集合
	var textboxMap = {'textbox':true,'passwordbox':true,'timespinner':true,'datetimespinner':true,'numberbox':true,'numberspinner':true,
		'combo':true,'combotreegrid':true,'combotree':true,'combogrid':true,'combobox':true,'datebox':true,'datetimebox':true};
	this.getUIValue = function(obj) {
		var clsName = this.getUIType(obj);
		if(clsName) {
			var target = getJqueryDomElement(obj);
			// textbox及继承自textbox的组件
			if(textboxMap[clsName]) {
				if(target[clsName]('options').multiple) {
					return target.eq(0)[clsName]('getValues');
				} else {
					return target.eq(0)[clsName]('getValue');
				}
			}
			// filebox,slider
			var clses = ['filebox','slider'],
				idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				return target.eq(0)[clses[idx]]('getValue');
			}
			// calendar
			clses = ['calendar'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				return target.eq(0)[clses[idx]]('options').current;				
			}
			// validatebox,input,textarea
			clses = ['validatebox','input','textarea'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				return target.eq(0).val();
			}
			// select,multiselect2side
			clses = ['select', 'multiselect2side'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				var rtnVal = [];
				$.each(target.children(':selected'), function(i,n){
					rtnVal.push($(this).val());
				});
				return rtnVal;
			}
			// radio
			clses = ['radio'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				var rtnVal = null;
				$.each(target.filter(':checked'), function(i,n){
					rtnVal=$(this).val();
					return false;
				});
				return rtnVal;
			}
			//checkbox
			clses = ['checkbox'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				var rtnVal = [];
				$.each(target.filter(':checked'), function(i,n){
					rtnVal.push($(this).val());
				});
				return rtnVal;
			}
		}else{
			return null;
		}
	};
	
	/**
	 * 设置指定ID值
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.setUIValue = function(obj, value) {
		var clsName = this.getUIType(obj);
		if(clsName) {
			var target = getJqueryDomElement(obj);
			// textbox及继承自textbox的组件
			if(textboxMap[clsName]) {
				if(target[clsName]('options').multiple) {
					target.eq(0)[clsName]('setValues', value);
				} else {
					target.eq(0)[clsName]('setValue', value);
				}
				return;
			}
			// filebox,slider
			var clses = ['filebox','slider'],
				idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				if (target[clses[idx]]('options').range) {
					target.eq(0)[clses[idx]]('setValues', value);
				} else{
					target.eq(0)[clses[idx]]('setValue', value);
				}
				return;
			}
			// calendar
			clses = ['calendar'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				target.eq(0)[clses[idx]]('options').current = value;
				return;
			}
			// validatebox,input,textarea
			clses = ['validatebox','input','textarea'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				if (0 == idx) {
					target.eq(0).val(value).validatebox('validate');
				} else {
					target.eq(0).val(value);
				}
				return;
			}
			// multiselect2side,select
			clses = ['multiselect2side', 'select'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				target.children(':selected').prop('selected', false);
				target.eq(0).val(value);
				return;
			}
			// radio
			clses = ['radio'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				target.prop('checked', false);
				target.each(function() {
					if($(this).val() == value) {
						$(this).prop('checked', true);
						return false;
					}
				});
				return;
			}
			// checkbox
			clses = ['checkbox'];
			idx = $.inArray(clsName, clses);
			if(-1 != idx) {
				target.prop('checked', false);
				target.each(function() {
					if ((typeof value === "string" && $(this).val() == value) ||
						(value instanceof Array && 1 !== $.inArray($(this).val(), value))) {
						$(this).prop('checked', true);
						return false;
					}
				});
				return;
			}
		}
	};
	
	/**
	 * 遍历区块数据组装成JSON对象
	 * @param container dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param skipNames 跳过的name数组，类型Array
	 * @param skipHidden 是否跳过HTML标准组件里的hidden字段，值：true|false
	 * @param skipDisabled 是否跳过disable字段，值：true|false
	 */
	this.loopBlock = function(container, skipNames, skipHidden, skipDisabled){
		var attrs = {}; // 返回的对象
		var gettedNames = []; // 需跳过的组件名数组
		if(!skipNames) {
			skipNames = [];   // 需跳过的name
		}
		var target = jQuery(document);
		if(container) {
			target = getJqueryDomElement(container);
		}
		
		var ipts = jQuery('[textboxname],[name],[slidername]', target).not('[class="textbox-value"]');
		if (ipts.length){
			var checkBtn = {}; // 存储radio和checkbox的键值对
			ipts.each(function(){
				var clsName = $CommonUI.getUIType(this);
				// textbox及继承自textbox的组件
				if (textboxMap[clsName]) {
					var name = jQuery(this).attr('textboxname');
					if(skipDisabled && jQuery(this)[clsName]('options').disabled) {
						return true;
					}
					if(skipNames && -1!==jQuery.inArray(name, skipNames)) {
						return true;
					}
					if (jQuery(this)[clsName]('options').multiple){
						var val = jQuery(this)[clsName]('getValues');
						extendJSON(name, val);
					} else {
						var val = jQuery(this)[clsName]('getValue');
						extendJSON(name, val);
					}
					return true;
				}
				// slider
				if ('slider' == clsName) {
					var name = jQuery(this).attr('slidername');
					if (skipDisabled && jQuery(this)[clsName]('options').disabled) {
						return true;
					}
					if(skipNames && -1!==jQuery.inArray(name, skipNames)) {
						return true;
					}
					if (jQuery(this)[clsName]('options').range) {
						var val = jQuery(this)[clsName]('getValues');
						extendJSON(name, val);
					} else{
						var val = jQuery(this)[clsName]('getValue');
						extendJSON(name, val);
					}
					return true;
				}
				// multiselect2side
				if ('multiselect2side' == clsName) {
					var name = jQuery(this).attr('name');
					if(skipDisabled && $(this).next('.ms2side__div').find(':disabled').length) {
						return true;
					}
					if(skipNames && -1!==jQuery.inArray(name, skipNames)) {
						return true;
					}
					var val = jQuery(this)['multiselect2side']('getValue');
					extendJSON(name, val);
					return true;
				}
				
				if(skipDisabled && $(this).is(":disabled")) {
					return true;
				}
				if(skipHidden && $(this).is(":hidden")) {
					return true;
				}
				// validatebox,input,textarea,select
				var cTypes = ['validatebox','input','textarea','select'],
					idx = $.inArray(clsName, cTypes);
				if (-1 != idx) {
					var name = jQuery(this).attr('name');
					if (0 == idx) {
						if (skipDisabled && jQuery(this)[clsName]('options').disabled) {
							return true;
						}
					} else {
						
					}
					if(skipNames && -1!==jQuery.inArray(name, skipNames)) {
						return true;
					}
					var val = jQuery(this).val();
					extendJSON(name, val);
					return true;
				}
				// radio,checkbox
				if ('radio'==clsName || 'checkbox'==clsName) {
					var name = jQuery(this).attr('name');
					if(skipNames && -1!==jQuery.inArray(name, skipNames)) {
						return true;
					}
					switch (clsName){
					case 'radio':
						if (jQuery(this).prop('checked')) {
							checkBtn[name] = jQuery(this).val();
						}
						return true;
					case 'checkbox':
						if (jQuery(this).prop('checked')) {
							if (checkBtn[name]) {
								checkBtn[name] += ',' + jQuery(this).val();
							} else{
								checkBtn[name] = jQuery(this).val();
							}
						}
						return true;
					}
				}	
			});
			jQuery.extend(attrs, checkBtn);
		}
		
		function extendJSON(name, val) {
			if(!name) return;
			if(-1 !== $.inArray(name, gettedNames)) {
				// 只获取第一个name的值
				return;
			} else {
				gettedNames.push(name);
			}
			val = 'undefined'!==typeof(val)? val:'';
			val = $.isArray(val)? val.join(','):val;
			var newObj = eval('({"'+name+'":'+jQuery.toJSON(val)+'})');
			jQuery.extend(attrs, newObj);
		}
		return attrs;
	};
	
	/**
	 * 通过JSON设置区块数据
	 * @param container dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param data json字符串或JSON对象
	 */
	this.fillBlock = function(container, data){
		if (!data) {
			return;
		}
		if (typeof data === 'string') {
			data = $.parseJSON(data);
		} else if (typeof data !== 'object') {
			return;
		}
		
		var target = $(document);
		if(container) {
			target = getJqueryDomElement(container);
		}
		
		// 遍历并加载数据
		var prefixs=[];
		loopJSON(data);
		
		function loopJSON(json) {
			$.each(json, function(i, d){
				if(null === d) {
					
				} else if('object' === $.type(d) || 'array' === $.type(d)) {
					prefixs.push(i);
					loopJSON(d);
					prefixs.pop();
				} else {
					prefixs.push(i);
					var prefix = prefixs.join('.').replace(/(\.(\d+))(?=.)?/i, '[$2]');
					setNameVal(prefix, d);
					prefixs.pop();
				}
			});
		}
		
		function setNameVal(name, val) {
			name = 'undefined'!==typeof(name)? ''+name:'';
			if('' === name) return;
			val = 'undefined'!==typeof(val)? val:'';
			
			var ipts = $('[textboxname="'+name+'"],[slidername="'+name+'"],[name="'+name+'"]', target).not('[class="textbox-value"]'),
				clsName = $CommonUI.getUIType(ipts);
			if (clsName) {
				// textbox及继承自textbox的组件
				if (textboxMap[clsName]) {
					var opts = ipts[clsName]('options');
					if (opts && opts.multiple) {
						ipts[clsName]('setValues', val);
					} else {
						if(-1!=$.inArray(clsName, ['datetimebox','datebox']) && $.isNumeric(val)) {
							var valDate = new Date();
							valDate.setTime(val);
							val = $.fn.datebox.defaults.formatter(valDate);
						}
						ipts[clsName]('setValue', val);
					}
					return;
				}
				// slider
				if ('slider' == clsName) {
					var t = ipts.filter(".slider-f");
					if (t[clsName]('options').range) {
						t[clsName]('setValues', val);
					} else{
						t[clsName]('setValue', val);
					}
					return;
				}
				// validatebox,input,textarea
				var cTypes = ['validatebox','input','textarea'],
					idx = $.inArray(clsName, cTypes);
				if (-1 != idx) {
					if (0 == idx) {
						ipts.val(val).validatebox('validate');
					} else{
						ipts.val(val);
					}
					return;
				}
				// multiselect2side,select
				var cTypes = ['multiselect2side', 'select'],
					idx = $.inArray(clsName, cTypes);
				if (-1 != idx) {
					if (0 == idx) {
						ipts[clsName]('setValue',val);
					} else {
						ipts.val(val);
					}
					return;
				}
				// radio,checkbox
				if ('radio'==clsName || 'checkbox'==clsName) {
					ipts.prop('checked',false).each(function() {
						var $this = $(this);
						if (val && -1!==String(val).indexOf($this.val()) || $this.val() == String(val)){
							$this.prop('checked', true);
						}
					});
					return;
				}
			}
		}
	};
	
	/**
	 * 取得悬浮提示框对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.getPoshytip = function(obj) {
		if(obj){
			includeCSS("/js/poshytip/tip-yellow/tip-yellow.css");
			includeJS("/js/poshytip/jquery.poshytip.min.js");
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 销毁悬浮提示信息
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 */
	this.destoryPoshytip = function(obj){
		var jqObj = getJqueryDomElement(obj);
		if(jqObj && jqObj.length > 0 && jqObj.poshytip){
			jqObj.poshytip('destroy');
		}
	};

	/**
	 * 创建悬浮提示信息
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param message 提示消息
	 * @param options poshytip选项
	 */
	this.poshytip = function(obj, message, options){
		var jqObj = getJqueryDomElement(obj);
		if(jqObj && jqObj.length > 0){
			includeCSS("/js/poshytip/tip-yellow/tip-yellow.css");
			includeJS("/js/poshytip/jquery.poshytip.min.js");
			
			jqObj.poshytip('destroy');
			
			var _options = {showOn: "none"};
			var settings = null;
			
			if(message){
				_options.content = message;
			}
			if(options){
				settings  = jQuery.extend({}, this._DEFAULT_POSHYTIP_OPTIONS, _options, options);
			}else{
				settings  = jQuery.extend({}, this._DEFAULT_POSHYTIP_OPTIONS, _options);
			}
			
			jqObj.poshytip(settings);
			jqObj.poshytip("show");
		}
	};
	
	/**
	 * 取得textarea对象
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @author 聂文来
	 * @since 2013-8-14
	 */
	this.getTextarea = function(obj) {
		if(obj){
			return getValidateBox(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 创建textarea组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param width textarea组件的宽度,默认为100
	 * @param height textarea组件的高度,默认为100
	 * @param required textarea组件是否为必填项,默认为false
	 * @author 聂文来
	 * @since 2013-8-14
	 */
	this.textarea = function(obj,width,height,required){
		var jqObj = getJqueryDomElement(obj);
		if(jqObj && jqObj.length > 0){
			
			width = width||100;
			jqObj.css("width",width);
			height = height||100;
			jqObj.css("height",height);
			
			if(required==null){
				required = false;
			}
			$CommonUI.getValidateBox(obj).validatebox({  
		        required: required 
		    });  
		}
	};
	
	/**
	 * 创建图片查看组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param myWidth 悬浮层宽度,默认为600
	 * @param myHeight 悬浮层高度,默认为350
	 * @param url 当前页面与js的相对路径
	 * @param component 工具栏组件
	 * @param component.Vertical 垂直翻转，true显示，false不显示,默认为false
	 * @param component.Horizontal 水平翻转，true显示，false不显示,默认为false
	 * @param component.Left 左旋转，true显示，false不显示,默认为false
	 * @param component.Right 右旋转，true显示，false不显示,默认为false
	 * @param component.ZoomBig 放大，true显示，false不显示,默认为false
	 * @param component.ZoomSmall 缩小，true显示，false不显示,默认为false
	 * @param component.Reset 复位，true显示，false不显示,默认为false
	 * @param component.Close 关闭，true显示，false不显示,默认为true
	 * @author 聂文来
	 * @since 2013-8-16
	 */
	this.imageTransfer = function(obj,url,myWidth,myHeight,component){
		includeCSS("/js/imageTrans/css/imageTrans.css");
		includeJS("/js/imageTrans/CJL.0.1.min.js");
		includeJS("/js/imageTrans/ImageTrans.js");
		var jqObj = getJqueryDomElement(obj);
		if(jqObj && jqObj.length > 0){
			
			//判断图片是否存在
			var mySrc = jqObj.attr("src")||"";
			if(mySrc==""){
				return;
			}
			myWidth = myWidth||600;
			myHeight = myHeight||350;
			
			//添加悬浮层
			jqObj.after('<div id="idContainer"></div>');
			//添加工具栏
			jqObj.after('<div id="idToolbar"></div>');
			$("#idToolbar").bind("mouseover",function(){$("#idToolbar").css("opacity","1");});
			$("#idToolbar").bind("mouseleave",function(){$("#idToolbar").css("opacity","0.2");});
			
			//添加悬浮层样式
			$("#idContainer").addClass("idContainer");
			$("#idContainer").width(myWidth);
			$("#idContainer").height(myHeight);
			//添加工具栏样式
			$("#idToolbar").addClass("idToolbar");
			$("#idToolbar").css("opacity","0.2");
			$("#idToolbar").width(15);
			$("#idToolbar").height(myHeight);
			
			//悬浮层居中
			var top = ($(window).height() - $("#idContainer").height())/2;   
			var left = ($(window).width() - $("#idContainer").width())/2;   
			var scrollTop = $(document).scrollTop();   
			var scrollLeft = $(document).scrollLeft();   
			$("#idContainer").css({'top':top+scrollTop,'left':left+scrollLeft});
			//工具栏居中
			$("#idToolbar").css({'top':top+scrollTop,'left':left+scrollLeft-15});
			
			//加载工具栏组件
			component.Vertical = component.Vertical||false;
			component.Horizontal = component.Horizontal||false;
			component.Left = component.Left||false;
			component.Right = component.Right||false;
			component.ZoomBig = component.ZoomBig||false;
			component.ZoomSmall = component.ZoomSmall||false;
			component.Reset = component.Reset||false;
			component.Close = component.Close||true;
			(function(){
			    var container = $$("idContainer"), src = mySrc,
				options = {
					onPreLoad: function(){},
					onLoad: function(){},
					onError: function(err){}
				},
				it = new ImageTrans( container, options );
				it.load(src);
				//关闭
				if(component.Close){
					$("#idToolbar").append('<img id="idClose" class="imageTrans"/>');
	            	$("#idClose").attr({"src":url+"/imageTrans/css/images/delete.png","title":"关闭"});
	            	$("#idClose").bind("click",function(){
						$("#idContainer").remove();
						$("#idToolbar").remove();
					});
				}
				//垂直翻转
				if(component.Vertical){
					$("#idToolbar").append('<img id="idVertical" class="imageTrans"/>');
					$("#idVertical").attr({"src":url+"/imageTrans/css/images/20080527114101460.png","title":"垂直翻转"});
					$$("idVertical").onclick = function(){ it.vertical(); };
				}
				//水平翻转
	            if(component.Horizontal){
	            	$("#idToolbar").append('<img id="idHorizontal" class="imageTrans"/>');
	            	$("#idHorizontal").attr({"src":url+"/imageTrans/css/images/20080527114056870.png","title":"水平翻转"});
	            	$$("idHorizontal").onclick = function(){ it.horizontal(); };
				}
				//左旋转
				if(component.Left){
					$("#idToolbar").append('<img id="idLeft" class="imageTrans"/>');
	            	$("#idLeft").attr({"src":url+"/imageTrans/css/images/20080527114051997.png","title":"向左旋转"});
					$$("idLeft").onclick = function(){ it.left(); };
				}
				//右旋转
				if(component.Right){
					$("#idToolbar").append('<img id="idRight" class="imageTrans"/>');
	            	$("#idRight").attr({"src":url+"/imageTrans/css/images/20080527114059481.png","title":"向右旋转"});
					$$("idRight").onclick = function(){ it.right(); };
				}
				//放大
				if(component.ZoomBig){
					$("#idToolbar").append('<img id="idZoomBig" class="imageTrans"/>');
	            	$("#idZoomBig").attr({"src":url+"/imageTrans/css/images/20080615141652172.png","title":"放大"});
					$$("idZoomBig").onclick = function(){ it.zoomBig(); };
				}
				//缩小
				if(component.ZoomSmall){
					$("#idToolbar").append('<img id="idZoomSmall" class="imageTrans"/>');
	            	$("#idZoomSmall").attr({"src":url+"/imageTrans/css/images/20080615141652103.png","title":"缩小"});
					$$("idZoomSmall").onclick = function(){ it.zoomSmall(); };
				}
				//复位
				if(component.Reset){
					$("#idToolbar").append('<img id="idReset" class="imageTrans"/>');
	            	$("#idReset").attr({"src":url+"/imageTrans/css/images/reset.png","title":"复位"});
					$$("idReset").onclick = function(){ it.reset(); };
				}
			})();
		}
	};
	
	/**
	 * 创建图片放大镜组件
	 * @param obj dom对象，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param title 放大后的图片说明
	 * @param options.lens 放大位置阴影是否显示，true为显示，false为不显示,默认为true
	 * @param options.zoomWidth 放大位置阴影宽度，默认为200
	 * @param options.zoomHeight 放大位置阴影高度，默认为200
	 * @param options.alwaysOn 放大的图片是否一直显示，true为一直显示，false为鼠标触发显示,默认为false 
	 * @param options.xOffset 左右偏移量,默认为10
	 * @param options.yOffset 上下偏移量,默认为0
	 * @param options.position 偏移方位,可选值为'left','right','bottom','top',默认为'left'
	 * @author 聂文来
	 * @since 2013-8-16
	 */
	this.imageZoom = function(obj, title, options) {
		includeCSS("/js/imageZoom/css/jquery.jqzoom.css");
		includeJS("/js/imageZoom/jquery.jqzoom-core.js");
		
		var jqObj = getJqueryDomElement(obj);
		if(jqObj && jqObj.length) {
			title = title || "图片说明";
			jqObj.attr("title", title);
			
			options = jQuery.extend({}, {
				zoomType: 'standard',
				preloadImages: false,
				lens: true,
				zoomWidth: 200,
				zoomHeight: 200,
				alwaysOn: false,
				xOffset: 10,
				yOffset: 0,
				position: "left"
			}, options);
			
			jqObj.jqzoom(options);
		}
	};
	
	/**
	 * 取得左右选择框对象
	 * @param obj 进度条dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(必须是select的)
	 */
	this.getMultiSelect2Side = function(obj) {
		includeCSS('/js/multiselect2side/css/jquery.multiselect2side.css');
		includeJS('/js/multiselect2side/jquery.multiselect2side.js');
		if(obj) {
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 左右选择框
	 * @param obj 进度条dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(必须是select的)
	 * @param labelsx 源选择框头（可选，默认为空）
	 * @param labeldx 目标选择框头（可选，默认为空）
	 * @param options 扩展选项（可选）
	 */
	this.multiselect2side = function(obj, labelsx, labeldx, options) {
		includeCSS('/js/multiselect2side/css/jquery.multiselect2side.css');
		includeJS('/js/multiselect2side/jquery.multiselect2side.js');
		if(obj) {
			var jqobj = getJqueryDomElement(obj);
			var _options = {
				optGroupSearch: false, // 是否组选择
				selectedPosition: 'right', // 选择项位置，提交项
				minSize: 8, // 显示的行数
				moveOptions: true, // 显示选择项可操作按钮
				labelTop: '置顶', // 置顶文本
				labelBottom: '置底', // 置底文本
				labelUp: '向上', // 向上文本
				labelDown: '向下', // 向下文本
				labelSort: '排序', // 排序文本
				labelsx: '', // 源选择框头
				labeldx: '', // 目标选择框头
				maxSelected: -1, // 最多选择数
				search: false, // 是否有搜索框，有则填搜索框前Label信息
				autoSort: false, // 自动排序选择项
				autoSortAvailable: false, // 自动排序所有
				caseSensitive: false, // 大小写敏感
				delay: 300 // 输入文本过滤时需要多久显示效果
			};
			if(labelsx) $.extend(_options, {"labelsx": labelsx});
			if(labeldx) $.extend(_options, {"labeldx": labeldx});
			if(options) $.extend(_options, options);
			jqobj.multiselect2side(_options);
		}
	};
	
	/**
	 * 弹窗
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
	 * @param href 值（url|object）
	 * @param title 窗口标题（可选）
	 * @param mode 是否模式窗口（可选）
	 * @param closed 是否可手动关闭（可选）
	 * @param cache 是否缓存（可选）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-22
	 */
	this.popWin = function(obj, href, title, mode, closed, cache, options){
		this.loadUIM('dialog');
		if(obj) {
			var jqobj = getJqueryDomElement(obj);
			var _options = { 
				width: 400,
				height: 300
			};
			if(href) jQuery.extend(_options, {'href':href});
			if(title) jQuery.extend(_options, {'title':title});
			if(mode) jQuery.extend(_options, {'mode':mode});
			if(closed) jQuery.extend(_options, {'closed':closed});
			if(cache) jQuery.extend(_options, {'cache':cache});
			if(options) jQuery.extend(_options, options);
			jqobj.dialog(_options);
		}
	};
	
	/**
	 * 创建默认文本
	 * @author 边航鹰
	 * @since 2013-08-19
	 */
	this.placeholder = function(){
		includeJS("/js/placeholder/jquery.placeholder.js");
		$.fn.placeholder();
	};
	
	/**
	 * FusionCharts 初始化
	 * @author 张磊
	 * @since 2013-7-22
	 */
	this.initCharts = function() {
		includeJS('/js/FusionCharts/FusionCharts.js');
		includeJS('/js/FusionCharts/FusionCharts.jqueryplugin.js');
	};

	/**
	 * FusionCharts
	 * @param chartID 图表ID（唯一）
	 * @param type 图表类型
	 * @param wrapper 图表所在容器ID
	 * @param dataurl 数据或URL（数据或者指向数据的URL）
	 * @param format 数据格式（可选xml|xmlurl|json|jsonurl，默认json）
	 * @param width 宽（可选，默认值400）
	 * @param height 高（可选，默认值300）
	 * @param renderer 显示方式（可选flash|javascript，默认值flash）
	 * @param options 扩展选项（可选）
	 * @author 张磊
	 * @since 2013-7-22
	 */
	this.Charts = function(chartID, type, wrapper, dataurl, format, width, height, renderer, options) {
		this.initCharts();
		var _format = format? format : 'json';
		var _width = width? width : '400';
		var _height = height? height : '300';
		var _renderer = renderer? renderer : 'flash';
		var _type = (/[a-zA-Z0-9]+/.test(type))? type:'';
		if('' === _type) {
			return; // 图表类型错误
		}
		var _confs = {
			'PBarLoadingText' : '加载图表中……',
			'XMLLoadingText' : '加载数据中……',
			'ParsingDataText' : '解析数据中……',
			'ChartNoDataText' : '没有数据',
			'RenderingChartText' : '生成图表中……',
			'LoadDataErrorText' : '数据加载异常',
			'InvalidXMLText' : '无效数据'
		};
		var _options = { // 新生成
			swfUrl: $WEB_ROOT_PATH + "/js/FusionCharts/swf/"+ _type +".swf", 
		    dataSource: dataurl, 
		    dataFormat: _format, 
		    renderer: _renderer,
		    registerWithJS: true,
		    width: _width, 
		    height: _height, 
		    id: chartID.toString()
		};
		options && jQuery.extend(_options, options);
		var chart = new FusionCharts(_options);
		chart.configure(_confs);
        chart.render(wrapper);
	};
	
	/**
	 * 取得图表对象
	 * @param id 生成的图表的ID（唯一）
	 */
	this.getCharts = function(id){
		this.initCharts();
		if(id){
			return FusionCharts.items[id];
		}
		return null;
	};

	/**
	 * echarts
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @param options 选项
	 * 
	 * @return obj
	 *
	 * @since 2013-9-24
	 */
	this.echarts = function(obj, options) {
		includeJS('/js/echarts/echarts.min.js');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			if(!jqobj.length) return null;
			var _options = {};
			options && jQuery.extend(_options, options);
			_options.theme = _options.theme || 'blue';
			var chart = echarts.init(jqobj.get(0), _options.theme);
			chart.setOption(_options);
			return chart;
		}
		return obj;
	};
	
	/**
	 * echarts3
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @param options 选项。echarts图表参数，详见官方API文档
	 * @param params 自定义参数（类型object），包括如下：
	 * 		  url 异步加载数据的url地址
	 * 		  data 异步加载数据的请求参数(json格式)
	 * 		  map 加载地图，按需传入文件夹map下的js或者json文件名称，如：china.js或china.json
	 * 		  theme 主题样式：dark|infographic|macarons|roma|shine|vintage，theme布尔判断为false则为默认样式
	 * 		  showLoading 是否显示数据加载动画。传入值不限，布尔判断为true则显示，布尔判断为false则不显示
	 *        chart 当前dom对象的echarts实例
	 *        resize 图表是否自适应window大小变化，前提是dom容器的width属性必须设为百分比值。默认为true。
	 * 		  onLoadSuccess 异步加载模式下的回调函数，将异步加载的数据通过setOption填入options配置项
	 * options 类型为string，表示调用方法（update更新），params.data传入参数，其他参数按需修改
	 * @since 2016-8-25
	 * @author 李飞
	 */
	var objMAP = new Object();
	this.echarts3 = function(obj, options, params) {
		includeJS('/js/echarts3/echarts.min.js');
		var chart = null;
		
		if(obj) {
			var jqobj = getJqueryDomElement(obj);
			if(!jqobj.length) return null;
			var _options = {};
			options && $.extend(_options, options);
			
			var getFileExp = function(file) {
				return file.substring(file.lastIndexOf(".") + 1);
			},
			getFileName = function(file) {
				return file.substring(0, file.indexOf("."));
			},
			createChart = function(target) {
				var params = $.data(target, 'echarts3').params;
				// 初始化echarts实例，并判断是否加载主题
				if (null == params.chart) {
					if (params.theme) {
						includeJS('/js/echarts3/themes/' + params.theme + '.js');
						params.chart = echarts.init(target.get(0), params.theme);
					} else {
						params.chart = echarts.init(target.get(0));
					}
					if (params.resize) {
						function adaption() {
							params.chart.resize();
						}
						window.onresize = function () {
							$.bsui.throttle(adaption);
						};
					}
				}
				chart = params.chart;
				// 显示数据加载动画
				if (params.showLoading) {
					params.chart.showLoading();
				}
				// 判断是否为异步加载
				if (params.url) {
					if (typeof _options == "object" && !$.isEmptyObject(_options)) {params.chart.setOption(_options);}
					$.ajax({url: params.url,data: params.data,dataType: 'json',success: function(data) {
							params.onLoadSuccess.call(params.chart, data);
							if (params.showLoading) {
								params.chart.hideLoading();
							}
						}
					});
				} else {
					params.chart.setOption(_options);
					if (params.showLoading) {
						params.chart.hideLoading();
					}
				}
			},
			methods = {
				update: function(jq, params) {
					return jq.each(function(index) {
						var $this = objMAP[obj][index],
							state = $.data($this, 'echarts3');
						$.extend(state.params, params);
						createChart.call(this, $this);
					});
				}
			};
			
			if (typeof options == 'string') {
				var method = methods[options];
				if (method) {
					return method(jqobj, params);
				}
			}
			
			params = params || {};
			jqobj.each(function(index) {
				var $this = $(this),
					state = $.data($this, 'echarts3');
				if (0 == index) {
					objMAP[obj] = [$this];
				} else {
					objMAP[obj].push($this);
				}
				if (state) {
					$.extend(state.params, params);
				} else {
					state = $.data($this, 'echarts3', {
						params: $.extend({}, {
							url: null,
							data: null,
							map: null,
							theme: null,
							showLoading: false,
							chart: null,
							resize: true,
							onLoadSuccess: function(data){}
						}, params)
					});
				}
				
				if (state.params.map && 'js'==getFileExp(state.params.map)) {
					// JavaScript方式引入地图
					includeJS('/js/echarts3/map/js/' + state.params.map);
					createChart.call(this, $this);
				} else if (state.params.map && 'json'==getFileExp(state.params.map)) {
					// JSON方式引入地图
					$.get($WEB_ROOT_PATH + '/js/echarts3/map/json/' + state.params.map, function(mapJson) {
						echarts.registerMap(getFileName(state.params.map), mapJson);
						createChart.call(this, $this);
					});
				} else {
					createChart.call(this, $this);
				}
				return state.params.chart;
			});
		}
		return chart || obj;
	};

	/**
	 * 取得行图对象
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @since 2013-9-24
	 */
	this.getSparkLine = function(obj){
		includeJS('/js/sparkline/jquery.sparkline.min.js');
		if(obj){
			return getJqueryDomElement(obj);
		} else {
			return null;
		}
	};
	
	/**
	 * 行图
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @param values 加载值
	 * @param options 选项
	 * @since 2013-9-24
	 */
	this.sparkline = function(obj, values, options) {
		includeJS('/js/sparkline/jquery.sparkline.min.js');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {};
			var _values = [];
			values && (_values = values);
			options && jQuery.extend(_options, options);
			jqobj.sparkline(values, _options);
		}
	};
	
	/**
	 * 局部打印
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @param options 选项
	 * 	 mode       | [string]   | ("iframe"),"popup"
	 *	 popHt      | [number]   | (500)             
	 *	 popWd      | [number]   | (400)             
	 *	 popX       | [number]   | (500)             
	 *	 popY       | [number]   | (500)             
	 *	 popTitle   | [string]   | ('')              
	 *	 popClose   | [boolean]  | (false),true
	 * 	 extraCss   | [string]   | ('')
	 *   retainAttr | [string[]] | ["id","class","style"]
	 *   standard   | [string]   | strict, loose, (html5)
	 *   extraHead  | [string]   | ('')
	 * @since 2017-4-11
	 */
	this.printArea = function(obj, options) {
		includeJS('/js/printarea/jquery.printarea.js');
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {};
			options && jQuery.extend(_options, options);
			jqobj.printArea(_options);
		}
	};
	
	/**
	 * 大日历
	 * @param obj dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * @param currDate 显示日期(不填为当日)
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param method 请求方法(默认post)
	 * @param eventClick 每事件点击事件(params: calEvent, jsEvent, view)
	 * @param dayClick 每日点击事件(params: date, jsEvent, view)
	 * @param options 选项
	 * 
	 * @since 2014-08-20
	 */
	this.fullcalendar = function(obj, currDate, url, params, method, eventClick, dayClick, options) {
		includeCSS('/js/fullcalendar/css/fullcalendar.css');
	    includeJS('/js/fullcalendar/moment.min.js');
	    includeJS('/js/fullcalendar/fullcalendar.min.js');
	    includeJS('/js/fullcalendar/lang/zh_cn.js');
	    
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			var _options = {}, 
				_eventClick = eventClick || jQuery.noop,
				_dayClick = dayClick || jQuery.noop;
			var _method = method? method.toUpperCase() : 'POST',
				_currDate = currDate || null;
				_events = {
					url: url,
					type: _method,
					data: params||''
				};
			jQuery.extend(_options, {
			    defaultDate: _currDate,
		        timeFormat: '',
		   	    editable: false,
		        lang: 'zh_cn',
		        dayClick: _dayClick,//params: date, jsEvent, view
		        eventClick: _eventClick,//params: calEvent, jsEvent, view
		   	    aspectRatio: 1.35,
		   	    weekMode: 'liquid',
		        firstDay: 0,
		        events: _events
			});
			options && jQuery.extend(_options, options);
			
			jqobj.fullCalendar(_options);
		}
	};
	
	/**
	 * 设置fullcalendar数据
	 * 
	 * @param obj
	 * @param method
	 * @param url
	 * @param params
	 */
	this.setFullCalendarEvents = function (obj, method, url, params) {
		includeCSS('/js/fullcalendar/css/fullcalendar.css');
	    includeJS('/js/fullcalendar/moment.min.js');
	    includeJS('/js/fullcalendar/fullcalendar.min.js');
	    includeJS('/js/fullcalendar/lang/zh_cn.js');
	    
		if(obj){
			var jqobj = getJqueryDomElement(obj);
			jqobj.fullCalendar({
				events: {
					url: url+'&sss'||'',
					type: method||'GET',
					data: params||''
				}
			});
			jqobj.fullCalendar('refetchEvents');
		}
	};
	
	/**
	 * 取得render模板对象
	 * @param templates String|Object
	 * @param callback回调函数
	 * @since 2016-12-26
	 * @author 李飞
	 */
	this.getJsRender = function(templates, callback) {
		includeJS('/js/render/jsrender.min.js');
		
		if (templates) {
			var tmpl = $.templates(templates);
			if (callback) {
				callback();
			}
			return tmpl;
		}
		return null;
	}
	
	/**
	 * jsrender
	 * @param params 参数（类型object），包括如下： 
	 * 		obj 加载模板内容的dom元素，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象(表单id)
	 * 		tmpl 模板<script type="text/x-jsrender">元素的jquery选择器（类型string）
	 * 		data Object|Array格式数据对象
	 *		options选项，Object|Boolean
	 *		onLoadSuccess回调函数
	 * @since 2016-12-23
	 * @author 李飞
	 */
	this.jsrender = function(params) {
		includeJS('/js/render/jsrender.min.js');
		var defaults = {
			obj: null,
			tmpl: null,
			data: null,
			options: null,
			onLoadSuccess: function() {}
		},
		_options = $.extend({}, defaults, params);
		
		if (_options.tmpl) {
			var jqobj = getJqueryDomElement(_options.tmpl);
			if(!jqobj.length) return null;
			_options.onLoadSuccess.call(this);
			var html =  jqobj.render(_options.data, _options.options); // Render template using data - as HTML string
			
			if (_options.obj) {
				jqobj = getJqueryDomElement(_options.obj);
				if(jqobj.length) {
					jqobj.html(html);
				}
			}
			return html;
		}
		return null;
	}
	
	/**
	 * 消息通知 - bootstrap notify插件
	 * @param message  类型：string
	 * @param settings 类型：Object
	 * 		  element:'body',
	 * 		  position:null,  //static|fixed|relative|absolute|null
	 * 		  type:'info',  //success|info|warning|danger
	 * 		  allow_dismiss:true,
	 * 		  newest_on_top:false,
	 * 		  showProgressbar:false
	 * 		  placement:{
	 * 			from: 'top',  //top|bottom
	 * 			align: 'right',  //left|center|right
	 * 		  },
	 * 		  offset:20,
	 * 		  spacing:10,
	 * 		  z_index:1031,
	 * 		  delay:1500,
	 * 		  timer:1000,
	 * 		  url_target:'_blank',  //_blank|_self|_parent|_top
	 * 		  mouse_over:null,  //pause|null
	 * 		  animate:{
	 * 			enter:'animated fadeInDown',
	 * 			exit:'animated fadeOutUp'
	 * 		  },
	 * 		  onShow:null,
	 * 		  onShown:null,
	 * 		  onClose:null,
	 * 		  onClosed:null,
	 * 		  icon_type:null,
	 * 		  template:'',
	 * @since 2017-5-25
	 */
	this.notify = function(message, settings) {
		includeJS("/js/notify/bootstrap-notify.min.js");
		
		_settings = {
			//type:'success',
			placement:{
				align: 'center'
			},
			delay: 1500,
			mouse_over: 'pause',
			template: '<div data-notify="container" class="col-xs-6 col-sm-4 col-md-3 col-lg-2 alert alert-{0}" role="alert">' +
				'<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
				'<span data-notify="icon"></span> ' +
				'<span data-notify="title">{1}</span> ' +
				'<span data-notify="message">{2}</span>' +
				'<div class="progress" data-notify="progressbar">' +
					'<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
				'</div>' +
				'<a href="{3}" target="{4}" data-notify="url"></a>' +
			'</div>' 
		};
		settings = jQuery.extend({}, _settings, settings);
		
		return $.notify(message, settings);
	}
	
	/**
	 * 初始化CommonUI
	 */
	this._init();
}

/**
 * 获取标签定义在class上的属性值
 * @param obj 对象（dom对象|jQuery对象|标签id）
 * @param propertyName
 * @returns
 */
CommonUI.getClassProperty = function(obj, propertyName){
	return getClassProperty(obj, propertyName);
};

/**
 * 在标签的class上自定义属性（class中添加表达式格式“[p=pvalue]”）
 * @param obj 对象（dom对象|jQuery对象|标签id）
 * @param propertyName 属性名
 * @param propertyValue 属性值
 * @returns
 */
CommonUI.setClassProperty = function(obj, propertyName, propertyValue){
	return setClassProperty(obj, propertyName, propertyValue);
};

/**
 * Ajax的异常处理函数
 * @param status 当前状态码
 * @param error 错误信息
 * @param callback 出错处理事件
 */
function ajaxDebugHandler(xhr, status, error)
{
	if($CommonUI._AJAX_DEBUG) {
		$('.debugshow').append('<tr><td>'+status+'</td><td>'+xhr.status+"|"+error+"|"+xhr.responseText+'</td></tr>');
		$('.debugshow').show();
	}
}

/**
 * Ajax的异常提示函数
 * @param status 当前状态码
 * @param error 错误信息
 * @param callback 出错处理事件
 */
function ajaxExceptionHandler(data)
{
	if(data) {
		if(undefined!=data.cbb) {
			var msg = data.cbb.msg||'';
			var tourl = data.cbb.tourl||'';
			exceptionAction(msg, tourl);
		}
	}
}

/**
 * 异常处理
 * @param error
 * @param toUrl
 */
function exceptionAction(msg, toUrl)
{
	var msg = msg? ''+msg:'';
	var toUrl = toUrl? ''+toUrl:'';
	var type = '';
	var tloc = top===self? location:top.location;
	var ontimer,
	timeout=$CommonUI._DEFAULT_AUTO_CLOSE_TIME;

	if(toUrl) {
		if(/^to:/i.test(toUrl)) {
			toUrl= toUrl.substr(3);
		} else if(/^(http|https|ftp)/i.test(toUrl)) {
			$WEB_ROOT_PATH!==toUrl.substr(0,$WEB_ROOT_PATH.length) && (toUrl = $WEB_ROOT_PATH);
		} else if(/^\//i.test(toUrl)) {
			toUrl = $WEB_ROOT_PATH + toUrl;
		} else {
			toUrl = $WEB_ROOT_PATH;
		}
	}
	if(msg!=null && msg!='') {
		 if(/^ERR\d+$/i.test(msg)) {
			type = 'error';
			msg = _SYS_ERRS&&_SYS_ERRS[msg]? _SYS_ERRS[msg]:"未定义错误";
		} else if(/^MSG\d+$/i.test(msg)) {
			type = 'warning';
			msg = _SYS_ERRS&&_SYS_ERRS[msg]? _SYS_ERRS[msg]:"未定义消息";
		} else {
			type = 'warning';
			msg = msg;
		}
		$CommonUI.showMessage(msg, type, $CommonUI._DEFAULT_TITLE_MESSAGE, 
				timeout, true, true, 
				$CommonUI._DEFAULT_OK_BUTTON_TEXT, function(){
			ontimer && clearTimeout(ontimer);
			toUrl && (tloc.href = toUrl);
		},'','',false);
	}
	if(toUrl) {
		if(msg) {
			ontimer = setTimeout(function(){
				tloc.href = toUrl;
			}, timeout);
		} else {
			tloc.href = toUrl;
		}
	}
}

/**
 * Ajax的成功提示函数
 * @param status 当前状态码
 * @param error 错误信息
 * @param callback 出错处理事件
 */
function ajaxSuccHandler()
{
	if($CommonUI._AJAX_SUCC) {
		$CommonUI.autoCloseCenterMessage('请求成功');
	}
}

/**
 * Ajax的错误提示函数
 * @param status 当前状态码
 * @param error 错误信息
 * @param callback 出错处理事件
 */
function ajaxErrorHandler()
{
	if($CommonUI._AJAX_ERROR) {
		$CommonUI.autoCloseCenterMessage("请求失败","error");
	}
}

/**
 * Ajax的Get方式请求
 * @param url 提交地址
 * @param container 数据所在的dom容器，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
 * @param successHandler 成功处理事件 （可选，一般都要设置）
 * @param errorHandler 出错处理事件 （可选，一般都要设置）
 * @param options jQuery ajax选项，传入后将覆盖默认设置
 * 		（可选，扩展增加自定义属性，skipNames[default=[]],skipHidden[default=false],skipDisabled[default=true]）
 * @param dataSource 提交数据源（支持按优先级进行数据覆盖，优先级：dataSource > formData（container中获取的数据） > options.data）
 */
function getReq(url, container, successHandler, errorHandler, options, dataSource) {
	var _options = {
		url : url,
		async : true,
		dataType : "json", // text,html,script,json
		type : "GET",
		data : {},
		skipNames : [], // 跳过指定name
		skipHidden : false, // 提交hidden值
		skipDisabled : true // 跳过不可见域
	};
	$.extend(_options, options);
	if(container) {
		var backJSON = $CommonUI.loopBlock(container, _options.skipNames, _options.skipHidden, _options.skipDisabled);
		$.extend(_options.data, backJSON||{});
	}
	$.extend(_options.data, dataSource||{});
	return $.ajax(_options).done(ajaxSuccHandler).done(successHandler).done(ajaxExceptionHandler)
			.fail(ajaxDebugHandler).fail(ajaxErrorHandler).fail(errorHandler);
}

/**
 * Ajax的Post方式请求
 * @param url 提交地址
 * @param container 数据所在的dom容器，可以是：dom元素的id | jQuery选择器 | dom对象 | jQuery对象
 * @param successHandler 成功处理事件 （可选，一般都要设置）
 * @param errorHandler 出错处理事件 （可选，一般都要设置）
 * @param options jQuery ajax选项，传入后将覆盖默认设置
 * 		（可选，扩展增加自定义属性，skipNames[default=[]],skipHidden[default=false],skipDisabled[default=true]）
 * @param dataSource 提交数据源（支持按优先级进行数据覆盖，优先级：dataSource > formData（container中获取的数据） > options.data）
 */
function postReq(url, container, successHandler, errorHandler, options, dataSource)
{
	return getReq(url, container, successHandler, errorHandler, $.extend(options, {type: 'POST'}), dataSource);
}

/**
 * Ajax的方式请求（默认get）
 * @param url 提交地址
 * @param data 提交数据源
 * @param successHandler 成功处理事件 （可选，一般都要设置）
 * @param errorHandler 出错处理事件 （可选，一般都要设置）
 * @param options jQuery ajax选项，传入后将覆盖默认设置
 */
function ajaxReq(url, data, successHandler, errorHandler, options)
{
	var _options = {
		url : url,
		async : true,
		dataType : "json",
		type : "GET",
		data : data
	};
	$.extend(_options, options);
	return $.ajax(_options).done(ajaxSuccHandler).done(successHandler).fail(ajaxDebugHandler).fail(ajaxErrorHandler).fail(errorHandler);
}

/**
 * ajax方式加载html内容
 * @param url: 提交地址
 * @param params :json形式参数(也可是CommonUI.loopBlock返回的数据)
 * @param container：加载页面的div id,默认container
 * @param method : post或者get,默认post
 * @param isSync :是否同步,默认true
 */
function ajaxLoadContent(url, params, container, method, isSync) {
	if (!container) {
		container = "container";
	}
	return ajaxReq(
		url, 
		params, 
		function(data){
			$("#" + container).empty();
			$("#" + container).html(data);
		}, 
		function(){
			$CommonUI.alert("信息加载失败！");
		}, 
		{dataType : "html", type : method? method:"POST", async : isSync? true:false}
	);
}

/**
 * 根据按钮权限设置按钮是否显示
 */
var AUTHORITY_MAP = new Object();
function setAuthority(options) {
	var defaultOptions = {
		list: [],
		display: 'hidden',  //disable or hidden
		opacity: '0.3',
		namespace: '',
		url: null,
		load: function(url, param, success, error) {
			$.ajax({
				type: 'get',
				url: url,
				data: param,
				dataType: 'json',
				success: function(data) {
					success(data);
				},
				error: function() {
					error.apply(this, arguments);
				}
			});
		},
	};
	options = $.extend({}, defaultOptions, options);
	
	// 若传入的权限为空，则通过ajax请求获取当前帐户的系统权限
	if (!options.list || !options.list.length) {
		options.load.call(this, options.url, null, function(data) {
			setAuthorityMap(data.list);
			setAuthObjs(options);
			setAuthLevelObjs(options.namespace, 1, options);
		}, function() {
			console.log("当前系统未配置权限或权限加载失败");
		});
	} else {
		setAuthorityMap(options.list);
		setAuthObjs(options);
		setAuthLevelObjs(options.namespace, 1, options);
	}
	
	function setAuthorityMap(list) {
		for (var i=0; i<list.length; i++) {
			var securityUrl = null;
			if (typeof list[i] == "string") {
				securityUrl = list[i];
			} else if (typeof list[i] == "object") {
				securityUrl = list[i].securityUrl;
			}
			
			if (typeof AUTHORITY_MAP[securityUrl] === "undefined") {
				AUTHORITY_MAP[securityUrl] = true;
			} 
		}
	}
}

/**
 * 获取设置权限的linkbutton按钮及无层次关系的dom元素
 */
function setAuthObjs(options) {
	// 处理非树结构dom的权限
	var authorizedObjs = $(document).find("[authority]:not([authority*=':'])");
	for (var i=0; i<authorizedObjs.length; i++) {
		var t = $(authorizedObjs[i]);
		var authority = t.attr('authority');
		
		if (!AUTHORITY_MAP[authority]) {
			setObjsAppear(t, options.display, options.opacity);
		}
	}
	
	// 处理linkbutton组件权限
	var authorizedBtns = $(document).data('authorizedBtns') || {};
	for (var key in authorizedBtns) {
		if (!AUTHORITY_MAP[authorizedBtns[key]]) {
			var curObj = $("#"+key);
			setObjsAppear(curObj, options.display, options.opacity);
		}
	}
}

/**
 * 扫描设置了权限的具有层次关系的dom元素，并根据权限设置显示效果
 */
function setAuthLevelObjs(namespace, level, options) {
	var authorizedObjs = null;
	if (1 == level) {
		authorizedObjs = $(document).find("[authority*='L1']");
	} else {
		authorizedObjs = $(document).find("[authority*='L"+level+"'][authority^='"+namespace+"']");
	}
	
	for (var i=0; i<authorizedObjs.length; i++) {
		var t = $(authorizedObjs[i]);
		var authority = t.attr('authority').split(':')[2];
		
		if (!AUTHORITY_MAP[authority]) {
			setObjsAppear(t, options.display, options.opacity);
		} else {
			setAuthLevelObjs(t.attr('authority').split(':')[0]+'-', parseInt(level)+1, options);
		}
	}
}

// 设置无权限的linkbutton组件或dom元素的显示效果
function setObjsAppear(target, display, opacity) {
	if ('disable' == display) {
		if (target.hasClass("bsui-linkbutton")) {
			target.linkbutton(display);
		} else {
			setDisabled(target, opacity);
		}
	} else if ('hidden' == display) {
		target.hide();
	}
}

// 将无权限的dom及其子元素均设为disabled
function setDisabled(target, opacity) {
	target.removeAttr('onclick').unbind().bind('click.authority', function() {
		$CommonUI.alert("您没有该操作权限！");
	}).css({'opacity':opacity, 'cursor':'default'});
	target.children().removeAttr('onclick').unbind().css('cursor', 'default');
}

/**
 * 批量获取页面字典数据
 */
function batDicts(opts)
{
	// action
	var url = '';
	// type
	var type = 'public';
	if(!!opts) {
		if(!!opts.url) {
			url = opts.url;
		}
		if(!!opts.type) {
			type = opts.type;
		}
	}
	var dicts = [];
	var pageCombos = $(document).data("pageCombos")||{};
	$.each(pageCombos, function(i, n){
		if(i && n) {
			var td = i.split('^');
			if(2 === td.length) {
				if(td[0]===type && -1===$.inArray(td[1], dicts)) {
					dicts.push(td[1]);
				}
			}
		}
	});
	if(!!url && dicts.length > 0) {
		// req params
		var params = '{"ids": "'+dicts.join(",")+'","dicts": "'+dicts.join(",")+'"}';
		// post request
		ajaxReq(url, params, '', '',{
			'type' : 'POST',
			'contentType':'application/json; charset=UTF-8'
		}).done(function(data){
			$.each(dicts, function(i,n){
				if(!!pageCombos[type+'^'+n] 
					&& !pageCombos[type+'^'+n]['getted'] 
					&& !!data[n]) {
					pageCombos[type+'^'+n]['getted'] = true;
					pageCombos[type+'^'+n]['data'] = data[n];
					$.each(pageCombos[type+'^'+n]["list"], function(){
						// 更新页面combo
						$(this).combobox('loadData', data[n]);
					});
				}
			});
		});
	}
}

/**
 * 一次设置页面上所有下拉列表
 */
function batCombo()
{
	batDicts({
		type: 'public',
		url: $CommonUI._PUBLIC_DICT_SERVICE_URL
	});
	batDicts({
		type: 'custom',
		url: $CommonUI._CUSTOM_DICT_SERVICE_URL
	});
}

/**
 * 改变页面皮肤
 * @param theme
 */
function useTheme(theme)
{
	theme = theme || $CommonUI._BSUI_DEFAULT_THEMES;
	if(theme == bsuiloader.theme) return;
	var newer = ''+theme,newWrap = '/'+newer+'/';
	var older = ''+bsuiloader.theme,oldWrap = '/'+older+'/';
    INCLUDE_CSS_MAP = {};//init css map
    var newList = [];
    $("style[styleid]").each(function(){
    	var cssURL = ''+$(this).attr('styleid');
    	if(cssURL.indexOf(oldWrap) > -1) {
    		newList.push(cssURL.replace(oldWrap, newWrap));
    	} else if(cssURL.indexOf('/themes/main.css') > -1 
    				|| cssURL.indexOf('/themes/icon') > -1) {
    		//ignore
    	} else {
    		newList.push(cssURL);
    	}
    });
	$("style[styleid]").remove();
	includeCSS("/js/bsui/themes/main.css");
	$CommonUI._useIcon(theme);
	newList.reverse();
    for(var i=0;i<newList.length;i++) {
    	includeCSS(newList[i]);
    }
	bsuiloader.theme = newer;
	if(location === top.location) {
		var iframes = $("iframe");
		for(var i=0;i<iframes.length;i++) {
			if('undefined'!==typeof window.frames[i]["useTheme"]) {
				window.frames[i]["useTheme"](newer);
			}
		}
	}
	//cookie
	addCookie($CommonUI._BSUI_THEMES_CNAME, newer, $CommonUI._BSUI_THEMES_DELAY);
}


/*====================================================================
								CommonUI
======================================================================*/
/**
 * CommonUI默认实例
 */
var $CommonUI = new CommonUI();
// CONFIG START
$CommonUI._PUBLIC_DICT_SERVICE_URL = $WEB_ROOT_PATH + "/dhccApi/dictService/pubBatchLoad";
$CommonUI._CUSTOM_DICT_SERVICE_URL = $WEB_ROOT_PATH + "/dhccApi/dictService/batchLoad";
$CommonUI._AJAX_DEBUG = false; // 开启AJAX的DEBUG提示功能
$CommonUI._AJAX_SUCC = false;  // 开启AJAX的默认成功提示
$CommonUI._AJAX_ERROR = false; // 开启AJAX的默认失败提示
var routeLogin, actionNoAuth;//ajax错误处理函数
routeLogin = function() {
	top.location.href = $WEB_ROOT_PATH + '/login.htm';
};
(function(){
// ajax全局设置
function urlInIgnoreList(url) {
	var list=['css', 'js'];
	for(var i=0;i<list.length;i++) {
		var expr = new RegExp('\\.'+list[i]+'$', 'i');
		if(expr.test(url)) {
			return true;
		}
	}
	return false;
}
$CommonUI._AJAX_RESCODE = $.extend({}, $CommonUI._AJAX_RESCODE, {
	403: function(res) {
		//UNAUTHORIZED
		var self = this;
		if(urlInIgnoreList(self.url)) return true;
		if ('UNAUTHORIZED' === res.statusText) {
			var commui = top.$CommonUI? top.$CommonUI : $CommonUI;
			commui.showMessage('没有该操作相应权限，请检查后再试', 'warning', '没有权限', 0, false, true, '确定', function(){
				if(undefined !== actionNoAuth)
					actionNoAuth.call(window, self);
					
			}, '', null, false);
		} else if ('Forbidden' === res.statusText) {
			var commui = top.$CommonUI? top.$CommonUI : $CommonUI;
			var msg="访问被拒绝,无访问权限!";
			if(null!=res.responseText&&res.responseText.length>0){
				msg=res.responseText;
			}
			commui.showMessage(msg, 'warning', '没有权限', 0, false, true, '确定', function(){
				if(undefined !== actionNoAuth)
					actionNoAuth.call(window, self);
					
			}, '', null, false);
		} else{
			$CommonUI.alert(res.statusText, 'error', res.status);
		}
	},
	404: function(res) {
		//404
		var self = this;
		if(urlInIgnoreList(self.url)) return true;
		$CommonUI.alert(res.statusText, 'error', res.status);
	},
	504: function(res) {
		//overDue
		var self = this;
		if(urlInIgnoreList(self.url)) return true;
		if ('overDue' === res.statusText) {
			var commui = top.$CommonUI? top.$CommonUI : $CommonUI;
			commui.showMessage('请重新登录', 'warning', '登录过期', 0, false, true, '确定', function(){
				if(undefined !== routeLogin)
					routeLogin.call(window, self);
					
			}, '', null, false);
		} else {
			$CommonUI.alert(res.statusText, 'error', res.status);
		}
	}
});
jQuery.ajaxSetup({
	statusCode : $CommonUI._AJAX_RESCODE
});
})();
var _SYS_ERRS = { //变量名唯一
	"ERR001":"系统错误，请联系管理员"
};
// CONFIG END

$(function(){
	// 非IE浏览器开启默认文本功能
	if (!/msie/.test(navigator.userAgent.toLowerCase())) {
		$CommonUI.placeholder();
	}
	// DEBUG
	if($CommonUI._AJAX_DEBUG) {
		$('body').prepend('<table class="debugshow" style="display:none;"><tr><th>status</th><th>exception</th></tr></table>');
	}
	//根据权限控制按钮显示
	//setAuthority();
	// 一次获取页面所有下拉列表
	batCombo();
	// 恢复显示
	if(this._SHOW_LOADING && _timerLoading) {
		clearTimeout(_timerLoading);
		setTimeout(_showBodyElement, 0);
	}
});
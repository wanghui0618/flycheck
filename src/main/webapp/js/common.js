/**
 * web应用根路径
 */
var $WEB_ROOT_PATH = getContextPath();

/**
 * 设置jQuery以传统方式解析提交的数据
 */
jQuery.ajaxSettings.traditional = true;

/**
 * 换行符
 */
var CRLF = "\n";

/**
 * JS加载记录MAP
 */
var INCLUDE_JS_MAP = new Object();

/**
 * CSS加载记录MAP
 */
var INCLUDE_CSS_MAP = new Object();

/**
 * JS缓存
 */
var INCLUDE_CACHE = true;

/**
 * bodyElement
 */
var _bodyElement = document.body || document.documentElement;

/**
 * loading timer
 */
var _timerLoading;
function _showBodyElement() {
	$(_bodyElement).removeClass("body-mask-big");
	if(''===$CommonUI._styleBodyElement) {
		$(_bodyElement).removeAttr('style');
	} else {
		$(_bodyElement).attr('style', $CommonUI._styleBodyElement);
	}
	if(''===$(_bodyElement).attr('class'))
		$(_bodyElement).removeAttr('class');
	setTimeout(function(){$(_bodyElement).css('visibility', 'visible');},0);
}

/**
 * 为string添加startWith方法
 * @param str
 * @returns
 */
String.prototype.startWith = function(str){
	var regexp = eval("/^" + str + "/");
	return regexp.test(this);
};

/**
 * 日期域
 * @param field 域（年、月、日、时、分、秒）
 * @param fieldValue
 * @class
 */
function DateField(field, fieldValue){
	this.field = field;//域（年、月、日、时、分、秒）
	this.fieldValue = fieldValue;//域值
	
	this.toString = function(){
		return "field=" + this.field + ", fieldValue=" + this.fieldValue;
	};
}

/**
 * 年
 */
DateField.prototype.YEAR = "Y";

/**
 * 月
 */
DateField.prototype.MONTH = "M";

/**
 * 日
 */
DateField.prototype.DATE = "D";

/**
 * 时
 */
DateField.prototype.HOUR = "H";

/**
 * 分
 */
DateField.prototype.MINUTE = "MIN";

/**
 * 秒
 */
DateField.prototype.SECOND = "S";

/**
 * 获取日期
 * @param expr 表达式（可以是字符串（日期表达式）、数字（毫秒值）、或者Date）
 * @param delimiter （仅当expr为字符串时此参数生效）
 * @returns Date
 */
function getDate(expr, delimiter){
	var result = null;
	if(null != expr){
		if(typeof(expr)== "string"){
			var _delimiter = "-";
			if(delimiter){
				_delimiter = delimiter;
			}
			var arr = expr.split(_delimiter);
			result = new Date(arr[0], (parseInt(arr[1], 10) - 1), arr[2]);
		}else if(expr instanceof Date){
			result = expr;
		}else if(!isNaN(expr)){
			result = new Date(expr);
		}
	}
	
	return result;
}

/**
 * 日期加减
 * @param date 要加减的日期 
 * @param field 要加减的域（年、月、日、时、分、秒，参考DateField常量）
 * @param value 域的值
 */
function dateAdd(date, field, value){
	if(date && field && value && !isNaN(value)){
		var val;		
		if("number" == typeof(value)){
			val = value;
		}else if("string" == typeof(value)){
			val = parseInt(value);			
		}else{
			throw "dateAdd: param value should be an integer";
		}
		if(DateField.prototype.YEAR == field){
			date.setFullYear(date.getFullYear() + val);
		}else if(DateField.prototype.MONTH == field){
			date.setMonth(date.getMonth() + val);
		}else if(DateField.prototype.DATE == field){
			date.setDate(date.getDate() + val);
		}else if(DateField.prototype.HOUR == field){
			date.setHours(date.getHours() + val);
		}else if(DateField.prototype.MINUTE == field){
			date.setMinutes(date.getMinutes() + val);
		}else if(DateField.prototype.SECOND == field){
			date.setSeconds(date.getSeconds() + val);
		}
	}	
}

/**
 * 比较两个日期
 * @param date1 表达式（可以是字符串（日期表达式）、数字（毫秒值）、或者Date）
 * @param date2 表达式（可以是字符串（日期表达式）、数字（毫秒值）、或者Date）
 * @returns true: date1 > date2, false: date1 < date2, null: date1或date2不是一个有效的日期表达式
 */
function compareDate(date1, date2){
	var result = null;
	
	var d1 = getDate(date1);
	var d2 = getDate(date2);
	
	if(d1 != null && d2 != null){
		return d1.getTime() > d2.getTime();
	}
	return result;
}

/**
 * 日期相减，返回两个日期相差的天数，日期不分前后
 * @param date1 可以是字符串（yyyy-mm-dd）、数字、或Date
 * @param date2 可以是字符串（yyyy-mm-dd）、数字、或Date
 * @param delimiter 日期的分隔符，默认为“-”
 * @returns 返回两个日期相差的天数
 */
function dateDiff(date1, date2, delimiter){
	var result = null;
	
	var sRDate = getDate(date1, delimiter);
	var eRDate = getDate(date2, delimiter);
		
	if(null != sRDate && null != eRDate){
		if(sRDate.getTime() > eRDate.getTime()){
			result = (sRDate - eRDate)/(24 * 60 * 60 * 1000);
		}else{
			result = (eRDate - sRDate)/(24 * 60 * 60 * 1000);
		}
	}
	
	return result;
}

/**
 * 简单日期格式化
 * @param date 日期
 * @param delimiter 分隔符，可选，默认为“-”
 * @returns 返回形式如“yyyy-mm-dd”的格式
 */
function simpleDateFormat(date, delimiter){
	var result = null;
	if(null != date){
		var _delimiter = "-";
		if(delimiter){
			_delimiter = delimiter;
		}
		var month = date.getMonth() + 1;
		return date.getFullYear() + _delimiter + 
			(month > 9 ? month : "0" + month) + _delimiter + 
			(date.getDate() > 9 ? date.getDate() : "0" + date.getDate());
	}
	
	return result;
}

/**
 * 获得应用的根路径
 */
function getContextPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	var basePath = prePath;
	//if(canBeAccess(prePath + postPath)){
		basePath = prePath + postPath;
	//}
	return basePath;
}

/**
 * 判断资源是否可被访问
 * @param url 资源路径
 * @returns {Boolean}
 */
function canBeAccess(url){
	var result = false;
	jQuery.ajax({
		url: url,
		async: false,
		complete: function(xhr, status){
			if(xhr.status == 200){
				result= true;
			}
		},
		timeout: 2000 //2秒超时
	});
	return result;
}

/**
 * 加载JS文件
 * @param filePath
 * @author：张磊
 * @since：2013-7-22
 */
//var lastIncludeJSURL='';
function includeJS(filePath, force){
	var reURL=filePath;
	if(!(/^http/i.test(filePath))){
		reURL = $WEB_ROOT_PATH+filePath;
	}
//	if(lastIncludeJSURL && lastIncludeJSURL === reURL) {
//		return false;
//	}
	if(force) INCLUDE_JS_MAP[reURL] = false;
	if(undefined === INCLUDE_JS_MAP[reURL]) {
		INCLUDE_JS_MAP[reURL] = false;
	}
	if(true === INCLUDE_JS_MAP[reURL]){
		return;//loaded
	}
	jQuery.ajax({
		cache: INCLUDE_CACHE,
		async: false,
		url:reURL,
		dataType:'script',
		scriptCharset: "utf-8"
	});
	INCLUDE_JS_MAP[reURL] = true;
//	lastIncludeJSURL = reURL;
}

/**
 * 加载css文件
 * @param filePath 相对web根目录的路径
 * @author：张磊
 * @since：2013-7-22
 */
function includeCSS(filePath, force){
	var reURL=filePath;
	if(!(/^http/i.test(filePath))){
		reURL = $WEB_ROOT_PATH+filePath;
	};
	if(force) INCLUDE_CSS_MAP[reURL]=false;
	if(undefined === INCLUDE_CSS_MAP[reURL]) {
		INCLUDE_CSS_MAP[reURL] = false;
	}
	if(true === INCLUDE_CSS_MAP[reURL]){
		return;//loaded
	}
	var _sid = reURL.substr($WEB_ROOT_PATH.length);
	INCLUDE_CSS_MAP[reURL]=true;
	jQuery.ajax({
		cache: INCLUDE_CACHE,
		async: false,
		url:reURL,
		dataType:'html',
		success: function(d) {
			if(/['"]?([_\-a-zA-Z0-9\.]+)\.(png|jpg|jpeg|gif)['"]?/i.test(d)){
				var xdp=filePath.substr(0, filePath.lastIndexOf('/'));
				var prefixUrl = $WEB_ROOT_PATH + xdp;
				if(/^http/i.test(xdp)) {
					prefixUrl = xdp;	
				}
				d = d.replace(/['"]?([_\/\\\-a-zA-Z0-9\.]+)\.(png|jpg|jpeg|gif|bmp)['"]?/ig,
				'"'+prefixUrl+"/$1.$2\"");
			}
			var styleTag = '<style type="text/css" styleid="'+_sid+'">'+d+'</style>';
			var iconLinkOBJ = jQuery("style[styleid$='/bsui/themes/icon.css']");
			if(iconLinkOBJ.length) {
				iconLinkOBJ.before(styleTag);
			} else if(-1!==_sid.indexOf('/bsui/')) {
				jQuery("head:first").prepend(styleTag);
			} else {
				jQuery("head:first").append(styleTag);
			}
		}
	});
}

/**
 * 获取标签定义在class上的属性值
 * @param obj 对象（dom对象|jQuery对象|标签id）
 * @param propertyName
 * @returns
 */
function getClassProperty (obj, propertyName){
	var result = "";
	if(obj){
		var _obj;
		if("string" == typeof(obj)){
			_obj = document.getElementById(obj);
		}else if("object" == typeof(obj)){
			_obj = (obj instanceof jQuery) ? obj[0] : obj; 
		}
		if(_obj && _obj.className){
			var regex = eval("/\\[\\s*?" + propertyName + "\\s*?=\\s*?([^\\]]*)\\s*?\\]/");//动态正则表达式
			var matches = _obj.className.match(regex);
			if(matches){
				result = matches[1];
			}
		}
	}
	return result;
}

/**
 * 在标签的class上自定义属性（class中添加表达式格式“[p=pvalue]”）
 * @param obj 对象（dom对象|jQuery对象|标签id）
 * @param propertyName 属性名
 * @param propertyValue 属性值
 * @returns
 */
function setClassProperty (obj, propertyName, propertyValue){
	var _obj;
	if(obj){
		if("string" == typeof(obj)){
			_obj = jQuery("#" + obj);
		}else if("object" == typeof(obj)){
			_obj = (obj instanceof jQuery) ? obj : jQuery(obj); 
		}
		if(_obj){
			_obj.addClass("[" + propertyName + "=" + propertyValue + "]");
		}
	}
	return _obj;
}

/**
 * 获取jQuery包装过的dom对象
 * @param obj
 * @returns
 */
function getJqueryDomElement(obj){
	var value = null;
	if(obj){
		var type = typeof(obj);
		if("string" == type){
			var _obj = jQuery.trim(obj);
			if( _obj.indexOf("#") > -1 || 
				_obj.indexOf(".") > -1 && /^\./i.test(_obj) ||
				_obj.indexOf(":") > -1 ||
				_obj.indexOf(" ") > -1 ||
				_obj.indexOf("=") > -1 ||
				_obj.indexOf("[") > -1){//简单判断是否是jQuery选择器
				value = jQuery(_obj);
			}else{
				_obj = _obj.replace(/\./g,'\\.');
				value = jQuery("#" + _obj);
			}
		}else if("object" == type){
			if(obj instanceof jQuery){
				value = obj;
			}else{
				value = jQuery(obj);
			}
		}else if("function" == type){
			value = obj();
		}
	}
	return value;
}

/**
 * 获取dom元素值
 * @param obj dom元素的id | jQuery选择器 | dom对象 | jQuery对象
 * @returns 没有获取到值时返回null
 */
function getDomElementValue(obj){
	var value = null;
	if(obj){
		var type = typeof(obj);
		if("string" == type){
			var _obj = jQuery.trim(obj);
			if( _obj.indexOf("#") > -1 || 
				_obj.indexOf(".") > -1 ||
				_obj.indexOf(":") > -1 ||
				_obj.indexOf(" ") > -1 ||
				_obj.indexOf("=") > -1 ||
				_obj.indexOf("[") > -1){//简单判断是否是jQuery选择器
				
				value = jQuery(_obj).val();
			}else{
				value = jQuery("#" + _obj).val();
			}
		}else if("object" == type){
			if(obj instanceof jQuery){
				value = obj.val();
			}else{
				value = jQuery(obj).val();
			}
		}else if("function" == type){
			value = obj();
		}
	}
	return value;
}

/**
 * 获取日期整形字符串
 * @param date1 日期对象或日期字符串（支持yyyy-mm-dd、yyyy/mm/dd、yyyymmdd）
 * @returns {Date}
 */
function getDateIntStr(date1){
	var d1 = null;
	if(date1 instanceof Date){
		var d1 = new Date();
		var month = date1.getMonth() + 1;
		var date = date1.getDate();
		d1 = "" + date1.getFullYear() + (month > 9 ? month : '0' + month) +  (date > 9 ? date : '0' + date);
	}else if("string" == typeof(date1)){
		if(/^\d{4}-\d{2}-\d{2}$/.test(date1)){
			d1 = date1.replace(/-/g, "");
		}else if(/^\d{4}\/\d{2}\/\d{2}$/.test(date1)){
			d1 = date1.replace(/\//g, "");
		}else if(/^\d{8}$/.test(date1)){
			d1 = date1;
		}
	}
	return d1;
}

/**
 * 比较两个日期
 * @param date1
 * @param date2
 * @returns {Number} -2:传入了为null的参数，-1:date1 < date2，0:date1 = date2，1:date1 > date2
 */
function dateCompare(date1, date2){
	var result = -2;
	if(date1 && date2){
		var d1 = getDateIntStr(date1);
		var d2 = getDateIntStr(date2);
		if(null != d1 && null != d2){
			var d1Int = parseInt(d1);
			var d2Int = parseInt(d2);
			
			if(d1Int > d2Int){
				result = 1;
			}else if(d1Int == d2Int){
				result = 0;
			}else{
				result = -1;
			}
		}
	}
	return result;
}

/**
 * 添加cookie变量
 * @param name 变量名
 * @param value 变量值
 * @param expires 过期时间（天）
 * @param path 指定可访问cookie的路径
 */
function addCookie(name, value, expires, path){
	var str = name + "=" + escape(value);
	if(expires){
		var date = new Date();
		date.setTime(date.getTime() + expires * 24 * 3600 * 1000);//expires单位为天
		str += ";expires=" + date.toGMTString();
	}
	if(path){
		str += ";path=" + path;//指定可访问cookie的路径
	}
	document.cookie = str;
}

/**
 * 获取cookie
 * @param cookie_name 变量名
 * @returns
 */
function getCookie(cookie_name){
	var value = null;
	var allcookies = document.cookie;
	var cookie_pos = allcookies.indexOf(cookie_name);
	
	if (cookie_pos != -1){		
		cookie_pos += cookie_name.length + 1;
		var cookie_end = allcookies.indexOf(";", cookie_pos);
		if (cookie_end == -1)
		{
			cookie_end = allcookies.length;
		}
		value = unescape(allcookies.substring(cookie_pos, cookie_end));
	}

	return value;
}

/**
 * 删除cookie
 * @param name 变量名
 */
function delCookie(name){//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
	addCookie(name, "0", -1, "/");
}

/**
 * 事件信息类
 * @class
 */
function EventInfo(){
	/**
	 * 键码
	 */
	this.keyCode = null;
	
	/**
	 * 事件源对象
	 */
	this.srcObj = null;
	
	/**
	 * 事件类型
	 */
	this.type = null;
}
/**
 * 获取事件信息
 * @param event
 * @returns {EventInfo}
 */
EventInfo.getEventInfo = function(event){
	var result = new EventInfo();
	var keyCode;
	var srcObj;
	var type;
	var userAgent = navigator.userAgent.toLowerCase();
	
	/*if(jQuery.browser.msie){*/
	if(/msie/.test(userAgent)&&!/opera/.test(userAgent)){
		keyCode = event.keyCode;
		srcObj = event.srcElement;
	}else{
		keyCode = event.which;
		srcObj = event.target;
	}
	
	result.type = event.type;
	result.keyCode = keyCode;
	result.srcObj = srcObj;
	return  result;
};

/**
 * 自适应iframe高度
 * @param iframe 可以是：iframe元素的id | jQuery选择器 | dom对象 | jQuery对象
 */
function autoHeightIFrame(iframe){
	var jqIFrame = getJqueryDomElement(iframe);
	
	if(jQuery.browser.chrome){
		jqIFrame.height(jqIFrame.contents().find("body")[0].scrollHeight + 50);
	}else{
		jqIFrame.height(jqIFrame.contents().find("body")[0].clientHeight + 50);
	}
}

/**
 * 日期格式化
 * @see yyyy-MM-dd HH:mm:ss
 */
Date.prototype.format = function(mask) {
	var d = this;
	var zeroize = function(value, length) {
	if (!length)
		length = 2;
	value = String(value);
	for ( var i = 0, zeros = ''; i < (length - value.length); i++) {
		zeros += '0';
	}
	return zeros + value;
	};
	return mask
		.replace(
			/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g,
			function($0) {
			switch ($0) {
			case 'd':
				return d.getDate();
			case 'dd':
				return zeroize(d.getDate());
			case 'ddd':
				return [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri',
					'Sat' ][d.getDay()];
			case 'dddd':
				return [ 'Sunday', 'Monday', 'Tuesday',
					'Wednesday', 'Thursday', 'Friday',
					'Saturday' ][d.getDay()];
			case 'M':
				return d.getMonth() + 1;
			case 'MM':
				return zeroize(d.getMonth() + 1);
			case 'MMM':
				return [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
					'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ][d
					.getMonth()];
			case 'MMMM':
				return [ 'January', 'February', 'March', 'April',
					'May', 'June', 'July', 'August',
					'September', 'October', 'November',
					'December' ][d.getMonth()];
			case 'yy':
				return String(d.getFullYear()).substr(2);
			case 'yyyy':
				return d.getFullYear();
			case 'h':
				return d.getHours() % 12 || 12;
			case 'hh':
				return zeroize(d.getHours() % 12 || 12);
			case 'H':
				return d.getHours();
			case 'HH':
				return zeroize(d.getHours());
			case 'm':
				return d.getMinutes();
			case 'mm':
				return zeroize(d.getMinutes());
			case 's':
				return d.getSeconds();
			case 'ss':
				return zeroize(d.getSeconds());
			case 'l':
				return zeroize(d.getMilliseconds(), 3);
			case 'L':
				var m = d.getMilliseconds();
				if (m > 99)
				m = Math.round(m / 10);
				return zeroize(m);
			case 'tt':
				return d.getHours() < 12 ? 'am' : 'pm';
			case 'TT':
				return d.getHours() < 12 ? 'AM' : 'PM';
			case 'Z':
				return d.toUTCString().match(/[A-Z]+$/);
				// Return quoted strings with the surrounding quotes removed     
			default:
				return $0.substr(1, $0.length - 2);
			}
			});
};
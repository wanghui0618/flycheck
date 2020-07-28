/**
 * 校验js
 */
//==============================校验=========================

/**
 * 校验函数，校验成功返回true，失败返回false并显示提示信息
 * @param tipSelector 提示信息选择器，默认为#tip
 * @param dom dom对象（可选，默认为当前document，类型：jQuery选择器|jQuery对象|dom对象）
 * @returns {Boolean} 校验成功返回true，失败返回false
 */
function doValidate(tipSelector, dom){
	var message = "";
	var result = true;
	var domObj = null;
	
	if("string" == typeof(dom)){
		domObj = jQuery(dom);
	}else if("object" == typeof(dom)){
		domObj = dom;
	}else{
		domObj = document;
	}
	
	//验证文本、文本框、下拉列表
	var requiredElements = jQuery(":text[class*='[required]']", domObj)
	.add(":password[class*='[required]']", domObj)
	.add("textarea[class*='[required]']", domObj)
	.add("select[class*='[required]']", domObj);
	requiredElements.each(function(){
		var tag = this;
		var tagName = tag.tagName.toUpperCase();
		var value;
		if("INPUT" == tagName){
			var tagType = tag.type.toUpperCase();
			if("TEXT" == tagType || "PASSWORD" == tagType){
				value = jQuery.trim(tag.value);
				if(!value){//未输入文本
					message = tag.title;
					result = false;
				}
			}			
		}else if("TEXTAREA" == tagName){
			value = jQuery.trim(tag.value);
			if(!value){//未输入文本
				message = tag.title;
				result = false;
			}
		}else if(tagName == "SELECT"){
			value = tag.value;
			if( value == "" || (!isNaN(value) && parseInt(value) <= 0)){//select值为0 或小于0的则认为是未选择
				message = tag.title;
				result = false;
			}
		}
		if(!result){
			return false;
		}
	});
	
	//验证单选按钮
	if(result){
		var requiredRadios = jQuery(":radio[class*='[required]']", domObj);
		var radioNames = new Object();
		requiredRadios.each(function(){
			var radio = this;
			radioNames[radio.name] = radio.title ? radio.title : "";//记录radio的name属性 和title属性
		});
		//循环去验证每组radio
		for(p in radioNames){
			if(jQuery(":radio[name='" + p + "'][checked=true]", domObj).length == 0){//无选中的radio
				result = false;
				message = radioNames[p];
				break;
			};
		}
	}
	
	if(!result){
		//top.CommonUI.alert(message, tipSelector);
		alert(message);
	}
	return result;
}

/**
 * 显示校验信息
 * @param messgae 消息
 * @param tipSelector 提示信息元素选择器
 */
function showValidationTip(message, tipSelector){
//	var _selector = tipSelector ? tipSelector : "#tip";
//	var tip = jQuery(_selector);
	if(message){
//		tip.html(message);
//		tip.show();
		
	}else{
		//tip.hide();
	}
}

/**
 * 数字检查
 * @param str 要检查的字符串
 * @param allowNegative 是否允许负数（默认为false，允许负数的情况下，也允许正数前有“+”号）
 * @param fractionDigits 最多小数位数，整数
 * @returns 满足条件返回true，否则返回false
 */
function numberCheck(str, allowNegative, fractionDigits){
	var result = false;
	var regexStr = "";
	var regex;
	var _allowNegative = allowNegative ? allowNegative : false;
	var _fraction = "";
	var symbols = "";
	if(_allowNegative){
		symbols = "[+-]{0,1}"; 
	}else{
		if(null == fractionDigits){
			regexStr = "/^" + symbols + "\\d+\\.\\d+$|^" + symbols + "\\d+$/";
		}else if(0 == fractionDigits){
			regexStr = "/^" + symbols + "\\d+$/";
		}else if(fractionDigits > 0){
			regexStr = "/^" + symbols + "\\d+\\.\\d{1," + fractionDigits + "}$|^" + symbols + "\\d+$/";
		}
	}
	
	regex = eval(regexStr);	
	if(str){
		result = regex.test(str);
	}
	return result;
}

/**
 * 只允许输入数字的事件验证
 * @param inputObj 文本框对象
 * @param errMsg 错误提示消息
 * @param allowNegative  是否允许负数
 * @param fractionDigits  小数位数，整数
 * @returns
 */
function onlyNumberEvent(event, allowNegative, fractionDigits){
	var result = true;
	var keyChar = "";
	var eventInfo = EventInfo.getEventInfo(event);
	var keyCode = eventInfo.keyCode;
	var inputObj = eventInfo.srcObj;
	
	if( 46 == keyCode ||//delete
		45 == keyCode ||//insert
		35 == keyCode ||//end
		36 == keyCode ||//home
		37 == keyCode ||//LeftArrow
		39 == keyCode ||//RightArrow
		8 == keyCode){//BackSpace
		
		if(0 == fractionDigits && 46 == keyCode){//小数位数为0不允许输入小数点
			result = false;
		}
	}else{
		keyChar = String.fromCharCode(keyCode);
		if(inputObj){
			result = numberCheck(inputObj.value + keyChar, allowNegative, fractionDigits);
			if(!result){
				result = false;
			}
		}
	}
	return result;
}

/**
 * 检查URL格式是否正确
 * @param str_url url字符串
 * @returns 是URL返回true，否则返回false
 */
function urlCheck(str_url){
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
	+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
	+ "(([0-9]{1,3}.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
	+ "|" // 允许IP和DOMAIN（域名）
	+ "([0-9a-z_!~*'()-]+.)*" // 域名- www.
	+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]." // 二级域名
	+ "[a-z]{2,6})" // first level domain- .com or .museum
	+ "(:[0-9]{1,4})?" // 端口- :80
	+ "((/?)|" // a slash isn't required if there is no file name
	+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	
	var re=new RegExp(strRegex, "i");  
	return !/^\w+$/.test(str_url) && //排除纯数字和字母组合
			re.test(str_url);
}

/**
 * 验证固定电话号码格式
 * @param str
 * @returns {Boolean}
 */
function fixedPhoneCheck(str){
	var flag = /(^[0-9]{7,8}$)|(^0[0-9]{1,3}-[0-9]{7,8}$)|(^0[0-9]{1,3}-[0-9]{7,8}-[0-9]{1,4}$)/.test(str);
	if(!flag){
		var hk = "00852";
		var mo = "00853";
		var tw = "00886";
		if( str.startWith(hk) || 
			str.startWith(mo) || 
			str.startWith(tw)){//支持港澳台固话
			flag = /(^[0-9]{5}-[0-9]{7,8}$)|(^[0-9]{5}-[0-9]{7,8}-[0-9]{1,4}$)/.test(str);
		}
	}
	
	return flag;
}

/**
 * 验证手机号码格式
 * @param str
 * @returns {Boolean}
 */
function mobilePhoneCheck(str){
	var flag = false;
	var unicom = [130, 131, 132, 145, 155, 156, 185, 186];
	var cmcc = [134, 135, 136, 137, 138, 139, 147, 150, 151, 152, 157, 158, 159, 182, 183, 187, 188];
	var telecom = [133, 153, 189, 180];
	var prefix = cmcc.concat(unicom).concat(telecom);
	
	for(var i in prefix){
		if(str.startWith(prefix[i])){
			flag = true;
		}
	}
	
	return (flag && /\d{11}/.test(str));
}

/**
 * 获取主域名
 * @param url
 * @returns
 */
function getDomain(url){
	if(!url){
		return null;
	}
	var temp = url.toLowerCase().replace("http://", "");
	var firstPathSeperatorIndex = temp.indexOf("/");
	return (firstPathSeperatorIndex > 0 ? temp.substring(0, firstPathSeperatorIndex) : temp);
}

/**
 * 验证给定的URL与给定的主域是否一致
 * @param domain 主域名
 * @param url 要验证的URL
 * @returns {Boolean}
 */
function validateDomain(domain, url){
	var result = false;
	if(domain && url){
		var realDomain = getDomain(domain);
		var urlDomain = getDomain(url);
		result = (realDomain == urlDomain || ("www." + realDomain) == urlDomain);
	}
	return result;
}

/**
 * 判断字符串是否是汉字
 * @param str 
 * @returns {Number} 是汉字返回1，否则返回0
 */
function checkHasChi(str){
    re = /[\u4E00-\u9FA0]/;//汉字
    if (re.test(str)){
        return 1;
    } else{
        return 0;
    }
}

/**
 * 检查字符串是否超长
 * @param str
 * @param longtest 长度限制
 * @returns {Boolean} true:超长, false:未超长
 */
function checkIsOverLong(str,longtest){
    var len=0;
    for(var l=0;l<str.length;l++){
        if(checkHasChi(str.charAt(l))==1){
            len+=2;
        } else{
            len+=1;
        }
        if(len>parseInt(longtest)){
            return true;
        }
    }
    return false;
}

/**
 * 获取字符串长度，1个中文字符为2个单位长度
 * @param str
 * @returns {Number}
 */
function length(str){
    var len = 0;
    for(var l=0; l<str.length; l++){
        if(checkHasChi(str.charAt(l))==1){
            len += 2;
        } else{
            len += 1;
        }
    }
    return len;
}
//~==============================校验=========================
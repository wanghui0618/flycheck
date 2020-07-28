/**
 * jQuery EasyUI 1.5.1
 * 
 * Copyright (c) 2009-2016 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the freeware license: http://www.jeasyui.com/license_freeware.php
 * To use it on other terms please contact us: info@jeasyui.com
 *
 */(function(c){c.fn.numberbox=function(a,b){if("string"==typeof a){var d=c.fn.numberbox.methods[a];return d?d(this,b):this.textbox(a,b)}a=a||{};return this.each(function(){var b=c.data(this,"numberbox");b?c.extend(b.options,a):c.data(this,"numberbox",{options:c.extend({},c.fn.numberbox.defaults,c.fn.numberbox.parseOptions(this),a)});var d=c.data(this,"numberbox"),b=d.options;c(this).addClass("numberbox-f").textbox(b);c(this).textbox("textbox").css({imeMode:"disabled"});c(this).attr("numberboxName",c(this).attr("textboxName"));d.numberbox=c(this).next();d.numberbox.addClass("numberbox");d=b.parser.call(this,b.value);b=b.formatter.call(this,d);c(this).numberbox("initValue",d).numberbox("setText",b)})};c.fn.numberbox.methods={options:function(a){var b=a.data("textbox")?a.textbox("options"):{};return c.extend(c.data(a[0],"numberbox").options,{width:b.width,originalValue:b.originalValue,disabled:b.disabled,readonly:b.readonly})},fix:function(a){return a.each(function(){var a=c(this).numberbox("options");a.value=null;a=a.parser.call(this,c(this).numberbox("getText"));c(this).numberbox("setValue",a)})},setValue:function(a,b){return a.each(function(){var a=b,e=c.data(this,"numberbox").options;e.value=parseFloat(a);var a=e.parser.call(this,a),f=e.formatter.call(this,a);e.value=a;c(this).textbox("setText",f).textbox("setValue",a);f=e.formatter.call(this,c(this).textbox("getValue"));c(this).textbox("setText",f)})},clear:function(a){return a.each(function(){c(this).textbox("clear");c(this).numberbox("options").value=""})},reset:function(a){return a.each(function(){c(this).textbox("reset");c(this).numberbox("setValue",c(this).numberbox("getValue"))})}};c.fn.numberbox.parseOptions=function(a){var b=c(a);return c.extend({},c.fn.textbox.parseOptions(a),c.parser.parseOptions(a,["decimalSeparator","groupSeparator","suffix",{min:"number",max:"number",precision:"number"}]),{prefix:b.attr("prefix")?b.attr("prefix"):void 0})};c.fn.numberbox.defaults=c.extend({},c.fn.textbox.defaults,{inputEvents:{keypress:function(a){var b=a.data.target;return c(b).numberbox("options").filter.call(b,a)},blur:function(a){c(a.data.target).numberbox("fix")},keydown:function(a){13==a.keyCode&&c(a.data.target).numberbox("fix")}},min:null,max:null,precision:0,decimalSeparator:".",groupSeparator:"",prefix:"",suffix:"",filter:function(a){var b=c(this).numberbox("options"),d=c(this).numberbox("getText");if(a.metaKey||a.ctrlKey||0<=c.inArray(String(a.which),["46","8","13","0"]))return!0;var e=c("\x3cspan\x3e\x3c/span\x3e");e.html(String.fromCharCode(a.which));a=e.text();e.remove();return a?"-"==a||a==b.decimalSeparator?-1==d.indexOf(a)?!0:!1:a==b.groupSeparator?!0:0<="0123456789".indexOf(a)?!0:!1:!0},formatter:function(a){if(!a)return a;a+="";var b=c(this).numberbox("options"),d=a,e="",f=a.indexOf(".");0<=f&&(d=a.substring(0,f),e=a.substring(f+1,a.length));if(b.groupSeparator)for(a=/(\d+)(\d{3})/;a.test(d);)d=d.replace(a,"$1"+b.groupSeparator+"$2");return e?b.prefix+d+b.decimalSeparator+e+b.suffix:b.prefix+d+b.suffix},parser:function(a){a+="";var b=c(this).numberbox("options");b.prefix&&(a=c.trim(a.replace(new RegExp("\\"+c.trim(b.prefix),"g"),"")));b.suffix&&(a=c.trim(a.replace(new RegExp("\\"+c.trim(b.suffix),"g"),"")));parseFloat(a)!=b.value&&(b.groupSeparator&&(a=c.trim(a.replace(new RegExp("\\"+b.groupSeparator,"g"),""))),b.decimalSeparator&&(a=c.trim(a.replace(new RegExp("\\"+b.decimalSeparator,"g"),"."))),a=a.replace(/\s/g,""));a=parseFloat(a).toFixed(b.precision);isNaN(a)?a="":"number"==typeof b.min&&a<b.min?a=b.min.toFixed(b.precision):"number"==typeof b.max&&a>b.max&&(a=b.max.toFixed(b.precision));return a}})})(jQuery);
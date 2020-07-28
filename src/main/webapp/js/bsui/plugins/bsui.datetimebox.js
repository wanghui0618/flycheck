/**
 * jQuery EasyUI 1.5.1
 * 
 * Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the freeware license: http://www.jeasyui.com/license_freeware.php
 * To use it on other terms please contact us: info@jeasyui.com
 *
 */(function(b){function h(a){var c=b.data(a,"datetimebox"),d=c.options;b(a).datebox(b.extend({},d,{onShowPanel:function(){var a=b(this).datetimebox("getValue");f(this,a,!0);d.onShowPanel.call(this)},formatter:b.fn.datebox.defaults.formatter,parser:b.fn.datebox.defaults.parser}));b(a).removeClass("datebox-f").addClass("datetimebox-f");b(a).datebox("calendar").calendar({onSelect:function(a){d.onSelect.call(this.target,a)}});if(!c.spinner){var e=b(a).datebox("panel"),e=b('\x3cdiv style\x3d"padding:2px"\x3e\x3cinput\x3e\x3c/div\x3e').insertAfter(e.children("div.datebox-calendar-inner"));c.spinner=e.children("input")}c.spinner.timespinner({width:d.spinnerWidth,showSeconds:d.showSeconds,separator:d.timeSeparator});b(a).datetimebox("initValue",d.value)}function g(a){var c=b.data(a,"datetimebox").options,d,e=b(a).datetimebox("calendar");d=b(a).datetimebox("spinner");e=e.calendar("options").current;d=new Date(e.getFullYear(),e.getMonth(),e.getDate(),d.timespinner("getHours"),d.timespinner("getMinutes"),d.timespinner("getSeconds"));f(a,c.formatter.call(a,d));b(a).combo("hidePanel")}function f(a,c,d){var e=b.data(a,"datetimebox").options;b(a).combo("setValue",c);d||(c?(d=e.parser.call(a,c),b(a).combo("setText",e.formatter.call(a,d)),b(a).combo("setValue",e.formatter.call(a,d))):b(a).combo("setText",c));d=e.parser.call(a,c);b(a).datetimebox("calendar").calendar("moveTo",d);b(a).datetimebox("spinner").timespinner("setValue",function(c){function d(a){return(10>a?"0":"")+a}var f=[d(c.getHours()),d(c.getMinutes())];e.showSeconds&&f.push(d(c.getSeconds()));return f.join(b(a).datetimebox("spinner").timespinner("options").separator)}(d))}b.fn.datetimebox=function(a,c){if("string"==typeof a){var d=b.fn.datetimebox.methods[a];return d?d(this,c):this.datebox(a,c)}a=a||{};return this.each(function(){var c=b.data(this,"datetimebox");c?b.extend(c.options,a):b.data(this,"datetimebox",{options:b.extend({},b.fn.datetimebox.defaults,b.fn.datetimebox.parseOptions(this),a)});h(this)})};b.fn.datetimebox.methods={options:function(a){var c=a.datebox("options");return b.extend(b.data(a[0],"datetimebox").options,{originalValue:c.originalValue,disabled:c.disabled,readonly:c.readonly})},cloneFrom:function(a,c){return a.each(function(){b(this).datebox("cloneFrom",c);b.data(this,"datetimebox",{options:b.extend(!0,{},b(c).datetimebox("options")),spinner:b(c).datetimebox("spinner")});b(this).removeClass("datebox-f").addClass("datetimebox-f")})},spinner:function(a){return b.data(a[0],"datetimebox").spinner},initValue:function(a,c){return a.each(function(){var a=b(this).datetimebox("options"),c=a.value;c&&(c=a.formatter.call(this,a.parser.call(this,c)));b(this).combo("initValue",c).combo("setText",c)})},setValue:function(a,b){return a.each(function(){f(this,b)})},reset:function(a){return a.each(function(){var a=b(this).datetimebox("options");b(this).datetimebox("setValue",a.originalValue)})}};b.fn.datetimebox.parseOptions=function(a){b(a);return b.extend({},b.fn.datebox.parseOptions(a),b.parser.parseOptions(a,["timeSeparator","spinnerWidth",{showSeconds:"boolean"}]))};b.fn.datetimebox.defaults=b.extend({},b.fn.datebox.defaults,{spinnerWidth:"100%",showSeconds:!0,timeSeparator:":",keyHandler:{up:function(a){},down:function(a){},left:function(a){},right:function(a){},enter:function(a){g(this)},query:function(a,b){f(this,a,!0)}},buttons:[{text:function(a){return b(a).datetimebox("options").currentText},handler:function(a){var c=b(a).datetimebox("options");f(a,c.formatter.call(a,new Date));b(a).datetimebox("hidePanel")}},{text:function(a){return b(a).datetimebox("options").okText},handler:function(a){g(a)}},{text:function(a){return b(a).datetimebox("options").closeText},handler:function(a){b(a).datetimebox("hidePanel")}}],formatter:function(a){var c=a.getHours(),d=a.getMinutes(),e=a.getSeconds(),f=b(this).datetimebox("spinner").timespinner("options").separator;a=b.fn.datebox.defaults.formatter(a)+" "+((10>c?"0":"")+c)+f+((10>d?"0":"")+d);b(this).datetimebox("options").showSeconds&&(a+=f+((10>e?"0":"")+e));return a},parser:function(a){if(""==b.trim(a))return new Date;var c=a.split(" ");a=b.fn.datebox.defaults.parser(c[0]);if(2>c.length)return a;var d=b(this).datetimebox("spinner").timespinner("options").separator,e=c[1].split(d),c=parseInt(e[0],10)||0,d=parseInt(e[1],10)||0,e=parseInt(e[2],10)||0;return new Date(a.getFullYear(),a.getMonth(),a.getDate(),c,d,e)}})})(jQuery);
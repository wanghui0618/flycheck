/**
 * dhcc-bsui-1.5.3.4
 * Component:filebox
 * Dependencies:
 * 	 textbox
 */(function(a){function f(c){var b=a.data(c,"filebox"),d=b.options;b.filebox.find(".textbox-value").remove();d.oldValue="";b=a('\x3cinput type\x3d"file" class\x3d"textbox-value"\x3e').appendTo(b.filebox);b.attr("id",d.fileboxId).attr("name",a(c).attr("textboxName")||"");b.attr("accept",d.accept);d.multiple&&b.attr("multiple","multiple");b.change(function(){var b=this.value;this.files&&(b=a.map(this.files,function(a){return a.name}).join(d.separator));a(c).filebox("setText",b);d.onChange.call(c,b,d.oldValue);d.oldValue=b});return b}var g=0;a.fn.filebox=function(c,b){if("string"==typeof c){var d=a.fn.filebox.methods[c];return d?d(this,b):this.textbox(c,b)}c=c||{};return this.each(function(){var b=a.data(this,"filebox");b?a.extend(b.options,c):a.data(this,"filebox",{options:a.extend({},a.fn.filebox.defaults,a.fn.filebox.parseOptions(this),c)});var d=a.data(this,"filebox"),b=d.options;b.fileboxId="filebox_file_id_"+ ++g;a(this).addClass("filebox-f").textbox(b);a(this).textbox("textbox").attr("readonly","readonly");d.filebox=a(this).next().addClass("filebox");var d=f(this),e=a(this).filebox("button");e.length&&(a('\x3clabel class\x3d"filebox-label" for\x3d"'+b.fileboxId+'"\x3e\x3c/label\x3e').appendTo(e),e.linkbutton("options").disabled?d.attr("disabled","disabled"):d.removeAttr("disabled"))})};a.fn.filebox.methods={options:function(c){var b=c.textbox("options");return a.extend(a.data(c[0],"filebox").options,{width:b.width,value:b.value,originalValue:b.originalValue,disabled:b.disabled,readonly:b.readonly})},clear:function(c){return c.each(function(){a(this).textbox("clear");f(this)})},reset:function(c){return c.each(function(){a(this).filebox("clear")})},setValue:function(a){return a},setValues:function(a){return a}};a.fn.filebox.parseOptions=function(c){var b=a(c);return a.extend({},a.fn.textbox.parseOptions(c),a.parser.parseOptions(c,["accept","separator"]),{multiple:b.attr("multiple")?!0:void 0})};a.fn.filebox.defaults=a.extend({},a.fn.textbox.defaults,{buttonIcon:null,buttonText:"\u9009\u62e9\u6587\u4ef6",buttonAlign:"right",inputEvents:{},accept:"",separator:",",multiple:!1})})(jQuery);
/**
 * dhcc-bsui-1.5.3.4
 * component:combobox
 * dependencies:panel
 */(function(d){function x(){d("body .textbox-f").each(function(){var a=d(this).data("textbox"),c=a.options,a=a.textbox,b=a.find(".textbox-text");a.css("width",c.width);b.outerWidth(a.width())})}function y(a){d(a).addClass("textbox-f").hide();var c=d('\x3cspan class\x3d"textbox"\x3e\x3cinput class\x3d"textbox-text form-control" autocomplete\x3d"off"\x3e\x3cinput type\x3d"hidden" class\x3d"textbox-value"\x3e\x3c/span\x3e').insertAfter(a),b=d(a).attr("name");b&&(c.find("input.textbox-value").attr("name",b),d(a).removeAttr("name").attr("textboxName",b));return c}function z(a){var c=d.data(a,"textbox"),b=c.options,f=c.textbox,e="_bsui_textbox_input"+ ++u;f.addClass(b.cls);f.find(".textbox-text").remove();b.multiline?d('\x3ctextarea id\x3d"'+e+'" class\x3d"textbox-text form-control" autocomplete\x3d"off"\x3e\x3c/textarea\x3e').prependTo(f):d('\x3cinput id\x3d"'+e+'" type\x3d"'+b.type+'" class\x3d"textbox-text form-control" autocomplete\x3d"off"\x3e').prependTo(f);d("#"+e).attr("tabindex",d(a).attr("tabindex")||"").css("text-align",a.style.textAlign||"");f.find(".textbox-addon").remove();var g=b.icons?d.extend(!0,[],b.icons):[];b.iconCls&&g.push({iconCls:b.iconCls,disabled:!0});if(g.length){var h=d('\x3cspan class\x3d"textbox-addon"\x3e\x3c/span\x3e').prependTo(f);h.addClass("textbox-addon-"+b.iconAlign);for(var k=0;k<g.length;k++)h.append('\x3ca href\x3d"javascript:void(0)" class\x3d"textbox-icon '+g[k].iconCls+'" icon-index\x3d"'+k+'" tabindex\x3d"-1"\x3e\x3c/a\x3e')}f.find(".textbox-button").remove();(b.buttonText||b.buttonIcon)&&d('\x3ca href\x3d"javascript:void(0)" class\x3d"textbox-button"\x3e\x3c/a\x3e').prependTo(f).addClass("textbox-button-"+b.buttonAlign).linkbutton({btnCls:b.btnCls,text:b.buttonText,iconCls:b.buttonIcon,onClick:function(){var a=d(this).parent().prev();a.textbox("options").onClickButton.call(a[0])}});b.label?"object"==typeof b.label?(c.label=d(b.label),c.label.attr("for",e)):(d(c.label).remove(),c.label=d('\x3clabel class\x3d"textbox-label"\x3e\x3c/label\x3e').html(b.label),c.label.css("textAlign",b.labelAlign).attr("for",e),"after"==b.labelPosition?c.label.insertAfter(f):c.label.insertBefore(a),c.label.removeClass("textbox-label-left textbox-label-right textbox-label-top"),c.label.addClass("textbox-label-"+b.labelPosition)):d(c.label).remove();v(a);q(a,b.disabled);w(a,b.readonly)}function r(a,c){function b(a){var b=0;t.filter(".textbox-button-"+a).each(function(){b="left"==a||"right"==a?b+d(this).outerWidth():b+d(this).outerHeight()});return b}var f=d.data(a,"textbox"),e=f.options,g=f.textbox,h=g.parent();c&&("object"==typeof c?d.extend(e,c):e.width=c);if(isNaN(parseInt(e.width))){var k=d(a).clone();k.css("visibility","hidden");k.insertAfter(a);e.width=k.outerWidth();k.remove()}(k=g.is(":visible"))||g.appendTo("body");var l=g.find(".textbox-text"),t=g.find(".textbox-button"),m=g.find(".textbox-addon"),n=m.find(".textbox-icon");"auto"==e.height&&l.css({margin:"",paddingTop:"",paddingBottom:"",height:"",lineHeight:""});g._size(e,h);e.label&&e.labelPosition&&("top"==e.labelPosition?f.label._size({width:"auto"==e.labelWidth?g.outerWidth():e.labelWidth},g):(f.label._size({width:e.labelWidth,height:g.outerHeight()},g),e.multiline||f.label.css("lineHeight",f.label.height()+"px"),g._size("width",g.outerWidth()-f.label.outerWidth())));f=a.style.paddingLeft||0;h=a.style.paddingRight||0;f=parseFloat(f);h=parseFloat(h);"left"==e.buttonAlign||"right"==e.buttonAlign?(t.linkbutton("resize",{height:g.height()}),"left"==e.buttonAlign?f+=b("left"):h+=b("right")):t.linkbutton("resize",{width:"100%"});var p="auto"==e.height?l.outerHeight():g.height();"left"==e.iconAlign||"right"==e.iconAlign?(m.css(e.iconAlign,b(e.iconAlign)+1+"px"),"left"==e.iconAlign?f+=m._outerWidth():h+=m._outerWidth()):m.css({left:"1px",right:"1px"});m.css("top",b("top")+1+"px");m.css("height",g.height()-2+"px");n.css({width:e.iconWidth+"px",height:p-2+"px"});l.css({paddingLeft:f+6,paddingRight:h+6,marginTop:b("top"),marginBottom:b("bottom")});e.multiline?(l.css({paddingTop:a.style.paddingTop||"",paddingBottom:a.style.paddingBottom||""}),l._outerHeight(p)):l.css({height:p+"px",lineHeight:p+"px"});l._outerWidth(g.width());e.onResizing.call(a,e.width,e.height);k||g.insertAfter(a);e.onResize.call(a,e.width,e.height)}function v(a){var c=d(a).textbox("options");d(a).textbox("textbox").validatebox(d.extend({},c,{deltaX:function(b){return d(a).textbox("getTipX",b)},deltaY:function(b){return d(a).textbox("getTipY",b)},onBeforeValidate:function(){c.onBeforeValidate.call(a);var b=d(this);b.is(":focus")||b.val()===c.value||(c.oldInputValue=b.val(),b.val(c.value))},onValidate:function(b){var f=d(this);void 0!=c.oldInputValue&&(f.val(c.oldInputValue),c.oldInputValue=void 0);f=f.parent();b?f.removeClass("textbox-invalid"):f.addClass("textbox-invalid");c.onValidate.call(a,b)}}))}function n(a){var c=d.data(a,"textbox"),b=c.options,f=c.textbox,e=f.find(".textbox-text");e.attr("placeholder",b.prompt);e.unbind(".textbox");d(c.label).unbind(".textbox");if(!b.disabled&&!b.readonly){c.label&&d(c.label).bind("click.textbox",function(c){b.hasFocusMe||(e.focus(),d(a).textbox("setSelectionRange",{start:0,end:e.val().length}))});e.bind("blur.textbox",function(a){f.hasClass("textbox-focused")&&(b.value=d(this).val(),""==b.value?d(this).val(b.prompt).addClass("textbox-prompt"):d(this).removeClass("textbox-prompt"),f.removeClass("textbox-focused"))}).bind("focus.textbox",function(a){b.hasFocusMe=!0;f.hasClass("textbox-focused")||(d(this).val()!=b.value&&d(this).val(b.value),d(this).removeClass("textbox-prompt"),f.addClass("textbox-focused"))});for(var g in b.inputEvents)e.bind(g+".textbox",{target:a},b.inputEvents[g])}c=f.find(".textbox-addon");c.unbind().bind("click",{target:a},function(c){var f=d(c.target).closest("a.textbox-icon:not(.textbox-icon-disabled)");if(f.length){var e=parseInt(f.attr("icon-index")),g=b.icons[e];g&&g.handler&&g.handler.call(f[0],c);b.onClickIcon.call(a,e)}});c.find(".textbox-icon").each(function(a){a=b.icons[a];var c=d(this);!a||a.disabled||b.disabled||b.readonly?c.addClass("textbox-icon-disabled"):c.removeClass("textbox-icon-disabled")});f.find(".textbox-button").linkbutton(b.disabled||b.readonly?"disable":"enable");f.unbind(".textbox").bind("_resize.textbox",function(b,c){(d(this).hasClass("bsui-fluid")||c)&&r(a);return!1})}function q(a,c){var b=d.data(a,"textbox"),f=b.options,e=b.textbox,g=e.find(".textbox-text"),h=d(a).add(e.find(".textbox-value"));f.disabled=c;f.disabled?(g.blur(),g.validatebox("disable"),e.addClass("textbox-disabled"),h.attr("disabled","disabled"),d(b.label).addClass("textbox-label-disabled")):(g.validatebox("enable"),e.removeClass("textbox-disabled"),h.removeAttr("disabled"),d(b.label).removeClass("textbox-label-disabled"))}function w(a,c){var b=d.data(a,"textbox"),f=b.options,b=b.textbox,e=b.find(".textbox-text");f.readonly=void 0==c?!0:c;f.readonly&&e.triggerHandler("blur.textbox");e.validatebox("readonly",f.readonly);b.removeClass("textbox-readonly").addClass(f.readonly?"textbox-readonly":"")}d(function(){d(window).resize(function(){d.bsui.throttle(x)})});var u=0;d.fn.textbox=function(a,c){if("string"==typeof a){var b=d.fn.textbox.methods[a];return b?b(this,c):this.each(function(){d(this).textbox("textbox").validatebox(a,c)})}a=a||{};return this.each(function(){var b=d.data(this,"textbox");b?(d.extend(b.options,a),void 0!=a.value&&(b.options.originalValue=a.value)):(b=d.data(this,"textbox",{options:d.extend({},d.fn.textbox.defaults,d.fn.textbox.parseOptions(this),a),textbox:y(this)}),b.options.originalValue=b.options.value);z(this);n(this);b.options.doSize&&r(this);var c=b.options.value;b.options.value="";d(this).textbox("initValue",c)})};d.fn.textbox.methods={options:function(a){return d.data(a[0],"textbox").options},cloneFrom:function(a,c){return a.each(function(){var a=d(this);if(!a.data("textbox")){d(c).data("textbox")||d(c).textbox();var f=d.extend(!0,{},d(c).textbox("options")),e=a.attr("name")||"";a.addClass("textbox-f").hide();a.removeAttr("name").attr("textboxName",e);var g=d(c).next().clone().insertAfter(a),h="_bsui_textbox_input"+ ++u;g.find(".textbox-value").attr("name",e);g.find(".textbox-text").attr("id",h);e=d(d(c).textbox("label")).clone();e.length&&(e.attr("for",h),"after"==f.labelPosition?e.insertAfter(a.next()):e.insertBefore(a));d.data(this,"textbox",{options:f,textbox:g,label:e.length?e:void 0});f=d(c).textbox("button");f.length&&a.textbox("button").linkbutton(d.extend(!0,{},f.linkbutton("options")));n(this);v(this)}})},textbox:function(a){return d.data(a[0],"textbox").textbox.find(".textbox-text")},button:function(a){return d.data(a[0],"textbox").textbox.find(".textbox-button")},label:function(a){return d.data(a[0],"textbox").label},destroy:function(a){return a.each(function(){var a=d.data(this,"textbox").textbox;a.find(".textbox-text").validatebox("destroy");a.remove();d(this).remove()})},resize:function(a,c){return a.each(function(){r(this,c)})},disable:function(a){return a.each(function(){q(this,!0);n(this)})},enable:function(a){return a.each(function(){q(this,!1);n(this)})},readonly:function(a,c){return a.each(function(){w(this,c);n(this)})},isValid:function(a){return a.textbox("textbox").validatebox("isValid")},clear:function(a){return a.each(function(){d(this).textbox("setValue","")})},setText:function(a,c){return a.each(function(){var a=d(this).textbox("options"),f=d(this).textbox("textbox");c=void 0==c?"":String(c);d(this).textbox("getText")!=c&&f.val(c);a.value=c;f.is(":focus")||(c?f.removeClass("textbox-prompt"):f.val(a.prompt).addClass("textbox-prompt"));d(this).textbox("validate")})},initValue:function(a,c){return a.each(function(){var a=d.data(this,"textbox");d(this).textbox("setText",c);a.textbox.find(".textbox-value").val(c);d(this).val(c)})},setValue:function(a,c){return a.each(function(){var a=d.data(this,"textbox").options,f=d(this).textbox("getValue");d(this).textbox("initValue",c);f!=c&&(a.onChange.call(this,c,f),d(this).closest("form").trigger("_change",[this]))})},getText:function(a){var c=a.textbox("textbox");return c.is(":focus")?c.val():a.textbox("options").value},getValue:function(a){return a.data("textbox").textbox.find(".textbox-value").val()},reset:function(a){return a.each(function(){var a=d(this).textbox("options");d(this).textbox("setValue",a.originalValue)})},getIcon:function(a,c){return a.data("textbox").textbox.find(".textbox-icon:eq("+c+")")},getTipX:function(a,c){var b=a.data("textbox"),d=b.options,e=b.textbox,b=e.find(".textbox-text");c=c||d.tipPosition;var d=e.offset(),g=b.offset(),e=e.outerWidth(),b=b.outerWidth();return"right"==c?e-b-g.left+d.left:"left"==c?d.left-g.left:(e-b-g.left+d.left)/2-(g.left-d.left)/2},getTipY:function(a,c){var b=a.data("textbox"),d=b.options,e=b.textbox,b=e.find(".textbox-text");c=c||d.tipPosition;var d=e.offset(),g=b.offset(),e=e.outerHeight(),b=b.outerHeight();return"left"==c||"right"==c?(e-b-g.top+d.top)/2-(g.top-d.top)/2:"bottom"==c?e-b-g.top+d.top:d.top-g.top},getSelectionStart:function(a){return a.textbox("getSelectionRange").start},getSelectionRange:function(a){a=a.textbox("textbox")[0];var c=0,b=0;"number"==typeof a.selectionStart?(c=a.selectionStart,b=a.selectionEnd):a.createTextRange&&(b=document.selection.createRange(),a=a.createTextRange(),a.setEndPoint("EndToStart",b),c=a.text.length,b=c+b.text.length);return{start:c,end:b}},setSelectionRange:function(a,c){return a.each(function(){var a=d(this).textbox("textbox")[0],f=c.start,e=c.end;a.setSelectionRange?a.setSelectionRange(f,e):a.createTextRange&&(a=a.createTextRange(),a.collapse(),a.moveEnd("character",e),a.moveStart("character",f),a.select())})}};d.fn.textbox.parseOptions=function(a){var c=d(a);return d.extend({},d.fn.validatebox.parseOptions(a),d.parser.parseOptions(a,["prompt","iconCls","iconAlign","buttonText","buttonIcon","buttonAlign","btnCls","label","labelPosition","labelAlign",{multiline:"boolean",iconWidth:"number",labelWidth:"number"}]),{value:c.val()||void 0,type:c.attr("type")?c.attr("type"):void 0})};d.fn.textbox.defaults=d.extend({},d.fn.validatebox.defaults,{doSize:!0,width:"auto",height:30,cls:null,prompt:"",value:"",type:"text",multiline:!1,icons:[],iconCls:null,iconAlign:"right",iconWidth:"auto",buttonText:"",buttonIcon:null,buttonAlign:"right",label:null,labelWidth:"auto",labelPosition:"before",labelAlign:"left",inputEvents:{blur:function(a){a=d(a.data.target);var c=a.textbox("options");a.textbox("getValue")!=c.value&&a.textbox("setValue",c.value)},keydown:function(a){13==a.keyCode&&(a=d(a.data.target),a.textbox("setValue",a.textbox("getText")))}},btnCls:"primary",onChange:function(a,c){},onResizing:function(a,c){},onResize:function(a,c){},onClickButton:function(){},onClickIcon:function(a){}})})(jQuery);
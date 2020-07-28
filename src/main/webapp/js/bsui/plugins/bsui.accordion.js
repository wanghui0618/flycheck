/**
 * dhcc-bsui-1.5.3.4
 * Component:calendar
 * Dependencies:
 * 	 panel
 */(function(c){function l(a,b){function d(a,b){for(var d=0,c=0;c<h.length;c++){var f=h[c],e=f.panel("header")._outerHeight(m);if(f.panel("options").collapsible==a){var k=isNaN(b)?void 0:b+m*e.length;f.panel("resize",{width:g.width(),height:a?k:void 0});d+=f.panel("panel").outerHeight()-m*e.length}}return d}var e=c.data(a,"accordion"),f=e.options,h=e.panels,g=c(a);b&&c.extend(f,{width:b.width,height:b.height});g._size(f);var m=40,e="auto",k=g.find("\x3e.panel\x3e.accordion-header");k.length&&(m=c(k[0]).css("height","")._outerHeight());isNaN(parseInt(f.height))||(e=g.height()-m*k.length);d(!0,e-d(!1)+1)}function k(a,b,d,e){a=c.data(a,"accordion").panels;for(var f=[],h=0;h<a.length;h++){var g=a[h];if(b)g.panel("options")[b]==d&&f.push(g);else if(g[0]==c(d)[0])return h}return b?e?f:f.length?f[0]:null:-1}function r(a){return k(a,"collapsed",!1,!0)}function u(a){a=r(a);return a.length?a[0]:null}function p(a,b){var d=c.data(a,"accordion").panels;return"number"==typeof b?0>b||b>=d.length?null:d[b]:k(a,"title",b)}function w(a){var b=c.data(a,"accordion"),d=c(a);d.addClass("accordion");b.panels=[];d.children("div").each(function(){var d=c.extend({},c.parser.parseOptions(this),{selected:c(this).attr("selected")?!0:void 0}),f=c(this);b.panels.push(f);v(a,f,d)});d.bind("_resize",function(b,d){(c(this).hasClass("bsui-fluid")||d)&&l(a);return!1})}function v(a,b,d){function e(b){var d=b.panel("options");d.collapsible&&(b=k(a,null,b),d.collapsed?n(a,b):t(a,b))}var f=c.data(a,"accordion").options,h="accordion-header";f.cls&&(h+=" "+f.cls);b.panel(c.extend({},{collapsible:!0,minimizable:!1,maximizable:!1,closable:!1,doSize:!1,collapsed:!0,cls:h,bodyCls:"accordion-body"},d,{onBeforeExpand:function(){if(d.onBeforeExpand&&0==d.onBeforeExpand.call(this))return!1;if(!f.multiple)for(var b=c.grep(r(a),function(a){return a.panel("options").collapsible}),e=0;e<b.length;e++)t(a,k(a,null,b[e]));b=c(this).panel("header");b.addClass("active");b.find(".accordion-collapse").removeClass("accordion-expand")},onExpand:function(){d.onExpand&&d.onExpand.call(this);f.onSelect.call(a,c(this).panel("options").title,k(a,null,this))},onBeforeCollapse:function(){if(d.onBeforeCollapse&&0==d.onBeforeCollapse.call(this))return!1;var a=c(this).panel("header");a.removeClass("active");a.find(".accordion-collapse").addClass("accordion-expand")},onCollapse:function(){d.onCollapse&&d.onCollapse.call(this);f.onUnselect.call(a,c(this).panel("options").title,k(a,null,this))}}));var h=b.panel("header"),g=h.children("div.panel-tool");g.children("a.panel-tool-collapse").hide();g=c('\x3ca href\x3d"javascript:void(0)"\x3e\x3c/a\x3e').addClass("accordion-collapse accordion-expand").appendTo(g);g.bind("click",function(){e(b);return!1});b.panel("options").collapsible?g.show():g.hide();h.click(function(){e(b);return!1})}function n(a,b){var d=p(a,b);if(d){q(a);var e=c.data(a,"accordion").options;d.panel("expand",e.animate)}}function t(a,b){var d=p(a,b);if(d){q(a);var e=c.data(a,"accordion").options;d.panel("collapse",e.animate)}}function x(a){function b(b){var c=d.animate;d.animate=!1;n(a,b);d.animate=c}var d=c.data(a,"accordion").options,e=k(a,"selected",!0);e?b(k(a,null,e)):b(d.selected)}function q(a){a=c.data(a,"accordion").panels;for(var b=0;b<a.length;b++)a[b].stop(!0,!0)}c.fn.accordion=function(a,b){if("string"==typeof a)return c.fn.accordion.methods[a](this,b);a=a||{};return this.each(function(){var b=c.data(this,"accordion");b?c.extend(b.options,a):(c.data(this,"accordion",{options:c.extend({},c.fn.accordion.defaults,c.fn.accordion.parseOptions(this),a),accordion:c(this).addClass("accordion"),panels:[]}),w(this));var b=c.data(this,"accordion").options,e=c(this);b.border?e.removeClass("accordion-noborder"):e.addClass("accordion-noborder");l(this);x(this)})};c.fn.accordion.methods={options:function(a){return c.data(a[0],"accordion").options},panels:function(a){return c.data(a[0],"accordion").panels},resize:function(a,b){return a.each(function(){l(this,b)})},getSelections:function(a){return r(a[0])},getSelected:function(a){return u(a[0])},getPanel:function(a,b){return p(a[0],b)},getPanelIndex:function(a,b){return k(a[0],null,b)},select:function(a,b){return a.each(function(){n(this,b)})},unselect:function(a,b){return a.each(function(){t(this,b)})},add:function(a,b){return a.each(function(){var a=c.data(this,"accordion"),e=a.options,a=a.panels;void 0==b.selected&&(b.selected=!0);q(this);var f=c("\x3cdiv\x3e\x3c/div\x3e").appendTo(this);a.push(f);v(this,f,b);l(this);e.onAdd.call(this,b.title,a.length-1);b.selected&&n(this,a.length-1)})},remove:function(a,b){return a.each(function(){var a=c.data(this,"accordion"),e=a.options,a=a.panels;q(this);var f=p(this,b),h=f.panel("options").title,g=k(this,null,f);f&&0!=e.onBeforeRemove.call(this,h,g)&&(a.splice(g,1),f.panel("destroy"),a.length&&(l(this),u(this)||n(this,0)),e.onRemove.call(this,h,g))})}};c.fn.accordion.parseOptions=function(a){c(a);return c.extend({},c.parser.parseOptions(a,["width","height","cls",{fit:"boolean",border:"boolean",animate:"boolean",multiple:"boolean",selected:"number"}]))};c.fn.accordion.defaults={width:"auto",height:"auto",fit:!1,border:!0,animate:!0,multiple:!1,selected:0,cls:null,onSelect:function(a,b){},onUnselect:function(a,b){},onAdd:function(a,b){},onBeforeRemove:function(a,b){},onRemove:function(a,b){}}})(jQuery);
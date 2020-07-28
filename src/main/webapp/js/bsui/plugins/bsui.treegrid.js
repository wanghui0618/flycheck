/**
 * jQuery EasyUI 1.5.1
 * 
 * Copyright (c) 2009-2016 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the freeware license: http://www.jeasyui.com/license_freeware.php
 * To use it on other terms please contact us: info@jeasyui.com
 *
 */(function(e){function K(a){var b=e.data(a,"treegrid"),d=b.options;e(a).datagrid(e.extend({},d,{url:null,data:null,loader:function(){return!1},onBeforeLoad:function(){return!1},onLoadSuccess:function(){},onResizeColumn:function(b,c){t(a);d.onResizeColumn.call(a,b,c)},onBeforeSortColumn:function(b,c){if(0==d.onBeforeSortColumn.call(a,b,c))return!1},onSortColumn:function(b,c){d.sortName=b;d.sortOrder=c;if(d.remoteSort)w(a);else{var f=e(a).treegrid("getData");y(a,null,f)}d.onSortColumn.call(a,b,c)},onClickCell:function(b,c){d.onClickCell.call(a,c,p(a,b))},onDblClickCell:function(b,c){d.onDblClickCell.call(a,c,p(a,b))},onRowContextMenu:function(b,c){d.onContextMenu.call(a,b,p(a,c))}}));var c=e.data(a,"datagrid").options;d.columns=c.columns;d.frozenColumns=c.frozenColumns;b.dc=e.data(a,"datagrid").dc;d.pagination&&(b=e(a).datagrid("getPager"),b.pagination({pageNumber:d.pageNumber,pageSize:d.pageSize,pageList:d.pageList,onSelectPage:function(b,c){d.pageNumber=b;d.pageSize=c;w(a)}}),d.pageSize=b.pagination("options").pageSize)}function t(a,b){var d=e.data(a,"datagrid").options;if(!e.data(a,"datagrid").dc.body1.is(":empty")&&(!d.nowrap||d.autoRowHeight)&&void 0!=b)for(var c=z(a,b),f=0;f<c.length;f++){var g=c[f][d.idField],h=d.finder.getTr(a,g,"body",1),g=d.finder.getTr(a,g,"body",2);h.css("height","");g.css("height","");var k=Math.max(h.height(),g.height());h.css("height",k);g.css("height",k)}e(a).datagrid("fixRowHeight",b)}function B(a){var b=e.data(a,"datagrid").dc;e.data(a,"treegrid").options.rownumbers&&b.body1.find("div.datagrid-cell-rownumber").each(function(a){e(this).html(a+1)})}function E(a){return function(b){e.fn.datagrid.defaults.rowEvents[a?"mouseover":"mouseout"](b);b=e(b.target);var d=a?"addClass":"removeClass";b.hasClass("tree-hit")&&(b.hasClass("tree-expanded")?b[d]("tree-expanded-hover"):b[d]("tree-collapsed-hover"))}}function r(a,b,d,c){var f=e.data(a,"treegrid").options;if(f.checkbox){var g=p(a,b);if(g.checkState){b=f.finder.getTr(a,b).find(".tree-checkbox");void 0==d&&(b.hasClass("tree-checkbox1")?d=!1:b.hasClass("tree-checkbox0")?d=!0:(void 0==g._checked&&(g._checked=b.hasClass("tree-checkbox1")),d=!g._checked));if(g._checked=d){if(b.hasClass("tree-checkbox1"))return}else if(b.hasClass("tree-checkbox0"))return;if(c||0!=f.onBeforeCheckNode.call(a,g,d))f.cascadeCheck?(L(a,g,d),C(a,g)):A(a,g,d?"1":"0"),c||f.onCheckNode.call(a,g,d)}}}function A(a,b,d){var c=e.data(a,"treegrid"),f=c.checkedRows,c=c.options;b.checkState&&void 0!=d&&(a=c.finder.getTr(a,b[c.idField]).find(".tree-checkbox"),a.length&&(b.checkState=["unchecked","checked","indeterminate"][d],b.checked="checked"==b.checkState,a.removeClass("tree-checkbox0 tree-checkbox1 tree-checkbox2"),a.addClass("tree-checkbox"+d),0==d?e.bsui.removeArrayItem(f,c.idField,b[c.idField]):e.bsui.addArrayItem(f,c.idField,b)))}function L(a,b,d){var c=d?1:0;A(a,b,c);e.bsui.forEach(b.children||[],!0,function(b){A(a,b,c)})}function C(a,b){var d=e.data(a,"treegrid").options;if(d=v(a,b[d.idField]))A(a,d,F(d)),C(a,d)}function F(a){var b=0,d=0,c=0;e.bsui.forEach(a.children||[],!1,function(a){a.checkState&&(b++,"checked"==a.checkState?c++:"unchecked"==a.checkState&&d++)});if(0!=b)return d==b?0:c==b?1:2}function G(a,b){var d=e.data(a,"treegrid").options;if(d.checkbox){var c=p(a,b),f=d.finder.getTr(a,b),g=f.find(".tree-checkbox");d.view.hasCheckbox(a,c)?(g.length||(c.checkState=c.checkState||"unchecked",e('\x3cspan class\x3d"tree-checkbox"\x3e\x3c/span\x3e').insertBefore(f.find(".tree-title"))),"checked"==c.checkState?r(a,b,!0,!0):"unchecked"==c.checkState?r(a,b,!1,!0):(d=F(c),0===d?r(a,b,!1,!0):1===d&&r(a,b,!0,!0))):(g.remove(),c.checkState=void 0,c.checked=void 0,C(a,c))}}function H(a,b){function d(a,b){e('\x3ctr class\x3d"treegrid-tr-tree"\x3e\x3ctd style\x3d"border:0px" colspan\x3d"'+b+'"\x3e\x3cdiv\x3e\x3c/div\x3e\x3c/td\x3e\x3c/tr\x3e').insertAfter(a)}var c=e.data(a,"treegrid").options,f=c.finder.getTr(a,b,"body",1),g=c.finder.getTr(a,b,"body",2),c=e(a).datagrid("getColumnFields",!0).length+(c.rownumbers?1:0),h=e(a).datagrid("getColumnFields",!1).length;d(f,c);d(g,h)}function y(a,b,d,c,f){var g=e.data(a,"treegrid"),h=g.options,k=g.dc;d=h.loadFilter.call(a,d,b);var l=p(a,b);if(l){var m=h.finder.getTr(a,b,"body",1),g=h.finder.getTr(a,b,"body",2),m=m.next("tr.treegrid-tr-tree").children("td").children("div"),n=g.next("tr.treegrid-tr-tree").children("td").children("div");c||(l.children=[])}else m=k.body1,n=k.body2,c||(g.data=[]);c||(m.empty(),n.empty());h.view.onBeforeRender&&h.view.onBeforeRender.call(h.view,a,b,d);h.view.render.call(h.view,a,m,!0);h.view.render.call(h.view,a,n,!1);h.showFooter&&(h.view.renderFooter.call(h.view,a,k.footer1,!0),h.view.renderFooter.call(h.view,a,k.footer2,!1));h.view.onAfterRender&&h.view.onAfterRender.call(h.view,a);!b&&h.pagination&&(b=e.data(a,"treegrid").total,c=e(a).datagrid("getPager"),c.pagination("options").total!=b&&c.pagination({total:b}));t(a);B(a);e(a).treegrid("showLines");e(a).treegrid("setSelectionState");e(a).treegrid("autoSizeColumn");f||h.onLoadSuccess.call(a,l,d)}function w(a,b,d,c,f){var g=e.data(a,"treegrid").options,h=e(a).datagrid("getPanel").find("div.datagrid-body");void 0==b&&g.queryParams&&(g.queryParams.id=void 0);d&&(g.queryParams=d);d=e.extend({},g.queryParams);g.pagination&&e.extend(d,{page:g.pageNumber,rows:g.pageSize});g.sortName&&e.extend(d,{sort:g.sortName,order:g.sortOrder});var k=p(a,b);if(0!=g.onBeforeLoad.call(a,k,d)){var l=h.find('tr[node-id\x3d"'+b+'"] span.tree-folder');l.addClass("tree-loading");e(a).treegrid("loading");0==g.loader.call(a,d,function(d){l.removeClass("tree-loading");e(a).treegrid("loaded");y(a,b,d,c);f&&f()},function(){l.removeClass("tree-loading");e(a).treegrid("loaded");g.onLoadError.apply(a,arguments);f&&f()})&&(l.removeClass("tree-loading"),e(a).treegrid("loaded"))}}function v(a,b){var d=p(a,b);return d._parentId?p(a,d._parentId):null}function z(a,b){var d=e.data(a,"treegrid").data;b&&(d=(d=p(a,b))?d.children||[]:[]);var c=[];e.bsui.forEach(d,!0,function(a){c.push(a)});return c}function p(a,b){var d=e.data(a,"treegrid"),c=d.options,f=null;e.bsui.forEach(d.data,!0,function(a){if(a[c.idField]==b)return f=a,!1});return f}function D(a,b){var d=e.data(a,"treegrid").options,c=p(a,b),f=d.finder.getTr(a,b),g=f.find("span.tree-hit");0==g.length||g.hasClass("tree-collapsed")||0==d.onBeforeCollapse.call(a,c)||(g.removeClass("tree-expanded tree-expanded-hover").addClass("tree-collapsed"),g.next().removeClass("tree-folder-open"),c.state="closed",f=f.next("tr.treegrid-tr-tree"),f=f.children("td").children("div"),d.animate?f.slideUp("normal",function(){e(a).treegrid("autoSizeColumn");t(a,b);d.onCollapse.call(a,c)}):(f.hide(),e(a).treegrid("autoSizeColumn"),t(a,b),d.onCollapse.call(a,c)))}function x(a,b){function d(d){h.state="open";c.animate?d.slideDown("normal",function(){e(a).treegrid("autoSizeColumn");t(a,b);c.onExpand.call(a,h)}):(d.show(),e(a).treegrid("autoSizeColumn"),t(a,b),c.onExpand.call(a,h))}var c=e.data(a,"treegrid").options,f=c.finder.getTr(a,b),g=f.find("span.tree-hit"),h=p(a,b);if(0!=g.length&&!g.hasClass("tree-expanded")&&0!=c.onBeforeExpand.call(a,h)){g.removeClass("tree-collapsed tree-collapsed-hover").addClass("tree-expanded");g.next().addClass("tree-folder-open");var k=f.next("tr.treegrid-tr-tree");if(k.length){var l=k.children("td").children("div");d(l)}else H(a,h[c.idField]),k=f.next("tr.treegrid-tr-tree"),l=k.children("td").children("div"),l.hide(),f=e.extend({},c.queryParams||{}),f.id=h[c.idField],w(a,h[c.idField],f,!0,function(){l.is(":empty")?k.remove():d(l)})}}function I(a,b){e.data(a,"treegrid").options.finder.getTr(a,b).find("span.tree-hit").hasClass("tree-expanded")?D(a,b):x(a,b)}function J(a,b){var d=e.data(a,"treegrid"),c=d.options;if(b.parent){var f=c.finder.getTr(a,b.parent);0==f.next("tr.treegrid-tr-tree").length&&H(a,b.parent);c=f.children('td[field\x3d"'+c.treeField+'"]').children("div.datagrid-cell").children("span.tree-icon");c.hasClass("tree-file")&&(c.removeClass("tree-file").addClass("tree-folder tree-folder-open"),c=e('\x3cspan class\x3d"tree-hit tree-expanded"\x3e\x3c/span\x3e').insertBefore(c),c.prev().length&&c.prev().remove())}y(a,b.parent,b.data,0<d.data.length,!0)}function M(a,b){function d(d){var e=d?1:2;d=f.finder.getTr(a,b.data[f.idField],"body",e);var g=d.closest("table.datagrid-btable");d=d.parent().children();e=f.finder.getTr(a,c,"body",e);if(b.before)d.insertBefore(e);else{var h=e.next("tr.treegrid-tr-tree");d.insertAfter(h.length?h:e)}g.remove()}var c=b.before||b.after,f=e.data(a,"treegrid").options,g=v(a,c);J(a,{parent:g?g[f.idField]:null,data:[b.data]});for(var g=g?g.children:e(a).treegrid("getRoots"),h=0;h<g.length;h++)if(g[h][f.idField]==c){g.splice(b.before?h:h+1,0,g[g.length-1]);g.splice(g.length-1,1);break}d(!0);d(!1);B(a);e(a).treegrid("showLines")}function N(a){function b(a){e.map(a,function(a){a.children&&a.children.length?b(a.children):c(a).find(".tree-icon").prev().addClass("tree-join")});a.length&&(a=c(a[a.length-1]),a.addClass("tree-node-last"),a.find(".tree-join").removeClass("tree-join").addClass("tree-joinbottom"))}function d(b){e.map(b,function(a){a.children&&a.children.length&&d(a.children)});for(var c=0;c<b.length-1;c++){var h=b[c],k=f.treegrid("getLevel",h[g.idField]);g.finder.getTr(a,h[g.idField]).next().find('tr.datagrid-row td[field\x3d"'+g.treeField+'"] div.datagrid-cell').find("span:eq("+(k-1)+")").addClass("tree-line")}}function c(b){return g.finder.getTr(a,b[g.idField]).find('td[field\x3d"'+g.treeField+'"] div.datagrid-cell')}var f=e(a),g=f.treegrid("options");if(g.lines){f.treegrid("getPanel").addClass("tree-lines");f.treegrid("getPanel").find("span.tree-indent").removeClass("tree-line tree-join tree-joinbottom");f.treegrid("getPanel").find("div.datagrid-cell").removeClass("tree-node-last tree-root-first tree-root-one");var h=f.treegrid("getRoots");1<h.length?c(h[0]).addClass("tree-root-first"):1==h.length&&c(h[0]).addClass("tree-root-one");b(h);d(h)}else f.treegrid("getPanel").removeClass("tree-lines")}e.fn.treegrid=function(a,b){if("string"==typeof a){var d=e.fn.treegrid.methods[a];return d?d(this,b):this.datagrid(a,b)}a=a||{};return this.each(function(){var b=e.data(this,"treegrid");b?e.extend(b.options,a):b=e.data(this,"treegrid",{options:e.extend({},e.fn.treegrid.defaults,e.fn.treegrid.parseOptions(this),a),data:[],checkedRows:[],tmpIds:[]});K(this);b.options.data&&e(this).treegrid("loadData",b.options.data);w(this)})};e.fn.treegrid.methods={options:function(a){return e.data(a[0],"treegrid").options},resize:function(a,b){return a.each(function(){e(this).datagrid("resize",b)})},fixRowHeight:function(a,b){return a.each(function(){t(this,b)})},loadData:function(a,b){return a.each(function(){y(this,b.parent,b)})},load:function(a,b){return a.each(function(){e(this).treegrid("options").pageNumber=1;e(this).treegrid("getPager").pagination({pageNumber:1});e(this).treegrid("reload",b)})},reload:function(a,b){return a.each(function(){var a=e(this).treegrid("options"),c;"object"==typeof b?c=b:(c=e.extend({},a.queryParams),c.id=b);if(c.id){var f=e(this).treegrid("find",c.id);f.children&&f.children.splice(0,f.children.length);a.queryParams=c;a=a.finder.getTr(this,c.id);a.next("tr.treegrid-tr-tree").remove();a.find("span.tree-hit").removeClass("tree-expanded tree-expanded-hover").addClass("tree-collapsed");x(this,c.id)}else w(this,null,c)})},reloadFooter:function(a,b){return a.each(function(){var a=e.data(this,"treegrid").options,c=e.data(this,"datagrid").dc;b&&(e.data(this,"treegrid").footer=b);a.showFooter&&(a.view.renderFooter.call(a.view,this,c.footer1,!0),a.view.renderFooter.call(a.view,this,c.footer2,!1),a.view.onAfterRender&&a.view.onAfterRender.call(a.view,this),e(this).treegrid("fixRowHeight"))})},getData:function(a){return e.data(a[0],"treegrid").data},getFooterRows:function(a){return e.data(a[0],"treegrid").footer},getRoot:function(a){a=e.data(a[0],"treegrid").data;return a.length?a[0]:null},getRoots:function(a){return e.data(a[0],"treegrid").data},getParent:function(a,b){return v(a[0],b)},getChildren:function(a,b){return z(a[0],b)},getLevel:function(a,b){var d=a[0],c=e.data(d,"treegrid").options;return c.finder.getTr(d,b).children('td[field\x3d"'+c.treeField+'"]').find("span.tree-indent,span.tree-hit").length},find:function(a,b){return p(a[0],b)},isLeaf:function(a,b){return 0==e.data(a[0],"treegrid").options.finder.getTr(a[0],b).find("span.tree-hit").length},select:function(a,b){return a.each(function(){e(this).datagrid("selectRow",b)})},unselect:function(a,b){return a.each(function(){e(this).datagrid("unselectRow",b)})},collapse:function(a,b){return a.each(function(){D(this,b)})},expand:function(a,b){return a.each(function(){x(this,b)})},toggle:function(a,b){return a.each(function(){I(this,b)})},collapseAll:function(a,b){return a.each(function(){var a=e.data(this,"treegrid").options,c=z(this,b);b&&c.unshift(p(this,b));for(var f=0;f<c.length;f++)D(this,c[f][a.idField])})},expandAll:function(a,b){return a.each(function(){var a=e.data(this,"treegrid").options,c=z(this,b);b&&c.unshift(p(this,b));for(var f=0;f<c.length;f++)x(this,c[f][a.idField])})},expandTo:function(a,b){return a.each(function(){for(var a=e.data(this,"treegrid").options,c=[],f=v(this,b);f;)f=f[a.idField],c.unshift(f),f=v(this,f);for(a=0;a<c.length;a++)x(this,c[a])})},append:function(a,b){return a.each(function(){J(this,b)})},insert:function(a,b){return a.each(function(){M(this,b)})},remove:function(a,b){return a.each(function(){var a=e.data(this,"treegrid"),c=a.options,f=v(this,b);e(this).datagrid("deleteRow",b);e.bsui.removeArrayItem(a.checkedRows,c.idField,b);B(this);f&&G(this,f[c.idField]);--a.total;e(this).datagrid("getPager").pagination("refresh",{total:a.total});e(this).treegrid("showLines")})},pop:function(a,b){var d=a.treegrid("find",b);a.treegrid("remove",b);return d},refresh:function(a,b){return a.each(function(){var a=e.data(this,"treegrid").options;a.view.refreshRow.call(a.view,this,b)})},update:function(a,b){return a.each(function(){var a=e.data(this,"treegrid").options,c=b.row;a.view.updateRow.call(a.view,this,b.id,c);void 0!=c.checked&&(c=p(this,b.id),e.extend(c,{checkState:c.checked?"checked":!1===c.checked?"unchecked":void 0}),G(this,b.id))})},beginEdit:function(a,b){return a.each(function(){e(this).datagrid("beginEdit",b);e(this).treegrid("fixRowHeight",b)})},endEdit:function(a,b){return a.each(function(){e(this).datagrid("endEdit",b)})},cancelEdit:function(a,b){return a.each(function(){e(this).datagrid("cancelEdit",b)})},showLines:function(a){return a.each(function(){N(this)})},setSelectionState:function(a){return a.each(function(){e(this).datagrid("setSelectionState");for(var a=e(this).data("treegrid"),d=0;d<a.tmpIds.length;d++)r(this,a.tmpIds[d],!0,!0);a.tmpIds=[]})},getCheckedNodes:function(a,b){b=b||"checked";var d=[];e.bsui.forEach(a.data("treegrid").checkedRows,!1,function(a){a.checkState==b&&d.push(a)});return d},checkNode:function(a,b){return a.each(function(){r(this,b,!0)})},uncheckNode:function(a,b){return a.each(function(){r(this,b,!1)})},clearChecked:function(a){return a.each(function(){var a=this,d=e(a).treegrid("options");e(a).datagrid("clearChecked");e.map(e(a).treegrid("getCheckedNodes"),function(b){r(a,b[d.idField],!1,!0)})})}};e.fn.treegrid.parseOptions=function(a){return e.extend({},e.fn.datagrid.parseOptions(a),e.parser.parseOptions(a,["treeField",{checkbox:"boolean",cascadeCheck:"boolean",onlyLeafCheck:"boolean"},{animate:"boolean"}]))};var O=e.extend({},e.fn.datagrid.defaults.view,{render:function(a,b,d){function c(b,d,n){for(var l=e(a).treegrid("getParent",n[0][f.idField]),l=(l?l.children.length:e(a).treegrid("getRoots").length)-n.length,m=['\x3ctable class\x3d"datagrid-btable" cellspacing\x3d"0" cellpadding\x3d"0" border\x3d"0"\x3e\x3ctbody\x3e'],p=0;p<n.length;p++){var q=n[p];"open"!=q.state&&"closed"!=q.state&&(q.state="open");var u=f.rowStyler?f.rowStyler.call(a,q):"",u=this.getStyleValue(u),r='class\x3d"datagrid-row '+(l++%2&&f.striped?"datagrid-row-alt ":" ")+u.c+'"';m.push('\x3ctr id\x3d"'+(h+"-"+(b?1:2)+"-"+q[f.idField])+'" node-id\x3d"'+q[f.idField]+'" '+r+" "+(u.s?'style\x3d"'+u.s+'"':"")+"\x3e");m=m.concat(k.renderRow.call(k,a,g,b,d,q));m.push("\x3c/tr\x3e");q.children&&q.children.length&&(u=c.call(this,b,d+1,q.children),m.push('\x3ctr class\x3d"treegrid-tr-tree"\x3e\x3ctd style\x3d"border:0px" colspan\x3d'+(g.length+(f.rownumbers?1:0))+'\x3e\x3cdiv style\x3d"display:'+("closed"==q.state?"none":"block")+'"\x3e'),m=m.concat(u),m.push("\x3c/div\x3e\x3c/td\x3e\x3c/tr\x3e"))}m.push("\x3c/tbody\x3e\x3c/table\x3e");return m}var f=e.data(a,"treegrid").options,g=e(a).datagrid("getColumnFields",d),h=e.data(a,"datagrid").rowIdPrefix;if(!d||f.rownumbers||f.frozenColumns&&f.frozenColumns.length){var k=this;this.treeNodes&&this.treeNodes.length&&(d=c.call(this,d,this.treeLevel,this.treeNodes),e(b).append(d.join("")))}},renderFooter:function(a,b,d){for(var c=e.data(a,"treegrid").options,f=e.data(a,"treegrid").footer||[],g=e(a).datagrid("getColumnFields",d),h=['\x3ctable class\x3d"datagrid-ftable" cellspacing\x3d"0" cellpadding\x3d"0" border\x3d"0"\x3e\x3ctbody\x3e'],k=0;k<f.length;k++){var l=f[k];l[c.idField]=l[c.idField]||"foot-row-id"+k;h.push('\x3ctr class\x3d"datagrid-row" node-id\x3d"'+l[c.idField]+'"\x3e');h.push(this.renderRow.call(this,a,g,d,0,l));h.push("\x3c/tr\x3e")}h.push("\x3c/tbody\x3e\x3c/table\x3e");e(b).html(h.join(""))},renderRow:function(a,b,d,c,f){var g=e.data(a,"treegrid"),h=g.options,k=[];d&&h.rownumbers&&k.push('\x3ctd class\x3d"datagrid-td-rownumber"\x3e\x3cdiv class\x3d"datagrid-cell-rownumber"\x3e0\x3c/div\x3e\x3c/td\x3e');for(d=0;d<b.length;d++){var l=b[d],m=e(a).datagrid("getColumnOption",l);if(m){var n=m.styler?m.styler(f[l],f)||"":"",n=this.getStyleValue(n),p=m.hidden?'style\x3d"display:none;'+n.s+'"':n.s?'style\x3d"'+n.s+'"':"";k.push('\x3ctd field\x3d"'+l+'" '+(n.c?'class\x3d"'+n.c+'"':"")+" "+p+"\x3e");p="";m.checkbox||(m.align&&(p+="text-align:"+m.align+";"),h.nowrap?h.autoRowHeight&&(p+="height:auto;"):p+="white-space:normal;height:auto;");k.push('\x3cdiv style\x3d"'+p+'" ');m.checkbox?k.push('class\x3d"datagrid-cell-check '):k.push('class\x3d"datagrid-cell '+m.cellClass);k.push('"\x3e');if(m.checkbox)f.checked?k.push('\x3cinput type\x3d"checkbox" checked\x3d"checked"'):k.push('\x3cinput type\x3d"checkbox"'),k.push(' name\x3d"'+l+'" value\x3d"'+(void 0!=f[l]?f[l]:"")+'"\x3e');else if(m=m.formatter?m.formatter(f[l],f):f[l],l==h.treeField){for(l=0;l<c;l++)k.push('\x3cspan class\x3d"tree-indent"\x3e\x3c/span\x3e');"closed"==f.state?(k.push('\x3cspan class\x3d"tree-hit tree-collapsed"\x3e\x3c/span\x3e'),k.push('\x3cspan class\x3d"tree-icon tree-folder '+(f.iconCls?f.iconCls:"")+'"\x3e\x3c/span\x3e')):f.children&&f.children.length?(k.push('\x3cspan class\x3d"tree-hit tree-expanded"\x3e\x3c/span\x3e'),k.push('\x3cspan class\x3d"tree-icon tree-folder tree-folder-open '+(f.iconCls?f.iconCls:"")+'"\x3e\x3c/span\x3e')):(k.push('\x3cspan class\x3d"tree-indent"\x3e\x3c/span\x3e'),k.push('\x3cspan class\x3d"tree-icon tree-file '+(f.iconCls?f.iconCls:"")+'"\x3e\x3c/span\x3e'));this.hasCheckbox(a,f)?(l=0,(n=e.bsui.getArrayItem(g.checkedRows,h.idField,f[h.idField]))?(l="checked"==n.checkState?1:2,f.checkState=n.checkState,f.checked=n.checked,e.bsui.addArrayItem(g.checkedRows,h.idField,f)):((n=e.bsui.getArrayItem(g.checkedRows,h.idField,f._parentId))&&"checked"==n.checkState&&h.cascadeCheck?(l=1,f.checked=!0,e.bsui.addArrayItem(g.checkedRows,h.idField,f)):f.checked&&e.bsui.addArrayItem(g.tmpIds,f[h.idField]),f.checkState=l?"checked":"unchecked"),k.push('\x3cspan class\x3d"tree-checkbox tree-checkbox'+l+'"\x3e\x3c/span\x3e')):(f.checkState=void 0,f.checked=void 0);k.push('\x3cspan class\x3d"tree-title"\x3e'+m+"\x3c/span\x3e")}else k.push(m);k.push("\x3c/div\x3e");k.push("\x3c/td\x3e")}}return k.join("")},hasCheckbox:function(a,b){var d=e.data(a,"treegrid").options;if(d.checkbox)if(e.isFunction(d.checkbox)){if(d.checkbox.call(a,b))return!0}else if(d.onlyLeafCheck){if(!("open"!=b.state||b.children&&b.children.length))return!0}else return!0;return!1},refreshRow:function(a,b){this.updateRow.call(this,a,b,{})},updateRow:function(a,b,d){function c(d){var c=e(a).treegrid("getColumnFields",d),n=f.finder.getTr(a,b,"body",d?1:2),p=n.find("div.datagrid-cell-rownumber").html(),q=n.find("div.datagrid-cell-check input[type\x3dcheckbox]").is(":checked");n.html(this.renderRow(a,c,d,h,g));n.attr("style",k||"");n.find("div.datagrid-cell-rownumber").html(p);q&&n.find("div.datagrid-cell-check input[type\x3dcheckbox]")._propAttr("checked",!0);m!=b&&(n.attr("id",l+"-"+(d?1:2)+"-"+m),n.attr("node-id",m))}var f=e.data(a,"treegrid").options,g=e(a).treegrid("find",b);e.extend(g,d);var h=e(a).treegrid("getLevel",b)-1,k=f.rowStyler?f.rowStyler.call(a,g):"",l=e.data(a,"datagrid").rowIdPrefix,m=g[f.idField];c.call(this,!0);c.call(this,!1);e(a).treegrid("fixRowHeight",b)},deleteRow:function(a,b){var d=e.data(a,"treegrid").options,c=d.finder.getTr(a,b);c.next("tr.treegrid-tr-tree").remove();c.remove();for(var f=e(a).treegrid("getParent",b),c=f?f.children:e(a).treegrid("getData"),g=0;g<c.length;g++)if(c[g][d.idField]==b){c.splice(g,1);break}f&&0==f.children.length&&(c=d.finder.getTr(a,f[d.idField]),c.next("tr.treegrid-tr-tree").remove(),d=c.children('td[field\x3d"'+d.treeField+'"]').children("div.datagrid-cell"),d.find(".tree-icon").removeClass("tree-folder").addClass("tree-file"),d.find(".tree-hit").remove(),e('\x3cspan class\x3d"tree-indent"\x3e\x3c/span\x3e').prependTo(d));this.setEmptyMsg(a)},onBeforeRender:function(a,b,d){e.isArray(b)&&(d={total:b.length,rows:b},b=null);if(!d)return!1;var c=e.data(a,"treegrid"),f=c.options;if(void 0==d.length)d.footer&&(c.footer=d.footer),d.total&&(c.total=d.total),d=this.transfer(a,b,d.rows);else{var g=function(a,b){for(var d=0;d<a.length;d++){var c=a[d];c._parentId=b;c.children&&c.children.length&&g(c.children,c[f.idField])}};g(d,b)}var h=p(a,b);h?h.children=h.children?h.children.concat(d):d:c.data=c.data.concat(d);this.sort(a,d);this.treeNodes=d;this.treeLevel=e(a).treegrid("getLevel",b)},sort:function(a,b){function d(b){b.sort(function(b,d){for(var c=0,h=0;h<f.length;h++){var c=f[h],k=g[h],c=(e(a).treegrid("getColumnOption",c).sorter||function(a,b){return a==b?0:a>b?1:-1})(b[c],d[c])*("asc"==k?1:-1);if(0!=c)break}return c});for(var c=0;c<b.length;c++){var h=b[c].children;h&&h.length&&d(h)}}var c=e.data(a,"treegrid").options;if(!c.remoteSort&&c.sortName){var f=c.sortName.split(","),g=c.sortOrder.split(",");d(b)}},transfer:function(a,b,d){function c(a,b){for(var d=[],c=0;c<b.length;c++){var e=b[c];e._parentId==a&&(d.push(e),b.splice(c,1),c--)}return d}a=e.data(a,"treegrid").options;d=e.extend([],d);b=c(b,d);for(var f=e.extend([],b);f.length;){var g=f.shift(),h=c(g[a.idField],d);h.length&&(g.children=g.children?g.children.concat(h):h,f=f.concat(h))}return b}});e.fn.treegrid.defaults=e.extend({},e.fn.datagrid.defaults,{treeField:null,checkbox:!1,cascadeCheck:!0,onlyLeafCheck:!1,lines:!1,animate:!1,singleSelect:!0,view:O,rowEvents:e.extend({},e.fn.datagrid.defaults.rowEvents,{mouseover:E(!0),mouseout:E(!1),click:function(a){var b=e(a.target),d=b.closest("tr.datagrid-row");if(d.length&&d.parent().length){var c=d.attr("node-id"),f=e(d).closest("div.datagrid-view").children(".datagrid-f")[0];if(b.hasClass("tree-hit"))I(f,c);else if(b.hasClass("tree-checkbox"))r(f,c);else{var g=e(f).datagrid("options");if(b.parent().hasClass("datagrid-cell-check")||g.singleSelect||!a.shiftKey)e.fn.datagrid.defaults.rowEvents.click(a);else{a=e(f).treegrid("getChildren");var h=e.bsui.indexOfArray(a,g.idField,g.lastSelectedIndex),k=e.bsui.indexOfArray(a,g.idField,c),l=Math.min(Math.max(h,0),k),h=Math.max(h,k),k=a[k],b=b.closest("td[field]",d);b.length&&(b=b.attr("field"),g.onClickCell.call(f,c,b,k[b]));e(f).treegrid("clearSelections");for(c=l;c<=h;c++)e(f).treegrid("selectRow",a[c][g.idField]);g.onClickRow.call(f,k)}}}}}),loader:function(a,b,d){var c=e(this).treegrid("options");if(!c.url)return!1;e.ajax({type:c.method,url:c.url,data:a,dataType:"json",success:function(a){b(a)},error:function(){d.apply(this,arguments)}})},loadFilter:function(a,b){return a},finder:{getTr:function(a,b,d,c){d=d||"body";c=c||0;var f=e.data(a,"datagrid").dc;if(0==c)return f=e.data(a,"treegrid").options,c=f.finder.getTr(a,b,d,1),b=f.finder.getTr(a,b,d,2),c.add(b);if("body"==d)return a=e("#"+e.data(a,"datagrid").rowIdPrefix+"-"+c+"-"+b),a.length||(a=(1==c?f.body1:f.body2).find('tr[node-id\x3d"'+b+'"]')),a;if("footer"==d)return(1==c?f.footer1:f.footer2).find('tr[node-id\x3d"'+b+'"]');if("selected"==d)return(1==c?f.body1:f.body2).find("tr.datagrid-row-selected");if("highlight"==d)return(1==c?f.body1:f.body2).find("tr.datagrid-row-over");if("checked"==d)return(1==c?f.body1:f.body2).find("tr.datagrid-row-checked");if("last"==d)return(1==c?f.body1:f.body2).find("tr:last[node-id]");if("allbody"==d)return(1==c?f.body1:f.body2).find("tr[node-id]");if("allfooter"==d)return(1==c?f.footer1:f.footer2).find("tr[node-id]")},getRow:function(a,b){var d="object"==typeof b?b.attr("node-id"):b;return e(a).treegrid("find",d)},getRows:function(a){return e(a).treegrid("getChildren")}},onBeforeLoad:function(a,b){},onLoadSuccess:function(a,b){},onLoadError:function(){},onBeforeCollapse:function(a){},onCollapse:function(a){},onBeforeExpand:function(a){},onExpand:function(a){},onClickRow:function(a){},onDblClickRow:function(a){},onClickCell:function(a,b){},onDblClickCell:function(a,b){},onContextMenu:function(a,b){},onBeforeEdit:function(a){},onAfterEdit:function(a,b){},onCancelEdit:function(a){},onBeforeCheckNode:function(a,b){},onCheckNode:function(a,b){}})})(jQuery);
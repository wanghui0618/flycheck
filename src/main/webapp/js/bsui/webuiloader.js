/**
 * webuiloader
 * @author ZL
 * @since 2013-7-28
 */
(function() {
    var modules = {
        validate_extend: {
            js: "bsui.validate_extend.js"
        },
        draggable: {
            js: "bsui.draggable.js"
        },
        droppable: {
            js: "bsui.droppable.js"
        },
        resizable: {
            js: "bsui.resizable.js"
        },
        linkbutton: {
            js: "bsui.linkbutton.js",
            css: "linkbutton.css"
        },
        progressbar: {
            js: "bsui.progressbar.js",
            css: "progressbar.css"
        },
        tooltip:{
			js:'bsui.tooltip.js',
			css:'tooltip.css'
		},
        pagination:{
			js:'bsui.pagination.js',
			css:'pagination.css',
			dependencies:['linkbutton']
		},
        edatagrid: {
            js: "bsui.edatagrid.js",
            dependencies: ["datagrid", "messager"]
        },
        datagrid:{
			js:'bsui.datagrid.js',
			css:'datagrid.css',
			dependencies:['panel','resizable','linkbutton','pagination']
		},
		treegrid:{
			js:'bsui.treegrid.js',
			css:'tree.css',
			dependencies:['datagrid']
		},
		propertygrid:{
			js:'bsui.propertygrid.js',
			css:'propertygrid.css',
			dependencies:['datagrid']
		},
        datalist:{
			js:'bsui.datalist.js',
			css:'datalist.css',
			dependencies:['datagrid']
		},
		panel:{
			js:'bsui.panel.js',
			css:'panel.css'
		},
		window:{
			js:'bsui.window.js',
			css:'window.css',
			dependencies:['resizable','draggable','panel']
		},
		dialog:{
			js:'bsui.dialog.js',
			css:'dialog.css',
			dependencies:['linkbutton','window']
		},
        messager:{
			js:'bsui.messager.js',
			css:'messager.css',
			dependencies:['linkbutton','dialog','progressbar']
		},
		layout:{
			js:'bsui.layout.js',
			css:'layout.css',
			dependencies:['resizable','panel']
		},
		form:{
			js:'bsui.form.js'
		},
		menu:{
			js:'bsui.menu.js',
			css:'menu.css'
		},
        tabs: {
			js:'bsui.tabs.js',
			css:'tabs.css',
			dependencies:['panel','linkbutton']
		},
		menubutton:{
			js:'bsui.menubutton.js',
			css:'menubutton.css',
			dependencies:['linkbutton','menu']
		},
		splitbutton:{
			js:'bsui.splitbutton.js',
			css:'splitbutton.css',
			dependencies:['menubutton']
		},
		switchbutton:{
			js:'bsui.switchbutton.js',
			css:'switchbutton.css'
		},
		accordion:{
			js:'bsui.accordion.js',
			css:'accordion.css',
			dependencies:['panel']
		},
        calendar:{
			js:'bsui.calendar.js',
			css:'calendar.css'
		},
		textbox:{
			js:'bsui.textbox.js',
			//css:'textbox.css',
			dependencies:['validatebox','linkbutton']
		},
		passwordbox:{
			js:'bsui.passwordbox.js',
			css:'passwordbox.css',
			dependencies:['textbox']
		},
		filebox:{
			js:'bsui.filebox.js',
			css:'filebox.css',
			dependencies:['textbox']
		},
        combo:{
			js:'bsui.combo.js',
			css:'combo.css',
			dependencies:['textbox','panel']
		},
		combobox:{
			js:'bsui.combobox.js',
			css:'combobox.css',
			dependencies:['combo']
		},
		combotree:{
			js:'bsui.combotree.js',
			dependencies:['combo','tree']
		},
		combogrid:{
			js:'bsui.combogrid.js',
			dependencies:['combo','datagrid']
		},
		combotreegrid:{
			js:'bsui.combotreegrid.js',
			dependencies:['combo','treegrid']
		},
		validatebox:{
			js:'bsui.validatebox.js',
			css:'validatebox.css',
			dependencies:['tooltip']
		},
        numberbox:{
			js:'bsui.numberbox.js',
			dependencies:['textbox']
		},
		searchbox:{
			js:'bsui.searchbox.js',
			css:'searchbox.css',
			dependencies:['menubutton','textbox']
		},
		spinner:{
			js:'bsui.spinner.js',
			css:'spinner.css',
			dependencies:['textbox']
		},
		numberspinner:{
			js:'bsui.numberspinner.js',
			dependencies:['spinner','numberbox']
		},
		timespinner:{
			js:'bsui.timespinner.js',
			dependencies:['spinner']
		},
		datetimespinner:{
			js:'bsui.datetimespinner.js',
			dependencies:['datebox','timespinner']
		},
        tree:{
			js:'bsui.tree.js',
			css:'tree.css',
			dependencies:['draggable','droppable']
		},
		datebox:{
			js:'bsui.datebox.js',
			css:'datebox.css',
			dependencies:['calendar','combo']
		},
		datetimebox:{
			js:'bsui.datetimebox.js',
			dependencies:['datebox','timespinner']
		},
		slider:{
			js:'bsui.slider.js',
			css:'slider.css',
			dependencies:['draggable']
		},
		parser:{
			js:'bsui.parser.js'
		},
		mobile:{
			js:'bsui.mobile.js'
		}
    };
    
    var locales = {
        'zh_CN':'bsui-lang_zh_CN.js'
    };
    
    bsuiloader = {
        modules: modules,
        locales: locales,
        base: '.',
        theme: "default",
        css: true,
        locale: null
    };
    
    bsuiloader.load = function(name, callback) {
        if (_timerLoading) clearTimeout(_timerLoading);
        
        var mm = [];
        var _valiext = false;
        if ('string' === typeof(name)) {
            add(name);
            if (name === 'validatebox') _valiext = true;
        } else {
            for (var i = 0; i < name.length; i++) {
                add(name[i]);
                if (name[i] === 'validatebox') _valiext = true;
            }
        }
        function add(_f, _fc) {
            if (!modules[_f]) return;
            var d0 = modules[_f]["dependencies"];
            if (d0 && d0.length) {
                for (var i = 0; i < d0.length; i++) {
                    add(d0[i]);
                    if (d0[i] === 'validatebox') _valiext = true;
                }
            }
            mm.push(modules[_f]);
        }
        function includeF(mm) {
            if (mm && mm.css) {
                includeCSS('/js/bsui/themes/' + bsuiloader.theme + '/' + mm.css);
            }
            if (mm && mm.js) {
                includeJS('/js/bsui/plugins/' + mm.js);
            }
        }
        if (mm && mm.length) {
            for (var i = 0; i < mm.length; i++) {
                includeF(mm[i]);
            }
        }
        if ('undefined' != typeof(bsuiloader.locales[bsuiloader.locale]) && bsuiloader.locales[bsuiloader.locale]) {
        	//includeJS('/js/bsui/locale/' + bsuiloader.locales[bsuiloader.locale], 1);
            if ('undefined' === typeof(langUsed)) {
            	includeJS('/js/bsui/locale/' + bsuiloader.locales[bsuiloader.locale], 1);
            }
            langUsed.call(jQuery);
        }
        if (_valiext) {
            includeF(modules['validate_extend']);
        }
        mm = null;
        if (callback) callback();
        _timerLoading = setTimeout(_showBodyElement, 120);
    };
    
    window.using = bsuiloader.load;
	
	if (window.jQuery){
		jQuery(function(){
			bsuiloader.load('parser', function(){
				jQuery.parser.parse();
			});
		});
	}
})();
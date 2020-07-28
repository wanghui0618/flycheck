$(function() {
	// 查询医疗机构编码
	$('#hospitalId').combogrid({
		panelWidth : 450,
		url : $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=hospitalId",
		fitColumns : true,
		striped : true,
		editable : true,
		pagination : true, // 是否分页
		rownumbers : true, // 序号
		collapsible : false, // 是否可折叠的
		fit : true, // 自动大小
		method : 'post',
		columns : [[ {field : 'hospitalId', title : '机构编码', width : 50}]],
		onLoadSuccess : function(data) {
			var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
			var pagesize = data._pagelines;
			var page = data._currpage;
			var index = (page - 1) * pagesize + 1;
			if ($rowDoms.length > 0) {
				for (var i = 0; i < $rowDoms.length; i++) {
					$rowDoms[i].innerText = index;
					index++;
				}
			}
			$(this).datagrid('fixRownumber');
			},
		onSelect : function(d, row) {
			$('#hospitalId').combogrid("setValue", row.hospitalId);
		}
	});


	$('#hospitalId').combogrid('textbox').unbind()// 先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
	.keydown(
		function(e) {
			if (e.keyCode == 13) {
				var keyValue = $('#hospitalId').combogrid('textbox').val();
				var queryParams = $('#hospitalId').combogrid("grid").datagrid('options').queryParams;
				queryParams.keyword = keyValue;// keyword;
				// 重新加载
				$('#hospitalId').combogrid("grid").datagrid("reload");
				$('#hospitalId').combogrid("setValue", keyValue);
			}
		});
	$('#hospitalId').combogrid('textbox').keyup(function(e) {
		var keyValue = $('#hospitalId').combogrid('textbox').val();
		$('#hospitalId').combogrid("setValue", keyValue);
	});
	$(function() {
		$('#hospitalId').combobox('textbox').bind('focus', function() {
			$('#hospitalId').combobox('showPanel');
			$('#hospitalId').combogrid("setValue", '');
		});
	});

	// 取得分页组件对象
	var pager = $('#hospitalId').combogrid('grid').datagrid('getPager');
	if (pager) {
		$(pager).pagination({
			pageSize : 10, // 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 30, 40, 50 ], // 可以设置每页记录条数的列表
			beforePageText : '第', // 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			// 选择页的处理
			onSelectPage : function(pageNumber, pageSize) { // 按分页的设置取数据
				var keyword = $('#hospitalId').combogrid('textbox').val();
				var url = $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=hospitalId&keyword=" + keyword;
				getData(pageNumber, pageSize, url);
				// 设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
				$('#hospitalId').combogrid("grid").datagrid(
					'options').pageSize = pageSize;
				},
			onChangePageSize : function() {
				}, // 改变页显示条数的处理
							// (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
			onRefresh : function(pageNumber, pageSize) { // 点击刷新的处理
				 }
		});
	}

	var getData = function(page, pagesize, url) {
		$.ajax({
			url : url,
			type : "POST",
			data : {
				"page" : page,
				"rows" : pagesize
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
			},
			success : function(data) {
				$('#hospitalId').combogrid("grid").datagrid("loadData", data);
			}
		});

	};

	$.extend($.fn.datagrid.methods, {
		fixRownumber : function(jq) {
			return jq
				.each(function() {
					var panel = $(this).datagrid("getPanel");
					var clone = $(".datagrid-cell-rownumber", panel).last().clone();
					clone.css({
						"position" : "absolute",
						left : -1000
					}).appendTo("body");
					var width = clone.width("auto").width();
					if (width > 25) {
						$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
						$(this).datagrid("resize");
						clone.remove();
						clone = null;
					} else {
						$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
						$(this).datagrid("resize");
					}
				});
		}
	});

});
/**
 * 查询医疗机构名称
 * 
 * @returns
 */
$(function() {
	//查询医疗机构名称
	$('#hospitalName').combogrid({
		panelWidth : 450,
		url : $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=hospitalName",
		fitColumns : true,
		striped : true,
		editable : true,
		pagination : true, // 是否分页
		rownumbers : true, // 序号
		collapsible : false, // 是否可折叠的
		fit : true, // 自动大小
		method : 'post',
		columns : [[ {field : 'hospitalName', title : '医院名称', width : 50},]],
		onLoadSuccess : function(data) {
			var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
			var pagesize = data._pagelines;
			var page = data._currpage;
			var index = (page - 1) * pagesize + 1;
			if ($rowDoms.length > 0) {
				for (var i = 0; i < $rowDoms.length; i++) {
					$rowDoms[i].innerText = index;
					index++;
				}
			}
			$(this).datagrid('fixRownumber');
			},
		onSelect : function(d, row) {
			$('#hospitalName').combogrid("setValue",
				row.hospitalName);
		}
	});

	$('#hospitalName').combogrid('textbox').unbind()// 先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
	.keydown(function(e) {
		if (e.keyCode == 13) {
			var keyValue = $('#hospitalName').combogrid('textbox').val();
			var queryParams = $('#hospitalName').combogrid("grid").datagrid('options').queryParams;
			queryParams.keyword = keyValue;// keyword;
			// 重新加载
			$('#hospitalName').combogrid("grid").datagrid("reload");
			$('#hospitalName').combogrid("setValue", keyValue);
		}
	});
	$('#hospitalName').combogrid('textbox').keyup(function(e) {
		var keyValue = $('#hospitalName').combogrid('textbox').val();
		$('#hospitalName').combogrid("setValue", keyValue);
	});

	$(function() {
		$('#hospitalName').combobox('textbox').bind('focus', function() {
			$('#hospitalName').combobox('showPanel');
			$('#hospitalName').combogrid("setValue", '');
		});
	});

	// 取得分页组件对象
	var pager = $('#hospitalName').combogrid('grid').datagrid('getPager');
	if (pager) {
		$(pager).pagination({
			pageSize : 10, // 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 30, 40, 50 ], // 可以设置每页记录条数的列表
			beforePageText : '第', // 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			// 选择页的处理
			onSelectPage : function(pageNumber, pageSize) { // 按分页的设置取数据
				var keyword = $('#hospitalName').combogrid('textbox').val();
				var url = $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=hospitalName&keyword=" + keyword;
				getData(pageNumber, pageSize, url);
				// 设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
				$('#hospitalName').combogrid("grid").datagrid('options').pageSize = pageSize;
			},
			onChangePageSize : function() {}, // 改变页显示条数的处理
			// (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
			onRefresh : function(pageNumber, pageSize) { // 点击刷新的处理
			}
		});
	}

	var getData = function(page, pagesize, url) {
		$.ajax({
			url : url,
			type : "POST",
			data : {
				"page" : page,
				"rows" : pagesize
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
			},
			success : function(data) {
				$('#hospitalName').combogrid("grid").datagrid("loadData", data);
			}
		});
	};

	$.extend($.fn.datagrid.methods, {
		fixRownumber : function(jq) {
			return jq.each(function() {
				var panel = $(this).datagrid("getPanel");
				var clone = $(".datagrid-cell-rownumber", panel).last().clone();
				clone.css({
					"position" : "absolute",
					left : -1000
				}).appendTo("body");
				var width = clone.width("auto").width();
				if (width > 25) {
					$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
					$(this).datagrid("resize");
					clone.remove();
					clone = null;
				} else {
					$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
					$(this).datagrid("resize");
				}
			});
		}
	});
});
/**
 * 查询诊断编码
 * 
 * @returns
 */
$(function() {
	//查询医疗机构名称
	$('#admissionDiseaseId').combogrid({
		panelWidth : 450,
		url : $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=admissionDiseaseId",
		fitColumns : true,
		striped : true,
		editable : true,
		pagination : true, // 是否分页
		rownumbers : true, // 序号
		collapsible : false, // 是否可折叠的
		fit : true, // 自动大小
		method : 'post',
		columns : [[ {field : 'admissionDiseaseId', title : '医院名称', width : 50},]],
		onLoadSuccess : function(data) {
			var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
			var pagesize = data._pagelines;
			var page = data._currpage;
			var index = (page - 1) * pagesize + 1;
			if ($rowDoms.length > 0) {
				for (var i = 0; i < $rowDoms.length; i++) {
					$rowDoms[i].innerText = index;
					index++;
				}
			}
			$(this).datagrid('fixRownumber');
		},
		onSelect : function(d, row) {
			$('#admissionDiseaseId').combogrid("setValue",
				row.admissionDiseaseId);
		}
	});

	$('#admissionDiseaseId').combogrid('textbox').unbind()// 先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
	.keydown(function(e) {
		if (e.keyCode == 13) {
			var keyValue = $('#admissionDiseaseId').combogrid('textbox').val();
			var queryParams = $('#admissionDiseaseId').combogrid("grid").datagrid('options').queryParams;
			queryParams.keyword = keyValue;// keyword;
			// 重新加载
			$('#admissionDiseaseId').combogrid("grid").datagrid("reload");
			$('#admissionDiseaseId').combogrid("setValue", keyValue);
		}
	});
	$('#admissionDiseaseId').combogrid('textbox').keyup(function(e) {
		var keyValue = $('#admissionDiseaseId').combogrid('textbox').val();
		$('#admissionDiseaseId').combogrid("setValue", keyValue);
	});
	$(function() {
		$('#admissionDiseaseId').combobox('textbox').bind('focus', function() {
			$('#admissionDiseaseId').combobox('showPanel');
			$('#admissionDiseaseId').combogrid("setValue", '');
		});
	});

	// 取得分页组件对象
	var pager = $('#admissionDiseaseId').combogrid('grid').datagrid('getPager');
	if (pager) {
		$(pager).pagination({
			pageSize : 10, // 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 30, 40, 50 ], // 可以设置每页记录条数的列表
			beforePageText : '第', // 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			// 选择页的处理
			onSelectPage : function(pageNumber, pageSize) { // 按分页的设置取数据
				var keyword = $('#admissionDiseaseId').combogrid('textbox').val();
				var url = $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=admissionDiseaseId&keyword=" + keyword;
				getData(pageNumber, pageSize, url);
				// 设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
				$('#admissionDiseaseId').combogrid("grid").datagrid('options').pageSize = pageSize;},
			onChangePageSize : function() {}, // 改变页显示条数的处理
			// (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
			onRefresh : function(pageNumber, pageSize) { // 点击刷新的处理
			}
		});
	}

	var getData = function(page, pagesize, url) {
		$.ajax({
			url : url,
			type : "POST",
			data : {
				"page" : page,
				"rows" : pagesize
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
			},
			success : function(data) {
				$('#admissionDiseaseId').combogrid("grid").datagrid("loadData",
						data);
			}
		});

	};

	$.extend($.fn.datagrid.methods, {
		fixRownumber : function(jq) {
			return jq.each(function() {
				var panel = $(this).datagrid("getPanel");
				var clone = $(".datagrid-cell-rownumber", panel).last().clone();
				clone.css({
					"position" : "absolute",
					left : -1000
				}).appendTo("body");
				var width = clone.width("auto").width();
				if (width > 25) {
					$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
					$(this).datagrid("resize");clone.remove();
					clone = null;
				} else {
					$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
					$(this).datagrid("resize");
				}
			});
		}
	});
});


/**
 * 查询诊断名称
 * 
 * @returns
 */
$(function() {
	//查询医疗机构名称
	$('#admissionDiseaseName').combogrid({
		panelWidth : 450,
		url : $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=admissionDiseaseName",
		fitColumns : true,
		striped : true,
		editable : true,
		pagination : true, // 是否分页
		rownumbers : true, // 序号
		collapsible : false, // 是否可折叠的
		fit : true, // 自动大小
		method : 'post',
		columns : [[ {field : 'admissionDiseaseName', title : '医院名称', width : 50} ]],
		onLoadSuccess : function(data) {
			var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
			var pagesize = data._pagelines;
			var page = data._currpage;
			var index = (page - 1) * pagesize + 1;
			if ($rowDoms.length > 0) {
				for (var i = 0; i < $rowDoms.length; i++) {
					$rowDoms[i].innerText = index;
					index++;
				}
			}
			$(this).datagrid('fixRownumber');
		},
		onSelect : function(d, row) {
			$('#admissionDiseaseName').combogrid("setValue", row.admissionDiseaseName);
		}
	});

	$('#admissionDiseaseName').combogrid('textbox').unbind()// 先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
	.keydown(function(e) {
		if (e.keyCode == 13) {
			var keyValue = $('#admissionDiseaseName').combogrid('textbox').val();
			var queryParams = $('#admissionDiseaseName').combogrid("grid").datagrid('options').queryParams;
			queryParams.keyword = keyValue;// keyword;
			// 重新加载
			$('#admissionDiseaseName').combogrid("grid").datagrid("reload");
			$('#admissionDiseaseName').combogrid("setValue", keyValue);
		}
	});
	$('#admissionDiseaseName').combogrid('textbox').keyup(function(e) {
		var keyValue = $('#admissionDiseaseName').combogrid('textbox').val();
		$('#admissionDiseaseName').combogrid("setValue", keyValue);
	});
	$(function() {
		$('#admissionDiseaseName').combobox('textbox').bind('focus', function() {
			$('#admissionDiseaseName').combobox('showPanel');
			$('#admissionDiseaseName').combogrid("setValue", '');
		});
	});

	// 取得分页组件对象
	var pager = $('#admissionDiseaseName').combogrid('grid').datagrid('getPager');
	if (pager) {
		$(pager).pagination({
			pageSize : 10, // 每页显示的记录条数，默认为10
			pageList : [ 10, 20, 30, 40, 50 ], // 可以设置每页记录条数的列表
			beforePageText : '第', // 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			// 选择页的处理
			onSelectPage : function(pageNumber, pageSize) { // 按分页的设置取数据
				var keyword = $('#admissionDiseaseName').combogrid('textbox').val();
				var url = $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=admissionDiseaseName&keyword=" + keyword;
				getData(pageNumber, pageSize, url);
				// 设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
				$('#admissionDiseaseName').combogrid("grid").datagrid('options').pageSize = pageSize;
			},
			onChangePageSize : function() {}, // 改变页显示条数的处理
			// (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
			onRefresh : function(pageNumber, pageSize) { // 点击刷新的处理
			}
		});
	}

	var getData = function(page, pagesize, url) {
		$.ajax({
			url : url,
			type : "POST",
			data : {
				"page" : page,
				"rows" : pagesize
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
			},
			success : function(data) {
				$('#admissionDiseaseName').combogrid("grid").datagrid("loadData", data);
			}
		});
	};

	$.extend($.fn.datagrid.methods, {
		fixRownumber : function(jq) {
			return jq.each(function() {
					var panel = $(this).datagrid("getPanel");
					var clone = $(".datagrid-cell-rownumber", panel).last().clone();
					clone.css({
						"position" : "absolute",
						left : -1000
					}).appendTo("body");
					var width = clone.width("auto").width();
					if (width > 25) {
						$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
						$(this).datagrid("resize");
						clone.remove();
						clone = null;
					} else {
						$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
						$(this).datagrid("resize");
					}
			});
		}
	});
});


/**
 * 医保项目编码
 *
 * @returns
 */
$(function() {
    //查询医保项目编码
    $('#itemId').combogrid({
        panelWidth : 450,
        url : $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=itemId",
        fitColumns : true,
        striped : true,
        editable : true,
        pagination : true, // 是否分页
        rownumbers : true, // 序号
        collapsible : false, // 是否可折叠的
        fit : true, // 自动大小
        method : 'post',
        columns : [[ {field : 'itemId', title : '医保项目编码', width : 50},]],
        onLoadSuccess : function(data) {
            var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
            var pagesize = data._pagelines;
            var page = data._currpage;
            var index = (page - 1) * pagesize + 1;
            if ($rowDoms.length > 0) {
                for (var i = 0; i < $rowDoms.length; i++) {
                    $rowDoms[i].innerText = index;
                    index++;
                }
            }
            $(this).datagrid('fixRownumber');
        },
        onSelect : function(d, row) {
            $('#itemId').combogrid("setValue",
                row.hospitalName);
        }
    });

    $('#itemId').combogrid('textbox').unbind()// 先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
        .keydown(
            function(e) {
                if (e.keyCode == 13) {
                    var keyValue = $('#itemId').combogrid('textbox').val();
                    var queryParams = $('#itemId').combogrid("grid").datagrid('options').queryParams;
                    queryParams.keyword = keyValue;// keyword;
                    // 重新加载
                    $('#itemId').combogrid("grid").datagrid("reload");
                    $('#itemId').combogrid("setValue", keyValue);
                }
            });
    $('#itemId').combogrid('textbox').keyup(function(e) {
        var keyValue = $('#itemId').combogrid('textbox').val();
        $('#itemId').combogrid("setValue", keyValue);
    });
    $(function() {
        $('#itemId').combobox('textbox').bind('focus', function() {
            $('#itemId').combobox('showPanel');
            $('#itemId').combogrid("setValue", '');
        });
    });

    // 取得分页组件对象
    var pager = $('#itemId').combogrid('grid').datagrid('getPager');
    if (pager) {
        $(pager).pagination({
            pageSize : 10, // 每页显示的记录条数，默认为10
            pageList : [ 10, 20, 30, 40, 50 ], // 可以设置每页记录条数的列表
            beforePageText : '第', // 页数文本框前显示的汉字
            afterPageText : '页    共 {pages} 页',
            displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            // 选择页的处理
            onSelectPage : function(pageNumber, pageSize) { // 按分页的设置取数据
                var keyword = $('#itemId').combogrid('textbox').val();
                var url = $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=itemId&keyword=" + keyword;
                getData(pageNumber, pageSize, url);
                // 设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
                $('#itemId').combogrid("grid").datagrid(
                    'options').pageSize = pageSize;
            },
            onChangePageSize : function() {
            }, // 改变页显示条数的处理
            // (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
            onRefresh : function(pageNumber, pageSize) { // 点击刷新的处理
            }
        });
    }

    var getData = function(page, pagesize, url) {
        $.ajax({
            url : url,
            type : "POST",
            data : {
                "page" : page,
                "rows" : pagesize
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.messager.progress('close');
            },
            success : function(data) {
                $('#itemId').combogrid("grid").datagrid("loadData", data);
            }
        });

    };

    $.extend($.fn.datagrid.methods, {
        fixRownumber : function(jq) {
            return jq
                .each(function() {
                    var panel = $(this).datagrid("getPanel");
                    var clone = $(".datagrid-cell-rownumber", panel).last().clone();
                    clone.css({
                        "position" : "absolute",
                        left : -1000
                    }).appendTo("body");
                    var width = clone.width("auto").width();
                    if (width > 25) {
                        $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
                        $(this).datagrid("resize");
                        clone.remove();
                        clone = null;
                    } else {
                        $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
                        $(this).datagrid("resize");
                    }
                });
        }
    });


    /**
     * 医保项目名称
     *
     * @returns
     */
    $(function() {
        //查询医保项目名称
        $('#itemName').combogrid({
            panelWidth : 450,
            url : $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=itemName",
            fitColumns : true,
            striped : true,
            editable : true,
            pagination : true, // 是否分页
            rownumbers : true, // 序号
            collapsible : false, // 是否可折叠的
            fit : true, // 自动大小
            method : 'post',
            columns : [[ {field : 'itemName', title : '医保项目编码', width : 50},]],
            onLoadSuccess : function(data) {
                var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
                var pagesize = data._pagelines;
                var page = data._currpage;
                var index = (page - 1) * pagesize + 1;
                if ($rowDoms.length > 0) {
                    for (var i = 0; i < $rowDoms.length; i++) {
                        $rowDoms[i].innerText = index;
                        index++;
                    }
                }
                $(this).datagrid('fixRownumber');
            },
            onSelect : function(d, row) {
                $('#itemName').combogrid("setValue",
                    row.hospitalName);
            }
        });

        $('#itemName').combogrid('textbox').unbind()// 先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
            .keydown(
                function(e) {
                    if (e.keyCode == 13) {
                        var keyValue = $('#itemName').combogrid('textbox').val();
                        var queryParams = $('#itemName').combogrid("grid").datagrid('options').queryParams;
                        queryParams.keyword = keyValue;// keyword;
                        // 重新加载
                        $('#itemName').combogrid("grid").datagrid("reload");
                        $('#itemName').combogrid("setValue", keyValue);
                    }
                });
        $('#itemName').combogrid('textbox').keyup(function(e) {
            var keyValue = $('#itemName').combogrid('textbox').val();
            $('#itemName').combogrid("setValue", keyValue);
        });
        $(function() {
            $('#itemName').combobox('textbox').bind('focus', function() {
                $('#itemName').combobox('showPanel');
                $('#itemName').combogrid("setValue", '');
            });
        });

        // 取得分页组件对象
        var pager = $('#itemName').combogrid('grid').datagrid('getPager');
        if (pager) {
            $(pager).pagination({
                pageSize : 10, // 每页显示的记录条数，默认为10
                pageList : [ 10, 20, 30, 40, 50 ], // 可以设置每页记录条数的列表
                beforePageText : '第', // 页数文本框前显示的汉字
                afterPageText : '页    共 {pages} 页',
                displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                // 选择页的处理
                onSelectPage : function(pageNumber, pageSize) { // 按分页的设置取数据
                    var keyword = $('#itemName').combogrid('textbox').val();
                    var url = $WEB_ROOT_PATH + "/dhccApi/childrensDrugs/childrensDrugs/getDict?dictName=itemName&keyword=" + keyword;
                    getData(pageNumber, pageSize, url);
                    // 设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
                    $('#itemName').combogrid("grid").datagrid(
                        'options').pageSize = pageSize;
                },
                onChangePageSize : function() {
                }, // 改变页显示条数的处理
                // (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
                onRefresh : function(pageNumber, pageSize) { // 点击刷新的处理
                }
            });
        }

        var getData = function(page, pagesize, url) {
            $.ajax({
                url : url,
                type : "POST",
                data : {
                    "page" : page,
                    "rows" : pagesize
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messager.progress('close');
                },
                success : function(data) {
                    $('#itemName').combogrid("grid").datagrid("loadData", data);
                }
            });

        };

        $.extend($.fn.datagrid.methods, {
            fixRownumber : function(jq) {
                return jq
                    .each(function() {
                        var panel = $(this).datagrid("getPanel");
                        var clone = $(".datagrid-cell-rownumber", panel).last().clone();
                        clone.css({
                            "position" : "absolute",
                            left : -1000
                        }).appendTo("body");
                        var width = clone.width("auto").width();
                        if (width > 25) {
                            $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
                            $(this).datagrid("resize");
                            clone.remove();
                            clone = null;
                        } else {
                            $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
                            $(this).datagrid("resize");
                        }
                    });
            }
        });

    });

});


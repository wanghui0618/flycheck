$(function () {
    //-------------------查询医疗机构
    $('#getItemId').combogrid({
        panelWidth: 450,
        textField: 'code',    //显示的字段
        textField: 'name',
        url: $WEB_ROOT_PATH +"/dhccApi/findDict/findDict/findDict?dictName=itemid",
        fitColumns: true,
        fit:true,
        striped: true,
        editable: true,
        pagination: true,           //是否分页
        rownumbers: true,           //序号
        collapsible: false,         //是否可折叠的
        fit: true,                  //自动大小
        method: 'post',
        multiple: true,
        idField:'code',
        checkOnSelect:true,
        columns: [[
        	{ field: 'id', title: 'Toolbutton', width: 50,checkbox:true },
            { field: 'code', title: '医保项目编码', width: 50 },
            { field: 'name', title: '医保项目名称', width: 80 }

        ]],
        onLoadSuccess:function(data){
            var $rowDoms = $(this).parent().children().find(".datagrid-btable .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
            var pagesize=data._pagelines;
            var page=data._currpage;
            var index=(page-1)*pagesize+1;
            if($rowDoms.length > 0){
                for(var i = 0;i < $rowDoms.length;i++){
                    $rowDoms[i].innerText=index;
                    index++;
                }
            }
            $(this).datagrid('fixRownumber');
        },
/*        onSelect: function (d,row) {
            $('#getItemId').combogrid("setValue", row.code);
            $('#itemName').val(row.name);
            $('#itemCode').val(row.code);
        }*/
    });

    
    $('#getItemId').combobox('panel').panel({   
    	onClose:function(){
    		var code='';
    		var nodes=$('#getItemId').combogrid('grid').datagrid('getChecked');
    		for(var i=0;i<nodes.length;i++){
    			code=code+nodes[i].code+',';
    		}
    		code=code.substr(0,code.length-1);
    		$('#itemId').val(code);
    	    }   
    	});  
    
    
    
    $('#getItemId').combogrid('textbox').unbind()//先解绑所有事件，要不输入的内容找不到匹配项，回车时输入框内容会被清空
        .keydown(function (e) {
            if (e.keyCode == 13) {
                var keyValue = $('#getItemId').combogrid('textbox').val();
                var queryParams = $('#getItemId').combogrid("grid").datagrid('options').queryParams;
                queryParams.keyword = keyValue;//keyword;
                //重新加载
                $('#getItemId').combogrid("grid").datagrid("reload");

                $('#getItemId').combogrid("setValue", keyValue);
            }
        });
    $('#getItemId').combogrid('textbox').keyup(function(e){
        var keyValue = $('#getItemId').combogrid('textbox').val();
        $('#getItemId').combogrid("setValue", keyValue);
    });

    $(function(){
        $('#getItemId').combobox('textbox').bind('focus',function(){
            $('#getItemId').combobox('showPanel');
           /* $('#getItemId').combogrid("setValue", '');
            $('#itemCode').val('');
            $('#itemName').val('');*/
        });
    });

    //取得分页组件对象
    var pager = $('#getItemId').combogrid('grid').datagrid('getPager');
    if (pager) {
        $(pager).pagination({
            pageSize: 10,               //每页显示的记录条数，默认为10
            pageList: [10, 20, 30, 40, 50],       //可以设置每页记录条数的列表
            beforePageText: '第',       //页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            //选择页的处理
            onSelectPage: function (pageNumber, pageSize) { //按分页的设置取数据
                var keyword =$('#getItemId').combogrid('textbox').val();
                var url=$WEB_ROOT_PATH +"/dhccApi/findDict/findDict/findDict?dictName=itemid&keyword="+keyword;
                getData(pageNumber, pageSize,url);
                //设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
                $('#getItemId').combogrid("grid").datagrid('options').pageSize = pageSize;
            },
            onChangePageSize: function () {}, //改变页显示条数的处理 (处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了)
            onRefresh: function (pageNumber, pageSize) { //点击刷新的处理
            }
        });
    }

    var getData = function (page, pagesize,url) {
        $.ajax({
            type: "POST",
            url: url,
            type : "POST",
            data: {
                "page" : page,
                "rows" : pagesize
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.messager.progress('close');
            },
            success: function (data) {
                $('#getItemId').combogrid("grid").datagrid("loadData", data);
            }
        });

    };

    $.extend($.fn.datagrid.methods, {
        fixRownumber : function (jq) {
            return jq.each(function () {
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

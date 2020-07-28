document.getElementById("lefttab").style.height=document.documentElement.clientHeight-15+"px";
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
    $("#diagNameText").html("按钮管理如下：");
    InitZTree();

       //点击树节点，加载右边able表
    function loadTable(data) {
    	var url;
    	var cols;
    	if(data==""){
    		url = $WEB_ROOT_PATH + '/dhccApi/button/button/list';
    		cols=[[
                {type: 'numbers',align: 'center', title: '序号'}
                , {field: 'id',align: 'center', title: 'ID', hide: true}
                , {field: 'buttonName',align: 'center', title: '按钮名称'}
                , {field: 'buttonCode',align: 'center', title: '按钮代码'}
                , {field: 'buttonPageName',align: 'center', title: '所属页面'}
            ]];
    	}
        if (data.menuCode == undefined) {
        	url = $WEB_ROOT_PATH + '/dhccApi/button/button/list';
        	cols=[[
                {type: 'numbers',align: 'center', title: '序号'}
                , {field: 'id',align: 'center', title: 'ID', hide: true}
                , {field: 'buttonName',align: 'center', title: '按钮名称'}
                , {field: 'buttonCode', align: 'center',title: '按钮代码'}
                , {field: 'buttonPageName',align: 'center', title: '所属页面'}
            ]];
        } else {
        	url = $WEB_ROOT_PATH + '/dhccApi/button/button/listTreeTable?button.button=' + data.menuCode + '&button.id=' + data.id + '&button.parentLeaf=' + data.parentLeaf;
        	cols=[[
                {type: 'numbers',align: 'center', title: '序号'}
                , {field: 'id', title: 'ID', hide: true}
                , {field: 'menuName',align: 'center', title: '按钮名称'}
                , {field: 'menuCode', align: 'center',title: '按钮代码'}
                , {field: 'onclickAft',align: 'center', title: '所属页面'}
            ]];
        }
        table.render({
            elem: '#buttonTable',
            url: url,
            height: tableHeight,
            cols:cols,
            page: true
        });
    }

    /*function InitTable() {
        table.render({
            elem: '#buttonTable'
               , url: $WEB_ROOT_PATH + '/dhccApi/button/button/list'
           // , url: $WEB_ROOT_PATH + '/dhccApi/button/button/getButtonAuthoByUserId'
            , cellMinWidth: 80
            , height: 435
            ,cols: [[
                  {type: 'numbers', title: '序号'}
                , {field: 'id', title: 'ID', hide: true}
                , {field: 'buttonName', title: '按钮名称'}
                , {field: 'buttonCode', title: '按钮代码'}
                , {field: 'buttonPageName', title: '所属页面'}
            ]],
            page: true
        })
    };*/
    loadTable("");

    //左边树展示
    function InitZTree() {
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                selectedMulti: false,
                addHoverDom: addHoverDom, //显示按钮
                removeHoverDom: removeHoverDom, //隐藏按钮
            },
            callback: {
                // 单击事件
                onClick: zTreeOnClick
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId"
                }
            },

        };
        $.ajax({
            async: false, //是否异步
            cache: false, //是否使用缓存
            type: 'POST', //请求方式：post
            url: $WEB_ROOT_PATH + '/dhccApi/button/button/listAllTree',//请求的路径
            /* data: {
                 "dictdiag.id": id,
                 "dictdiag.parentIndex": parentIndex
             },*/
            success: function (data) {
                treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
            }
        });
        var t = $("#tree-sorts");
        t.html();
        t = $.fn.zTree.init(t, setting, treeNodes);
        var nodes=t.getNodes()
        if (nodes.length>0) {
            for (var i = 0; i < nodes.length; i++) {
                t.expandNode(nodes[i], true, false, false);//默认展开第一级节点
            }
        }
    }

    function addHoverDom(treeId, treeNode){
        var sObj = $("#" + treeNode.tId + "_a");
        var level = parseInt(treeNode.parentLeaf);
        var name =treeNode.name
        if(treeNode.isAdd=='1') {
            if (treeNode.editNameFlag || $("#editBtn_" + treeNode.tId).length > 0) return;
            var addStr =
                "<span class='layui-icon layui-icon-edit' style='font-size: 25px; color: #1E9FFF' id='editBtn_" + treeNode.tId +
                "' title='修改'></span>" +
                "<span class='layui-icon layui-icon-delete' style='font-size: 25px; color:red' id='deleteBtn_" + treeNode.tId +
                "' title='删除'></span>";
        }else {
            if (treeNode.isAdd == '3') {
                if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
                var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                    "' title='新增'></span>"
            }else {
                if (treeNode.hasChildren == '0'){
                    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
                    var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                        "' title='新增'></span>"
                }
            }
        }
        sObj.append(addStr);
        var addBtn = $("#addBtn_" + treeNode.tId);
        var editBtn = $("#editBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);
        //新增
        if (addBtn) addBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
                    layer.open({
                    type: 2
                    , title: '新增【' + treeNode.name + "】子节点信息"
                    , content: $WEB_ROOT_PATH + '/button/buttonAdd'
                    , maxmin: true
                    , area: ['900px', '400px']
                    , btn: ['确定', '取消']
                    , success: function (layero, index) {
                        var iframeWindow = window['layui-layer-iframe' + index];
                        //向此iframe层方法 传递参数
                        //iframeWindow.child(JSON.stringify(treeNode));
                    }, yes: function (index, layero) {
                        var iframeWindow = window['layui-layer-iframe' + index]
                            , submitID = 'layuiadmin-btn-useradmin'
                            , submit = layero.find('iframe').contents().find('#' + submitID);

                        //监听提交
                        iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                            var field = data.field; //获取提交的字段
                            //提交 Ajax后台
                            $.ajax({
                                type: "POST",
                                async: false,
                                url: $WEB_ROOT_PATH + '/dhccApi/button/button/save',
                                data: {
                                    "button.parentId": treeNode.id,
                                    "button.buttonName": field["button.buttonName"],
                                    "button.buttonCode": field["button.buttonCode"],
                                    "button.buttonPageName":name,
                                    "button.parentLeaf": level + 1
                                },
                                success: function (data) {
                                    if (data.flag == "1") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('添加成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("buttonTable")
                                    } else {
                                        if (data.flag == "3") {
                                            InitZTree()
                                            layer.msg('' + "新增的按钮已存在" + '', {
                                                icon: 5,
                                                time: 1000
                                            });
                                        } else {
                                            InitZTree()
                                            layer.msg('' + "添加失败" + '', {
                                                icon: 5,
                                                time: 1000
                                            });
                                        }
                                    }
                                    layer.close(index);
                                }
                            });
                        });
                        submit.trigger('click');
                    }
                });
        });
       // 编辑
        if (editBtn) editBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                type: 2
                , title: '编辑【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/button/buttonAdd'
                , maxmin: true
                , area: ['900px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(treeNode));
                }, yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        $.ajax({
                            type: "POST",
                            async: false,
                            url: $WEB_ROOT_PATH + '/dhccApi/button/button/save',
                            data: {
                                "button.id": field["button.id"],
                                "button.parentId": treeNode.parentId,
                                "button.buttonName": field["button.buttonName"],
                                "button.buttonCode": field["button.buttonCode"],
                                "button.buttonPageName":treeNode.roleCode,
                                "button.parentLeaf": level
                            },
                            success: function (data) {
                                if (data.flag == "1") {
                                    zTree.addNodes(treeNode, {
                                        pId: treeNode.id,
                                        name: field["unit.unitName"]
                                    });
                                    InitZTree();//重新加载，不然再次添加会报错
                                    layer.msg('修改成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    table.reload("buttonTable")
                                } else {
                                    if (data.flag == "3") {
                                        InitZTree()
                                        layer.msg('' + "修改的按钮代码已存在" + '', {
                                            icon: 5,
                                            time: 1000
                                        });
                                    } else {
                                        InitZTree()
                                        layer.msg('' + "修改失败" + '', {
                                            icon: 5,
                                            time: 1000
                                        });
                                    }
                                }
                                layer.close(index);
                            }
                        });
                    });
                    submit.trigger('click');
                }
            });
        });
        //删除
        if (deleteBtn) deleteBtn.bind("click", function () {
            //将beroreDelete的值作为一个标志传到后端，如果为0则直接删除，否则需要去找下面的所有的子节点
            var beroreDelete;
            if (treeNode.isParent == true) {
                beroreDelete = "1";
            } else {
                beroreDelete = "0";
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: $WEB_ROOT_PATH + '/dhccApi/button/button/delete',
                        data: {
                            "button.id": treeNode.id
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                if ("1" == treeNode.parentLeaf) {
                                    //  console.log(treeNode.parentLeaf)
                                    //删除第一级时重载table
                                    table.reload('buttonTable');
                                }
                                InitZTree() //重新加载，不然再次添加会报错
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000
                                });
                                table.reload('buttonTable');
                                return true;
                            } else {
                                InitZTree()
                                layer.msg('' + "删除失败" + '', {
                                    icon: 5,
                                    time: 1000
                                });
                                return false;
                            }
                        }
                    });
                    layer.close(index);
                },
                function () {//取消时刷新tree
                    InitZTree()
                    layer.msg('已取消', {
                        icon: 6,
                        time: 1000
                    });
                    return false;
                }
            );
        });
    }

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
        $("#editBtn_" + treeNode.tId).unbind().remove();
        $("#deleteBtn_" + treeNode.tId).unbind().remove();
    }

    function zTreeOnClick(event, treeId, treeNode) {
        addUserData = treeNode;
        loadTable(treeNode);
    }

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('buttonTable', {
            where: field,
            page:{curr:1}
        });
    });


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

})
document.getElementById("lefttab").style.height=document.documentElement.clientHeight-14+"px";
var indexAll;
var tableAll;
var indexAdd;
//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
    tableAll=table;

    //展示树
    $("#diagNameText").html("菜单管理如下：");
    InitZTree();


    function loadTable(data) {
        var url;
        if(data==""){
            url = $WEB_ROOT_PATH + '/dhccApi/menu/menu/list';
        }
        if (data.menuCode == undefined) {
            url = $WEB_ROOT_PATH + '/dhccApi/menu/menu/list';
        } else {
            url=$WEB_ROOT_PATH + '/dhccApi/menu/menu/listTreeTable?menu.id=' + data.id + '&menu.parentLeaf=' + data.parentLeaf + '&menu.parentId="' + data.parentId + '"';
        }

        table.render({
            elem: '#menuTable'
            ,
            url: url
            ,
            height: tableHeight
            ,
            cols: [[
                {type: 'numbers',title: '序号'}
                , {align: 'center', title: '操作', toolbar: '#table-useradmin-webuser', width: 220}
                , {field: 'menuName', align: 'center', title: '菜单名称'}
                , {field: 'menuCode', align: 'center', title: '菜单代码',}
                , {field: 'menuUrl', align: 'center', title: 'URL',}
            ]]
            ,
            page: true
        });
    }

    /*function initTable() {
        table.render({
            elem: '#menuTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/list'
            , height: 435
            , where: {ilegalChild: '1'}
            , cols: [[
                {type: 'numbers', title: '序号'}
                , {field: 'menuName', align: 'center', title: '菜单名称'}
                , {field: 'menuCode', align: 'center', title: '菜单代码',}
                , {field: 'menuUrl', align: 'center', title: 'URL',}
            ]]
            , page: true
        });
    }*/

    loadTable("");
    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('menuTable', {
            where: field
            ,page: { curr: 1}
        });
    });

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
            /*edit: {
                enable: true,
                removeTitle: '删除',
                showRenameBtn: false
            },*/
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
            url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/listAllTree',//请求的路径
            /*        data:{
                    "menu.menuName":"权限管理系统"
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
        // t.expandAll(false);
    }

    function zTreeOnClick(event, treeId, treeNode) {
        loadTable(treeNode);
    }

    function addHoverDom(treeId, treeNode) {
        if (treeNode.name == "全部菜单") {
            var level = treeNode.level;
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                "' title='新增'></span>";
        } else {
            var level = treeNode.level;
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                "' title='新增'></span>" +
                "<span class='layui-icon layui-icon-edit' style='font-size: 25px; color: #1E9FFF' id='editBtn_" + treeNode.tId +
                "' title='修改'></span>" +
                "<span class='layui-icon layui-icon-delete' style='font-size: 25px; color:red' id='deleteBtn_" + treeNode.tId +
                "' title='删除'></span>";
        }
        sObj.after(addStr);
        var objWidth=sObj.width()+110;
        $("#" + treeNode.tId).css('width',objWidth+'px');
        var addBtn = $("#addBtn_" + treeNode.tId);
        var editBtn = $("#editBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);
        //点击事件，添加功能
        if (addBtn) addBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                type: 2
                , title: '新增【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/menu/menuAdd'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    indexAdd=index;
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.childAdd(JSON.stringify(treeNode));

                }, yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    /*iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        $.ajax({
                            type: "POST",
                            async: false,
                            url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/save',
                            data: {
                                "menu.parentId": treeNode.id,
                                "menu.menuName": field["menu.menuName"],
                                "menu.menuCode": field["menu.menuCode"],
                                "menu.parentLeaf": level + 1,
                                "menu.menuUrl": field["menu.menuUrl"]
                            },
                            success: function (data) {
                               // var inFlag = data.inFlag;
                               // alert(inFlag)
                                if (data== "success") {
                                    zTree.addNodes(treeNode, {
                                        pId: treeNode.id,
                                        name: field["menu.menuName"]
                                    });
                                    InitZTree();//重新加载，不然再次添加会报错
                                    layer.msg('添加成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    table.reload("menuTable")
                                } else if(data== "exist") {
                                        InitZTree()
                                        layer.msg('' + "新增的菜单已存在" + '', {
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
                                layer.close(index);
                            }
                        });
                    });*/
                    submit.trigger('click');
                    InitZTree();
                }
            });
        });
        //点击事件，编辑功能
        if (editBtn) editBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                type: 2
                , title: '编辑【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/menu/menuAdd2'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    indexAll=index;
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.childAdd(JSON.stringify(treeNode));
                }, yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin1'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    /*                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                                            var field = data.field; //获取提交的字段
                                            //提交 Ajax后台
                                            $.ajax({
                                                type: "POST",
                                                async: false,
                                                url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/save',
                                                data: {
                                                    "menu.id": field["menu.id"],
                                                    "menu.menuName": field["menu.menuName"],
                                                    "menu.menuCode": field["menu.menuCode"],
                                                    "menu.parentLeaf": treeNode.parentLeaf,
                                                    "menu.parentId": treeNode.parentId,
                                                    "menu.menuUrl": field["menu.menuUrl"]
                                                },
                                                success: function (data) {
                                                    if (data.inFlag == "success") {
                                                        zTree.addNodes(treeNode, {
                                                            pId: field["menu.id"],
                                                            name: field["menu.menuName"]
                                                        });
                                                        InitZTree() //重新加载，不然再次添加会报错
                                                        //修改第一级成功重载table
                                                        table.reload("menuTable");

                                                        layer.msg('修改成功', {
                                                            icon: 1,
                                                            time: 1000
                                                        });
                                                    } else {
                                                        if (data == "exist") {
                                                            InitZTree()
                                                            //table.reload('diseasesFirstClassTable');
                                                            layer.msg('' + "已存在该菜单名称，请重新编辑" + '', {
                                                                icon: 5,
                                                                time: 1000
                                                            });
                                                        } else {
                                                            InitZTree()
                                                            //table.reload('diseasesFirstClassTable');
                                                            layer.msg('' + "修改失败" + '', {
                                                                icon: 5,
                                                                time: 1000
                                                            });
                                                        }
                                                    }
                                                    layer.close(index);
                                                }
                                            });
                                        });*/
                    submit.trigger('click');
                    InitZTree();
                }
            });
        });
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
                        url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/delete',
                        data: {
                            "menu.id": treeNode.id,
                            "menu.pId": treeNode.pId,
                            "menu.menuCode": beroreDelete,
                            "menu.parentLeaf": treeNode.parentLeaf,
                            "menu.onclickBef": treeNode.onclickBef,
                            "menu.onclickAft": treeNode.onclickAft
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                if ("1" == treeNode.parentLeaf) {
                                    //删除第一级时重载table
                                    table.reload('menuTable');
                                }
                                InitZTree() //重新加载，不然再次添加会报错
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000
                                });
                                table.reload('menuTable');
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

    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
        $("#editBtn_" + treeNode.tId).unbind().remove();
        $("#deleteBtn_" + treeNode.tId).unbind().remove();


    }


    //添加事件
    var active = {
        add: function () {
            //添加菜单信息
            layer.open({
                type: 2
                , title: '菜单信息'
                , content: $WEB_ROOT_PATH + '/menu/menuAdd'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        var url = $WEB_ROOT_PATH + "/dhccApi/menu/menu/save";
                        $.post(url, field, function (result) {
                            if (result.operateSuccess) {
                                layer.msg('新增成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('menuTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            } else {
                                layer.msg('新增失败!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('menuTable'); //数据刷新
                            }
                            //关闭弹层
                        });
                    });
                    submit.trigger('click');
                }
            });
        }
    };

    //监听行点击
    table.on('tool(menuTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            //修改
            layer.open({
                type: 2
                , title: '编辑【' + data.menuName + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/menu/menuAdd2'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    indexAll=index;
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));
                }, yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin1'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url=$WEB_ROOT_PATH+"/dhccApi/menu/menu/save";
                        $.post(url,field,function(result){
                            layer.msg('修改成功!');
                            //后台成功后，静态更新表格中的数据
                            table.reload('menuTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                    });
                    submit.trigger('click');
                    InitZTree();
                }
            });

        } /*else if (obj.event === 'delete') {
            //删除
            var id = data.id;
            layer.confirm('是否确定删除', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/menu/menu/delete";
                $.post(url, {"menu.id": id}, function (result) {
                    table.reload('menuTable');
                    layer.msg('已删除');
                });
            });
        }*/
    });

    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});

function layeredit(log){
    if (log == 1) {
        tableAll.reload("menuTable");
        layer.msg('修改成功', {
            icon: 1,
            time: 1000
        });
        layer.close(indexAll);
    }else if (log == 2) {
        layer.msg('' + "名称已存在，请修改" + '', {
            icon: 5,
            time: 1000
        });
    } else {
        layer.msg('' + "修改失败" + '', {
            icon: 5,
            time: 1000
        });
    }

}

function layeradd(log){
    if (log== 1) {
        tableAll.reload("menuTable");
        layer.msg('新增成功', {
            icon: 1,
            time: 1000
        });
        layer.close(indexAdd);
    } else if(log== 2) {
        layer.msg('' + "新增的菜单已存在" + '', {
            icon: 5,
            time: 1000
        });
    } else {
        layer.msg('' + "新增失败" + '', {
            icon: 5,
            time: 1000
        });
    }
}
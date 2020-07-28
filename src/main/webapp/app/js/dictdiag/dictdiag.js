//初始化	
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
    //左边列表
    table.render({
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                id = res.data[0].id;
                parentIndex = res.data[0].parentIndex;
                diagName = res.data[0].diagName
                $("#diagNameText").html(diagName + "，扩展如下：");
                InitZTree();
                //对树进行相应操作
                //移除鼠标隐藏按钮
            },
            elem: '#diseasesFirstClassTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/dictdiag/dictdiag/list'
            , height: 415
            , cellMinWidth: 80
            , cols: [[
                {type: 'numbers', width: 40, title: '序号'}
                , {field: 'id', width: 40, title: '疾病编码', sort: true, hide: true}
                , {title: '操作', toolbar: '#table-useradmin-webuser', width: 150, align: 'center'}
                , {field: 'diagCode', align: 'center', width: 90, title: '疾病编码'}
                , {field: 'diagName', align: 'center', title: '疾病名称'}
            ]]
            , page: true
        },
    );


    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('diseasesFirstClassTable', {
            where: field
        });
    });

    var id = null;
    var diagName = null;

    function InitZTree() {
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                selectedMulti: false,
                addHoverDom: addHoverDom, //显示按钮
                removeHoverDom: removeHoverDom, //隐藏按钮
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
            }
        };
        $.ajax({
            async: false, //是否异步
            cache: false, //是否使用缓存
            type: 'POST', //请求方式：post
            url: $WEB_ROOT_PATH + '/dhccApi/dictdiag/dictdiag/ztreeDiag',//请求的路径
            data: {
                "dictdiag.id": id,
                "dictdiag.parentIndex": parentIndex
            },
            success: function (data) {
                treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
            }
        });
        var t = $("#tree-sorts");
        t.html();
        t = $.fn.zTree.init(t, setting, treeNodes);
        t.expandAll(true);
    }

    function addHoverDom(treeId, treeNode) {
        var level = treeNode.level;
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
            "' title='新增'></span>" +
            "<span class='layui-icon layui-icon-edit' style='font-size: 25px; color: #1E9FFF' id='editBtn_" + treeNode.tId +
            "' title='修改'></span>" +
            "<span class='layui-icon layui-icon-delete' style='font-size: 25px; color:red' id='deleteBtn_" + treeNode.tId +
            "' title='删除'></span>"
        ;
        sObj.after(addStr);
        var addBtn = $("#addBtn_" + treeNode.tId);
        var editBtn = $("#editBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);

        if (addBtn) addBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                type: 2
                , title: '新增【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/dictdiag/dictdiagAdd'
                , maxmin: true
                , area: ['500px', '400px']
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
                            url: $WEB_ROOT_PATH + '/dhccApi/dictdiag/dictdiag/saveDiag',
                            data: {
                                "dictdiag.parentId": treeNode.id,
                                "dictdiag.diagName": field["dictdiag.diagName"],
                                "dictdiag.diagDesc": field["dictdiag.diagDesc"],
                                "dictdiag.parentLeaf": level + 2,
                                "dictdiag.diagCode": field["dictdiag.diagCode"],
                                "dictdiag.parentIndex": treeNode.parentIndex
                            },
                            success: function (data) {
                                if (data == "success") {
                                    zTree.addNodes(treeNode, {
                                        pId: treeNode.id,
                                        name: field["dictdiag.diagName"]
                                    });
                                    InitZTree();//重新加载，不然再次添加会报错
                                    layer.msg('添加成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                } else {
                                    if (data == "exist") {
                                        InitZTree()
                                        layer.msg('' + "新增的疾病已存在" + '', {
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
        //点击事件，编辑功能
        if (editBtn) editBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                type: 2
                , title: '编辑【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/dictdiag/dictdiagAdd'
                , maxmin: true
                , area: ['500px', '400px']
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
                            url: $WEB_ROOT_PATH + '/dhccApi/dictdiag/dictdiag/updateDiag',
                            data: {
                                "dictdiag.id": field["dictdiag.id"],
                                "dictdiag.diagName": field["dictdiag.diagName"],
                                "dictdiag.diagDesc": field["dictdiag.diagDesc"],
                                //"dictdiag.parentLeaf":level+2,
                                "dictdiag.diagCode": field["dictdiag.diagCode"],
                                "dictdiag.parentIndex": treeNode.parentIndex
                            },
                            success: function (data) {
                                if (data == "success") {
                                    zTree.addNodes(treeNode, {
                                        pId: field["dictdiag.id"],
                                        name: field["dictdiag.diagName"]
                                    });
                                    InitZTree() //重新加载，不然再次添加会报错

                                    if ("1"==treeNode.parentLeaf) {
                                        //修改第一级成功重载table
                                        table.reload('diseasesFirstClassTable');
                                    }
                                    layer.msg('修改成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                } else {
                                    if (data == "exist") {
                                        InitZTree()
                                        //table.reload('diseasesFirstClassTable');
                                        layer.msg('' + "已存在该疾病名称，请重新编辑" + '', {
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
                    });
                    submit.trigger('click');
                }
            });
        });

        if (deleteBtn) deleteBtn.bind("click", function () {
            console.log(treeNode)
            if (treeNode.isParent == true) {
                layer.msg('请首先删除子节点', {
                    icon: 5,
                    time: 1000
                });
                return false;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: $WEB_ROOT_PATH + '/dhccApi/dictdiag/dictdiag/deleteDiag',
                        data: {
                            "dictdiag.id": treeNode.id,
                            "dictdiag.pId": treeNode.pId
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                if ("1" == treeNode.parentLeaf) {
                                    console.log(treeNode.parentLeaf)
                                    //删除第一级时重载table
                                    table.reload('diseasesFirstClassTable');
                                }
                                InitZTree() //重新加载，不然再次添加会报错
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000
                                });
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
            //添加疾病信息
            layer.open({
                type: 2
                , title: '添加疾病'
                , content: $WEB_ROOT_PATH + '/dictdiag/dictdiagAdd'
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
                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/dictdiag/dictdiag/saveFirst";
                        $.post(url, field, function (result) {
                            var inFlag = result.inFlag;
                            if (inFlag == 1) {
                                layer.msg('该疾病名称已存在!');
                                return false;
                            } else {
                                layer.msg('新增成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('diseasesFirstClassTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            }
                            //关闭弹层
                        });


                    });
                    submit.trigger('click');
                }
            });
        }
    };

    //监听行单击事件（单击事件为：rowDouble）
    table.on('row(diseasesFirstClassTable)', function (obj) {
        var data = obj.data;
        id = data["id"];
        parentIndex = data["parentIndex"]
        diagName = data["diagName"];
        $("#diagNameText").html(diagName + "，扩展如下：");
        //右侧tree
        InitZTree();


        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });

    //监听left点击
    table.on('tool(diseasesFirstClassTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'xiugai') {
            //修改
            layer.open({
                type: 2
                , title: '编辑疾病信息'
                , content: $WEB_ROOT_PATH + '/dictdiag/dictdiagAdd'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));
                }

                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/dictdiag/dictdiag/saveFirst";
                        $.post(url, field, function (result) {
                            layer.msg('编辑成功!');
                            //后台成功后，静态更新表格中的数据
                            table.reload('diseasesFirstClassTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                    });
                    submit.trigger('click');
                }
            });

        } else if (obj.event === 'shanchu') {
            //删除
            var id = data.id;
            layer.confirm('是否确定删除', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/dictdiag/dictdiag/delete";
                $.post(url, {"dictdiag.id": id}, function (result) {
                    table.reload('diseasesFirstClassTable');
                    layer.msg('已删除');
                });
            });
        } else if (obj.event === 'detail') {
            var diagName = data.diagName
            layer.open({
                type: 2
                ,
                title: '疾病:<span style="color: red;">' + diagName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;该疾病扩展如下'
                ,
                content: $WEB_ROOT_PATH + '/dictdiag/dictdiagSecond'
                ,
                maxmin: true
                ,
                area: ['800px', '500px']
                ,
                success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));
                }
            });
        }
    });


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
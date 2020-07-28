//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    function loadTable(data) {
        if (data.name == "数据权限") {
            initTable();
        } else {
            table.render({
                elem: '#dataAuthorityTable',
                url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/listTreeTable?dataAuthority.id=' + data.id + '&dataAuthority.parentLeaf=' + data.parentLeaf + '&dataAuthority.parentId="' + data.parentId + '"'
                ,
                height: 415
                ,
                where: {ilegalChild: '1'}
                ,
                cols: [[
                    {type: 'numbers', width: 80, title: '序号'}
                    , {field: 'cityName', align: 'center', title: '权限名称'}
                    , {field: 'cityCode', align: 'center', title: '权限代码'}
                    , {field: 'orgName', align: 'center', title: '医院名称'}
                    , {field: 'orgCode', align: 'center', title: '医院代码'}
                ]]
                ,
                page: true
            });
        }
    }

    function initTable() {
        table.render({
            elem: '#dataAuthorityTable'
          //  , url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/list'
            , url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/findCity'
            , height: 415
            , where: {ilegalChild: '1'}
            , cols: [[
                {type: 'numbers', width: 80, title: '序号'}
                , {field: 'cityName', align: 'center', title: '权限名称'}
                , {field: 'cityCode', align: 'center', title: '权限代码'}
                , {field: 'orgName', align: 'center', title: '医院名称'}
                , {field: 'orgCode', align: 'center', title: '医院代码'}
            ]]
            , page: true
        });
    }

    initTable();
    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('dataAuthorityTable', {
            where: field
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
            url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/listAllTree',//请求的路径
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
        t.expandAll(false);
    }

    function zTreeOnClick(event, treeId, treeNode) {
        loadTable(treeNode);
    }

    function addHoverDom(treeId, treeNode) {
       // console.log(treeNode)
        if (treeNode.level == 1) {
            var level = treeNode.level;
            var sObj = $("#" + treeNode.tId + "_span");
           // console.log("sObj")
         //   console.log(sObj)
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                "' title='新增'></span>";
        } else if (treeNode.level == 0) {
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
        var addBtn = $("#addBtn_" + treeNode.tId);
        var editBtn = $("#editBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);

        if (addBtn) addBtn.bind("click", function () {
           // console.log("add")
            //console.log(arguments)
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                    type: 2
                , title: '新增【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/dataauthority/dataauthorityAdd'
                , maxmin: true
                , area: ['450px', '550px']
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
                       console.log("提交字段")
                        console.log(field)
                        //提交 Ajax后台
                        $.ajax({
                            type: "POST",
                            async: false,
                            url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/save',
                            data: {
                                "dataAuthority.parentId": treeNode.id,
                                "dataAuthority.cityCode": field["dataAuthority.cityCode"],
                                "dataAuthority.orgCode": field["dataAuthority.orgCode"],
                                "dataAuthority.parentLeaf": level + 1
                            },
                            success: function (data) {
                               // console.log(data);
                                var inFlag = data.inFlag;
                                if (inFlag == 1) {
                                    zTree.addNodes(treeNode, {
                                        pId: treeNode.id,
                                        name: field["menu.menuName"]
                                    });
                                    InitZTree();//重新加载，不然再次添加会报错
                                    layer.msg('添加成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    table.reload("dataAuthorityTable")
                                } else {
                                    if (inFlag == 2) {
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
                , content: $WEB_ROOT_PATH + '/dataauthority/dataauthorityAdd'
                , maxmin: true
                , area: ['700px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    console.log("树节点")
                    console.log(treeNode)
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
                            url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/save',
                            data: {
                                "menu.id": treeNode.id,
                                "dataAuthority.cityCode": field["dataAuthority.cityCode"],
                                "dataAuthority.orgCode": field["dataAuthority.orgCode"],
                            },
                            success: function (data) {
                                if (data == "success") {
                                    zTree.addNodes(treeNode, {
                                        pId: field["menu.id"],
                                        name: field["menu.menuName"]
                                    });
                                    InitZTree() //重新加载，不然再次添加会报错
                                    //修改第一级成功重载table
                                    table.reload("dataAuthorityTable");

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
                    });
                    submit.trigger('click');
                }
            });
        });

        if (deleteBtn) deleteBtn.bind("click", function () {
           // console.log(treeNode)
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
                        url: $WEB_ROOT_PATH + '/dhccApi/dataauthority/dataauthority/delete',
                        data: {
                            "dataAuthority.id": treeNode.id
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                if ("1" == treeNode.parentLeaf) {
                                    //删除第一级时重载table
                                }
                                InitZTree() //重新加载，不然再次添加会报错
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000
                                });
                                table.reload('dataAuthorityTable');
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

    //展示树
    InitZTree();


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});

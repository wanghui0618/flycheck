document.getElementById("lefttab").style.height=document.documentElement.clientHeight-13+"px";
var menuString="菜单授权成功";
var dataStrings='数据授权成功';
var buttonStrings="按钮授权成功";
//初始化
var roleId;
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    //展示树
    $("#diagNameText").html("角色管理如下：");
    InitZTree();

    function loadTable(data) {
    	var url;
    	if(data==""){
    		url = $WEB_ROOT_PATH + '/dhccApi/role/role/list';
    	}
        if (data.roleId == undefined) {
        	url = $WEB_ROOT_PATH + '/dhccApi/role/role/list';
        } else {
        	url=  $WEB_ROOT_PATH + '/dhccApi/role/role/listTreeTable?role.id=' + data.id + '&role.parentLeaf=' + data.parentLeaf + '&role.parentId="' + data.parentId + '"';
        }
        table.render({
            elem: '#roleTable'
            ,url:url
            ,cellMinWidth: 80
            ,height: tableHeight
            ,cols: [[
                {type: 'numbers',  title: '序号'}
                 , {title: '操作', toolbar: '#table-useradmin-webuser', align: 'center',width:100}
                , {field: 'roleName', align: 'center', title: '角色名称'}
                , {field: 'roleCode', align: 'center', title: '角色代码',}
            ]]
            ,
            page: true
        });
    }


    loadTable("");
    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('roleTable', {
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
            url: $WEB_ROOT_PATH + '/dhccApi/role/role/listAllTree',//请求的路径
            /* data: {
                 "dictdiag.id": id,
                 "dictdiag.parentIndex": parentIndex
             },*/
            success: function (data) {
                treeNodes = JSON.parse(data); //把后台封装好的标准的Json格式赋给treeNodes
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

    function zTreeOnClick(event, treeId, treeNode) {
        loadTable(treeNode);
    }

    function addHoverDom(treeId, treeNode) {
        if (treeNode.name == "全部角色") {
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
        sObj.append(addStr);
        var objWidth=sObj.width()+25;
        $("#" + treeNode.tId).css('width',objWidth+'px');
        var addBtn = $("#addBtn_" + treeNode.tId);
        var editBtn = $("#editBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);

        if (addBtn) addBtn.bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            layer.open({
                type: 2
                , title: '新增【' + treeNode.name + "】子节点信息"
                , content: $WEB_ROOT_PATH + '/role/roleAdd'
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
                            url: $WEB_ROOT_PATH + '/dhccApi/role/role/save',
                            data: {
                                "role.parentId": treeNode.id,
                                "role.roleName": field["role.roleName"],
                                "role.roleCode": field["role.roleCode"],
                                "role.parentLeaf": level + 1
                            },
                            success: function (data) {
                                if (data == "success") {
                                    zTree.addNodes(treeNode, {
                                        pId: treeNode.id,
                                        name: field["role.roleName"]
                                    });
                                    InitZTree();//重新加载，不然再次添加会报错
                                    layer.msg('添加成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    table.reload("roleTable")
                                } else {
                                    if (data == "exist") {
                                        InitZTree()
                                        layer.msg('' + "新增的角色已存在" + '', {
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
                , content: $WEB_ROOT_PATH + '/role/roleAdd'
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
                            url: $WEB_ROOT_PATH + '/dhccApi/role/role/save',
                            data: {
                                "role.id": field["role.id"],
                                "role.roleName": field["role.roleName"],
                                "role.roleCode": field["role.roleCode"],
                                "role.parentLeaf": treeNode.parentLeaf,
                                "role.parentId": treeNode.parentId
                            },
                            success: function (data) {
                                if (data == "success") {
                                    zTree.addNodes(treeNode, {
                                        pId: field["role.id"],
                                        name: field["role.roleName"]
                                    });
                                    InitZTree() //重新加载，不然再次添加会报错
                                    //修改第一级成功重载table
                                    table.reload("roleTable");

                                    layer.msg('修改成功', {
                                        icon: 1,
                                        time: 1000
                                    });
                                } else {
                                    if (data == "exist") {
                                        InitZTree()
                                        //table.reload('diseasesFirstClassTable');
                                        layer.msg('' + "已存在该角色名称，请重新编辑" + '', {
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
                        url: $WEB_ROOT_PATH + '/dhccApi/role/role/delete',
                        data: {
                            "role.id": treeNode.id,
                            "role.roleCode": beroreDelete,
                            "role.parentLeaf":treeNode.parentLeaf
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                if ("1" == treeNode.parentLeaf) {
                                    //删除第一级时重载table
                                    table.reload('roleTable');
                                }
                                InitZTree() //重新加载，不然再次添加会报错
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000
                                });
                                table.reload('roleTable');
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
            //添加角色信息
            layer.open({
                type: 2
                , title: '角色信息'
                , content: $WEB_ROOT_PATH + '/role/roleAdd'
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
                        var url = $WEB_ROOT_PATH + "/dhccApi/role/role/save";
                        $.post(url, field, function (result) {
                            if (result.operateSuccess) {
                                layer.msg('新增成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('roleTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            } else {
                                layer.msg('新增失败!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('roleTable'); //数据刷新
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
    table.on('tool(roleTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'update') {
            //修改
            layer.open({
                type: 2
                , title: '编辑角色信息'
                , content: $WEB_ROOT_PATH + '/role/roleAdd'
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
                        var url = $WEB_ROOT_PATH + "/dhccApi/role/role/save";
                        $.post(url, field, function (result) {
                            layer.msg('编辑成功!');
                            //后台成功后，静态更新表格中的数据
                            table.reload('roleTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                    });
                    submit.trigger('click');
                }
            });

        } else if (obj.event === 'delete') {
            //删除
            var id = data.id;
            layer.confirm('是否确定删除', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/role/role/delete";
                $.post(url, {"role.id": id}, function (result) {
                    table.reload('roleTable');
                    layer.msg('已删除');
                });
            });
        }
        else  if (obj.event === 'autho') {
            roleId = data.id;
            layer.open({
                type: 2
                , title: '角色授权'
                , content: $WEB_ROOT_PATH + '/role/roleAutho'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    //iframeWindow.child(JSON.stringify(data));

                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    var frameId = $(layero).find("iframe").attr('id');//父页面获取子页面的iframe
                    var id = $(window.frames[frameId].document).find("#nodes").val();
                    var beforeMenus = $(window.frames[frameId].document).find("#beforeMenus").val();
                    var dataId = $(window.frames[frameId].document).find("#datas").val();
                    var dataIds = $(window.frames[frameId].document).find("#beforeDatas").val();
                    var buttonId=$(window.frames[frameId].document).find("#button").val();
                    var beforeButton=$(window.frames[frameId].document).find("#beforeButton").val();
                    var arr = JSON.parse(id);
                    var menuArrs=JSON.parse(beforeMenus);
                    var dataArr = JSON.parse(dataId);
                    var dataArrs = JSON.parse(dataIds);
                    var buttonArr=JSON.parse(buttonId)
                    var buttonArrs=JSON.parse(beforeButton);
                    //菜单权限是否发生变化
                    var menuResult=equar(arr,menuArrs);
                    //比较数据授权是否发生变化，没有变化则不用调用后端接口
                    var result=equar(dataArr,dataArrs)
                    var buttonResult=equar(buttonArr,buttonArrs);

                   if (menuResult==false) {
                       var nodes = [];
                       jQuery.each(arr, function (i, val) {
                           var id = val["id"]
                           nodes.push(id)
                       })
                       if (nodes.length == 0) {
                           layer.msg("请勾选菜单授权", {
                               icon: 1,
                               time: 1000,
                           })
                           return;
                       }
                       var nodeString = JSON.stringify(nodes);
                       $.ajax({
                           type: "POST",
                           async: false,
                           url: $WEB_ROOT_PATH + '/dhccApi/role/role/saveAutho',
                           data: {
                               "role.id": nodeString,
                               //将选中的一行用户的id赋给name这里的赋值只为传递无实际意义
                               "role.roleName": data.id,
                           },
                           success: function (data) {
                               if (data.operateSuccess == true) {
                                   menuString = '菜单授权成功'
                               } else {
                                   menuString = "菜单授权失败"
                               }
                           },
                           error: function (d) {
                               menuString = "菜单授权失败"
                           }
                       });
                   }
                   if(buttonResult==false) {
                       var buttons = [];
                       jQuery.each(buttonArr, function (i, val) {
                           var id = val["id"]
                           buttons.push(id)
                       })

                       if (buttons.length == 0) {
                           layer.msg("请勾选按钮授权", {
                               icon: 1,
                               time: 1000,
                           })
                           return;
                       }
                       var buttonString = JSON.stringify(buttons);
                       $.ajax({
                           type: "POST",
                           async: false,
                           url: $WEB_ROOT_PATH + '/dhccApi/role/role/saveRoleButtonAutho',
                           data: {
                               "role.id": buttonString,
                               //将选中的一行用户的id赋给name这里的赋值只为传递无实际意义
                               "role.roleName": data.id,
                           },
                           success: function (data) {
                               if (data.operateSuccess == true) {
                                   buttonStrings = '按钮授权成功'
                               } else {
                                   buttonStrings = "按钮授权失败"
                               }
                           },
                           error: function (d) {
                               buttonStrings = "按钮授权失败"
                           }
                       });
                   }

                    if (result==false){
                        var datas = [];
                        jQuery.each(dataArr, function (i, val) {
                            var objTree={id:val["id"],unitCode:val["unitCode"],parentLeaf:val["parentLeaf"]}
                            datas.push(objTree)
                        })
                        if (datas.length==0) {
                            layer.msg("请勾选数据授权",{
                                icon:1,
                                time:1000,
                            })
                            return;
                        }
                        var dataString = JSON.stringify(datas);
                        $.ajax({
                            type: "POST",
                            async: false,
                            url: $WEB_ROOT_PATH + '/dhccApi/role/role/saveDataAutho',
                            data: {
                                "role.id":dataString,
                                //将选中的一行用户的id赋给name这里的赋值只为传递无实际意义
                                "role.roleName": data.id,
                            },
                            success: function (data) {
                                if (data.operateSuccess == true) {
                                    dataStrings='数据授权成功'
                                }else {
                                    dataStrings='数据授权失败'
                                }
                            }
                        });
                    }

                    layer.msg(menuString+"--"+dataStrings+"--"+buttonStrings, {
                        icon: 1,
                        time: 1000
                    });
                    layer.close(index);
                    submit.trigger('click');
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

function equar(a, b) {
    // 判断数组的长度
    if (a.length !== b.length) {
        return false
    } else {
        var  count=0;
        // 循环遍历数组的值进行比较
        for (var i = 0; i < a.length; i++) {
            for (var j=0;j<b.length;j++){
                if (a[i] ["id"]== b[j]["id"]) {
                    count++;
                }
            }
        }
        console.log(count);
        if (count!==a.length){
            return false;
        }
        return true;
    }
}
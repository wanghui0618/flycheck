document.getElementById("lefttab").style.height=document.documentElement.clientHeight-13+"px";

//初始化
var unitName= "";
var phone= "";
var concat= "";
var addUserData;
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;


    $("#diagNameText").html("组织架构扩展如下：");
    //展示树
    InitZTree();
    loadTable("");

    function loadTable(data) {
    	var url=$WEB_ROOT_PATH + '/dhccApi/unit/unit/list';
    	if(data==""){
    		url=$WEB_ROOT_PATH + '/dhccApi/unit/unit/list';
    	}
        if (data.unitId == undefined) {
        	url=$WEB_ROOT_PATH + '/dhccApi/unit/unit/list';
        } else {
        	url=$WEB_ROOT_PATH + '/dhccApi/unit/unit/listTreeTable?unit.unitId=' + data.unitId + '&unit.id=' + data.id + '&unit.parentLeaf='
            + data.parentLeaf;
        }
        table.render({
            elem: '#unitTable',
            url: url,
            height: tableHeight,
            cols: [[
                {type: 'numbers', title: '序号',align: 'center'}
                , {field: 'id', title: 'ID', hide: true,align: 'center'}
                , {field: 'unitName', title: '组织名称',align: 'center'}
                , {field: 'unitAddress', title: '地址',align: 'center'}
                , {field: 'concat', title: '联系人',align: 'center'}
                , {field: 'phone', title: '联系方式',align: 'center'}
                , {field: 'email', title: '电子邮箱',align: 'center'}
                , {field: 'webUrl', title: '网址',align: 'center'}
                /*, {field: 'addDate', width: 170, title: '添加日期',align: 'center'}*/
            ]]
            ,
            page: true
        });
    }



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
            url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/listAllTree',//请求的路径
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

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
        $("#editBtn_" + treeNode.tId).unbind().remove();
        $("#deleteBtn_" + treeNode.tId).unbind().remove();
    }

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_a");
        var level = treeNode.level;
        
        if (treeNode.isOauth!='1') {
            if (treeNode.name == "全部组织") {
                if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
                var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                    "' title='新增'></span>";
            } else {
                if (treeNode.name != '地市') {
                    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
                    var addStr = "<span class='layui-icon  layui-icon-add-1'  style='font-size: 25px; color:#0A6457' id='addBtn_" + treeNode.tId +
                        "' title='新增'></span>" +
                        "<span class='layui-icon layui-icon-edit' style='font-size: 25px; color: #1E9FFF' id='editBtn_" + treeNode.tId +
                        "' title='修改'></span>" +
                        "<span class='layui-icon layui-icon-delete' style='font-size: 25px; color:red' id='deleteBtn_" + treeNode.tId +
                        "' title='删除'></span>";
                }
            }
        }
        sObj.append(addStr);

        var objWidth=sObj.width()+18;
        $("#" + treeNode.tId).css('width',objWidth+'px');
        var addBtn = $("#addBtn_" + treeNode.tId);
        var editBtn = $("#editBtn_" + treeNode.tId);
        var deleteBtn = $("#deleteBtn_" + treeNode.tId);

        if (addBtn) addBtn.bind("click", function () {
            var isHospital="1";
            var isNotHospital="0";
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            /*if (treeNode.name == "全部组织"||treeNode.name == "系统" ||treeNode.name == "总公司"||
                treeNode.name == "医保局"||treeNode.name == "游客"||treeNode.isHospital=="0")
            {*/
                layer.open({
                    type: 2
                    , title: '新增【' + treeNode.name + "】子节点信息"
                    , content: $WEB_ROOT_PATH + '/unit/unitAdd'
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
                          //  console.log("add")
                            //console.log(field)
                            //提交 Ajax后台
                            $.ajax({
                                type: "POST",
                                async: false,
                                url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/save',
                                data: {
                                    "unit.parentId": treeNode.id,
                                    "unit.unitName": field["unit.unitName"],
                                    "unit.unitId": field["unit.unitId"],
                                    "unit.concat": field["unit.concat"],
                                    "unit.phone": field["unit.phone"],
                                    "unit.email": field["unit.email"],
                                    "unit.webUrl": field["unit.webUrl"],
                                    "unit.unitAddress": field["unit.unitAddress"],
                                    "unit.isUnit": field["unit.isUnit"],
                                    "unit.cityCode":field["unit.cityCode"],
                                    "unit.isHospital":isNotHospital,
                                    "unit.parentLeaf": level + 1
                                },
                                success: function (data) {
                                    if (data == "success") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('添加成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("unitTable")
                                    } else {
                                        if (data == "exist") {
                                            InitZTree()
                                            layer.msg('' + "新增的组织架构已存在" + '', {
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
            /*}else if (treeNode.isUnit=="1"||treeNode.name=="医院"){
                layer.open({
                    type: 2
                    , title: '新增【' + treeNode.name + "】子节点信息"
                    , content: $WEB_ROOT_PATH + '/unit/unitAddnew1'
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
                                url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/save',
                                data: {
                                    "unit.parentId": treeNode.id,
                                    "unit.unitName": field["unit.unitName"],
                                    "unit.unitId": field["unit.unitId"],
                                    "unit.concat": field["unit.concat"],
                                    "unit.phone": field["unit.phone"],
                                    "unit.email": field["unit.email"],
                                    "unit.webUrl": field["unit.webUrl"],
                                    "unit.unitAddress": field["unit.unitAddress"],
                                    "unit.isUnit": field["unit.isUnit"],
                                    "unit.cityCode":field["unit.cityCode"],
                                    "unit.isHospital":isHospital,
                                    "unit.parentLeaf": level + 1
                                },
                                success: function (data) {
                                    if (data == "success") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('添加成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("unitTable")
                                    } else {
                                        if (data == "exist") {
                                            InitZTree()
                                            layer.msg('' + "新增的组织架构已存在" + '', {
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
                })
            }else {
                layer.open({
                    type: 2
                    , title: '新增【' + treeNode.name + "】子节点信息"
                   // , content: $WEB_ROOT_PATH + '/unit/unitAdd1'
                    , content: $WEB_ROOT_PATH + '/unit/unitAddnew'
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
                           // console.log("add")
                           // console.log(field)
                            //提交 Ajax后台
                            $.ajax({
                                type: "POST",
                                async: false,
                                url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/save',
                                data: {
                                    "unit.parentId": treeNode.id,
                                    "unit.unitName": field["unit.unitName"],
                                    "unit.unitId": field["unit.unitId"],
                                    "unit.concat": field["unit.concat"],
                                    "unit.phone": field["unit.phone"],
                                    "unit.email": field["unit.email"],
                                    "unit.webUrl": field["unit.webUrl"],
                                    "unit.unitAddress": field["unit.unitAddress"],
                                    "unit.isUnit": field["unit.isUnit"],
                                    "unit.cityCode":field["unit.cityCode"],
                                    "unit.parentLeaf": level + 1,
                                    "unit.isHospital":isHospital,
                                },
                                success: function (data) {
                                    if (data == "success") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('添加成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("unitTable")
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
            }*/

        });
        //点击事件，编辑功能
        if (editBtn) editBtn.bind("click", function () {
            console.log(treeNode.isOauth)
            var zTree = $.fn.zTree.getZTreeObj("tree-sorts");
            // if (treeNode.name == "全部组织"||treeNode.name == "系统" ||treeNode.name == "总公司"||treeNode.name == "医保局"||treeNode.isOauth==undefined||treeNode.isOauth=='')
            // {
                layer.open({
                    type: 2
                    , title: '编辑【' + treeNode.name + "】子节点信息"
                    , content: $WEB_ROOT_PATH + '/unit/unitAdd'
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
                            //  console.log("add")
                            //console.log(field)
                            //提交 Ajax后台
                            $.ajax({
                                type: "POST",
                                async: false,
                                url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/save',
                                data: {
                                    "unit.id": treeNode.id,
                                    "unit.parentId": treeNode.parentId,
                                    "unit.unitName": field["unit.unitName"],
                                    "unit.unitId": field["unit.unitId"],
                                    "unit.concat": field["unit.concat"],
                                    "unit.phone": field["unit.phone"],
                                    "unit.email": field["unit.email"],
                                    "unit.webUrl": field["unit.webUrl"],
                                    "unit.unitAddress": field["unit.unitAddress"],
                                    "unit.isUnit": field["unit.isUnit"],
                                    "unit.cityCode":field["unit.cityCode"],
                                    "unit.isHospital":treeNode.isHospital,
                                    "unit.parentLeaf": treeNode.level
                                },
                                success: function (data) {
                                    if (data == "success") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('编辑成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("unitTable")
                                    } else {
                                        if (data == "exist") {
                                            InitZTree()
                                            layer.msg('' + "修改的组织架构已存在" + '', {
                                                icon: 5,
                                                time: 1000
                                            });
                                        } else {
                                            InitZTree()
                                            layer.msg('' + "编辑失败" + '', {
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
          //  }
            /*else if (treeNode.isUnit=="1"||treeNode.name=="医院"){
                layer.open({
                    type: 2
                    , title: '编辑【' + treeNode.name + "】子节点信息"
                    , content: $WEB_ROOT_PATH + '/unit/unitAddnew1'
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
                                url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/save',
                                data: {
                                    "unit.id": treeNode.id,
                                    "unit.parentId": treeNode.parentId,
                                    "unit.unitName": field["unit.unitName"],
                                    "unit.unitId": field["unit.unitId"],
                                    "unit.concat": field["unit.concat"],
                                    "unit.phone": field["unit.phone"],
                                    "unit.email": field["unit.email"],
                                    "unit.webUrl": field["unit.webUrl"],
                                    "unit.unitAddress": field["unit.unitAddress"],
                                    "unit.isUnit": field["unit.isUnit"],
                                    "unit.cityCode":field["unit.cityCode"],
                                    "unit.isHospital":treeNode.isHospital,
                                    "unit.parentLeaf": treeNode.level
                                },
                                success: function (data) {
                                    if (data == "success") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('编辑成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("unitTable")
                                    } else {
                                        if (data == "exist") {
                                            InitZTree()
                                            layer.msg('' + "修改的组织架构已存在" + '', {
                                                icon: 5,
                                                time: 1000
                                            });
                                        } else {
                                            InitZTree()
                                            layer.msg('' + "编辑失败" + '', {
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
                })
            }else {
                layer.open({
                    type: 2
                    , title: '编辑【' + treeNode.name + "】子节点信息"
                    // , content: $WEB_ROOT_PATH + '/unit/unitAdd1'
                    , content: $WEB_ROOT_PATH + '/unit/unitAddnew'
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
                            // console.log("add")
                            // console.log(field)
                            //提交 Ajax后台
                            $.ajax({
                                type: "POST",
                                async: false,
                                url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/save',
                                data: {
                                    "unit.id": treeNode.id,
                                    "unit.parentId": treeNode.parentId,
                                    "unit.unitName": field["unit.unitName"],
                                    "unit.unitId": field["unit.unitId"],
                                    "unit.concat": field["unit.concat"],
                                    "unit.phone": field["unit.phone"],
                                    "unit.email": field["unit.email"],
                                    "unit.webUrl": field["unit.webUrl"],
                                    "unit.unitAddress": field["unit.unitAddress"],
                                    "unit.isUnit": field["unit.isUnit"],
                                    "unit.cityCode":field["unit.cityCode"],
                                    "unit.parentLeaf": treeNode.level,
                                    "unit.isHospital":treeNode.isHospital,
                                },
                                success: function (data) {
                                    if (data == "success") {
                                        zTree.addNodes(treeNode, {
                                            pId: treeNode.id,
                                            name: field["unit.unitName"]
                                        });
                                        InitZTree();//重新加载，不然再次添加会报错
                                        layer.msg('编辑成功', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        table.reload("unitTable")
                                    } else {
                                        if (data == "exist") {
                                            InitZTree()
                                            layer.msg('' + "修改的角色已存在" + '', {
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
            }*/
        });

        if (deleteBtn) deleteBtn.bind("click", function () {
           // console.log("删除")
          //  console.log(treeNode.id)
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
                        url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/delete',
                        data: {
                            "unit.id": treeNode.id,
                            "unit.unitCode": beroreDelete,
                            "unit.parentLeaf": treeNode.parentLeaf
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                if ("1" == treeNode.parentLeaf) {
                                  //  console.log(treeNode.parentLeaf)
                                    //删除第一级时重载table
                                    table.reload('unitTable');
                                }
                                InitZTree() //重新加载，不然再次添加会报错
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 1000
                                });
                                table.reload('unitTable');
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

    function zTreeOnClick(event, treeId, treeNode) {
       unitName=  $("#unitName").val()
       concat=  $("#concat").val()
       phone=  $("#phone").val()
        addUserData = treeNode;
        loadTable(treeNode);
    }

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('unitTable', {
            where: field
            ,page: { curr: 1}
        });
    });


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
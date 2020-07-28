document.getElementById("lefttab").style.height=document.documentElement.clientHeight-13+"px";
document.getElementById("leftPan").style.height=document.documentElement.clientHeight-88+"px";
//初始化
var menuString;
var dataStrings='数据授权成功';
var buttonStrings;
var addUserData;
var userId;
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
    //展示树
    InitZTree();
    InitZTree1();
    function loadTable(data) {
        console.log(data)
        if (data.id == undefined) {
            InitTable(data);
        } else {
            table.render({
                elem: '#userTable1',
                //url: $WEB_ROOT_PATH + '/dhccApi/user/user/listTreeTable?userVo.roleCode=' + data.roleCode,
                url: $WEB_ROOT_PATH + '/dhccApi/user/user/listTreeTable?userVo.unitId=' + data.id,
                height: tableHeight,
                where: {ilegalChild: '1'},
                cols: [[
                    {type: 'numbers', title: '序号',align: 'center'}
                    , {field: 'id', title: 'ID', hide: true,align: 'center'}
                    , {align: 'center', title: '操作', toolbar: '#table-useradmin-webuser', width: 220}
                    , {
                        field: 'left', title: '审核状态', width: 110, templet: function (d) {
                            var codex = d.status;
                            if (codex == null || codex == "" || codex == "1") {
                                codex = "<button class='layui-btn layui-btn-xs'>已审核通过</button>";
                            }
                            if (codex == "0") {
                                return "<a class='layui-btn layui-btn-warm layui-btn-xs' lay-event='auditing'><i class='layui-icon layui-icon-vercode'></i>等待审核</a>"
                            }
                            if (codex == "2") {
                                return "<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='auditing'><i class='layui-icon layui-icon-vercode'></i>审核未通过</a>"
                            }
                            return '<span style="color: green;">' + codex + '</span>'
                        }
                    }
                    , {field: 'name', title: '姓名',align: 'center'}
                    , {field: 'loginName', title: '用户名',align: 'center'}
                    , {field: 'phone', title: '手机号码',align: 'center'}
                    , {field: 'email', title: '邮箱',align: 'center'}
                    , {field: 'unitName', title: '组织名称',align: 'center'}
                    , {field: 'roleName', title: '角色名称',align: 'center'}
                    /*, {field: 'addDate', width: 170, title: '添加日期',align: 'center'}*/
                    /*,{field:'updateDate', title: '修改日期', sort: true}*/
                ]]
                ,
                page: true
            });
        }
    }

    function loadTable1(data) {
        if (data.roleId == undefined) {
            InitTable();
        } else {
            table.render({
                elem: '#userTable1',
                url: $WEB_ROOT_PATH + '/dhccApi/user/user/listTreeTable?userVo.roleId=' + data.roleId,
                height: tableHeight,
                where: {ilegalChild: '1'},
                cols: [[
                    {type: 'numbers', title: '序号',align: 'center'}
                    , {field: 'id', title: 'ID', hide: true,align: 'center'}
                    , {align: 'center', title: '操作', toolbar: '#table-useradmin-webuser', width: 220}
                    , {
                        field: 'left', title: '审核状态', width: 110, templet: function (d) {
                            var codex = d.status;
                            if (codex == null || codex == "" || codex == "1") {
                                codex = "<button class='layui-btn layui-btn-xs'>已审核通过</button>";
                            }
                            if (codex == "0") {
                                return "<a class='layui-btn layui-btn-warm layui-btn-xs' lay-event='auditing'><i class='layui-icon layui-icon-vercode'></i>等待审核</a>"
                            }
                            if (codex == "2") {
                                return "<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='auditing'><i class='layui-icon layui-icon-vercode'></i>审核未通过</a>"
                            }
                            return '<span style="color: green;">' + codex + '</span>'
                        }
                    }
                    , {field: 'name', title: '姓名',align: 'center'}
                    , {field: 'loginName', title: '用户名',align: 'center'}
                    , {field: 'phone', title: '手机号码',align: 'center'}
                    , {field: 'email', title: '邮箱',align: 'center'}
                    , {field: 'unitName', title: '组织名称',align: 'center'}
                    , {field: 'roleName', title: '角色名称',align: 'center'}
                    /*, {field: 'addDate', width: 170, title: '添加日期',align: 'center'}*/
                    /*,{field:'updateDate', title: '修改日期', sort: true}*/
                ]]
                ,
                page: true
            });
        }
    }

    function InitTable(data) {
    	var url;
    	if(data==""){
    		url = $WEB_ROOT_PATH + '/dhccApi/user/user/listUserVo'
    	}
        if (data.id == undefined) {
        	url = $WEB_ROOT_PATH + '/dhccApi/user/user/listUserVo'
        } else {
        	url=  $WEB_ROOT_PATH + '/dhccApi/user/user/listTreeTable?userVo.unitId=' + data.id;
        }
        table.render({
            elem: '#userTable1',
            url: url,
            height: tableHeight,
            where: {ilegalChild: '1'},
            cols: [[
                {type: 'numbers', title: '序号',align: 'center'}
                , {field: 'id', title: 'ID', hide: true,align: 'center'}
                , {align: 'center', title: '操作', toolbar: '#table-useradmin-webuser', width: 180}
                , {
                    field: 'left', title: '审核状态', width: 100, templet: function (d) {
                        var codex = d.status;
                        if (codex == null || codex == "" || codex == "1") {
                            codex = "<button class='layui-btn layui-btn-xs'>已审核通过</button>";
                        }
                        if (codex == "0") {
                            return "<a class='layui-btn layui-btn-warm layui-btn-xs' lay-event='auditing'><i class='layui-icon layui-icon-vercode'></i>等待审核</a>"
                        }
                        if (codex == "2") {
                            return "<a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='auditing'><i class='layui-icon layui-icon-vercode'></i>审核未通过</a>"
                        }
                        return '<span style="color: green;">' + codex + '</span>'
                    }
                }
                , {field: 'loginName', title: '用户名',align: 'center'}
                , {field: 'name', title: '姓名',align: 'center'}
                , {field: 'phone', title: '手机号码',align: 'center'}
                , {field: 'email', title: '邮箱',align: 'center'}
                , {field: 'unitName', title: '组织名称',align: 'center'}
                , {field: 'roleName', title: '角色名称',align: 'center'}
                /*, {field: 'addDate', width: 170, title: '添加日期',align: 'center'}*/
                /*,{field:'updateDate', title: '修改日期', sort: true}*/
            ]]
            ,
            page: true
        });
    }
    
    InitTable("");
    //左边组织架构树展示
    function InitZTree() {
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                selectedMulti: false,
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
                //console.log(data)
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

    //左边角色管理树展示
    function InitZTree1() {
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                selectedMulti: false,
            },
            callback: {
                // 单击事件
                onClick: zTreeOnClick1
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
            url: $WEB_ROOT_PATH + '/dhccApi/role/role/listAllTree',//请求的路径
            /* data: {
                 "dictdiag.id": id,
                 "dictdiag.parentIndex": parentIndex
             },*/
            success: function (data) {
                treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
            }
        });
        var t = $("#tree-sorts1");
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
        addUserData = treeNode;
        loadTable(treeNode);
    }

    function zTreeOnClick1(event, treeId, treeNode) {
        loadTable1(treeNode);
    }

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('userTable1', {
            where: field
            ,page: { curr: 1}
        });
    });

    //添加事件
    var active = {
        add: function () {
            //新增方法
            layer.open({
                type: 2
                , title: '添加用户'
                , content: $WEB_ROOT_PATH + '/user/userform'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/user/user/save";
                        $.post(url, field, function (result) {
                            var inFlag = result.inFlag;
                            if (inFlag == 0) {
                                layer.msg('添加成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('userTable1'); //数据刷新
                                layer.close(index); //关闭弹层
                            } else if (inFlag == 1) {
                                layer.msg('该手机号已被注册!');
                                return false;
                            } else if (inFlag == 2) {
                                layer.msg('该邮箱已被注册!');
                                return false;
                            } else if (inFlag == 3) {
                                layer.msg('该用户名已被注册!');
                                return false;
                            }
                        });
                    });
                    submit.trigger('click');
                }
            });
        }
    };

    //监听行点击
    table.on('tool(userTable1)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            //删除
            layer.confirm('是否确定删除用户？', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/user/user/delete";
                $.post(url, {"user.id": data.id}, function (result) {
                    table.reload('userTable1');
                    layer.msg('已删除');
                });
            });
        } else if (obj.event === 'edit') {
            //修改方法
            layer.open({
                type: 2
                , title: '编辑用户信息'
                , content: $WEB_ROOT_PATH + '/user/userformedit'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    var roleCodes = data.roleCode;
                    iframeWindow.child(JSON.stringify(data));

                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/user/user/save";
                        $.post(url, field, function (result) {
                            var inFlag = result.inFlag;
                            if (inFlag == 0) {
                                layer.msg('修改成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('userTable1'); //数据刷新
                                layer.close(index); //关闭弹层
                            } else if (inFlag == 1) {
                                layer.msg('该手机号已被注册!');
                                return false;
                            } else if (inFlag == 2) {
                                layer.msg('该邮箱已被注册!');
                                return false;
                            }
                        });
                    });
                    submit.trigger('click');
                }
            });
        } else if (obj.event === 'auditing') {
            layer.open({
                type: 2
                , title: '用户审核'
                , content: $WEB_ROOT_PATH + '/user/userAuditing'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));
                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {

                        var field = data.field; //获取提交的字段
                        var auditing = data.field.auditing;
                        var remark = field["user.remark"];

                        String.prototype.trim = function () {
                            return this.replace(/(^\s*)|(\s*$)/g, "");
                        };
                        if (remark == null || "" == $.trim(remark) || "null" == $.trim(remark)) {
                            Layer.alert("审核意见不能为空！");
                            return false;
                        }

                        if (auditing == "pass") {
                            //提交 Ajax后台
                            var url = $WEB_ROOT_PATH + "/dhccApi/user/user/auditing";
                            $.post(url, field, function (result) {
                                var inFlag = result.inFlag;
                                if (inFlag == 0) {
                                    layer.msg('审核通过!');
                                    //后台成功后，静态更新表格中的数据
                                    table.reload('userTable1'); //数据刷新
                                    layer.close(index); //关闭弹层
                                } else if (inFlag == 1) {
                                    layer.msg('网络问题，操作失败!');
                                    return false;
                                }
                            });
                        } else if (auditing = "nopass") {
                            //提交 Ajax后台
                            var url = $WEB_ROOT_PATH + "/dhccApi/user/user/reauditing";
                            $.post(url, field, function (result) {
                                var inFlag = result.inFlag;
                                if (inFlag == 0) {
                                    layer.msg('审核未通过!');
                                    //后台成功后，静态更新表格中的数据
                                    table.reload('userTable1'); //数据刷新
                                    layer.close(index); //关闭弹层
                                } else if (inFlag == 1) {
                                    layer.msg('网络问题，操作失败!');
                                    return false;
                                }
                            });
                        }
                    });
                    submit.trigger('click');
                }
            });
        } else if (obj.event === 'autho') {
            userId = data.id;
            layer.open({
                type: 2
                , title: '用户授权'
                , content: $WEB_ROOT_PATH + '/user/userAutho'
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
                    var dataId = $(window.frames[frameId].document).find("#datas").val();
                    var buttonId=$(window.frames[frameId].document).find("#button").val();
                    var arr = JSON.parse(id);
                    var dataArr = JSON.parse(dataId);
                    var buttonArr=JSON.parse(buttonId);
                    var nodes = [];
                    var datas = [];
                    var buttons=[];
                    jQuery.each(arr, function (i, val) {
                        var id = val["id"]
                        nodes.push(id)
                    })
                    jQuery.each(dataArr, function (i, val) {
                        var id = val["id"]
                        datas.push(id)
                    })

                    jQuery.each(buttonArr, function (i, val) {
                        var id = val["id"]
                        buttons.push(id)
                    })
                    if (nodes.length==0) {
                        layer.msg("请勾选菜单授权",{
                            icon:1,
                            time:1000,
                        })
                        return;
                    } if (datas.length==0) {
                        layer.msg("请勾选数据授权",{
                            icon:1,
                            time:1000,
                        })
                        return;
                    }
                    if (buttons.length==0) {
                        layer.msg("请勾选按钮授权",{
                            icon:1,
                            time:1000,
                        })
                        return;
                    }

                    var nodeString = JSON.stringify(nodes);
                    var dataString = JSON.stringify(datas);
                    var buttonString=JSON.stringify(buttons);
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: $WEB_ROOT_PATH + '/dhccApi/user/user/saveAutho',
                        data: {
                            "user.id": nodeString,
                            //将选中的一行用户的id赋给name这里的赋值只为传递无实际意义
                            "user.name": data.id,
                        },
                        success: function (data) {
                            if (data.operateSuccess == true){
                                menuString='菜单授权成功'
                            }else {
                                menuString="菜单授权失败"
                            }
                        },
                        error: function (d) {
                            menuString="菜单授权失败"
                        }
                    });


                    $.ajax({
                        type: "POST",
                        async: false,
                        url: $WEB_ROOT_PATH + '/dhccApi/user/user/saveButtonAutho',
                        data: {
                            "user.id": buttonString,
                            //将选中的一行用户的id赋给name这里的赋值只为传递无实际意义
                            "user.name": data.id,
                        },
                        success: function (data) {
                            if (data.operateSuccess == true){
                                buttonStrings='按钮授权成功'
                            }else {
                                buttonStrings="按钮授权失败"
                            }
                        },
                        error: function (d) {
                            buttonStrings="按钮授权失败"
                        }
                    });
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: $WEB_ROOT_PATH + '/dhccApi/user/user/saveDataAutho',
                        data: {
                            "user.id":dataString,
                            //将选中的一行用户的id赋给name这里的赋值只为传递无实际意义
                            "user.name": data.id,
                        },
                        success: function (data) {
                            if (data.operateSuccess == true) {
                                dataStrings='数据授权成功'
                            }else {
                                dataStrings='数据授权失败'
                            }
                            layer.msg(menuString+"--"+dataStrings+"--"+buttonStrings, {
                                icon: 1,
                                time: 1000
                            });
                            layer.close(index);

                        }
                    });
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
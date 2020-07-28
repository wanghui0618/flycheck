//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    //左边树展示
    function InitZTree() {
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                selectedMulti: false,
                //   addHoverDom: addHoverDom, //显示按钮
                // removeHoverDom: removeHoverDom, //隐藏按钮
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "ps", "N": "s"}
            },
            callback: {
                // 单击事件
                //onClick: zTreeOnClick,
                onCheck: zTreeOnCheck
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
            url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/roleMenuAuthoList',//请求的路径
            data:{
                "menu.id":window.parent.roleId
            },
            success: function (data) {
                treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
            }
        });
        t = $("#tree-sorts");
        t.html();
        t = $.fn.zTree.init(t, setting, treeNodes);
        $("#beforeMenus").val(JSON.stringify(t.getCheckedNodes(true)));
        t.expandAll(true)
    }

    var t = "";


    function zTreeOnCheck(event, treeId, treeNode) {
        var nodes = t.getCheckedNodes(true);
        $("#nodes").val(JSON.stringify(nodes));
    };

    //展示树
    InitZTree();
    zTreeOnCheck();


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
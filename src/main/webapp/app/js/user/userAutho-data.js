//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;


    console.log(arguments)
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
                chkboxType: {"Y": "s", "N": "s"}
            },
            callback: {
                onClick: onCloik,
                // 单击事件
                onCheck: zTreeOnCheck,

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
            url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/dataAuthoList',//请求的路径
            data:{
                "unit.id":window.parent.userId
            },
            success: function (data) {
                treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
            }
        });
        t = $("#tree-sorts-data");
        t.html();
        t = $.fn.zTree.init(t, setting, treeNodes);
        t.expandAll(true)
    }

    var t = "";
   //点击节点，勾选该节点
    function onCloik(e, treeId, treeNode, clickFlag) {
        t.checkNode(treeNode, !treeNode.checked, true);
        //调用获取节点信息，赋值给datas
        zTreeOnCheck();
    }
    function zTreeOnCheck(event, treeId, treeNode) {
        var nodes = t.getCheckedNodes(true);
        console.log(JSON.stringify(nodes))
        $("#datas").val(JSON.stringify(nodes));
    };

    //展示树
    InitZTree();
    //第一次进入页面调用方法
    zTreeOnCheck();


    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
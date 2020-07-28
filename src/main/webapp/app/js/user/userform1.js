var rolecodeClick="";
//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index','table','form'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table

    form.verify({
        article_desc: function(value){
            $("#unitIdHidden").val(rolecodeClick);
        }
    });

    $.ajax({
        async: false, //是否异步
        cache: false, //是否使用缓存
        type: 'POST', //请求方式：post
        url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/listAllTree',//请求的路径
        success: function (data) {
            orgList2 =JSON.parse(data); //把后台封装好的标准的Json格式赋给treeNodes
            $.fn.zTree.init($("#treeDemo1"), setting2, orgList2);
        }
    });

})
var orgList2 =[];
var setting2 = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId"
        }
    },
    //回调
    callback: {
        onClick: zTreeOnClick1
    },
    view: {
        fontCss: { fontSize: "14px" }
    }
};
//节点点击事件
function zTreeOnClick1(event, treeId, treeNode) {
    console.log(event);
    $('#unitName').val(treeNode.name);
    $('#unitId').val(treeNode.roleCode)
    hideTree();
    unitId = treeNode.unitId;
    //console.log(roleCode);
    rolecodeClick = unitId;
};


function hideTree() {
    $('#'+id+' .ztree').css('display','none');
    $("body").unbind("mousedown", onBodyDownByActionType);
    return false;
}

function showTree1(arg){
    console.log(arg)
    var id= this.id;
    if($('.ztree').css('display') == 'none'){
        $('.ztree').css('display','block');
    } else{
        $('.ztree').css('display','none');
    }
    $("body").bind("mousedown", onBodyDownByActionType);
}
//区域外点击事件
function onBodyDownByActionTyperole(event) {
    if (event.target.id.indexOf('treeDemo1') == -1){
        if(event.target.id != 'selectDevType'){
            hideTree();
        }
    }
}


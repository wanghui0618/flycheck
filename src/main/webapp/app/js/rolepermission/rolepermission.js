//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','form'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    table.render({
        elem: '#rolePermissionTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/rolepermission/rolePermission/list'
        , height: 415
        , where: {ilegalChild: '1'}
        , cols: [[
            {type: 'numbers', width: 80, title: '序号'}
            , {title: '操作', toolbar: '#table-useradmin-webuser', align: 'center'}
            , {field: 'roleName', align: 'center', title: '角色名称'}
            , {field: 'permissionCode', align: 'center',  title: '权限代码',}
            , {field: 'permissionName', align: 'center',  title: '权限名称',}
            , {field: 'accessingResource', align: 'center',  title: '访问资源',}

        ]]
        , page: true
    });

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('rolePermissionTable', {
            where: field
        });
    });

    //添加事件
    var active = {
        add: function () {
            //添加角色信息
            layer.open({
                type: 2
                , title: '添加权限信息'
                , content: $WEB_ROOT_PATH + '/rolepermission/rolepermissionAdd'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'layuiadmin-btn-useradmin'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    console.log("information")
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/rolepermission/rolePermission/save";
                        $.post(url, field, function (result) {
                         if(result.operateSuccess){
                                layer.msg('新增成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('rolePermissionTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            }else {
                             layer.msg('新增失败!');
                             //后台成功后，静态更新表格中的数据
                             table.reload('rolePermissionTable'); //数据刷新
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
    table.on('tool(rolePermissionTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'update') {
            //修改
            layer.open({
                type: 2
                , title: '编辑权限信息'
                , content: $WEB_ROOT_PATH + '/rolepermission/rolepermissionAdd'
                , maxmin: true
                , area: ['500px', '400px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    console.log(data)
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
                        var url = $WEB_ROOT_PATH + "/dhccApi/rolepermission/rolePermission/save";
                        $.post(url, field, function (result) {
                            layer.msg('编辑成功!');
                            //后台成功后，静态更新表格中的数据
                            table.reload('rolePermissionTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                    });
                    submit.trigger('click');
                }
            });
        } else
        if (obj.event === 'delete') {
            //删除
            var id = data.id;
            layer.confirm('是否确定删除', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/rolepermission/rolePermission/delete";
                $.post(url, {"rolePermission.id": id}, function (result) {
                    table.reload('rolePermissionTable');
                    layer.msg('已删除');
                });
            });
        }
    });

    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
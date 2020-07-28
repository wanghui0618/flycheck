//
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
    table.render({
        elem: '#outtreatmentTable'
        ,url: $WEB_ROOT_PATH+'/dhccApi/outtreatment/outtreatment/list'
        ,cellMinWidth: 80
        ,height: tableHeight
        ,cols: [[
            {type: 'numbers', width:40, title: '编号'}
            ,{title:'操作', width: 160, align:'center', toolbar: '#table-useradmin-webuser'}
            ,{field:'cityCode',width: 120,align:'center', title: '城市编码' }
            ,{field:'typeNo', width: 120,align:'center',title: '规则编码' }
            ,{field:'typeName',width: 120,align:'center', title: '规则名称' }
            ,{field:'itemCode', width: 120,align:'center',title: '目录编码' }
            ,{field:'itemName',width: 120, align:'center',title: '目录名称' }
            ,{field:'logic', width: 120,align:'center',title: '判断逻辑' }
            ,{field:'logicSql', width: 120,align:'center',title: '逻辑算法' }
            ,{field:'comments', align:'center',title: '备注' }
        ]]
        ,page: true
    });
    // hideButtonStatic();//按钮权限

    //监听搜索
    form.on('submit(LAY-user-front-search)', function(data){
        var field = data.field;
        //执行重载
        layui.table.reload('outtreatmentTable', {
            where: field
        });
    });

    //添加事件
    var active = {
        add: function(){
            //新增方法
            layer.open({
                type: 2
                ,title: '添加超限定疗程'
                ,content: $WEB_ROOT_PATH+'/outtreatment/outtreatmentAdd'
                ,maxmin: true
                ,area: ['750px', '450px']
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'layuiadmin-btn-useradmin'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url=$WEB_ROOT_PATH+"/dhccApi/outtreatment/outtreatment/save";
                        $.post(url,field,function(result){
                            if(result.operateSuccess==true){
                                layer.msg('添加成功!');
                            }else{
                                layer.msg('添加失败!');
                            }
                            //后台成功后，静态更新表格中的数据
                            table.reload('outtreatmentTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                    });
                    submit.trigger('click');
                }
            });
        }
    };
    //监听行点击
    table.on('tool(outtreatmentTable)', function(obj){
        var data = obj.data;
        console.log(data)
        if(obj.event === 'del'){
            //删除
            layer.confirm('确定删除该超限定疗程？', function(index){
                //执行 Ajax 后重载
                var url=$WEB_ROOT_PATH+"/dhccApi/outtreatment/outtreatment/delete";
                $.post(url,{'outtreatment.id':data.id},function(result){
                    table.reload('outtreatmentTable');
                    layer.msg('已删除');
                });
            });
        } else if(obj.event === 'edit'){
            //修改方法
            layer.open({
                type: 2
                ,title: '编辑超限定疗程'
                ,content: $WEB_ROOT_PATH+'/outtreatment/outtreatmentAdd'
                ,maxmin: true
                ,area: ['750px', '450px']
                ,btn: ['确定', '取消']
                ,success: function(layero, index){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));

                }
                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'layuiadmin-btn-useradmin'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        console.log(field)
                        //提交 Ajax后台
                        var url=$WEB_ROOT_PATH+"/dhccApi/outtreatment/outtreatment/save";
                        $.post(url,field,function(result){
                            if(result.operateSuccess==true){
                                layer.msg('修改成功!');
                            } else{
                                layer.msg('修改失败!');
                                //后台成功后，静态更新表格中的数据
                            }
                            table.reload('outtreatmentTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        });
                    });
                    submit.trigger('click');
                }
            });
        }
    });

    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
    function InitTable() {
        table.render({
            elem: '#qualityTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/quality/quality/list'
            , cellMinWidth: 80
            , height: document.documentElement.clientHeight-65
            , cols: [[
                {type: 'numbers', title: '序号'}
                , {field: 'id', title: 'ID', hide: true}
                , {field: 'left', title: '操作', toolbar: '#table-useradmin-quality', width: 220}
                , {field: 'tableName', title: '业务表名'}
                , {field: 'tableCol', title: '业务表字段'}
                , {field: 'tableColName', title: '表字段名'}
                , {field: 'type', title: '类型'}

            ]]
            , page: true
        })
    };
    InitTable();
    //搜索
    form.on('submit(LAY-quality-front-search)', function(data){
        var field = data.field;
        //执行重载
        layui.table.reload('qualityTable', {
            where: field
            ,page: { curr: 1}
        });
    });

    var active = {
        add: function () {
            /* console.log("addUserData")
             console.log(addUserData)*/
            //新增方法
            layer.open({
                type: 2
                , title: '添加数据质量标准'
                , content: $WEB_ROOT_PATH + '/quality/qualityAdd'
                , maxmin: true
                , area: ['700px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-quality-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        console.info(field);
                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/quality/quality/save";
                        $.post(url, field, function (result) {
                            var inFlag = result.operateSuccess;
                            if (inFlag == true) {
                                layer.msg('添加成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('qualityTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            }else{
                                layer.msg('添加失败!');
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
    table.on('tool(qualityTable)', function(obj) {
        var data = obj.data;
        if (obj.event === 'change') {
            layer.open({
                type: 2
                ,title: '数据质量标准修改'
                ,content: $WEB_ROOT_PATH+'/quality/qualityInfo'
                ,maxmin: true
                ,area: ['800px', '500px']
                ,btn: ['确定', '取消']
                ,success: function(layero, index){
                    console.log(data);
                    var iframeWindow = window['layui-layer-iframe'+ index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));
                }

                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-quality-front-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url=$WEB_ROOT_PATH+"/dhccApi/quality/quality/save";
                        $.post(url,field,function(result){

                            layer.msg('编辑成功!');
                            //后台成功后，静态更新表格中的数据
                            table.reload('qualityTable'); //数据刷新
                            layer.close(index); //关闭弹层

                        });

                    });
                    submit.trigger('click');
                }
            });

        }else if(obj.event='delete'){
            var id=data.id;
            layer.confirm('是否确定删除', function(index){
                //执行 Ajax 后重载
                var url=$WEB_ROOT_PATH+"/dhccApi/quality/quality/delete";
                $.post(url,{"quality.id":id},function(result){
                    table.reload('qualityTable');
                    layer.msg('已删除');
                });
            });
        }
    });
    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    });


function child(obj) {
    var diseasesClass = JSON.parse(obj);
    $("#id").val(diseasesClass["id"]);
    for ( var index in diseasesClass) {
        $("#" + index).val(diseasesClass[index])
    }
}
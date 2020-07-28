//初始化	
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    table.render({
        elem: '#clinicalPathTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/clinicalpath/clinicalPath/listVo'
        , height: tableHeight
        , cellMinWidth: 80
        , where: {ilegalChild: '1'}
        , cols: [[
            {type: 'numbers',align: 'center', title: '序号'}
            , {field: 'id', width: 80, hide: true, title: '编号'}
            , {title: '操作', align: 'center', toolbar: '#table-useradmin-webuser', width: 250,hide:rowOperate(['clinicalPath-show','clinicalPath-update','clinicalPath-delete'])}
            , {field: 'name',align: 'center', width: 350, title: '名称'}
            , {field: 'edition',align: 'center', title: '版本'}
            , {field: 'code',align: 'center', title: '编号'}
            /*, {field: 'createDate', title: '创建时间'}*/
            /*,{field:'updateDate',width:265, title: '更新时间'}*/
        ]]
        , page: true
    });
  //加载医院下拉字典
    $.getJSON($WEB_ROOT_PATH+'/dhccApi/clinicalpath/clinicalPath/dictClinicalPath',
        function(data){
            var orgs=data;
            //var  dataList= data.dictList;
           // var org_save=JSON.stringify(orgs);//解析为字符串
            //localStorage.clear();
           
            //localStorage.setItem('org_save',org_save);//存入浏览器数据库
            for(var i=0 ;i<orgs.length;i++){
                var mm="<option value='"+orgs[i].code+"'>"+orgs[i].name+"</option>";
                $("#zyOrgName").append(mm);
            }
            form.render('select');
       });
    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('clinicalPathTable', {
            where: field
        });
    });

    //添加事件
    var active = {
        add: function () {
            //添加记录
            layer.open({
                type: 2
                , title: '临床路径'
                , content: $WEB_ROOT_PATH + '/clinicalPathway/clinicalPathwayInfo'
                , maxmin: true
                , area: ['800px', '500px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //加载select下拉option
                    /*iframeWindow.loadSelect();*/
                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-cityprice-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/clinicalpath/clinicalPath/saveMine";
                        field["content"]=field["clinicalPath.content"];
                        field["clinicalPath.content"]="";
                        $.post(url, field, function (result) {
                            var inFlag = result.inFlag;
                            if (inFlag == 0) {
                                layer.msg('保存成功!');
                                //后台成功后，静态更新表格中的数据
                                layer.close(index); //关闭弹层
                                table.reload('clinicalPathTable'); //数据刷新
                            } else if (inFlag == 1) {
                                layer.msg('已经存在该条记录!');
                                return false;
                            }
                        });
                    });
                    submit.trigger('click');
                }
            });
        }
    };
    hideButtonStatic();//静态按钮授权
    //监听行点击
    table.on('tool(clinicalPathTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('确定要删除该条数据？', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/clinicalpath/clinicalPath/delete";
                $.post(url, {'clinicalPath.id': data.id}, function (result) {
                    table.reload('clinicalPathTable');
                    layer.msg('删除成功！');
                });
            });

        } else if (obj.event === 'view') {
            //预览
            console.log(data)
            window.parent.parent.showClinicalPathWayView(data);
        } else if (obj.event === 'update') {
            //修改
            layer.open({
                type: 2
                , title: '修改临床路径'
                , content: $WEB_ROOT_PATH + '/clinicalPathway/clinicalPathwayInfo'
                , maxmin: true
                , area: ['800px', '500px']
                , btn: ['确定', '取消']
                , success: function (layero, index) {
                    var iframeWindow = window['layui-layer-iframe' + index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-cityprice-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url = $WEB_ROOT_PATH + "/dhccApi/clinicalpath/clinicalPath/saveMine";
                        field["content"]=field["clinicalPath.content"];
                        field["clinicalPath.content"]="";
                        $.post(url, field, function (result) {
                            var inFlag = result.inFlag;
                            if (inFlag == 0) {
                                layer.msg('修改成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('clinicalPathTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            } else {
                                layer.msg('已经存在该条记录!');
                                return false;
                            }
                        });
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
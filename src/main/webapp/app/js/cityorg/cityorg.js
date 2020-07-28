//初始化	
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table;
    //加载城市下拉字典
    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city',
        function(data){
            var  dataList= data.dictList;
            for(var i=0 ;i<dataList.length;i++){
                var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
                $("#city").append(mm);
            }
            form.render('select');
        });
   /* //加载医院下拉字典
    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
        function(data){
            var orgs=data.pageModel.rows
            //var  dataList= data.dictList;
            // var org_save=JSON.stringify(orgs);//解析为字符串
            //localStorage.clear();

            //localStorage.setItem('org_save',org_save);//存入浏览器数据库
            for(var i=0 ;i<orgs.length;i++){
                var mm="<option value='"+orgs[i].text+"'>"+orgs[i].text+"</option>";
                $("#zyOrgName").append(mm);
            }
            form.render('select');
        });*/
    hideButtonStatic();//静态按钮权限
    table.render({
        elem: '#cityOrgTable'
        ,url: $WEB_ROOT_PATH+'/dhccApi/cityorg/dictCityOrg/listVo'
        ,height: tableHeight
        ,where: {  ilegalChild: '1'  }
        ,cols: [[
            {type: 'numbers', title: '序号' }
            ,{field:'id', width:80,hide:true,title: '编号'}
            ,{title:'操作', align:'center', width:180,toolbar: '#table-useradmin-webuser',hide:rowOperate(['cityorg-update','cityorg-delete'])
            }
            ,{field:'orgCode', width:110,title: '机构编码'}
            ,{field:'orgName',width:220, title: '机构名称'}
            /*,{field:'cityName',width:110, title: '城市名称'}*/
            ,{field:'orgKind',width:120, title: '服务机构类型'}
            ,{field:'orgType',width:120, title: '医疗机构类别'}
            ,{field:'orgLevel', width:200,title: '医院等级'}
            ,{field:'medicareCoverage',width:120, title: '医保许可范围'}
            ,{field:'bedNum',width:80, title: '床位数'}
            ,{field:'publicHospitalLevel',width:120, title: '公立医院级别'}
            ,{field:'administrativeLevel',width:120, title: '行政区域级别'}
            ,{field:'orgTypeLevel',width:140, title: '医疗机构类别级别'}
            ,{field:'orgLevelAlias',width:140, title: '医疗机构级别别名'}
            /*,{field:'cityCode',width:110, title: '城市编码'}*/

        ]]
        ,page: true
    });

    //监听搜索
    form.on('submit(LAY-user-front-search)', function(data){
        var field = data.field;
        //执行重载
        layui.table.reload('cityOrgTable', {
            where: field
        });
    });
    //监听同步
    form.on('submit(LAY-user-front-syn)', function(){
        layer.confirm('确定同步？', function () {
           //var url = $WEB_ROOT_PATH + '/dhccApi/unit/unit/synInfo';
            var url = $WEB_ROOT_PATH + '/dhccApi/unit/unit/insertDataAutho';
            $.post(url,function (result) {
                if (result=="success"){
                    layer.msg('同步成功');
                }else{
                    layer.msg('同步失败');
                }
            });
        });
    });






    //添加事件
    var active = {
        add: function(){

            //添加记录
            layer.open({
                type: 2
                ,title: '保存城市机构'
                ,content: $WEB_ROOT_PATH+'/cityorg/cityorginfo'
                ,maxmin: true
                ,area: ['750px', '450px']
                ,btn: ['确定', '取消']
                ,success: function(layero, index){
                    var iframeWindow = window['layui-layer-iframe'+ index];
                    //加载select下拉option
                    iframeWindow.loadSelect();
                }
                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-cityorg-front-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url=$WEB_ROOT_PATH+"/dhccApi/cityorg/dictCityOrg/saveMine";
                        $.post(url,field,function(result){
                            var inFlag= result.inFlag;
                            if(inFlag==0){
                                layer.msg('保存成功!');
                                //后台成功后，静态更新表格中的数据
                                layer.close(index); //关闭弹层
                                table.reload('cityOrgTable'); //数据刷新
                            }else if(inFlag==1){
                                layer.msg('已经存在该条orgCode+cityCode!');
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
    table.on('tool(cityOrgTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'delete'){
            layer.confirm('确定要删除该条数据？', function(index){
                //执行 Ajax 后重载
                var url=$WEB_ROOT_PATH+"/dhccApi/cityorg/dictCityOrg/delete";
                $.post(url,{'dictCityOrg.id':data.id},function(result){
                    table.reload('cityOrgTable');
                    layer.msg('删除成功！');
                });
            });

        }else if(obj.event === 'update'){
            //修改
            layer.open({
                type: 2
                ,title: '修改城市机构'
                ,content: $WEB_ROOT_PATH+'/cityorg/cityorginfo'
                ,maxmin: true
                ,area: ['750px', '450px']
                ,btn: ['确定', '取消']
                ,success: function(layero, index){
                    var iframeWindow = window['layui-layer-iframe'+ index];
                    //向此iframe层方法 传递参数
                    iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
                }
                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-cityorg-front-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax后台
                        var url=$WEB_ROOT_PATH+"/dhccApi/cityorg/dictCityOrg/saveMine";
                        $.post(url,field,function(result){
                            var inFlag= result.inFlag;
                            if(inFlag==0){
                                layer.msg('修改成功!');
                                //后台成功后，静态更新表格中的数据
                                table.reload('cityOrgTable'); //数据刷新
                                layer.close(index); //关闭弹层
                            }else{
                                layer.msg('已经存在该条orgCode+cityCode!');
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
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
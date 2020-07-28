var tableAll;
var updateData;
var Obj;
var index;
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'upload'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;
    tableAll = table;

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('clinicalGuidelineTable', {
            where: field
        });
    });

    table.render({
        elem: '#clinicalGuidelineTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/clinicalGuideline/clinicalGuideline/findAllList'
        , cellMinWidth: 80
        , height: tableHeight
        , where: {ilegalChild: '1'}
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'left',align:'center', title: '操作', toolbar: '#table-useradmin-webuser', width:220,hide:rowOperate(['clinicalguideline-view','clinicalguideline-update','clinicalguideline-delete'])}
            , {field: 'name', title: '临床指南名称', align: 'center'}
            , {field: 'source', title: '来源', align: 'center'}
            , {field: 'createDate', title: '添加时间', align: 'center'}
            /*, {field: 'createUser', title: '创建人', align: 'center'}*/
        ]]
        , page: true
    });
    hideButtonStatic();//按钮权限

    table.on('tool(clinicalGuidelineTable)', function (obj) {
        Obj=obj;
        beforesize = $('tbody>tr').size()
        var data = obj.data;
        if (obj.event === 'delete') {
            //删除
            var id = data.id;
            layer.confirm('是否确定删除', function (index) {
                //执行 Ajax 后重载
                var url = $WEB_ROOT_PATH + "/dhccApi/clinicalGuideline/clinicalGuideline/delete";
                $.post(url, {"clinicalGuideline.id": id}, function (result) {
                    table.reload('clinicalGuidelineTable');
                    layer.msg('已删除');
                });
            });
        }
        if (obj.event === 'update') {
		    layer.open({
		          type: 2
		          ,title: '编辑临床指南信息'
		          , content: $WEB_ROOT_PATH + '/clinicalguideline/clinicalguidelineAdd'
		          ,maxmin: true
		          , area: ['800px', '500px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index]
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-org-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/clinicalGuideline/clinicalGuideline/edit";
		              field["content"]=field["clinicalGuideline.content"];
		              field["clinicalGuideline.content"]="";
		              $.post(url,field,function(result){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('clinicalGuidelineTable'); //数据刷新
			                  layer.close(index); //关闭弹层

					  });
		            });  
		            submit.trigger('click');
		          }
		        });
        }
        
        if(obj.event === 'view'){
      		//预览
    	  	window.parent.parent.showView(data);
        } 
    });

    //添加事件
    var active = {
  	      add: function(){
  	    	//新增方法
  	        layer.open({
  	          type: 2
  	          ,title: '添加临床指南信息'
  	          , content: $WEB_ROOT_PATH + '/clinicalguideline/clinicalguidelineAdd'
  	          ,maxmin: true
  	          , area: ['800px', '500px']
  	          ,btn: ['确定', '取消']
  	          ,yes: function(index, layero){
  	            var iframeWindow = window['layui-layer-iframe'+ index]
  	            ,submitID = 'LAY-org-front-submit'
  	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

  	            //监听提交
  	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
  	              var field = data.field; //获取提交的字段
  	              //提交 Ajax后台 
  	              var url=$WEB_ROOT_PATH+"/dhccApi/clinicalGuideline/clinicalGuideline/save";
  	            field["content"]=field["clinicalGuideline.content"];
                field["clinicalGuideline.content"]="";
  	              $.post(url,field,function(result){
  	            		  layer.msg('添加成功!');
  	            		//后台成功后，静态更新表格中的数据
  		                  table.reload('clinicalGuidelineTable'); //数据刷新
  		                  layer.close(index); //关闭弹层
  				  });
  	            });  
  	            submit.trigger('click');
  	          }
  	        }); 
  	      }
  	    };

    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


});


//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	
	table.render({
		elem: '#druglnstmctionTable'
		,url: $WEB_ROOT_PATH+'/dhccApi/druglnstmction/drugLnstmction/listVo'
		,cellMinWidth: 80
        ,height: tableHeight
		,where: {ilegalChild: '1'}
	    ,cols: [[
		 {type: 'numbers', title: '序号' }
		,{field:'id', hide:true,title: '编号'}
		,{title:'操作', align:'center',toolbar: '#table-orgadmin-webuser', width:250}
		,{field:'name',align: 'center', width:250,title: '药品名称'}
		,{field:'contraindication',align: 'center', title: '禁忌症'}
		,{field:'indication',align: 'center', title: '适应症'}
		/*,{field:'createPeople', title: '添加人'}*/
		/*,{field:'createDate', title: '添加时间'}*/
		]]
	,page: true
	});
	hideButtonStatic();//按钮权限
	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//执行重载
		layui.table.reload('druglnstmctionTable', {
			where: field
		});
	});

	//添加事件
	var active = {
			add: function(){
			//添加记录
			layer.open({
				type: 2
				,title: '添加药品说明书'
				,content: $WEB_ROOT_PATH+'/druglnstmction/druglnstmction'
				,maxmin: true
				,area: ['800px', '500px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//加载select下拉option
					/*iframeWindow.loadSelect();*/
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityprice-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
			
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/druglnstmction/drugLnstmction/save";
					field["content"]=field["drugLnstmction.content"];
                    field["drugLnstmction.content"]="";
					$.post(url,field,function(result){
						if(result.inFlag=="1"){
		            		  layer.msg('该药品名称已经存在，请勿重复添加！');
		            	  }else{
		            		  layer.msg('添加成功!');
		            		  table.reload('druglnstmctionTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }
					});
				});
				submit.trigger('click');
				}
				});
			}
	};
	
	


	
    //监听行点击
    table.on('tool(druglnstmctionTable)', function(obj){
    	var id = obj.data.id;
        var data=obj.data;
        //console.info(data+"---");
        //console.log(id);
        $.ajax({
      	  url:$WEB_ROOT_PATH+'/dhccApi/druglnstmction/drugLnstmction/listVo2',
      	  	 type : "post",		
  	         async : false,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
  	         dataType : "json",
  	         data:{findId:id},
  	         success : function(result) {
  	        	 var data1 = result.data;
  	        	 data.content=data1[0].content;//类型不一致！
  	        	// console.log(data);
  	        	if(obj.event === 'del'){
  	    	    	//删除
  	    		  layer.confirm('确定删除该项？', function(index){
  	    		      //执行 Ajax 后重载
  	    			  var url=$WEB_ROOT_PATH+"/dhccApi/druglnstmction/drugLnstmction/delete";
  	    		        $.post(url,{'drugLnstmction.id':data.id},function(result){
  	    		        	table.reload('druglnstmctionTable');
  	    		            layer.msg('已删除');
  	    		    	    });
  	    		        });
  	    	      }else if(obj.event === 'view'){
  	    	      		//预览
  	    	    	  	window.parent.parent.showView(data);
  	    	      } else if(obj.event === 'edit'){
  	    	    	//修改方法
  	    		    layer.open({
  	    		          type: 2
  	    		          ,title: '编辑药品说明书'
  	    		          ,content: $WEB_ROOT_PATH+'/druglnstmction/druglnstmction'
  	    		          ,maxmin: true
  	    		          ,area: ['800px', '500px']
  	    		          ,btn: ['确定', '取消']
  	    		          ,success: function(layero, index){
  	    		        	  var iframeWindow = window['layui-layer-iframe'+ index]
  	    		        	  //向此iframe层方法 传递参数
  	    		        	  iframeWindow.child(JSON.stringify(data));
  	    		          }
  	    		          ,yes: function(index, layero){
  	    		            var iframeWindow = window['layui-layer-iframe'+ index]
  	    		            ,submitID = 'LAY-cityprice-front-submit' 
  	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

  	    		            //监听提交
  	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
  	    		              var field = data.field; //获取提交的字段
  	    		              //提交 Ajax后台 
  	    		              var url=$WEB_ROOT_PATH+"/dhccApi/druglnstmction/drugLnstmction/edit";
  	    		              field["content"]=field["drugLnstmction.content"];
  	    	                  field["drugLnstmction.content"]="";
  	    		              $.post(url,field,function(result){
  	    			                  if(result.inFlag=="1"){
  	    			            		  layer.msg('该药品名称已经存在，请勿重复添加！');
  	    			            	  }else{
  	    			            		  layer.msg('修改成功!');
  	    			            		  table.reload('druglnstmctionTable'); //数据刷新
  	    				                  layer.close(index); //关闭弹层
  	    			            	  }
  	    					  });
  	    		            });  
  	    		            submit.trigger('click');
  	    		          }
  	    		        }); 
  	    	      }
  	         }
  	         
        });
        
        //console.info(data.content+"----");
    });

	//按钮事件绑定底层方法-勿动
	$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});
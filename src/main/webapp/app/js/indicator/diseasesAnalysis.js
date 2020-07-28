//初始化	 
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	   /* $(function(){
	    	test1();
	    	test2();
		});*/
	    	 table.render({
	    	     elem: '#addressTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/indicator/diseasesAnalysis/listVo'
	            ,cellMinWidth: 80
	            ,height: 340
				 ,where:{"diseasesAnalysis.orgName":"hadding"}
	            ,skin: 'nob' //无边框
	            /*,even: true*/ //开启隔行背景
	            ,size: 'sm'
	            ,cols: [[
	            	   {type: 'numbers',width: '10%', title: '序号' }
		              ,{field:'handdingInsName',width: '30%',title: '统筹区'}
		              ,{field:'totalCost',width: '30%', title: '病种费用(元)'}
		              ,{field:'totalProportion',title: '占总比',templet:function (value) {
							 return value.totalProportion+"%";
						 }}
		            ]]
	    	 ,done:function(){
	    		 $('th').css({'background-color': 'white', 'color': '#3D85C6','font-weight':'bold'});
	    		 $('div').css({'border':'0px','background-color': 'white'})
	    		 $('tr').css({'border':'0px'})
	    	 }
		          });
	    	 table.render({
	    	     elem: '#doctorTable'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/indicator/diseasesAnalysis/listVo'
	            ,cellMinWidth: 80
	            ,height: 340
				 ,where:{"diseasesAnalysis.orgName":"all"}
	            ,skin: 'nob' //行边框风格
	            /*,even: true *///开启隔行背景
	            ,size: 'sm'
	            ,cols: [[
	            	   {type: 'numbers',width: '10%', title: '序号' }
		              ,{field:'orgName',width: '30%', title: '医院名字'}
		              ,{field:'totalCost',width: '30%', title: '病种费用(元)'}
					 ,{field:'totalProportion',title: '占总比',templet:function (value) {
							 return value.totalProportion+"%";
						 }}
		            ]]
		          });
	    
	    	 table.render({
	    	     elem: '#diseasesAnalysisTable'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/indicator/diseasesAnalysis/listVo'
	            ,cellMinWidth: 80
	            ,height: 340
				 ,where:{"diseasesAnalysis.orgName":"hosAll"}
	            ,skin: 'nob' //行边框风格
	            /*,even: true *///开启隔行背景
	            ,size: 'sm'
	            ,cols: [[
					 {type: 'numbers',width: '10%', title: '序号' }
					 ,{field:'orgName',width: '30%', title: '医院名字'}
					 ,{field:'totalCost',width: '30%', title: '病种费用(元)'}
					 ,{field:'totalProportion',title: '占总比',templet:function (value) {
							 return value.totalProportion+"%";
						 }}
		            ]]
	    	   ,page:true
		           });

	table.render({
		elem: '#diseasesAnalysisTable1'
		,url:$WEB_ROOT_PATH+'/dhccApi/indicator/diseasesAnalysis/listVo'
		,cellMinWidth: 80
		,height: 340
		,where:{"diseasesAnalysis.orgName":"outAll"}
		,skin: 'nob' //行边框风格
		/*,even: true *///开启隔行背景
		,size: 'sm'
		,cols: [[
			{type: 'numbers',width: '10%', title: '序号' }
			,{field:'orgName',width: '30%', title: '医院名字'}
			,{field:'totalCost',width: '30%', title: '病种费用(元)'}
			,{field:'totalProportion',title: '占总比',templet:function (value) {
					return value.totalProportion+"%";
				}}
		]]
		,page:true
	});
	  
	   /* 
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
	   //添加事件
	    var active = {
	    		add: function(){
	    			
	    			 
	    		    	//新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加字典'
	    		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
	    		          ,maxmin: true
	    		          ,area: ['500px', '450px']
	    		          ,btn: ['确定', '取消']
	    		          ,success: function(layero, index){
	    						var iframeWindow = window['layui-layer-iframe'+ index];
	    						//加载select下拉option
	    						iframeWindow.loadSelect();
	    					}
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	    		            //监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; //获取提交的字段
	    		             // console.log(field);
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('userTable'); //数据刷新
                      layer.close(index); //关闭弹层
            	  }else if(inFlag==1){
            		  layer.msg('该字典名称已存在！')
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
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定要删除该条数据？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/delete";
	            $.post(url,{"dictmaintain.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改字典'
		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
		          ,maxmin: true
		          ,area: ['500px', '450px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	//加载select下拉option
						iframeWindow.loadSelect();
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  console.log(JSON.stringify(data));
		        	
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              console.log(field);
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save1";
		              $.post(url,field,function(result){
		            	  var inFlag=result.inFlag;
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
		            		  //后台成功后，静态更新表格中的数据
		                      table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('修改成功!')
		            		   table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            		  //return false;
		            	  }
					  });
			       });
		            submit.trigger('click');
		          }
		        }); 
	      }
	    });
	    

	    function deRepeat(arr){
	           var newArr=[];
	           for(var i=0;i<arr.length;i++){
	        	   var text=arr[i].text;
	               if($.inArray(arr[i],newArr)==-1){
	                   newArr.push(arr[i]);
	               }
	           }
	           return newArr;
	       }*/
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
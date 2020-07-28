//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    //日期范围  
		var laydate1=layui.laydate;
		laydate1.render({
			elem:'#startTime'
				,trigger:'click'
						,format:'yyyy-MM-dd' 
							,range: true
		});
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/app//js/resultAppeal/resultCount.json'
	            ,cellMinWidth: 80
	             ,height: 415
	            //,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	  {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'verifyType', title: '审核状态'}
		              ,{field:'indications', title: '适应症违规'}
		              ,{field:'contraindication', title: '禁忌症违规'}
		              ,{field:'sexViolation', title: '性别违规'}
		              ,{field:'hospitalLevelDrug', title: '医院等级用药违规'}
		              ,{field:'zhuYuanDrug', title: '住院用药违规'}
		              ,{field:'birthInsurance', title: '生育保险用药违规'}
		              ,{field:'rescueDrug', title: '抢救用药违规'}
		              ,{field:'childDrug', title: '儿童用药违规'}
		              ,{field:'fenJie', title: '分解住院违规'}
		              ,{field:'repeatCharges', title: '重复收费违规'}
		              ,{field:'violationPer', title: '总违规占比（%）'}
		            ]]
	           
		    		,page: true
		          });
	    
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
	       }
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
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
	    	elem: '#medicalTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/getList'
	            ,cellMinWidth: 80
	            ,height: 415
	            ,where: { 'medicalDetail.tPiccbidMedicalId': t_piccbid_medical_id  }
	            ,cols: [[
	              { type: 'numbers',width:40, title: '序号'  }
	              ,{title:'操作', toolbar: '#table-useradmin-webuser', width:140}
	              ,{field:'id', title: 'ID', sort: true, hide:true}
	              ,{field:'itemName', title: '项目名称'}
	              ,{field:'itemCode', title: '项目编号'}
	              ,{field:'itemStandard', title: '项目规格'}
	              ,{field:'itemPrice', title: '项目单价'}
	              ,{field:'itemQuantity', title: '项目数量'}
	              ,{field:'itemCost',title: '项目金额'}
	              ,{field:'drugType', title: '药品类别'}
	              ,{field:'billingNo', title: '收费单据号'}
	              ,{field:'tPiccbidMedicalId', title: 't_Piccbid_medical表主键' }
	              
	              
	            ]]
	           
	            ,page: false
	            ,done:function(res, curr, count){
	            	console.log(res)
	            }
	            
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('medicalTable', {
	            where: field
	        });
	    });
	  
		//添加事件
	    var active = {
	    		
	    		addinfo: function(){
	    			console.log($("#dto.medical.id"));
	    		    	//新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加信息'
	    		          ,content: $WEB_ROOT_PATH+'/medical/medicalDetailInfoform'
	    		          ,maxmin: true
	    		          ,area: ['800px', '450px']
	    		          ,btn: ['确定', '取消']
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	    		            //监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; //获取提交的字段
	    		             // console.log(field);
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('medicalTable'); //数据刷新
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
	    table.on('tool(medicalTable)', function(obj){
	      var data = obj.data;
	      console.log(data);
	      if(obj.event === 'delinfo'){
	    	//删除
	        layer.confirm('真的删除行么', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/delete";
	            $.post(url,{"medicalDetail.id":data.id},function(result){
	        	    table.reload('medicalTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'editinfo'){
	    	//修改方法
			    layer.open({
			          type: 2
			          ,title: '修改信息'
			          ,content: $WEB_ROOT_PATH+'/medical/medicalDetailInfoform'
			          ,maxmin: true
			          ,area: ['800px', '450px']
			          ,btn: ['确定', '取消']
			          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index];
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			          }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'LAY-user-front-submit'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
		
			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/save1";
			              $.post(url,field,function(result){
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==0){
			            		  layer.msg('修改成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('medicalTable'); //数据刷新
				                  layer.close(index); //关闭弹层
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
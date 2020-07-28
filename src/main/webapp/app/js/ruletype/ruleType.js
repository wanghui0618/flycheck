//初始化	
var ruleNum;
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','element','form'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table
	    ,element=layui.element

	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/ruletype/ruleType/listVo'
	            ,cellMinWidth: 80
	            ,height: tableHeight
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'ruleNo', width:100,title: '规则编号'}
		              ,{field:'ruleName', title: '规则名称'}
		              ,{field:'ruleCatagory', title: '规则分类'}
		              ,{field:'diagType', title: '规则适用业务类型'}
		              ,{field:'ruleProperty',title:'规则适用类型'}
		              ,{field:'validFlag', title: '有效标志'}
		              ,{field:'ruleCatagory', title: '规则分类', sort: true, hide:true}
		              ,{field:'ruleDetails', title: '规则详情', sort: true, hide:true}
		              ,{field:'statements', title: '查询语句', sort: true, hide:true}
		            ]],
		            done:function(){
		      			$("[data-field='diagType']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("住院")
		                    }else if($(this).text()=='2'){
		                        $(this).text("全部")
		                    }else if($(this).text()=='1'){
		                        $(this).text("门诊")
		                    }
		                });
		            
		      			$("[data-field='validFlag']").children().each(function(){
		      				if($(this).text()=='0'){
		                        $(this).text("无效")
		                    }else if($(this).text()=='1'){
		                        $(this).text("有效")
		                    }
		      			});
		      			
		      			$("[data-field='ruleCatagory']").children().each(function(){
		      				if($(this).text()=='0'){
		                        $(this).text("违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("疑似违规")
		                    }
		      			});
		      			
		      			$("[data-field='ruleProperty']").children().each(function(){
		      				if($(this).text()=='0'){
		                        $(this).text("主病例")
		                    }else if($(this).text()=='1'){
		                        $(this).text("明细")
		                    }
		      			});
		      		  }
		    		,page: true
		          });
	    hideButtonStatic();//按钮权限
	    
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
	    		          ,title: '添加规则'
	    		          ,content: $WEB_ROOT_PATH+'/ruletype/ruleTypeform'
	    		          ,maxmin: true
	    		          ,area: ['1159px', '550px']
	    		          ,btn: ['确定', '取消']
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	    		           // document.getElementById("ruleNo").readOnly=false;
	    		            $("#ruleNo").removeAttr("readonly");

	    		            //监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; //获取提交的字段
	    		            
	    		              console.log(field);
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/ruletype/ruleType/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('userTable'); //数据刷新
                      layer.close(index); //关闭弹层
            	  }else if(inFlag==1){
            		  layer.msg('该规则编号已存在！')
            		  return false;
            	  }
			  });
	       });
	    submit.trigger('click');
	     }
	  }); 
	}
};
	    
	    function  getSome1(){
	    	
			var ss = $("input[name='ruleType.applyCity']");
			alert();
			console.log(ss[0].checked);
			console.log(ss[1].checked);
			console.log($("input[name='ruleType.cityName']").html());
				if (ss[0].checked == false) {
					$("input[name='ruleType.cityName']").show();
				} 
				if (ss[1].checked == true){
					$("input[name='ruleType.cityName']").hide();
				}
			};
	  //监听行点击
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	      
	      //$("input[name='ruleType.diagType']").get(data.diagType).checked=true;
		 // $("input[name='ruleType.validFlag']").get(validFlag.validFlag).checked=true;
	      
         
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定删除该条记录吗？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/ruletype/ruleType/delete";
	            $.post(url,{"ruleType.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	  
	    	 // table.reload('dictTable'); //数据刷新
	    	
		    	 
	    	//修改方法
		    layer.open({
		    	 
		          type: 2
		          ,title: '修改规则'
		          ,content: $WEB_ROOT_PATH+'/ruletype/ruleTypeform'
		          ,maxmin: true
		          ,area: ['900px', '450px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	 
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	
			        	//加载select下拉option
							//iframeWindow.loadSelect();
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			        	 /* iframeWindow.ruleProperty(JSON.stringify(data));*/
			        	  var status = data.applyCity;
			        	  ruleNum=data.ruleName;
			          }
			          ,yes: function(index, layero){
			        	
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'LAY-user-front-submit'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
			           // document.getElementById("ruleNo").readOnly=true;
			          
			           
			           
		            
			            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              console.log(field);
		              
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/ruletype/ruleType/save?ruleNum="+ruleNum;
		              $.post(url,field,function(result){
		            	  var inFlag= result.inFlag; 
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('userTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('该规则编号重复！编号无法改变！')
		            		   table.reload('userTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }else if(inFlag==2){
		            		  layer.msg('完成')
		            		   table.reload('userTable'); //数据刷新
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
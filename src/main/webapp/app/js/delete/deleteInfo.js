layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    table.render({
	    	elem: '#deleteTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/delete/delete/listVo'
	        ,cellMinWidth: 80
	        ,height: tableHeight+10
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
			 ,{field:'id', title: 'ID',  align:'center',sort: true, hide:true}
   			,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['delete_update','delete_delete']),}
   			/*,{field:'cityCode',align:'center', title: '城市编码'}*/
			,{field:'handdingInsCode',align:'center', title: '统筹区编码' , hide:true}
			/*,{field:'handdingInsName', align:'center',title: '统筹区' }*/
			,{field:'orgName', align:'center',title: '医疗机构' }
			,{field:'dealFlag',align:'center', title: '处理方式' }
			,{field:'outHosFlag', align:'center',title: '出院状态' }
			,{field:'seeDocType', align:'center',title: '就诊类别' }
			,{field:'workId', align:'center',title: '业务编码' }
			,{field:'workType', align:'center',title: '业务类别' }
			/*,{field:'createDate', align:'center',title: '创建日期' }*/
	        ]]
		    ,done:function(){
	  			$("[data-field='dealFlag']").children().each(function(){
	                if($(this).text()=='1'){
	                    $(this).text("修改")
	                }else if($(this).text()=='2'){
	                    $(this).text("删除")
	                }
	            });
	  			
	  			$("[data-field='outHosFlag']").children().each(function(){
	                if($(this).text()=='0'){
	                    $(this).text("在院")
	                }else if($(this).text()=='1'){
	                    $(this).text("出院")
	                }else if($(this).text()=='2'){
	                    $(this).text("撤销出院")
	                }
	            });
	  			
	  			$("[data-field='seeDocType']").children().each(function(){
	                if($(this).text()=='1'){
	                    $(this).text("住院")
	                }else if($(this).text()=='2'){
	                    $(this).text("门诊")
	                }
	            });
	  		  }
	          ,page: true
	          });
	    
	    hideButtonStatic();//按钮权限
	    function rowOperate(ids) {
	    	var count;
	    	if(ids){
	    		for(var i=0;i<ids.length;i++){
	    			if(!existsButton(ids[i])){
	    				return false;
	    			}
	    		}
	    		return true;
	    	}else{
	    	    return true;
	    	}
	    }
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
			var field = data.field;
			//执行重载
	        layui.table.reload('deleteTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '新增就诊数据删除'
	          ,content: $WEB_ROOT_PATH+'/delete/deleteAdd'
	          ,maxmin: true
	          ,area: ['800px', '465px']
	          ,btn: ['确定', '取消']
	          ,success: function(layero, index){
	        	  var iframeWindow = window['layui-layer-iframe'+ index];
	        	  //向此iframe层方法 传递参数
	        	  iframeWindow.loadSelect();
	              }
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/delete/delete/save";
	              $.post(url,field,function(result){
	            	  if(result.operateSuccess==true){
	            		  layer.msg('添加成功!');
	            	  }else{
	            		  layer.msg('添加失败，请稍后再试!');
	            	  }
	            	  table.reload('deleteTable'); //数据刷新
		              layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(deleteTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该信息？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/delete/delete/delete";
		        $.post(url,{'delete.id':data.id},function(result){
		        	table.reload('deleteTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑就诊数据删除'
		          ,content: $WEB_ROOT_PATH+'/delete/deleteAdd'
		          ,maxmin: true
		          ,area: ['800px', '465px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/delete/delete/save";
		              $.post(url,field,function(result){
		            	  if(result.operateSuccess==true){
		            		  layer.msg('修改成功!');
		            	  }else{
		            		  layer.msg('添加失败，请稍后再试!');
		            	  }
		            	  table.reload('deleteTable'); //数据刷新
			              layer.close(index); //关闭弹层
					  });
		            });  
		            submit.trigger('click');
		          }
		        }); 
	      }
	    });
	    
	    //加载医院下拉字典
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityOrgDict',
            function(data){
                //var  dataList= data.dictList;
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getOrgName").append(mm);
                }
                form.render('select');
           });*/
        
        
        //加载城市下拉字典
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getCityName").append(mm);
                }
                form.render('select');
           });*/
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
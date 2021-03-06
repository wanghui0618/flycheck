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
	    table.render({
	    	elem: '#drugruleTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/jointOper/jointOper/list'
	            /*,cellMinWidth: 80*/
	           // ,where: {  cityCode: 'DHCC'  }
	            ,height: tableHeight
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	  ,{field:'id', title: 'ID', sort: true, hide:true} 
		              ,{title:'基础信息', toolbar: '#table-useradmin-treatment',fixed: 'left', width:150,align:'center'}
		 /*             ,{field:'typeNo',width:130,align:'center', title: '规则编码',fixed: 'left'}
		              ,{field:'typeName',width:130,align:'center', title: '规则名称',fixed: 'left'}*/
		              ,{field:'cityName', width:88,title: '所属城市',fixed: 'left',align:'center',templet: function(d){
	                	    var codex =d.cityName;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'itemCode',width:90,align:'center', title: '项目编码',fixed: 'left'}
		              ,{field:'itemName',width:230,align:'center', title: '项目名称',fixed: 'left'}
		              ,{field:'logicType',width:100,align:'center',title: '逻辑类型'}
		              ,{field:'logicTypeDetail',width:100,align:'center', title: '逻辑明细'}
		              ,{field:'logic',width:350,align:'center', title: '判断逻辑'}
		              ,{field:'includeItemCode',width:110,align:'center', title: '内涵项目编码'}
		              ,{field:'includeItemName',width:120,align:'center', title: '内涵项目名称'}     
		              ,{field:'publicFirstFee',width:125,align:'center', title: '公立一类费用'}     
		              ,{field:'publicSecondFee',width:125,align:'center', title: '公立二类费用'}     
		              ,{field:'publicThirdFee',width:125,align:'center', title: '公立三类费用'}     
		              ,{field:'firstFee',width:130,align:'center', title: '非公立一类费用'}
		              ,{field:'secondFee', width:130,align:'center',title: '非公立二类费用'}
		              ,{field:'thirdFee', width:130,align:'center',title: '非公立三类费用'}
		              ,{field:'comments',width:160,align:'center', title: '备注'}
	            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	        //执行重载
	        layui.table.reload('drugruleTable', {
	            where: field
	        });
	    });
		
		//添加事件
	    var active = {
	      add: function(){
	    	  //信息新增
	    	    layer.open({
			          type: 2
			          ,title: '新增'
				          ,content: $WEB_ROOT_PATH+'/matchCheck/jointOperInfo'
				          ,maxmin: true
				          ,area: ['1000px', '530px']
				          ,btn: ['确定', '取消']
	    	          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.loadSelect();
		              }
	                  ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'layuiadmin-btn-useradmin'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);

			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
                        //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/jointOper/jointOper/save";
			              $.post(url,field,function(result){	
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==1){
				                  layer.msg('该编码已存在!');
			            		  return false;
			            	 }else {
			            		 layer.msg('新增成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('drugruleTable'); //数据刷新
				                  layer.close(index); //关闭弹层
			            	 }
			            	  //关闭弹层
						  });
			            });  
			            submit.trigger('click');
			          }
			        }); 
	      }
	    };
	  
	    //监听行点击
	    table.on('tool(drugruleTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'xiugai'){
	    	  //修改
	    	    layer.open({
			          type: 2
			          ,title: '修改'
			          ,content: $WEB_ROOT_PATH+'/matchCheck/jointOperInfo'
			          ,maxmin: true
			          ,area: ['1000px', '530px']
			          ,btn: ['确定', '取消']
	    	          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		              }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			          ,submitID = 'layuiadmin-btn-useradmin'
			          ,submit = layero.find('iframe').contents().find('#'+ submitID);

			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
			              
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/jointOper/jointOper/save";
			              $.post(url,field,function(result){	
			            	 // var inFlag= result.inFlag; 
			            	  //if(inFlag==0){
			            		  layer.msg('编辑成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('drugruleTable'); //数据刷新
				                  layer.close(index); //关闭弹层
						  });
			            });  
			            submit.trigger('click');
			          }
			        }); 
	   
	      } else if(obj.event === 'shanchu'){
	    	//删除
	    	  var itemCode=data.itemCode;
		        layer.confirm('是否确定删除', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/jointOper/jointOper/delete";
		            $.post(url,{"jointOper.itemCode":itemCode},function(result){
		        	    table.reload('drugruleTable');
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
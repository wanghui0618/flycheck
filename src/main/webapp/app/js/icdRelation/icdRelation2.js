//全局变量(radio单选中的数据)
var radioLeft='';//左边选中的数据
var radioRight='';//右边选中的数据
var lastClickRadio='';//最后一次点击关联的方向(left/right)
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','element'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var element = layui.element;
        var dataLeft;
	    
	    $(document).on("click", ".layui-table-body table.layui-table tbody tr", function () {
			 var obj = event ? event.target : event.srcElement;
			 var tag = obj.tagName;
			 var index = $(this).attr('data-index');
			 var tableBox = $(this).parents(".layui-table-box");
			 //存在固定列
			 if (tableBox.find('.layui-table-fixed.layui-table-fixed-l').length > 0) {
			 tableDiv = tableBox.find('.layui-table-fixed.layui-table-fixed-l');
			 } else {
			 tableDiv = tableBox.find('.layui-table-body.layui-table-main');
			 }
			 var checkCell = tableDiv.find('tr[data-index=' + index + ']').find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
			 if (checkCell.length > 0) {
			 //if(tag == 'DIV') {
			 checkCell.click();
			 //}
			 }
			 });
			 $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
			 e.stopPropagation();
			 });

    //监听行单击事件（单击事件为：rowDouble）
	    table.on('row(icdTable)', function(obj){   	
	       obj.tr.find('i[class="layui-anim layui-icon"]').trigger("click");//选中单选按钮
	       dataLeft = obj.data;
	       var code=dataLeft["code"];
	       var name=dataLeft["name"];
	       var type=dataLeft["type"];
	 /*  layer.alert(JSON.stringify(data), {
	    	title: '当前行数据：'
	    });*/
	   table.render({
		    	    elem:'#icdTableTo'
		            ,url: $WEB_ROOT_PATH+'/dhccApi/icdrelation/icdRelation/listLike'	           
		            ,height: document.documentElement.clientHeight-150
		            ,where: {"icdRelation.icdCodeLeft":code,
		            	"icdRelation.icdNameLeft":name,
						'icdRelation.typeLeft':type }
		            ,cols: [[
		            	 {type:'checkbox',title: '选择'}
		            	,{type:'numbers',width:40, title: '序号' }
		            	,{type:'status',title: '状态',width:150,align:'center',templet: function(d){
	                	    var codex =d.status;
		                	if(codex=="已关联"){
		                		 return '<span style="color:#0EA50E"; >'+ codex +'</span>'	 
		                	}else if(codex=="未关联-名称相似"){
		                		return '<span style="color:#FF3300 "; >'+ codex +'</span>'	 
		                	}
	                  }}
			            ,{field:'code',width:190,align:'center', title: 'ICD编码'}
			            ,{field:'name',width:300,align:'center', title: 'ICD名称'}
			            //,{field:'type',width:120,align:'center', title: 'ICD版本号'}
			            // ,{field:'typeName',width:210,align:'center',title: 'ICD版本名'}
			          /*  ,{field:'typeName',width:210,align:'center',title: 'ICD版本名', hide:true}*/
		            ]]
		            ,page: true
		            ,done:function(res, curr, count){
		            }
		          });	
	    });
	    
	    table.render({
    	    elem:'#icdTable'
            ,url: $WEB_ROOT_PATH+'/dhccApi/icdrelation/icdRelation/listAll'	           
            ,height: document.documentElement.clientHeight-150
            ,where: {"icd.typeName":'wode' }
            ,cols: [[
            	 {type:'radio',title: '选择'}
            	,{type:'numbers',width:40, title: '序号' }
	            ,{field:'code',width:190,align:'center', title: 'ICD编码'}
	            ,{field:'name',width:300,align:'center', title: 'ICD名称'}
	            //,{field:'type',width:120,align:'center', title: 'ICD版本号'}
	            // ,{field:'typeName',width:210,align:'center',title: 'ICD版本名'}
            ]]
            ,page: true
            ,done:function(res, curr, count){
            }
          });
	    
	    table.render({
    	    elem:'#icdTableTo'
            ,url: $WEB_ROOT_PATH+'/dhccApi/icdrelation/icdRelation/listAll'	           
            ,height: document.documentElement.clientHeight-150
            ,where: {"icd.typeName":'国家临床V2.0 ICD-10'}
            ,cols: [[
            	 {type:'radio',title: '选择'}
            	,{type:'numbers',width:40, title: '序号' }
	            ,{field:'code',width:190,align:'center', title: 'ICD编码'}
	            ,{field:'name',width:300,align:'center', title: 'ICD名称'}
	            // ,{field:'type',width:120,align:'center', title: 'ICD版本号'}
	            // ,{field:'typeName',width:210,align:'center',title: 'ICD版本名'}
            ]]
            ,page: true
            ,done:function(res, curr, count){
            }
          });
	    
		//加载ICD版本名字典
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=T_PICCBID_DICT_ICD', 
					function(data){
			     		var  dataList= data.dictList;
			     		for(var i=0 ;i<dataList.length;i++){
			     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			$("#typeName").append(mm); 
			     			$("#typeName2").append(mm); 
			     		}
			     	$("#typeName2").find("option[value ='国家临床V2.0 ICD-10']").attr("selected","selected");
			     	$("#typeName").find("option[value ='']").attr("selected","selected");
			     	form.render('select');
		 });
		 
		 
		 
		 //下拉框不重复选择
		 noreplace(form,$,'select(typeName)','#typeName2 option:selected','#typeName');
		 noreplace(form,$,'select(typeName2)','#typeName option:selected','#typeName2');
	

	/*	//监听radio
		 table.on('radio(icdTable)', function(obj){
			 radioLeft=obj.data;
			 layer.msg(radioLeft);
		 });*/
		 	
		//添加事件
		var active = {
				allrelation: function(){//一键关联
					var typeName=$("#layui-card-header").val();
					var typeName2=$("#layui-card-header-s").html();
			
					if(typeName ==""  || typeName2 ==null){
						layer.msg('请选择ICD版本名!');
						return;
					}else if(typeName==typeName2){
						layer.msg('请选择两个不同的ICD版本名!');
						return;
					}else{
					      layer.confirm('将一键关联<span style="color: red;">"'+typeName+'版"</span><span>与</span><span style="color: red;">"'+typeName2+'版"</span><span>同名ICD数据</span>', function(index){ 	
					    	var url=$WEB_ROOT_PATH+"/dhccApi/icdrelation/icdRelation/saveAll";			
							var field={
									'icdRelation.typeNameLeft':typeName,
									'icdRelation.typeNameRight':typeName2,
									};
							$.post(url,field,function(result){
								var inFlag= result.inFlag; 
									layer.msg('成功关联<span style="color: red;">'+inFlag+"条'</span>'信息");

								});
							});

					}
				}
		            ,relation: function(){//关联
		                var checkStatus = table.checkStatus('icdTableTo')
		                ,data = checkStatus.data;
		       /*      layer.alert(JSON.stringify(data));
		    */
		                
		                if(dataLeft==null){
							layer.msg('请先选择左边数据!');
							return;
						    }
		            	if(data.length==0){
		            		layer.msg('请先选择右边数据!');
							return;
		            	}
 			     	 
					var url=$WEB_ROOT_PATH+"/dhccApi/icdrelation/icdRelation/save";
					var icdCodeLeft=dataLeft.code;
					var icdNameLeft=dataLeft.name;					
					var typeNameLeft=dataLeft.typeName;
					var typeLeft=dataLeft.type;
					var inFlag;
					for(var i = 0; i < data.length; i++) {    
				        var icdCodeRight=data[i].code;
						var icdNameRight=data[i].name;
						var typeRight=data[i].type;
						var typeNameRight=data[i].typeName;									
					var field={'icdRelation.icdCodeLeft':icdCodeLeft,
							   'icdRelation.icdNameLeft':icdNameLeft,
							   'icdRelation.typeNameLeft':typeNameLeft,
							   'icdRelation.typeLeft':typeLeft,
							   'icdRelation.icdCodeRight':icdCodeRight,
							   'icdRelation.icdNameRight':icdNameRight,
							   'icdRelation.typeNameRight':typeNameRight,
							   'icdRelation.typeRight':typeRight,
							};
					$.post(url,field,function(result){
						 inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('关联成功!');
								  layui.table.reload('icdTableTo', {
							            where: field
							        });

							}else if(inFlag==1){
								layer.msg('已经存在该条关联数据');
								return false;
							}
					});
					}	
				}
				,breakrelation: function(){//取消关联
					 var checkStatus = table.checkStatus('icdTableTo')
		                ,data = checkStatus.data;
					 /* layer.alert(JSON.stringify(data));*/
					   if(dataLeft==null){
							layer.msg('请先选择左边数据!');
							return;
						    }
		            	if(data.length==0){
		            		layer.msg('请选择右边数据!');
							return;
		            	}
			 
			     	  var url=$WEB_ROOT_PATH+"/dhccApi/icdrelation/icdRelation/deleteMine";
			     		var icdCodeLeft=dataLeft.code;
						var icdNameLeft=dataLeft.name;					
						var typeNameLeft=dataLeft.typeName;
						var typeLeft=dataLeft.type;
						var inFlag;
						layer.confirm('确定要解除该条数据关联？', function(index){	
			     		for(var i = 0; i < data.length; i++) {    
					        var icdCodeRight=data[i].code;
							var icdNameRight=data[i].name;
							var typeRight=data[i].type;
							var typeNameRight=data[i].typeName;	
							
							
						var field={'icdRelation.icdCodeLeft':icdCodeLeft,
								'icdRelation.icdNameLeft':icdNameLeft,
								'icdRelation.typeNameLeft':typeNameLeft,
								'icdRelation.typeLeft':typeLeft,
								'icdRelation.icdCodeRight':icdCodeRight,
								'icdRelation.icdNameRight':icdNameRight,
								'icdRelation.typeNameRight':typeNameRight,
								'icdRelation.typeRight':typeRight,
								};
					
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('解除关联成功!');
								//执行重载
						        layui.table.reload('icdTableTo', {
						            where: field
						        });
							}else if(inFlag==1){
								layer.msg('两条数据不存在关联关系！');
								return false;
							}else{
								layer.msg('解除关联失败');
								return false;
							}
						});
					
			  }
						});
				},showrelation: function(){
			        layer.open({
				          type: 2
				          ,title: 'ICD关联信息'
				          ,content: $WEB_ROOT_PATH+'/icdRelation/icdRelationShow'
				          ,maxmin: true
				          ,area: ['1000px', '500px']
			              ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index];
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			          }
			        }); 
				}
				,selectrelationLeft:function(){//left关联查询
					//selectRelation('left',$,table)
					
				}
				,selectrelationRight:function(){//right关联查询
					//selectRelation('right',$,table)
				}
		};
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	      });
	 
	  
	  
	    //监听搜索
		form.on('submit(city-search)', function(data){
	    	var field = data.field;    	
	    	var value =$("#layui-card-header").val();
	    	 if(value==""){
	    		 layer.msg('请先选择ICD版本名称！');
					return;	 
	    	 }
	    	 
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	        });
	    });
		
		  //监听搜索
		form.on('submit(LAY-user-front-search-to)', function(data){
	    	var field = data.field;    	
	        //执行重载
	        layui.table.reload('icdTableTo', {
	            where: field
	        });
	    });
	  
	  
	    
	    
	    
	    
	    
	  
	  
	  
	  });


	//下拉框不冲突选择
	function noreplace(form,$,filterlay,selectedId,id){
		 form.on(filterlay, function(data){
			 var value=$(data.elem).find("option:selected").val();
			 var value2= $(selectedId).val();
			 if(value==value2){
				 layer.msg('冲突，请选择其ICD版本号！');
				 $(id).find("option[value ='']").attr("selected","selected");
			 }
			form.render('select');
			
			if(id=='#typeName2'){
				$("#layui-card-header-s").html(value);
				$("#layui-card-header-s").val(value);
			}else if(id=='#typeName'){
				$("#layui-card-header").html(value);
				$("#layui-card-header").val(value);
			}

		 });
	}
	
	
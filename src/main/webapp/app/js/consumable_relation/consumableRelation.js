//全局变量(radio单选中的数据)
var radioLeft='';//左边选中的数据
var radioRight='';//右边选中的数据
var radioRightAll='';//右边所有选中数据
var lastClickRadio='';//最后一次点击关联的方向(left/right)
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    table =  $.extend(table, {config: {checkName: 'checked'}});
		//加载城市下拉字典
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
					function(data){
			     		var  dataList= data.dictList;
			     		for(var i=0 ;i<dataList.length;i++){
			     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			$("#city").append(mm); 
			     			$("#city2").append(mm); 
			     		}
			     	$("#city").find("option[value ='']").attr("selected","selected");
			     	$("#city2").find("option[value ='110000']").attr("selected","selected");
			     	form.render('select');
		 });
		 //下拉框不重复选择
		 noreplace(form,$,'select(city)','#city2 option:selected','#city');
		 noreplace(form,$,'select(city2)','#city option:selected','#city2');
		 //初始化left
		 var url=$WEB_ROOT_PATH+'/dhccApi/consumablerule/consumableRule/list';
		 var elem='#drugruleTable';
		 var elemn='drugruleTable'
		 var cityCode='noCity';
		 loadDate(url,$,form,table,elem,elemn,cityCode,'radio');
		
		 //初始化right
		 elem='#drugruleTablecity';
		 elemn='drugruleTablecity';
		 cityCode="110000";//不存在的城市编码
		 loadDate(url,$,form,table,elem,elemn,cityCode,'checkbox');
		
		//查询按钮监听reload
		formOn(url,$,form,table,'submit(LAY-user-front-search)','drugruleTable');
		formOn(url,$,form,table,'submit(LAY-user-front-search-city)','drugruleTablecity');
		
		//监听radio
		 table.on('radio(drugruleTable)', function(obj){
			 radioLeft=obj.data;
		 });
		//监听radio
		 table.on('checkbox(drugruleTablecity)', function(obj){
			 radioRight=obj.data;
			 radioRightAll=layui.table.checkStatus('drugruleTablecity').data;   // table1为表格id
			 //console.log(radioRightAll);
		 });
		
		 //监听left表行点击
		 table.on('tool(drugruleTable)', function(obj){
			 var data = obj.data;
			 if(obj.event === 'findrelation'){
				 obj.tr.find('i[class="layui-anim layui-icon"]').trigger("click");//选中单选按钮
				 radioLeft=data;
				 radioRight='';
				 lastClickRadio='left';
				 selectRelation('left',$,table);
			 }
		 });	
		 table.on('row(drugruleTable)', function(obj){
			 var data = obj.data;
			
				 obj.tr.find('i[class="layui-anim layui-icon"]').trigger("click");
				 radioLeft=data;
				 radioRight='';
				 lastClickRadio='left';
				 selectRelation('left',$,table);
			 
		 });
		 //监听right表行点击
		 table.on('tool(drugruleTablecity)', function(obj){
			 var data = obj.data;
			 if(obj.event === 'findrelation'){
				 obj.tr.find('i[class="layui-anim layui-icon"]').trigger("click");
				 radioRight=data;
				 radioLeft='';
				 lastClickRadio='right';
				 selectRelation('right',$,table);
			 }
		 });
		 table.on('row(drugruleTablecity)', function(obj){
			 var data = obj.data;
			
				 radioRight=data;
				 //radioLeft='';
				 lastClickRadio='right';
				 radioRightAll=layui.table.checkStatus('drugruleTablecity').data;
				 //console.log(radioRightAll);
				 //selectRelation('right',$,table);
			 
		 });
		 hideButtonStatic();//静态按钮权限
		 //单击行勾选checkbox事件
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
		//添加事件
		var active = {
				relation: function(){//关联
					if((!radioLeft)||(!radioRightAll)){
						layer.msg('请选择数据!');
						return;
					}
					var url=$WEB_ROOT_PATH+"/dhccApi/cityrelation/cityRelation/save";
					var cityCodeLeft=radioLeft.cityCode;
					var cityNameLeft=radioLeft.cityName;
					var itemCodeLeft=radioLeft.itemCode;
					var itemNameLeft=radioLeft.itemName;
					var countTrue=0;
					var countFalse=0;
					for(var i = 0; i < radioRightAll.length; i++) {    
				        var cityCodeRight=radioRightAll[i].cityCode;
						var cityNameRight=radioRightAll[i].cityName;
						var itemCodeRight=radioRightAll[i].itemCode;
						var itemNameRight=radioRightAll[i].itemName;
						if(cityCodeLeft==cityCodeRight && itemCodeLeft==itemCodeRight ){
							layer.msg('是同一条数据,不能关联');
							return;
						}
						var field={'cityRelation.cityCodeLeft':cityCodeLeft,'cityRelation.cityNameLeft':cityNameLeft,'cityRelation.itemCodeLeft':itemCodeLeft,'cityRelation.itemNameLeft':itemNameLeft,'cityRelation.cityCodeRight':cityCodeRight,'cityRelation.cityNameRight':cityNameRight,'cityRelation.itemCodeRight':itemCodeRight,'cityRelation.itemNameRight':itemNameRight,'cityRelation.type':3};
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								countTrue++;
								layer.msg("关联成功");
								if((countTrue+countFalse)==radioRightAll.length && countFalse>0){
									layer.msg("存在"+countFalse+"条是已关联数据。其他"+countTrue+"条数据关联成功！");
								}
							}else{
								countFalse++;
								if((countTrue+countFalse)==radioRightAll.length){
									layer.msg("存在"+countFalse+"条是已关联数据。其他"+countTrue+"条数据关联成功！");
								}
							}
						});
				    };
				}
				,breakrelation: function(){//取消关联
					if((!radioLeft)||(!radioRightAll)){
						layer.msg('请选择数据!');
						return;
					}
					var url=$WEB_ROOT_PATH+"/dhccApi/cityrelation/cityRelation/deleteMine";
					var cityCodeLeft=radioLeft.cityCode;
					var cityNameLeft=radioLeft.cityName;
					var itemCodeLeft=radioLeft.itemCode;
					if(radioRightAll.length>1){
						layer.msg('一次只能解除一条关联数据!');
						return;
					}
					var cityCodeRight=radioRightAll[0].cityCode;
					var cityNameRight=radioRightAll[0].cityName;
					var itemCodeRight=radioRightAll[0].itemCode;
					var field={'cityRelation.cityCodeLeft':cityCodeLeft,'cityRelation.cityNameLeft':cityNameLeft,'cityRelation.itemCodeLeft':itemCodeLeft,'cityRelation.cityCodeRight':cityCodeRight,'cityRelation.cityNameRight':cityNameRight,'cityRelation.itemCodeRight':itemCodeRight,'cityRelation.type':3};
					layer.confirm('确定要解除该条数据关联？', function(index){
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('解除关联成功!');
								//刷新
								if(lastClickRadio=='left'){
									selectRelation('left',$,table);
								}else if(lastClickRadio=='right'){
									selectRelation('right',$,table);
								}else{
									layer.msg('请点击要操作数据上《关联查询》按钮，手动刷新!');
								}
							}else if(inFlag==1){
								layer.msg('两条数据不存在关联关系！');
								return false;
							}else{
								layer.msg('解除关联失败');
								return false;
							}
						});
					});
					
				},showrelation: function(){
			        layer.open({
				          type: 2
				          ,title:'耗材版本对照关系'
				          ,content: $WEB_ROOT_PATH+'/consumableRelation/showRelation'
				          ,maxmin: true
				          ,area: ['950px', '480px']
			              ,success: function(layero, index){
			        	  /*var iframeWindow = window['layui-layer-iframe'+ index];
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));*/
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
	  });
/*提出来的函数*/
	//初始化渲染table
	function loadDate(url,$,form,table,elem,elemn,cityCode,type){
		 table.render({
		    	elem: elem
		            ,url: ''
		            /*,cellMinWidth: 80*/
		            ,height: document.documentElement.clientHeight-100
		            ,cols: [[
		            	{type:type,title: '选择'}
		            	,{type: 'numbers',width:40, title: '序号' }
		            	,{field:'id', title: 'ID', sort: true, hide:true} 
		            	,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:110,hide:rowOperate(['consumable-relation-cxgl'])}
			            ,{field:'cityName', width:90,title: '所属城市',align:'center',templet: function(d){
		                	    var codex =d.cityName;
			                	if(codex==null||codex==""){
			                		codex="DHCC";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
			              ,{field:'itemCode',width:120,align:'center', title: '编码'}
			              ,{field:'itemName',width:160,align:'center', title: '名称'}
			              ,{field:'dirType',width:60,align:'center', title: '类别',templet: function(d){
		                	    var codex =d.dirType;
			                	if(codex==null||codex==""){
			                		codex="";
			                	}
			                	//alert(codex);
			                	if(codex=="1"){
			                		codex="药品";
			                	}
			                	if(codex=="2"){
			                		codex="诊疗";
			                	}
			                	if(codex=="3"){
			                		codex="耗材";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
			              ,{field:'itemType', width:60,align:'center',title: '分类'}
			              ,{field:'sexFlag',width:90,align:'center', title: '性别限制' ,templet: function(d){
		                	    var codex =d.sexFlag;
			                	if(codex==null||codex==""){
			                		codex="";
			                	}
			                	//alert(codex);
			                	if(codex=="0"){
			                		codex="无限制";
			                	}
			                	if(codex=="1"){
			                		codex="限男性";
			                	}
			                	if(codex=="2"){
			                		codex="限女性";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }
		                }
			              ,{field:'indicationFlag', width:100,align:'center',title: '适应症标志',templet: function(d){
		                	    var codex =d.indicationFlag;
			                	if(codex==null||codex==""){
			                		codex="";
			                	}
		    
			                	if(codex=="1"){
			                		codex="是";
			                	}
			                	if(codex=="0"){
			                		codex="否";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
			              ,{field:'indicationComments',width:100,align:'center',title: '适应症内容'}
			              ,{field:'rescueFlag',width:100,align:'center',title: '限抢救使用',templet: function(d){
		                	    var codex =d.rescueFlag;
			                	if(codex==null||codex==""){
			                		codex="";
			                	}
		
			                	if(codex=="1"){
			                		codex="是";
			                	}
			                	if(codex=="0"){
			                		codex="否";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
			              ,{field:'contraindicationFlag',width:90,align:'center', title: '禁忌标志',templet: function(d){
		                	    var codex =d.contraindicationFlag;
			                	if(codex==null||codex==""){
			                		codex="";
			                	}

			                	if(codex=="1"){
			                		codex="是";
			                	}
			                	if(codex=="0"){
			                		codex="否";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
			              ,{field:'contraindicationComments',width:90,align:'center', title: '禁忌内容'}
			              ,{field:'insuranceMark',width:90,align:'center', title: '险种标志'}
			              ,{field:'orgLevel', width:90,align:'center',title: '医院等级'}
			              ,{field:'personType',width:100,align:'center', title: '适用：儿童',templet: function(d){
		                	    var codex =d.personType;
			                	if(codex==null||codex==""){
			                		codex="";
			                	}
			                	if(codex=="1"){
			                		codex="是";
			                	}
			                	if(codex=="0"){
			                		codex="否";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
			              ,{field:'diagType',width:90,align:'center', title: '就医类型'
			            	  ,templet: function(d){
			                	    var codex =d.diagType;
				                	if(codex==null||codex==""){
				                		codex="";
				                	}
				       
				                	if(codex=="1"){
				                		codex="门诊";
				                	}
				                	if(codex=="0"){
				                		codex="住院";
				                	}
				                    return '<span >'+ codex +'</span>'
			                  }}
			              ,{field:'orgTypeLevel', width:120,align:'center',title: '医疗机构类型'}
			              ,{field:'consumableType',width:90,align:'center', title: '耗材分类'}
			              ,{field:'itemStandard',width:90,align:'center', title: '耗材规格'}
			              ,{field:'itemUnit',width:60,align:'center', title: '单位'}
			              ,{field:'invoiceProject',width:90,align:'center', title: '发票项目'}     
			              ,{field:'beginTime',width:90,align:'center', title: '起始日期'}
			              ,{field:'endTime', width:90,align:'center',title: '终止日期'}
			              ,{field:'isMedicare',width:90,align:'center', title: '医保项目'
			            	  ,templet: function(d){
			                	    var codex =d.isMedicare;
				                	if(codex==null||codex==""){
				                		codex="";
				                	}
				                	//alert(codex);
				                	if(codex=="1"){
				                		codex="是";
				                	}
				                	if(codex=="0"){
				                		codex="否";
				                	}
				                    return '<span >'+ codex +'</span>'
			                  }}
			              ,{field:'validFlag',width:90,align:'center', title: '是否有效'
			            	  ,templet: function(d){
			                	    var codex =d.validFlag;
				                	if(codex==null||codex==""){
				                		codex="";
				                	}
				                	//alert(codex);
				                	if(codex=="0"){
				                		codex="否";
				                	}
				                	if(codex=="1"){
				                		codex="是";
				                	}
				                    return '<span >'+ codex +'</span>'
			                  }}
			              ,{field:'comments',width:60,align:'center', title: '备注'}
		         
		            ]]
		            ,page: true
		            ,done(res){
		            	var result=res.data;
		            	//console.log(result);
		            	var arrays=new Array();
		            	for(var i=0;i<result.length;i++){
		            		if(result[i].checked==true){
		            			arrays.push(result[i]);
		            		}
		            	}
		            	radioRightAll=arrays;
		            	//console.log(radioRightAll);
		            }
		          });
		//执行reload().cityCode=DHCC
		 var field={"consumableInfo.cityCode":cityCode};
		 layui.table.reload(elemn,{
			 url: url,
			 height: document.documentElement.clientHeight-100,
	         where: field
	     });
	
	}
	//监听搜索
	function formOn(url,$,form,table,submitTable,elemn){
		form.on(submitTable, function(data){
			var field = data.field;    	
			//console.log(field);
			//执行重载
			layui.table.reload(elemn, {
			    url: url,
				where: field
			});
			//查询后，清除之前数据选中状态
			if(elemn=='drugruleTable'){
				radioLeft='';
			}else if(elemn=='drugruleTablecity'){
				radioRight='';
			}
		});
	}
	//关联查询
	function selectRelation(direction,$,table){
		
		//debugger;
		var url=$WEB_ROOT_PATH+"/dhccApi/cityrelation/cityRelation/listVo";
		var data=(direction=='left')?radioLeft:radioRight;
		if(!data){
			layer.msg('请选择数据!');
			return;
		}
		var elemn=(direction=='left')?'drugruleTablecity':'drugruleTable';
		
		var fieldLeft={"cityRelation.cityCodeLeft":data.cityCode,
				'cityRelation.itemCodeLeft':data.itemCode,
				'cityRelation.itemNameLeft':data.itemName,
				'cityRelation.type':3};
		var fieldRight={"cityRelation.cityCodeRight":data.cityCode,
				'cityRelation.itemCodeRight':data.itemCode,
				'cityRelation.itemNameRight':data.itemName,
				'cityRelation.type':3};
		var field=(direction=='left')?fieldLeft:fieldRight;
		 layui.table.reload(elemn,{
			 url: url,
	         where: field
	     });
		 //每次查询管理后，把对方选中数据清空。
		 if(direction=='left'){
			 radioRight='';
		 }else{
			 radioLeft='';
		 }
	}
	//下拉框不冲突选择
	function noreplace(form,$,filterlay,selectedId,id){
		 form.on(filterlay, function(data){
			 var value=$(data.elem).find("option:selected").val();
			 var value2= $(selectedId).val();
			 if(value==value2){
				 layer.msg('冲突，请选择其他城市！');
				 $(id).find("option[value ='']").prop("selected",true);
			 }
			form.render('select');
		 });
	}
	
	
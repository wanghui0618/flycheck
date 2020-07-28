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
		elem: '#medicalrangeinfo'
		,url: $WEB_ROOT_PATH+'/dhccApi/rangeinfo/rangeinfo/listVo'
		,height: tableHeight
		,cellMinWidth: 80
		,where: {ilegalChild: '1'}
	    ,cols: [[
		 {type: 'numbers', title: '序号' }
		,{field:'id', width:80,hide:true,title: '编号',align:"center"}
		,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:230	,align:"center",hide:rowOperate(['/rangeinfo/edit','/rangeinfo/delete','/rangeinfo/look'])}
		,{field:'orgCode', width:100,hide:true,title: '机构编号'}
		,{field:'orgName', width:300,title: '医疗机构',align:"center",templet: function(d){
    	    var codex =d.orgName;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'cityName', width:120,title: '所属城市',hide:true,align:'center',templet: function(d){
    	    var codex =d.cityName;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'handdingInsCode', width:90,title: '经办机构ID',hide:true}
		,{field:'orgType',width:90, title: '机构类型',align:"center",templet: function(d){
    	    var codex =d.orgType;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }else if(codex == '1'){
 			   codex='医院'
 		   }else if(codex == '2'){
 			   codex='药店'
 		   }else if(codex == '0'){
 			   codex='门诊'
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'netContact',width:140, title: '互联网结算开通',templet:function(d){
 		   var codex = d.netContact;
		   if(codex == "1"){
			   codex="开通";
		   }else if(codex == "0"){
			   codex="未开通";
		   }else if(codex == "2"){
			   codex="停止"
		   }else if(codex == ""){
			   codex="-";
		   }else if(codex == "null"){
			   codex="-";
		   }else if(codex == null){
			   codex="-";
		   }else{
			   codex=codex;
		   }
		   return '<span >'+ codex +'</span>';
	   },align:"center"}
		,{field:'inhosBalance',width:120, title: '住院结算开通',templet:function(d){
	 		   var codex = d.inhosBalance;
			   if(codex == "1"){
				   codex="开通";
			   }else if(codex == "2"){
				   codex="停止"
			   }else if(codex == "0"){
				   codex="未开通";
			   }else if(codex == ""){
				   codex="-";
			   }else if(codex == "null"){
				   codex="-";
			   }else if(codex == null){
				   codex="-";
			   }else{
				   codex=codex;
			   }
			   return '<span >'+ codex +'</span>';
		   },align:"center"}
		,{field:'outpatientBalance', width:160,title: '门诊大病结算开通',templet:function(d){
	 		   var codex = d.outpatientBalance;
			   if(codex == "1"){
				   codex="开通";
			   }else if(codex == "2"){
				   codex="停止"
			   }else if(codex == "0"){
				   codex="未开通";
			   }else if(codex == ""){
				   codex="-";
			   }else if(codex == "null"){
				   codex="-";
			   }else if(codex == null){
				   codex="-";
			   }else{
				   codex=codex;
			   }
			   return '<span >'+ codex +'</span>';
		   },align:"center"}
		,{field:'birthBalance',width:120, title: '生育结算开通',templet:function(d){
	 		   var codex = d.birthBalance;
			   if(codex == "1"){
				   codex="开通";
			   }else if(codex == "2"){
				   codex="停止"
			   }else if(codex == "0"){
				   codex="未开通";
			   }else if(codex == ""){
				   codex="-";
			   }else if(codex == "null"){
				   codex="-";
			   }else if(codex == null){
				   codex="-";
			   }else{
				   codex=codex;
			   }
			   return '<span >'+ codex +'</span>';
		   },align:"center"}
		,{field:'cardBanlance',width:120, title: '刷卡结算开通',templet:function(d){
	 		   var codex = d.cardBanlance;
			   if(codex == "1"){
				   codex="开通";
			   }else if(codex == "2"){
				   codex="停止"
			   }else if(codex == "0"){
				   codex="未开通";
			   }else if(codex == ""){
				   codex="-";
			   }else if(codex == "null"){
				   codex="-";
			   }else if(codex == null){
				   codex="-";
			   }else{
				   codex=codex;
			   }
			   return '<span >'+ codex +'</span>';
		   },align:"center"}
		,{field:'insBalanceDate',width:180, title: '定点协议结算日期',align:"center"}
		,{field:'insBalanceStopDate',width:220, title: '定点协议终止或暂停结算日期',align:"center"}
		/*,{field:'createDate', title: '创建时间'}*/
		/*,{field:'updateDate',width:120, title: '更新时间'}*/
		]]
	,page: true
	,done:function(res, curr, count){    //res 接口返回的信息
		    $("[data-field = 'insuranceType']").children().each(function(){
		        if($(this).text() == '1'){
		            $(this).text("医疗");
		        }else if($(this).text() == '2'){
		             $(this).text("工伤");
		        }else if($(this).text() == '3'){
			         $(this).text("生育");
			    }
		    })
		}
		
	});
	hideButtonStatic();//按钮权限
	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		console.log(field);
		//执行重载
		layui.table.reload('medicalrangeinfo', {
			where: field
		});
	});

	//添加事件
	var active = {
			add: function(){

			//添加记录
			layer.open({
				type: 2
				,title: '保存医疗机构服务范围信息'
				,content: $WEB_ROOT_PATH+'/rangeinfoinfo/rangeinfoinfo'
				,maxmin: true
				,area: ['800px', '420px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//加载select下拉option
					iframeWindow.loadSelect();
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/rangeinfo/rangeinfo/saveMine";
					console.log(field);
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('保存成功!');
							//后台成功后，静态更新表格中的数据
							layer.close(index); //关闭弹层
							table.reload('medicalrangeinfo'); //数据刷新
						}else if(inFlag==1){
							layer.msg('已经存在该条threeDirectoryCode+cityCode!');
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
	table.on('tool(medicalrangeinfo)', function(obj){
		var data = obj.data;
		if(obj.event === 'delete'){
			layer.confirm('确定要删除该条数据？', function(index){
				//执行 Ajax 后重载
				var url=$WEB_ROOT_PATH+"/dhccApi/rangeinfo/rangeinfo/delete";
				$.post(url,{'rangeInfo.id':data.id},function(result){
					table.reload('medicalrangeinfo');
					layer.msg('删除成功！');
				});
			});

		}else if(obj.event === 'update'){
			//修改
			layer.open({
				type: 2
				,title: '修改医疗机构服务范围信息'
					,content: $WEB_ROOT_PATH+'/rangeinfoinfo/rangeinfoinfo'
					,maxmin: true
					,area: ['800px', '420px']
			,btn: ['确定', '取消']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
				//向此iframe层方法 传递参数
				iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
			}
			,yes: function(index, layero){
				var iframeWindow = window['layui-layer-iframe'+ index]
				,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);

				//监听提交
				iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/rangeinfo/rangeinfo/saveMine";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('修改成功!');
							//后台成功后，静态更新表格中的数据
							table.reload('medicalrangeinfo'); //数据刷新
							layer.close(index); //关闭弹层
						}else{
							layer.msg('已经存在该条threeDirectoryCode+cityCode!');
							return false;
						}
					});
				});  
				submit.trigger('click');
			}
			}); 
		}else if(obj.event === 'view'){
			 //修改
    	    layer.open({
		          type: 2
		          ,title: '查看医疗机构服务范围信息'
		          ,content: $WEB_ROOT_PATH+'/rangeinfoinfo/rangeinfoinfo-view'
		          ,maxmin: true
		          ,area: ['800px', '400px']
		          ,btn: ['关闭']
    	          ,success: function(layero, index){
	        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
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
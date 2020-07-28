//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','form'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	var laydate = layui.laydate;
	  laydate.render({
	    	elem: '#paymentDate'
	    		,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: true
	  });
	

	table.render({
		elem: '#cityOrgTable'
		,url: $WEB_ROOT_PATH+'/dhccApi/scheduledtask/scheduledTask/listVo'
		,height: tableHeight
		,cellMinWidth: 100
	    ,cols: [[
		 {type: 'numbers', title: '序号' }
		,{field:'id', width:80,hide:true,title: '编号'}
		,{title:'操作', align:'center',templet: function (d) {
			if(d.taskKey=='medicalSysAduitTask'){
				return '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="start"><i class="layui-icon layui-icon-play"></i>启动</a>'+
				'<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>'+
				/*'<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>'+*/
				'<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="playOnce"><i class="layui-icon layui-icon-play"></i>立即执行一次</a>'+	
				'<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="playOnceException"><i class="layui-icon layui-icon-play"></i>异常数据重审</a>';
			}else {
                if (d.taskKey=='synchronizeUnit') {
                    return '<a class="layui-btn  layui-btn-xs" lay-event="Synchronize"><i class="layui-icon layui-icon-play"></i>同步</a>'
                } if (d.taskKey=='addHospUsers') {
                    return '<a class="layui-btn  layui-btn-xs" lay-event="addHospUsers"><i class="layui-icon layui-icon-play"></i>批量添加医院用户</a>'
                }
                else {
                    return '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="start"><i class="layui-icon layui-icon-play"></i>启动</a>' +
                        '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
                        /*'<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>';*/
                }
            }
			}, width: 440}
		,{field:'isStart',align: 'center', title: '当前状态',templet: function(d){
    	    var codex =d.isStart;
        	if(codex=="0"){
        		codex="启动";
        	}else if(codex=="1"){
        		codex="停止";
        	}else{
        		codex=codex;
        	}
            return '<span >'+ codex +'</span>'
      }}
		,{field:'taskKey',align: 'center', width:200,title: '任务名称'}
		,{field:'taskCron', align: 'center',title: '时间间隔表达式'}
		,{field:'initStartFlag',align: 'center', title: '是否开机启动',templet: function(d){
    	    var codex =d.initStartFlag;
        	if(codex=="0"){
        		codex="否";
        	}else if(codex=="1"){
        		codex="是";
        	}else{
        		codex=codex;
        	}
            return '<span >'+ codex +'</span>'
      }}
		,{field:'taskDesc',align: 'center',width:270, title: '任务描述'}
		]]
	,page: true
	});

	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//执行重载
		layui.table.reload('cityOrgTable', {
			where: field
		});
	});

	//添加事件
	var active = {
			add: function(){

			//添加记录
			layer.open({
				type: 2
				,title: '新增定时任务'
				,content: $WEB_ROOT_PATH+'/scheduledtask/scheduledTaskinfo'
				,maxmin: true
				,area: ['750px', '360px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//加载select下拉option
					//iframeWindow.loadSelect();
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/saveMine";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('保存成功!');
							//后台成功后，静态更新表格中的数据
							layer.close(index); //关闭弹层
							table.reload('cityOrgTable'); //数据刷新
						}else if(inFlag==1){
							layer.msg('已经存在该条记录');
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
	table.on('tool(cityOrgTable)', function(obj){
		var data = obj.data;
		console.log(obj)
		if(obj.event === 'delete'){
			if(data.isStart=='1'){
				layer.confirm('确定要删除该条数据？', function(index){
					//执行 Ajax 后重载
					var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/delete";
					$.post(url,{'scheduledTask.id':data.id},function(result){
						table.reload('cityOrgTable');
						layer.msg('删除成功！');
					});
				});
			}else if(data.isStart=='0'){
				layer.msg('请先停止该任务后再删除！');
			}
		}else if(obj.event === 'update'){
			if(data.isStart=='1'){
				//修改
				layer.open({
					type: 2
					,title: '编辑定时任务'
						,content: $WEB_ROOT_PATH+'/scheduledtask/scheduledTaskinfo'
						,maxmin: true
						,area: ['750px', '360px']
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
						var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/saveMine";
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('修改成功!');
								//后台成功后，静态更新表格中的数据
								table.reload('cityOrgTable'); //数据刷新
								layer.close(index); //关闭弹层
							}else{
								layer.msg('已经存在该条记录!');
								return false;
							}
						});
					});  
					submit.trigger('click');
				}
				}); 
			}else if(data.isStart=='0'){
				layer.msg('请先停止该任务后再编辑！');
			}
		}else if(obj.event === 'start'){ 
			if(data.isStart=='1'){
				var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/start?scheduledTask.taskKey="+data.taskKey;
				$.get(url,function(result){
					if(result=='start success'){
						layer.msg('启动成功！');
						updateIsStart($,data.taskKey,'0',table);
					}
				});
			}else {
                    var url = $WEB_ROOT_PATH + "/dhccApi/scheduledtask/scheduledTask/stop?scheduledTask.taskKey=" + data.taskKey;
                    $.get(url, function (result) {
                        if (result == 'stop success') {
                            layer.msg('停止成功！');
                            updateIsStart($, data.taskKey, '1', table);

                        }
                    });
                }
		}else if(obj.event === 'stop'){
			if(data.isStart=='0'){
				var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/stop?scheduledTask.taskKey="+data.taskKey;
				$.get(url,function(result){
					if(result=='stop success'){
						layer.msg('停止成功！');
						updateIsStart($,data.taskKey,'1',table);
						
					}
				});
			}else{
				layer.msg('请先停止该任务！');
			}
		}else if(obj.event === 'restart'){
			if(data.isStart=='1'){
				var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/restart?scheduledTask.taskKey="+data.taskKey;
				$.get(url,function(result){
					if(result=='restart success'){
						layer.msg('重启成功！');
						updateIsStart($,data.taskKey,'0',table);
					}
				});
			}else{
				layer.msg('该任务已经启动！');
			}
		}else if(obj.event==='playOnce'){
			if(data.isStart=='1'){
				  updateIsStart($,data.taskKey,'0',table);
				  var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/playOnce";
				  var date = $("#paymentDate").val();
				  var field={"scheduledTask.taskKey":data.taskKey,"balanceDate":date};
				  $.post(url,field,function(result){
						layer.msg(result+" 测试阶段");
						updateIsStart($,data.taskKey,'1',table);
				  });
				/*layer.open({
					type: 2
					,title: '立即执行一次'
					,content: $WEB_ROOT_PATH+'/scheduledTask/SysMedicalDataSelect'
					,maxmin: true
					,area: ['650px', '530px']
					,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//向此iframe层方法 传递参数
					iframeWindow.child(data.taskKey);调用弹出窗口，填充该行数据到修改表单
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);

					//监听提交
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
						 var field = data.field; //获取提交的字段
						  console.log(field);
						  console.log(field.paymentDate);
						  //提交 Ajax后台
						  updateIsStart($,data.taskKey,'0',table);
						  var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/playOnce";
						  $.post(url,field,function(result){
								layer.msg(result+" 测试阶段");
								updateIsStart($,data.taskKey,'1',table);
						  });
						  layer.close(index); //关闭弹层
					});  
					submit.trigger('click');
				}
				}); */
				
			}else{
				layer.msg("请先停止任务！");
			}
		}else if(obj.event=='playOnceException'){
			if(data.isStart=='1'){
				
			/*	layer.open({
					type: 2
					,title: '立即执行一次'
					,content: $WEB_ROOT_PATH+'/scheduledTask/SysMedicalDataSelect'
					,maxmin: true
					,area: ['650px', '530px']
					,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//向此iframe层方法 传递参数
					iframeWindow.child(data.taskKey);调用弹出窗口，填充该行数据到修改表单
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);

					//监听提交
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
						 var field = data.field; //获取提交的字段
						  console.log(field);
						  console.log(field.paymentDate);
						  field.isException='yccs';
						  console.log(field);
						  //提交 Ajax后台
						 updateIsStart($,data.taskKey,'0',table);
						  var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/playOnce";
						  $.post(url,field,function(result){
								layer.msg(result+" 测试阶段");
								updateIsStart($,data.taskKey,'1',table);
						  });
						  layer.close(index); //关闭弹层
					});  
					submit.trigger('click');
				}
				}); */
					updateIsStart($,data.taskKey,'0',table);
				  var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/playOnce";
				  var date = $("#paymentDate").val();
				  var field={"scheduledTask.taskKey":data.taskKey,"balanceDate":date,"isException":'yccs'};
				  $.post(url,field,function(result){
						layer.msg(result+" 测试阶段");
						updateIsStart($,data.taskKey,'1',table);
				  });
				/*updateIsStart($,data.taskKey,'0',table);
				var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/playOnce?scheduledTask.taskKey="+data.taskKey;
				$.get(url,{"isException":'yccs'},function(result){
					layer.msg(result+" 测试阶段");
					updateIsStart($,data.taskKey,'1',table);
				});*/
			}else
                {
                    layer.msg("请先停止任务！");
                }
		}else if (obj.event=='Synchronize') {
            layer.confirm('确定同步？', function () {
                //updateIsStart($,data.taskKey,'0',table);
               var url = $WEB_ROOT_PATH + '/dhccApi/unit/unit/insertDataAutho';
                $.post(url,function (result) {
                    if (result=="success"){
                        layer.msg('同步成功');
                     //   updateIsStart($,data.taskKey,'1',table);
                    }else{
                        layer.msg('同步失败');
                      //  updateIsStart($,data.taskKey,'1',table);
                    }
                });
            });
        }else if(obj.event=='addHospUsers') {
            layer.confirm('确定批量添加医院用户？', function () {
                //updateIsStart($,data.taskKey,'0',table);
                var url = $WEB_ROOT_PATH + '/dhccApi/user/user/creatHospUsers';
                $.post(url,function (result) {
                    if (result=="success"){
                        layer.msg('批量添加成功');
                        //   updateIsStart($,data.taskKey,'1',table);
                    }else{
                        layer.msg('批量添加失败');
                        //  updateIsStart($,data.taskKey,'1',table);
                    }
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
//更新启动状态
function updateIsStart($,taskKey,isStart,table){
	var url=$WEB_ROOT_PATH+"/dhccApi/scheduledtask/scheduledTask/updateIsStart";
	var field = {"scheduledTask.taskKey":taskKey,"scheduledTask.isStart":isStart};
	$.post(url,field,function(result){
		table.reload('cityOrgTable',{
			done:function(res){
				var index=-1;
   	            //分类显示中文名称
   	            $("[data-field='taskKey']").children().each(function () {
	   	            index++;
	   	            if ($(this).text() == taskKey) {
	   	            	console.log($(this).text());
	   	            	
	   	            	if(isStart=='0'){
	   	            		$('tr').eq(index).children().eq(2).children().eq(0).children().eq(0).html("<i class='layui-icon layui-icon-pause'></i>停止");
	   	            		/*if(taskKey=='medicalSysAduitTask'){
		   	            		console.log($('tr').eq(index).children().eq(2).children().eq(0).children().eq(3).html("<i class='layui-icon layui-icon-pause'></i>单次停止"));
		   	            	}*/
	   	            	}else{
	   	            		$('tr').eq(index).children().eq(2).children().eq(0).children().eq(0).html('<i class="layui-icon layui-icon-play"></i>启动');
	   	            		/*if(taskKey=='medicalSysAduitTask'){
		   	            		console.log($('tr').eq(index).children().eq(2).children().eq(0).children().eq(3).html('<i class="layui-icon layui-icon-play"></i>立即执行一次'));
		   	            	}*/
	   	            	}
	   	            }
   	            });
				$('tr').eq(1).css("background-color","#C0C0C0");
			}
		}); //数据刷新
	});
}
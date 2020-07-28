//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','layer'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
    var layer = layui.layer;

	table.render({
		elem: '#policyTable'
		,url: $WEB_ROOT_PATH+'/app/js/assistDecision/data.json'
		,height: tableHeight
		,cellMinWidth: 80
	    ,cols: [[
		 {type: 'numbers', title: '序号' }
		,{field:'id', width:80,hide:true,title: '编号'}
		,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:320}
		,{field:'forzh1', title: '报告状态', width: 100
            ,templet: function(d){
          	    var id =d.id;
          	  return '<span style="color: green;">已生成</span>'
            }
          }
		,{field:'simulateName', width:200,title: '主题'}
		,{field:'simulateStartDate',width:120, title: '开始时间'}
		,{field:'simulateEndDate',width:120, title: '结束时间'}
		,{field:'simulateDesc', title: '描述'}
		]]
	,page: true
	});

	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//执行重载
		layui.table.reload('policyTable', {
			where: field
		});
	});

	//添加事件
	var active = {
			weihu: function(){
				//添加记录
				layer.open({
					type: 2
					,title: '现行政策维护'
					,content: $WEB_ROOT_PATH+'/assistDecision/policyBase'
					,maxmin: true
					,area: ['950px', '550px']
					,btn: ['确定', '取消']
					,success: function(layero, index){
						var iframeWindow = window['layui-layer-iframe'+ index];
					}
					/*,yes: function(index, layero){
					}*/
				});
			},
			add: function(){
				//添加记录
				layer.open({
					type: 2
					,title: '模拟政策新增'
						,content: $WEB_ROOT_PATH+'/assistDecision/policyAdd'
						,maxmin: true
						,area: ['950px', '550px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
				}
				/*,yes: function(index, layero){
				}*/
				});
			}
	};

	//监听行点击
	table.on('tool(policyTable)', function(obj){
		var data = obj.data;
		if(obj.event === 'addRs'){
			layer.alert("生成成功");
		}else if(obj.event === 'lookRs'){
			layer.open({
				type: 2
				,title: '模拟政策报告预览'
					,content: $WEB_ROOT_PATH+'/assistDecision/policyReport'
					,maxmin: true
					,area: ['1100px', '550px']
			,btn: ['关闭']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
				//向此iframe层方法 传递参数
				iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
			}
			}); 
		}else if(obj.event === 'update'){
			layer.open({
				type: 2
				,title: '模拟政策编辑'
					,content: $WEB_ROOT_PATH+'/assistDecision/policyAdd'
					,maxmin: true
					,area: ['950px', '550px']
			,btn: ['确定', '取消']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
				//向此iframe层方法 传递参数
				iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
			}
			/*,yes: function(index, layero){
			}*/
			}); 
		}
			
	});

	//按钮事件绑定底层方法-勿动
	$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});

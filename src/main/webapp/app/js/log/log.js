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
	    	elem: '#logTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/log/log/listLogVo' 
	            ,cellMinWidth: 80
	            ,height: tableHeight
	            ,cols: [[
	               {type: 'numbers', align: 'center',title: '序号' }
	              ,{field:'id', title: 'ID', hide:true}
	              ,{field:'loginName',align: 'center', title: '用户名'}
	              ,{field:'name',align: 'center', title: '姓名'}
	              ,{field:'phone',align: 'center', title: '手机号'}
	              ,{field:'email',align: 'center', title: '邮箱'}
	              ,{field:'unitName',align: 'center', title: '组织名称'}
	              ,{field:'roleName',align: 'center', title: '角色名称'}
	              ,{field:'loginTime',align: 'center', title: '登录时间'}
	              ,{field:'loginoutTime',align: 'center', title: '退出时间'}
	              ,{field:'minutes',align: 'center', title: '在线时长'}
	            ]]
	            ,page: true
	          });
	      
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('logTable', {
	            where: field
	        });
	    });
	  
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
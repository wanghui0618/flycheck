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
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/druglnstmction/drugLnstmction/listVo1'
	            ,cellMinWidth: 80
	             ,height: 415
	            ,where: {  'drugLnstmction.id' : id  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'mainIngredients', title: '主要成分'}
		              ,{field:'use', title: '用途'}
		              ,{field:'dosage', title: '用法用量'}
		              ,{field:'product' , title: '产品说明'}
		             
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
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  

	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
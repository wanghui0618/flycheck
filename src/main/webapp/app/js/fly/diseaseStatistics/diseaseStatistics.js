layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	  
	    table.render({
	    	elem: '#diseaseStatistics'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/diseaseStatistics/diseaseStatistics/list'
	        ,cellMinWidth: 50
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
			 ,{field:'INHOSDIAG',align:'center', title: '诊断'}
	         ,{field:'CASES',align:'center', title: '病例数'}
		     ,{field:'TOTALCOST', align:'center',title: '总费用（元）' }
			 ,{field:'AVGCOST',align:'center', title: '次均费用（元）' }
			 ,{field:'FUNDCOST', align:'center',title: '医保报销金额（元）' }
			 ,{field:'AVGFUNDCOST',align:'center', title: '医保报销次均金额（元）' }
	        ]]
	          ,page: true
	          });
	    
	   
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
			var field = data.field;
			//执行重载
	        layui.table.reload('diseaseStatistics', {
	            where: field
	        });
	    });
	  
	   
	  
	    
	  
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
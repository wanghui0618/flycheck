//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','element'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/singleDisease'
	            ,cellMinWidth: 80
	           // ,where: {  }
	            ,height: tableHeight
	            ,cols: [[
	            	{field:'id', title: 'ID', sort: true, hide:true}
	            	,{title: '序号',templet: '#xuhao'}
	            	  ,{field: 'illName', title: '疾病名称' }
	            	  ,{field: 'illNumber', title: '病例数' }
	            	  ,{field: 'totalCost', title: '总费用' }
	            	  ,{field: 'pjCost', title: '次均费用' }
	            	  ,{field: 'totalCost1', title: '三级医院费用'}
	            	  ,{field: 'totalCost2', title: '二级医院费用'}  
	            	  ,{field: 'totalCost3', title: '一级医院费用'} 
	            	  ,{field: 'totalCost4', title: '社区医院费用'}
	            	  ,{field: 'totalCost4', title: '社区医院费用'} 
	            	  ,{field: 'totalPay', title: '医保报销金额'}
	            	  ,{field: 'pjPay', title: '医保报销次均金额'}
	            ]]
	     ,page: true
	          });
	    
	    //监听搜索
	    form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	            ,page: {
	                curr: 1 //重新从第 1 页开始
	              }
	        });
	    });
	    layui.use('laydate', function() {
			var laydate = layui.laydate;
			//年范围
			laydate.render({
				elem: '#test10'
				,value: nowYear()
				,max:nowYear()
				,type: 'year'
			});
		});
	    function nowYear(){
			var date=new Date();
			var nowYear=date.getFullYear();
			return nowYear.toString();
		}
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
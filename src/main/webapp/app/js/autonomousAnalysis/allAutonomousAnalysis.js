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
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/yearData'
	            ,cellMinWidth: 80
	           // ,where: {  }
	            ,height: tableHeight
	            ,cols: [[
	            	{field:'id', title: 'ID', sort: true, hide:true}
	            	,{title: '序号',templet: '#xuhao',width:'10%'}
	            	  ,{field: 'orgName', title: '医院名' ,width:'22%'}
	            	  ,{field: 'orgCode', title: '医院编码' ,width:'20%'}
	            	  ,{field: 'totalNumber', title: '病例数',width:'10%'}
	            	  ,{field:'totalPnum', title: '住院人数',width:'10%'}
		              ,{field:'avgCost', title: '次均费用',width:'10%'}
		              ,{field:'avgDay', title: '平均住院天数',width:'10%'}
		              ,{field:'drugRatio', title: '药占比',
		            	  templet: function(d){
		            		  var codex = d.totalRatio;
								return codex + "%"
		            	  }}	             

	            ]]
	     ,page: true
	          });
	    
	    //监听搜索
	    form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	    	//console.log(field);
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	    layui.use('laydate', function() {
			var laydate = layui.laydate;
			//年范围
			laydate.render({
				elem: '#test2'
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
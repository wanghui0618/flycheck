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
	            ,url: $WEB_ROOT_PATH+'/dhccApi/flyAvgDay/flyAvgDay/flyAvgDay'
	            ,cellMinWidth: 80
	           // ,where: {  }
	            ,height: tableHeight
	            ,cols: [[
	            	{field:'id', title: 'ID', sort: true, hide:true}
	            	,{title: '序号',templet: '#xuhao',width:'5%'}
	            	  ,{field: 'orgName', title: '医疗机构名称' ,width:'15%'}
	            	  ,{field: 'allDay', title: '年总住院天数' ,width:'10%'}
	            	  ,{field: 'avgStayLength', title: '平均住院天数',width:'10%' }
	            	  ,{field: 'mNum', title: '月均住院人数',width:'10%' }
	            	  ,{field: 'maxStayLength', title: '单人最大住院天数',width:'10%'}  
	            	  ,{field: 'name', title: '单人最大住院天数姓名',width:'13%'} 
	            	  ,{field: 'stayLength', title: '单月最大住院天数',width:'10%'} 
	            	  ,{field: 'mTime', title: '最大住院总天数月份',
	            		  templet: function(d){
	            			  var x= parseInt(d.mTime);
	            			  return x;		            		  
		            	  }}
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
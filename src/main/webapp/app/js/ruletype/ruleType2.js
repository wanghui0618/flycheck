//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','element','form'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table
	    ,element=layui.element

	    table.render({
	    	elem: '#userTable2'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/ruletype/ruleType/listVo'
	            ,cellMinWidth: 80
	            ,height: tableHeight
	            ,cols: [[
	            	   {type: 'numbers',align:"left", title: '序号' }
		              ,{field:'ruleName',align:"left", title: '规则名称'}
		              ,{field:'ruleDetails',align: 'left',  title: '规则解释',templet:function(d){return d.ruleDetails!=null?d.ruleDetails:"-"}}
		            ]],
		            done:function(){
		      			$("ruleName").val()!=null?$("ruleName").val():$("ruleName").val("-");
		      			$("ruleDetails").val()!=null?$("ruleDetails").val():$("ruleDetails").val("-");
		      		  }
		    		,page: true
		          });
	    
	    //监听搜索
			form.on('submit(LAY-org-front-search)', function(data){
				var field = data.field;
				//执行重载
		        layui.table.reload('userTable2', {
		            where: field
		        });
		    });
			
	    hideButtonStatic();//按钮权限   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
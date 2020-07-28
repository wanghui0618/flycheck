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
	            ,url: $WEB_ROOT_PATH+'/dhccApi/child/child/listinfo'
	            /*,cellMinWidth: 80*/
	            ,height: 415
	            ,where: {  billingNo: billing_no ,admissionNo: admission_no ,ilegalChild: '1'}
	            ,cols: [[
	              { type: 'numbers',width:40, title: '序号'  }
	              //,{title:'操作', toolbar: '#table-useradmin-webuser', width:210}
	              ,{field:'ilegalChild', title: '违规?', width: 80, sort: true
	                  ,templet: function(d){
	                	    var codex =d.ilegalChild;
		                	if(codex==null||codex==""){
		                		codex="";
		                	}
		                	if(codex=="1"){
		                		codex="违规";
		                	}
		                	if(codex=="2"){
		                		codex="疑点违规";
		                	}
		                	if(codex=="0"){
		                		codex=" ";
		                	}
		                    return '<span style="color: red;">'+ codex +'</span>'
	                  }
	                }
	              ,{field:'xmlb', title: '类别', width: 100, sort: true
	                  ,templet: function(d){
	                	    var codex =d.xmlb;
		                	if(codex==null||codex==""){
		                		codex="";
		                	}
		                	if(codex=="1"){
		                		codex="药品";
		                	}
		                	if(codex=="2"){
		                		codex="诊疗";
		                	}
		                	if(codex=="3"){
		                		codex="低值耗材";
		                	}
		                	if(codex=="4"){
		                		codex="高值耗材";
		                	}
		                    return '<span style="color: blue;">'+ codex +'</span>'
	                  }
	                }
	             
	              ,{field:'commentsCodex', width:200,title: '备注'}
	              ,{field:'itemName', width:180,title: '项目名称'}
	              ,{field:'itemCode', width:200,title: '项目编号'}
	              ,{field:'drugType', width:80,title: '药品类别'}
	            ]]
	            ,page: false
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    
		});
	  
//	    //监听行点击
//	    table.on('tool(userTable)', function(obj){
//	      var data = obj.data;
//	      if(obj.event === 'changeToWeiweigui'){
//		    	//变更成未违规
//		        layer.confirm(data.itemName+'标记为未违规', function(index){
//		        	//执行 Ajax 后重载
//		            var url=$WEB_ROOT_PATH+"/dhccApi/child/child/changeChildToWei";
//		            $.post(url,{'child.billingNo':data.itemCode},function(result){
//		        	    table.reload('userTable');
//		                layer.msg('已变更成未违规');
//		    	    });
//		        });
//	      } else if(obj.event === 'changeToWeigui'){
//	    	    //修改方法
//		        layer.confirm(data.itemName+'标记为违规', function(index){
//		        	//执行 Ajax 后重载
//		        	var url=$WEB_ROOT_PATH+"/dhccApi/child/child/changeChildToYi";
//		            $.post(url,{'child.billingNo':data.itemCode},function(result){
//		        	    table.reload('userTable');
//		                layer.msg('已变更成违规');
//		    	    });
//		        });
//	      }
//	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
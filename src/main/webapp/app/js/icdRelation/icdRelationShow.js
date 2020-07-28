//全局变量(radio单选中的数据)
var radioLeft='';//左边选中的数据
var radioRight='';//右边选中的数据
var lastClickRadio='';//最后一次点击关联的方向(left/right)
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=T_PICCBID_DICT_ICD',
				function(data){
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#typeName").append(mm); 
		     		}
		     	form.render('select');
			});
	    
		   table.render({
		    	   elem: '#icdTableShow'
		            ,url:$WEB_ROOT_PATH+'/dhccApi/icdrelation/icdRelation/list'
		            ,height:  document.documentElement.clientHeight-65
		            ,cols: [[
						 {type: 'numbers', width:80, title: '序号'}
						 ,{field:'id', title: 'ID', sort: true, hide:true} 
						 ,{title:'操作', width: 155, align:'center', toolbar: '#table-orgadmin-webuser'} 
						,{field:'icdNameLeft',width:150, title: 'ICD名称' }
						,{field:'icdCodeLeft', width:150,title: 'ICD编码' }
						,{field:'typeNameLeft', width:180,title: 'ICD版本名' }
						//,{field:'typeLeft', width:150,title: 'ICD版本号' }
						,{field:'icdNameRight',width:150, title: '关联ICD名称' }
						,{field:'icdCodeRight', width:150,title: '关联ICD编码' }
						,{field:'typeNameRight', width:200,title: '关联ICD版本名' }
						//,{field:'typeRight', width:150,title: '关联ICD版本号' }
						]]
		            ,page: true
		          });
		   
			form.on('submit(LAY-org-front-search)', function(data){
		    	var field = data.field;
		        //执行重载
		        layui.table.reload('icdTableShow', {
		            where: field
		        });
		    });
			
		    //监听行点击
		    table.on('tool(icdTableShow)', function(obj){
		      var data = obj.data;
		      if(obj.event === 'del'){
		    	//删除
			  layer.confirm('确定删除该关联信息吗？', function(index){
			      //执行 Ajax 后重载
			        var url=$WEB_ROOT_PATH+"/dhccApi/icdrelation/icdRelation/delete";
			        $.post(url,{'icdRelation.id':data.id},function(result){
			        	table.reload('icdTableShow');
			            layer.msg('解除关联成功！');
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

  






 
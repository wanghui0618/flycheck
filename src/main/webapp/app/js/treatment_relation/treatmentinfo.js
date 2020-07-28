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
	    
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_city_relation',
				function(data){
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#cityNameLeft").append(mm); 
		     		}
		     	form.render('select');
			});
	    
		   table.render({
		    	   elem: '#drugTableShow'
		            ,url:$WEB_ROOT_PATH+'/dhccApi/cityrelation/cityRelation/list'
		            ,where:{"cityRelation.type":"2","selectType":"all"}
		            ,height: 340
		            ,cols: [[
						 {type: 'numbers', width:80, title: '序号'}
						 ,{title:'操作', width: 155, align:'center', toolbar: '#table-orgadmin-webuser'} 
						,{field:'cityNameLeft',width:150, title: '版本信息' }
						,{field:'itemCodeLeft', width:150,title: '诊疗编码' }
						,{field:'itemNameLeft', width:150,title: '诊疗名称' }
						,{field:'cityNameRight',width:150, title: '关联版本信息' }
						,{field:'itemCodeRight', width:150,title: '关联诊疗编码' }
						,{field:'itemNameRight', width:150,title: '关联诊疗名称' }
						]]
		            ,page: true
		          });
		   
			form.on('submit(LAY-org-front-search)', function(data){
		    	var field = data.field;
		        //执行重载
		        layui.table.reload('drugTableShow', {
		            where: field
		        });
		    });
			
		    //监听行点击
		    table.on('tool(drugTableShow)', function(obj){
		      var data = obj.data;
		      if(obj.event === 'del'){
		    	//删除
			  layer.confirm('确定删除该关联信息吗？', function(index){
			      //执行 Ajax 后重载
			        var url=$WEB_ROOT_PATH+"/dhccApi/cityrelation/cityRelation/delete";
			        $.post(url,{'cityRelation.id':data.id},function(result){
			        	var inFlag= result.inFlag; 
			        	if(inFlag==0){
			        		table.reload('drugTableShow');
			        		layer.msg('解除关联成功！');
			        	}else{
			        		layer.msg('解除关联失败！');
			        	}
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

  






 
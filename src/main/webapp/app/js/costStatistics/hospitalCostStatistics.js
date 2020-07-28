//初始化	 
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;

	// $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
	// 	function(data){
	// 		var orgs=data.pageModel.rows
	// 		//var  dataList= data.dictList;
	// 		var org_save=JSON.stringify(orgs);//解析为字符串
	// 		//localStorage.clear();
	//
	// 		localStorage.setItem('org_save',org_save);//存入浏览器数据库
	// 		for(var i=0 ;i<orgs.length;i++){
	// 			var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
	// 			$("#zyOrgName").append(mm);
	// 		}
	// 		form.render('select');
	// 	});


	table.render({
	    	elem: '#userTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/hospitalCostStatistics/listVo'
	            ,cellMinWidth: 80
	             ,height: document.documentElement.clientHeight-65
	             // ,height: 430
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'orgName',width:200, title: '医疗机构'}
		              ,{field:'totalCost',align:'center', title: '总费用（元）'}
		              ,{field:'growthRate',align:'center', title: '增长率（相比去年）%'}
		              ,{field:'fundCost',align:'center', title: '报销费用（元）'}
		              ,{field:'selfCost',align:'center', title: '自费费用（元）'}
			          ,{field:'stayLength',align:'center', title: '总住院天数'}
			          ,{field:'stayPersonNum',align:'center', title: '住院人数'}
			          ,{field:'perStay',align:'center', title: '人均住院天数'}
		              ,{field:'personNum',align:'center', title: '就诊人数'}
		              ,{field:'perCost',align:'center', title: '人均费用（元）'}
		              ,{field:'totalTimes',align:'center', title: '就诊次数'}
		              ,{field:'eachCost',align:'center', title: '均次费用（元）'}

		              // ,{field:'illegalCases',align:'center',title: '违规（例）'}
		              // ,{field:'illegalAccounted',align:'center', title: '占比（%）'}
		              // ,{field:'illegalCost',width:100,align:'center', title: '违规金额（元）'}
					  //
		              // ,{field:'normalCases',align:'center', title: '正常（例）'}
		              // ,{field:'normalAccounted',align:'center', title: '占比（%）'}
		            ]]
		    		,page: true
		          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data) {
			var field = data.field;

			layui.table.reload('userTable', {
				where: field
				, page: {curr: 1}
			});

			var year = document.getElementById("test2");
			if (year.value === "") {
			year.value = nowYear();
			}
	    });
	  
	   //添加事件
	    var active = {
	    		add: function(){
	    			
	    			 
	    		    	//新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加字典'
	    		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
	    		          ,maxmin: true
	    		          ,area: ['500px', '450px']
	    		          ,btn: ['确定', '取消']
	    		          ,success: function(layero, index){
	    						var iframeWindow = window['layui-layer-iframe'+ index];
	    						//加载select下拉option
	    						iframeWindow.loadSelect();
	    					}
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	    		            //监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; //获取提交的字段
	    		             // console.log(field);
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('userTable'); //数据刷新
                      layer.close(index); //关闭弹层
            	  }else if(inFlag==1){
            		  layer.msg('该字典名称已存在！')
            		  return false;
            	  }
			  });
	       });
	    submit.trigger('click');
	     }
	  }); 
	}
};
	  //监听行点击
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定要删除该条数据？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/delete";
	            $.post(url,{"dictmaintain.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改字典'
		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
		          ,maxmin: true
		          ,area: ['500px', '450px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	//加载select下拉option
						iframeWindow.loadSelect();
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  console.log(JSON.stringify(data));
		        	
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              console.log(field);
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save1";
		              $.post(url,field,function(result){
		            	  var inFlag=result.inFlag;
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
		            		  //后台成功后，静态更新表格中的数据
		                      table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('修改成功!')
		            		   table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            		  //return false;
		            	  }
					  });
			       });
		            submit.trigger('click');
		          }
		        }); 
	      }
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
	    

	    function deRepeat(arr){
	           var newArr=[];
	           for(var i=0;i<arr.length;i++){
	        	   var text=arr[i].text;
	               if($.inArray(arr[i],newArr)==-1){
	                   newArr.push(arr[i]);
	               }
	           }
	           return newArr;
	       }
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
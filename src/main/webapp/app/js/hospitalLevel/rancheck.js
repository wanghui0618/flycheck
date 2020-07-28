//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var options={
	    		elem: '#userTable'
            	,url: $WEB_ROOT_PATH+'/dhccApi/orgdirectory/orgDirectory/list'
	            ,where:{ilegalOrgLevel:'1'}
            	,cellMinWidth: 80
	            ,height: 415
	            ,cols: [[
	              {fixed: 'left', type: 'numbers', title: '序号'  }
	             ,{fixed: 'left',field:'caseNo', title: '编号',width:'6%'}
	             ,{fixed: 'left', title:'操作',align:'center', toolbar: '#table-useradmin-webuser', width:'25%'}
	             ,{field:'ilegalOrgLevel', title: '违规?', width: '8%', sort: true
	                  ,templet: function(d){
	                	    var codex =d.ilegalOrgLevel;
	                	    if(codex==null||codex==""){
		                		codex="";
		                		return '<span >'+ codex +'</span>'
		                	}
		                	if(codex=="1"){
		                		codex="违规";
		                		return '<span style="color: red;">'+ codex +'</span>'
		                	}
		                	if(codex=="2"){
		                		codex="疑点违规";
		                		return '<span style="color: yellow;">'+ codex +'</span>'
		                	}
		                	if(codex=="0"){
		                		codex="未违规";
		                		return '<span style="color: green;">'+ codex +'</span>'
		                	}
		                    
	                  }
	                }
	              ,{field:'patientName',width:80, title: '姓名'}
	              ,{field:'age',width:60, title: '年龄'}
	              ,{field:'condition',width:100,title:'诊断'}
	              ,{field:'idNo',width:150, title: '身份证号'}
	              ,{field:'orgCode', width:100,title: '机构编码'}
	              ,{field:'billingNo',width:120, title: '收费单据号'}
	              ,{field:'admissionNo',width:120, title: '住院号'}
	            ]]
	            ,page: true
	    }
	    table.render(options);
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	       exec: function(){
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/user/user/save";
              $.post(url,field,function(result){
            	  layer.msg('执行成功!');
            	  //后台成功后，静态更新表格中的数据
                  table.reload('LAY-user-front-submit'); //数据刷新
			  });
	       }
	    };
	    
	    //监听行点击
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'changeToWeiweigui'){
		    	//变更成未违规
	    	  	if(data.ilegalOrgLevel=="0"){
	    	  		layer.msg('已做未违规标记');
	    	  	}else{
	    	  		layer.confirm(data.caseNo+'标记为未违规', function(index){
			        	//执行 Ajax 后重载
			            var url=$WEB_ROOT_PATH+"/dhccApi/orgdirectory/orgDirectory/changeToWei";
			            $.post(url,{'orgVO.billingNo':data.billingNo,
			            	'orgVO.admissionNo':data.admissionNo
			            },function(result){
			        	    table.reload('userTable');
			                layer.msg('已变更成未违规');
			    	    });
			        });
	    	  	}
		   } else if(obj.event === 'changeToWeigui'){
	    	    //变更为违规
	    	  if(data.ilegalOrgLevel=="1"){
	    		   layer.msg('已做违规标记');
	    	  }else{
	    		  layer.confirm(data.caseNo+'标记为违规', function(index){
			        	//执行 Ajax 后重载
		    		  var url=$WEB_ROOT_PATH+"/dhccApi/orgdirectory/orgDirectory/changeToWG";
		    		  $.post(url,{'orgVO.billingNo':data.billingNo,
			            	'orgVO.admissionNo':data.admissionNo},function(result){
				        	    table.reload('userTable');
				                layer.msg('已变更成违规');
			    	    });
			        });
	    	  }
	    	}
		   else if(obj.event === 'viewInfo'){
	    	    //明细
			  var orgCode = data.orgCode;
			  $.post($WEB_ROOT_PATH+'/dhccApi/orgdirectory/orgDirectory/findById?OrgDirectory.orgCode='+orgCode,function(res){
				  if(res!=null){
					  var orgDirectory=res.orgDirectory;
					  layer.open({
				          type: 2
				          ,title: '机构明细:&nbsp;<span style="color:red;">药品比对-机构等级:&nbsp;'+orgDirectory.orgLevel+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;诊疗比对-机构类型:&nbsp;'+orgDirectory.publicHospitalLevel+'</span>'
				          ,content: $WEB_ROOT_PATH+'/ordirectory/orgCheckInfo'
				          ,maxmin: true
				          ,area: ['1000px', '500px']
				          ,success: function(layero, index){
				        	  var iframeWindow = window['layui-layer-iframe'+ index];
				        	  //向此iframe层方法 传递参数
				        	  iframeWindow.child(JSON.stringify(data));
				          }
				        });
				  }
				  
			  })
	    	}
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
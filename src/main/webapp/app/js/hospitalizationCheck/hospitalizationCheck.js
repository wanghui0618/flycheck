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
	            ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalization/costdetailHos/getListRecord'
	            /*,cellMinWidth: 80*/
	            ,height: 415
	            ,where: { ilegalHospitalization: '1' }
	            ,cols: [[
	            	{field:'caseNo', width:80,title: '编号'}
	              ,{title:'操作', toolbar: '#table-useradmin-webuser', width:260}
	              ,{field:'ilegalHospitalization', title: '违规?', width: 90, sort: true
	                  ,templet: function(d){
	                	  console.log(d)
	                	    var codex =d.ilegalHospitalization;
		                	if(codex==null||codex==""){
		                		codex="";
		                	}
		                	if(codex=="1"){
		                		codex="违规";
		                	}
		                	if(codex=="2"){
		                		codex="疑似违规";
		                	}
		                	if(codex=="0"){
		                		codex="未违规";
		                	}
		                    return '<span style="color: red;">'+ codex +'</span>'
	                  }
	                }
	              ,{field:'admissionType', width:100,title: '住院类型'}
	              ,{field:'condition', width:120,title: '诊断'}
	              ,{field:'patientName',width:80, title: '姓名'}
	              ,{field:'idNo',width:180, title: '身份证号'}
	              ,{field:'age',width:60, title: '年龄'}
	              ,{field:'orgCode', width:110,title: '机构编码'}
	              ,{field:'admissionNo',width:120, title: '住院号'}
	              ,{field:'billingNo',width:120, title: '收费单据号'}
	            ]]
	    		,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	    	console.log(field)
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
	    //监听行点击
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'changeToWeiweigui'){
		    	//变更成未违规
		        layer.confirm(data.caseNo+'标记为未违规', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/hospitalization/costdetailHos/changeRecordHospitalizationToWei";
		            $.post(url,{'costdetailHos.admissionNo':data.admissionNo,'costdetailHos.billingNo':data.billingNo},function(result){
		        	    table.reload('userTable');
		                layer.msg('已变更成未违规');
		    	    });
		        });
	      } else if(obj.event === 'changeToWeigui'){
	    	    //修改方法
		        layer.confirm(data.caseNo+'标记为违规', function(index){
		        	//执行 Ajax 后重载
		        	var url=$WEB_ROOT_PATH+"/dhccApi/hospitalization/costdetailHos/changeRecordHospitalizationToYi";
		            $.post(url,{'costdetailHos.admissionNo':data.admissionNo,'costdetailHos.billingNo':data.billingNo},function(result){
		        	    table.reload('userTable');
		                layer.msg('已变更成违规');
		    	    });
		        });
	      } else if(obj.event === 'viewInfo'){
	    	    //明细
	    	  layer.open({
		          type: 2
		          ,title: '就诊明细-当前人住院类型：<span style="color: red;">'+ data["admissionType"] +'</span>'
		          ,content: $WEB_ROOT_PATH+'/hospitalizationcheck/hospitalizationcheckinfo'
		          ,maxmin: true
		          ,area: ['1000px', '550px']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		          }
		        }); 
	      }
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
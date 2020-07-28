
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','echarts','laydate'], function(){
		    var $ = layui.$
		    ,form = layui.form
		    ,table = layui.table;

		    
		    //日期范围  
			var laydate1=layui.laydate;
			laydate1.render({
				elem:'#startTime'
					,trigger:'click'
							,format:'yyyy-MM-dd' 
								,range: true
			});

	/*		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=T_PICCBID_MEDICAL_ORGNAME', 
					 function(data){
				 var  form=layui.form;
				 var  dataList= data.dictList;
				 for(var i=0 ;i<dataList.length;i++){
		     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
		     			$("#orgCode").append(mm); 
		     		}
				 form.render('select');
			 }); */
			 
			 
		    table.render({
		    	elem: '#blackList'
		            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/countOrg'
		            ,cellMinWidth: 80
		            ,where: {}
		            ,height: tableHeight
		            ,cols: [[
		            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
			              ,{field:'orgCode', title: '机构代码',width:150,align:'center'}
			              ,{field:'orgName',title: '机构名称', width:150,align:'center'}
			              ,{field:'type1', title: '门诊',width:100,align:'center',templet: function(d){
		                	    var codex =d.type1;
			                	if(codex==null){
			                		codex="无数据";
			                	}else{
			                		codex=codex;
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}	                          
			              ,{field:'type2', title: '住院',width:100,align:'center',templet: function(d){
		                	    var codex =d.type2;
		                		if(codex==null){
			                		codex="无数据";
			                	}else{
			                		codex=codex;
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}	                          
			              ,{field:'type3', title: '门特',width:100,align:'center',templet: function(d){
		                	    var codex =d.type3;
		                		if(codex==null){
			                		codex="无数据";
			                	}else{
			                		codex=codex;
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}	                          
			             // ,{field:'countNumber',title: '就诊人数', width:100,align:'center' }
		            ]]
		            //,page: true
		          });
		    
		    //监听搜索
			form.on('submit(LAY-user-front-search)', function(data){
		    	var field = data.field;    	
		    	 console.info(field);
		    	 var orgCode =$("#orgCode").val();
		    	 var startTimeNew =$("#startTime").val();
		    	 if( orgCode== "" || startTimeNew ==""){
		    		 layer.msg('请先选择机构代码和时间段!');
		    	 }
		    	 
		        //执行重载
		        layui.table.reload('blackList', {
		            where: field
		        });
		    });
			
			

		  

		    
		    //按钮事件绑定底层方法-勿动
		    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		      var type = $(this).data('type');
		      active[type] ? active[type].call(this) : '';
		    });
		  
		
 });


layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(
						[ 'laydate', 'laypage', 'layer', 'table', 'carousel',
								'upload', 'element', 'slider','form' ],
						function() {
							var laydate = layui.laydate //日期
							, table = layui.table //表格
							, form = layui.form
							, laypage = layui.laypage; //分页
							table.render({
										elem :'#admissionDiseaseName'
										,cellMinWidth: 80
										,height:tableHeight
										,url : $WEB_ROOT_PATH+'/dhccApi/hospitalizationConditions/hospitalizationConditions/getlist'  //数据接口
										,title : '分解住院情况筛查分析表'	
									    ,cols: [[
										 {field:'id', title: 'id'}
										,{field:'inDiagnosisNo', title: '诊断编码'}
										,{field:'inDiagnosisName',title: '诊断名称'}
										]],
										page : true //开启分页
										,done: function(res, curr, count){
								    		
								    		ReadRightTable();
								    	}
									});
							table.on('row(admissionDiseaseName)', function(obj){
						    	var data = obj.data;
						        var inDiagnosisName=data.inDiagnosisName;
						    	ReadRightTable(inDiagnosisName);
						    });
							function ReadRightTable(inDiagnosisName){
						    	 table.render({
						 	    	elem: '#HospitalizationConditions'
						 	            ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalizationConditions/hospitalizationConditions/getconditions'
						 	            ,cellMinWidth: 80
						 		        ,height: tableHeight
						 	            ,cols: [[
						 	            	{field: 'billingNo', title: '收费单据号' }
						 	            	  ,{field: 'idcard', title: '身份证号' }
						 	            	 ,{field: 'sscno', title: '社保卡号'}
						 	            	  ,{field: 'name', title: '姓名'}
						 	            	  ,{field: 'sex', title: '性别'}
						 	            	  ,{field: 'inDiagnosisName', title: '入院诊断名称'}
						 	            	  ,{field: 'inDiagnosisNo', title: '入院诊断编码'}
						 	            	  ,{field: 'itemName', title: '项目名称'}
						 	            	  ,{field: 'itemCode', title: '项目编号'}
						 	            	  ,{field: 'diagType', title: '就诊类型'}
						 	            	  ,{field: 'doctorName', title: '主治医生'}
						 	            	  ,{field: 'outHosDepartCode', title: '出院科室编码'}
						 	            	  ,{field: 'outHosDepart', title: '出院科室'}
						 	            ]]
						    	 ,page: true
						    	 		,where:{
						    	 			'admissionDiseaseName':inDiagnosisName
						    	 		}
						 	          });
						    };
							form.on('submit(search)', function(data){
							    var formData = data.field;
							    var limitdays = formData.limitdays;
							    var limitgrade = formData.limitgrade;
					            //数据表格重载
					            table.reload('HospitalizationConditions',{
					            	page:{curr:1}
									,cellMinWidth: 80
									,height:tableHeight
					                 ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalizationConditions/hospitalizationConditions/getconditions'  
					                ,where:{
					                	limitdays:limitdays,
									    limitgrade:limitgrade,
					                }
					            });
					        });
						});
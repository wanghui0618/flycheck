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
										,{field:'admissionDiseaseId', title: '诊断编码'}
										,{field:'admissionDiseaseName',title: '诊断名称'}
										]],
										page : true //开启分页
										,done: function(res, curr, count){
								    		
								    		ReadRightTable();
								    	}
									});
							table.on('row(admissionDiseaseName)', function(obj){
						    	var data = obj.data;
						        var admissionDiseaseName=data.admissionDiseaseName;
						    	ReadRightTable(admissionDiseaseName);
						    });
							function ReadRightTable(admissionDiseaseName){
						    	 table.render({
						 	    	elem: '#HospitalizationConditions'
						 	            ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalizationConditions/hospitalizationConditions/getconditions'
						 	            ,cellMinWidth: 80
						 		        ,height: tableHeight
						 	            ,cols: [[
						 	            	{field: 'medicalRecordId', title: '病案编码' }
						 	            	  ,{field: 'patientName', title: '患者姓名' }
						 	            	  ,{field: 'patientGender', title: '患者性别'}
						 	            	  ,{field: 'patientAge', title: '年龄'}
						 	            	  ,{field: 'hospitalId', title: '医疗机构编码'}
						 	            	  ,{field: 'hospitalName', title: '医疗机构名称'}
						 	            	  ,{field: 'itemName', title: '住院护理'}
						 	            ]]
						    	 ,page: true
						    	 		,where:{
						    	 			'admissionDiseaseName':admissionDiseaseName
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
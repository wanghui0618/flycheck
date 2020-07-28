//初始Hua
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','element'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table
	    /*table.on('row(inhos_diag)', function(obj){
	        var data = obj.data;
	        console.log(JSON.stringify(data.inhosDiag));
	        //标注选中样式
	        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
	      });*/
	    table.render({
	    	elem: '#admissionDiseaseName'
	    	,cellMinWidth: 80
	    	,page: true
	        ,height: tableHeight
	    	,url: $WEB_ROOT_PATH+'/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics/getlist'
	    	,cols: [[
            	{field:'hisid', title: 'ID', sort: true, hide:true}
            	/*,{title: '序号',templet: '#xuhao',width:'15%'}*/
            	,{type: 'numbers', width: 40, title: '编号',width:'15%'}
            	  ,{field: 'admissionDiseaseName', title: '诊断名称' ,width:'85%'}
            ]]
	    	,done: function(res, curr, count){
	    		/*var firstData= res.data[0];
	    		alert(firstData.inhosDiag);*/
	    		initRightTable();
	    	}
	    });
	    table.on('row(admissionDiseaseName)', function(obj){
	    	var data = obj.data;
/*	    	alert(data.inhosDiag);*/
	    	console.log(data)
	        var admissionDiseaseName=data.admissionDiseaseName;
	        initRightTable(admissionDiseaseName);
	    });
	    function initRightTable(param,year,month){
	    	 table.render({
	 	    	elem: '#diseaseAndDiagnosticStatistics'
	 	            ,url: $WEB_ROOT_PATH+'/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics/getlistByinhosDiag'
	 	            ,cellMinWidth: 80
	 		        ,height: tableHeight
	 	            ,page: true
	 	            ,cols: [[
	 	            	{field:'hisid', title: '序号', sort: true, hide:true}
	 	            	  ,{field: 'admissionDate', title: '就诊时间' ,width:'24%'}
	 	            	  ,{field: 'hospitalName', title: '就诊机构' ,width:'26%'}
	 	            	  ,{field: 'patientName', title: '参保人姓名',width:'25%' }
	 	            	 ,{field: 'patientAge', title: '参保人年龄',width:'25%' }
	 	            ]]
	    	 		,where:{
	    	 			admissionDiseaseName:param,
	    	 		}
	 	     
	 	          });
	    }
	    function initleftTable(param){
	    	 table.render({
	 	    	elem: '#admissionDiseaseName'
	 	            ,url: $WEB_ROOT_PATH+'/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics/getlist'
	 	            ,cellMinWidth: 80
	 		        ,height: tableHeight
	 	            ,page: true
	 	            ,cols: [[
	 	            	{field:'hisid', title: 'ID', sort: true, hide:true}
	 	            	,{type: 'numbers', width: 40, title: '编号',width:'15%'}
	 	            	,{field: 'admissionDiseaseName', title: '诊断名称' ,width:'85%'}
	 	            ]]
	    	 		,where:{
	    	 			admissionDiseaseName:param,
	    	 		}
	 	          });
	    }
	    
	    //监听搜索
	    form.on('submit(year)', function(data){
	    	var field = data.field;
	    	console.log(field)
	        //执行重载
	    	table.render({
	 	    	elem: '#diseaseAndDiagnosticStatistics'
	 	            ,url: $WEB_ROOT_PATH+'/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics/getlistByinhosDate'
	 	            ,cellMinWidth: 80
	 		        ,height: tableHeight
	 	            ,page: true
	 	            ,cols: [[
	 	            	{field:'hisid', title: 'ID', sort: true, hide:true}
	 	            	,{field: 'admissionDate', title: '就诊时间' ,width:'25%'}
	 	            	,{field: 'hospitalName', title: '就诊机构' ,width:'25%'}
	 	            	,{field: 'patientName', title: '参保人姓名',width:'25%' }
	 	            	,{field: 'patientAge', title: '参保人年龄',width:'25%' }
	 	            ]]
	    	 		,where:{
	    	 			year:field.year,
	    	 			month:field.month,
	    	 		}
	 	     
	 	          });
	    });
	    
	    //诊断名称
	    form.on('submit(zhenduanName)', function(data){
	    	var field = data.field;
	    	var inhosDiag=field.inhosDiag
	    	console.log(data)
	        //执行重载
	    	initleftTable(inhosDiag);
	    });
	    
	    layui.use('laydate', function() {
			var laydate = layui.laydate;
			//年范围
			laydate.render({
				elem: '#year'
				,type: 'year'
			});
		});
	    layui.use('laydate', function() {
			var laydate = layui.laydate;
			//月范围
			laydate.render({
				elem: '#month'
				,type: 'month'
			});
		});
	    
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
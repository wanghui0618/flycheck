//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    table.render({
	    	elem: '#outpatientTable'
	            ,url: $WEB_ROOT_PATH+'/app/js/medicalreport/hospitalizationAnalysis.json'
	            ,cellMinWidth: 80
	             ,height: 415
	             ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {field:'id', title: 'ID', sort: true, hide:true}
	            	  ,{field: 'hospitalGrade', title: '医院等级' }
		              ,{field:'peopleNumber', title: '总人次'}
		              ,{field:'totalCost', title: '总费用（万元）'}
		              ,{field:'overallPayment', title: '统筹支付费用（万元）'}
		              ,{field:'selfpay', title: '自付费用（万元）'}
		              ,{field:'selfExpenses', title: '自费费用（万元）'}
		              ,{field:'drugCost', title: '药品费用（万元）'}
		              ,{field:'projectCost', title: '项目费用（万元）'}
		              ,{field:'materialCost', title: '材料费用（万元）'}
		            ]]
		    		,page: true
		          });
	    
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
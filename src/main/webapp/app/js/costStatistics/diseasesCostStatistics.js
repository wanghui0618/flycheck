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
	            ,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/diseasesCostStatistics/listVo'
	            ,cellMinWidth: 80
	             ,height: document.documentElement.clientHeight-65
	             // ,height: 415
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	  {type: 'numbers',align:'center', title: '序号' }
		              ,{field:'id', align:'center',title: 'ID', sort: true, hide:true}
		              ,{field:'mainDiagName',align:'center',width:300, title: '疾病名称'}
		              ,{field:'totalCost', align:'center',title: '总花费'}
		              ,{field:'personNum', align:'center',title: '患病人数'}
		              ,{field:'perCost', align:'center',title: '人均费用（元）', templet: function (value) {
						return value.perCost.toFixed(2);
					}}
		              ,{field:'totalTimes', align:'center',title: '就诊次数'}
		              ,{field:'eachCost',align:'center', title: '均次费用（元）',templet: function (value) {
                        return value.eachCost.toFixed(2);
                    }}
		              ,{field:'growthRate',align:'center', title: '增长率（%）'}
		              ,{field:'fundCost',align:'center', title: '报销费用（元）'}
		              ,{field:'selfCost',align:'center', title: '自费费用（元）'}
		              ,{field:'stayLength',align:'center', title: '总住院天数'}
		              ,{field:'dayCost',align:'center', title: '日均费用',templet: function (value) {
                        return value.dayCost.toFixed(2);
                    }}
		              // ,{field:'illegalCases',align:'center', title: '违规（例）'}
		              // ,{field:'illegalAccounted',align:'center', title: '占比（%）'}
		              // ,{field:'illegalCost',align:'center', title: '违规费用'}
		              // ,{field:'normalCases', align:'center',title: '正常（例）'}
		              // ,{field:'normalAccounted', align:'center',title: '占比（%）'}
		            ]]
	           
		    		,page: true
		          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
				,page: { curr: 1}
	        });
			var year = document.getElementById("test2");
			if (year.value === "") {
				year.value = nowYear();
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
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
	    		,url: $WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/serviceCostData'
	            ,cellMinWidth: 80
	           // ,where: {  }
	            ,height: document.documentElement.clientHeight-80
	            ,cols: [[
	            	 {field:'id', title: 'ID', sort: true, hide:true}
	            	  //,{field: 'totalNumber', title: '总病例数' ,width:86,unresize:true}
	            	 ,{title: '序号',templet: '#xuhao',width:'15%'}
	            	  ,{field: 'orgName', title: '医院名称',width:'34%' }
		              ,{field:'serviceFee', title: '诊疗总费用',width:'26%'}
		             // ,{field:'rank', title: '排名',width:115,unresize:true}
		              ,{field:'serviceRatio', title: '诊疗费用占比',width:'25%',
		            	  templet: function(d){
		            		  var codex = d.totalRatio;
								return codex + "%"
		            	  }
		              }
		              //,{field:'money', title: '医保报销金额',unresize:true}
		              //,{field:'averageDay', title: '平均住院天数',unresize:true}
		             // ,{field:'drugRatio', title: '药占比',unresize:true}
		              //,{field:'allCost1', title: '可疑报销金额（元）'}
		              //,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
		             
		            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	    	 console.info(field);
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//年范围
			laydate.render({
				elem: '#test5'
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
		//按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
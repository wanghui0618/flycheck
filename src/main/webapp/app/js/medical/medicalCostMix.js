//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var laydate=layui.laydate;
		laydate.render({
			elem:'#createTime'
				,type:'year'
				,max:nowYear()
				,value:nowYear()
		});
		function nowYear(){
			var date=new Date();
			var nowYear=date.getFullYear();

			return nowYear.toString();
		}
	    
	    table.render({
	    	elem: '#medicalCostMixTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/medicalCostMix'
	        ,cellMinWidth: 80
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
			 ,{field:'orgCode', title: '机构编码',  align:'center',sort: true, hide:true}
			,{field:'orgName',align:'center', title: '医疗机构' }
			,{field:'totalCase',align:'center', title: '总病例数' }
			,{field:'totalCost',align:'center', title: '总费用'}
			,{field:'medicalCost', align:'center',title: '药品总费用' }
			,{field:'medicalCostMix', align:'center',title: '药品费用占比' }
			,{field:'treatmentCost', align:'center',title: '诊疗总费用' }
			,{field:'treatmentCostMix', align:'center',title: '诊疗费用占比' }
			,{field:'consumablesCost', align:'center',title: '耗材总费用' }
			,{field:'consumablesCostMix', align:'center',title: '耗材费用占比' }
			,{field:'qita', align:'center',title: '其它总费用' }
			,{field:'qitaMix', align:'center',title: '其它费用占比' }
			,{field:'insureCost', align:'center',title: '医保报销金额' }
			,{field:'selfCost', align:'center',title: '个人负担金额' }
		
	            ]]
	    
	    
	            ,page: true
	          });
	    
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
			var field = data.field;
			//执行重载
	        layui.table.reload('medicalCostMixTable', {
	            where: field
	        });
	    });
		
		 //加载医院下拉字典
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityOrgDict',
            function(data){
                //var  dataList= data.dictList;
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getOrgName").append(mm);
                }
                form.render('select');
           });*/
        
       /* 
        //加载城市下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getCityName").append(mm);
                }
                form.render('select');
           });*/
	  
	  
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
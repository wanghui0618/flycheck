var active='';
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','echarts'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    
	 
	    
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/anesthesia/anesthesia/listVo'
	            ,cellMinWidth: 80
	            ,height: document.documentElement.clientHeight-80
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	 {field: 'hisid', title: '结算单据号', width:190}
	            	 ,{field: 'hospitalName', title: '医院名称' , width:190}
	            	 ,{field:'visitingRoute', title:'就诊途径' , width:190}
		              ,{field:'benefitGroupId', width:80,title:'人员类型', width:190 }
		              ,{field:'year', width:80, title:'年度', width:100}
		              ,{field:'medicalTotal',width:60, title: '总费用' , width:190}
		              ,{field:'detailId', title: '明细主键ID', width:190}
		              ,{field:'patientId', title:'单据号' , width:190}
		              ,{field:'patientId', title:'个人编码' , width:190}
		              ,{field:'zyh', width:150,title:'住院号' , width:190}
		              ,{field:'hospitalId', width:80, title:'医疗机构编码', width:190}
		              ,{field:'hospitalName',width:60, title: '医疗机构名称' , width:240}
		              ,{field:'dischargeDeptId', width:60,title:'出院科室编码' , width:190}
		              ,{field:'dischargeDeptName', width:180,title:'出院科室名称' , width:190}
		              ,{field:'doctorId', title:'主诊医师编码' , width:190}
		              ,{field:'dischargeDiseaseNameMain',  width:150,title: '主诊医师姓名' , width:130}
		              ,{field:'pCategory',width:100, title:'费用类别', width:190}
		              ,{field:'usageDate',width:100,  title:'项目使用日期' , width:190}
		              ,{field:'usageDateFlag',width:100,  title:'项目使用日期标识' , width:190}
		              ,{field:'billDate', title:'结算日期' , width:190}
		              ,{field:'year',  width:90,title: '收费年份' , width:100}
		              ,{field:'month', width:175,title:'收费月份' , width:100}
		              ,{field:'itemIdHosp', width:150,title: '医院项目编码' , width:190}
		              ,{field:'itemNameHosp',  width:90,title: '医院项目名称' , width:190}
		              ,{field:'itemId', title:'医保项目编码' , width:190}
		              ,{field:'itemName', title:'医保项目名称' , width:190}
		              ,{field:'itemType', title:'项目类型', width:190 ,templet: function(d){
						  var b =d.itemType;
						  if(b=="1"){
							  b="药品";
						  }else if(b=="2"){
							  b="诊疗";
						  }else if(b=="3"){
							  b="耗材";
						  }
						  return '<span >'+ b +'</span>'
					  }}
		              ,{field:'drugSpec', title:'规格', width:190 }
		              ,{field:'dosageForm', width:90, title:'剂型' , width:190}
		              ,{field:'packageUnit',  width:90,title:'最小包装单位' , width:190}
		              ,{field:'unitPrice', title:'单价' , width:190}
		              ,{field:'num',  width:115,title:'数量' , width:190}
		              ,{field:'cost',  width:120,title:'金额' , width:190}
		              ,{field:'bmiConveredAmount',  width:115,title:'医保范围内金额' , width:190}
		              ,{field:'bmiPayAmount',width:175, title:'医保医保实际支付金额', width:190}
		              ,{field:'pType', title:'支付类别' , width:190}
		              ,{field:'pTypePct',width:115, title:'报销比例', width:190}
		            ]]
	            ,done:function(){}
		    		,page: true
		          });
	    hideButtonStatic();// 按钮权限
	    
	    // 监听搜索
		form.on('submit(LAY-anesthesia-front-search)', function(data){
	    	var field = data.field;
	        // 执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
	  
	   
	    
	    // 按钮事件绑定底层方法-勿动
		 $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		      var type = $(this).data('type');
		      active[type] ? active[type].call(this) : '';
		    });
		  });
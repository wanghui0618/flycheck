//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    table.render({
	    	elem: '#flydetailTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/flydetail/flydetail/listVo'
	        ,cellMinWidth: 80
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
   			/*,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['city-update','city-delete'])} */       
			,{field:'orgCode',width:160,align:'center', title: '定点医疗机构编码' }
			,{field:'name',width:100,align:'center', title: '姓名' }
			,{field:'billingNo',width:140, align:'center',title: '单据号' }
			,{field:'diagType',width:120, align:'center',title: '医疗类别' }
			,{field:'recipelId',width:200,align:'center', title: '处方号' }
			,{field:'itemCodeHos', width:180,align:'center',title: '医院项目收费内码' }
			,{field:'itemNameHos', width:240,align:'center',title: '医院项目收费名称' }
			,{field:'itemCodeIns', width:160,align:'center',title: '药品项目编码' }
			,{field:'itemNameIns', width:250,align:'center',title: '社保目录名称' }
			,{field:'insuranceCode', width:180,align:'center',title: '医保号' }
			,{field:'preDate', width:160,align:'center',title: '处方日期' }
			,{field:'chargeType', width:120,align:'center',title: '收费类别' }
			,{field:'feeGrade', width:140,align:'center',title: '收费项目等级' }
			,{field:'itemPrice', width:120,align:'center',title: '单价' }
			,{field:'itemNum',width:120, align:'center',title: '数量' }
			,{field:'itemCost',width:120, align:'center',title: '金额' }
			,{field:'doseUnit',width:120, align:'center',title: '单位' }
			,{field:'depart',width:180, align:'center',title: '科室' }
			,{field:'specs',width:120, align:'center',title: '规格' }
			,{field:'docCode',width:120, align:'center',title: '处方医生编码' }
			,{field:'docName',width:120, align:'center',title: '处方医生名称' }
			,{field:'balanceDate',width:120, align:'center',title: '结算日期' }
		
	            ]]
	            ,page: true
	          });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
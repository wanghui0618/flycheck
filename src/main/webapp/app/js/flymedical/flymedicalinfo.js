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
	    	elem: '#flymedicalTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/flymedical/flymedical/listVo'
	        ,cellMinWidth: 80
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
   			/*,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['city-update','city-delete'])} */       
			,{field:'name',width:100,align:'center', title: '姓名' }
			,{field:'sex',width:60, align:'center',title: '性别' }
			,{field:'idcard',width:220, align:'center',title: '身份证号' }
			,{field:'crowdType', width:280,align:'center',title: '人员类别' }
			,{field:'orgName',width:220,align:'center', title: '机构名称' }
			,{field:'address', width:280,align:'center',title: '家庭地址' }
			,{field:'inhosDiag', width:200,align:'center',title: '入院诊断' }
			,{field:'outhosDiag', width:200,align:'center',title: '出院诊断' }
			,{field:'stayLength', width:120,align:'center',title: '住院天数' }
			,{field:'outhosDepart', width:160,align:'center',title: '出院科室' }
			,{field:'inhosDate', width:160,align:'center',title: '入院日期' }
			,{field:'outhosDate', width:160,align:'center',title: '出院日期' }
			,{field:'admissionNo', width:180,align:'center',title: '住院号' }
			,{field:'totalCost', width:120,align:'center',title: '费用总额' }
			,{field:'fundCost',width:120, align:'center',title: '基金支付金额' }
		
	            ]]
	            ,page: true
	          });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
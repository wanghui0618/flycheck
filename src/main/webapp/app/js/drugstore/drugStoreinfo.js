//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;

	    //$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city_xingzheng',
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city',
				function(data){
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#cityCode").append(mm); 
		     		}
			form.render('select');
			});
	    table.render({
	    	elem: '#drugstoreTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/drugstore/drugStore/list'
	        ,cellMinWidth: 80
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:60, title: '编号'}      
	/*		,{field:'areaCode',width:150,align:'center', title: '药店所在地区编码' }
			 ,{field:'cityName', width:140,align:'center',title: '城市名称' }*/
			,{field:'storeName', width:260,align:'center',title: '药店名称' }
			,{field:'storeNo', width:100,align:'center',title: '药店编号' }
			,{field:'designInsurance', width:150,align:'center',title: '是否定点医保单位' }
			,{field:'insuranceContact', width:110,align:'center',title: '医保联系人' }
			,{field:'insuranceLeading',width:140,align:'center',title: '分管医保负责人' }
			,{field:'legalName', width:140,align:'center',title: '法定代表人姓名' }
			,{field:'ownership', width:140,align:'center',title: '所有制形式' }
			,{field:'phone', width:180,align:'center',title: '电话' }
			,{field:'postalCode', width:110,align:'center',title: '邮政编码' }
			,{field:'licenseNo',width:150, align:'center',title: '执业许可证登记号' }
			,{field:'practisingCode',width:150, align:'center',title: '药店执业范围代码' }
			,{field:'storeLeading', width:110,align:'center',title: '机构负责人' }
			,{field:'storeLevel', width:100,align:'center',title: '药店等级' }
			/*,{field:'storeType', width:100,align:'center',title: '药店类型' }*/
			,{field:'address', width:320,align:'center',title: '地址' }
	          ]]
	            ,page: true
	          });
	    
		form.on('submit(LAY-drugstore-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('drugstoreTable', {
	            where: field
	        });
	    });

	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
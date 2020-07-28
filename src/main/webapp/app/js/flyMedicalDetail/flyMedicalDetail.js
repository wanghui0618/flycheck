layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var laydate1=layui.laydate;
	    laydate1.render({
			elem:'#createTime1'
				,type:'year'
	            
		});
	    
	    var laydate2=layui.laydate;
	    laydate2.render({
			elem:'#createTime2'
				,type:'month'
	            
		});
	    
	    /*function nowYear(){
			var date=new Date();
			var nowYear=date.getFullYear();
			var month= date.getMonth()+1;
			var rdate=nowYear+"-"+month;
			return rdate+" ~ "+rdate;
		}*/
	    table.render({
	    	elem: '#flyMedicalDetailTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/flyMedicalDetail/flyMedicalDetail/listVo'
	        ,cellMinWidth: 80
	        ,height: tableHeight+10
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
			,{field:'hospitalName',align:'center', title: '医疗机构名称'}
			,{field:'itemNameHosp',align:'center', title: '医疗收费项目'}
			,{field:'money', align:'center',title: '检查费收费' }
			,{field:'itemNum',align:'center', title: '频次' }
	        ]]
	          ,page: true
	          });
	    
	   
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
			var field = data.field;
			//执行重载
	        layui.table.reload('flyMedicalDetailTable', {
	            where: field
	        });
	    });
	  
	   
	  
	    
	  
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
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
	    	elem: '#tegrityTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/Tegrity'
	        ,cellMinWidth: 80
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
			 ,{field:'id', title: 'ID',  align:'center',sort: true, hide:true}
			,{field:'qualityName',align:'center', title: '医疗机构' }
			,{field:'totalCount', align:'center',title: '数据总量' }
			,{field:'effectiveNumber',align:'center', title: '完整数据量' }
			,{field:'qualityNumber', align:'center',title: '不完整数据量' }
			,{field:'effectiveRate', align:'center',title: '完整率' ,templet:function(d){
				if(d.effectiveRate=='0'){
					return d.effectiveRate;
				}else{
					return d.effectiveRate+"%";
				}
  	  	  }}
	            ]]
	    
  			
	    
	            ,page: true
	          });
	    
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
			var field = data.field;
			//执行重载
	        layui.table.reload('tegrityTable', {
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
        
        
        //加载城市下拉字典
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityDict',
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
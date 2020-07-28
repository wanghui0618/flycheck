layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function () {
	  var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	     
	    table.render({
	    	elem: '#hospitalInfo'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/yearData'
	            ,cellMinWidth: 80
	             ,page:false
	             ,skin:'nob'
	            ,event:true
	            //,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	 //{field:'id', title: 'ID', sort: true, hide:true}
	            	  {title: '序号',templet: '#xuhao',width:'8%'}
	            	  ,{field: 'orgName', title: '医院名称' ,width:'22%'}
	            	  ,{field: 'orgCode', title: '医院编码' ,width:'20%'}
	            	  ,{field: 'totalNumber', title: '病例数',width:'10%' }
		              ,{field:'totalPnum', title: '住院人数',width:'10%'}
		              ,{field:'avgCost', title: '次均费用',width:'10%'}
		              ,{field:'avgDay', title: '平均住院天数',width:'10%'}
		              ,{field:'drugRatio', title: '药占比',
		            	  templet: function(d){
		            		  var codex = d.totalRatio;
								return codex + "%"
		            	  }
		              }
		            ]]
	    		,limit: 7
	    		,done : function(res, curr, count){
	    	        tableList=res.data;
	    	        var that = this.elem.next();
	    	        res.data.forEach(function (item, index) {
	    	            //console.log(item.empName);item表示每列显示的数据
	    	            if (index % 2 == 0) {
	    	                var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color", "#fff");
	    	            } else {
	    	                var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("background-color", "#F5FCF9");
	    	            }
	    	        });
	    	        $('th').css({'background-color': '#E5F7EF', 'color': '#353535','font-size':'14px','line-height':'30px'})
	    			}
		          });
	    
	    //监听搜索
		/*form.on('select(inFlag)', function(data){
			var value = data.value;
			var field={'inFlag':value};
	        //执行重载
	        layui.table.reload('hospitalInfo', {
	            where: field
	        });
	    });*/
	   
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });

function getValue(){
	var value = $("#findYear option:selected").val();
	//alert(value);
	var field={'inFlag':value};
	layui.table.reload('hospitalInfo',{
		where: field
	});
	getValue1();
	getValue4();
	getValue5();
	getValue6();
	//yearChange();
	yearChange2();
}
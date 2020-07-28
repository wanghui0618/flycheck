layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function () {
	  var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	     
	    table.render({
	    	elem: '#materialCostRank'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/materialCostData'
	            ,cellMinWidth: 80
	             ,page:false
	             ,skin:'nob'
	            ,event:true

	            //,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	 {field:'id', title: 'ID', sort: true, hide:true}
	            	  //,{field: 'totalNumber', title: '总病例数' ,width:86,unresize:true}
	            	 ,{title: '序号',templet: '#xuhao',width:'15%'}
	            	  ,{field: 'orgName', title: '医院名称',width:'34%' }
		              ,{field:'materialFee', title: '耗材总费用',width:'26%'}
		              ,{field:'materialRatio', title: '耗材费用占比',width:'24.555555%',
		            	  templet: function(d){
								codex= d.totalRatio;
								return codex + "%"
		            	  }
		              }
		              //,{field:'money', title: '医保报销金额',unresize:true}
		              //,{field:'averageDay', title: '平均住院天数',unresize:true}
		             // ,{field:'drugRatio', title: '药占比',unresize:true}
		              //,{field:'allCost1', title: '可疑报销金额（元）'}
		              //,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
		             
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
	  
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });

function getValue6(){
	var value = $("#findYear option:selected").val();
	//alert(value);
	var field={'inFlag':value};
	layui.table.reload('materialCostRank',{
		where: field
	});
}
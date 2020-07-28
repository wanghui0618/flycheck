document.getElementById("main").style.height=document.documentElement.clientHeight-42+"px";
var selectedYear=thisYear()
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    
	    table.render({
	    	elem: '#medicalNumTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listNumber'
	        ,cellMinWidth: 80
	        ,height:document.documentElement.clientHeight-65
			,where: {  'medical.status':thisYear()}
			,cols: [[
			 {type: 'numbers', width:40, title: '编号'}     
			,{field:'medicalName',width:280,align:'center', title: '疾病名称' }
			,{field:'medicalNumber', align:'center',title: '患病人数', templet:'#groupid'}
		
	            ]]
	            ,page: false
	          });

	table.on('row(medicalNumTable)', function (obj){
		console.log(obj)
		var value={};
		value.condition = obj.data;
		value.year=selectedYear

			layer.open({
				type: 2
				, title: value.condition .medicalName+'病例详情'
				, content: $WEB_ROOT_PATH + '/medical/listCondition'
				// , content: $WEB_ROOT_PATH + '/bigDataAntiFraud/illnesisUseItemViolationInfo'
				, maxmin: true
				,async:false
				, area: ['1020px', '485px']
				,success: function(layero, index) {
					var iframeWindow = window['layui-layer-iframe' + index];
					//向此iframe层方法 传递参数
					iframeWindow.child(JSON.stringify(value));
				}
			});
	});



	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });

layui.use('laydate', function() {

	var laydate = layui.laydate;


	laydate.render({
		elem:'#year',//制定元素
		type:'year',
		value:thisYear(),
		max:thisYear(),//规定时间期限
		position:'absolute',
		done:function(value){//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
			selectedYear=value
			layui.table.reload('medicalNumTable', {
				where: {"medical.status":value}
				,page: { curr: 1}
			});
		}
	});

});
function thisYear(){
	var myDate = new Date();
	var tYear = myDate.getFullYear();
	return tYear;
}
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','echarts'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	   
	    
           option = {
                title:{
                    text:'住院天数频次图'
                },
                tooltip:{},
                xAxis:{
                    data:["1","2","3","4","5","6","7","8","9","10",
                    	"11","12","13","14","15","16","17","18","19","20",
                    	"21","22","23","24","25","26","27","28","29","30",
                    	"31","32","33","34","35","36","37","38","39","40",
                    	"41","42","43","44","45","46","47","48","49","50",
                    	"51","52","53","54","55","56","57","58","59","60",
                    	"61","62","63","64","65","66","67","68","69","70",
                    	"71","72"]
                },
                yAxis:{

                },
                series:[{
                    name:'频次',
                    type:'line',
                    data:[0,0,0,0,0,6,5,8,6,2
                    	,0,0,0,0,2,0,0,3,1,0
                    	,0,0,1,0,2,1,0,2,1,0
                    	,0,0,1,0,0,0,0,0,0,0
                    	,0,0,0,0,0,0,0,0,0,0
                    	,0,0,0,0,0,0,0,0,0,0
                    	,0,0,0,0,0,0,0,0,0,0
                    	,0,1]
                }]
            };
            //初始化echarts实例
              myChart = layui.echarts.init($('#main')[0]);

            //使用制定的配置项和数据显示图表
            myChart.setOption(option);

	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
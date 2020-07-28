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
	            ,url: $WEB_ROOT_PATH+'/app//js/bigDataAntiFraud/abnormalStay.json'
	            ,cellMinWidth: 80
	             ,height: 425
	            //,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	  {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		             /* ,{field:'cityName', title: '城市名称',width:90,align:'center'}*/
		              ,{field:'orgName', title: '机构名称',width:180,align:'center'}
		              ,{field:'orgLevel', title: '医院等级',width:90,align:'center'}
		              ,{field:'abnormalType', title: '问题类型',width:90,align:'center'}
		              ,{field:'superviseItem', title: '监管对象',width:90,align:'center'}
		              ,{field:'findTime', title: '发现时间',width:105,align:'center'}
		              ,{field:'superviseCost', title: '可疑总金额',width:110,align:'center'}
		              ,{field:'supervisePay', title: '可疑报销金额',width:120,align:'center'}
		              ,{field:'patientId', title: '患者ID',width:130,align:'center'}
		              ,{field:'diagnose', title: '诊断疾病',width:100,align:'center'}
		              ,{field:'age', title: '年龄',width:70,align:'center'}
		              ,{field:'stayDay', title: '连续住院天数',width:120,align:'center'}
		              ,{field:'stayNumber', title: '连续住院次数',width:120,align:'center'}
		            ]]
	           
		    		,page: true
		          });
	    
	 option = {
	    	    title: {
	    	        text: '住院时序图',
	    	    },
	    	    tooltip : {},
	    	    toolbox: {},
	    	    calculable : true,
	    	    xAxis : [
	    	        {
	    	            type : 'value',
	    	            	data:["30","60","90","120"]
	    	        }
	    	    ],
	    	    yAxis : [
	    	        {
	    	            type : 'category',
	    	            data:["连续住院"]
	    	      /*  data:["第一次","第二次","第三次","第四次","第五次","第六次","第七次","第八次","第九次"]*/
	    	        }
	    	    ],
	    	    series : [
	    	        {
	    	        	name: '第一次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[12]
/*	    	        data:[12,12,12,12,12,12,12,12,12]
*/	    	        },
	    	        {
	                    name: '第二次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[18]
/*data:[18,18,18,18,18,18,18,18,18]
*/	    	        },
	    	        {
	                    name: '第三次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[19]
/*data:[19,19,19,19,19,19,19,19,19]
*/	    	        },
	    	        {
	                    name: '第四次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[20]
/*data:[20,20,20,20,20,20,20,20,20]
*/	    	        },
	     	        {
	                    name: '第五次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[9]
/*data:[9,9,9,9,9,9,9,9,9]
*/	    	        }
	    	        ,
	    	        {
	    	        	name: '第六次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[15]
/*	    	        data:[15,15,15,15,15,15,15,15,15]
*/	    	        }
	    	        ,
	    	        {
	    	        	name: '第七次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[7]
/*	    	        data:[7,7,7,7,7,7,7,7,7]
*/	    	        }
	    	        ,
	    	        {
	    	        	name: '第八次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[12]
/*	    	        data:[12,12,12,12,12,12,12,12,12]
*/	    	        }
	    	        ,
	    	        {
	    	        	name: '第九次',
	    	            type:'bar',
	    	            stack: '天数',
	    	            data:[5]
/*	    	        data:[5,5,5,5,5,5,5,5,5]
*/	    	        }
	    	    ]
	    	    
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
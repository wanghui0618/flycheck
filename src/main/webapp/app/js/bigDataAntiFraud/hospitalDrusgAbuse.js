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
	            ,url: $WEB_ROOT_PATH+'/app//js/bigDataAntiFraud/hospitalDrugsAbuse.json'
	            ,cellMinWidth: 80
	             ,height: 425
	            //,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	  {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'hosp', title: '医院名称',align:'center'}
		              ,{field:'wenTiType', title: '问题类型',align:'center'}
		              ,{field:'jianguan', title: '监管对象',width:90,align:'center'}
		              ,{field:'money', title: '可疑总金额',align:'center'}
		              ,{field:'money1', title: '可疑报销金额',width:100,align:'center'}
		              ,{field:'findTime', title: '发现时间',width:100,align:'center'}
		              
		              ,{field:'drugs', title: '治疗疾病',width:110,align:'center'}
		              ,{field:'propect', title: '一场药品组',width:120,align:'center'}
		              ,{field:'month', title: '异常年份',width:130,align:'center'}
		              ,{field:'renci', title: '涉及人次',width:90,align:'center'}
		              ,{field:'biLi', title: '异常药品组使用比例',width:80,align:'center'}
		              ,{field:'biLi1', title: '全市该使用比例',width:90,align:'center'}
		            ]]
	           
		    		,page: true
		          });
	    
	   
	    option = {
			    title : {
			        text: '医院项目滥用',
			       
			    },
			    tooltip : {
			        trigger: 'axis',
			        showDelay : 0,
			        formatter : function (params) {
			            if (params.value.length > 1) {
			                return params.seriesName + ' :<br/>'
			                   + params.value[0] + '%' 
			                   + params.value[1] + '%';
			            }
			            else {
			                return params.seriesName + ' :<br/>'
			                   + params.name + ' : '
			                   + params.value + ' ';
			            }
			        },  
			        axisPointer:{
			            show: true,
			            type : 'cross',
			            lineStyle: {
			                type : 'dashed',
			                width : 1
			            }
			        }
			    },
			    legend: {
			        data:['比例']
			    },
			    toolbox: {
			        
			    },
			    xAxis : [
			        {
			            type : 'value',
			            name:'患者人数%',
			            data: [0,20,40,60,80],
			            axisLabel : {
			                formatter: '{value}'
			            }
			        }
			    ],
			    yAxis : [
			        { 
			        	type : 'value',
			            name:'使用比例%',
			            data: [0,20,40,60,80],
			            axisLabel : {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series : [
			        
			        {
			            name:'比例',
			            type:'scatter',
			            data: [[0, 8], [50, 15], [43, 22], [81, 17], [6, 12],
			               [25,27],[74,17],
			              
			            ],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        }
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

//初始化
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','element'], function() {
    var $ = layui.$
        , form = layui.form
        , table = layui.table
    , element = layui.element;
    
    var url=$WEB_ROOT_PATH+"/dhccApi/home/regionalfunds/bigDataHomepage";
    $.post(url,function(data){
    	console.log(data);
    	yiri=parseInt(data.yiRi);
    	erri=parseInt(data.erRi);
    	sanri=parseInt(data.sanRi);
    	siri=parseInt(data.siRi);
    	wuri=parseInt(data.wuRi);
    	liuri=parseInt(data.liuRi);
    	qiri=parseInt(data.qiRi);
    	zhen=parseInt(data.menFeiYong/10000);
    	te=parseInt(data.menTeTotal/10000);
    	yuan=parseInt(data.zhuFeiYong/10000);
    	yi=parseInt(data.yiJi/10000);
    	er=parseInt(data.erJi/10000);  
    	san=parseInt(data.sanJi/10000);
    	shi=parseInt(data.shiShu/10000);
    	xian=parseInt(data.xianShu/10000);
    	qu=parseInt(data.quShu/10000);
    	yaopinfei=parseInt(data.menYao/10000);
    	zhenliaofei=parseInt(data.menZhenliao/10000);
    	haocaifei=parseInt(data.menHao/10000);
    	menZhenRenCi=data.menZhenRenCi;
    	 document.getElementById("menZhenRenCi").innerText = data.menZhenRenCi;
    	 document.getElementById("menTeRenCi").innerText = data.menTeRenCi;
    	 document.getElementById("peopleNumber").innerText = data.zuoRiJiuZhen;
    	 document.getElementById("benZhouRenShu").innerText = data.benZhouRenShu;
    	 document.getElementById("ruYuanRenCi").innerText = data.ruYuanRenCi;
    	 document.getElementById("chuYuanRenCi").innerText = data.zuoRiChuYuan;
    	 document.getElementById("menZhenShouShu").innerText = data.menZhenShouShu;
    	 document.getElementById("zhuYuanShouShu").innerText = data.zhuYuanShouShu;
    	 document.getElementById("menYao").innerText = data.menYaoNum;
    	 document.getElementById("menHao").innerText = data.menHaoNum;
    	 document.getElementById("menZhenliao").innerText = data.menZhenliaoNum;
    	 document.getElementById("zhuYao").innerText = data.zhuYaoNum;
    	 document.getElementById("zhuHao").innerText = data.zhuHaoNum;
    	 document.getElementById("zhuZhenliao").innerText = data.zhuZhenliaoNum;
    	 document.getElementById("zongFeiYong").innerText = parseInt(data.zongFeiYong/10000)+"万元";
    	 document.getElementById("zhouShouRu").innerText = parseInt(data.menFeiYong/10000);
    	 

    	 element.progress('zuorimenzhenren', GetPercent(data.menZhenRenCi,data.zuoRiJiuZhen));
    	 element.progress('mente', GetPercent(data.menTeRenCi,data.zuoRiJiuZhen));
    	 element.progress('zuoriruyuan', GetPercent(data.ruYuanRenCi,data.zuoRiJiuZhen));
    	 element.progress('chuYuan', GetPercent(data.zuoRiChuYuan,data.zaiYuanRenShu));
    	 element.progress('menshou', GetPercent(data.menZhenShouShu,data.shouShu));
    	 element.progress('zhushou', GetPercent(data.zhuYuanShouShu,data.shouShu));
    	 element.progress('menzhenyao', GetPercent(data.menYaoNum,data.menFeiYongNum));
    	 element.progress('menzhenhao', GetPercent(data.menHaoNum,data.menFeiYongNum));
    	 element.progress('menzhenzhenliao', GetPercent(data.menZhenliaoNum,data.menFeiYongNum));
    	 element.progress('zhuyuanyao', GetPercent(data.zhuYaoNum,data.zhuFeiYongNum));
    	 element.progress('zhuyuanhao', GetPercent(data.zhuHaoNum,data.zhuFeiYongNum));
    	 element.progress('zhuyuanzhenliao', GetPercent(data.zhuZhenliaoNum,data.zhuFeiYongNum));
    	 
    	 
    	 
    	 require.config({
    		    paths: {
    		        echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
    		    }
    		});
    			// 使用
    			require(
    		 	[
    		     	'echarts',
    		     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    		 	],
    		 	function (ec) {
    	            // 基于准备好的dom，初始化echarts实例
    	            var myChart = echarts.init(document.getElementById('main'));
    	            var names=[];//定义两个数组
    	            var nums=[];
    	       	 
    	             names.push("门诊总费用");
    				 var obj = new Object();
    				 obj.name = "门诊总费用";
    				 obj.value = zhen;
    				 nums.push(obj);
    				 
    				 names.push("门特总费用");
    				 var obj = new Object();
    				 obj.name = "门特总费用";
    				 obj.value = te;
    				 nums.push(obj);
    				 
    				 names.push("住院总费用");
    				 var obj = new Object();
    				 obj.name = "住院总费用";
    				 obj.value = yuan;
    				 nums.push(obj);
    				 console.log(nums);
    				 console.log(names);
    				 
    	    myChart.setOption({ //加载数据图表
    	    	title: {
                    text: '业务维度分析',
                    "x": '9%',
                    "y": '',
                    textStyle:{ //设置主标题风格
                   	 color:'#4679BB',//设置主标题字体颜色
                   	 fontStyle:'',//主标题文字风格
                   	 "fontSize": 10,
                   	 }
                },
    	    	tooltip : {
    	            trigger: 'item',
    	            formatter: "{b} : <br>{c}万元 <br>({d}%)"
    	        },
    	        legend: {
    	        	orient: 'vertical',
    		        x: '55%',
    		        y: '25%',
    		        itemWidth: 10,             // 图例图形宽度
    		        itemHeight: 7,
    		        textStyle:{
                        fontSize: 9
                    },
    	            data:names
    	        },
    	        calculable : false,
    	        series : [
    	            {
    	                name:'业务维度分析',
    	                type:'pie',
    	                center:['25%','55%'],
    		            radius: ['35%', '60%'],
    	                itemStyle : {
    	                    normal : {
    	                        label : {
    	                            show : false
    	                        },
    	                        labelLine : {
    	                            show : false
    	                        }
    	                    },
    	                    emphasis : {
    	                        label : {
    	                            show : true,
    	                            position : 'center',
    	                            textStyle : {
    	                                fontSize : '5',
    	                                fontWeight : 'bold'
    	                            }
    	                        }
    	                    }
    	                },
    	                data:nums
    	            }
    	        ]
    		});
    	}
    );
    
    			require(
    	    		 	[
    	    		     	'echarts',
    	    		     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	    		 	],
    	    		 	function (ec) {
    	    	            // 基于准备好的dom，初始化echarts实例
    	    	            var myChart = echarts.init(document.getElementById('main1'));
    	    	            var names=[];//定义两个数组
    	    	            var nums=[];
    	    	       	 
    	    	             names.push("一级医院");
    	    				 var obj = new Object();
    	    				 obj.name = "一级医院";
    	    				 obj.value = yi;
    	    				 nums.push(obj);
    	    				 
    	    				 names.push("二级医院");
    	    				 var obj = new Object();
    	    				 obj.name = "二级医院";
    	    				 obj.value = er;
    	    				 nums.push(obj);
    	    				 
    	    				 names.push("三级医院");
    	    				 var obj = new Object();
    	    				 obj.name = "三级医院";
    	    				 obj.value = san;
    	    				 nums.push(obj);
    	    				 
    	    	    myChart.setOption({ //加载数据图表
    	    	    	title: {
    	                    text: '级别维度分析',
    	                    "x": '9%',
    	                    "y": '',
    	                    textStyle:{ //设置主标题风格
    	                   	 color:'#4679BB',//设置主标题字体颜色
    	                   	 fontStyle:'',//主标题文字风格
    	                   	 "fontSize": 10,
    	                   	 }
    	                },
    	    	    	tooltip : {
    	    	            trigger: 'item',
    	    	            formatter: "{b} : <br>{c}万元 <br>({d}%)"
    	    	        },
    	    	        legend: {
    	    	        	orient: 'vertical',
    	    		        x: '55%',
    	    		        y: '25%',
    	    		        itemWidth: 10,             // 图例图形宽度
    	    		        itemHeight: 7,
    	    		        textStyle:{
    	                        fontSize: 9
    	                    },
    	    	            data:names
    	    	        },
    	    	        calculable : false,
    	    	        series : [
    	    	            {
    	    	                name:'级别维度分析',
    	    	                type:'pie',
    	    	                center:['25%','55%'],
    	    		            radius: ['35%', '60%'],
    	    	                itemStyle : {
    	    	                    normal : {
    	    	                        label : {
    	    	                            show : false
    	    	                        },
    	    	                        labelLine : {
    	    	                            show : false
    	    	                        }
    	    	                    },
    	    	                    emphasis : {
    	    	                        label : {
    	    	                            show : true,
    	    	                            position : 'center',
    	    	                            textStyle : {
    	    	                                fontSize : '5',
    	    	                                fontWeight : 'bold'
    	    	                            }
    	    	                        }
    	    	                    }
    	    	                },
    	    	                data:nums
    	    	            }
    	    	        ]
    	    		});
    	    	}
    	    );			
    	
    			require(
    	    		 	[
    	    		     	'echarts',
    	    		     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	    		 	],
    	    		 	function (ec) {
    	    	            // 基于准备好的dom，初始化echarts实例
    	    	            var myChart = echarts.init(document.getElementById('main3'));
    	    	            var names=[];//定义两个数组
    	    	            var nums=[];
    	    	       	 
    	    	             names.push("药品费");
    	    				 var obj = new Object();
    	    				 obj.name = "药品费";
    	    				 obj.value = yi;
    	    				 nums.push(obj);
    	    				 
    	    				 names.push("诊疗费");
    	    				 var obj = new Object();
    	    				 obj.name = "诊疗费";
    	    				 obj.value = er;
    	    				 nums.push(obj);
    	    				 
    	    				 names.push("耗材费");
    	    				 var obj = new Object();
    	    				 obj.name = "耗材费";
    	    				 obj.value = san;
    	    				 nums.push(obj);
    	    				 
    	    	    myChart.setOption({ //加载数据图表
    	    	    	tooltip : {
    	    	            trigger: 'item',
    	    	            formatter: "{b} : <br>{c}万元 <br>({d}%)"
    	    	        },
    	    	        legend: {
    	    	        	orient: 'vertical',
    	    		        x: '55%',
    	    		        y: '25%',
    	    		        itemWidth: 10,             // 图例图形宽度
    	    		        itemHeight: 7,
    	    		        textStyle:{
    	                        fontSize: 9
    	                    },
    	    	            data:names
    	    	        },
    	    	        calculable : false,
    	    	        series : [
    	    	            {
    	    	                name:'收入',
    	    	                type:'pie',
    	    	                center:['25%','55%'],
    	    		            radius: ['35%', '60%'],
    	    	                itemStyle : {
    	    	                    normal : {
    	    	                        label : {
    	    	                            show : false
    	    	                        },
    	    	                        labelLine : {
    	    	                            show : false
    	    	                        }
    	    	                    },
    	    	                    emphasis : {
    	    	                        label : {
    	    	                            show : true,
    	    	                            position : 'center',
    	    	                            textStyle : {
    	    	                                fontSize : '5',
    	    	                                fontWeight : 'bold'
    	    	                            }
    	    	                        }
    	    	                    }
    	    	                },
    	    	                data:nums
    	    	            }
    	    	        ]
    	    		});
    	    	}
    	    );			
    			
    			require(
    	    		 	[
    	    		     	'echarts',
    	    		     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	    		 	],
    	    		 	function (ec) {
    	    	            // 基于准备好的dom，初始化echarts实例
    	    	            var myChart = echarts.init(document.getElementById('main2'));
    	    	            var names=[];//定义两个数组
    	    	            var nums=[];
    	    	       	 
    	    	             names.push("市属医院");
    	    				 var obj = new Object();
    	    				 obj.name = "市属医院";
    	    				 obj.value = shi;
    	    				 nums.push(obj);
    	    				 
    	    				 names.push("县属医院");
    	    				 var obj = new Object();
    	    				 obj.name = "县属医院";
    	    				 obj.value = xian;
    	    				 nums.push(obj);
    	    				 
    	    				 names.push("区属医院");
    	    				 var obj = new Object();
    	    				 obj.name = "区属医院";
    	    				 obj.value = qu;
    	    				 nums.push(obj);
    	    				 
    	    	    myChart.setOption({ //加载数据图表
    	    	    	title: {
    	                    text: '归属维度分析',
    	                    "x": '9%',
    	                    "y": '',
    	                    textStyle:{ //设置主标题风格
    	                   	 color:'#4679BB',//设置主标题字体颜色
    	                   	 fontStyle:'',//主标题文字风格
    	                   	 "fontSize": 10,
    	                   	 }
    	                },
    	    	    	tooltip : {
    	    	            trigger: 'item',
    	    	            formatter: "{b} : <br>{c}万元 <br>({d}%)"
    	    	        },
    	    	        legend: {
    	    	        	orient: 'vertical',
    	    		        x: '55%',
    	    		        y: '25%',
    	    		        itemWidth: 10,             // 图例图形宽度
    	    		        itemHeight: 7,
    	    		        textStyle:{
    	                        fontSize: 9
    	                    },
    	    	            data:names
    	    	        },
    	    	        calculable : false,
    	    	        series : [
    	    	            {
    	    	                name:'归属维度分析',
    	    	                type:'pie',
    	    	                center:['25%','55%'],
    	    		            radius: ['35%', '60%'],
    	    	                itemStyle : {
    	    	                    normal : {
    	    	                        label : {
    	    	                            show : false
    	    	                        },
    	    	                        labelLine : {
    	    	                            show : false
    	    	                        }
    	    	                    },
    	    	                    emphasis : {
    	    	                        label : {
    	    	                            show : true,
    	    	                            position : 'center',
    	    	                            textStyle : {
    	    	                                fontSize : '5',
    	    	                                fontWeight : 'bold'
    	    	                            }
    	    	                        }
    	    	                    }
    	    	                },
    	    	                data:nums
    	    	            }
    	    	        ]
    	    		});
    	    	}
    	    );
    			////折线图
    			require(
    	    		 	[
    	    		     	'echarts',
    	    		     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	    		 	],
    	    		 	function (ec) {
    	    	            // 基于准备好的dom，初始化echarts实例
    	    		 		var myChart = echarts.init(document.getElementById('main4'));
    	    		 		var cost=[];
    	    		 		cost.push(qiri);cost.push(liuri);cost.push(wuri);cost.push(siri);cost.push(sanri);cost.push(erri);cost.push(yiri);
    	    				 
    	    	    myChart.setOption({

    	                xAxis: {
    	                    type: 'category',
    	                    boundaryGap: false,
    	                    data: ['一日', '二日', '三日', '四日', '五日', '六日', '七日']
    	                },
    	                yAxis: {
    	                    type: 'value'
    	                },
    	                grid: {
    	                    x:  50,
    	                    x2: 15,
    	                    y: 30,
    	                    y2: 30
    	                },
    	                series: [{
    	                    data: cost,
    	                    
    	                    type: 'line',
    	                    /*areaStyle: {
    	                      normal: {
    	                   	    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    	                              { offset: 0, color: "LightSalmon" },
    	                              { offset: 0.5, color: "pink" },
    	                              { offset: 1, color: "Seashell" }
    	                            ])
    	                             color:'#E77D65' 
    	                      }
    	                    },*/
    	                    itemStyle : { 
    	                   	 normal: {
    	                   		 label : {
    	                   			 show: true,
    	                   			 textStyle: {
    	                   			        color: '#E77D65',
    	                   			        fontSize:'9',
    	                   			    }
    	                   			 }
    	                        }
    	                    },
    	                    
    	                }]
    	    	    });
    	    	}
    	    );	
    			
  });

    

});

var form;
var table;
var $;
var arrList;
var echartsAmount;
var echartsDetail;
var echartsRegion;
var echartsHospital;
var echartsCase;

layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','echarts','laydate'], function(){
	laydate=layui.laydate;
	form = layui.form;
	table = layui.table;
	$ = layui.$;

	echartsAmount = layui.echarts.init($('#amount')[0]);
	echartsCase = layui.echarts.init($('#case')[0]);
	echartsDetail = layui.echarts.init($('#detail')[0]);
	echartsRegion = layui.echarts.init($('#region')[0]);
	echartsHospital = layui.echarts.init($('#hospital')[0]);

	laydate.render({
		elem: '#firstTime'
			,type:"year"
				,trigger:'click'
					,value: new Date() 
	                  ,isInitValue:true
	                    ,done:function(value){  
		firstTableChange(value);
	}
	}); 
	firstTableChange();
});
function firstTableChange(value){

	echartsAmount.clear();
	echartsCase.clear();
	echartsDetail.clear();
	echartsRegion.clear();
	echartsHospital.clear();


	var date =new Date();
	if(value==null|value==''){
		value=date.getFullYear();
	}
	//amount
	urlAmount=$WEB_ROOT_PATH+'/dhccApi/countUpViolation/countUpViolation/violationAmount?countUpViolationVo.year='+value;
	//case
	urlCase=$WEB_ROOT_PATH+'/dhccApi/countUpViolation/countUpViolation/violationCase?countUpViolationVo.year='+value;
	//detail
	urlDetail=$WEB_ROOT_PATH+'/dhccApi/countUpViolation/countUpViolation/violationDetail?countUpViolationVo.year='+value;
	//region
	urlRegion=$WEB_ROOT_PATH+'/dhccApi/countUpViolation/countUpViolation/violationByRegion?countUpViolationVo.year='+value;

	postReq(urlAmount,"",function(data){
		if(data.size==0){
		}else{
			var arrStatus = new Array();
			for(var key in data ){			
				var	amount = data[key];
				arrStatus.push(key);		
			} 	

			var optionAmount = {
					color: [ '#00AA00','#FFCC00', '#DD0000'],
					title : {
						text:""
							,x:'center' ,
							y:'top',
							textStyle :{fontSize: 25}
					},
					tooltip : {
						formatter:  function (a) {  
							console.log(a);
                         return a[1]+"金额：<br/>"+(a[2]/1000).toFixed(2)+"万("+a[3]+"%)";
                        }
					},
					legend: {
						/*orient : 'vertical',*/
						x : 'center',
						y:'top',
						data:[arrStatus[2],arrStatus[1],arrStatus[0]]
					},
					calculable : true,
					series : [   
						{
							type:'pie',
							radius : '65%',
							center: ['50%', '60%'],
							startAngle:90,
							axisLine:{show : true} ,
							data:[ 
								{value:data[arrStatus[2]], name:arrStatus[2]},
								{value:data[arrStatus[1]], name:arrStatus[1]},
								{value:data[arrStatus[0]], name:arrStatus[0]}   		           
								]
						}
						]
			};

			echartsAmount.setOption(optionAmount); 
		}
	}, function(){
	}, {
		skipHidden : false
	});

	postReq(urlCase,"",function(data){
		if(data.size==0){
		}else{
			var arrStatus = new Array();
			for(var key in data ){			
				var	caseNum = data[key];
				arrStatus.push(key);		
			} 	
			var optionCase = {
					color: [ '#00AA00','#FF9900', '#3366FF'],
					title : {
						text:""
							,x:'center' ,
							y:'top',
							textStyle :{fontSize: 25}
					},
					tooltip : {
						formatter:  function (a) {  
							console.log(a);
                         return a[1]+"金额：<br/>"+(a[2]/1000).toFixed(2)+"万("+a[3]+"%)";
                        }
					},
					legend: {
						/*orient : 'vertical',*/
						x : 'center',
						y:'top',
						data:[arrStatus[2],arrStatus[1],arrStatus[0]]
					},
					calculable : true,
					series : [   
						{
							type:'pie',
							radius : '65%',
							center: ['50%', '60%'],
							startAngle:90,
							axisLine:{show : true} ,
							data:[ 
								{value:data[arrStatus[2]], name:arrStatus[2]},
								{value:data[arrStatus[1]], name:arrStatus[1]},
								{value:data[arrStatus[0]], name:arrStatus[0]}   		           
								]
						}
						]
			};
			echartsCase.setOption(optionCase); 
		}
	}, function(){
	}, {
		skipHidden : false
	});   

	postReq(urlDetail,"",function(data){
		if(data.size==0){
		}else{
			var arrStatus = new Array();
			for(var key in data ){			
				var	detail = data[key];
				arrStatus.push(key);		
			} 	

			var optionDetail = {
					color: [ '#999999','#FF9900', '#3366FF'],
					title : {
						text:""
							,x:'center' ,
							y:'top',
							textStyle :{fontSize: 25}
					},
					tooltip : {
						formatter:  function (a) {  
							console.log(a);
                         return a[1]+"金额：<br/>"+(a[2]/1000).toFixed(2)+"万("+a[3]+"%)";
                        }
					},
					legend: {
						/*orient : 'vertical',*/
						x : 'center',
						y:'top',
						data:[arrStatus[2],arrStatus[1],arrStatus[0]]
					},
					calculable : true,
					series : [   
						{
							type:'pie',
							radius : '65%',
							center: ['50%', '60%'],
							startAngle:90,
							axisLine:{show : true} ,
							data:[ 
								{value:data[arrStatus[2]], name:arrStatus[2]},
								{value:data[arrStatus[1]], name:arrStatus[1]},
								{value:data[arrStatus[0]], name:arrStatus[0]}   		           
								]
						}
						]
			};
			echartsDetail.setOption(optionDetail); 
		}
	}, function(){
		/*$CommonUI.alert("失败!");*/
	}, {
		skipHidden : false
	});   

	postReq(urlRegion,"",function(data){
		if(data.size==0){
		}else{
			var arrRegion= new Array();
			var doubtfulAmount= new Array();
			var violationAmount= new Array();
			var normalAmount= new Array();
			for(var key in data ){			
				arrRegion.push(key);	
				var Region = data[key];
				violationAmount.push(Region[0]/10000);	
				doubtfulAmount.push(Region[1]/10000);	
				normalAmount.push(Region[2]/10000);	
			} 	


			var optionRegion = {
					color: [ '#00AA00','#FFCC00', '#DD0000'],
					legend: {
						data:['正常','疑似违规','违规']  ,
						selected: {
				            '正常' : false,
				            '疑似违规' : false
				        }
					},
					toolbox: {
						feature : {
							mark : {show: true}
						}
					},
					tooltip : {
						formatter:  function (a) {      
                         return a[0]+"金额："+a[2].toFixed(2) +"万";
                        }
					},
					calculable : true,
					xAxis : [
						{
							type : 'category',
							data : arrRegion,
							axisTick: {show: false},
							axisLabel: {
								interval:0,
								rotate:16,
								clickable: true
							}

						}
						],
						yAxis : [
							{   name:'金额（万）',
								type : 'value'
							}
							],
							series : [
								{
									name:'正常',
									type:'bar',
									stack: '总量',
									barMaxWidth:20, 
									itemStyle : {
										normal : {
											label : {
												textStyle : {
													align : 'center',
													baseline : 'middle',
													fontFamily : '微软雅黑',
													fontSize : 30,
													fontWeight : 'bolder'
												}
											}
										}
								  },data:normalAmount
								},
								{
									name:'疑似违规',
									type:'bar',
									stack: '总量',
									barMaxWidth:20, 
									itemStyle : {
										normal : {
											label : {
												textStyle : {
													align : 'center',
													baseline : 'middle',
													fontFamily : '微软雅黑',
													fontSize : 30,
													fontWeight : 'bolder'
												}
											}
										}
								  },data:doubtfulAmount
								},
								{
									name:'违规',
									type:'bar',
									stack: '总量',
									barMaxWidth:20, 
									itemStyle : {
										normal : {
											label : {
												textStyle : {
													align : 'center',
													baseline : 'middle',
													fontFamily : '微软雅黑',
													fontSize : 30,
													fontWeight : 'bolder'
												}
											}
										}
								  },data:violationAmount
								}
								]
			};

			echartsRegion.setOption(optionRegion); 
			echartsRegion.on('click', function(title){
				region(title["name"],value);
			}); 
			region(arrRegion[0],value);
		}
	}, function(){
	}, {
		skipHidden : false
	});
}

function region(name,value){
	//hospital
	urlHospital=$WEB_ROOT_PATH+'/dhccApi/countUpViolation/countUpViolation/violationByHospital?countUpViolationVo.year='+value+'&countUpViolationVo.handdingInsName='+name;

	postReq(urlHospital,"",function(data){
		if(data==null||data==""){
		}else{
			var arrHospital= new Array();
			var doubtfulAmount= new Array();
			var violationAmount= new Array();
			var normalAmount= new Array();
			for(var key in data ){			
				arrHospital.push(key);	
				var Hospital = data[key];
				violationAmount.push(Hospital[0]/10000);	
				doubtfulAmount.push(Hospital[1]/10000);	
				normalAmount.push(Hospital[2]/10000);	
			} 	

			var optionHospital = {
					color: [ '#3366FF','#999999', '#FF9900'],
					legend: {
						data:['正常','疑似违规','违规'] ,
						selected: {
				            '正常' : false,
				            '疑似违规' : false
				        }
					},
					toolbox: {
						show : true,
					},
					tooltip : {
						formatter:  function (a) {      
                         return a[0]+"金额："+a[2].toFixed(2) +"万";
                        }
					},
					calculable : true,
					xAxis : [
						{
							type : 'category',
							data :arrHospital,
							axisTick: {show: false},
							axisLabel: {
								interval:0,
								rotate:16
							}
						}
						],
						yAxis : [
							{      name:'金额（万）',
								type : 'value'
							}
							],
							series : [
								{
									name:'正常',
									type:'bar',
									stack: '总量',
									barMaxWidth:20, 
									itemStyle : {  color :'#000000',normal: {label : {show: false, position: 'insideRight'}}},
							        data:normalAmount
								},
								{
									name:'疑似违规',
									type:'bar',
									stack: '总量',
									barMaxWidth:20, 
									itemStyle : {  color :'#000000',normal: {label : {show: false, position: 'insideRight'}}},
									data:doubtfulAmount
								},
								{
									name:'违规',
									type:'bar',
									stack: '总量',
									barMaxWidth:20, 
									itemStyle : {  color :'#000000' ,normal: {label : {show: false, position: 'insideRight'}}},
									data:violationAmount
								}
								]
			};

			echartsHospital.setOption(optionHospital); 
		}
	}, function(){
	}, {
		skipHidden : false
	});

}

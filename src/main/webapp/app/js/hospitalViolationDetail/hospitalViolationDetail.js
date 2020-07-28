//全局变量
var status_qj='';//机审0  终审1
var on='';//orgName
var cn='';//cityName
var status='0';//机审 0 终审 1
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
	var str = url.substr(1);
	strs = str.split("&");
	for (var i = 0; i < strs.length; i++) {
	theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
	}
	}
	return theRequest;
}
//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table
	,laydate = layui.laydate;
	
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
	        
	
	var a = new Array("日", "一", "二", "三", "四", "五", "六");
	var date = new Date();
	var today_nian = date.getFullYear();
	var today_yue = date.getMonth()+1;
	var today_day = date.getDate();
	var week = date.getDay();
	var str = today_nian+"年"+today_yue+"月"+today_day+"日&nbsp;星期"+ a[week];
	$("#dateweek").html(str);
	
	var Request = GetRequest()
	var statusUrl = Request["status"];
	status_qj=statusUrl;
	//加载城市下拉字典
	 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
				function(data){
		     		var  dataList= data.dictList;
		     		for(var i=0 ;i<dataList.length;i++){
		     			var mm="<option value='"+dataList[i].text+"'>"+dataList[i].text+"</option>";
		     			$("#city").append(mm); 
		     		}
		     	form.render('select');
	});
	//加载医院下拉字典
     $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
         function(data){
             var orgs=data.pageModel.rows
             //var  dataList= data.dictList;
             //var org_save=JSON.stringify(orgs);//解析为字符串
             //localStorage.clear();
            
             //localStorage.setItem('org_save',org_save);//存入浏览器数据库
             for(var i=0 ;i<orgs.length;i++){
                 var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                 $("#zyOrgName").append(mm);
             }
             form.render('select');
        });
   
	table.render({
		elem: '#hospitalViolationTable1'
		,url: $WEB_ROOT_PATH+'/dhccApi/hospitalviolationdetail/hospitalViolationDetail/listVo'
		 ,height: document.documentElement.clientHeight-110
		,limit:10
		,where: {  ilegalChild: '1' ,"status":statusUrl }
		,cols: [[
			{type: 'numbers', title: '序号' }
			,{field:'projectType', title: '项目类型'}
			,{field:'itemName',width:300, title: '项目名称'}
			,{field:'caseLoad',  title: '明细数'}
			,{field:'violationCount',  title: '违规占比'}
			,{field:'yearr',  title: '年份',hidden:true}
		]]
	,page: true
	,done:function(res){
		 $('tr').eq(1).css("background-color","#C0C0C0");
    	var result=res.data;
    	
    	if(result.length>0){
    		console.log(result);
    		reloadTable(result[0].itemName,status,result[0].yearr);
    		//全局变量赋值
   		 	on=result[0].itemName;
   		 $("#yiyuan").html(on);
    	}
    	
	 }
	});
	
	 //监听行单击事件（单击事件为：rowDouble）
	  table.on('tool(hospitalViolationTable1)', function(obj){
		
	    var data = obj.data;
	    if(obj.event === 'setSign'){
	    	//$("#yl").attr("lay-href",'/piccbid/medical/medical/jpp?orgCode='+data.orgCode+'&type=1');
	    	//$("#yl").trigger('click') ;
	    	var index = layui.layer.open({
	            title: "智能审核病例终审",
	            type: 2,
	            content: '/piccbid/medical/medical/jpp?orgCode='+data.orgCode+'&type=1'
	        });
	        layui.layer.full(index);
	    }
	    if(obj.event === 'play'){
	    	event.stopPropagation();
	    	var param = {seriesIndex:"1", name:data.cityName+"-"+data.orgName};
	    	eConsole(param);
	    }
	    
	  });

	//监听搜索
	form.on('submit(LAY-user-front-search1)', function(data){
		var field = data.field;
		console.log(field);
		//执行重载
		layui.table.reload('hospitalViolationTable1', {
			where: field
		});
	});
	
	
	 table.on('row(hospitalViolationTable1)', function(obj){
		
		 $("tr").css("background-color",""); 
         $(this).css("background-color","#C0C0C0"); 
         
		 var result=obj.data;
		 var orgName=result.itemName;
		 var yearr=result.yearr;
		 //全局变量赋值
		 on=orgName;
		 $("#yiyuan").html(on);
		 reloadTable(orgName,status,yearr)
	 });
	//导出
	form.on('submit(LAY-user-front-export)', function(data){
		var field = data.field;
		//执行重载
		window.open($WEB_ROOT_PATH+'/dhccApi/hospitalviolation/hospitalViolation/exportExcelToSelf?status='+statusUrl);
	});
	 
	//按钮事件绑定底层方法-勿动
	$('layui-btn layuiadmin-btn-useradmin').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});



//reload医院违规TOP10
function reloadTableHospital(status) {/*
	 
	 require(['echarts','echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		 	  ],
	          function (echarts) {
					echarts.init(document.getElementById('mainHos')).dispose();//销毁前一个实例
					memoBar = echarts.init(document.getElementById('mainHos'));//构建下一个实例
					  
	                 var option = {
	                     tooltip: {
	                         show: true
	                     },
	                     padding: [0, 0, 10, 10],  // 位置
	                     legend: {
	                         padding: 10,    // [5, 10, 15, 20]
	                         itemGap: 20,
	                         left:'right',
	                         data:['医院违规记录数']
	                     },
	                     xAxis : [
	                         {
	                             type : 'category',
	                             data : [],
	                             axisLabel : {//坐标轴刻度标签的相关设置。
	                                 interval:0,
	                                 rotate:"20"
	                             }
	                         }
	                     ],
	                     yAxis : [
	                         {
	                             type : 'value'
	                         }
	                     ],
	                     series : [
	                         {
	                             "name":"违规数量",
	                             "type":"bar",
	                             "data":[],
	                             barWidth:16,
	                             itemStyle:{
	                                 normal:{
	                                     color:'#419bf9'
	                                 }
	                             }
	                         }
	                     ]
	                 };
	                 // 为echarts对象加载数据 
	                   $.ajax({
	    			         url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolationdetail/hospitalViolationDetail/echartList",
	    			         type : "post",		
	    			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
	    			         data:{"itemName":orgName},
	    			         dataType : "json",	
	    			         success : function(result) {
	    			        	 var hvdata=result.data;
	    			        	 if (hvdata != null && hvdata.length > 0) {
	    			        		 var k;
	    			        		 if(hvdata.length>10){
	    			        			 k=10;
	    			        		 }else{
	    			        			 k=hvdata.length;
	    			        		 }
	    			        		 for(var i=0;i<k;i++){ 
	    				                   option.series[0].data.push(hvdata[i].shu);
	    				                   option.xAxis[0].data.push(hvdata[i].medicalInsName);
	    				             } 
	    			        		 memoBar.setOption(option); 
	    			        	 }
	    			         }
	                   });
	           
	                 //下面是需要添加的方法内容  
	                 //点击柱状图跳转相应页面的功能，其中param.name参数为横坐标的值   
	                 var ecConfig = require('echarts/config');  
	                 
	                 memoBar.on(ecConfig.EVENT.CLICK, eConsole);          
	                   
	                   
	 }); 
	 
	 
	
	 
	*/}

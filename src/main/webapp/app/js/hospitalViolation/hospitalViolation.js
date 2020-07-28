//全局变量
var status_qj='';//机审0  终审1
var on='';//orgName
var cn='';//cityName
var oc='';//orgCode
var handdingInsNameAll='';
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
$(function(){

});

//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;

	var a = new Array("日", "一", "二", "三", "四", "五", "六");
	var date = new Date();
	var today_nian = date.getFullYear();
	var today_yue = date.getMonth()+1;
	var today_day = date.getDate();
	var week = date.getDay();
	var str = today_nian+"年"+today_yue+"月"+today_day+"日&nbsp;星期"+ a[week];
	$("#dateweek").html(str);

	var Request = GetRequest();
	var statusUrl = Request["status"];
	var comType = Request["type"];
	var handdingInsName = Request["tcqName"];
	handdingInsNameAll=handdingInsName;//全局变量负值
	//转码
	//alert(handdingInsName);
	//handdingInsName=decodeURI(handdingInsName);
	//alert(handdingInsName);
	//handdingInsName=encodeURI(encodeURI(handdingInsName)); 
	status_qj=statusUrl;
	//加载城市下拉字典
	/*$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
			function(data){
		var  dataList= data.dictList;
		for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].text+"'>"+dataList[i].text+"</option>";
			$("#city").append(mm); 
		}
		form.render('select');
	});*/
	//加载医院下拉字典
	/*$.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
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
	});*/

	medicalStatusStatistics();//待审核状态统计
	table.render({
		elem: '#hospitalViolationTable'
			,url: $WEB_ROOT_PATH+'/dhccApi/hospitalviolation/hospitalViolation/listVo'
			,height: 251
			,limit:5
			,where: {  ilegalChild: '1'  }
	,cols: [[
		{type: 'numbers', title: '序号' }
		/*,{field:'cityName',title: '城市名称'}*/
		,{field:'orgCode',hide:true, title: '医院编号'}
		,{field:'orgName',width:300, title: '医院名称'}
		,{field:'vioCount', title: '案件数量'}
		]]
	,page: true
	});
	table.render({
		elem: '#hospitalViolationTable1'
			,url: $WEB_ROOT_PATH+'/dhccApi/hospitalviolation/hospitalViolation/listVo'
			,height: document.documentElement.clientHeight-80
			,limit:10
			,where: { ilegalChild: '1' ,"status":statusUrl,"comType":comType,"handdingInsName":handdingInsName}
	,cols: [[
		{type: 'numbers', title: '序号' }
		/*,{title:'操作', align:'center',templet: function (d) {
					return '<a class="layui-btn  layui-btn-xs" lay-event="play" >查看明细</a>'
				}
			}*/
		/*,{field:'cityName',title: '城市名称'}*/
		,{field:'orgCode',hide:true, title: '医院编号'}
		,{field:'orgName', title: '医院名称'}
		,{field:'vioCount',  title: '违规病例数'}
		,{field:'susCount',  title: '可疑病例数',hide:ifHide()}
		,{field:'vioMoney',  title: '违规金额',templet: function (d) {
			if(d.vioMoney==""||d.vioMoney==null ){
				return '-';
			}else{
				return d.vioMoney;
			}
		}}
		,{field:'susMoney',  title: '可疑金额',hide:ifHide(),templet: function (d) {
			if(d.susMoney==""||d.susMoney==null ){
				return '-';
			}else{
				return d.susMoney;
			}
		}}
		
		]]
	,page: true
	,done:function(res){
		$('tr').eq(1).css("background-color","#C0C0C0");
		var result=res.data;
		if(result.length>0){
			reloadTable(result[0].orgCode,result[0].cityName,statusUrl,comType,handdingInsName);
			reloadTable1(result[0].orgCode,statusUrl,comType,handdingInsName);
			reloadTable2(result[0].orgCode,statusUrl,comType,handdingInsName);
			reloadTable3(result[0].orgCode,statusUrl,comType,handdingInsName);
			reloadTable4(result[0].orgCode,statusUrl,comType,handdingInsName);
			//全局变量赋值
			on=result[0].orgName;
			cn=result[0].cityName;
			oc=result[0].orgCode;
			$("#yiyuan").html(on);
		}

	}
	});
	
	function ifHide(){
		if(statusUrl=='1'){
			return true;
		}else{
			return false;
		}
	}

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
			var param = {seriesIndex:"1", name:data.cityName+"-"+data.orgName,};
			eConsole(param);
		}

	});

	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//console.log(field);
		//执行重载
		layui.table.reload('hospitalViolationTable', {
			where: field
		});
	});
	form.on('submit(LAY-user-front-search1)', function(data){
		var field = data.field;
		//console.log(field);
		//执行重载
		layui.table.reload('hospitalViolationTable1', {
			where: field
		});
	});

	table.on('row(hospitalViolationTable1)', function(obj){
		$("tr").css("background-color",""); 
		$(this).css("background-color","#C0C0C0"); 
		var result=obj.data;
		var orgName=result.orgName;
		var cityName=result.cityName;
		//全局变量赋值
		on=orgName;
		cn=cityName;
		oc=result.orgCode;
		$("#yiyuan").html(on);
		reloadTable(oc,cityName,statusUrl,comType,handdingInsName);
		reloadTable1(oc,statusUrl,comType,handdingInsName);
		reloadTable2(oc,statusUrl,comType,handdingInsName);
		reloadTable3(oc,statusUrl,comType,handdingInsName);
		reloadTable4(oc,statusUrl,comType,handdingInsName);
	});
	//导出
	form.on('submit(LAY-user-front-export)', function(data){
		var field = data.field;
		//执行重载
		window.open($WEB_ROOT_PATH+'/dhccApi/hospitalviolation/hospitalViolation/exportExcelToSelf?status='+statusUrl+'&handdingInsName='+handdingInsName);
	});
	sysVerify("0",form);//违规统计

	//切换下拉框--机审--终审
	$("#hospital-top10").bind("change",function(){
		// alert("qiehuan ");
		var status=$("#hospital-top10 option:selected").val();
		status_qj=status;//全局赋值
		reloadTableHospital(status);
		sysVerify(status,form);
		reloadSysViolation(status);
		if(status=='0'){
			$("#hospital-top10-href").attr("lay-href","/piccbid/hospitalviolation/hospitalViolation?status="+status);
			$("#violation-top10-href").attr("lay-href","/piccbid/violationDetail/violationDetail?status="+status);
		}else if(status=='1'){
			$("#violation-top10-href").attr("lay-href","/piccbid/violationDetail/violationDetail?status="+status);
			$("#hospital-top10-href").attr("lay-href","/piccbid/hospitalviolation/hospitalViolation?status="+status);
		}
	});
	$("#violation-top10").bind("change",function(){
		var status=$("#violation-top10 option:selected").val();
	});

	/*$("#yiyuan").click(function(){
		alert("dianjile");
		 var param = {seriesIndex:"1", name:on+"-"+cn};
		 //
		 eConsole(param);
	 });*/
	$("#yiyuan").on("click",function(){
		
		var param = {seriesIndex:"1", name:cn+"-"+on,handdingInsName:handdingInsName,data:{orgcode:oc}};
		eConsole(param);
	});
	//按钮事件绑定底层方法-勿动
	$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});

});
//明细查看点击事件
function seeDetails(p){
	event.stopPropagation();
	var param = {seriesIndex:"1", name:p[0]+"-"+p[1]};
	eConsole(param);
}

//reload医院违规TOP10
function reloadTableHospital(status) {

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
								barMaxWidth:20,
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
			url:$WEB_ROOT_PATH+"/dhccApi/hospitalviolation/hospitalViolation/listVo",
			type : "post",		
			async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			data:{"status":status},
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
						option.series[0].data.push(hvdata[i].vioCount);
						option.xAxis[0].data.push(hvdata[i].cityName+"-"+hvdata[i].orgName);
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




}
//医院违规top10柱状图点击事件
function eConsole(param) {  
	if (typeof param.seriesIndex != 'undefined') { 
		//return;
		var str=param.name;
		var s=str.split('-'); 
		//var s0=java.net.urlencoder.encode(s[0]);
		//var s1=java.net.urlencoder.encode(s[1]);
		var s0=encodeURI(encodeURI(s[0]));   
		var s1=encodeURI(encodeURI(s[1]));
		var s2=encodeURI(encodeURI(param.data.orgcode));
		var handdingInsName=param.handdingInsName;

		if(handdingInsName){
			handdingInsName=decodeURI(handdingInsName);
			handdingInsName=encodeURI(encodeURI(handdingInsName));
		}
		if(!status_qj){
			status_qj=$("#hospital-top10 option:selected").val();
		}
		//默认为初审
		if(!status_qj){
			status_qj='0';
		}

		var tit=status_qj=='0'?"事后病例初审":"事后病历终审";
		if(handdingInsName){
			var cs="/piccbid/medical/medical/jpp?cityName="+s0+"&type=1&handdingInsName="+handdingInsName+"&orgCode="+s2;
		}else{
			var cs="/piccbid/medical/medical/jpp?cityName="+s0+"&type=1&orgCode="+s2;
		}
		var zs="/piccbid/medical/medical/jpp-zs?cityName="+s0+"&type=1&orgCode="+s2;

		var urlGo=status_qj=='0'?cs:zs;

		$("#tz-hide").attr('lay-href',urlGo);
		$("#tz-hide").html(tit);
		$('#tz-hide').trigger("click");
	}  
}  
//饼状图 reload
function reloadSysViolation(status){
	//饼状图
	require(['echarts','echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		],
		function (echarts) {
		echarts.init(document.getElementById('main1')).dispose();//销毁前一个实例
		memoBar1 = echarts.init(document.getElementById('main1'));//构建下一个实例
		// 为echarts对象加载数据 
		var names=[];//定义两个数组
		var nums=[];
		$.ajax({
			url:$WEB_ROOT_PATH+"/dhccApi/medical/medical/sysStatus",
			type : "post",		
			async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			data:{"status":status},
			dataType : "json",	
			success : function(result) {
				for(var i=0;i<result.length;i++){
					names.push(result[i].wgName);
					var obj = new Object();
					obj.name = result[i].wgName;
					obj.value = parseInt(result[i].wgNum);
					nums[i]=obj;
				}
				memoBar1.setOption({ //加载数据图表
					title : {
						x:'center'
					},
					tooltip : {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						left: 'left',
						data: names
					},
					series: [{
						name: '违规数量',
						type: 'pie',
						radius : '60%',
						center:['50%','60%'],
						data: nums
					}]
				});

			}
		});

	}
	);

}
//右侧各类违规统计态拼接
function sysVerify(status,form){
	
	var div=document.getElementById("sysVerifyTableDiv");
	if(!div){
		return;
	}
	//removeAllChild(div);
	$("#sysVerifyTableDiv").empty();
	//加一个新的空table  font-size: 14px;color: #353535;width: 100%;line-height: 34px;" border="0" cellpadding="0" cellspacing="0"
	var table=document.createElement("table");
	table.setAttribute("id","sysVerify");

	table.setAttribute("border","0");
	table.setAttribute("cellpadding","0");
	table.setAttribute("cellspacing","0");
	div.appendChild(table)



	//ajax根据medical_id获取数据
	var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMedicalId";
	$.post(url,{"shzt":'zy',"status":status},function(result){
		//2.1拼接数据
		var sysVerifyVo=result.sysVerifyVo;
		if(!sysVerifyVo){
			var tr=document.createElement('tr');
			tr.innerHTML='暂时无违规信息！';
			table.appendChild(tr);
			return;
		}
		var tr=document.createElement('tr');
		var td=document.createElement('td');
		var td2=document.createElement('td');
		td.style.paddingLeft = "30px";
		td2.style.paddingLeft = "30px";
		tr.style.backgroundColor="#E8F2FF";
		td.innerHTML="违规类型";
		td2.innerHTML="违规案例数量";
		tr.appendChild(td);
		tr.appendChild(td2);
		table.appendChild(tr);

		for(var i=0;i<sysVerifyVo.length;i++){
			var tr=document.createElement('tr');
			tr.setAttribute("typevalue",sysVerifyVo[i].typeNo);
			tr.setAttribute("class",'isexistNum');
			var td=document.createElement('td');
			var td2=document.createElement('td');
			td.style.paddingLeft = "30px";
			if(i%2==0){
				tr.style.backgroundColor="#fff";
			}else{
				tr.style.backgroundColor="#F6FAFF";
			}
			switchChang(i,td,sysVerifyVo[i].typeNo,sysVerifyVo[i].typeNames);
			td2.innerHTML="<span>&#12288;&#12288;</span>"+sysVerifyVo[i].countNum+"条";
			tr.appendChild(td);
			tr.appendChild(td2);
			table.appendChild(tr);
			/*最多9条*/
			if(i>6){
				break;
			}
		}
		//为tr(存在违规条数的)添加点击事件
		$("#sysVerify").on('click','.isexistNum',function(){
			wgDetailonclick(this,form);
		});
	});
}
function removeAllChild(table1){
	while(table1.hasChildNodes()) //当div下还存在子节点时 循环继续
	{
		table1.removeChild(table.firstChild);
	}
}
function switchChang(i,td,typeNo,typeNames){
	var typeNo=parseInt(typeNo);
	if(typeNames){
		td.innerHTML=i+1+"."+typeNames;
	}else{
		td.innerHTML=i+1+"."+"其他";
	}

}
//中间违规明细点击事件
function wgDetailonclick(typeno,form){
	var t=$(typeno).attr("typevalue");

	var t=encodeURI(encodeURI(t));
	if(!status_qj){
		status_qj=$("#hospital-top10 option:selected").val();
	}

	var tit=status_qj=='0'?"事后病例初审":"事后病历终审";
	var cs="/piccbid/medical/medical/jpp?typeNo="+t+"&type=2";//2表示按违规类型跳转
	var zs="/piccbid/medical/medical/jpp-zs?typeNo="+t+"&type=2";

	var urlGo=status_qj=='0'?cs:zs;

	$("#tz-hide").attr('lay-href',urlGo);
	$("#tz-hide").html(tit);
	$('#tz-hide').trigger("click");
}
//初审、稽核、公示、终审数据统计
function medicalStatusStatistics(){
	var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/medicalStatus";
	$.post(url,function(result){
		//console.log(result[0]);
		//标签赋值
		if(result[0].sysStatus==0){
			$("#userStatus-li").removeAttr('lay-href');
			$("#userStatus").html('-');
		}else{
			$("#userStatus").html(result[0].sysStatus);
		}
		if(result[0].jh==0){
			$("#jh-li").removeAttr('lay-href');
			$("#jh").html('-');
		}else{
			$("#jh").html(result[0].jh);
		}
		if(result[0].gs==0){
			$("#gs-li").removeAttr('lay-href');
			$("#gs").html('-');
		}else{
			$("#gs").html(result[0].gs);
		}
		if(result[0].finaStatus==0){
			$("#finaStatus-li").removeAttr('lay-href');
			$("#finaStatus").html('-');
		}else{
			$("#finaStatus").html(result[0].finaStatus);
		}

	});
}
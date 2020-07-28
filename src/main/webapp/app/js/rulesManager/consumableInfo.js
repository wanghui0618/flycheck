//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	
	var laydate=layui.laydate;
	laydate.render({
		elem:'#endTime'
			,trigger:'click'
			,type:'datetime'
				,format:'yyyy-MM-dd' 
	});
	var laydate1=layui.laydate;
	laydate1.render({
		elem:'#beginTime'
			,trigger:'click'
			,type:'datetime'
				,format:'yyyy-MM-dd' 
	});
	
	
});

function child(obj) {
	var drugInfo = JSON.parse(obj);
	$("#id").val(drugInfo["id"]);
	for ( var index in drugInfo) {
		$("#" + index).val(drugInfo[index])
	}
	
	
	var code=drugInfo.cityCode;
	loadSelect(code);
	var consumableType=drugInfo.consumableType;
	$("#consumableType").find("option[value ='"+consumableType+"']").attr("selected","selected");
	var isMedicare=drugInfo.isMedicare;
	if(isMedicare=="否"){
		isMedicare="0";
	}else if(isMedicare=="是"){
		isMedicare="1";
	}
	$("#isMedicare").find("input[value ='"+isMedicare+"']").attr("checked","true");
	var validFlag=drugInfo.validFlag;
	$("#validFlag").find("input[value ='"+validFlag+"']").attr("checked","true");
	var consumableLevel=drugInfo.consumableLevel;
	$("#consumableLevel").find("input[value ='"+consumableLevel+"']").attr("checked","true");
};
//加载城市下拉字典
function loadSelect(code){
	$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
			function(data){
		var  dataList= data.dictList;
		var  form = layui.form
		for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#cityCode").append(mm); 
		}
		if(code!=null){
			$("#cityCode").find("option[value ='"+code+"']").attr("selected","selected");
		}
		form.render('select');
	});

}



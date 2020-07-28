//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	
	 var laydate1=layui.laydate;
		laydate1.render({
			elem:'#createDate'
				,trigger:'click'
				,type:'date'
					,format:'yyyy-MM-dd' 
		});
		var laydate2=layui.laydate;
		laydate2.render({
			elem:'#updateDate'
				,trigger:'click'
				,type:'date'
					,format:'yyyy-MM-dd' 
		});
		var laydate3=layui.laydate;
		laydate3.render({
			elem:'#deleteDate'
				,trigger:'click'
				,type:'date'
					,format:'yyyy-MM-dd' 
		});
		



});

function child(obj) {
	var deleteInfo = JSON.parse(obj);
	$("#id").val(deleteInfo["id"]);
	for ( var index in deleteInfo) {
		$("#" + index).val(deleteInfo[index])
	}
	
	var code=deleteInfo.cityCode;
	loadSelect(code);
	
	var dealFlag=deleteInfo.dealFlag;
	$("#dealFlag").find("option[value ='"+dealFlag+"']").attr("selected","selected");
	
	var outHosFlag=deleteInfo.outHosFlag;
	$("#outHosFlag").find("option[value ='"+outHosFlag+"']").attr("selected","selected");
	
	var seeDocType=deleteInfo.seeDocType;
	$("#seeDocType").find("option[value ='"+seeDocType+"']").attr("selected","selected");

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






//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	$("#" + index).val("1031")
	$("#" + index).val("项目与项目匹配")

});

function child(obj) {
	var drugInfo = JSON.parse(obj);
	 console.info(drugInfo);
	$("#id").val(drugInfo["id"]);
	for ( var index in drugInfo) {
		$("#" + index).val(drugInfo[index])
	}
	
	
	var code=drugInfo.cityCode;
	loadSelect(code);
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

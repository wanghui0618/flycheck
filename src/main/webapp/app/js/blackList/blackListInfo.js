//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	
/*	//监听select change
	 form.on('select(city)', function(data){
	 $("#cityName").val( $(data.elem).find("option:selected").text());
	 form.render('select');
	});
	*/

});

//信息回填
function child(obj) {
	var blackList = JSON.parse(obj);
	 console.info(blackList);
	$("#id").val(blackList["id"]);	
	for ( var index in blackList) {
		$("#" + index).val(blackList[index]);
	}

	
	var code=blackList.cityCode;
	loadSelect(code);
	
	var type=blackList.type;
	$("#type").find("option[value ='"+type+"']").attr("selected","selected");
	var scale=blackList.scale;
	$("#scale").find("option[value ='"+scale+"']").attr("selected","selected");
	
}

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
 

 
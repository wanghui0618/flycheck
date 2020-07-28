layui.config(
	{
		base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/'
	}
).extend(
	{
		index: 'lib/index' //主入口模块
	}
).use(
	['index', 'form','laydate'],
	function(){
		var form=layui.form;
		//监听select change
		 form.on('select(city)', function(data){
			$("#cityName").val($(data.elem).find("option:selected").text());
			form.render('select');
		});
	}
);
function child(obj){
	var selfPayRatio = JSON.parse(obj);
	$("#id").val(selfPayRatio["id"]);
	for (var index in selfPayRatio){
		$("#"+index).val(selfPayRatio[index]);
	}
	$("#insuranceType").find("option[value ='"+selfPayRatio.insuranceType+"']").prop("selected",true);
	var code=selfPayRatio.cityCode;
	loadSelect(code);
}
//加载城市下拉字典,根据参数为select赋值
function loadSelect(code){
	$.getJSON(
		$WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
		function(data){
			var form = layui.form;
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
				var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
				$("#city").append(mm); 
			}
			if(code!=null){
			   $("#city").find("option[value ='"+code+"']").attr("selected","selected");
			}
			form.render('select');
		}
	);
}
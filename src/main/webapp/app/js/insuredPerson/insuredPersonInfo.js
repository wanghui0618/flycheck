//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	
	//监听select change
	 form.on('select(city)', function(data){
			 //$("#cityName").attr("value",data.text);
			// alert($(data.elem).find("option:selected").text());

			$("#cityName").val( $(data.elem).find("option:selected").text());
			form.render('select');
	 
	});
	
	var laydate=layui.laydate;
	laydate.render({
		elem:'#workBeginTime'
			,trigger:'click'
			  ,type:'date'
				//,format:'yyyy-MM-dd' 
	});
	var laydate1=layui.laydate;
	laydate1.render({
		elem:'#birthday'
			,trigger:'click'
				  ,type:'date'
						//,format:'yyyy-MM-dd' 
			});
	var laydate2=layui.laydate;
	laydate1.render({
		elem:'#insuranceBeginTime'
			,trigger:'click'
				  ,type:'date'
						//,format:'yyyy-MM-dd' 
			});

});

//信息回填
function child(obj) {
	var insuredPerson = JSON.parse(obj);

	$("#id").val(insuredPerson["id"]);	
	for ( var index in insuredPerson) {
		$("#" + index).val(insuredPerson[index]);
	}
	var code=insuredPerson.cityCode;
	loadSelect(code);

	var poorSigns=insuredPerson.poorSigns;
	$("#poorSigns").find("input[value ='"+poorSigns+"']").attr("checked","true");
	var sex=insuredPerson.sex;
	$("#sex").find("input[value ='"+sex+"']").attr("checked","true");
	var maritalStatus=insuredPerson.maritalStatus;
	$("#maritalStatus").find("input[value ='"+maritalStatus+"']").attr("checked","true");
	var relocationSigns=insuredPerson.relocationSigns;
	$("#relocationSigns").find("input[value ='"+relocationSigns+"']").attr("checked","true");
	var farmerSigns=insuredPerson.farmerSigns;
	$("#farmerSigns").find("input[value ='"+farmerSigns+"']").attr("checked","true");
	
	
}


 

 //加载城市下拉字典,根据参数为select赋值
 function loadSelect(code){
	 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
			 function(data){
		 var form=layui.form;
		 var  dataList= data.dictList;
		 for(var i=0 ;i<dataList.length;i++){
			 var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			 $("#city").append(mm); 
		 }
		 if(code!=null){
			 $("#city").find("option[value ='"+code+"']").attr("selected","selected");
		 }
		 form.render('select');
	 }); 
 }

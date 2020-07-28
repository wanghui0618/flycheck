//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;




});
function child(obj) {
	var treatmentRule = JSON.parse(obj);
	$("#id").val(treatmentRule["id"]);
	for ( var index in treatmentRule) {
		$("#" + index).val(treatmentRule[index]);
	}
	var sexFlag=treatmentRule.sexFlag;
	$("#sexFlag").find("option[value ='"+sexFlag+"']").attr("selected","selected");
	var personType=treatmentRule.personType;
	$("#personType").find("input[value ='"+personType+"']").attr("checked","true");
	var indicationFlag=treatmentRule.indicationFlag;
	$("#indicationFlag").find("input[value ='"+indicationFlag+"']").attr("checked","true");
	var contraindicationFlag=treatmentRule.contraindicationFlag;
	$("#contraindicationFlag").find("input[value ='"+contraindicationFlag+"']").attr("checked","true");
	var rescueFlag=treatmentRule.rescueFlag;
	$("#rescueFlag").find("input[value ='"+rescueFlag+"']").attr("checked","true");
	var diagType=treatmentRule.diagType;
	$("#diagType").find("input[value ='"+diagType+"']").attr("checked","true");
}

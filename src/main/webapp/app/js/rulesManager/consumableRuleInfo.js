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
	var consumableRule = JSON.parse(obj);
	$("#id").val(consumableRule["id"]);
	for ( var index in consumableRule) {
		$("#" + index).val(consumableRule[index]);
	}
	var sexFlag=consumableRule.sexFlag;
	$("#sexFlag").find("option[value ='"+sexFlag+"']").attr("selected","selected");
	var personType=consumableRule.personType;
	$("#personType").find("input[value ='"+personType+"']").attr("checked","true");
	var indicationFlag=consumableRule.indicationFlag;
	$("#indicationFlag").find("input[value ='"+indicationFlag+"']").attr("checked","true");
	var contraindicationFlag=consumableRule.contraindicationFlag;
	$("#contraindicationFlag").find("input[value ='"+contraindicationFlag+"']").attr("checked","true");
	var rescueFlag=consumableRule.rescueFlag;
	$("#rescueFlag").find("input[value ='"+rescueFlag+"']").attr("checked","true");
	var diagType=consumableRule.diagType;
	$("#diagType").find("input[value ='"+diagType+"']").attr("checked","true");
}

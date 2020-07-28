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
	var drugRule = JSON.parse(obj);
	var form = layui.form
	console.info(drugRule);
	$("#id").val(drugRule["id"]);
	for ( var index in drugRule) {
		$("#" + index).val(drugRule[index]);
	}
	
	var sexFlag=drugRule.sexFlag;
	$("#sexFlag").find("option[value ='"+sexFlag+"']").attr("selected","selected");
	var personType=drugRule.personType;
	$("#personType").find("input[value ='"+personType+"']").attr("checked","true");
	var indicationFlag=drugRule.indicationFlag;
	$("#indicationFlag").find("input[value ='"+indicationFlag+"']").attr("checked","true");
	var contraindicationFlag=drugRule.contraindicationFlag;
	$("#contraindicationFlag").find("input[value ='"+contraindicationFlag+"']").attr("checked","true");
	var rescueFlag=drugRule.rescueFlag;
	$("#rescueFlag").find("input[value ='"+rescueFlag+"']").attr("checked","true");
	var diagType=drugRule.diagType;
	$("#diagType").find("input[value ='"+diagType+"']").attr("checked","true");
}

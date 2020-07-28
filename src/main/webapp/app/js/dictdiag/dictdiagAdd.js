//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
    //自定义验证规则
    form.verify({
        cityCode:[
            // /^[A-Za-z0-9_\-]+$/
            /^[A-Za-z0-9_\-]+$/
            ,'城市编码只能为字母/数字/下划线/-'
        ]
    });
});

function child(obj) {
	var diseasesClass = JSON.parse(obj);
	$("#id").val(diseasesClass["id"]);
	for ( var index in diseasesClass) {
		$("#" + index).val(diseasesClass[index])
	}
};

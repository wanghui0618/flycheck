layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'laydate'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table;

    table.render({
        elem: '#itemCodeAndNameSearchTable'
        , url: $WEB_ROOT_PATH + '/dhccApi/itemCodeAndNameSearch/itemCodeAndNameSearch/getItemInfoByCodeOrName'
        , cellMinWidth: 80
        , height: tableHeight
        , cols: [[
            {type: 'numbers', width: 40, title: '编号'}
            , {field: 'itemId', width: 120, align: 'center', title: '医保项目编码'}
            , {field: 'itemName', align: 'center', title: '医保项目名称'}
            , {field: 'itemIdHosp', width: 120, align: 'center', title: '医院项目编码'}
            , {field: 'itemNameHosp', align: 'center', title: '医院项目名称'}
        ]]
        , page: true
        , where: {  'queryType': $("#queryType").val(),
                    'queryItemInfo': $("#queryItemInfo").val(),
                    'queryItemType': $("#queryItemType").val()
                }

    });

    //左侧监听搜索
    form.on('submit(itemCodeAndNameSearch)', function (data) {
        var field = data.field;
        //执行重载
        layui.table.reload('itemCodeAndNameSearchTable', {
            where: field
        });
    });
  //重置
	form.on('submit(LAY-user-front-reset)', function () {
		$('#queryItemInfo').val('');

	});
	
	  $("#exportInfo").click(function () {
	        window.open($WEB_ROOT_PATH+'/dhccApi/itemCodeAndNameSearch/itemCodeAndNameSearch/exportExcel');
	        return false;
	    });
});
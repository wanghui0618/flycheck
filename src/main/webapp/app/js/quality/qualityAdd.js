layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['layer','form'], function(){
 		Layer = layui.layer;
 		var $=layui.$;
 		form = layui.form;
 		//监听select change
 		form.on('select(tableName)', function(data){
			loadSelect(data.value);
 			form.render('select');
 		});
 		/*form.on('select(ruleType_name)', function(data){
			$("#colname").val(data.elem[data.elem.selectedIndex].text);
 		});*/
 	});
	
	
	
	//加载表字段名,根据参数为select赋值
	function loadSelect(code){
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=dataQuality&dictRequestVo.ttName='+code, 
				function(data){
						var form=layui.form;
		     		var  dataList= data.dictList;
		     		for(var i=0 ;i<dataList.length;i++){
		     			var mm="<option text='"+dataList[i].text+"' value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
		     			$("#name").append(mm); 
		     		}
		     		form.render('select');
		}); 
	}
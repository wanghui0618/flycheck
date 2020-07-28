function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
	var str = url.substr(1);
	strs = str.split("&");
	for (var i = 0; i < strs.length; i++) {
	theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
	}
	}
	return theRequest;
}


//
var icd1="DT_ICD9";
var icd2="DT_ICD10";
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','form'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;

	    var Request = GetRequest();
	    var type = Request["type"];
	    switch(type)
		{
		    case "1":
		    	$('#btn1').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="广东版";
		        break;
		    case "2":
		    	$('#btn2').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="国标2011";
		        break;
		    case "3":
		    	$('#btn3').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="国标2015";
		        break;
		    case "4":
		    	$('#btn4').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="国家临床V1.1";
		        break;
		    case "5":
		    	$('#btn5').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="国家临床V2.0";
		        break;
		    case "6":
		    	$('#btn6').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="北京临床V6.01";
		        break;
		    case "7":
		    	$('#btn').addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger');
		    	type="所有数据";
		        break;
		    default:
		    	
		}
	    
	    table.render({
	    	elem: '#icdTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow'
	        ,cellMinWidth: 80
	        ,height:document.documentElement.clientHeight-65
	        /*,where: {  type: '1'  }*/
	        ,cols: [[
			 {type: 'numbers', width:40, title: '编号'}      
			,{field:'code',width:250,align:'center', title: 'ICD编码' }
			,{field:'name', align:'center',title: 'ICD名称' }
			/*,{field:'type',align:'center', title: '类型' }*/
			,{field:'typeName', align:'center',title: '类型名称' }
	            ]]
	            ,page: true
	          });
	    $('#btn').click(function(){$(this).addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger')});
	    $('#btn1').click(function(){$(this).addClass(' layui-btn-danger').siblings().removeClass(' layui-btn-danger')});
	    $('#btn2').click(function(){$(this).addClass('layui-btn-danger').siblings().removeClass(' layui-btn-danger')});
	    $('#btn3').click(function(){$(this).addClass('layui-btn-danger').siblings().removeClass(' layui-btn-danger')});
	    $('#btn4').click(function(){$(this).addClass('layui-btn-danger').siblings().removeClass(' layui-btn-danger')});
	    $('#btn5').click(function(){$(this).addClass('layui-btn-danger').siblings().removeClass(' layui-btn-danger')});
	    $('#btn6').click(function(){$(this).addClass('layui-btn-danger').siblings().removeClass(' layui-btn-danger')});

    	form.on('checkbox(icd1)',function(data){
      		if(data.elem.checked==true){
      			icd1=data.value;
      		}
      		if(data.elem.checked==false){
      			icd1="";
      		}
        	});
    	
      	form.on('checkbox(icd2)',function(data){
      		if(data.elem.checked==true){
      			icd2=data.value;
      		}
      		if(data.elem.checked==false){
      			icd2="";
      		}
      		
            	});
      	
      	//监听搜索全部
	    form.on('submit(LAY-btn-front-search)', function(data){
	    	$("#aaa").prop("checked",true); 
	    	$("#bbb").prop("checked",true); 
	    	 icd1="DT_ICD9";
	    	 icd2="DT_ICD10";
/*	    	$("[name='like3[write]']").prop("checked",true);
	    	$("[name='like3']").attr("checked",'true'); */
	    	var field = data.field;
	        //执行重载
    		field["icd.typeName"]="";
    		field["icd.type"]="";
	        field["icd.code"]="";
	        field["icd.name"]="";
	        layui.table.reload('icdTable', {
	            where: field
	        });
	        form.render();
	    });
      	
      	//监听搜索
	    form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	    	console.log(field);
	        //执行重载
    		field["icd.type"]="";
    		field["icd.name"]="";
    		 var han = /^[\u4e00-\u9fa5]+$/;
    	     if (han.test( field["icd.code"])) {
    	    	 field["icd.name"]=field["icd.code"];
    	    	 field["icd.code"]="";
    	        };
    		
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.type"]="";
	    	}
	    	if(icd2=="" && icd1==""){
	    		field["icd.code"]="没有数据";
	    	}
	        layui.table.reload('icdTable', {
	            where: field
		           ,page: { curr: 1 
		            }
	        });
	    });
      	
      	
	    //监听搜索
	    //广东版

		form.on('submit(LAY-icdg-front-search)', function(data){
	    	var field = data.field;
	    	field["icd.code"]="";
	    	field["icd.name"]="";
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.typeName"]="广东版";
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.typeName"]="广东版";
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.typeName"]="广东版";
	    		field["icd.type"]="";
	    	}
	    	
	    	if(icd2=="" && icd1==""){
	    		field["icd.typeName"]="没有数据";
	    	}
	    	var url=$WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow';
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	            ,url:url
	        });
	    });
		
		//国际2011版
		form.on('submit(LAY-icdgj1-front-search)', function(data){
	    	var field = data.field;
	    	field["icd.code"]="";
	    	field["icd.name"]="";
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.typeName"]="国标2011";
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.typeName"]="国标2011";
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.typeName"]="国标2011";
	    		field["icd.type"]="";
	    	}
	    	if(icd2=="" && icd1==""){
	    		field["icd.typeName"]="没有数据";
	    	}
	    	var url=$WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow';
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	            ,url:url
	        });
	    });
		
		//国际2015
		form.on('submit(LAY-icdgj2-front-search)', function(data){
	    	var field = data.field;
	    	field["icd.code"]="";
	    	field["icd.name"]="";
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.typeName"]="国标2015";
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.typeName"]="国标2015";
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.typeName"]="国标2015";
	    		field["icd.type"]="";
	    	}
	    	if(icd2=="" && icd1==""){
	    		field["icd.typeName"]="没有数据";
	    	}
	    	var url=$WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow';
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	            ,url:url
	        });
	    });
		
		//国家临床V1.1
		form.on('submit(LAY-icdgjlc1-front-search)', function(data){
	    	var field = data.field;
	    	field["icd.code"]="";
	    	field["icd.name"]="";
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.typeName"]="国家临床V1.1";
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.typeName"]="国家临床V1.1";
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.typeName"]="国家临床V1.1";
	    		field["icd.type"]="";
	    	}
	    	if(icd2=="" && icd1==""){
	    		field["icd.typeName"]="没有数据";
	    	}
	    	var url=$WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow';
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	            ,url:url
	        });
	    });
		
		//国家临床V2.0
		form.on('submit(LAY-icdgjlc2-front-search)', function(data){
	    	var field = data.field;
	    	field["icd.code"]="";
	    	field["icd.name"]="";
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.typeName"]="国家临床V2.0";
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.typeName"]="国家临床V2.0";
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.typeName"]="国家临床V2.0";
	    		field["icd.type"]="";
	    	}
	    	if(icd2=="" && icd1==""){
	    		field["icd.typeName"]="没有数据";
	    	}
	    	var url=$WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow';
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	            ,url:url
	        });
	    });
		
		//北京临床V6.01
		form.on('submit(LAY-icdbjlc-front-search)', function(data){
	    	var field = data.field;
	    	field["icd.code"]="";
	    	field["icd.name"]="";
	    	if(icd1=="DT_ICD9" && icd2==""){
	    		field["icd.typeName"]="北京临床V6.01";
	    		field["icd.type"]="DT_ICD9";
	    	}
	    	if(icd2=="DT_ICD10" && icd1==""){
	    		field["icd.typeName"]="北京临床V6.01";
	    		field["icd.type"]="DT_ICD10";
	    	}
	    	if(icd1=="DT_ICD9" && icd2=="DT_ICD10"){
	    		field["icd.typeName"]="北京临床V6.01";
	    		field["icd.type"]="";
	    	}
	    	if(icd2=="" && icd1==""){
	    		field["icd.typeName"]="没有数据";
	    	}
	    	var url=$WEB_ROOT_PATH+'/dhccApi/icd/icd/listShow';
	        //执行重载
	        layui.table.reload('icdTable', {
	            where: field
	            ,url:url
	        });
	    });
		

		


	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
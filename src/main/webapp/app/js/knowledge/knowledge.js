layui.config({
	 base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块,'element'
  }).use(['index', 'table','element'], function(){
	   var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	//罗列所有大类
	  var url = $WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listAllMainType';
	 	postReq(url,'', function(data){
	 		//console.log(data);
	 		var text="";
	 		var i=0;
	 		for(;i<data.length;i++){	
	 			var dataType = data[i];
	 			var str="checkB('"+dataType+"')";
	 			text+=' <dl><dt style="width:90px;height:35px;"><a style="padding-left: 8px; width:110px ; font-weight: bold;font-size:14px;color:#FF0000;" onclick='+str+'>'+dataType+'</a></dt><dd id="'+dataType+'">';
 			
	 			//罗列所有小分类
	 			$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listAllSecondType?knowledge.typeSmall='+dataType,function(dataSmall){
	 				//console.log(dataSmall);
	 				var textSecond="";
	 				for(var key in dataSmall ){			
	 					var	dataType = dataSmall[key];
	 					var	strTitle="checkC('"+key+"')";
	 					textSecond+='<dt><a style="padding-left: 5px; font-size:11px;font-weight: normal;" onclick='+strTitle+'>'+key+'</a></dt>';
	 							
	 				} 	
	 				document.getElementById(dataType).innerHTML=textSecond;	 		
	 			});		 				 			
	 			text+='</dd></dl>';
	 		} 		 		
	 		document.getElementById("hotType").innerHTML=text;	 		
	 	}, function(){
	 	/*	$CommonUI.alert("失败!");*/
	 	}, {
	 		skipHidden : false
	 	});
	  
	  
	  
	//热门词条TOP10
	  var url = $WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listHotKeys';
	  console.log(url);
	 	postReq(url, '', function(data){
	 		//console.log(data);
	 		var text="";
	 		for(var i=0;i<data.length;i++){ 			
	 			var keywords = data[i];
 				var str="checkA('"+keywords+"')";
 				text+='<dt style="width:90px;height:35px;"><a style="font-size:12px; padding-left:5px; width:80px ;align:center;" onclick='+str+'>'+keywords+'</a></dt>';

	 		}
	 		document.getElementById("hotKeys").innerHTML=text;
	 	}, function(){
	 		/*$CommonUI.alert("失败!");*/
	 	}, {
	 		skipHidden : false
	 	});
	 	

	 	//文本框输入
	 	$('#title').bind('input propertychange', function() {
	 		$("#div_items").css('display', 'block');//只要输入就显示列表框

	 		if ($("#title").val().length <= 0) {
	 			$(".div_item").css('display', 'none');//如果什么都没填，全部隐藏
	 			return;
	 		}
	 		$(".div_item").css('display', 'none');//如果填了，先将所有的选项隐藏
	 		var keywords=$("#title").val();

	 		$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/findHotKeys?knowledge.keywords='+keywords,function(data){
	 			//var str="<div style='height:15px;line-height:15px;padding-left: 8px;font-size:12px;align:center;' class='div_item'>文章标题：</div>";
	 
	 			//var num = data.length;
	 			var str="<div style='height:35px;line-height:35px;padding-left: 8px;' class='div_item'>相关文章标题：</div>";
	 			for( var key in data ){
	 				var value=data[key];
	 				str+="<div style='height:35px;line-height:35px;padding-left: 8px;' typeName='"+value+"' class='div_item'>"+key+"</div>";
	 			}
	 			document.getElementById('div_items').innerHTML=str;
	 			// 解决样式不生效
	 			$("#div_items").css({"width":"644px","border":"1px solid #66afe9","border-top":"0px","position":"absolute"});
	 			//移入移出效果
	 			$(".div_item").hover(function () {
	 				$(this).css('background-color', '#1C86EE').css('color', 'white');
	 			}, function () {
	 				$(this).css('background-color', 'white').css('color', 'black');
	 			});

	 			 //行点击事件
    		    $(".div_item").on("click",function(e){
    		        //console.info($(this).attr("id"));
    		    	var type=$(this).attr("typeName");
    		    	var title=$(this).html();
    		   
    		    	if(type==2){
    		    		checkC(title);
    		    	}else{
    		    		checkB(type);
    		    	}
    		    	
    		    	$("#title").val(title);
    		    /*	//增加浏览次数+1
    		    	addViews($(this).attr("id"));
    		    	e.stopPropagation();*/
    		    });
    			
	 		});
	 		 //隐藏列表框
	 	    $("body").click(function () {
		        $("#div_items").css('display', 'none');
		    });

	 		/*  for (var i = 0; i < $(".div_item").length; i++) {
	            //模糊匹配，将所有匹配项显示
	            if ($(".div_item").eq(i).text().substr(0, $("#title").val().length) == $("#title").val()) {
	                $(".div_item").eq(i).css('display', 'block');
	            }
	        }
	    });
	    $("#title").keydown(function(event){
	    	if(event.keyCode == 13){
	    		check();
	    	};
	    });
	 		 */

	 	});



  });
//top10查询
function checkA(title){
    layer.open({
        type: 2
        ,title: "关键字："+title
        ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfo'
        ,maxmin: true
        ,area: ['1200px', '550px']
        ,success: function(layero, index){
  	  var iframeWindow = window['layui-layer-iframe'+ index];
  	  //向此iframe层方法 传递参数
  	  iframeWindow.listKeyTitle(title);
    }
  }); 

}
//搜索按钮
function check(){
	var title=$("#title").val();
    layer.open({
        type: 2
        ,title: "相关关键字："+title
        ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfo'
        ,maxmin: true
        ,area: ['1200px', '550px']
        ,success: function(layero, index){
  	  var iframeWindow = window['layui-layer-iframe'+ index];
  	  //向此iframe层方法 传递参数
  	  iframeWindow.listAll(title);
    }
  });  
}

//分类浏览   大类
function checkB(title){
    layer.open({
        type: 2
        ,title:  "分类为："+title
        ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfo'
        ,maxmin: true
        ,area: ['1200px', '550px']
        ,success: function(layero, index){
  	  var iframeWindow = window['layui-layer-iframe'+ index];
  	  //向此iframe层方法 传递参数
  	  iframeWindow.list(title);
    }
  });   
 }
//分类浏览   小类
function checkC(title){
	$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/list?knowledge.title='+title,function(data){
		var fieldNo=data.data;
		var field =fieldNo[0]; 
		window.parent.parent.showKnowledgeView(field);
	});
}

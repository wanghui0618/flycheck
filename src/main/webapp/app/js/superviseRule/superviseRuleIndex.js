//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	  
	    //获取就诊信息
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/RuleNumber',
				function(data){
	    	var data =data.data;
	    	/*$("#totalnumber").html(data[0].totalNumber)
	    	$("#personnumber").html(data[0].personNumber)
	    	$("#moneynumber").html(data[0].moneyNumber)*/
	    	
	    	$("#h2number").html("统筹区监控规则共"+data[0].totalNumber+"条，"+"机审检查出监控规则违规"+data[0].personNumber+"人次")
	    	
	    	
		});
	    
	    
	    //监控规则排名
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/MonitorNumber',
				function(data){
	    	var tboy=$("#MonitorNumber");
	    	var mm;
			var  dataList= data.data;
			var count=0;
			for(var i=0 ;i<dataList.length;i++){
				if(count<=4&&dataList[i].pname!=null){
					var tr = document.createElement("tr");
					tr.innHTML="<td style='padding-left:30px'>"+(i+1)+"</td>"
					+"<td style='padding-left:30px'>"+dataList[i].pname+"</td>"
					+"<td>"+dataList[i].pnumber+"</td>";
					if(count%2==0){
						mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
					}else{
						mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
					}
					count++;
				}
		    }
			if(dataList.length==0){
				var tr = document.createElement("tr");
				tr.innHTML="<td colspan='5' style='text-align: center;'>无数据。。。</td>"
				mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
			}
			tboy.html(mm);
		});
	    
	    //统筹区排名
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/TcNumber',
				function(data){
	    	var tboy=$("#tcNumber");
	    	var mm;
			var  dataList= data.data;
			var count=0;
			for(var i=0 ;i<dataList.length;i++){
				if(count<=4&&dataList[i].pname!=null){
					if(dataList[i].pnumber==0 ||dataList[i].pnumber ==null){
						dataList[i].pnumber="-";
					}
					var tr = document.createElement("tr");
					tr.innHTML="<td style='padding-left:30px'>"+(i+1)+"</td>"
					+"<td style='padding-left:30px'>"+dataList[i].pname+"</td>"
					+"<td>"+dataList[i].pnumber+"</td>";
					if(count%2==0){
						mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
					}else{
						mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
					}
					count++;
				}
		    }
			if(dataList.length==0){
				var tr = document.createElement("tr");
				tr.innHTML="<td colspan='5' style='text-align: center;'>无数据。。。</td>"
				mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
			}
		/*	if(dataList.length==0){
				var tr = document.createElement("tr");
				tr.innHTML="<td colspan='5' style='text-align: center;'><img src="+$WEB_ROOT_PATH+"/images/empty.png></td>"
				mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
			}*/
			tboy.html(mm);
		});
	  
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
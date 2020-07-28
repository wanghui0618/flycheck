document.getElementById("mainTwo").style.height=document.documentElement.clientHeight-305+"px";
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function () {
    var $ = layui.$
        , form = layui.form
        , element = layui.element
        , table = layui.table;
    //统计在线人员
    initOnline();
    //统计活跃用户
    initActive();
    //统计最近登录
    initRecentlyLogin();
    //通讯录
    addressBook();
    
    function initOnline() {
    	$.getJSON($WEB_ROOT_PATH+'/dhccApi/user/user/userOnline',
				function(data){
	    	var tboy=$("#tableOnline");
	    	var mm;
			var dataList= data.data;
			/*console.info(dataList);*/
			for(var i=0 ;i<dataList.length;i++){
				if(i<=3){
					var tr = document.createElement("tr");
					var date;
					if(dataList[i].loginTime.length>=16){
						date = (dataList[i].loginTime).substring(11,16);
					}
					var minutes = dataList[i].minutes;
					if(minutes>0){
						minutes = "在线"+minutes;
					}else{
						minutes = minutes;
					}
					
					/*if(minutes==0){
						minutes="刚刚登录";
					}else{
						if(minutes>60){
							console.info(minutes);
							minutes = "在线"+parseInt(minutes/60)+"小时"+minutes%60+"分钟";
						}else{
							minutes = "在线"+minutes+"分钟";
						}
					}*/
					tr.innHTML="<td>"+dataList[i].name+"</td>"+"<td style='text-align: left;'>"+dataList[i].phone+"</td><td style='text-align: center;'><img style='padding-right: 8px;margin-top: -4px;' src='"+$WEB_ROOT_PATH+"/images/oauth/shijian.png'>"+date+"</td><td style='text-align: right;'>"+minutes+"</td>";
					if(i==3){
						mm+="<tr style='line-height: 45px;'>"+tr.innHTML+"</tr>";
					}else{
						mm+="<tr style='line-height: 45px;border-bottom: 1px dashed #E1E2E4;'>"+tr.innHTML+"</tr>";
					}
				}
		    }
			tboy.html(mm);
		});
    };
    
    function initActive(){
    	$.ajax({
   	     url: $WEB_ROOT_PATH+"/dhccApi/user/user/userNumber",
	         type : "post",		
	         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
	         dataType : "json",	
	         success : function(result) {
	        	var total = result.data[0].total;
	        	var lastDay = result.data[0].lastday;
	        	var lastWeek = result.data[0].lastweek;
	        	var lastMonth = result.data[0].lastmonth;
	        	//<a  href="javascript:;" lay-href="<%=request.getContextPath()%>/user/newLogin?num=1">
	        	$("#lastOneDaySpan").html("<a href='javascript:;' lay-urlname='最近一天在线' lay-href='"+$WEB_ROOT_PATH+"/user/newLogin?num=1'>"+lastDay+"/"+total+"</a>");
	        	$("#lastSevenDaySpan").html("<a href='javascript:;' lay-urlname='最近一周在线'  lay-href='"+$WEB_ROOT_PATH+"/user/newLogin?num=7'>"+lastWeek+"/"+total+"</a>");
	        	$("#last30DaySpan").html("<a href='javascript:;' lay-urlname='最近一月在线' lay-href='"+$WEB_ROOT_PATH+"/user/newLogin?num=31'>"+lastMonth+"/"+total+"</a>");
	        	$('#lastOneDayBar').attr('lay-percent',"");
	        	$('#lastSevenDayBar').attr('lay-percent',"");
	        	$('#last30DayBar').attr('lay-percent',"");
	        	element.init();
	        	element.progress('lastOneDay', GetPercent(lastDay,total));
	        	element.progress('lastSevenDay', GetPercent(lastWeek,total));
	        	element.progress('last30Day', GetPercent(lastMonth,total));
	       }
      });
    }
    
    function initRecentlyLogin() {
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/user/user/latestLogin',
				function(data){
	    	var tboy=$("#qualityTable");
	    	var mm;
			var  dataList= data.data;
			for(var i=0 ;i<dataList.length;i++){
				if(i<3){
					var tr = document.createElement("tr");
					var date;
					if(dataList[i].loginTime.length>=16){
						date = (dataList[i].loginTime).substring(0,16);
					}
					tr.innHTML="<td><img style='padding-right: 15px;float: left;' src='"+$WEB_ROOT_PATH+"/images/oauth/shijian.png'><div style='display: block;float: left;'><div style='line-height: 20px;color:#3D3D3D'>"+dataList[i].name+"</div><div style='line-height: 20px;color:#3D3D3D;font-size:12px;'>登录</div></div></td>"+"<td style='text-align: right;'><img style='padding-right: 8px;margin-top: -4px;' src='"+$WEB_ROOT_PATH+"/images/oauth/shijian.png'>"+date+"</td>";
					if(i==2){
						mm+="<tr style='line-height: 60px;'>"+tr.innHTML+"</tr>";
					}else{
						mm+="<tr style='line-height: 60px;border-bottom: 1px dashed #E1E2E4;'>"+tr.innHTML+"</tr>";
					}
				}
		    }
			tboy.html(mm);
		});
    };
    
    function addressBook(){
    	table.render({
            elem: '#userTable1'
            , url: $WEB_ROOT_PATH + '/dhccApi/user/user/query'
            , cellMinWidth: 80
            ,limits: [5,10,20]
            ,limit: 5
            , height: document.documentElement.clientHeight-305
            , cols: [[
                  {type: 'numbers', title: '序号'}
                , {field: 'id', title: 'ID', hide: true}
                , {field: 'name', title: '姓名',align: 'center'}
                , {field: 'phone', title: '手机号码',align: 'center'}
                , {field: 'email', title: '邮箱',align: 'center'}
                , {field: 'unitName', title: '组织名称',align: 'center'}
                , {field: 'roleName', title: '角色名称',align: 'center'}
            ]]
            , page: true
        })
    
    };
    
    /*var isInputZh = false;
    var search = document.querySelector('input');

    search.addEventListener('compositionstart', function (e) {
      isInputZh = true;
    }, false);
    search.addEventListener('compositionend', function (e) {
      isInputZh = false;

      doSomething(search.value);
    }, false);
    search.addEventListener('input', function (e) {
      if (isInputZh) return;
      var value = this.value;

      doSomething(value);
    }, false);*/

    var flag = true;
    $('#keyDom').on('compositionstart',function(){
        flag = false;
    })
    $('#keyDom').on('compositionend',function(){
        flag = true;
    })
    $('#keyDom').on('input',function(){
        setTimeout(function(){
            if(flag){
            	var keyDom = document.getElementById("keyDom").value;
            	layui.table.reload('userTable1', {
                    where: {keyDom:keyDom}
                    ,page: { curr: 1}
                });
            }
        },0)
    })

    
    /*document.getElementById("keyDom").oninput = function() {myFunction()};
    //监听搜索
    function myFunction() {
        var keyDom = document.getElementById("keyDom").value;
        console.log(keyDom)
        //执行重载
        layui.table.reload('userTable1', {
            where: {keyDom:keyDom}
            ,page: { curr: 1}
        });
    }*/
    
  //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});
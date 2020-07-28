<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bsui/font-awesome/css/font-awesome.min.css"/>
    <script src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/bsui/dhccbs3/dhccbs3.7.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonUI.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/commonValidate.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/echarts3/echarts.js" type="text/javascript" charset="utf-8"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
  	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/lay/modules/layer.js" type="text/javascript" charset="utf-8"></script>
  	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/modules/layer/default/layer.css" media="all">
	<script>
	var tableHeight=document.documentElement.clientHeight-65;
	var Layer;
	layui.use('layer', function(){
   		Layer = layui.layer;
   	});
	function initform(formId,data) {
		  var   list=$("#"+formId+" input,select,textarea")

		  var   object={};
			for(var i =0;i<list.length ;i++) {
			var name=$(list[i]).attr('name');
			 if(name!=""&&name!=undefined){
				 var id="";
					var index=name.indexOf(".");
					if(index>0) {
					id=name.substr(index+1)
					}else{
					id=name	;
					}
				 object[name]=data[id] ; 
			 }
			 
			}
			return object;
		}
    function isNullOrEmpty(value) {
        if (value === '' || value === undefined || value === null) {
            return true;
        }

        return false;
    }
    function logout(){
    	layer.confirm('您确定要退出系统？', function(index){
    		top.location.href=$WEB_ROOT_PATH+"/";
    		$.get($WEB_ROOT_PATH+"/dhccApi/user/user/logout",
    			{
    			},function(dog){
    			},"json");
        });
    }
    //按钮权限获取
    function buttonAuthority(){
    	//ajax请求当前权限
    	var url=$WEB_ROOT_PATH+'/dhccApi/button/button/getButtonAuthoByUserId';
    	$.get(url,function(result){
    		//console.log(result[1]);
    		localStorage.setItem('buttonAuthority',result);//存入浏览器数据库
    		
    	});
    }
    //静态按钮权限(各页面初始化时执行，隐藏静态按钮)
    function hideButtonStatic(){
    	var result=localStorage.getItem('buttonAuthority');//从浏览器数据库取出
    	var result=JSON.parse(result);
    	//console.log("开始查询按钮权限");
    	//console.log(result);
    	if(result){
    		for(var i=0;i<result.length;i++){
    			try {
    					$("#"+result[i]).hide();
    				}
    			catch(err){}
    		}
		}
    }
    //表格操作按钮权限查询 （返回true的需要隐藏  false可以显示）
    function existsButton(buttonName){
    	var result=localStorage.getItem('buttonAuthority');//从浏览器数据库取出
    	var result=JSON.parse(result);
    	if(result){
    		for(var i=0;i<result.length;i++){
    			if(result[i]==buttonName){
    				return true;
    			}
    		}
    		return false;
		}else{
			return false;
		}
    }
    //行操作隐藏(参数：行操作按钮id数组，返回true 隐藏，false 显示)
    function rowOperate(ids){
    	if(ids){
    		for(var i=0;i<ids.length;i++){
    			if(!existsButton(ids[i])){
    				return false;
    			}
    		}
    		return true;
    	}else{
    		return true;
    	}
    }
    function GetPercent(num, total) {
        /// <summary>
        /// 求百分比
        /// </summary>
        /// <param name="num">当前数</param>
        /// <param name="total">总数</param>
        num = parseFloat(num);
        total = parseFloat(total);
        if (isNaN(num) || isNaN(total)) {
            return "-";
        }
        return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
    }
    
  //下拉上收按钮代码块
    function showSearch(){
    	$("#shangla").show();
    	$("#xiala").hide();
    	$(".cxtjtop").show();
    	$(".xmb").hide();//notExists.jsp专用
    	var path=$("#path").val();
    	if(path=='门诊'){
    		$(".zysj").hide();//notExists.jsp专用
    	}
    }
    function hideSearch(){
    	$(".cxtjtop").hide();
    	$("#shangla").hide();
    	$("#xiala").show();
    }

   	</script>
</head>
<body>
</body>
</html>
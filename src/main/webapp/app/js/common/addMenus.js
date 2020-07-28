$(function(){
	//menus("自主分析系统");
	eightMenus();
});
//其他菜单
function menus(parentMenusName){
	 $.ajax({
         async: false, //是否异步
         cache: false, //是否使用缓存
         type: 'POST', //请求方式：post
         url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/listTree',//非8大目录请求的路径
         data:{
             "menu.menuName":parentMenusName
         },
         success: function (data) {
             treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
             console.log(treeNodes);
             var menuList=treeNodes[0].children;
         		if(menuList.length>0){
         			for(var i=0;i<menuList.length;i++){
         				if(menuList[i].hasChildren==0){
         					addMenus(menuList[i]);//单菜单拼接
         				}else{
         					var child=menuList[i].children;
         					if(child.length>0){
         						addMenusChild(menuList[i]);//带子菜单拼接
         					}
         				}
         			}
         		}
         }
     });
}
//8大目录菜单
function eightMenus(){
	$WEB_ROOT_PATH + '/dhccApi/menu/menu/getFirstMenu'
	 $.ajax({
         async: false, //是否异步
         cache: false, //是否使用缓存
         type: 'POST', //请求方式：post
         url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/getFirstMenu',//8大目录请求的路径
         
         success: function (data) {
        	 console.log(data);
        	 if(data.length>0){
        		 addEightMenus(data);
        	 }
         }
     });
}
function addEightMenus(data){
	var div=document.getElementById("eightMenus");
	var ul1=document.createElement("ul");
	ul1.setAttribute("class","imgul");
	var ul2=document.createElement("ul");
	ul2.setAttribute("class","imgul");
	for(var i=0;i<data.length;i++){
		if(i<4){
			addPartition(ul1,data[i],i);
			console.log(data[i]);
		}else{
			addPartition(ul2,data[i],i);
		}
	}
	div.appendChild(ul1);
	div.appendChild(ul2);
}
//加载下拉菜单
function addPartition(ul,data,i){
	var li=document.createElement("li");
	var div1=document.createElement("div");
	var a=document.createElement("a");
	a.setAttribute("href",$WEB_ROOT_PATH+data.menuUrl);
	var img=document.createElement("img");
	img.setAttribute("class",data.onclickAft+"&"+data.onclickBef);//点击前/后图片地址
	img.setAttribute("name","tp");
	img.setAttribute("src",$WEB_ROOT_PATH+data.onclickBef);//点击前，初始化图片地址
	var div2=document.createElement("div");
	div2.setAttribute("class","a"+(i+1));
	div2.setAttribute("style","height:10px;width: 100px;margin: 0 auto;");
	a.appendChild(img);
	a.appendChild(div2);
	div1.appendChild(a);
	var div3=document.createElement("div");
	var a2=document.createElement("a");
	a2.setAttribute("class","a"+(i+1));
	a2.setAttribute("name","wz");
	a2.setAttribute("href",$WEB_ROOT_PATH+data.menuUrl);
	a2.innerHTML =data.menuName;
	div3.appendChild(a2);
	li.appendChild(div1);
	li.appendChild(div3);
	ul.appendChild(li);
}
function addMenus(menu){
	var ul=document.getElementById("ul");
	var li=document.createElement("li");
	li.setAttribute("class","layui-nav-item");
	var a=document.createElement("a");
	a.setAttribute("href","javascript:;");
	a.setAttribute("lay-href",$WEB_ROOT_PATH+menu.menuUrl);
	a.setAttribute("lay-tips",menu.menuName);
	a.setAttribute("class","jc1");
	//a标签中拼接img图标
	var img=document.createElement("img");
	img.setAttribute("class","btnImg");
	img.setAttribute("src",$WEB_ROOT_PATH+"/images/main/jc11.png");
	//a.innerHTML =menu.menuName;
	a.appendChild(img);
	var span=document.createElement("span");
	span.innerHTML =menu.menuName;
	a.appendChild(span);
	//a.innerHTML =menu.menuName;
	
	li.appendChild(a);
	ul.appendChild(li);
}
function addMenusChild(menu){
	var ul=document.getElementById("ul");
	
	var li=document.createElement("li");
	li.setAttribute("class","layui-nav-item");
	
	var a=document.createElement("a");
	a.setAttribute("href","javascript:;");
	a.setAttribute("class","jc1");
	//a标签中拼接img图标
	var img=document.createElement("img");
	img.setAttribute("class","btnImg");
	img.setAttribute("src",$WEB_ROOT_PATH+"/images/main/jc11.png");
	a.appendChild(img);
	var span=document.createElement("span");
	span.innerHTML =menu.menuName;
	a.appendChild(span);
	
	var dl=document.createElement("dl");
	dl.setAttribute("class","layui-nav-child");
	
	var child=menu.children;
	for(var i=0;i<child.length;i++){
		var dd=document.createElement("dd");
		var a1=document.createElement("a");
		a1.setAttribute("href","javascript:;");
		a1.setAttribute("lay-href",$WEB_ROOT_PATH+child[i].menuUrl);
		a1.innerHTML = child[i].menuName;
		dd.appendChild(a1);
		dl.appendChild(dd);
	}
	
	li.appendChild(a);
	li.appendChild(dl);
	ul.appendChild(li);
}
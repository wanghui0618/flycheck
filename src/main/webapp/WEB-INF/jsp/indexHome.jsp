<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.dhcc.piccbid.entity.user.User"%>
<%@page import="com.dhcc.piccbid.utils.DhccUtil"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
  <title><%=DhccUtil.title %></title>
</head>
<%
	User user = (User) session.getAttribute("user");
	String name = user.getName();
%>
<style>
.layui-layout-admin .layui-layout-left, .layadmin-pagetabs, .layui-layout-admin .layui-body, .layui-layout-admin .layui-footer {
    left: 220px;
}
.layadmin-pagetabs{
  margin-top:-40px;
}
</style>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>
          
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="note">
              <i class="layui-icon layui-icon-note"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect="">
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <img style="margin: 0 auto; height: 36px; padding: 0px;width:36px;border-radius:50%;"
                             src="<%=request.getContextPath()%>${sessionScope.headimgurl}"
                             onerror='<c:catch>src="<%=request.getContextPath()%>/images/main/touxiang.png"</c:catch>' />
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite style="color: black">您好 <%=name%></cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="<%=request.getContextPath()%>/set/info">基本资料</a></dd>
              <dd><a lay-href="<%=request.getContextPath()%>/set/password">修改密码</a></dd>
              <hr>
              <dd  style="text-align: center;"><a onclick="logout()">退出</a></dd>
            </dl>
          </li>
          
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="待加"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
        </ul>
      </div>
      
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu" id="cbMenus">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="">
           <img style="margin: 0 auto; height: 18px; padding: 0px;" src="<%=request.getContextPath()%>/images/main/logo_white_picc.png">
          </div>
	          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
	          </ul>
          </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="" lay-attr="" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="<%=request.getContextPath()%>/flyGeneralOverview/flyGeneralOverview" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
      
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>
  <script>
  $(document).ready(function() {
        menus();
        $("#cbMenus").width();
  });
  layui.config({
    base: 'plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');
  layui.use(['element', 'layer', 'jquery','table','form'], function() {
  	var element = layui.element;
	var table=layui.table;
	var form=layui.form;
    var $ = layui.$;
  });
  
  function menus() {
      $.ajax({
          async : false, //是否异步
          cache : false, //是否使用缓存
          type : 'POST', //请求方式：post
          url : $WEB_ROOT_PATH + '/dhccApi/menu/menu/listTreeNew',//非8大目录请求的路径
          success : function(data) {
             treeNodes = JSON.parse(data); //把后台封装好的简单Json格式赋给treeNodes
             if(treeNodes.length>0){
          	   var j=1;
          	   for (var i = 0; i < treeNodes.length; i++) {
          		   var indexMenus= document.getElementById("LAY-system-side-menu");
          		   addMenuNew(treeNodes[i],indexMenus,j);
          		   j++;
          	   }
             }
          }
      });
  }
  function addMenuNew(data,indexMenus,j){
	var li = document.createElement("li");
	//设置li属性
	li.setAttribute("class", "layui-nav-item layui-nav-itemed");
	li.setAttribute("data-name", data[0].menuName);
	var a = document.createElement("a");
	//设置a属性
	a.setAttribute("href", "javascript:;");
	a.setAttribute("lay-tips", data[0].menuName);
	a.setAttribute("lay-direction",'2');
    var i = document.createElement("i");
    i.setAttribute("style", "margin-left:-35px");
    var img = document.createElement("img");
    img.setAttribute("class", "btnImg");
    img.setAttribute("src", $WEB_ROOT_PATH + data[0].onclickBef);//点击前图片地址
    img.setAttribute("style", "width:15px;height:15px;margin-top: -5px");
    i.appendChild(img);
    a.appendChild(i)
   var cite = document.createElement("cite");
   cite.setAttribute("style", "margin-left:10px");
   cite.innerHTML = data[0].menuName;
   a.appendChild(cite);
   var dl = document.createElement("dl");
	 //设置dl属性
	 dl.setAttribute("class", "layui-nav-child");
	 //遍历创建子菜单
	 var children=data[0].children;
	 if(children!=undefined&&children.length>0){
		 for(var i = 0; i < children.length; i++){
		      var dd = document.createElement("dd");
		      if(children[i].hasChildren=='0'){
			      dd.setAttribute("data-name", children[i].menuName);
			      var a1 = document.createElement("a");
			      a1.setAttribute("lay-href","<%=request.getContextPath()%>"+children[i].menuUrl);
			      a1.innerHTML = children[i].menuName;
			      dd.appendChild(a1);
			      dl.appendChild(dd);
		      }else{
		    	 
		    	  addThreenMenu(children[i],dd)
		      }
		      dl.appendChild(dd);
		 } 
	 }
   //放菜单
   li.appendChild(a);
   li.appendChild(dl);
   indexMenus.appendChild(li);
}
//带3级菜单的
function addThreenMenu(chileData,dd){
	
   //建a
    var a2 = document.createElement("a");
    a2.setAttribute("href", "javascript:;");
    a2.innerHTML = chileData.menuName;
   //建dl
    var dl2 = document.createElement("dl");
    dl2.setAttribute("class", 'layui-nav-child');
    var lenchild=chileData.children;
    
    if(lenchild.length>0){
	   	for(var t=0;t<lenchild.length;t++){
	   		
	   		var dd3 = document.createElement("dd");
	   		dd3.setAttribute("data-name",lenchild[t].menuName);
	   	 	var a3 = document.createElement("a");
	     	a3.setAttribute("href", "<%=request.getContextPath()%>"+lenchild[t].menuUrl);
	     	a3.innerHTML = lenchild[t].menuName;
	     	dd3.appendChild(a3);
	     	dl2.appendChild(dd3);
	     	
	   	}
    }
    dd.appendChild(a2);
 	dd.appendChild(dl2);
 
}
  </script>
 
</body>
</html>



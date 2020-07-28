<%@ page language="java" import="java.util.*"	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"  content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/knowledge/index.css">
<title>知识库</title>
<style >
.curr {
    color: #0171bb;
    font-style: italic;
    font-weight: bolder;
}
.skin_knw .part {
    background: url(images/knw_top_bg.jpg) repeat-x;
}

.part .hd {
    background-color: #F7F7F7;
    background-position: 0 -11px;
    background-repeat: repeat-x;
    border-color: #CCE5B8;
    border-style: solid solid none;
    border-width: 1px 1px medium;
    height: 28px;
    padding-left: 10px;
    border-radius: 4px;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
    width: 779px ;
}
</style> 
</head>
<body style="overflow-x:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-body">
        	<div style="width:55%; margin:30px auto 0;font: 12px/1.5 Arial, 宋体, Helvetica,sans_serif;" id="knowledgeDiv">
		        <div style="margin:10px 0px 50px 25px;">
		            <div style="margin-bottom:10px;">
		                <div id="parentDiv">
		                	<table>
		                		<tr>
		                			<td><input id="title" maxlength="40" class="validatebox"
		                           data-options="required:true" name="title"
		                           style="font-size: 14px;  width: 640px ;height: 45px ;padding-left:5px" placeholder="搜行业或医药知识（综合搜索）" /></td>
		                			<td><button id="title" style="background-color:#02A68A;border-color: #02A68A;height: 47px ;margin-left: 10px;color:#fff" class="btn btn-info"
		                            type="button" onclick="check()">搜索词条
		                    </button></td>
		                		</tr>
		                	</table>
		                </div>
		                <div id="div_items" style="display:none">
		                    <div>
		                    </div>
		                </div>
		                <div class="sortBrow part" style="margin-top:50px;">
		                    <span class="rc-tp"><span></span></span>
		                    <div class="hd" style="width: 779px ;">
		                        <h3 style="width: 779px ;">热门词条</h3>
		                    </div>
		                    <div class="bd clearfix" id="hotKeys" style="height:55px;width: 769px;"></div>
		                    <div class="rc-bt"><span></span></div>
		                </div>
		                <div class="sortBrow part">
		                    <span class="rc-tp"><span></span></span>
		                    <div class="hd">
		                        <h3 style="width: 779px ;">分类浏览
					                    <button class="layui-btn layuiadmin-btn-useradmin layui-btn-xs"  href="javascript:;" lay-href="<%=request.getContextPath()%>/knowledge/knowledgeInfo" lay-tips="知识库编辑" style="float:right; margin-right:5px"><i class="layui-icon">知识库编辑</i></button>
									</h3>
		                    </div>
		                    <div class="bd clearfix" id="hotType" style="width: 769px ;">
		                        
		                    </div>
		                    <!-- <div class="ft"><a href="#" rel="nofollow" onclick="checkB('')">更多&gt;&gt;</a></div> -->
		                    <div class="rc-bt"><span></span></div>
		                </div>
		            </div>
		        </div>
		    </div>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/knowledge/knowledge.js"></script>
</body>
</html>
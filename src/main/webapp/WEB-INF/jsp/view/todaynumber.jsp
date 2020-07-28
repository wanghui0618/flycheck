<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>昨日上传数量统计</title>
</head>
<body style="">
<div class="layui-fluid">
  <div class="layui-card">
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
     <div class="layui-form-item">
        <!--        <div class="layui-inline">
            <label class="layui-form-label">城市名称</label>
            <div class="layui-input-block" style="width:160px;">
              <select id="cityName" name="admin.cityName" lay-search="">
                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
              </select>
            </div>
           </div> -->
           
          <!--  <div class="layui-inline">
            <label class="layui-form-label">医疗机构</label>
            <div class="layui-input-block" style="width:160px;">
              <select id="orgName" name="admin.orgName" lay-search="">
                  <option value=""  class="layui-select-tips layui-unselect">请选择</option>
              </select>
            </div>
          </div> -->

			<div class="layui-inline">
				<label class="layui-form-label">医疗机构</label>
				<div class="layui-input-inline">
					<input id="getOrgName" /> <input type="text" id="orgName"
						name="admin.orgName" style="display: none;" />
				</div>
			</div>

					<div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
          </div>
     </div>
    </div>
    <table id="todayTable" class="layui-hide" lay-filter="todayTable"></table>
    </div>
    </div>
		<script type="text/javascript" ">
		//
		layui.config({
			    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
			  }).extend({
			    index: 'lib/index' //主入口模块
			  }).use(['index', 'table'], function(){
			    var $ = layui.$
			    ,form = layui.form
			    ,table = layui.table;
/* 			    
			    var time=(new Date).getTime()-24*60*60*1000;
			    var yesterday=new Date(time);
				var today_nian = yesterday.getFullYear();
				var today_yue = yesterday.getMonth()+1;
				var today_day = yesterday.getDate();
				var str = today_nian+"-"+today_yue+"-"+today_day; */
				
				
		        //加载医疗机构下拉字典
		       /*  $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findOrgNameDict',
		            function(data){
		                for(var i=0 ;i<data.length;i++){
		                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
		                    $("#orgName").append(mm);
		                }
		                form.render('select');
		           }); */
		        
		/* 		
		        //加载医疗机构下拉字典
		        $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityNameDict',
		            function(data){
		                for(var i=0 ;i<data.length;i++){
		                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
		                    $("#cityName").append(mm);
		                }
		                form.render('select');
		           }); */
			
			    //今日上传数量
			    table.render({
			    	elem: '#todayTable'
			        ,url: $WEB_ROOT_PATH+'/dhccApi/admin/admin/TodayNumber'
			      /*   ,where: {inFlag: str} */
			        ,cellMinWidth: 80
			        ,height:document.documentElement.clientHeight-80
			        ,cols: [[
			        	{type: 'numbers',width:40,align:'center',title: '序号 '}
			        /* 	,{field:'cityName',width:180,align:'center', title: '城市名称' } */
					  ,{field:'orgName',align:'center', title: '医院名称' }
					  ,{field:'totalNumber', align:'center',title: '上传总数量' }
			            ]]
			            ,page: true
			          });
			    
			    //监听搜索
				form.on('submit(LAY-org-front-search)', function(data){
					var field = data.field;
					//执行重载
			        layui.table.reload('todayTable', {
			            where: field
			        });
			    });
			    
			    //按钮事件绑定底层方法-勿动
			    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			      var type = $(this).data('type');
			      active[type] ? active[type].call(this) : '';
			    });
		
			  });
	</script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>
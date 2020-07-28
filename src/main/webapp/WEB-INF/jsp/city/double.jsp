<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>城市字典维护</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">城市代码</label>
            <div class="layui-input-block">
              <input type="text" name="city.cityCode" placeholder="请输入城市编码" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">城市名称</label>
            <div class="layui-input-block">
              <input type="text" name="city.cityName" placeholder="请输入城市名称" autocomplete="off" maxlength="10" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
             <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">添加</button>
          </div>
        </div>
      </div>
	 <div style="float:left;" class="layui-inline"><table id="cityTable" class="layui-hide" lay-filter="cityTable"></table></div>
	 <div style="float:left;" class="layui-inline"><table id="cityTable1" class="layui-hide" lay-filter="cityTable1"></table></div>


        <script type="text/html" id="table-orgadmin-webuser">
         <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
         <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        </script>
      </div>
    </div>
  </div>
 <script type="text/javascript" >
 layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    table.render({
	    	elem: '#cityTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/city/city/list'
	            ,cellMinWidth: 80
	            ,height: 510
	            ,cols: [[
	        ,{type: 'checkbox',width:50, fixed: 'left'}
   			,{title:'操作', width: 150, align:'center', toolbar: '#table-orgadmin-webuser'}        
			,{field:'cityCode',width:170, title: '姓名' }
			,{field:'cityName', width:170,title: '年龄' }
		
	            ]]
	            ,page: true
	          });
	  
	    
	    table.render({
	    	elem: '#cityTable1'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/city/city/list'
	            ,cellMinWidth: 80
	            ,height: 510
	            ,cols: [[
	        ,{type: 'checkbox', width:50,fixed: 'left' }
   			,{title:'操作', width: 150, align:'center', toolbar: '#table-orgadmin-webuser'}        
			,{field:'cityCode',width:170, title: '名称' }
			,{field:'cityName', width:170,title: '隶属' }
		
	            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('cityTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加城市'
	          ,content: $WEB_ROOT_PATH+'/city/cityadd'
	          ,maxmin: true
	          ,area: ['800px', '300px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/city/city/save";
	              $.post(url,field,function(result){
	            	  if(result.id=="1"){
	            		  layer.msg('该城市编码已经存在，请勿重复添加！');
	            	  }else if(result.id=="2"){
	            		  layer.msg('该城市名称已经存在，请勿重复添加!');
	            	  }else{
	            		  layer.msg('添加成功!');
	            	  }
	            		 
	            	  //后台成功后，静态更新表格中的数据
	                  table.reload('cityTable'); //数据刷新
	                  layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    
	    //监听行点击
	    table.on('tool(cityTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该城市？', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/city/city/delete";
		            $.post(url,{'city.id':data.id},function(result){
		        	    table.reload('cityTable');
		                layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改城市'
		          ,content: $WEB_ROOT_PATH+'/city/cityadd'
		          ,maxmin: true
		          ,area: ['800px', '300px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index]
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-org-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/city/city/save";
		              $.post(url,field,function(result){
		            	  if(result.id=="1"){
		            		  layer.msg('该城市编码已经存在，请重新填写修改信息！');
		            	  }else if(result.id=="2"){
		            		  layer.msg('该城市名称已经存在，请重新填写修改信息!');
		            	  }else{
		            		  layer.msg('添加成功!');
		            	  }
		            	  //后台成功后，静态更新表格中的数据
		                  table.reload('cityTable'); //数据刷新
		                  layer.close(index); //关闭弹层
					  });
		            });  
		            submit.trigger('click');
		          }
		        }); 
	      }
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
 
 </script>
   <script>

  </script>
</body>
</html>
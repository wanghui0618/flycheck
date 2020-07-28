var typeSmall='';
var keywords='';
var inflag='';
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
		  var $ = layui.$
		  ,form = layui.form
		  ,table = layui.table;

		  table.render({
			  elem: '#knowledge'
				  ,url: $WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/list'
				  ,cellMinWidth: 80				 
				  ,height: document.documentElement.clientHeight-65
				  ,cols: [[
					  {type: 'numbers',width:40, title: '序号',fixed: 'left' }
					  ,{field:'id', title: 'ID', sort: true, hide:true} 
					  ,{title:'操作', toolbar: '#table-useradmin-webuser', width:250,align:'center'}
					  ,{field:'type', title: '类型',align:'center',templet: function(d){
						  var codex =d.type;
						  if(codex=="1"){
							  codex="大类";
						  }else if(codex=="2"){
							  codex="小类";
						  }
						  return '<span >'+ codex +'</span>'
					  }}
					  ,{field:'typeSmall', title: '分类',align:'center'}
					  ,{field:'title',title: '标题', width:300,align:'center'}
					  ,{field:'content', title: '内容',align:'center', hide:true}	                          
					  ,{field:'keywords',title: '关键字',align:'center' }
					  ,{field:'createUser',title: '添加人',align:'center'}
					  ,{field:'createDate', title: '添加时间',align:'center'}
					  //,{field:'updateDate', title: '更新时间',align:'center'}
					  ,{field:'fabulosFrequency', title: '更新时间',align:'center', hide:true}
					  ,{field:'lookFrequency', title: '点赞数量',align:'center', hide:true}
					  ,{field:'treadFrequency', title: '踩数量',align:'center', hide:true}
					  ]]
		  ,page: true
		  
		  });
	
		  //监听搜索
		  form.on('submit(LAY-user-front-search)', function(data){
			  var field = data.field;    	
			  //console.info(field);
			  //执行重载
			  layui.table.reload('knowledge', {
				  where: field
			  });
		  });

		  //添加事件
		  var active = {
				  add: function(){
					  //参保信息新增
					  layer.open({
						  type: 2
						  ,title: '知识库新增'
							  ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfoEdit'
							  ,maxmin: true
							  ,area: ['800px', '500px']
					  ,btn: ['确定', '取消']  
					  ,yes: function(index, layero){

						  var iframeWindow = window['layui-layer-iframe'+ index]

						  ,submitID = 'layuiadmin-btn-useradmin'
							  ,submit = layero.find('iframe').contents().find('#'+ submitID);

						  //监听提交
						  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
							  var field = data.field; //获取提交的字段
							  //提交 Ajax后台 
							  var url=$WEB_ROOT_PATH+"/dhccApi/knowledge/knowledge/save";
							
							  field["content"]=field["knowledge.content"];
							  field["knowledge.content"]="";
							  //console.info(field);
							  $.post(url,field,function(result){	
								  var inflag= result.inflag; 
								  if(inflag==2){
									  layer.msg('该标题已存在!');
									  return false;
								  }else if(inflag==1){
									  layer.msg('新增成功!');
									  //后台成功后，静态更新表格中的数据
									  table.reload('knowledge'); //数据刷新
									  layer.close(index); //关闭弹层
								  }else if(inflag==3){
									  layer.msg('不存在此分类的大类，请先创建大类!');
									  //后台成功后，静态更新表格中的数据
									  table.reload('knowledge'); //数据刷新
									  layer.close(index); //关闭弹层
								  }else if(inflag==4){
									  layer.msg('已存在分类的大类');
									  //后台成功后，静态更新表格中的数据
									  table.reload('knowledge'); //数据刷新
									  layer.close(index); //关闭弹层
								  } else{
									  layer.msg('网络异常');
									  //后台成功后，静态更新表格中的数据
									  table.reload('knowledge'); //数据刷新
									  layer.close(index); //关闭弹层
								  }
								  //关闭弹层
							  });


						  });  
						  submit.trigger('click');
					  }
					  }); 

				  }
		  };



		  //监听行点击
		  table.on('tool(knowledge)', function(obj){
			  var data = obj.data;
			  if(obj.event === 'xiugai'){
				  //修改
				  layer.open({
					  type: 2
					  ,title: '知识库修改'
						  ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfoEdit'
						  ,maxmin: true
						  ,area: ['800px', '500px']
				  ,btn: ['确定', '取消']
				  ,success: function(layero, index){
					  var iframeWindow = window['layui-layer-iframe'+ index];
					  //向此iframe层方法 传递参数
					  iframeWindow.child(JSON.stringify(data));
				  }
				  ,yes: function(index, layero){
					  var iframeWindow = window['layui-layer-iframe'+ index]
					  ,submitID = 'layuiadmin-btn-useradmin'
						  ,submit = layero.find('iframe').contents().find('#'+ submitID);

					  //监听提交
					  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
						  var field = data.field; //获取提交的字段
						  //提交 Ajax后台 
						  var url=$WEB_ROOT_PATH+"/dhccApi/knowledge/knowledge/save";
						
						  field["content"]=field["knowledge.content"];
						  field["knowledge.content"]="";
						  field["knowledge.updateDate"]="";
						  field["knowledge.createDate"]="";
						  //console.info(field);
						  $.post(url,field,function(result){	
							  var inflag= result.inflag; 
							  if(inflag==2){
								  layer.msg('该标题已存在!');
								  return false;
							  }else if(inflag==1){
								  layer.msg('新增成功!');
								  //后台成功后，静态更新表格中的数据
								  table.reload('knowledge'); //数据刷新
								  layer.close(index); //关闭弹层
							  }else if(inflag==3){
								  layer.msg('不存在此分类的大类，请先创建大类!');
								  //后台成功后，静态更新表格中的数据
								  table.reload('knowledge'); //数据刷新
								  layer.close(index); //关闭弹层
							  }else if(inflag==4){
								  layer.msg('已存在分类的大类');
								  //后台成功后，静态更新表格中的数据
								  table.reload('knowledge'); //数据刷新
								  layer.close(index); //关闭弹层
							  } else{
								  layer.msg('网络异常');
								  //后台成功后，静态更新表格中的数据
								  table.reload('knowledge'); //数据刷新
								  layer.close(index); //关闭弹层
							  }
							  //关闭弹层
						  });


					  /*
						 // console.log(data2);
						  var field = data2.field; //获取提交的字段
						  //console.log(field);
						  //提交 Ajax后台 
						  var myurl=$WEB_ROOT_PATH+"/dhccApi/knowledge/knowledge/save";
						  var content=" ";
						  var createUser=" ";
						  var id=" ";
						  var fabulosFrequency=" ";
						  var lookFrequency=" ";
						  var treadFrequency=" ";
						  var type=" ";
						  var typeSmall=" ";
						  var title=" ";
						  var keywords=" ";
						  content=field["knowledge.content"];
						  createUser=field["knowledge.createUser"];
						  id=field["knowledge.id"];   		  	   
						  fabulosFrequency=field["knowledge.fabulosFrequency"];	    		  
						  lookFrequency=field["knowledge.lookFrequency"];
						  treadFrequency=field["knowledge.treadFrequency"];	    		  	 
						  type=field["knowledge.type"];
						  typeSmall=field["knowledge.typeSmall"];
						  title=field["knowledge.title"];
						  keywords=field["knowledge.keywords"];
			 
	           $.post(myurl, {"knowledge.id":id,
							  "knowledge.createUser":createUser,
							  "knowledge.fabulosFrequency":fabulosFrequency,
							  "knowledge.lookFrequency":lookFrequency,
							  "knowledge.treadFrequency":treadFrequency,
							  "content":content,
							  "knowledge.type":type,
							  "knowledge.typeSmall":typeSmall,
							  "knowledge.title":title,
							  "knowledge.keywords":keywords,
							  "knowledge.content":"",
						  },function(result){ 
							  var inflag= result.operateSuccess; 
							  if(inflag==true){
								  layer.msg('修改成功!');
								  //后台成功后，静态更新表格中的数据
								  table.reload('knowledge'); //数据刷新
								  layer.close(index); //关闭弹层

							  }else{
								  layer.msg('修改失败!');
								  //后台成功后，静态更新表格中的数据
								  table.reload('knowledge'); //数据刷新
								  layer.close(index); //关闭弹层
							  }
						  });

					  */});  
					  submit.trigger('click');
				  }
				  }); 

			  } else if(obj.event === 'shanchu'){
				  //删除
				  var id=data.id;
				  var title=data.title;
				  layer.confirm('是否确定删除<span style="color: red;">'+title+'</span>的信息', function(index){
					  //执行 Ajax 后重载
					  var url=$WEB_ROOT_PATH+"/dhccApi/knowledge/knowledge/delete";
					  $.post(url,{"knowledge.id":id},function(result){
						  table.reload('knowledge');
						  layer.msg('已删除');
					  });
				  });
			  } else if(obj.event === 'view'){
				  //预览
				  //console.log(data)
				  window.parent.parent.showKnowledgeView(data);

			  }
		  });




		  //按钮事件绑定底层方法-勿动
		  $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			  var type = $(this).data('type');
			  active[type] ? active[type].call(this) : '';
		  });
		  
			
				layui.table.reload('knowledge', {
					where:{"knowledge.keywords":keywords ,"knowledge.typeSmall":typeSmall,"inflag":inflag}				
				  });	
			
	  });

/*function list(title) {
	$('#typeSmall').val(title);
}

function listAll(title) {
	//设置分词查询words
	$('#keywords').val(title);
	$('#inflag').val("1");
}
function listKeyTitle(title) {
	//查询TOP10热门词汇文章
	$('#keywords').val(title);
	$('#inflag').val("2");	
}*/
function list(title) {
	typeSmall=title;
	//console.log("list");
}

function listAll(title,smallType) {
	//设置分词查询words
	keywords=title;
	typeSmall=smallTypel;
	//console.log("listAll");
}
function listKeyTitle(title) {
	//查询TOP10热门词汇文章
	keywords=title;
	inflag="2";	
	//console.log("listKeyTitle");
}
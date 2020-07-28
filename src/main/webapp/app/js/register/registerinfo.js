//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    table.render({
	    	elem: '#registerTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/register/register/listVo'
	        ,cellMinWidth: 80
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:40, title: '编号'}
			 ,{title:'操作', width:100, align:'center', toolbar: '#table-orgadmin-webuser'}  
			/*,{field:'cityName',width:120,align:'center', title: '城市名称' }*/
			,{field:'hosName',width:180, align:'center',title: '医院名称' }
			,{field:'inhosDate', align:'center',width:140,title: '入院日期' }
			,{field:'seeDocId',align:'center',width:160, title: '就诊登记ID' }
			,{field:'name', align:'center',title: '姓名' }
			,{field:'sex', align:'center',title: '性别' ,templet: function(d){
				var sex = d.sex;
				if(sex=="1"){
					sex="男";
					return '<span>'+ sex +'</span>'
				}
				if(sex=="2"){
					sex="女";
					return '<span>'+ sex +'</span>'
				}
				return '<span>'+ sex +'</span>'
		}}
			,{field:'age', align:'center',title: '年龄' }
			,{field:'idCard', align:'center',width:220,title: '身份证号' }
/*			,{field:'personType', align:'center',width:120,title: '人群类别' }
			,{field:'insuranceType', align:'center',width:120,title: '险种标志' }
			,{field:'selfMedical', align:'center',width:120,title: '个人医疗年度' }
			,{field:'seeDocType', align:'center',width:120,title: '就诊类别' }
			,{field:'seeDocDetail', align:'center',width:120,title: '就诊类别明细' }
			,{field:'hosCode', align:'center',width:120,title: '医院编码' }
			,{field:'hosName', align:'center',width:120,title: '医院名称' }
			,{field:'departCode', align:'center',width:120,title: '科室编码' }
			,{field:'departName', align:'center',width:120,title: '科室名称' }
			,{field:'docCode', align:'center',width:120,title: '医师编码' }
			,{field:'docName', align:'center',width:120,title: '医师名称' }
			,{field:'inhosDate', align:'center',width:120,title: '入院日期' }
			,{field:'outhosDate', align:'center',width:120,title: '出院日期' }
			,{field:'balanceDate', align:'center',width:120,title: '结算日期' }
			,{field:'inhosWay', align:'center',width:120,title: '入院方式' }
			,{field:'outhosReason', align:'center',width:120,title: '出院原因' }
			,{field:'inhosSeeDoc', align:'center',width:120,title: '入院诊断' }
			,{field:'outhosReason', align:'center',width:120,title: '出院诊断' }
			,{field:'balanceArea', align:'center',width:120,title: '医院结算地点' }
			,{field:'balanceAreaFlag', align:'center',width:120,title: '异地结算标志' }
			,{field:'personFlag', align:'center',width:120,title: '人员状态' }
			,{field:'balanceId', align:'center',width:120,title: '结算ID' }
			,{field:'insPersonType', align:'center',width:120,title: '参保人员类别' }
			,{field:'insPersonType', align:'center',width:120,title: '出院标志' }*/
			,{field:'createDate', align:'center',width:150,title: '上传时间' }
	               ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('registerTable', {
	            where: field
	        });
	    });
	    //监听行点击
	    table.on('tool(registerTable)', function(obj){
	      var data = obj.data;
	       if (obj.event === 'view') {
			    layer.open({
			          type: 2
			          ,title: data.name
			          ,content: $WEB_ROOT_PATH+'/register/registerview'
			          ,maxmin: true
			          ,area: ['1200px', '450px']
			          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index]
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			        	  
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
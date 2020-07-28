//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index','form', 'table'], function(){
	    var $ = layui.$,form = layui.form,table = layui.table;
	    var idNo=  $("#idNo").val();
	    var ilegalInHosp=  $("#ilegalInHosp").val();
	    var caseNo=  $("#caseNo").val();
	    table.render({
	    	elem: '#dg'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalrecord/medicalRecord/findViolationList'
	            ,cellMinWidth: 60
	            ,height: 'full-85'
	            ,even:true
	            ,cols: [[
	               { fixed:'left',type: 'numbers', title: '序号',align:'center' }
	              ,{ fixed:'left',title: '操作',align:'center', width:'27%',toolbar: '#toolbar' }
	              ,{fixed:'left',field:'caseNo', title: '案件编号',width:'8%',align:'right' }
	              ,{fixed:'left',field:'ilegalInHosp', title: '是否违规',width:'8%',align:'center',templet: function(d){
              	    var codex =d.ilegalInHosp;
                	if(codex==null||codex==""){
                		codex="";
                	}
                	if(codex=="1"){
                		codex="违规";
                	}
                	if(codex=="2"){
                		codex="疑似违规";
                	}
                	if(codex=="0"){
                		codex="未违规";
                	}
                    return '<span style="color: red;">'+ codex +'</span>'
	              }}
	              ,{fixed:'left',field:'intervalDay', title: '最近一次住院间隔时间(天)',width:'18%',align:'center',templet: function(d){
	              	    var lastDischargeDate =d.lastDischargeDate;
	              	    var admissionDate =d.admissionDate;
	              	    
	              	    if(lastDischargeDate&&admissionDate){
	              	    	var	intervalDay =  GetDateDiff(lastDischargeDate,admissionDate,'day');
	              	    	return '<span style="color: red;">'+ intervalDay +'</span>'
	              	    }else{
	              	    	return "";
	              	    }
	                   
		           }}
	              ,{field:'orgName', title: '医疗机构',width:'15%'}
	              ,{field:'admissionNo', title: '住院号',width:'10%',align:'center'}
	              ,{field:'idNo', title: '身份证号',width:'15%',align:'center'}
	              ,{field:'patientName', title: '患者姓名',width:'10%',align:'center'}
	              ,{field:'billingNo', title: '收费单据号',width:'10%',align:'center'}
	              ,{field:'condition', title: '病情',width:'20%',align:'left'}
	              ,{field:'lastDischargeDate', title: '上次出院日期',style:'color:red;',width:'16%',align:'center'}
	              ,{field:'admissionDate', title: '本次入院日期',style:'color:red;',width:'16%',align:'center'}
	              ,{field:'dischargeDate', title: '本次出院日期',width:'16%',align:'center'}
	              
	             
	            ]]
	            ,page: true
	            ,where: {
	            	"medicalRecord.idNo":idNo,
	            	"medicalRecord.caseNo":caseNo,
	            	"medicalRecord.ilegalInHosp":ilegalInHosp
	            }
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('dg', {
	            where: field
	        });
	    });
	  
	    
	    //监听行点击
	    table.on('tool(dg)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'detail'){
	      var url =  $WEB_ROOT_PATH+'/medicalrecord/showDetail?medicalRecord.orgCode='+data.orgCode+'&medicalRecord.idNo='+data.idNo+'&medicalRecord.patientName='+data.patientName+'&medicalRecord.condition='+data.condition;
	      if(data.lastDischargeDate){
	    	  url+='&medicalRecord.lastDischargeDate='+ data.lastDischargeDate
	       }
	      if(data.admissionDate){
	    	  url+='&medicalRecord.admissionDate='+data.admissionDate;
	      }
	    	  layer.open({
	    		   type: 2 //Page层类型
	    		  ,title: '分解住院病案查询'
	    		  ,shade:0.5 //遮罩透明度
	    		  ,maxmin: false//允许全屏最小化
	    		  ,anim:0 //0-6的动画形式，-1不开启
	    		  ,area: ['90%', '90%'] //自定义文本域宽高
	    		  ,content:url
	    		  });
	      }
	      if(obj.event === 'markToViolation'){
	    	  layer.confirm('确定将该案件标记为违规案件？', function(index){
		        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalrecord/medicalRecord/updateFlag";
		            $.post(url,{'medicalRecord.orgCode':data.orgCode,
		            			'medicalRecord.idNo':data.idNo,
		            			'medicalRecord.patientName':data.patientName,
		            			'medicalRecord.admissionDate':data.admissionDate,
		            			'medicalRecord.ilegalInHosp':1
		            	
		            	       },function(result){
		        	    table.reload('dg');
		                layer.msg('标记成功！');
	    	    });
		     });
	      }
	      if(obj.event === 'markToNormal'){
	    	  layer.confirm('确定将该案件标记为正常案件？', function(index){
		        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalrecord/medicalRecord/updateFlag";
	            $.post(url,{'medicalRecord.orgCode':data.orgCode,
        			'medicalRecord.idNo':data.idNo,
        			'medicalRecord.patientName':data.patientName,
        			'medicalRecord.admissionDate':data.admissionDate,
        			'medicalRecord.ilegalInHosp':0
        	
        	       },function(result){
        	    	   table.reload('dg');
        	    	   layer.msg('标记成功！');
        	     });
		     });
	      }
	 });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn .layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });

function GetDateDiff(startTime,endTime,diffType){
  startTime=startTime.replace(/\-/g,"/");
  endTime=endTime.replace(/\-/g,"/");
  diffType=diffType.toLowerCase();
  var sTime = new Date(startTime);    //开始时间
  var eTime = new Date(endTime);  //结束时间</font>
  //作为除数的数字
  var divNum = 1;
  switch (diffType){
    case "second":
      divNum=1000;
      break;
    case "minute":
      divNum=1000*60;
        break;
    case "hour":
      divNum=1000*3600;
      break;
    case "day":
      divNum=1000*3600*24;
      break;
    default:
      break;
  }
  return parseInt((eTime.getTime()-sTime.getTime())/parseInt(divNum));
  }

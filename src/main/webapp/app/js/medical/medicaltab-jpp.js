var billing_no="";
var medical_id;
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate','layer'], function(){
		  var $ = layui.jquery
		  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table
	    ,layer=layui.layer;
	    var laydate = layui.laydate;
	    
	    
	    table.render({
	    	elem: '#medicalTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/getList'
	            ,cellMinWidth: 130
	            ,height: 330
	            ,where: { 'medicalDetail.billingNo': billing_no  }
	    ,cols: [[
            { type: 'numbers',width:40, title: '序号'  }
            ,{field:'id', title: 'ID', sort: true, hide:true}
            /*,{field:'adviceId', title: '住院医嘱编号'}*/
            
            ,{field:'itemCode', title: '项目编号'}
            ,{field:'itemName', title: '项目名称'}
            ,{field:'itemCost', title: '项目金额'}
            ,{field:'itemNum', title: '项目数量'}
            ,{field:'itemPrice', title: '项目单价'}
            ,{field:'itemStandard', title: '项目规格'}
            ,{field:'limitPrice', title: '限价金额'}
            ,{field:'partialOrdination', title: '部分统筹'}
            ,{field:'partialPayment', title: '部分自付'}
            ,{field:'recipelId', title: '门诊处方编号'}
            ,{field:'selfPayAmount', title: '自付金额'}
            ,{field:'singleDose', title: '单次用量'}
            ,{field:'sumAmount', title: '总金额'}
            ,{field:'takeFrequence', title: '服用频次'}
            ,{field:'useDay', title: '用药天数'}
            ,{field:'applyPayAmount', title: '报销金额'}
            ,{field:'applyPayLevel', title: '报销级别',templet: function(d){
        	    var a =d.applyPayLevel;
            	if(a=="1"){
            		a="甲类";
            	}else if(a=="2"){
            		a="乙类";
            	}else if(a=="3"){
            		a="丙类";
            	}else if(a==null || a=="null"){
            		a="";
            	}
                return '<span >'+ a +'</span>'
          }}
            /*,{field:'billingNo', title: '收费单据号'}*/
            ,{field:'chargeType', title: '收费类别'}
            ,{field:'deliverWay',title: '给药途径'}
            ,{field:'doseForm', title: '剂型'}
            ,{field:'doseUnit', title: '用量单位'}
            ,{field:'drugType', title: '药品类别'}
            ,{field:'feeCreateDate', title: '费用发生时间'}
            ,{field:'fullOrdination', title: '全额统筹'}
            ,{field:'fullPayment', title: '全额自付'}
            ,{field:'ilegalType', title: '违规类型'}
            ,{field:'isIlegal', title: '是否违规'}
            ,{field:'isInsuranceProject', title: '是否医保项目',templet: function(d){
        	    var a =d.isInsuranceProject;
            	if(a=="0"){
            		a="否";
            	}else if(a=="1"){
            		a="是"
            	}else if(a==null || a=="null"){
            		a="";
            	}
                return '<span >'+ a +'</span>'
          }}
          ]]
	            ,page: true
	            ,done:function(res, curr, count){
	            	var result=res.data;
	            	
	            	MedicalCostShow(result[0].id,form);
	            	var index=-1;  
                    //分类显示中文名称
                    $("[data-field='id']").children().each(function () {
                    index++;
                      if ($(this).text() == result[0].id) {
                    	 $('tr').eq(index).css("background-color","#C0C0C0");
                      }
                    });
	            }
	          });
	  
	    	//张伟义加
	    	 var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/findData1?medicalId1="+medical_id;
	            $.post(url,{"medicalExamine.medicalId":medical_id},function(result1){
	            	
	            	 /*if(result1.medicalExamine['status']!=null || result1.medicalExamine['comments']!=null){
		        		  
		        	  }else if(result1.medicalExamine['status']==null || result1.medicalExamine['comments']==null){
		        		
		        	  }*/
	            	//radio标签动态选中
		        	  $("input[id='status3']").get(result1.medicalExamine['status']).checked=true;
		        	  form.render();
		        	  
		        	  //审核意见动态赋值
		        	  form.val("layuiadmin-form-useradmin2", {
			    		  // "name": "value"
			    		  "medicalExamine.comments":result1.medicalExamine['comments']
			    		  ,"check[write]": true
			    		  ,"open": false
			    		  ,"desc": "我爱layui"
			    		})
			    		
	            });
	          
	            //张伟义加
	            form.on('submit(LAY-user-front-save2)', function(data){
	            	var field = data.field;
	            	

	            	var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/save1?medicalId2="+medical_id;
	            	$.post(url,field,function(result){
	            		  layer.msg('添加成功!');
	            		  //后台成功后，静态更新表格中的数据
	            	});

	            });
	    
	      
	     
	    
	   
	    //明细表行监听
	     table.on('row(medicalTable)', function(obj){
	      var data = obj.data;
	      $("tr").css("background-color",""); 
          $(this).css("background-color","#C0C0C0"); 
	      MedicalCostShow(data["id"],form); 
	      $("#costId").val(data["id"]);
	     });
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
function MedicalCostShow(cost_id,form){
	  /*费用明细回显*/
    var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/medicalCostList";
    $.post(url,{"medicalCostVerify.medicalId":medical_id,"medicalCostVerify.costId":cost_id},function(result){
    	
    	//radio标签动态选中
    	$("input[id='violationStatus']").get(result.violationStatus).checked=true;
    	form.render();
    	//审核意见动态赋值
    	form.val("MedicalCost", {
    		"medicalCostVerify.deductions":result["deductions"]
    		,"medicalCostVerify.withholdingAmount": result["withholdingAmount"]
    		,"medicalCostVerify.withholdingQuantity": result["withholdingQuantity"]
    	})
    	
    });
}
function showdate1(){
	var violationStatus=$("input[name='medicalCostVerify.violationStatus']:checked").val();
	  var costId=$("#costId").val();
	  var deductions=$("#deductions").val();
	  var withholdingAmount=$("#withholdingAmount").val();
	  var withholdingQuantity=$("#withholdingQuantity").val();
	  var field={"medicalCostVerify.medicalId":medical_id,"medicalCostVerify.costId":costId,"medicalCostVerify.violationStatus":violationStatus,"medicalCostVerify.deductions":deductions,"medicalCostVerify.withholdingAmount":withholdingAmount,"medicalCostVerify.withholdingQuantity":withholdingQuantity};
	  var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/medicalCostSave";
    $.post(url,field,function(result){
  	  if(result.inFlag==0){
  		  layer.msg('添加成功!');
  	  }
    });
    MedicalCostShow(costId,form)
}
function restform(){
	  
	 $("#deductions").val("");
	 $("#withholdingAmount").val("");
	 $("#withholdingQuantity").val("");
}


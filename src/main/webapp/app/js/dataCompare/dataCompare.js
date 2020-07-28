layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(
						[ 'laydate', 'laypage', 'layer', 'table', 'carousel',
								'upload', 'element', 'slider','form' ],
						function() {
							var laydate = layui.laydate //日期
							, table = layui.table //表格
							, form = layui.form;
							laydate.render({
							    elem: '#billDate'
							    ,range: true
							  });
							form.on('submit(search)', function(data){
							    var formData = data.field;
					            console.debug(formData);
					            //数据表格重载
					            table.reload('abnormalHospitalStay',{
					            	page : {curr:1}
									,cellMinWidth: 80
									,height:tableHeight
					                 ,url: $WEB_ROOT_PATH+'/dhccApi/abnormalHospitalStay/abnormalHospitalStay/abnormalHospitalStay' //后台做模糊搜索接口路径 
					                , where:{ 
					                	 'hospitalId':formData.hospitalId,
					                	 'hospitalName':formData.hospitalName,
					                	 'zyts':formData.zyts,
					                	 'admissionDiseaseId':formData.admissionDiseaseId,
					                     'admissionDiseaseName':formData.admissionDiseaseName,
					                     'paydate':formData.paydate,
					                     'indate':formData.indate,
					                     'outdate':formData.outdate
					                },      
					            });
					        });
							table.render({
										elem :'#abnormalHospitalStay'
										,cellMinWidth: 80
										,height:tableHeight
										,totalRow : true //开启合计行
										,url : $WEB_ROOT_PATH+'/dhccApi/abnormalHospitalStay/abnormalHospitalStay/abnormalHospitalStay'  //数据接口
										,title : '住院天数异常'	
									    ,cols: [[
									     {field:'id', align:'center',title: '序号', width:80,type:'numbers',totalRowText : '汇总'}
										,{field:'hospitalId', align:'center',title: '医疗机构编码', width:100}
										,{field:'hospitalName', align:'center',title: '医疗机构名称', width:200}
										,{field:'zyh',align: 'center', width:200,title: '住院号'}
										,{field:'patientId',align: 'center', width:200,title: '身份证号码'}
										,{field:'admissionDeptName',align: 'center', title: '就诊科室', width:200}
										,{field:'patientName',align: 'center', title: '患者姓名', width:100}
										,{field:'admissionDate',align: 'center', title: '入院日期',width:200}
										,{field:'dischargeDate',align: 'center', title: '出院日期',width:200}
										,{field:'zyts',align: 'center', title: '住院天数',width:200}
										,{field:'totalAmount',align: 'center', title: '医疗总费用',width:200,totalRow: true}
										,{field:'benefitType',align: 'center', title: '险种类型' ,width:200}
										,{field:'westernMedicineFee', align:'center',title: '西药费', width:200}
										,{field:'chineseMedicineYinpian',align: 'center', width:200,title: '中药饮片费'}							
										,{field:'chineseMedicineForm', align:'center',title: '中成药费', width:200}
										,{field:'accommodationFee',align: 'center', width:200,title: '床位费'}
										,{field:'diagnosisFee',align: 'center', width:200,title: '诊查费'}
										,{field:'inspectionFee',align: 'center', title: '检查费', width:200}
										,{field:'testFee',align: 'center', title: '化验费', width:200}
										,{field:'treatmentFee',align: 'center', title: '治疗费', width:200}
										,{field:'nursingFee',align: 'center', title: '护理费', width:200}
										,{field:'materialFee',align: 'center', title: '卫生材料费',width:200}
										,{field:'consultationFee	',align: 'center', title: '一般诊疗费',width:200}
										,{field:'registrationFee',align: 'center', title: '挂号费' ,width:200}
										,{field:'otherFee', align:'center',title: '其他费', width:200}
										]],
										page : true //开启分页
									});
							
						    //监听搜索
							form.on('submit(LAY-user-front-search)', function(data){
						    	var field = data.field;
						        //执行重载
						       /* layui.table.reload('userHistoryTable', {
						            where: field
						        });*/
						    });
						  
						    //按钮事件绑定底层方法-勿动
						    $('.layui-form.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
						      var type = $(this).data('type');
						      active[type] ? active[type].call(this) : '';
						    });
						})
						
                                function restart(){
	                             $("#addGoodsForm")[0].reset(); 

	                              layui.form.render();
                                  }

function goodsSave(){
	$('#search').click(function(){
		console("aaaa")
		/*var tt=$("#title").val();
		var st=$("#smallTit").val();
		var ius=$("#imgUrls").val();*/
		var menuName=$("#menuName").val();
		var menuCode=$("#menuCode").val();
		var menuUrl=$("#menuUrl").val();
		var parentLeaf=$("#parentLeaf").val();
		var parentId=$("#parentId").val();
		var owner=$("#owner").val();
		console.log(menuName)
		console.log(menuCode)
		console.log(menuUrl)
		console.log(parentLeaf)
		console.log(parentId)
		console.log(imgurl1)
		console.log(imgurl2)
		console.log(owner)
        $.ajax({
            type: "POST",
            async: false,
            url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/save',
            data: {
                "menu.parentId": parentId,
                "menu.menuName": menuName,
                "menu.menuCode": menuCode,
                "menu.parentLeaf": parentLeaf,
                "menu.menuUrl": menuUrl,
                "menu.owner": owner,
                "menu.onclickBef":imgurl1,
                "menu.onclickAft":imgurl2
            },
/*            success: function (data) {
                var inFlag = data.inFlag;
                window.parent.layeradd(inFlag);
            	 if(inFlag==1){
              		  layer.msg('提交成功!');
              		  opener.init();
              	  }else if(inFlag==2){
              		  layer.msg('已存在!');
              	  }else{
              		layer.msg('提交失败!');
              	  }
            }*/
        });
	});
}
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
							    elem: '#test7'
							    ,range: true
							  });
							laydate.render({
							    elem: '#test6'
							    ,range: true
							  });
							laydate.render({
							    elem: '#test8'
							    ,range: true
							  });
							
							//导出
							form.on('submit(LAY-user-front-export)', function(data){
								var field = data.field;
								var param=encodeURI(JSON.stringify(field));
								//执行重载
								window.open($WEB_ROOT_PATH+'/dhccApi/abnormalHospitalStay/abnormalHospitalStay/exportExcelToSelf?params='+param);
							});
							
							form.on('submit(search)', function(data){
							    var formData = data.field;
					            console.debug(formData);
					            //数据表格重载
					            table.reload('abnormalHospitalStay',{
					            	page : {curr:1}
									,cellMinWidth: 80
									,height:tableHeight-80
					                 ,url: $WEB_ROOT_PATH+'/dhccApi/abnormalHospitalStay/abnormalHospitalStay/abnormalHospitalStay' //后台做模糊搜索接口路径 
					                , where:{ 
					                	 'hospitalName':formData.hospitalName,
					                	 'zyts':formData.zyts,
					                     'admissionDiseaseName':formData.admissionDiseaseName,
					                     'paydate':formData.paydate,
					                     'indate':formData.indate,
					                     'outdate':formData.outdate,
					                     'code':formData.code,
					                     
					                },
					                done: function(res, curr, count){
					                    sumCount(formData)
					                }
					            });
					        });
							
							/*form.on('submit("excel_submit")', function(){
								url : $WEB_ROOT_PATH+'/dhccApi/abnormalHospitalStay/abnormalHospitalStay/exportExcel'  //数据接口
								
							});*/
							
							table.render({
										elem :'#abnormalHospitalStay'
										,cellMinWidth: 80
										,height:tableHeight -80
										,url : $WEB_ROOT_PATH+'/dhccApi/abnormalHospitalStay/abnormalHospitalStay/abnormalHospitalStay'  //数据接口
										,title : '住院天数异常'	
									    ,cols: [[
									     {title: '序号', width:50,type:'numbers'}
									    ,{title:'操作', toolbar: '#barDemo', width:100}
									    ,{field:'hisid',title: 'hisid', width:100,hide:true}
										,{field:'hospitalId',title: '医疗机构编码', width:100}
										,{field:'hospitalName',title: '医疗机构名称', width:130}
										,{field:'zyh', width:160,title: '住院号'}
										,{field:'admissionDeptName', title: '就诊科室', width:100}
										,{field:'patientName', title: '患者姓名', width:100}
										,{field:'admissionDate', title: '入院日期',width:170}
										,{field:'dischargeDate', title: '出院日期',width:170}
										,{field:'zyts', title: '住院天数',width:90}
										,{field:'totalAmount', title: '医疗总费用',width:100}
										,{field:'benefitType', title: '险种类型' ,width:100}
										,{field:'westernMedicineFee',title: '西药费', width:100}
										,{field:'chineseMedicineYinpian', width:80,title: '中药饮片费'}							
										,{field:'chineseMedicineForm',title: '中成药费', width:80}
										,{field:'accommodationFee', width:80,title: '床位费'}
										,{field:'diagnosisFee', width:80,title: '诊查费'}
										,{field:'inspectionFee', title: '检查费', width:80}
										,{field:'testFee', title: '化验费', width:80}
										,{field:'treatmentFee', title: '治疗费', width:80}
										,{field:'nursingFee', title: '护理费', width:80}
										,{field:'materialFee', title: '卫生材料费',width:80}
										,{field:'consultationFee	', title: '一般诊疗费',width:80}
										,{field:'registrationFee', title: '挂号费' ,width:80}
										,{field:'otherFee',title: '其他费', width:80}
										]],
										done: function(res, curr, count){
								            sumCount()
								        },
										page : true //开启分页
									});
							//重置
							form.on('submit(resets)', function (data) {
								$('#getOrgName').combogrid("setValue", "");
								$('#getDiagName').combogrid("setValue", "");
								$("#zyts").val("");
								$("#test6").val("");
								$("#test7").val("");
								$("#test8").val("");
								layui.form.render();
							});
							
							table.on('tool(abnormalHospitalStay)', function(obj){

						        if(obj.event === 'searcDetail'){
						            var data =  {'hisid':obj.data.hisid};
						            var formIndex = layer.open({
						                type: 2,
						                area: ['1100px', '500px'],
						                content:$WEB_ROOT_PATH + '/abnormalHospitalStay/detaileTable',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
						                success: function(layero, index){
						                    var body = layer.getChildFrame('body', index);
						                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						                    iframeWin.initData(data);
						                }
						            });
						            return false;
						        }
						    });
						});

function sumCount(data) {
    layui.$.ajax({
        url:$WEB_ROOT_PATH + '/dhccApi/abnormalHospitalStay/abnormalHospitalStay/countabnormalHospitalStay',
        type:"post",
        dataType:"json",
        data:data,
        success:function (data) {
            if (data.data[0]!=null&&data.data[0]!==""){
                $("#rowsum").text(data.data[0].rowsum);
                $("#sumAmount").text(data.data[0].sumAmount);
            }else {
                $("#rowsum").text(0);
                $("#sumAmount").text(0);

            }

        }
    });
}
//初始化
function init(){
	layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    
	    table.render({
	    	elem: '#medicalTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/getlist'
	            ,cellMinWidth: 80
	            ,height: 415
	            ,where: { 'medicalDetail.medicalId':medical_id}
	            ,cols: [[
	              { type: 'numbers',width:40, title: '序号'  }
	              ,{title:'操作',  width: 120, align:'center',toolbar: '#table-useradmin-webuser'}
	              ,{field:'id', title: 'ID', sort: true, hide:true}
	              ,{field:'itemName',  width: 200, align:'center', title: '项目名称'}
	              ,{field:'itemCode',  width: 200, align:'center', title: '项目编号'}
	              ,{field:'itemStandard',  width: 140, align:'center', title: '项目规格'}
	              ,{field:'itemPrice',  width: 140, align:'center', title: '项目单价'}
	              ,{field:'itemNum',  width: 140, align:'center', title: '项目数量'}
	              ,{field:'itemCost', width: 140, align:'center', title: '项目金额'}
	              ,{field:'drugType', width: 140, align:'center',  title: '药品类别'}
	              ,{field:'billingNo', width: 140, align:'center', title: '收费单据号'}
	              ,{field:'applyPayLevel', width: 140, align:'center', title: '报销级别'}
	              ,{field:'isInsuranceProject', width: 140, align:'center', title: '是否医保项目'}
	              ,{field:'doseForm', width: 140, align:'center', title: '剂型'}
	              ,{field:'feeCreateDate', width: 140, align:'center', title: '费用发生时间'}
	              ,{field:'chargeType', width: 140, align:'center', title: '收费类别'}
	              ,{field:'useDay', width: 140, align:'center', title: '用药天数'}
	              ,{field:'singleDose', width: 140, align:'center', title: '单次用量'}
	              ,{field:'doseUnit', width: 140, align:'center', title: '用量单位'}
	              ,{field:'deliverWay', width: 140, align:'center', title: '给药途径'}
	              ,{field:'takeFrequence', width: 140, align:'center', title: '服用频次'}
	              ,{field:'sumAmount', width: 140, align:'center', title: '总金额'}
	              ,{field:'applyPayAmount', width: 140, align:'center', title: '报销金额'}
	              ,{field:'selfPayAmount', width: 140, align:'center', title: '自付金额'}
	              ,{field:'fullOrdination', width: 140, align:'center', title: '全额统筹'}
	              ,{field:'partialOrdination', width: 140, align:'center', title: '部分统筹'}
	              ,{field:'partialPayment', width: 140, align:'center', title: '部分自付'}
	              ,{field:'fullPayment', width: 140, align:'center', title: '全额自付'}
	              ,{field:'limitPrice', width: 140, align:'center', title: '限价金额'}
	              ,{field:'isIlegal', width: 140, align:'center', title: '是否违规'}
	              ,{field:'ilegalType', width: 140, align:'center', title: '违规类型'}
	              ,{field:'recipelId', width: 140, align:'center', title: '门诊处方ID'}
	              ,{field:'adviceId', width: 140, align:'center', title: '住院医嘱ID'}
	              ,{field:'projectType', width: 140, align:'center', title: '项目类别'}
	              ,{field:'specs', width: 140, align:'center', title: '规格'}
	              ,{field:'totalDrugIntake', width: 140, align:'center', title: '取药总量'}
	              ,{field:'dosageDays', width: 140, align:'center', title: '药量天数'}
	              ,{field:'prescriptionDischarge', width: 180, align:'center', title: '是否出院带药处方'}
	              ,{field:'medicalInsCode', width: 140, align:'center', title: '医疗机构编码'}
	              ,{field:'medicalInsName', width: 140, align:'center', title: '医疗机构名称'}
	              ,{field:'departCode', width: 140, align:'center', title: '科室编码'}
	              ,{field:'departName', width: 140, align:'center', title: '科室名称'}
	              ,{field:'docCode', width: 140, align:'center', title: '医师编码'}
	              ,{field:'docName', width: 140, align:'center', title: '医师姓名'}
	              ,{field:'drugMeaUnit', width: 140, align:'center', title: '药品计量单位'}
	              ,{field:'feeMeaUnit', width: 140, align:'center', title: '收费计量单位'}
	              ,{field:'hosServiceName', width: 180, align:'center', title: '医院的医疗服务名称'}
	              ,{field:'balanceDate', width: 140, align:'center', title: '结算日期'}
	              ,{field:'insCost', width: 140, align:'center', title: '医保金额'}
	              ,{field:'fundCost', width: 140, align:'center', title: '基金支付'}
	              ,{field:'notInsCost', width: 140, align:'center', title: '非医保金额'}
	              ,{field:'durgDosage', width: 140, align:'center', title: '药品用量'}
	              ,{field:'itemUnit', width: 140, align:'center', title: '项目单位'}
	              ,{field:'adviceGroup', width: 140, align:'center', title: '医嘱组号'}
	              ,{field:'adviceOrder', width: 140, align:'center', title: '医嘱顺序号'}
	              ,{field:'selfPayRate', width: 140, align:'center', title: '自付比例'}
	              ,{field:'createDate', width: 140, align:'center', title: '创建日期'}
	              //,{field:'tPiccbidMedicalId', title: 't_Piccbid_medical表主键' }
	            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('medicalTable', {
	            where: field
	        });
	    });
	 
		//监听行点击
	    table.on('tool(medicalTable)', function(obj){
	      var data = obj.data;
	    if(obj.event === 'editinfo'){
	    	//详情方法
			    layer.open({
			          type: 2
			          ,title: data.itemName
			          ,content: $WEB_ROOT_PATH+'/datastatistics/datastatisticsview'
			          ,maxmin: true
			          ,area: ['92%', '450px']
			          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index];
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
}
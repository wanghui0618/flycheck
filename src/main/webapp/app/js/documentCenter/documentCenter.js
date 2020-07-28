//初始化	
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate'], function(){
    var $ = layui.$
        ,form = layui.form
        ,table = layui.table
        ,laydate=layui.laydate;
    //日期范围
    laydate.render({
    	elem: '#finaTime'
    		,trigger:'click'
    			,format:'yyyy-MM-dd'
    				,range: true
    });
    laydate.render({
    	elem: '#inhosDate'
    		,trigger:'click'
    			,format:'yyyy-MM-dd'
    				,range: true
    });
    laydate.render({
    	elem: '#outhosDate'
    		,trigger:'click'
    			,format:'yyyy-MM-dd'
    				,range: true
    });
//加载规则下拉字典
    //localStorage.clear();
	var dict_rule=localStorage.getItem('dict_rule');//从浏览器数据库取出
	var rule=$.parseJSON(dict_rule);//解析成对象
	if(rule){
		for(var i=0 ;i<rule.length;i++){
 			var mm="<option value='"+rule[i].value+"'>"+rule[i].text+"</option>";
 			//$("#ruleType").append(mm); 
 			$("#ruleTypeOn").append(mm); 
 		}
		form.render('select');
	}else{
		//加载违规类型下拉字典
		$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_rule', 
					function(data){
			     		var  dataList= data.dictList;
			     		var dict_rule=JSON.stringify(dataList);//解析为字符串
			     		//localStorage.clear();
			     		localStorage.setItem('dict_rule',dict_rule);//存入浏览器数据库
			     		
			     		for(var i=0 ;i<dataList.length;i++){
			     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			//$("#ruleType").append(mm); 
			     			$("#ruleTypeOn").append(mm); 
			     		}
			     	form.render('select');
		});
	}
    
    
    
    
  
    hideButtonStatic();//静态按钮权限
    table.render({
    	elem: '#medicalTable'
            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo-documentCenter'
            ,cellMinWidth: 100
             ,height: tableHeight
             ,limits: [5,10,20]
             ,limit: 10
             ,where:{"sHType":"cs"}
            ,cols: [[
            	   {type: 'numbers', title: '序号' }
	              ,{field:'id', title: 'ID', sort: true, hide:true}
	              ,{title:'操作', align:'center', width:120,toolbar: '#table-useradmin-webuser',hide:rowOperate(['cityorg-update','cityorg-delete'])}
	              ,{field:'finaStatus', title:'终审结果' }
	              ,{field:'aduitStatus', title:'稽核结果' }
	              ,{field:'resultAppealStatu', title:'公示结果' }
	              ,{field:'userStatus', title:'初审结果' }
	              ,{field:'sysStatus', title:'机审结果' }
	              ,{field:'name', width:80, title:'姓名'}
	              ,{field:'sex',width:60, title: '性别' }
	              ,{field:'age', width:60,title:'年龄' }
	              ,{field:'idcard', width:150,title:'身份证号' }
	              ,{field:'sscno', width:150,title: '社保卡号' }
	              ,{field:'diagType', title:'就诊类型' }
	              ,{field:'orgName', width:180,title:'医疗机构' }
	              ,{field:'condition',  width:150,title: '病情' }
	              ,{field:'paymentDate',width:100, title:'结算日期'}
	              ,{field:'admissionNo', width:170,title:'住院号' }
	              ,{field:'inhosDate',width:100,  title:'入院日期' }
	              ,{field:'outhosDate',width:100,  title:'出院日期' }
	              ,{field:'departName', title:'科室' }
	              ,{field:'billingNo',width:170, title:'收费单据号'}
	              ,{field:'crowdType', title:'人群类别' }
	              ,{field:'reversalMark',width:115, title:'冲销标志'}
	              ,{field:'admissionType',  width:90,title: '住院类型' }
	              ,{field:'medicalType',  width:90,title: '险种类型' }
	              ,{field:'totalCost', title:'总金额' }
	              ,{field:'fundCost', title:'基金支付金额' }
	              ,{field:'selfCost', title:'个人负担金额' }
	              ,{field:'basicCostM', width:90, title:'基本统筹应支付' }
	              ,{field:'povertyAlleviationSubsidy',  width:90,title:'扶贫补助' }
	              ,{field:'financeSubsidy', title:'财政补助' }
	              ,{field:'officialSubsidy',  width:115,title:'公务员补助' }
	              ,{field:'treatmentType',  width:115,title:'待遇享受类别' }
	              ,{field:'medicalTreatmentState',  width:115,title:'医疗待遇状态' }
	              ,{field:'dischargeState', title:'出院状态' }
	              ,{field:'treatmentWay', title:'就诊方式' }
	              ,{field:'stayLength', title:'住院天数' }
	              ,{field:'createTime', title:'数据上传时间' }
	              ,{field:'rangeCost', title:'本次纳入报销范围金额' }
	              ,{field:'basicCostR', title:'基本统筹实际支付' }
	              ,{field:'selfPayAmount', title:'个人自付金额' }
	              ,{field:'selfExpenditureAmount', title:'个人自费金额' }
	              ,{field:'sscAccountCost', title:'个人账户自付' }
	              ,{field:'cashCost', title:'现金支付金额' }
	              ,{field:'largeCostM', title:'大额应支付' }
	              ,{field:'largeCostR', title:'大额实支付' }
	              ,{field:'civilAffairSubsidy', title:'民政救助'}
	              ,{field:'fullOrdination', title:'全额统筹' }
	              ,{field:'partialOrdination', title:'部分统筹' }
	              ,{field:'partialPayment', title:'部分自付' }
	              ,{field:'fullPayment', title:'全额自付' }
	              ,{field:'reimbursementType', title:'报销类型' }
	              ,{field:'hospCount', title:'住院次数' }
	              ,{field:'insuranceMark', title:'险种标志' }
	              ,{field:'outDiagnosisNo', title:'出院诊断编码' }
	              ,{field:'outDiagnosisName', title:'出院诊断名称' }
	            ]]
	            ,done:function(res){
	            	$('tr').eq(1).css("background-color","#EEF6FF");
	      		 }
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
       if(obj.event === 'view'){
    	   window.parent.parent.MeditalDetailView('病例明细',data);
       }   
    });
       
    //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
      
});
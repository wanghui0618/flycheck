layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(
    ['laydate', 'laypage', 'layer', 'table', 'carousel',
        'upload', 'element', 'slider', 'form'],
    function () {
        var laydate = layui.laydate //日期
            , table = layui.table //表格
            , form = layui.form;
        laydate.render({
            elem: '#test6'
            	,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: false
        });
        laydate.render({
            elem: '#test7'
            	,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: false
        });
        laydate.render({
            elem: '#test8'
            	,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: false
        });
        form.on('submit(search)', function (data) {
            var formData = data.field;
            goal_path=formData.path;//全局变量赋值  住院 门诊
  /*           console.log("aaa"+formData.path);
             console.log("aaa"+formData.hospitalId);
             console.log("aaa"+formData.billDate);
             console.log("aaa"+formData.admissionDate);
             console.log("aaa"+formData.dischargeDate);
             console.log("aaa"+formData.dischargeDeptName);
             console.log("aaa"+formData.hisid);
             console.log("aaa"+formData.itemId);
             console.log("aaa"+formData.itemName);
             console.log("aaa"+formData.itemId1);
             console.log("aaa"+formData.itemName1);*/
            //数据表格重载
            table.reload('notExists', {
                page: {curr: 1}
                , cellMinWidth: 80
                , height: tableHeight-80
                , url: $WEB_ROOT_PATH + '/dhccApi/notExists/notExists/notExists1' //后台做模糊搜索接口路径
                , where: {
                    'path': formData.path,
                    /*'benefitType': formData.benefitType,*/
                    'hospitalId': formData.hospitalId,
                    'billDate': formData.billDate,
                  /*  'admissionDate': formData.admissionDate,
                    'dischargeDate': formData.dischargeDate,*/
                    'dischargeDeptName': formData.dischargeDeptName,
                    'hisid': formData.hisid,
                    'itemId': formData.itemId,
                    'itemName': formData.itemName,
                    'itemId1': formData.itemId1,
                    'itemName1': formData.itemName1
                },
                done: function (res, curr, count) {
                   /* sumCount(formData)*/
                    sumCount1(formData)
                }
            });

           
        });
        table.render({
            elem: '#notExists'
            , cellMinWidth: 80
            , height: tableHeight-80
            , url: $WEB_ROOT_PATH + '/dhccApi/notExists/notExists/notExists'  //数据接口
            ,where:{"path":"住院"}
            , title: '项目查询'
            , cols: [[
            	 {type: 'numbers',title: '序号'}
                 , {field: 'hisid', align: 'center', title: '单据号',width:"200"}
                 , {field: 'patientId', align: 'center', title: '个人编码',width:"130"}
                 , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:"125"}
                 , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:"225"}
                 , {field: 'dischargeDeptId', align: 'center', title: '出院科室编码',width:"140"}
                 , {field: 'dischargeDeptName', align: 'center', title: '出院科室名称',width:"180"}
                 , {field: 'doctorId', align: 'center', title: '主诊医师编码',width:"114"}
                 , {field: 'doctorName', align: 'center', title: '主诊医师姓名',width:"133"}
                 , {field: 'dischargeDiseaseNameMain', align: 'center', title: '出院诊断名称',width:"130"}
                 , {field: 'pCategory', align: 'center', title: '费用类别',width:"120"}
                 , {field: 'billDate', align: 'center', title: '结算日期',width:"190"}
                 , {field: 'year', align: 'center', title: '收费年份',width:"110"}
                 , {field: 'month', align: 'center', title: '收费月份',width:"90"}
                 , {field: 'itemIdHosp', align: 'center', title: '医院项目编码',width:"180"}
                 , {field: 'itemNameHosp', align: 'center', title: '医院项目名称',width:"220"}
                 , {field: 'itemId', align: 'center', title: '医保项目编码',width:"180"}
                 , {field: 'itemName', align: 'center', title: '医保项目名称',width:"220"}
                 , {field: 'dosageForm', align: 'center', title: '剂型',width:"100"}
                 , {field: 'drugSpec', align: 'center', title: '规格',width:"100"}
                 , {field: 'packageUnit', align: 'center', title: '最小包装单位',width:"120"}
                 , {field: 'unitPrice', align: 'center', title: '单价',width:"88"}
                 , {field: 'num', align: 'center', title: '数量',width:"65"}
                 , {field: 'cost', align: 'center', title: '金额',width:"70"}
                 , {field: 'bmiConveredAmount', align: 'center', title: '医保范围内金额',width:"141"}
                 , {field: 'bmiPayAmount', align: 'center', title: '医保实际支付金额',width:"160"}
                 , {field: 'pType', align: 'center', title: '支付类别',width:"120"}
                 , {field: 'itemType', align: 'center', title: '项目类型',width:"103"} 
            ]],
            done: function (res, curr, count) {
                /*sumCount()*/
            	console.log(res.data);
                sumCount1();
            },
            page: true //开启分页
        });
        table.on('tool(notExists)', function (obj) {
            console.log("aaa" + obj.data.hisid)
            if (obj.event === 'searcDetail') {
                var data = {'selecthisid': obj.data.hisid,'selectBelong':$("#initbelong").val()};
                var formIndex = layer.open({
                    type: 2,
                    area: ['1100px', '500px'],
                    content: $WEB_ROOT_PATH + '/notExistsmx/notExistsmx',//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        
                        iframeWin.initData(data,goal_path);
                    }
                });
                return false;
            }
        });
       
        form.on('select(xzzt)', function(data){
             if(data.value=='='||data.value=='>'||data.value=='<'){
             	xzzthide();
             	$("#xzxztj").css("width","85px");
             }else{
             	xzztshow();
             	//$("#xzxztj").css("width","158px");
             }
		});
        form.on('select(path)', function(data){
        	//alert(data.value);
        	if(data.value=='住院'){
        		$(".zysj").show();
        	}else{
        		$(".zysj").hide();
        	}
        });

		//重置
		form.on('submit(LAY-user-front-reset)', function () {
			$('input').val('');
			$('#benefitType').val('');
			$('#path').val('');
			$('#timelimit').val('');

		});
		
		  $("#exportInfo").click(function () {
		        window.open($WEB_ROOT_PATH+'/dhccApi/notExists/notExists/exportExcel');
		        return false;
		    });

    });

/*function sumCount(data) {
    layui.$.ajax({
        url: $WEB_ROOT_PATH + '/dhccApi/notExists/notExists/countnotExists',
        type: "post",
        dataType: "json",
        data: data,
        success: function (data) {
            if (data.data[0] != null && data.data[0] !== "") {
                $("#rowsum").text(data.data[0].rowsum);
                $("#sumAmount").text(data.data[0].sumAmount);
            } else {
                $("#rowsum").text(0);
                $("#sumAmount").text(0);

            }

        }
    });
}*/

function sumCount1(data) {
    layui.$.ajax({
        url: $WEB_ROOT_PATH + '/dhccApi/notExists/notExists/countnotExists1',
        type: "post",
        dataType: "json",
        data: data,
        success: function (data) {
        	console.log("data is =="+data.data)
            if (data.data[0] != null && data.data[0] !== "") {
                $("#rowsum1").text(data.data[0].rowsum1);
                $("#sumAmount1").text(data.data[0].sumAmount1);
            } else {
                $("#rowsum1").text(0);
                $("#sumAmount1").text(0);

            }

        }
    });
}

function restart() {
    $("#addGoodsForm")[0].reset();

    layui.form.render();
}
//初始化	 
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var number=999;
	    var number1=333;
	    $(function(){
	    	var clock = $('#clock');
	    	var digit_to_name = 'zero one two three four five six seven eight nine'.split(' ');
	    	var digits = {};
	    	var digits1 = {};
	    	var positions = [
	    		'a', 'b', 'c', 'd', 'e','','f','g','h','i','j'
	    	];
	    	var digit_holder = clock.find('.digits');
	    	$.each(positions, function(){
	    		if(this == ''){
	    			digit_holder.append('<div class="dots" >');
	    		}else{
	    			var pos = $('<div style="background-color: #DDDDDD;width: 35px;height: 62px;">');
	    			for(var i=1; i<8; i++){
	    				pos.append('<span class="d' + i + '" style="margin:5px 4px 5px 4px;">');
	    			}
	    			digits[this] = pos;
	    			digit_holder.append(pos);
	    		}
	    	});
	    	(function update_number(){
	    		/*var now = moment().format("hhmmssdA");*/
	    		/*console.log(now);*/
	    		
	    		var numbers=(number+"").split('');
	    		var numbers1=(number1+"").split('');
	    		switch (numbers.length) {
				case 1:
					digits.a.attr('class', digit_to_name[0]);
		    		digits.b.attr('class', digit_to_name[0]);
		    		digits.c.attr('class', digit_to_name[0]);
		    		digits.d.attr('class', digit_to_name[0]);
		    		digits.e.attr('class', digit_to_name[numbers[0]]);
					break;
				case 2:
					digits.a.attr('class', digit_to_name[0]);
		    		digits.b.attr('class', digit_to_name[0]);
		    		digits.c.attr('class', digit_to_name[0]);
		    		digits.d.attr('class', digit_to_name[numbers[0]]);
		    		digits.e.attr('class', digit_to_name[numbers[1]]);
					break;
				case 3:
					digits.a.attr('class', digit_to_name[0]);
		    		digits.b.attr('class', digit_to_name[0]);
		    		digits.c.attr('class', digit_to_name[numbers[0]]);
		    		digits.d.attr('class', digit_to_name[numbers[1]]);
		    		digits.e.attr('class', digit_to_name[numbers[2]]);
					break;
				case 4:
					digits.a.attr('class', digit_to_name[0]);
		    		digits.b.attr('class', digit_to_name[numbers[0]]);
		    		digits.c.attr('class', digit_to_name[numbers[1]]);
		    		digits.d.attr('class', digit_to_name[numbers[2]]);
		    		digits.e.attr('class', digit_to_name[numbers[3]]);
					break;
				case 5:
					digits.a.attr('class', digit_to_name[numbers[0]]);
		    		digits.b.attr('class', digit_to_name[numbers[1]]);
		    		digits.c.attr('class', digit_to_name[numbers[2]]);
		    		digits.d.attr('class', digit_to_name[numbers[3]]);
		    		digits.e.attr('class', digit_to_name[numbers[4]]);
					break;
				default:
					break;
				}
	    		switch (numbers1.length) {
				case 1:
					digits.f.attr('class', digit_to_name[0]);
		    		digits.g.attr('class', digit_to_name[0]);
		    		digits.h.attr('class', digit_to_name[0]);
		    		digits.i.attr('class', digit_to_name[0]);
		    		digits.j.attr('class', digit_to_name[numbers1[0]]);
					break;
				case 2:
					digits.f.attr('class', digit_to_name[0]);
		    		digits.g.attr('class', digit_to_name[0]);
		    		digits.h.attr('class', digit_to_name[0]);
		    		digits.i.attr('class', digit_to_name[numbers1[0]]);
		    		digits.j.attr('class', digit_to_name[numbers1[1]]);
					break;
				case 3:
					digits.f.attr('class', digit_to_name[0]);
		    		digits.g.attr('class', digit_to_name[0]);
		    		digits.h.attr('class', digit_to_name[numbers1[0]]);
		    		digits.i.attr('class', digit_to_name[numbers1[1]]);
		    		digits.j.attr('class', digit_to_name[numbers1[2]]);
					break;
				case 4:
					digits.f.attr('class', digit_to_name[0]);
		    		digits.g.attr('class', digit_to_name[numbers1[0]]);
		    		digits.h.attr('class', digit_to_name[numbers1[1]]);
		    		digits.i.attr('class', digit_to_name[numbers1[2]]);
		    		digits.j.attr('class', digit_to_name[numbers1[3]]);
					break;
				case 5:
					digits.f.attr('class', digit_to_name[numbers1[0]]);
		    		digits.g.attr('class', digit_to_name[numbers1[1]]);
		    		digits.h.attr('class', digit_to_name[numbers1[2]]);
		    		digits.i.attr('class', digit_to_name[numbers1[3]]);
		    		digits.j.attr('class', digit_to_name[numbers1[4]]);
					break;
				default:
					break;
				}
	    		number++;
	    		number1=number1+2;
	    		setTimeout(update_number, 1000);

	    	})();
	    });

    table.render({
        elem: '#personTop'
        ,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/hospitalCostStatistics/listPersonTop'
        ,cellMinWidth: 80
        ,height: 302
		,where:{"hospitalCostStatistics.orgName":"hopPerson"}
        ,cols: [[
            {type: 'numbers', width:40, title: '编号'}
            ,{field:'orgName',width:250,align:'center', title: '医院名称' }
            ,{field:'personNum', align:'center',title: '住院人数' }
            ,{field:'totalCost', align:'center',title: '住院总费用' }
            ,{field:'violation', align:'center',title: '违规病例数' }
            ,{field:'violationProportion', align:'center',title: '违规占比' }
        ]]
        ,page: false
    });

	table.render({
		elem: '#outpatientPersonTop'
		,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/hospitalCostStatistics/listPersonTop'
		,cellMinWidth: 80
		,height: 302
		,where:{"hospitalCostStatistics.orgName":"outPerson"}
		,cols: [[
			{type: 'numbers', width:40, title: '编号'}
			,{field:'orgName',width:250,align:'center', title: '医院名称' }
			,{field:'personNum', align:'center',title: '门诊人数' }
			,{field:'totalCost', align:'center',title: '门诊总费用' }
			,{field:'violation', align:'center',title: '违规病例数' }
			,{field:'violationProportion', align:'center',title: '违规占比' }
		]]
		,page: false
	});

    // table.render({
    //     elem: '#costTop'
    //     ,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/hospitalCostStatistics/listPersonTop'
    //     ,cellMinWidth: 80
    //     ,height: 310
    //     ,where:{"hospitalCostStatistics.orgName":"cost"}
    //     ,cols: [[
    //         {type: 'numbers', width:40, title: '编号'}
    //         ,{field:'orgName',width:250,align:'center', title: '医院名称' }
    //         ,{field:'totalCost', align:'center',title: '住院费用' }
    //     ]]
    //     ,page: false
    // });

    table.render({
        elem: '#inTop'
        ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalizationMonitor/hospitalizationMonitor/inTop'
        ,cellMinWidth: 80
        ,height: 310
        // ,where:{"hospitalCostStatistics.orgName":"cost"}
        ,cols: [[
            {type: 'numbers', width:40, title: '编号'}
            ,{field:'name',width:250,align:'center', title: '医院名称' }
            ,{field:'totalAmount', align:'center',title: '入院人数' }
        ]]
        ,page: false
    });


	$.getJSON($WEB_ROOT_PATH+'/dhccApi/hospitalizationMonitor/hospitalizationMonitor/yesterday?hospitalizationMonitor.name=totalHos',
		function(data){
			var total_count=document.getElementById("hopNum");
			var totalAmount=data.rows["0"].totalAmount
			if(totalAmount==null){
				totalAmount=0
			}
			total_count.innerText=totalAmount;
		});

	$.getJSON($WEB_ROOT_PATH+'/dhccApi/hospitalizationMonitor/hospitalizationMonitor/yesterday?hospitalizationMonitor.name=person',
		function(data){

			for(var i=0;i<data.rows.length;i++){
				if(data.rows[i].diagType=='1'){
					var person=document.getElementById("person");
					person.innerText=data.rows[i].personNum;
				}
				if(data.rows[i].diagType=='2'){
					var person=document.getElementById("diag2person");
					person.innerText=data.rows[i].personNum;
				}
			}
		});

	$.getJSON($WEB_ROOT_PATH+'/dhccApi/hospitalizationMonitor/hospitalizationMonitor/yesterday?hospitalizationMonitor.name=case',
		function(data){
			for(var i=0;i<data.rows.length;i++){
				if(data.rows[i].diagType=='1'){
					var person=document.getElementById("case");
					person.innerText=data.rows[i].caseNum;
				}
				if(data.rows[i].diagType=='2'){
					var person=document.getElementById("diag2Case");
					person.innerText=data.rows[i].caseNum;
				}
			}
		});
	$.getJSON($WEB_ROOT_PATH+'/dhccApi/hospitalizationMonitor/hospitalizationMonitor/yesterday?hospitalizationMonitor.name=money',
		function(data){
			for(var i=0;i<data.rows.length;i++){
				if(data.rows[i].diagType=='1'){
					var person=document.getElementById("money");
					var money=data.rows[i].totalCost;
					if(money<10000){
                        person.innerText=(money/1).toFixed(2)+"元";
                    }
					if(money>10000&&money<100000000){
						person.innerText=(money/10000).toFixed(2)+"万元";
					}
					if(money>100000000){
						person.innerText=(money/100000000).toFixed(2)+"亿元";
					}
				}
				if(data.rows[i].diagType=='2'){
					var person=document.getElementById("diag2money");
					var money=data.rows[i].totalCost;
                    if(money<10000){
                        person.innerText=(money/1).toFixed(2)+"元";
                    }
                    if(money>10000&&money<100000000){
                        person.innerText=(money/10000).toFixed(2)+"万元";
                    }
                    if(money>100000000){
                        person.innerText=(money/100000000).toFixed(2)+"亿元";
                    }
				}
			}
		});


	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
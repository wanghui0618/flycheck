var indexAll;
var tableAll;
var indexAdd;
//初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index'//主入口模块
}).use(['index', 'table','form','laydate'], function () {
    var  form = layui.form
        , table = layui.table
        ,laydate = layui.laydate;
    tableAll=table;
    laydate.render({
        elem: '#limitBillDate'
        ,format:'yyyyMMdd'
        ,trigger: 'click'
        ,range: true //或 range: '~' 来自定义分割字符
    });

        table.render({
            elem: '#overclockingItemTable'
            ,height: '500'
            ,method:'post'
               , url:$WEB_ROOT_PATH+"/dhccApi/OverclockingAndAmountItem/OverclockingAndAmountItem/searchList"

            ,cols: [
                [
                    {type: 'numbers',title: '序号' ,width:'50'}
                    , {field: 'hisid', align: 'center', title: 'hisid',width:'200' }
                    , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:'120' }
                    , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:'240'}
                    , {field: 'zyh', align: 'center', title: '住院号',width:'160'}
                    , {field: 'patientId', align: 'center', title: '个人编码',width:'220'}
                    , {field: 'socialCardId', align: 'center', title: '患者社保卡号',width:'220'}
                    , {field: 'patientName', align: 'center', title: '患者姓名',width:'102'}
                    , {field: 'patientGender', align: 'center', title: '性别',width:'102'}
                    , {field: 'patientBirthday', align: 'center', title: '出生日期',width:'102'}
                    , {field: 'billDate', align: 'center', title: '结算日期',width:'102'}
                    , {field: 'dischargeDeptName', align: 'center', title: '出院科室',width:'177'}
                    , {field: 'admissionDate', align: 'center', title: '入院日期',width:'110'}
                    , {field: 'dischargeDate', align: 'center', title: '出院日期',width:'116'}
                    , {field: 'zyts', align: 'center', title: '住院天数',width:'100'}
                    , {field: 'usageCount', align: 'center', title: '项目使用次数',width:'100'}
                ]
            ]
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            ,
            page: true
        });

    $("#searchItems").change(function(){
        //要触发的事件
        var itemlist=$("#searchItems").val();
        var arr = itemlist.split(",")
        var resources = "";
        for (var i = 0; i < arr.length; i++) {
            var arr1 = arr[i].split(/\s+/);
            for (var j = 0; j < arr1.length; j++) {
                if (jQuery.trim(arr1[j]) != "") {
                    resources += jQuery.trim(arr1[j]) + ",";
                }
            }
        }
        $("#selectItems").val(resources) ;
        return false;
    });

//监听下拉选择框
    form.on('select(test2)',function (data) {
      var isMZ=data.value;
      if(isMZ=="门诊") {
          $("#mzzyts").attr("disabled", true);

      }else {
          $("#mzzyts").attr("disabled", false);

      }
        form.render("select");
    });

    form.on('select(test)',function (data) {
        var isOverNum =data.value;
        if(isOverNum=="超频次收费"){
            $("#limitChargeType").val("");
            $("#limitDayType").val("");
            $("#limitChargeType").attr("disabled",true);
            $("#limitDayType").attr("disabled",true);
            form.render('select');
        }else{
            $("#limitChargeType").attr("disabled",false);
            $("#limitDayType").attr("disabled",false);
            form.render('select');
        }
        if(isOverNum=="超出住院天数收费"){
            $("#limitNumber").val("");
            $("#limitNumber").attr("disabled",true);
        }else {
            $("#limitNumber").attr("disabled",false);
        }

        return false;

    });
    //监听搜索
    form.on('submit(LAY-OverclockingItem-search)', function (data) {
        var field=data.field;
         var limitNumber=$("#limitNumber").val();

         if( $("#limitBelong").val()==""){
             layer.alert("请选择就诊途径");
             return false;
         }
         if( $("#getOrgName").val()==""){
             layer.alert("请选择医疗机构");
             return false;
         }
        if( $("#limitQueryPlan").val()==""){
            layer.alert("请选择查询方案");
            return false;
        }
        if( $("#limitQueryPlan").val()=="超频次收费"){

            if(limitNumber==""){
                layer.alert("请输入限制次数");
                return false;
            };
            var isnum =/^[\d|\.]*$/.test(limitNumber);
            if(!isnum){
                layer.alert("请输入正确的数字")
                return false;
            };

        };
        if( $("#limitQueryPlan").val()=="超出住院天数收费"){
            if( $("#limitChargeType").val()==""){
                layer.alert("请选择项目收费类型");
                return false;
            }
            if( $("#limitDayType").val()==""){
                layer.alert("请选择住院天数计算方式");
                return false;
            }

        };

        if( $("#limitBillDate").val()==""){
            layer.alert("请选择结算时间范围");
            return false;
        }
        if( $("#limitCountType").val()==""){
            layer.alert("请选择使用次数的计算方式");
            return false;
        }
        if( $("#limitAppearType").val()==""){
            layer.alert("请选择项目的出现条件");
            return false;
        }
        if( $("#selectItems").val()==""){
            layer.alert("请输入要查询的医保项目名称");
            return false;
        }
      //执行重载
        layui.table.reload('overclockingItemTable', {

           where: field
            ,page: { curr: 1}
        });
        return false;
    });


    $("#exportInfo").click(function () {
        window.open($WEB_ROOT_PATH+'/dhccApi/OverclockingAndAmountItem/OverclockingAndAmountItem/exportExcel');
        return false;
    });
//重置数据字典残留问题
    $("#reset").click(function () {
        $('#getOrgName').combogrid("setValue", "");
        $('#getDiagName').combogrid("setValue", "");
        $("#searchItems").val("");
    });


});


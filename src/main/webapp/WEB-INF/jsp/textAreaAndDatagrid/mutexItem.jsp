<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<title>项目互斥与相关</title>
</head>
<style>
.wjl5 input{
width:150px;
}
.wjl4 input{
width:80px;
}
.wjl3 input{
width:200px;
}
.wjl2 input{
    width:120px;
}
.wjl1 input{
    width:120px;
}
.wjl input{
width:120px;
}

</style>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline ">
		                <label class="layui-form-label ">就诊类型</label>
		                <div class="layui-input-inline wjl" style="width:120px">
		                    <select id="dialogType" name="dialogType" lay-filter="dialogType" >
		                        <option value="mz" >门    诊</option>
		                        <option value="zy" selected>住    院</option>
	                    	</select>
		                </div>
            		</div>
				<!-- <div class="layui-inline">
                    <label class="layui-form-label">医疗机构</label>
                    <div class="layui-input-inline wjl3" style="width:80px">
		                    <select id="hospType" name="hospType" lay-filter="hospType" >
		                        <option value="code" selected>编码</option>
		                        <option value="name">名称</option>
	                    	</select>
		             </div>
                    <div class="layui-input-inline " style="width:140px;margin-left:-5px">
                         <input type="text" id="hospValue" name="hospValue" placeholder="请输入" autocomplete="off" class="layui-input" style="width:140px">
                    </div>
                </div> -->
                
                 <div class="layui-inline" style="padding-left:40px">
	                <label class="layui-form-label">医院选择</label>
	                <div class="layui-input-inline wjl3" style="width:200px" id="hosp">
	                    <input id="getOrgName" name="getOrgName"  />
	                    <input type="text" id="orgCode" name="orgCode"   style="display: none;"/>
	                </div>
            	</div>
                
                <div class="layui-inline">
                    <label class="layui-form-label">科室选择</label>
                    <div class="layui-input-inline " style="width:72px">
		                    <select id="dischargeType" name="dischargeType" lay-filter="dischargeType" >
		                        <option value="code" selected>编码</option>
		                        <option value="name">名称</option>
	                    	</select>
		             </div>
                    <div class="layui-input-inline " style="width:140px">
                         <input type="text" id="dischargeValue" name="dischargeValue" placeholder="请输入" autocomplete="off" class="layui-input" style="width:140px">
                    </div>
                </div>
                           	
            	<div class="layui-inline" style="padding-left:30px">
	                <label class="layui-form-label ">互斥类型</label>
	                <div class="layui-input-inline wjl5" style="width:150px">
	                    <select id="mutex" name="mutex" lay-filter="mutex" >
	                        <option value="mix" selected>互斥</option>
	                        <option value="mutex">相关</option>
                    	</select>
	                </div>
            	</div>
                
                 </div>
                <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width:70px">左框项目</label>
                    <div class="layui-input-inline wjl1" style="width:120px">
		                    <select id="itemType" name="itemType" lay-filter="itemType" >
		                        <option value="hospcode" >医院编码</option>
		                        <option value="hospname">医院名称</option>
		                        <option value="code" selected>医保编码</option>
		                        <option value="name">医保名称</option>
	                    	</select>
		             </div>
                    
                </div>
                <div class="layui-inline" style="padding-left:40px">
                    <label class="layui-form-label" style="width:70px">右框项目</label>
                    <div class="layui-input-inline wjl2" style="width:120px">
		                    <select id="itemHospType" name="itemHospType" lay-filter="itemHospType" >
		                        <option value="hospcode1" >医院编码</option>
		                        <option value="hospname1">医院名称</option>
		                        <option value="code1" selected>医保编码</option>
		                        <option value="name1">医保名称</option>
	                    	</select>
		             </div>
                    <div class="layui-input-inline " style="width:76px">
                         <!-- <input type="text" id="dischargeValue" name="dischargeValue" placeholder="请输入" autocomplete="off" class="layui-input" style="width:140px"> -->
                    </div>
                </div>
                
 				<div class="layui-inline ">
	                <label class="layui-form-label ">出现条件</label>
	                <div class="layui-input-inline wjl4" style="width:80px">
	                    <select id="limite" name="limite" lay-filter="limite" >
	                        <option value="tc" >同次</option>
	                        <option value="tt" selected>同天</option>
                    	</select>
	                </div>
            	</div>
				<div class="layui-inline">
					<button id="consumableRule-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search" stylename="search">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					</button>
					 <button class="layui-btn layui-btn-primary" lay-submit
                        id="resets" stylename="allUpdate" lay-filter="LAY-user-front-reset" onclick="restart()">
                    <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                </button>
					<button id="violation-dc" stylename="export"
							class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
							lay-submit lay-filter="LAY-user-front-export">
							<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
					</button>
				</div>

				</div>

			</div>

			<div class="layui-card-body">
				<div class="layui-container11 ">
					<div class="layui-row">
						<div class=" layui-col-md2">
							<textarea id="bbj" name="bbj" required lay-verify="required" placeholder="请输入被比较项目,多个项目之间用逗号隔开" class="layui-textarea" style="width:98%;min-height:540px"></textarea>
						</div>
						<div class=" layui-col-md2">
							<textarea id="bj" name="bj" required lay-verify="required" placeholder="请输入比较项目,多个项目之间用逗号隔开" class="layui-textarea" style="width:98%;min-height:540px"></textarea>
						</div>
						<div class=" layui-col-md8">
						 	<table id="tableTest" class="layui-hide" lay-filter="tableTest"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript">
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','form'], function(){
    var $ = layui.$
    ,form = layui.form
    ,table = layui.table;
     table.render({
        elem: '#tableTest'
        ,url: $WEB_ROOT_PATH+'/dhccApi/notExists/notExists/searchMutex1'
        ,height: tableHeight-11
        ,cols: [[
            {type: 'numbers',title: '序号'}
            , {field: 'hisid', align: 'center', title: '单据号',width:"200"}
            , {field: 'patientId', align: 'center', title: '个人编码',width:"130"}
            , {field: 'zyh', align: 'center', title: '住院号',width:"160"}
            , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:"125"}
            , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:"225"}
            , {field: 'dischargeDeptId', align: 'center', title: '出院科室编码',width:"140"}
            , {field: 'dischargeDeptName', align: 'center', title: '出院科室名称',width:"180"}
            , {field: 'doctorId', align: 'center', title: '主诊医师编码',width:"114"}
            , {field: 'doctorName', align: 'center', title: '主诊医师姓名',width:"133"}
            , {field: 'dischargeDiseaseNameMain', align: 'center', title: '出院诊断名称',width:"130"}
            , {field: 'pCategory', align: 'center', title: '费用类别',width:"120"}
            , {field: 'usageDate', align: 'center', title: '项目使用日期',width:"200"}
            , {field: 'usageDateFlag', align: 'center', title: '项目使用日期标识',width:"165"}
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
            , {field: 'pTypePct', align: 'center', title: '报销比例',width:"103"}
            , {field: 'itemType', align: 'center', title: '项目类型',width:"103"} 
        ]]
        ,page: true
    }); 
    form.on('submit(LAY-user-front-search)', function (data) {
        var formData = data.field;
        var mybbj=$('#bbj').val();
        var mybj=$('#bj').val();
        var dischargeValue=$('#dischargeValue').val();
        var getOrgName=$('#getOrgName').val();
        var orgCode=$('#orgCode').val();
        var dialogType=$('#dialogType').val();
        console.log(formData.dialogType);
        console.log(getOrgName);
        console.log(orgCode);
        console.log(formData.dischargeType);
        console.log(dischargeValue);
        console.log(formData.itemType);
        console.log(formData.itemHospType);
        console.log(formData.limite);
        console.log(formData.mutex);
        console.log(mybbj);
        console.log(mybj);
        if(dialogType==''||dialogType==null){
            alert("请选择医院");
        }else if(getOrgName==''||getOrgName==null){
            alert("请选择医院");
        }else if(mybbj==''||mybbj==null){
        	alert("左边框内容不能为空，请输入项目");
        }else if(mybj==''||mybj==null){
        	alert("右边框内容不能为空，请输入项目");
        }else{
        //数据表格重载
        if(formData.dialogType=='zy'){
        console.log("住院哈")
        table.reload('tableTest', {
            page: {curr: 1}
            , cellMinWidth: 80
            , height: tableHeight-80
             , url: $WEB_ROOT_PATH + '/dhccApi/notExists/notExists/searchMutex' //后台做模糊搜索接口路径 
            , where: {
                'dialogType': formData.dialogType,
                'hospType': getOrgName,
                'dischargeType':formData.dischargeType,
                'itemType':formData.itemType,
                'itemHospType':formData.itemHospType,
                'limite': formData.limite,
                'mutex': formData.mutex,
                'bbj': mybbj,
                'bj': mybj,
                'dischargeValue': dischargeValue,
                'hospValue': orgCode
            }
            ,cols: [[
                {type: 'numbers',title: '序号'}
                , {field: 'hisid', align: 'center', title: '单据号',width:"200"}
                , {field: 'patientId', align: 'center', title: '个人编码',width:"115"}
                , {field: 'zyh', align: 'center', title: '住院号',width:"160"}
                , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:"125"}
                , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:"225"}
                , {field: 'dischargeDeptId', align: 'center', title: '出院科室编码',width:"125"}
                , {field: 'dischargeDeptName', align: 'center', title: '出院科室名称',width:"140"}
                , {field: 'doctorId', align: 'center', title: '主诊医师编码',width:"120"}
                , {field: 'doctorName', align: 'center', title: '主诊医师姓名',width:"120"}
                , {field: 'dischargeDiseaseNameMain', align: 'center', title: '出院诊断名称',width:"120"}
                , {field: 'pCategory', align: 'center', title: '费用类别',width:"95"}
                , {field: 'usageDate', align: 'center', title: '项目使用日期',width:"150"}
                , {field: 'usageDateFlag', align: 'center', title: '项目使用日期标识',width:"165"}
                , {field: 'billDate', align: 'center', title: '结算日期',width:"125"}
                , {field: 'year', align: 'center', title: '收费年份',width:"100"}
                , {field: 'month', align: 'center', title: '收费月份',width:"95"}
                , {field: 'itemIdHosp', align: 'center', title: '医院项目编码',width:"125"}
                , {field: 'itemNameHosp', align: 'center', title: '医院项目名称',width:"155"}
                , {field: 'itemId', align: 'center', title: '医保项目编码',width:"125"}
                , {field: 'itemName', align: 'center', title: '医保项目名称',width:"155"}
                , {field: 'dosageForm', align: 'center', title: '剂型',width:"70"}
                , {field: 'drugSpec', align: 'center', title: '规格',width:"70"}
                , {field: 'packageUnit', align: 'center', title: '最小包装单位',width:"120"}
                , {field: 'unitPrice', align: 'center', title: '单价',width:"85"}
                , {field: 'num', align: 'center', title: '数量',width:"65"}
                , {field: 'cost', align: 'center', title: '金额',width:"85"}
                , {field: 'bmiConveredAmount', align: 'center', title: '医保范围内金额',width:"135"}
                , {field: 'bmiPayAmount', align: 'center', title: '医保实际支付金额',width:"150"}
                , {field: 'pType', align: 'center', title: '支付类别',width:"100"}
                , {field: 'pTypePct', align: 'center', title: '报销比例',width:"103"}
                , {field: 'itemType', align: 'center', title: '项目类型',width:"100"}
            ]]
        });
        }else{
        	table.render({
                elem: '#tableTest'
                ,url: $WEB_ROOT_PATH+'/dhccApi/notExists/notExists/searchMutex1'
                ,height: tableHeight-11
                ,cols: [[
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
                ]]
                ,page: true
            }); 
        	console.log("门诊哈")
        	table.reload('tableTest', {
                page: {curr: 1}
                , cellMinWidth: 80
                , height: tableHeight-80
                 , url: $WEB_ROOT_PATH + '/dhccApi/notExists/notExists/searchMutex' //后台做模糊搜索接口路径 
                , where: {
                    'dialogType': formData.dialogType,
                    'hospType': getOrgName,
                    'dischargeType':formData.dischargeType,
                    'itemType':formData.itemType,
                    'itemHospType':formData.itemHospType,
                    'limite': formData.limite,
                    'mutex': formData.mutex,
                    'bbj': mybbj,
                    'bj': mybj,
                    'dischargeValue': dischargeValue,
                    'hospValue': orgCode
                }
                ,cols: [[
                    {type: 'numbers',title: '序号'}
                    , {field: 'hisid', align: 'center', title: '单据号',width:"200"}
                    , {field: 'patientId', align: 'center', title: '个人编码',width:"115"}
                    , {field: 'hospitalId', align: 'center', title: '医疗机构编码',width:"125"}
                    , {field: 'hospitalName', align: 'center', title: '医疗机构名称',width:"225"}
                    , {field: 'dischargeDeptId', align: 'center', title: '科室编码',width:"125"}
                    , {field: 'dischargeDeptName', align: 'center', title: '科室名称',width:"140"}
                    , {field: 'doctorId', align: 'center', title: '主诊医师编码',width:"120"}
                    , {field: 'doctorName', align: 'center', title: '主诊医师姓名',width:"120"}
                    , {field: 'dischargeDiseaseNameMain', align: 'center', title: '出院诊断名称',width:"120"}
                    , {field: 'pCategory', align: 'center', title: '费用类别',width:"95"}
                    , {field: 'billDate', align: 'center', title: '结算日期',width:"125"}
                    , {field: 'year', align: 'center', title: '收费年份',width:"100"}
                    , {field: 'month', align: 'center', title: '收费月份',width:"95"}
                    , {field: 'itemIdHosp', align: 'center', title: '医院项目编码',width:"125"}
                    , {field: 'itemNameHosp', align: 'center', title: '医院项目名称',width:"155"}
                    , {field: 'itemId', align: 'center', title: '医保项目编码',width:"125"}
                    , {field: 'itemName', align: 'center', title: '医保项目名称',width:"155"}
                    , {field: 'dosageForm', align: 'center', title: '剂型',width:"70"}
                    , {field: 'drugSpec', align: 'center', title: '规格',width:"70"}
                    , {field: 'packageUnit', align: 'center', title: '最小包装单位',width:"120"}
                    , {field: 'unitPrice', align: 'center', title: '单价',width:"85"}
                    , {field: 'num', align: 'center', title: '数量',width:"65"}
                    , {field: 'cost', align: 'center', title: '金额',width:"85"}
                    , {field: 'bmiConveredAmount', align: 'center', title: '医保范围内金额',width:"135"}
                    , {field: 'bmiPayAmount', align: 'center', title: '医保实际支付金额',width:"150"}
                    , {field: 'pType', align: 'center', title: '支付类别',width:"100"}
                    , {field: 'itemType', align: 'center', title: '项目类型',width:"100"}
                ]]
            });
        }
    }
    });


//重置
form.on('submit(LAY-user-front-reset)', function () {
	$('#getOrgName').combogrid("setValue", "");
	$('#bbj').val('');
	$('#bj').val('');
	$('#dischargeValue').val('')
});
 // 导出
	form.on('submit(LAY-user-front-export)',function(data) {
						var field = data.field;
				        var mybbj=$('#bbj').val();
				        var mybj=$('#bj').val();
						console.log(mybbj);
						console.log(mybj);
						var c;
						var d="";
						for (i = 0; i < mybbj.length; i++) {
					        c = mybbj.substr(i, 1);
					        if ( c=="\r" || c == "\n")
					            d = d + "\t";
					        else if (c != "\r")
					            d = d + c;
					    }
						var c1;
						var d1="";
						for (i = 0; i < mybj.length; i++) {
					        c1 = mybj.substr(i, 1);
					        if ( c1== "\r" || c1 == "\n")
					            d1 = d1 + "\t";
					        else if (c1 != "\r")
					            d1 = d1 + c1;
					    }
				        field.mybbj=d;
						field.mybj=d1;
					    var param=encodeURI(JSON.stringify(field)); 
					    console.log(field);
	 // 执行重载
	  window.open($WEB_ROOT_PATH+ '/dhccApi/notExists/notExists/exportMutex?param='+param);
				});  
});

</script>
</body>
</html>
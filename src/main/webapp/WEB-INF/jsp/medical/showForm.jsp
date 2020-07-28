<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>

<style>
.showtable{
	width:1200px;
}
.showtable tr{
	height:32px;
}
.showtable td{
	padding-left:10px;
	border: none;
}
.showtable2{
	width:1200px;
}
.showtable2 tr{
	height:32px;
}
.showtable2 td{
	padding-left:10px;
	border: none;
}
</style>
<form style="width: 100%; background: #fafafa">
<table class="showtable" border="0" cellpadding="0" cellspacing="0">
	<!-- <tr>
		<td style="width: 250px;">1.限性别违规</td>
		<td colspan="2">违规原因：该患者为男性，实际用药女性</td>
	</tr>
	-->
</table>
<table class="showtable2" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td style="width:279px"><input type="radio"  name="violationStatus" value="2" >正常</input><input type="radio"  style="margin-left:6px" name="violationStatus" value="0" >违规</input><input type="radio"  style="margin-left:6px" name="violationStatus" value="1" >疑似违规</input><a onclick="save1(this)" class="layui-btn layui-btn-xs" lay-event="yes"><i class="layui-icon layui-icon-ok"></i>提交</a></td>
		<td >预扣金额：<input style="height:28px" id="withholdingAmount" name="withholdingAmount" class="easyui-input "  /></td>
		<td >预扣数量：<input style="height:28px" id="withholdingQuantity" name="withholdingQuantity"  class="easyui-input "  /></td>
		<td >扣除原因：<input id="deductions" style="height:28px;width:300px;" name="deductions" class="easyui-input" /></td>
	</tr>
	
</table>

</form>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medical/validator.js"></script> --%>
<script type="text/javascript">
$.fn.serializeObject = function () {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function () {
	    if (o[this.name] !== undefined) {
	        if (!o[this.name].push) {
	            o[this.name] = [o[this.name]];
	        }
	        o[this.name].push(this.value || '');
	    } else {
	        o[this.name] = this.value || '';
	    }
	});
	return o;
	};
	$(function(){
		//根据medical_id\cost_id查询数据，渲染
	});
	/* $("input[name=violationStatus]").click(function(){
	    if(violationStatus=='2'){
			$("#withholdingAmount").val("");
			$("#withholdingQuantity").val("");
			$("#deductions").val("");
	    }
	}); */
	function save1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		var formTr = tr.next();
		var valueObject=formTr.find("form").serializeObject();
		console.info(valueObject);
		if(!IsNumberCheckSub(valueObject.withholdingQuantity)){
			 layer.msg('数量必须是数字!');
			return;
		}
		if(!IsNumberCheckSub(valueObject.withholdingAmount)){
			 layer.msg('金额必须是数字!');
			return;
		} 
		
		getvalue(valueObject,index);
	}
	function cancel1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		//console.log(index)
		cancelItem(index);
	}
	//正则判断数字
	function IsNumberCheckSub(value){
		// return /^[0-9]+$/.test(value);	 
		return /^\d+$|^\d+\.\d+$/g.test(value);	 
	}

</script>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<form style="width: 100%; background: #fafafa">
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
<table class="showtable" border="0" cellpadding="0" cellspacing="0">

</table>
<table class="showtable2" border="0" cellpadding="0" cellspacing="0">
<tr>
		<!-- <td style="width: 250px;"></td> -->
		<td style="width: 1px;"><a onclick="save1(this)" class="layui-btn layui-btn-xs" lay-event="yes"><i class="layui-icon layui-icon-ok"></i>提交</a></td>
		<td style="width: 100px;">扣除金额：<input  style="height:28px;" id="withholdingAmount" name="withholdingAmount" class="easyui-input" /></td>
		<td  style="width: 100px;">扣除数量：<input  style="height:28px;" id="withholdingQuantity" name="withholdingQuantity" class="easyui-input" /></td>
		<td  style="width: 150px;">扣除原因：<input  id="deductions" style="height:28px;width:150px;" name="deductions" class="easyui-input" /></td>
	</tr>
</table>

</form>

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
	function save1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		var formTr = tr.next();
		var valueObject=formTr.find("form").serializeObject();
		//var row = $('#dg').datagrid('getRows')[index];
		//saveItem(index);
		getvalue(valueObject,index);
	}
	function cancel1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		console.log(index)
		cancelItem(index);
	}
	
	/* var deductions=$("#deductions").val();
	var withholdingAmount=$("#withholdingAmount").val();
	var withholdingQuantity=$("#withholdingQuantity").val();
	$("#deductionsSpan").val(deductions);
	$("#withholdingAmountSpan").val(deductions);
	$("#withholdingQuantitySpan").val(withholdingQuantity); */
</script>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<form style="width: 100%; background: #fafafa">
<style>
.showtable {
	width: 1200px;
}

.showtable tr {
	height: 32px;
}

.showtable td {
	padding-left: 28px;
	border: none;
}

.showtable2 {
	width: 1200px;
}

.showtable2 tr {
	height: 32px;
}

.showtable2 td {
	padding-left: 28px;
	border: none;
}
</style>
<table class="showtable" border="0" cellpadding="0" cellspacing="0">

</table>
<table class="showtable2" id="costDetial" border="0" cellpadding="0" cellspacing="0">
<tr>
        <td style="display:none;margin-top: 4px;width: 100px;height: 28px;" class="changeCostMoney"><a onclick="save1()" class="layui-btn layui-btn-xs" lay-event="yes"><i class="layui-icon layui-icon-ok"></i>提交</a></td>
		<td style="position:relative;width: 280px;">扣除金额：<input  style="height:28px;border:0;width:180px; " id="withholdingAmount" readonly="true" name="withholdingAmount" class="easyui-input" /></td>
		<td style="position:relative;width: 350px;">扣除数量：<input  style="height:28px;border:0;width:180px;" id="withholdingQuantity" readonly="true" name="withholdingQuantity" class="easyui-input" /></td>
		<td >扣除原因：<input  style="height:28px;width:300px;border:0;"  id="deductions" readonly="true" name="deductions" class="easyui-input" /></td>
		<td style="display:none;"><input  style="height:28px;border:0;width:180px; " id="medicalId"  name="medicalId" class="easyui-input" /></td>
	</tr>
</table>

</form>

<script type="text/javascript">
var withholdingQuantity;
var withholdingAmount;
var deductions;
	$(function(){
		
	});
	function save1(){
		saveItem();
	}
	function cancel1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		cancelItem(index);
	}
	
</script>
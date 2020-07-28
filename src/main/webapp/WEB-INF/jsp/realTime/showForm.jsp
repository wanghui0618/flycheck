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
	<!-- <tr>
		<td style="width: 250px;">1.限性别违规</td>
		<td colspan="2">违规原因：该患者为男性，实际用药女性</td>
	</tr>
	-->
</table>
<table class="showtable2" border="0" cellpadding="0" cellspacing="0">
<tr>
		<!-- <td style="width: 250px;"></td> -->
		
		<td >扣除金额：<input  style="height:28px;border:0;width:100px; " id="withholdingQuantity" readonly="true" name="withholdingQuantity" class="easyui-input" /></td>
		<td>扣除数量：<input  style="height:28px;border:0;width:100px;" id="withholdingAmount" readonly="true" name="withholdingAmount" class="easyui-input" /></td>
		<td>扣除原因：<input  style="height:28px;width:300px;border:0;"  id="deductions" readonly="true" name="deductions" class="easyui-input" /></td>	</tr>
</table>
</form>

<script type="text/javascript">
	$(function(){
		//根据medical_id\cost_id查询数据，渲染
	});
	function save1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		//var row = $('#dg').datagrid('getRows')[index];
		saveItem(index);
	}
	function cancel1(target){
		var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
		var index = parseInt(tr.attr('datagrid-row-index'));
		console.log(index)
		cancelItem(index);
	}
</script>
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
.showtable2 td input{
	/* padding-left:10px; */
	background-color:#fafafa;
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
		<td style="width:279px"><input type="radio"  name="violationStatus" value="2" >正常</input><input type="radio"  style="margin-left:6px" name="violationStatus" value="0" >违规</input><input type="radio"  style="margin-left:6px" name="violationStatus" value="1" >疑似违规</input></td>
		<td >预扣金额：<input style="height:28px" id="withholdingAmount" name="withholdingAmount" class="easyui-input" /></td>
		<td >预扣数量：<input style="height:28px" id="withholdingQuantity" name="withholdingQuantity"  class="easyui-input" /></td>
		<td >扣除原因：<input id="deductions" style="height:28px;width:300px;" name="deductions" class="easyui-input" /></td>
	</tr>
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
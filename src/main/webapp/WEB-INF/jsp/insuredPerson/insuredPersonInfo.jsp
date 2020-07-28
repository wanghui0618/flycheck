<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>

<style>
</style>
<title>参保人信息新增/修改页面</title>
<style>
.layui-form-label {
	width: 115px;
}
</style>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
		
			<div class="layui-card-body">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="insuredPerson.id" id="id" hide=true>
					<input type="hidden" name="insuredPerson.cityName" id="cityName">
					<!-- 第一行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">城市名称</label>
						<div class="layui-input-inline">
							<select name="insuredPerson.cityCode" autocomplete="off"
								id="city" lay-verify="required" lay-filter="city">
								<option value="">请选择城市</option>
							</select>
						</div>

						<label class="layui-form-label">参保号</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceCode"
								name="insuredPerson.insuranceCode" lay-verify="required|number"
								placeholder="请输入参保号" autocomplete="off" class="layui-input">
						</div>
					</div>


					<fieldset class="layui-elem-field layui-field-title"
						style="margin-top: 0px;">
						<legend>参保人基础信息</legend>
					</fieldset>


					<!-- 第二行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-inline">
							<input type="text" id="name" name="insuredPerson.name"
								lay-verify="required" placeholder="请输入姓名" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">性别</label>
						<div class="layui-input-inline" id="sex">
							<input type="radio" name="insuredPerson.sex" value="1" title="男"
								> <input type="radio" name="insuredPerson.sex"
								value="0" title="女">
						</div>

					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">出生日期</label>
						<div class="layui-input-inline">
							<input type="text" id="birthday" name="insuredPerson.birthday"
								lay-verify="required" placeholder="请选择有效出生日期" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<!-- 第3行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">证件类型</label>
						<div class="layui-input-inline">
							<input type="text" id="idCardType" name="insuredPerson.idCardType"
								lay-verify="required" placeholder="请输入证件类型" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">身份证号</label>
						<div class="layui-input-inline">
							<input type="text" id="idCard" name="insuredPerson.idCard"
								lay-verify="required|identity" placeholder="请输入身份证号" autocomplete="off"
								class="layui-input">
						</div>
					</div>


				<fieldset class="layui-elem-field layui-field-title"
						style="margin-top: 20px;">
						<legend>个人详情</legend>
					</fieldset>
					
					<div class="layui-form-item">
						<label class="layui-form-label">联系电话</label>
						<div class="layui-input-inline">
							<input type="text" id="phone" name="insuredPerson.phone"
								lay-verify="required|phone" placeholder="请输入联系电话" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">住址</label>
						<div class="layui-input-inline">
							<input type="text" id="address" name="insuredPerson.address"
								lay-verify="required" placeholder="请输入住址" autocomplete="off"
								class="layui-input">
						</div>

					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">电子邮箱</label>
						<div class="layui-input-inline">
							<input type="text" id="email" name="insuredPerson.email"
								lay-verify="required|email" placeholder="请输入电子邮箱" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">民族</label>
						<div class="layui-input-inline">
							<input type="text" id="nation" name="insuredPerson.nation"
								lay-verify="required" placeholder="请输入机构级别" autocomplete="off"
								class="layui-input">
						</div>

					</div>

					
					<div class="layui-form-item">

						<label class="layui-form-label">婚姻状态</label>
						<div class="layui-input-inline" id="maritalStatus">
							<input type="radio" name="insuredPerson.maritalStatus" value="0"
								title="未婚" > <input type="radio"
								name="insuredPerson.maritalStatus" value="1" title="已婚">
						</div>
						<label class="layui-form-label">异地安置</label>
						<div class="layui-input-inline" id="relocationSigns">
							<input type="radio" name="insuredPerson.relocationSigns"
								value="0" title="本地" > <input type="radio"
								name="insuredPerson.relocationSigns" value="1" title="异地">
						</div>
					</div>

					
					<div class="layui-form-item">
						<label class="layui-form-label">户口性质</label>
						<div class="layui-input-inline">
							<input type="text" id="householdRegist"
								name="insuredPerson.householdRegist" lay-verify="required"
								placeholder="请输入户口性质" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">户口所在地</label>
						<div class="layui-input-inline">
							<input type="text" id="householdAddress"
								name="insuredPerson.householdAddress" lay-verify="required"
								placeholder="请输入户口所在地" autocomplete="off" class="layui-input">
						</div>
					</div>

				
					<div class="layui-form-item">
						<label class="layui-form-label">住址邮政编码</label>
						<div class="layui-input-inline">
							<input type="text" id="postalCode"
								name="insuredPerson.postalCode" lay-verify="required|number"
								placeholder="请输入住址邮政编码" autocomplete="off" class="layui-input">
						</div>
					</div>


					<fieldset class="layui-elem-field layui-field-title"
						style="margin-top: 20px;">
						<legend>社会信息</legend>
					</fieldset>


					
					<div class="layui-form-item">
						<label class="layui-form-label">个人状态</label>
						<div class="layui-input-inline">
							<input type="text" id="personStatus" name="insuredPerson.personStatus"
								lay-verify="required" placeholder="请输入个人状态" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">健康状态</label>
						<div class="layui-input-inline">
							<input type="text" id="health" name="insuredPerson.health"
								lay-verify="required" placeholder="请输入健康状态" autocomplete="off"
								class="layui-input">
						</div>
					</div>

					
					<div class="layui-form-item">
						<label class="layui-form-label">个人身份</label>
						<div class="layui-input-inline">
							<input type="text" id="identity" name="insuredPerson.identity"
								lay-verify="required" placeholder="请输入个人身份" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">学历</label>
						<div class="layui-input-inline">
							<input type="text" id="education" name="insuredPerson.education"
								lay-verify="required" placeholder="请输入学历" autocomplete="off"
								class="layui-input">
						</div>
					</div>

				
					<div class="layui-form-item">
						<label class="layui-form-label">就业状态</label>
						<div class="layui-input-inline">
							<input type="text" id="employmentStatus"
								name="insuredPerson.employmentStatus" lay-verify="required"
								placeholder="请输入就业状态" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">参加工作时间</label>
						<div class="layui-input-inline">
							<input type="text" id="workBeginTime"
								name="insuredPerson.workBeginTime" lay-verify="required"
								placeholder="请选择有效起始日期" autocomplete="off" class="layui-input">
						</div>
					</div>

					
					<div class="layui-form-item">
						<label class="layui-form-label">行政职务</label>
						<div class="layui-input-inline">
							<input type="text" id="administrativePost"
								name="insuredPerson.administrativePost" lay-verify="required"
								placeholder="请输入行政职务" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">离退休</label>
						<div class="layui-input-inline">
							<input type="text" id="retirementSigns"
								name="insuredPerson.retirementSigns" lay-verify="required"
								placeholder="请输入离退休标志" autocomplete="off" class="layui-input">
						</div>

					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">国家职业资格等级</label>
						<div class="layui-input-inline">
							<input type="text" id="nationCareerGrade"
								name="insuredPerson.nationCareerGrade" lay-verify="required"
								placeholder="请输入国家职业资格等级" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">专业技术职务等级</label>
						<div class="layui-input-inline">
							<input type="text" id="technicalGrade"
								name="insuredPerson.technicalGrade" lay-verify="required"
								placeholder="请输入专业技术职务等级" autocomplete="off" class="layui-input">
						</div>
					</div>


					
					<div class="layui-form-item">
						<label class="layui-form-label">农民工</label>
						<div class="layui-input-inline" id="farmerSigns">
							<input type="radio" name="insuredPerson.farmerSigns" value="0"
								title="否"></input><input type="radio"
								name="insuredPerson.farmerSigns" value="1" title="是" ></input>
						</div>
						<label class="layui-form-label">精准扶贫</label>
						<div class="layui-input-inline" id="poorSigns">
							<input type="radio" name="insuredPerson.poorSigns" value="0"
								title="否" ><input type="radio"
								name="insuredPerson.poorSigns" value="1" title="是" >
						</div>
					</div>

					
					<div class="layui-form-item">
						<label class="layui-form-label">生存状态</label>
						<div class="layui-input-inline">
							<input type="text" id="survivalStatus"
								name="insuredPerson.survivalStatus" lay-verify="required"
								placeholder="请输入生存状态" autocomplete="off" class="layui-input">
						</div>
					</div>





					<fieldset class="layui-elem-field layui-field-title"
						style="margin-top: 20px;">
						<legend>社保详情</legend>
					</fieldset>

					
					<div class="layui-form-item">
						<label class="layui-form-label">参保日期</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceBeginTime"
								name="insuredPerson.insuranceBeginTime" lay-verify="required"
								placeholder="请选择有效出生日期" autocomplete="off" class="layui-input">
						</div>

						<label class="layui-form-label">参保属性</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceAttr"
								name="insuredPerson.insuranceAttr" lay-verify="required"
								placeholder="请输入参保属性" autocomplete="off" class="layui-input">
						</div>
					</div>

					
					<div class="layui-form-item">
						<label class="layui-form-label">险种类型</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceType"
								name="insuredPerson.insuranceType" lay-verify="required"
								placeholder="请输入险种类型" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">统筹区邮政编码</label>
						<div class="layui-input-inline">
							<input type="text" id="cityPostalCode"
								name="insuredPerson.cityPostalCode" lay-verify="required|number"
								placeholder="请输入统筹区邮政编码" autocomplete="off" class="layui-input">
						</div>
					</div>


					
					<div class="layui-form-item">
						<label class="layui-form-label">连续参保年数</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceYear"
								name="insuredPerson.insuranceYear" lay-verify="required|number"
								placeholder="请输入连续参保年数" autocomplete="off" class="layui-input">
						</div>

						<label class="layui-form-label">医保卡状态</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceCardStatus"
								name="insuredPerson.insuranceCardStatus" lay-verify="required"
								placeholder="请输入医保卡状态" autocomplete="off" class="layui-input">
						</div>
					</div>

					
					<div class="layui-form-item">
						<label class="layui-form-label">状态变更原因</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceCardChangeReason"
								name="insuredPerson.insuranceCardChangeReason"
								lay-verify="required" placeholder="请输入状态变更原因" autocomplete="off"
								class="layui-input">
						</div>
						<label class="layui-form-label">社会化管理形式</label>
						<div class="layui-input-inline">
							<input type="text" id="socialForm"
								name="insuredPerson.socialForm" lay-verify="required"
								placeholder="请输入社会化管理形式" autocomplete="off" class="layui-input">
						</div>
					</div>


					
					<div class="layui-form-item">
						<label class="layui-form-label">参保单位名称</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceUnitsName"
								name="insuredPerson.insuranceUnitsName" lay-verify="required"
								placeholder="请输入参保单位名称" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">参保单位编码</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceUnitsCode"
								name="insuredPerson.insuranceUnitsCode" lay-verify="required"
								placeholder="请输入机构级别" autocomplete="off" class="layui-input">
						</div>
					</div>

				
					<div class="layui-form-item">

						<label class="layui-form-label">参保单位联系人</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceUnitsInChareName"
								name="insuredPerson.insuranceUnitsInChareName" lay-verify="required"
								placeholder="请输入参保单位联系人" autocomplete="off" class="layui-input">
						</div>
						<label class="layui-form-label">参保单位联电话</label>
						<div class="layui-input-inline">
							<input type="text" id="insuranceUnitsInCharePhone"
								name="insuredPerson.insuranceUnitsInCharePhone" lay-verify="required|phone"
								placeholder="请输入机构类型" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-inline">
							<input type="text" id="comments" name="insuredPerson.comments"
								lay-verify="required" placeholder="请输入备注" autocomplete="off"
								class="layui-input">
						</div>
					</div>




					<!-- 	<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">限抢救用药</label>
								<div class="layui-input-inline" id="rescueFlag">
									<input type="radio" name="drugRule.rescueFlag" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
										name="drugRule.rescueFlag" value="0" title="否&nbsp;&nbsp;&nbsp;" checked>
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label">就医类型</label>
								<div class="layui-input-inline" id="diagType">
									<input type="radio" name="drugRule.diagType" value="1"
										title="门诊" checked> <input type="radio"
										name="drugRule.diagType" value="0" title="住院">
								</div>
							</div>
						</div>
						 -->


					<div class="layui-form-item layui-hide">
						<input type="button" lay-submit
							lay-filter="layuiadmin-btn-useradmin"
							id="layuiadmin-btn-useradmin" value="确认">
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/insuredPerson/insuredPersonInfo.js"></script>
</body>
</html>
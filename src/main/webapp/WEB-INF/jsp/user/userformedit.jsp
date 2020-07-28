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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
    <script
            src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.core.min.js"></script>
    <link
            href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.min.css"
            rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/webuploader/webuploader.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/webuploader/webuploader.min.js"></script>
<style>
.trg {
	width: 0;
	height: 0;
	border-left: 3px solid transparent;
	border-right: 3px solid transparent;
	border-top: 6px solid black;;
	position: absolute;
	left: 180px;
	top: 15px;
}

.org-select {
	cursor: default;
	z-index: -1;
	width: 200px;
}

.ztree {
	display: none;
	position: absolute;
	right: 0px;
	top: 37px;
	border: 1px solid #4aa5ff;
	background-color: #f2f2f2;
	width: 168px;
	z-index: 999999;
	overflow: auto;
}
</style>
<title>用户管理新增/修改页面</title>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="user.id" id="id"> <input
			type="hidden" name="user.password" id="password"> <input
			type="hidden" name="user.status" id="status"> <input
			type="hidden" name="user.loginTime" id="loginTime"> <input
			type="hidden" name="user.roleCode" id="rolecodeHidden"
			lay-verify="article_desc"> <input type="hidden"
			name="user.unitId" id="unitIdHidden" lay-verify="article_desc">
		<!-- <input type="hidden"  name="roleCodeTemp"  id="rolecodeTempHidden"  > -->

		<div id="unit" class="layui-form-item" style="padding-left:40px" >
			<label class="layui-form-label">组织架构</label>

			<div class="layui-input-inline">
				<div class="selectDevType">
					<!-- 模拟select点击框 以及option的text值显示-->
					<input id="unitName" class="role-select layui-input"
						onClick="showTree(this)" readonly>
					<!-- 模拟select右侧倒三角 -->
					<i class="trg" style="position: absolute;margin-left: -18px"></i>
				</div>
				<input id="unitId" type="hidden" name="user.unitId" />
				<!-- 存储 模拟select的value值 -->

				<!-- zTree树状图 相对定位在其下方 -->
				<div class="ztree">
					<ul id="treeDemo1"></ul>
				</div>
			</div>


		</div>


		<div id="role" class="layui-form-item" style="padding-left:40px">
			<label class="layui-form-label">角色</label>
			<div class="layui-input-inline" style="position: relative">
				<!-- 模拟select点击框 以及option的text值显示-->
				<div class="selectDevType">
					<input id="roleName" class="role-select layui-input"
						onClick="showTree(this)" readonly>
					<!-- 模拟select右侧倒三角 -->
					<i class="trg" style="position: absolute;margin-left: -18px"></i>
				</div>
                <input id="roleId" type="hidden" lay-verify="required" name="user.roleId"/>
				<!-- 存储 模拟select的value值 -->

				<!-- zTree树状图 相对定位在其下方 -->
				<div class="ztree">
					<ul id="treeDemo"></ul>
				</div>
			</div>
		</div>

		<div class="layui-form-item" style="padding-left:40px">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-inline">
				<input type="text" id="loginName" name="user.loginName"
					lay-verify="required" placeholder="请输入用户名" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item" style="padding-left:40px">
			<label class="layui-form-label">真实姓名</label>
			<div class="layui-input-inline">
				<input type="text" id="name" name="user.name" lay-verify="required"
					placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
			</div>
		</div>

    <div class="layui-form-item" style="padding-left:40px">
        <label class="layui-form-label" >手机号</label>
        <div class="layui-input-inline">
            <input type="text" id="phone" name="user.phone" lay-verify="phone" placeholder="请输入手机号码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" style="padding-left:40px">
        <label class="layui-form-label" >邮箱</label>
        <div class="layui-input-inline">
            <input type="text" id="email" name="user.email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

		<div class="layui-upload" style="padding-left:40px">
			<label class="layui-form-label">头像</label> <input type="hidden"
				id="photo" name="user.photo">
			<div class="layui-upload-list" style="display: inline-block">
				<div id="fileListImg" style="margin-top: 0px;" onclick="checkA()"
					class="uploader-list-img"></div>
				<span id="imgspan"> </span>
				<!-- <img class="layui-upload-img" id="img" name="user.photo" src=""
					style="width: 90px; hight: 90px;"> -->
				<p id="demoText"></p>
			</div>

			<button class="layui-btn" id="test1" type="button">修改头像</button>
		</div>

		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-user-front-submit"
				id="LAY-user-front-submit" value="确认">
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/user/userform.js"></script>
	<script type="text/javascript">
		layui
				.use(
						'upload',
						function() {
							var $ = layui.jquery, upload = layui.upload;
							//普通图片上传
							var uploadInst = upload
									.render({
										elem : '#test1',
										url : $WEB_ROOT_PATH
												+ '/dhccApi/uploadfile/saveheadimg',
										before : function(obj) {
											
											//预读本地文件示例，不支持ie8
											obj.preview(function(index, file,
													result) {
												$("#fileListImg").remove();
												$("#imgspan>").remove();
												/* $('#img').attr('src', result); */ //图片链接（base64）
												var innerHTML = "";
												innerHTML += "<div class='layui-upload-list' style='display: inline-block'>";
												innerHTML += "<img class='layui-upload-img' id='img' style='width: 90px; hight: 90px;'>";
												innerHTML += "<p id='demoText'></p>";
												innerHTML += "</div>";
												$("#imgspan").append(
														innerHTML);
												$('#img').attr('src',result);
												return;
											});
										},
										done : function(res) {
											//如果上传失败
											if (res.code > 0) {
												return layer.msg('上传失败');
											}
											console.log(res.fileUrl);
											$("#photo").val(res.fileUrl);
											layer.msg('上传成功');
											//上传成功
										},
										error : function() {
											//演示失败状态，并实现重传
											var demoText = $('#demoText');
											demoText
													.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
											demoText.find('.demo-reload').on(
													'click', function() {
														uploadInst.upload();
													});
										},
										choose : function(obj) {
											//将每次选择的文件追加到文件队列
											var files = obj.pushFile();
											//预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
											obj.preview(function(index, file,
													result) {
												/* var photoName = $("#photo").val(file.name); */
												console.log(index);
												console.log(file);
												console.log(file.name);//得到文件索引
												/* console.log(photoName); */ //得到文件对象
												//console.log(result); //得到文件base64编码，比如图片
												//obj.resetFile(index, file, 'aaa.jpg'); //重命名文件名，layui 2.3.0 开始新增
											});
										}
									})
						})
		var id;
		var photo;
		function child(obj) {
			var user = JSON.parse(obj);
			id = user["id"];
			photo = user["photo"];
			for ( var index in user) {
				$("#" + index).val(user[index]);
			}
			// 图片回填
			if (photo != null && photo != "") {
				var imgs = photo.split(",");
				for (var i = 0; i < imgs.length; i++) {
					console.log(imgs);
					var filetype = imgs[i].split(".")[1];
					if (filetype == "jpg" || filetype == "png") {
						addFile("../storeFile/headimg/" + imgs[i], imgs[i]);
					} else {
						addFile("../storeFile/headimg/" + imgs[i], imgs[i]);
					}
				}
			}
		}
	</script>
</body>
</html>
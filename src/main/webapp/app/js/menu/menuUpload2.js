/**
 * 图片上传数量及其大小等控制
 * 点击开始上传按钮(test9)，执行上传
 * 
 */
var success=0;
var fail=0;
var imgurls="";
var imgurl1="";
var imgurl2="";

$(function (){
	var imgsName="";
	layui.use(['upload','layer'],function() {
		var upload = layui.upload;
		var layer=layui.layer;

		upload.render({
			elem: '#test1',
			url: $WEB_ROOT_PATH + '/dhccApi/uploadfile/upload',
			multiple: true,
			auto:false,
//			上传的单个图片大小
			size:10240,
//			最多上传的数量
			number:2,
//			MultipartFile file 对应，layui默认就是file,要改动则相应改动
			field:'file',
			bindAction: '#test9',
			before: function(obj) {
				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result) {
					$('#demo2').append('<img src="' + result 
						+ '" alt="' + file.name 
						+'"height="92px" width="92px" class="layui-upload-img uploadImgPreView">')
				});
			},
			done: function(res, index, upload) {
				//每个图片上传结束的回调，成功的话，就把新图片的名字保存起来，作为数据提交
				if(res.inFlag=="1"){
					fail++;
				}else{
					success++;
					if(success==3){
						success=success-2;			    
						imgurls="";
					    imgurl1="";
					    imgurl2="";
						}
					if(success==1){
					imgurl1=imgurl1+"/"+res.data.src;
					console.log(imgurl1)
					}else{
					imgurl2=imgurl2+"/"+res.data.src;
					console.log(imgurl2)
					};
					imgurls=imgurls+""+res.data.src+",";
					$("#imgUrls").val(imgurls);
				}
			},
			allDone:function(obj){
			    layer.msg("总共要上传图片总数为："+(fail+success)+"\n"
			    			+"其中上传成功图片数为："+success+"\n"
			    			+"其中上传失败图片数为："+fail
			    		 )
			    success=0;
			    fail=0;
			}
		});

	});
	
	//清空预览图片
	cleanImgsPreview();
	//保存商品
	goodsSave();
});

/**
 * 清空预览的图片及其对应的成功失败数
 * 原因：如果已经存在预览的图片的话，再次点击上选择图片时，预览图片会不断累加
 * 表面上做上传成功的个数清0，实际后台已经上传成功保存了的，只是没有使用，以最终商品添加的提交的为准
 */
function cleanImgsPreview(){
	$("#cleanImgs").click(function(){
		success=0;
		fail=0;
		$('#demo2').html("");
		$('#imgUrls').val("");
	});
}


/**
 * 保存商品
 */
function goodsSave(){
	$('#layuiadmin-btn-useradmin1').click(function(){
		var menuName=$("#menuName").val();
		var menuCode=$("#menuCode").val();
		var menuUrl=$("#menuUrl").val();
		var parentLeaf=$("#parentLeaf").val();
		var parentId=$("#parentId").val();
		var id=$("#id").val();
		var owner=$("#owner").val();
/*		console.log(id)
		console.log(menuName)
		console.log(menuCode)
		console.log(menuUrl)
		console.log(parentLeaf)
		console.log(parentId)
		console.log(imgurl1)
		console.log(imgurl2)
		console.log(owner)*/
        $.ajax({
            type: "POST",
            async: false,
            url: $WEB_ROOT_PATH + '/dhccApi/menu/menu/update',
            data: {
            	"menu.id": id,
                "menu.parentId": parentId,
                "menu.menuName": menuName,
                "menu.menuCode": menuCode,
                "menu.parentLeaf": parentLeaf,
                "menu.menuUrl": menuUrl,
                "menu.owner": owner,
                "menu.onclickBef":imgurl1,
                "menu.onclickAft":imgurl2
            },
            success: function (data) {
                var inFlag = data.inFlag;
                window.parent.layeredit(inFlag);
/*            	 if(inFlag==1){
            		 layer.msg('' + "修改成功" + '', {
                         icon: 5,
                         time: 1000
                     })
              	  }else if(inFlag==2){
              		layer.msg('菜单名称已存在，请修改');
              	  }else{
              		layer.msg('编辑失败!');
              	  }*/
            }
        });
	});
}

//上传附件
/*var aa = function(){
	new_element=document.createElement("script");
	new_element.setAttribute("type","text/javascript");
	new_element.setAttribute("src","/menu.js");
	document.body.appendchild(new_element);
}

loadJS('menuInfo.js',function(){
	loadJS('menu.js',function(){
		alert('$');
	});
});*/
var parentId ;
var level;
var $ = jQuery,
$list = $('#fileList'),
$listImg = $('#fileListImg'),
// 优化retina, 在retina下这个值是2
ratio = window.devicePixelRatio || 1,
// 缩略图大小
thumbnailWidth = 90 * ratio,
thumbnailHeight = 90 * ratio,
// Web Uploader实例
uploader;
var fileUrl = "";
var originalFilename="";
$(function () {
    uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: false,
        disableGlobalDnd: true,
        // swf文件路径
        swf: $WEB_ROOT_PATH + "/js/webuploader/Uploader.swf",
        // 文件接收服务端。
        server: $WEB_ROOT_PATH + '/dhccApi/uploadfile/save',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filepicker',
        allowMagnify: false,
        resize: false,
        //fileSingleSizeLimit:102400,  //限制单个上传文件大小
        formData: {
            "filePath": "jihe/"
            //是否压缩 1压缩生成略缩图  0不需要压缩生成略缩图
        },
        fileNumLimit: 10,
        /*accept: {
        	title: 'Images',
        	extensions: 'gif,jpg,jpeg,bmp,png',
        	mimeTypes: 'image/*'
        },*/
    });

    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
    	if(file.type=="image/jpeg"||file.type=="image/png"||file.type=="image/gif"||file.type=="image/jpg"||file.type=="image/bmp"){
    		//图片
    		var $li = $(
    				'<div id="' + file.id + '" class="hover_img file-item thumbnail">' +
    				'<div class="del_img"></div>' +
                    '<img>' +
                    '<div style="width: 95px;line-height: 18px;font-size: 15px;white-space:normal;word-break:break-all;word-wrap:break-word; display: inline-block;height: 16px;line-height: 16px">' + file.name + '</div>' +
                '</div>'
    	            ),
    	            $img = $li.find('img');
    		$listImg.append( $li );
    		uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
    	}else{
    		//其他文件
    		$list.append('<div class="webuploader-item">' +
    	            '<div  id="' + file.id + '"  class="hover_file thumbnail" style="overflow: auto;line-height: 25px;">' +
    	            '<h4 style="font-size: 15px;display: inline-block;height: 16px;line-height: 16px">' + file.name + '</h4>' +
    	            '<div class="del_file" style="display: inline-block;"></div>' +
    	            '</div>' +
    	            '</div>');
    	}
        /*$(".hover_file").on("mouseover", function () {
            $(this).children(".del_file").css('visibility', 'visible');
        })
        //隐藏删除按钮
        $(".hover_file").on("mouseout", function () {
            $(this).children(".del_file").css('visibility', 'hidden');
        });
        
        $(".hover_img").on("mouseover", function () {
            $(this).children(".del_img").css('visibility', 'visible');
        })
        //隐藏删除按钮
        $(".hover_img").on("mouseout", function () {
            $(this).children(".del_img").css('visibility', 'hidden');
        });*/
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on('uploadSuccess', function (file, response) {
        $('#' + file.id).addClass('upload-state-done');
        //后台保存的文件名
        if(fileUrl==""){
            fileUrl=response.fileUrl;
        }else{
            fileUrl=fileUrl+","+response.fileUrl;
        }
        //原始文件名字
        if(originalFilename==""){
        	originalFilename=response.originalFilename;
        }else{
        	originalFilename=originalFilename+","+response.originalFilename;
        }
    });

    // 文件上传失败，显示上传出错。
    uploader.on('uploadError', function (file) {
        var $li = $('#' + file.id),
            $error = $li.find('div.error');

        // 避免重复创建
        if (!$error.length) {
            $error = $('<div class="error"></div>').appendTo($li);
        }
        $error.text('上传失败');
    });

    /**
     * 验证文件格式以及文件大小
     */
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            $CommonUI.alert("选择文件格式出错");
        }
        else if (type == "F_EXCEED_SIZE") {
            $CommonUI.alert("文件大小不能超过10M");
        }
        else if (type == "Q_EXCEED_NUM_LIMIT") {
            $CommonUI.alert("只允许上传10个文件");
        } else {
            $CommonUI.alert("上传出错！请检查后重新上传！错误代码" + type);
        }
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').remove();
    });

    //所有文件上传完毕
    uploader.on("uploadFinished", function () {
        //提交到数据库
        saveDb();
    });

    //执行删除方法
    $list.on("click", ".del_file", function () {
        var Id = $(this).parent().attr("id");
        uploader.removeFile(uploader.getFile(Id, true));
        $(this).parent().remove();
    });
    
    //图片执行删除方法
    $listImg.on("click", ".del_img", function () {
        var Id = $(this).parent().attr("id");
        uploader.removeFile(uploader.getFile(Id, true));
        $(this).parent().remove();
    });
    
});
function saveDb(){
	var url = $WEB_ROOT_PATH + "/dhccApi/menu/menu/save";
	var onclickBef ="/images/main/";
	dataAll.field["menu.fileUrl"]=fileUrl;
	dataAll.field["menu.originalFilename"]=originalFilename;
	dataAll.field["menu.id"]=$("#id").val();
	dataAll.field["menu.parentId"]=$("#parentId").val();
	dataAll.field["menu.parentLeaf"]=$("#parentLeaf").val();
	dataAll.field["menu.menuName"]=$("#menuName").val();
	dataAll.field["menu.menuCode"]=$("#menuCode").val();
	dataAll.field["menu.menuUrl"]=$("#menuUrl").val();
	dataAll.field["menu.onclickBef"]=onclickBef+fileUrl;
    $.post(url, dataAll.field, function (result) {
    	console.info(dataAll.field);
    	console.info(result);
    	 var inFlag= result.inFlag; 
   	  if(inFlag==1){
   		  layer.msg('提交成功!');
   	  }else if(inFlag==2){
   		  layer.msg('已存在!');
   	  }else{
   		layer.msg('提交失败!');
   	  }
       
    });
}

//回显图片时执行增加到picList
function addFile(url,filename){
	//url="http://127.0.0.1:8080/piccbid/storeFile/3f3fb961gc1860773bef6&690.jpg";
	console.info("addFile:"+url);
	console.info("filename:"+filename);
	//需要编辑的图片列表
	var picList = [url];
	$.each(picList, function(index,item){
	  getFileObject(item, function (fileObject) {
	    var wuFile = new WebUploader.Lib.File(WebUploader.guid('rt_'),fileObject);
	    var file = new WebUploader.File(wuFile);
	    uploader.addFiles(file);
	  },filename);
	});
}

//获取文件blob
var getFileBlob = function (url, cb) {
	  var xhr = new XMLHttpRequest();
	  xhr.open("GET", url);
	  xhr.responseType = "blob";
	  xhr.addEventListener('load', function() {
	  	  cb(xhr.response);
	  });
	  xhr.send();
};
//js处理将blob转换成file
var blobToFile = function (blob, name) {
     blob.lastModifiedDate = new Date();
     blob.name = name;
     return blob;
};

//获取文件对象
var getFileObject = function(filePathOrUrl, cb,filename) {
  	 getFileBlob(filePathOrUrl, function (blob) {
  	 	cb(blobToFile(blob, filename));
  });
};
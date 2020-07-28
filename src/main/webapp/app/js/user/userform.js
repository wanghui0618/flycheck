var rolecodeClick = "";
var fileUrl = "";
var originalFilename="";
// 初始化
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' // 静态资源所在路径
}).extend({
    index: 'lib/index' // 主入口模块
}).use(['index', 'table', 'form'], function () {
    var $ = layui.$
        , form = layui.form
        , table = layui.table

    form.verify({
        article_desc: function (value) {
            $("#rolecodeHidden").val(rolecodeClick);
        }
    });

    $.ajax({
        async: false, // 是否异步
        cache: false, // 是否使用缓存
        type: 'POST', // 请求方式：post
        url: $WEB_ROOT_PATH + '/dhccApi/role/role/listTree',// 请求的路径
        success: function (data) {
            orgList = JSON.parse(data); // 把后台封装好的标准的Json格式赋给treeNodes
            $.fn.zTree.init($("#treeDemo"), setting, orgList);
        }
    });
    $.ajax({
        async: false, // 是否异步
        cache: false, // 是否使用缓存
        type: 'POST', // 请求方式：post
        url: $WEB_ROOT_PATH + '/dhccApi/unit/unit/listAllTree',// 请求的路径
        success: function (data) {
            orgList = JSON.parse(data); // 把后台封装好的标准的Json格式赋给treeNodes
            $.fn.zTree.init($("#treeDemo1"), setting2, orgList);
        }
    });
    
});

var id = "";
var orgList = [];
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId"
        }
    },
    // 回调
    callback: {
        onClick: zTreeOnClick
    },
    view: {
        fontCss: {fontSize: "14px"}
    }
};

// 节点点击事件
function zTreeOnClick(event, treeId, treeNode) {
    $('#roleName').val(treeNode.name);
    $('#roleId').val(treeNode.roleId);
    hideTree();
    roleCode = treeNode.roleCode;
    // console.log(roleCode);
    rolecodeClick = roleCode;
};
var setting2 = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId"
        }
    },
    // 回调
    callback: {
        onClick: zTreeOnClick1
    },
    view: {
        fontCss: {fontSize: "14px"}
    }
};

// 节点点击事件
function zTreeOnClick1(event, treeId, treeNode) {
    console.log(event);
    $('#unitName').val(treeNode.name);
    $('#unitId').val(treeNode.unitId)
    hideTree();
    unitId = treeNode.unitId;
    // console.log(roleCode);
    rolecodeClick = unitId;
};


function hideTree() {
    $('#' + id + ' .ztree').css('display', 'none');
    id="";
    $("body").unbind("mousedown", onBodyDownByActionType);
    return false;
}

function showTree(obj) {
    var ev = window.event;
    newTreeID = ev.target.parentNode.parentNode.parentNode.id;
    if(id !=="" && id!==newTreeID){
        $('#' + id + ' .ztree').css('display', 'none');
        id="";
        return
    }else{
        id = newTreeID;
    }
    if ($('#' + id + ' .ztree').css('display') === 'none') {
        $('#' + id + ' .ztree').css('display', 'block');
    } else {
        $('#' + id + ' .ztree').css('display', 'none');
        id="";
    }
    $("body").bind("click", onBodyDownByActionType);
}

// 区域外点击事件
function onBodyDownByActionType(event) {
    var idTree = "";
    // 取树id
    $("#" + id + " .ztree")[0].childNodes.forEach((item) => {
        if (item.nodeType === 1) {
            idTree = item.id;
            return
        }
    });
    if (event.target.id.indexOf(idTree) === -1) {
        if (event.target.parentNode.className !== 'selectDevType') {
            hideTree();
        }
    }
}
// 信息回填
function child(obj) {
    var user = JSON.parse(obj);
    console.log(user)
    $("#id").val(user["id"]);
    for (var index in user) {
        $("#" + index).val(user[index]);
    }
    // $("#rolecodeHidden").val(user["roleCode"]);

    $("#loginName").attr("readonly", "readonly");
}
/*
 * $(document).ready(function() { var photoPath="/storeFile/headimg/";
 * $('#img').attr('src',
 * $WEB_ROOT_PATH+photoPath+"4028e6bd6c83dabb016c83dca4320000.jpg"); });
 */
var arrPicList =new Array();
// 缩略图大小
/*ratio = window.devicePixelRatio || 2,
thumbnailWidth = 50 * ratio,
thumbnailHeight = 50 * ratio,*/
$(function () {
    uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: false,
        disableGlobalDnd: true,
        // swf文件路径
        swf: $WEB_ROOT_PATH + "/js/webuploader/Uploader.swf",
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        allowMagnify: false,
        resize: false,
        quality: 900,
        crop: false,
        duplicate:true,
        // fileSingleSizeLimit:102400, //限制单个上传文件大小
        fileNumLimit: 10,
    });

    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
    	console.log(file)
    	// console.log(file.id);
    	if(file.type=="image/jpeg"||file.type=="image/png"||file.type=="image/gif"||file.type=="image/jpg"||file.type=="image/bmp"){
    		// 图片
    		var $li = $(
    				'<div id="' + file.id + '" class="hover_img file-item thumbnail">' +
    				'<div class="del_img"></div>' +
                    '<img mysrc="" class="prev" id="' + 45678 + '" style="width: 90px; hight: 90px;">' +
                '</div>'
    	            ),
    	            $img = $li.find('img');
    		$("#fileListImg").append( $li );
    		uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
               $img.attr( 'src', src );
            });
    	}
    });
})
function addFile(url,filename){
	// url="http://127.0.0.1:8080/piccbid/storeFile/3f3fb961gc1860773bef6&690.jpg";
	console.info("addFile:"+url);
	console.info("filename:"+filename);
	// 需要编辑的图片列表
	var picList = [url];
	$.each(picList, function(index,item){
	  getFileObject(item, function (fileObject) {
	    var wuFile = new WebUploader.Lib.File(WebUploader.guid('rt_'),fileObject);
	    var file = new WebUploader.File(wuFile);
	    arrPicList.push(url);
	    uploader.addFiles(file);
	  },filename);
	});
}

// 获取文件blob
var getFileBlob = function (url, cb) {
	  var xhr = new XMLHttpRequest();
	  xhr.open("GET", url);
	  xhr.responseType = "blob";
	  xhr.addEventListener('load', function() {
	  	  cb(xhr.response);
	  });
	  xhr.send();
};

// js处理将blob转换成file
var blobToFile = function (blob, name) {
     blob.lastModifiedDate = new Date();
     blob.name = name;
     return blob;
};

// 获取文件对象
var getFileObject = function(filePathOrUrl, cb,filename) {
  	 getFileBlob(filePathOrUrl, function (blob) {
  	 	cb(blobToFile(blob, filename));
  });
};
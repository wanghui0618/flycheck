/**
 * 功能描述：扩展jQuery插件
 * @param $
 */

+(function($) {
	/**
	* 扩展resize方法(可支持window和非window对象)
	* callback:回调函数(长宽发生变化时触发)
	*/
	$.fn.resize = function(callback) {
		$(this).each(function() {
			var resizeDom = this;
			resizeDom.callback = callback;

			// 确定绑定的对象是否是window
			if(this == window) {
				window.onresize = function() {
					resizeDom.callback();
				};
			} else {
				$(this).divResize(callback);
			}
		});
	};

	/**
	 * 封装非Window对象的resize方法
	 * callback:回调函数(长宽发生变化时触发)
	 */
	$.fn.divResize = function(callback) {
		var cycleTime = 250; // 定义监控大小变化的时间
		if(callback == undefined || callback == null) {
			return;
		}
		if(!(typeof callback == "function")) {
			return;
		}

		$(this).each(function() {
			var resizeDom = this;
			resizeDom.data = {};
			resizeDom.data.width = $(resizeDom).css("width"); // 存放当前宽度
			resizeDom.data.height = $(resizeDom).css("height"); // 存放当前高度
			resizeDom.callback = callback;
			// 定义监控函数
			var resizeMonitor = function() {

				// 判断高度和宽度是否发生变化
				if($(resizeDom).css("width") != resizeDom.data.width || $(resizeDom).css("height") != resizeDom.data.height) {
					resizeDom.data.width = $(resizeDom).css("width");
					resizeDom.data.height = $(resizeDom).css("height");
					resizeDom.callback();
				}
				var callFunc = arguments.callee; // 获取本身函数
				setTimeout(function() {
					callFunc();
				}, cycleTime);
			};
			resizeMonitor();
		});
	};

}(jQuery));
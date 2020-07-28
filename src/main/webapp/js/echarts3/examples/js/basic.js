/***************************************************************************
 * 基本示例，params为空
 ***************************************************************************/
// 指定图表的配置项和数据
var option0 = {
	title : {
		text : 'echarts基本示例'
	},
	tooltip : {
		trigger : 'axis',
		axisPointer : { // 坐标轴指示器，坐标轴触发有效
			type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
		}
	},
	legend : {
		data : [ '销量' ]
	},
	xAxis : {
		data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
	},
	yAxis : {},
	series : [ {
		name : '销量',
		type : 'bar',
		data : [ 5, 20, 36, 10, 10, 20 ]
	} ]
};
$CommonUI.echarts3('#basic', option0);

/***************************************************************************
 * 自定义主题，动态数据
 ***************************************************************************/
// 动态数据
function splitData(rawData) {
    var categoryData = [];
    var values = [];
    var volumns = [];
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i]);
        volumns.push(rawData[i][4]);
    }
    return {
        categoryData: categoryData,
        values: values,
        volumns: volumns
    };
}

function calculateMA(dayCount, data) {
    var result = [];
    for (var i = 0, len = data.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += data.values[i - j][1];
        }
        result.push(+(sum / dayCount).toFixed(3));
    }
    return result;
}

$CommonUI.echarts3('#theme', null, {
	url : $WEB_ROOT_PATH + '/js/echarts3/examples/data/stock-DJI.json',
	theme : 'shine',
	onLoadSuccess : function(rawData) {
		var data = splitData(rawData);
		
		this.setOption(option = {
		        backgroundColor: '#eee',
		        animation: false,
		        legend: {
		            bottom: 10,
		            left: 'center',
		            data: ['Dow-Jones index', 'MA5', 'MA10', 'MA20', 'MA30']
		        },
		        tooltip: {
		            trigger: 'axis',
		            axisPointer: {
		                type: 'line'
		            }
		        },
		        toolbox: {
		            feature: {
		                dataZoom: {
		                    yAxisIndex: false
		                },
		                brush: {
		                    type: ['lineX', 'clear']
		                }
		            }
		        },
		        brush: {
		            xAxisIndex: 'all',
		            brushLink: 'all',
		            outOfBrush: {
		                colorAlpha: 0.1
		            }
		        },
		        grid: [
		            {
		                left: '10%',
		                right: '8%',
		                height: '50%'
		            },
		            {
		                left: '10%',
		                right: '8%',
		                top: '63%',
		                height: '16%'
		            }
		        ],
		        xAxis: [
		            {
		                type: 'category',
		                data: data.categoryData,
		                scale: true,
		                boundaryGap : false,
		                axisLine: {onZero: false},
		                splitLine: {show: false},
		                splitNumber: 20,
		                min: 'dataMin',
		                max: 'dataMax'
		            },
		            {
		                type: 'category',
		                gridIndex: 1,
		                data: data.categoryData,
		                scale: true,
		                boundaryGap : false,
		                axisLine: {onZero: false},
		                axisTick: {show: false},
		                splitLine: {show: false},
		                axisLabel: {show: false},
		                splitNumber: 20,
		                min: 'dataMin',
		                max: 'dataMax'
		            }
		        ],
		        yAxis: [
		            {
		                scale: true,
		                splitArea: {
		                    show: true
		                }
		            },
		            {
		                scale: true,
		                gridIndex: 1,
		                splitNumber: 2,
		                axisLabel: {show: false},
		                axisLine: {show: false},
		                axisTick: {show: false},
		                splitLine: {show: false}
		            }
		        ],
		        dataZoom: [
		            {
		                type: 'inside',
		                xAxisIndex: [0, 1],
		                start: 95,
		                end: 100
		            },
		            {
		                show: true,
		                xAxisIndex: [0, 1],
		                type: 'slider',
		                top: '85%',
		                start: 98,
		                end: 100
		            }
		        ],
		        series: [
		            {
		                name: 'Dow-Jones index',
		                type: 'candlestick',
		                data: data.values,
		                itemStyle: {
		                    normal: {
		                        borderColor: null,
		                        borderColor0: null
		                    }
		                },
		                tooltip: {
		                    formatter: function (param) {
		                        var param = param[0];
		                        return [
		                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
		                            'Open: ' + param.data[0] + '<br/>',
		                            'Close: ' + param.data[1] + '<br/>',
		                            'Lowest: ' + param.data[2] + '<br/>',
		                            'Highest: ' + param.data[3] + '<br/>'
		                        ].join('');
		                    }
		                }
		            },
		            {
		                name: 'MA5',
		                type: 'line',
		                data: calculateMA(5, data),
		                smooth: true,
		                lineStyle: {
		                    normal: {opacity: 0.5}
		                }
		            },
		            {
		                name: 'MA10',
		                type: 'line',
		                data: calculateMA(10, data),
		                smooth: true,
		                lineStyle: {
		                    normal: {opacity: 0.5}
		                }
		            },
		            {
		                name: 'MA20',
		                type: 'line',
		                data: calculateMA(20, data),
		                smooth: true,
		                lineStyle: {
		                    normal: {opacity: 0.5}
		                }
		            },
		            {
		                name: 'MA30',
		                type: 'line',
		                data: calculateMA(30, data),
		                smooth: true,
		                lineStyle: {
		                    normal: {opacity: 0.5}
		                }
		            },
		            {
		                name: 'Volumn',
		                type: 'bar',
		                xAxisIndex: 1,
		                yAxisIndex: 1,
		                data: data.volumns
		            }
		        ]
		    }, true);
	}
});

/***************************************************************************
 * 异步加载，无loading动画
 ***************************************************************************/
var option2 = {
	title : {
		text : '异步加载',
		subtext : '无loading动画',
		textStyle : {
			color : '#333',
			fontStyle : 'normal',
			fontWeight : 'border',
			fontFamily : 'sans-serif',
			fontSize : 15,
		}
	},
	legend : {
		data : [ 'HTMLElement', 'WebGL', 'SVG', 'CSS', 'Other' ]
	},
	series : [ {
		type : 'graph',
		layout : 'force',
		animation : false,
		label : {
			normal : {
				position : 'right',
				formatter : '{b}'
			}
		},
		draggable : true,
		data : [],
		categories : [],
		force : {
			initLayout: 'circular',
			gravity: 1,
			repulsion: 25,
			edgeLength : 5
		},
		edges : []
	} ]
};
$CommonUI.echarts3('#async', option2, {
	url : $WEB_ROOT_PATH + '/js/echarts3/examples/data/webkit-dep.json',
	onLoadSuccess : function(data) {
		// 填入数据，并动态更新图表
		this.setOption({
			series : [ {
				// 根据类型对应到相应的系列
				type : 'graph',
				data : data.nodes.map(function(node, idx) {
					node.id = idx;
					return node;
				}),
				categories : data.categories,
				edges : data.links
			} ]
		});
	}
});

// 数据更新
var timeTicket = setInterval(function() {
	$CommonUI.echarts3('#async', 'update', {
		onLoadSuccess : function(data) {
			var option = {
				series : [ {
					type : 'graph',
					animation : true,
					data : data.nodes.map(function(node, idx) {
						node.id = idx;
						return node;
					}),
					categories : data.categories,
					force : {
						gravity: Math.random(),
						repulsion: Math.random() * 20 + 5,
						edgeLength : Math.random() * 5 + 3
					},
					edges : data.links
				} ]
			};
			// 填入数据，并动态更新图表
			this.setOption(option);
		}
	});
}, Math.random() * 20000 + 5000);

/***************************************************************************
 * 异步加载，带loading动画
 ***************************************************************************/
var option3 = {
	title : {
		text : '异步加载',
		subtext : '带loading动画',
		left : 'right'
	},
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'cross'
		}
	},
	xAxis : {
		type : 'value',
		splitLine : {
			show : false
		},
		scale : true,
		splitNumber : 5,
		max : 'dataMax',
		axisLabel : {
			formatter : function(val) {
				return val + 's';
			}
		}
	},
	yAxis : {
		type : 'value',
		min : 0,
		max : 360,
		interval : 60,
		name : 'Hue',
		splitLine : {
			show : false
		}
	},
	series : [ {
		name : 'scatter',
		type : 'scatter',
		symbolSize : [],
		itemStyle : {
			normal : {
				color : []
			}
		},
		data : []
	} ]
};
$CommonUI.echarts3('#async-loading', option3, {
	url : $WEB_ROOT_PATH
			+ '/js/echarts3/examples/data/masterPainterColorChoice.json',
	showLoading : true,
	onLoadSuccess : function(json) {
		var data = json[0].x.map(function(x, idx) {
			return [ +x, +json[0].y[idx] ];
		});
		// 填入数据，并动态更新图表
		this.setOption({
			series : [ {
				// 根据名字对应到相应的系列
				name : 'scatter',
				symbolSize : function(val, param) {
					return json[0].marker.size[param.dataIndex]
							/ json[0].marker.sizeref;
				},
				itemStyle : {
					normal : {
						color : function(param) {
							return json[0].marker.color[param.dataIndex];
						}
					}
				},
				data : data
			} ]
		});
	}
});

/***************************************************************************
 * 地图：JavaScript方式引入
 ***************************************************************************/
$CommonUI.echarts3('#weibo', null, {
	url : $WEB_ROOT_PATH + '/js/echarts3/examples/data/weibo.json',
	map : 'china.js',
	showLoading : true,
	onLoadSuccess : function(weiboData) {
		weiboData = weiboData.map(function(serieData, idx) {
			var px = serieData[0] / 1000;
			var py = serieData[1] / 1000;
			var res = [ [ px, py ] ];

			for ( var i = 2; i < serieData.length; i += 2) {
				var dx = serieData[i] / 1000;
				var dy = serieData[i + 1] / 1000;
				var x = px + dx;
				var y = py + dy;
				res.push([ x.toFixed(2), y.toFixed(2), 1 ]);

				px = x;
				py = y;
			}
			return res;
		});
		this.setOption(option = {
			backgroundColor : '#404a59',
			title : {
				text : '地图：JavaScript引入',
				subtext : '微博签到数据点亮中国',
				sublink : 'http://www.thinkgis.cn/public/sina',
				left : 'center',
				top : 'top',
				textStyle : {
					color : '#fff'
				}
			},
			tooltip : {},
			legend : {
				left : 'left',
				data : [ '强', '中', '弱' ],
				textStyle : {
					color : '#ccc'
				}
			},
			geo : {
				name : '强',
				type : 'scatter',
				map : 'china',
				label : {
					emphasis : {
						show : false
					}
				},
				itemStyle : {
					normal : {
						areaColor : '#323c48',
						borderColor : '#111'
					},
					emphasis : {
						areaColor : '#2a333d'
					}
				}
			},
			series : [ {
				name : '弱',
				type : 'scatter',
				coordinateSystem : 'geo',
				symbolSize : 1,
				large : true,
				itemStyle : {
					normal : {
						shadowBlur : 2,
						shadowColor : 'rgba(37, 140, 249, 0.8)',
						color : 'rgba(37, 140, 249, 0.8)'
					}
				},
				data : weiboData[0]
			}, {
				name : '中',
				type : 'scatter',
				coordinateSystem : 'geo',
				symbolSize : 1,
				large : true,
				itemStyle : {
					normal : {
						shadowBlur : 2,
						shadowColor : 'rgba(14, 241, 242, 0.8)',
						color : 'rgba(14, 241, 242, 0.8)'
					}
				},
				data : weiboData[1]
			}, {
				name : '强',
				type : 'scatter',
				coordinateSystem : 'geo',
				symbolSize : 1,
				large : true,
				itemStyle : {
					normal : {
						shadowBlur : 2,
						shadowColor : 'rgba(255, 255, 255, 0.8)',
						color : 'rgba(255, 255, 255, 0.8)'
					}
				},
				data : weiboData[2]
			} ]
		});
	}
});

/***************************************************************************
 * 地图：JSON方式引入
 ***************************************************************************/
$CommonUI.echarts3('#flights', null, {
	url : $WEB_ROOT_PATH + '/js/echarts3/examples/data/flights.json',
	map : 'world.json',
	showLoading : true,
	onLoadSuccess : function(data) {
		function getAirportCoord(idx) {
			return [ data.airports[idx][3], data.airports[idx][4] ];
		}
		var routes = data.routes
				.map(function(airline) {
					return [ getAirportCoord(airline[1]),
							getAirportCoord(airline[2]) ];
				});
		this.setOption(option = {
			title : {
				text : '地图：JSON引入',
				subtext : '世界航班',
				left : 'center',
				textStyle : {
					color : '#eee'
				}
			},
			backgroundColor : '#003',
			tooltip : {
				formatter : function(param) {
					var route = data.routes[param.dataIndex];
					return data.airports[route[1]][1] + ' > '
							+ data.airports[route[2]][1];
				}
			},
			geo : {
				map : 'world',
				left : 0,
				right : 0,
				silent : true,
				itemStyle : {
					normal : {
						borderColor : '#003',
						color : '#005'
					}
				}
			},
			series : [ {
				type : 'lines',
				coordinateSystem : 'geo',
				data : routes,
				large : true,
				largeThreshold : 100,
				lineStyle : {
					normal : {
						opacity : 0.05,
						width : 0.5,
						curveness : 0.3
					}
				},
				// 设置混合模式为叠加
				blendMode : 'lighter'
			} ]
		});
	}
});
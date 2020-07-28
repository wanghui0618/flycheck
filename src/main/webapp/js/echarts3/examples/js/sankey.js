$CommonUI.echarts3('#main',null,{
   url: $WEB_ROOT_PATH + '/js/echarts3/examples/data/product.json',
   showLoading: true,
   onLoadSuccess: function(data) {
   
    this.setOption(option = {
        title: {
            text: 'Sankey Diagram'
        },
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'

        },
        series: [
            {
                type: 'sankey',
                layout:'none',
                data: data.nodes,
                links: data.links,
                itemStyle: {
                    normal: {
                        borderWidth: 1,
                        borderColor: '#aaa'
                    }
                },
                lineStyle: {
                    normal: {
                        curveness: 0.5
                    }
                }
            }
        ]
    });
   }

});
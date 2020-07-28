package com.dhcc.piccbid.dto.home;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.home.MonitorPolygonalchart;

import java.util.List;

public class MonitorPolygonalchartDto extends BaseAbstractDto {
    private static final long serialVersionUID = 1L;

    private MonitorPolygonalchart monitorPolygonalchart;
    private List<MonitorPolygonalchart> monitorPolygonalcharts;

    public MonitorPolygonalchart getRegionalfunds() {
        return monitorPolygonalchart;
    }

    public void setRegionalfunds(MonitorPolygonalchart regionalfunds) {
        this.monitorPolygonalchart = regionalfunds;
    }

    public List<MonitorPolygonalchart> getRegionalfundss() {
        return monitorPolygonalcharts;
    }

    public void setRegionalfundss(List<MonitorPolygonalchart> regionalfundss) {
        this.monitorPolygonalcharts = monitorPolygonalcharts;
    }
}

package com.dhcc.piccbid.dto.hospitalizationMonitor;

import java.util.List;
import com.dhcc.piccbid.entity.hospitalizationMonitor.HospitalizationMonitor;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-19 18:10:19
 * @version V1.0
 */
public class HospitalizationMonitorDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private HospitalizationMonitor hospitalizationMonitor;
	private List<HospitalizationMonitor> hospitalizationMonitors;

	public HospitalizationMonitor getHospitalizationMonitor() {
		return hospitalizationMonitor;
	}

	public void setHospitalizationMonitor(HospitalizationMonitor hospitalizationMonitor) {
		this.hospitalizationMonitor = hospitalizationMonitor;
	}

	public List<HospitalizationMonitor> getHospitalizationMonitors() {
		return hospitalizationMonitors;
	}

	public void setHospitalizationMonitors(List<HospitalizationMonitor> hospitalizationMonitors) {
		this.hospitalizationMonitors = hospitalizationMonitors;
	}
}

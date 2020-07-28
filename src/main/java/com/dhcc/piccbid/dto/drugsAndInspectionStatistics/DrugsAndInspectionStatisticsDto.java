package com.dhcc.piccbid.dto.drugsAndInspectionStatistics;

import java.util.List;
import com.dhcc.piccbid.entity.drugsAndInspectionStatistics.DrugsAndInspectionStatistics;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-15 16:48:40
 * @version V1.0
 */
public class DrugsAndInspectionStatisticsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private DrugsAndInspectionStatistics drugsAndInspectionStatistics;
	private List<DrugsAndInspectionStatistics> drugsAndInspectionStatisticss;
	private String chooseYear;
	private String chooseMonth;

    public String getChooseYear() {
        return chooseYear;
    }

    public void setChooseYear(String chooseYear) {
        this.chooseYear = chooseYear;
    }

    public String getChooseMonth() {
        return chooseMonth;
    }

    public void setChooseMonth(String chooseMonth) {
        this.chooseMonth = chooseMonth;
    }

    public DrugsAndInspectionStatistics getDrugsAndInspectionStatistics() {
		return drugsAndInspectionStatistics;
	}

	public void setDrugsAndInspectionStatistics(DrugsAndInspectionStatistics drugsAndInspectionStatistics) {
		this.drugsAndInspectionStatistics = drugsAndInspectionStatistics;
	}

	public List<DrugsAndInspectionStatistics> getDrugsAndInspectionStatisticss() {
		return drugsAndInspectionStatisticss;
	}

	public void setDrugsAndInspectionStatisticss(List<DrugsAndInspectionStatistics> drugsAndInspectionStatisticss) {
		this.drugsAndInspectionStatisticss = drugsAndInspectionStatisticss;
	}
}

package com.dhcc.piccbid.service.flyTreatmentFeeCount.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.piccbid.dao.flyTreatmentFeeCount.FlyTreatmentFeeCountJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.flyTreatmentFeeCount.FlyTreatmentFeeCountDao;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.FlyTreatmentFeeCountDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyTreatmentFeeCount;
import com.dhcc.piccbid.service.flyTreatmentFeeCount.FlyTreatmentFeeCountService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zfm
 * @date 2019-10-17 22:02:46
 * @version V1.0
 */
@Service("flyTreatmentFeeCountService")
public class FlyTreatmentFeeCountServiceImpl extends AbstractBaseService<FlyTreatmentFeeCount, String> implements FlyTreatmentFeeCountService {

	private FlyTreatmentFeeCountDao flyTreatmentFeeCountDao;

	@Autowired
    private CommonService commonService;
	@Autowired
    private FlyTreatmentFeeCountJdbc flyTreatmentFeeCountJdbc;
	public FlyTreatmentFeeCountServiceImpl(FlyTreatmentFeeCountDao flyTreatmentFeeCountDao) {
		super(flyTreatmentFeeCountDao);
		this.flyTreatmentFeeCountDao = flyTreatmentFeeCountDao;
	}

    @Override
    public PageModel list(FlyTreatmentFeeCountDto dto) {

        flyTreatmentFeeCountJdbc.search(dto);
        PageModel pageModel = dto.getPageModel();
       // commonService.fillSqlPageModelData(pageModel,FlyTreatmentFeeCount.class,"item");
        return dto.getPageModel();
    }

    @Override
    public PageModel ultrasoundlist(FlyTreatmentFeeCountDto dto) {

        flyTreatmentFeeCountJdbc.ultrasoundlist(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel,FlyTreatmentFeeCount.class, "item");
        return dto.getPageModel();
    }


}

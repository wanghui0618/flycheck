package com.dhcc.piccbid.service.procedure.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dhcc.piccbid.dao.procedure.ProcedureJdbcDao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.procedure.ProcedureDao;
import com.dhcc.piccbid.dto.procedure.ProcedureDto;
import com.dhcc.piccbid.entity.procedure.Procedure;
import com.dhcc.piccbid.service.procedure.ProcedureService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-06 11:48:54
 * @version V1.0
 */
@Service("procedureService")
public class ProcedureServiceImpl extends AbstractBaseService<Procedure, String> implements ProcedureService {

	private ProcedureDao procedureDao;
	@Resource
	private ProcedureJdbcDao procedureJdbcDao;

	public ProcedureServiceImpl(ProcedureDao procedureDao) {
		super(procedureDao);
		this.procedureDao = procedureDao;
	}

	@Override
	public PageModel list(ProcedureDto dto) {
		Specification<Procedure> spec = getListSpecification(dto);
		if (dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		Page<Procedure> page = procedureDao.findAll(spec, dto.getPageModel().getPageable());
		dto.getPageModel().setPage(page);
		return dto.getPageModel();
	}
	
	protected Specification<Procedure> getListSpecification(final ProcedureDto dto){
        return new Specification<Procedure>() {
            @Override
            public Predicate toPredicate(Root<Procedure> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件
   
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
	}

	public PageModel listVo(ProcedureDto dto){
		procedureJdbcDao.listVo(dto);
		return dto.getPageModel();
	}

	public boolean execute(ProcedureDto dto){
		boolean success= procedureJdbcDao.execute(dto);
		return success;
	}

	public PageModel executeAll(ProcedureDto dto){
		procedureJdbcDao.executeAll(dto);
		return dto.getPageModel();
	}

	public boolean flag(){
		boolean flag=procedureJdbcDao.flag();
		return flag;
	}

	public boolean check(ProcedureDto dto){
		if(dto.getProcedure().getFlag()!=null&&dto.getProcedure().getFlag().equals("all")) {
			boolean check = procedureJdbcDao.checkAndLock();
			return check;
		}else {
			boolean check = procedureJdbcDao.checkAndLockOne(dto.getProcedure());
			return check;
		}
	}

	public boolean stop(){
		boolean flag=procedureJdbcDao.stop();
		return flag;
	}


}

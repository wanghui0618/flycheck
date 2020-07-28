package com.dhcc.piccbid.blh.dictdiag;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.dhcc.piccbid.service.dictdiag.impl.DictdiagServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.dictdiag.DictdiagDto;
import com.dhcc.piccbid.entity.dictdiag.Dictdiag;
import com.dhcc.piccbid.service.dictdiag.DictdiagService;

import java.util.List;
import java.util.UUID;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class DictdiagBlh extends BaseAbstractBlh<DictdiagDto> {

	private static Log logger = LogFactory.getLog(DictdiagBlh.class);

	@Resource
	private DictdiagService DictdiagService;

	public DictdiagBlh() {
		logger.debug("DictdiagBlh Init");
	}

	/**
	 *
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);
		if(dto.getDictdiag()==null||"".equals(dto.getDictdiag().getDiagName())&&"".equals(dto.getDictdiag().getDiagCode())) {
			dto.setDictdiag(new Dictdiag());
			dto.getDictdiag().setParentLeaf("1");
			//调用service查询方法
			PageModel pageModel=DictdiagService.list(dto);
			//不要删除这行代码，调用set是为以后 service层要加缓存
			dto.setPageModel(pageModel);
		}else {
			//调用service查询方法
			PageModel pageModel=DictdiagService.list(dto);
			//不要删除这行代码，调用set是为以后 service层要加缓存
			dto.setPageModel(pageModel);
		}

	}

	public void listSecondVo(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);
		String parentId = WebContextHolder.getContext().getRequest().getParameter("parentId");
		if (dto.getDictdiag()==null) {
			dto.setDictdiag(new Dictdiag());
		}
		dto.getDictdiag().setParentId(parentId);;
		//调用service查询方法
	    PageModel pageModel=DictdiagService.listSecondVo(dto);
			//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);


	}

	/**
	 *
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		DictdiagService.save(dto.getDictdiag());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}


	public void saveFirst(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		dto.getDictdiag().setParentLeaf("1");
		dto.getDictdiag().setParentIndex(UUID.randomUUID().toString().replaceAll("-",""));
		DictdiagService.save(dto.getDictdiag());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	public void saveSecond(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		dto.getDictdiag().setParentLeaf("2");
		DictdiagService.save(dto.getDictdiag());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 *
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);

		//调用对应的service删除方法
		DictdiagService.delete(dto.getDictdiag().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 *
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *
	 */
	public void findById(BaseAbstractDto basedto) {
		DictdiagDto dto = super.getExactlyDto(basedto);

		//调用对应的service查询某个实体的方法
		Dictdiag entity = DictdiagService.findOne(dto.getDictdiag().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setDictdiag(entity);
	}

/*	public void listVo(DictdiagDto dto) {		
		String parentDiagCode = WebContextHolder.getContext().getRequest().getParameter("id");
		dto.setDictdiag(new Dictdiag());
		dto.getDictdiag().setParentDiagCode(parentDiagCode);
		PageModel pageModel=DictdiagService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}*/
public String ztreeDiag(BaseAbstractDto basedto) {
	DictdiagDto dto = super.getExactlyDto(basedto);
	//调用service查询方法
	String  tressString=DictdiagService.ztreeDiag(dto);
	return tressString;
}

	public String updateDiag(BaseAbstractDto baseAbstractDto) {
		DictdiagDto dictdiagDto = super.getExactlyDto(baseAbstractDto);
		String diagName = dictdiagDto.getDictdiag().getDiagName();
		//根据diagName去查询，为了校验新增diagName是否重复
		List<Dictdiag> dictdiagList = DictdiagService.dictdiagList(dictdiagDto);

		System.out.println(JSON.toJSONString(dictdiagList));
		for (Dictdiag dictdiag : dictdiagList) {
			if (diagName.equals(dictdiag.getDiagName())) {
				return "exist";
			}
		}
		if (null == diagName || "".equals(dictdiagDto.getDictdiag().getDiagName())) {
			return "false";
		} else {
			DictdiagService.updateDiag(dictdiagDto);
			return "success";
		}
	}
	public String saveDiag(BaseAbstractDto baseAbstractDto){
		DictdiagDto dictdiagDto=super.getExactlyDto(baseAbstractDto);
		//新增的时候获取疾病名
		String diagName=dictdiagDto.getDictdiag().getDiagName();
        //根据diagName去查询，为了校验新增diagName是否重复
		List<Dictdiag> dictdiagList=DictdiagService.dictdiagList(dictdiagDto);
	//	System.out.println(JSON.toJSON(dictdiagList));
		for (Dictdiag dictdiag:dictdiagList){
			if (diagName.equals(dictdiag.getDiagName())){
				return "exist";
			}
		}
		//新增的时候，疾病名为必传，否则返回false
		if (null==diagName||"".equals(dictdiagDto.getDictdiag().getDiagName())){
			return "false";
		}else {
			//dictdiagDto.getDictdiag();
			DictdiagService.save(dictdiagDto.getDictdiag());
			return "success";
		}
	}

	public void deleteDiag(BaseAbstractDto baseAbstractDto){
		DictdiagDto dictdiagDto=super.getExactlyDto(baseAbstractDto);
		dictdiagDto.getDictdiag();
		DictdiagService.delete(dictdiagDto.getDictdiag());
		dictdiagDto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
}

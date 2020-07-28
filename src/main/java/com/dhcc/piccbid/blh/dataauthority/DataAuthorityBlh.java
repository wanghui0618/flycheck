package com.dhcc.piccbid.blh.dataauthority;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.dataauthority.DataAuthorityDto;
import com.dhcc.piccbid.entity.dataauthority.DataAuthority;
import com.dhcc.piccbid.service.dataauthority.DataAuthorityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class DataAuthorityBlh extends BaseAbstractBlh<DataAuthorityDto> {

    private static Log logger = LogFactory.getLog(DataAuthorityBlh.class);

    @Resource
    private DataAuthorityService dataAuthorityService;

    public DataAuthorityBlh() {
        logger.debug("DataAuthorityBlh Init");
    }

    /**
     * 进入某个列表的入口方法（分页查询方法�?
     *
     * @param basedto
     */
    public void list(BaseAbstractDto basedto) {
        DataAuthorityDto dto = super.getExactlyDto(basedto);
        //调用service查询方法
       dataAuthorityService.list(dto);

    }

    /**
     * 保存对象，若无ID则新建对象，若有ID则更新对�?
     *
     * @param basedto
     */
    public void save(BaseAbstractDto basedto) {
        DataAuthorityDto dto = super.getExactlyDto(basedto);
        //调用对应的service保存方法
        dataAuthorityService.save(dto.getDataAuthority());
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param basedto
     */
    public void delete(BaseAbstractDto basedto) {
        DataAuthorityDto dto = super.getExactlyDto(basedto);
        //调用对应的service删除方法
        dataAuthorityService.delete(dto.getDataAuthority().getId());
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }

    /**
     * 根据ID查询实体的方�?
     *
     * @param: basedto
     */
    public void findById(BaseAbstractDto basedto) {
        DataAuthorityDto dto = super.getExactlyDto(basedto);

        //调用对应的service查询某个实体的方�?
        DataAuthority entity = dataAuthorityService.findOne(dto.getDataAuthority().getId());
        //不要删除这行代码，调用set是为以后 service层要加缓�?
        dto.setDataAuthority(entity);
    }

    public String listAllTree(BaseAbstractDto baseAbstractDto) {
        DataAuthorityDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = dataAuthorityService.listAllTree(dto);
        return jsonString;
    }
    public void listTreeTable(BaseAbstractDto baseAbstractDto) {
        DataAuthorityDto dto = super.getExactlyDto(baseAbstractDto);
        dataAuthorityService.listTreeTable(dto);
    }
    public void findCity(BaseAbstractDto baseAbstractDto){
        DataAuthorityDto dto = super.getExactlyDto(baseAbstractDto);
       dataAuthorityService.findCity(dto);
    }

    public void findOrg(BaseAbstractDto baseAbstractDto){
        DataAuthorityDto dto = super.getExactlyDto(baseAbstractDto);
       dataAuthorityService.findOrg(dto);
    }

}

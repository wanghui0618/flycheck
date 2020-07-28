package com.dhcc.piccbid.service.dataauthority;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.dataauthority.DataAuthorityDto;
import com.dhcc.piccbid.entity.dataauthority.DataAuthority;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @version V1.0
 * @date 2019-06-25 09:36:33
 */
public interface DataAuthorityService extends BaseService<DataAuthority, String> {

    void list(DataAuthorityDto dto);

    String listAllTree(DataAuthorityDto dto);

    void listTreeTable(DataAuthorityDto dto);

    void findCity(DataAuthorityDto dto);

    void findOrg(DataAuthorityDto dto);

}

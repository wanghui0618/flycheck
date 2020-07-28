package com.dhcc.piccbid.service.button;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.button.ButtonDto;
import com.dhcc.piccbid.entity.button.Button;

public interface ButtonService extends BaseService<Button, String> {

    PageModel list(ButtonDto dto);

    PageModel search(ButtonDto dto);

    String listAllTree(ButtonDto dto);

    PageModel listTreeTable(ButtonDto dto);

    int isExist(ButtonDto dto);

    String getButtonAuthoByUserId(ButtonDto dto);



}

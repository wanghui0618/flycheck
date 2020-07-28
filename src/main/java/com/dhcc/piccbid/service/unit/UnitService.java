package com.dhcc.piccbid.service.unit;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.unit.UnitDto;
import com.dhcc.piccbid.dto.user.UserDto;
import com.dhcc.piccbid.entity.unit.Unit;

import java.util.List;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @version V1.0
 * @date 2019-06-17 13:02:58
 */
public interface UnitService extends BaseService<Unit, String> {

    PageModel list(UnitDto dto);

    String unitAllList(UnitDto dto);

    void synInfo(UnitDto dto);

    List<Unit> units(UnitDto dto);

    PageModel search(UnitDto dto);

    PageModel listTreeTable(UnitDto dto);

    PageModel listTreeTableForDelete(UnitDto dto);

    void deleteArray(String[] arrays);

    String dataAuthoList(UnitDto dto);

    String roleDataAuthoList(UnitDto dto);

    List<String> getUserDataAhthority();

    List<String> getUserDataAhthorityForApp(String userId);

    StringBuilder appendDataAuhoritySqlOld(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list);

    StringBuilder appendDataAuhoritySql(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list);

    int isExist(UnitDto dto);

    void updateUserDataAuthorityAll(String name);

    String roleAreaAuthoList(UnitDto dto);

    List<Unit> areas(UnitDto dto);

//    void insertDataAutho();


}

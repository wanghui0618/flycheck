package com.dhcc.piccbid.service.flyAvgDay;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
import com.dhcc.piccbid.entity.flyAvgDay.FlyAvgDay;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wy
 * @date 2019-10-15 14:35:33
 * @version V1.0
 */
public interface FlyAvgDayService extends BaseService<FlyAvgDay, String> {

	PageModel list(FlyAvgDayDto dto);

	/** 
	* 方法名:          flyAvgDay
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年10月15日 下午2:42:48
	*/
	PageModel flyAvgDay(FlyAvgDayDto dto);

}

package com.dhcc.piccbid.blh.button;

import com.alibaba.fastjson.JSON;
import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.button.ButtonDto;
import com.dhcc.piccbid.entity.button.Button;
import com.dhcc.piccbid.service.button.ButtonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * 鎻忚堪: TODO<br/>
 * 鍏徃: 涓滃崕杞欢鑲′唤鍏徃<br/>
 * 鐗堟潈: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class ButtonBlh extends BaseAbstractBlh<ButtonDto> {

    private static Log logger = LogFactory.getLog(ButtonBlh.class);

    @Resource
    private ButtonService buttonService;

    public ButtonBlh() {
        logger.debug("ButtonBlh Init");
    }


    public void list(BaseAbstractDto basedto) {
        PageModel pageModel=new PageModel();
        ButtonDto dto = super.getExactlyDto(basedto);
     /*   if (dto.getButton()==null) {
            pageModel = buttonService.list(dto);
        }else {
            Button button=dto.getButton();
            if (button.getButtonName()!=null||button.getButtonCode()!=null) {
                pageModel =buttonService.search(dto);
            }
        }*/
        pageModel =buttonService.search(dto);
        dto.setPageModel(pageModel);
    }


    public void save(BaseAbstractDto basedto) {
        ButtonDto dto = super.getExactlyDto(basedto);
            //判断新增的按钮代码是否已存在
            int count = buttonService.isExist(dto);
            if (count > 0) {
                dto.setFlag("3");
            } else {
                buttonService.save(dto.getButton());
                dto.setFlag("1");
            }

    }


    public void delete(BaseAbstractDto basedto) {
        ButtonDto dto = super.getExactlyDto(basedto);

        buttonService.delete(dto.getButton().getId());
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }


    public void findById(BaseAbstractDto basedto) {
        ButtonDto dto = super.getExactlyDto(basedto);


        Button entity = buttonService.findOne(dto.getButton().getId());

        dto.setButton(entity);
    }


    public String listAllTree(BaseAbstractDto baseAbstractDto) {
        ButtonDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = buttonService.listAllTree(dto);
        return jsonString;
    }

    public void listTreeTable(BaseAbstractDto baseAbstractDto) {
        ButtonDto dto = super.getExactlyDto(baseAbstractDto);
        buttonService.listTreeTable(dto);
    }

    public String getButtonAuthoByUserId(BaseAbstractDto baseAbstractDto) {
        ButtonDto dto = super.getExactlyDto(baseAbstractDto);
       String resultString= buttonService.getButtonAuthoByUserId(dto);
       //System.out.println(resultString);
        return resultString;
    }


}

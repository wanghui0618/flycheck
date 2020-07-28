package com.dhcc.piccbid.dto.button;

import java.util.List;
import com.dhcc.piccbid.entity.button.Button;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 *
 * ����: TODO<br/>
 * ��˾: ��������ɷݹ�˾<br/>
 * ��Ȩ: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-07-10 09:17:54
 * @version V1.0
 */
public class ButtonDto extends BaseAbstractDto {

    private static final long serialVersionUID = 1L;

    private Button button;
    private List<Button> buttons;
    //1表示成功，2表示失败，3表示存在
    private String  flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}

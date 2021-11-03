package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.ActionDataPOJO;

public class GetActionListVO {
    private Integer code;
    private String msg;
    private ActionDataPOJO[] action;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ActionDataPOJO[] getAction() {
        return action;
    }

    public void setAction(ActionDataPOJO[] action) {
        this.action = action;
    }
}

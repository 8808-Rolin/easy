package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.PersonActionPOJO;

public class GetActionOverviewVO {
    private Integer code;
    private String msg;
    private PersonActionPOJO[] action;

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

    public PersonActionPOJO[] getAction() {
        return action;
    }

    public void setAction(PersonActionPOJO[] action) {
        this.action = action;
    }
}

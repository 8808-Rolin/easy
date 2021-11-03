package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.GetAssUserPOJO;

public class GetAssMembersVO {
    private Integer code;
    private String msg;
    private GetAssUserPOJO[] user;

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

    public GetAssUserPOJO[] getUser() {
        return user;
    }

    public void setUser(GetAssUserPOJO[] user) {
        this.user = user;
    }
}

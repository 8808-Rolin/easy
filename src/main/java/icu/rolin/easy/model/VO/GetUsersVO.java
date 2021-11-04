package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.GetUserPOJO;

public class GetUsersVO {
    private Integer code;
    private String msg;
    private GetUserPOJO[] user;

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

    public GetUserPOJO[] getUser() {
        return user;
    }

    public void setUser(GetUserPOJO[] user) {
        this.user = user;
    }
}

package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.UserPOJO;

public class UserRespVO {
    private Integer code;
    private String msg;
    private UserPOJO user;

    public UserRespVO(Integer code, String msg, UserPOJO user) {
        this.code = code;
        this.msg = msg;
        this.user = user;
    }

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

    public UserPOJO getUser() {
        return user;
    }

    public void setUser(UserPOJO user) {
        this.user = user;
    }

}

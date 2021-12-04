package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.POJO.UserSimplePOJO;

public class ActionMemberVO {
    private Integer code;
    private String msg;
    private User[] action_member;

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


    public User[] getAction_member() {
        return action_member;
    }

    public void setAction_member(User[] action_member) {
        this.action_member = action_member;
    }
}

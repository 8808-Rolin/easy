package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.DiscussPOJO;

public class GetDiscussVO {
    private Integer code;
    private String msg;
    private DiscussPOJO[] discuss;

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

    public DiscussPOJO[] getDiscuss() {
        return discuss;
    }

    public void setDiscuss(DiscussPOJO[] discuss) {
        this.discuss = discuss;
    }
}

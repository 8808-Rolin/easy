package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.JoinApplyPOJO;

public class GetJoinApplyVO {
    private Integer code;
    private String msg;
    private JoinApplyPOJO[] apply;

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

    public JoinApplyPOJO[] getApply() {
        return apply;
    }

    public void setApply(JoinApplyPOJO[] apply) {
        this.apply = apply;
    }
}

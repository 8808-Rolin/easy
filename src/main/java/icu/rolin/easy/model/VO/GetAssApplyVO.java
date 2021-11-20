package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.AssApplyPOJO;

public class GetAssApplyVO {
    private Integer code;
    private String msg;
    private AssApplyPOJO[] apply;

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

    public AssApplyPOJO[] getApply() {
        return apply;
    }

    public void setApply(AssApplyPOJO[] apply) {
        this.apply = apply;
    }
}

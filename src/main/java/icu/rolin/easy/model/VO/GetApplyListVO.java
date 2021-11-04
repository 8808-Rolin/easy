package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.ApplyListPOJO;

public class GetApplyListVO {
    private Integer code;
    private String msg;
    private ApplyListPOJO[] list;

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

    public ApplyListPOJO[] getList() {
        return list;
    }

    public void setList(ApplyListPOJO[] list) {
        this.list = list;
    }
}

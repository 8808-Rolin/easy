package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.ShowDataAssPOJO;

public class ShowDataVO {
    private Integer code;
    private String msg;
    private ShowDataAssPOJO[] ass;

    public ShowDataVO(Integer code, String msg, ShowDataAssPOJO[] ass) {
        this.code = code;
        this.msg = msg;
        this.ass = ass;
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

    public ShowDataAssPOJO[] getAss() {
        return ass;
    }

    public void setAss(ShowDataAssPOJO[] ass) {
        this.ass = ass;
    }
}

package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.AssDetailListPOJO;

public class AssDetailListVO {
    private Integer code;
    private AssDetailListPOJO[] ass;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AssDetailListPOJO[] getAss() {
        return ass;
    }

    public void setAss(AssDetailListPOJO[] ass) {
        this.ass = ass;
    }
}

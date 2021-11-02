package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.AssOverviewPOJO;

public class AssListVO {
    private Integer code;
    private AssOverviewPOJO[] ass;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AssOverviewPOJO[] getAss() {
        return ass;
    }

    public void setAss(AssOverviewPOJO[] ass) {
        this.ass = ass;
    }
}

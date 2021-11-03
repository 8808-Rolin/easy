package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.AssInfoPOJO;
import icu.rolin.easy.model.POJO.ShowInfoPOJO;

public class AssShowInfoVO {
    private Integer code;
    private ShowInfoPOJO showInfo;
    private AssInfoPOJO assInfo;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ShowInfoPOJO getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(ShowInfoPOJO showInfo) {
        this.showInfo = showInfo;
    }

    public AssInfoPOJO getAssInfo() {
        return assInfo;
    }

    public void setAssInfo(AssInfoPOJO assInfo) {
        this.assInfo = assInfo;
    }
}

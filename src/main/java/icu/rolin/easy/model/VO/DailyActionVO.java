package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.DailyViewPOJO;

public class DailyActionVO {
    private Integer code;
    private DailyViewPOJO[] daily;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DailyViewPOJO[] getDaily() {
        return daily;
    }

    public void setDaily(DailyViewPOJO[] daily) {
        this.daily = daily;
    }
}

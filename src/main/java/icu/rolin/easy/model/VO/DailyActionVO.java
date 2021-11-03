package icu.rolin.easy.model.VO;

public class DailyActionVO {
    private Integer code;
    private DailyActionVO[] daily;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DailyActionVO[] getDaily() {
        return daily;
    }

    public void setDaily(DailyActionVO[] daily) {
        this.daily = daily;
    }
}

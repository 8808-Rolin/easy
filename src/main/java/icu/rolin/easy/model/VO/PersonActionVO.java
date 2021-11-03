package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.DataViewPOJO;

public class PersonActionVO {
    private Integer code;
    private DataViewPOJO[] data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataViewPOJO[] getData() {
        return data;
    }

    public void setData(DataViewPOJO[] data) {
        this.data = data;
    }
}

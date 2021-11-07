package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.CollegePOJO;

public class CollegeListVO {
    private Integer code;
    private CollegePOJO[] college;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CollegePOJO[] getCollege() {
        return college;
    }

    public void setCollege(CollegePOJO[] college) {
        this.college = college;
    }
}

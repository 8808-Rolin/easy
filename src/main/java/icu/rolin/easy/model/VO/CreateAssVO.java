package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.CreateAssPOJO;

public class CreateAssVO {
    private Integer code;
    private CreateAssPOJO[] create;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CreateAssPOJO[] getCreate() {
        return create;
    }

    public void setCreate(CreateAssPOJO[] create) {
        this.create = create;
    }
}

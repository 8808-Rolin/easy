package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.CreateAssPOJO;

import java.util.ArrayList;

public class CreateAssVO {
    private Integer code;
    private ArrayList<CreateAssPOJO> create;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ArrayList<CreateAssPOJO> getCreate() {
        return create;
    }

    public void setCreate(ArrayList<CreateAssPOJO> create) {
        this.create = create;

    }

}

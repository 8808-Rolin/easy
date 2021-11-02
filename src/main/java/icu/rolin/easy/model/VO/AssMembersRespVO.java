package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.AssMemberPOJO;

public class AssMembersRespVO {
    private Integer code;
    private AssMemberPOJO[] members;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AssMemberPOJO[] getMembers() {
        return members;
    }

    public void setMembers(AssMemberPOJO[] members) {
        this.members = members;
    }
}

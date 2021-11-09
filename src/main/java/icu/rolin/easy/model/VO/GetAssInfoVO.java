package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.GetAssInfoAssPOJO;

public class GetAssInfoVO {
    private Integer code;
    private String msg;
    private Integer permissionCode;
    private GetAssInfoAssPOJO ass;



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(Integer permissionCode) {
        this.permissionCode = permissionCode;
    }

    public GetAssInfoAssPOJO getAss() {
        return ass;
    }

    public void setAss(GetAssInfoAssPOJO ass) {
        this.ass = ass;
    }
}

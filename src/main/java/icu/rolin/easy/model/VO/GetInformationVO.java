package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.ZoneJoinAssPOJO;
import icu.rolin.easy.model.POJO.ZoneUserDataPOJO;

public class GetInformationVO {
    private Integer code;
    private Integer assNum;
    private String notice;
    private ZoneUserDataPOJO userdata;
    private ZoneJoinAssPOJO[] joinass;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAssNum() {
        return assNum;
    }

    public void setAssNum(Integer assNum) {
        this.assNum = assNum;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public ZoneUserDataPOJO getUserdata() {
        return userdata;
    }

    public void setUserdata(ZoneUserDataPOJO userdata) {
        this.userdata = userdata;
    }

    public ZoneJoinAssPOJO[] getJoinass() {
        return joinass;
    }

    public void setJoinass(ZoneJoinAssPOJO[] joinass) {
        this.joinass = joinass;
    }
}

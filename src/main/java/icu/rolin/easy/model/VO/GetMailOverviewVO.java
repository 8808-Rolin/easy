package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.MailOverviewPOJO;

public class GetMailOverviewVO {
    private Integer code;
    private MailOverviewPOJO[] mail;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MailOverviewPOJO[] getMail() {
        return mail;
    }

    public void setMail(MailOverviewPOJO[] mail) {
        this.mail = mail;
    }
}

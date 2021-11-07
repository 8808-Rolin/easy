package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.SimpleNoticePOJO;

public class SimpleNoticeVO {
    private Integer code;
    private SimpleNoticePOJO[] notice;

    public Integer getCode() {
        return code;
    }

    public SimpleNoticeVO(Integer code, SimpleNoticePOJO[] notice) {
        this.code = code;
        this.notice = notice;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SimpleNoticePOJO[] getNotice() {
        return notice;
    }

    public void setNotice(SimpleNoticePOJO[] notice) {
        this.notice = notice;
    }
}

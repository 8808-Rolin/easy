package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.ZonePostPOJO;

public class ZonePostVO {
    private Integer code;
    private ZonePostPOJO[] posts;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ZonePostPOJO[] getPosts() {
        return posts;
    }

    public void setPosts(ZonePostPOJO[] posts) {
        this.posts = posts;
    }
}

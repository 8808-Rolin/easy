package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.PostsPOJO;

public class GetPostListVO {
    private Integer code;
    private PostsPOJO[] posts;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PostsPOJO[] getPosts() {
        return posts;
    }

    public void setPosts(PostsPOJO[] posts) {
        this.posts = posts;
    }

    public GetPostListVO(Integer code, PostsPOJO[] posts) {
        this.code = code;
        this.posts = posts;
    }
}

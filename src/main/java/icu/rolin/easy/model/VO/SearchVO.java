package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.SearchPostPOJO;
import icu.rolin.easy.model.POJO.SearchUserPOJO;

public class SearchVO {
    private Integer code;
    private String msg;
    private SearchPostPOJO[] posts;
    private SearchUserPOJO[] users;

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

    public SearchPostPOJO[] getPosts() {
        return posts;
    }

    public void setPosts(SearchPostPOJO[] posts) {
        this.posts = posts;
    }

    public SearchUserPOJO[] getUsers() {
        return users;
    }

    public void setUsers(SearchUserPOJO[] users) {
        this.users = users;
    }
}

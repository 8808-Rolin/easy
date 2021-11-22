package icu.rolin.easy.model.POJO;

import java.util.Objects;

public class SearchUserPOJO {
    private Integer uid;
    private String username;
    private String intro;
    private Integer numberOfPost;
    private String image;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getNumberOfPost() {
        return numberOfPost;
    }

    public void setNumberOfPost(Integer numberOfPost) {
        this.numberOfPost = numberOfPost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // 重写hashCode方法与equal方法，以便实现使用集合去重
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchUserPOJO that = (SearchUserPOJO) o;
        return uid.equals(that.uid);
    }

    // 只要UID相同即可判定为是同一个对象
    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}

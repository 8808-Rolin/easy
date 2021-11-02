package icu.rolin.easy.model.POJO;

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
}

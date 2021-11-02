package icu.rolin.easy.model.PO;

public class ReleasePostPO {
    private Integer uid;
    private Integer releaseArea;
    private String postType;
    private String postTitle;
    private String content;
    private String tags;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReleaseArea() {
        return releaseArea;
    }

    public void setReleaseArea(Integer releaseArea) {
        this.releaseArea = releaseArea;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

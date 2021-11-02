package icu.rolin.easy.model.POJO;

public class DiscussPOJO {
    private Integer page;
    private DiscussAuthorPOJO author;
    private DiscussContentPOJO content;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public DiscussAuthorPOJO getAuthor() {
        return author;
    }

    public void setAuthor(DiscussAuthorPOJO author) {
        this.author = author;
    }

    public DiscussContentPOJO getContent() {
        return content;
    }

    public void setContent(DiscussContentPOJO content) {
        this.content = content;
    }
}

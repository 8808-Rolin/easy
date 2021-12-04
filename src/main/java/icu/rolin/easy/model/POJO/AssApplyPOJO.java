package icu.rolin.easy.model.POJO;

public class AssApplyPOJO {
    private Integer aaid;
    private String title;
    private Integer content_id;
    private String date;
    private Integer status;

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Integer getAaid() {
        return aaid;
    }

    public void setAaid(Integer aaid) {
        this.aaid = aaid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

package icu.rolin.easy.model.POJO;

public class PersonActionPOJO {
    private Integer actid;
    private String title;
    private String date;
    private String position;
    private Integer isAttend;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getActid() {
        return actid;
    }

    public void setActid(Integer actid) {
        this.actid = actid;
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

    public Integer getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Integer isAttend) {
        this.isAttend = isAttend;
    }
}

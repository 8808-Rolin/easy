package icu.rolin.easy.model.PO;

public class SendMailPO {
    private Integer isSystem;
    private Integer mailType;
    private Integer formuid;
    private Integer touid;
    private String title;
    private String content;

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public Integer getMailType() {
        return mailType;
    }

    public void setMailType(Integer mailType) {
        this.mailType = mailType;
    }

    public Integer getFormuid() {
        return formuid;
    }

    public void setFormuid(Integer formuid) {
        this.formuid = formuid;
    }

    public Integer getTouid() {
        return touid;
    }

    public void setTouid(Integer touid) {
        this.touid = touid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

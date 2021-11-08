package icu.rolin.easy.model.POJO;

public class GetAssInfoAssPOJO {
    private String assName;
    private String assIntro;
    private String assImage;
    private String assOrg;
    private String assHead;

    public GetAssInfoAssPOJO(String assName, String assIntro, String assImage, String assOrg, String assHead) {
        this.assName = assName;
        this.assIntro = assIntro;
        this.assImage = assImage;
        this.assOrg = assOrg;
        this.assHead = assHead;
    }

    public String getAssName() {
        return assName;
    }

    public void setAssName(String assName) {
        this.assName = assName;
    }

    public String getAssIntro() {
        return assIntro;
    }

    public void setAssIntro(String assIntro) {
        this.assIntro = assIntro;
    }

    public String getAssImage() {
        return assImage;
    }

    public void setAssImage(String assImage) {
        this.assImage = assImage;
    }

    public String getAssOrg() {
        return assOrg;
    }

    public void setAssOrg(String assOrg) {
        this.assOrg = assOrg;
    }

    public String getAssHead() {
        return assHead;
    }

    public void setAssHead(String assHead) {
        this.assHead = assHead;
    }
}

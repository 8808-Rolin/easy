package icu.rolin.easy.model.PO;

public class CreateAssPO {
    private String assname;
    private String assintro;
    private String note;
    private String assprofile;
    private Integer uid;
    private String org;

    public CreateAssPO() {
    }

    public String getAssname() {
        return assname;
    }

    public void setAssname(String assname) {
        this.assname = assname;
    }

    public String getAssintro() {
        return assintro;
    }

    public void setAssintro(String assintro) {
        this.assintro = assintro;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAssprofile() {
        return assprofile;
    }

    public void setAssprofile(String assprofile) {
        this.assprofile = assprofile;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public CreateAssPO(String assname, String assintro, String note, String assprofile, Integer uid, String org) {
        this.assname = assname;
        this.assintro = assintro;
        this.note = note;
        this.assprofile = assprofile;
        this.uid = uid;
        this.org = org;
    }

    @Override
    public String toString() {
        return "CreateAssPO{" +
                "assname='" + assname + '\'' +
                ", assintro='" + assintro + '\'' +
                ", note='" + note + '\'' +
                ", assprofile='" + assprofile + '\'' +
                ", uid=" + uid +
                ", org=" + org +
                '}';
    }
}

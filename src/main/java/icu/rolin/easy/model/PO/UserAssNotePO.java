package icu.rolin.easy.model.PO;

public class UserAssNotePO {
    private Integer uid;
    private Integer aid;
    private String note;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "UserAssNotePO{" +
                "uid=" + uid +
                ", aid=" + aid +
                ", note='" + note + '\'' +
                '}';
    }
}

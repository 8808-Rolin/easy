package icu.rolin.easy.model.POJO;

public class AssMemberPOJO {
    private Integer uid;
    private String username;
    private String realname;
    private String studentid;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public AssMemberPOJO(Integer uid, String username, String realname, String studentid) {
        this.uid = uid;
        this.username = username;
        this.realname = realname;
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}

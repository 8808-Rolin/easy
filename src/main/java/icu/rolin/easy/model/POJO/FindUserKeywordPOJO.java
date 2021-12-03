package icu.rolin.easy.model.POJO;

import java.util.Objects;

public class FindUserKeywordPOJO {
    private Integer uid;
    private String name;
    private String student_number;
    private String realname;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindUserKeywordPOJO that = (FindUserKeywordPOJO) o;
        return uid.equals(that.uid);
    }

    public FindUserKeywordPOJO(Integer uid, String name, String student_number, String realname) {
        this.uid = uid;
        this.name = name;
        this.student_number = student_number;
        this.realname = realname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}

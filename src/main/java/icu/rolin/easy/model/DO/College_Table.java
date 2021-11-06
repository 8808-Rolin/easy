package icu.rolin.easy.model.DO;

import java.io.Serializable;
import java.sql.Timestamp;

public class College_Table implements Serializable {
    private Integer id;
    private String college_name;
    private Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}

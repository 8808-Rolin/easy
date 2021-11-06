package icu.rolin.easy.model.DO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Apply_Commond implements Serializable {
    private Integer id;
    private Integer a_id;
    private String title;
    private Integer content_id;
    private Integer is_approved;
    private Timestamp create_time;
    private Timestamp update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Integer getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(Integer is_approved) {
        this.is_approved = is_approved;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}

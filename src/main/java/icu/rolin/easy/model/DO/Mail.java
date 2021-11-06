package icu.rolin.easy.model.DO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Mail implements Serializable {
    private Integer id;
    private Integer from_id;
    private Integer to_id;
    private String title;
    private String content;
    private Integer is_read;
    private Integer is_system;
    private Integer mail_type;
    private Timestamp create_time;
    private Timestamp update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public Integer getTo_id() {
        return to_id;
    }

    public void setTo_id(Integer to_id) {
        this.to_id = to_id;
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

    public Integer getIs_read() {
        return is_read;
    }

    public void setIs_read(Integer is_read) {
        this.is_read = is_read;
    }

    public Integer getIs_system() {
        return is_system;
    }

    public void setIs_system(Integer is_system) {
        this.is_system = is_system;
    }

    public Integer getMail_type() {
        return mail_type;
    }

    public void setMail_type(Integer mail_type) {
        this.mail_type = mail_type;
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

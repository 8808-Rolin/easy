package icu.rolin.easy.model.DO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Apply_Content implements Serializable {
    private Integer id;
    private Integer aa_id;
    private Integer content_id;
    private Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAa_id() {
        return aa_id;
    }

    public void setAa_id(Integer aa_id) {
        this.aa_id = aa_id;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}

package icu.rolin.easy.model.DO;

import java.io.Serializable;
import java.sql.Timestamp;

public class Join_Action implements Serializable {
    private Integer id;
    private Integer act_id;
    private Integer u_id;
    private Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAct_id() {
        return act_id;
    }

    public void setAct_id(Integer act_id) {
        this.act_id = act_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}

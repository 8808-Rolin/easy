package icu.rolin.easy.model.DO;





import java.sql.Timestamp;


public class Action {
    private Integer id;
    private Integer a_id;
    private String title;
    private String content;
    private Timestamp start_time;
    private Timestamp end_time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
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
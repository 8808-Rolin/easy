package icu.rolin.easy.model.POJO;

import java.util.HashMap;
import java.util.Objects;

public class SearchPostPOJO {
    private Integer pid;
    private String title;
    private String content;
    private String aname;
    private Integer aid;
    private String authorName;
    private Integer authorUID;
    private String releaseDate;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAuthorUID() {
        return authorUID;
    }

    public void setAuthorUID(Integer authorUID) {
        this.authorUID = authorUID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    // 重写hashCode方法与equal方法，以便实现使用集合去重
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPostPOJO that = (SearchPostPOJO) o;
        return pid.equals(that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}

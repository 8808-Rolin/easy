package icu.rolin.easy.model.POJO;

public class ShowInfoPOJO {
    private Integer headcount;
    private Integer postcount;
    private Integer actioncount;

    public Integer getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Integer headcount) {
        this.headcount = headcount;
    }

    public Integer getPostcount() {
        return postcount;
    }

    public void setPostcount(Integer postcount) {
        this.postcount = postcount;
    }

    public Integer getActioncount() {
        return actioncount;
    }

    public void setActioncount(Integer actioncount) {
        this.actioncount = actioncount;
    }

    public ShowInfoPOJO(Integer headcount, Integer postcount, Integer actioncount) {
        this.headcount = headcount;
        this.postcount = postcount;
        this.actioncount = actioncount;
    }
}

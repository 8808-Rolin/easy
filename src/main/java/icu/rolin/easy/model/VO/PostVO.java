package icu.rolin.easy.model.VO;

import icu.rolin.easy.model.POJO.MasterPOJO;
import icu.rolin.easy.model.POJO.PostPOJO;

public class PostVO {
    private Integer code;
    private Integer permissionCode;
    private String msg;
    private PostPOJO post;
    private MasterPOJO master;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(Integer permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PostPOJO getPost() {
        return post;
    }

    public void setPost(PostPOJO post) {
        this.post = post;
    }

    public MasterPOJO getMaster() {
        return master;
    }

    public void setMaster(MasterPOJO master) {
        this.master = master;
    }
}

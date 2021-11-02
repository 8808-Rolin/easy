package icu.rolin.easy.model.VO;


/**
 * 接口3.1.1.1的响应对象模型
 */
public class LoginVO {
    private Integer code;
    private String msg;
    private Integer uid;
    private String token;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

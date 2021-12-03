package icu.rolin.easy.model.VO;


/**
 * 简单的VO类，仅包含两个参数：
 * code ：返回代码，可以表示是否完成业务
 * msg ： 当code有意义时，该选项为返回的数据
 * 使用该VO的接口有：
 * 注册 - 3.1.2   忘记密码 - 3.1.3
 */
public class SimpleVO {
    private Integer code;
    private Object msg;

    public SimpleVO() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public SimpleVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}



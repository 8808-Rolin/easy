package icu.rolin.easy.model.PO;

import com.alibaba.fastjson.JSON;

/**
 * loginPO:接口3.1.1.1的参数对象
 * PO:Param Object
 */
public class LoginPO {
    private Integer loginType;
    private String account;
    private String password;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

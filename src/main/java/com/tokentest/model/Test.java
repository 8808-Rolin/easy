package com.tokentest.model;


public class Test {

    private Integer loginType;
    private String account;
    private String password;

    public Test(Integer loginType,String account, String password) {
        this.account = account;
        this.password = password;
        this.loginType = loginType;
    }

    public Test() {

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

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "Test{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", loginType=" + loginType +
                '}';
    }
}

package com.tokentest.model;

public class ResponseBase{

    private Integer status;
    private String tip;
    private String time;
    private Object data;

    public ResponseBase(Integer status, String tip, String time, Object data) {
        this.status = status;
        this.tip = tip;
        this.time = time;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "status=" + status +
                ", tip='" + tip + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}

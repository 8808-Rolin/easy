package icu.rolin.easy.model.VO;


import com.alibaba.fastjson.JSON;
import icu.rolin.easy.utils.common;

/**
 * 通用返回对象的外壳，用来生成响应信息体
 * 包含如下构造方法：
 *  一个无参构造方法
 *  一个默认状态构造方法
 *  一个自定义构造方法
 *  以及一个静态方法工厂构造器
 *  在实例化时时间戳就已经生成，请在返回数据给前端时调用构造方法
 *  或者在发送数据时，调用update()方法更新时间戳
 */
public class ResponseVO {
    private int status;
    private String message;
    private String timestamp;
    private Object data;

    public ResponseVO(){}
    public ResponseVO(Object data){
        this.status = 200;
        this.message = "";
        this.timestamp = common.getTimestamp();
        this.data = data;
    }
    public ResponseVO(String tip, Object data){
        this.status = 200;
        this.message = tip;
        this.timestamp = common.getTimestamp();
        this.data = data;
    }
    public ResponseVO(int status, Object data){
        this.status = status;
        this.message = "";
        this.timestamp = common.getTimestamp();
        this.data = data;
    }
    public ResponseVO(int status, String tip, Object data){
        this.status = status;
        this.message = tip;
        this.timestamp = common.getTimestamp();
        this.data = data;
    }

    /**
     * 更新当前对象的时间戳，并返回更新后的对象
     * @return 更新时间戳后的对象
     */
    public ResponseVO update(){
        this.timestamp = common.getTimestamp();
        return this;
    }


    /**
     * @return 对象的JSON字符串形式
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

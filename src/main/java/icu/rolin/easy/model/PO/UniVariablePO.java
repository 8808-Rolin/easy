package icu.rolin.easy.model.PO;

import com.alibaba.fastjson.JSON;

public class UniVariablePO {
    private String studentID;
    private String phone;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

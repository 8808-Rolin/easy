package com.tokentest.model;

import java.sql.Date;
import java.sql.Timestamp;

public class User {

    private Integer id; // uid
    private String userName;
    private String realName;
    private String student_number; // studentID
    private Integer college_id; // cid
    private String password;
    private String email;
    private String phone;
    private Integer sex;
    private Date birth;
    private Integer is_open_zone;
    private Integer level; // 用户的权限，0为普通用户，1社团学生，2为学校管理员
    private String user_avatar; // 用户头像
    private Integer post_number;
    private String notice;
    private String intro;
    private Timestamp create_time;
    private Timestamp update_time;

    public User(Integer id, String userName, String realName, String student_number, Integer college_id, String password, String email, String phone, Integer sex, Date birth, Integer is_open_zone, Integer level, String user_avatar, Integer post_number, String notice, String intro, Timestamp create_time, Timestamp update_time) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.student_number = student_number;
        this.college_id = college_id;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.birth = birth;
        this.is_open_zone = is_open_zone;
        this.level = level;
        this.user_avatar = user_avatar;
        this.post_number = post_number;
        this.notice = notice;
        this.intro = intro;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public Integer getCollege_id() {
        return college_id;
    }

    public void setCollege_id(Integer college_id) {
        this.college_id = college_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getIs_open_zone() {
        return is_open_zone;
    }

    public void setIs_open_zone(Integer is_open_zone) {
        this.is_open_zone = is_open_zone;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public Integer getPost_number() {
        return post_number;
    }

    public void setPost_number(Integer post_number) {
        this.post_number = post_number;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", student_number='" + student_number + '\'' +
                ", college_id=" + college_id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", birth='" + birth + '\'' +
                ", is_open_zone=" + is_open_zone +
                ", level=" + level +
                ", user_avatar='" + user_avatar + '\'' +
                ", post_number=" + post_number +
                ", notice='" + notice + '\'' +
                ", intro='" + intro + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

}


package com.tokentest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tokentest.model.ResponseBase;
import com.tokentest.model.Test;
import com.tokentest.model.User;
import com.tokentest.service.ResponseService;
import com.tokentest.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.tokentest.utils.TransformCurrentTimeUtil.returnCurrentTime;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ResponseService responseService;

    @GetMapping("/login")
    @ResponseBody
    public JSONObject userLogin(Integer loginType, String account, String password){
        JSONObject data = new JSONObject();
        Test test = new Test(loginType,account,password);

//        if (logintype == 0){
//
//        }else if (logintype == 1){
//
//        }else {
//
//        }
        String token = TokenUtil.sign(loginType,password);
        System.out.println("用户获取token时间为："+returnCurrentTime());
        System.out.println(test);
        data.put("responseBody", responseService.setResultSuccess(test));
        data.put("token",token);

        return data;
    }

    @GetMapping("/interceptorTest")
    @ResponseBody
    public JSONObject interceptorTest(){
        JSONObject data = new JSONObject();
        data.put("Test","拦截测试可能成功了....");

        return data;
    }

    @GetMapping("/register")
    @ResponseBody
    public JSONObject userRegister(User user){
        JSONObject data = new JSONObject();

        user.setLevel(0);
        user.setPost_number(0);

        //....调用userService

        data.put("responeseBody",responseService.setResultSuccess());
        return data;
    }

    @GetMapping("/forget-password")
    @ResponseBody
    public JSONObject userForgetPassword(String studentID, String phone, String email, String password){
        JSONObject data = new JSONObject();

        // 调用userService.changePassword(studentId,phone,email,password),添加判定



        data.put("responseBody",responseService.setResultSuccess());
        return data;
    }

}

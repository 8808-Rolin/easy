package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.ForgetPasswordPO;
import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.model.PO.RegisterPO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping(value = "/login")
    public ResponseVO login(LoginPO loginPO){

        Integer key = userService.varifyLogin(loginPO);// 此处使用key作为验证返回码，区分service的code
        if (key == 1) {
            return new ResponseVO(new LoginPO());
        } else if (key == 0) {
            logger.error("用户登录...找不到该用户");
            return new ResponseVO(808, "未找到该用户！请前往注册", new SimpleVO(1, "用户登录失败，找不到用户"));
        } else {
            logger.error("用户登录...缺失登录码");
            return new ResponseVO(1000, "坏了！连登陆码都没有你登个P啊！");
        }

    }

    @PostMapping(value = "/register")
    public ResponseVO register(RegisterPO registerPO){

        Integer key = userService.userRegister(registerPO);
        if (key == 1){
            return new ResponseVO(new SimpleVO(0,"注册成功"));
        }else {
            logger.error("用户注册...插入数据失败");
            return new ResponseVO(500,"注册都能注歪来，让他早日ramake！",new SimpleVO(999,"注册失败"));
        }

    }

    @PostMapping(value = "/forget-password")
    public ResponseVO forget_password(ForgetPasswordPO fp){

        Integer key = userService.userForgetPassword(fp);
        if (key == 1){
            return new ResponseVO(new SimpleVO(0,"修改密码成功"));
        }else {
            logger.error("用户忘记密码...修改新密码错误");
            return new ResponseVO(500,"你自己的邮箱学号电话都能输错是吧？早日重开吧，别来了",new SimpleVO(999,"找回密码失败"));
        }

    }

}

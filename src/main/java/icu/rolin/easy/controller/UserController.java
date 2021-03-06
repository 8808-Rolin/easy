package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.ForgetPasswordPO;
import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.model.PO.RegisterPO;
import icu.rolin.easy.model.VO.LoginVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.service.*;
import icu.rolin.easy.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;


    @PostMapping(value = "/login")
    public ResponseVO login(LoginPO loginPO){
        boolean status = ss.verifyLogin(loginPO);// 此处使用key作为验证返回码，区分service的code
        if (status) {
            int uid = ss.getUserID(loginPO);
            String token = UtilsService.generateToken(loginPO,uid);
            LoginVO lvo  = new LoginVO();
            lvo.setCode(0);
            lvo.setMsg("用户登陆成功");
            lvo.setUid(uid);
            lvo.setToken(token);
            return new ResponseVO(lvo);
        }else {
            return new ResponseVO(new SimpleVO(1, "用户登陆失败,请检查你的账号密码"));
        }
    }

    @PostMapping(value = "/register")
    public ResponseVO register(RegisterPO registerPO){

        boolean status = is.userRegister(registerPO);
        if (status){
            return new ResponseVO(new SimpleVO(0,"注册成功"));
        }else {
            return new ResponseVO(new SimpleVO(1,"注册失败"));
        }

    }


    @PostMapping(value = "/forget-password")
    public ResponseVO forget_password(ForgetPasswordPO fp){

        boolean key = us.userForgetPassword(fp);
        if (key){
            return new ResponseVO(new SimpleVO(0,"修改密码成功"));
        }else {
            return new ResponseVO("你自己的邮箱学号电话都能输错是吧？早日重开吧，别来了",new SimpleVO(1,"找回密码失败"));
        }

    }

}

package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.ForgetPasswordPO;
import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.model.PO.RegisterPO;
import icu.rolin.easy.model.VO.LoginVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/user")
public class userController {

    @PostMapping(value = "/login")
    public ResponseVO login(LoginPO loginPO){
        return new ResponseVO(new LoginPO());
    }

    @PostMapping(value = "/register")
    public ResponseVO register(RegisterPO registerPO){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/forget-password")
    public ResponseVO forget_password(ForgetPasswordPO fp){
        return new ResponseVO(new SimpleVO());
    }


}

package icu.rolin.easy.service;

import icu.rolin.easy.interceptor.UserInterceptor;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.PO.ForgetPasswordPO;
import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.model.PO.RegisterPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    // 核验登录
    public Integer varifyLogin(LoginPO loginPO){

        if (loginPO.getLoginType()==0){
            System.out.println("用户使用学号登录");
            Integer code = userMapper.varifyLoginByStudent_number(loginPO.getAccount(), loginPO.getPassword());
            if (code != null){
                System.out.println("检索到用户存在");
                return 1;
            }else {
                return 0;
            }

        }else if (loginPO.getLoginType()==1){
            System.out.println("用户使用手机号登录");
            Integer code = userMapper.varifyLoginByPhone_number(loginPO.getAccount(), loginPO.getPassword());
            if (code != null){
                System.out.println("检索到用户存在");
                return 1;
            }else {
                return 0;
            }
        }else {
            return null;
        }
    }

    // 用户注册
    public Integer userRegister(RegisterPO registerPO){

        Integer code = userMapper.insertUser(registerPO.getRealName(), registerPO.getUserName(), registerPO.getStudentID(), registerPO.getCollege(), registerPO.getPassword(), registerPO.getEmail(), registerPO.getPhone(), registerPO.getSex(), registerPO.getBirth(), registerPO.getHeadImage());
        if (code != 0){
            System.out.println("成功插入"+registerPO.toString());
            return 1;
        }else {
            return 0;
        }

    }

    // 用户忘记密码
    public Integer userForgetPassword(ForgetPasswordPO fp){

        Integer code = userMapper.updatePassword(fp.getPassword(), fp.getStudentID(), fp.getPhone(), fp.getEmail());
        if (code != 0){
            System.out.println("成功更改密码为"+fp.getPassword());
            return 1;
        }else {
            return 0;
        }

    }

}

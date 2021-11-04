package icu.rolin.easy.service;

import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.PO.ForgetPasswordPO;
import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.model.PO.RegisterPO;
import icu.rolin.easy.utils.TokenUtil;
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
    public boolean verifyLogin(LoginPO loginPO){

        if (loginPO.getLoginType()==0) {
            return userMapper.verifyLoginByStudent_number(loginPO.getAccount(), loginPO.getPassword()) == 1;
        }else if (loginPO.getLoginType()==1) {
            return userMapper.verifyLoginByPhone_number(loginPO.getAccount(), loginPO.getPassword()) == 1;
        }else {
            logger.warn("登陆参数错误");
            return false;
        }
    }

    //获取用户UID
    public int getUserID(LoginPO lpo){
        Integer uid = -1;
        if (lpo.getLoginType() == 0)
            uid = userMapper.findIdByNumber(lpo.getAccount());
        else
            uid = userMapper.findIdByPhone(lpo.getAccount());

        if (uid == null) uid = -1;
        return uid;
    }

    //获取Token
    public String generateToken(LoginPO lpo,int uid){
        return TokenUtil.sign(uid,lpo.getPassword());

    }

    // 用户注册
    public boolean userRegister(RegisterPO registerPO){
        Integer code = userMapper.insertUser(registerPO.getRealName(), registerPO.getUserName(), registerPO.getStudentID(), registerPO.getCollege(), registerPO.getPassword(), registerPO.getEmail(), registerPO.getPhone(), registerPO.getSex(), registerPO.getBirth(), registerPO.getHeadImage());
        if (code == null) {
            logger.error("用户注册失败，数据库返回空结果集");
            code = -1;
        }
        return code == 1;
    }

    // 用户忘记密码
    public boolean userForgetPassword(ForgetPasswordPO fp){
        Integer code = userMapper.updatePassword(fp.getPassword(), fp.getStudentID(), fp.getPhone(), fp.getEmail());
        if (code == null){
            logger.warn("忘记密码功能出错，null");
            code = 0;
        }
        return code == 1;
    }

}

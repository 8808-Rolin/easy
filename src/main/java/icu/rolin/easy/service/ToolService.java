package icu.rolin.easy.service;

import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.PO.UniVariablePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

    @Autowired
    private UserMapper userMapper;

    // 判断用户学号与手机号是否唯一
    public Integer varifyAccountUniqueness(UniVariablePO uv){

        Integer code = userMapper.varifyAccount(uv.getStudentID(), uv.getPhone());
        if (code == 0){
            System.out.println("用户获取账号唯一性成功");
            return 0;
        }else if (code == 1){
            Integer Stundet_numberUniCode = userMapper.varifyStudent_number(uv.getStudentID());
            Integer PhoneUniCode = userMapper.varifyStudent_number(uv.getPhone());
            if (Stundet_numberUniCode == 1 && PhoneUniCode == 0){
                return 1;
            }else if (Stundet_numberUniCode == 0 && PhoneUniCode == 1){
                return 2;
            }else {
                System.out.println("核验账号学号处出现错误！");
                return null;
            }
        }else if (code == 2){
            System.out.println("存在多个账号，账号不唯一");
            return 3;
        }else {
            System.out.println("核验账号学号方法出现了超过2条数据的情况，检查语句是否出现错误！");
            return 4;
        }

    }

}

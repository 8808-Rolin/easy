package icu.rolin.easy.service;

import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.PO.UniVariablePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

    @Autowired
    private UserMapper userMapper;
    private final static Logger logger = LoggerFactory.getLogger(ToolService.class);
    // 判断用户学号与手机号是否唯一
    public int verifyAccountUniqueness(UniVariablePO uv){

        Integer code = userMapper.verifyAccount(uv.getStudentID(), uv.getPhone());
        if (code == 0){
            return 0;
        }else if (code == 1) {
            Integer Student_numberUniCode = userMapper.verifyStudent_number(uv.getStudentID());
            Integer PhoneUniCode = userMapper.verifyPhone(uv.getPhone());
            if (Student_numberUniCode == 1 && PhoneUniCode == 0) {
                return 1;
            } else if (Student_numberUniCode == 0 && PhoneUniCode == 1) {
                return 2;
            } else if (Student_numberUniCode == 1 && PhoneUniCode == 1) {
                return 3;
            }else{
                return 4;
            }
        }else if(code == 2){
            return 3;
        }else {
            logger.error("核验账号学号方法出现了超过2条数据的情况，检查语句是否出现错误！");
            return 4;
        }

    }

}

package icu.rolin.easy.service;

import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.POJO.CollegePOJO;
import icu.rolin.easy.model.POJO.UserPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InfoService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollegeTableMapper collegeTableMapper;
    private final static Logger logger = LoggerFactory.getLogger(InfoService.class);

    public UserPOJO getPersonInformation(Integer uid){

        if (uid == null){
            logger.error("用户uid缺失");
            return null;
        }else {
            User userDO = userMapper.findById(uid);
            System.out.println(userDO.toString());

            College_Table college_table = collegeTableMapper.getCollegeById(userDO.getCollege_id());
            System.out.println(college_table);
            UserPOJO userPOJO = new UserPOJO(userDO.getUsername(), userDO.getRealname(), userDO.getStudent_number(), college_table.getCollege_name(), userDO.getIntro(), userDO.getLevel(), userDO.getUser_avatar());

            return userPOJO;
        }
    }

}

package icu.rolin.easy.service;

import icu.rolin.easy.mapper.AssociationMapper;
import icu.rolin.easy.mapper.AssociationUserMapper;
import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.DO.Association_User;
import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.POJO.AssMemberPOJO;
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
    @Autowired
    private AssociationUserMapper associationUserMapper;
    @Autowired
    private AssociationMapper am;

    private final static Logger logger = LoggerFactory.getLogger(InfoService.class);

    public UserPOJO getPersonInformation(Integer uid){
        if (uid == null){
            logger.error("用户uid缺失");
            return null;
        }else {
            User userDO = userMapper.findById(uid);
            System.out.println(userDO.toString());

            College_Table college_table = collegeTableMapper.findCollegeById(userDO.getCollege_id());
            System.out.println(college_table);
            UserPOJO userPOJO = new UserPOJO(
                    userDO.getUsername(),
                    userDO.getRealname(),
                    userDO.getStudent_number(),
                    college_table.getCollege_name(),
                    userDO.getIntro(),
                    userDO.getLevel(),
                    userDO.getUser_avatar());
            return userPOJO;
        }
    }


    /**
     * 该接口是获取简单的社员信息，用以选择社员
     * @param aid 传入一个社团ID值，用以获取该社团的成员
     * @return 返回一个列表，供Controller包装
     */
    public AssMemberPOJO[] getAssMemberList (Integer aid){
        ArrayList<Association_User> aau = associationUserMapper.findAllMembersByAID(aid);
        AssMemberPOJO[] amps = new AssMemberPOJO[aau.size()];
        for (int i = 0; i < aau.size(); i++) {
            amps[i] = new AssMemberPOJO();
            amps[i].setUid(aau.get(i).getU_id());
            User user = userMapper.findSomeById(amps[i].getUid());
            if(user == null) {
                amps[i].setUsername("该用户不存在");
                amps[i].setRealname("该用户不存在");
                amps[i].setStudentid("-1");
            }else{
                amps[i].setUsername(user.getUsername());
                amps[i].setRealname(user.getRealname());
                amps[i].setStudentid(user.getRealname());
            }
        }
        return amps;
    }

    /**
     * 判断一个社团ID是否存在对于社团
     * @param aid 传入一个aid作为判据
     * @return  返回一个布尔值，真表示存在，否表示不存在
     */
    public boolean getAssIsExist(Integer aid){
        return am.findAssIsExist(aid) == 1;
    }

}

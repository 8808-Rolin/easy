package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.*;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.POJO.AssMemberPOJO;
import icu.rolin.easy.model.POJO.CollegePOJO;
import icu.rolin.easy.model.POJO.PersonActionPOJO;
import icu.rolin.easy.model.POJO.UserPOJO;
import icu.rolin.easy.model.VO.GetActionInfoVO;
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
    @Autowired
    private ActionMapper actionMapper;
    @Autowired
    private JoinActionMapper joinActionMapper;
    @Autowired
    private ContentMapper contentMapper;

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
     * attendCode 的判定或许存在bug..
     * 方法判定或许还存在缺失..
     */

    public PersonActionPOJO[] getActionOverview(UserAssNotePO ua){
        ArrayList<Action> actions = actionMapper.findAssActionsByA_id(ua.getAid());
        if (actions.size()==0) {
            return new PersonActionPOJO[actions.size()];
        }else if (actions == null){
            return null;
        }
        PersonActionPOJO[] personActionPOJOS = new PersonActionPOJO[actions.size()];
        Integer attendCode = joinActionMapper.verifyUserJoinActionById(ua.getAid(), ua.getUid());
        if (attendCode==null) attendCode = 0;
        else attendCode = 1;
        for (int i=0;i<actions.size();i++){
            personActionPOJOS[i].setActid(actions.get(i).getA_id());
            personActionPOJOS[i].setTitle(actions.get(i).getTitle());
            personActionPOJOS[i].setDate(actions.get(i).getStart_time().toString());
            personActionPOJOS[i].setIsAttend(attendCode);
        }
        return personActionPOJOS;
    }
    /**
     * 活动的详细信息JooLum觉得无需再做判定
     *此处通过二步核验来决定用户参加活动的权限
     * 1. 先核验用户是否是该社团的
     * 2. 若是,则进行权限核验,若不是则返回无权限码
     */
    public GetActionInfoVO getDetailedActionInformation(Integer uid, Integer actid){
        GetActionInfoVO getActionInfoVO = new GetActionInfoVO();
        Action actionInfo = actionMapper.getDetailedAssActioonByAcId(actid);
        Content content = contentMapper.getContentByID(actionInfo.getContent_id());
        Integer status;
        Integer isJoinAssCode = associationUserMapper.getUserIsJoinAssociation(actionInfo.getA_id(),uid);
        if (isJoinAssCode == 0){
            status = 1;
        }else if (isJoinAssCode == 1){
            status = joinActionMapper.verifyUserJoinActionById(actid,uid);
            if (status == null){
                getActionInfoVO.setCode(0);
                getActionInfoVO.setMsg("获取成功");
                getActionInfoVO.setTitle(actionInfo.getTitle());
                getActionInfoVO.setContent(content.getContent());
                getActionInfoVO.setReleaseDate(actionInfo.getCreate_time().toString());
                getActionInfoVO.setStartDate(actionInfo.getStart_time().toString());
                getActionInfoVO.setOverDate(actionInfo.getEnd_time().toString());
                getActionInfoVO.setPosition(actionInfo.getPosition());
                getActionInfoVO.setStatus(0);
            }
            getActionInfoVO.setCode(0);
            getActionInfoVO.setMsg("获取成功");
            getActionInfoVO.setTitle(actionInfo.getTitle());
            getActionInfoVO.setContent(content.getContent());
            getActionInfoVO.setReleaseDate(actionInfo.getCreate_time().toString());
            getActionInfoVO.setStartDate(actionInfo.getStart_time().toString());
            getActionInfoVO.setOverDate(actionInfo.getEnd_time().toString());
            getActionInfoVO.setPosition(actionInfo.getPosition());
            getActionInfoVO.setStatus(2);
        }

        return getActionInfoVO;
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

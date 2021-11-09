package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.*;
import icu.rolin.easy.model.PO.GetPostsPO;
import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.POJO.*;
import icu.rolin.easy.model.VO.CollegeListVO;
import icu.rolin.easy.model.VO.GetActionInfoVO;
import icu.rolin.easy.utils.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class SelectService {
    private final static Logger logger = LoggerFactory.getLogger(SelectService.class);
    //注入所有的数据库操作表
    @Autowired
    ActionMapper actionMapper;
    @Autowired
    ApplyCommondMapper applyCommondMapper;
    @Autowired
    ApplyCreateMapper applyCreateMapper;
    @Autowired
    ApplyJoinAssociationMapper applyJoinAssociationMapper;
    @Autowired
    AssociationMapper associationMapper;
    @Autowired
    AssociationUserMapper associationUserMapper;
    @Autowired
    CollegeTableMapper collegeTableMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    FavoriteTableMapper favoriteTableMapper;
    @Autowired
    JoinActionMapper joinActionMapper;
    @Autowired
    MailMapper mailMapper;
    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;

    // ----获取操作----


    /**
     * 获取用户的UID操作
     * @param lpo 闯入一个登陆对象。实际只需要账号以及登陆类型，不需要密码
     * @return 返回一个UID给用户
     */
    public int getUserID(LoginPO lpo){
        Integer uid = -1;
        if (lpo.getLoginType() == 0)
            uid = userMapper.findIdByNumber(lpo.getAccount());
        else
            uid = userMapper.findIdByPhone(lpo.getAccount());

        if (uid == null) uid = -1;
        return uid;
    }


    /**
     * 通过UID获取用户的Level字段值
     * @param id 一个uid
     * @return 返回一个Level等级，详细权限请查看文档
     */
    public Integer getTheLevelById(Integer id){
        return userMapper.getLevelById(id);
    }

    /**
     * 获取最后插入的CAID
     * @return 返回一个CAID
     */
    public int getCAID(){
        return applyCreateMapper.getLastInsertId();
    }

    /**
     * 获取公告交流区的公告
     * @return 返回一个SimpleNoticePOJO数组
     */
    public SimpleNoticePOJO[] showNotices(){
        ArrayList<Post> posts = postMapper.findPostByPostType(0);
        if(posts.size() == 0) return null;

        SimpleNoticePOJO[] simpleNoticePOJOS = new SimpleNoticePOJO[posts.size()];
        for (int i = 0;i<posts.size();i++){
            simpleNoticePOJOS[i] = new SimpleNoticePOJO();
            simpleNoticePOJOS[i].setTitle(posts.get(i).getTitle());
            simpleNoticePOJOS[i].setDate(Common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd"));
            simpleNoticePOJOS[i].setPid(posts.get(i).getId());
        }
        return simpleNoticePOJOS;
    }

    /**
     * 用以所有社团信息、用户加入信息业务处理
     * @param uid 传入一个UID
     * @return  返回一个ShowDataAssPOJO数组
     */
    public ShowDataAssPOJO[] showDatum(Integer uid){
        ArrayList<Association> associations = associationMapper.findAllAssociation();
        if(associations.size() == 0) return null;
        ShowDataAssPOJO[] sdaPOJOs = new ShowDataAssPOJO[associations.size()];
        for (int i=0; i < associations.size(); i++) {
            sdaPOJOs[i].setAid(associations.get(i).getId());
            sdaPOJOs[i].setAssImage(associations.get(i).getLogo());
            sdaPOJOs[i].setAssName(associations.get(i).getName());
            //判断用户是否加入该社团
            Integer result = associationUserMapper.getUserIsJoinAssociation(sdaPOJOs[i].getAid(),uid);
            if (result > 1 || result < 0) {
                logger.error("获取用户是否参加社团数据失败，可能是数据库出现了不唯一性，请检查数据库\n数据处理结果Result："+result);
                return null;
            }
            sdaPOJOs[i].setIsJoin(result);
        }
        return sdaPOJOs;
    }


    /**
     * 根据用户需求返回对应的类型的帖子
     * @author Joolum
     * @param gppo 传入一个参数对象
     * @return 返回一个PostsPOJO数组
     */
    public PostsPOJO[] getPosts(GetPostsPO gppo){
        //获取该论坛的所有非公告帖子
        ArrayList<Post> posts;
        if (gppo.getType() == 1){
            posts = postMapper.findPostsByAidType(gppo.getAid(),0);
        }else if(gppo.getType() == 2){
            posts = postMapper.findPostsByAidExType(gppo.getAid(),0);
        }else{
            return null;
        }

        if (posts.size() == 0 || posts == null) return null;
        PostsPOJO[] ps = new PostsPOJO[posts.size()];
        //注值
        for (int i = 0; i < posts.size(); i++) {
            Integer id = posts.get(i).getId();
            ps[i] = new PostsPOJO();
            ps[i].setPid(id);
            ps[i].setPostType(Common.POST_TYPE.get(posts.get(i).getPost_type()));
            ps[i].setPostTitle(posts.get(i).getTitle());
            ps[i].setPostAuthor(userMapper.getNameById(posts.get(i).getU_id()));
            Comments last = commentsMapper.getLastCommentWithPost(id);
            if (last != null)
                ps[i].setReplyTime(Common.convertTimestamp2Date(last.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            else
                ps[i].setReplyTime(Common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            ps[i].setReleaseTime(Common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd"));
            ps[i].setReplies(commentsMapper.countCommentsByPid(id));
        }
        //排序
        Arrays.sort(ps, new Comparator<PostsPOJO>() {
            @Override
            public int compare(PostsPOJO o1, PostsPOJO o2) {
                Long o1Stamp = Common.date2Stamp(o1.getReplyTime(),"yyyy-MM-dd HH:mm:ss");
                Long o2Stamp = Common.date2Stamp(o2.getReplyTime(),"yyyy-MM-dd HH:mm:ss");
                Long res = o1Stamp - o2Stamp;
                if (res > 0)    return 1;
                else if (res < 0)   return -1;
                return 0;
            }
        });
        return ps;
    };


    /**
     * @param ua 参数对象，仅有UID与AID，Note字段是空值
     * @author :JooLum
     * @update-time: 2021年11月9日 11点33分
     * @return GetAssInfoAssPOJO对象
     * 如果出错则直接返回一个空对象
     */
    public GetAssInfoAssPOJO getAssInformation(UserAssNotePO ua){
        Association association =
                associationMapper.findAssociationById(ua.getAid());
        if (association == null) {
            logger.error("并不存在此社团，请检查数据库数据是否出错！");
            return null;
        }
        //获取ass数据
        String ass_admin = userMapper.getNameById(association.getLeader_id());
        GetAssInfoAssPOJO getAssInfoAssPOJO = new GetAssInfoAssPOJO(
                association.getName(),
                association.getIntro(),
                association.getLogo(),
                association.getParent_organization(),
                ass_admin
        );
        return getAssInfoAssPOJO;
    }


    /**
     * 获取用户在该社团的一个权限代码
     * @param ua 参数对象，仅有UID与AID，Note字段是空值
     * @author :Rolin
     * @create-time: 2021年11月9日 11点33分
     * @return 权限代码，2为社团管理员 1为用户以加入该社团，0为用户未加入该社团
     * 如果出错则直接返回一个空对象
     */
    public Integer getUserAssPermission(UserAssNotePO ua){
        //判断该人是否加入了社团，否则返回0 是则下一步
        Integer level = userMapper.findLevelById(ua.getUid());
        if (level == null) return null;
        if (level == 0) return 0;
        //判断用户是否加入了该社团，否则返回0，是则继续
        Association_User au = associationUserMapper.findAssociation_userById(ua.getUid(),ua.getAid());
        if(au == null) return 0;
        if(au.getIs_admin() == 0) return 1;
        else if (au.getIs_admin() == 1) return 2;
        else return null;
    }


    /**
     * 通过用户ID查找用户的名字，如果用户不存在则返回一个“找不到该用户”
     * @param uid 用户的ID
     * @return 用户名(Username)
     */
    public String getUserNameById(Integer uid){
        String userName = userMapper.getNameById(uid);
        if (userName == null){
            return "找不到该用户";
        }else {
            return userName;
        }
    }


    /**
     * 该方法返回一个CollegeListVO给Controller
     * 可以让前端展示学院与其ID的对应关系
     * @return CollegeListVO视图对象
     */
    public CollegeListVO getCollegeList()  {
        CollegeListVO cvo = new CollegeListVO();
        ArrayList<College_Table> ctList = collegeTableMapper.findAll();
        CollegePOJO[] cps = new CollegePOJO[ctList.size()];
        if (ctList == null){
            cvo.setCode(-1);
            cvo.setCollege(null);
            return cvo;
        }

        for (int i = 0; i < ctList.size(); i++) {
            cps[i] = new CollegePOJO();
            cps[i].setName(ctList.get(i).getCollege_name());
            cps[i].setCoid(ctList.get(i).getId());
        }
        cvo.setCode(ctList.size());
        cvo.setCollege(cps);
        return cvo;
    }


    /**
     * 获取所有社团列表，展示简单的数据
     * @return 返回一个AssOverviewPOJO数组
     */
    public AssOverviewPOJO[] get_association_list(){
        ArrayList<Association>  as= associationMapper.getAssOverview();
        if(as == null) return null;
        AssOverviewPOJO[] aos = new AssOverviewPOJO[as.size()];
        for (int i = 0; i < as.size(); i++) {
            aos[i] = new AssOverviewPOJO();
            aos[i].setAid(as.get(i).getId());
            aos[i].setName(as.get(i).getName());
        }
        return aos;
    }


    /**
     * 进行一个个人信息的获取
     * @param uid 传入一个用户uid进行查询
     * @return 返回以恶搞UserPOJO 包含用户信息
     */
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
     * @author Joolum
     * @update-time 2021年11月9日 18点12分
     * @param ua 传入一个Uid以及Aid的包装对象
     * @return 返回一个PersonActionPOJO,错误或无数据返回null
     */
    public PersonActionPOJO[] getActionOverview(UserAssNotePO ua){
        // 获取该社团的所有活动
        ArrayList<Action> actions = actionMapper.findByA_id(ua.getAid());
        if(actions == null || actions.size()==0) return null;
        // 创建对象数组
        PersonActionPOJO[] personActionPOJOS = new PersonActionPOJO[actions.size()];
        //注值
        for (int i=0;i<actions.size();i++){
            personActionPOJOS[i] = new PersonActionPOJO();
            personActionPOJOS[i].setActid(actions.get(i).getId());
            personActionPOJOS[i].setTitle(actions.get(i).getTitle());
            personActionPOJOS[i].setPosition(actions.get(i).getPosition());
            personActionPOJOS[i].setDate(Common.convertTimestamp2Date(actions.get(i).getStart_time(),"yyyy-MM-dd"));
            Integer attendCode = joinActionMapper.verifyUserJoinActionById(actions.get(i).getId(), ua.getUid());
            personActionPOJOS[i].setIsAttend(attendCode);
        }
        return personActionPOJOS;
    }


    /**
     * 活动的详细信息JooLum觉得无需再做判定 OK√
     *  获取状态码流程：
     * 1. 先核验用户是否是该社团的
     * 2. 若是,则进行权限核验,若不是则返回无权限码
     * @author Joolum
     * @param uid  传入一个uid,是用户的身份标志
     * @param actid 传入一个actid 是活动标志
     * @return 返回一个GetActionInfoVO对象
     */
    public GetActionInfoVO getDetailedActionInformation(Integer uid, Integer actid){
        // 实例化一个新返回对象
        GetActionInfoVO getActionInfoVO = new GetActionInfoVO();
        Content content = null;
        Action actionInfo = null;
        try{
            //获取活动详细信息
            actionInfo = actionMapper.getDetailedAssActioonByAcId(actid);
            //获取活动内容
            content = contentMapper.getContentByID(actionInfo.getContent_id());
        }catch (Exception e){
            //获取出错，返回一个错误状态对象
            e.printStackTrace();
            getActionInfoVO.setCode(1);
            getActionInfoVO.setMsg("数据库查询或后台错误，错误类型："+e.getMessage());
            return getActionInfoVO;
        }
        //获取状态码
        Integer status;
        // 判断该用户是否加入了社团 0：未加入，1：已加入
        if(associationUserMapper.getUserIsJoinAssociation(actionInfo.getA_id(),uid)==0)
            status = 0;
        else{
            status = joinActionMapper.verifyUserJoinActionById(actid,uid);
        }
        if(status == null) { //status等于null时表示数据库查询出大问题
            getActionInfoVO.setCode(1);
            getActionInfoVO.setMsg("数据库查询错误-status = null");
            return getActionInfoVO;
        }
        if(status == 1){ // 已参加
            status = 2;
        }else if (status != 0){ // 出大问题
            getActionInfoVO.setCode(1);
            getActionInfoVO.setMsg("数据库查询错误- status > 1");
            return getActionInfoVO;
        }
        getActionInfoVO.setCode(0);
        getActionInfoVO.setMsg("获取成功");
        getActionInfoVO.setTitle(actionInfo.getTitle());
        getActionInfoVO.setContent(content.getContent());
        getActionInfoVO.setReleaseDate(Common.convertTimestamp2Date(actionInfo.getCreate_time(),"yyyy-MM-dd"));
        getActionInfoVO.setStartDate(Common.convertTimestamp2Date(actionInfo.getStart_time(),"yyyy-MM-dd HH:mm:ss"));
        getActionInfoVO.setOverDate(Common.convertTimestamp2Date(actionInfo.getEnd_time(),"yyyy-MM-dd HH:mm:ss"));
        getActionInfoVO.setPosition(actionInfo.getPosition());
        getActionInfoVO.setStatus(status);
        return getActionInfoVO;
    }


    // -----验证操作-----


    /**
     * 核验登录信息的准确性
     * @param loginPO 传入一个登录参数对象
     * @return 返回一个布尔值，告知是否登陆成功
     */
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


    /**
     * 校验用户填入的手机与学号是否在表中是唯一的
     * @param uv 传入一个校验参数对象
     * @return 返回一个Int值，告诉处于何种状态，具体对应请查看接口开发文档
     */
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


    /**
     * 判断一个社团ID是否存在对于社团
     * @param aid 传入一个aid作为判据
     * @return  返回一个布尔值，真表示存在，否表示不存在
     */
    public boolean getAssIsExist(Integer aid){
        return associationMapper.findAssIsExist(aid) == 1;
    }

    /**
     * 判断用户是否加入了某个社团
     * @param aid 传入一个aid
     * @param uid 传入一个uid
     * @return 返回一个布尔值，表示用户有无参加该社团
     */
    public boolean userIsJoinAss(Integer aid,Integer uid){
        return associationUserMapper.getUserIsJoinAssociation(aid,uid) == 1;
    };



}

package icu.rolin.easy.service;

import icu.rolin.easy.interceptor.ZoneInterceptor;
import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.*;
import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.POJO.*;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.utils.Common;
import icu.rolin.easy.utils.TransformCurrentTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static icu.rolin.easy.interceptor.ZoneInterceptor.*;


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
        ArrayList<Post> posts = postMapper.findPostByPostTypeWithIndex(0);
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
            sdaPOJOs[i] = new ShowDataAssPOJO();
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
     * 根据用户需求返回对应的类型的帖子，并对其进行一个序的排
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
                long o1Stamp = Common.date2Stamp(o1.getReplyTime(),"yyyy-MM-dd HH:mm:ss");
                long o2Stamp = Common.date2Stamp(o2.getReplyTime(),"yyyy-MM-dd HH:mm:ss");
                long res = o1Stamp - o2Stamp;
                if (res > 0)    return -1;
                else if (res < 0)   return 1;
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
        logger.info("请求参数："+ua.toString());
        if(ua.getAid() == 0){
            return new GetAssInfoAssPOJO(
                    "公共论坛",
                    "无",
                    null,
                    null,
                    null
            );
        }
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
     * 获取用户在该社团的一个权限代码
     * 重写
     * @author :JooLum
     * @return 权限代码，1为社团管理员 0为普通用户，2为黑户
     * 如果出错则直接返回一个空对象
     */
    public Integer getUserAssPermission(Integer uid,Integer aid){
        //判断该人是否加入了社团，否则返回2 是则下一步
        Integer level = userMapper.findLevelById(uid);
        if (level == null) return null;
        if (level == 0) return 2;
        //判断用户是否加入了该社团，否则返回2，是则继续
        Association_User au = associationUserMapper.findAssociation_userById(uid,aid);
        if(au == null) return 2;
        if(au.getIs_admin() == 0) return 0;
        else if (au.getIs_admin() == 1) return 1;
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
     * @return 返回以一个UserPOJO 包含用户信息
     */
    public UserPOJO getPersonInformation(Integer uid){
        User userDO = userMapper.findById(uid);
        logger.info(userDO.toString());

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
            amps[i].setIs_admin(aau.get(i).getIs_admin());
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
        ArrayList<Action> actions = actionMapper.findByA_idApproved(ua.getAid());
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
        logger.info("actid:"+actid+"----uid:"+uid);
        logger.debug("test");
        // 实例化一个新返回对象
        GetActionInfoVO getActionInfoVO = new GetActionInfoVO();
        Content content = null;
        Action actionInfo = null;
        try{
            //获取活动详细信息
            actionInfo = actionMapper.getDetailedAssActionByAcId(actid);
            logger.info(actionInfo.toString());
            //获取活动内容
            content = contentMapper.getContentByID(actionInfo.getContent_id());
            logger.info(content.getContent());
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
            // 未加入社团 没有权限参加活动 为1
            status = 1;
        else{
            status = joinActionMapper.verifyUserJoinActionById(actid,uid);
            if (status == 0){ //是社团 未参加活动
                status = 0;
            }else if(status == 1){ //是社团 已经参加活动
                status = 2;
            }else{
                status = null;
            }
        }

        if(status == null) { //status等于null时表示数据库查询出大问题
            getActionInfoVO.setCode(1);
            getActionInfoVO.setMsg("数据库查询错误-status = null");
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

    /**
     * 活动的详细信息JooLum觉得无需再做判定 OK√
     *  获取状态码流程：
     * 1. 先核验用户是否是该社团的
     * 2. 若是,则进行权限核验,若不是则返回无权限码
     * @author Joolum
     * @param actid 传入一个actid 是活动标志
     * @return 返回一个GetActionInfoVO对象
     */
    public GetActionInfoVO getDetailedActionInformation(Integer actid){
        // 实例化一个新返回对象
        GetActionInfoVO getActionInfoVO = new GetActionInfoVO();
        Content content = null;
        Action actionInfo = null;
        try{
            //获取活动详细信息
            actionInfo = actionMapper.getDetailedAssActionByAcId(actid);
            logger.info(actionInfo.toString());
            //获取活动内容
            content = contentMapper.getContentByID(actionInfo.getContent_id());
            logger.info(content.getContent());
        }catch (Exception e){
            //获取出错，返回一个错误状态对象
            e.printStackTrace();
            getActionInfoVO.setCode(1);
            getActionInfoVO.setMsg("数据库查询或后台错误，错误类型："+e.getMessage());
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
        return getActionInfoVO;
    }



    /**
     * 获取发帖人权限信息代码，获取帖子与发帖人信息的业务拆分-A
     * @author Rolin
     * @param pid 传入一个帖子PID
     * @return 返回一个权限代码，1为管理员，0为普通用户 2为用户不存在
     */
    public int getPermissionCodeWithPost(Integer pid){
        // 通过PID获取UID与AID
        Post post = postMapper.findPostById(pid);
        Integer muid = post.getU_id();
        Integer aid = post.getA_id();
        // 通过校验UID与AID，可以判断当前的用户身份
        Integer res = associationUserMapper.findUserIsAdminByUidAid(aid,muid);
        if(res == null) {
            return 2;
        }
        return res;
    }


    /**
     * 获取帖子的详细内容，获取帖子与发帖人信息的业务拆分-B
     * @author Rolin
     * @param pid 帖子ID
     * @return 返回PostPOJO
     */
    public PostPOJO getPostInfoWithPost(Integer pid,Integer uid){
        // 获取Post表内容
        String context = "";
        Post post = postMapper.findPostById(pid);
        if(post == null) return null;
        // 通过内容ID获取
        Content content = contentMapper.getContentByID(post.getContent_id());
        //通过PID获取用户是否收藏了该帖子
        Integer is_favorite = favoriteTableMapper.isFavorite(pid,uid);
        // 返回对应内容对象
        if (content != null) context = content.getContent();
        PostPOJO postPOJO = new PostPOJO();
        postPOJO.setTitle(post.getTitle());
        postPOJO.setContent(context);
        postPOJO.setIsFavorite(is_favorite);
        postPOJO.setReleaseDate(Common.convertTimestamp2Date(post.getCreate_time(),"yyyy-MM-dd"));
        // 进行一个标签的分割
        String tagString  = post.getTags();
        String[] tags = tagString.split(",");
        postPOJO.setTags(tags);
        return postPOJO;
    }

    /**
     * 获取帖子的楼主的信息，获取帖子与发帖人信息的业务拆分-C
     * @author Rolin
     * @param pid 传入一个PID
     * @return 返回一个MasterPojo
     */
    public MasterPOJO getMasterInfoWithPost(Integer pid){
        Post post = postMapper.findPostById(pid);
        Integer uid = post.getU_id();
        MasterPOJO masterPOJO = new MasterPOJO();
        User user = userMapper.findById(uid);
        if(user == null) return null;
        masterPOJO.setMuid(uid);
        masterPOJO.setUsername(user.getUsername());
        masterPOJO.setOrg(collegeTableMapper.findCollegeNameById(user.getCollege_id()));
        masterPOJO.setIntro(user.getIntro());
        masterPOJO.setImage(user.getUser_avatar());
        return masterPOJO;
    }

    /**
     * 获取用户发布的帖子，用于展示在空间上
     * @param uid 用户UID(空间UID)
     * @return 返回一个ZonePostPOJO数组
     */
    public ZonePostPOJO[] zoneGetPostRelease (int uid){
        ArrayList<Post> posts = postMapper.getPostsByU_id(uid);
        ZonePostPOJO[] zonePostPOJOS = new ZonePostPOJO[posts.size()];
        for (int i=0;i< zonePostPOJOS.length;i++){
            zonePostPOJOS[i] = new ZonePostPOJO();
            Association association = null;
            if(posts.get(i).getA_id() != 0){
                association = associationMapper.findAssociationById(posts.get(i).getA_id());
            }
            int replies = commentsMapper.countCommentsByPid(posts.get(i).getId());
            if(association == null){
                zonePostPOJOS[i].setAid(0);
                zonePostPOJOS[i].setAname("公共交流区");
                zonePostPOJOS[i].setAimage("/images/default.jpg");
            }else{
                zonePostPOJOS[i].setAid(association.getId());
                zonePostPOJOS[i].setAname(association.getName());
                zonePostPOJOS[i].setAimage(association.getLogo());
            }
            zonePostPOJOS[i].setPid(posts.get(i).getId());
            zonePostPOJOS[i].setTitle(posts.get(i).getTitle());
            zonePostPOJOS[i].setDate(Common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            zonePostPOJOS[i].setReplies(replies);
        }
        return zonePostPOJOS;
    }

    /**
     * 获取用户收藏的帖子，用于展示在空间上
     * @param uid 用户UID(空间UID)
     * @return 返回一个ZonePostPOJO数组
     */
    public ZonePostPOJO[] zoneGetPostFavorite (int uid){
        //得到该用户收藏的所有列表
        ArrayList<Favorite_Table> favorite_tables = favoriteTableMapper.getAllFavoriteTableByU_id(uid);
        ArrayList<Post> posts = new ArrayList<>();
        ZonePostPOJO[] zonePostPOJOS = new ZonePostPOJO[favorite_tables.size()];
        int index = 0;
        for (Favorite_Table favorite_table : favorite_tables) {
            posts.add(postMapper.findPostById(favorite_table.getP_id()));
            zonePostPOJOS[index] = new ZonePostPOJO();
            zonePostPOJOS[index].setDate(Common.convertTimestamp2Date(favorite_table.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            zonePostPOJOS[index].setPid(favorite_table.getP_id());
            index++;
        }
        for (int ix=0;ix< zonePostPOJOS.length;ix++){
            Association association = null;
            int replies = commentsMapper.countCommentsByPid(favorite_tables.get(ix).getP_id());
            if(posts.get(ix).getA_id() != 0){
                association = associationMapper.findAssociationById(posts.get(ix).getA_id());

            }
            if(association == null){
                zonePostPOJOS[ix].setAid(0);
                zonePostPOJOS[ix].setAname("公共交流区");
                zonePostPOJOS[ix].setAimage("/images/default.jpg");
            }else{
                zonePostPOJOS[ix].setAid(association.getId());
                zonePostPOJOS[ix].setAname(association.getName());
                zonePostPOJOS[ix].setAimage(association.getLogo());
            }
            zonePostPOJOS[ix].setTitle(posts.get(ix).getTitle());
            zonePostPOJOS[ix].setReplies(replies);
        }
        return zonePostPOJOS;
    }


    /**
     * 获取邮箱概要，即获取isRead字段为1和0的邮件概要
     * @param uid 用户UID
     * @return 返回渲染好的视图对象
     */
    public MailOverviewPOJO[] getMails(Integer uid){
        // 我收的
        ArrayList<Mail> mailsTo = mailMapper.getMailsByTo_id(uid);
        // 我发的
        ArrayList<Mail> mailsFrom = mailMapper.getMailsByFrom_id(uid);
        int mailsNumber = mailsTo.size() + mailsFrom.size();

        MailOverviewPOJO[] mailOverviewPOJOS = new MailOverviewPOJO[mailsNumber];
        // 收件箱
        for (int i=0;i<mailsTo.size();i++){
            mailOverviewPOJOS[i] = new MailOverviewPOJO();
            mailOverviewPOJOS[i].setMid(mailsTo.get(i).getId());
            mailOverviewPOJOS[i].setTitle(mailsTo.get(i).getTitle());
            mailOverviewPOJOS[i].setDate(Common.convertTimestamp2Date(mailsTo.get(i).getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            mailOverviewPOJOS[i].setIsRead(mailsTo.get(i).getIs_read());
            mailOverviewPOJOS[i].setIsSystem(mailsTo.get(i).getIs_system());
            mailOverviewPOJOS[i].setType(0);
            if(mailsTo.get(i).getIs_system() == 0){
                String fromUserName = userMapper.getNameById(mailsTo.get(i).getFrom_id());
                mailOverviewPOJOS[i].setName(fromUserName);
                mailOverviewPOJOS[i].setName_id(mailsTo.get(i).getFrom_id());
            }else{
                mailOverviewPOJOS[i].setName("系 统");
            }
        }
        int j = 0;
        // 发件箱
        for (int i=mailsTo.size();i<mailsNumber;i++){
            mailOverviewPOJOS[i] = new MailOverviewPOJO();
            mailOverviewPOJOS[i].setMid(mailsFrom.get(j).getId());
            mailOverviewPOJOS[i].setTitle(mailsFrom.get(j).getTitle());
            mailOverviewPOJOS[i].setDate(Common.convertTimestamp2Date(mailsFrom.get(j).getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            mailOverviewPOJOS[i].setIsRead(mailsFrom.get(j).getIs_read());
            mailOverviewPOJOS[i].setIsSystem(mailsFrom.get(j).getIs_system());
            String toUserName = userMapper.getNameById(mailsFrom.get(j).getFrom_id());
            mailOverviewPOJOS[i].setName(toUserName);
            mailOverviewPOJOS[i].setType(1);
            j++;
        }
        return mailOverviewPOJOS;
    }


    /**
     * 获取邮箱邮件的详细信息
     * 如果isRead字段为2则无法获取（因为该邮件已被删除）
     *
     * @param type 0：表示不会改变已读，1：表示会将邮件置为已读状态
     * @param mid 邮件ID
     * @return 返回渲染好的视图
     * @update 添加获取之后，将其标识为已读
     */
    public SimpleVO getMailContent( Integer mid,int type){
        SimpleVO simpleVO = new SimpleVO();
        Mail mail = mailMapper.getContentById(mid);
        if (mail == null || mail.getContent() == null){
            simpleVO.setMsg("获取邮件错误");
            simpleVO.setCode(-1);
        }else if (mail.getContent().equals("")){
            logger.info("这封邮件就像那群嘉心糖一样，P用没有！");
            simpleVO.setMsg("这是一封空邮件");
            simpleVO.setCode(0);
        }else {
            simpleVO.setMsg(mail.getContent());
            simpleVO.setCode(1);
            if(type == 1){
                mailMapper.setMailRead(mail.getId());
            }
        }
        if(mail != null && mail.getIs_read() == 2) {
            simpleVO.setMsg("该邮件已删除");
            simpleVO.setCode(0);
        }
        return simpleVO;
    }

    /**
     * 获取空间个人信息以及公告、社团信息
     * @param uid 空间主人的uid
     * @param req 请求，用于判断Token
     * @return 返回一个ZoneUserDataPOJO对象
     */
    public ZoneUserDataPOJO getZoneUserData(int uid,HttpServletRequest req){
        ZoneUserDataPOJO zud = new ZoneUserDataPOJO();
        User user = userMapper.findById(uid);
        boolean status = ZoneInterceptor.verifyZoneStatus(req,uid);
        zud.setUid(user.getId());
        zud.setUsername(user.getUsername());
        zud.setProfile(user.getUser_avatar());
        zud.setEmail(user.getEmail());
        zud.setCollege(collegeTableMapper.findCollegeNameById(user.getCollege_id()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        zud.setBirth(dateFormat.format(user.getBirth()));
        int number = postMapper.countPostOfUid(uid);
        number += commentsMapper.countCommentsByUid(uid);
        zud.setNumpost(number);
        zud.setNotice(user.getNotice());
        if(status){
            zud.setRealname(user.getRealname());
            zud.setPhone(user.getPhone());
        }else{
            zud.setRealname(Common.getStarString2(user.getRealname(),1,1));
            zud.setPhone(Common.getStarString2(user.getPhone(),3,4));
        }
        return zud;
    }

    /**
     * 空间信息，用户加入的社团基础信息
     * @param uid 用户ID
     * @return 返回一个数组对象
     */
    public ZoneJoinAssPOJO[] getZoneAssInfo(int uid){
        ArrayList<Association_User> aus= associationUserMapper.getAllUserJoinAssociations(uid);
        ArrayList<Association> associations = new ArrayList<>();
        for (Association_User au : aus) {
            associations.add(associationMapper.findAssociationById(au.getA_id()));
        }
        ZoneJoinAssPOJO[] zja = new ZoneJoinAssPOJO[associations.size()];
        for (int i = 0; i < zja.length; i++) {
            zja[i] = new ZoneJoinAssPOJO();
            zja[i].setAid(associations.get(i).getId());
            zja[i].setName(associations.get(i).getName());
            zja[i].setProfile(associations.get(i).getLogo());
        }
        return zja;
    }


    /**
     * 获取一个帖子下的评论数量，然后对其进行一个分页算法
     * @param pid 帖子PID
     * @param max_per_page 每一页最大的数据条数
     * @return 返回一个分页数量
     */
    public int getMaxPageInDiscuss(Integer pid,int max_per_page){
        Integer discuss_number = commentsMapper.countCommentsByPid(pid);
        // 没有评论
        if(discuss_number == 0) return 0;
        // 有评论，计算页数
        return (discuss_number / max_per_page) + 1;
    }


    /**
     * 根据参数获取对应的回复列表
     * @author Rolin
     * @param page 对应页码
     * @param pid 对应帖子
     * @param max 单页最大帖子数量
     * @return 返回一个评论数组
     */
    public DiscussPOJO[] getDiscuss(int page,int pid,int max) {
        // 计算左右限
        int left = (page - 1) * max;
        int right = page * max;
        //查询对应评论
        ArrayList<Comments> comments = commentsMapper.findByPidWithTimeDescForLimit(pid, left, right);
        //实例化对象
        if (comments.size() == 0) return null;
        DiscussPOJO[] discussPOJOS = new DiscussPOJO[comments.size()];
        // 注入信息
        for (int i = 0; i < comments.size(); i++) {
            discussPOJOS[i] = new DiscussPOJO();
            discussPOJOS[i].setPage(page);
            DiscussContentPOJO dc = new DiscussContentPOJO();
            dc.setCid(comments.get(i).getId());
            dc.setText(comments.get(i).getContent());
            dc.setReleaseDate(Common.convertTimestamp2Date(comments.get(i).getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
            discussPOJOS[i].setContent(dc);
            //获取评论人信息
            Integer uid = comments.get(i).getU_id();
            User user = userMapper.findById(uid);
            DiscussAuthorPOJO author = new DiscussAuthorPOJO();
            author.setCuid(uid);
            author.setUsername(user.getUsername());
            author.setUserImage(user.getUser_avatar());
            discussPOJOS[i].setAuthor(author);
        }
        return discussPOJOS;

    }


    /**
     * 搜索帖子，返回所有帖子组成的数组对象
     * @param key 关键词
     * @return SearchPostPOJO
     */
    // TODO: 2021/11/22 增加连锁搜索，搜索按关联性排序
    public SearchPostPOJO[] getSearchResultOfPost(String key){
        // 初始化集合对象
        Set<SearchPostPOJO> ssp = new HashSet<>();
        // 匹配标题，添加到集合中
        ArrayList<Post> posts = postMapper.findPostTitleLikeKey(key);
        for (Post post : posts) {
           addPostToSet(ssp,post);
        }
        // 搜索标签，将结果添加到集合中
        ArrayList<Integer> tags = postMapper.findIdByKey(key);
        for (Integer tag : tags) {
            Post post = postMapper.findPostById(tag);
            addPostToSet(ssp,post);
        }

        // 搜索内容表，返回所有符合的内容(只有ID),获取所有的帖子ID信息(ID and Content_id)
        ArrayList<Content> contents = contentMapper.findContentLikeKey(key);
        Set<Integer> contents_id = new HashSet<>();
        for (Content content : contents) {
            contents_id.add(content.getId());
        }
        ArrayList<Post> postIDs = postMapper.findPostIdContentId();
        // 比对帖子ID，提取符合的内容,添加到列表中
        for (Post postID : postIDs) {
            if(contents_id.contains(postID.getContent_id())){ //如果相同，则写入，如果不同，则下一个循环
                Post p = postMapper.findPostById(postID.getId());
                addPostToSet(ssp,p);
            }
        }
        // 搜索符合条件的评论
        ArrayList<Comments> comments = commentsMapper.findCommentLikeKey(key);
        // 提取帖子ID，添加到列表中
        for (Comments comment : comments) {
            Post p = postMapper.findPostById(comment.getP_id());
            addPostToSet(ssp,p);
        }
        // 构造数组并排序
        SearchPostPOJO[] spp = new SearchPostPOJO[ssp.size()];
        int index = 0;
        for (SearchPostPOJO searchPostPOJO : ssp) {
            spp[index++] = searchPostPOJO;
        }
        Arrays.sort(spp, new Comparator<SearchPostPOJO>() {
            @Override
            public int compare(SearchPostPOJO o1, SearchPostPOJO o2) {
                long time1 = Common.date2Stamp(o1.getReleaseDate(),"yyyy-MM-dd HH:mm:ss");
                long time2 = Common.date2Stamp(o2.getReleaseDate(),"yyyy-MM-dd HH:mm:ss");
                long res = time2 - time1;
                if(res < 0 ) return -1;
                else if(res == 0) return 0;
                else return 1;
            }
        });
        // 返回结果
        return spp;
    }

    /**
     * 提取方法，将一个帖子处理后放置到Set集合中去
     * @param ssp 需要增加的Set合集
     * @param p 帖子
     */
    public void addPostToSet(Set<SearchPostPOJO> ssp, Post p){
        SearchPostPOJO sp = new SearchPostPOJO();
        sp.setPid(p.getId());
        if(ssp.contains(sp)) return;
        sp.setTitle(p.getTitle());
        // 对内容进行处理
        String content  = contentMapper.getContentByID(p.getContent_id()).getContent();
        String text = Common.StripHT(content);
        if(text.length() >= 150){
            sp.setContent(text.substring(0,140));
        }else sp.setContent (text);
        sp.setAid(p.getA_id());
        if (p.getA_id() == 0){
            sp.setAname("公共交流区");
        }else{
            sp.setAname(associationMapper.getAssociationNameById(p.getA_id()));
        }
        sp.setAuthorUID(p.getU_id());
        sp.setAuthorName(userMapper.getNameById(p.getU_id()));
        sp.setReleaseDate(Common.convertTimestamp2Date(p.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
        ssp.add(sp);
    }

    /**
     * 搜索帖子，返回所有用户组成的数组对象
     * @param key 关键词
     * @return SearchUserPOJO
     */
    // TODO: 2021/11/22  添加更多判定功能，智能化选择需要的用户
    public SearchUserPOJO[] getSearchResultOfUser(String key){
        // 初始化
        Set<SearchUserPOJO> ssu = new HashSet<>();
        // 唯一性匹配
        User u = userMapper.findByPhoneStudentID(key,key);
        if (u != null ){
            addUserToSet(ssu,u);
            SearchUserPOJO[] t = new SearchUserPOJO[1];
            for (SearchUserPOJO searchUserPOJO : ssu) {
                t[0] = searchUserPOJO;
            }
            return t;
        }
        // 搜索用户名称、简介匹配
        ArrayList<User> users = userMapper.findLikeKey(key);
        for (User user : users) {
            addUserToSet(ssu,user);
        }
        // 帖子Tags匹配的用户
        ArrayList<Post> uid = postMapper.findUidByTags(key);
        for (Post post : uid) {
            User user = userMapper.findById(post.getU_id());
            addUserToSet(ssu,user);
        }
        SearchUserPOJO[] t = new SearchUserPOJO[ssu.size()];
        int i = 0;
        for (SearchUserPOJO supojo : ssu) {
           t[i++] = supojo;
        }
        return t;
    }

    /**
     * 提取方法，将一个用户信息处理后放置到Set集合中去
     * @param ssu 需要增加的Set合集
     * @param u 用户信息
     */
    public void addUserToSet(Set<SearchUserPOJO> ssu, User u){
        SearchUserPOJO su = new SearchUserPOJO();
        su.setUid(u.getId());
        if(ssu.contains(su)) return;
        su.setUsername(u.getUsername());
        su.setIntro(u.getIntro());
        su.setImage(u.getUser_avatar());
        su.setNumberOfPost(postMapper.countPostOfUid(u.getId()));
        ssu.add(su);
    }


    /**
     * 获取社团管理后台数据，成功code为1，失败返回一个负数
     * @param aid 社团ID
     * @return 返回一个渲染完成的VO对象
     */
    public AssShowInfoVO getShowInfo(Integer aid){
        // 初始化对象
        AssShowInfoVO assShowInfoVO = new AssShowInfoVO();
        //获取对应社团信息
        Association association = associationMapper.findAssociationById(aid);
        // 初始化社团信息POJO对象
        AssInfoPOJO assInfoPOJO = new AssInfoPOJO();
        // 给社团信息对象注值
            // 获取社团负责人名字
        String associationLeaderName = userMapper.getNameById(association.getLeader_id());
        assInfoPOJO.setName(association.getName());
        assInfoPOJO.setIntro(association.getIntro());
        assInfoPOJO.setPrincipal(associationLeaderName);
        assInfoPOJO.setOrg(association.getParent_organization());
        assInfoPOJO.setProfile(association.getLogo());

        // 获取展示信息POJO
        ShowInfoPOJO showInfoPOJO = new ShowInfoPOJO();
        Integer headCount = associationUserMapper.getTheAssociationMembers(aid);
        Integer postCount = postMapper.getTheAssociationPostNumber(aid);
        Integer actionCount = actionMapper.getToBeHeldActionsNumber(aid, TransformCurrentTimeUtil.returnCurrentTime());
        Association_User finalMember = associationUserMapper.findByAidDescTime(aid);
        Post finalPost = postMapper.findByAidTimeDesc(aid);
        Action nextAction = actionMapper.findByAidNext(aid);
        showInfoPOJO.setHeadcount(headCount);
        showInfoPOJO.setPostcount(postCount);
        showInfoPOJO.setActioncount(actionCount);
        if (finalMember == null)
            showInfoPOJO.setFinalMember("");
        else
            showInfoPOJO.setFinalMember(Common.convertTimestamp2Date(finalMember.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));

        if (finalPost == null)
            showInfoPOJO.setFinalPost("");
        else
            showInfoPOJO.setFinalPost(Common.convertTimestamp2Date(finalPost.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));

        if (nextAction == null)
            showInfoPOJO.setNextAction("");
        else
            showInfoPOJO.setNextAction(nextAction.getTitle());

        //获取成功,返回code 1
        assShowInfoVO.setCode(1);
        assShowInfoVO.setAssInfo(assInfoPOJO);
        assShowInfoVO.setShowInfo(showInfoPOJO);

        return assShowInfoVO;
    }

    /**
     * 获取社团邮件概要,仅获取用户或系统发送给社团的邮件
     * @param aid 社团UID
     * @return 返回社团邮件
     */
    public GetMailOverviewVO getAssMails(Integer aid){
        GetMailOverviewVO gmovvo = new GetMailOverviewVO();
        ArrayList<Mail> mails = mailMapper.getAssMailsByTo_id(aid);
        int mailsNumber = mails.size();
        if (mailsNumber == 0){
            gmovvo.setCode(0);
        }else {
            gmovvo.setCode(mailsNumber);
            MailOverviewPOJO[] mailOverviewPOJOS = new MailOverviewPOJO[mailsNumber];
            for (int i=0;i<mailsNumber;i++){
                mailOverviewPOJOS[i] = new MailOverviewPOJO();
                mailOverviewPOJOS[i].setMid(mails.get(i).getId());
                mailOverviewPOJOS[i].setTitle(mails.get(i).getTitle());
                mailOverviewPOJOS[i].setDate(mails.get(i).getCreate_time().toString());
                mailOverviewPOJOS[i].setIsRead(mails.get(i).getIs_read());
                mailOverviewPOJOS[i].setIsSystem(mails.get(i).getIs_system());
                mailOverviewPOJOS[i].setType(0);
                if(mails.get(i).getIs_system() == 0){
                    String fromUserName = userMapper.getNameById(mails.get(i).getFrom_id());
                    mailOverviewPOJOS[i].setName(fromUserName);
                }else{
                    mailOverviewPOJOS[i].setName("系 统");
                }
            }
            gmovvo.setMail(mailOverviewPOJOS);
        }

        return gmovvo;
    }


    /**
     * 获取社团成员
     * @param aid 社团ID
     * @return 返回视图对象
     */
    public GetUsersVO getMemberInformation(Integer aid){
        GetUsersVO getUsersVO = new GetUsersVO();
        ArrayList<Association_User> association_users = associationUserMapper.findAllMembersByAID(aid);
        int membersNumber = association_users.size();
        if (membersNumber == 0){
            getUsersVO.setCode(0);
            getUsersVO.setMsg("可能该社团没有任何成员");
            getUsersVO.setUser(null);
            return getUsersVO;
        }

        User[] users = new User[membersNumber];
        for (int i=0;i<membersNumber;i++){
            users[i] = userMapper.findById(association_users.get(i).getU_id());
        }
        GetUserPOJO[] getUserPOJOS = new GetUserPOJO[membersNumber];
        // 判断社团会长
        Integer leaderID = associationMapper.findAssociationById(aid).getLeader_id();
        for (int i=0;i<membersNumber;i++) {
            getUserPOJOS[i] = new GetUserPOJO();
            getUserPOJOS[i].setUid(users[i].getId());
            getUserPOJOS[i].setUsername(users[i].getUsername());
            getUserPOJOS[i].setRealname(users[i].getRealname());
            getUserPOJOS[i].setStudentID(users[i].getStudent_number());
            getUserPOJOS[i].setCollege(collegeTableMapper.findCollegeNameById(users[i].getCollege_id()));
            getUserPOJOS[i].setIntro(users[i].getIntro());
            if(users[i].getId() == leaderID){
                getUserPOJOS[i].setPermisson(3);
            }else{
                getUserPOJOS[i].setPermisson(associationUserMapper.findUserIsAdminByUidAid(aid,users[i].getId()));
            }
            getUserPOJOS[i].setPermisson(associationUserMapper.findUserIsAdminByUidAid(aid,users[i].getId()));
            getUserPOJOS[i].setBirth(users[i].getBirth().toString());
        }
        // 排序 管理员在前
        Arrays.sort(getUserPOJOS, new Comparator<GetUserPOJO>() {
            @Override
            public int compare(GetUserPOJO o1, GetUserPOJO o2) {
                if(o1.getPermisson()>o2.getPermisson()) {
                    return -1;
                }
                if (o1.getPermisson() == o1.getPermisson()){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
        getUsersVO.setCode(membersNumber);
        getUsersVO.setMsg("获取社团用户信息成功");
        getUsersVO.setUser(getUserPOJOS);

        return getUsersVO;
    }

    /**
     * 获取全部用户的信息
     * @return 返回视图对象
     */
    public GetUsersVO getAllUserInformation(){
        GetUsersVO getUsersVO = new GetUsersVO();
        ArrayList<User> users = userMapper.findAll();
        int membersNumber = users.size();
        if (membersNumber == 0){
            getUsersVO.setCode(-1);
            getUsersVO.setMsg("获取出错");
            getUsersVO.setUser(null);
            return getUsersVO;
        }

        GetUserPOJO[] getUserPOJOS = new GetUserPOJO[membersNumber];
        for (int i=0;i<membersNumber;i++) {
            getUserPOJOS[i] = new GetUserPOJO();
            getUserPOJOS[i].setUid(users.get(i).getId());
            getUserPOJOS[i].setUsername(users.get(i).getUsername());
            getUserPOJOS[i].setRealname(users.get(i).getRealname());
            getUserPOJOS[i].setStudentID(users.get(i).getStudent_number());
            getUserPOJOS[i].setCollege(collegeTableMapper.findCollegeNameById(users.get(i).getCollege_id()));
            getUserPOJOS[i].setIntro(users.get(i).getIntro());
            getUserPOJOS[i].setPermisson(users.get(i).getLevel());
            getUserPOJOS[i].setBirth(users.get(i).getBirth().toString());
        }
        getUsersVO.setCode(membersNumber);
        getUsersVO.setMsg("获取所有信息成功！");
        getUsersVO.setUser(getUserPOJOS);

        return getUsersVO;
    }


    /**
     * 这里是用户申请加入社团时，社团管理员审批社团成员所调用的接口
     * @author Joolum
     * @param aid 社团ID
     * @return 返回视图
     */
    public GetJoinApplyVO getJoinApplyList(int aid) throws Exception{
        GetJoinApplyVO gjavo = new GetJoinApplyVO();
        ArrayList<Apply_Join_Association> ajass = applyJoinAssociationMapper.getApplyJoinList(aid);
        int ajassNumber = ajass.size();
        if (ajassNumber == 0) {
            gjavo.setCode(0);
            gjavo.setMsg("莫得数据");
            gjavo.setApply(null);
            return gjavo;
        }

        User[] users = new User[ajassNumber];
        for (int i=0;i<ajassNumber;i++){
            users[i] = userMapper.findById(ajass.get(i).getU_id());
            if (users[i] == null){
                logger.warn("找不到该用户！");
                return null;
            }
        }
        JoinApplyPOJO[] joinApplyPOJOs = new JoinApplyPOJO[ajassNumber];
        for (int i=0;i<ajassNumber;i++){
            joinApplyPOJOs[i] = new JoinApplyPOJO();
            joinApplyPOJOs[i].setUid(users[i].getId());
            joinApplyPOJOs[i].setUaid(ajass.get(i).getId());
            joinApplyPOJOs[i].setUserName(users[i].getUsername());
            joinApplyPOJOs[i].setRealName(users[i].getRealname());
            joinApplyPOJOs[i].setStudentID(users[i].getStudent_number());
            joinApplyPOJOs[i].setCollege(collegeTableMapper.findCollegeNameById(users[i].getCollege_id()));
            joinApplyPOJOs[i].setNote(ajass.get(i).getNote());
            joinApplyPOJOs[i].setStatus(ajass.get(i).getIs_approved());
            if(ajass.get(i).getIs_approved() == 0){
                joinApplyPOJOs[i].setTime(Common.convertTimestamp2Date(ajass.get(i).getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            }else{
                joinApplyPOJOs[i].setTime(Common.convertTimestamp2Date(ajass.get(i).getUpdate_time(),"yyyy-MM-dd HH:mm:ss"));
            }
        }
        gjavo.setCode(joinApplyPOJOs.length);
        gjavo.setMsg("获取社团申请表用户信息成功");
        gjavo.setApply(joinApplyPOJOs);

        return gjavo;
    }


    /**
     * 获取社团的审批列表,该审批是社团向学校提交的审批
     * @param aid 社团ID
     * @return 返回一个渲染好的视图
     */
    public GetAssApplyVO getAssociationListApplyList(Integer aid){
        GetAssApplyVO gaavo = new GetAssApplyVO();
        ArrayList<Apply_Commond> apply_commonds = applyCommondMapper.getAssociationApplyList(aid);
        int acNumber = apply_commonds.size();
        gaavo.setCode(acNumber);
        AssApplyPOJO[] assApplyPOJOS = new AssApplyPOJO[acNumber];
        for (int i=0;i<acNumber;i++){
            assApplyPOJOS[i] = new AssApplyPOJO();
            assApplyPOJOS[i].setAaid(apply_commonds.get(i).getId());
            assApplyPOJOS[i].setStatus(apply_commonds.get(i).getIs_approved());
            assApplyPOJOS[i].setTitle(apply_commonds.get(i).getTitle());
            assApplyPOJOS[i].setContent_id(apply_commonds.get(i).getContent_id());
            assApplyPOJOS[i].setDate(apply_commonds.get(i).getCreate_time().toString());
        }
        gaavo.setApply(assApplyPOJOS);
        gaavo.setMsg("你所热爱的，就是你的生活");

        return gaavo;
    }

    /**
     * 获取社团活动列表，包括审核了和未审核的活动
     * @param aid  社团ID
     * @return 视图对象
     */
    public GetActionListVO getActionList(Integer aid){
        GetActionListVO galco = new GetActionListVO();
        ArrayList<Action> actions = actionMapper.findByA_id(aid);
        Integer actionNumber = actions.size();

        galco.setCode(actionNumber);
        ActionDataPOJO[] actionDataPOJOS = new ActionDataPOJO[actionNumber];
        for (int i=0;i<actionNumber;i++){
            actionDataPOJOS[i] = new ActionDataPOJO();
            String assName = associationMapper.getAssociationNameById(actions.get(i).getA_id());
            String content = contentMapper.findContentById(actions.get(i).getContent_id());
            actionDataPOJOS[i].setActid(actions.get(i).getId());
            actionDataPOJOS[i].setAid(actions.get(i).getA_id());
            actionDataPOJOS[i].setAssname(assName);
            actionDataPOJOS[i].setTitle(actions.get(i).getTitle());
            actionDataPOJOS[i].setContent(content);
            actionDataPOJOS[i].setStartTime(actions.get(i).getStart_time().toString());
            actionDataPOJOS[i].setEndTime(actions.get(i).getEnd_time().toString());
            actionDataPOJOS[i].setStatus(actions.get(i).getIs_approved());
        }
        galco.setAction(actionDataPOJOS);
        galco.setMsg("获取社团活动列表成功！");
        return galco;
    }

    /**
     * 获取所有的社团活动列表，包括审核了和未审核的活动
     * @return 视图对象
     */
    public GetActionListVO getActionList(){
        GetActionListVO galco = new GetActionListVO();
        ArrayList<Action> actions = actionMapper.findAll();
        Integer actionNumber = actions.size();

        galco.setCode(actionNumber);
        ActionDataPOJO[] actionDataPOJOS = new ActionDataPOJO[actionNumber];
        for (int i=0;i<actionNumber;i++){
            actionDataPOJOS[i] = new ActionDataPOJO();
            String assName = associationMapper.getAssociationNameById(actions.get(i).getA_id());
            String content = contentMapper.findContentById(actions.get(i).getContent_id());
            actionDataPOJOS[i].setActid(actions.get(i).getId());
            actionDataPOJOS[i].setAid(actions.get(i).getA_id());
            actionDataPOJOS[i].setAssname(assName);
            actionDataPOJOS[i].setTitle(actions.get(i).getTitle());
            actionDataPOJOS[i].setContent(content);
            actionDataPOJOS[i].setStartTime(actions.get(i).getStart_time().toString());
            actionDataPOJOS[i].setEndTime(actions.get(i).getEnd_time().toString());
            actionDataPOJOS[i].setStatus(actions.get(i).getIs_approved());
        }
        galco.setAction(actionDataPOJOS);
        galco.setMsg("获取社团活动列表成功！");
        return galco;
    }

    /**
     * 用户可以通过该业务，以一个cid获取内容
     * @param cid 内容ID
     * @return 返回一个内容对象
     */
    public Content getContentById(int cid){
        Content c = contentMapper.getContentByID(cid);
        return c;
    }

    /**
     * 获取活动参加的人员列表
     * @param aid  要获取的社团ID
     * @return 返回一个视图对象
     */
    public ActionMemberVO getActionMember(Integer aid){
        ActionMemberVO amvo = new ActionMemberVO();

        ArrayList<Join_Action> join_actions = joinActionMapper.getActionPersonNumber(aid);
        Integer personNumber = join_actions.size();
        amvo.setCode(personNumber);
        User[] users = new User[personNumber];
        for (int i = 0; i < users.length; i++) {
            users[i] = userMapper.findById(join_actions.get(i).getU_id());
        }
        amvo.setAction_member(users);
        amvo.setMsg("获取活动人员信息成功!");
        return amvo;
    }


    /**
     * 该接口会返回个人活跃度前10的用户的活跃度信息。
     * @param aid 社团ID
     * @return 返回社团活跃度排名前几的用户和活跃度信息
     */
    public DataViewPOJO[] getAssPersonAct(int aid){
        // 获取社团的所有成员
        ArrayList<Association_User> users_id = associationUserMapper.findAllMembersByAID(aid);
        System.out.println();
        if (users_id == null || users_id.size() == 0) return null;

        // 定义当前和七天前的时间戳
        Calendar nowTime =Calendar.getInstance();
        nowTime.setTime(new Date());
        nowTime.set(Calendar.DATE,nowTime.get(Calendar.DATE)-7);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp week_before = new Timestamp(nowTime.getTimeInMillis());

        // 遍历计算所有的社团成员的活跃度
        DataViewPOJO[] pa = new DataViewPOJO[users_id.size()];
        for (int i = 0; i < users_id.size(); i++) {
            // 获得用户信息
            User user = userMapper.findById(users_id.get(i).getU_id());
            // 获得用户发帖和评论数据
            ArrayList<Post> post = postMapper.findByUidAid(users_id.get(i).getU_id(),aid);
            ArrayList<Comments> comments = new ArrayList<>();
            ArrayList<Post> post_temp = postMapper.findByAid(aid);
            for (Post p : post_temp) {
                ArrayList<Comments> c = commentsMapper.findByPidUid(
                        p.getId(),
                        user.getId()
                );
                if(c == null || c.size()==0) continue;
                comments.addAll(c);
            }
            // 七天内收藏总数
            ArrayList<Favorite_Table> favorite_tables = favoriteTableMapper.getAllFavoriteTableByU_idAfter(users_id.get(i).getU_id(),week_before);
            // 七天内论坛发帖总数
            ArrayList<Post> assPost = postMapper.findByAidDate(aid,week_before);

            pa[i] = new DataViewPOJO();
            pa[i].setId(users_id.get(i).getU_id());
            pa[i].setName(user.getUsername());
            int userPost = 0;
            int userComment = 0;
            int userFavorite = 0;
            int assPostNum = assPost.size();
            // 计算七天内用户发帖数量
            for (Post p : post) {
                if (p.getCreate_time().before(week_before)) continue;
                userPost++;
            }
            // 计算七天内用户的发布评论数量
            for (Comments c : comments) {
                if(c.getCreate_time().before(week_before)) continue;
                userComment++;
            }

            // 进行一个法的算
            // TODO: 2021/11/30 修改个人活跃度算法 
            float x = userPost * 0.5f + userComment * 0.4f + userFavorite * 0.1f;
            pa[i].setNum(x);

        }
        return pa;
        // 对其进行排序

        // 截断返回

    }


    /**
     * 获取社团每天的活跃度
     * 初版算法：以每日发帖量计算
     * @param aid 社团ID
     * @return 返回数据数组
     */
    public DailyViewPOJO[] getDailyAct(int aid){
        final long ms = 1000 * 60 * 60 * 24;

        // 获取七天分别的数据
        DailyViewPOJO[] days = new DailyViewPOJO[15];
        for (int i = 0; i <15; i++) {
            Date today = new Date(System.currentTimeMillis() - ms * i);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            days[i] = new DailyViewPOJO();
            days[i].setNum(postMapper.countByDate(i) / 1.2f);
            days[i].setDate(formatter.format(today));
        }
        return days;
    }


    /**
     * 通过社团的ID可以查询到社团的详细信息
     * @param id 社团ID，是一个AID
     * @return 返回一个社团表对象
     */
    public Association getAssociationInfoById(Integer id){
        return associationMapper.findAssociationById(id);
    }

    /**
     * 通过关键词查询用户，可以返回用户信息列表
     * 关键字类型如下：
     * 昵称、真实姓名
     * 学号、手机号
     * @param keyword 关键字
     * @param uid 需要排除的UID
     * @return 返回去重后的结果
     */
    public Set<FindUserKeywordPOJO> findUserKeyword(String keyword,int uid){
        // 昵称模糊搜索  真实姓名模糊搜索 简介模糊搜索
        ArrayList<User> userA = userMapper.findLikeKey(keyword);
        // 学号精准搜素
        Integer userB_id = userMapper.findIdByNumber(keyword);
        User userB = null;
        if (userB_id != null){
            userB = userMapper.findById(userB_id);
        }
        // 手机号精准搜索
        Integer userC_id = userMapper.findIdByPhone(keyword);
        User userC = null;
        if (userC_id != null) {
            userC = userMapper.findById(userC_id);
        }
        // 插入数组，精准搜索结果在前，模糊搜索结果在后，去重操作
        Set<FindUserKeywordPOJO> setObject = new HashSet<>();
        // 插入手机
        if(userC_id != null){
            if (userC.getId() != uid){
                setObject.add(new FindUserKeywordPOJO(userC.getId(),userC.getUsername(),userC.getStudent_number(),userC.getRealname()));
            }
        }
        // 插入学号
        if(userB_id != null){
            if(userB.getId() != uid){
                setObject.add(new FindUserKeywordPOJO(userB.getId(),userB.getUsername(),userB.getStudent_number(),userB.getRealname()));
            }
        }
        //插入模糊搜索
        for (User user : userA) {
            setObject.add(new FindUserKeywordPOJO(user.getId(),user.getUsername(),user.getStudent_number(),user.getRealname()));
        }
        return setObject;
    }

    /**
     * 获取某社团的所有管理员信息
     * @param aid 社团ID
     * @return 返回一个数组
     */
    public ArrayList<FindUserKeywordPOJO> findAssAdmin(int aid){
        ArrayList<Association_User> aus = associationUserMapper.findByAidAdmin(aid);
        if (aus == null) {
            return null;
        }
        ArrayList<FindUserKeywordPOJO> find = new ArrayList<>();
        for (Association_User au : aus) {
            User user = userMapper.findById(au.getU_id());
            find.add(new FindUserKeywordPOJO(user.getId(),user.getUsername(),user.getStudent_number(),user.getRealname()));
        }
        return find;
    }

    public ArrayList<Association> getAllAss(){
        return associationMapper.findAllAssociation();
    }


    /**
     * 获取所有的创建社团列表
     * @return 对象数组
     */
    public ArrayList<CreateAssPOJO> getCreateList(){
        ArrayList<CreateAssPOJO> cas = new ArrayList<>();
        // 获取所有审核情况
        ArrayList<Apply_Create> ac = applyCreateMapper.findAll();
        for (Apply_Create apply_create : ac) {
            CreateAssPOJO ca = new CreateAssPOJO();
            ca.setCaid(apply_create.getId());
            ca.setName(apply_create.getName());
            ca.setIntro(apply_create.getIntro());
            ca.setNote(apply_create.getNote());
            ca.setUid(apply_create.getU_id());
            User user = userMapper.findById(apply_create.getU_id());
            if(user == null) {
                logger.error("找不到对应用户，数据库出大问题！出错UID："+apply_create.getU_id());
                continue;
            }
            ca.setRealname(user.getRealname());
            ca.setStudentID(user.getStudent_number());
            ca.setStatus(apply_create.getIs_approved());
            cas.add(ca);
        }
        return cas;
    }

    public Apply_Create getApply_createById(int id){
        return applyCreateMapper.findById(id);
    }

    public User getUserById(int id){
        return userMapper.findById(id);
    }






    // -----验证操作-----


    /**
     * 判断一个CAID是否已经呗审批过来
     * 若被审批过则无法再次审批
     * @param caid caid
     * @return 布尔值
     */
    public boolean judgeCAIDIsApproved(int caid){
        Apply_Create ac = applyCreateMapper.findById(caid);
        if(ac == null || ac.getIs_approved() == 0){
            return true;
        }else{
            return false;
        }
    }


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


    /**
     * 验证用户对于空间的权限
     * @author Joolum
     * @param muid 空间主人的UID
     * @param request 请求，用以获取Token
     * @return 返回一个整数，0->表示自己空间，1->可访问空间，2->不可访问空间,负数 -> 错误
     */
    public int verifyUserZoneStatus(Integer muid, HttpServletRequest request){
        // 判断用户是否归属当前空间
        boolean key = verifyZoneStatus(request,muid);
        if (key){
            return 0;
        }else {
            Integer code = userMapper.isOpenZone(muid);
            if(code == null) return -2; //获取用户空间权限失败，可能不存在该用户
            if(code == 1){ //访问
                return 1;
            }else if(code == 0){
                return 2;
            }else{
                return code;//数据库出错
            }
        }
    }

    /**
     * 对用户进行一个断的判
     *  1. 查询活动所在的社团
     *  2. 查询用户是否加入该社团
     * @param actid  传入活动ID
     * @param uid    传入用户ID
     * @return 返回一个布尔值，告知是否通过验证
     */
    public boolean verifyUserJoinAssWithAction(Integer actid,Integer uid){
        Action action = actionMapper.getDetailedAssActionByAcId(actid);
        Integer act_aid  = action.getA_id();
        return userIsJoinAss(act_aid,uid);
    }

    /**
     * 判断用户是否收藏了某个帖子
     * @param pid 帖子ID
     * @param uid 用户ID
     * @return 返回当前用户收藏状态
     */
    public boolean judgeUserFavoriteStatus(int pid,int uid){
        Integer status = favoriteTableMapper.isFavorite(pid,uid);
        return status == 1;
    }

    /**
     * 验证一个用户是否存在，判断其合法性
     * @param uid 要验证的用户ID
     * @return 返回一个状态
     */
    public boolean verifyUserExist(int uid){
        User code = userMapper.findById(uid);
        return code != null;
    }
    /**
     * 验证一个社团aid是否存在，判断其合法性
     * @param aid 要验证的用户ID
     * @return 返回一个状态
     */
    public boolean verifyAssExist(int aid){
        Integer code = associationMapper.findAssIsExist(aid);
        return code == 1;
    }

    /**
     * 判断某用户是否是社团管理员
     * @param uid 用户ID
     * @param aid 社团ID
     * @return 返回布尔值
     */
    public boolean verifyUserIsAssAdmin(int uid,int aid){
        return associationUserMapper.findUserIsAdminByUidAid(aid,uid) == 1;

    }

    /**
     * 验证UAID是否合法，是否存在该id
     * @param uaid 社团加入申请ID
     * @return 布尔值
     */
    public boolean verifyUaidExist(int uaid){
        return applyJoinAssociationMapper.findByID(uaid) != null;
    }


    /**
     * 判断UAID所指向的内容和否合法,以及用户权限
     * @param uaid 社团加入申请ID
     * @param uid  操作该接口的用户UID
     * @return 布尔值
     */
    public boolean verifyUaidValidity(int uaid,int uid){
        Apply_Join_Association info = applyJoinAssociationMapper.findByID(uaid);
        Integer status = associationUserMapper.findUserIsAdminByUidAid(info.getA_id(),uid);
        if( status == null || status == 0 ){
            logger.error("该登录用户不是管理员，无法操作");
            return false;
        }

        Association_User au = associationUserMapper.findAssociation_userById(info.getU_id(),info.getA_id());
        if(au != null) {
            logger.error("该用户已经加入社团，无法再次加入");
            if(info.getIs_approved() != 1){
                applyJoinAssociationMapper.setJoinApplyStatus(1,uaid);
            }
            return false;
        }
        return true;
    }

    /**
     * 判断用户是否提交过申请入社表单，当用户目前有一个正在审批的申请则无法提交
     * @param uid 用户ID
     * @param aid 社团ID
     * @return 布尔值,有则返回真，无则返回假
     */
    public boolean verifyUserIsSubmitApprove(int uid,int aid){
        Integer x = applyJoinAssociationMapper.countApproveNumberByIDID(aid,uid);
        return x == 1;
    }

}

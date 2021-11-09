package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.Association;
import icu.rolin.easy.model.DO.Association_User;
import icu.rolin.easy.model.DO.Comments;
import icu.rolin.easy.model.DO.Post;
import icu.rolin.easy.model.PO.GetPostsPO;
import icu.rolin.easy.model.PO.ReleasePostPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.POJO.GetAssInfoAssPOJO;
import icu.rolin.easy.model.POJO.PostsPOJO;
import icu.rolin.easy.model.POJO.ShowDataAssPOJO;
import icu.rolin.easy.model.POJO.SimpleNoticePOJO;
import icu.rolin.easy.model.VO.SimpleNoticeVO;
import icu.rolin.easy.utils.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BBSService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private AssociationUserMapper associationUserMapper;
    @Autowired
    private AssociationMapper associationMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContentMapper contentMapper;
    private final static Logger logger = LoggerFactory.getLogger(BBSService.class);

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
            simpleNoticePOJOS[i].setDate(common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd"));
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
            ps[i].setPostType(common.POST_TYPE.get(posts.get(i).getPost_type()));
            ps[i].setPostTitle(posts.get(i).getTitle());
            ps[i].setPostAuthor(userMapper.getNameById(posts.get(i).getU_id()));
            Comments last = commentsMapper.getLastCommentWithPost(id);
            if (last != null)
                ps[i].setReplyTime(common.convertTimestamp2Date(last.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            else
                ps[i].setReplyTime(common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
            ps[i].setReleaseTime(common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd"));
            ps[i].setReplies(commentsMapper.countCommentsByPid(id));
        }
        //排序
        Arrays.sort(ps, new Comparator<PostsPOJO>() {
            @Override
            public int compare(PostsPOJO o1, PostsPOJO o2) {
                Long o1Stamp = common.date2Stamp(o1.getReplyTime(),"yyyy-MM-dd HH:mm:ss");
                Long o2Stamp = common.date2Stamp(o2.getReplyTime(),"yyyy-MM-dd HH:mm:ss");
                Long res = o1Stamp - o2Stamp;
                if (res > 0)    return 1;
                else if (res < 0)   return -1;
                return 0;
            }
        });
        return ps;

    };


    /**
     * @param rppo 传入一个参数对象
     * @author :JooLum
     * @update-time：2021年11月9日 10点24分
     * update-content:
     *  - 增加事务管理，若插入失败则整个事务回滚
     * @return 返回一个Map键值对，Key-pid：返回的pid，Key-status：返回是否正确处理事务
     */
    @Transactional
    public Map<String,Integer> publishPost(ReleasePostPO rppo){
        // 新建返回对象
        Map<String,Integer> result = new HashMap<String,Integer>();
        //插入内容
        Integer contentCode = contentMapper.savePost_content(rppo.getContent());
        if (contentCode != 1) { //验证插入内容是否成功
            logger.error("前端发帖请求，缺失相关发送数据...");
            result.put("status",0);
            return result;
        }
        // 获取最新插入内容ID
        Integer contentId = contentMapper.getTheLatestID();
        //获取帖子的类型INT
        Integer postType = common.getKey(common.POST_TYPE,rppo.getPostType());
        if (postType == null) postType = 5;
        //进行一个数据的插入
        Integer postCode = postMapper.savePost(
                rppo.getReleaseArea(),
                rppo.getUid(),
                rppo.getPostTitle(),
                contentId,
                rppo.getTags(),
                postType);
        if (postCode == null || postCode == 0){
            logger.warn("帖子没有成功写入数据库!");
            result.put("status",0);
        }
        result.put("pid",postMapper.getTheLatestId());
        result.put("status",1);
        return result;
    }

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

}

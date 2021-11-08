package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.Association;
import icu.rolin.easy.model.DO.Association_User;
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

import java.util.ArrayList;

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
     * @author :JooLum
     * 可能还存在些许的关联问题，辛苦检查
     */
    public PostsPOJO[] showPosts(GetPostsPO gppo){
        ArrayList<Post> posts = postMapper.findPostsByA_id(gppo.getAid(),gppo.getType());
        if (posts.size() == 0) return null;
        PostsPOJO[] postsPOJOS = new PostsPOJO[posts.size()];
        for (int i=0;i<posts.size();i++){
            postsPOJOS[i].setPid(posts.get(i).getId());
            postsPOJOS[i].setPostType(posts.get(i).getPost_type());
            postsPOJOS[i].setPostTitle(posts.get(i).getTitle());
            postsPOJOS[i].setPostAuthor(userMapper.getNameById(posts.get(i).getU_id()));
            postsPOJOS[i].setReplies(commentsMapper.getCommentQuantitesByP_id(posts.get(i).getId()));
            postsPOJOS[i].setReplyTime(commentsMapper.getTheLatestComment().toString());
            postsPOJOS[i].setReleaseTime(posts.get(i).getCreate_time().toString());
        }
        return postsPOJOS;
    }

    /**
     * @author :JooLum
     * 可能还存在些许的关联问题，辛苦检查
     * 用contentCode来验证是否成功写入内容表
     * contentId接收刚插入内容表的最新id
     * postType接收请求帖子的类型
     * 用postCode来验证是否成功插入贴子表
     */
    public boolean publishPost(ReleasePostPO rppo){
        Integer contentCode = contentMapper.insertPost_content(rppo.getContent());
        Integer contentId = contentMapper.getTheLatestID();
        int postType = common.transPostType(rppo.getPostType());
        if (contentCode == null || contentId == null || postType == 2 ) {
            logger.error("前端发帖请求，缺失相关发送数据...");
            return false;
        }
        Integer postCode = postMapper.insertPost(rppo.getReleaseArea(), rppo.getUid(), rppo.getPostTitle(), contentId, rppo.getTags(), postType);
        if (postCode == null){
            logger.warn("帖子没有成功写入数据库!");
            postCode = 0;
        }
        return postCode == 1;
    }

    /**
     * @author :JooLum
     * 可能还存在些许的关联问题，辛苦检查
     * UserAssNotePO中的Note参数没有用上
     */
    public GetAssInfoAssPOJO getAssInformation(UserAssNotePO ua){
        Association association = associationMapper.findAssociationById(ua.getAid());
        if (association == null) {
            logger.error("并不存在此社团，请检查数据库数据是否出错！");
            association = null;
        }
        String assAdministrator = userMapper.getNameById(association.getLeader_id());
        if (assAdministrator == null){
            logger.error("无此社团管理员，请检查数据库数据是否出错！");
            assAdministrator = "Lose Administrator";
        }
        GetAssInfoAssPOJO getAssInfoAssPOJO = new GetAssInfoAssPOJO(association.getName(), association.getIntro(), association.getLogo(), association.getParent_organization(), assAdministrator);
        return getAssInfoAssPOJO;
    }


    // 返回插入数据的最新id字段值
    public Integer getTheLatestP_id(){return postMapper.getTheLatestId();}

}

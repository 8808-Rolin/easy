package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;

/**
 *
 */
@Service
public class DeleteService {
    private final static Logger logger = LoggerFactory.getLogger(DeleteService.class);
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

    /**
     * 删除帖子业务操作，同时要删除所有评论以及内容表内容
     * 防止表的内容不一，采用事务回滚的方式控制
     * @param pid 传入一个帖子ID
     * @return 返回一个SimpleVO
     */
    @Transactional(rollbackFor = Exception.class)
    public SimpleVO deletePost (int pid){
        try{
            //通过主帖获得内容ID以及所有的评论ID
            Integer content_id = postMapper.findContentById(pid);
            ArrayList<Integer> comments_id = commentsMapper.findIdByPid(pid);
            //对附表进行一个删除 (内容)
            contentMapper.deleteById(content_id);
            //对附表进行一个删除 (评论)
            for (Integer cid : comments_id) {
                commentsMapper.deleteCommentById(cid);
            }
            // 对帖子主表进行一个删除
            postMapper.deletePostById(pid);

            return new SimpleVO(0,"删除操作成功");
        }catch (Exception e){
            e.printStackTrace();
            //手动强制回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new SimpleVO(1,"删除帖子失败，事务回滚了，请重试");
        }
    }

    /**
     * 删除帖子下对应的评论
     * @param cid 传入一个评论ID
     * @return 返回一个SimpleVO
     */
    public SimpleVO deleteComment(int cid){
        try{
            commentsMapper.deleteCommentById(cid);
            return new SimpleVO(0,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new SimpleVO(1,"删除回复失败");
        }
    }



    public SimpleVO removeMember(UserAssNotePO ua){
        SimpleVO simpleVO = new SimpleVO();
        if (ua.getUid() == null || ua.getAid() == null){
            simpleVO.setMsg("请求参数丢失");
            simpleVO.setCode(-1);
            logger.error("清除邮箱---请求参数丢失...");
        }else {
            Integer code = associationUserMapper.deleteUserByAidUid(ua.getAid(), ua.getUid());
            if (code != 0){
                simpleVO.setMsg("成功删除社团用户");
                simpleVO.setCode(code);
            }else {
                simpleVO.setCode(-1);
                simpleVO.setMsg("删除社团用户失败");
            }
        }

        return simpleVO;
    }


    /**
     * 移除用户的收藏帖子，对收藏表的数据进行一个删除
     * @param uid 用户ID
     * @param pid 帖子ID
     * @return 返回一个布尔值，表示操作是否成功
     */
    public boolean removeFavorite(int uid,int pid){
        Integer code = favoriteTableMapper.deleteCollectPost(pid,uid);
        return code == 1;
    }

}

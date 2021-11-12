package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.VO.SimpleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //3.4.5 删除帖子以及回复
    public SimpleVO deletePostDiscuss(Integer requestType,Integer typeid){
        SimpleVO simpleVO = new SimpleVO();
        if (requestType == null || typeid == null){
            simpleVO.setMsg("请求参数丢失");
            simpleVO.setCode(-1);
            logger.error("删除帖子或者评论---请求参数丢失...");
        }else {
            if (requestType == 0){
                Integer code = postMapper.deletePostById(typeid);
                if (code == 0){
                    simpleVO.setMsg("删除帖子失败");
                    simpleVO.setCode(-1);
                    logger.error("删除帖子---删除失败，可能并没有这个帖子...");
                }else {
                    simpleVO.setMsg("删除帖子成功");
                    simpleVO.setCode(0);
                }
            }else {
                Integer code = commentsMapper.deleteCommentById(typeid);
                if (code == 0){
                    simpleVO.setMsg("删除评论失败");
                    simpleVO.setCode(-1);
                    logger.error("删除评论---删除失败，可能并没有这个评论...");
                }else {
                    simpleVO.setMsg("删除评论成功");
                    simpleVO.setCode(0);
                }
            }
        }

        return simpleVO;
    }

}

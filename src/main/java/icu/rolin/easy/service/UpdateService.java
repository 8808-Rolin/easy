package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.PO.ForgetPasswordPO;
import icu.rolin.easy.model.PO.PostUserPO;
import icu.rolin.easy.model.PO.UpdatePostPO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    private final static Logger logger = LoggerFactory.getLogger(UpdateService.class);
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
     * 当用户忘记密码时，调用该业务为用户修改密码
     * @param fp 传入一个对象参数
     * @return 返回一个布尔值，告知是否修改成功
     */
    public boolean userForgetPassword(ForgetPasswordPO fp){
        Integer code = userMapper.updatePassword(fp.getPassword(), fp.getStudentID(), fp.getPhone(), fp.getEmail());
        if (code == null){
            logger.warn("忘记密码功能出错，null");
            code = 0;
        }
        return code == 1;
    }

    //3.4.6 修改帖子
    public SimpleVO modifyPost(UpdatePostPO up){
        SimpleVO simpleVO = new SimpleVO();
        if (up.getUid() == null || up.getPid() == null){
            simpleVO.setMsg("请求参数丢失");
            simpleVO.setCode(-1);
            logger.error("修改帖子---请求参数丢失...");
        }else {
            Integer code = postMapper.updatePost(up.getNewContent(), up.getPid(), up.getUid());
            if (code == 0){
                simpleVO.setMsg("修改帖子失败");
                simpleVO.setCode(-1);
            }else {
                simpleVO.setMsg("修改帖子成功");
                simpleVO.setCode(0);
            }
        }

        return simpleVO;
    }

}

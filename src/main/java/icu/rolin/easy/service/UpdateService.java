package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.PO.*;
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


    /**
     * 修改帖子内容时调用的方法，验证用户身份
     * 直接修改内容ID所对应的内容即可
     * @param up 参数对象
     * @return 返回一个SimpleVO
     */
    public SimpleVO modifyPost(UpdatePostPO up){
        SimpleVO simpleVO = new SimpleVO();
        //验证用户身份
        Integer is = postMapper.findMatchWithPidUid(up.getUid(),up.getPid());
        if(is == 0){//用户权限不足，无法进行修改
            simpleVO.setMsg("用户权限不足，无法修改");
            simpleVO.setCode(-1);
            return simpleVO;
        }
        //  获取内容ID
        Integer content_id = postMapper.findContentById(up.getPid());
        //  修改内容ID
        Integer res = contentMapper.updateContentById(content_id,up.getNewContent());
        //  返回
        if (res == 0){
            simpleVO.setMsg("修改帖子失败");
            simpleVO.setCode(-1);
        }else {
            simpleVO.setMsg("修改帖子成功");
            simpleVO.setCode(0);
        }


        return simpleVO;
    }

    public SimpleVO modifyNotice(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserNotice(up.getNewProfile(),up.getUid());
        if(code == 1){
            simpleVO.setCode(0);
            simpleVO.setMsg("修改成功！");
        }else{
            simpleVO.setCode(1);
            simpleVO.setMsg("修改失败！");
        }
        return simpleVO;
    }

    public SimpleVO updateUserName(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserName(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户修改用户名失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("用户无法修改新用户名");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("用户成功修改新的用户名！");
        }

        return simpleVO;
    }

    public SimpleVO updateUserIntro(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserIntro(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户修改简介失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("用户无法修改新的简介");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("用户成功修改新的简介！");
        }

        return simpleVO;
    }

    public SimpleVO updateUserHeadImage(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserHeadImage(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户更换头像失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("用户无法更换头像");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("用户头像更换成功！");
        }

        return simpleVO;
    }

    public SimpleVO updateUserEmail(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserEmail(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户更换邮箱失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("用户无法修改邮箱");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("用户邮箱修改成功！");
        }

        return simpleVO;
    }

    public SimpleVO updateUserBirth(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserBirth(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户更换生日失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("用户无法修改生日");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("用户生日修改成功！");
        }

        return simpleVO;
    }

    public SimpleVO setJoinApplyStatus(Integer type,Integer uaid){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = applyJoinAssociationMapper.setJoinApplyStatus(type,uaid);
        if (code == 0){
            logger.warn("修改申请状态失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("无法修改申请状态");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("修改成功！");
        }

        return simpleVO;
    }

    public SimpleVO updateAssociationInfo(AssInfoUpdatePO aiu){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = associationMapper.updateAssociationInfo(aiu.getName(), aiu.getIntro(), aiu.getLogo(), aiu.getHeaderuid(), aiu.getAid());
        if (code == 0){
            logger.warn("修改社团基本信息失败！");
            simpleVO.setCode(-1);
            simpleVO.setMsg("无法修改社团信息");
        }else {
            simpleVO.setCode(1);
            simpleVO.setMsg("修改成功！");
        }

        return simpleVO;
    }


    /**
     * 清空邮箱，实质上是将is_read字段修改为2，即不可见
     * @param uid 用户UID
     * @return 视图对象
     */
    public SimpleVO deleteMail(Integer uid){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = mailMapper.emptyMailById(uid);
        simpleVO.setMsg("成功清除邮箱");
        simpleVO.setCode(code);
        return simpleVO;
    }

}

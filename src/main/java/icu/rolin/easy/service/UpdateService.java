package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.Apply_Join_Association;
import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.VO.SimpleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Autowired
    IncreaseService is;


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

    /**
     * 用户修改用户名称时调用该业务方法
     * @param up 参数对象
     * @return 响应对象SimpleVO
     */
    public SimpleVO modifyUserName(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserName(up.getNewProfile(), up.getUid());
        simpleVO.setCode(0);
        simpleVO.setMsg("用户成功修改新的用户名！");
        return simpleVO;
    }

    /**
     * 用户修改用户简介时调用该业务方法
     * @param up 参数对象
     * @return 响应对象SimpleVO
     */
    public SimpleVO updateUserIntro(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserIntro(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户修改简介失败！");
            simpleVO.setCode(1);
            simpleVO.setMsg("用户无法修改新的简介");
        }else {
            simpleVO.setCode(0);
            simpleVO.setMsg("用户成功修改新的简介！");
        }
        return simpleVO;
    }

    /**
     * 用户修改头像时调用该业务方法
     * @param up 参数对象
     * @return 响应对象SimpleVO
     */
    public SimpleVO updateUserHeadImage(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserHeadImage(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户更换头像失败！");
            simpleVO.setCode(1);
            simpleVO.setMsg("更换头像失败，请重试");
        }else {
            simpleVO.setCode(0);
            simpleVO.setMsg("用户头像更换成功！");
        }
        return simpleVO;
    }

    /**
     * 用户修改Email时调用该业务方法
     * @param up 参数对象
     * @return 响应对象SimpleVO
     */
    public SimpleVO updateUserEmail(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserEmail(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户更换邮箱失败！");
            simpleVO.setCode(1);
            simpleVO.setMsg("用户无法修改邮箱");
        }else {
            simpleVO.setCode(0);
            simpleVO.setMsg("用户邮箱修改成功！");
        }

        return simpleVO;
    }

    /**
     * 用户修改生日时调用该业务方法
     * @param up 参数对象
     * @return 响应对象SimpleVO
     */
    public SimpleVO updateUserBirth(UidProfilePO up){
        SimpleVO simpleVO = new SimpleVO();
        Integer code = userMapper.updateUserBirth(up.getNewProfile(), up.getUid());
        if (code == 0){
            logger.warn("用户更换生日失败！");
            simpleVO.setCode(1);
            simpleVO.setMsg("修改生日失败，请重试");
        }else {
            simpleVO.setCode(0);
            simpleVO.setMsg("用户生日修改成功！");
        }
        return simpleVO;
    }

    /**
     * 需要事务回滚，若申请通过则将用户成为该社团的成员，若申请不通过，则数据库对应字段值应为-1
     * @param type 类型 0：不通过，1：通过
     * @param uaid 申请ID
     * @return 返回渲染视图
     */
    @Transactional(rollbackFor = Exception.class)
    public SimpleVO setJoinApplyStatus(int type,int uaid){
        Apply_Join_Association aj = applyJoinAssociationMapper.findByID(uaid);
        if (aj == null) {
            return new SimpleVO(-8,"不存在这个UAID");
        }
        SimpleVO simpleVO = new SimpleVO();
        Integer status = -1;

        // 起稿邮件
        SendMailPO sm = new SendMailPO();
        sm.setIsSystem(1);
        sm.setFromuid(null);
        sm.setTouid(aj.getU_id());
        sm.setTitle("申请加入社团结果通知");
        sm.setMailType(0);

        try{
            switch (type){
                case 0:
                    // 拒绝申请
                    status = applyJoinAssociationMapper.setJoinApplyStatus(2,uaid);
                    if(status == 1){
                        logger.info("拒绝了学生的入社申请");
                        sm.setContent("很遗憾的告诉你，你申请加入的《"+associationMapper.findAssociationById(aj.getA_id()).getName()+"社团》\n申请" +
                                "没有通过，你可以重新发起申请审批，继续完善你的申请备注，谢谢！");
                        is.sendEmailWithSystem(sm);
                    }
                    break;
                case 1:
                    // 通过申请
                    status = applyJoinAssociationMapper.setJoinApplyStatus(1,uaid);
                    if(status == 0){
                        logger.info("通过学生的入社申请ERROR");
                        return new SimpleVO(-9,"通过学生的入社申请ERROR");
                    }else{
                        status = associationUserMapper.insertUserToAss(aj.getA_id(),aj.getU_id());
                        sm.setContent("很开心的告诉你，你申请加入的《"+associationMapper.findAssociationById(aj.getA_id()).getName()+"社团》\n申请" +
                                "通过了！恭喜你成为该社团中的一员，谨代表所有会员欢迎你的加入");
                        is.sendEmailWithSystem(sm);
                    }

                    break;
                default:
                    return new SimpleVO(-3,"参数大错误！");
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new SimpleVO(-5,"操作失败，请联系管理员");
        }
        if(status == 1) {
            simpleVO.setCode(0);
            simpleVO.setMsg("操作成功！");
        }else {
            simpleVO.setCode(-1);
            simpleVO.setMsg("操作失败！");
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

    /**
     * 修改用户空间访问权限
     * @param uid 要修改的用户ID
     * @return 返回一个代码，负数表示错误。0表示关闭，1表示开启
     */
    public int switchZoneStatus(Integer uid) {
        // 判断当前空间开闭状态
        Integer state = userMapper.findZoneStateByUid(uid);
        if(state == null || state < 0 || state > 1){
            return -100;
        }
        // 执行切换状态
        Integer result = -1;
        switch (state){
            case 0:
                result = userMapper.updateZonStateByUidState(uid,1);
                if(result == 1) result = 1;
                break;
            case 1:
                result = userMapper.updateZonStateByUidState(uid,0);
                if(result == 1) result = 0;
                break;
            default:
                result = -101;
        }
        if (result == null) return -102;
        // 返回值
        return result;
    }
}

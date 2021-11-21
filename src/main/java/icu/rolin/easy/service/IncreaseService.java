package icu.rolin.easy.service;

import icu.rolin.easy.mapper.*;
import icu.rolin.easy.model.DO.Apply_Create;
import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.utils.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class IncreaseService {
    private final static Logger logger = LoggerFactory.getLogger(IncreaseService.class);
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
    SelectService selectService;

    /**
     * 当用户申请参加活动时，程序将调用该接口为用户注册参加活动信息
     *
     * @param uid  传入一个UID，表示当前操作该业务的用户
     * @param acid 传入一个acid
     * @return 返回一个布尔值，表示业务的结果状态
     * @update 为业务添加判断模式，当用户以参加活动时将插入失败
     */
    public boolean participateAction(Integer uid, Integer acid) {
        Integer status = joinActionMapper.verifyUserJoinActionById(acid, uid);
        if (status == 1) return false;

        Integer code = joinActionMapper.insertJoinActionForm(acid, uid);
        if (code == null) {
            logger.warn("申请参加活动表插入失败，请检查数据准确性");
            code = 0;
        }
        return code == 1;
    }


    /**
     * 用户提交创建社团申请，该方法用以处理该业务
     *
     * @param cap 传入的参数对象
     * @return 返回一个Map对象，Key-1 代表处理结果 Key-2代表caid
     */
    public Map<Integer, Integer> createAssociation(CreateAssPO cap) {
        Map<Integer, Integer> res = new HashMap<Integer, Integer>();
        Apply_Create ac = new Apply_Create();
        ac.setU_id(cap.getUid());
        ac.setName(cap.getAssname());
        ac.setLogo(cap.getAssprofile());
        ac.setIntro(cap.getAssintro());
        ac.setParent_organization(cap.getOrg());
        ac.setNote(cap.getNote());
        Integer code = applyCreateMapper.insertCreatAssociationForm(ac);
        if (code == 0) { //插入失败
            res.put(1, 0);
            return res;
        } else if (code == 1) { //插入成功
            res.put(1, 1);
            res.put(2, selectService.getCAID());
            return res;
        } else { //插入错误或者其他情况
            logger.error("用户申请社团失败，数据库返回空集！");
            res.put(1, -1);
            return res;
        }
    }


    /**
     * 用户申请加入社团时触发该业务
     *
     * @param uan 传入一个对象参数 Uid、Aid以及Note
     * @return 返回一个布尔值, 表示提交申请是否成功
     * @author Joolum
     */
    public boolean applyJoinAssociation(UserAssNotePO uan) {
        Integer code = applyJoinAssociationMapper.insertJoinAssForm(uan.getUid(), uan.getAid(), uan.getNote());
        if (code == null) {
            logger.warn("申请加入协会表插入失败，请检查数据准确性");
            code = 0;
        }
        return code == 1;
    }

    /**
     * 用户注册操作，当用户执行注册时，会调用该业务，对数据进行一个数据的插入
     *
     * @param registerPO 传入一个对象参数
     * @return 返回一个布尔值，表示当前注册业务是否成功
     */
    public boolean userRegister(RegisterPO registerPO) {
        Integer code = userMapper.insertUser(registerPO.getRealName(), registerPO.getUserName(), registerPO.getStudentID(), registerPO.getCollege(), registerPO.getPassword(), registerPO.getEmail(), registerPO.getPhone(), registerPO.getSex(), registerPO.getBirth(), registerPO.getHeadImage());
        if (code == null) {
            logger.error("用户注册失败，数据库返回空结果集");
            code = -1;
        }
        return code == 1;
    }


    /**
     * @param rppo 传入一个参数对象
     * @return 返回一个Map键值对，Key-pid：返回的pid，Key-status：返回是否正确处理事务
     * @author :JooLum
     * @update-time：2021年11月9日 10点24分
     * update-content:
     * - 增加事务管理，若插入失败则整个事务回滚
     */
    @Transactional
    public Map<String, Integer> publishPost(ReleasePostPO rppo) {
        // 新建返回对象
        Map<String, Integer> result = new HashMap<String, Integer>();
        //插入内容
        Integer contentCode = contentMapper.savePost_content(rppo.getContent());
        if (contentCode != 1) { //验证插入内容是否成功
            logger.error("前端发帖请求，缺失相关发送数据...");
            result.put("status", 0);
            return result;
        }
        // 获取最新插入内容ID
        Integer contentId = contentMapper.getTheLatestID();
        //获取帖子的类型INT
        Integer postType = Common.getKey(Common.POST_TYPE, rppo.getPostType());
        if (postType == null) postType = 5;
        //进行一个数据的插入
        Integer postCode = postMapper.savePost(
                rppo.getReleaseArea(),
                rppo.getUid(),
                rppo.getPostTitle(),
                contentId,
                rppo.getTags(),
                postType);
        if (postCode == null || postCode == 0) {
            logger.warn("帖子没有成功写入数据库!");
            result.put("status", 0);
        }
        result.put("pid", postMapper.getTheLatestId());
        result.put("status", 1);
        return result;
    }


    /**
     * 由系统发送邮件给用户，该右键无需注明发送发送者
     *
     * @param sm 邮件参数对象(该参数没有from_uid选项)
     * @return 返回一个boolean值，是当前业务的状态
     */
    public SimpleVO sendEmailWithSystem(SendMailPO sm) {
        SimpleVO svo = new SimpleVO();
        Integer code = mailMapper.insertMailWithSystem(sm.getTouid(), sm.getTitle(), sm.getContent(), sm.getMailType());
        logger.error("发送邮件数据库返回码：" + code.toString());
        if (code == 1) {
            svo.setCode(mailMapper.getMaxMid());
            svo.setMsg("系统发送邮件成功");
        } else {
            svo.setCode(-1);
            svo.setMsg("发送邮件失败");
        }
        return svo;
    }

    /**
     * 由用户发送邮件给用户，该邮件需要注明发送者和接收者的id
     *
     * @param sm 邮件参数对象
     * @return 返回一个boolean值，是当前业务的状态
     */
    public SimpleVO sendEmailWithUser(SendMailPO sm) {
        SimpleVO svo = new SimpleVO();
        Integer code = mailMapper.insertMailWithUser(sm.getFromuid(), sm.getTouid(), sm.getTitle(), sm.getContent(), sm.getMailType());
        logger.error("发送邮件数据库返回码：" + code.toString());
        if (code == 1) {
            svo.setCode(mailMapper.getMaxMid());
            svo.setMsg("用户发送邮件成功");
        } else {
            svo.setCode(-1);
            svo.setMsg("发送邮件失败");
        }
        return svo;
    }

    /**
     * 发送评论，计入数据库，返回布尔值
     * @author Joolum
     * @param rd 一个 参数对象
     * @return SimpleVO
     */
    public SimpleVO sendComment(ReleaseDiscussPO rd){
        SimpleVO simpleVO = new SimpleVO();
        if (rd.getUid() == null || rd.getPid() == null) {
            simpleVO.setCode(-1);
            simpleVO.setMsg("缺失任一ID字段值");
            logger.warn("ID字段值丢失");
        } else {
            Integer code = commentsMapper.addComment(rd.getPid(), rd.getUid(), rd.getContent());
            if (code == 0) {
                simpleVO.setCode(-1);
                simpleVO.setMsg("插入评论失败");
                logger.warn("插入评论---操作数据库失败..");
            }else {
                simpleVO.setCode(rd.getPid());
                simpleVO.setMsg("发表评论成功");
            }
        }

        return simpleVO;
    }


    /**
     * 收藏帖子，往收藏表中插入一条数据
     * @param pid 帖子ID
     * @param uid   用户ID
     * @return 返回一个布尔值表示操作是否成功
     */
    public boolean collectPost(int pid,int uid) {
        Integer code = favoriteTableMapper.collectPost(pid, uid);
        return code == 1;
    }

    public SimpleVO submitAssApply(SendApplyPO sapo) {
        SimpleVO simpleVO = new SimpleVO();
        if (sapo.getAid() == null) {
            simpleVO.setMsg("请求参数丢失");
            simpleVO.setCode(-1);
            logger.error("提交社团申请---请求参数丢失...");
        } else {
            int contentCode = contentMapper.savePost_content(sapo.getContent());
            if (contentCode == 0) {
                simpleVO.setCode(0);
                simpleVO.setMsg("申请内容插入数据库插入失败..");
                logger.error("提交社团申请---内容插入失败");
            } else {
                int contentId = contentMapper.getTheLatestID();
                int code = applyCommondMapper.insertAssApply(sapo.getAid(), sapo.getTitle(), contentId);
                if (code == 0) {
                    simpleVO.setMsg("社团申请提交失败");
                    simpleVO.setCode(-1);
                } else {
                    simpleVO.setMsg("社团申请提交成功");
                    simpleVO.setCode(1);
                }
            }

        }

        return simpleVO;
    }

    public SimpleVO releaseAction(ReleaseActionPO actObj) {
        SimpleVO simpleVO = new SimpleVO();

        int contentCode = contentMapper.savePost_content(actObj.getContent());
        if (contentCode == 0) {
            simpleVO.setCode(0);
            simpleVO.setMsg("活动内容插入数据库插入失败..");
            logger.error("提交社团活动申请---内容插入失败");
        } else {
            int contentId = contentMapper.getTheLatestID();
            int code = actionMapper.releaseAction(actObj.getAid(), actObj.getTitle(), contentId, actObj.getPosition(), actObj.getStartTime(), actObj.getEndTime());
            if (code == 0) {
                simpleVO.setMsg("社团活动申请提交失败");
                simpleVO.setCode(-1);
            } else {
                int actId = actionMapper.getTheLatestId();
                simpleVO.setMsg("社团活动申请提交成功");
                simpleVO.setCode(actId);
            }
        }

        return simpleVO;
    }


}

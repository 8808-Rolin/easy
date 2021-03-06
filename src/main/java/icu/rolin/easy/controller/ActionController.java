package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.ReleaseActionPO;
import icu.rolin.easy.model.VO.ActionMemberVO;
import icu.rolin.easy.model.VO.GetActionListVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/action")
public class ActionController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;



    // 申请参加活动接口
    @PostMapping(value = "/participate")
    public ResponseVO participate_action (Integer uid,Integer actid){
        if(!ss.verifyUserJoinAssWithAction(actid,uid)){
            return new ResponseVO(new SimpleVO(1,"申请提交失败，用户未加入该社团"));
        }
        //  3. 加入活动
        boolean key = is.participateAction(uid,actid);
        if (key) return new ResponseVO(new SimpleVO(0,"申请成功"));
        return new ResponseVO(new SimpleVO(1,"申请提交失败，请检查数据传输准确性,也有可能是用户已经参加了该活动"));
    }

    @PostMapping(value = "/release-action")
    public ResponseVO release(ReleaseActionPO actObj){
        return new ResponseVO(is.releaseAction(actObj));
    }
    @PostMapping(value = "/get-action-list")
    public ResponseVO get_action_list(Integer aid){
        return new ResponseVO(ss.getActionList(aid));
    }

    @GetMapping(value = "/get-action-member")
    public ResponseVO get_action_member(Integer aid){
        return new ResponseVO(ss.getActionMember(aid));
    }

    @PostMapping(value = "/get-act-apply")
    public ResponseVO get_action_apply(Integer aid){
        return new ResponseVO(new GetActionListVO());
    }

}

package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.ReleaseActionPO;
import icu.rolin.easy.model.VO.ActionMemberVO;
import icu.rolin.easy.model.VO.GetActionListVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping(value = "/participate")
    public ResponseVO participate_action (Integer uid,Integer actid){
        boolean key = actionService.participateAction(uid,actid);
        if (key) return new ResponseVO(new SimpleVO(0,"申请成功"));
        return new ResponseVO(new SimpleVO(1,"申请提交失败，请检查数据传输准确性！"));
    }

    @PostMapping(value = "/release-action")
    public ResponseVO release(ReleaseActionPO actObj){
        return new ResponseVO(new SimpleVO());
    }
    @PostMapping(value = "/get-action-list")
    public ResponseVO get_action_list(Integer aid){
        return new ResponseVO(new GetActionListVO());
    }

    @GetMapping(value = "/get-action-member")
    public ResponseVO get_action_member(Integer aid){
        return new ResponseVO(new ActionMemberVO());
    }

    @PostMapping(value = "/get-act-apply")
    public ResponseVO get_action_apply(Integer aid){
        return new ResponseVO(new GetActionListVO());
    }

}

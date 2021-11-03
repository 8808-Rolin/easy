package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.ReleaseActionPO;
import icu.rolin.easy.model.VO.ActionMemberVO;
import icu.rolin.easy.model.VO.GetActionListVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/action")
public class ActionController {

    @PostMapping(value = "/participate")
    public ResponseVO participate_action (Integer uid,Integer actid){
        return new ResponseVO(new SimpleVO());
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

}

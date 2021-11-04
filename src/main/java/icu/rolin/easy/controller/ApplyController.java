package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.CreateAssPO;
import icu.rolin.easy.model.PO.SendApplyPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.*;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/apply")
public class ApplyController {

    @PostMapping(value = "/create-ass")
    public ResponseVO create_ass(CreateAssPO cap){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/join-association")
    public ResponseVO join_ass(UserAssNotePO uan){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/get-join-apply-list")
    public ResponseVO get_join_apply_list(Integer aid){
        return  new ResponseVO(new GetJoinApplyVO());
    }

    @PostMapping(value = "/set-join-apply-status")
    public ResponseVO set_join_apply(Integer type,Integer uaid){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/get-association-apply-list")
    public ResponseVO get_ass_apply_list(Integer aid, @RequestParam(value = "headeruid") Integer uid){
        return new ResponseVO(new GetAssApplyVO());
    }

    @PostMapping(value = "/submit-association-apply")
    public ResponseVO send_apply_ass(SendApplyPO sapo){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-create-apply-list")
    public ResponseVO get_create_apply_list(){
        return new ResponseVO(new CreateAssPO());
    }

    @PostMapping(value = "/send-act-reply")
    public ResponseVO send_act_reply(Integer actid,Integer status){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/send-aa-reply")
    public ResponseVO send_action_apply_reply(Integer aaid,Integer status){
        return new ResponseVO(new SimpleVO());
    }
    @PostMapping(value = "/get-ass-apply-list")
    public ResponseVO get_ass_apply_list(){
        return new ResponseVO(new GetApplyListVO());
    }

}

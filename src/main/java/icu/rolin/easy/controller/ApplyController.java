package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.CreateAssPO;
import icu.rolin.easy.model.PO.SendApplyPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.GetAssApplyVO;
import icu.rolin.easy.model.VO.GetJoinApplyVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
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



}

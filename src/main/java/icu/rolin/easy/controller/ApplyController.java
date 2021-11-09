package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.CreateAssPO;
import icu.rolin.easy.model.PO.SendApplyPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/apply")
public class ApplyController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;



    @PostMapping(value = "/create-ass")
    public ResponseVO create_ass(CreateAssPO cap){
        Map<Integer,Integer> res = is.createAssociation(cap);
        if (res.get(1) == 1) //成功
            return new ResponseVO(new SimpleVO(res.get(2),"申请成功,code字段是caid"));
        else
            return new ResponseVO(new SimpleVO(-1,"申请社团失败"));
    }

    //非成员社团用户可以在社团页面申请加入社团，该接口需要提供一个uid、aid、以及申请备注放能够提交成功
    @PostMapping(value = "/join-association")
    public ResponseVO join_ass(UserAssNotePO uan){
        boolean key = is.applyJoinAssociation(uan);
        if (key) return new ResponseVO(new SimpleVO(0,"申请成功提交"));
        return new ResponseVO(new SimpleVO(1,"申请提交失败，请检查数据传输准确性！"));
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

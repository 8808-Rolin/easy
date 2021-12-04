package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.CreateAssPO;
import icu.rolin.easy.model.PO.SendApplyPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.POJO.JoinApplyPOJO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        // 参数合法性认证
        if(uan == null || uan.getAid() == null || uan.getUid() == null ||!ss.verifyAssExist(uan.getAid())){
            return new ResponseVO(new SimpleVO(-1,"参数错误！！！请检查参数"));
        }
        // 是否已经提交过申请
        if(ss.verifyUserIsSubmitApprove(uan.getUid(),uan.getAid())){
            return new ResponseVO(new SimpleVO(-2,"你已提交过申请，请耐心等待审批......"));
        }
        boolean key = is.applyJoinAssociation(uan);
        if (key) return new ResponseVO(new SimpleVO(0,"申请成功提交"));
        return new ResponseVO(new SimpleVO(1,"申请提交失败，请检查数据传输准确性！"));
    }

    @PostMapping(value = "/get-join-apply-list")
    public ResponseVO get_join_apply_list(Integer aid){
        // 参数合法性认证
        if(aid == null || !ss.verifyAssExist(aid)){
            return new ResponseVO(new SimpleVO(-1,"参数错误！！！请检查参数"));
        }

        try{
            GetJoinApplyVO g = ss.getJoinApplyList(aid);
            return  new ResponseVO(g);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get_join_apply_list ERROR");
            return  new ResponseVO(new SimpleVO(-2,"ERROR,请联系管理员处理"));
        }


    }

    @PostMapping(value = "/set-join-apply-status")
    public ResponseVO set_join_apply(Integer type, Integer uaid, HttpServletRequest request){
        // 判断参数合法性
        if(type == null || uaid == null || type < 0 || type > 1|| uaid <= 0|| !ss.verifyUaidExist(uaid)){
            return new ResponseVO(new SimpleVO(-1,"参数错误！！！"));
        }
        // 判断该UAID是否已经被审批过或者对象用户已经在社团中
        if(UtilsService.getUidWithTokenByRequest(request) == null ||
                !ss.verifyUaidValidity(uaid,UtilsService.getUidWithTokenByRequest(request))){
            return new ResponseVO(new SimpleVO(-2,"操作错误，请重新检查参数!"));
        }
        return new ResponseVO(us.setJoinApplyStatus(type,uaid));
    }

    @PostMapping(value = "/get-association-apply-list")
    public ResponseVO get_ass_apply_list(Integer aid){
        // 判断参数合法性
        if(aid == null || !ss.verifyAssExist(aid)){
            return new ResponseVO(new SimpleVO(-1,"参数错误，请检查参数"));
        }
        return new ResponseVO(ss.getAssociationListApplyList(aid));
    }

    @PostMapping(value = "/submit-association-apply")
    public ResponseVO send_apply_ass(SendApplyPO sapo,HttpServletRequest request){
        // 验证参数合法性
        if(sapo == null || sapo.getAid() == null || sapo.getTitle() == null || sapo.getContent() == null
                ||!ss.verifyAssExist(sapo.getAid()) || sapo.getTitle().equals("") || sapo.getContent().equals("")){
            return new ResponseVO(new SimpleVO(-1,"参数错误，提交失败"));
        }
        // 验证权限
        Integer uid = UtilsService.getUidWithTokenByRequest(request);
        if(uid == null || ss.verifyUserIsAssAdmin(uid,sapo.getAid())){
            return new ResponseVO(new SimpleVO(-2,"权限错误，提交失败"));
        }
        return new ResponseVO(is.submitAssApply(sapo));
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

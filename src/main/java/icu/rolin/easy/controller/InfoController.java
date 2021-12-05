package icu.rolin.easy.controller;

import com.alibaba.fastjson.JSON;
import icu.rolin.easy.interceptor.UserInterceptor;
import icu.rolin.easy.model.DO.Association;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.PO.AssInfoUpdatePO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.POJO.*;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/info")
public class InfoController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;



    @PostMapping(value = "/get-common-person-information")
    public ResponseVO get_common_person_information(Integer uid){

        UserPOJO userPOJO = ss.getPersonInformation(uid);
        if (userPOJO == null){
            return new ResponseVO(new SimpleVO(1,"获取用户信息失败"));
        }else {
            return new ResponseVO(new UserRespVO(0,"",userPOJO));
        }

    }

    @GetMapping(value = "/get-member-list")
    public ResponseVO get_members(Integer aid){
        AssMemberPOJO[] amps = ss.getAssMemberList(aid);
        AssMembersRespVO amrvo = new AssMembersRespVO();
        if (aid == null) return new ResponseVO(202,"参数错误",null);

        if (!ss.getAssIsExist(aid)){
            amrvo.setCode(0);
            amrvo.setMembers(null);
            return new ResponseVO("获取信息失败,该社团不存在",amrvo);
        }
        System.out.println(JSON.toJSONString(amps));
        amrvo.setCode(amps.length);
        amrvo.setMembers(amps);
        return new ResponseVO("获取成功",amrvo);
    }

    //获取社团展示列表
    @PostMapping(value = "/get-action-overview")
    public ResponseVO get_action_overview(UserAssNotePO ua){
        PersonActionPOJO[] personActionPOJOS = ss.getActionOverview(ua);
       if (personActionPOJOS==null || personActionPOJOS.length==0 )
            return new ResponseVO(new GetActionOverviewVO(0,"该社团暂无任何活动",personActionPOJOS));
       return new ResponseVO(new GetActionOverviewVO(personActionPOJOS.length, "获取活动列表成功",personActionPOJOS));
    }

    //3.3.7 获取活动详细信息接口
    @PostMapping(value = "/get-action-info")
    public ResponseVO get_action_info(Integer uid,Integer actid){
        if(actid == null) return new ResponseVO(new SimpleVO(-1,"参数错误！！！"));
        if(uid == null){
            return new ResponseVO(ss.getDetailedActionInformation(actid));
        }else{
            return new ResponseVO(ss.getDetailedActionInformation(uid,actid));
        }

    }


    @GetMapping(value = "/delete-mail")
    public ResponseVO delete_mail (Integer uid){
        if(uid == null || uid <= 0 ){
            return new ResponseVO(new SimpleVO(-1,"参数错误，请重试"));
        }
        return new ResponseVO(us.deleteMail(uid));
    }
    
    // 获取邮箱概要
    @GetMapping(value = "/get-mails")
    public ResponseVO get_mails(Integer uid) {
        if(uid == null || uid <= 0){
            return new ResponseVO(new SimpleVO(-1,"获取错误，请检查参数"));
        }
        GetMailOverviewVO gmovo = new GetMailOverviewVO();
        MailOverviewPOJO[] mo = ss.getMails(uid);
        gmovo.setCode(mo.length);
        gmovo.setMail(mo);
        return new ResponseVO(gmovo);
    }

    @GetMapping(value = "/get-mail-content")
    public ResponseVO get_mail_content(Integer mid,Integer status){
        if(mid == null || mid <= 0 || status == null){
            return new ResponseVO(new SimpleVO(-1,"获取错误，请检查参数"));
        }
        if (!(status.equals(1) || status.equals(0))){
            status = 0;
        }
        return new ResponseVO(ss.getMailContent(mid,status));
    }

    @GetMapping(value = "/get-ass-mails")
    public ResponseVO get_ass_mail_overview(Integer aid,HttpServletRequest request){
        // 校验当前参数输入是否合法
        if(aid == null || aid <= 0 || !ss.getAssIsExist(aid))
            return new ResponseVO(new SimpleVO(-1,"参数出错，请检查参数aid"));
        // 校验当前用户是否拥有该社团的管理权限
        Integer uid = UtilsService.getUidWithTokenByRequest(request);
        if(uid == null)
            return new ResponseVO(new SimpleVO(-2,"请先登录！"));
        if(!ss.verifyUserIsAssAdmin(uid,aid))
            return new ResponseVO(new SimpleVO(-3,"你不是管理员！没有权限进入后台"));
        return new ResponseVO(ss.getAssMails(aid));
    }

    @GetMapping(value = "/get-fixed-show-info")
    public ResponseVO get_show_info(Integer aid, HttpServletRequest request){
        // 校验当前参数输入是否合法
        if(aid == null || aid <= 0 || !ss.getAssIsExist(aid))
            return new ResponseVO(new SimpleVO(-1,"参数出错，请检查参数aid"));
        // 校验当前用户是否拥有该社团的管理权限
        Integer uid = UtilsService.getUidWithTokenByRequest(request);
        if(uid == null)
            return new ResponseVO(new SimpleVO(-2,"请先登录！"));
        if(!ss.verifyUserIsAssAdmin(uid,aid))
            return new ResponseVO(new SimpleVO(-3,"你不是管理员！没有权限进入后台"));
        // 获取数据并返回
        return new ResponseVO(ss.getShowInfo(aid));
    }

    @GetMapping(value = "/get-person-act")
    // TODO: 2021/11/29 优化截断以及算法
    public ResponseVO get_person_act(Integer aid){
        // 判断数据合法性
        if(aid == null || aid <= 0 || !ss.getAssIsExist(aid))
            return new ResponseVO(new SimpleVO(-1,"参数出错，请检查参数aid"));
        return new ResponseVO(ss.getAssPersonAct(aid));
    }

    @GetMapping(value = "/get-daily-act")
    public ResponseVO get_daily_act(Integer aid){
        // 判断参数合法性
        if(aid == null || aid <= 0 || !ss.getAssIsExist(aid))
            return new ResponseVO(new SimpleVO(-1,"参数出错，请检查参数aid"));
        DailyViewPOJO[] x = ss.getDailyAct(aid);
        DailyActionVO y = new DailyActionVO();
        y.setCode(x.length);
        y.setDaily(x);
        return new ResponseVO(y);
    }

    @GetMapping(value = "/get-ass-act")
    public ResponseVO get_ass_act(){
        return new ResponseVO(new PersonActionVO());
    }
    @GetMapping(value = "/get-sys-act")
    public ResponseVO get_system_active(){
        return new ResponseVO(new DailyActionVO());
    }

    @PostMapping(value = "/get-member-information-list")
    public ResponseVO get_member_list_ass(Integer aid){
        if(aid == null || aid <= 0 || !ss.getAssIsExist(aid))
            return new  ResponseVO(ss.getAllUserInformation());
        return new ResponseVO(ss.getMemberInformation(aid));
    }

    @PostMapping(value = "/remove-user")
    public ResponseVO remove_user(UserAssNotePO ua,HttpServletRequest request){
        if(ua == null || ua.getAid() == null || ua.getUid() == null || ua.getAid() <= 0 || ua.getUid()<= 0){
            return new ResponseVO(new SimpleVO(-1,"参数错误！！！请检查参数"));
        }
        //判断登录用户是否具有该社团的管理员资格
        Integer uid = UtilsService.getUidWithTokenByRequest(request);
        if(uid == null || uid <= 0){
            return new ResponseVO(new SimpleVO(-2,"Token错误！是否未登录"));
        }
        if(!ss.verifyUserIsAssAdmin(uid,ua.getAid())){
            return new ResponseVO(new SimpleVO(-3,"权限错误！你不是社团管理员"));
        }
        String state = ds.removeMember(ua);
        if(state.equals("")){
            //成功
            return new ResponseVO(new SimpleVO(0,"移除成功！"));
        }else{
            return new ResponseVO(new SimpleVO(1,state));
        }
    }

    @PostMapping(value = "/update-ass-info")
    public ResponseVO update_ass_info(AssInfoUpdatePO aiu,HttpServletRequest request){
        // 判断合法性
        if(aiu.getAid() == null || !ss.verifyAssExist(aiu.getAid()))
            return new ResponseVO(new SimpleVO(-1,"参数错误!"));
        // 判断管理权限
        Integer uid = UtilsService.getUidWithTokenByRequest(request);
        if(uid == null || uid <= 0){
            return new ResponseVO(new SimpleVO(-2,"Token错误！是否未登录"));
        }
        if(!ss.verifyUserIsAssAdmin(uid,aiu.getAid())){
            return new ResponseVO(new SimpleVO(-3,"权限错误！你不是社团管理员"));
        }

        // 执行业务语句
        SimpleVO s = null;
        switch (us.updateAssociationInfo(aiu)){
            case 0:
                s = new SimpleVO(0,"修改成功");
                break;
            case -1:
                s = new SimpleVO(-1,"修改名字错误！");
                break;
            case -2:
                s = new SimpleVO(-2,"修改简介错误！");
                break;
            case -3:
                s = new SimpleVO(-3,"修改社团LOGO错误！");
                break;
            case -4:
                s = new SimpleVO(-4,"移交会长失败，数据库错误！");
                break;
            case -5:
                s = new SimpleVO(-5,"移交会长失败，新会长不是管理员，无法移交");
                break;
        }
        return new ResponseVO(s);

    }

    @GetMapping(value = "/get-alluser-number")
    public ResponseVO get_user_number(){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-user-per")
    public ResponseVO get_user_per(){
        return new ResponseVO(new GetUserPerVO());
    }

    @GetMapping(value = "/get-action-list")
    public ResponseVO get_action_list(@RequestParam(value = "requestType")Integer rType,@RequestParam(value = "statusType") Integer sType){
        return new ResponseVO(new GetActionListVO());
    }


    @GetMapping(value = "/get-ass-detail-list")
    public ResponseVO get_ass_detail(){
        return new ResponseVO(new AssDetailListVO());
    }

    @PostMapping(value = "/get-all-users")
    public ResponseVO get_all_user(){
        return new ResponseVO(new GetUsersVO());
    }

    @GetMapping(value = "/get-association-info")
    public ResponseVO get_ass_info(Integer aid){
        if (aid == null || aid == 0) return  new ResponseVO(-1,"参数错误,请重新输入");
        Association association = ss.getAssociationInfoById(aid);
        if(association == null) return new ResponseVO(-1,"不存在该社团，请检查aid");
        return new ResponseVO(0,association);
    }

    @GetMapping(value = "/get-all-ass")
    public ResponseVO getAssList(){
        class List_{
            private Integer aid;
            private String name;

            public Integer getAid() {
                return aid;
            }

            public void setAid(Integer aid) {
                this.aid = aid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        class VO{
            private Integer code;
            private List_[] list;

            public VO() {
            }

            public VO(Integer code, List_[] list) {
                this.code = code;
                this.list = list;
            }

            public Integer getCode() {
                return code;
            }

            public void setCode(Integer code) {
                this.code = code;
            }

            public List_[] getList() {
                return list;
            }

            public void setList(List_[] list) {
                this.list = list;
            }
        }

        ArrayList<Association> associations = ss.getAllAss();
        List_[] list_s = new List_[associations.size()];
        for (int i = 0; i < associations.size(); i++) {
            list_s[i] = new List_();
            list_s[i].setAid(associations.get(i).getId());
            list_s[i].setName(associations.get(i).getName());
        }
        return new ResponseVO(new VO(list_s.length,list_s));
    }

    @PostMapping(value = "/set-ass-admin")
    public ResponseVO setAdminInAss(Integer aid,Integer uid,Integer status,HttpServletRequest request){
        // 判断参数合法性
        if(aid == null || uid == null || status == null ||
            !ss.verifyAssExist(aid) || !ss.verifyUserExist(uid)){
            return new ResponseVO(new SimpleVO(-1,"参数错误"));
        }

        // 判断用户权限
        Integer loginUID = UtilsService.getUidWithTokenByRequest(request);
        if(loginUID == null) return new ResponseVO(new SimpleVO(-2,"登陆状态不对头"));
        User loginUser = ss.getUserById(loginUID);
        if(loginUser.getLevel() < 2) return new ResponseVO(new SimpleVO(-3,"权限错误！"));

        boolean flags = false;
        switch (status){
            case 0:
                flags = us.unsetAssAdmin(uid,aid);
                break;
            case 1:
                flags = us.setAssAdmin(uid,aid);
                break;
            default:
                return new ResponseVO(new SimpleVO(-5,"操作状态参数错误"));
        }
        if(!flags){
            return new ResponseVO(new SimpleVO(-1,"操作失败，请联系管理员"));
        }else{
            return new ResponseVO(new SimpleVO(0,"操作成功！"));
        }

    }
}

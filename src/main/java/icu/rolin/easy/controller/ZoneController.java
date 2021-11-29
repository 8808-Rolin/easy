package icu.rolin.easy.controller;


import icu.rolin.easy.interceptor.ZoneInterceptor;
import icu.rolin.easy.model.PO.UidProfilePO;
import icu.rolin.easy.model.POJO.ZoneJoinAssPOJO;
import icu.rolin.easy.model.POJO.ZonePostPOJO;
import icu.rolin.easy.model.POJO.ZoneUserDataPOJO;
import icu.rolin.easy.model.VO.GetInformationVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.model.VO.ZonePostVO;
import icu.rolin.easy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/zone")
public class ZoneController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;


    @GetMapping(value = "/get-zone-status")
    public ResponseVO get_status(Integer muid, HttpServletRequest request) {
        if(muid == null || muid == 0 || request.getHeader("token") == null){
            return new ResponseVO(new SimpleVO(-1, "权限错误！参数错误！"));
        }

        SimpleVO svo = new SimpleVO();
        int res = ss.verifyUserZoneStatus(muid, request);
        switch (res){
            case 0:
                svo.setCode(0);
                svo.setMsg("用户正在访问自己的空间");
                break;
            case 1:
                svo.setCode(1);
                svo.setMsg("用户正在访问其他人的开放空间");
                break;
            case 2:
                svo.setCode(2);
                svo.setMsg("当前空间用户未开放，无法访问！！");
                break;
            case -3:
                svo.setCode(-2);
                svo.setMsg("数据库出错，请联系管理员处理");
                break;
            default:
                svo.setCode(res);
                svo.setMsg("其他错误！");
        }
        return new ResponseVO(svo);
    }

    @GetMapping(value = "/get-post")
    public ResponseVO get_post(Integer type, @RequestParam(value = "zone-uid") Integer uid){
        // 初始化类型，赋默认值
        int typ = 0;
        if (type != null && type >= 1){
            typ = 1;
        }
        // 判断参数合法性
        if(uid == null || uid == 0 || ss.getUserNameById(uid) == null)
            return new ResponseVO(new SimpleVO(-1,"参数错误！,该用户不存在！"));
        // 初始化实例数组
        ZonePostPOJO[] zonePostPOJOS;
        if(typ == 0){ // 获取发表帖子
            zonePostPOJOS = ss.zoneGetPostRelease(uid);
        }else{ // 获取收藏的帖子
            zonePostPOJOS = ss.zoneGetPostFavorite(uid);
        }
        ZonePostVO zp = new ZonePostVO();
        zp.setCode(zonePostPOJOS.length);
        zp.setPosts(zonePostPOJOS);
        return new ResponseVO(zp);
    }

    @GetMapping(value = "/get-information")
    public ResponseVO get_information(Integer uid,HttpServletRequest hsr){
        if(uid == null || uid <= 0 || !ss.verifyUserExist(uid)){
            return new ResponseVO(new SimpleVO(0,"获取失败，请检查参数的合法性"));
        }
        GetInformationVO givo = new GetInformationVO();
        ZoneUserDataPOJO zud = ss.getZoneUserData(uid,hsr);
        ZoneJoinAssPOJO[] z = ss.getZoneAssInfo(uid);
        givo.setCode(1);
        givo.setAssNum(z.length);
        givo.setUserdata(zud);
        givo.setJoinass(z);
        return new ResponseVO(givo);
    }

    @PostMapping(value = "/update-notice")
    public ResponseVO notice_notice(UidProfilePO up,HttpServletRequest req){
        if(
                up == null ||
                up.getUid() == null ||
                up.getNewProfile() == null ||
                !ZoneInterceptor.verifyZoneStatus(req,up.getUid())
        ){
            return new ResponseVO(new SimpleVO(1,"修改失败，请检查参数的合法性"));
        }
        return new ResponseVO(us.modifyNotice(up));
    }

    @PostMapping(value = "/update-name")
    public ResponseVO name_update(UidProfilePO up,HttpServletRequest req){
        if(up == null || up.getUid() == null || up.getNewProfile() == null || !ZoneInterceptor.verifyZoneStatus(req,up.getUid())){
            return new ResponseVO(new SimpleVO(1,"修改失败，请检查参数的合法性"));
        }
        return new ResponseVO(us.modifyUserName(up));
    }

    @PostMapping(value = "/update-intro")
    public ResponseVO intro_update(UidProfilePO up,HttpServletRequest req){
        if(up == null || up.getUid() == null || up.getNewProfile() == null || !ZoneInterceptor.verifyZoneStatus(req,up.getUid())){
            return new ResponseVO(new SimpleVO(1,"修改失败，请检查参数的合法性"));
        }
        return new ResponseVO(us.updateUserIntro(up));
    }

    @PostMapping(value = "/update-profile")
    public ResponseVO profile_update(UidProfilePO up,HttpServletRequest req){
        if(up == null || up.getUid() == null || up.getNewProfile() == null || !ZoneInterceptor.verifyZoneStatus(req,up.getUid())){
            return new ResponseVO(new SimpleVO(1,"修改失败，请检查参数的合法性"));
        }
        return new ResponseVO(us.updateUserHeadImage(up));
    }
    @PostMapping(value = "/update-email")
    public ResponseVO email_update(UidProfilePO up,HttpServletRequest req){
        if(up == null || up.getUid() == null || up.getNewProfile() == null || !ZoneInterceptor.verifyZoneStatus(req,up.getUid())){
            return new ResponseVO(new SimpleVO(1,"修改失败，请检查参数的合法性"));
        }
        return new ResponseVO(us.updateUserEmail(up));
    }

    @PostMapping(value = "/update-birth")
    public ResponseVO birth_update(UidProfilePO up,HttpServletRequest req){
        if(up == null || up.getUid() == null || up.getNewProfile() == null || !ZoneInterceptor.verifyZoneStatus(req,up.getUid())){
            return new ResponseVO(new SimpleVO(1,"修改失败，请检查参数的合法性"));
        }
        return new ResponseVO(us.updateUserBirth(up));
    }

    @PostMapping(value = "/switch-state")
    public ResponseVO switch_state(Integer uid,HttpServletRequest request,HttpServletResponse response){
        // 判断参数合法性以及权限
        if(uid == null || uid <= 0)
            return new ResponseVO(new SimpleVO(-1,"参数错误！错误代码：-1"));
        Integer loginUserUid = UtilsService.getUidWithTokenByRequest(request);
        if(loginUserUid == null || loginUserUid <= 0 || !ss.verifyUserExist(uid)){
            response.setStatus(809);
            return new ResponseVO(new SimpleVO(-2,"当前未登录或用户不存在,错误代码：-2"));
        }
        if (!uid.equals(loginUserUid))
            return new ResponseVO(new SimpleVO(-3,"当前用户无权操作！错误代码：-3"));

        // 执行操作
        int code = us.switchZoneStatus(uid);
        SimpleVO svo = new SimpleVO();
        svo.setCode(code);
        switch (code){
            case 0:
                svo.setMsg("你已关闭该空间，他人无法直接访问到你的信息了！");
                break;
            case 1:
                svo.setMsg("你已开启空间，别人可以直接访问你的个人空间了！");
                break;
            default:
                svo.setMsg("操作错误，错误代码:"+code);
        }
        return new ResponseVO(svo);
    }
}

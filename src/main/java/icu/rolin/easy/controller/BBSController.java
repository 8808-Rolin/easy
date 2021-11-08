package icu.rolin.easy.controller;

import com.rabbitmq.client.AMQP;
import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.POJO.GetAssInfoAssPOJO;
import icu.rolin.easy.model.POJO.PostsPOJO;
import icu.rolin.easy.model.POJO.ShowDataAssPOJO;
import icu.rolin.easy.model.POJO.SimpleNoticePOJO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.BBSService;
import icu.rolin.easy.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/bbs")
public class BBSController {

    @Autowired
    private BBSService bbsService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get-simple-notice")
    public ResponseVO get_simple_notices(){
        SimpleNoticePOJO[] sn = bbsService.showNotices();
        SimpleNoticeVO snvo = new SimpleNoticeVO();
        if (sn == null) {
            snvo.setCode(0);
            snvo.setNotice(null);
            return new ResponseVO("没有找到公告数据",snvo);
        }
        snvo.setCode(sn.length);
        snvo.setNotice(sn);
        return new ResponseVO(snvo);
    }

    // 3.3.1 获取论坛顶部数据
    @PostMapping(value = "/get-show-data")
    public ResponseVO get_show_data(Integer uid){
        ShowDataAssPOJO[] showDataAssPOJOS = bbsService.showDatum(uid);
        if (showDataAssPOJOS == null) return new ResponseVO(new ShowDataVO(0,"获取失败,可能是没有数据",null));
        return new ResponseVO(new ShowDataVO(showDataAssPOJOS.length,"获取成功",showDataAssPOJOS));
    }

    @GetMapping(value = "/get-post-list")
    public ResponseVO get_post_list(GetPostsPO gppo){
        PostsPOJO[] postsPOJOS = bbsService.showPosts(gppo);
        if (postsPOJOS == null) return new ResponseVO("没有帖子",new GetPostListVO(0,null));
        return new ResponseVO(new GetPostListVO(postsPOJOS.length,postsPOJOS));
    }

    @PostMapping("/release-post")
    public ResponseVO release_post(ReleasePostPO rppo){
        boolean key = bbsService.publishPost(rppo);
        if (key) return new ResponseVO(new SimpleVO(bbsService.getTheLatestP_id(),"成功发表一篇帖子"));
        return new ResponseVO(new SimpleVO(-1,"可能由于请求丢失数据，至使无法成功插入数据库..."));
    }


    //-1代表无任何权限,是黑户
    @PostMapping("/get-ass-information")
    public ResponseVO get_ass_info(UserAssNotePO ua){
        GetAssInfoAssPOJO getAssInfoAssPOJO = bbsService.getAssInformation(ua);
        int permissonCode = userService.getTheLevelById(ua.getUid());
        if (getAssInfoAssPOJO != null && permissonCode != -1) return new ResponseVO(new GetAssInfoVO(0,"获取社团信息成功", permissonCode, getAssInfoAssPOJO));
        return new ResponseVO(new GetAssInfoVO(1,"缺失权限状态码和社团信息！",-1,null));
    }

    @PostMapping(value = "/get-post-page-info")
    public ResponseVO get_post_info(Integer pid){
        return new ResponseVO(new PostVO());
    }

    @PostMapping(value = "/get-discuss-list")
    public ResponseVO get_discuss_list(GetDiscussPO gd){
        return new ResponseVO(new GetDiscussVO());
    }

    @PostMapping(value = "/release-discuss")
    public ResponseVO release_discuss(ReleaseDiscussPO rd){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/delete-post-discuss")
    public ResponseVO delete_context (Integer requestType,Integer typeid){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/modify-post")
    public ResponseVO modify_post(UpdatePostPO up){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/favoriteProcess")
    public ResponseVO favorite(PostUserPO pu){
        return new ResponseVO(new SimpleVO());
    }




}

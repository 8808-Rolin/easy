package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.POJO.GetAssInfoAssPOJO;
import icu.rolin.easy.model.POJO.PostsPOJO;
import icu.rolin.easy.model.POJO.ShowDataAssPOJO;
import icu.rolin.easy.model.POJO.SimpleNoticePOJO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.*;
import icu.rolin.easy.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/bbs")
public class BBSController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;




    @GetMapping(value = "/get-simple-notice")
    public ResponseVO get_simple_notices(){
        SimpleNoticePOJO[] sn = ss.showNotices();
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
        ShowDataAssPOJO[] showDataAssPOJOS = ss.showDatum(uid);
        if (showDataAssPOJOS == null) return new ResponseVO(new ShowDataVO(0,"获取失败,可能是没有数据",null));
        return new ResponseVO(new ShowDataVO(showDataAssPOJOS.length,"获取成功",showDataAssPOJOS));
    }

    // 给注册用户以及非注册用户获取帖子列表使用，能够得到帖子数据的基本概要，需要传入一个页面参数，该参数可空，空则为第一页
    @GetMapping(value = "/get-post-list")
    public ResponseVO get_post_list(GetPostsPO gppo){
        Integer page = gppo.getPage();
        Integer limit = gppo.getLimit();
        if (page == null||page == 0) page = 1;
        if(limit == null||limit == 0) limit =10;
        PostsPOJO[] postsPOJOS = ss.getPosts(gppo);


        if (postsPOJOS == null || postsPOJOS.length==0)
            return new ResponseVO("没有帖子,可能是参数错误或者真就没有帖子",new GetPostListVO(0,null));
        return new ResponseVO(new GetPostListVO(postsPOJOS.length, Common.getPageLimitPost(postsPOJOS,page,limit)));
    }

    //发布帖子接口
    @PostMapping("/release-post")
    public ResponseVO release_post(ReleasePostPO rppo){
        Map<String,Integer> kv = is.publishPost(rppo);
        if(kv.get("status") == 0){//失败
            return new ResponseVO(new SimpleVO(-1,"请求错误！发表帖子错误"));
        }
        return new ResponseVO(new SimpleVO(kv.get("pid"),"发表成功！请刷新页面"));
    }


    //-1代表无任何权限,是黑户
    //用户点击进入论坛页面时，可以用该接口获取相关的个人权限以及论坛的基础信息
    @PostMapping("/get-ass-information")
    public ResponseVO get_ass_info(UserAssNotePO ua){
        GetAssInfoAssPOJO gaia = ss.getAssInformation(ua);
        Integer permissonCode = ss.getUserAssPermission(ua);
        GetAssInfoVO gai = new GetAssInfoVO();
        if (gaia == null || permissonCode == null) return new ResponseVO(new SimpleVO(1,"数据库查询错误！没有找到对应数据"));
        else {
            gai.setCode(0);
            gai.setMsg("获取成功");
            gai.setPermissionCode(permissonCode);
            gai.setAss(gaia);
            return new ResponseVO(gai);
        }
    }

    @PostMapping(value = "/get-post-page-info")
    public ResponseVO get_post_info(Integer pid){
        return new ResponseVO(ss.getPostInfo(pid));
    }

    @PostMapping(value = "/get-discuss-list")
    public ResponseVO get_discuss_list(GetDiscussPO gd){
        //不会分页，我不动这个
        return new ResponseVO(new GetDiscussVO());
    }

    @PostMapping(value = "/release-discuss")
    public ResponseVO release_discuss(ReleaseDiscussPO rd){
        return new ResponseVO(is.addComment(rd));
    }

    @PostMapping(value = "/delete-post-discuss")
    public ResponseVO delete_context (Integer requestType,Integer typeid){
        return new ResponseVO(ds.deletePostDiscuss(requestType,typeid));
    }

    @PostMapping(value = "/modify-post")
    public ResponseVO modify_post(UpdatePostPO up){
        return new ResponseVO(us.modifyPost(up));
    }

    @PostMapping(value = "/favoriteProcess")
    public ResponseVO favorite(PostUserPO pu){
        return new ResponseVO(is.collectPost(pu));
    }


}

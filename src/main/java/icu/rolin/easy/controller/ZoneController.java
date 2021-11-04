package icu.rolin.easy.controller;


import icu.rolin.easy.model.PO.UidProfilePO;
import icu.rolin.easy.model.VO.GetInformationVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.model.VO.ZonePostVO;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/zone")
public class ZoneController {

    @GetMapping(value = "/get-zone-status")
    public ResponseVO get_status(Integer muid){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-post")
    public ResponseVO get_post(Integer type, @RequestParam(value = "zone-uid") Integer uid){
        return new ResponseVO(new ZonePostVO());
    }

    @GetMapping(value = "/get-information")
    public ResponseVO get_information(Integer uid){
        return new ResponseVO(new GetInformationVO());
    }

    @PostMapping(value = "/update-name")
    public ResponseVO name_update(UidProfilePO up){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/update-intro")
    public ResponseVO intro_update(UidProfilePO up){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/update-profile")
    public ResponseVO profile_update(UidProfilePO up){
        return new ResponseVO(new SimpleVO());
    }
    @PostMapping(value = "/update-email")
    public ResponseVO email_update(UidProfilePO up){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/update-birth")
    public ResponseVO birth_update(UidProfilePO up){
        return new ResponseVO(new SimpleVO());
    }

}

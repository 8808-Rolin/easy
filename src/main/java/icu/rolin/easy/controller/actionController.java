package icu.rolin.easy.controller;

import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(value = "/api/action")
public class actionController {

    @PostMapping(value = "/participate")
    public ResponseVO participate_action (Integer uid,Integer actid){
        return new ResponseVO(new SimpleVO());
    }
}

package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.CreateAssPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(value = "/api/apply")
public class applyController {

    @PostMapping(value = "/create-ass")
    public ResponseVO create_ass(CreateAssPO cap){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/join-association")
    public ResponseVO join_ass(UserAssNotePO uan){
        return new ResponseVO(new SimpleVO());
    }


}

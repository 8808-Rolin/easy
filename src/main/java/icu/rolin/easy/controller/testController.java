package icu.rolin.easy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(value = "/test")
public class testController {

    @RequestMapping(value = "/one")
    public String test_one(){
        return "Hello Easy_club";
    }
}

package com.hnsic.demospringbootshiro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName testController
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/19  13:07
 * Version   1.0
 **/
@Controller
@RequestMapping("test1")
public class testController {
    @RequestMapping("main")
    public String test() {
        return "main2";
    }

    @RequestMapping("user")
    public String test1() {
        return "user";
    }
}

package com.taotao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mutoulazy on 2017/10/6.
 */

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}

package com.grule.web.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018/03/05.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("common")
public class CommonController {

    @RequestMapping("/")
    @ResponseBody
    public String getByKey(String key) {
        return "i'm fun~";
    }
}

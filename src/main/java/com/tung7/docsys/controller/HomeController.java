package com.tung7.docsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/1.
 * @update
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUI() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexUI() {
        return "index";
    }
}

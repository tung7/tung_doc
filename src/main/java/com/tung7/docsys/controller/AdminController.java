package com.tung7.docsys.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tung7.docsys.bean.vo.NavVO;
import com.tung7.docsys.bean.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @ModelAttribute("navRoot")
//    @ResponseBody
    public NavVO navList() throws JsonProcessingException {
        NavVO root = new NavVO().setName("管理首页").setHref("/admin");
        List<NavVO> navList = new ArrayList<>();
        navList.add(new NavVO().setName("文档首页").setHref("/"));
        navList.add(new NavVO().setName("文章管理").setSubNavs(
                new ArrayList<NavVO>(){{
                    add(new NavVO().setName("新增文章").setHref("/admin/article/add"));
                    add(new NavVO().setName("文章列表").setHref("/admin/article/list"));
                }}
        ));
        navList.add(new NavVO().setName("类别管理").setHref("/admin/category/list"));
        navList.add(new NavVO().setName("角色管理").setHref("/admin/role/list"));
        navList.add(new NavVO().setName("权限管理").setHref("/admin/permission/list"));
        navList.add(new NavVO().setName("用户管理").setHref("/admin/user/list"));
        navList.add(new NavVO().setName("数据字典").setHref("/admin/dictionary/list"));
        navList.add(new NavVO().setName("配置管理").setHref("/admin/config/list"));
//        ObjectMapper mapper = new ObjectMapper();
//        String rtn = mapper.writeValueAsString(root.setSubNavs(navList));
//        System.out.println(rtn);
//        return rtn;
        return root.setSubNavs(navList);
    }

    @RequestMapping(value = {"/index", ""}, method = RequestMethod.GET)
    public String indexUI() {
        return "admin/index";
    }

    @RequestMapping(value = {"/temp"}, method = RequestMethod.GET)
    public String tempUI() {
        return "admin/temp";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String userUI() {
        return "admin/user";
    }

    @RequestMapping(value = "/permission/list", method = RequestMethod.GET)
    public String permissionUI() {
        return "admin/permission";
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public String roleUI() {
        return "admin/role";
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public String categoryUI() {
        return "admin/category";
    }

    @RequestMapping(value = "/article/add", method = RequestMethod.GET)
    public String articleAddUI() {
        return "admin/article_add";
    }
}

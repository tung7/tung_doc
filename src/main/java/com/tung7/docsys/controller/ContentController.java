package com.tung7.docsys.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tung7.docsys.bean.vo.ArticleVO;
import com.tung7.docsys.bean.vo.NavVO;
import com.tung7.docsys.bean.vo.UserVO;
import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.support.utils.DocSystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/1.
 * @update
 */
@Controller
public class ContentController {
    private NavVO navRoot() {
        NavVO root = new NavVO().setName("Docker").setHref("/category/1");
        List<NavVO> navList = new ArrayList<>();
        navList.add(new NavVO().setName("Docker简介").setSubNavs(
                new ArrayList<NavVO>(){{
                    add(new NavVO().setName("Docker 守护进程").setHref("/admin/article/add"));
                    add(new NavVO().setName("Docker 客户端").setHref("/admin/article/list"));
                    add(new NavVO().setName("Docker 内部").setSubNavs(
                            new ArrayList<NavVO>(){{
                                add(new NavVO().setName("Docker 镜像").setHref("/admin/article/list"));
                                add(new NavVO().setName("Docker 仓库").setHref("/admin/article/list"));
                                add(new NavVO().setName("Docker 容器").setHref("/admin/article/list"));
                            }}
                    ));
                    add(new NavVO().setName("libcontainer").setHref("/admin/article/list"));
                    add(new NavVO().setName("命名空间「Namespaces」").setHref("///"));
                    add(new NavVO().setName("资源配额「cgroups」").setHref("/admin/article/list"));
                }}
        ));
        navList.add(new NavVO().setName("Docker 安装").setSubNavs(
                new ArrayList<NavVO>(){{
                    add(new NavVO().setName("Ubuntu").setHref("/admin/article/add"));
                    add(new NavVO().setName("CentOS").setHref("/admin/article/list"));
                }}
        ));
        navList.add(new NavVO().setName("Docker 基础用法").setHref("///"));
        navList.add(new NavVO().setName("Docker 命令帮助").setHref("/admin/permission/list"));
        navList.add(new NavVO().setName("Docker 端口映射").setHref("/admin/user/list"));
        navList.add(new NavVO().setName("Docker 网络配置").setHref("/admin/dictionary/list"));
        navList.add(new NavVO().setName("Dockerfile").setHref("/admin/config/list"));
        navList.add(new NavVO().setName("容器数据管理").setHref("/admin/config/list"));
        navList.add(new NavVO().setName("链接容器").setHref("/admin/config/list"));
        navList.add(new NavVO().setName("构建私有库").setHref("/admin/config/list"));

        return root.setSubNavs(navList);
    }

    @ModelAttribute("curUser")
    public UserVO curUser() {
        DocUser user = DocSystemUtils.getCurrentUser();
        if (user == null)
            return null;
        return new UserVO().setId(user.getId())
                .setNickname(user.getNickname())
                .setEmail(user.getEmail())
                .setPhoto(user.getPhoto())
                .setUsername(user.getUsername());
    }

    @ModelAttribute("config")
    public Map<String, Serializable> config() {
        return DocSystemUtils.getSystemConfigs();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUI() {
        return "content/login";
    }

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String indexUI() {
        return "content/index";
    }

    @RequestMapping(value = "/category/{cid}", method = RequestMethod.GET)
    public String categoryMainUI(@PathVariable("cid") Long cid, ModelMap modelMap) {
        modelMap.addAttribute("navRoot", navRoot());
        return "content/detail";
    }

    @RequestMapping(value = "/category/{cid}/article/{id}", method = RequestMethod.GET)
    public String postUI(@PathVariable("cid") Long cid, @PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("navRoot", navRoot());
        modelMap.addAttribute("history", new ArrayList<NavVO>());
        modelMap.addAttribute("article", new ArticleVO());
        return "content/detail";
    }

    @RequestMapping("/category/{cid}/post/add")
    public String postAddUI(@PathVariable("cid") Long cid, ModelMap modelMap) {
        modelMap.addAttribute("navRoot", navRoot());
        return "content/article_add";
    }

    @RequestMapping("/showDetailPart")
    public String showDetailPart(ModelMap modelMap) {
        modelMap.addAttribute("articleTitle", "detail Part title");
        return "content/detail :: #main";
    }
}

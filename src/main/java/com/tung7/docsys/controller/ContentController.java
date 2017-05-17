package com.tung7.docsys.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tung7.docsys.bean.vo.ArticleVO;
import com.tung7.docsys.bean.vo.NavVO;
import com.tung7.docsys.bean.vo.UserVO;
import com.tung7.docsys.entity.*;
import com.tung7.docsys.service.inf.IArticleService;
import com.tung7.docsys.service.inf.IArticleVersionService;
import com.tung7.docsys.service.inf.ICategoryService;
import com.tung7.docsys.service.inf.IGroupService;
import com.tung7.docsys.support.excpetion.NotFoundException;
import com.tung7.docsys.support.utils.DocSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @Autowired
    IGroupService groupService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IArticleService articleService;
    @Autowired
    IArticleVersionService articleVersionService;

    private NavVO navRoot() {
        NavVO root = new NavVO().setName("Docker").setType(NavVO.NavType.LINK);
        List<NavVO> navList = new ArrayList<>();
        navList.add(new NavVO().setName("Docker简介").setType(NavVO.NavType.FOLDER).setSubNavs(
                new ArrayList<NavVO>(){{
                    add(new NavVO().setName("Docker 守护进程").setType(NavVO.NavType.LINK));
                    add(new NavVO().setName("Docker 客户端").setType(NavVO.NavType.LINK));
                    add(new NavVO().setName("Docker 内部").setType(NavVO.NavType.FOLDER).setSubNavs(
                            new ArrayList<NavVO>(){{
                                add(new NavVO().setName("Docker 镜像").setType(NavVO.NavType.LINK));
                                add(new NavVO().setName("Docker 仓库").setType(NavVO.NavType.LINK));
                                add(new NavVO().setName("Docker 容器").setType(NavVO.NavType.LINK));
                            }}
                    ));
                    add(new NavVO().setName("libcontainer").setType(NavVO.NavType.LINK));
                    add(new NavVO().setName("命名空间「Namespaces」").setType(NavVO.NavType.LINK));
                    add(new NavVO().setName("资源配额「cgroups」").setType(NavVO.NavType.LINK));
                }}
        ));
        navList.add(new NavVO().setName("Docker 安装").setType(NavVO.NavType.FOLDER).setSubNavs(
                new ArrayList<NavVO>(){{
                    add(new NavVO().setName("Ubuntu").setType(NavVO.NavType.LINK));
                    add(new NavVO().setName("CentOS").setType(NavVO.NavType.LINK));
                }}
        ));
        navList.add(new NavVO().setName("Docker 基础用法").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("Docker 命令帮助").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("Docker 端口映射").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("Docker 网络配置").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("Dockerfile").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("容器数据管理").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("链接容器").setType(NavVO.NavType.LINK));
        navList.add(new NavVO().setName("构建私有库").setType(NavVO.NavType.LINK));

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
    public String indexUI(ModelMap modelMap) {
        /* 处理顶级分组 */
        List<DocGroup> groups = groupService.findAllOrderByTaxis();


        modelMap.put("groups", groups);
        return "content/index";
    }
    private NavVO getNavsRoot( DocCategory topCategory) {
        if (topCategory == null) {
            throw new NotFoundException("找不到相应类别");
        }
        Set<DocResource> resourceSet = topCategory.getResourceSet();

        NavVO root = new NavVO().setId(topCategory.getId()).setName(topCategory.getName()).setHref("/category/" + topCategory.getId());
        List<NavVO> subNavs = root.getSubNavs();
        for (DocResource item : resourceSet) {
            subNavs.add(recurseTransNavVO(item));
        }
        return root;
    }

    @RequestMapping(value = "/category/{cid}", method = RequestMethod.GET)
    public String categoryMainUI(@PathVariable("cid") Long cid, ModelMap modelMap) {
        DocCategory topCategory = categoryService.findById(cid);
//        root.setSubNavs(subNavs);

//        modelMap.addAttribute("navRoot", navRoot());
        modelMap.addAttribute("navRoot", getNavsRoot(topCategory));
        modelMap.addAttribute("topCategory", topCategory);
        return "content/detail";
    }

    private NavVO recurseTransNavVO(DocResource resource) {
        Set<DocResource> resourceSet = resource.getResourceSet();

        NavVO root = new NavVO().setId(resource.getId()).setName(resource.getName());
        if (resource instanceof DocArticle) {
            root.setType(NavVO.NavType.LINK);
            return root;
        }

        root.setType(NavVO.NavType.FOLDER);
        List<NavVO> subNavs = root.getSubNavs();
        for (DocResource item : resourceSet) {
            subNavs.add(recurseTransNavVO(item));
        }
        return root;
    }



    @RequestMapping(value = "/category/{cid}/article/{id}", method = RequestMethod.GET)
    public String postUI(@PathVariable("cid") Long cid, @PathVariable("id") Long id, ModelMap modelMap) {
        DocCategory topCategory = categoryService.findById(cid);

        DocArticle docArticle = articleService.findById(id);
        if (docArticle == null) {
            throw new NotFoundException("没有找到指定文章！");
        }
        DocArticleVersion head = articleVersionService.findById(docArticle.getHeadVersion());
        if (head == null) {
            throw new NotFoundException("没有找到文章head版本！");
        }
        ArticleVO vo = new ArticleVO()
                .setId(docArticle.getId())
                .setTitle(docArticle.getName())
                .setCategory((DocCategory) docArticle.getParent())
                .setVersionAlias(head.getVersion())
                .setContent(head.getContent())
                .setVersionComment(head.getComment());

        modelMap.addAttribute("navRoot", getNavsRoot(topCategory));
        modelMap.addAttribute("topCategory", topCategory);
        modelMap.addAttribute("history", new ArrayList<NavVO>());
        modelMap.addAttribute("article", vo);
        return "content/detail";
    }

    @RequestMapping("/category/{cid}/post/add")
    public String postAddUI(@PathVariable("cid") Long cid, ModelMap modelMap) {
        DocCategory topCategory = categoryService.findById(cid);

        if (topCategory == null) {
            throw new NotFoundException("找不到相应类别");
        }

        modelMap.addAttribute("topCategory", topCategory);
        modelMap.addAttribute("navRoot", navRoot());
        return "content/article_add";
    }

    @RequestMapping("/showDetailPart")
    public String showDetailPart(ModelMap modelMap) {
        modelMap.addAttribute("articleTitle", "detail Part title");
        return "content/detail :: #main";
    }
}

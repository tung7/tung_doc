package com.tung7.docsys.rest;

import com.tung7.docsys.bean.vo.JsonResult;
import com.tung7.docsys.bean.vo.NavVO;
import com.tung7.docsys.entity.DocArticle;
import com.tung7.docsys.entity.DocCategory;
import com.tung7.docsys.entity.DocResource;
import com.tung7.docsys.service.inf.ICategoryService;
import com.tung7.docsys.support.utils.DocSystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/10.
 * @update
 */
@RestController
@RequestMapping("/api")
public class CategoryRest {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRest.class);

    @Autowired
    ICategoryService categoryService;

    @RequestMapping("/category/{id}")
    public JsonResult<DocCategory> getCategory(@PathVariable("id")Long id, JsonResult<DocCategory> jr) {
        DocCategory category = categoryService.findById(id);
        jr.setData(category);
        return jr;
    }

    @RequestMapping(value = "/admin/category/save", method = RequestMethod.POST)
    public JsonResult<DocCategory> saveTop(DocCategory category, JsonResult<DocCategory> jr) {
        DocCategory c;
        try {
            c = categoryService.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            jr.setMessage(e.getClass().getName());
            return jr;
        }
        jr.setSuccess(true);
        jr.setData(c);
        return  jr;
    }

    @RequestMapping(value = "/admin/category/check-name")
    public JsonResult<Boolean> checkName(String name, JsonResult<Boolean> jr) {
        DocCategory c = categoryService.findByName(name);
        if (c == null) {
            jr.setData(false);
            jr.setMessage("名称不重复");
        } else {
            jr.setData(true);
            jr.setMessage("名称重复");
        }
        return jr;
    }


    /**
     * 获取(某顶级)类别下的 所有类别, 用于新增编辑分类选择父类别
     * @param pid
     * @param jr
     * @return
     */
    @RequestMapping(value = "/admin/category/sub-cate/{pid}")
    public JsonResult<NavVO> getSubCates(@PathVariable("pid") Long pid, JsonResult<NavVO> jr) {
        DocCategory parent = categoryService.findById(pid);
        if (parent == null) {
            jr.setMessage("没有找到类别");
            return jr;
        }
        NavVO root = recurseDocResourceOnlyCategory(parent);
        jr.setSuccess(true).setData(root);
        return jr;
    }

    private NavVO recurseDocResourceOnlyCategory(DocResource parent) {
        NavVO root = new NavVO().setId(parent.getId()).setName(parent.getName());
        if (parent instanceof DocArticle) {
            root.setType(NavVO.NavType.LINK);
            return root;
        }
        root.setType(NavVO.NavType.FOLDER);
        List<NavVO> list = root.getSubNavs();

        Set<DocResource> subSets = parent.getResourceSet();
        for (DocResource item : subSets) {
            list.add(recurseDocResourceOnlyCategory(item));
        }
        //好像要不要都无所谓
//        root.setSubNavs(list);
        return root;
    }



}

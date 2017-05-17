package com.tung7.docsys.support.utils;

import com.tung7.docsys.entity.DocGroup;
import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.service.inf.ICategoryService;
import com.tung7.docsys.service.inf.IConfigService;
import com.tung7.docsys.service.inf.IGroupService;
import com.tung7.docsys.service.inf.ISystemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/4.
 * @update
 */
@Component
public class DocSystemUtils {

    private static IConfigService configService;
    private static ISystemService systemService;
    private static ICategoryService categoryService;
    private static IGroupService groupService;

    @Autowired
    public void setGroupService(IGroupService groupService) {
        DocSystemUtils.groupService = groupService;
    }

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        DocSystemUtils.categoryService = categoryService;
    }

    @Autowired
    public void setConfigService(IConfigService configService) {
        DocSystemUtils.configService = configService;
    }

    @Autowired
    public void setSystemService(ISystemService systemService) {
        DocSystemUtils.systemService = systemService;
    }

    /**
     * 获取当前登录用户
     * @return null if there has not any logined user;
     */
    public static DocUser getCurrentUser() {
        return  (DocUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取系统的所有配置。这些配置对应数据库配置表。
     * @return
     */
    public static Map<String, Serializable> getSystemConfigs() {
        return configService.findAll2Map();
    }

//    /**
//     * 获取所有category的字典
//     * @return
//     */
//    public static Map<String, Long> getCategoriesMap() {
//        return categoryService.findAllForMap();
//    }

//    /**
//     * 获取指定类别的子类别集合的字典
//     * @param id
//     * @return
//     */
//    public static Map<String, Long> getCategoriesMap(Long id) {
//        return categoryService.findSubForMap(id);
//    }

    /**
     * TODO 处理未分组。。。 top category 未分组。
     * @return
     */
    public static List<DocGroup> getCategoryGroups() {
        List<DocGroup> groups = groupService.findAllOrderByTaxis();
        return groups;
    }


    /**
     * 系统初次运行的时候，执行初始化数据。
     */
    public static void initDB() {

        systemService.initDB();
    }

}

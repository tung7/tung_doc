package com.tung7.docsys.support.utils;

import com.tung7.docsys.entity.DocConfig;
import com.tung7.docsys.entity.DocPermission;
import com.tung7.docsys.entity.DocRole;
import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.service.inf.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /**
     * 系统初次运行的时候，执行初始化数据。
     */
    public static void initDB() {

        systemService.initDB();
    }

}

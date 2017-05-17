package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.*;
import com.tung7.docsys.service.inf.*;
import com.tung7.docsys.support.constant.ConfigKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/6.
 * @update
 */
@Service
@Transactional
public class SystemService implements ISystemService{
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IRoleService roleService;
    @Autowired
    IUserService userService;
    @Autowired
    IConfigService configService;
    @Autowired
    IGroupService groupService;

    @Override
    public void initDB() {
        DocConfig config = configService.findByKey(ConfigKeyConstant.INTI_FLAG);
        if (config != null) { // no matter what the value is。
            return;
        }

         /* 插入基础权限 */
        Set<DocPermission> permissionSet = new HashSet<DocPermission>(){{
            add(new DocPermission().setName("admin").setCode("admin").setDescription("后台管理权限"));
        }};
        List<DocPermission> permissionList = permissionService.saveAll(permissionSet);

        /* 插入基础角色 */
        Set<DocRole> roleSet = new HashSet<>();
        roleSet.add(new DocRole().setName("admin").setDescription("管理员角色").setPermissionSet(new HashSet(permissionList)));
        roleService.saveAll(roleSet);


        /* 插入超级管理员  */
        DocUser docUser = new DocUser()
                .setUsername("admin")
                .setPassword("admin")
                .setNickname("admin")
                .setRoleSet(roleSet);
        userService.save(docUser);

         /* 插入类别未分组  */
        DocGroup docGroup = new DocGroup()
                .setName("未分组")
                .setTaxis(0L);
        groupService.save(docGroup);


         /* 插入基础配置 */
        Set<DocConfig> configSet = new HashSet<DocConfig>() {{
            add(new DocConfig(ConfigKeyConstant.INTI_FLAG, "1"));
            add(new DocConfig(ConfigKeyConstant.TITLE, "Tung Docs"));
            add(new DocConfig(ConfigKeyConstant.SLOGAN_TITLE, "Tung Docs"));
            add(new DocConfig(ConfigKeyConstant.SLOGAN_CONTENT, "Do whatever you want when you want to."));
            add(new DocConfig(ConfigKeyConstant.COLOPHON, "Copyright © 2015-2017 Tung. All rights reserved. | 粤ICP备xxxxx号"));
        }};
        configService.saveAll(configSet);

    }
}

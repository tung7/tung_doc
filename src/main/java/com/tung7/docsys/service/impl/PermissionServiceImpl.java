package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocPermission;
import com.tung7.docsys.repositories.PermissionRepository;
import com.tung7.docsys.service.inf.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public List<DocPermission> saveAll(Iterable<DocPermission> iterable) {
        return permissionRepository.save(iterable);
    }
}

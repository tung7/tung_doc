package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocRole;
import com.tung7.docsys.repositories.RoleRepository;
import com.tung7.docsys.service.inf.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<DocRole> saveAll(Iterable<DocRole> iterable) {
        return roleRepository.save(iterable);
    }
}

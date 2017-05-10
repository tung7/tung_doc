package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.repositories.UserRepository;
import com.tung7.docsys.service.inf.IUserService;
import com.tung7.docsys.shiro.DocShiroRealm;
import com.tung7.docsys.support.PageBean;
import com.tung7.docsys.support.utils.Digests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/30.
 * @update
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public DocUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public DocUser save(DocUser user) {
        String plainPass = user.getPassword();
        byte[] saltBytes = Digests.generateSalt();
        byte[] hashPassword = Digests.digest(plainPass.getBytes(), DocShiroRealm.DEFAUL_HASH_ALGORITHM, saltBytes, DocShiroRealm.DEFAUL_HASH_ITERATION);

        user.setPassword(Digests.encodeHex(hashPassword));
        user.setSalt(Digests.encodeHex(saltBytes));
        return userRepository.save(user);
    }

    @Override
    public PageBean<DocUser> findAll(PageBean<DocUser> pageBean) {
        Page<DocUser> page = userRepository.findAll(new PageRequest(pageBean.getCurPage()-1,pageBean.getLineSize()));
        pageBean.setList(page.getContent());
        pageBean.setItemSum(page.getTotalElements());
        return pageBean;
    }
}

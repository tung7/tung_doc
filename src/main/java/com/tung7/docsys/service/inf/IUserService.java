package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.support.PageBean;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/30.
 * @update
 */

public interface IUserService {
    DocUser findByUsername(String username);

    DocUser save(DocUser user);

    PageBean<DocUser> findAll(PageBean<DocUser> pageBean);

}

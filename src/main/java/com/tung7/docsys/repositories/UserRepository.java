package com.tung7.docsys.repositories;

import com.tung7.docsys.entity.DocUser;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
public interface UserRepository extends BaseRepository<DocUser, Long> {
    DocUser findByUsername(String username);
}

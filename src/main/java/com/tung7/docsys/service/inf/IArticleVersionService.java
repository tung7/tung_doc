package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocArticleVersion;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/16.
 * @update
 */

public interface IArticleVersionService {
    DocArticleVersion save(DocArticleVersion version);

    DocArticleVersion findById(Long id);
}

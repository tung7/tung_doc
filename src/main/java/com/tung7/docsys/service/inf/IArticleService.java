package com.tung7.docsys.service.inf;

import com.tung7.docsys.bean.vo.ArticleVO;
import com.tung7.docsys.entity.DocArticle;

import javax.print.Doc;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/16.
 * @update
 */

public interface IArticleService {
    DocArticle update(DocArticle article);

    /**
     * 新建文章， 应该同时新建版本
     * @param vo
     * @return
     */
    DocArticle add(ArticleVO vo);

    DocArticle save(DocArticle docArticle);

    DocArticle findById(Long id);
}

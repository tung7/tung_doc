package com.tung7.docsys.service.impl;

import com.tung7.docsys.bean.vo.ArticleVO;
import com.tung7.docsys.entity.DocArticle;
import com.tung7.docsys.entity.DocArticleVersion;
import com.tung7.docsys.repositories.ArticleRepository;
import com.tung7.docsys.repositories.ArticleVersionRepository;
import com.tung7.docsys.repositories.ResourceRepository;
import com.tung7.docsys.service.inf.IArticleService;
import com.tung7.docsys.service.inf.IArticleVersionService;
import com.tung7.docsys.support.utils.DocSystemUtils;
import com.tung7.docsys.support.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/16.
 * @update
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleVersionRepository articleVersionRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    IArticleVersionService articleVersionService;

    @Override
    public DocArticle update(DocArticle article) {
        return null;
    }

    @Override
    public DocArticle add(ArticleVO vo) {

        DocArticle article = new DocArticle();
        article.setParent(vo.getCategory());
        article.setName(vo.getTitle());
        article.setForcePass(vo.getForcePass());
        article.setPassword(vo.getPassword());
        article.setCreator(DocSystemUtils.getCurrentUser());
        article.setCreateTime(new Date());
        article.setTaxis(Utils.getNextTaxis(resourceRepository.findMaxTaxis()));
        article = resourceRepository.save(article);

        DocArticleVersion version = new DocArticleVersion();
        version.setContent(vo.getContent());
        version.setArticle(article);
        version.setPoster(article.getCreator());
        version.setPostTime(article.getCreateTime());
        version.setVersion(vo.getVersionAlias());
        version.setComment(vo.getVersionComment());
        version = articleVersionService.save(version);

        article.setHeadVersion(version.getId());
        return article;
    }

    @Override
    public DocArticle save(DocArticle docArticle) {
        if (docArticle.getTaxis() == null) {
            docArticle.setTaxis(Utils.getNextTaxis(resourceRepository.findMaxTaxis()));
        }
        return resourceRepository.save(docArticle);
    }

    @Override
    public DocArticle findById(Long id) {
        return articleRepository.findOne(id);
    }
}

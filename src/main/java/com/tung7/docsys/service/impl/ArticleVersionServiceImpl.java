package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocArticleVersion;
import com.tung7.docsys.repositories.ArticleVersionRepository;
import com.tung7.docsys.service.inf.IArticleVersionService;
import com.tung7.docsys.support.excpetion.RepeatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ArticleVersionServiceImpl implements IArticleVersionService{
    @Autowired
    ArticleVersionRepository articleVersionRepository;

    @Override
    public DocArticleVersion save(DocArticleVersion version) {
        return articleVersionRepository.save(version);
    }

    @Override
    public DocArticleVersion findById(Long id) {
        return articleVersionRepository.findOne(id);
    }
}

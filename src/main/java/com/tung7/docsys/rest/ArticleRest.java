package com.tung7.docsys.rest;

import com.tung7.docsys.bean.vo.ArticleVO;
import com.tung7.docsys.bean.vo.JsonResult;
import com.tung7.docsys.entity.DocArticle;
import com.tung7.docsys.entity.DocArticleVersion;
import com.tung7.docsys.service.inf.IArticleService;
import com.tung7.docsys.service.inf.IArticleVersionService;
import com.tung7.docsys.support.utils.DocSystemUtils;
import com.tung7.docsys.support.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/14.
 * @update
 */
@RestController
@RequestMapping("/api")
public class ArticleRest {
    private static final Logger logger = LoggerFactory.getLogger(ArticleRest.class);

    @Autowired
    IArticleService articleService;
    @Autowired
    IArticleVersionService articleVersionService;

    @RequestMapping(value = "/admin/article/save", method = RequestMethod.POST)
    public JsonResult<ArticleVO> save(ArticleVO vo, JsonResult<ArticleVO> jr) {


        if (vo.getId() == null) { //新增文章

            DocArticle article = new DocArticle();
//            article.setParent(vo.getCategory());
//            article.setName(vo.getTitle());
//            article.setForcePass(vo.getForcePass());
//            article.setPassword(vo.getPassword());
//            article.setCreator(DocSystemUtils.getCurrentUser());
//            article.setCreateTime(new Date());
//            article = articleService.save(article);
//
//            DocArticleVersion version = new DocArticleVersion();
//            version.setContent(vo.getContent());
//            version.setArticle(article);
//            version.setPoster(article.getCreator());
//            version.setPostTime(article.getCreateTime());
//            version.setVersion(vo.getVersionAlias());
//            version.setComment(vo.getVersionComment());
//            version = articleVersionService.save(version);
//
//            article = articleService.save(article.setHeadVersion(version.getId()));
            article = articleService.add(vo);

            Long id = article.getId();
            jr.setSuccess(true);
            jr.setData(vo.setId(id));
        } else {
            // 编辑文章，新增版本

            // 编辑文章，修改版本
        }

        return jr;
    }
}

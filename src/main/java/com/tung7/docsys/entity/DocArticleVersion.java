package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/30.
 * @update
 */
@Entity
@Table(name = "tdoc_article_version")
public class DocArticleVersion extends IdEntity {

    /**
     * 版本对应的文章
     */
    @ManyToOne
    @JoinColumn(name = "article_id")
    private  DocArticle article;

    /**
     * 该版本的文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 该版本的文章内容
     */
    @Column(name = "content", length = 5000)
    private String content;

    /**
     * 文章版本号
     */
    @Column(name = "version", length = 127)
    private String version;


    /**
     * 发布该文章版本的用户
     */
    @ManyToOne
    @JoinColumn(name = "poster_id")
    private DocUser poster;


    /**
     * 发布该文章版本的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date postTime;



    public String getTitle() {
        return title;
    }

    public DocArticleVersion setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public DocArticleVersion setContent(String content) {
        this.content = content;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public DocArticleVersion setVersion(String version) {
        this.version = version;
        return this;
    }


    public DocUser getPoster() {
        return poster;
    }

    public DocArticleVersion setPoster(DocUser poster) {
        this.poster = poster;
        return this;
    }

    public Date getPostTime() {
        return postTime;
    }

    public DocArticleVersion setPostTime(Date postTime) {
        this.postTime = postTime;
        return this;
    }

    public DocArticle getArticle() {
        return article;
    }

    public DocArticleVersion setArticle(DocArticle article) {
        this.article = article;
        return this;
    }
}

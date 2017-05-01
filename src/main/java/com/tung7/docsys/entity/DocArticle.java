package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/30.
 * @update
 */
@Entity
@Table(name = "tdoc_article")
public class DocArticle extends IdEntity {

    /**
     * 匿名用户访问文章时的密码。
     */
    @Column(name = "password")
    private String password;

    /**
     * 当前文章对应的head版本号
     */
    @Column(name = "head_version", length = 127)
    private String headVersion;

    /**
     * 创建这篇文章的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    /**
     * 创建这篇文章的用户（根据文章id做唯一标志）
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private DocUser creator;

    public String getPassword() {
        return password;
    }

    public DocArticle setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * 文章下的版本
     */
    @JsonIgnore()
    @OneToMany(mappedBy = "article")
    private Set<DocArticleVersion> articleVersionSet = new HashSet<>();

    /**
     * 该文章对应的类别
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private DocCategory category;


    public String getHeadVersion() {
        return headVersion;
    }

    public DocArticle setHeadVersion(String headVersion) {
        this.headVersion = headVersion;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DocArticle setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public DocUser getCreator() {
        return creator;
    }

    public DocArticle setCreator(DocUser creator) {
        this.creator = creator;
        return this;
    }

    public DocCategory getCategory() {
        return category;
    }

    public DocArticle setCategory(DocCategory category) {
        this.category = category;
        return this;
    }

    public Set<DocArticleVersion> getArticleVersionSet() {
        return articleVersionSet;
    }

    public DocArticle setArticleVersionSet(Set<DocArticleVersion> articleVersionSet) {
        this.articleVersionSet = articleVersionSet;
        return this;
    }
}

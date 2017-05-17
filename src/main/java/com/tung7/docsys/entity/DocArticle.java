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
@DiscriminatorValue("ARTICLE")
public class DocArticle extends DocResource {

    /**
     * 匿名用户访问文章时的密码。
     */
    @Column(name = "password")
    private String password;

    @Column(name = "force_pass")
    private Boolean forcePass;

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

    @Column(name = "head_version")
    private Long headVersion;


    /**
     * 文章下的版本
     */
    @JsonIgnore
    @OneToMany(mappedBy = "article")
    @OrderBy("id DESC")
    private Set<DocArticleVersion> articleVersionSet = new HashSet<>();


    public Long getHeadVersion() {
        return headVersion;
    }

    public DocArticle setHeadVersion(Long headVersion) {
        this.headVersion = headVersion;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DocArticle setPassword(String password) {
        this.password = password;
        return this;
    }


    public Boolean getForcePass() {
        return forcePass;
    }

    public DocArticle setForcePass(Boolean forcePass) {
        this.forcePass = forcePass;
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


    public Set<DocArticleVersion> getArticleVersionSet() {
        return articleVersionSet;
    }

    public DocArticle setArticleVersionSet(Set<DocArticleVersion> articleVersionSet) {
        this.articleVersionSet = articleVersionSet;
        return this;
    }
}

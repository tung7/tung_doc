package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@Entity
@DiscriminatorValue("CATEGORY")
public class DocCategory extends DocResource {

    /**
     * 类别的描述简介, only for 顶级类别
     */
    @Column(name = "description", length = 1000)
    private String description;

    /**
     * 该类别下的文章总数， 不包含子类别。
     */
    @Column(name = "anum", length = 10)
    private Long articleNum;

    /**
     * 类别下的文章集合 不包含子类别的文章
     */
    @OneToMany
    private Set<DocArticle> articlesSet = new HashSet<>();

    /**
     * 仅用于top类别。用于标志所属分组
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private DocGroup group;

    public String getDescription() {
        return description;
    }

    public DocCategory setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getArticleNum() {
        return articleNum;
    }

    public DocCategory setArticleNum(Long articleNum) {
        this.articleNum = articleNum;
        return this;
    }

    public Set<DocArticle> getArticlesSet() {
        return articlesSet;
    }

    public DocCategory setArticlesSet(Set<DocArticle> articlesSet) {
        this.articlesSet = articlesSet;
        return this;
    }

    public DocGroup getGroup() {
        return group;
    }

    public DocCategory setGroup(DocGroup group) {
        this.group = group;
        return this;
    }
}

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
@Table(name = "tdoc_category")
public class DocCategory extends IdEntity {
    /**
     * 父级类别
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    private DocCategory parent;

    /**
     * 类别的名称
     */
    @Column(name = "cname")
    private String name;
    /**
     * 类别的描述简介
     */
    @Column(name = "description", length = 1000)
    private String description;

    /**
     * 该类别下的文章总数， 不包含子类别。
     */
    @Column(name = "anum", length = 10)
    private Long articleNum;

    /**
     * 类别下的文章
     */
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<DocArticle> articlesSet = new HashSet<>();



    public Set<DocArticle> getArticlesSet() {
        return articlesSet;
    }

    public DocCategory setArticlesSet(Set<DocArticle> articlesSet) {
        this.articlesSet = articlesSet;
        return this;
    }

    public DocCategory getParent() {
        return parent;
    }

    public DocCategory setParent(DocCategory parent) {
        this.parent = parent;
        return this;
    }

    public String getName() {
        return name;
    }

    public DocCategory setName(String name) {
        this.name = name;
        return this;
    }

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
}

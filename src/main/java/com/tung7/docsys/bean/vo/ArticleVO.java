package com.tung7.docsys.bean.vo;

import com.tung7.docsys.entity.DocCategory;
import com.tung7.docsys.entity.DocResource;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/9.
 * @update
 */

public class ArticleVO {
    private Long id ;

    private String title;

    private DocCategory category;

    private String content;

    private Boolean newVersion;

    private String versionAlias;

    private String versionComment;

    private String password;

    private Boolean forcePass;

    public Long getId() {
        return id;
    }

    public ArticleVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public DocCategory getCategory() {
        return category;
    }

    public ArticleVO setCategory(DocCategory category) {
        this.category = category;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleVO setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getNewVersion() {
        return newVersion;
    }

    public ArticleVO setNewVersion(Boolean newVersion) {
        this.newVersion = newVersion;
        return this;
    }

    public String getVersionAlias() {
        return versionAlias;
    }

    public ArticleVO setVersionAlias(String versionAlias) {
        this.versionAlias = versionAlias;
        return this;
    }

    public String getVersionComment() {
        return versionComment;
    }

    public ArticleVO setVersionComment(String versionComment) {
        this.versionComment = versionComment;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ArticleVO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getForcePass() {
        return forcePass;
    }

    public ArticleVO setForcePass(Boolean forcePass) {
        this.forcePass = forcePass;
        return this;
    }
}

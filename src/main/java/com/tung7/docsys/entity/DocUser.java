package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
@Table(name = "tdoc_user")
public class DocUser extends IdEntity {
    /**
     * 用户登录帐号
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户登录密码，加密。
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户头像，地址
     */
    @Column(name = "photo", length = 511)
    private String photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tdoc_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(FetchMode.SUBSELECT) // Fecth策略定义
    @OrderBy("id ASC") // 集合按id排序
    @JsonIgnore
    private Set<DocRole> roleSet = new HashSet<DocRole>();



    public String getLoginName() {
        return loginName;
    }

    public DocUser setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public DocUser setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DocUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public DocUser setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Set<DocRole> getRoleSet() {
        return roleSet;
    }

    public DocUser setRoleSet(Set<DocRole> roleSet) {
        this.roleSet = roleSet;
        return this;
    }
}

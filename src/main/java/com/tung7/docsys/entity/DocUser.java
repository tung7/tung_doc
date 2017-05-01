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
    @Column(name = "username", unique = true)
    private String username;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    /**
     * 用户登录密码，加密。
     */
    @Column(name = "password")
    private String password;

    /**
     * 密码盐值
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 用户头像，地址
     */
    @Column(name = "photo", length = 511)
    private String photo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tdoc_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(FetchMode.SUBSELECT) // Fecth策略定义
    @OrderBy("id ASC") // 集合按id排序
    @JsonIgnore
    private Set<DocRole> roleSet = new HashSet<DocRole>();


    public String getEmail() {
        return email;
    }

    public DocUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DocUser setUsername(String username) {
        this.username = username;
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

    public String getSalt() {
        return salt;
    }

    public DocUser setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    /**
     * salt下 必需有这个方法。。。。。。。日了狗。。。
     * @return
     */
    public String getCredentialsSalt() {
        System.out.println("getCredentialsSalt");
        return salt;
    }

    public Set<DocRole> getRoleSet() {
        return roleSet;
    }

    public DocUser setRoleSet(Set<DocRole> roleSet) {
        this.roleSet = roleSet;
        return this;
    }
}

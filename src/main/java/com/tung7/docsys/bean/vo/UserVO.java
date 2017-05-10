package com.tung7.docsys.bean.vo;

import javax.persistence.Column;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/5.
 * @update
 */

public class UserVO {
    private Long id;
    /**
     * 用户登录帐号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    private String email;

    /**
     * 用户头像，地址
     */
    private String photo;

    public String getUsername() {
        return username;
    }

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public UserVO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserVO setId(Long id) {
        this.id = id;
        return this;
    }
}

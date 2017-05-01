package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
@Table(name = "tdoc_role")
public class DocRole extends IdEntity {
    /**
     * 角色名称
     */
    @Column(name = "rname", unique = true)
    private String name;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tdoc_user_role", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @Fetch(FetchMode.SUBSELECT) // Fecth策略定义
    @OrderBy("id ASC") // 集合按id排序
    @JsonIgnore
    private Set<DocUser> userSet;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tdoc_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    @Fetch(FetchMode.SUBSELECT) // Fecth策略定义
    @JsonIgnore
    private Set<DocPermission> permissionSet;


    public String getName() {
        return name;
    }

    public DocRole setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DocRole setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<DocUser> getUserSet() {
        return userSet;
    }

    public DocRole setUserSet(Set<DocUser> userSet) {
        this.userSet = userSet;
        return this;
    }

    public Set<DocPermission> getPermissionSet() {
        return permissionSet;
    }

    public DocRole setPermissionSet(Set<DocPermission> permissionSet) {
        this.permissionSet = permissionSet;
        return this;
    }
}

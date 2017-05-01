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
@Table(name = "tdoc_permission")
public class DocPermission extends IdEntity{
    @Column(name = "pname", unique = true)
    private String name;

    @Column(name = "pcode", unique = true)
    private String code;

    /**
     * 权限字符串。<br/>
     * menu例子：role:*，<br/>
     * button例子：role:create,role:update,role:delete,role:view
     */
    @Column(name = "permission")
    private String permission;



    @Column(name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tdoc_role_permission", joinColumns = {@JoinColumn(name = "permission_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(FetchMode.SUBSELECT) // Fecth策略定义
    @JsonIgnore
    private Set<DocRole> roleSet;

    public String getName() {
        return name;
    }

    public DocPermission setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DocPermission setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DocPermission setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<DocRole> getRoleSet() {
        return roleSet;
    }

    public DocPermission setRoleSet(Set<DocRole> roleSet) {
        this.roleSet = roleSet;
        return this;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public DocPermission setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public enum ResourceType{
        MENU,//菜单
        BUTTON
    }
}

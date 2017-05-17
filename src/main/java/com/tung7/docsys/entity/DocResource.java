package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/13.
 * @update
 */
@Entity
@Table(name = "tdoc_resource")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="res_type",discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue("RESOURCE")
public class DocResource extends IdEntity {
    @Column(name = "rname")
    private String name;
    @Column(name = "taxis", length = 10)
    private Long taxis;

    /**
     * 父级
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonIgnore
    private DocResource parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    @OrderBy("taxis ASC")
    private Set<DocResource> resourceSet;

    public DocResource getParent() {
        return parent;
    }

    public DocResource setParent(DocResource parent) {
        this.parent = parent;
        return this;
    }

    public Set<DocResource> getResourceSet() {
        return resourceSet;
    }

    public DocResource setResourceSet(Set<DocResource> resourceSet) {
        this.resourceSet = resourceSet;
        return this;
    }

    public String getName() {
        return name;
    }

    public DocResource setName(String name) {
        this.name = name;
        return this;
    }

    public Long getTaxis() {
        return taxis;
    }

    public DocResource setTaxis(Long taxis) {
        this.taxis = taxis;
        return this;
    }
}

package com.tung7.docsys.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/12.
 * @update
 */
@Entity
@Table(name = "tdoc_group")
public class DocGroup extends IdEntity {

    @Column(name = "taxis")
    private Long taxis;
    @Column(name = "gname")
    private String name;

    /**
     * 分组下的顶级类别集合。
     */
    @OneToMany(mappedBy = "group")
    @OrderBy("taxis ASC")
    private Set<DocCategory> topCategories;


    public Long getTaxis() {
        return taxis;
    }

    public DocGroup setTaxis(Long taxis) {
        this.taxis = taxis;
        return this;
    }

    public String getName() {
        return name;
    }

    public DocGroup setName(String name) {
        this.name = name;
        return this;
    }

    public Set<DocCategory> getTopCategories() {
        return topCategories;
    }

    public DocGroup setTopCategories(Set<DocCategory> topCategories) {
        this.topCategories = topCategories;
        return this;
    }
}

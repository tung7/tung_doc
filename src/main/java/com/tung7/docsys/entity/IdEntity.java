package com.tung7.docsys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */

@MappedSuperclass
public abstract class IdEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

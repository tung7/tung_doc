package com.tung7.docsys.entity;

import javax.persistence.*;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/30.
 * @update
 */
@Entity
@Table(name = "tdoc_dictionary")
public class DocDictionary extends IdEntity {
    /**
     * 配置项的key
     */
    @Column(name = "dkey", unique = true)
    private String key;

    /**
     * 配置项的value
     */
    @Column(name = "dvalue", length = 1000)
    private String value;

    @ManyToOne
    @JoinColumn(name = "pid")
    private DocDictionary parent;

    public String getKey() {
        return key;
    }

    public DocDictionary setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DocDictionary setValue(String value) {
        this.value = value;
        return this;
    }
}

package com.tung7.docsys.entity;

import javax.persistence.*;

/**
 * 系统配置实体类
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@Entity
@Table(name = "tdoc_config")
public class DocConfig extends IdEntity{
    /**
     * 配置项的key
     */
    @Column(name = "ckey", unique = true)
    private String key;

    /**
     * 配置项的value
     */
    @Column(name = "cvalue", length = 1000)
    private String value;

    /**
     * The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected.
     */
    protected DocConfig() {}

    public DocConfig(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public DocConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DocConfig setValue(String value) {
        this.value = value;
        return this;
    }
}
